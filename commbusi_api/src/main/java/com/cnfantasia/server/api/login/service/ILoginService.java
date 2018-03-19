package com.cnfantasia.server.api.login.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.api.login.entity.SimpleLoginInfo;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;

public interface ILoginService {
	/**
	 * 发送短信
	 * @param mobile
	 * @param msg
	 * @return
	 */
	public boolean sendMsg(String mobile,String msg);
	
	/**
	 * 检查账号是否已经注册
	 * @param account
	 * @param type 账号类型
	 * @return true表示已注册，false表示未注册
	 */
	public boolean checkIsRegist(String account,Long accType);
//	/**
//	 * 发送邮件
//	 * @param phone
//	 * @return
//	 */
//	public boolean sendMail(String mail,String msg);
	/**
	 * 用户注册,并返回登陆信息
	 * @param account 账号
	 * @param regType 注册方式
	 * @param subChannelId 所属渠道
	 * @param utmCampaign 用户注册参加的活动
	 * @param utmContent 区分推广内容（关键字、logo连接）
	 * @param inviteNo 邀请码,邀请人提供的信息
	 * @param deviceId 设备Id
	 * @return
	 */
	public LoginNo regist(String account,Integer regType,Long subChannelId,String version,String utmCampaign,String utmContent,String inviteNo,String deviceId,String password);
	
	/**
	 * 社区模块的用户注册
	 * @param account
	 * @param regType
	 * @param subChannelId
	 * @param deviceId
	 * @param password
	 * @param nickName
	 * @return
	 */
	public LoginNo registClub(String account,Integer regType,Long subChannelId,String version,String deviceId,String password,String nickName);
	
	/**
	 * * 仅注册，不查询登录信息
	 * @param account
	 * @param accountType 账号类别
	 * @param subChannelId
	 * @param utmCampaign
	 * @param utmContent
	 * @param inviteNo
	 * @param deviceId
	 * @param mobile 手机号
	 * @param realName 真实姓名
	 * @param sex 性别
	 * @param birthday 生日
	 * @param imgprofile 图像
	 * @return
	 */
	public LoginNo registSimple(String account,Long accountType,Long subChannelId,String version,String utmCampaign,String utmContent,String inviteNo
			,String deviceId,String mobile,String realName,String nickName,String sex,String birthday,String imgprofile,String password,String unionId);
	/**第三方注册
	 * 
	 * @param regType 注册方式
	 * @param openId
	 * @param accessToken
	 * @param mobile
	 * @param realName
	 * @param nickName
	 * @param sex
	 * @param birthday
	 * @param userImage
	 * @param imgprofile
	 * @param subChannelId
	 * @param deviceId
	 * @return
	 */
	public LoginNo regist3rd(Integer regType,String openId,String accessToken,String mobile,String realName,String nickName,String sex,String birthday,byte[] userImage,String imgprofile,Long subChannelId,String version,String deviceId
				,String unionId);
	/**
	 * 登录
	 * @return 登录结果信息
	 */
	public LoginNoEntity login(SimpleLoginInfo simpleLoginInfo,Long subChannelId,String version,String deviceId);
	
	/**
	 * 通过账号，账号类别获取对应的用户Id
	 * @param account
	 * @param accountType
	 * @return
	 */
	public BigInteger getUserIdByAccount(String account,Long accountType);
	
	/**
	 * 设置新密码
	 * @param account 账号
	 * @param password 密码
	 * @param type 找回方式
	 * @return true表示密码修改成功，false表示失败
	 */
	public LoginNo setNewPassword(String account,String password,Long type);
	/**
	 * 根据用户Id验证密码是否正确
	 * @param id
	 * @param oldPassword
	 * @return
	 */
	public boolean validatePwdByUserId(BigInteger id,String oldPassword);
	/**
	 * 修改密码
	 * @param id 用户Id 
	 * @param password 新密码
	 * @param oldPassword 旧密码
	 * @return
	 */
	public boolean changePassword(BigInteger id,String newPassword);
	/**
	 * 根据账号及类别查询用户登录信息
	 * @param accountNo
	 * @param accountType
	 * @return
	 */
	public LoginNoEntity getLoginNoEntityByAccount(String accountNo,Long accountType);
	
//	/**
//	 * 刷新用户缓存信息
//	 */
//	public void freshUserContext();
	
//	/**
//	 * 校验当前token的合法性(淘宝)
//	 * @param appKey
//	 * @param appSecret
//	 * @param accessToken
//	 * @return
//	 */
//	public boolean validateAccessTokenTaobao(String accessToken);
//	/**
//	 * 校验当前token的合法性(QQ)
//	 * @param appKey
//	 * @param appSecret
//	 * @param accessToken
//	 * @return
//	 */
//	public boolean validateAccessTokenQQ(String accessToken,String openId);
//	
//	/**
//	 * 校验当前token的合法性(微信)
//	 * @param accessToken
//	 * @param openId
//	 * @return
//	 */
//	boolean validateAccessTokenWeiXin(String accessToken, String openId);
	
//	/**
//	 * 验证登陆信息
//	 * @param simpleLoginInfo
//	 */
//	public void validateLoginType(SimpleLoginInfo simpleLoginInfo);
	
	/**
	 * 查询临时用最新一条的抽奖记录
	 */
	public PrizeRecordTmp getLastRecord(BigInteger userTmpId);
	
//	/**
//	 * 批量新增登陆日志
//	 * @param loginLogList
//	 */
//	public void insertLoginLogBatchForQueueAutoId(List<LoginLog> loginLogList);

}
