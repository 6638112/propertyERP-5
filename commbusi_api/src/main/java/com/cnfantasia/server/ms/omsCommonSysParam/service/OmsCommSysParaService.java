package com.cnfantasia.server.ms.omsCommonSysParam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.business.pub.omsSysParam.IOmsSysParamService;
import com.cnfantasia.server.domainbase.omsCommSysPara.dao.IOmsCommSysParaBaseDao;
import com.cnfantasia.server.domainbase.omsCommSysPara.entity.OmsCommSysPara;
import com.cnfantasia.server.ms.pub.sysParam.SysParamManager;

/**
 * 描述:(Oms系统参数) 具体业务Service层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsCommSysParaService implements IOmsCommSysParaService, IOmsSysParamService {
	
	@Resource
	private IOmsCommSysParaBaseDao omsCommSysParaBaseDao;

	public void setOmsCommSysParaBaseDao(IOmsCommSysParaBaseDao omsCommSysParaBaseDao) {
		this.omsCommSysParaBaseDao = omsCommSysParaBaseDao;
	}

	@Override
	public List<Map<String, Object>> getAllSysParamListMap() {
		List<OmsCommSysPara> paramList = omsCommSysParaBaseDao.selectOmsCommSysParaByCondition(null, true);
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for (OmsCommSysPara tmpPara : paramList) {
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put(SysParamManager.SYSPARA_CODE, tmpPara.getSysParaCode());
			tmpMap.put(SysParamManager.SYSPARA_VALUE, tmpPara.getSysParaValue());
			resList.add(tmpMap);
		}
		return resList;
	}
	
}
