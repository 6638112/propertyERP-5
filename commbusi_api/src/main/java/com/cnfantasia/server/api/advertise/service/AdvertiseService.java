/**   
* Filename:    AdvertiseService.java   
* @version:    1.0  
* Create at:   2014年12月1日 上午5:24:23   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.advertise.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.advertise.constant.AdvertiseConstant;
import com.cnfantasia.server.api.advertise.entity.AdvertiseModel;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.communityLocal.service.ICommunityLocalService;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.microblog.service.IMicroblogService;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.version.util.VersionTransferUtil;
import com.cnfantasia.server.api.xanadu.service.IXanaduService;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.communityAds.entity.CommunityAds;

/**
 * Filename:    AdvertiseService.java
 * @version:    1.0.0
 * Create at:   2014年12月1日 上午5:24:23
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月1日       shiyl             1.0             1.0 Version
 */
public class AdvertiseService implements IAdvertiseService{
//	private IExchangeService exchangeService;
//	public void setExchangeService(IExchangeService exchangeService) {
//		this.exchangeService = exchangeService;
//	}
	
//	private IPinyipinService pinyipinService;
//	public void setPinyipinService(IPinyipinService pinyipinService) {
//		this.pinyipinService = pinyipinService;
//	}
	
	private ICommunityLocalService communityLocalService;
//	public final void setCommunityLocalService(ICommunityLocalService communityLocalService) { //syl-del 2015-1-19 19:41:56
	public void setCommunityLocalService(ICommunityLocalService communityLocalService) {//syl-add 2015-1-19 19:41:56
		this.communityLocalService = communityLocalService;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private IMicroblogService microblogService;
	public void setMicroblogService(IMicroblogService microblogService) {
		this.microblogService = microblogService;
	}
	
	private IXanaduService xanaduService;
	public void setXanaduService(IXanaduService xanaduService) {
		this.xanaduService = xanaduService;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private IAddressOperationService addressOperationService;
	public void setAddressOperationService(IAddressOperationService addressOperationService) {
		this.addressOperationService = addressOperationService;
	}

	@Override
	public List<AdvertiseModel> getAdvertiseList(BigInteger userId,String version) {
		List<AdvertiseModel> resModelList = new ArrayList<AdvertiseModel>();
//		PinyipinContentEntity pinyipinContentEntity = pinyipinService.getMostHotContent(userId);
//		ExchangeEntity exchangeEntity = exchangeService.getMostHotContent(userId);
		
//		//热门拼单数据 syl-del-2015-7-27 17:54:49去掉广告
//		if(pinyipinContentEntity!=null){
//			Map<String,Object> pinyipinMap = new HashMap<String, Object>();
//			pinyipinMap = pinyipinService.pinyipinContent2Map(null,userId, pinyipinContentEntity);
//			BigInteger id = pinyipinContentEntity.getId();
//			Integer dataType = AdvertiseConstant.Comminuty_DataType.Pinyipin;
//			String picUrl = pinyipinMap.get("ext_defaultIcon")==null?null:pinyipinMap.get("ext_defaultIcon").toString();
//			String jsonEntity = JSON.toJSONString(pinyipinMap);
//			AdvertiseModel tmpAdvertiseModel = new AdvertiseModel(id, dataType, picUrl, jsonEntity);
//			resModelList.add(tmpAdvertiseModel);
//		}
		
//		//热门换物数据 syl-del-2015-7-27 17:54:49去掉广告
//		if(exchangeEntity!=null){
//			Map<String,Object> exchangeMap = new HashMap<String, Object>();
//			exchangeMap = exchangeService.exhcangeContent2Map(null,userId, exchangeEntity);
//			BigInteger id = exchangeEntity.getId();
//			Integer dataType = AdvertiseConstant.Comminuty_DataType.Exchange;
//			String picUrl = exchangeMap.get("ext_defaultIcon")==null?null:exchangeMap.get("ext_defaultIcon").toString();
//			String jsonEntity = JSON.toJSONString(exchangeMap);
//			AdvertiseModel tmpAdvertiseModel = new AdvertiseModel(id, dataType, picUrl, jsonEntity);
//			resModelList.add(tmpAdvertiseModel);
//		}
		
//		{//8大业务板块数据 syl-del-2014-12-25 17:04:14
//			//AdvertiseConstant.Comminuty_DataType.CommunityForumTypesOfAds
//			List<CommunityForumType> communityForumTypeList = communityLocalService.getCommunityForumTypeList(CommunityLocalDict.CommunityForum_Type_ModelType.Is8Type,new PageModel(0, 10));
//			//String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath();
//			String accessBaseUrl = fileServerService.getAccessBaseUrl();
//			for(CommunityForumType tmpObj:communityForumTypeList){
//			//	Map<String,Object> tmpMap = communityLocalService.communityForumType2Map(userId,tmpObj,basePath);
//				Map<String,Object> tmpMap = communityLocalService.communityForumType2Map(userId,tmpObj,accessBaseUrl);
//				BigInteger id = tmpObj.getId();
//				Integer dataType = AdvertiseConstant.Comminuty_DataType.CommunityForumTypesOfAds;
//				String picUrl = tmpMap.get("topicUrl")==null?null:tmpMap.get("topicUrl").toString();
//				String jsonEntity = JSON.toJSONString(tmpMap);
//				AdvertiseModel tmpAdvertiseModel = new AdvertiseModel(id, dataType, picUrl, jsonEntity);
//				resModelList.add(tmpAdvertiseModel);
//			}
//		}
		
		{//获取世外桃源广告信息
			AdvertiseModel tmpAdvertiseModel = getXanaduAdvertise(userId,version);
			if(tmpAdvertiseModel!=null){
				resModelList.add(tmpAdvertiseModel);
			}
		}
		
		{//20计划
			AdvertiseModel tmpAdvertiseModel = getActivity5Advertise(userId);
			if(tmpAdvertiseModel!=null){
				resModelList.add(tmpAdvertiseModel);
			}
		}
		{//大V计划广告
			String advertisePicTmpStmp = sysParamManager.getSysParaValue(SysParamKey.AdvertisePicTmpStmp);
			String relativeUrl = "htmlPages/zjxs/zjxs.htm?"+"timStmp="+new Date().getTime()+"&";
			String activityName = "活动详情";
			String iconTail = "zjxs-banner.jpg"+"?timStmp="+advertisePicTmpStmp+"&";
			AdvertiseModel tmpAdvertiseModel = initHtmlAdvertise(relativeUrl, activityName, iconTail);
			resModelList.add(tmpAdvertiseModel);
		}
		return resModelList;
	}
	

	@Override
	public List<AdvertiseModel> getCommunityAdsDym(BigInteger userId,String version) {
		List<AdvertiseModel> resModelList = new ArrayList<AdvertiseModel>();
		{//获取世外桃源广告信息
			AdvertiseModel tmpAdvertiseModel = getXanaduAdvertise(userId,version);
			if(tmpAdvertiseModel!=null){
				resModelList.add(tmpAdvertiseModel);
			}
		}
		{//20计划
			AdvertiseModel tmpAdvertiseModel = getActivity5Advertise(userId);
			if(tmpAdvertiseModel!=null){
				resModelList.add(tmpAdvertiseModel);
			}
		}
		{//获取其它广告信息
			//获取用户所在小区Id
			BigInteger gbId = getGroupBuildingIdByUserId(userId);
			List<CommunityAds> communityAdsList = addressOperationService.getCommunityAdsListByGbId(gbId);
			if(communityAdsList!=null&&communityAdsList.size()>0){
				for(CommunityAds tmpAds:communityAdsList){
					AdvertiseModel toAdd = initHtmlAdvertise(tmpAds);
					resModelList.add(toAdd);
				}
			}
		}
		return resModelList;
	}
	
	private BigInteger getGroupBuildingIdByUserId(BigInteger userId){
		BigInteger groupBuildingId = null;
		if(xanaduService.getCurrStatus(userId)){
			groupBuildingId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
		}else{
			groupBuildingId = commonRoomService.getGroupBuildingIdByUserId(userId);
		}
		return groupBuildingId;
	}
	
	/**获取5户活动广告*/
	private AdvertiseModel getActivity5Advertise(BigInteger userId) {//2015-11-13 14:02:18 屏蔽五户活动
		//20计划
//		String advertisePicTmpStmp = sysParamManager.getSysParaValue(SysParamKey.AdvertisePicTmpStmp);
//		GroupBuilding tmpGroupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
//		Integer signStatus = tmpGroupBuilding==null?null:tmpGroupBuilding.getSignStatus();
//		if(userId!=null&&!(signStatus!=null&&signStatus.compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN)==0)){//用户Id不为空且小区不是签约的
//			String relativeUrl = "htmlPages/activity_plan20/activity_20.htm?"+"timStmp="+new Date().getTime()+"&";
//			String activityName = "活动详情";
//			String iconTail = "activity_10.jpg"+"?timStmp="+advertisePicTmpStmp+"&";//20计划
//			AdvertiseModel tmpAdvertiseModel = initHtmlAdvertise(relativeUrl, activityName, iconTail);
//			return tmpAdvertiseModel;
//		}
		return null;
	}
	
	/**获取世外桃源广告*/
	private AdvertiseModel getXanaduAdvertise(BigInteger userId,String version) {
		AdvertiseModel tmpAdvertiseModel = null;
		boolean useNewData = false;
		if(!StringUtils.isEmpty(version)){
			Long versionCurr = VersionTransferUtil.versionStr2Long(version);
			Long version150 = VersionTransferUtil.versionStr2Long("1.5.0");
			if(versionCurr.compareTo(version150)>0/*&&HeaderManager.getSubChannelId().equals(DictConstants.Channel_Sub.IOS)*/){//只有1.5.0以后的版本才处理
				useNewData = true;
			}
		}
		if(useNewData){
			//当前用户不在世外桃源且发微博的用户数量<5个
			Boolean isInXanadu = xanaduService.getCurrStatus(userId);
			if(!isInXanadu){//不在世外桃源
				Integer userSendCount = microblogService.getHaveSendBlogUserCount(userId);
				if(userSendCount<5){
					//世外桃源广告条 根据当前用户状态以及缓存状态动态判断是否返回
					BigInteger id = null;
					Integer dataType = AdvertiseConstant.Comminuty_DataType.CommunityXanadu;
					String picUrl = fileServerService.getAccessUrl(sysParamManager.getSysParaValue(SysParamKey.XANADU_ADS_PICPATH), "");//不为空则使用服务器的图片
					String jsonEntity = null;
					tmpAdvertiseModel = new AdvertiseModel(id, dataType, picUrl, jsonEntity);
				}
			}
		}else{
			Integer dataType = AdvertiseConstant.Comminuty_DataType.CommunityXanadu;
			String detailUrlAll = null;
			String activityName = "世外桃源";
			String iconTail = "xanaduAds.png";
			String updTime = null;
			BigInteger recordId = null;
			String iconUrlAll = StringUtils.isEmpty(iconTail)?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(getCommunityAdsPicBasePath()+iconTail,updTime);
			tmpAdvertiseModel = initHtmlAdvertise(dataType, detailUrlAll, activityName, iconUrlAll, recordId);
		}
		return tmpAdvertiseModel;
	}
	
	
	/**
	 * 创建包含detailUrl的广告模型
	 * @param id
	 * @param dataType
	 * @param picUrl
	 * @param detailUrl
	 * @return
	 */
	private AdvertiseModel initHtmlAdvertise(CommunityAds communityAds){
		String iconUrlAll = null;
		{
			String communityAdsPicBasePath = getCommunityAdsPicBasePath();
			iconUrlAll = StringUtils.isEmpty(communityAds.getIcon())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(communityAdsPicBasePath+communityAds.getIcon(),communityAds.getLastUpdTime());
		}
		String detailUrlAll = null;
		if(communityAds.getDetailUrl()!=null){
			if(communityAds.getDetailUrl().startsWith("http")){//是http使用本身数据
				detailUrlAll = communityAds.getDetailUrl();
			}else{//否则使用API内部数据
				detailUrlAll = getAccessBaseUrl()+communityAds.getDetailUrl();
			}
		}
		Integer dataType = AdvertiseConstant.Comminuty_DataType.CommunityForumTypesOfAds;
		return initHtmlAdvertise(dataType,detailUrlAll, communityAds.getName(),iconUrlAll, communityAds.getId());
	}
	
	private AdvertiseModel initHtmlAdvertise(String relativeUrl,String activityName,String iconTail){
		Integer dataType = AdvertiseConstant.Comminuty_DataType.CommunityForumTypesOfAds;
		String detailUrlAll = getAccessBaseUrl()+relativeUrl;
		String updTime = null;
		String iconUrlAll = StringUtils.isEmpty(iconTail)?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(getCommunityAdsPicBasePath()+iconTail,updTime);
		BigInteger recordId = null;
		return initHtmlAdvertise(dataType,detailUrlAll, activityName, iconUrlAll, recordId);
	}
	private AdvertiseModel initHtmlAdvertise(Integer dataType,String detailUrlAll,String activityName,String iconUrlAll,BigInteger recordId){
		AdvertiseModel tmpAdvertiseModel = null;
		Map<String,Object> tmpMap = communityLocalService.communityForumType2Map8Type(activityName, detailUrlAll);
		tmpMap.put("topicUrl", iconUrlAll);
		String jsonEntity = JSON.toJSONString(tmpMap);
		tmpAdvertiseModel = new AdvertiseModel(recordId, dataType, iconUrlAll, jsonEntity);
		return tmpAdvertiseModel;
	}
	
	private String getAccessBaseUrl(){
		String accessBaseUrl = sysParamManager.getSysParaValue(SysParamKey.Curr_Server_BaseUrl);
		return accessBaseUrl;
	}
	private String getCommunityAdsPicBasePath(){
		String communityAdsPicBasePath = sysParamManager.getSysParaValue(SysParamKey.CommunityForum_Type_Ico_BasePath);
		return communityAdsPicBasePath;
	}
	
}
