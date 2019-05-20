package com.sss.bank.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sss.bank.validation.api.pojo.BankResponse;
import com.sss.bank.validation.service.BankValidationSrvIntf;

@RestController
public class SSSEquifaxController {

	@Autowired
	private BankValidationSrvIntf bankValidationSrvIntf;

	@RequestMapping(value = "/api/sg/bank", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Object> bankValidation(
			@RequestParam(required = true, value = "sortcode") String sortcode,
			@RequestParam(required = true, value = "account") String account) {

		BankResponse bankResponse = null;

		Boolean valid = bankValidationSrvIntf.validate(sortcode, account);

		if (valid.equals(true)) {

			bankResponse = new BankResponse();
			bankResponse.setValid("Account is valid");

		} else {
			
			bankResponse = new BankResponse();
			bankResponse.setValid("Account is not valid");
		}

		return new ResponseEntity<Object>(bankResponse, HttpStatus.OK);

	}

}
