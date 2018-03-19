/**   
* Filename:    AccountManageController.java   
* @version:    1.0  
* Create at:   2015年4月29日 上午6:30:07   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.accountManage.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.accountManage.entity.LoginInfoWithBindEntity;
import com.cnfantasia.server.api.accountManage.entity.LoginNoSimpleEntity;
import com.cnfantasia.server.api.accountManage.service.IAccountManageService;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;

/**
 * Filename:    AccountManageController.java
 * @version:    1.0.0
 * Create at:   2015年4月29日 上午6:30:07
 * Description:账号管理Controller
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月29日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/accountManage")
public class AccountManageController extends BaseController{
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private IAccountManageService accountManageService;
	public void setAccountManageService(IAccountManageService accountManageService) {
		this.accountManageService = accountManageService;
	}

	/**
	 * 查询用户当前账号绑定情况
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryAccountBindInfo.json")
	@ResponseBody
	public JsonResponse qryAccountBindInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		BigInteger currLoginNoId = UserContext.getCurrLoginNoId();
		//2.交互
		LoginInfoWithBindEntity loginInfoWithBindEntity = accountManageService.getLoginInfoWithBindEntity(userId, currLoginNoId);
		//3.结果处理
		Map<String,Object> resMap = loginInfoWithBindEntity2Map(loginInfoWithBindEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 提交账号绑定 qq或者微信等第三方账号的绑定
	 * 手机号+验证码的绑定方式通过另外单独的接口来处理
	 * @param request
	 * @return
	 */
	@RequestMapping("/submitAccountBind3rd.json")
	@ResponseBody
	public JsonResponse submitAccountBind3rd(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer regType = Integer.parseInt(request.getParameter("regType"));//注册方式
		String openId = request.getParameter("openId");//当前注册方式下的用户唯一标识
		String accessToken = request.getParameter("accessToken");//合法性校验token
		String unionId = request.getParameter("unionId");//微信联合Id unionId
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		BigInteger currLoginNoId = UserContext.getCurrLoginNoId();
		//2.交互
		LoginInfoWithBindEntity loginInfoWithBindEntity = accountManageService.submitAccountBind3rd(userId,regType,openId,accessToken,unionId,currLoginNoId);
		//3.结果处理
		//返回当前账号绑定结果信息
		Map<String,Object> resMap = loginInfoWithBindEntity2Map(loginInfoWithBindEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 提交账号绑定 手机号+验证码的绑定方式的绑定
	 * @param request
	 * @return
	 */
	@RequestMapping("/submitAccountBindPhone.json")
	@ResponseBody
	public JsonResponse submitAccountBindPhone(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		//3.结果处理
		return jsonResponse;
	}
	
	private Map<String,Object> loginInfoWithBindEntity2Map(LoginInfoWithBindEntity tmpEmtity){
		LoginNoSimpleEntity currLoginNo = tmpEmtity.getLoginNoSimpleEntity();//当前登录的账号信息 (用户基本信息及登录账号信息)
		List<LoginNo> bindAccList = tmpEmtity.getLoginNoList();//当前账号绑定的信息列表
		Map<String,Object> resMap = new HashMap<String, Object>();
		if(currLoginNo==null){throw new BusinessRuntimeException("accountManage.qryAccountBindInfo.currLoginNo.null");}
		{//当前登录的信息
			Long accType = currLoginNo.getType();//账号类别
			String accNo = null;
			if(currLoginNo.getType().compareTo(LoginDict.AccountType.HUA_ID)==0||currLoginNo.getType().compareTo(LoginDict.AccountType.MOBILE)==0){
				accNo = currLoginNo.getNo();//花号或者手机号时返回数据
			}
			Map<String,Object> ext_userInfo = commEntityConvertService.baseUser2Map(currLoginNo.getUserSimpleEntity());
			Map<String,Object> currLoginAccountMap = new HashMap<String, Object>();
			currLoginAccountMap.put("accType", accType);//账号类别
			currLoginAccountMap.put("accNo",accNo );
			currLoginAccountMap.put("ext_userInfo", ext_userInfo);//用户信息
			resMap.put("currLoginAccount", currLoginAccountMap);
		}
		String userCurrBindPhoneNo = null;//当前用户绑定的手机信息
		List<Map<String,Object>> bindAccResListMap = new ArrayList<Map<String,Object>>();
		if(bindAccList!=null&&bindAccList.size()>0){
			boolean isPhoneBind = false;
			String phoneNo = null;
			boolean isQqBind = false;
			boolean isWeiXinBind = false;
			for(LoginNo tmpLoginNo:bindAccList){
				if(tmpLoginNo.getType().compareTo(LoginDict.AccountType.MOBILE)==0){
					isPhoneBind = true;
					phoneNo = tmpLoginNo.getNo();
					{//保存当前用户绑定的手机号信息
						userCurrBindPhoneNo = tmpLoginNo.getNo();
					}
				}else if(tmpLoginNo.getType().compareTo(LoginDict.AccountType.QQ)==0){
					isQqBind = true;
				}else if(tmpLoginNo.getType().compareTo(LoginDict.AccountType.WEI_XIN)==0||tmpLoginNo.getType().compareTo(LoginDict.AccountType.WEI_XIN_LIGHT_APP)==0){
					isWeiXinBind = true;
				} 
			}
			
			if(currLoginNo.getType().compareTo(LoginDict.AccountType.MOBILE)!=0){
				Map<String,Object> aaMap = bindAcc2Map(LoginDict.AccountType.MOBILE, "绑定手机", phoneNo, isPhoneBind);
				bindAccResListMap.add(aaMap);
			}
			if(currLoginNo.getType().compareTo(LoginDict.AccountType.QQ)!=0){
				Map<String,Object> aaMap = bindAcc2Map(LoginDict.AccountType.QQ, "绑定QQ", null, isQqBind);
				bindAccResListMap.add(aaMap);
			}
			if(currLoginNo.getType().compareTo(LoginDict.AccountType.WEI_XIN)!=0&&currLoginNo.getType().compareTo(LoginDict.AccountType.WEI_XIN_LIGHT_APP)!=0){
				Map<String,Object> aaMap = bindAcc2Map(LoginDict.AccountType.WEI_XIN, "绑定微信", null, isWeiXinBind);
				bindAccResListMap.add(aaMap);
			}
			resMap.put("bindAccList", bindAccResListMap);//相关账号的绑定状态及相关信息
		}
		resMap.put("userPhoneNo",userCurrBindPhoneNo);
		return resMap;
	}
	
	private Map<String,Object> bindAcc2Map(Long accType,String accName,String accNo,boolean isBind){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("accType", accType);//账号类别
		tmpMap.put("accName", accName);
		tmpMap.put("accNo", accNo);
		tmpMap.put("bindStatus", isBind?"2":"1");//bindStatus	绑定状态	Integer	1未绑定，2已绑定
		return tmpMap;
	}
	
//	private Map<String,Object> bindLoginNoEntity2Map(BindLoginNoEntity bindLoginNoEntity){
//		if(bindLoginNoEntity==null){
//			return null;
//		}
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("id", bindLoginNoEntity.getId());
//		tmpMap.put("no", bindLoginNoEntity.getNo());
//		tmpMap.put("status", bindLoginNoEntity.getStatus());
//		tmpMap.put("userId", bindLoginNoEntity.gettUserFId());
//		tmpMap.put("type", bindLoginNoEntity.getType());
//		tmpMap.put("unionId", bindLoginNoEntity.getUnionId());
//		tmpMap.put("valLevel", bindLoginNoEntity.getValLevel());
//		UserSimpleEntity userSimpleEntity = bindLoginNoEntity.getUserSimpleEntity();
//		if(userSimpleEntity!=null){
//			Map<String,Object> ext_userInfo = commEntityConvertService.baseUser2Map(userSimpleEntity);
//			tmpMap.put("ext_userInfo", ext_userInfo);
//		}
//		return tmpMap;
//	}
	
//	{//当前登录的账号信息 包含用户基本信息
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		{
//			Map<String,Object> userMap = new HashMap<String, Object>();
//			userMap.put("imgProfile", "http://ww.b/aaa.jpg");
//			userMap.put("userId", "12345");
//			tmpMap.put("ext_userInfo", userMap);//用户信息
//		}
//		tmpMap.put("accNo", "15311111111");//账号
//		tmpMap.put("accType", "0");//账号类别
//		resMap.put("currLoginAccount", tmpMap);
//	}
//	
//	{
//		List<Map<String,Object>> bindAccList = new ArrayList<Map<String,Object>>();
//		{
//			Map<String,Object> aaMap = new HashMap<String, Object>();
//			aaMap.put("accType", "3");//账号类别
//			aaMap.put("accName", "绑定QQ");
//			aaMap.put("bindStatus", "1");
//			bindAccList.add(aaMap);
//		}
//		{
//			Map<String,Object> aaMap = new HashMap<String, Object>();
//			aaMap.put("accType", "7");//账号类别
//			aaMap.put("accName", "绑定微信");
//			aaMap.put("bindStatus", "2");
//			bindAccList.add(aaMap);
//		}
//		resMap.put("bindAccList", bindAccList);//相关账号的绑定状态及相关信息
//	}
//	
//	{//当前用户绑定的手机号信息
//		resMap.put("userPhoneNo", "15311111111");
//	}
	
	
}
