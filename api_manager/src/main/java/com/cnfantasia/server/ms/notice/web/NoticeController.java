package com.cnfantasia.server.ms.notice.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.message.service.IMessageBaseService;
import com.cnfantasia.server.domainbase.messagePrint.entity.MessagePrint;
import com.cnfantasia.server.domainbase.messagePrint.service.IMessagePrintBaseService;
import com.cnfantasia.server.ms.commonBusiness.entity.MultiFileEntity;
import com.cnfantasia.server.ms.commonBusiness.util.CommonMultiFileUtil;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.dto.DataValue;
import com.cnfantasia.server.ms.notice.dto.MessageBean;
import com.cnfantasia.server.ms.notice.dto.NoticeListBean;
import com.cnfantasia.server.ms.notice.dto.NoticeRequest;
import com.cnfantasia.server.ms.notice.dto.PlaceBean;
import com.cnfantasia.server.ms.notice.entity.MessageEntity;
import com.cnfantasia.server.ms.notice.service.INoticeService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.DictConstants;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.entity.JsonMessage;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtil;

/**
 * 
 * 类说明：物业公告
 * 
 * @author hunter
 * @since 2014年6月4日下午4:43:11
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

	private Log logger = LogFactory.getLog(getClass());

	@Resource
	private IHttpClient simpleHttpClient;
	@Resource
	private INoticeService noticeService;
	@Resource
	private IMessagePrintBaseService messagePrintBaseService;
	@Resource
	private IMessageBaseService messageBaseService;

	/**
	 * 物业公告首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/notice/noticeList");
		return modelAndView;
	}
	
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		String title = request.getParameter("title");
		String beginTime = request.getParameter("date01");
		String endTime = request.getParameter("date02");
		String gbName = request.getParameter("gbName");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title", title);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		paramMap.put("gbName", gbName);

		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/notice/noticeList");
		return modelAndView;
	}

	@RequestMapping("/list.html")
	@ResponseBody
	public Object list(String title, String page, String rows) throws Exception {
		logger.info("title = " + title);
		logger.info("page = " + page);
		logger.info("rows = " + rows);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("pageNum", rows);
		params.put("type", DictConstants.Message_Type.NOTICE_MESSAGE_TYPE);
		if (title != null) {
			params.put("title", title);
		}
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/message/toMessageList.json", params);
		logger.info(jsonResponse.getDataValue());
		DataValue dataValue = JSONObject.parseObject(String.valueOf(jsonResponse.getDataValue()), DataValue.class);
		List<MessageBean> beans = JSONArray.parseArray(dataValue.getList(), MessageBean.class);
		JsonMessage message = new JsonMessage();
		message.setTotal(dataValue.getCount() == null ? 0 : dataValue.getCount());
		message.setRows(beans);
		return message;
	}

	@RequestMapping("/noticeAdd.html")
	public ModelAndView addNotice() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/notice/noticeAdd");
		return modelAndView;
	}

	private List<PlaceBean> retrieveAddressList(String url, Map<String, Object> params) {
		JsonResponse jsonResponse = simpleHttpClient.submitSimple(url, params);
		logger.info(jsonResponse.getDataValue());
		DataValue dataValue = JSONObject.parseObject(String.valueOf(jsonResponse.getDataValue()), DataValue.class);
		List<PlaceBean> beans = JSONArray.parseArray(dataValue.getList(), PlaceBean.class);
		return beans;
	}

	private List<Map<String, String>> retrieveTreeData(String id, Map<String, Object> params) {
		List<Map<String, String>> treeDate = new ArrayList<Map<String, String>>();
		if (id == null) {
			List<PlaceBean> beans = retrieveAddressList("/addressProvince/getAddressProvinceList.json", params);
			for (PlaceBean bean : beans) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", "province_" + bean.getId());
				map.put("text", bean.getName());
				map.put("state", "closed");
				treeDate.add(map);
			}
		} else {
			String[] ids = id.split("_");
			if (ids[0].equals("province")) {
				params.put("provId", ids[1]);
				List<PlaceBean> beans = retrieveAddressList("/addressCity/getAddressCityListById.json", params);
				for (PlaceBean bean : beans) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", "city_" + bean.getId());
					map.put("text", bean.getName());
					map.put("state", "closed");
					treeDate.add(map);
				}
			} else if (ids[0].equals("city")) {
				params.put("cityId", ids[1]);
				List<PlaceBean> beans = retrieveAddressList("/addressBlock/getAddressBlockListById.json", params);
				for (PlaceBean bean : beans) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", "block_" + bean.getId());
					map.put("text", bean.getName());
					map.put("state", "closed");
					treeDate.add(map);
				}
			} else if (ids[0].equals("block")) {
				params.put("blockId", ids[1]);
				List<PlaceBean> beans = retrieveAddressList("/groupBuilding/getGroupBuildingListById.json", params);
				for (PlaceBean bean : beans) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", "groupBuilding_" + bean.getId());
					map.put("text", bean.getName());
					map.put("state", "open");
					treeDate.add(map);
				}
			}
		}
		return treeDate;
	}

	@RequestMapping("/treedata.html")
	@ResponseBody
	public Object groupbuilding(String id) throws Exception {
		JsonMessage message = new JsonMessage();
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, String>> treeDate = new ArrayList<Map<String, String>>();
		logger.info(String.format("加载id = %s的树节点", id));
		try {
			treeDate = retrieveTreeData(id, params);
		} catch (Exception e) {
			logger.error(e);
			message.setMessage("加载小区信息错误");
			message.setResult("error");
		}
		return treeDate;
	}

	private JsonResponse addOrEditNotice(HttpServletRequest request, JsonMessage message, String method,String url) throws Exception {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String address = request.getParameter("address");
		String delState= request.getParameter("delState");
		if ("add".equals(method) && (address == null || "".equals(address.trim()))) {
			throw new RuntimeException("请选择小区");
		}

		// 开始组装小区信息
		Set<String> groupBuildSet = new HashSet<String>();
		if (address != null && !"".equals(address.trim())) {
			// "province_12,city_232,block_23,groupBuilding_1"
			Map<String, Object> params = new HashMap<String, Object>();
			String[] addressList = address.split(",");
			for (String adrs : addressList) {
				String[] ids = adrs.split("_");
				String flag = ids[0];
				String id = ids[1];
				if ("province".equals(flag)) {
					params.put("provId", id);
					List<PlaceBean> citys = retrieveAddressList("/addressCity/getAddressCityListById.json", params);
					for (PlaceBean city : citys) {
						params.put("cityId", city.getId());
						List<PlaceBean> blocks = retrieveAddressList("/addressBlock/getAddressBlockListById.json", params);
						for (PlaceBean block : blocks) {
							params.put("blockId", block.getId());
							List<PlaceBean> groups = retrieveAddressList("/groupBuilding/getGroupBuildingListById.json", params);
							for (PlaceBean group : groups) {
								groupBuildSet.add(group.getId());
							}
						}
					}
				} else if ("city".equals(flag)) {
					params.put("cityId", id);
					List<PlaceBean> blocks = retrieveAddressList("/addressBlock/getAddressBlockListById.json", params);
					for (PlaceBean block : blocks) {
						params.put("blockId", block.getId());
						List<PlaceBean> groups = retrieveAddressList("/groupBuilding/getGroupBuildingListById.json", params);
						for (PlaceBean group : groups) {
							groupBuildSet.add(group.getId());
						}
					}
				} else if ("block".equals(flag)) {
					params.put("blockId", id);
					List<PlaceBean> groups = retrieveAddressList("/groupBuilding/getGroupBuildingListById.json", params);
					for (PlaceBean group : groups) {
						groupBuildSet.add(group.getId());
					}
				} else if ("groupBuilding".equals(flag)) {
					groupBuildSet.add(id);
				}
			}
			logger.info("组装的小区数据 = " + groupBuildSet);
		}
		
		if ("add".equals(method) && (groupBuildSet == null || groupBuildSet.size() <=0)) {
			throw new RuntimeException("请确定是否选中小区节点，或者该城市内没有小区");
		}
		
		// 上传图片
		File targetFile = null;
		MultiFileEntity fileEntity = CommonMultiFileUtil.parseRequsetFileInfo(request, "imgfile");
		if(fileEntity.getDatas() != null && fileEntity.getDatas().length > 0){
			targetFile = new File(fileEntity.getFileName());
			OutputStream os = new FileOutputStream(targetFile);
			InputStream ins = fileEntity.getInputStream();
			try {
				int bytesRead = 0;
				byte[] buffer = new byte[1024];
				while ((bytesRead = ins.read(buffer, 0, 1024)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
			} finally {
				os.close();
				ins.close();
			}
		}
		ContentType contentType = ContentType.create("multipart/form-data", Consts.UTF_8);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();        
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.setCharset(Charset.forName("UTF-8"));
		builder.addTextBody("title", title,contentType);
		builder.addTextBody("content", content,contentType);
		builder.addTextBody("delState", delState,contentType);
		if(!"edit".equals(method)){
			builder.addTextBody("type", DictConstants.Message_Type.NOTICE_MESSAGE_TYPE,contentType);
			builder.addTextBody("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),contentType);
			builder.addTextBody("creater", UserContext.getOperId(),contentType);
		}else if("edit".equals(method)){
			builder.addTextBody("id", request.getParameter("id"),contentType);
		}
		if(groupBuildSet!=null&&groupBuildSet.size()>0){
			builder.addTextBody("groupBuilds", groupBuildSet.toString(),contentType);
		}
		if(targetFile!=null){
			builder.addPart("imgfile", new FileBody(targetFile)); 
		}
		HttpEntity entity = builder.build();
		// 读取服务端返回数据
		JsonResponse jsonResponse = simpleHttpClient.doPost(url, entity, null, null);
		return jsonResponse;
	}
	
	@RequestMapping("/addresslist.html")
	@ResponseBody
	public Object addresslist(String msgId) throws Exception {
		logger.info("msgId = " + msgId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("msgId", msgId);
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/message//addresslist.json", params);
		logger.info(jsonResponse.getDataValue());
		DataValue dataValue = JSONObject.parseObject(String.valueOf(jsonResponse.getDataValue()), DataValue.class);
		List<MessageBean> beans = JSONArray.parseArray(dataValue.getList(), MessageBean.class);
		JsonMessage message = new JsonMessage();
		message.setTotal(dataValue.getCount()==null?0:dataValue.getCount());
		message.setRows(beans);
		return message;
	}

	@RequestMapping("/doAddNotice.html")
	@ResponseBody
	public Object doAddNotice(HttpServletRequest request) throws Exception {
		JsonMessage message = new JsonMessage();
		try {
			JsonResponse response = addOrEditNotice(request, message, "add","/message/messageAdd.json");
			if (!CommConstants.ResponseStatus.BUSINESS_FAILED.equals(response.getStatus())) {
				logger.info("httpclient请求成功");
				message.setMessage("新增物业公告成功");
				message.setResult("info");
			} else {
				message.setMessage("新增物业公告失败");
				message.setResult("error");
			}
		} catch (RuntimeException re) {
			message.setMessage(re.getMessage());
			message.setResult("error");
		} catch (Exception e) {
			logger.error(e);
			message.setMessage("新增物业公告失败");
			message.setResult("error");
		}
		return message;
	}
	
	@RequestMapping("/noticeEdit.html")
	public ModelAndView editNotice(String id, String pageType) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/notice/noticeEdit");
		modelAndView.addObject("pageType", pageType);
		
		Message msgBean = null;
		if(id!=null){//有id表示编辑，无id是新增
			msgBean = noticeService.getMessageDetail(new BigInteger(id));
			if(StringUtils.isNotBlank(msgBean.getContent())){
				msgBean.setContent(msgBean.getContent().replaceAll(" ", "&nbsp;"));
			}
			
			modelAndView.addObject("msgBean", msgBean);
			
			if(StringUtils.isNotBlank(msgBean.getTime())){
				modelAndView.addObject("publishTime", DateUtils.convertDateToStr(DateUtils.convertStrToDate(msgBean.getTime()), "yyyy年MM月dd日"));
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tMessageFId", id);
			List<MessagePrint> messagePrintList = messagePrintBaseService.getMessagePrintByCondition(paramMap);
			if(messagePrintList!=null && messagePrintList.size()>0 ){
				modelAndView.addObject("messagePrint", messagePrintList.get(0));
			}
		} else {
			modelAndView.addObject("now", DateTime.now().toString("yyyy年MM月dd日"));
		}
		
		dealExpiryDate(msgBean, modelAndView);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isadmin", UserContext.getCurrUser().getIsadmin());
		params.put("userId", UserContext.getOperId());
		params.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		List<GroupBuildingSimpleEntity> gbList = null;
		if (id == null) {
			gbList = noticeService.getGroupBuildingSimpleList(params);
		} else {
			gbList = noticeService.getGroupBuildingSimpleList(UserContext.getOperIdBigInteger(), new Integer(id));
		}
		modelAndView.addObject("gbList", gbList);
		return modelAndView;
	}
	
	/**
	 * 处理有效期，非空处理
	 * @param msgBean
	 * @param modelAndView
	 */
	private void dealExpiryDate(Message msgBean, ModelAndView modelAndView){
		if(msgBean == null){
			msgBean = new Message();
			modelAndView.addObject("msgBean", msgBean);
		}
		if(StringUtils.isBlank(msgBean.getExpiryDateStart())){
			msgBean.setExpiryDateStart(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
		}
		if(StringUtils.isBlank(msgBean.getExpiryDateEnd())){
			Date after3Day = DateUtils.addDays(new Date(), 3);
			String expiryDateEnd = DateUtils.convertDateToStr(after3Day, "yyyy-MM-dd HH:mm:ss");
			msgBean.setExpiryDateEnd(expiryDateEnd);
		}
	}
	
/*	之前老的方法
 * @RequestMapping("/editnotice.html")
	public ModelAndView editNotice(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/notice/editnotice");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/message/toMessageDetail.json", params);
		logger.info(jsonResponse.getDataValue());
		MessageBean bean = JSONObject.parseObject(String.valueOf(jsonResponse.getDataValue()), MessageBean.class);
		modelAndView.addObject("bean", bean);
		return modelAndView;
	}*/

	@RequestMapping("/doEditNotice.html")
	@ResponseBody
	public Object doEditNotice(HttpServletRequest request) throws Exception {
		JsonMessage message = new JsonMessage();
		try {
			JsonResponse response = addOrEditNotice(request, message, "edit","/message/messageUpd.json");
			if (!CommConstants.ResponseStatus.BUSINESS_FAILED.equals(response.getStatus())) {
				logger.info("httpclient请求成功");
				message.setMessage("编辑物业公告成功");
				message.setResult("info");
			} else {
				message.setMessage("编辑物业公告失败");
				message.setResult("error");
			}
		} catch (RuntimeException re) {
			message.setMessage(re.getMessage());
			message.setResult("error");
		} catch (Exception e) {
			logger.error(e);
			message.setMessage("编辑物业公告失败");
			message.setResult("error");
		}
		return message;
	}

	@RequestMapping("/noticeView.html")
	@ResponseBody
	public ModelAndView viewnotice(HttpServletRequest request) {
		BigInteger messageId = new BigInteger(request.getParameter("id"));
		MessageEntity messageDetail = noticeService.getMessageDetail(messageId);
		request.setAttribute("msgDetail", messageDetail);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/notice/noticeView");
		return modelAndView;
	}
	
	@RequestMapping("/noticeDelete.html")
	@ResponseBody
	public String viewNotice(String id) {
		BigInteger messageId = new BigInteger(id);
		noticeService.deleteMessageById(messageId);
		return "删除成功";
	}
	
	@RequestMapping("/noticeSave.html")
	//@ResponseBody
	public ModelAndView saveNotice(NoticeRequest noticeRequest, HttpServletRequest request) {
		//1要推送的小区id集
		String[] gdIds = request.getParameterValues("gbId");
		noticeRequest.setGbIds(gdIds);
		
		//2与数据库交互
		noticeService.saveNotice(noticeRequest);

		//3结果返回
		request.setAttribute(JSPConstants.OprtResult, "物业公告保存成功!");
		request.setAttribute(JSPConstants.ToURL, "../notice/index.html");
		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}

	/**
	 * 发布公告
	 * @param id
	 * @return
	 */
	@RequestMapping("/publish.html")
	@ResponseBody
	public JsonResponse publish(BigInteger id) {
		return noticeService.publish(id);
	}
	
	/**
	 * 统一处理List和Search请求
	 * 
	 * @param request
	 * @param paramMap
	 *            请求参数
	 */
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("userId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());

		int resultSize = noticeService.searchNoticeList_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<NoticeListBean> searchRestList = noticeService.searchNoticeList_forPage(curPageIndex, pageSize, paramMap);
		// 处理小区提示
		if(searchRestList!=null && searchRestList.size()>0){
			for(NoticeListBean noticeBean : searchRestList){
				List<String> gbNames = noticeBean.getGbNames();
				if(gbNames!=null && gbNames.size()>0){
					String gbNamesTip = gbNames.toString();
					gbNamesTip = gbNamesTip.substring(1, gbNamesTip.length()-1);
					
					noticeBean.setGbNamesTip(gbNamesTip);
				}
			}
		}

		request.setAttribute("resList", searchRestList);
	}
	
	/**
	 * 物业公告编辑搜索
	 * 
	 * @param gbName 小区名称
	 * @param pmName 物业名称
	 * @return
	 */
	@RequestMapping("/editNoticeForSearch.html")
	public ModelAndView editNoticeForSearch(String gbName, String pmName){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/notice/noticeEdit");
		return modelAndView;
	}
	
	/**
	 * 小区autocomplete框后台实现
	 * 
	 * @param gbName
	 * @return
	 */
	@RequestMapping("/gbFilter.html")
	@ResponseBody
	public JsonResponse gbFilter(String gbName){
		List<Map<String, Object>> gbs = noticeService.gbFilter(gbName);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(gbs);
		
		return jsonResponse;
	}
	
	/**
	 * 物业autocomplete框后台实现
	 * 
	 * @param pcName
	 * @return
	 */
	@RequestMapping("/pcFilter.html")
	@ResponseBody
	public JsonResponse propertyCompanyFilter(String pcName){
		List<Map<String, Object>> pcs = noticeService.propertyCompanyFilter(pcName);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(pcs);
		
		return jsonResponse;
	}
	
	/**
	 * 打印公告
	 * 
	 * @param msgId
	 * @return
	 * @throws IOException 
	 * @throws InvalidParameterException 
	 */
	@RequestMapping("print.html")
	public ModelAndView print(BigInteger msgId){
		ModelAndView mav = new ModelAndView("/notice/print");
		Message message = messageBaseService.getMessageBySeqId(msgId);
		mav.addObject("title", message.getTitle());
		mav.addObject("content", message.getContent());
		mav.addObject("now", DateTime.now().toString("yyyy-MM-dd"));
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tMessageFId", msgId);
		List<MessagePrint> messagePrintList = messagePrintBaseService.getMessagePrintByCondition(paramMap);
		if(messagePrintList!=null && messagePrintList.size()>0 ){
			MessagePrint messagePrint = messagePrintList.get(0);
			
			mav.addObject("pageHeader", messagePrint.getPageHeader());
			mav.addObject("signature", messagePrint.getSignature());
			mav.addObject("rqCode", messagePrint.getRqCode());
		}
		return mav;
	}
	
	/*@RequestMapping("print.html")
	public void print(BigInteger msgId, HttpServletResponse response) throws InvalidParameterException, IOException{
		Message message = messageBaseService.getMessageBySeqId(msgId);
		String pageHeader = "";
		String title = "";
		String content = "";
		String signature = "";
		Integer qCode = 0;
		if(StringUtils.isNotBlank(message.getTitle())){
			title = message.getTitle();
		}
		if(StringUtils.isNotBlank(message.getContent())){
			content = message.getContent();
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tMessageFId", msgId);
		List<MessagePrint> messagePrintList = messagePrintBaseService.getMessagePrintByCondition(paramMap);
		if(messagePrintList!=null && messagePrintList.size()>0 ){
			MessagePrint messagePrint = messagePrintList.get(0);
			
			if(StringUtils.isNotBlank(messagePrint.getPageHeader())){
				pageHeader = messagePrint.getPageHeader();
			}
			if(StringUtils.isNotBlank(messagePrint.getSignature())){
				signature = messagePrint.getSignature();
			}
			if(messagePrint.getRqCode()!=null){
				qCode = messagePrint.getRqCode();
			}
		}
		String html = getNoticePrintHtml(pageHeader, title, content, signature, qCode);
		System.err.println(html);
		Html2PdfUtil.html2pdf(response, html, null);
	}
	
	private static final String getNoticePrintHtml(String pageHeader, String title, String content, String signature, Integer qCode){
		StringBuilder sb = new StringBuilder(100);
		sb.append("<html>");
		sb.append("    <head>");
		sb.append("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
		sb.append("        <style>");
		sb.append("            .content{margin: 50px 20px;}");
		sb.append("            .pageHeader{text-align: center;color: red;}");
		sb.append("            .title{text-align: center;}");
		sb.append("            .rqCode{margin-top: 50px;}");
		sb.append("            .rqCode div{text-align: center;}");
		sb.append("            .signature{margin-top: 20px;}");
		sb.append("            .signature, .now{text-align: right;margin-right: 4em;}");
		sb.append("            img{width: 200px;}");
		sb.append("        </style>");
		sb.append("    </head>");
		sb.append("    <body>");
		sb.append("        <div class=\"content\">");
		sb.append("            <div class=\"pageHeader\"><h1>").append(pageHeader).append("</h1></div>");
		sb.append("            <div class=\"title\"><h2>").append(title).append("</h2></div>");
		sb.append("            <div class=\"content\">").append(content).append("</div>");
		sb.append("            <div class=\"signature\">").append(signature).append("</div>");
		sb.append("            <div class=\"now\">").append(DateTime.now().toString("yyyy-MM-dd")).append("</div>");
		if(qCode ==1){
			sb.append("        <div class=\"rqCode\">");
			sb.append("            <div>");
			sb.append("                <img src=\"http://public.image.jiefangqu.com/notice/jfq_app_down.png\"/>");
			sb.append("            </div>");
			sb.append("        </div>");
		}
		sb.append("        </div>");
		sb.append("    </body>");
		sb.append("</html>");
		return sb.toString();
	}*/
}