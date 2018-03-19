package com.cnfantasia.server.ms.operateMsg.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.commonBusiness.entity.OperateConfigRange;
import com.cnfantasia.server.api.operateMsg.entity.MessageAddNewParamter;
import com.cnfantasia.server.api.operateMsg.service.OperateMsgService;
import com.cnfantasia.server.api.operation.constant.OperationDict;
import com.cnfantasia.server.api.operation.constant.OperationDict.OperationMsgDict;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.HTMLUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.messageToBeSend.entity.MessageToBeSend;
import com.cnfantasia.server.domainbase.messageToBeSend.service.IMessageToBeSendBaseService;
import com.cnfantasia.server.domainbase.operationMsgPushConfig.entity.OperationMsgPushConfig;
import com.cnfantasia.server.domainbase.operationMsgPushConfig.service.IOperationMsgPushConfigBaseService;
import com.cnfantasia.server.ms.advertise.service.IAdvertiseForOmsService;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.service.INoticeService;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;

@Controller
@RequestMapping("/operateMsg")
public class OperateMsgController {
	
	@Resource
	OperateMsgService operateMsgService;
	
	@Resource
	private INoticeService noticeService;
	
	@Resource 
    IAdvertiseForOmsService advertiseForOmsService;
	
	@Resource 
	IMessageToBeSendBaseService messageToBeSendBaseService;
	
	@Resource 
	IOperationMsgPushConfigBaseService operationMsgPushConfigBaseService;
	/**
	 * 运营消息推送列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("msgType", request.getParameter("msgType"));
		paramMap.put("title", request.getParameter("title"));
		paramMap.put("sendRange", request.getParameter("sendRange"));
		paramMap.put("sendTarget", request.getParameter("sendTarget"));
		paramMap.put("addTimeStart", request.getParameter("addTimeStart"));
		paramMap.put("addTimeEnd", request.getParameter("addTimeEnd"));
		paramMap.put("sendTimeStart", request.getParameter("sendTimeStart"));
		paramMap.put("sendTimeEnd", request.getParameter("sendTimeEnd"));
		int resultSize = operateMsgService.qryMessageToBeSendListForCount(paramMap);
		PageUtils.addPageInfoToParam(request, paramMap);
		List<MessageToBeSend> resList = operateMsgService.qryMessageToBeSendListForPage(paramMap);
		request.setAttribute("resList", resList);
		request.setAttribute("resultSize", resultSize);
		return new ModelAndView("/operateMsg/operateMsgList");
	}
	
	/**
	 * 运营消息推送查看
	 * @param request
	 * @return
	 */
	@RequestMapping("/view.html")
	public ModelAndView view(HttpServletRequest request) {
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		if(id==null){
			throw new ValidateRuntimeException("id不能为空！");
		}
		request.setAttribute("m", messageToBeSendBaseService.getMessageToBeSendBySeqId(id));
		List<OperateConfigRange> sendRangeList = operateMsgService.qrySendRangeByMsgId(id);
		request.setAttribute("sendRangeList", sendRangeList);//发送范围
		

		List<OperationMsgPushConfig> oprtConfigList = operationMsgPushConfigBaseService.getOperationMsgPushConfigByCondition(null);
		request.setAttribute("oprtConfigList", oprtConfigList);
		
		return new ModelAndView("/operateMsg/operateMsgView");
	}
	
