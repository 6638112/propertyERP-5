package com.cnfantasia.server.ms.buildingRoom.service;

import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.roomVerifyPayment.dao.IRoomVerifyPaymentDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.propertyProprietor.dao.IPropertyProprietorBaseDao;
import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.realRoomHasTPropertyProprietor.dao.IRealRoomHasTPropertyProprietorBaseDao;
import com.cnfantasia.server.domainbase.realRoomHasTPropertyProprietor.entity.RealRoomHasTPropertyProprietor;
import com.cnfantasia.server.domainbase.roomVerifyPayment.entity.RoomVerifyPayment;
import com.cnfantasia.server.ms.buildingRoom.constant.BuildingRoomConstant;
import com.cnfantasia.server.ms.buildingRoom.dao.IBuildingDao;
import com.cnfantasia.server.ms.buildingRoom.dao.IRoomDao;
import com.cnfantasia.server.ms.buildingRoom.entity.BuildingEntity;
import com.cnfantasia.server.ms.buildingRoom.entity.RoomEntity;
import com.cnfantasia.server.ms.groupBuilding.dao.IGroupBuildingDao;
import com.cnfantasia.server.ms.property.importer.entity.BuildingRoomProprietor;
import com.cnfantasia.server.ms.property.importer.entity.PropertyProprietor4Import;
import com.cnfantasia.server.ms.property.importer.entity.RealRoom4Import;
import com.cnfantasia.server.ms.propertyPayConfig.entity.MrExportModelEntity;
import com.cnfantasia.server.ms.propertyPayConfig.entity.MrImportModelEntity;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

public class BuildingRoomService implements IBuildingRoomService {
	
	private Log logger = LogFactory.getLog(getClass());
	
	private IBuildingDao buildingDao;
	
	private IRoomDao roomDao;
	
	private IPropertyProprietorBaseDao propertyProprietorBaseDao;

	@Resource
	private IRealRoomHasTPropertyProprietorBaseDao realRoomHasTPropertyProprietorBaseDao;
	
	@Resource
	private IRoomVerifyPaymentDao roomVerifyPaymentDao;
	@Resource
	private IGroupBuildingDao groupBuildingDao;

	public IBuildingDao getBuildingDao() {
		return buildingDao;
	}

	public void setBuildingDao(IBuildingDao buildingDao) {
		this.buildingDao = buildingDao;
	}

	public IRoomDao getRoomDao() {
		return roomDao;
	}

	public void setRoomDao(IRoomDao roomDao) {
		this.roomDao = roomDao;
	}

	public IPropertyProprietorBaseDao getPropertyProprietorBaseDao() {
		return propertyProprietorBaseDao;
	}

	public void setPropertyProprietorBaseDao(IPropertyProprietorBaseDao propertyProprietorBaseDao) {
		this.propertyProprietorBaseDao = propertyProprietorBaseDao;
	}

	@Override
	public int queryRoomForCount(Map<String, Object> paramMap) {
		return roomDao.queryRoomForCount(paramMap);
	}

	@Override
	public List<RoomEntity> queryRoomForList(Integer curPageIndex, Integer pageSize, Map<String, Object> paramMap, boolean isPage) {
		if(isPage){
			paramMap.put("_begin", pageSize * curPageIndex);
			paramMap.put("_length", pageSize);
		}
		return roomDao.queryRoomForList(paramMap);
	}

