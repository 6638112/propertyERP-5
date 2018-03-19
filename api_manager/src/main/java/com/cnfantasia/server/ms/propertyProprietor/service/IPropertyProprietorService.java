package com.cnfantasia.server.ms.propertyProprietor.service;

import com.cnfantasia.server.domainbase.propertyLessee.entity.PropertyLessee;
import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;
import com.cnfantasia.server.domainbase.propertyProprietor.service.IPropertyProprietorBaseService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface IPropertyProprietorService extends IPropertyProprietorBaseService {

	List<PropertyProprietorEntity> getPPList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
	/**
	 * 根据序列号查询(业主信息表)信息
	 * 
	 * @param id
	 * @return
	 */
	public PropertyProprietorEntity getPropertyProprietorByRoomId(java.math.BigInteger id);

	String saveImportPPEntity(List<PropertyProprietorEntity> ppList);

	/**
	 * 保存业主信息：姓名，身份证号，手机号<br>
	 * 保存房间信息：单元号，房号
	 * 
	 * @param rrId
	 *            房间Id
	 * @param rrId2
	 * @param roomNumber
	 * @param unitName
	 * 
	 * @param ppEntity
	 *            业主信息
	 * @return 返回更新结果
	 */
	String updatePropertyProprietor_and_realRoom(RealRoom rr);

	int getPPList_byUserId_forCount(Map<String, Object> paramMap);

	/**
	 * 保存新增的房间与业主信息
	 * 
	 * @param rr
	 *            房间
	 * @param pp
	 *            业主
	 * @return 保存结果
	 */
	String saveAddNew(RealRoom rr, PropertyProprietor pp);

	/**
	 * 门牌自动验证
	 * 
	 * @param rr
	 */
	void doAutoRoomValidateFromDB(RealRoom rr);

	/**
	 * 根据房间id获得所有业主信息列表
	 * @param rrId 房间ID
	 * @return
	 */
    List<PropertyProprietor> getPropertyProprietorListByRoomId(BigInteger rrId);

	/**
	 * 根据房间id获得所有租户信息列表
	 * @param rrId 房间ID
	 * @return
	 */
	List<PropertyLessee> getPropertyLesseeListByRoomId(BigInteger rrId);

	/**
	 * 保存业主信息
	 * @param rrId
	 * @param pp
	 * @return
	 */
	BigInteger savePropertyProprietor(BigInteger rrId, PropertyProprietor pp);

	/**
	 * 删除业主信息
	 * @param rrId
	 * @param ppId
	 * @return
	 */
	int deletePropertyProprietor(BigInteger rrId, BigInteger ppId);

	/**
	 * 保存租户信息
	 * @param rrId
	 * @param pp
	 * @return
	 */
	BigInteger savePropertyLessee(BigInteger rrId, PropertyLessee propertyLessee);

	/**
	 * 删除租客信息
	 * @param ppId
	 * @return
	 */
	int deletePropertyLessee(BigInteger rrId, BigInteger ppId);
}
