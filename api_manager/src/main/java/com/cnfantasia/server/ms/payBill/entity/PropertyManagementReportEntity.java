package com.cnfantasia.server.ms.payBill.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: propertyManagementEntity
 * @Date: 2017-06-08 16:13
 * @Auther: yanghua
 * @Description:(报表：管理处)
 */
public class PropertyManagementReportEntity {
    private BigInteger propertyManagementId;
    private String propertyManagementName;
    private Integer rowspanCountsPM;//前端需要合并的单元格数（物业公司、管理处
    /**小区列表*/
    private List<GroupBuildingReportEntity> groupBuildingReportEntityList;

    public BigInteger getPropertyManagementId() {
        return propertyManagementId;
    }

    public void setPropertyManagementId(BigInteger propertyManagementId) {
        this.propertyManagementId = propertyManagementId;
    }

    public String getPropertyManagementName() {
        return propertyManagementName;
    }

    public void setPropertyManagementName(String propertyManagementName) {
        this.propertyManagementName = propertyManagementName;
    }

    public List<GroupBuildingReportEntity> getGroupBuildingReportEntityList() {
        return groupBuildingReportEntityList;
    }

    public void setGroupBuildingReportEntityList(List<GroupBuildingReportEntity> groupBuildingReportEntityList) {
        this.groupBuildingReportEntityList = groupBuildingReportEntityList;
    }

    public Integer getRowspanCountsPM() {
        return rowspanCountsPM;
    }

    public void setRowspanCountsPM(Integer rowspanCountsPM) {
        this.rowspanCountsPM = rowspanCountsPM;
    }
    
    public Integer getGbCount() {
    	if(groupBuildingReportEntityList == null) {
    		return 1;
    	}
    	int count = 0;
    	for(GroupBuildingReportEntity gbEntity : groupBuildingReportEntityList) {
    		count += gbEntity.getPfCount();
    	}
    	return count;
    }
    
    
}
