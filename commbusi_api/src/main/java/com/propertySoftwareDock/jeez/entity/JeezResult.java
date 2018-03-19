package com.propertySoftwareDock.jeez.entity;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @ClassName: JeezUserResult
 * @Date: 2016-11-28 9:11
 * @Auther: kangduo
 * @Description:()
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Result")
public class JeezResult {
    @XmlElement(name="Flag")
    private Integer flag;

    @XmlElement(name="MaxIndex")
    private Integer maxIndex;

    @XmlElementWrapper(name = "Customers")
    @XmlElement(name="Customer")
    private List<JeezUser> customers;

    @XmlElementWrapper(name = "Houses")
    @XmlElement(name="House")
    private List<JeezHouse> houses;

    @XmlElementWrapper(name = "List")
    @XmlElement(name="Item")
    private List<JeezRepairDetail> repairDetails;

    @XmlElement(name="ErrorCode")
    private String errorCode;

    @XmlElement(name="Message")
    private String message;

    public Integer getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(Integer maxIndex) {
        this.maxIndex = maxIndex;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<JeezUser> getCustomers() {
        return customers;
    }

    public void setCustomers(List<JeezUser> customers) {
        this.customers = customers;
    }

    public List<JeezHouse> getHouses() {
        return houses;
    }

    public void setHouses(List<JeezHouse> houses) {
        this.houses = houses;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<JeezRepairDetail> getRepairDetails() {
        return repairDetails;
    }

    public void setRepairDetails(List<JeezRepairDetail> repairDetails) {
        this.repairDetails = repairDetails;
    }
}
