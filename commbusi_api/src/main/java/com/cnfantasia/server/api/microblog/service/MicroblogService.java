/**   
* Filename:    MicroblogService.java   
* @version:    1.0  
* Create at:   2014年7月22日 上午10:09:13   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.microblog.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.achievement.constant.AchievementConstant.AchievementEnum;
import com.cnfantasia.server.api.achievement.service.IAchievementService;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.callable.UploadSmallPicRunnable;
import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ISmallPicUploadService;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.microblog.constant.MicroblogDict;
import com.cnfantasia.server.api.microblog.dao.IMicroblogDao;
import com.cnfantasia.server.api.microblog.entity.MicroblogContentEntity;
import com.cnfantasia.server.api.microblog.entity.XibaoEntity;
import com.cnfantasia.server.api.microblog.entity.XibaoGroupBuilding;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.room.service.IRoomService;
import com.cnfantasia.server.api.xanadu.service.IXanaduService;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.lanternFestivalPrize.entity.LanternFestivalPrize;
import com.cnfantasia.server.domainbase.microblogContent.dao.IMicroblogContentBaseDao;
import com.cnfantasia.server.domainbase.microblogContent.entity.MicroblogContent;
import com.cnfantasia.server.domainbase.microblogPic.dao.IMicroblogPicBaseDao;
import com.cnfantasia.server.domainbase.microblogPic.entity.MicroblogPic;
import com.cnfantasia.server.domainbase.microblogReport.dao.IMicroblogReportBaseDao;
import com.cnfantasia.server.domainbase.microblogReport.entity.MicroblogReport;
import com.cnfantasia.server.domainbase.microblogType.dao.IMicroblogTypeBaseDao;
import com.cnfantasia.server.domainbase.microblogType.entity.MicroblogType;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;

/**
 * Filename:    MicroblogService.java
 * @version:    1.0.0
 * Create at:   2014年7月22日 上午10:09:13
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月22日       shiyl             1.0             1.0 Version
 */
public class MicroblogService implements IMicroblogService{
	private Log logger = LogFactory.getLog(getClass());
	
	private IMicroblogTypeBaseDao microblogTypeBaseDao;
	public void setMicroblogTypeBaseDao(IMicroblogTypeBaseDao microblogTypeBaseDao) {
		this.microblogTypeBaseDao = microblogTypeBaseDao;
	}
	private IMicroblogDao microblogDao;
	public void setMicroblogDao(IMicroblogDao microblogDao) {
		this.microblogDao = microblogDao;
	}
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private IMicroblogContentBaseDao microblogContentBaseDao;
	public void setMicroblogContentBaseDao(IMicroblogContentBaseDao microblogContentBaseDao) {
		this.microblogContentBaseDao = microblogContentBaseDao;
	}
	
