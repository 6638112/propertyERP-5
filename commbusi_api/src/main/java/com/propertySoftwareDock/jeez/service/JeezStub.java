package com.propertySoftwareDock.jeez.service;

import com.cnfantasia.server.api.plotproperty.entity.PayBillEntity;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.common.xml.JaxbXMLUtil;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.ms.payBill.constant.PayBillDict;
import com.propertySoftwareDock.jeez.entity.*;
import com.propertySoftwareDock.jeez.util.JeezUtil;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: JeezStub
 * @Date: 2016-11-30 10:49
 * @Auther: kangduo
 * @Description: (请求方法调用)
 */
public class JeezStub {

    private static final String TargetNamspace = "http://tempuri.org/";
    static final String SuccessFlag = "1";

    public static boolean payByBills(String houseId, String customerId, String dbCode, String orderNo,
                               JeezFeeEntity jeezFeeEntity, KeyPair keyPair, String serverAddr) throws Exception {
        PublicKey publicKey = keyPair.getPublic();
        String timestamp = getEncryptionKey(keyPair, serverAddr);
        JeezXml xml = new JeezXml();
        xml.setCount(12);
        List<String> paramList = new ArrayList<String>();
        paramList.add(JeezUtil.getValueByPublicKey(houseId, publicKey));
        paramList.add("");
        paramList.add("");
//      paramList.add(JeezUtil.getValueByPublicKey(dbCode, publicKey));
        paramList.add(dbCode);
        paramList.add("");
        paramList.add(JeezUtil.getValueByPublicKey(jeezFeeEntity.getTotalAmount().toString(), publicKey));
        paramList.add("");
        paramList.add(JeezUtil.getValueByPublicKey("1", publicKey));
        paramList.add(JeezUtil.getValueByPublicKey(timestamp, publicKey));
        paramList.add(JeezUtil.getValueByPublicKey("0", publicKey));//customerId，文档中要求置0
        paramList.add("");
        paramList.add(JeezUtil.getValueByPublicKey("16", publicKey));
        paramList.add(JeezUtil.getValueByPublicKey("jfq_" + orderNo, publicKey));
        paramList.add(JeezUtil.getValueByPublicKey("解放区缴费", publicKey));
        xml.setParamList(paramList);
        List<String> extInfoList = new ArrayList<String>(jeezFeeEntity.getFeeItems().size());
        int idx = 0;
        for (JeezSingleFeeItem jeezSingleFeeItem : jeezFeeEntity.getFeeItems()) {
            JeezPayByBillItem jzItem = new JeezPayByBillItem();
            jzItem.setPayNo("jfq_" + orderNo + "_" + idx++);
            jzItem.setArrearNo(jeezSingleFeeItem.getBillNo());
            jzItem.setAmount(jeezSingleFeeItem.getAmount().toString());
            String info = jzItem.toString();
            System.out.println(info);
//            extInfoList.add(JeezUtil.getValueByPublicKey(info, publicKey));
            extInfoList.add(info);
        }
        xml.setExtInfoList(extInfoList);
        System.out.println("物业软件对接：销账发送数据" + JaxbXMLUtil.convertToXml(xml));
        String result = invokeCall(serverAddr, JeezDict.ReqUrl.To_Pay_Bill, true, new Object[]{JaxbXMLUtil.convertToXml(xml)}, keyPair.getPrivate(), 9);
        System.out.println("物业软件对接：销账返回数据" + result);
        Document doc = DocumentHelper.parseText(result);
        return SuccessFlag.equals(JeezUtil.getElementValue(doc, "Flag"));
    }

    public static String getPayBill(String houseId, String customerId, String DbCode, KeyPair keyPair, String serverAddr) throws Exception{
        JeezXml xml = new JeezXml();
        xml.setCount(7);
        List<String> paramList = new ArrayList<String>();
        paramList.add(JeezUtil.getValueByPublicKey(houseId, keyPair.getPublic()));
        paramList.add("");
        paramList.add("");
//        paramList.add(JeezUtil.getValueByPublicKey(DbCode, keyPair.getPublic()));
        paramList.add(DbCode);
        paramList.add("");
        paramList.add("");
        paramList.add(JeezUtil.getValueByPublicKey(customerId, keyPair.getPublic()));
        xml.setParamList(paramList);
        return invokeCall(serverAddr, JeezDict.ReqUrl.Get_Pay_Bill, true, new Object[]{JaxbXMLUtil.convertToXml(xml)}, keyPair.getPrivate(), 4);
    }