	@Override
	@Transactional
	public int saveOrUpdateRoom(String id, String buildingId, String unitName, String num, String proprietorId, String proprietorName, String proprietorTel,
			String proprietorIdentifyNo, String roomDesc) {
		int i = 0;
		try{
			BigInteger ppId= null;
			//1.保存业主
			if(!StringUtils.isEmpty(proprietorName)){
				PropertyProprietor proprietor = new PropertyProprietor();
				proprietor.setName(proprietorName);
				proprietor.setPhone(proprietorTel);
				proprietor.setIdentifyNo(proprietorIdentifyNo);
				if(!StringUtils.isEmpty(proprietorId)){
					proprietor.setId(CnfantasiaCommUtil.convert2BigInteger(proprietorId));
					CnfantasiaCommUtil.updateStandard(proprietor);
					i = this.getPropertyProprietorBaseDao().updatePropertyProprietor(proprietor);
				}else{
					CnfantasiaCommUtil.newStandard(proprietor,SEQConstants.t_property_proprietor);
					i = this.getPropertyProprietorBaseDao().insertPropertyProprietor(proprietor);
				}
				ppId = proprietor.getId();
			}
			//2.保存房号
			RealRoom room = new RealRoom();
			room.settBuildingFId(CnfantasiaCommUtil.convert2BigInteger(buildingId));
			room.setUnitName(unitName);
			room.setNumTail(num);
			room.setNum(unitName==null?"":unitName+"-"+num);
			room.settPropertyProprietorFId(ppId);
			room.setCheckStatus(9);
			room.setDesc(roomDesc);
			if(!StringUtils.isEmpty(id)){
				room.setId(CnfantasiaCommUtil.convert2BigInteger(id));
				CnfantasiaCommUtil.updateStandard(room);
				i = this.getRoomDao().updateRealRoom(room);
			}else{
				CnfantasiaCommUtil.newStandard(room, SEQConstants.t_real_room);
				room.setNumTailCharOrder(com.cnfantasia.server.common.utils.StringUtils.getSubCharString(room.getNumTail()));
				room.setNumTailDigitOrder(com.cnfantasia.server.common.utils.StringUtils.getSubDigitString(room.getNumTail()));
				i = this.getRoomDao().insertRealRoom(room);
			}
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
			i = 0;
		}
		return i;
	}

	@Override
	public int queryBuildingForCount(Map<String, Object> paramMap) {
		return buildingDao.queryBuildingForCount(paramMap);
	}

	@Override
	public List<BuildingEntity> queryBuildingForList(int curPageIndex, int pageSize, Map<String, Object> paramMap, boolean isPage) {
		if(isPage){
			paramMap.put("_begin", pageSize * curPageIndex);
			paramMap.put("_length", pageSize);
		}
		return buildingDao.queryBuildingForList(paramMap);
	}

