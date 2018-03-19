/**   
 * Filename:    AutoFetchSupllyDataService.java   
 * @version:    1.0  
 * Create at:   2014年11月28日 上午9:14:10   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年11月28日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.communitySupply.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.callable.UploadSmallPicRunnable;
import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ISmallPicUploadService;
import com.cnfantasia.server.api.communitySupply.constant.CommunitySupplyDict;
import com.cnfantasia.server.api.communitySupply.dao.ICommunitySupplyDao;
import com.cnfantasia.server.api.communitySupply.entity02.CoordinateJsonResponse.CoordinateResult;
import com.cnfantasia.server.api.communitySupply.entity02.GroupBuildingAndCommunitySupply;
import com.cnfantasia.server.api.communitySupply.entity02.MerchantResult;
import com.cnfantasia.server.api.communitySupply.entity02.SuppluContectInfoEntity;
import com.cnfantasia.server.api.communitySupply.entity2mljia.MljiaShopEntity;
import com.cnfantasia.server.api.communitySupply.util.AutoFetchServiceUtil;
import com.cnfantasia.server.api.communitySupply.util.BaseFetchUtil;
import com.cnfantasia.server.api.communitySupply.util.CommunitySupplyDataFetchUtil;
import com.cnfantasia.server.api.communitySupply.util.MljiaFetchUtil;
import com.cnfantasia.server.api.communitySupply.util.TelPhoneParser;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.room.constant.GroupBuildingDict;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.utils.UrlFileFetchUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.communitySupply.dao.ICommunitySupplyBaseDao;
import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyContect.dao.ICommunitySupplyContectBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.communitySupplyType.dao.ICommunitySupplyTypeBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.dao.IGroupBuildingHasTCommunitySupplyBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.entity.GroupBuildingHasTCommunitySupply;

/**
 * Filename: AutoFetchSupllyDataService.java
 * 
 * @version: 1.0.0 Create at: 2014年11月28日 上午9:14:10 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月28日 shiyl 1.0 1.0 Version
 */
public class AutoFetchSupllyDataService implements IAutoFetchSupllyDataService {
	private Log logger = LogFactory.getLog(getClass());
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

	private ICommunitySupplyTypeBaseDao communitySupplyTypeBaseDao;
	public void setCommunitySupplyTypeBaseDao(ICommunitySupplyTypeBaseDao communitySupplyTypeBaseDao) {
		this.communitySupplyTypeBaseDao = communitySupplyTypeBaseDao;
	}

	private ICommunitySupplyBaseDao communitySupplyBaseDao;
	public void setCommunitySupplyBaseDao(ICommunitySupplyBaseDao communitySupplyBaseDao) {
		this.communitySupplyBaseDao = communitySupplyBaseDao;
	}

	private ICommunitySupplyContectBaseDao communitySupplyContectBaseDao;
	public void setCommunitySupplyContectBaseDao(ICommunitySupplyContectBaseDao communitySupplyContectBaseDao) {
		this.communitySupplyContectBaseDao = communitySupplyContectBaseDao;
	}

	private IGroupBuildingHasTCommunitySupplyBaseDao groupBuildingHasTCommunitySupplyBaseDao;
	public void setGroupBuildingHasTCommunitySupplyBaseDao(
			IGroupBuildingHasTCommunitySupplyBaseDao groupBuildingHasTCommunitySupplyBaseDao) {
		this.groupBuildingHasTCommunitySupplyBaseDao = groupBuildingHasTCommunitySupplyBaseDao;
	}

	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	private IGroupBuildingBaseDao groupBuildingBaseDao;
	public void setGroupBuildingBaseDao(IGroupBuildingBaseDao groupBuildingBaseDao) {
		this.groupBuildingBaseDao = groupBuildingBaseDao;
	}

	private ICommunitySupplyDao communitySupplyDao;
	public void setCommunitySupplyDao(ICommunitySupplyDao communitySupplyDao) {
		this.communitySupplyDao = communitySupplyDao;
	}

	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private ISmallPicUploadService smallPicUploadService;
	public void setSmallPicUploadService(ISmallPicUploadService smallPicUploadService) {
		this.smallPicUploadService = smallPicUploadService;
	}
	