    public static String getRoomInfo(String customerId, String DbCode, KeyPair keyPair, String serverAddr) throws Exception{
        JeezXml xml = new JeezXml();
        xml.setCount(6);
        List<String> paramList = new ArrayList<String>();
        paramList.add("");
        paramList.add("");
        paramList.add("");
        paramList.add("");
//      paramList.add(JeezUtil.getValueByPublicKey(DbCode, keyPair.getPublic()));
        paramList.add(DbCode);
        paramList.add(JeezUtil.getValueByPublicKey(customerId, keyPair.getPublic()));
        xml.setParamList(paramList);
        return invokeCall(serverAddr, JeezDict.ReqUrl.Get_Room_Info, true, new Object[]{JaxbXMLUtil.convertToXml(xml)}, keyPair.getPrivate(), 4);
    }

    public static String getUsersInfo(String communityId, String qryNum, String minId, String DbCode, KeyPair keyPair, String serverAddr) throws Exception{
        JeezXml xml = new JeezXml();
        xml.setCount(5);
        List<String> paramList = new ArrayList<String>();
        paramList.add(JeezUtil.getValueByPublicKey(qryNum, keyPair.getPublic()));
        paramList.add(JeezUtil.getValueByPublicKey(minId, keyPair.getPublic()));
//      paramList.add(JeezUtil.getValueByPublicKey(DbCode, keyPair.getPublic()));
        paramList.add(DbCode);
        paramList.add("");
        paramList.add(JeezUtil.getValueByPublicKey(communityId, keyPair.getPublic()));
        xml.setParamList(paramList);
        return invokeCall(serverAddr, JeezDict.ReqUrl.Get_Users_Info, true, new Object[]{JaxbXMLUtil.convertToXml(xml)}, keyPair.getPrivate(), 4);
    }

    public static String pushRepair(String serverAddr, String houseId, String expectTime, String content, String linkName,
                                    String linkPhone, String dbCode, String customerId, String image1, String image2, String image3, KeyPair keyPair) throws Exception {
        JeezXml xml = new JeezXml();
        content = content.length() > 50 ? content.substring(0, 50) + "..." : content;
        xml.setCount(15);
        List<String> paramList = new ArrayList<String>();
        paramList.add(JeezUtil.getValueByPublicKey(houseId, keyPair.getPublic()));
        paramList.add("");
        paramList.add("");
        paramList.add(JeezUtil.getValueByPublicKey(expectTime, keyPair.getPublic()));
//        paramList.add(JeezUtil.getValueByPublicKey("网上报修", keyPair.getPublic()));
        paramList.add("");
        paramList.add(JeezUtil.getValueByPublicKey(content, keyPair.getPublic()));
//      paramList.add(JeezUtil.getValueByPublicKey(dbCode, keyPair.getPublic()));
        paramList.add(dbCode);
        paramList.add("");
        paramList.add("");
        paramList.add(JeezUtil.getValueByPublicKey(linkName, keyPair.getPublic()));
        paramList.add(JeezUtil.getValueByPublicKey(linkPhone, keyPair.getPublic()));
        paramList.add("");
        paramList.add("");
        paramList.add(JeezUtil.getValueByPublicKey("解放区下单", keyPair.getPublic()));
        paramList.add(JeezUtil.getValueByPublicKey(customerId, keyPair.getPublic()));
        paramList.add(JeezUtil.getValueByPublicKey("1", keyPair.getPublic()));
        if (image1 != null) {
            paramList.add("JPG|||" + image1);
        } else {
            paramList.add("");
        }
        if (image2 != null) {
            paramList.add("JPG|||" + image2);
        } else {
            paramList.add("");
        }
        if (image2 != null) {
            paramList.add("JPG|||" + image3);
        } else {
            paramList.add("");
        }
        xml.setParamList(paramList);
        return invokeCall(serverAddr, JeezDict.ReqUrl.Push_Property_Repair, true, new Object[]{JaxbXMLUtil.convertToXml(xml)}, keyPair.getPrivate(), 9);
    }

