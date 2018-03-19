/**   
* Filename:    HomeSupplyTypeService.java   
* @version:    1.0  
* Create at:   2015年8月17日 下午12:18:43   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.communitySupply.dao.ICommunitySupplyDao;
import com.cnfantasia.server.api.communitySupply.entity.CommunitySupplyTypeEntity;
import com.cnfantasia.server.api.communitySupply.entity.HomeSupplyType;
import com.cnfantasia.server.api.communitySupply.entity.Level3ListMapEntity;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.operation.constant.OperationDict;
import com.cnfantasia.server.api.operation.entity.OperationHomeSupplyTypeEntity;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyConstant;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.plotproperty.entity.Propfee;
import com.cnfantasia.server.api.plotproperty.service.IPlotpropertyService;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.entity.GroupBuildingHasTPropertyService;
import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.service.IGroupBuildingHasTPropertyServiceBaseService;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;

/**
 * Filename:    HomeSupplyTypeService.java
 * @version:    1.0.0
 * Create at:   2015年8月17日 下午12:18:43
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月17日       shiyl             1.0             1.0 Version
 */
public class HomeSupplyTypeService implements IHomeSupplyTypeService{
	
	private IPlotpropertyService plotpropertyService;
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private ICommunitySupplyDao communitySupplyDao;
	public void setCommunitySupplyDao(ICommunitySupplyDao communitySupplyDao) {
		this.communitySupplyDao = communitySupplyDao;
	}
	
