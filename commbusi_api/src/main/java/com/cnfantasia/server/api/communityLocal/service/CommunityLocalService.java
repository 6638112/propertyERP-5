/**   
* Filename:    CommunityLocalService.java   
* @version:    1.0  
* Create at:   2014年7月13日 上午10:47:37   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communityLocal.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.callable.UploadSmallPicRunnable;
import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ISmallPicUploadService;
import com.cnfantasia.server.api.commonBusiness.util.RelativeDateFormat;
import com.cnfantasia.server.api.communityLocal.constant.CommunityLocalDict;
import com.cnfantasia.server.api.communityLocal.dao.ICommunityLocalDao;
import com.cnfantasia.server.api.communityLocal.entity.CommunityForumContentEntity;
import com.cnfantasia.server.api.communityLocal.util.HeheNianGenerator;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.support.constant.SupportDict;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.communityForumContent.dao.ICommunityForumContentBaseDao;
import com.cnfantasia.server.domainbase.communityForumContent.entity.CommunityForumContent;
import com.cnfantasia.server.domainbase.communityForumPic.dao.ICommunityForumPicBaseDao;
import com.cnfantasia.server.domainbase.communityForumPic.entity.CommunityForumPic;
import com.cnfantasia.server.domainbase.communityForumType.dao.ICommunityForumTypeBaseDao;
import com.cnfantasia.server.domainbase.communityForumType.entity.CommunityForumType;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

/**
 * Filename:    CommunityLocalService.java
 * @version:    1.0.0
 * Create at:   2014年7月13日 上午10:47:37
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月13日       shiyl             1.0             1.0 Version
 */
public class CommunityLocalService implements ICommunityLocalService{
	private Log logger = LogFactory.getLog(getClass());
	
	private ICommunityForumTypeBaseDao communityForumTypeBaseDao;
	public void setCommunityForumTypeBaseDao(ICommunityForumTypeBaseDao communityForumTypeBaseDao) {
		this.communityForumTypeBaseDao = communityForumTypeBaseDao;
	}
	
