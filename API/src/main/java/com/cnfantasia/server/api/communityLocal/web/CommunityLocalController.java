/**   
* Filename:    CommunityLocalController.java   
* @version:    1.0  
* Create at:   2014年7月9日 上午2:31:16   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communityLocal.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.util.RelativeDateFormat;
import com.cnfantasia.server.api.communityLocal.constant.CommunityLocalDict;
import com.cnfantasia.server.api.communityLocal.entity.CommunityForumContentEntity;
import com.cnfantasia.server.api.communityLocal.service.ICommunityLocalService;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.communityForumContent.entity.CommunityForumContent;
import com.cnfantasia.server.domainbase.communityForumType.entity.CommunityForumType;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

/**
 * Filename:    CommunityLocalController.java
 * @version:    1.0.0
 * Create at:   2014年7月9日 上午2:31:16
 * Description: 社区论坛模块-本地实现
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月9日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/communityLocal")
public class CommunityLocalController extends BaseController{
	
	private ICommunityLocalService communityLocalService;
	public final void setCommunityLocalService(ICommunityLocalService communityLocalService) {
		this.communityLocalService = communityLocalService;
	}
	
	private IFileServerService fileServerService;
	public final void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private ISysParamParser communityForumTypeIcoParamParser;
	public void setCommunityForumTypeIcoParamParser(ISysParamParser communityForumTypeIcoParamParser) {
		this.communityForumTypeIcoParamParser = communityForumTypeIcoParamParser;
	}
	
	private ISysParamParser groupBuildingIconParamParser;
	public void setGroupBuildingIconParamParser(ISysParamParser groupBuildingIconParamParser) {
		this.groupBuildingIconParamParser = groupBuildingIconParamParser;
	}
	
	private ISysParamParser groupBuildingPicDescParamParser;
	public void setGroupBuildingPicDescParamParser(ISysParamParser groupBuildingPicDescParamParser) {
		this.groupBuildingPicDescParamParser = groupBuildingPicDescParamParser;
	}
	
	private IDualDao dualDao;
	public final void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
//	private ISysParamManager sysParamManager;
//	public void setSysParamManager(ISysParamManager sysParamManager) {
//		this.sysParamManager = sysParamManager;
//	}

	
	@RequestMapping("/qryHomeShow.json")
	@ResponseBody
	public JsonResponse qryHomeShow(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		GroupBuilding defaultArea=communityLocalService.getGroupBuildingByUserId(userId);
		String areaName = defaultArea.getName();
		Map<CommunityForumType,CommunityForumContent> resMap = communityLocalService.getHomeInfo(defaultArea.getId(),userId,CommunityLocalDict.CommunityForum_Type_ModelType.ComminityType);
		String forumTypeIcoBasePath = communityForumTypeIcoParamParser.parseParamValue();
		String areaIconBasePath=groupBuildingIconParamParser.parseParamValue();
		String areaPicDescBasePath=groupBuildingPicDescParamParser.parseParamValue();
		//3.结果处理
		jsonResponse.putData("areaName", areaName);
		{//小区图标
			String iconUrl = defaultArea.getIconUrl();
			if(StringUtils.isEmpty(iconUrl)){
				iconUrl = RoomConstants.DEFAULT_GROUPBUILDING_ICON;
			}
			jsonResponse.putData("areaIcoUrl", StringUtils.isEmpty(iconUrl)?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(areaIconBasePath+iconUrl,defaultArea));
		}
		{//小区大图
			String picDescUrl = defaultArea.getPicDescUrl();
			if(StringUtils.isEmpty(picDescUrl)){
				picDescUrl = RoomConstants.DEFAULT_GROUPBUILDING_PICDESC;
			}
			jsonResponse.putData("areaDescUrl", StringUtils.isEmpty(picDescUrl)?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(areaPicDescBasePath+picDescUrl,defaultArea));
		}
		List<Map<String,Object>> topicList = new ArrayList<Map<String,Object>>();
		String nowTime = dualDao.getNowTime();
		Long nowTimeLong = DateUtil.timeToLong(nowTime);
		for(CommunityForumType tmpType:resMap.keySet()){
			CommunityForumContent tmpBlog = resMap.get(tmpType);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("topicId", tmpType.getId());
			map.put("topicName", tmpType.getName());
			map.put("topicUrl", StringUtils.isEmpty(tmpType.getIcon())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(forumTypeIcoBasePath+tmpType.getIcon(),tmpType));
			map.put("hotMessageContent", tmpBlog.getText());
			map.put("hotMessageTime",  RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(tmpBlog.getCreateTime()),nowTimeLong));
			topicList.add(map);
		}
		jsonResponse.putData("topicList", topicList);
		return jsonResponse;
	}
	/**
	 * 查询广告类别
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryCommunityForumTypesOfAds.json")
	@ResponseBody
	public JsonResponse qryCommunityForumTypesOfAds(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		List<CommunityForumType> communityForumTypeList = communityLocalService.getCommunityForumTypeList(CommunityLocalDict.CommunityForum_Type_ModelType.Is8Type,pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		for(CommunityForumType tmpObj:communityForumTypeList){
			Map<String,Object> tmpMap = communityLocalService.communityForumType2Map(userId,tmpObj,basePath);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	
	}
	@RequestMapping("/qryCommunityForumTypes.json")
	@ResponseBody
	public JsonResponse qryCommunityForumTypes(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
//		List<CommunityForumType> communityForumTypeList = communityLocalService.getCommunityForumTypeList(CommunityLocalDict.CommunityForum_Type_ModelType.ComminityType,pageModel);
		List<CommunityForumType> communityForumTypeList = communityLocalService.getCommunityForumTypeNot8List(pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		for(CommunityForumType tmpObj:communityForumTypeList){
			Map<String,Object> tmpMap = communityLocalService.communityForumType2Map(userId,tmpObj,basePath);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	/**
	 * 此地址 名称url不可随便修改
	 * @param request
	 * @return
	 */
	@RequestMapping("/jumpForumTypeUrl.json")
	public ModelAndView jumpForumTypeUrl(HttpServletRequest request){
		//1.搜集参数
		BigInteger forumTypeId = null;
		try {
			forumTypeId=new BigInteger(request.getParameter("id"));
		} catch (Exception e) {
		}
		//2.交互
		CommunityForumType communityForumType=null;
		if(forumTypeId!=null){
			communityForumType=communityLocalService.getCommunityForumTypeById(forumTypeId);
		}
		String goalUrl = communityForumType==null?null:communityForumType.getUrlFor8Goal();
		if(StringUtils.isEmpty(goalUrl)){
			throw new BusinessRuntimeException("CommunityLocalController.jumpForumTypeUrl.goalUrl.isEmpty");
		}
		//3.结果处理
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:"+goalUrl);
		return mav;
	}
	
	@RequestMapping("/qryMicroblogList.json")
	@ResponseBody
	public JsonResponse qryMicroBlogList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String microblobTypeIdStr = request.getParameter("topicId");
		BigInteger microblobTypeId = null;
		if(!StringUtils.isEmpty(microblobTypeIdStr)){
			microblobTypeId=new BigInteger(microblobTypeIdStr);
		}
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		List<CommunityForumContentEntity> communityForumContentEntityList=new ArrayList<CommunityForumContentEntity>();
		if(microblobTypeId!=null){
			communityForumContentEntityList = communityLocalService.getForumList(microblobTypeId, pageModel,userId);
		}else{
			communityForumContentEntityList = communityLocalService.getForumList(pageModel,userId);
		}
		String nowTime = dualDao.getNowTime();
		Long nowTimeLong = DateUtil.timeToLong(nowTime);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(CommunityForumContentEntity tmpObj:communityForumContentEntityList){
			Map<String,Object> tmpMap = communityLocalService.communityForumContent2Map(tmpObj, tmpObj.getCommunityForumPicList(), tmpObj.getUserInfo(), nowTimeLong,tmpObj.getExtIsFavour(),tmpObj.getExtFavourCount(),tmpObj.getExtCommentCount());
			resList.add(tmpMap);
		}