	private IAddressOperationService addressOperationService;
	public void setAddressOperationService(IAddressOperationService addressOperationService) {
		this.addressOperationService = addressOperationService;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

	private IGroupBuildingHasTPropertyServiceBaseService groupBuildingHasTPropertyServiceBaseService;
	public void setGroupBuildingHasTPropertyServiceBaseService(IGroupBuildingHasTPropertyServiceBaseService groupBuildingHasTPropertyServiceBaseService) {
		this.groupBuildingHasTPropertyServiceBaseService = groupBuildingHasTPropertyServiceBaseService;
	}

	@Override
	public Level3ListMapEntity getSupplyTypeAll() {
		List<Map<String,Object>> level01List = new ArrayList<Map<String,Object>>();
		{
			List<CommunitySupplyTypeEntity> communitySupplyTypeList = communitySupplyDao.selectCommunitySupplyTypeLevel01();
			if(communitySupplyTypeList!=null&&communitySupplyTypeList.size()>0){
				for(CommunitySupplyTypeEntity communitySupplyType:communitySupplyTypeList){
					Map<String,Object> tmpMap = commEntityConvertService.communitySupplyType2Map(communitySupplyType,communitySupplyType.getTopCommunitySupplyType());
					if(tmpMap!=null){level01List.add(tmpMap);}
				}
			}
		}
		List<Map<String,Object>> level02List = new ArrayList<Map<String,Object>>();
		{
			List<CommunitySupplyTypeEntity> communitySupplyTypeList = communitySupplyDao.selectCommunitySupplyTypeLevel02();
			if(communitySupplyTypeList!=null&&communitySupplyTypeList.size()>0){
				for(CommunitySupplyTypeEntity communitySupplyType:communitySupplyTypeList){
					Map<String,Object> tmpMap = commEntityConvertService.communitySupplyType2Map(communitySupplyType,communitySupplyType.getTopCommunitySupplyType());
					if(tmpMap!=null){level02List.add(tmpMap);}
				}
			}
		}
		return new Level3ListMapEntity(level01List, level02List);
	}

	@Override
	public Level3ListMapEntity getSupplyTypeList4Url() {
		List<Map<String,Object>> level01List = new ArrayList<Map<String,Object>>();
		{
			{
				List<Map<String,Object>> existLevel01List = new ArrayList<Map<String,Object>>();
				String ho2oConfigStr = sysParamManager.getSysParaValue(SysParamKey.HO2O_Config);
				if(!StringUtils.isEmpty(ho2oConfigStr)){
					String[] idArr = ho2oConfigStr.split(";");
					List<String> idList = Arrays.asList(idArr);
					if(idList.contains("11")){
						existLevel01List.add(createO2OLink(new BigInteger("11"),"巴士", "h_bashi.jpg","http://jiefangqu.m.dadabus.com/Order/index.html#search_section"));
					}
					if(idList.contains("12")){
						existLevel01List.add(createO2OLink(new BigInteger("12"),"推拿", "h_tuina.jpg","http://m.nanapanda.cn/channel/jiefangqu/personal"));
					}
//					if(idList.contains("13")){
//						existLevel01List.add(createO2OLink(new BigInteger("13"),"保养", "h_baoyang.png",""));
//					}
				}
				if(existLevel01List!=null&&existLevel01List.size()>0){
					level01List.addAll(existLevel01List);
				}
			}
			List<CommunitySupplyTypeEntity> communitySupplyTypeList = communitySupplyDao.selectCommunitySupplyTypeLevel01();
			if(communitySupplyTypeList!=null&&communitySupplyTypeList.size()>0){
				for(CommunitySupplyTypeEntity communitySupplyType:communitySupplyTypeList){
					Map<String,Object> tmpMap = commEntityConvertService.communitySupplyType2Map(communitySupplyType,communitySupplyType.getTopCommunitySupplyType());
					if(tmpMap!=null){level01List.add(tmpMap);}
				}
			}
		}
		List<Map<String,Object>> level02List = new ArrayList<Map<String,Object>>();
		{
			List<CommunitySupplyTypeEntity> communitySupplyTypeList = communitySupplyDao.selectCommunitySupplyTypeLevel02();
			if(communitySupplyTypeList!=null&&communitySupplyTypeList.size()>0){
				for(CommunitySupplyTypeEntity communitySupplyType:communitySupplyTypeList){
					Map<String,Object> tmpMap = commEntityConvertService.communitySupplyType2Map(communitySupplyType,communitySupplyType.getTopCommunitySupplyType());
					if(tmpMap!=null){level02List.add(tmpMap);}
				}
			}
		}
		return new Level3ListMapEntity(level01List, level02List);
	}
	
	@Override
	public Level3ListMapEntity getSupplyTypeList3Level(BigInteger userId, BigInteger countryId, BigInteger provinceId,
													   BigInteger cityId, BigInteger blockId, BigInteger gbId) {
		//是否开启物业维修
		GroupBuildingHasTPropertyService gbhps = new GroupBuildingHasTPropertyService();
		gbhps.settGroupBuildingFId(gbId);
		gbhps.setStatus(1);
		boolean openRepair = groupBuildingHasTPropertyServiceBaseService.getGroupBuildingHasTPropertyServiceCount(MapConverter.toMap(gbhps)) > 0;
		GroupBuilding groupBuilding = groupBuildingBaseDao.selectGroupBuildingBySeqId(gbId);
		boolean canPayBill = groupBuilding != null && groupBuilding.getPropfeeCanpay() != null && groupBuilding.getPropfeeCanpay() == 1;

		//取出全部数据
		List<OperationHomeSupplyTypeEntity> totalSrcDataList = addressOperationService.getOperationHomeSupplyTypeList(countryId, provinceId, cityId, blockId, gbId);
		List<HomeSupplyType> hp1 = new ArrayList<HomeSupplyType>();
		List<HomeSupplyType> hp2 = new ArrayList<HomeSupplyType>();
		List<HomeSupplyType> hp3 = new ArrayList<HomeSupplyType>();
		//归类123，合并上下级
		if (totalSrcDataList != null && totalSrcDataList.size() > 0) {
			List<OperationHomeSupplyTypeEntity> listhomeplace_1 = new ArrayList<OperationHomeSupplyTypeEntity>();
			List<OperationHomeSupplyTypeEntity> listhomeplace_2 = new ArrayList<OperationHomeSupplyTypeEntity>();
			List<OperationHomeSupplyTypeEntity> listhomeplace_3 = new ArrayList<OperationHomeSupplyTypeEntity>();
			List<OperationHomeSupplyTypeEntity> listlevel_2_hp1 = new ArrayList<OperationHomeSupplyTypeEntity>();
			List<OperationHomeSupplyTypeEntity> listlevel_2_hp2 = new ArrayList<OperationHomeSupplyTypeEntity>();
			List<OperationHomeSupplyTypeEntity> listlevel_2_hp3 = new ArrayList<OperationHomeSupplyTypeEntity>();
			for (OperationHomeSupplyTypeEntity tmpOHT : totalSrcDataList) {
				boolean isRepair = "weixiu".equals(tmpOHT.getCode()) && tmpOHT.getSupplyTypeId() != null &&
						DredgeConstant.ParentCommunitySupplyType.WYBX.compareTo(tmpOHT.getSupplyTypeId()) == 0;

				if (!openRepair && ("wuyebaoxiu".equals(tmpOHT.getCode()) || isRepair)) {
					//未开通物业维修，去除此图标
				} else if ("wuyejiaofei".equals(tmpOHT.getCode()) && !canPayBill) {
					//不能缴费，则去掉这个图标，会有另一个H5的补上
				} else {
					if (OperationDict.OperationHomeSupplyType_DataLevel.level_1.compareTo(tmpOHT.getDataLevel()) == 0) {//顶层数据
						if (OperationDict.OperationHomeSupplyType_HomePlace.place_1.compareTo(tmpOHT.getHomePlace()) == 0) {
							listhomeplace_1.add(tmpOHT);
						} else if (OperationDict.OperationHomeSupplyType_HomePlace.place_2.compareTo(tmpOHT.getHomePlace()) == 0) {
							listhomeplace_2.add(tmpOHT);
						} else if (OperationDict.OperationHomeSupplyType_HomePlace.place_3.compareTo(tmpOHT.getHomePlace()) == 0) {
							listhomeplace_3.add(tmpOHT);
						}
					} else if (OperationDict.OperationHomeSupplyType_DataLevel.level_2.compareTo(tmpOHT.getDataLevel()) == 0) {//第二层数据
						if (OperationDict.OperationHomeSupplyType_HomePlace.place_1.compareTo(tmpOHT.getHomePlace()) == 0) {
							listlevel_2_hp1.add(tmpOHT);
						} else if (OperationDict.OperationHomeSupplyType_HomePlace.place_2.compareTo(tmpOHT.getHomePlace()) == 0) {
							listlevel_2_hp2.add(tmpOHT);
						} else if (OperationDict.OperationHomeSupplyType_HomePlace.place_3.compareTo(tmpOHT.getHomePlace()) == 0) {
							listlevel_2_hp3.add(tmpOHT);
						}
					}
				}
			}
			initHSTData(hp1, listhomeplace_1, listlevel_2_hp1, userId);
			initHSTData(hp2, listhomeplace_2, listlevel_2_hp2, userId);
			initHSTData(hp3, listhomeplace_3, listlevel_2_hp3, userId);
		}

		//组装返回结果数据
		List<Map<String, Object>> level00List = new ArrayList<Map<String, Object>>();
		for (HomeSupplyType tmp : hp1) {
			level00List.add(commEntityConvertService.communitySupplyType2Map(tmp));
		}
		List<Map<String, Object>> level01List = new ArrayList<Map<String, Object>>();

		//广告栏必须前不能有空缺的情况处理，所以广告栏必须排在可以被三整除的地方－－begin
		List<HomeSupplyType> hp21 = new ArrayList<HomeSupplyType>(); //非广告
		List<HomeSupplyType> hp22 = new ArrayList<HomeSupplyType>(); //广告
		int index = 0; //第一个是广告的标记

		for (int i = 0; i < hp2.size(); i++) {
			HomeSupplyType tmp = hp2.get(i);
			if ("homeAds".equals(tmp.getCode())) {
				hp22.add(tmp);
				if (index == 0) {
					index = i;
				}
			} else {
				hp21.add(tmp);
			}
		}

		if (index % 3 != 0) { //广告在不被3整除的地方出现
			index = index + 3 - index % 3;
			if (index <= hp21.size()) {
//					index = index - 1;
				for (int i = 0; i < hp21.size(); i++) {
					if (index == i) {
						for (HomeSupplyType tmp : hp22) {
							level01List.add(commEntityConvertService.communitySupplyType2Map(tmp));
						}
					}
					level01List.add(commEntityConvertService.communitySupplyType2Map(hp21.get(i)));
				}
				if (index >= hp21.size()) {
					for (HomeSupplyType tmp : hp22) {
						level01List.add(commEntityConvertService.communitySupplyType2Map(tmp));
					}
				}
			} else {
				for (HomeSupplyType tmp : hp21) {
					level01List.add(commEntityConvertService.communitySupplyType2Map(tmp));
				}
				for (HomeSupplyType tmp : hp22) {
					level01List.add(commEntityConvertService.communitySupplyType2Map(tmp));
				}
			}
		} else {
			for (HomeSupplyType tmp : hp2) {
				level01List.add(commEntityConvertService.communitySupplyType2Map(tmp));
			}
		}
		//广告栏必须前不能有空缺的情况处理，所以广告栏必须排在可以被三整除的地方－－end

		List<Map<String, Object>> level02List = new ArrayList<Map<String, Object>>();
		for (HomeSupplyType tmp : hp3) {
			level02List.add(commEntityConvertService.communitySupplyType2Map(tmp));
		}

		return new Level3ListMapEntity(level00List, level01List, level02List);

	}
	
	@Resource
	IGroupBuildingBaseDao groupBuildingBaseDao;
	
	@Override
	public Level3ListMapEntity getSupplyTypeList3Level(BigInteger userId) {
		//获取用户所在小区Id
		BigInteger gbId = commonRoomService.getGroupBuildingIdByUserIdForHome(userId);
		 
		return getSupplyTypeList3Level(userId, null, null, null, null, gbId);
	}
	
	private Map<String,Object> createO2OLink(BigInteger id,String name,String iconName,String linkUrl){
		Map<String,Object> tmpMap = null;
		String lastUpdTime = null;
		HomeSupplyType homeSupplyType = HomeSupplyType.newInstanceForHrefLink(id, name, iconName, linkUrl, lastUpdTime);
		tmpMap =commEntityConvertService.communitySupplyType2Map(homeSupplyType);
		return tmpMap;
	}
	
	private void initHSTData(List<HomeSupplyType> hp1,List<OperationHomeSupplyTypeEntity> listhomeplace_1,List<OperationHomeSupplyTypeEntity> listlevel_2_hp1, BigInteger userId){
		if(listhomeplace_1!=null&&listhomeplace_1.size()>0){
			for(OperationHomeSupplyTypeEntity tmpParent:listhomeplace_1){
				HomeSupplyType toAddParent = HomeSupplyType.newInstanceForOperationHomeSupplyType(tmpParent);
				for(OperationHomeSupplyTypeEntity tmpSub:listlevel_2_hp1){
					BigInteger parentId = tmpSub.getParentId();
					if(parentId!=null&&tmpParent.getId().compareTo(parentId)==0){//附加子类
						HomeSupplyType toAddSub = HomeSupplyType.newInstanceForOperationHomeSupplyType(tmpSub);
						appendData(toAddSub, userId);
						toAddParent.addSubData(toAddSub);
					}
				}
				appendData(toAddParent, userId);
				
				hp1.add(toAddParent);
			}
		}
	}
	
	private void appendData(HomeSupplyType toAddParent, BigInteger userId) {
		if("jinrong".equals(toAddParent.getCode())) {
			if(userId == null) {
				toAddParent.setVerifyStatus(1);
				toAddParent.setPropfeeCanpay(0);
			} else {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("userId", userId);
				//查看小区是否校验，是否可以缴费，是否导入过账单
				Propfee propfee = plotpropertyService.getPropertyFeeAndCount(paramMap);
				if(propfee != null ) {
					toAddParent.setVerifyStatus(propfee.getVerifyStatus() == null ? 1 : propfee.getVerifyStatus());
					if(propfee.getPropfeeCanpay() == null || propfee.getPropfeeCanpay() != 1) {
						toAddParent.setPropfeeCanpay(0); //0小区未开通缴费
					} else if(propfee.getTotalPrice() != null && propfee.getTotalPrice() > 0) {
						toAddParent.setPropfeeCanpay(2); //小区可以缴费且导入过账单
					} else {
						toAddParent.setPropfeeCanpay(1); //小区可以缴费，但是从未导入过账单
					}
				} else {
					toAddParent.setVerifyStatus(1);
					toAddParent.setPropfeeCanpay(0);
				}
			}
		}
		//syl-add-2015-12-9 18:56:51物业缴费查询二级菜单,目前采用图标配置的方式
		else if(PlotpropertyConstant.HOME_CODE_PAYBILL.equals(toAddParent.getCode())){
			List<PayBillType> payBillTypeList = plotpropertyService.getPayBillTypeList(userId);
			if(payBillTypeList!=null&&payBillTypeList.size()>0){
				BigInteger topParentId = toAddParent.getId();
				for(PayBillType srcType:payBillTypeList){
					String code=PlotpropertyConstant.HOME_CODE_FEE_OTHER;//其它费用
					if(srcType.getIsPropFee().compareTo(PlotpropertyDict.PayBillType_IsPropFee.YES)==0){//如果为物业费类别
						code=PlotpropertyConstant.HOME_CODE_FEE_PROP;
					}
					HomeSupplyType toAddSub = HomeSupplyType.newInstanceForPayBillType(srcType, topParentId, code);
					toAddParent.addSubData(toAddSub);
				}
			}
		}
	}

	public void setPlotpropertyService(IPlotpropertyService plotpropertyService) {
		this.plotpropertyService = plotpropertyService;
	}
	
}
