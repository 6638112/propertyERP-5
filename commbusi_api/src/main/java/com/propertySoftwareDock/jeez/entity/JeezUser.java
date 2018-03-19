package com.propertySoftwareDock.jeez.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: JeezUser
 * @Date: 2016-11-28 9:15
 * @Auther: kangduo
 * @Description:()
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Customer")
public class JeezUser {
    @XmlElement(name="ID")
    private String id;
    private String clientCode;
    @XmlElement(name="Name")
    private String name;
    private Integer sexId;
    @XmlElement(name="Mobile")
    private String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSexId() {
        return sexId;
    }

    public void setSexId(Integer sexId) {
        this.sexId = sexId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
