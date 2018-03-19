package com.cnfantasia.server.ms.omsUser.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction;
import com.cnfantasia.server.domainbase.omsUser.dao.IOmsUserBaseDao;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.ms.omsUser.entity.OmsUserWithRoles;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanySimple;
import com.cnfantasia.server.ms.user.entity.UserEntity;
/**
 * 描述:() 具体业务Dao层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsUserDao extends IOmsUserBaseDao{
	/**
	 * 根据用户编号查询用户详情
	 * @param userId
	 * @return
	 */
	public UserEntity selectUserById(BigInteger userId);

	/**
	 * 根据用户ID，删除 用户-角色 中间表
	 * 
	 * @param paramMap
	 * @return
	 */
	public int delete_userRole_byUserId(Map<String, Object> paramMap);

	/**
	 * 根据 omsUser.id（用户ID） 查找所拥有的omsPermiFunction
	 */
	public List<OmsPermiFunction> select_OmsPermiFunction_byOmsUserId(BigInteger omsUserId);

	/**
	 * 获得左上角的欢迎消息，目前取公司名称
	 * 
	 * @param omsUserId
	 * @return
	 */
	public PropertyCompanySimple getWelcomMsgInfo_byOmsuserId(BigInteger omsUserId);

	public List<OmsUserWithRoles> selectSubUserList(Map<String, Object> paramMap);
	
	/**
	 * 查询开启银行托收的小区数量
	 * @param userId
	 * @return
	 */
	public Integer selectOpenBCGbCount(BigInteger userId);
	
	public List<OmsUser> selectAccountList(Map<String, Object> paramMap);
	
	public Integer selectAccountCount(Map<String, Object> paramMap);
}
