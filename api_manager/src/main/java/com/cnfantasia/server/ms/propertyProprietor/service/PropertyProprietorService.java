package com.cnfantasia.server.ms.propertyProprietor.service;

import com.cnfantasia.server.api.roomVerifyPayment.dao.IRoomVerifyPaymentDao;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.building.dao.IBuildingBaseDao;
import com.cnfantasia.server.domainbase.propertyLessee.dao.PropertyLesseeBaseDao;
import com.cnfantasia.server.domainbase.propertyLessee.entity.PropertyLessee;
import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;
import com.cnfantasia.server.domainbase.propertyProprietor.service.PropertyProprietorBaseService;
import com.cnfantasia.server.domainbase.realRoom.dao.IRealRoomBaseDao;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.realRoomHasTPropertyLessee.dao.RealRoomHasTPropertyLesseeBaseDao;
import com.cnfantasia.server.domainbase.realRoomHasTPropertyLessee.entity.RealRoomHasTPropertyLessee;
import com.cnfantasia.server.domainbase.realRoomHasTPropertyProprietor.dao.IRealRoomHasTPropertyProprietorBaseDao;
import com.cnfantasia.server.domainbase.realRoomHasTPropertyProprietor.entity.RealRoomHasTPropertyProprietor;
import com.cnfantasia.server.domainbase.roomVerifyPayment.entity.RoomVerifyPayment;
import com.cnfantasia.server.ms.buildingRoom.dao.IRoomDao;
import com.cnfantasia.server.ms.propertyProprietor.dao.IPropertyProprietorDao;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

public class PropertyProprietorService extends PropertyProprietorBaseService implements IPropertyProprietorService {
	Log logger = LogFactory.getLog(getClass());

	IPropertyProprietorDao propertyProprietorDao;

	private IUuidManager uuidManager;

	private IDualDao dualDao;

	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	public void setPropertyProprietorDao(IPropertyProprietorDao propertyProprietorDao) {
		this.propertyProprietorDao = propertyProprietorDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	@Resource
	IRealRoomBaseDao realRoomBaseDao;

	@Resource
	IBuildingBaseDao buildingBaseDao;

	@Resource
	IRoomDao roomDao;

	@Resource
	PropertyLesseeBaseDao propertyLesseeBaseDao;

	@Resource
	RealRoomHasTPropertyLesseeBaseDao realRoomHasTPropertyLesseeBaseDao;
	
	@Resource
	private IRoomVerifyPaymentDao roomVerifyPaymentDao;

	@Resource
	private IRealRoomHasTPropertyProprietorBaseDao realRoomHasTPropertyProprietorBaseDao;

	/**
	 * 根据序列号查询(业主信息表)信息
	 * 
	 * @param id
	 * @return
	 */
	public PropertyProprietorEntity getPropertyProprietorByRoomId(java.math.BigInteger id) {
		return propertyProprietorDao.selectPropertyProprietorByRoomId(id);
	}

	@Override
	public String saveImportPPEntity(List<PropertyProprietorEntity> ppList) {
		String resultInfo = propertyProprietorDao.vierfyImportPayBill(ppList);
		return resultInfo;
	}

	@Override
	public int getPPList_byUserId_forCount(Map<String, Object> paramMap) {
		return propertyProprietorDao.getPPList_byUserId_forCount(paramMap);
	}

	@Override
	public List<PropertyProprietorEntity> getPPList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return propertyProprietorDao.getPPList_byUserId_forPage(curPageIndex, pageSize, paramMap);
	}

	@Override
	@Transactional
	public String updatePropertyProprietor_and_realRoom(RealRoom rr) {
		int udpCount = 0;

		udpCount += realRoomBaseDao.updateRealRoom(rr);

		//udpCount += propertyProprietorDao.updatePropertyProprietor_for_NameIdentfyNoPhone(ppEntity);

		return udpCount > 0 ? "保存成功" : "保存失败";
	}

