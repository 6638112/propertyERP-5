package com.cnfantasia.server.ms.cloudkey.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.cloudkey.entity.CarPayLogList;
import com.cnfantasia.server.ms.cloudkey.entity.CloudKeyAudit;

public interface ICloudKeyService {
	public List<CloudKeyAudit> qryCloudKeyAuditList(Map<String, Object> paramMap);
	
	public Integer qryCloudKeyAuditcount(Map<String, Object> paramMap);
	
	public CloudKeyAudit qryCloudKeyAuditDetail(BigInteger id);
	
	public List<CarPayLogList> qryCarPayLog(Map<String, Object> paramMap);
	
	public Integer qryCarPayLogCount(Map<String, Object> paramMap);
	
	public String qryuserEndTime(BigInteger userId);
	
	/**
	 * 查询停车费总额
	 * 
	 * @param paramMap
	 * @return
	 */
	public Long qryCarTotalPay(Map<String, Object> paramMap);
}
