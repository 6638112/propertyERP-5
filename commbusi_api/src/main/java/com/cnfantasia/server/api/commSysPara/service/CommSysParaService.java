package com.cnfantasia.server.api.commSysPara.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.pub.sysParam.SysParamManager;
import com.cnfantasia.server.business.pub.sysParam.ISysParamService;
import com.cnfantasia.server.domainbase.commSysPara.dao.ICommSysParaBaseDao;
import com.cnfantasia.server.domainbase.commSysPara.entity.CommSysPara;
//import com.cnfantasia.server.api.commSysPara.dao.ICommSysParaDao;
/**
 * 描述:(系统参数) 具体业务Service层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommSysParaService  implements ICommSysParaService,ISysParamService{
//	private ICommSysParaDao commSysParaDao;
//	public void setCommSysParaDao(ICommSysParaDao commSysParaDao) {
//		this.commSysParaDao = commSysParaDao;
//	}
	
	private ICommSysParaBaseDao commSysParaBaseDao;
	public void setCommSysParaBaseDao(ICommSysParaBaseDao commSysParaBaseDao) {
		this.commSysParaBaseDao = commSysParaBaseDao;
	}


	@Override
	public List<Map<String, Object>> getAllSysParamListMap() {
		List<CommSysPara> paramList = commSysParaBaseDao.selectCommSysParaByCondition(null, true);
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(CommSysPara tmpPara:paramList){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put(SysParamManager.SYSPARA_CODE, tmpPara.getSysParaCode());
			tmpMap.put(SysParamManager.SYSPARA_VALUE, tmpPara.getSysParaValue());
			resList.add(tmpMap);
		}
		return resList;
	}
	
	
	
}