	@Override
	@Transactional
	public String saveAddNew(RealRoom rr, PropertyProprietor pp) {
		int insertCount = 0;

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("unitName", rr.getUnitName());
		paramMap.put("tBuildingFId", rr.gettBuildingFId());
		paramMap.put("numTail", rr.getNumTail());
		paramMap.put("sys0DelState", 0);
		if (realRoomBaseDao.selectRealRoomCount(paramMap, false) > 0) {
			return "该楼栋下已存在同名的房间";
		}

		pp.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_proprietor));
		insertCount += propertyProprietorDao.insertPropertyProprietor(pp);

		rr.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_real_room));
		rr.settPropertyProprietorFId(pp.getId());
		rr.setNumTailCharOrder(com.cnfantasia.server.common.utils.StringUtils.getSubCharString(rr.getNumTail()));
		rr.setNumTailDigitOrder(com.cnfantasia.server.common.utils.StringUtils.getSubDigitString(rr.getNumTail()));
		
		insertCount += realRoomBaseDao.insertRealRoom(rr);

		if(insertCount == 2){
			//门牌验证缴费情况查询表
			List<RoomVerifyPayment> roomVerifyPayments = roomVerifyPaymentDao.selectRoomVerifyPaymentsByBuildingId(rr.gettBuildingFId());
			List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_room_verify_payment, roomVerifyPayments.size());
			for(int i=0; i<roomVerifyPayments.size(); i++){
				RoomVerifyPayment roomVerifyPayment = roomVerifyPayments.get(i);
				roomVerifyPayment.setId(ids.get(i));
				roomVerifyPayment.settRealRoomId(rr.getId());
				if (StringUtils.isNotBlank(rr.getUnitName())) {
					if(StringUtils.isNotBlank(rr.getNum()) && rr.getNum().lastIndexOf("-")>0){
						roomVerifyPayment.setRoomNum(rr.getNum().substring(rr.getNum().lastIndexOf("-")+1));
					}
				} else {
					roomVerifyPayment.setRoomNum(rr.getNum());
				}
				roomVerifyPayment.setUnit(rr.getUnitName());
				roomVerifyPayment.setRegisterState(0);
			}
			roomVerifyPaymentDao.insertRoomVerifyPaymentBatch(roomVerifyPayments);
		}
		
		return insertCount == 2 ? "保存成功" : "保存失败";
	}

	@Override
	public void doAutoRoomValidateFromDB(RealRoom rr) {
		roomDao.doAutoRoomValidateFromDB(buildingBaseDao.selectBuildingBySeqId(rr.gettBuildingFId()).gettGroupBuildingFId());
	}

	@Override
	public List<PropertyProprietor> getPropertyProprietorListByRoomId(BigInteger rrId) {
		return propertyProprietorDao.getPropertyProprietorListByRoomId(rrId);
	}

	@Override
	public List<PropertyLessee> getPropertyLesseeListByRoomId(BigInteger rrId) {
		return propertyProprietorDao.getPropertyLesseeListByRoomId(rrId);
	}

	@Override
	public BigInteger savePropertyProprietor(BigInteger rrId, PropertyProprietor pp) {
		int updCount = 0;
		if(pp.getId() == null){
			pp.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_proprietor));
			updCount += propertyProprietorDao.insertPropertyProprietor(pp);

			RealRoomHasTPropertyProprietor realRoomHasTPropertyProprietor = new RealRoomHasTPropertyProprietor();
			realRoomHasTPropertyProprietor.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_real_room_has_t_property_proprietor));
			realRoomHasTPropertyProprietor.settRealRoomFId(rrId);
			realRoomHasTPropertyProprietor.settPropertyProprietorFId(pp.getId());
			realRoomHasTPropertyProprietorBaseDao.insertRealRoomHasTPropertyProprietor(realRoomHasTPropertyProprietor);

			if(realRoomBaseDao.selectRealRoomBySeqId(rrId).gettPropertyProprietorFId() == null) {
				RealRoom realRoom = new RealRoom();
				realRoom.setId(rrId);
				realRoom.settPropertyProprietorFId(pp.getId());
				realRoomBaseDao.updateRealRoom(realRoom);
			}
		}else {
			updCount += propertyProprietorDao.updatePropertyProprietor_for_NameIdentfyNoPhone(pp);
		}
		return pp.getId();
	}

	@Override
	public int deletePropertyProprietor(BigInteger rrId, BigInteger ppId) {
		int updCount = 0;
		updCount += propertyProprietorDao.deletePropertyProprietor(rrId, ppId);

		RealRoomHasTPropertyProprietor realRoomHasTPropertyProprietorQry = new RealRoomHasTPropertyProprietor();
		realRoomHasTPropertyProprietorQry.settRealRoomFId(rrId);
		List<RealRoomHasTPropertyProprietor> realRoomHasTPropertyProprietorList =
				realRoomHasTPropertyProprietorBaseDao.selectRealRoomHasTPropertyProprietorByCondition(MapConverter.toMap(realRoomHasTPropertyProprietorQry), false);
		if(realRoomHasTPropertyProprietorList.size() > 0) {//要删除房间关联的业主信息
			RealRoom rr = new RealRoom();
			rr.setId(rrId);
			int size = realRoomHasTPropertyProprietorList.size();
			if(ppId.equals(realRoomHasTPropertyProprietorList.get(size-1).gettPropertyProprietorFId())){
				rr.settPropertyProprietorFId(null);
			}else {
				rr.settPropertyProprietorFId(realRoomHasTPropertyProprietorList.get(size - 1).gettPropertyProprietorFId());
			}
			updCount += propertyProprietorDao.updateRealRoomPropertyPropietor(rr);
		}else {
			RealRoom rr = new RealRoom();
			rr.setId(rrId);
			rr.settPropertyProprietorFId(null);
			updCount += propertyProprietorDao.updateRealRoomPropertyPropietor(rr);
		}

		return updCount;
	}

	@Override
	public BigInteger savePropertyLessee(BigInteger rrId, PropertyLessee propertyLessee) {
		int updCount = 0;
		if(propertyLessee.getId() == null){
			propertyLessee.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_lessee));
			updCount += propertyLesseeBaseDao.insertPropertyLessee(propertyLessee);

			RealRoomHasTPropertyLessee realRoomHasTPropertyLessee = new RealRoomHasTPropertyLessee();
            realRoomHasTPropertyLessee.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_real_room_has_t_property_lessee));
			realRoomHasTPropertyLessee.settRealRoomFId(rrId);
			realRoomHasTPropertyLessee.settPropertyLesseeFId(propertyLessee.getId());
			realRoomHasTPropertyLesseeBaseDao.insertRealRoomHasTPropertyLessee(realRoomHasTPropertyLessee);
		}else {
			updCount += propertyLesseeBaseDao.updatePropertyLessee(propertyLessee);
		}
		return propertyLessee.getId();
	}

	@Override
	public int deletePropertyLessee(BigInteger rrId, BigInteger plId) {
		return propertyProprietorDao.deletePropertyLessee(rrId, plId);
	}
}
