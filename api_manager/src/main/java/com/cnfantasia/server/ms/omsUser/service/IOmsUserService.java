package com.cnfantasia.server.ms.omsUser.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUser.service.IOmsUserBaseService;
import com.cnfantasia.server.ms.omsUser.entity.OmsUserWithRoles;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanySimple;
import com.cnfantasia.server.ms.user.entity.UserEntity;
/**
 * 描述:() 具体业务Service层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsUserService extends IOmsUserBaseService{
	/**
	 * 根据用户编号查询用户详情
	 * @param userId
	 * @return
	 */
	public UserEntity getUserById(BigInteger userId);
	
	
	/**
	 * 更改个人信息
	 * @return
	 */
	public UserEntity updPersonInfo(OmsUser omsUser);

	/**
	 * 保存账号
	 * 
	 * @param omsUser
	 *            用户信息
	 * @param roldIds
	 *            所选角色
	 */
	public int saveOmsUser(OmsUser omsUser, String[] roldIds);

	public List<OmsPermiFunction> select_OmsPermiFunction_byOmsUserId(BigInteger omsUserId);

	/**
	 * 获得左上角的欢迎消息(物业公司名称), 以及合作状态
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