	@Override
	public int saveOrUpdateBuilding(String id, String groupbuildingId, String name, String code) {
		int i = 0;
		try{
			Building building = new Building();
			building.settGroupBuildingFId(CnfantasiaCommUtil.convert2BigInteger(groupbuildingId));
			building.setName(name);
			building.setCode(code);
			building.setCheckStatus(BuildingRoomConstant.Audit_Type.AT9);
			building.setCreater(CnfantasiaCommUtil.getCurrentUserId());
			//building.setSourceType(BuildingRoomConstant.Source_Type.ST1);//表未补全，需要强哥补全这个字段
			if(!StringUtils.isEmpty(id)){
				building.setId(CnfantasiaCommUtil.convert2BigInteger(id));
				CnfantasiaCommUtil.updateStandard(building);
				i = this.getBuildingDao().updateBuilding(building);
			}else{
				CnfantasiaCommUtil.newStandard(building, SEQConstants.t_building);
				building.setNameCharOrder(com.cnfantasia.server.common.utils.StringUtils.getSubCharString(name));
				building.setNameDigitOrder(com.cnfantasia.server.common.utils.StringUtils.getSubDigitString(name));
				i = this.getBuildingDao().insertBuilding(building);
			}
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public RoomEntity queryRoomById(String id) {
		return roomDao.queryRoomById(id);
	}

	@Override
	public BuildingEntity queryBuildingById(String id) {
		return buildingDao.queryBuildingById(id);
	}

	@Override
	public int deleteBuilding(String buildingId) {
		int i = 0;
		try{
			i = buildingDao.deleteBuildingLogic(CnfantasiaCommUtil.convert2BigInteger(buildingId));
			if(i>0){
				roomDao.deleteRoomByBuildingId(buildingId);
			}
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int deleteRoom(String roomId, String propertyProprietorId) {
		int i = 0;
		try{
			//1.删除房号
			if(!StringUtils.isEmpty(roomId)){
				i = this.roomDao.deleteRealRoomLogic(CnfantasiaCommUtil.convert2BigInteger(roomId));
			}
			//2.删除业主
			if(!StringUtils.isEmpty(propertyProprietorId)){
				i = this.propertyProprietorBaseDao.deletePropertyProprietorLogic(CnfantasiaCommUtil.convert2BigInteger(propertyProprietorId));
			}
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return i;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional
	public int saveImportRooms(List<RoomEntity> importRooms) {
		int i = 0;
		IUuidManager uuidManager = (IUuidManager) CnfantasiaCommUtil.getBeanManager("uuidManager");
		List<BigInteger> proprietorIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_proprietor, importRooms.size());
		List<PropertyProprietor> savePropertyProprietors = new ArrayList<PropertyProprietor>();
		List saveRealRooms = new ArrayList();
		BigInteger id;
		PropertyProprietor proprietor;
		RealRoom room;
		for (int index = 0; index < proprietorIds.size(); index++) {
			id = proprietorIds.get(index);
			proprietor = importRooms.get(index).getProprietor();
			proprietor.setId(id);
			proprietor.setSys0AddTime(CnfantasiaCommUtil.getCurrentTime());//创建时间
			proprietor.setSys0AddUser(CnfantasiaCommUtil.getCurrentUserId());//创建人
			savePropertyProprietors.add(proprietor);

			room = importRooms.get(index);
			room.settPropertyProprietorFId(id);
			room.setCheckStatus(BuildingRoomConstant.Audit_Type.AT9);
			room.setNumTailCharOrder(com.cnfantasia.server.common.utils.StringUtils.getSubCharString(room.getNumTail()));
			room.setNumTailDigitOrder(com.cnfantasia.server.common.utils.StringUtils.getSubDigitString(room.getNumTail()));
			
			saveRealRooms.add(room);
		}
		if (propertyProprietorBaseDao.insertPropertyProprietorBatch(savePropertyProprietors) > 0) {//业主信息保存成功
			CnfantasiaCommUtil.newStandardList(saveRealRooms, SEQConstants.t_real_room);
			i = this.roomDao.insertRealRoomBatch(saveRealRooms);
		}
		return i;
	}

	/**
	 * 保存从Excel导入的信息
	 * <p>
	 * 处理思路：先从DB中取出相关记录，对比发现若有已存在的数据，更新之，否则插入新的记录
	 * 
	 * @author wenfq
	 * @return 导入的结果
	 */
	@Override
	@Transactional
	public String saveBuildingRoomProprietor(BigInteger groupBuildingId, Set<String> buildingSet, Set<BuildingRoomProprietor> brpSet) {
		StringBuilder sb = new StringBuilder("导入结果如下：\\r\\r");

		// 1. 先导入楼栋，已存的楼栋需要更新CheckStatu==9，没有就插入
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sys0DelState", 0);
		paramMap.put("tGroupBuildingFId", groupBuildingId);

		List<Building> buildingList = buildingDao.selectBuildingByCondition(paramMap, false);
		List<Building> willBeUpdatedBuildingList = new ArrayList<Building>(); //待更新状态的楼栋

		for (Building building : buildingList) {
			if (buildingSet.remove(building.getName())) {//剔除已存在的楼栋
				if (building.getCheckStatus() != BuildingRoomConstant.Audit_Type.AT2 && building.getCheckStatus() != BuildingRoomConstant.Audit_Type.AT9) {
					Building b = new Building();
					b.setId(building.getId());
					b.setCheckStatus(BuildingRoomConstant.Audit_Type.AT9);
					willBeUpdatedBuildingList.add(b);
				}
			}
			updateBuildingIdForBrpSet(brpSet, building);
		}

		if (willBeUpdatedBuildingList.size() > 0) {//已存在但用户看不见的楼栋需要更新CheckStatu==9
			buildingDao.updateBuildingBatch(willBeUpdatedBuildingList);
			sb.append("共更新").append(willBeUpdatedBuildingList.size()).append("条楼栋数据；\\r");
		}

		IUuidManager uuidManager = (IUuidManager) CnfantasiaCommUtil.getBeanManager("uuidManager");
		if (!buildingSet.isEmpty()) {
			List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_building, buildingSet.size());
			List<Building> willInsertBuildingList = new ArrayList<Building>();
			int i = 0;
			for(String buildName: buildingSet){
				Building b = new Building();
				b.setId(idList.get(i++));
				b.setName(buildName);
				b.setNameCharOrder(com.cnfantasia.server.common.utils.StringUtils.getSubCharString(buildName));
				b.setNameDigitOrder(com.cnfantasia.server.common.utils.StringUtils.getSubDigitString(buildName));
				b.settGroupBuildingFId(groupBuildingId);
				b.setSys0AddTime(CnfantasiaCommUtil.getCurrentTime());
				b.setSys0AddUser(CnfantasiaCommUtil.getCurrentUserId());
				b.setCheckStatus(RoomDict.CheckStatus.WuXuShenHe);
				willInsertBuildingList.add(b);
				updateBuildingIdForBrpSet(brpSet, b);
			}
			buildingDao.insertBuildingBatch(willInsertBuildingList);
			logger.info("building insert size is: " + willInsertBuildingList.size());
			sb.append("共插入").append(willInsertBuildingList.size()).append("条楼栋数据；\\r");
		}

		//2. 导入房间 
		Set<BigInteger> buildingIdSet = new HashSet<BigInteger>();
		for (BuildingRoomProprietor brp : brpSet) {
			if (brp.getBuildingId() != null)
				buildingIdSet.add(brp.getBuildingId());
		}
		List<BigInteger> buildingIdList = new ArrayList<BigInteger>(buildingIdSet);

		List<RealRoom4Import> rrList = roomDao.selectRealRoomByBuildingIdList(buildingIdList);
		List<RealRoom> realRoomWillUpdList = new ArrayList<RealRoom>();
		Set<BuildingRoomProprietor> willInsertBrpSet = (Set<BuildingRoomProprietor>) ((HashSet) brpSet).clone(); //待插入的房间
		for (int i = 0; i < rrList.size(); i++) {
			RealRoom4Import rr4import = rrList.get(i);
			for (BuildingRoomProprietor brp : brpSet) {
				if (brp.getBuildingId().equals(rr4import.getbId())
						&& brp.getRoomNumber().equals(rr4import.getRrNumTail()) //楼栋、单元、房号相同，需要更新房间 
						&& ((brp.getRoomUnit() == null && rr4import.getRrUnitName() == null) || (brp.getRoomUnit() != null && brp.getRoomUnit().equals(
								rr4import.getRrUnitName())))) {
					{
						RealRoom rr = new RealRoom();
						rr.setId(rr4import.getRrId());
						int updFieldCount = 0;
						if (rr4import.getRoomSize() <= 0 && brp.getRoomSize() != null && brp.getRoomSize() > 0) {
							rr.setRoomSize(brp.getRoomSize());
							updFieldCount++;
						}
						if (rr4import.getRoomManagerPrice() <= 0 && brp.getRoomManagerPrice() != null && brp.getRoomManagerPrice() > 0) {
							rr.setPropertyFeePerM2(brp.getRoomManagerPrice());
							updFieldCount++;
						}
						if (rr4import.getPropMoney() <= 0 && brp.getManangeFee() > 0) {
							rr.setPropMoney(PriceUtil.multiply100(brp.getManangeFee()));
							updFieldCount++;
						}

						if (brp.getSaleStatus() > 0) {
							rr.setSaleStatus(brp.getSaleStatus());
							updFieldCount++;
						}
						if (brp.getLivingStatus() > 0) {
							rr.setLivingStatus(brp.getLivingStatus());
							updFieldCount++;
						}
						if (brp.getLeaseStatus() > 0) {
							rr.setLeaseStatus(brp.getLeaseStatus());
							updFieldCount++;
						}

						boolean needUpdateCheckStatus = (rr4import.getCheckStatus() != BuildingRoomConstant.Audit_Type.AT2 && rr4import.getCheckStatus() != BuildingRoomConstant.Audit_Type.AT9);
						if (needUpdateCheckStatus) {//已存在的房间，如果checkStatus不是1也不是9，需要更新为checkStatus==9
							rr.setCheckStatus(BuildingRoomConstant.Audit_Type.AT9);
						}

						if (updFieldCount > 0 || needUpdateCheckStatus) {
							realRoomWillUpdList.add(rr);
						}
						brp.setRoomId(rr.getId());
					}
					willInsertBrpSet.remove(brp);
				}
			}
		}

		// 2.1 更新之前的老房间数据
		if (!realRoomWillUpdList.isEmpty()) { // updte之前的房间
			roomDao.updateRealRoomBatch(realRoomWillUpdList);
			logger.info("updated room size: " + realRoomWillUpdList.size());
			sb.append("共更新").append(realRoomWillUpdList.size()).append("条房间数据；\\r");
		}
		// 2.2插入新的房间
		if (!willInsertBrpSet.isEmpty()) {
			List<RealRoom> realRoomWillInsertList = new ArrayList<RealRoom>(willInsertBrpSet.size());
			List<BigInteger> rrIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_real_room, willInsertBrpSet.size());
			int i = 0;
			List<RoomVerifyPayment> roomVerifyPayments = new ArrayList<RoomVerifyPayment>();
			for (BuildingRoomProprietor brp : willInsertBrpSet) {
				RealRoom rr = new RealRoom();
				rr.setId(rrIdList.get(i++));
				rr.settBuildingFId(brp.getBuildingId());
				rr.setNum(StringUtils.isEmpty(brp.getRoomUnit()) ? brp.getRoomNumber() : brp.getRoomUnit() + "-" + brp.getRoomNumber());
				rr.setNumTail(brp.getRoomNumber());
				rr.setNumTailCharOrder(com.cnfantasia.server.common.utils.StringUtils.getSubCharString(rr.getNumTail()));
				rr.setNumTailDigitOrder(com.cnfantasia.server.common.utils.StringUtils.getSubDigitString(rr.getNumTail()));
				rr.setUnitName(brp.getRoomUnit());
				rr.setRoomSize(brp.getRoomSize());
				rr.setPropertyFeePerM2(brp.getRoomManagerPrice());
				rr.setPropMoney(brp.getManangeFee() == null ? null: PriceUtil.multiply100(brp.getManangeFee()));
				rr.setCheckStatus(RoomDict.CheckStatus.WuXuShenHe);

				rr.setSaleStatus(brp.getSaleStatus());
				rr.setLivingStatus(brp.getLivingStatus());
				rr.setLeaseStatus(brp.getLeaseStatus());

				brp.setRoomId(rr.getId());
				realRoomWillInsertList.add(rr);
				// 门牌验证缴费情况查询表
				{
					List<RoomVerifyPayment> roomVerifyPaymentsTmp = roomVerifyPaymentDao.selectRoomVerifyPaymentsByBuildingId(rr.gettBuildingFId());
					for(int k=0; k<roomVerifyPaymentsTmp.size(); k++){
						RoomVerifyPayment roomVerifyPaymentTmp = roomVerifyPaymentsTmp.get(k);
						RoomVerifyPayment roomVerifyPayment = new RoomVerifyPayment();
						
						roomVerifyPayment.settAddressProvinceId(roomVerifyPaymentTmp.gettAddressProvinceId());
						roomVerifyPayment.setProvince(roomVerifyPaymentTmp.getProvince());
						roomVerifyPayment.settAddressCityId(roomVerifyPaymentTmp.gettAddressCityId());
						roomVerifyPayment.setCity(roomVerifyPaymentTmp.getCity());
						roomVerifyPayment.settGroupBuildingId(roomVerifyPaymentTmp.gettGroupBuildingId());
						roomVerifyPayment.setGbName(roomVerifyPaymentTmp.getGbName());
						roomVerifyPayment.settBuildingId(roomVerifyPaymentTmp.gettBuildingId());
						roomVerifyPayment.setBuildingName(roomVerifyPaymentTmp.getBuildingName());
						
						roomVerifyPayment.settRealRoomId(rr.getId());
						if (org.apache.commons.lang.StringUtils.isNotBlank(rr.getUnitName())) {
							if(org.apache.commons.lang.StringUtils.isNotBlank(rr.getNum()) && rr.getNum().lastIndexOf("-")>0){
								roomVerifyPayment.setRoomNum(rr.getNum().substring(rr.getNum().lastIndexOf("-")+1));
							}
						} else {
							roomVerifyPayment.setRoomNum(rr.getNum());
						}
						roomVerifyPayment.setUnit(rr.getUnitName());
						roomVerifyPayment.setRegisterState(0);
						roomVerifyPayments.add(roomVerifyPayment);
					}
				}
			}

			roomDao.insertRealRoomBatch(realRoomWillInsertList);
			{
				List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_room_verify_payment, roomVerifyPayments.size());
				for(int n=0; n<roomVerifyPayments.size(); n++){
					roomVerifyPayments.get(n).setId(ids.get(n));
				}
				roomVerifyPaymentDao.insertRoomVerifyPaymentBatch(roomVerifyPayments);
			}
			logger.info("inserted room size: " + realRoomWillInsertList.size());
			sb.append("共插入").append(realRoomWillInsertList.size()).append("条房间数据；\\r");
		}

		//3. 导入业主信息 
		Set<BigInteger> realRoomIdSet = new HashSet<BigInteger>();
		for (BuildingRoomProprietor brp : brpSet) {
			if (brp.getRoomId() != null)
				realRoomIdSet.add(brp.getRoomId());
		}
		List<BigInteger> realRoomIdList = new ArrayList<BigInteger>(realRoomIdSet);
		List<PropertyProprietor4Import> ppList = roomDao.selectPropertyProprietorByRealRoomIdList(realRoomIdList);
		List<PropertyProprietor> propertyProprietorWillUpdList = new ArrayList<PropertyProprietor>();
		Set<BuildingRoomProprietor> willInsertBrpSet_pp = (Set<BuildingRoomProprietor>) ((HashSet) brpSet).clone(); //待插入业主
		for (int i = 0; i < ppList.size(); i++) {
			PropertyProprietor4Import pp4import = ppList.get(i);//业主1
			PropertyProprietor4Import pp4importNext = (i == ppList.size() - 1 ? null : ppList.get(i + 1));//业主2
			for (BuildingRoomProprietor brp : brpSet) {
				if (brp.getRoomId().equals(pp4import.getRrId())) {
					PropertyProprietor pp1 = new PropertyProprietor();
					pp1.setId(pp4import.getPpId());
					int updFieldCount1 = 0;
					if (!StringUtils.isEmpty(brp.getProprietorName1()) && StringUtils.isEmpty(pp4import.getPpName())) {
						pp1.setName(brp.getProprietorName1());
						updFieldCount1++;
					}
					if (!StringUtils.isEmpty(brp.getProprietorPhone1()) && StringUtils.isEmpty(pp4import.getPpPhone())) {
						pp1.setPhone(brp.getProprietorPhone1());
						updFieldCount1++;
					}
					if (!StringUtils.isEmpty(brp.getProprietorIdNumber1()) && StringUtils.isEmpty(pp4import.getPpIdNumber())) {
						pp1.setIdentifyNo(brp.getProprietorIdNumber1());
						updFieldCount1++;
					}
					if (updFieldCount1 > 0) {
						propertyProprietorWillUpdList.add(pp1);
					}
					if (pp4importNext != null &&
							brp.getRoomId().equals(pp4importNext.getRrId())) {
						i++;
						PropertyProprietor pp2 = new PropertyProprietor();
						pp2.setId(pp4importNext.getPpId());
						int updFieldCount2 = 0;
						if (!StringUtils.isEmpty(brp.getProprietorName2()) && StringUtils.isEmpty(pp4importNext.getPpName())) {
							pp2.setName(brp.getProprietorName1());
							updFieldCount2++;
						}
						if (!StringUtils.isEmpty(brp.getProprietorPhone2()) && StringUtils.isEmpty(pp4importNext.getPpPhone())) {
							pp2.setPhone(brp.getProprietorPhone2());
							updFieldCount2++;
						}
						if (!StringUtils.isEmpty(brp.getProprietorIdNumber2()) && StringUtils.isEmpty(pp4importNext.getPpIdNumber())) {
							pp2.setIdentifyNo(brp.getProprietorIdNumber2());
							updFieldCount2++;
						}
						if (updFieldCount2 > 0) {
							propertyProprietorWillUpdList.add(pp2);
						}
					}
					willInsertBrpSet_pp.remove(brp);
				}
			}
		}
		if (!propertyProprietorWillUpdList.isEmpty()) {
			propertyProprietorBaseDao.updatePropertyProprietorBatch(propertyProprietorWillUpdList);
			logger.info("propertyProprietorWillUpdList size is: " + propertyProprietorWillUpdList.size());
			sb.append("共更新").append(propertyProprietorWillUpdList.size()).append("条业主信息；\\r");
		}
		if (!willInsertBrpSet_pp.isEmpty()) {
			List<RealRoom> realRoomWillUdpList = new ArrayList<RealRoom>();
			List<PropertyProprietor> propertyProprietorWillInsertList = new ArrayList<PropertyProprietor>();
			List<RealRoomHasTPropertyProprietor> realRoomHasTPropertyProprietorWillInsertList = new ArrayList<>();

			List<BigInteger> ppIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_proprietor, willInsertBrpSet_pp.size()*2);
			int i = 0;
			for (BuildingRoomProprietor brp : willInsertBrpSet_pp) {
				RealRoom rr = new RealRoom();
				rr.setId(brp.getRoomId());

//				if(!StringUtils.isEmpty(brp.getProprietorName1())) {  //空的业主名称也允许插入一条记录，因为好多地方inner join t_property_proprietor
					PropertyProprietor pp1 = new PropertyProprietor();
					pp1.setId(ppIdList.get(i++));
					pp1.setName(brp.getProprietorName1());
					pp1.setPhone(brp.getProprietorPhone1());
					pp1.setIdentifyNo(brp.getProprietorIdNumber1());
					propertyProprietorWillInsertList.add(pp1);

					RealRoomHasTPropertyProprietor rrpp1 = new RealRoomHasTPropertyProprietor();
					rrpp1.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_real_room_has_t_property_proprietor, 1).get(0));
					rrpp1.settRealRoomFId(brp.getRoomId());
					rrpp1.settPropertyProprietorFId(pp1.getId());
					realRoomHasTPropertyProprietorWillInsertList.add(rrpp1);

					rr.settPropertyProprietorFId(pp1.getId());

				if(!StringUtils.isEmpty(brp.getProprietorName2())) {
					PropertyProprietor pp2 = new PropertyProprietor();
					pp2.setId(ppIdList.get(i++));
					pp2.setName(brp.getProprietorName2());
					pp2.setPhone(brp.getProprietorPhone2());
					pp2.setIdentifyNo(brp.getProprietorIdNumber2());
					propertyProprietorWillInsertList.add(pp2);

					RealRoomHasTPropertyProprietor rrpp2 = new RealRoomHasTPropertyProprietor();
					rrpp2.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_real_room_has_t_property_proprietor, 1).get(0));
					rrpp2.settRealRoomFId(brp.getRoomId());
					rrpp2.settPropertyProprietorFId(pp2.getId());
					realRoomHasTPropertyProprietorWillInsertList.add(rrpp2);

					if(rr.gettPropertyProprietorFId() == null) {
						rr.settPropertyProprietorFId(pp2.getId());//业主1要与房间的关联字段对应上
					}
				}

