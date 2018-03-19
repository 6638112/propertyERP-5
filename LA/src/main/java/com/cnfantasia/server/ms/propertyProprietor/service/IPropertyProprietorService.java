package com.cnfantasia.server.ms.propertyProprietor.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.propertyProprietor.service.IPropertyProprietorBaseService;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;

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
	 * 保存业主信息：姓名，身份证号，手机号
	 * 
	 * @param ppEntity
	 */
	void updatePropertyProprietor_for_NameIdentfyNoPhone(PropertyProprietorEntity ppEntity);

	int getPPList_byUserId_forCount(Map<String, Object> paramMap);
}
