package com.cnfantasia.server.ms.cloudkey.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.cloudkey.dao.ICloudKeyDao;
import com.cnfantasia.server.ms.cloudkey.entity.CarPayLogList;
import com.cnfantasia.server.ms.cloudkey.entity.CloudKeyAudit;

public class CloudKeyService implements ICloudKeyService{
	private ICloudKeyDao cloudKeyDao;
	public void setCloudKeyDao(ICloudKeyDao cloudKeyDao) {
		this.cloudKeyDao = cloudKeyDao;
	}
	@Override
	public List<CloudKeyAudit> qryCloudKeyAuditList(Map<String, Object> paramMap) {
		return cloudKeyDao.qryCloudKeyAuditList(paramMap);
	}
	@Override
	public Integer qryCloudKeyAuditcount(Map<String, Object> paramMap) {
		return cloudKeyDao.qryCloudKeyAuditcount(paramMap);
	}
	@Override
	public CloudKeyAudit qryCloudKeyAuditDetail(BigInteger id) {
		return cloudKeyDao.qryCloudKeyAuditDetail(id);
	}
	@Override
	public List<CarPayLogList> qryCarPayLog(Map<String, Object> paramMap) {
		return cloudKeyDao.qryCarPayLog(paramMap);
	}
	@Override
	public Integer qryCarPayLogCount(Map<String, Object> paramMap) {
		return cloudKeyDao.qryCarPayLogCount(paramMap);
	}
	@Override
	public String qryuserEndTime(BigInteger userId) {
		return cloudKeyDao.qryuserEndTime(userId);
	}
	
	/**
	 * 查询停车费总额
	 * 
	 * @param paramMap
	 * @return
	 */
	public Long qryCarTotalPay(Map<String, Object> paramMap){
		return cloudKeyDao.qryCarTotalPay(paramMap);
	}
}
