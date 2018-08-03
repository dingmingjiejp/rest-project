package com.trizic.restapi.repository;

import com.trizic.restapi.model.Model;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Model Repository
 *
 */
@Repository
public interface ModelRepository extends PagingAndSortingRepository<Model, Long> {

  /**
   * Return a pagination list of models searched by advisor id.
   * @param advisorId advisor id
   * @param pageRequest page request
   * @return Page
   */
  Page<Model> findPagedModelsByAdvisorIdOrderById(String advisorId, Pageable pageRequest);

  /**
   *  return a model searched by advisor id and model name.
   * @param advisorId advisor id
   * @param name model name
   * @return Model
   */
  @Query("SELECT m FROM Model m where m.advisorId = :advisorId and m.name = :name")
  Optional<Model> findByAdvisorIdName(@Param("advisorId") String advisorId, @Param("name") String name);
}

