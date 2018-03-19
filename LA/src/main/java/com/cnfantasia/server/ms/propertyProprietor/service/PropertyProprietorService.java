package com.cnfantasia.server.ms.propertyProprietor.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domainbase.propertyProprietor.service.PropertyProprietorBaseService;
import com.cnfantasia.server.ms.propertyProprietor.dao.IPropertyProprietorDao;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;

public class PropertyProprietorService extends PropertyProprietorBaseService implements IPropertyProprietorService {
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
	public void updatePropertyProprietor_for_NameIdentfyNoPhone(PropertyProprietorEntity ppEntity) {
		propertyProprietorDao.updatePropertyProprietor_for_NameIdentfyNoPhone(ppEntity);
	}

	@Override
	public int getPPList_byUserId_forCount(Map<String, Object> paramMap) {
		return propertyProprietorDao.getPPList_byUserId_forCount(paramMap);
	}

	@Override
	public List<PropertyProprietorEntity> getPPList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return propertyProprietorDao.getPPList_byUserId_forPage(curPageIndex, pageSize, paramMap);
	}
}
