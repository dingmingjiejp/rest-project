package com.trizic.restapi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  AllocationPercentageTotalInvalidException is Exception that represent the total percent of allocations is not 100
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AllocationPercentageTotalInvalidException extends RuntimeException {

	public AllocationPercentageTotalInvalidException() {
		super("allocation.percentage.total.invalid");
	}

}
