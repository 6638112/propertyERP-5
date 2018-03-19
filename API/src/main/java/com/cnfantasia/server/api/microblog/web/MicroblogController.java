/**   
* Filename:    MicroblogController.java   
* @version:    1.0  
* Create at:   2014年7月18日 上午3:20:59   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.microblog.web;

import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.util.RelativeDateFormat;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.microblog.constant.MicroblogConstant;
import com.cnfantasia.server.api.microblog.constant.MicroblogDict;
import com.cnfantasia.server.api.microblog.entity.MicroblogContentEntity;
import com.cnfantasia.server.api.microblog.entity.XibaoEntity;
import com.cnfantasia.server.api.microblog.entity.XibaoGroupBuilding;
import com.cnfantasia.server.api.microblog.service.IMicroblogService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.entity.BuildingEntity;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.api.user.service.IUserService;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.microblogQueue.cache.McVisitRecordCache;
import com.cnfantasia.server.commbusi.microblogQueue.constant.MicroblogQueueConstant;
import com.cnfantasia.server.commbusi.microblogQueue.entity.PropertyGoodNewsRowDataEntity;
import com.cnfantasia.server.commbusi.microblogQueue.entity.PropertyGoodNewsShellEntity;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.TimeOutRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.FileUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.lanternFestivalPrize.entity.LanternFestivalPrize;
import com.cnfantasia.server.domainbase.microblogContent.entity.MicroblogContent;
import com.cnfantasia.server.domainbase.microblogReport.entity.MicroblogReport;
import com.cnfantasia.server.domainbase.microblogType.entity.MicroblogType;

/**
 * Filename:    MicroblogController.java
 * @version:    1.0.0
 * Create at:   2014年7月18日 上午3:20:59
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月18日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/microblog")
public class MicroblogController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private IUserService userService;
	
	private IMicroblogService microblogService;
	public final void setMicroblogService(IMicroblogService microblogService) {
		this.microblogService = microblogService;
	}
	
	private IFileServerService fileServerService;
	public final void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
//	private ISysParamParser microblogPicParamParser;
//	public final void setMicroblogPicParamParser(ISysParamParser microblogPicParamParser) {
//		this.microblogPicParamParser = microblogPicParamParser;
//	}
//	
//	private ISysParamParser userImageParamParser;
//	public void setUserImageParamParser(ISysParamParser userImageParamParser) {
//		this.userImageParamParser = userImageParamParser;
//	}
	
	private ISysParamParser microblogTypeIcoParamParser;
	public void setMicroblogTypeIcoParamParser(ISysParamParser microblogTypeIcoParamParser) {
		this.microblogTypeIcoParamParser = microblogTypeIcoParamParser;
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
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	@Resource
	private IUuidManager uuidManager;
	@Resource
    private ISysParamParser fileServerParamParser;

	@RequestMapping("/qryHomeShow.json")
	@ResponseBody
	public JsonResponse qryHomeShow(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		GroupBuilding defaultArea=microblogService.getGroupBuildingByUserId(userId);
		String areaName = defaultArea.getName();
		Map<MicroblogType,MicroblogContent> resMap = microblogService.getHomeInfo(defaultArea.getId(),userId);
		String microBlogTypeIcoBasePath = microblogTypeIcoParamParser.parseParamValue();
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
		for(MicroblogType tmpType:resMap.keySet()){
			MicroblogContent tmpBlog = resMap.get(tmpType);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("topicId", tmpType.getId());
			map.put("topicName", tmpType.getName());
			map.put("topicUrl", StringUtils.isEmpty(tmpType.getIcon())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(microBlogTypeIcoBasePath+tmpType.getIcon(),tmpType));
			map.put("hotMessageContent", tmpBlog.getText());
			try {
				map.put("hotMessageTime",  RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(tmpBlog.getCreateTime()),nowTimeLong));
			} catch (Exception e) {
				logger.error(e);
				map.put("hotMessageTime","");
			}
			topicList.add(map);
		}
		jsonResponse.putData("topicList", topicList);
		return jsonResponse;
	}
	
	@RequestMapping("/qryMicroblogTypes.json")
	@ResponseBody
	public JsonResponse qryMicroblogTypes(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//2.交互
		List<MicroblogType> microblogTypeList = microblogService.getMicroblogTypeList(pageModel);
		String microBlogTypeIcoBasePath = microblogTypeIcoParamParser.parseParamValue();
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(MicroblogType tmpObj:microblogTypeList){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("typeId", tmpObj.getId());
			tmpMap.put("typeName", tmpObj.getName());
			tmpMap.put("typeIcon", StringUtils.isEmpty(tmpObj.getIcon())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(microBlogTypeIcoBasePath+tmpObj.getIcon(),tmpObj));
			tmpMap.put("typeIconBefore", StringUtils.isEmpty(tmpObj.getIconBefore())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(sysParamManager.getSysParaValue(SysParamKey.MICROBLOG_TYPE_ICO_BEFORE_BASE_PATH)+tmpObj.getIconBefore(),tmpObj));
			tmpMap.put("typeIconAfter", StringUtils.isEmpty(tmpObj.getIconAfter())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(sysParamManager.getSysParaValue(SysParamKey.MICROBLOG_TYPE_ICO_AFTER_BASE_PATH)+tmpObj.getIconAfter(),tmpObj));
			resList.add(tmpMap);
		}
//		{
//			Map<String,Object> tmpMap = new HashMap<String, Object>();
//			tmpMap.put("typeId", "1");
//			tmpMap.put("typeName", "微博类别1");
//			tmpMap.put("typeIcon", "http://127.0.0.1/a.jpg");
//			resList.add(tmpMap);
//		}
//		{
//			Map<String,Object> tmpMap = new HashMap<String, Object>();
//			tmpMap.put("typeId", "2");
//			tmpMap.put("typeName", "微博类别2");
//			tmpMap.put("typeIcon", "http://127.0.0.1/b.jpg");
//			resList.add(tmpMap);
//		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	@RequestMapping("/qryMicroblogList.json")
	@ResponseBody
	public JsonResponse qryMicroBlogList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String microblobTypeIdStr = request.getParameter("microblobTypeId");
		BigInteger microblobTypeId = null;
		if(!StringUtils.isEmpty(microblobTypeIdStr)){
			microblobTypeId=new BigInteger(microblobTypeIdStr);
		}
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		McVisitRecordCache.addVisitCount(microblogService.getGroupBuildingIdByUserId(userId));//缓存请求次数
		String nowUpdTime = microblogService.fetchAllMicroblogLastUpdTime(microblobTypeId, pageModel, userId);
		if(!ControllerUtils.checkHasNewData(request, jsonResponse, nowUpdTime)){
			return jsonResponse;
		}
		List<MicroblogContentEntity> microblogContentEntityList=new ArrayList<MicroblogContentEntity>();
		if(microblobTypeId!=null){
			microblogContentEntityList = microblogService.getMicroblogList(microblobTypeId, pageModel,userId);
		}else{
			microblogContentEntityList = microblogService.getMicroblogList(pageModel,userId);
		}
		String nowTime = dualDao.getNowTime();
		Long nowTimeLong = DateUtil.timeToLong(nowTime);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(MicroblogContentEntity tmpObj:microblogContentEntityList){
			Map<String,Object> tmpMap = commEntityConvertService.microblogContent2Map(tmpObj, tmpObj.getMicroblogPicList(), tmpObj.getUserInfo(), nowTimeLong,tmpObj.getExtIsFavour(),tmpObj.getExtFavourCount(),tmpObj.getExtCommentCount(),true);
//			tmpMap.put("linkTitle", "测试标题a");
//			tmpMap.put("linkDetail", "http://www.baidu.com");
			resList.add(tmpMap);			
		}
		
		Date company_Activity_End_Date = DateUtils.convertStrToDate(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Company_Activity_End_Date), "yyyy-MM-dd HH:mm:ss");
		Date company_Activity_Start_Date = DateUtils.convertStrToDate(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Company_Activity_Start_Date), "yyyy-MM-dd HH:mm:ss");
		if(company_Activity_Start_Date != null && company_Activity_End_Date != null && pageModel.currentPage==0/*第1页才需要插入*/ && new Date().after(company_Activity_Start_Date)
				&& new Date().before(company_Activity_End_Date)){//增加周年庆活动
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("id", "1001");
			tmpMap.put("createTime", RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Company_Activity_Start_Date)),nowTimeLong));
			tmpMap.put("txt", "感谢您的厚爱陪伴，物业缴费、到家服务、超市等服务通通逐步完善，给您送去最贴心的社区服务！在街坊发布对解放区的祝福，砸金蛋！老板马上派现金粮票！￥￥￥最高领100元哦！（全平台通用）");
			tmpMap.put("activityName", "#解放区周年庆#");
			List<String> picList = new ArrayList<String>();
			
			String basePath = "anniversary_celebration/";
	        /*FileServerConfigEntity fileServerConfigEntity = fileServerParamParser.parseParamValue();
	        String accessBaseUrl = fileServerConfigEntity.getAccessBaseUrl();*/
			String imageServerUrl = sysParamManager.getImageServerUrl(basePath);
			picList.add(imageServerUrl + basePath + "zhou.png");
			picList.add(imageServerUrl + basePath + "nian.png");
			picList.add(imageServerUrl + basePath + "qing.png");
			tmpMap.put("picList", picList);
			tmpMap.put("userName", "解放区");
			tmpMap.put("userPicUrl", "http://127.0.0.1/user.jpg");
			tmpMap.put("favourCount", "500");
			tmpMap.put("isFavour", true);
			tmpMap.put("commentCount", "5625");
			tmpMap.put("sourceType", MicroblogDict.MicroblogContent_SourceType.Activity);
			tmpMap.put("linkTitle", CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Neighbour_Activity_LinkTitle));
			tmpMap.put("linkDetail", CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Neighbour_Activity_LinkDetail));
			
			tmpMap.put("userId", 100); //100是“解放区”用户，系统发贴都是用这个用户
			tmpMap.put("ext_room_isAdmin",false);
					
			resList.add(0, tmpMap);
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
	
	@RequestMapping("/qryMicroblogDetail.json")
	@ResponseBody
	public JsonResponse qryMicroblogDetail(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger id =new BigInteger(request.getParameter("id"));
		BigInteger userId = UserContext.getOperIdBigInteger();
		String contextPath = request.getContextPath(); 
		String reportPath = request.getScheme()+"://"+request.getServerName()+":"+
                request.getServerPort()+contextPath+"/microblog/reportPage.html";
		//2.交互
		MicroblogContentEntity tmpObj = microblogService.getMicroblogDetail(id,userId);
		String nowTime = dualDao.getNowTime();
		Long nowTimeLong = DateUtil.timeToLong(nowTime);
		//3.结果处理
		if(tmpObj!=null){
			Map<String,Object> tmpMap = commEntityConvertService.microblogContent2Map(tmpObj, tmpObj.getMicroblogPicList(), tmpObj.getUserInfo(), nowTimeLong,tmpObj.getExtIsFavour(),tmpObj.getExtFavourCount(),tmpObj.getExtCommentCount(),false);
			String pathUrl = request.getSession().getServletContext().getRealPath("");
			String nickName= tmpObj.getUserInfo().getNickName()==null?"":tmpObj.getUserInfo().getNickName();
			tmpMap.put("microblogReportUrl", reportPath+"?id="+id+"&huaId="+tmpObj.getUserInfo().getHuaId()
					+"&nickName="+nickName);//IOS举报功能html地址
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
	
	@RequestMapping("/postMicroblog.json")
	@ResponseBody
	public JsonResponse postMicroblog(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String typeIdStr = request.getParameter("typeId");
		BigInteger typeId =null;
		if(!StringUtils.isEmpty(typeIdStr)){
			typeId = new BigInteger(typeIdStr);
		}
		if(typeId==null){//若获取到微博类别为空使用默认的类别
			typeId = MicroblogConstant.DEFAULT_MICROBLOG_TYPEID;
		}
		String messageTxt = request.getParameter("messageTxt");
		String activityName = request.getParameter("activityName");//运营活动名称, eg. “#解放区周年庆#”
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
		microblogService.postMicroblog(userId, messageTxt, activityName, typeId, picList);
		//3.结果处理
		return jsonResponse;
	}
	public static void main(String[] args) {
		String resStr = null;
		try {
			URI uri = new URI(MicroblogController.class.getResource("")+"detailJosn");
			String filePath = uri.getPath();
			byte[] bytes = FileUtils.fileToByte(filePath);
			resStr = new String(bytes);
		} catch (Exception e) {}
		System.out.println(resStr);
		PropertyGoodNewsShellEntity detailData = null;
		detailData = JSON.parseObject(resStr, PropertyGoodNewsShellEntity.class);
		System.out.println(detailData);
	}
	/**
	 * 调整到链接详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/linkDetail.html")
	public ModelAndView linkDetail(HttpServletRequest request){
		//1.搜集参数
		BigInteger blogId = ParamUtils.getBigInteger(request, MicroblogQueueConstant.Param_MicroBlogId, null);
		//2.交互
		PropertyGoodNewsShellEntity detailData = null;
		if(blogId!=null){
			try {
				String detailJson = microblogService.getMicroblogLinkDetail(blogId);
				if(!StringUtils.isEmpty(detailJson)){
					detailData = JSON.parseObject(detailJson, PropertyGoodNewsShellEntity.class);
				}
			} catch (Exception e) {
				logger.info("linkDetail.html cause exception",e);
			}
			
		}
		//3.结果处理
		String gbName = "*小区";
		String propMonth = "*";
		String propPayEndDay = "*";
		Long userCount = 0L;
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		if(detailData!=null){
			gbName = detailData.fetchGbName();
			propMonth = detailData.fetchPropMonth();
			propPayEndDay = detailData.fetchPropPayEndDay();
			userCount = detailData.getTotalPayUserCount();
			List<PropertyGoodNewsRowDataEntity> srcDataList = detailData.getDataList();
			if(srcDataList!=null&&srcDataList.size()>0){
				for(PropertyGoodNewsRowDataEntity tmpAA:srcDataList){
					Map<String,Object> tmpMap = new HashMap<String, Object>();
					tmpMap.put("order", tmpAA.getOrder());
					tmpMap.put("buildingName", tmpAA.getBuildingName());
					tmpMap.put("jfNum", tmpAA.getJfNum());
					tmpMap.put("phoneTail", tmpAA.getPhoneTail());
					tmpMap.put("discount", tmpAA.getDiscount()==null?"无":tmpAA.getDiscount());
					tmpMap.put("savedMoney", tmpAA.getSavedMoney()==null?0:PriceUtil.div100(tmpAA.getSavedMoney()));
					dataList.add(tmpMap);
				}
			}
		}
		
		ModelAndView mav = new ModelAndView();
		request.setAttribute("gbName", gbName);
		request.setAttribute("propMonth", propMonth);
		request.setAttribute("propPayEndDay", propPayEndDay);
		request.setAttribute("userCount", userCount);
		request.setAttribute("dataList", dataList);
		mav.setViewName("/xibaoPage/xibao");
		return mav;
	}
	
	/**
	 * 调整到链接详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/xibao.html")
	public ModelAndView xibao(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		BigInteger userId = UserContext.getOperIdBigInteger();
		if(userId == null) {
			logger.debug("financeController user is null!sessionKey:" + request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY) + ",userId:" + ParamUtils.getLong(request, "", 0L));
			userId = ParamUtils.getBigInteger(request, "userId", null);
		}
		if(userId == null) {
			throw new TimeOutRuntimeException();
		}
		UserEntity userEntity = userService.getUserById(userId);
		BuildingEntity buildingEntity = userEntity.getDefaultRoomEntity().getRealRoomEntity().getBuildingEntity();
		BigInteger groupBuildId = buildingEntity.gettGroupBuildingFId();
		
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupBuildId", groupBuildId);
		paramMap.put("pageSize", 10);
		paramMap.put("beginData", 0);
		XibaoGroupBuilding xibaoGroupBuilding = microblogService.selectXibaoGroupBuilding(paramMap);
		if(xibaoGroupBuilding != null) {
			paramMap.put("pbDateStr", xibaoGroupBuilding.getPbDateStr());
			List<XibaoEntity> dataList = microblogService.selectXibaoList(paramMap);
			int totalCount = microblogService.getSelectXibaoListCount(paramMap);
			if(dataList != null && totalCount > 10) {//判断是否还有下一页
				request.setAttribute("lastPage", false);
			} else {
				request.setAttribute("lastPage", true);
			}
			request.setAttribute("gbName", buildingEntity.getGroupBuildingEntity().getName());
			request.setAttribute("xibaoGroupBuilding", xibaoGroupBuilding);
			request.setAttribute("dataList", dataList);
		}
		
		mav.setViewName("/xibaoPage/xibaoNew");
		return mav;
	}
		
	/**
	 * 查询喜报数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/getXiBaoDataList.json")
	@ResponseBody
	public JsonResponse getXiBaoDataList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();

		Integer pageNo = new Integer(request.getParameter("pageNo"));
		Integer pageSize = 10;
		BigInteger userId = UserContext.getOperIdBigInteger();
		
		if(userId == null) {
			logger.debug("financeController user is null!sessionKey:" + request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY) + ",userId:" + ParamUtils.getLong(request, "", 0L));
			userId = ParamUtils.getBigInteger(request, "userId", null);
		}
		if(userId == null) {
			throw new TimeOutRuntimeException();
		}
		UserEntity userEntity = userService.getUserById(userId);
		BuildingEntity buildingEntity = userEntity.getDefaultRoomEntity().getRealRoomEntity().getBuildingEntity();
		BigInteger groupBuildId = buildingEntity.gettGroupBuildingFId();
		
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupBuildId", groupBuildId);
		paramMap.put("pageNo", pageNo);
		paramMap.put("pageSize", pageSize);
		paramMap.put("beginData", (pageNo-1)*pageSize);
		XibaoGroupBuilding xibaoGroupBuilding = microblogService.selectXibaoGroupBuilding(paramMap);
		List<XibaoEntity> dataList = new ArrayList<XibaoEntity>();
		if(xibaoGroupBuilding != null) {
			paramMap.put("pbDateStr", xibaoGroupBuilding.getPbDateStr());
			dataList = microblogService.selectXibaoList(paramMap);
		}
		
		//返回数据信息
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount = microblogService.getSelectXibaoListCount(paramMap);
		if((pageNo*pageSize) < totalCount) {
			resMap.put("lastPage", false);
		} else {
			resMap.put("lastPage", true);
		}
		
		resMap.put("pageNo", pageNo);
		resMap.put("dataList", dataList);
		
		jsonResponse.setDataValue(resMap);
		
		return jsonResponse;
	}
	
	
	/**
	 * 元宵节活动
	 * @param request
	 * @return
	 */
	@RequestMapping("/lanternFestivalPrize.html")
	public ModelAndView lanternFestivalPrize(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageSize", 500);//本次活动只有200份猜灯迷奖品
		paramMap.put("beginData", 0);
		
		List<LanternFestivalPrize> dataList = microblogService.selectLanternFestivalPrizeList(paramMap);
		int totalCount = microblogService.getSelectLanternFestivalPrizeCount(paramMap);
		if(dataList != null && totalCount > 500) {//判断是否还有下一页
			request.setAttribute("lastPage", false);
		} else {
			request.setAttribute("lastPage", true);
		}
		request.setAttribute("dataList", dataList);
		
		mav.setViewName("/lanternFestival/prize");
		return mav;
	}
	
//	/**判断是否为新版App*/
//	private boolean checkIsV300(HttpServletRequest request){
//		boolean isV300 = false;
//		String version = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION);
//		Long historyVersion = null;
//		if(!StringUtils.isEmpty(version)){
//			historyVersion = VersionTransferUtil.versionStr2Long(version);
//			Long v300Version = VersionTransferUtil.versionStr2Long("3.0.0");
//			if(historyVersion.compareTo(v300Version)>=0){
//				isV300 = true;
//			}
//		}
//		return isV300;
//	}
	
	/**
	 * IOS举报跳转页面
	 */
	@RequestMapping("/reportPage.html")
	public String reportDetail(HttpServletRequest request,ModelMap model){
		BigInteger id =ParamUtils.getBigInteger(request, "id", null);
		BigInteger huaId =ParamUtils.getBigInteger(request, "huaId", null);
		String nickName = ParamUtils.getString(request, "nickName",null);
		model.addAttribute("id", id);
		if(nickName!=null){
			model.addAttribute("nickName", nickName);
		}else{
			model.addAttribute("nickName", huaId);
		}
		return "/reportPage/index";
	}
	
	/**
	 * IOS举报功能
	 */
	@RequestMapping("/microblogReport.html")
	public String microblogReport(HttpServletRequest request){
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		BigInteger id =ParamUtils.getBigInteger(request, "id",null);
		String reportInfo = ParamUtils.getString(request, "reportInfo");
		String areaInfo = ParamUtils.getString(request, "reportTextAreaInfo",null);
		//2.交互
		MicroblogContentEntity tmpObj = microblogService.getMicroblogDetail(id,userId);
		if(tmpObj != null){
			BigInteger reportId = uuidManager.getNextUuidBigInteger(SEQConstants.t_microblog_report);
			MicroblogReport microblogReport = new MicroblogReport();
			microblogReport.setId(reportId);
			microblogReport.setReportInfo(reportInfo);
			microblogReport.settMicroblogContentFId(id);
			microblogReport.setAreaInfo(areaInfo);
			microblogReport.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
			microblogReport.setSys0DelState(0);
			microblogService.saveReport(microblogReport);
			return "/reportPage/done";
		}else{
			throw new BusinessRuntimeException("microblogReport.tmpObj.isEmpty");
		}
	}
}
