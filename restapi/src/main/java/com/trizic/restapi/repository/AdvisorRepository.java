package com.trizic.restapi.repository;

import com.trizic.restapi.model.Advisor;
import com.trizic.restapi.model.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Advisor Repository Class
 */
@Repository
public interface AdvisorRepository extends CrudRepository<Advisor, String> {

}