	@Override
	public void fetchSupply2DB(BigInteger groupBuildingId) {
		String baiduLbsAk = sysParamManager.getSysParaValue(SysParamKey.BAIDU_LBS_AK);
		try {
			fetchSupply2DBForBaidu(groupBuildingId,baiduLbsAk);
		} catch (Exception e) {
			logger.info("AutoFetchSupllyDataService.fetchSupply2DB.fetchSupply2DBForBaidu cause exception",e);
		}
		try {//越是精准的数据越往后处理
			fetchSupply2DBForMljia(groupBuildingId,baiduLbsAk,false);
		} catch (Exception e) {
			logger.info("AutoFetchSupllyDataService.fetchSupply2DB.fetchSupply2DBForMljia cause exception",e);
		}
	}
	@Override
	public synchronized void fetchSupply2DBForMljia(BigInteger groupBuildingId,String baiduLbsAk,boolean needReload) {
		logger.info("fetchSupply2DBForMljia start to fetch data,groupBuildingId is :"+groupBuildingId+",baiduLbsAk is "+baiduLbsAk);
		if (groupBuildingId == null) {
			return;
		}
		if(StringUtils.isEmpty(baiduLbsAk)){
			baiduLbsAk = sysParamManager.getSysParaValue(SysParamKey.BAIDU_LBS_AK);
		}
//		synchronized (groupBuildingId) {
			// 查询小区明细
			GroupBuildingEntity currGroupBuilding = commonRoomService.getGroupBuildingById(groupBuildingId);
			logger.info("currGroupBuilding data is :"+JSON.toJSONString(currGroupBuilding));
			if (
					(currGroupBuilding.getMljFetchStatus() != null&&currGroupBuilding.getMljFetchStatus() == GroupBuildingDict.GroupBuilding_fetchStatus.HasFetch
					&&!needReload)//美丽加已经抓取过数据 且不需要重新刷新
					|| currGroupBuilding.getCheckStatus().compareTo(RoomDict.CheckStatus.DaiShenHe) == 0
					|| currGroupBuilding.getCheckStatus().compareTo(RoomDict.CheckStatus.ShenHeBuTongGuo) == 0) {
				logger.info("fetchSupply2DBForMljia ignore to fetch data,groupBuildingId is :"+groupBuildingId);
				return;
			}
			
			List<CommunitySupplyContect> communitySupplyContectList_db = communitySupplyDao
					.selectCommunitySupplyContect(groupBuildingId);
			List<CommunitySupply> communitySupplyList_db = communitySupplyDao.selectCommunitySupply(groupBuildingId);
			GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupplyQry = new GroupBuildingHasTCommunitySupply();
			groupBuildingHasTCommunitySupplyQry.settGroupBuildingFId(groupBuildingId);
			List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList_db = groupBuildingHasTCommunitySupplyBaseDao
					.selectGroupBuildingHasTCommunitySupplyByCondition(MapConverter.toMap(groupBuildingHasTCommunitySupplyQry),
							true);
			//抓取美容的数据
//			CommunitySupplyConstant.SupplyTypeId.Meirong_SuppplyTypeId
			//待新增的数据
			List<CommunitySupply> communitySupplyList = new ArrayList<CommunitySupply>();
			List<CommunitySupplyContect> communitySupplyContectList = new ArrayList<CommunitySupplyContect>();
			List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList = new ArrayList<GroupBuildingHasTCommunitySupply>();
			
			//待更新的数据
			List<CommunitySupply> communitySupplyListUpd = new ArrayList<CommunitySupply>();
			List<CommunitySupplyContect> communitySupplyContectListUpd = new ArrayList<CommunitySupplyContect>();
			List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyListUpd = new ArrayList<GroupBuildingHasTCommunitySupply>();
			
			GroupBuilding toUpdGroupBuilding = new GroupBuilding();
			toUpdGroupBuilding.setId(groupBuildingId);
			{//抓取美容的数据
				//1.获取坐标数据
				String baiduLocatLat = currGroupBuilding.getBaiduLocatLat();
				String baiduLocatLng = currGroupBuilding.getBaiduLocatLng();
				if(baiduLocatLat==null||baiduLocatLng==null){
					logger.info("exist groupbuilding lat or lng is null,groupBuildingId is "+groupBuildingId+",baiduLocatLat is "+baiduLocatLat+",baiduLocatLng is "+baiduLocatLng);
					String cityName = currGroupBuilding.getAddressBlockEntity().getAddressCityEntity().getName();
					String groupBuildIngName = currGroupBuilding.getName();
					CoordinateResult tmpCoordinateResult = BaseFetchUtil.getLocationByName(baiduLbsAk, cityName, currGroupBuilding
							.getAddressBlockEntity().getName() + groupBuildIngName);
					if (tmpCoordinateResult == null) {
						logger.info(groupBuildingId + " has no Location.");
						return;
					}
					toUpdGroupBuilding.setBaiduLevel(tmpCoordinateResult.getLevel());
					toUpdGroupBuilding.setBaiduLocatLat(tmpCoordinateResult.getLocation().getLat());
					toUpdGroupBuilding.setBaiduLocatLng(tmpCoordinateResult.getLocation().getLng());
					toUpdGroupBuilding.setBaiduPrecise(tmpCoordinateResult.getPrecise() == null ? null : tmpCoordinateResult
							.getPrecise().longValue());
					baiduLocatLat = tmpCoordinateResult.getLocation().getLat();
					baiduLocatLng = tmpCoordinateResult.getLocation().getLng();
					if(baiduLocatLat==null||baiduLocatLng==null){
						logger.info("fetch lat or lng result is null,groupBuildingId is "+groupBuildingId+",baiduLocatLat is "+baiduLocatLat+",baiduLocatLng is "+baiduLocatLng);
						return;
					}
				}
				//2.根据坐标获取美丽加美容数据
				List<MljiaShopEntity> mljiaShopEntityList = MljiaFetchUtil.fetchShopInfo(baiduLocatLng, baiduLocatLat);
				List<SuppluContectInfoEntity> suppluContectInfoEntityList = new ArrayList<SuppluContectInfoEntity>();
				List<GroupBuildingAndCommunitySupply> groupBuildingAndCommunitySupplyList = new ArrayList<GroupBuildingAndCommunitySupply>();
				if(mljiaShopEntityList!=null&&mljiaShopEntityList.size()>0){//商家信息批量入库
						List<BigInteger> toAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply,
								mljiaShopEntityList.size());
						for (int i = 0; i < mljiaShopEntityList.size(); i++) {
							MljiaShopEntity tmpMljiaShopEntity = mljiaShopEntityList.get(i);
							// 商家信息
							String supplyName = tmpMljiaShopEntity.getShop_name();
							String pinyinName = null;
							try {
								pinyinName = PinyinUtil.hanyuToPinyinSimple(supplyName);
							} catch (BadHanyuPinyinOutputFormatCombination e) {
								logger.info(supplyName + " to pinyin cause exception," + e.getMessage());
							}
							String supplyAddress = tmpMljiaShopEntity.getShop_addr();
							BigInteger supplyTypeId = tmpMljiaShopEntity.getSupplyTypeId();
							// Long supplyAverageCost = null;
							String supplyInfoDesc = tmpMljiaShopEntity.getShop_intr();//店铺描述
							Double distance = null;
							if(!StringUtils.isEmpty(tmpMljiaShopEntity.getDistance())){
								distance = Double.valueOf(tmpMljiaShopEntity.getDistance());
							}
							String openHoursDesc = null;
							if(!StringUtils.isEmpty(tmpMljiaShopEntity.getShop_business_start_time())
									&&!StringUtils.isEmpty(tmpMljiaShopEntity.getShop_business_end_time())){
								StringBuffer openHoursDescSbf = new StringBuffer();
								openHoursDescSbf.append(tmpMljiaShopEntity.getShop_business_start_time());
								openHoursDescSbf.append("~");
								openHoursDescSbf.append(tmpMljiaShopEntity.getShop_business_end_time());
								openHoursDesc = openHoursDescSbf.toString();
							}
							
							BigInteger communitySupplyId = AutoFetchServiceUtil.getIdByMljiaShopId(tmpMljiaShopEntity.getShop_id()+"", groupBuildingId, communitySupplyList, communitySupplyList_db);
							if(communitySupplyId==null){//整合更新其他渠道的数据
								communitySupplyId = AutoFetchServiceUtil.getIdByCommunitySupplyName(supplyName, groupBuildingId, communitySupplyListUpd, communitySupplyList_db, groupBuildingHasTCommunitySupplyListUpd, groupBuildingHasTCommunitySupplyList_db);
							}
							CommunitySupply tmpCommunitySupply = new CommunitySupply();
							{//设定商家信息
								tmpCommunitySupply.setAddressDetail(supplyAddress);
								tmpCommunitySupply.setDesc(supplyInfoDesc == null ? "" : supplyInfoDesc);
//								tmpCommunitySupply.setDistance(distance);
								tmpCommunitySupply.setName(supplyName);
								tmpCommunitySupply.setOpenHoursDesc(openHoursDesc);
								tmpCommunitySupply.setPinyinName(pinyinName);
								tmpCommunitySupply.setSys0DelState(0);
								tmpCommunitySupply.settCommunitySupplyTypeFId(supplyTypeId);
								{//美丽加相关数据
									tmpCommunitySupply.setSourceType(CommonModuleDict.Data_SourceType.Mljia);
									tmpCommunitySupply.setMljiaShopId(tmpMljiaShopEntity.getShop_id());;
									tmpCommunitySupply.setMljiaShopShopBustiness(tmpMljiaShopEntity.getShop_shop_bustiness());;
									tmpCommunitySupply.setMljiaShopCertificationStar(tmpMljiaShopEntity.getShop_certification_star());;
									tmpCommunitySupply.setMljiaShopArea(tmpMljiaShopEntity.getShop_area());;
									tmpCommunitySupply.setMljiaShopCredit(tmpMljiaShopEntity.getShop_credit());;
									tmpCommunitySupply.setMljiaShopBusinessStartTime(tmpMljiaShopEntity.getShop_business_start_time());;
									tmpCommunitySupply.setMljiaShopBusinessEndTime(tmpMljiaShopEntity.getShop_business_end_time());;
									tmpCommunitySupply.setMljiaShopImgUrl(tmpMljiaShopEntity.getShop_img_url());;
									tmpCommunitySupply.setMljiaShopImgUrl2(tmpMljiaShopEntity.getShop_img_url2());;
								}
							}
							Boolean needAdd = null;
							if (communitySupplyId == null) {
								communitySupplyId = toAddIdList.get(i);
								needAdd = true;
							}else{
								needAdd = false;
							}
							{//尝试保存图片信息
								String tmpFileName = communitySupplyId+".jpg";//图片存储
								String srcHttpUrl = tmpMljiaShopEntity.getShop_img_url2();//使用美丽加的原图
								boolean uploadRes = uploadSupplyPic(srcHttpUrl, tmpFileName);//上传商家图片到服务器
								if(uploadRes){//成功则记录图片地址
									tmpCommunitySupply.setPicUrl(tmpFileName);
								}
							}
							if(needAdd){//新增
								tmpCommunitySupply.setId(communitySupplyId);
								communitySupplyList.add(tmpCommunitySupply);
							}else{//更新
								tmpCommunitySupply.setId(communitySupplyId);
								communitySupplyListUpd.add(tmpCommunitySupply);
							}
							
//							if (communitySupplyId == null) {
//								// 设定商家信息
//								tmpCommunitySupply.setAddressDetail(supplyAddress);
//								tmpCommunitySupply.setDesc(supplyInfoDesc == null ? "" : supplyInfoDesc);
//								tmpCommunitySupply.setDistance(distance);
//								tmpCommunitySupply.setName(supplyName);
//								tmpCommunitySupply.setOpenHoursDesc(openHoursDesc);
//								tmpCommunitySupply.setPicUrl(picUrl);
//								tmpCommunitySupply.setPinyinName(pinyinName);
//								tmpCommunitySupply.setSys0DelState(0);
//								tmpCommunitySupply.settCommunitySupplyTypeFId(supplyTypeId);
//								{//美丽加相关数据
//									tmpCommunitySupply.setSourceType(CommonModuleDict.Data_SourceType.Mljia);
//									tmpCommunitySupply.setMljiaShopId(tmpMljiaShopEntity.getShop_id());;
//									tmpCommunitySupply.setMljiaShopShopBustiness(tmpMljiaShopEntity.getShop_shop_bustiness());;
//									tmpCommunitySupply.setMljiaShopCertificationStar(tmpMljiaShopEntity.getShop_certification_star());;
//									tmpCommunitySupply.setMljiaShopArea(tmpMljiaShopEntity.getShop_area());;
//									tmpCommunitySupply.setMljiaShopCredit(tmpMljiaShopEntity.getShop_credit());;
//									tmpCommunitySupply.setMljiaShopBusinessStartTime(tmpMljiaShopEntity.getShop_business_start_time());;
//									tmpCommunitySupply.setMljiaShopBusinessEndTime(tmpMljiaShopEntity.getShop_business_end_time());;
//									tmpCommunitySupply.setMljiaShopImgUrl(tmpMljiaShopEntity.getShop_img_url());;
//									tmpCommunitySupply.setMljiaShopImgUrl2(tmpMljiaShopEntity.getShop_img_url2());;
//								}
//								communitySupplyId = toAddIdList.get(i);
//								tmpCommunitySupply.setId(communitySupplyId);
//								communitySupplyList.add(tmpCommunitySupply);
//							}else{
//								//商家数据  不存在则新增;存在则更新
//							}
							// 商家联系方式
							if (!StringUtils.isEmpty(tmpMljiaShopEntity.getShop_tel())) {
								List<String> phoList = TelPhoneParser.getPhoneList(tmpMljiaShopEntity.getShop_tel());
								if (phoList != null && phoList.size() > 0) {
									for (int m = 0; m < phoList.size(); m++) {
										SuppluContectInfoEntity tmpSuppluContectInfoEntity = new SuppluContectInfoEntity();
										tmpSuppluContectInfoEntity.setPhone(phoList.get(m));
										tmpSuppluContectInfoEntity.setSupplyId(communitySupplyId);
										suppluContectInfoEntityList.add(tmpSuppluContectInfoEntity);
									}

								}
								{// 商家小区关系
									GroupBuildingAndCommunitySupply tmpGroupBuildingAndCommunitySupply = new GroupBuildingAndCommunitySupply();
									tmpGroupBuildingAndCommunitySupply.setCommunitySupplyId(communitySupplyId);
									tmpGroupBuildingAndCommunitySupply.setGroupBuildingId(groupBuildingId);
									tmpGroupBuildingAndCommunitySupply.setDistance(distance);
									tmpGroupBuildingAndCommunitySupply.setOrder(null);
									groupBuildingAndCommunitySupplyList.add(tmpGroupBuildingAndCommunitySupply);
								}
							}
						}
						
					// 商家联系方式批量初始化
					supplyContectBatchInit(suppluContectInfoEntityList, communitySupplyContectList, communitySupplyContectList_db, communitySupplyContectListUpd);
					// 商家小区关系批量初始化
					groupBuildingHasTCommunitySupplyBatchInit(groupBuildingAndCommunitySupplyList, groupBuildingHasTCommunitySupplyList, groupBuildingHasTCommunitySupplyList_db, groupBuildingHasTCommunitySupplyListUpd);;
				}
				// 批量更改三个表的数据
				dataBatch2DB(communitySupplyList, communitySupplyContectList, groupBuildingHasTCommunitySupplyList, communitySupplyListUpd, communitySupplyContectListUpd, groupBuildingHasTCommunitySupplyListUpd);;
				//99.更新小区信息
				if(mljiaShopEntityList!=null){
//					toUpdGroupBuilding.setFetchStatus(GroupBuildingDict.GroupBuilding_fetchStatus.MljiaFetch);//设定美丽加已经抓取
					toUpdGroupBuilding.setMljFetchStatus(GroupBuildingDict.GroupBuilding_fetchStatus.HasFetch);
				}
				groupBuildingBaseDao.updateGroupBuilding(toUpdGroupBuilding);
			}
//		}
	}
	@Override
	public synchronized void fetchSupply2DBForBaidu(BigInteger groupBuildingId, String baiduLbsAk) {
		if (groupBuildingId == null) {
			return;
		}
		if(StringUtils.isEmpty(baiduLbsAk)){
			baiduLbsAk = sysParamManager.getSysParaValue(SysParamKey.BAIDU_LBS_AK);
		}
//		synchronized (groupBuildingId) {
			logger.info("BaiduApi start to fetch data,groupBuildingId is :"+groupBuildingId+",baiduLbsAk is "+baiduLbsAk);
			// 查询小区明细
			GroupBuildingEntity currGroupBuilding = commonRoomService.getGroupBuildingById(groupBuildingId);
			logger.info("currGroupBuilding data is :"+JSON.toJSONString(currGroupBuilding));
			if (
					(currGroupBuilding.getBaiduFetchStatus()!= null&&currGroupBuilding.getBaiduFetchStatus() == GroupBuildingDict.GroupBuilding_fetchStatus.HasFetch 
					)//百度已经抓取过数据
					|| currGroupBuilding.getCheckStatus().compareTo(RoomDict.CheckStatus.DaiShenHe) == 0
					|| currGroupBuilding.getCheckStatus().compareTo(RoomDict.CheckStatus.ShenHeBuTongGuo) == 0) {
				logger.info("fetchSupply2DBForBaidu ignore to fetch data,groupBuildingId is :"+groupBuildingId);
				return;
			}
			
			// 查询商家类别列表
			List<CommunitySupplyType> communitySupplyTypeList_db = communitySupplyTypeBaseDao
					.selectCommunitySupplyTypeByCondition(null, true);
			List<CommunitySupplyContect> communitySupplyContectList_db = communitySupplyDao
					.selectCommunitySupplyContect(groupBuildingId);
			List<CommunitySupply> communitySupplyList_db = communitySupplyDao.selectCommunitySupply(groupBuildingId);
			GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupplyQry = new GroupBuildingHasTCommunitySupply();
			groupBuildingHasTCommunitySupplyQry.settGroupBuildingFId(groupBuildingId);
			List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList_db = groupBuildingHasTCommunitySupplyBaseDao
					.selectGroupBuildingHasTCommunitySupplyByCondition(MapConverter.toMap(groupBuildingHasTCommunitySupplyQry),
							true);
			
			//待新增的数据
			List<CommunitySupply> communitySupplyList = new ArrayList<CommunitySupply>();
			List<CommunitySupplyContect> communitySupplyContectList = new ArrayList<CommunitySupplyContect>();
			List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList = new ArrayList<GroupBuildingHasTCommunitySupply>();
			
			//待更新的数据
			List<CommunitySupply> communitySupplyListUpd = new ArrayList<CommunitySupply>();
			List<CommunitySupplyContect> communitySupplyContectListUpd = new ArrayList<CommunitySupplyContect>();
			List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyListUpd = new ArrayList<GroupBuildingHasTCommunitySupply>();
			
			GroupBuilding toUpdGroupBuilding = new GroupBuilding();
			toUpdGroupBuilding.setId(groupBuildingId);

			{// 百度API抓取数据
				// 遍历完一个小区，就存入数据库
				String cityName = currGroupBuilding.getAddressBlockEntity().getAddressCityEntity().getName();
				String groupBuildIngName = currGroupBuilding.getName();
				// if (!cityInfo.equals("深圳市福田区")) {
				// continue;
				// }

				// 1.查询所有已经签约的小区列表[或只指定哪些小区]
				CoordinateResult tmpCoordinateResult = BaseFetchUtil.getLocationByName(baiduLbsAk, cityName, currGroupBuilding
						.getAddressBlockEntity().getName() + groupBuildIngName);
				if (tmpCoordinateResult == null) {
					logger.info(groupBuildingId + " has no Location.");
					return;
				}
				toUpdGroupBuilding.setBaiduLevel(tmpCoordinateResult.getLevel());
				toUpdGroupBuilding.setBaiduLocatLat(tmpCoordinateResult.getLocation().getLat());
				toUpdGroupBuilding.setBaiduLocatLng(tmpCoordinateResult.getLocation().getLng());
				toUpdGroupBuilding.setBaiduPrecise(tmpCoordinateResult.getPrecise() == null ? null : tmpCoordinateResult
						.getPrecise().longValue());
				logger.info(tmpCoordinateResult);
				List<MerchantResult> toDealMerchantResultList = new ArrayList<MerchantResult>();
				// 2.组装查询条件，查询对应的商家列表数据
				for (CommunitySupplyType communitySupplyType : communitySupplyTypeList_db) {
					if(communitySupplyType.getSearchKey() != null && communitySupplyType.getSearchKey().contains("便民")) {
						continue;
					}
					if (communitySupplyType.getImportanceLevel() <= 0) {
						continue;
					}// 过滤顶级类别
						// if(!communitySupplyType.getName().equals("街道办")){
						// continue;
						// }
					BigInteger supplyTypeId = communitySupplyType.getId();// 美容
					List<String> searchKeyList = CommunitySupplyDataFetchUtil.getSearchNameList(communitySupplyType.getName(),
							communitySupplyType.getSearchKey());

					// List<MerchantResult> totalMerchantResultResults =
					// BaseFetchUtil.getListByLocation(tmpCoordinateResult,searchKeyList/*,new
					// PageModel(0, 10)*/);
					List<MerchantResult> totalMerchantResultResults = new ArrayList<MerchantResult>();
					for (int radis = 100; radis <= 1000; radis += 100) {
						if (totalMerchantResultResults.size() >= BaseFetchUtil.totalMaxCount) {
							break;
						}
						List<MerchantResult> tmpList = BaseFetchUtil.getListByLocation(baiduLbsAk, tmpCoordinateResult,
								searchKeyList, radis);
						if (tmpList != null && tmpList.size() > 0) {
							totalMerchantResultResults.addAll(tmpList);
						}
					}
					logger.info("查询" + cityName + "(" + currGroupBuilding.getAddressBlockEntity().getName() + ")~"
							+ groupBuildIngName + "(" + groupBuildingId + ")~类别" + communitySupplyType.getName() + "(" + supplyTypeId
							+ "):总数据为：" + totalMerchantResultResults.size() + "个。");
					// 3.分析数据，组装，存在则不处理，不存在则更新:商家数据，商家小区关系数据，区分数据录入方式，其它信息存储，更新小区的坐标
					if (totalMerchantResultResults != null && totalMerchantResultResults.size() > 0) {
						for (MerchantResult tmpAA : totalMerchantResultResults) {
							boolean isExist = false;
							for (MerchantResult existData : toDealMerchantResultList) {
								if (tmpAA.getName().equals(existData.getName())) {
									isExist = true;
									break;
								}
							}
							if (!isExist) {
								tmpAA.setSupplyTypeId(supplyTypeId);
								toDealMerchantResultList.add(tmpAA);
							}
						}
					}
				}

				List<SuppluContectInfoEntity> suppluContectInfoEntityList = new ArrayList<SuppluContectInfoEntity>();
				List<GroupBuildingAndCommunitySupply> groupBuildingAndCommunitySupplyList = new ArrayList<GroupBuildingAndCommunitySupply>();
				if (toDealMerchantResultList.size() > 0) {// 商家信息批量入库
					List<BigInteger> toAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply,
							toDealMerchantResultList.size());
					for (int i = 0; i < toDealMerchantResultList.size(); i++) {
						MerchantResult tmpMerchantResult = toDealMerchantResultList.get(i);
						// 商家信息
						String supplyName = tmpMerchantResult.getName();
						String pinyinName = null;
						try {
							pinyinName = PinyinUtil.hanyuToPinyinSimple(supplyName);
						} catch (BadHanyuPinyinOutputFormatCombination e) {
							logger.info(supplyName + " to pinyin cause exception," + e.getMessage());
						}
						String supplyAddress = tmpMerchantResult.getAddress();
						BigInteger supplyTypeId = tmpMerchantResult.getSupplyTypeId();
						// Long supplyAverageCost = null;
						String supplyInfoDesc = null;
						String openHoursDesc = null;
						Double distance = null;//距离
						String picUrl = null;//图片
						BigInteger communitySupplyId = AutoFetchServiceUtil.getIdByCommunitySupplyName(supplyName, groupBuildingId,
								communitySupplyList, communitySupplyList_db, groupBuildingHasTCommunitySupplyList,
								groupBuildingHasTCommunitySupplyList_db);
						CommunitySupply tmpCommunitySupply = new CommunitySupply();
						{// 设定商家信息
							tmpCommunitySupply.setAddressDetail(supplyAddress);
							tmpCommunitySupply.setDesc(supplyInfoDesc == null ? "" : supplyInfoDesc);
							tmpCommunitySupply.setName(supplyName);
							tmpCommunitySupply.setOpenHoursDesc(openHoursDesc);
//							tmpCommunitySupply.setDistance(distance);
							tmpCommunitySupply.setPicUrl(picUrl);
							tmpCommunitySupply.setPinyinName(pinyinName);
							tmpCommunitySupply.setSys0DelState(0);
							tmpCommunitySupply.settCommunitySupplyTypeFId(supplyTypeId);
							{// baidu相关数据
								tmpCommunitySupply.setSourceType(CommonModuleDict.Data_SourceType.BAIDU_MAP_API);
								tmpCommunitySupply.setBaiduLocatLat(tmpMerchantResult.getLocation().getLat());
								tmpCommunitySupply.setBaiduLocatLng(tmpMerchantResult.getLocation().getLng());
								tmpCommunitySupply.setBaiduStreetId(tmpMerchantResult.getStreet_id());
								tmpCommunitySupply.setBaiduUid(tmpMerchantResult.getUid());
							}
						}
						if (communitySupplyId == null) {
							communitySupplyId = toAddIdList.get(i);
							tmpCommunitySupply.setId(communitySupplyId);
							communitySupplyList.add(tmpCommunitySupply);
						}else{
							tmpCommunitySupply.setId(communitySupplyId);
							communitySupplyListUpd.add(tmpCommunitySupply);
						}
//						if (communitySupplyId == null) {
//							// 设定商家信息
//							tmpCommunitySupply.setAddressDetail(supplyAddress);
//							tmpCommunitySupply.setDesc(supplyInfoDesc == null ? "" : supplyInfoDesc);
//							tmpCommunitySupply.setName(supplyName);
//							tmpCommunitySupply.setOpenHoursDesc(supplyServerTime);
//							tmpCommunitySupply.setPinyinName(pinyinName);
//							tmpCommunitySupply.setSys0DelState(0);
//							tmpCommunitySupply.settCommunitySupplyTypeFId(supplyTypeId);
//							{// baidu相关数据
//								tmpCommunitySupply.setSourceType(CommonModuleDict.Data_SourceType.BAIDU_MAP_API);
//								tmpCommunitySupply.setBaiduLocatLat(tmpMerchantResult.getLocation().getLat());
//								tmpCommunitySupply.setBaiduLocatLng(tmpMerchantResult.getLocation().getLng());
//								tmpCommunitySupply.setBaiduStreetId(tmpMerchantResult.getStreet_id());
//								tmpCommunitySupply.setBaiduUid(tmpMerchantResult.getUid());
//							}
//							communitySupplyId = toAddIdList.get(i);
//							tmpCommunitySupply.setId(communitySupplyId);
//							communitySupplyList.add(tmpCommunitySupply);
//						}
						// 商家联系方式
						if (!StringUtils.isEmpty(tmpMerchantResult.getTelephone())) {
							List<String> phoList = TelPhoneParser.getPhoneList(tmpMerchantResult.getTelephone());
							if (phoList != null && phoList.size() > 0) {
								for (int m = 0; m < phoList.size(); m++) {
									SuppluContectInfoEntity tmpSuppluContectInfoEntity = new SuppluContectInfoEntity();
									tmpSuppluContectInfoEntity.setPhone(phoList.get(m));
									tmpSuppluContectInfoEntity.setSupplyId(communitySupplyId);
									suppluContectInfoEntityList.add(tmpSuppluContectInfoEntity);
								}

							}
							{// 商家小区关系
								GroupBuildingAndCommunitySupply tmpGroupBuildingAndCommunitySupply = new GroupBuildingAndCommunitySupply();
								tmpGroupBuildingAndCommunitySupply.setCommunitySupplyId(communitySupplyId);
								tmpGroupBuildingAndCommunitySupply.setGroupBuildingId(groupBuildingId);
								tmpGroupBuildingAndCommunitySupply.setDistance(distance);
								tmpGroupBuildingAndCommunitySupply.setOrder(null);
								groupBuildingAndCommunitySupplyList.add(tmpGroupBuildingAndCommunitySupply);
							}
						}
					}
				}
				// 商家联系方式批量初始化
				supplyContectBatchInit(suppluContectInfoEntityList, communitySupplyContectList, communitySupplyContectList_db, communitySupplyContectListUpd);;
				// 商家小区关系批量初始化
				groupBuildingHasTCommunitySupplyBatchInit(groupBuildingAndCommunitySupplyList, groupBuildingHasTCommunitySupplyList, groupBuildingHasTCommunitySupplyList_db, groupBuildingHasTCommunitySupplyListUpd);;
			}
			// 批量更改三个表的数据
			dataBatch2DB(communitySupplyList, communitySupplyContectList, groupBuildingHasTCommunitySupplyList, communitySupplyListUpd, communitySupplyContectListUpd, groupBuildingHasTCommunitySupplyListUpd);;
