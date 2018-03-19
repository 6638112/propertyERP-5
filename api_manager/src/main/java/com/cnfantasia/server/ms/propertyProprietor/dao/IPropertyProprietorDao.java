package com.cnfantasia.server.ms.propertyProprietor.dao;

import com.cnfantasia.server.domainbase.propertyLessee.entity.PropertyLessee;
import com.cnfantasia.server.domainbase.propertyProprietor.dao.IPropertyProprietorBaseDao;
import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface IPropertyProprietorDao extends IPropertyProprietorBaseDao {

	int getPPList_byUserId_forCount(Map<String, Object> paramMap);

	/**
	 * 根据序列号查询(业主信息表)信息
	 * 
	 * @param id
	 * @return
	 */
	public PropertyProprietorEntity selectPropertyProprietorByRoomId(java.math.BigInteger id);

	String vierfyImportPayBill(List<PropertyProprietorEntity> ppList);

	/**
	 * 更新业主 姓名，身份证号，联系方式
	 * 
	 * @param propertyProprietor
	 * @return
	 */
	int updatePropertyProprietor_for_NameIdentfyNoPhone(PropertyProprietor propertyProprietor);

	List<PropertyProprietorEntity> getPPList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	int selectCountBeforeUpdateRoom(Map<String, Object> paramMap);

    List<PropertyProprietor> getPropertyProprietorListByRoomId(BigInteger rrId);

    List<PropertyLessee> getPropertyLesseeListByRoomId(BigInteger rrId);

    int deletePropertyProprietor(BigInteger rrId, BigInteger ppId);

    int deletePropertyLessee(BigInteger rrId, BigInteger ppId);

	/**
	 * update real_room's PropertyPropietor
	 * @param realRoom
	 * @return
	 */
	int updateRealRoomPropertyPropietor(RealRoom realRoom);
}
