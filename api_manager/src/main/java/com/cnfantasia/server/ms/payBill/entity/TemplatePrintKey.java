package com.cnfantasia.server.ms.payBill.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cnfantasia.server.ms.payBill.constant.PrintKey;
import com.cnfantasia.server.ms.payBill.constant.PrintTemplateCode;

public class TemplatePrintKey {
	private BigInteger payBillId;
	/** 物业公司logo */
	private String companyIcon;
	/** 房号（格式：楼栋单元房间号） */
	private String roomAddress;
	/** 业主姓名 */
	private String realName;
	/** 房屋面积 */
	private String roomSize;
	/** 小区名称 */
	private String gbName;
	/** 账单年 */
	private String feeDateYear;
	/** 账单月 */
	private String feeDateMonth;
	/** 账单名称 */
	private String feeName;
	/** 物业缴费账单开始年 */
	private String wyPayStartYear;
	/** 物业缴费账单开始月 */
	private String wyPayStartMonth;
	/** 物业缴费账单截至年 */
	private String wyPayEndYear;
	/** 物业缴费账单截至月 */
	private String wyPayEndMonth;
	/** 物业缴费账单截止最后一天 */
	private String monthLastDay;
	/** 水电缴费账单开始年 */
	private String sdPayStartYear;
	/** 水电缴费账单开始月 */
	private String sdPayStartMonth;
	/** 水电缴费账单截至年  */
	private String sdPayEndYear;
	/** 水电缴费账单截至月 */
	private String sdPayEndMonth;
	/** 总费用 */
	private BigDecimal totalFee;
	/** 缴费窗口截止年 */
	private String payEndYear;
	/** 缴费窗口截止月 */
	private String payEndMonth;
	/** 账单月份 */
	private String billYYYYMM;
	/** 物业公司 */
	private String pc;
	/** 现在的时间（<u>yyyy</u>年<u>MM</u>月<u>dd</u>日） */
	private String now;
	private List<PrintFeeDetail> printFeeDetails;
	/**抄水表往月欠费（单位：元）*/
	private BigDecimal lastUunpaid;
	private static NumberFormat nf = new DecimalFormat("#0.00");
	
	public String getCompanyIcon() {
		return companyIcon;
	}

	public void setCompanyIcon(String companyIcon) {
		this.companyIcon = companyIcon;
	}

	public String getRoomAddress() {
		return roomAddress;
	}