//			toUpdGroupBuilding.setFetchStatus(GroupBuildingDict.GroupBuilding_fetchStatus.BaiduHasFetch);//设定百度已经抓取
			toUpdGroupBuilding.setBaiduFetchStatus(GroupBuildingDict.GroupBuilding_fetchStatus.HasFetch);
			// 更新小区数据
			groupBuildingBaseDao.updateGroupBuilding(toUpdGroupBuilding);
//		}
	}
	
	/**
	 * 商家联系方式批量 初始化
	 * @param suppluContectInfoEntityList
	 * @param communitySupplyContectList
	 * @param communitySupplyContectList_db
	 */
	private void supplyContectBatchInit(List<SuppluContectInfoEntity> suppluContectInfoEntityList,List<CommunitySupplyContect> communitySupplyContectList
			,List<CommunitySupplyContect> communitySupplyContectList_db
			,List<CommunitySupplyContect> communitySupplyContectListUpd){
		if (suppluContectInfoEntityList.size() > 0) {// 商家联系方式批量入库
			List<BigInteger> toAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_contect,
					suppluContectInfoEntityList.size());
			for (int aa = 0; aa < suppluContectInfoEntityList.size(); aa++) {// 商家联系方式
				SuppluContectInfoEntity tmpSuppluContectInfoEntity = suppluContectInfoEntityList.get(aa);
				String contectInfo = tmpSuppluContectInfoEntity.getPhone();
				BigInteger communitySupplyId = tmpSuppluContectInfoEntity.getSupplyId();
				String contectUserName = "";
				// 查询是否已存在
				BigInteger existRelaId = AutoFetchServiceUtil.getIdByCommunitySupplyAndCommunityContect(communitySupplyId,
						contectInfo, communitySupplyContectList, communitySupplyContectList_db);
				CommunitySupplyContect tmpCommunitySupplyContect = new CommunitySupplyContect();
				{//设定联系方式信息
					tmpCommunitySupplyContect.setClickCount(0L);
					tmpCommunitySupplyContect.setContectInfo(contectInfo);
					tmpCommunitySupplyContect.setEsqName(contectUserName);
					tmpCommunitySupplyContect.setSys0DelState(0);
					tmpCommunitySupplyContect.settCommunitySupplyFId(communitySupplyId);
					tmpCommunitySupplyContect.setUserIdentity("商家");
					tmpCommunitySupplyContect.setType(CommunitySupplyDict.CommunitySupplyContect_Type.Phone);//电话
				}
				if (existRelaId == null) {
					existRelaId = toAddIdList.get(aa);
					tmpCommunitySupplyContect.setId(existRelaId);
					communitySupplyContectList.add(tmpCommunitySupplyContect);
				}else{
					tmpCommunitySupplyContect.setId(existRelaId);
					communitySupplyContectListUpd.add(tmpCommunitySupplyContect);
				}
//				if (existRelaId == null) {
//					tmpCommunitySupplyContect.setClickCount(0L);
//					tmpCommunitySupplyContect.setContectInfo(contectInfo);
//					tmpCommunitySupplyContect.setEsqName(contectUserName);
//					tmpCommunitySupplyContect.setSys0DelState(0);
//					tmpCommunitySupplyContect.settCommunitySupplyFId(communitySupplyId);
//					tmpCommunitySupplyContect.setUserIdentity("商家");
//					tmpCommunitySupplyContect.setType(CommunitySupplyDict.CommunitySupplyContect_Type.Phone);//电话
//					existRelaId = toAddIdList.get(aa);
//					tmpCommunitySupplyContect.setId(existRelaId);
//					communitySupplyContectList.add(tmpCommunitySupplyContect);
//				}
			}
		}
	}
	
	/**
	 * 商家小区关系批量初始化
	 * @param groupBuildingAndCommunitySupplyList
	 * @param groupBuildingHasTCommunitySupplyList
	 * @param groupBuildingHasTCommunitySupplyList_db
	 */
	private void groupBuildingHasTCommunitySupplyBatchInit(List<GroupBuildingAndCommunitySupply> groupBuildingAndCommunitySupplyList
			,List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList
			,List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList_db
			,List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyListUpd){
		if (groupBuildingAndCommunitySupplyList.size() > 0) {// 商家小区关系批量入库
			List<BigInteger> toAddIdList = uuidManager.getNextUuidBigInteger(
					SEQConstants.t_group_building_has_t_community_supply, groupBuildingAndCommunitySupplyList.size());
			for (int i = 0; i < groupBuildingAndCommunitySupplyList.size(); i++) {
				GroupBuildingAndCommunitySupply tmpGroupBuildingAndCommunitySupply = groupBuildingAndCommunitySupplyList
						.get(i);
				BigInteger tmpGroupBuildingId = tmpGroupBuildingAndCommunitySupply.getGroupBuildingId();
				BigInteger communitySupplyId = tmpGroupBuildingAndCommunitySupply.getCommunitySupplyId();
				Double distance = tmpGroupBuildingAndCommunitySupply.getDistance();
				Integer order = tmpGroupBuildingAndCommunitySupply.getOrder();
				// 校验关系信息是否存在
				BigInteger existId = AutoFetchServiceUtil.getIdByGroupBuildingAndCommunitySupply(tmpGroupBuildingId,
						communitySupplyId, groupBuildingHasTCommunitySupplyList, groupBuildingHasTCommunitySupplyList_db);
				GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupply = new GroupBuildingHasTCommunitySupply();
				{//设定商家小区关系信息
					groupBuildingHasTCommunitySupply.setSys0DelState(0);
					groupBuildingHasTCommunitySupply.settCommunitySupplyFId(communitySupplyId);
					groupBuildingHasTCommunitySupply.settGroupBuildingFId(tmpGroupBuildingId);
					groupBuildingHasTCommunitySupply.setDistance(distance);
					groupBuildingHasTCommunitySupply.setOrder(order);
				}
				if (existId == null) {
					existId = toAddIdList.get(i);
					groupBuildingHasTCommunitySupply.setId(existId);
					groupBuildingHasTCommunitySupplyList.add(groupBuildingHasTCommunitySupply);
				}else{
					groupBuildingHasTCommunitySupply.setId(existId);
					groupBuildingHasTCommunitySupplyListUpd.add(groupBuildingHasTCommunitySupply);
				}
//				if (existId == null) {
//					groupBuildingHasTCommunitySupply.setSys0DelState(0);
//					groupBuildingHasTCommunitySupply.settCommunitySupplyFId(communitySupplyId);
//					groupBuildingHasTCommunitySupply.settGroupBuildingFId(tmpGroupBuildingId);
//					existId = toAddIdList.get(i);
//					groupBuildingHasTCommunitySupply.setId(existId);
//					groupBuildingHasTCommunitySupplyList.add(groupBuildingHasTCommunitySupply);
//				}
			}
		}
	}
	
	private void dataBatch2DB(
			List<CommunitySupply> communitySupplyList,List<CommunitySupplyContect> communitySupplyContectList,List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList
			,List<CommunitySupply> communitySupplyListUpd,List<CommunitySupplyContect> communitySupplyContectListUpd,List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyListUpd
			){
		int maxCount = 1000;
		//批量新增
		
//		if(communitySupplyList.size()>0){
//			communitySupplyBaseDao.insertCommunitySupplyBatch(communitySupplyList);
//		}
		if(communitySupplyList.size()>0){
			List<CommunitySupply> tmpList = new ArrayList<CommunitySupply>();
			for(int i=0;i<communitySupplyList.size();i++){
				tmpList.add(communitySupplyList.get(i));
				if(i!=0&&i%maxCount==0){
					if(tmpList.size()>0){communitySupplyBaseDao.insertCommunitySupplyBatch(tmpList);}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){communitySupplyBaseDao.insertCommunitySupplyBatch(tmpList);}
		}
		
//		if(communitySupplyContectList.size()>0){
//			communitySupplyContectBaseDao.insertCommunitySupplyContectBatch(communitySupplyContectList);
//		}
		if(communitySupplyContectList.size()>0){
			List<CommunitySupplyContect> tmpList = new ArrayList<CommunitySupplyContect>();
			for(int i=0;i<communitySupplyContectList.size();i++){
				tmpList.add(communitySupplyContectList.get(i));
				if(i!=0&&i%maxCount==0){
					if(tmpList.size()>0){communitySupplyContectBaseDao.insertCommunitySupplyContectBatch(tmpList);}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){communitySupplyContectBaseDao.insertCommunitySupplyContectBatch(tmpList);}
		}
		
//		if(groupBuildingHasTCommunitySupplyList.size()>0){
//			groupBuildingHasTCommunitySupplyBaseDao.insertGroupBuildingHasTCommunitySupplyBatch(groupBuildingHasTCommunitySupplyList);
//		}
		if(groupBuildingHasTCommunitySupplyList.size()>0){
			List<GroupBuildingHasTCommunitySupply> tmpList = new ArrayList<GroupBuildingHasTCommunitySupply>();
			for(int i=0;i<groupBuildingHasTCommunitySupplyList.size();i++){
				tmpList.add(groupBuildingHasTCommunitySupplyList.get(i));
				if(i!=0&&i%maxCount==0){
					if(tmpList.size()>0){groupBuildingHasTCommunitySupplyBaseDao.insertGroupBuildingHasTCommunitySupplyBatch(tmpList);}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){groupBuildingHasTCommunitySupplyBaseDao.insertGroupBuildingHasTCommunitySupplyBatch(tmpList);}
		}
		
		//批量更新
//		if(communitySupplyListUpd.size()>0){
//			communitySupplyBaseDao.updateCommunitySupplyBatch(communitySupplyListUpd);
//		}
		if(communitySupplyListUpd.size()>0){
			List<CommunitySupply> tmpList = new ArrayList<CommunitySupply>();
			for(int i=0;i<communitySupplyListUpd.size();i++){
				tmpList.add(communitySupplyListUpd.get(i));
				if(i!=0&&i%maxCount==0){
					if(tmpList.size()>0){communitySupplyBaseDao.updateCommunitySupplyBatch(tmpList);}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){communitySupplyBaseDao.updateCommunitySupplyBatch(tmpList);}
		}
		
//		if(communitySupplyContectListUpd.size()>0){
//			communitySupplyContectBaseDao.updateCommunitySupplyContectBatch(communitySupplyContectListUpd);
//		}
		if(communitySupplyContectListUpd.size()>0){
			List<CommunitySupplyContect> tmpList = new ArrayList<CommunitySupplyContect>();
			for(int i=0;i<communitySupplyContectListUpd.size();i++){
				tmpList.add(communitySupplyContectListUpd.get(i));
				if(i!=0&&i%maxCount==0){
					if(tmpList.size()>0){communitySupplyContectBaseDao.updateCommunitySupplyContectBatch(tmpList);}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){communitySupplyContectBaseDao.updateCommunitySupplyContectBatch(tmpList);}
		}
		
//		if(groupBuildingHasTCommunitySupplyListUpd.size()>0){
//			groupBuildingHasTCommunitySupplyBaseDao.updateGroupBuildingHasTCommunitySupplyBatch(groupBuildingHasTCommunitySupplyListUpd);
//		}
		if(groupBuildingHasTCommunitySupplyListUpd.size()>0){
			List<GroupBuildingHasTCommunitySupply> tmpList = new ArrayList<GroupBuildingHasTCommunitySupply>();
			for(int i=0;i<groupBuildingHasTCommunitySupplyListUpd.size();i++){
				tmpList.add(groupBuildingHasTCommunitySupplyListUpd.get(i));
				if(i!=0&&i%maxCount==0){
					if(tmpList.size()>0){groupBuildingHasTCommunitySupplyBaseDao.updateGroupBuildingHasTCommunitySupplyBatch(tmpList);}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){groupBuildingHasTCommunitySupplyBaseDao.updateGroupBuildingHasTCommunitySupplyBatch(tmpList);}
		}
		
//	if(addressCityList.size()>0){
//	List<AddressCity> tmpList = new ArrayList<AddressCity>();
//	for(int i=0;i<addressCityList.size();i++){
//		tmpList.add(addressCityList.get(i));
//		if(i!=0&&i%maxCount==0){
//			if(tmpList.size()>0){addressCityBaseService.insertAddressCityBatch(tmpList);}
//			tmpList.clear();
//		}
//	}
//	if(tmpList.size()>0){addressCityBaseService.insertAddressCityBatch(tmpList);}
//}

	}
	
	/**
	 * 上传商家图片
	 * @param srcHttpUrl 网络图片地址
	 * @param resFileName 目标图片名称
	 */
	public boolean uploadSupplyPic(String srcHttpUrl,String resFileName){
		boolean resBool = false;
		if(!StringUtils.isEmpty(srcHttpUrl)&&!StringUtils.isEmpty(resFileName)){
			byte[] fileContent = UrlFileFetchUtil.getBytesByUrl(srcHttpUrl);
			String supplyPicBase = sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_SUPPLY_BASIC_PICPATH);
			try {
				fileServerService.uploadFile(supplyPicBase+resFileName, fileContent);
				//增加生成小图的任务处理
				FutureTask<Boolean> task = new FutureTask<Boolean>(new UploadSmallPicRunnable(smallPicUploadService,BusinessModelType.CommunitySupply,fileServerService.getFileServierUploadBasePath(),supplyPicBase, resFileName, fileContent));
				new Thread(task).start();
				resBool = true;
			} catch (IOException e) {
				logger.info("AutoFetchSupllyDataService.uploadSupplyPic.uploadFile cause exception",e);
			}
		}
		return resBool;
	}
	
}
