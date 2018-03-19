package com.cnfantasia.jfq.share.web;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.jfq.share.constant.ShareConstant;
import com.cnfantasia.jfq.share.constant.SurpriseGiftDict;
import com.cnfantasia.jfq.share.entity.PrizeSurpriseGiftEntityDetail;
import com.cnfantasia.jfq.share.entity.ShareActiveEntity;
import com.cnfantasia.jfq.share.entity.SurpriseGiftResponse;
import com.cnfantasia.jfq.share.service.IShareService;
import com.cnfantasia.jfq.wechat.util.TemplateMsgSender;
import com.cnfantasia.pub.constant.CookieConstant;
import com.cnfantasia.pub.entity.Regist3rdResponse;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.business.pub.utils.CookieUtil;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.business.pub.utils.NumberUtils;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.shareActive.service.IShareActiveBaseService;
import com.cnfantasia.server.domainbase.shareActiveDetail.entity.ShareActiveDetail;
import com.cnfantasia.server.domainbase.shareActiveDetail.service.IShareActiveDetailBaseService;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;
import com.cnfantasia.wl.wechat.util.JsapiTicketGetter;
import com.cnfantasia.wl.wechat.util.Sign;
import com.cnfantasia.wl.wechat.util.UserInfoGetter;
import com.cnfantasia.wl.wechat.web.LoginHelper;

/**
 * 折扣分享 Controller
 * 
 * @author wenfq 2015-04-16
 * 
 */
