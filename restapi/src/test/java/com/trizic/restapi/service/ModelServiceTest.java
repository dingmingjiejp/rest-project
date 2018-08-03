package com.trizic.restapi.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import com.trizic.restapi.Exception.AdvisorNotFoundException;
import com.trizic.restapi.Exception.AllocationPercentageTotalInvalidException;
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
  public void testFindAdvisorByIdAdvisorIdExist() {
    // testcase 1 : advisorId exist
    Optional<Advisor> advisor = modelService.findAdvisorById("test");
    assertThat(advisor.isPresent(), is(true));
    assertThat(advisor.get().getId(), is("test"));
    assertThat(advisor.get().getName(), is("test"));

  }

  @Test
  public void testFindAdvisorByIdAdvisorIdNotExist() {
    // testcase 2 : advisorId not exist
    Optional<Advisor> advisor = modelService.findAdvisorById("notExist");
    assertThat(advisor.isPresent(), is(false));

  }

  @Test
  public void testSaveModelCreateNewWithExistAdvisorId() {

    // testcase 1 : create new model
    Model model = Model.of(null, "0002", "test", "test", 10, 10, ModelType.QUALIFIED,
        RebalanceFrequency.ANNUAL, null
    );

    List<AssetAllocation> list = new ArrayList<>();
    list.add(AssetAllocation.of(null, "test1", 100D));
    model.setAssetAllocations(list);

    Model modelReal = modelService.saveModel(model, "0002");
    assertThat(modelReal, notNullValue());
    assertThat(modelReal.getId(), notNullValue());
    assertModel(modelReal, model);

  }

  @Test(expected = AdvisorNotFoundException.class)
  public void testSaveModelCreateNewWithNotExistAdvisorId() {

    // testcase 2 : create new model
    Model model = Model.of(null, "notExist", "test", "test", 10, 10, ModelType.QUALIFIED,
        RebalanceFrequency.ANNUAL, null
    );

    List<AssetAllocation> list = new ArrayList<>();
    list.add(AssetAllocation.of(null, "test1", 100D));
    model.setAssetAllocations(list);

    modelService.saveModel(model, "notExist");

  }


  @Test
  public void testSaveModelUpdateExistedModel() {

    Model model = Model.of(null, "0002", "test", "test", 10, 10, ModelType.QUALIFIED,
        RebalanceFrequency.ANNUAL, null
    );

    List<AssetAllocation> list = new ArrayList<>();
    list.add(AssetAllocation.of(null, "test1", 100D));
    model.setAssetAllocations(list);

    Model modelReal = modelService.saveModel(model, "0002");
    assertThat(modelReal, notNullValue());
    assertThat(modelReal.getId(), notNullValue());
    assertModel(modelReal, model);

    // testcase 2 : update existed model
    modelReal.setDescription("update");
    modelReal.setCashHoldingPercentage(10);
    modelReal.setRebalanceFrequency(RebalanceFrequency.MONTHLY);
    modelReal.setModelType(ModelType.TAXABLE);
    modelReal.setDriftPercentage(50);
    modelReal.getAssetAllocations().get(0).setSymbol("test2");
    modelReal.getAssetAllocations().get(0).setPercentage(100D);

    Model modelReal2 = modelService.saveModel(modelReal, "0002");
    assertModel(modelReal2, modelReal);

    // testcase 3 : search test
    PageList<Model> pageList = modelService
        .findPageModelsByAdvisorId("0002", PageRequest.of(0, 20));

    Model modelS = null;
    for (Model m : pageList.getPage()) {
      if (m.getName().equals("test")) {
        modelS = m;
      }
    }

    assertThat(modelS, notNullValue());
    assertModel(modelS, modelReal2);

  }


  @Test(expected = AllocationPercentageTotalInvalidException.class)
  public void testSaveModelWithAllocationInvalid() {

    Model model = Model.of(null, "test", "test", "test", 10, 10, ModelType.QUALIFIED,
        RebalanceFrequency.ANNUAL, null
    );

    List<AssetAllocation> list = new ArrayList<>();
    list.add(AssetAllocation.of(null, "test1", 10D));
    model.setAssetAllocations(list);

    modelService.saveModel(model, "test");
  }

  @Test
  public void testFindPageModelsByAdvisorIdWithAdvisorIdExisting() {

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

  @Test
  public void testFindPageModelsByAdvisorIdOnePage() {

    PageList<Model> pageList = modelService
        .findPageModelsByAdvisorId("test", PageRequest.of(0, 20));

    assertThat(pageList.getNumberOfPages(), is(1));
    assertThat(pageList.getPageNumber(), is(0));
    assertThat(pageList.getPageSize(), is(20));
    assertThat(pageList.getTotalNumberOfElements(), is(3L));

  }

  @Test
  public void testFindPageModelsByAdvisorIdTwoPages() {

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

  @Test(expected = AdvisorNotFoundException.class)
  public void testFindPageModelsByAdvisorIdWithAdvisorIdNotExisted() {

    modelService.findPageModelsByAdvisorId("NotExisted", PageRequest.of(0, 20));

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