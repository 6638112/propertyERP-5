package com.cnfantasia.sqpay.entity;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.commons.httpclient.HttpException;
//import org.junit.Test;
//import org.junit.Test;

import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.common.xml.JaxbXMLUtil;

public class Epay95Test1 {
	/**
	 * @throws JAXBException
	 */
	//@Test
	public void showMarshaller()  {
		Epay95 epay95 = new Epay95();
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccount("6225523663");
		accountInfo.setAccountKind("email");
		epay95.setAccountInfo(accountInfo);
		
		TransferInfo transferInfo = new TransferInfo("orderNO","bankName","province","city","branchName","accountName","bankCardNo","transAmount","phone",
				"remark","username","encryptionstr","mD5Infox") ;
		epay95.setTransferInfo(transferInfo);
		
		System.out.println(JaxbXMLUtil.convertToXml(epay95));
	}
	
	/**
	 * @throws JAXBException
	 */
	//@Test
	public void showUnMarshaller()  {
		String xmlString ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><epay95>    <AccountInfo>        <account>6225351122</account>        <accountKind>Mobile</accountKind>    </AccountInfo>    <TransferInfo>        <OrderNO>orderNO</OrderNO>        <bankName>bankName</bankName>        <province>province</province>        <city>city</city>        <branchName>branchName</branchName>        <accountName>accountName</accountName>        <bankCardNo>bankCardNo</bankCardNo>        <transAmount>transAmount</transAmount>        <phone>phone</phone>        <remark>remark</remark>        <username>username</username>        <encryptionstr>encryptionstr</encryptionstr>        <MD5Info>mD5Infox</MD5Info>    </TransferInfo></epay95>";
		Epay95 epay95  = JaxbXMLUtil.convertToJavaBean(xmlString, Epay95.class);
		System.out.println(epay95);
	}
	
	/**
	 * 乾多多API提现
	 * @throws HttpException
	 * @throws IOException
	 */
	//@Test
	public void pushWithdrawMsgToEpay() throws HttpException, IOException {

		Epay95 epay95 = new Epay95();
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccount("13145826351");
		accountInfo.setAccountKind("phone");
		epay95.setAccountInfo(accountInfo);
		
		TransferInfo transferInfo = new TransferInfo("orderNO-0002","招商银行","广东省","深圳市","科技园支行","文福强","6225 2522 3663 5852","0.11","13145826351",
				"测试数据","username","encryptionstr","mD5Infox") ;
		epay95.setTransferInfo(transferInfo);
		
		HttpUtil httpUtil = new HttpUtil();
		epay95.getTransferInfo().generatePushWithdrawEncryptionstr();;
		httpUtil.addParameter("EpayXMLstr", JaxbXMLUtil.convertToXml(epay95));
System.out.println(JaxbXMLUtil.convertToXml(epay95));
		//String returnString = httpUtil.post("https://www.moneymoremore.com/InterfaceTransfer.action");
		//String returnString = httpUtil.post("http://218.4.234.150:88/main/InterfaceTransfer.action");
		//String returnString = httpUtil.post("http://10.62.22.76/main/InterfaceTransfer.action");
		String returnString = httpUtil.post("http://218.4.234.150:10002/main/InterfaceTransfer.action");//王志勇本地
		System.out.println(returnString);
	}
	
	/**
	 * 乾多多API提现查询接口
	 * @throws HttpException
	 * @throws IOException
	 */
	//@Test
	public void qryWithdrawMsgToEpay() throws HttpException, IOException {
		Epay95 epay95 = new Epay95();
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccount("13145826351");
		accountInfo.setAccountKind("phone");
		epay95.setAccountInfo(accountInfo);
		
		TransferInfo transferInfo = new TransferInfo("orderNO-0003","招商银行","广东省","深圳市","科技园支行","文福强","6225 2522 3663 5852","0.11","13145826351",
				"测试数据","username","encryptionstr","mD5Infox") ;
		epay95.setTransferInfo(transferInfo);
		
		HttpUtil httpUtil = new HttpUtil();
		epay95.getTransferInfo().generateQryWithdrawEncryptionstr();;
		httpUtil.addParameter("EpayXMLstr", JaxbXMLUtil.convertToXml(epay95));
System.out.println(JaxbXMLUtil.convertToXml(epay95));
		//String returnString = httpUtil.post("https://www.moneymoremore.com/InterfaceTransfer.action");
		//String returnString = httpUtil.post("http://218.4.234.150:88/main/InterfaceTransfer.action");
		//String returnString = httpUtil.post("http://10.62.22.76/main/InterfaceTransfer.action");
		String returnString = httpUtil.post("http://218.4.234.150:10002/main/InquiryPortQMM.action");//王志勇本地
		System.out.println(returnString);
	}
}
