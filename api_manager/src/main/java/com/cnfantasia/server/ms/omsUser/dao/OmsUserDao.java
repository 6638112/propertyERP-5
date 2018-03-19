package com.cnfantasia.server.ms.omsUser.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction;
import com.cnfantasia.server.domainbase.omsUser.dao.OmsUserBaseDao;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.ms.omsUser.entity.OmsUserWithRoles;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanySimple;
import com.cnfantasia.server.ms.user.entity.UserEntity;
/**
 * 描述:() 具体业务Dao层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OmsUserDao extends OmsUserBaseDao implements IOmsUserDao{

	@Override
	public UserEntity selectUserById(BigInteger userId) {
		return selectUserById(userId);
	}

	@Override
	public int delete_userRole_byUserId(Map<String, Object> paramMap) {
		return sqlSession.delete("omsUser.delete_userRole_byUserId", paramMap);
	}

	@Override
	public List<OmsPermiFunction> select_OmsPermiFunction_byOmsUserId(BigInteger omsUserId) {
		return sqlSession.selectList("omsUser.select_omsPermiFunction_byOmsUserId", omsUserId);
	}

	@Override
	public PropertyCompanySimple getWelcomMsgInfo_byOmsuserId(BigInteger omsUserId) {
		return sqlSession.selectOne("omsUser.getWelcomMsgInfo_byOmsuserId", omsUserId);
	}

	@Override
	public List<OmsUserWithRoles> selectSubUserList(Map<String, Object> paramMap) {
		return sqlSession.selectList("omsUser.selectSubUserList", paramMap);
	}

	/**
	 * 查询开启银行托收的小区数量
	 * @param userId
	 * @return
	 */
	@Override
	public Integer selectOpenBCGbCount(BigInteger userId){
		return sqlSession.selectOne("omsUser.selectOpenBCGbCount", userId);
	}
	
	@Override
	public List<OmsUser> selectAccountList(Map<String, Object> paramMap){
		return sqlSession.selectList("omsUser.selectAccountManageList", paramMap);
	}
	
	@Override
	public Integer selectAccountCount(Map<String, Object> paramMap){
		return sqlSession.selectOne("omsUser.selectAccountManageCount", paramMap);
	}
}
