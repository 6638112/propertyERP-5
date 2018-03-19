package com.cnfantasia.jfq.share.web;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.jfq.share.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.jfq.share.entity.SurpriseGiftResponse;
import com.cnfantasia.jfq.share.service.IShareService;
import com.cnfantasia.jfq.wechat.util.TemplateMsgSender;
import com.cnfantasia.pub.constant.CookieConstant;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.business.pub.utils.CookieUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;
import com.cnfantasia.wl.wechat.web.LoginHelper;

@Controller
@RequestMapping("/prize")
public class PrizeController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	@Resource
	private IHttpClient simpleHttpClient;
	@Resource
	private IShareService shareService;
	

	@RequestMapping("/index.do")
	public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SnsUserinfo snsUserInfo = LoginHelper.getSnsUserInfo(request, response);

		//** 白名单验证

		/*boolean isWhiteUser = false;
		for (int i = 0; i < WeChatUserConstant.whiteUser.length; i++) {
			if (snsUserInfo.getUnionid().equals(WeChatUserConstant.whiteUser[i])) {
				isWhiteUser = true;
				break;
			}
		}
		if (!isWhiteUser) {
			return new ModelAndView("/notice");
		}*/

		//白名单用户继续

		LoginHelper.loginAPI(simpleHttpClient, request, snsUserInfo);//微信登录
		//String loginResponse = LoginHelper.login(request, simpleHttpClient).getDataValue().toString();//普通用户登录
		request.getSession().setAttribute("userId", LoginHelper.getRegist3rdResponseFromSession(request).getUserId());

		return new ModelAndView("/prize/index");
	}

	/**
	 * 去领一个粮票
	 */
	@RequestMapping("/getHBAmount.do")
	@ResponseBody
	public JsonResponse getHBAmount(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fromType", 5);

		JsonResponse surpriseGiftRes = simpleHttpClient.submitSimple("/surpriseGift/doGetSurpriseGift.json", params, LoginHelper.prepareReqHeader(request));
		logger.info(surpriseGiftRes.toString());

		String userId = (String) request.getSession().getAttribute("userId");
		String userIdAndDate = userId + new SimpleDateFormat("yyyy-MM-dd").format(new Date());


		SurpriseGiftResponse surpriseGiftResponse = JSON.parseObject(surpriseGiftRes.getDataValue().toString(), SurpriseGiftResponse.class);
		if (surpriseGiftResponse.getHbAmount() > 0) {//抽到粮票
			CookieUtil.addCookie(response, userIdAndDate, "1", CookieConstant.cookieExpireTime_one_week);
		}

		try {//推送板消息
			if (surpriseGiftResponse.getHbAmount() > 0) {
				//sendTemplateMsg(request, LoginHelper.getSnsUserInfo(request, response), surpriseGiftResponse);
			}
		} catch (Exception e) {
			logger.info("sendTemplateMsg has exception: " + e.getMessage());
			e.printStackTrace();
		}
		return surpriseGiftRes;
	}

	/**
	 * 发送《领取消费券提醒》模板消息
	 * 
	 * @param request
	 * @param snsUserInfo
	 * @param hbAmount
	 */
	private void sendTemplateMsg(HttpServletRequest request, SnsUserinfo snsUserInfo, SurpriseGiftResponse surpriseGiftResponse) {
		String postData = "{\"touser\":\"OPENID\",\"template_id\":\"TEMPLATE_ID\",\"url\":\"URL\",\"topcolor\":\"#FF0000\",\"data\":DATA}";
		postData = postData.replace("OPENID", snsUserInfo.getOpenid());
		postData = postData.replace("TEMPLATE_ID", "-C8Xo9oB6CoHX-6OzFrTOFsGAvdKXgZh2XIqGLLQYn4");//消费券的模板ID
		//postData = postData.replace("TEMPLATE_ID", "BEpC3EyVOL8hF9w2WD9wVjsbKuyXNU7VNAxrJjFB1Gs");//消费券的模板ID-test
		String url = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI",
				"http%3a%2f%2fwww.jiefangqu.cn%2fLA%2fprize%2flist.do");
		postData = postData.replace("URL", url);
		Date expiryTime = new Date(surpriseGiftResponse.getExpiryTimeLong());//有效期
		postData = postData.replace("DATA",
				"{\"first\":{\"value\":\"恭喜！你已成功领取解放区消费券\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"" + surpriseGiftResponse.getHbAmount()
				+ "元\",\"color\":\"#da372a\"},\"keyword2\":{\"value\":\"" + new SimpleDateFormat("yyyy-MM-dd").format(expiryTime)
				+ "\",\"color\":\"#173177\"},\"remark\":{\"value\":\"消费券可在解放区超市中购物，冲抵现金。\",\"color\":\"#173177\"}}");
		try {
			TemplateMsgSender.sendMessage(postData);
		} catch (Exception e) {
			logger.info("sendTemplateMsg has exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 消费券列表
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/list.do")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SnsUserinfo snsUserInfo = LoginHelper.getSnsUserInfo(request, response);
		LoginHelper.loginAPI(simpleHttpClient, request, snsUserInfo);//微信登录
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", 1);
		params.put("pageNum", 100);//TODO 先查100个券，后续要做成延迟加载效果
		JsonResponse surpriseGiftListRes = simpleHttpClient.submitSimple("/surpriseGift/qryGiftList.json", params, LoginHelper.prepareReqHeader(request));
		logger.info(surpriseGiftListRes.getDataValue().toString());
		request.setAttribute("giftList", surpriseGiftListRes.getDataValue());
		return new ModelAndView("/prize/prize-list");
	}
	
	
	/**
	 * 消费券详情
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/prizeDetail.do")
	public ModelAndView prizeDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SnsUserinfo snsUserInfo = LoginHelper.getSnsUserInfo(request, response);
		LoginHelper.loginAPI(simpleHttpClient, request, snsUserInfo);//微信登录
//		boolean checkIsLightApp = true;
//		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;
//		BigInteger userId = JSON.parseObject(loginResponse).getBigInteger("userId");
		//1.搜集参数
		BigInteger detailId = ParamUtils.getBigInteger(request, "detailId", null);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("detailId", detailId);
		//2.交互
		JsonResponse remoteJsonResponse = simpleHttpClient.submitSimple("/surpriseGift/prizeDetail.json", params, LoginHelper.prepareReqHeader(request));
		logger.info(remoteJsonResponse.getDataValue().toString());
		request.setAttribute("gift", remoteJsonResponse.getDataValue());
//		PrizeSurpriseGiftEntityDetail detailEntity = shareService.qryPrizeSurpriseGiftDetail(detailId, userId, userType, checkIsLightApp);
		//3.结果处理
//		Map<String,Object> gift = this.prizeSurpriseGift2Map(detailEntity);
//		request.setAttribute("gift", gift);
//		request.setAttribute("valueCode", detailEntity.getTicketValue());
//		request.setAttribute("msPrizeOption", detailEntity.getMsPrizeOption());
//		request.setAttribute("msPrizeGift", detailEntity.getMsPrizeGift());
//		request.setAttribute("msPrizeGiftCodeList", detailEntity.getMsPrizeGiftCodeList());
		return new ModelAndView("/prize/prizeDetail");
	}
	
//	/**
//	 * 消费券列表
//	 * @throws IOException
//	 * @throws ClientProtocolException
//	 */
//	@RequestMapping("/list.do")
//	public ModelAndView list2(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
//		SnsUserinfo snsUserInfo = LoginHelper.getSnsUserInfo(request, response);
//		String loginResponse = LoginHelper.loginAPI(simpleHttpClient, request, snsUserInfo);//微信登录
//		PageModel pageModel = new PageModel(1, 100);//TODO 先查100个券，后续要做成延迟加载效果
//		boolean checkIsLightApp = true;
//		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;
//		BigInteger userId = JSON.parseObject(loginResponse).getBigInteger("userId");
//		List<PrizeSurpriseGiftEntity> prizeSurpriseGiftList = shareService.qryPrizeSurpriseGiftList(userId, userType, pageModel, checkIsLightApp);
//		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
//		if(prizeSurpriseGiftList!=null&&prizeSurpriseGiftList.size()>0){
//			for(PrizeSurpriseGiftEntity tmpGift:prizeSurpriseGiftList){
//				Map<String,Object> tmpMap = this.prizeSurpriseGift2Map(tmpGift);
//				resList.add(tmpMap);
//			}
//		}
//		JsonResponse jsonResponse = new JsonResponse();
//		ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
//		request.setAttribute("giftList", JSON.toJSON(jsonResponse.getDataValue()));
//		return new ModelAndView("/prize/prize-list");
//	}

	private Map<String,Object> prizeSurpriseGift2Map(PrizeSurpriseGiftEntity tmpEntity){
		if(tmpEntity==null){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("hbAmount", 0);
			return tmpMap;
		}
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("id", tmpEntity.getId());
		tmpMap.put("name", tmpEntity.getName());
		tmpMap.put("type", tmpEntity.getDataType());
		{
			Long expiryTimeLong = null;
			String expiryTime = tmpEntity.getExpiryTime();
			if(!StringUtils.isEmpty(expiryTime)){
				expiryTimeLong = DateUtil.timeToLong(expiryTime);
			}
			tmpMap.put("expiryTimeLong", expiryTimeLong==null?"":expiryTimeLong);
		}
		{
			Long createTimeLong = null;
			String createTime = tmpEntity.getCreateTime();
			if(!StringUtils.isEmpty(createTime)){
				createTimeLong = DateUtil.timeToLong(createTime);
			}
			tmpMap.put("createTimeLong", createTimeLong==null?"":createTimeLong);
		}
		if(tmpEntity.getDataType()!=null&&tmpEntity.getHbAmount()!=null){
			if(true){
				tmpMap.put("hbAmount", PriceUtil.div100(tmpEntity.getHbAmount()));
			}
		}
		tmpMap.put("fetchStatus", tmpEntity.getFetchStatus());
		tmpMap.put("useStatus", tmpEntity.getUseStatus());//使用状态
		tmpMap.put("exchCode", tmpEntity.getExchCode());
		tmpMap.put("showCount", tmpEntity.getShowCount()==null?"":tmpEntity.getShowCount());
		return tmpMap;
	}
	
}
