package com.propertySoftwareDock.jeez.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: JeezHouse
 * @Date: 2016-11-28 9:23
 * @Auther: kangduo
 * @Description:()
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "House")
public class JeezHouse {
    @XmlElement(name = "ID")
    private String id;
    @XmlElement(name = "Number")
    private String number;
    @XmlElement(name = "SimpleHouseNumber")
    private String simpleHouseNumber;
    @XmlElement(name = "BuildName")
    private String buildingName;
    @XmlElement(name = "UnitNumber")
    private String unitNumber;
    //1为业主，2为租户，3为住户
    @XmlElement(name = "CustomerType")
    private Integer customerType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSimpleHouseNumber() {
        return simpleHouseNumber;
    }

    public void setSimpleHouseNumber(String simpleHouseNumber) {
        this.simpleHouseNumber = simpleHouseNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    //极致houseId一致即可视为同一房间，此方法不可改，做Map的key有用
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JeezHouse jeezHouse = (JeezHouse) o;

        return id.equals(jeezHouse.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