	/**
	 * 运营消息推送编辑
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit.html")
	public ModelAndView edit(HttpServletRequest request) {
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		if(id==null){
			throw new ValidateRuntimeException("id不能为空！");
		}
		request.setAttribute("m", messageToBeSendBaseService.getMessageToBeSendBySeqId(id));
		List<OperateConfigRange> sendRangeList = operateMsgService.qrySendRangeByMsgId(id);
		request.setAttribute("sendRangeList", sendRangeList);//发送范围

		List<OperationMsgPushConfig> oprtConfigList = operationMsgPushConfigBaseService.getOperationMsgPushConfigByCondition(null);
		Collections.reverse(oprtConfigList);
		request.setAttribute("oprtConfigList", oprtConfigList);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("gbIdList", UserContext.getGbIdList());
		List<GroupBuildingSimpleEntity> gbList = noticeService.getGroupBuildingSimpleList(params);
		List<GroupBuildingSimpleEntity> blockList = noticeService.getBlockList(params);
		request.setAttribute("gbList", gbList);
		request.setAttribute("blockList", blockList);
		
		return new ModelAndView("/operateMsg/operateMsgEdit");
	}
	
	/**
	 * 运营消息推送编辑保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/update.html")
	public ModelAndView update(HttpServletRequest request) {
		BigInteger id = ParamUtils.getBigInteger(request, "msgId", null);
		if(id==null){
			throw new ValidateRuntimeException("id不能为空！");
		}
		
		MessageAddNewParamter m = new MessageAddNewParamter();
		m.setId(id);
		m.setSendRange(ParamUtils.getInt(request, "sendRange", 1));//1 == all country
		m.setMsgType(ParamUtils.getInt(request, "msgType", 1));
		m.setJumpTarget(ParamUtils.getString(request, "jumpTarget"));
		m.setEbuyProductDetail(ParamUtils.getString(request, "prdtParam"));
		String sendTime = ParamUtils.getString(request, "sendTime");
		m.setSendTime(sendTime);
		m.setShortUrl(ParamUtils.getString(request, "shortUrl"));
		m.setTitle(ParamUtils.getString(request, "title").trim());
		String content = request.getParameter("content").trim();		
		m.setContent(HTMLUtils.delHTMLTag(getRealContent(content.replace("&nbsp;", " ")))); //替换为空格
		
		String blackList = ParamUtils.getString(request, "blackList", null);
		String whiteList = ParamUtils.getString(request, "whiteList", null);
		
		m.setBlockIds(ParamUtils.getBigIntegerList(request, "blockId", -1));
		m.setGbIds(ParamUtils.getBigIntegerList(request, "gbId", -1));
		
		// 3.结果处理
		m.setOperatorUser(UserContext.getCurrUser().getUserAccount());
		if(StringUtils.isNotBlank(blackList))
			m.setBlackList(blackList);
		if(StringUtils.isNotBlank(whiteList))
			m.setWhiteList(whiteList);
		messageToBeSendBaseService.updateMessageToBeSend(m);
		
		advertiseForOmsService.dealServiceArea(m.getSendRange(), id, null, m.getBlockIds(), m.getGbIds(), OperationDict.OperationSaHasEbSupply_type.t_message_to_be_send);
		
		return new ModelAndView("redirect:/operateMsg/list.html");
	}
	
	/**
	 * 运营消息推送新增保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/addNew.html")
	public ModelAndView addNew(HttpServletRequest request) {
		// 1.搜集参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("gbIdList", UserContext.getGbIdList());
				
		List<GroupBuildingSimpleEntity> gbList = noticeService.getGroupBuildingSimpleList(params);
		List<GroupBuildingSimpleEntity> blockList = noticeService.getBlockList(params);
		
		List<OperationMsgPushConfig> oprtConfigList = operationMsgPushConfigBaseService.getOperationMsgPushConfigByCondition(null);
		
		// 3.结果处理
		request.setAttribute("gbList", gbList);
		request.setAttribute("blockList", blockList);
		Collections.reverse(oprtConfigList);
		request.setAttribute("oprtConfigList", oprtConfigList);
		
		return new ModelAndView("/operateMsg/operateMsgAddNew");
	}
	
	private String getRealContent(String content){
		String shortUrl = "";

		//通配符中也要加入转移字符 (.+?)代表要查找的内容
		Pattern pattern =Pattern.compile("value=\"(.+?)\"");

		// String content = "asfdasfda<input type=\"button\" value=\"http://t.cn\" />";
		
	    Matcher matcher=pattern.matcher(content);

		while (matcher.find()){
			shortUrl = matcher.group(1);
		}
		
		int leftIndex = content.indexOf("<");
		int rightIndex = content.indexOf(">");
		
		String realContent = "";
		if(leftIndex>= 0 && rightIndex > 0) //含有短链
			if(rightIndex < content.length() - 1) //插入的知链不在末尾
				realContent = content.substring(0, leftIndex) + " " + shortUrl + " " + content.substring(rightIndex + 1);
			else //插入的短链在末尾
				realContent = content.substring(0, leftIndex) + shortUrl;
		else //没有短链 
			realContent = content;
		
		return realContent;
	}
	
	/**
	 * 运营消息推送新增保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveAddNew.html")
	public ModelAndView saveAddNew(HttpServletRequest request) throws IllegalStateException, IOException {
			// 1.搜集参数
			MessageAddNewParamter m = new MessageAddNewParamter();
			m.setSendRange(ParamUtils.getInt(request, "sendRange", 1));//1 == all country
			m.setMsgType(ParamUtils.getInt(request, "msgType", 1));
			m.setJumpTarget(ParamUtils.getString(request, "jumpTarget"));
			m.setEbuyProductDetail(ParamUtils.getString(request, "prdtParam"));
			int sendTimeType = ParamUtils.getInt(request, "sendTimeType", 1);
			if(sendTimeType == 1){//立即发送
				//m.setSendTime(DateUtils.getCurrentDate());
			}else{//定时发送
				String sendTime = ParamUtils.getString(request, "sendTime");
				m.setSendTime(sendTime);
			}
			m.setShortUrl(ParamUtils.getString(request, "shortUrl"));
			m.setTitle(ParamUtils.getString(request, "title").trim());
			String content = request.getParameter("content").trim();		
			m.setContent(HTMLUtils.delHTMLTag(getRealContent(content.replace("&nbsp;", " ")))); //替换为空格
			
			MultipartFile blackFile = ((MultipartHttpServletRequest) request).getFile("blackFile");
			MultipartFile whiteFile =  ((MultipartHttpServletRequest) request).getFile("whiteFile");
			
			m.setBlockIds(ParamUtils.getBigIntegerList(request, "blockId", -1));
			m.setGbIds(ParamUtils.getBigIntegerList(request, "gbId", -1));
			
			// 2.交互
			if(m.getSendRange() == OperationMsgDict.SendRange_Block &&
				m.getBlockIds().size() == 0) {
				throw new ValidateRuntimeException("发送目标城市/区县时，传入城市/区县不能为空！");
			}
			if(m.getSendRange() == OperationMsgDict.SendRange_GroupBuilding &&
					m.getGbIds().size() == 0) {
				throw new ValidateRuntimeException("销售范围为小区时，传入小区不能为空！");
			}
			
			// 3.结果处理
			m.setOperatorUser(UserContext.getCurrUser().getUserAccount());
			BigInteger msgId = operateMsgService.saveMessageToBeSend(m, blackFile, whiteFile);
			
			advertiseForOmsService.dealServiceArea(m.getSendRange(), msgId, null, m.getBlockIds(), m.getGbIds(), OperationDict.OperationSaHasEbSupply_type.t_message_to_be_send);
//			if(sendTimeType == 1){//立即发送
//				operateMsgService.sendMsg(msgId);
//			}
			return new ModelAndView("redirect:/operateMsg/list.html");
	}
	
	
	/**
	 * 运营消息——发送
	 * @param request
	 * @return
	 */
	@RequestMapping("/send.json")
	@ResponseBody
	public JsonResponse send(HttpServletRequest request) {
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		if(id==null){
			throw new ValidateRuntimeException("id不能为空！");
		}
		JsonResponse jsonResponse = new JsonResponse();
		
		operateMsgService.sendMsg(id);
		
		jsonResponse.setMessage("发送成功");
		return jsonResponse;
	}
	
	/**
	 * 运营消息推送新增保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete.html")
	public ModelAndView delete(HttpServletRequest request) {
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		if(id==null){
			throw new ValidateRuntimeException("id不能为空！");
		}
		
		messageToBeSendBaseService.deleteMessageToBeSendLogic(id);
		
		return new ModelAndView("redirect:/operateMsg/list.html");
	}
}
