package com.trizic.restapi.controller;

import com.trizic.restapi.model.Model;
import com.trizic.restapi.service.ModelService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * PortfolioResource is a class that provide the Portfolio Restful API including Put and Get.
 */
@RestController
@RequestMapping("/v1/advisor/{advisorId}/model")
public class PortfolioResource {

  @Autowired
  ModelService modelService;

  /**
   * Save or update the Model and return the updated Model as response.
   * @param id
   * @param model
   * @return ResponseEntity
   */
  @RequestMapping(method = RequestMethod.PUT)
  public ResponseEntity<Object> putMode(@PathVariable("advisorId") String id, @Valid @RequestBody Model model) {

    return new ResponseEntity<>(modelService.saveModel(model, id), HttpStatus.OK);
  }


  /**
   * return a pagination list of models searched by advisor id.
   * @param id
   * @param pageNumber
   * @param pageSize
   * @return ResponseEntity
   */
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Object>  getModels(@PathVariable("advisorId") String id,
      @RequestParam("pageNumber") Optional<Integer> pageNumber,
      @RequestParam("pageSize") Optional<Integer> pageSize) {

    PageRequest request = PageRequest.of(pageNumber.orElse(0), pageSize.orElse(20));
    return new ResponseEntity<> (modelService.findPageModelsByAdvisorId(id, request), HttpStatus.OK);
  }


}
