package com.kingdee.eas.payBill;

import java.net.URL;

import com.kingdee.eas.client.WSContext;
import com.kingdee.eas.login.EASLoginProxy;
import com.kingdee.eas.login.EASLoginProxyServiceLocator;

public class TestWEB {
	
	final static String address="http://210.75.8.38:8888"; 
//	final static String address="http://191.168.1.5:8888";
	
	 /* static String bizAccountStr="[{\"appDate\":\"2016-06-09 12:34:56\",\"applier\":\"105798\",\"applierCompany\":\"752DC01\"," +
		"\"applierDept\":\"752DC.12\",\"attachment\":[{\"attachId\":\"20160526.96226427\",\"file\":\"c2ZzYWRmZGFzZmRh6PO12bjU6PO12bjU\",\"fileName\":\"test\"," +
		"\"isShared\":false,\"simpleName\":\"txt\",\"size\":\"0 KB\",\"sizeInByte\":126,\"storageType\":\"0\"}],\"attachmentAccount\":1," +
		"\"billStatus\":\"保存\",\"bizAccountEntry\":[{\"budgetNumber\":\"ZJJH02\",\"amount\":123,\"approvedAmount\":123,\"comment\":\"备注0-0\"," +
		"\"expenseType\":\"110911.010\",\"happenTime\":\"2015-05-09 12:34:56\",\"operationType\":\"1110\",\"originalAmount\":123," +
		"\"purpose\":\"费用说明0-0\"},{\"budgetNumber\":\"ZJJH02\",\"amount\":123,\"approvedAmount\":123,\"comment\":\"备注0-1\",\"expenseType\":\"110911.010\"," +
		"\"happenTime\":\"2015-05-09 12:34:56\",\"operationType\":\"1110\",\"originalAmount\":123,\"purpose\":\"费用说明0-1\"}]," +
		"\"cause\":\"事由0\",\"currency\":\"BB01\",\"desc\":\"备注0\",\"payCompany\":\"752DC01\"," +
		"\"payMode\":\"31\",\"position\":\"000DC01.04.01-04\",\"prior\":\"HIGH\",\"revAccount\":\"1234567\",\"revDept\":\"花样年测试\"," +
		"\"revOpenBank\":\"收款方开户行0\",\"isonlyperson\":\"1\",\"srcBillNumber\":\"源单编码0\",\"theme\":\"主题0\",\"biller\":\"anh\"},{\"appDate\":\"2016-06-09 12:34:56\",\"applier\":\"105798\",\"applierCompany\":\"752DC01\"," +
		"\"applierDept\":\"752DC.12\",\"attachment\":[{\"attachId\":\"20160526.96226427\",\"file\":\"c2ZzYWRmZGFzZmRh6PO12bjU6PO12bjU\",\"fileName\":\"test\"," +
		"\"isShared\":false,\"simpleName\":\"txt\",\"size\":\"0 KB\",\"sizeInByte\":126,\"storageType\":\"0\"}],\"attachmentAccount\":1," +
		"\"billStatus\":\"保存\",\"bizAccountEntry\":[{\"budgetNumber\":\"ZJJH02\",\"amount\":123,\"approvedAmount\":123,\"comment\":\"备注0-0\"," +
		"\"expenseType\":\"110911.010\",\"happenTime\":\"2015-05-09 12:34:56\",\"operationType\":\"1110\",\"originalAmount\":123," +
		"\"purpose\":\"费用说明0-0\"},{\"budgetNumber\":\"ZJJH02\",\"amount\":123,\"approvedAmount\":123,\"comment\":\"备注0-1\",\"expenseType\":\"110911.010\"," +
		"\"happenTime\":\"2015-05-09s 12:34:56\",\"operationType\":\"1110\",\"originalAmount\":123,\"purpose\":\"费用说明0-1\"}]," +
		"\"cause\":\"事由0\",\"currency\":\"BB01\",\"desc\":\"备注0\",\"payCompany\":\"752DC01\"," +
		"\"payMode\":\"31\",\"position\":\"000DC01.04.01-04\",\"prior\":\"HIGH\",\"revAccount\":\"1234567\",\"revDept\":\"花样年测试\"," +
		"\"revOpenBank\":\"收款方开户行0\",\"isonlyperson\":\"1\",\"srcBillNumber\":\"源单编码0\",\"theme\":\"主题0\",\"biller\":\"anh\"}]";
	  */
	
