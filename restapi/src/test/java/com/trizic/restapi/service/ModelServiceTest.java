package com.trizic.restapi.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.trizic.restapi.model.Advisor;
import com.trizic.restapi.model.AssetAllocation;
import com.trizic.restapi.model.Model;
import com.trizic.restapi.model.Model.ModelType;
import com.trizic.restapi.model.Model.RebalanceFrequency;
import com.trizic.restapi.model.PageList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelServiceTest {

  @Autowired
  private ModelService modelService;


  @Test
  public void testFindAdvisorById() {
    // testcase 1 : advisorId exist
    Optional<Advisor> advisor = modelService.findAdvisorById("test");
    assertThat(advisor.isPresent(), is(true));
    assertThat(advisor.get().getId(), is("test"));
    assertThat(advisor.get().getName(), is("test"));

    // testcase 2 : advisorId not exist
    advisor = modelService.findAdvisorById("notExist");
    assertThat(advisor.isPresent(), is(false));

  }

  @Test
  public void testSaveModel() {

    // testcase 1 : create new model
    Model model = Model.of(null, "test", "test", "test", 10, 10, ModelType.QUALIFIED,
        RebalanceFrequency.ANNUAL, null
    );

    List<AssetAllocation> list = new ArrayList<>();
    list.add(AssetAllocation.of(null, "test1", 10D));
    model.setAssetAllocations(list);

    Model modelReal = modelService.saveModel(model, "test");
    assertThat(modelReal, notNullValue());
    assertThat(modelReal.getId(), notNullValue());
    assertModel(modelReal, model);

    // testcase 2 : update existed model
    modelReal.setDescription("update");
    modelReal.setCashHoldingPercentage(10);
    modelReal.setRebalanceFrequency(RebalanceFrequency.MONTHLY);
    modelReal.setModelType(ModelType.TAXABLE);
    modelReal.setDriftPercentage(50);
    modelReal.getAssetAllocations().get(0).setSymbol("Test");
    modelReal.getAssetAllocations().get(0).setPercentage(10D);

    Model modelReal2 = modelService.saveModel(modelReal, "test");
    assertModel(modelReal2, modelReal);

    // testcase 3 : search test
    PageList<Model> pageList = modelService
        .findPageModelsByAdvisorId("test", PageRequest.of(0, 20));

    Model modelS = null;
    for (Model m : pageList.getPage()) {
      if (m.getName().equals("test")) {
        modelS = m;
      }
    }

    assertThat(modelS, notNullValue());
    assertModel(modelS, modelReal2);
  }

  @Test
  public void testFindPageModelsByAdvisorId() {

    //testcase 1 : search all data
    PageList<Model> pageList = modelService
        .findPageModelsByAdvisorId("test", PageRequest.of(0, 20));

    assertThat(pageList.getNumberOfPages(), is(1));
    assertThat(pageList.getPageNumber(), is(0));
    assertThat(pageList.getPageSize(), is(20));
    assertThat(pageList.getTotalNumberOfElements(), is(3L));

    //testcase 2 : paging test
    PageList<Model> pageList1 = modelService
        .findPageModelsByAdvisorId("test", PageRequest.of(0, 2));
    PageList<Model> pageList2 = modelService
        .findPageModelsByAdvisorId("test", PageRequest.of(1, 2));

    assertThat(pageList1.getPage().size(), is(2));
    assertThat(pageList2.getPage().size(), is(1));

    assertThat(pageList1.getPage().get(0).getId(), is(99991L));
    assertThat(pageList1.getPage().get(1).getId(), is(99992L));
    assertThat(pageList2.getPage().get(0).getId(), is(99993L));

  }


  private void assertModel(Model real, Model expected) {
    assertThat(real.getName(), is(expected.getName()));
    assertThat(real.getAdvisorId(), is(expected.getAdvisorId()));
    assertThat(real.getCashHoldingPercentage(), is(expected.getCashHoldingPercentage()));
    assertThat(real.getDescription(), is(expected.getDescription()));
    assertThat(real.getDriftPercentage(), is(expected.getDriftPercentage()));
    assertThat(real.getModelType(), is(expected.getModelType()));
    assertThat(real.getRebalanceFrequency(), is(expected.getRebalanceFrequency()));

    // Here only check the size of asset allocations.
    // The content will be checked on integrate test.
    assertThat(real.getAssetAllocations().size(), is(expected.getAssetAllocations().size()));
    for (int i = 0; i < real.getAssetAllocations().size() - 1; i++) {
      assertThat(real.getAssetAllocations().get(i).getSymbol(),
          is(expected.getAssetAllocations().get(i).getSymbol()));
      assertThat(real.getAssetAllocations().get(i).getPercentage(),
          is(expected.getAssetAllocations().get(i).getPercentage()));
    }


  }


}