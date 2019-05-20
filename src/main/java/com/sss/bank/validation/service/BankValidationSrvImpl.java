package com.sss.bank.validation.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.github.pauldambra.moduluschecker.ModulusChecker;

@Service
public class BankValidationSrvImpl implements BankValidationSrvIntf {

	@Override
	public Boolean validate(String sortcode, String account) {

		ModulusChecker modulusChecker;
		Boolean result = false;

		try {
			modulusChecker = new ModulusChecker();

			result = modulusChecker.checkBankAccount(sortcode, account);

			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