	  static String bizAccountStr ="[{\"appDate\":\"2016-10-11 14:12:48\",\"applier\":\"HYNZGJT0290\",\"applierCompany\":\"HYN_LLL\",\"applierDept\":\"HYN_LLL_01_01\",\"attachment\":[{\"attachId\":\"20161017.31237145\",\"file\":\"0M8R4KGxGuEAAAAAAAAAAAAAAAAAAAAAOwADAP7/CQAGAAAAAAAAAAAAAAABAAAAAAAAAAAAAAAAEAAABwAAAAEAAAD+////AAAAAAgAAAD///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////9SAG8AbwB0ACAARQBuAHQAcgB5AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFgAFAf//////////AQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAADAAAAAAAAFcAbwByAGsAYgBvAG8AawAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASAAIB////////////////AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPQLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAAAD///////////////8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA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ABgAAAD9AAoAAQAEABUAGQAAAP0ACgABAAUAFQAaAAAA/QAKAAEABgAVABoAAAD9AAoAAQAHABUAGgAAAP0ACgABAAgAFQAaAAAA/QAKAAEACQAVABsAAAD9AAoAAQAKABUAGgAAAP0ACgABAAsAFQAcAAAA/QAKAAEADAAVAB0AAAD9AAoAAQANABUAHgAAAP0ACgABAA4AFQAfAAAA/QAKAAEADwAVACAAAAD9AAoAAQAQABUAIQAAAP0ACgABABEAFQAiAAAA/QAKAAEAEgAVACMAAAD9AAoAAQATABUAJAAAAP0ACgABABQAFQAlAAAAvgAUAAIAAAAVABUAFQAVABUAFQAVAAYA/QAKAAIABwAVABoAAAD9AAoAAgAIABUAGgAAAP0ACgACAAkAFQAbAAAA/QAKAAIACgAVABoAAAD9AAoAAgALABUAHAAAAP0ACgACAAwAFQAdAAAA/QAKAAIADQAVAB4AAAD9AAoAAgAOABUAHwAAAP0ACgACAA8AFQAgAAAA/QAKAAIAEAAVACYAAAC+AA4AAgARABUAFQAVABUAFADXAAoAPgMAACgAJgEmAT4CEgC2BgAAAABAAAAAAAAAAAAAAAAdAA8AAwAAAAAAAAEAAAAAAAAACgAAAP///////////////wEAAAACAAAAAwAAAAQAAAAFAAAABgAAAAcAAAAIAAAACQAAAAoAAAALAAAADAAAAA0AAAAOAAAADwAAABAAAAARAAAAEgAAABMAAAAUAAAAFQAAABYAAAAXAAAAGAAAABkAAAAaAAAAGwAAABwAAAAdAAAAHgAAAB8AAAAgAAAAIQAAACIAAAAjAAAAJAAAACUAAAAmAAAAJwAAACgAAAApAAAAKgAAACsAAAAsAAAALQAAAC4AAAAvAAAA/v///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////v///wIAAAADAAAABAAAAAUAAAAGAAAA/v////7////+//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8=\",\"fileName\":\"代收供应商费用明细\",\"isShared\":false,\"simpleName\":\"xls\",\"size\":\"5120\",\"sizeInByte\":5120,\"storageType\":\"0\"}],\"billStatus\":\"提交\",\"biller\":\"wangchong\",\"bizAccountEntry\":[{\"amount\":0.6999999999999999555910790149937383830547332763671875,\"approvedAmount\":0.6999999999999999555910790149937383830547332763671875,\"budgetNumber\":\"LLL.02\",\"comment\":\"ra24685978996180280app5\",\"expenseType\":\"Al.01.02\",\"happenTime\":\"2016-10-11 14:12:48\",\"operationType\":\"AL.01\",\"originalAmount\":0.6999999999999999555910790149937383830547332763671875,\"purpose\":\"超市供应商（实收）\"}],\"cause\":\"报销代收供应商费用\",\"currency\":\"BB01\",\"desc\":\"附件为明细\",\"isonlyperson\":\"1\",\"payCompany\":\"HYN_LLL\",\"payMode\":\"00\",\"position\":\"HYN_LLL_01.01.002\",\"prior\":\"LOW\",\"revAccount\":\"6222024000012345678\",\"revDept\":\"蒙蒙\",\"revOpenBank\":\"中国银行西乡支行\",\"srcBillNumber\":\"ra24685978996180280app5\",\"theme\":\"报销单\"},{\"appDate\":\"2016-10-11 14:12:48\",\"applier\":\"HYNZGJT0290\",\"applierCompany\":\"HYN_LLL\",\"applierDept\":\"HYN_LLL_01_01\",\"attachment\":[{\"attachId\":\"20161017.48437710\",\"file\":\"0M8R4KGxGuEAAAAAAAAAAAAAAAAAAAAAOwADAP7/CQAGAAAAAAAAAAAAAAABAAAAAAAAAAAAAAAAEAAABwAAAAEAAAD+////AAAAAAgAAAD///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////9SAG8AbwB0ACAARQBuAHQAcgB5AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFgAFAf//////////AQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAADAAAAAAAAFcAbwByAGsAYgBvAG8AawAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASAAIB////////////////AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPQLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAAAD///////////////8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA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ABgAAAD9AAoAAQAEABUAGQAAAP0ACgABAAUAFQAaAAAA/QAKAAEABgAVABoAAAD9AAoAAQAHABUAGgAAAP0ACgABAAgAFQAaAAAA/QAKAAEACQAVABsAAAD9AAoAAQAKABUAGgAAAP0ACgABAAsAFQAcAAAA/QAKAAEADAAVAB0AAAD9AAoAAQANABUAHgAAAP0ACgABAA4AFQAfAAAA/QAKAAEADwAVACAAAAD9AAoAAQAQABUAIQAAAP0ACgABABEAFQAiAAAA/QAKAAEAEgAVACMAAAD9AAoAAQATABUAJAAAAP0ACgABABQAFQAlAAAAvgAUAAIAAAAVABUAFQAVABUAFQAVAAYA/QAKAAIABwAVABoAAAD9AAoAAgAIABUAGgAAAP0ACgACAAkAFQAbAAAA/QAKAAIACgAVABoAAAD9AAoAAgALABUAHAAAAP0ACgACAAwAFQAdAAAA/QAKAAIADQAVAB4AAAD9AAoAAgAOABUAHwAAAP0ACgACAA8AFQAgAAAA/QAKAAIAEAAVACYAAAC+AA4AAgARABUAFQAVABUAFADXAAoAPgMAACgAJgEmAT4CEgC2BgAAAABAAAAAAAAAAAAAAAAdAA8AAwAAAAAAAAEAAAAAAAAACgAAAP///////////////wEAAAACAAAAAwAAAAQAAAAFAAAABgAAAAcAAAAIAAAACQAAAAoAAAALAAAADAAAAA0AAAAOAAAADwAAABAAAAARAAAAEgAAABMAAAAUAAAAFQAAABYAAAAXAAAAGAAAABkAAAAaAAAAGwAAABwAAAAdAAAAHgAAAB8AAAAgAAAAIQAAACIAAAAjAAAAJAAAACUAAAAmAAAAJwAAACgAAAApAAAAKgAAACsAAAAsAAAALQAAAC4AAAAvAAAA/v///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////v///wIAAAADAAAABAAAAAUAAAAGAAAA/v////7////+//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8=\",\"fileName\":\"供应商补贴明细\",\"isShared\":false,\"simpleName\":\"xls\",\"size\":\"5120\",\"sizeInByte\":5120,\"storageType\":\"0\"}],\"billStatus\":\"提交\",\"biller\":\"wangchong\",\"bizAccountEntry\":[{\"amount\":2.70000000000000017763568394002504646778106689453125,\"approvedAmount\":2.70000000000000017763568394002504646778106689453125,\"budgetNumber\":\"LLL.04\",\"comment\":\"ra24685978996180280app5\",\"expenseType\":\"AL06.13\",\"happenTime\":\"2016-10-11 14:12:48\",\"operationType\":\"AL.06\",\"originalAmount\":2.70000000000000017763568394002504646778106689453125,\"purpose\":\"超市供应商（补贴）\"}],\"cause\":\"报销供应商补贴\",\"currency\":\"BB01\",\"desc\":\"附件为明细\",\"isonlyperson\":\"1\",\"payCompany\":\"HYN_LLL\",\"payMode\":\"00\",\"position\":\"HYN_LLL_01.01.002\",\"prior\":\"LOW\",\"revAccount\":\"6222024000012345678\",\"revDept\":\"蒙蒙\",\"revOpenBank\":\"中国银行西乡支行\",\"srcBillNumber\":\"ra24685978996180280app5\",\"theme\":\"报销单\"}]";
	  

	  
	 // budgetProject
	    //static String bizAccountStr="";
	   
//	   static String param="[{\"adminOrgUnit\":\"755CN03\",\"attachment\":[{\"attachId\":\"20160526.07584549\",\"file\":\"c2ZzYWRmZGFzZmRh6PO12bjU6PO12bjU\",\"fileName\":\"test\"," +
//	   		"\"isShared\":false,\"simpleName\":\"txt\",\"size\":\"0 KB\",\"sizeInByte\":126,\"storageType\":\"0\"}],\"attachmentAccount\":1,\"billStatus\":\"保存\"," +
//	   		"\"bizDate\":\"2016-05-27 14:55:23\",\"" +
//			"casPaymentEntry\":[{\"actPayAmt\":123,\"actPayLocAmt\":123,\"entryCostCenter\":" +
//			"\"755WY_JD\",\"expenseType\":\"110911.010\",\"outBgItem\":\"12345\",\"remark\":" +
//			"\"备注0-0\"},{\"actPayAmt\":1234,\"actPayLocAmt\":1234,\"entryCostCenter\":" +
//			"\"755WY_JD\",\"expenseType\":\"110911.010\",\"outBgItem\":\"123\",\"remark\":" +
//			"\"备注0-0\"}],\"costCenter\":\"755WY_JD\",\"currency\":\"BB01\"," +
//			"\"desc\":\"摘要0\",\"mainOrgCompany\":\"000CN03\",\"payBillType\":\"201\",\"payee\":\"佛山健康药业有限公司\"," +
//			"\"payeeAccountBank\":\"766657928507\",\"payeeBank\":\"收银银行\",\"payeeType\":\"00002\",\"payerAccount\":\"1001.01\"," +
//			"\"payerAccountBank\":\"766657928507\",\"payerBank\":\"04.01.01\",\"settlementType\":\"PayerPay\"," +
//			"\"srcBillNumber\":\"20160527\",\"paymentType\":\"003\",\"sourceType\":\"AP\"}]";
	   
