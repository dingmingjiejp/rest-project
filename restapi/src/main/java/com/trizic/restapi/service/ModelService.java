package com.trizic.restapi.service;


import com.trizic.restapi.model.Advisor;
import com.trizic.restapi.model.Model;
import com.trizic.restapi.model.PageList;
import com.trizic.restapi.repository.AdvisorRepository;
import com.trizic.restapi.repository.ModelRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Model Service Class
 */
@Service
public class ModelService {

  @Autowired
  ModelRepository modelRepository;

  @Autowired
  AdvisorRepository advisorRepository;

  public Optional<Advisor> findAdvisorById(String id) {
    return advisorRepository.findById(id);
  }

  /**
   * Save Model and return the saved model.
   *
   * @return Model
   */
  public Model saveModel(Model model, String advisorId) {
    model.setAdvisorId(advisorId);

    Optional<Model> m = modelRepository.findByAdvisorIdName(advisorId, model.getName());

    if (m.isPresent()) {
      model.setId(m.get().getId());
    }

    return modelRepository.save(model);
  }

  /**
   * return a PageList of models searched by advisor id
   *
   * @return PageList
   */
  public PageList<Model> findPageModelsByAdvisorId(String advisorId, PageRequest page) {

    Page<Model> pageModel = modelRepository.findPagedModelsByAdvisorIdOrderById(advisorId, page);

    PageList<Model> pageList = PageList.of(pageModel.getNumber(), pageModel.getSize(), pageModel.getTotalPages(), pageModel
        .getTotalElements(), pageModel.getContent());

    return pageList;
  }

}