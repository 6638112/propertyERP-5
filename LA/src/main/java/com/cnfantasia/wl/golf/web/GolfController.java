package com.cnfantasia.wl.golf.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.domainbase.golfGroup.entity.GolfGroup;
import com.cnfantasia.server.domainbase.golfGroup.service.IGolfGroupBaseService;
import com.cnfantasia.server.domainbase.golfGroupDetail.entity.GolfGroupDetail;
import com.cnfantasia.server.domainbase.golfGroupDetail.service.IGolfGroupDetailBaseService;
import com.cnfantasia.server.ms.pub.constant.SEQConstants;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.util.JsapiTicketGetter;
import com.cnfantasia.wl.wechat.util.Sign;

/**
 * 文旅专项Controller
 * 
 * @author wenfq
 * 
 */
@Controller
@RequestMapping("/golf")
public class GolfController {
	private Log logger = LogFactory.getLog(getClass());
	private static final String auth2URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
			+ WeChatConfig.APPID
			+ "&redirect_uri=http%3a%2f%2fwww.jiefangqu.com%2fwlLightApp%2fgolf%2fviewGolf.html&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect";
	//	private static final String auth2URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx681fb1b637c7c74f&redirect_uri=http%3a%2f%2fwww.jiefangqu.com%2fwlLightApp%2fgolf%2fviewGolf.html&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

	private String[] dates = { "2015-02-07", "2015-02-08", "2015-02-09", "2015-02-10", "2015-02-11", "2015-02-12", "2015-02-13", "2015-02-14", "2015-02-15", };

	private String[] times = { "14:00-15:00", "15:00-16:00", "16:00-17:00" };

	@Resource
	private IHttpClient simpleHttpClient;

	@Resource
	private IUuidManager uuidManager;

	private IGolfGroupDetailBaseService golfGroupDetailBaseService;

	public IGolfGroupDetailBaseService getGolfGroupDetailBaseService() {
		return golfGroupDetailBaseService;
	}

	public void setGolfGroupDetailBaseService(IGolfGroupDetailBaseService golfGroupDetailBaseService) {
		this.golfGroupDetailBaseService = golfGroupDetailBaseService;
	}

	private IGolfGroupBaseService golfGroupBaseService;

	public IGolfGroupBaseService getGolfGroupBaseService() {
		return golfGroupBaseService;
	}

	public void setGolfGroupBaseService(IGolfGroupBaseService golfGroupBaseService) {
		this.golfGroupBaseService = golfGroupBaseService;
	}

	@RequestMapping("/test.html")
	@ResponseBody
	public ModelAndView toTestPage(HttpServletRequest request) throws ClientProtocolException, IOException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tGroupOpenid", "o33fQtyXoS-hZUo3-zTX54fqgCxs");
		List<GolfGroup> golsGroupList = golfGroupBaseService.getGolfGroupByCondition(paramMap);
		if (golsGroupList.size() == 1) {
			paramMap = new HashMap<String, Object>();
			paramMap.put("tGolfGroupFId", golsGroupList.get(0).getId());
			List<GolfGroupDetail> ggDetailList = golfGroupDetailBaseService.getGolfGroupDetailByCondition(paramMap);
			request.setAttribute("ggDetailList", ggDetailList);
		}

