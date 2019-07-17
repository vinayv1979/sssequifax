package com.sss.bank.validation.api.pojo;

import java.io.Serializable;





public class BankResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}

	
	private String valid;
	
	//private String bankname;
	 
	//private String address;
	 
	

}