	public void setRoomAddress(String roomAddress) {
		this.roomAddress = roomAddress;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getFeeDateYear() {
		return feeDateYear;
	}

	public void setFeeDateYear(String feeDateYear) {
		this.feeDateYear = feeDateYear;
	}

	public String getFeeDateMonth() {
		return feeDateMonth;
	}

	public void setFeeDateMonth(String feeDateMonth) {
		this.feeDateMonth = feeDateMonth;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getWyPayStartYear() {
		return wyPayStartYear;
	}

	public void setWyPayStartYear(String wyPayStartYear) {
		this.wyPayStartYear = wyPayStartYear;
	}

	public String getWyPayStartMonth() {
		return wyPayStartMonth;
	}

	public void setWyPayStartMonth(String wyPayStartMonth) {
		this.wyPayStartMonth = wyPayStartMonth;
	}

	public String getWyPayEndYear() {
		return wyPayEndYear;
	}

	public void setWyPayEndYear(String wyPayEndYear) {
		this.wyPayEndYear = wyPayEndYear;
	}

	public String getWyPayEndMonth() {
		return wyPayEndMonth;
	}

	public void setWyPayEndMonth(String wyPayEndMonth) {
		this.wyPayEndMonth = wyPayEndMonth;
	}

	public String getMonthLastDay() {
		return monthLastDay;
	}

	public void setMonthLastDay(String monthLastDay) {
		this.monthLastDay = monthLastDay;
	}

	public String getSdPayStartYear() {
		return sdPayStartYear;
	}

	public void setSdPayStartYear(String sdPayStartYear) {
		this.sdPayStartYear = sdPayStartYear;
	}

	public String getSdPayStartMonth() {
		return sdPayStartMonth;
	}

	public void setSdPayStartMonth(String sdPayStartMonth) {
		this.sdPayStartMonth = sdPayStartMonth;
	}

	public String getSdPayEndYear() {
		return sdPayEndYear;
	}

	public void setSdPayEndYear(String sdPayEndYear) {
		this.sdPayEndYear = sdPayEndYear;
	}

	public String getSdPayEndMonth() {
		return sdPayEndMonth;
	}

	public void setSdPayEndMonth(String sdPayEndMonth) {
		this.sdPayEndMonth = sdPayEndMonth;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getPayEndYear() {
		return payEndYear;
	}

	public void setPayEndYear(String payEndYear) {
		this.payEndYear = payEndYear;
	}

	public String getPayEndMonth() {
		return payEndMonth;
	}

	public void setPayEndMonth(String payEndMonth) {
		this.payEndMonth = payEndMonth;
	}

	public String getBillYYYYMM() {
		return billYYYYMM;
	}

	public void setBillYYYYMM(String billYYYYMM) {
		this.billYYYYMM = billYYYYMM;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public List<PrintFeeDetail> getPrintFeeDetails() {
		return printFeeDetails;
	}

	public void setPrintFeeDetails(List<PrintFeeDetail> printFeeDetails) {
		this.printFeeDetails = printFeeDetails;
	}

	public BigDecimal getLastUunpaid() {
		return lastUunpaid;
	}

	public void setLastUunpaid(BigDecimal lastUunpaid) {
		this.lastUunpaid = lastUunpaid;
	}

	public BigInteger getPayBillId() {
		return payBillId;
	}

	public void setPayBillId(BigInteger payBillId) {
		this.payBillId = payBillId;
	}

	/**
	 * 处理相同名称的欠费费用
	 * @param printFeeDetails
	 */
	public static void mergeUnPaidSameNameWithRow(List<PrintFeeDetail> printFeeDetails){
		if(null!=printFeeDetails && printFeeDetails.size()>1){
			for(int i = printFeeDetails.size()-1; i > 0; i--) {
				PrintFeeDetail pfd1 = printFeeDetails.get(i);
				if(pfd1.isOwe()){
					for(int j = i-1; j>=0; j--) {
						PrintFeeDetail pfd2 = printFeeDetails.get(j);
						if(pfd2.isOwe() && pfd1.getName().equals(pfd2.getName())){
							pfd1.setTotalMoney(pfd1.getTotalMoney().add(pfd2.getTotalMoney()));
							printFeeDetails.remove(j);
							i--;
						}
					}
				}
			}
		}
	}
	
	/**
	 * 处理相同名称的欠费
	 * @param printFeeDetails
	 */
	public static void mergeUnPaidSameNameWithColumn(List<PrintFeeDetail> printFeeDetails){
		if(null!=printFeeDetails && printFeeDetails.size()>1){
			for(int i = printFeeDetails.size()-1; i > 0; i--) {
				PrintFeeDetail pfd1 = printFeeDetails.get(i);
				if(!pfd1.isOwe()){
					for(int j = i-1; j>=0; j--) {
						PrintFeeDetail pfd2 = printFeeDetails.get(j);
						if(pfd2.isOwe() && pfd1.getName().equals(pfd2.getName())){
							if(null==pfd1.getUnPaid()){pfd1.setUnPaid(BigDecimal.ZERO);}
							pfd1.setUnPaid(pfd1.getUnPaid().add(pfd2.getUnPaid()));
							printFeeDetails.remove(j);
							i--;
						}
					}
				} else {
					for(int j = i-1; j>=0; j--) {
						PrintFeeDetail pfd2 = printFeeDetails.get(j);
						if(!pfd2.isOwe() && pfd1.getName().equals(pfd2.getName())){
							if(null==pfd2.getUnPaid()){pfd2.setUnPaid(BigDecimal.ZERO);}
							pfd2.setUnPaid(pfd2.getUnPaid().add(pfd1.getTotalMoney()));
							printFeeDetails.remove(i);
							break;
						}
					}
				}
			}
		}
	}
	
	/** 模板1 账单table中的内容 */
	private String getTableContent1(){
		if(printFeeDetails!=null){
			final StringBuilder tableContent = new StringBuilder(); 

			for(PrintFeeDetail printFeeDetail:printFeeDetails){
				String pfdName = printFeeDetail.getName();
				if(printFeeDetail.isOwe()){
					pfdName  += "欠费";
				}
				tableContent.append("<tr>")
						    .append("    <td>").append(pfdName).append("</td>");
				if(printFeeDetail.getMpbrId()!=null){// 是否为水电费
					if(StringUtils.isNotBlank(printFeeDetail.getStartValue())){
					    tableContent.append("    <td>").append(printFeeDetail.getStartValue()).append("</td>");
					} else {
						tableContent.append("    <td>&nbsp;</td>");
				    }
					if(StringUtils.isNotBlank(printFeeDetail.getEndValue())){
						tableContent.append("    <td>").append(printFeeDetail.getEndValue()).append("</td>");
					} else {
						tableContent.append("    <td>&nbsp;</td>");
					}
				} else {
					tableContent.append("    <td>&nbsp;</td>")
								.append("    <td>&nbsp;</td>");
				}
				
				if(StringUtils.isNotBlank(printFeeDetail.getPriceUnitValue())){
					tableContent.append("    <td>").append(delDotZero(printFeeDetail.getPriceUnitValue())).append("</td>");
				} else {
					tableContent.append("    <td>&nbsp;</td>");
				}
				if(printFeeDetail.getMpbrId()!=null && printFeeDetail.getMrPriceList()!=null && printFeeDetail.getMrPriceList().size()>0){
					tableContent.append("    <td>").append(getMrPrices(printFeeDetail.getMrPriceList())).append("</td>");
				} else if(StringUtils.isNotBlank(printFeeDetail.getSignalPrice())){
					tableContent.append("    <td>").append(delDotZero(printFeeDetail.getSignalPrice())).append("</td>");
				} else {
					tableContent.append("    <td>&nbsp;</td>");
				}
				
				tableContent.append("    <td>").append(printFeeDetail.getTotalMoney()).append("</td>")
				  			.append("</tr>");
				
				totalFee = totalFee.add(printFeeDetail.getTotalMoney());
			}
			// 合计
			tableContent.append("<tr>")
			  .append("    <td>合计</td>")
			  .append("    <td>&nbsp;</td>")
			  .append("    <td>&nbsp;</td>")
			  .append("    <td>&nbsp;</td>")
			  .append("    <td>&nbsp;</td>")
			  .append("    <td>").append(nf.format(totalFee)).append("</td>")
			  .append("</tr>");
			
			return tableContent.toString();
		}
		return null;
	}
	
	/** 模板2 账单table中的内容 */
	private String getTableContent2(){
		if(printFeeDetails!=null){
			final StringBuilder tableContent = new StringBuilder(); 
			BigDecimal totalMoney = BigDecimal.ZERO;
			BigDecimal totalUnPaid = BigDecimal.ZERO;
			for(PrintFeeDetail printFeeDetail:printFeeDetails){
				BigDecimal money = printFeeDetail.getTotalMoney();
				BigDecimal unPaid = (printFeeDetail.getUnPaid()==null) ? BigDecimal.ZERO : printFeeDetail.getUnPaid();
				BigDecimal fee = money.add(unPaid);
				
				totalMoney = totalMoney.add(money);
				totalUnPaid = totalUnPaid.add(unPaid);
				
				String pfdName = printFeeDetail.getName();
				if(printFeeDetail.isOwe()){
					pfdName  += "欠费";
				}
				
				tableContent.append("<tr>")
						    .append("    <td>").append(pfdName).append("</td>")				
						    .append("    <td>").append(money).append("</td>");
				if(unPaid!=null && unPaid.doubleValue()>0){
					tableContent.append("    <td>").append(nf.format(printFeeDetail.getUnPaid())).append("</td>");
				} else {
					tableContent.append("    <td>&nbsp;</td>");
				}
				tableContent.append("    <td>").append(fee).append("</td>")
				  			.append("</tr>");
			}
			
			// 合计
			tableContent.append("<tr>")
			  .append("    <td>合计</td>")
			  .append("    <td>").append(totalMoney).append("</td>");
			if(totalUnPaid!=null && totalUnPaid.doubleValue()>0){
				tableContent.append("    <td>").append(nf.format(totalUnPaid)).append("</td>");
			} else {
				tableContent.append("    <td>&nbsp;</td>");
			}
			totalFee = totalMoney.add(totalUnPaid);
			tableContent.append("    <td>").append(totalFee).append("</td>")
			  .append("</tr>");
			
			return tableContent.toString();
		}
		return null;
	}
	
	private String getTableContent(String code){
		mergeUnPaidSameNameWithRow(printFeeDetails);
		if (PrintTemplateCode.template1.equals(code)) {
			return getTableContent1();
		} else if (PrintTemplateCode.template2.equals(code)) {
			mergeUnPaidSameNameWithColumn(printFeeDetails);
			return getTableContent2();
		}
		return null;
	}
	
	public Map<String, String> getPrintMap(String feeType, String code){
		Map<String, String> printMap = new HashMap<String, String>();
		printMap.put(PrintKey.$companyIcon$, companyIcon);
		printMap.put(PrintKey.$roomAddress$, roomAddress);
		printMap.put(PrintKey.$realName$, realName);
		printMap.put(PrintKey.$roomSize$, roomSize);
		printMap.put(PrintKey.$gbName$, gbName);
		printMap.put(PrintKey.$feeDateYear$, feeDateYear);
		printMap.put(PrintKey.$feeDateMonth$, feeDateMonth);
		printMap.put(PrintKey.$feeName$, feeName);
		printMap.put(PrintKey.$wyPayStartYear$, wyPayStartYear);
		printMap.put(PrintKey.$wyPayStartMonth$, wyPayStartMonth);
		printMap.put(PrintKey.$wyPayEndYear$, wyPayEndYear);
		printMap.put(PrintKey.$wyPayEndMonth$, wyPayEndMonth);
		printMap.put(PrintKey.$monthLastDay$, monthLastDay);
		printMap.put(PrintKey.$sdPayStartYear$, sdPayStartYear);
		printMap.put(PrintKey.$sdPayStartMonth$, sdPayStartMonth);
		printMap.put(PrintKey.$sdPayEndYear$, sdPayEndYear);
		printMap.put(PrintKey.$sdPayEndMonth$, sdPayEndMonth);
		printMap.put(PrintKey.$payEndYear$, payEndYear);
		printMap.put(PrintKey.$payEndMonth$, payEndMonth);
		printMap.put(PrintKey.$billYYYYMM$, billYYYYMM);
		printMap.put(PrintKey.$pc$, pc);
		printMap.put(PrintKey.$now$, now);
		totalFee = BigDecimal.ZERO;
		printMap.put(PrintKey.$tableContent$, getTableContent(code));
		printMap.put(PrintKey.$totalFee$, String.valueOf(totalFee.doubleValue()));
		
		return printMap;
	}
	
	/**
	 * 去掉数字后面多余的0
	 * 
	 * @param s
	 * @return
	 */
	private static final String delDotZero(String s) {
		if (StringUtils.isNotBlank(s)) {
			if (s.indexOf(".") > 0) {
				s = s.replaceAll("0+?$", "");//去掉多余的0
				s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
			}
		}
		return s;
	}

	private static final String getMrPrices(List<String> mrPriceList) {
		StringBuilder sb = new StringBuilder();
		if(mrPriceList!=null) {
			for(int i=0; i<mrPriceList.size(); i++) {
				if(mrPriceList.size()>1 && i>0) {
					sb.append(" / ");
				}
				sb.append(delDotZero(mrPriceList.get(i)));
			}
		}
		
		return sb.toString();
	}
}
