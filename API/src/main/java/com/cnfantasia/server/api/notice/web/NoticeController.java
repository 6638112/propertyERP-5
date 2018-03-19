package com.cnfantasia.server.api.notice.web;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.notice.entity.NoticeBean;
import com.cnfantasia.server.api.notice.nuomi.entity.NoticeData;
import com.cnfantasia.server.api.notice.nuomi.entity.NoticeItem;
import com.cnfantasia.server.api.notice.nuomi.entity.NoticeMonth;
import com.cnfantasia.server.api.notice.service.INoticeService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.HtmlTagFilterUtil;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;

/**
 * 
 * 类说明：物业公告action
 * 
 * @author hunter
 * @since 2014年6月4日上午9:56:40
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {
	
	private INoticeService noticeService;
	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private ISysParamParser noticeSysParamParser;
	public void setNoticeSysParamParser(ISysParamParser noticeSysParamParser) {
		this.noticeSysParamParser = noticeSysParamParser;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	@Resource
	private IUserBaseService userBaseService;
	/**
	 * 查询物业公告列表
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list.json")
	@ResponseBody
	public JsonResponse list(HttpServletRequest request) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Map<String, Object> groupBuildingMap = noticeService.queryGroupBuildingSignAndSummon(userId);
		//2.交互
		List<NoticeBean> beans = noticeService.queryPropertyNoticeList(pageModel, userId);
		//3.结果处理
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, String>> resList = new ArrayList<Map<String,String>>();
		for(NoticeBean bean:beans){
			Map<String, String> noticeMap = new HashMap<String, String>();
			noticeMap.put("id", bean.getId().toString());
			noticeMap.put("title", bean.getTitle());
			noticeMap.put("time", dateFormat.format(bean.getNoticeDate()));
			noticeMap.put("content", HtmlTagFilterUtil.removeHtmlTag(bean.getContent()));
			noticeMap.put("picUrl", bean.getPicPath()==null?"":bean.getPicPath());
			
			String picUrlAll = null;
			if(StringUtils.isNotBlank(bean.getPicPath())){//picUrlAll
				String relativePath = noticeSysParamParser.parseParamValue()+bean.getPicPath();
				picUrlAll = fileServerService.getAccessUrl(relativePath, "");
			}
			noticeMap.put("picUrlAll", picUrlAll==null?"":picUrlAll);//syl-upd-2015-3-11 17:01:34
			resList.add(noticeMap);
		}
		jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
		//返回 小区签约状态和是否点召唤的信息
		{
			jsonResponse.putData("signStatus", groupBuildingMap.get("signStatus"));
			jsonResponse.putData("mobilePhone", groupBuildingMap.get("tel"));
			jsonResponse.putData("haveSummon", groupBuildingMap.get("haveSummon"));
			String connectInfo = sysParamManager.getSysParaValue(SysParamKey.Oos_Connect_Info);
			jsonResponse.putData("connectInfo", connectInfo);//"http://www.jiefangqu.com/oos/";
		}
		return jsonResponse;
	}
	
	/*=======================糯米对接start=======================*/
	/**
	 * （糯米）公告列表查询
	 * 
	 * @param city
	 * @param block
	 * @param gbName
	 * @param page
	 * @return
	 */
	@RequestMapping("/noticeList.json")
	@ResponseBody
	public JsonResponse noticeListForNuomi(String city, String block, String gbName, Integer page, HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");  
		
		/**截取掉“市”、“区”，以便统一处理*/
		if(StringUtils.isNotBlank(city) && city.endsWith("市")){
			city = city.substring(0, city.length()-1);
		}
		if(StringUtils.isNotBlank(block) && block.endsWith("区")){
			block = block.substring(0, block.length()-1);
		}
		
		int pageNum = 10;
		JsonResponse jsonResponse = new JsonResponse();
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("city", city);
		paramMap.put("block", block);
		paramMap.put("gbName", gbName);
		Integer count = noticeService.selectNoticeCountForNuomi(paramMap);

		paramMap.put("_begin", (page-1)*pageNum);
		paramMap.put("_length", pageNum);
		List<NoticeMonth> noticeMonths = noticeService.selectNoticeListForNuomi(paramMap);
		if(noticeMonths!=null && noticeMonths.size()>0){
			for(NoticeMonth noticeMonth:noticeMonths){
				List<NoticeItem> noticeItems = noticeMonth.getNoticeItems();
				for(NoticeItem noticeItem:noticeItems){
					if(StringUtils.isNotBlank(noticeItem.getContent())){
						String content = HtmlTagFilterUtil.removeHtmlTag(noticeItem.getContent());
						noticeItem.setContent(content);
					}
				}
			}
		}
		
		boolean hasNext = (page*pageNum < count);
		
		NoticeData noticeData = new NoticeData();
		noticeData.setHasNext(hasNext);
		noticeData.setNoticeMonths(noticeMonths);
		
		jsonResponse.setDataValue(noticeData);
		return jsonResponse;
	}
	/*======================= 糯米对接end =======================*/
	
}
