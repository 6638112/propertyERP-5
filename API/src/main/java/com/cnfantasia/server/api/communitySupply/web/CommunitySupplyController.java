/**   
* Filename:    CommunitySupplyController.java   
* @version:    1.0  
* Create at:   2014年8月26日 上午3:38:48   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.web;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.entity.CompanyInfoConfig;
import com.cnfantasia.server.api.commSysPara.parser.CompanyInfoParamParser;
import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.comments.entity.CommentsPointsAvgEntity;
import com.cnfantasia.server.api.common.HomePageList;
import com.cnfantasia.server.api.commonBusiness.entity.SimpleResultMap;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.communitySupply.constant.CommunityPhoneTypeConstant;
import com.cnfantasia.server.api.communitySupply.constant.CommunitySupplyDict;
import com.cnfantasia.server.api.communitySupply.entity.BaiduLocation;
import com.cnfantasia.server.api.communitySupply.entity.CallCountEntity;
import com.cnfantasia.server.api.communitySupply.entity.CommunityPhoneTypeEntity;
import com.cnfantasia.server.api.communitySupply.entity.CommunitySupplyEntity;
import com.cnfantasia.server.api.communitySupply.entity.CommunitySupplyTypeEntity;
import com.cnfantasia.server.api.communitySupply.entity.CommunitySupplyTypeFinanceEntity;
import com.cnfantasia.server.api.communitySupply.entity.GroupBuildingHasTCommunitySupply_Supply;
import com.cnfantasia.server.api.communitySupply.entity.Level3ListMapEntity;
import com.cnfantasia.server.api.communitySupply.service.ICommunitySupplyService;
import com.cnfantasia.server.api.communitySupply.service.IHomeSupplyTypeService;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdForLstSales;
import com.cnfantasia.server.api.ebuy.service.IEbuyNewService;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.flashDealActivity.service.IFlashDealActivityService;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.constant.DictConstants;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.entity.PropertyManagementEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.room.service.IRoomService;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressCity.service.AddressCityBaseService;
import com.cnfantasia.server.domainbase.commentsLabel.entity.CommentsLabel;
import com.cnfantasia.server.domainbase.commentsPoints.entity.CommentsPoints;
import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.entity.GroupBuildingHasTCommunitySupply;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Filename:    CommunitySupplyController.java
 * @version:    1.0.0
 * Create at:   2014年8月26日 上午3:38:48
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月26日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/communitySupply")
public class CommunitySupplyController extends BaseController{
	
	private Log logger = LogFactory.getLog(CommunitySupplyController.class);
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private ICommunitySupplyService communitySupplyService;
	public void setCommunitySupplyService(ICommunitySupplyService communitySupplyService) {
		this.communitySupplyService = communitySupplyService;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private IHomeSupplyTypeService homeSupplyTypeService;
	public void setHomeSupplyTypeService(IHomeSupplyTypeService homeSupplyTypeService) {
		this.homeSupplyTypeService = homeSupplyTypeService;
	}

	private IFlashDealActivityService flashDealActivityService;
	public void setFlashDealActivityService(IFlashDealActivityService flashDealActivityService) {
		this.flashDealActivityService = flashDealActivityService;
	}

	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

	@Resource
	private IAddressOperationService addressOperationService;

	@Resource
	private IGroupBuildingBaseService groupBuildingBaseService;

	@Resource
	private IRoomService roomService;


	@RequestMapping("/qrySupplyDetail.json")
	@ResponseBody
	public JsonResponse qrySupplyDetail(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger supplyId = new BigInteger(request.getParameter("id"));
		BigInteger groupBuildingId = null;
		{
			String groupBuildingIdStr = request.getParameter("groupBuildingId");
			if(!StringUtils.isEmpty(groupBuildingIdStr)){
				groupBuildingId = new BigInteger(groupBuildingIdStr);
			}
		}
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		GroupBuildingHasTCommunitySupply_Supply tmpEntity = communitySupplyService.getCommunitySupplyDetail(supplyId,userId,groupBuildingId);
		//3.结果处理
		Map<String,Object> resMap = groupBuildingHasTCommunitySupply2Map(tmpEntity, tmpEntity.getCommunitySupplyEntity(), tmpEntity.getCurrUserSupplyBelong(), tmpEntity.getSuccessSupplyBelong());
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	@RequestMapping("/qrySupplyTypeList.json")
	@ResponseBody
	public JsonResponse qrySupplyTypeList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger parentTypeId = new BigInteger(request.getParameter("parentTypeId"));
		Integer level = Integer.valueOf(request.getParameter("level"));
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//2.交互
		List<CommunitySupplyTypeEntity> communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeList(parentTypeId, level, pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(CommunitySupplyTypeEntity communitySupplyType:communitySupplyTypeList){
			Map<String,Object> tmpMap = commEntityConvertService.communitySupplyType2Map(communitySupplyType,communitySupplyType.getTopCommunitySupplyType());
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	@RequestMapping("/qrySupplyTypeAll.json")
	@ResponseBody
	@Deprecated
	public JsonResponse qrySupplyTypeAll(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		Level3ListMapEntity resEntity = homeSupplyTypeService.getSupplyTypeAll();
		List<Map<String,Object>> level01List = resEntity.getLevel01List();
		List<Map<String,Object>> level02List = resEntity.getLevel02List();
		//3.结果处理
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("level01List", level01List);
		resMap.put("level02List", level02List);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	
	@RequestMapping("/qrySupplyTypeList4Url.json")
	@ResponseBody
	@Deprecated
	public JsonResponse qrySupplyTypeList4Url(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		Level3ListMapEntity resEntity = homeSupplyTypeService.getSupplyTypeList4Url();
		List<Map<String,Object>> level01List = resEntity.getLevel01List();
		List<Map<String,Object>> level02List = resEntity.getLevel02List();
		//3.结果处理
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("level01List", level01List);
		resMap.put("level02List", level02List);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	@RequestMapping("/qrySupplyTypeList3Level.json")
	@ResponseBody
	@Deprecated
	public JsonResponse qrySupplyTypeList3Level(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		Level3ListMapEntity resEntity = homeSupplyTypeService.getSupplyTypeList3Level(userId);
		List<Map<String,Object>> level00List = resEntity.getLevel00List();
		List<Map<String,Object>> level01List = resEntity.getLevel01List();
		
		List<Map<String,Object>> level02List = resEntity.getLevel02List();

		BigInteger gbId = commonRoomService.getGroupBuildingIdByUserId(userId);
		GroupBuilding gb = groupBuildingBaseService.getGroupBuildingBySeqId(gbId);
		// 查询是否是有活动（t_flash_deal_activity）。
		boolean needRemove = false;
		for (Map<String, Object> map : level01List) {
			if ("homeAds".equals(map.get("code"))) {
				List<SimpleResultMap> subList = (ArrayList<SimpleResultMap>)map.get("subList");
				SimpleResultMap remove = null;
				if (subList != null) {
					for (SimpleResultMap simpleResultMap : subList) {
						if ("flashDealActivity".equals(simpleResultMap.get("code"))) {
							if (DataUtil.isEmpty(userId)) {
								needRemove = true;
							} else {
								if (DataUtil.isEmpty(gbId) || gbId.compareTo(new BigInteger("-1")) == 0
										|| flashDealActivityService.getActivityList(userId, null, gbId, new PageModel(0, 1)).size() <= 0) {
									needRemove = true;
								}
							}
							if (needRemove) {
								remove = simpleResultMap;
							}
						}
						simpleResultMap = dealOne(simpleResultMap);
					}
					if (needRemove) {
						subList.remove(remove);
						map.put("subList", subList);
					}
				}
			}
		}
		//3.结果处理
		Map<String,Object> resMap = new HashMap<String, Object>();
		//未签约小区不显示level0
		resMap.put("level00List", HomePageList.list(level00List));
		resMap.put("level01List", HomePageList.list(level01List));
		resMap.put("level02List", HomePageList.list(level02List));
		jsonResponse.setDataValue(resMap);
		logger.debug("qrySupplyTypeList3Level:" + JSON.toJSONString(jsonResponse));
		return jsonResponse;
	}
	
	@Resource
	private IEbuyNewService ebuyNewService;
	/**
	 * <p>超市推广商品可跳转到详情处理</p>
	 * 查询链接里面对应的商品的数量，如果为1，直接将跳转地址修改为详情页的地址。
	 * 
	 * @param simpleResultMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private SimpleResultMap dealOne(SimpleResultMap simpleResultMap) {
		if (simpleResultMap.get("linkUrl") != null) {
			String url = String.valueOf(simpleResultMap.get("linkUrl"));
			String key = "advId=";
			if (url.contains("ebuyNew/themeProductAd.html") && url.contains(key)) {
				String advId = url.substring(url.indexOf(key) + key.length()).trim();
				List<EbuyProdForLstSales> products = ebuyNewService.selectAdvertiseThemeProduct(new BigInteger(advId));
				if (products != null && products.size() == 1) {
					simpleResultMap.put("code", "filmTicket");
					simpleResultMap.put("isAppUrl", true);
					simpleResultMap.put("supplyTypeId", products.get(0).getId());
					simpleResultMap.put("isLink", false);
				}
			}
		}
		
		return simpleResultMap;
	}

	
	@RequestMapping("/qrySupplyTypeList2Level.json")
	@ResponseBody
	public JsonResponse qrySupplyTypeList2Level(HttpServletRequest request){
		return qrySupplyTypeList3Level(request);
	}
	
	/**
	 * 轻应用师傅端首页调用
	 * @param request
	 * @return
	 */
	@RequestMapping("/qrySupplyTypeList3LevelByCityName.json")
	@ResponseBody
	public JsonResponse qrySupplyTypeList3LevelByCityName(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		String cityName = request.getParameter("cityName");
		
		
		//2.交互
		if(cityName ==null){
			throw new ValidateRuntimeException("cityName can't be null. ");
		}
		
		AddressCity city = new AddressCity();
		city.setName(cityName);
		
		AddressCityBaseService addressCityBaseService = (AddressCityBaseService)CnfantasiaCommbusiUtil.getBeanManager("addressCityBaseService");
		List<AddressCity> cityList = addressCityBaseService.getAddressCityByConditionDim(MapConverter.convertBean(city));
		if (cityList.size() == 0) {
			throw new ValidateRuntimeException("can't find city by name " + cityName);
		} else if (cityList.size() > 1) {
			throw new ValidateRuntimeException("find more than one city by name " + cityName);
		}

		Level3ListMapEntity resEntity = homeSupplyTypeService.getSupplyTypeList3Level(userId, null, null, cityList.get(0).getId(), null, null);
		
		List<Map<String,Object>> level01List = resEntity.getLevel01List();
		//3.结果处理
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("level01List", level01List);
		jsonResponse.setDataValue(resMap);
		logger.debug("qrySupplyTypeList3Level:" + JSON.toJSONString(jsonResponse));
		return jsonResponse;
	}
	
	@RequestMapping("/searchSupplyList.json")
	@ResponseBody
	public JsonResponse searchSupplyList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String typeIdStr = request.getParameter("typeId");
		if(StringUtils.isEmpty(typeIdStr)){
			throw new ValidateRuntimeException("communitySupply.searchSupplyList.typeId.empty");
		}
		BigInteger typeId = new BigInteger(request.getParameter("typeId"));
		String supplyNameKey = request.getParameter("supplyNameKey");
		
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdBigInteger();
		BaiduLocation baiduLocation = null;//百度地址
		{
			String longitudeStr = request.getParameter("longitude");
			String latitudeStr = request.getParameter("latitude");
			if(!StringUtils.isEmpty(longitudeStr)&&!StringUtils.isEmpty(latitudeStr)){
				baiduLocation = new BaiduLocation(Double.valueOf(longitudeStr), Double.valueOf(latitudeStr));
			}
		}
		//2.交互
		BigInteger groupBuildingId = communitySupplyService.fetchGroupBuildingIdByPosition(userId, baiduLocation);
		List<GroupBuildingHasTCommunitySupply_Supply> tmpEntityList = communitySupplyService.searchCommunitySupplyList(typeId, supplyNameKey, pageModel,userId,groupBuildingId);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(tmpEntityList!=null&&tmpEntityList.size()>0){
			for(GroupBuildingHasTCommunitySupply_Supply tmpEntity:tmpEntityList){
				Map<String,Object> resMap = groupBuildingHasTCommunitySupply2Map(tmpEntity, tmpEntity.getCommunitySupplyEntity(), tmpEntity.getCurrUserSupplyBelong(), tmpEntity.getSuccessSupplyBelong());
				resList.add(resMap);
			}
		}
		jsonResponse.putData("groupBuildingId", groupBuildingId);
		if(tmpEntityList!=null){
			return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
		}else{
			return ControllerUtils.addPageInfo(jsonResponse, resList);
		}
	}
	
	@RequestMapping("/qrySupplyCommentsTypeList.json")
	@ResponseBody
	public JsonResponse qrySupplyCommentsTypeList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger typeId = new BigInteger(request.getParameter("typeId"));
		//2.交互
		List<CommentsLabel> commentsLabelList = communitySupplyService.getCommentsLabelListBySupplyType(typeId);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(CommentsLabel commentsLabel:commentsLabelList){
			Map<String,Object> tmpMap = commEntityConvertService.commentsLabel2Map(commentsLabel);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	@RequestMapping("/qrySupplyCommentsPointsList.json")
	@ResponseBody
	public JsonResponse qrySupplyCommentsPointsList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger typeId = new BigInteger(request.getParameter("typeId"));
		//2.交互
		List<CommentsPoints> commentsPointsList = communitySupplyService.getCommentsPointsListBySupplyType(typeId);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(CommentsPoints commentsLabel:commentsPointsList){
			Map<String,Object> tmpMap = commEntityConvertService.commentsPoints2Map(commentsLabel);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	@RequestMapping("/addCallCount.json")
	@ResponseBody
	public JsonResponse addCallCount(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger contectId = new BigInteger(request.getParameter("contectId"));
		//2.交互
		CallCountEntity callCountEntity = communitySupplyService.addCallCount(contectId);
		//3.结果处理
		jsonResponse.putData("count", callCountEntity.getCount());
		jsonResponse.putData("totalCallCount", callCountEntity.getTotalCallCount());
		return jsonResponse;
	}
	
	@RequestMapping("/qrySupplyTypeRecommend.json")
	@ResponseBody
	public JsonResponse qrySupplyTypeRecommend(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		List<CommunitySupplyTypeEntity> communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeRecommend();
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(CommunitySupplyTypeEntity tmpCommunitySupplyType:communitySupplyTypeList){
			Map<String,Object> tmpMap = commEntityConvertService.communitySupplyType2Map(tmpCommunitySupplyType,tmpCommunitySupplyType.getTopCommunitySupplyType());
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	private Map<String,Object> communitySupply2Map(CommunitySupply communitySupply
			,List<CommunitySupplyContect> communitySupplyContectList,CommunitySupplyTypeEntity communitySupplyTypeEntity
			,List<CommunitySupplyPic> communitySupplyPicList,CommentsEntity firstComments,Integer collectFlag,Boolean isPropertySuggest
			,Integer commentTotalCount){

		CommunitySupplyType topCommunitySupplyType = null;
		if(communitySupplyTypeEntity!=null&&communitySupplyTypeEntity.getTopCommunitySupplyType()!=null){
			topCommunitySupplyType = communitySupplyTypeEntity.getTopCommunitySupplyType();
		}
		Map<String,Object> tmpMap = commEntityConvertService.communitySupply2Map(communitySupply, communitySupplyContectList,topCommunitySupplyType);
		{//商家图片列表
			List<String> picList = new ArrayList<String>();
			if(communitySupplyPicList!=null){
				for(CommunitySupplyPic tmpCommunitySupplyPic:communitySupplyPicList){
					String picUrl = tmpCommunitySupplyPic.getPicUrl();
					if(!StringUtils.isEmpty(picUrl)){
						picList.add(StringUtils.isEmpty(picUrl)?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_SUPPLY_PIC_LIST_BASEPATH)+picUrl, tmpCommunitySupplyPic));
					}
				}
			}else{//图片列表没有的时候取用商家图片
				picList.add(StringUtils.isEmpty(communitySupply.getPicUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_SUPPLY_BASIC_PICPATH)+communitySupply.getPicUrl(), communitySupply));
			}
			tmpMap.put("ext_PicList", picList);
		}
		if(isPropertySuggest!=null){
			tmpMap.put("isPropertySuggest", isPropertySuggest);
		}
		//第一条评论信息
		if(firstComments!=null){
			tmpMap.put("ext_firstComments", commEntityConvertService.comments2Map(firstComments, firstComments.getUserGroupBuilding(),firstComments.getUser(), firstComments.getNoticeUserList(),firstComments.getCommentsLabelList(),firstComments.getCommentsHasTCommentsPointsEntityList(),firstComments.getAvgTotalStarLevel()));
		}
		//是否收藏标识
		if(collectFlag!=null&&CommunitySupplyDict.CommunitySupply_IsCollect.YES.compareTo(collectFlag)==0){
			tmpMap.put("ext_CollectFlag", true);
		}else{
			tmpMap.put("ext_CollectFlag", false);
		}
		//总评论数
		if(commentTotalCount!=null){
			tmpMap.put("ext_commentTotalCount", commentTotalCount);
		}
		return tmpMap;
	}
	
	/**
	 * @param groupBuildingHasTCommunitySupply 关系信息
	 * @param communitySupplyEntity 商家基本信息
	 * @param currUserSupplyBelong 当前用户认领信息
	 * @param successSupplyBelong 成功认领信息
	 * @return
	 */
	private Map<String,Object> groupBuildingHasTCommunitySupply2Map(GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupply
			,CommunitySupplyEntity communitySupplyEntity
			,CommunitySupplyBelong currUserSupplyBelong,CommunitySupplyBelong successSupplyBelong,List<CommentsPointsAvgEntity> commentsPointsAvgEntityList){
		Map<String,Object> resMap = communitySupply2Map(communitySupplyEntity, communitySupplyEntity.getCommunitySupplyContectList()
				,communitySupplyEntity.getCommunitySupplyTypeEntity()
				,communitySupplyEntity.getCommunitySupplyPicList(),communitySupplyEntity.getFirstComments(),communitySupplyEntity.getCollectFlag()
				,communitySupplyEntity.getIsPropertySuggest(),communitySupplyEntity.getCommentTotalCount());
		{	//小区与商家的距离 syl-2015-1-7 19:01:25 add
			resMap.put("ext_distance", groupBuildingHasTCommunitySupply.getDistance()==null?null:(groupBuildingHasTCommunitySupply.getDistance().intValue())+"m");//距离
			//syl-add 2015-1-30 15:46:52
			resMap.put("ext_groupBuildSupplyRelaId", groupBuildingHasTCommunitySupply.getId());//小区商家关系Id
			resMap.put("ext_currUser_claimStatus", currUserSupplyBelong==null?null:currUserSupplyBelong.getAuditStatus());//当前用户的认领状态
			resMap.put("ext_currUser_claimDesc", currUserSupplyBelong==null?null:currUserSupplyBelong.getAuditDesc());//当前用户认领审核描述
			resMap.put("ext_isClaimedSucc", (successSupplyBelong!=null&&successSupplyBelong.getId()!=null)?true:false);//是否已被成功认领
		}
		
		if(commentsPointsAvgEntityList!=null){
			List<Map<String,Object>> totalPointDataList = new ArrayList<Map<String,Object>>();
			for(CommentsPointsAvgEntity currCommentsPointsAvgEntity:commentsPointsAvgEntityList){
				Map<String,Object> tmpPointDataMap = new HashMap<String, Object>();
				tmpPointDataMap.put("name", currCommentsPointsAvgEntity.getName());
				tmpPointDataMap.put("value", currCommentsPointsAvgEntity.getAvgPoints());
				totalPointDataList.add(tmpPointDataMap);
			}
			resMap.put("ext_totalPointDataList", totalPointDataList);
		}
		return resMap;
	}
	
	/**
	 * 
	 * @param groupBuildingHasTCommunitySupply 关系信息
	 * @param communitySupplyEntity 商家基本信息
	 * @param currUserSupplyBelong 当前用户认领信息
	 * @param successSupplyBelong 成功认领信息
	 * @return
	 */
	private Map<String,Object> groupBuildingHasTCommunitySupply2Map(GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupply
			,CommunitySupplyEntity communitySupplyEntity
			,CommunitySupplyBelong currUserSupplyBelong,CommunitySupplyBelong successSupplyBelong){
		List<CommentsPointsAvgEntity> commentsPointsAvgEntityList = null;
		return groupBuildingHasTCommunitySupply2Map(groupBuildingHasTCommunitySupply, communitySupplyEntity, currUserSupplyBelong, successSupplyBelong, commentsPointsAvgEntityList);
	}

	/**
	 * 501版本废弃
	 * @return
     */
	@RequestMapping(value = "/financeLevel.json", method = RequestMethod.GET)
	@ResponseBody
	@Deprecated
	public JsonResponse getFinanceLevelIcon() {
		JsonResponse json = new JsonResponse();
		//理财类的用配置的形式，上架审核期间刷缓存
		String financeStr = sysParamManager.getSysParaValue(SysParamKey.Community_Supply_Type_Finance);
		if (!DataUtil.isEmpty(financeStr)) {
			CommunitySupplyTypeFinanceEntity entity = JSON.parseObject(financeStr, CommunitySupplyTypeFinanceEntity.class);
			boolean hasUpShelf = entity.isHasUpShelf();
			Long subChannelIdLong = HeaderManager.getSubChannelIdLong();
			//已上架或非IOS返回数据
			if (hasUpShelf || (subChannelIdLong != null && subChannelIdLong.intValue() != DictConstants.Channel_Sub.IOS)) {
				String picBase = sysParamManager.getImageServerUrl(SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH) +
						sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH);
				BigInteger userId = UserContext.getOperIdBigInteger();
				BigInteger gbId = commonRoomService.getGroupBuildingIdByUserIdForEbuy(userId);
				if(gbId == null) {
					gbId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
				}
				List<BigInteger> codeIdList = addressOperationService.getCodeIdList(null, null, null, null, gbId);
				//codeIdList用来筛选内容范围
				entity.setAreaIdList(codeIdList);
				//picBasePath用来拼图片路径
				entity.setPicBasePath(picBase);
				json.putData("list", entity.getEntityList());
			}
		}
		return json;
	}

	@RequestMapping(value = "qryHelpPhoneTypeList.json", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse getHelpPhoneTypeList() {

		BigInteger userId = UserContext.getOperIdBigInteger();
		JsonResponse json = new JsonResponse();
		//根据版本号来，无版本号不返回数据
		Integer version = HeaderManager.getVersionNum();
		if (version == null) {
			return json;
		}
		
//		BigInteger gbId = roomInfo.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getId();
		BigInteger gbId = commonRoomService.getGroupBuildingIdByUserId(userId);
		
		List<CommunityPhoneTypeEntity> communityPhoneTypeList = communitySupplyService.getCommunityPhoneTypeList(version, gbId);
		List<CommunityPhoneTypeEntity> resList = new ArrayList<>(communityPhoneTypeList.size());
		for (CommunityPhoneTypeEntity communityPhoneTypeEntity : communityPhoneTypeList) {
			//解放区客服电话
			if (CommunityPhoneTypeConstant.PhoneTypeCode.Jfq_Service_Phone.equals(communityPhoneTypeEntity.getCode())) {
				CompanyInfoParamParser companyInfoParamParser = (CompanyInfoParamParser) CnfantasiaCommbusiUtil.getBeanManager("companyInfoParamParser");
				CompanyInfoConfig companyInfoConfig = companyInfoParamParser.parseParamValue();
				communityPhoneTypeEntity.setPhone(companyInfoConfig.getTel());

				resList.add(communityPhoneTypeEntity);
			} else if (CommunityPhoneTypeConstant.PhoneTypeCode.Gb_Management.equals(communityPhoneTypeEntity.getCode())) {//管理处电话
				if (!RoomConstants.DEFAULT_GROUP_BUILDING_ID.equals(gbId) && userId != null) {//未添加门牌，排除此项
					RoomEntityWithValidate roomInfo = roomService.getDefaultRoomInfo(userId);
					PropertyManagementEntity propertymanager = roomInfo.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getPropertyManagementEntity();
					communityPhoneTypeEntity.setPhone(propertymanager == null ? null : propertymanager.getTel());
					resList.add(communityPhoneTypeEntity);
				}
			} else {
				resList.add(communityPhoneTypeEntity);
			}
		}
		json.putData("list", resList);
		return json;
	}
}
