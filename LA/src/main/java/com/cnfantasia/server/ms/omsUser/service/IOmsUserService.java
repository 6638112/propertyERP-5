package com.cnfantasia.server.ms.omsUser.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.ms.user.entity.UserEntity;
import com.cnfantasia.server.msbase.omsPermiFunction.entity.OmsPermiFunction;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUser.service.IOmsUserBaseService;
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
	public void saveOmsUser(OmsUser omsUser, String[] roldIds);

	public List<OmsPermiFunction> select_OmsPermiFunction_byOmsUserId(BigInteger omsUserId);

	/**
	 * 获得左上角的欢迎消息，目前取公司名称
	 * 
	 * @param omsUserId
	 * @return
	 */
	public String getWelcomMsgInfo_byOmsuserId(BigInteger omsUserId);
}
