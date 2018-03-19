package com.cnfantasia.server.ms.userQuestion.web;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.ebuy.entity.DredgeProductSpecEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.dredge.service.DredgeService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.userQuestion.constant.UserQuestionDict;
import com.cnfantasia.server.api.userQuestion.entity.UserQuestion4Admin;
import com.cnfantasia.server.api.userQuestion.entity.UserQuestionDetail4Admin;
import com.cnfantasia.server.api.userQuestion.service.UserQuestionService;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;

/**
 * 帮帮忙
 * @author wenfq 2017-02-27
 *
 */
@Controller
@RequestMapping("/userQuestion")
public class UserQuestionController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
		
	@Resource
	UserQuestionService userQuestionService;
	
	@Resource 
	DredgeService dredgeService;
	
	@Resource
	IHttpClient simpleHttpClient;
	
	/**
	 * 帮帮忙查询列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/userQuestionList.html")
	public ModelAndView userQuestionList(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("location", ParamUtils.getString(request, "location"));
		paramMap.put("gbName", ParamUtils.getString(request, "gbName"));
		paramMap.put("content", ParamUtils.getString(request, "content"));
		paramMap.put("status", ParamUtils.getInt(request, "status", -1));
		paramMap.put("huaId", ParamUtils.getBigInteger(request, "huaId", null));
		paramMap.put("pushTimeBegin", ParamUtils.getString(request, "pushTimeBegin"));
		paramMap.put("pushTimeEnd", ParamUtils.getString(request, "pushTimeEnd"));
		paramMap.put("gbIdList", UserContext.getGbIdList());

		int resultSize = userQuestionService.getUserQuestionCount(paramMap);
		PageUtils.addPageInfoToParam(request, paramMap);
		List<UserQuestion4Admin> uqList = userQuestionService.getUserQuestionList(paramMap);

		//总数量
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("resList", uqList);
		return new ModelAndView("/userQuestion/userQuestionList");
	}

	/**
	 * 帮帮忙详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewUserQuestionDetail.html")
	public ModelAndView viewUserQuestionDetail(HttpServletRequest request, BigInteger uqId) {
		UserQuestionDetail4Admin uq = userQuestionService.getUserQuestionDetailDetail(uqId);
//		uq.setPicList(dredgeService.getPicInfoList(false, uq.getPics(), null));
		//总数量
		request.setAttribute("uq", uq);
		return new ModelAndView("/userQuestion/userQuestionView");
	}

	/**
	 * 转上门服务单 
	 */
	@RequestMapping("/convertToDredgeBill.json")
	@ResponseBody
	public JsonResponse convertToDredgeBill(HttpServletRequest request, String serviceType, String serviceItem, 
			String expectDate, String userId, String pics,String dredgeAddress,
			String tel, String dredgeContent, BigInteger roomId, BigInteger dredgePrdtId/*上门服务*/) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dredgeTypeId", serviceType);
		params.put("subTypeId", serviceItem);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date = df.parse(expectDate);
			params.put("expectDate", date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		params.put("userId", userId);
		
		params.put("pics", pics);
		params.put("dredgeAddress", dredgeAddress);
		params.put("dredgeContent", dredgeContent);
		params.put("tel", tel);
		params.put("roomId", roomId);
		params.put("dredgeProductId", dredgePrdtId);

		Map<String, Object> resMap = dredgeService.getRoomAddressIdByRoom(roomId);
		params.put("blockId", resMap.get("blockId"));

		params.put("specList", request.getParameter("specList"));
		
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/addDredgeBill.json", params);
		
		if(jsonResponse.getStatus().equals("0000")){
			BigInteger uqId = ParamUtils.getBigInteger(request, "uqId", null);
			Integer dredgeBillId = (Integer) ((Map<String, Object>)jsonResponse.getDataValue()).get("dredgeBillId");
			userQuestionService.afterConvertBill(userId, uqId, dredgeBillId, UserQuestionDict.QuestionStatus.TURN_DREDGE);
		}
		
		return jsonResponse;
	}
	
	/**
	 * 转公共维修单 
	 */
	@RequestMapping("/convertToPropertyRepairBill.json")
	@ResponseBody
	public JsonResponse convertToPropertyRepairBill(HttpServletRequest request, String serviceType, String serviceItem, 
			String expectDate, String userId, String pics,String dredgeAddress,
			String tel, String dredgeContent, BigInteger roomId, BigInteger gbId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dredgeTypeId", serviceType);
		//params.put("subTypeId", serviceItem);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date = df.parse(expectDate);
			params.put("expectDate", date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		params.put("userId", userId);
		
		params.put("pics", pics);
		params.put("dredgeAddress", dredgeAddress);
		params.put("dredgeContent", dredgeContent);
		params.put("tel", tel);
		params.put("roomId", roomId);
		params.put("gbId", gbId);
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/addDredgeBill.json", params);
		
		if(jsonResponse.getStatus().equals("0000")){
			BigInteger uqId = ParamUtils.getBigInteger(request, "uqId", null);
			Integer repairBillId = (Integer) ((Map<String, Object>)jsonResponse.getDataValue()).get("dredgeBillId");
			userQuestionService.afterConvertBill(userId, uqId, repairBillId, UserQuestionDict.QuestionStatus.TURN_DREDGE_REPAIR);
		}
		
		return jsonResponse;
	}
	
	/**
	 * 回复 
	 * @param replyType 5解放区回复， 6物业回复
	 */
	@RequestMapping("/reply.json")
	@ResponseBody
	public JsonResponse reply(HttpServletRequest request,int replyType, String userId, BigInteger uqId, String replyContent) {
		JsonResponse jsonResponse = new JsonResponse();
		
		userQuestionService.replyUser(replyType, userId, uqId, replyContent);
		return jsonResponse;
	}
}