	private ICommunityLocalDao communityLocalDao;
	public void setCommunityLocalDao(ICommunityLocalDao communityLocalDao) {
		this.communityLocalDao = communityLocalDao;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ICommunityForumContentBaseDao communityForumContentBaseDao;
	public void setCommunityForumContentBaseDao(ICommunityForumContentBaseDao communityForumContentBaseDao) {
		this.communityForumContentBaseDao = communityForumContentBaseDao;
	}
	
	private ICommunityForumPicBaseDao communityForumPicBaseDao;
	public void setCommunityForumPicBaseDao(ICommunityForumPicBaseDao communityForumPicBaseDao) {
		this.communityForumPicBaseDao = communityForumPicBaseDao;
	}
	
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	public void setGroupBuildingBaseDao(IGroupBuildingBaseDao groupBuildingBaseDao) {
		this.groupBuildingBaseDao = groupBuildingBaseDao;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private ISysParamParser communityForumPicParamParser;
	public void setCommunityForumPicParamParser(ISysParamParser communityForumPicParamParser) {
		this.communityForumPicParamParser = communityForumPicParamParser;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ISmallPicUploadService smallPicUploadService;
	public void setSmallPicUploadService(ISmallPicUploadService smallPicUploadService) {
		this.smallPicUploadService = smallPicUploadService;
	}
	
	
//	private ISysParamParser userImageParamParser;
//	public void setUserImageParamParser(ISysParamParser userImageParamParser) {
//		this.userImageParamParser = userImageParamParser;
//	}
	
	private ISysParamParser communityForumSmallPicParamParser;
	public void setCommunityForumSmallPicParamParser(ISysParamParser communityForumSmallPicParamParser) {
		this.communityForumSmallPicParamParser = communityForumSmallPicParamParser;
	}
	
	private ISysParamParser communityForumTypeIcoParamParser;
	public void setCommunityForumTypeIcoParamParser(ISysParamParser communityForumTypeIcoParamParser) {
		this.communityForumTypeIcoParamParser = communityForumTypeIcoParamParser;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}

	@Override
	public List<CommunityForumType> getCommunityForumTypeList(Integer type,PageModel pageModel) {
		return communityLocalDao.selectCommunityForumTypeList(type, pageModel);
//		CommunityForumType communityForumTypeQry = new CommunityForumType();
//		communityForumTypeQry.setModelType(type);
//		return communityForumTypeBaseDao.selectCommunityForumTypeByCondition(MapConverter.toMap(communityForumTypeQry), pageModel, false);
	}
	
	@Override
	public List<CommunityForumType> getCommunityForumTypeNot8List(PageModel pageModel) {
		return communityLocalDao.selectCommunityForumTypeNot8List(pageModel);
	}
	
	@Override
	public CommunityForumType getCommunityForumTypeById(BigInteger forumTypeId) {
		return communityForumTypeBaseDao.selectCommunityForumTypeBySeqId(forumTypeId);
	}
	
	@Override
	public List<CommunityForumContentEntity> getForumList(BigInteger forumType, PageModel pageModel, BigInteger userId) {
		BigInteger groupBuildId=getGroupBuildingIdByUserId(userId);
		return communityLocalDao.selectForumList(forumType, groupBuildId, pageModel,userId);
	}

	@Override
	public List<CommunityForumContentEntity> getForumList(PageModel pageModel, BigInteger userId) {
		BigInteger groupBuildId=getGroupBuildingIdByUserId(userId);
		return communityLocalDao.selectForumList(null, groupBuildId, pageModel,userId);
	}
	
	@Override
	public void postForum(BigInteger userId, String text, BigInteger typeId, List<RequestFileEntity> picList) {
		BigInteger forumContentAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_forum_content);
		BigInteger groupBuildId=getGroupBuildingIdByUserId(userId);
		String createTime = dualDao.getNowTime();
		{//新增帖子
			CommunityForumContent forumContent = new CommunityForumContent();
			forumContent.setCreateTime(createTime);
			forumContent.setId(forumContentAddId);
			forumContent.setText(text);
			forumContent.settGroupBuildingFId(groupBuildId);
			forumContent.settCommunityForumTypeFId(typeId);
			forumContent.setUserId(userId);
			int res = communityForumContentBaseDao.insertCommunityForumContent(forumContent);
			if(res<=0){
				throw new BusinessRuntimeException("ForumService.postForum.insertCommunityForumContent.failed");
			}
		}
		if(picList!=null&&picList.size()>0){//保存图片信息
			String forumPicBasePath = communityForumPicParamParser.parseParamValue();
			List<String> picUrlList = new ArrayList<String>();
			for(int i=0;i<picList.size();i++){//上传图片
				RequestFileEntity requestFile = picList.get(i);
				//生成文件名 userId+time+index+两位随机数
				String resFileName = new StringBuffer().append(userId).append(DateUtil.getCurrSysTimeStr())
						.append("_").append(i+1).append("_").append(RandomUtils.createRandom(true, 3))
						.append(".").append(requestFile.getFileType()).toString();
				picUrlList.add(resFileName);
//				String serverPath = fileServerService.getAccessUrl(microBlogBasePath)+resFileName;
				String serverPath = forumPicBasePath+resFileName;
				try {
					fileServerService.uploadFile(serverPath, requestFile.getFileContent());
				} catch (IOException e) {
					logger.info("upload forum file cause exception:"+e.getMessage(), e);
					throw new BusinessRuntimeException("ForumService.postForum.uploadFile.error",new Object[]{requestFile.getFileName()});
				}
				//增加生成小图的任务处理 syl-2014-9-4 11:40:43
				FutureTask<Boolean> task = new FutureTask<Boolean>(new UploadSmallPicRunnable(smallPicUploadService,BusinessModelType.JieFang,fileServerService.getFileServierUploadBasePath(),forumPicBasePath, resFileName, requestFile.getFileContent()));
				new Thread(task).start();
			}
			List<CommunityForumPic> communityForumPicAddList = new ArrayList<CommunityForumPic>();
			List<BigInteger> picAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_forum_pic,picUrlList.size());
//			for(String picUrl:picUrlList){
			for(int i=0;i<picUrlList.size();i++){
				String picUrl = picUrlList.get(i);
//				BigInteger picAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_forum_pic);
				BigInteger picAddId = picAddIdList.get(i);
				CommunityForumPic communityForumPic = new CommunityForumPic();
				communityForumPic.setDesc(null);
				communityForumPic.setId(picAddId);
				communityForumPic.setName(null);
				communityForumPic.settCommunityForumContentFId(forumContentAddId);
				communityForumPic.setUploader(userId);
				communityForumPic.setUrl(picUrl);
				communityForumPicAddList.add(communityForumPic);
			}
			int res = communityForumPicBaseDao.insertCommunityForumPicBatch(communityForumPicAddList);
			if(res!=communityForumPicAddList.size()){
				throw new BusinessRuntimeException("ForumService.postForum.insertForumPicBatch.failed");
			}
		}
	}

	@Override
	public CommunityForumContentEntity getForumDetail(BigInteger forumId, BigInteger userId) {
		return communityLocalDao.selectForumDetail(forumId,userId);
	}
	
	@Override
	public GroupBuilding getGroupBuildingByUserId(BigInteger userId){
		BigInteger gbId = null;
		if(userId==null){
			gbId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
		}else{//TODO 待改造
			gbId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
		}
		GroupBuilding res=groupBuildingBaseDao.selectGroupBuildingBySeqId(gbId);
		return res;
	}
	private BigInteger getGroupBuildingIdByUserId(BigInteger userId){
		return getGroupBuildingByUserId(userId).getId();
	}

	@Override
	public Map<CommunityForumType, CommunityForumContent> getHomeInfo(BigInteger broupBuildId,BigInteger userId,Integer is8Type) {
		Map<CommunityForumType, CommunityForumContent> resMap = new HashMap<CommunityForumType, CommunityForumContent>();
		BigInteger groupBuildId = getGroupBuildingIdByUserId(userId);
		//查询前两个类别
		PageModel pageModelType = new PageModel(0, 2);
		List<CommunityForumType> communityForumTypeList = getCommunityForumTypeList(is8Type,pageModelType);
		//查询各个类别下的最新一个帖子
		for(CommunityForumType tmpBlogType:communityForumTypeList){
			CommunityForumContent forumContent = communityLocalDao.selectMostHotForum(tmpBlogType.getId(), groupBuildId);
			if(forumContent!=null){
				resMap.put(tmpBlogType, forumContent);
			}
		}
		return resMap;
	}
	@Override
	public Map<String,Object> communityForumType2Map8Type(/*String icoBasePath,*//*BigInteger topicId,*/String topicName,/*String iconTail,String updTime,*/String detailUrlAll){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("topicId", topicId);
		tmpMap.put("topicName", topicName);
//		tmpMap.put("topicUrl", StringUtils.isEmpty(iconTail)?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(icoBasePath+iconTail,updTime));
		tmpMap.put("is8Type", true);
		tmpMap.put("urlFor8", detailUrlAll);
		return tmpMap;
	}
	
	@Override
	public Map<String,Object> communityForumType2Map(BigInteger userId,CommunityForumType communityForumType,String basePath){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		String forumTypeIcoBasePath = communityForumTypeIcoParamParser.parseParamValue();
		tmpMap.put("topicId", communityForumType.getId());
		tmpMap.put("topicName", communityForumType.getName());
		tmpMap.put("topicUrl", StringUtils.isEmpty(communityForumType.getIcon())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(forumTypeIcoBasePath+communityForumType.getIcon(),communityForumType));
		
		if(communityForumType.getModelType()!=null&&CommunityLocalDict.CommunityForum_Type_ModelType.Is8Type.compareTo(communityForumType.getModelType())==0){
			tmpMap.put("is8Type", true);
			if(!StringUtils.isEmpty(communityForumType.getUrlFor8())){
				tmpMap.put("urlFor8", basePath+communityForumType.getUrlFor8());
			}else{
				if(!StringUtils.isEmpty(communityForumType.getUrlFor8Goal())){
					String resUrl = communityForumType.getUrlFor8Goal();
					if(!StringUtils.isEmpty(userId)){
						resUrl = HeheNianGenerator.getGoalUrl(communityForumType.getUrlFor8Goal(), HeheNianGenerator.secret, HeheNianGenerator.via, userId.toString(), null, null, null, null, null);
					}
					tmpMap.put("urlFor8", resUrl);
				}else{
					tmpMap.put("urlFor8", null);
				}
			}
		}else{
			tmpMap.put("is8Type", false);
			tmpMap.put("urlFor8", null);
		}
		return tmpMap;
	}
	
	@Override
	public Map<String,Object> communityForumContent2Map(CommunityForumContent tmpObj,List<CommunityForumPic> communityForumPicList,UserSimpleEntity userInfo,Long nowTimeLong,Integer isFavour,Integer favourCount,Integer commentCount){
		String communityForumPicBasePath = communityForumPicParamParser.parseParamValue();
		String smallPicBasePath = communityForumSmallPicParamParser.parseParamValue();
//		UserImageParamEntity userImageParamEntity=userImageParamParser.parseParamValue();
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("id", tmpObj.getId());
		tmpMap.put("createTime",  RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(tmpObj.getCreateTime()),nowTimeLong));
		
		List<String> picList = new ArrayList<String>();
		if(communityForumPicList!=null){
			for(CommunityForumPic tmpPic:communityForumPicList){
					picList.add(StringUtils.isEmpty(tmpPic.getUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(communityForumPicBasePath+tmpPic.getUrl(),tmpPic));
			}
			tmpMap.put("picList", picList);
		}
		if(userInfo!=null){
			Map<String,Object> tmpUserMap = commEntityConvertService.baseUser2MapForCommunityForumContent(userInfo);
			tmpMap.putAll(tmpUserMap);
//			tmpMap.put("userName", userInfo.getNickName());
//			tmpMap.put("userId", userInfo.getId());
//			if(userInfo.getImgprofile()!=null){
//				tmpMap.put("userPicUrl", fileServerService.getAccessUrl(userImageParamEntity.getBasePath()+userInfo.getImgprofile(),userInfo));
//			}else{
//				tmpMap.put("userPicUrl",null);
//			}
		}
		if(favourCount==null){favourCount=0;}
		tmpMap.put("favourCount", favourCount);
		if(commentCount==null){commentCount=0;}
		tmpMap.put("commentCount", commentCount);
		if(isFavour!=null){
			if(isFavour!=null&&SupportDict.Support_IsFavour.YES.compareTo(isFavour)==0){
				tmpMap.put("isFavour", true);
			}else{
				tmpMap.put("isFavour", false);
			}
		}
		{//小图
			tmpMap.put("smallIcoUrl",StringUtils.isEmpty(tmpObj.getSmallIconUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(smallPicBasePath+tmpObj.getSmallIconUrl(),tmpObj));
		}
		
//		tmpMap.put("txt", tmpObj.getText());
		{//简介
			tmpMap.put("smallTxt", tmpObj.getTitle());//syl--upd 2014-8-2 15:34:14 只使用标题
//			if(!StringUtils.isEmpty(tmpObj.getSmallTxt())){
//				tmpMap.put("smallTxt", tmpObj.getSmallTxt());
//			}else{//没有简介就取标题
//				tmpMap.put("smallTxt", tmpObj.getTitle());
//			}
		}
		{//详情地址
			tmpMap.put("detailUrl", tmpObj.getDetailUrl());
		}
		return tmpMap;
	}
	
}