				if(rr.gettPropertyProprietorFId()!=null) {
					realRoomWillUdpList.add(rr);
				}
			}

			if (!propertyProprietorWillInsertList.isEmpty()) {
				logger.info("propertyProprietorWillUpdList size is: " + propertyProprietorWillUpdList.size());
				propertyProprietorBaseDao.insertPropertyProprietorBatch(propertyProprietorWillInsertList);
				sb.append("共插入").append(propertyProprietorWillInsertList.size()).append("条业主信息；");
				logger.info("after propertyProprietor inserted, will updte realRoom size:  " + realRoomWillUdpList.size());
				roomDao.updateRealRoomBatch(realRoomWillUdpList);

				realRoomHasTPropertyProprietorBaseDao.insertRealRoomHasTPropertyProprietorBatch(realRoomHasTPropertyProprietorWillInsertList);
				logger.info("after realRoomHasTPropertyProprietorWillInsertList inserted, insert size：" + realRoomHasTPropertyProprietorWillInsertList.size());
			}
		}

		if (sb.length() == "导入结果如下：\\r\\r".length()) {
			sb.append("没有更新和插入数据");
		} else {
			int udpCount = roomDao.doAutoRoomValidateFromDB(groupBuildingId);
			logger.info("do auto room validate count is: " + udpCount);
		}

		return sb.toString();
		//throw new RuntimeException("roll back... ");
	}

	/**
	 * 更新BuildingRoomProprietor中的BuildingId
	 * 
	 * @param brpSet
	 * @param building
	 */
	private void updateBuildingIdForBrpSet(Set<BuildingRoomProprietor> brpSet, Building building) {
		for (BuildingRoomProprietor brp : brpSet) {
			if (building.getName().equals(brp.getBuildingName())) {
				brp.setBuildingId(building.getId());
			}
		}
	}

	@Override
	public Integer getBuildingAndRoomCountByGbId(BigInteger gbId) {
		return groupBuildingDao.getBuildingAndRoomCountByGbId(gbId);
	}

	@Override
	public List<MrImportModelEntity> getMrImportModelEntity(BigInteger gbId) {
		return roomDao.getMrImportModelEntity(gbId);
	}

	@Override
	public List<MrExportModelEntity> getMrExportModelEntity(BigInteger gbId, BigInteger itemId) {
		return roomDao.getMrExportModelEntity(gbId, itemId);
	}
}