	   static String param="[{\"adminOrgUnit\":\"755CN03\",\"attachment\":[{\"attachId\":\"20160526.07584549\",\"file\":\"c2ZzYWRmZGFzZmRh6PO12bjU6PO12bjU\",\"fileName\":\"test\"," +
		   		"\"isShared\":false,\"simpleName\":\"xls\",\"size\":\"0 KB\",\"sizeInByte\":126,\"storageType\":\"0\"}],\"attachmentAccount\":1,\"billStatus\":\"保存\"," +
		   		"\"bizDate\":\"2016-05-27 14:55:23\",\"" +
				"casPaymentEntry\":[{\"actPayAmt\":123,\"actPayLocAmt\":123,\"entryCostCenter\":" +
				"\"755WY_JD\",\"expenseType\":\"110911.010\",\"outBgItem\":\"12345\",\"remark\":" +
				"\"备注0-0\"},{\"actPayAmt\":1234,\"actPayLocAmt\":1234,\"entryCostCenter\":" +
				"\"755WY_JD\",\"expenseType\":\"110911.010\",\"outBgItem\":\"123\",\"remark\":" +
				"\"备注0-0\"}],\"costCenter\":\"755WY_JD\",\"currency\":\"BB01\"," +
				"\"desc\":\"摘要0\",\"mainOrgCompany\":\"000CN03\",\"payBillType\":\"201\",\"payee\":\"佛山健康药业有限公司\"," +
				"\"payeeAccountBank\":\"766657928507\",\"payeeBank\":\"收银银行\",\"payeeType\":\"00002\",\"payerAccount\":\"1001.01\"," +
				"\"payerAccountBank\":\"766657928507\",\"payerBank\":\"04.01.01\",\"settlementType\":\"PayerPay\"," +
				"\"srcBillNumber\":\"20151224110650208\",\"paymentType\":\"003\",\"sourceType\":\"AP\"}]";

	   
	public static void main(String[] args) {
			TestWEB test=new TestWEB();
			// WSContext context = test.easLogin(address);
			 WSContext context = test.easLogin_prod(address);
			/*	if(null==context||context.getSessionId().length()==0){
					System.out.println("EAS登录不成功");
				}else{
					System.out.println("登录成功。ID是"+context.getSessionId().toString());
					try {
						String filePath="";
						String fileName="";
			 
						String resultInfo = test.paybillserver(bizAccountStr, "");
						//JSON.parseObject(resultInfo, ResultInfo.class);
						System.out.println(resultInfo); 
					} catch (Exception e) {
						e.printStackTrace();
					}  
				}*/
 
	}
	
