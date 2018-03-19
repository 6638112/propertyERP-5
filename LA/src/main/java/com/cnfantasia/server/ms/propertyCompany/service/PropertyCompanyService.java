package com.cnfantasia.server.ms.propertyCompany.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.propertyCompany.service.PropertyCompanyBaseService;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.propertyCompany.dao.IPropertyCompanyDao;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyEntity;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class PropertyCompanyService extends PropertyCompanyBaseService implements IPropertyCompanyService {
	IPropertyCompanyDao propertyCompanyDao;

	private IUuidManager uuidManager;
	private IDualDao dualDao;

	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	public void setPropertyCompanyDao(IPropertyCompanyDao propertyProprietorDao) {
		this.propertyCompanyDao = propertyProprietorDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	/**
	 * 根据序列号查询(物业公司 )信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCompanyEntity selectPropertyCompanyByOmsUserId(BigInteger id) {
		return propertyCompanyDao.selectPropertyCompanyByOmsUserId(id);
	}

	@Override
	public List<GroupBuildingSimpleEntity> select_gbList_ByOmsUserId(BigInteger id) {
		return propertyCompanyDao.select_gbList_ByOmsUserId(id);
	}

	@Override
	public void savePropertyCompany(PropertyCompany pcEntity) {
		if (pcEntity.getId() == null) {//新增
			pcEntity.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_company));
			pcEntity.setAdminId(UserContext.getCurrUser() != null ? UserContext.getCurrUser().getId() : null);
			propertyCompanyDao.insertPropertyCompany(pcEntity);
		} else {//更新
			propertyCompanyDao.updatePropertyCompany(pcEntity);
		}
	}

	@Override
	public int getPCList4admin_forCount(Map<String, Object> paramMap) {
		return propertyCompanyDao.getPCList4admin_forCount(paramMap);
	}

	@Override
	public List<PropertyCompanyEntity> getPCList4admin_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return propertyCompanyDao.getPCList4admin_forPage(curPageIndex, pageSize, paramMap);
	}

	@Override
	public List<Map<String, Object>> select_gbrList_ByPCId(BigInteger pcId) {
		return propertyCompanyDao.select_gbrList_ByPCId(pcId);
	}

}
