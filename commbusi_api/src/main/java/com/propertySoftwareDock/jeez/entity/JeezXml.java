package com.propertySoftwareDock.jeez.entity;

import com.cnfantasia.server.common.xml.JaxbXMLUtil;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: JeezXml
 * @Date: 2016-11-26 15:05
 * @Auther: kangduo
 * @Description:()
 */
@XmlRootElement(name = "Request")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"count", "paramList", "extInfoList"})
public class JeezXml {

    @XmlElement(name = "Count")
    private int count;
    @XmlElement(name = "Param")
    private List<String> paramList;
    @XmlElementWrapper(name = "Items")
    @XmlElement(name = "Item")
    private List<String> extInfoList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getParamList() {
        return paramList;
    }

    public void setParamList(List<String> paramList) {
        this.paramList = paramList;
    }

    public List<String> getExtInfoList() {
        return extInfoList;
    }

    public void setExtInfoList(List<String> extInfoList) {
        this.extInfoList = extInfoList;
    }

    public static void main(String[] args) {
        JeezXml xml = new JeezXml();
        xml.setCount(5);
        List<String> param = new ArrayList<String>();
        param.add("");
        param.add("dlkja");
        xml.setParamList(param);
        List<String> items = new ArrayList<String>();
        JeezPayByBillItem item = new JeezPayByBillItem();
        item.setPayNo("bill001");
        item.setArrearNo("001");
        items.add(item.toString());
        item = new JeezPayByBillItem();
        item.setPayNo("bill002");
        item.setArrearNo("002");
        items.add(item.toString());
        xml.setExtInfoList(items);
        System.out.println(JaxbXMLUtil.convertToXml(xml));
    }

}
