package com.sss.bank.validation.api.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;



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
	 @JsonIgnore
	private String bankname;
	 @JsonIgnore
	private String address;
	 
	

}
