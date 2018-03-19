package com.cnfantasia.server.api.user.service;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.api.user.entity.HobbyEntity;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.hobby.entity.Hobby;
import com.cnfantasia.server.domainbase.user.entity.User;
/**
 * 描述:() 具体业务Service层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserService {
	
	/**
	 * 根据用户编号查询用户详情
	 * @param userId
	 * @return
	 */
	public UserEntity getUserById(BigInteger userId);
	
	/**
	 * 更改个人信息 增加用户兴趣列表
	 * @return
	 */
	public UserEntity updPersonInfo(BigInteger userId,String mobile,String realName,String sex,String birthday,byte[] userImage,String userImgprofileName,String nickName,String signature,List<BigInteger> hobbyIds
			,String inviteNo, String role);
	
	/**
	 * 绑定手机号
	 * @param userId
	 * @param newPhone
	 */
	public void bindPhone(HttpServletRequest request,BigInteger userId,String newPhone);
	
	/**
	 * 查询所有可选的兴趣爱好列表
	 * @param pageModel
	 * @param userId
	 * @return
	 */
	public List<HobbyEntity> getAllHobbyList(PageModel pageModel,BigInteger userId);
	
	/**
	 * 查询用户的兴趣爱好列表
	 * @param userId
	 * @return
	 */
	public List<Hobby> getHobbyListByUserId(BigInteger userId);
	/**
	 * 用户设定自己的兴趣爱好列表
	 * @param hobbyIds
	 * @param userId
	 * @return
	 */
	public List<Hobby> submitAllHobby(List<BigInteger> hobbyIds,BigInteger userId);
	
}
