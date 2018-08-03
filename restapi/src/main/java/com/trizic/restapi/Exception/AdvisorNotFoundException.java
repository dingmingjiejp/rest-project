package com.trizic.restapi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  AdvisorNotFoundException is Exception that represent that the advisor id can not be found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdvisorNotFoundException extends RuntimeException {

	public AdvisorNotFoundException() {
		super("advisor.not.found");
	}

}