    public static String getRepairDetail(String serverAddr, String houseId, String billNo, String dbCode, String customerId, KeyPair keyPair) throws Exception {
        JeezXml xml = new JeezXml();
        xml.setCount(10);
        List<String> paramList = new ArrayList<String>();
        paramList.add(JeezUtil.getValueByPublicKey(houseId, keyPair.getPublic()));
        paramList.add("");
        paramList.add("");
        paramList.add("");
        paramList.add("");
        paramList.add(JeezUtil.getValueByPublicKey(billNo, keyPair.getPublic()));
//        paramList.add(JeezUtil.getValueByPublicKey(dbCode, keyPair.getPublic()));
        paramList.add(dbCode);
        paramList.add("");
        paramList.add("");
        paramList.add(JeezUtil.getValueByPublicKey(customerId, keyPair.getPublic()));
        xml.setParamList(paramList);
        return invokeCall(serverAddr, JeezDict.ReqUrl.Property_Repair_List, true, new Object[]{JaxbXMLUtil.convertToXml(xml)}, keyPair.getPrivate(), 9);
    }

    /**
     * 获取销账用的时间戳，无参数
     * @return 如 2016/11/24 16:24:12
     * @throws Exception
     */
    public static String getEncryptionKey(KeyPair keyPair, String serverAddr) throws Exception {
        return invokeCall(serverAddr, JeezDict.ReqUrl.Get_Encryption_Key, false, null, keyPair.getPrivate(), 4);
    }

    private static String invokeCall(String serverAddr, String reqUrl, boolean hasParam, Object[] param, PrivateKey key, int timeOutSecond) throws Exception {

        HttpUtil httpUtil = new HttpUtil();
        httpUtil.setTIMEOUT_TIME(1000);
        int maxTry = 3;
        while (maxTry > 0) {
            boolean normal = true;
            try {
                httpUtil.get(serverAddr);
            } catch (Exception e) {
                normal = false;
                e.printStackTrace();
            }
            if (normal) {
                break;
            }
            maxTry--;
        }

        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setUseSOAPAction(true);
        call.setTargetEndpointAddress(new URL(serverAddr));
        call.setOperationName(new QName(TargetNamspace, reqUrl));
        call.setSOAPActionURI(TargetNamspace + reqUrl);
        if (hasParam) {
            call.addParameter(new QName(TargetNamspace, "Request"),
                    new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, ParameterMode.IN);
        }
        //返回参数类型
        call.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class);
        call.setTimeout(timeOutSecond * 1000);
        String result = (String) call.invoke(param);
        try {
        	Document doc = DocumentHelper.parseText(result);
            String encrypt = JeezUtil.getElementValue(doc, "Encrypt");
            String unEncrypt = JeezUtil.getElementValue(doc, "UnEncrypt");

            return JeezUtil.getValueByPrivateKey(encrypt, unEncrypt, key);
        } catch (Exception e) {
			return result;
		}
    }

    static PayBillEntity getPayBillEntityBySoftwareFee(JeezFeeEntity jeezFeeEntity, RealRoomEntity realRoomEntity, BigInteger softwareFeeId) {
        //外部部分
        PayBillEntity payBillEntity = new PayBillEntity();
        payBillEntity.setDataFromType(PayBillDict.DataFromType.fromSoftware);
        payBillEntity.settRealroomSoftwareFeeFId(softwareFeeId);
        //payBill部分
        payBillEntity.setAmount(jeezFeeEntity.getTotalAmount().multiply(BigDecimal.valueOf(100)).longValue());
        payBillEntity.setBillTypeName("物业费");
        payBillEntity.setIsPay(2);//未缴费
        payBillEntity.setFinanceStatus(0);//未抵扣
        payBillEntity.setBillMonthStart(jeezFeeEntity.getFeeItems().get(0).getCalcStartDate());
        //propertyFeeDetailList部分
        List<JeezSingleFeeItem> feeItemList = jeezFeeEntity.getFeeItems();
        List<PropertyFeeDetail> propertyFeeDetailList = new ArrayList<PropertyFeeDetail>(feeItemList.size());
        for (JeezSingleFeeItem jeezSingleFeeItem : feeItemList) {
            PropertyFeeDetail propertyFeeDetail = jeezSingleFeeItem.turnToPropertyFeeDetail();
            propertyFeeDetailList.add(propertyFeeDetail);
        }
        payBillEntity.setPropertyFeeDetailList(propertyFeeDetailList);
        //realRoomEntity部分
        payBillEntity.setRealRoomEntity(realRoomEntity);
        return payBillEntity;
    }
}