	private IMicroblogPicBaseDao microblogPicBaseDao;
	public void setMicroblogPicBaseDao(IMicroblogPicBaseDao microblogPicBaseDao) {
		this.microblogPicBaseDao = microblogPicBaseDao;
	}
	
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	public void setGroupBuildingBaseDao(IGroupBuildingBaseDao groupBuildingBaseDao) {
		this.groupBuildingBaseDao = groupBuildingBaseDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ISmallPicUploadService smallPicUploadService;
	public void setSmallPicUploadService(ISmallPicUploadService smallPicUploadService) {
		this.smallPicUploadService = smallPicUploadService;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private ISysParamParser microblogPicParamParser;
	public void setMicroblogPicParamParser(ISysParamParser microblogPicParamParser) {
		this.microblogPicParamParser = microblogPicParamParser;
	}
	
//	private ICommCommunityService commCommunityService;
//	public void setCommCommunityService(ICommCommunityService commCommunityService) {
//		this.commCommunityService = commCommunityService;
//	}
	
	private IXanaduService xanaduService;
	public void setXanaduService(IXanaduService xanaduService) {
		this.xanaduService = xanaduService;
	}
	
	private IAchievementService achievementService;
	public void setAchievementService(IAchievementService achievementService) {
		this.achievementService = achievementService;
	}
	
	private IRoomService roomService;
	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}
	
	@Resource
	private IMicroblogReportBaseDao microblogReportBaseDao;
	
	@Override
	public List<MicroblogType> getMicroblogTypeList(PageModel pageModel) {
		return microblogTypeBaseDao.selectMicroblogTypeByCondition(new HashMap<String, Object>(), pageModel, false);
	}

	@Override
	public List<MicroblogContentEntity> getMicroblogList(BigInteger microBlogType, PageModel pageModel, BigInteger userId) {
		BigInteger groupBuildId=getGroupBuildingIdByUserId(userId);
		List<MicroblogContentEntity> resList = microblogDao.selectMicroblogList(microBlogType, groupBuildId, pageModel,userId);
//		if(resList==null||resList.size()<=0){
//			List<MicroblogContentEntity> tmpList  = microblogDao.selectMicroblogList(microBlogType, RoomConstants.DEFAULT_GROUP_BUILDING_ID, pageModel,userId);
//			if(tmpList!=null&&tmpList.size()>0){
//				resList.add(tmpList.get(0));
//				pageModel.count = 1;
//				pageModel.isLast = true;
//			}
//		}
		return resList;
	}

	@Override
	public List<MicroblogContentEntity> getMicroblogList(PageModel pageModel, BigInteger userId) {
		BigInteger groupBuildId=getGroupBuildingIdByUserId(userId);
		List<MicroblogContentEntity> resList =  microblogDao.selectMicroblogList(null, groupBuildId, pageModel,userId);
//		if(resList==null||resList.size()<=0){
//			List<MicroblogContentEntity> tmpList  = microblogDao.selectMicroblogList(null, RoomConstants.DEFAULT_GROUP_BUILDING_ID, pageModel,userId);
//			if(tmpList!=null&&tmpList.size()>0){
//				resList.add(tmpList.get(0));
//				pageModel.count = 1;
//				pageModel.isLast = true;
//			}
//		}
		return resList;
	}
	
	@Override
	public void postMicroblog(BigInteger userId, String text, String activityName, BigInteger typeId, List<RequestFileEntity> picList) {
//		commCommunityService.checkXanaduOperation(userId);//syl-del-2015-2-6 14:42:29 去掉室外桃源的限制
		BigInteger groupBuildId=getGroupBuildingIdByUserId(userId);
		/**
		 * 验证当前用户是否当天已经发送过消息（每个用户每天只能发送一条）
		 * huangzj 2015-06-23
		 * */
		if(microblogDao.selectUserSendBlogForToday(userId, RoomConstants.DEFAULT_GROUP_BUILDING_ID)>0
				&& RoomConstants.DEFAULT_GROUP_BUILDING_ID.equals(groupBuildId)){
			throw new BusinessRuntimeException("microblog.postMicroblog.sendBlogTodayOnce");
		}
		if(groupBuildId!=null&&RoomConstants.DEFAULT_GROUP_BUILDING_ID.compareTo(groupBuildId)==0){//世外桃源用户门牌必须验证
			RoomValidate inviteUserRoomValidate = roomService.getRoomValidateWithDefault(userId);
			if(inviteUserRoomValidate!=null&&inviteUserRoomValidate.getVerifyStatus()!=null
					&&inviteUserRoomValidate.getVerifyStatus().compareTo(RoomDict.RoomValidte_VerifyStatus.SUCCESS)==0){
			}else{
				throw new BusinessRuntimeException("MicroblogService.postMicroblog.room.notValidate");
			}
		}
		BigInteger microblogContentAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_microblog_content);
		String createTime = dualDao.getNowTime();
		{//新增微博
			MicroblogContent microblogContent = new MicroblogContent();
			microblogContent.setCreateTime(createTime);
			microblogContent.setId(microblogContentAddId);
			microblogContent.setText(text);
			microblogContent.setActivityName(activityName);
			microblogContent.settGroupBuildingFId(groupBuildId);
			microblogContent.settMicroblogTypeFId(typeId);
			microblogContent.setUserId(userId);
			microblogContent.setSourceType(MicroblogDict.MicroblogContent_SourceType.CommMsg);//默认普通用户信息
			int res = microblogContentBaseDao.insertMicroblogContent(microblogContent);
			if(res<=0){
				throw new BusinessRuntimeException("MicroblogService.postMicroblog.insertMicroblogContent.failed");
			}
		}
		if(picList!=null&&picList.size()>0){//保存图片信息
			String microBlogBasePath = microblogPicParamParser.parseParamValue();
			List<String> picUrlList = new ArrayList<String>();
			for(int i=0;i<picList.size();i++){//上传图片
				RequestFileEntity requestFile = picList.get(i);
				//生成文件名 userId+time+index+两位随机数
				String resFileName = new StringBuffer().append(userId).append(DateUtil.getCurrSysTimeStr())
						.append("_").append(i+1).append("_").append(RandomUtils.createRandom(true, 3))
						.append(".").append(requestFile.getFileType()).toString();
				picUrlList.add(resFileName);
//				String serverPath = fileServerService.getAccessUrl(microBlogBasePath)+resFileName;
				String serverPath = microBlogBasePath+resFileName;
				try {
					fileServerService.uploadFile(serverPath, requestFile.getFileContent());
				} catch (IOException e) {
					logger.info("upload micorblog file cause exception:"+e.getMessage(), e);
					throw new BusinessRuntimeException("MicroblogService.postMicroblog.uploadFile.error",new Object[]{requestFile.getFileName()});
				}
				//增加生成小图的任务处理 syl-2014-9-4 11:40:43
				FutureTask<Boolean> task = new FutureTask<Boolean>(new UploadSmallPicRunnable(smallPicUploadService,BusinessModelType.JieFang,fileServerService.getFileServierUploadBasePath(),microBlogBasePath, resFileName, requestFile.getFileContent()));
				new Thread(task).start();
				
			}
			List<MicroblogPic> microblogPicAddList = new ArrayList<MicroblogPic>();
			List<BigInteger> picAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_microblog_pic,picUrlList.size());
//			for(String picUrl:picUrlList){
			for(int i=0;i<picUrlList.size();i++){
				String picUrl = picUrlList.get(i);
//				BigInteger picAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_microblog_pic);
				BigInteger picAddId = picAddIdList.get(i);
				MicroblogPic microblogPic = new MicroblogPic();
				microblogPic.setDesc(null);
				microblogPic.setId(picAddId);
				microblogPic.setName(null);
				microblogPic.settMicroblogContentFId(microblogContentAddId);
				microblogPic.setUploader(userId);
				microblogPic.setUrl(picUrl);
				microblogPicAddList.add(microblogPic);
			}
			int res = microblogPicBaseDao.insertMicroblogPicBatch(microblogPicAddList);
			if(res!=microblogPicAddList.size()){
				throw new BusinessRuntimeException("MicroblogService.postMicroblog.insertMicroblogPicBatch.failed");
			}
		}
		//成就判断是否为第一勇士
		Integer userSendCount = getHaveSendBlogUserCount(userId);
		if(userSendCount<=1){
			achievementService.addAchievement(userId, AchievementEnum.diyiyongshi);
		}
	}

	@Override
	public MicroblogContentEntity getMicroblogDetail(BigInteger microBolgId, BigInteger userId) {
		return microblogDao.selectMicroblogDetail(microBolgId,userId);
	}
	
	@Override
	public GroupBuilding getGroupBuildingByUserId(BigInteger userId){
		BigInteger gbId = null;
		//TODO 判断是否为世外桃源 
		boolean isInXanadu = xanaduService.getCurrStatus(userId);
		if(isInXanadu){
			gbId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
		}else{
			if(userId==null){
				gbId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
			}else{
//				gbId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
				gbId = commonRoomService.getGroupBuildingIdByUserId(userId);
			}
		}
		GroupBuilding res=groupBuildingBaseDao.selectGroupBuildingBySeqId(gbId);
		return res;
	}
	
	@Override
	public BigInteger getGroupBuildingIdByUserId(BigInteger userId){
		return getGroupBuildingByUserId(userId).getId();
	}

	@Override
	public Map<MicroblogType, MicroblogContent> getHomeInfo(BigInteger broupBuildId,BigInteger userId) {
		Map<MicroblogType, MicroblogContent> resMap = new HashMap<MicroblogType, MicroblogContent>();
		BigInteger groupBuildId = getGroupBuildingIdByUserId(userId);
		//查询前两个类别
		PageModel pageModelType = new PageModel(0, 2);
		List<MicroblogType> microblogTypeList = getMicroblogTypeList(pageModelType);
		//查询各个类别下的最新一个微博
		for(MicroblogType tmpBlogType:microblogTypeList){
			MicroblogContent micContent = microblogDao.selectMostHotMicBlog(tmpBlogType.getId(), groupBuildId);
			if(micContent!=null){
				resMap.put(tmpBlogType, micContent);
			}
		}
		return resMap;
	}

	@Override
	public String fetchAllMicroblogLastUpdTime(BigInteger microblobTypeId,PageModel pageModel,BigInteger userId) {
		String res = null;
		if(pageModel.begin==0){
			BigInteger groupBuildId = getGroupBuildingIdByUserId(userId);
			res = microblogDao.selectAllMicroblogLastUpdTime(microblobTypeId, groupBuildId, pageModel, userId);
		}
		return res;
	}

	@Override
	public Integer getHaveSendBlogUserCount(BigInteger userId) {
		BigInteger groupBuildId = getGroupBuildingIdByUserId(userId);
		return microblogDao.selectHaveSendBlogUserCount(groupBuildId);
	}

	@Override
	public String getMicroblogLinkDetail(BigInteger microBolgId) {
		return microblogDao.selectMicroblogLinkDetail(microBolgId);
	}

	@Override
	public void saveReport(MicroblogReport microblogReport) {
		microblogReportBaseDao.insertMicroblogReport(microblogReport);
	}
	
	public List<XibaoEntity> selectXibaoList(Map<String,Object> paramMap) {
		if(paramMap.get("pbDateStr") == null) {
			return null;
		} else {
			String billStartDateStr = DateUtils.convertDateToStr(new Date(), "yyyy-MM");
			paramMap.put("pbDateEndStr", billStartDateStr+"-01");
			int type = 2;//默认查询前两个月的数据
			paramMap.put("type", type);
			int totalCount = getSelectXibaoListCount(paramMap);
			if(totalCount > 10) {
				return microblogDao.selectXibaoList(paramMap);
			} else {
				return selectXibaoListRecursion(paramMap, type);
			}
		}
	}
	
	public List<XibaoEntity> selectXibaoListRecursion(Map<String,Object> paramMap, int type) {
		List<XibaoEntity> list = microblogDao.selectXibaoList(paramMap);
		if((list!=null && list.size() >= 10) || type >= 12) {
			return list;
		} else {
			String billStartDateStr = DateUtils.convertDateToStr(new Date(), "yyyy-MM");
			paramMap.put("pbDateEndStr", billStartDateStr+"-01");
			type = type + 2;
			paramMap.put("type", type);//默认查询前两个月的数据
			
			return selectXibaoListRecursion(paramMap, type);
		}
	}
	
	@Override
	public int getSelectXibaoListCount(Map<String,Object> paramMap) {
		if(paramMap.get("pbDateStr") == null) {
			return 0;
		} else {
			int count = microblogDao.getSelectXibaoListCount(paramMap);
			if(count > 10) {
				return count;
			} else {
				int type = (Integer) paramMap.get("type");
				return getSelectXibaoListCountForEach(paramMap, type);
			}
		}
	}
	
	private int getSelectXibaoListCountForEach(Map<String,Object> paramMap, int type) {
		if(type >= 12 || microblogDao.getSelectXibaoListCount(paramMap) > 10) {
			return microblogDao.getSelectXibaoListCount(paramMap);
		} else {
			type = type + 2;
			return getSelectXibaoListCountForEach(paramMap, type);
		}
	}
	
	public XibaoGroupBuilding selectXibaoGroupBuilding(Map<String,Object> paramMap) {
		return microblogDao.selectXibaoGroupBuilding(paramMap);
	}

	@Override
	public List<LanternFestivalPrize> selectLanternFestivalPrizeList(Map<String, Object> paramMap) {
		return microblogDao.selectLanternFestivalPrizeList(paramMap);
	}

	@Override
	public int getSelectLanternFestivalPrizeCount(Map<String, Object> paramMap) {
		return microblogDao.getSelectLanternFestivalPrizeCount(paramMap);
	}

}