//		{
//			Map<String,Object> tmpMap = new HashMap<String, Object>();
//			tmpMap.put("id", "1001");
//			tmpMap.put("createTime", "3分钟前");
//			tmpMap.put("txt", "第一次发微博，好紧张。(⊙o⊙)");
//			List<String> picList = new ArrayList<String>();
//			picList.add("http://127.0.0.1/a1.jpg");
//			picList.add("http://127.0.0.1/a2.jpg");
//			tmpMap.put("picList", picList);
//			tmpMap.put("userName", "小明");
//			tmpMap.put("userPicUrl", "http://127.0.0.1/user.jpg");
//			tmpMap.put("favourCount", "500");
//			tmpMap.put("isFavour", true);
//			tmpMap.put("commentCount", "5625");
//			resList.add(tmpMap);
//		}
//		{
//			Map<String,Object> tmpMap = new HashMap<String, Object>();
//			tmpMap.put("id", "1002");
//			tmpMap.put("createTime", "5分钟前");
//			tmpMap.put("txt", "第二次发微博。O(∩_∩)O~");
//			List<String> picList = new ArrayList<String>();
//			picList.add("http://127.0.0.1/a1.jpg");
//			picList.add("http://127.0.0.1/a2.jpg");
//			tmpMap.put("picList", picList);
//			tmpMap.put("userName", "小花");
//			tmpMap.put("userPicUrl", "http://127.0.0.1/user.jpg");
//			tmpMap.put("favourCount", "370");
//			tmpMap.put("isFavour", false);
//			tmpMap.put("commentCount", "980");
//			resList.add(tmpMap);
//		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	@RequestMapping("/qryForumDetail.json")
	@ResponseBody
	public JsonResponse qryMicroblogDetail(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger id =new BigInteger(request.getParameter("id"));
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		CommunityForumContentEntity tmpObj = communityLocalService.getForumDetail(id,userId);
		String nowTime = dualDao.getNowTime();
		Long nowTimeLong = DateUtil.timeToLong(nowTime);
		//3.结果处理
		{
			Map<String,Object> tmpMap = communityLocalService.communityForumContent2Map(tmpObj, tmpObj.getCommunityForumPicList(), tmpObj.getUserInfo(), nowTimeLong,tmpObj.getExtIsFavour(),tmpObj.getExtFavourCount(),tmpObj.getExtCommentCount());
			jsonResponse.setDataValue(tmpMap);
		}
//		{
//			Map<String,Object> tmpMap = new HashMap<String, Object>();
//			tmpMap.put("id", "1002");
//			tmpMap.put("createTime", "5分钟前");
//			tmpMap.put("txt", "第二次发微博。O(∩_∩)O~");
//			List<String> picList = new ArrayList<String>();
//			picList.add("http://127.0.0.1/a1.jpg");
//			picList.add("http://127.0.0.1/a2.jpg");
//			tmpMap.put("picList", picList);
//			tmpMap.put("userName", "小花");
//			tmpMap.put("userPicUrl", "http://127.0.0.1/user.jpg");
//			tmpMap.put("favourCount", "370");
//			tmpMap.put("isFavour", false);
//			tmpMap.put("commentCount", "980");
//			jsonResponse.setDataValue(tmpMap);
//		}
		return jsonResponse;
	}
	
	@RequestMapping("/postForumContent.json")
	@ResponseBody
	public JsonResponse postMicroblog(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger typeId =new BigInteger(request.getParameter("topicId"));
		String messageTxt = request.getParameter("messageTxt");
//		List<String> picInfoParams = null;
//		{
//			if (request instanceof MultipartHttpServletRequest) {// 获取图片
//				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//				Iterator<String> paramNames=multipartRequest.getFileNames();
//				while(paramNames.hasNext()){
//					String tmpName=paramNames.next();
//					if(tmpName.startsWith("picInfo")){
//						if(picInfoParams==null){
//							picInfoParams=new ArrayList<String>();
//						}
//						picInfoParams.add(tmpName);
//					}
//				}
//			}
//		}
//		List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfo(request, picInfoParams);
		List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "picInfo");
		
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		communityLocalService.postForum(userId, messageTxt, typeId, picList);
		//3.结果处理
		return jsonResponse;
	}
	
}