	public WSContext easLogin(String address) {
		try {
			URL uri=new URL(address+"/ormrpc/services/EASLogin?wsdl\n");
			System.out.println(uri+"--------------");
			EASLoginProxy easLoginProxy=null;
			easLoginProxy=new EASLoginProxyServiceLocator().getEASLogin(uri);
			WSContext context=easLoginProxy.login("wangchong", "123456", "eas", "t160712", "L2", 2,"BaseDB");
			return context;
		}   catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public WSContext easLogin_prod(String address) {
		try {
			URL uri=new URL("http://210.75.12.78:6888/"+"ormrpc/services/EASLogin?wsdl\n");
			System.out.println(uri+"--------------");
			EASLoginProxy easLoginProxy=null;
			easLoginProxy=new EASLoginProxyServiceLocator().getEASLogin(uri);
			WSContext context=easLoginProxy.login("yuanx", "yuanxhyn ", "eas", "cnfantasia", "L2", 2,"BaseDB");
			return context;
		}   catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String paybillserver(String viriCode,String payBillStr){
		try {
			URL uri=new URL(address+"/ormrpc/services/WSPayBillServerInterFacade?wsdl\n");
			WSPayBillServerInterFacadeSrvProxy billServerInterFacadeSrvProxy=new 
			WSPayBillServerInterFacadeSrvProxyServiceLocator().getWSPayBillServerInterFacade(uri);
			return billServerInterFacadeSrvProxy.importPayBillServer(viriCode, payBillStr);
		}  catch (Exception e) {
		 
			e.printStackTrace();
		}
		return null;
	}
}
