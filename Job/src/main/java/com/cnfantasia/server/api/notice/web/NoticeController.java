package com.cnfantasia.server.api.notice.web;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.notice.entity.NoticeBean;
import com.cnfantasia.server.api.notice.service.INoticeService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.RoomEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.HtmlTagFilterUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.user.entity.User;
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
		User user = userBaseService.getUserBySeqId(userId);
		RoomEntity roomEntity = commonRoomService.getDefaultRoomInfoWithSummon(userId);
		GroupBuildingEntity groupBuildingEntity = roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity();
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
//			noticeMap.put("content", bean.getContent());//syl-upd-2014-11-20 10:46:43
			noticeMap.put("content", HtmlTagFilterUtil.removeHtmlTagInfo(bean.getContent()));
			noticeMap.put("picUrl", bean.getPicPath()==null?"":bean.getPicPath());
			
			String picUrlAll = null;
			if(!StringUtils.isEmpty(bean.getPicPath())){//picUrlAll
				String relativePath = noticeSysParamParser.parseParamValue()+bean.getPicPath();
				picUrlAll = fileServerService.getAccessUrl(relativePath, "");
//				noticeMap.put("picUrlAll", picUrlAll);
			}/*else{
				noticeMap.put("picUrlAll", null);
			}*/
			noticeMap.put("picUrlAll", picUrlAll==null?"":picUrlAll);//syl-upd-2015-3-11 17:01:34
			
//			{//detailUrl
//				String path = request.getContextPath();
//				String basePath = request.getScheme() + "://"
//						+ request.getServerName() + ":" + request.getServerPort()
//						+ path;
//				noticeMap.put("detailUrl",basePath+NoticeConstant.Dedatil_Interface_Url+bean.getId());//TODO
//			}
			
			resList.add(noticeMap);
		}
		jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
		//返回 小区签约状态和是否点召唤的信息
		{
			BigInteger pcId = groupBuildingEntity.gettPropertyManagementFId();
			String mobilePhone = noticeService.queryMobilePhone(pcId);
			Boolean isSign = groupBuildingEntity.isSign();
			Boolean isSummon = groupBuildingEntity.getIsSummon();
			jsonResponse.putData("signStatus", isSign);
//			if(isSign!=null&&!isSign){
//				isSummon = false;
//			}
//			if(beans!=null&&beans.size()>0){
//				isSummon = true;
//			}
			jsonResponse.putData("mobilePhone", mobilePhone);
			jsonResponse.putData("haveSummon", isSummon);
			String connectInfo = sysParamManager.getSysParaValue(SysParamKey.Oos_Connect_Info);
			jsonResponse.putData("connectInfo", connectInfo);//"http://www.jiefangqu.com/oos/";
		}
		return jsonResponse;
	}
	
	
//@RequestMapping("/updMsgState.json")
//@ResponseBody
//public JsonResponse updMsgState(HttpServletRequest request){
//	JsonResponse jsonResponse = new JsonResponse();
//	//1.搜集参数
//	BigInteger msgId = new BigInteger(request.getParameter("msgId"));
//	Integer state = Integer.parseInt(request.getParameter("state"));
//	BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//	//2.交互
//	boolean updRes = userService.updMsgState(userId, msgId, state);
//	if(!updRes){
//		throw new BusinessRuntimeException("user.updMsgState.failed");
//	}
//	//3.结果处理
//	return jsonResponse;
//}
//
//@RequestMapping("/delMsg.json")
//@ResponseBody
//public JsonResponse delMsg(HttpServletRequest request){
//	JsonResponse jsonResponse = new JsonResponse();
//	//1.搜集参数
//	BigInteger msgId = new BigInteger(request.getParameter("msgId"));
//	BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//	//2.交互
//	boolean delRes = userService.deleteMsg(userId, msgId);
//	if(!delRes){
//		throw new BusinessRuntimeException("user.delMsg.failed");
//	}
//	//3.结果处理
//	return jsonResponse;
//}
	
}
