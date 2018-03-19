package com.cnfantasia.server.api.notice.web;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.notice.entity.NoticeBean;
import com.cnfantasia.server.api.notice.service.INoticeService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.business.pub.entity.MultiFileEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.CommonMultiFileUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.message.service.IMessageBaseService;
import com.cnfantasia.server.domainbase.messageGroup.entity.MessageGroup;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	
	private IMessageBaseService messageBaseService;
	public void setMessageBaseService(IMessageBaseService messageBaseService) {
		this.messageBaseService = messageBaseService;
	}

	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private INoticeService noticeService;
	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	private ISysParamParser noticeSysParamParser;
	public void setNoticeSysParamParser(ISysParamParser noticeSysParamParser) {
		this.noticeSysParamParser = noticeSysParamParser;
	}

	/**
	 * 查询(消息表)列表信息，分页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toMessageList.json")
	@ResponseBody
	public JsonResponse toMessageListPage(HttpServletRequest request) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集、整理参数;
		Map<String, Object> messageQryMap = MapConverter.toMap(requestToMessage(request));
		PageModel pageModel = ControllerUtils.getPageModel(request);
		// 2.与数据库交互
		List<Message> messageListMap = messageBaseService.getMessageByConditionDim(messageQryMap, pageModel);
		// 加载数据字典信息
		// EbhMessageSourceUtil.insertDictInfo(messageListMap,
		// SEQConstants.t_message);
		// 3.返回
		return ControllerUtils.addPageInfo(jsonResponse, messageListMap, pageModel.isLast, pageModel.count);
	}
	

	/**
	 * 返回公告关联的地址信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/addresslist.json")
	@ResponseBody
	public JsonResponse addresslist(HttpServletRequest request) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();
		String msgId = request.getParameter("msgId");
		List<NoticeBean> noticeBeans = noticeService.queryAddressByMsgid(new BigInteger(msgId));
		return ControllerUtils.addPageInfo(jsonResponse, noticeBeans, true,noticeBeans.size());
	}
	
	/**
	 * 新增或者修改物业公告
	 * @param request
	 * @param messageParamMap
	 * @param groups
	 * @throws Exception
	 */
	private Message saveOrEditNotice(HttpServletRequest request) throws Exception{
		ControllerUtils.checkToken(request);// 防止重复提交
		// 1.搜集、整理参数;
		Message messageParamMap = requestToMessage(request);
		
		//上传图片
		MultiFileEntity multiFileEntity = CommonMultiFileUtil.parseRequsetFileInfo(request, "imgfile");
		if(multiFileEntity.getDatas() != null && multiFileEntity.getDatas().length > 0){
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			fileName += String.valueOf((1000 + new Random().nextInt(8999)));
			if (multiFileEntity.getFileName() != null && multiFileEntity.getFileName().contains(".")) {
				String[] tmp = multiFileEntity.getFileName().split("\\.");
				String suffix = tmp[1].toLowerCase();
				if (!suffix.equals("jpg") && !suffix.equals("jpeg") && !suffix.equals("png") && !suffix.equals("gif")) {
					logger.error("请上传图片格式");
					throw new BusinessRuntimeException("messageController.messageAdd.type.failed");
				}
				fileName += "." + suffix;
			}
			logger.info("保存的文件名 =" + fileName);
			String fileUploadConfig = noticeSysParamParser.parseParamValue();
			fileServerService.uploadFile(fileUploadConfig + fileName, multiFileEntity.getDatas());
			// 保存图片路径
//			messageParamMap.setPicUrl(String.format("/%s%s",fileUploadConfig, fileName));
			messageParamMap.setPicUrl(fileName);
		}
		return messageParamMap;
	}

	/**
	 * 往(消息表)新增一条记录
	 */
	@RequestMapping("/messageAdd.json")
	@ResponseBody
	public JsonResponse messageAdd(HttpServletRequest request) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			String[] groups = null;
			Message messageParamMap = saveOrEditNotice(request);
			// 组装小区数据
			String groupBuilds = request.getParameter("groupBuilds");
			if(groupBuilds != null && !"".equals(groupBuilds.trim())){
				StringBuilder builder = new StringBuilder(groupBuilds);
				builder.deleteCharAt(0);
				builder.deleteCharAt(builder.length() - 1);
				logger.info("小区参数 = " + builder.toString());
				groups = builder.toString().split(",");
			}
			// 2.与数据库交互
			int res = noticeService.saveNoticeMessage(messageParamMap, groups);
			if (res <= 0) {
				throw new BusinessRuntimeException("message.messageAdd.insertMessage.failed");
			}
			// 3.返回
			return messageToJsonResponse(messageParamMap, jsonResponse);
		} catch (Exception e) {
			logger.error(e);
			throw new BusinessRuntimeException("message.messageAdd.insertMessage.failed",e);
		}
	}

	/**
	 * 跳转到(消息表)详情页面
	 */
	@RequestMapping("/toMessageDetail.json")
	@ResponseBody
	public JsonResponse toMessageDetail(HttpServletRequest request) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集、整理参数;
		Message messageParamMap = requestToMessage(request);
		BigInteger uuid = messageParamMap.getId();
		// 2.与数据库交互 根据uuid查询单个记录的详情
		Message messageSignalMap = messageBaseService.getMessageBySeqId(uuid);
		// 加载数据字典信息
		// EbhMessageSourceUtil.insertDictInfo(messageSignalMap,
		// SEQConstants.t_message);
		// 3.返回
		return messageToJsonResponse(messageSignalMap, jsonResponse);
	}
	/**
	 * 跳转到(消息表)详情页面
	 */
	@RequestMapping("/toMessageDetail.html")
	public ModelAndView toMessageDetailHtml(HttpServletRequest request) throws Exception {
		// 1.搜集、整理参数;
		Message messageParamMap = requestToMessage(request);
		BigInteger uuid = messageParamMap.getId();
		// 2.与数据库交互 根据uuid查询单个记录的详情
		Message messageSignalMap = messageBaseService.getMessageBySeqId(uuid);
		// 加载数据字典信息
		// EbhMessageSourceUtil.insertDictInfo(messageSignalMap,
		// SEQConstants.t_message);
		//
		request.setAttribute("title", messageSignalMap.getTitle());
		request.setAttribute("time", messageSignalMap.getTime());
		request.setAttribute("content", messageSignalMap.getContent());
		String picUrl = StringUtils.isEmpty(messageSignalMap.getPicUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(noticeSysParamParser.parseParamValue()+messageSignalMap.getPicUrl(), messageSignalMap);
		request.setAttribute("picUrl",picUrl);
		// 3.返回
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/notice/noticeModel");
		return mav;
	}
	
	/**
	 * 根据消息id查询所有关联小区
	 */
	@RequestMapping("/groupBuildList.json")
	@ResponseBody
	public JsonResponse groupBuildList(HttpServletRequest request) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集、整理参数;
		String id = request.getParameter("id");
		// 2.与数据库交互 根据uuid查询单个记录的详情
		List<MessageGroup> messageGroups = noticeService.getGroupBuildingByMsgid(new BigInteger(id));
		jsonResponse.setDataValue(messageGroups);
		return jsonResponse;
	}

	/**
	 * 更新(消息表)信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/messageUpd.json")
	@ResponseBody
	public JsonResponse messageUpd(HttpServletRequest request) throws Exception {		
		JsonResponse jsonResponse = new JsonResponse();
		try {
			Message messageParamMap = saveOrEditNotice(request);
			// 2.与数据库交互
			int res = messageBaseService.updateMessage(messageParamMap);
			if (res <= 0) {
				throw new BusinessRuntimeException("message.messageAdd.insertMessage.failed");
			}
			// 3.返回
			return messageToJsonResponse(messageParamMap, jsonResponse);
		} catch (Exception e) {
			logger.error(e);
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			return jsonResponse;
		}
	}

	/**
	 * 根据主键号删除(消息表)信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/messageDel.json")
	@ResponseBody
	public JsonResponse messageDel(HttpServletRequest request) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集、整理参数;
		Message messageParamMap = requestToMessage(request);
		// 2.与数据库交互
		int res = messageBaseService.deleteMessageLogic(messageParamMap.getId());
		if (res <= 0) {
			throw new BusinessRuntimeException("message.messageDel.deleteMessage.failed");
		}
		// 3.返回
		return jsonResponse;
	}

	private JsonResponse messageToJsonResponse(Message messageSignalMap, JsonResponse jsonResponse) {
		jsonResponse.putData("content", messageSignalMap.getContent());
		jsonResponse.putData("creater", messageSignalMap.getCreater());
		jsonResponse.putData("id", messageSignalMap.getId());
		jsonResponse.putData("picUrl", messageSignalMap.getPicUrl());
		jsonResponse.putData("time", messageSignalMap.getTime());
		jsonResponse.putData("title", messageSignalMap.getTitle());
		jsonResponse.putData("type", messageSignalMap.getType());
		return jsonResponse;
	}

	private Message requestToMessage(HttpServletRequest request) {
		Message message = new Message();
		if (!StringUtils.isEmpty(request.getParameter("id"))) {
			message.setId(new BigInteger(request.getParameter("id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("type"))) {
			message.setType(Long.parseLong(request.getParameter("type")));
		}
		if (!StringUtils.isEmpty(request.getParameter("title"))) {
			message.setTitle(request.getParameter("title"));
		}
		if (!StringUtils.isEmpty(request.getParameter("content"))) {
			message.setContent(request.getParameter("content"));
		}
		if (!StringUtils.isEmpty(request.getParameter("time"))) {
			message.setTime(request.getParameter("time"));
		}
		if (!StringUtils.isEmpty(request.getParameter("creater"))) {
			message.setCreater(new BigInteger(request.getParameter("creater")));
		}
		if (!StringUtils.isEmpty(request.getParameter("picUrl"))) {
			message.setPicUrl(request.getParameter("picUrl"));
		}
		return message;
	}
}