		return new ModelAndView("/golf/joinGolf");
	}

	/**
	 * 引导关注者打开的页面
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/viewGolf.html")
	@ResponseBody
	public ModelAndView toViewGolf(HttpServletRequest request) throws ClientProtocolException, IOException {

		String code = request.getParameter("code");

		String requestURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
		String groupOpenid = (String) request.getParameter("state"); //组ID;

		requestURL = String.format(requestURL, WeChatConfig.APPID, WeChatConfig.APPSECRET, code);
		HttpGet httpGet = new HttpGet(requestURL);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(httpGet);
		String entityContent = EntityUtils.toString(response.getEntity());
		if (entityContent.contains("errcode")) {
			if (groupOpenid != null) {
				return new ModelAndView("redirect:" + auth2URL.replace("%s", groupOpenid));
			} else {
				return new ModelAndView("redirect:" + auth2URL.replace("%s", "STATE"));
			}
		}
		JSONObject jsonObj = JSON.parseObject(entityContent);
		String access_token = jsonObj.getString("access_token");
		String openid = jsonObj.getString("openid");

		requestURL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
		requestURL = String.format(requestURL, access_token, openid);
		httpGet = new HttpGet(requestURL);
		httpClient = HttpClients.createDefault();
		response = httpClient.execute(httpGet);
		entityContent = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		jsonObj = JSON.parseObject(entityContent);

		System.out.println("最后输出的JSON串" + entityContent);
		ModelAndView view = new ModelAndView("/golf/golf");
		String nickName = jsonObj.getString("nickname");
		String headimgurl = jsonObj.getString("headimgurl");
		request.setAttribute("openid", openid);
		request.setAttribute("nickName", nickName);
		request.setAttribute("headimgurl", headimgurl);
		request.setAttribute("dateList", getDates());

		if (groupOpenid == null || "STATE".equals(groupOpenid)) {
			//如果没有组团，自己可以组团
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("groupOpenid", openid);
			List<GolfGroup> golsGroupList = golfGroupBaseService.getGolfGroupByCondition(paramMap);
			if (golsGroupList.size() >= 1) {
				paramMap = new HashMap<String, Object>();
				paramMap.put("tGolfGroupFId", golsGroupList.get(0).getId());
				List<GolfGroupDetail> ggDetailList = golfGroupDetailBaseService.getGolfGroupDetailByCondition(paramMap);
				request.setAttribute("golfGroup", golsGroupList.get(0));
				request.setAttribute("ggDetailList", ggDetailList);
				view = new ModelAndView("/golf/joinGolf");
			} else {
				request.setAttribute("groupOpenid", openid);
				view = new ModelAndView("/golf/golf");
			}
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("groupOpenid", groupOpenid);
			List<GolfGroup> golsGroupList = golfGroupBaseService.getGolfGroupByCondition(paramMap);
			if (golsGroupList.size() == 0) {
				view = new ModelAndView("/golf/golf");
			} else {
				//如果有团，且团员未满，加入该团
				GolfGroup gg = golsGroupList.get(0);
				gg.getId();
				paramMap.clear();
				paramMap.put("tGolfGroupFId", gg.getId());
				List<GolfGroupDetail> ggDetailList = golfGroupDetailBaseService.getGolfGroupDetailByCondition(paramMap);
				request.setAttribute("golfGroup", golsGroupList.get(0));
				request.setAttribute("ggDetailList", ggDetailList);

				if (ggDetailList.size() < 8) {//小于8人可以再加入
					view = new ModelAndView("/golf/joinGolf");
				} else {//等于8人
					if (groupOpenid.equals(openid)) {//组长，进来可以查看报名情况
						view = new ModelAndView("/golf/joinGolf");
					} else {
						for (int i = 0; i < ggDetailList.size(); i++) {
							if (ggDetailList.get(i).getOpenid().equals(openid)) {
								view = new ModelAndView("/golf/joinGolf");
								break;
							}
							if (i == (ggDetailList.size() - 1)) {//报名已满
								//view = new ModelAndView("/golf/golf");
								view = new ModelAndView("/golf/joinGolf");
							}
						}
					}
				}
			}
		}

		return view;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/golf.html")
	public ModelAndView viewGolf(HttpServletRequest request) {
		String groupOpenid = (String) request.getAttribute("state");
		String openid = (String) request.getAttribute("openid");
		if (groupOpenid == null) {
			//如果没有组团，自己可以组团

			//TODO 与DB交互判断是否已组过团
			String url = request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + "/golf/golf.html";
			request.setAttribute("sign", Sign.sign(JsapiTicketGetter.getJsapiTicket(), url));
			return new ModelAndView("/golf/golf");
		} else {
			//
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("groupOpenid", groupOpenid);
			List<GolfGroup> golsGroupList = golfGroupBaseService.getGolfGroupByCondition(paramMap);
			if (golsGroupList.size() == 0) {//这个
				String url = request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + "/golf/golf.html";
				request.setAttribute("sign", Sign.sign(JsapiTicketGetter.getJsapiTicket(), url));
				return new ModelAndView("/golf/golf");
			} else {
				//如果有团，且团员未满，加入该团
				GolfGroup gg = golsGroupList.get(0);
				gg.getId();
				paramMap.clear();
				paramMap.put("tGolfGroupFId", gg.getId());
				List<GolfGroupDetail> ggDetailList = golfGroupDetailBaseService.getGolfGroupDetailByCondition(paramMap);
				if (ggDetailList.size() < 8) {
					//String url = request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + "/golf/joinGolf.html";
					//request.setAttribute("sign", Sign.sign(JsapiTicketGetter.getJsapiTicket(), url));
					//小于8人可以再加入
					request.setAttribute("ggDetailList", ggDetailList);
					return new ModelAndView("/golf/joinGolf");
				} else {//等于8人，要重新组团
					return new ModelAndView("/golf/golf");
				}
			}
		}
	}

	/**
	 * 组建新团
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveGroupInfo.html")
	//@ResponseBody
	public ModelAndView saveGroupInfo(HttpServletRequest request) {
		String openid = request.getParameter("openid");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupOpenid", openid);
		if (golfGroupBaseService.getGolfGroupCount(paramMap) > 0) {
			//return "您已组团";
			return new ModelAndView("redirect:" + auth2URL.replace("%s", openid));
		}
		paramMap.clear();
		paramMap.put("openid", openid);
		List<GolfGroupDetail> ggdList = golfGroupDetailBaseService.getGolfGroupDetailByCondition(paramMap);
		if (ggdList.size() > 0) {
			//return "您已报团";
			return new ModelAndView("redirect:" + auth2URL.replace("%s", ggdList.get(0).gettGolfGroupFId().toString()));
		}

		String phone = request.getParameter("phone");

		GolfGroup gg = new GolfGroup();
		gg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_golf_group));
		gg.setGroupDate(request.getParameter("groupDate"));
		gg.setGroupTime(request.getParameter("groupTime"));
		gg.setGroupOpenid(openid);
		golfGroupBaseService.insertGolfGroup(gg);

		GolfGroupDetail ggd = new GolfGroupDetail();
		ggd.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_golf_group_detail));
		ggd.settGolfGroupFId(gg.getId());
		ggd.setIsAdmin(1);
		ggd.setPhone(phone);
		ggd.setOpenid(openid);
		ggd.setNickname(request.getParameter("nickName"));
		ggd.setRealName(request.getParameter("realName"));
		ggd.setHeadpicUrl(request.getParameter("headimgurl"));
		golfGroupDetailBaseService.insertGolfGroupDetail(ggd);

		//return "组团成功";
		
		//return new ModelAndView("redirect:" + auth2URL.replace("%s", openid));
		return new ModelAndView("redirect:" + "http://www.jiefangqu.com/wlLightApp/golf/viewGolf.html?code=0314c2c6eeae4795a335ebd5e3c2259Q&state=" + openid);
	}

	/**
	 * 保存团组员
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveGroupDetailInfo.html")
	@ResponseBody
	public String saveGroupDetailInfo(HttpServletRequest request) {
		String groupId = (String) request.getParameter("groupId");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tGolfGroupFId", groupId);
		if (golfGroupDetailBaseService.getGolfGroupDetailCount(paramMap) == 8)
			return "名额已满";

		String openid = request.getParameter("openid");
		paramMap.clear();
		paramMap.put("openid", openid);
		List<GolfGroupDetail> ggdList = golfGroupDetailBaseService.getGolfGroupDetailByCondition(paramMap);
		if (ggdList.size() > 0) {
			paramMap.clear();
			paramMap.put("isAdmin", 1);
			paramMap.put("tGolfGroupFId", ggdList.get(0).gettGolfGroupFId());
			ggdList = golfGroupDetailBaseService.getGolfGroupDetailByCondition(paramMap);
			return "您已报名" + ggdList.get(0).getOpenid();
		}

		String phone = request.getParameter("phone");

		GolfGroupDetail ggd = new GolfGroupDetail();
		ggd.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_golf_group_detail));
		ggd.settGolfGroupFId(new BigInteger(groupId));
		ggd.setIsAdmin(0);
		ggd.setPhone(phone);
		ggd.setOpenid(openid);
		ggd.setNickname(request.getParameter("nickName"));
		ggd.setRealName(request.getParameter("realName"));
		ggd.setHeadpicUrl(request.getParameter("headimgurl"));
		golfGroupDetailBaseService.insertGolfGroupDetail(ggd);

		return "报名成功";
	}

	/**
	 * 加入团
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/joinGolf.html")
	public ModelAndView joinGolf(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/golf/joinGolf");
		return view;
	}

	private List<String> getDates() {
		List<String> dateList = new ArrayList<String>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<GolfGroup> ggList = golfGroupBaseService.getGolfGroupByCondition(paramMap);
		for (int i = 0; i < dates.length; i++) {
			int j = 0;
			for (GolfGroup gg : ggList) {
				if (dates[i].equals(gg.getGroupDate().substring(0, 10)))
					j++;//记录dates[i]的出现次数
			}
			//时间段已经出现了3次，不能再出现在客户端
			if (j < 3)
				dateList.add(dates[i]);
		}
		return dateList;
	}

	@RequestMapping("/getTimes.html")
	@ResponseBody
	public String getTimes(HttpServletRequest request) {
		String groupDate = (String) request.getParameter("groupDate");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupDate", groupDate);
		List<GolfGroup> ggList = golfGroupBaseService.getGolfGroupByCondition(paramMap);
		List<String> validTimeList = new ArrayList<String>();
		for (int i = 0; i < times.length; i++) {
			validTimeList.add(times[i]);
			for(int j = 0; j< ggList.size();j++){
				if (times[i].equals(ggList.get(j).getGroupTime())) {
					validTimeList.remove(ggList.get(j).getGroupTime());
				}
			}
		}

		return JSON.toJSONString(validTimeList);
	}
	
	@RequestMapping("/checkDateAndTime.html")
	@ResponseBody
	public String checkDateAndTime(HttpServletRequest request) {
		String groupDate = (String) request.getParameter("groupDate");
		String groupTime = (String) request.getParameter("groupTime");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupDate", groupDate);
		paramMap.put("groupTime", groupTime);
		List<GolfGroup> ggList = golfGroupBaseService.getGolfGroupByCondition(paramMap);
		if (ggList.size() > 0)
			return "已被使用";
		else
			return "可以提交";
	}
}
