package com.cnfantasia.server.api.commonBusiness.service;

import java.util.List;

public interface ICommMobileService {

	public boolean sendMsg(List<String> mobiles, String msg);

	public boolean sendMsg(String mobile, String msg);

}