@Controller
@RequestMapping("/shareDiscount")
public class ShareDiscountController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());

	//private static final String auth2URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect"
			//.replace("APPID", WeChatConstant.APPID).replace("REDIRECT_URI", "http%3a%2f%2fwww.jiefangqu.com%2fLA%2fshareDiscount%2fgetSnsUser.do")
			//.replace("SCOPE", "snsapi_userinfo");

	@Resource
	IShareService shareService;
	@Resource
	IHttpClient simpleHttpClient;
	@Resource
	IShareActiveBaseService shareActiveBaseService;
	@Resource
	IUuidManager uuidManager;

	@Resource
	IShareActiveDetailBaseService shareActiveDetailBaseService;
	
	/**
	 * 开始分享_惊喜
	 * 
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/startShareAmazing.do")
	public ModelAndView startShareAmazing(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		String huaid = request.getParameter("uid") == null ? "50122" : request.getParameter("uid");
		String cash = request.getParameter("cash") == null ? "10.50" : request.getParameter("cash");
		String timeParam = request.getParameter("time");
		String time = null;
		if (timeParam != null) {
			DateTime dateTime = new DateTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(timeParam));
			time = dateTime.toString("yyyy-MM-dd HH:mm:ss");
		} else {
			//throw new Exception
			time = "2015-04-21 21:00:30";
		}

		String bigStr = "cash=" + cash + "&time=" + timeParam + "&uid=" + huaid;
		String nonStr = Md5Util.MD5(Md5Util.MD5(bigStr).toLowerCase()).toLowerCase();
		if (!nonStr.equals(request.getParameter("nonStr"))) {
			return new ModelAndView("/shareDiscount/invalidRequest");
		}

		{//分享给朋友或朋友圈
			StringBuilder startUrl = new StringBuilder(request.getRequestURL());
			startUrl.append("?uid=" + huaid).append("&cash=" + cash).append("&time=" + timeParam).append("&nonStr=" + nonStr);
			logger.info("the request url is: " + startUrl.toString());
			//CookieUtil.addCookie(response, CookieConstant.ShareUrl, startUrl.toString(), CookieConstant.cookieExpireTime_one_day);
			request.getSession().setAttribute(CookieConstant.ShareUrl, startUrl.toString());
			String shareUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
			request.setAttribute("signInfo", Sign.sign(JsapiTicketGetter.getJsapiTicket(), shareUrl));
		}

		request.getSession().setAttribute("huaidTime", huaid + timeParam);
		User user = shareService.getUserInfo(huaid);
		synchronized (this) {
			ShareActiveEntity sae = shareService.getShareDetail(huaid, time);
			if (sae == null) {//还没有分享记录，插入一条
				sae = new ShareActiveEntity();
				sae.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_share_active));
				sae.setShareTime(time);
				sae.settUserFId(new BigInteger(huaid));
				sae.setType("1");//意外惊喜分享
				sae.setSys0AddUser(new BigInteger(huaid));
				sae.setShareCashAmt(NumberUtils.doubleM100ToLong(Double.parseDouble(cash)));
				shareActiveBaseService.insertShareActive(sae);
			}
			request.setAttribute("sae", sae);
			request.getSession().setAttribute("activeId", sae.getId());
		}

		request.setAttribute("user", user);
		request.setAttribute("time", time);
		request.setAttribute("cash", cash);
		request.setAttribute("huaidTime", huaid + timeParam);

		return new ModelAndView("/shareDiscount/share");
	}

	/**
	 * 开始分享_最低折扣
	 * 
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/start.do")
	public ModelAndView startShare(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		if (request.getParameter("discount") == null)
			return new ModelAndView("forward:/shareDiscount/startShareAmazing.do");

		String huaid = request.getParameter("uid");
		String discount = request.getParameter("discount");
		String cash = request.getParameter("cash");
		String timeParam = request.getParameter("time");
		String time = null;
		if (timeParam != null) {
			DateTime dateTime = new DateTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(timeParam));
			time = dateTime.toString("yyyy-MM-dd HH:mm:ss");
		}else{
			//throw new Exception
			time = "2015-04-21 21:00:30";
		}
		String bigStr = (discount != null ? "cash=" + cash + "&discount=" + discount + "&time=" + timeParam + "&uid=" + huaid : "cash=" + cash + "&time="
				+ timeParam + "&uid=" + huaid);
		String nonStr = Md5Util.MD5(Md5Util.MD5(bigStr).toLowerCase()).toLowerCase();
		if (!nonStr.equals(request.getParameter("nonStr"))) {
			return new ModelAndView("/shareDiscount/invalidRequest");
		}

		{//分享给朋友或朋友圈
			StringBuilder startUrl = new StringBuilder(request.getRequestURL());
			startUrl.append("?uid=" + huaid).append("&discount=" + discount).append("&cash=" + cash).append("&time=" + timeParam).append("&nonStr=" + nonStr);
			logger.info("the request url is: " + startUrl.toString());
			request.getSession().setAttribute(CookieConstant.ShareUrl, startUrl.toString());
			String shareUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
			request.setAttribute("signInfo", Sign.sign(JsapiTicketGetter.getJsapiTicket(), shareUrl));
		}

		request.getSession().setAttribute("huaidTime", huaid + timeParam);

		User user = shareService.getUserInfo(huaid);
		synchronized (this) {

			ShareActiveEntity sae = shareService.getShareDetail(huaid, time);
			if (sae == null) {//还没有分享记录，插入一条
				sae = new ShareActiveEntity();
				sae.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_share_active));
				sae.setShareTime( time);
				sae.settUserFId(new BigInteger(huaid));
				sae.setType("0");//折扣分享
				sae.setSys0AddUser(new BigInteger(huaid));
				sae.setShareCashAmt(NumberUtils.doubleM100ToLong(Double.parseDouble(cash)));
				shareActiveBaseService.insertShareActive(sae);
			}
			request.setAttribute("sae", sae);
			request.getSession().setAttribute("activeId", sae.getId());
		}

		request.setAttribute("user", user);
		request.setAttribute("time", time);
		request.setAttribute("cash", cash);
		request.setAttribute("discount", discount);
		request.setAttribute("huaidTime", huaid + timeParam);
		
		if(CookieUtil.getCookieByName(request, huaid + timeParam)!=null
				&& "1".equals(CookieUtil.getCookieByName(request, huaid + timeParam).getValue())){ //已参与活动
			return new ModelAndView("/shareDiscount/share");
		}else{//没参与活动，直接跳到下载界面 added by wenfq 2016-01-20
			return new ModelAndView("/shareDiscount/shareToDownload");
		}

		//return new ModelAndView("/shareDiscount/share");
	}

	/**
	 * 沾沾喜气（我也要）
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/toGet.do")
	public ModelAndView toGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return new ModelAndView("redirect:" + WeChatConfig.AUTH2URL);//跳到微信授权页
		/*if (CookieUtil.getCookieByName(request, CookieConstant.SnsUser) == null) {
			return new ModelAndView("redirect:" + auth2URL);//跳到微信授权页
		} else {//Cookie已经有了微信信息，就不需微信授权
			return new ModelAndView("redirect:/shareDiscount/getSnsUser.do");
		}*/
	}

	/**
	 * 获取微信用户信息，登录解放区，新用户登录时，会顺便注册一个账号
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/getSnsUser.do")
	public ModelAndView getSnsUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

		{//分享给朋友或朋友圈
			String shareUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
			request.setAttribute("signInfo", Sign.sign(JsapiTicketGetter.getJsapiTicket(), shareUrl));
		}

		SnsUserinfo snsUserInfo = LoginHelper.getSnsUserInfo(request, response);

		LoginHelper.loginAPI(simpleHttpClient, request, snsUserInfo);

		toGetHBAmount(request, snsUserInfo, LoginHelper.getRegist3rdResponseFromSession(request));
		String huaidTime = (String) request.getSession().getAttribute("huaidTime");
		CookieUtil.addCookie(response, huaidTime, "1", CookieConstant.cookieExpireTime_one_week);
		double hbAmount = (Double) request.getAttribute("hbAmount");
		Integer rewardType = (Integer) request.getAttribute("rewardType");
		String showCount = (String)request.getAttribute("showCount");
		if (hbAmount > 0 || (rewardType!=null&&
					SurpriseGiftDict.PrizeSurpriseGift_DataType.MARKET_OPT.compareTo(rewardType)==0
					&&	!StringUtils.isEmpty(showCount)	)) {//领到惊喜
			CookieUtil.addCookie(response, huaidTime + "_FetchStatus", "1", CookieConstant.cookieExpireTime_one_week);
			//sendTemplateMsg(request, snsUserInfo, hbAmount);
		} else {//未领到惊喜
			CookieUtil.addCookie(response, huaidTime + "_FetchStatus", "0", CookieConstant.cookieExpireTime_one_week);
			return new ModelAndView("/amazing/amazing-prize_zero");
		}

		return new ModelAndView("/amazing/amazing-prize");
	}

	/**
	 * 发送《领取消费券提醒》模板消息
	 * 
	 * @param request
	 * @param snsUserInfo
	 * @param hbAmount
	 */
	private void sendTemplateMsg(HttpServletRequest request, SnsUserinfo snsUserInfo, double hbAmount) {
		String postData = "{\"touser\":\"OPENID\",\"template_id\":\"TEMPLATE_ID\",\"url\":\"URL\",\"topcolor\":\"#FF0000\",\"data\":DATA}";
		postData = postData.replace("OPENID", snsUserInfo.getOpenid());
		postData = postData.replace("TEMPLATE_ID", "-C8Xo9oB6CoHX-6OzFrTOFsGAvdKXgZh2XIqGLLQYn4");//消费券的模板ID
		//postData = postData.replace("TEMPLATE_ID", "BEpC3EyVOL8hF9w2WD9wVjsbKuyXNU7VNAxrJjFB1Gs");//消费券的模板ID-test
		String url = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI",
				"http%3a%2f%2fwww.jiefangqu.com%2fLA%2fprize%2flist.do");
		postData = postData.replace("URL", url);
		Date expiryTime = new Date((Long) (request.getAttribute("expiryTimeLong")));//有效期
		postData = postData.replace("DATA", "{\"first\":{\"value\":\"恭喜！你已成功领取解放区消费券\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"" + (hbAmount / 100)
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
	 * 去领一个粮票
	 * 
	 * @param request
	 * @param snsUserInfo
	 *            微信用户信息
	 * @param regist3rdResponse
	 *            微信登录后的返回信息
	 */
	private synchronized void toGetHBAmount(HttpServletRequest request, SnsUserinfo snsUserInfo, Regist3rdResponse regist3rdResponse) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		BigInteger userId = new BigInteger(regist3rdResponse.getUserId());
		paramMap.put("participatorId", userId);
		paramMap.put("tShareActiveFId", request.getSession().getAttribute("activeId"));
		List<ShareActiveDetail> sadList = shareActiveDetailBaseService.getShareActiveDetailByCondition(paramMap);
		if (sadList.size() == 0) { //从未领过粮票，去领一个
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("fromType", 4);
			params.put("cityName", snsUserInfo.getCity());
			params.put("activeId", request.getSession().getAttribute("activeId"));

			JsonResponse surpriseGiftRes = simpleHttpClient.submitSimple("/surpriseGift/doGetSurpriseGift.json", params, LoginHelper.prepareReqHeader(request));
			logger.info(surpriseGiftRes.toString());
			if (surpriseGiftRes.getDataValue() != null) {
				SurpriseGiftResponse surpriseGiftResponse = JSON.parseObject(surpriseGiftRes.getDataValue().toString(), SurpriseGiftResponse.class);
				int type = surpriseGiftResponse.getType();//派奖类型
				ShareActiveDetail sad = new ShareActiveDetail();
				sad.settShareActiveFId((BigInteger) request.getSession().getAttribute("activeId"));
				sad.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_share_active_detail));
				sad.setUnionid(snsUserInfo.getUnionid());
				sad.setIsAdmin(1);
				sad.setHeadpicUrl(snsUserInfo.getHeadimgurl());
				sad.setType(type);
				sad.setExchcode(surpriseGiftResponse.getExchCode());
				if (ShareConstant.Record_Type.jfq_hb == type) {//解放区消费劵
					sad.setMessage("以后购物就靠你了，么么哒");
				} else if (ShareConstant.Record_Type.jfq_yb == type) {//怡宝
					sad.setMessage("以后喝水就靠你了，么么哒");
				} else if (ShareConstant.Record_Type.jfq_58 == type) {//58家政
					sad.setMessage("以后家务就靠你了，么么哒");
				} else if (ShareConstant.Record_Type.jfq_zuche == type) {
					sad.setMessage("以后租车就靠你了，么么哒");
				} else if (ShareConstant.Record_Type.jfq_film == type) {
					sad.setMessage("以后看电影就靠你了，么么哒");
				}else if (ShareConstant.Record_Type.jfq_market == type) {
					try {
						BigInteger detailId = new BigInteger(surpriseGiftResponse.getId());
						PrizeSurpriseGiftEntityDetail detailEntity = shareService.qryPrizeSurpriseGiftDetail(detailId);
						sad.setMessage(detailEntity.getMsPrizeOption().getLuckDesc());
					} catch (Exception e) {
						logger.error("ShareDiscountController.toGetHBAmount cause exception!", e);
					}
				}
				sad.setParticipatorId(userId);
				sad.setParticipatorName(snsUserInfo.getNickname());
				sad.setParticipateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				sad.setCashAmt(NumberUtils.doubleM100ToLong(surpriseGiftResponse.getHbAmount()));
				shareActiveDetailBaseService.insertShareActiveDetail(sad);
				request.setAttribute("name", surpriseGiftResponse.getName());
				request.setAttribute("showCount", surpriseGiftResponse.getShowCount());
				
				request.setAttribute("hbAmount", surpriseGiftResponse.getHbAmount());
				request.setAttribute("expiryTimeLong", surpriseGiftResponse.getExpiryTimeLong());
				request.setAttribute("exchCode", surpriseGiftResponse.getExchCode());
				request.setAttribute("rewardType", surpriseGiftResponse.getType());
				request.setAttribute("subscribe", JSONObject.parseObject(UserInfoGetter.getUserInfo(snsUserInfo.getOpenid())).getInteger("subscribe"));
			} else {
				request.setAttribute("hbAmount", 0.0);
			}
		} else {//显示之前已领的
			request.setAttribute("hbAmount", sadList.get(0).getCashAmt() * 1.0);
		}
	}
	
	/**
	 * 去购物
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/toShopping.do")
	public ModelAndView toShopping(HttpServletRequest request) {
		return new ModelAndView("redirect:" + "../product/index.do");
	}
}