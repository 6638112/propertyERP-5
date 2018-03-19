package com.propertySoftwareDock.base.entity;

import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: RepairPushEntity
 * @Date: 2016-12-21 9:54
 * @Auther: kangduo
 * @Description: (推送到物业软件所需数据对象)
 */
public class RepairPushEntity implements Serializable{
    private static final long serialVersionUID = 6254334745388218836L;

    private Integer failCount;
    private PropertyRepair repair;
    private DredgeBill dredgeBill;
    private String repairTypeName;
    private RealRoomSoftwareInfo softwareInfo;
    //上传的图片
    private List<RequestFileEntity> picList;

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public PropertyRepair getRepair() {
        return repair;
    }

    public void setRepair(PropertyRepair repair) {
        this.repair = repair;
    }

    public RealRoomSoftwareInfo getSoftwareInfo() {
        return softwareInfo;
    }

    public void setSoftwareInfo(RealRoomSoftwareInfo softwareInfo) {
        this.softwareInfo = softwareInfo;
    }

    public List<RequestFileEntity> getPicList() {
        return picList;
    }

    public void setPicList(List<RequestFileEntity> picList) {
        this.picList = picList;
    }

    public String getRepairTypeName() {
        return repairTypeName;
    }

    public void setRepairTypeName(String repairTypeName) {
        this.repairTypeName = repairTypeName;
    }

    public DredgeBill getDredgeBill() {
        return dredgeBill;
    }

    public void setDredgeBill(DredgeBill dredgeBill) {
        this.dredgeBill = dredgeBill;
    }
}
