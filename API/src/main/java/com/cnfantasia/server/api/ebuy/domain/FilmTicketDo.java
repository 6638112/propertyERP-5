package com.cnfantasia.server.api.ebuy.domain;

import java.io.Serializable;
import java.lang.String;

public class FilmTicketDo implements Serializable {

	private static final long serialVersionUID = 8045431738832602232L;

	private String identifyCode;
	
	private String expireTm; //f_expire_tm;

	public String getIdentifyCode() {
		return identifyCode;
	}

	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}

	public String getExpireTm() {
		return expireTm;
	}

	public void setExpireTm(String expireTm) {
		this.expireTm = expireTm;
	}
	
}
