package com.cnfantasia.server.ms.payBill.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: feeCollectionViewEntity
 * @Date: 2017-06-08 16:07
 * @Auther: yanghua
 * @Description:(收费情况汇总表)
 */
public class FeeCollectionViewEntity {
    private BigInteger propertyCompanyId;
    private String propertyCompanyName;
    private Integer rowspanCountsPc;//前端需要合并的单元格数（物业公司、管理处）
    /**管理处列表*/
    private List<PropertyManagementReportEntity> propertyManagementReportEntityList;

    public BigInteger getPropertyCompanyId() {
        return propertyCompanyId;
    }

    public void setPropertyCompanyId(BigInteger propertyCompanyId) {
        this.propertyCompanyId = propertyCompanyId;
    }

    public String getPropertyCompanyName() {
        return propertyCompanyName;
    }

    public void setPropertyCompanyName(String propertyCompanyName) {
        this.propertyCompanyName = propertyCompanyName;
    }

    public List<PropertyManagementReportEntity> getPropertyManagementReportEntityList() {
        return propertyManagementReportEntityList;
    }

    public void setPropertyManagementReportEntityList(List<PropertyManagementReportEntity> propertyManagementReportEntityList) {
        this.propertyManagementReportEntityList = propertyManagementReportEntityList;
    }

    public Integer getRowspanCountsPc() {
        return rowspanCountsPc;
    }

    public void setRowspanCountsPc(Integer rowspanCountsPc) {
        this.rowspanCountsPc = rowspanCountsPc;
    }
    
    public Integer getPmCount() {
    	if(propertyManagementReportEntityList == null || propertyManagementReportEntityList.size() == 0) {
    		return 1;
    	}
    	int count = 0;
    	for(PropertyManagementReportEntity pmEntity : propertyManagementReportEntityList) {
    		count += pmEntity.getGbCount();
    	}
    	return count;
    }
}
