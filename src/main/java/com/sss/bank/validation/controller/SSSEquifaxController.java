package com.sss.bank.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sss.bank.validation.api.pojo.BankResponse;
import com.sss.bank.validation.api.pojo.ErrorResponse;
import com.sss.bank.validation.exception.ModCheckNotFoundException;
import com.sss.bank.validation.exception.ModCheckValidationException;
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
		Boolean valid = false;

		String sc = sortcode.replaceAll("\\s", "");
		String ac = account.replaceAll("\\s", "");

		if (StringUtils.hasText(sc) && sc.length() != 0) {

			if (StringUtils.hasText(ac) && ac.length() != 0) {

				valid = bankValidationSrvIntf.validate(sortcode, account);

			} else {
				throw new ModCheckValidationException("Input not correct or null");

			}
			throw new ModCheckValidationException("Input not correct or null");

		}

		if (valid.equals(true)) {

			bankResponse = new BankResponse();
			bankResponse.setValid("Account is valid");

		} else {

			bankResponse = new BankResponse();
			bankResponse.setValid("Account is not valid");
		}

		return new ResponseEntity<Object>(bankResponse, HttpStatus.OK);

	}

	@ExceptionHandler(ModCheckValidationException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

	@ExceptionHandler(ModCheckNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler1(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

}
