package com.cnfantasia.server.ms.cloudkey.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.cnfantasia.server.ms.cloudkey.entity.CarPayLogList;
import com.cnfantasia.server.ms.cloudkey.entity.CloudKeyAudit;

public class CloudKeyDao implements ICloudKeyDao{
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public List<CloudKeyAudit> qryCloudKeyAuditList(Map<String, Object> paramMap) {
		return sqlSession.selectList("cloudKeyAudit.select_user_audit_list",paramMap);
	}
	@Override
	public Integer qryCloudKeyAuditcount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("cloudKeyAudit.select_user_audit_list_count",paramMap);
	}
	@Override
	public CloudKeyAudit qryCloudKeyAuditDetail(BigInteger id) {
		return sqlSession.selectOne("cloudKeyAudit.select_user_audit_detail", id);
	}
	@Override
	public List<CarPayLogList> qryCarPayLog(Map<String, Object> paramMap) {
		return sqlSession.selectList("cloudKeyAudit.select_cars_pay_log_list",paramMap);
	}
	@Override
	public Integer qryCarPayLogCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("cloudKeyAudit.select_cars_pay_log_list_count", paramMap);
	}
	@Override
	public String qryuserEndTime(BigInteger userId) {
		return sqlSession.selectOne("cloudKeyAudit.select_user_max_endtime", userId);
	}
	
	/**
	 * 查询停车费总额
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public Long qryCarTotalPay(Map<String, Object> paramMap){
		return sqlSession.selectOne("cloudKeyAudit.select_car_totalPay", paramMap);
	}
	
}
