package com.cnfantasia.server.ms.notice.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.ms.commonBusiness.entity.MultiFileEntity;
import com.cnfantasia.server.ms.commonBusiness.util.CommonMultiFileUtil;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.dto.DataValue;
import com.cnfantasia.server.ms.notice.dto.MessageBean;
import com.cnfantasia.server.ms.notice.dto.PlaceBean;
import com.cnfantasia.server.ms.notice.entity.MessageEntity;
import com.cnfantasia.server.ms.notice.service.INoticeService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.DictConstants;
import com.cnfantasia.server.ms.pub.entity.JsonMessage;
import com.cnfantasia.server.ms.pub.session.UserContext;

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

	private IHttpClient simpleHttpClient;
	public void setSimpleHttpClient(IHttpClient simpleHttpClient) {
		this.simpleHttpClient = simpleHttpClient;
	}
	
	private INoticeService noticeService;
	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}


	/**
	 * 物业公告首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", UserContext.getCurrUser().getId());

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
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title", title);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		paramMap.put("userId", UserContext.getCurrUser().getId());

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
	public ModelAndView editNotice(String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/notice/noticeEdit");
		Message msgBean = new Message();
		if(id!=null){//有id表示编辑，无id是新增
			msgBean = noticeService.getMessageDetail(new BigInteger(id));
		}
		modelAndView.addObject("msgBean", msgBean);

		List<GroupBuildingSimpleEntity> gbList = noticeService.getGroupBuildingSimpleList(UserContext.getCurrUser().getId());
		if (id != null) {
			gbList = noticeService.getGroupBuildingSimpleList(UserContext.getCurrUser().getId(), new Integer(id));
		}
		modelAndView.addObject("gbList", gbList);
		return modelAndView;
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
	public ModelAndView saveNotice(HttpServletRequest request) {
		//1收集参数
		String msgId = request.getParameter("msgId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pushWay = request.getParameter("pushWay");// 推送方式
		String pushTime = request.getParameter("date01");// 推送时间
		//要推送的小区，ID集
		String[] gdIds = request.getParameterValues("gbId");
		//2与数据库交互
		noticeService.saveNotice(msgId, title, content, pushWay, pushTime, gdIds);

		//3结果返回
		ModelAndView modelAndView = new ModelAndView();
		request.setAttribute("result", "物业公告保存成功!");
		modelAndView.setViewName("/notice/noticeOprtSuccess");//先跳到保存成功中转页，然后再跳回/notice/noticeList
		return modelAndView;
	}

	/**
	 * 统一处理List和Search请求
	 * 
	 * @param request
	 * @param paramMap
	 *            请求参数
	 */
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		int resultSize = noticeService.searchNoticeList_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<Message> searchRestList = noticeService.searchNoticeList_forPage(curPageIndex, pageSize, paramMap);

		request.setAttribute("resList", searchRestList);
	}
}