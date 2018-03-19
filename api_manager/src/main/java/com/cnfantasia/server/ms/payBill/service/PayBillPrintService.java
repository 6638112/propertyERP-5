package com.cnfantasia.server.ms.payBill.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.propertyCompany.dao.IPropertyCompanyBaseDao;
import com.cnfantasia.server.domainbase.propertyManagement.dao.IPropertyManagementBaseDao;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.ms.payBill.constant.PrintKey;
import com.cnfantasia.server.ms.payBill.dao.FeePrintDao;
import com.cnfantasia.server.ms.payBill.dao.PayBillPrintDao;
import com.cnfantasia.server.ms.payBill.entity.CommPrintKey;
import com.cnfantasia.server.ms.payBill.entity.FeePrint;
import com.cnfantasia.server.ms.payBill.entity.PrintFeeDetail;
import com.cnfantasia.server.ms.payBill.entity.PrintTemplateEntity;
import com.cnfantasia.server.ms.payBill.entity.TemplatePrintKey;
import com.cnfantasia.server.ms.pd4ml.util.Html2PdfUtil;
import com.cnfantasia.server.ms.pd4ml.util.PrintConfig;

/**
 * 物业账单打印
 * 
 * @author liyulin
 * @version 1.0 2016年12月8日 下午6:01:16
 */
public class PayBillPrintService {
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	protected IPayBillService payBillService;
	private IPropertyCompanyBaseDao propertyCompanyBaseDao;
	private FeePrintDao feePrintDao;
	private PayBillPrintDao payBillPrintDao;
	private IPropertyManagementBaseDao propertyManagementBaseDao;
	
	/*=========================================批量打印start===========================================*/
	/**
	 * 账单打印（根据小区id）
	 * 
	 * @param gbId
	 * @param gbbcId
	 * @param tmpDir
	 * @param pageSize
	 * @return
	 * @throws InvalidParameterException
	 * @throws IOException
	 */
	public List<File> generatePDFs(BigInteger gbId, BigInteger gbbcId, String tmpDir, String pageSize, String feeType, Integer pageDivision) throws InvalidParameterException, IOException{
		List<File> pdfs = new ArrayList<File>();
		PrintTemplateEntity printTemplateEntity = getPrintTemplateByGbId(gbId);
		
		StringBuilder templates = new StringBuilder(200);
		if(printTemplateEntity!=null){
			List<TemplatePrintKey> templatePrintKeys = payBillPrintDao.selectByGbIdWithTemplate(gbbcId, feeType, printTemplateEntity.getCode());
			if(templatePrintKeys!=null && templatePrintKeys.size()>0){
				String[] allKeys = PrintKey.getAllKeys();
				String[] allEscapeKeys = PrintKey.getAllEscapeKeys();
				for(int k=0; k<templatePrintKeys.size(); k++){
					TemplatePrintKey templatePrintKey = templatePrintKeys.get(k);
					Map<String, String> printMap = templatePrintKey.getPrintMap(feeType, printTemplateEntity.getCode());
					clearNullForMap(printMap);
					
					String printTemplate = printTemplateEntity.getTemplateContent();
					for (int i=0; i<allKeys.length; i++) {
						printTemplate = printTemplate.replaceAll(allEscapeKeys[i], printMap.get(allKeys[i]));
					}
					templates.append(printTemplate);

					if (isNeedWrite(k + 1, templatePrintKeys.size())) {
						String templateStr = templates.toString();
						templateStr = Html2PdfUtil.getPageDivision(templateStr, pageDivision, pageSize);
						String fileUrl = tmpDir +File.separator+pdfs.size()+".pdf";
						File pdf = new File(fileUrl);
						String html = Html2PdfUtil.getHtml(templateStr);
						Html2PdfUtil.writePdf(html, pdf, pageSize);
						
						pdfs.add(pdf);
						templates.delete(0, templates.length());
					}
				}
			}
		} else {
			List<CommPrintKey> commPrintKeys = payBillPrintDao.selectByGbIdWithNoTemplate(gbbcId, feeType);
			for(int k=0; k<commPrintKeys.size(); k++){
				CommPrintKey commPrintKey = commPrintKeys.get(k);
				templates.append(getCommPrintTemplate(commPrintKey, feeType));
				
				if (isNeedWrite(k + 1, commPrintKeys.size())) {
					String templateStr = templates.toString();
					templateStr = Html2PdfUtil.getPageDivision(templateStr, pageDivision, pageSize);
					String fileUrl = tmpDir +File.separator+pdfs.size()+".pdf";
					File pdf = new File(fileUrl);
					String html = Html2PdfUtil.getHtml(templateStr);
					Html2PdfUtil.writePdf(html, pdf, pageSize);
					
					pdfs.add(pdf);
					templates.delete(0, templates.length());
				}
			}
		}
		
		return pdfs;
	}
	
	/**
	 * 判断是否需要写入
	 * 
	 * @param index
	 * @param size
	 * @return
	 */
	private boolean isNeedWrite(int index, int size){
		return (index % PrintConfig.BILL_HTML_BUFFER_SIZE == 0) || (index % PrintConfig.BILL_HTML_BUFFER_SIZE != 0 && index == size);
	}
	
	/**
	 * 账单打印（根据账单id）
	 * @param gbId
	 * @param payBillIdList
	 */
	public String getPrintTemplateByBillId(String gbId, List<BigInteger> payBillIdList, String type, String feeType){
		if("zq".equals(type)){// 账期
			return getZQPrintTemplateByBillId(gbId, payBillIdList, feeType);
		}else {// 账单
			return getZDPrintTemplateByBillId(gbId, payBillIdList, feeType);
		}
	}
	
	/**
	 * 账单管理页面批量打印
	 * 
	 * @param gbId
	 * @param payBillIdList
	 * @return
	 */
	public String getZDPrintTemplateByBillId(String gbId, List<BigInteger> payBillIdList, String feeType){
		StringBuilder templates = new StringBuilder(200);
		
		List<BigInteger> gbIds = JSON.parseArray(gbId, BigInteger.class);
		for(int k=0; k<payBillIdList.size(); k++){
			PrintTemplateEntity printTemplateEntity = getPrintTemplateByGbId(gbIds.get(k));
			List<BigInteger> payBillId = Arrays.asList(payBillIdList.get(k));
			if(printTemplateEntity!=null){
				List<TemplatePrintKey> templatePrintKeys = payBillPrintDao.selectByBillIdWithTemplate(payBillId, feeType, printTemplateEntity.getCode());
				if(templatePrintKeys!=null && templatePrintKeys.size()>0){
					String[] allKeys = PrintKey.getAllKeys();
					String[] allEscapeKeys = PrintKey.getAllEscapeKeys();
					for(TemplatePrintKey templatePrintKey:templatePrintKeys){
						Map<String, String> printMap = templatePrintKey.getPrintMap(feeType, printTemplateEntity.getCode());
						clearNullForMap(printMap);

						String printTemplate = printTemplateEntity.getTemplateContent();
						for (int i=0; i<allKeys.length; i++) {
							printTemplate = printTemplate.replaceAll(allEscapeKeys[i], printMap.get(allKeys[i]));
						}
						templates.append(printTemplate);
					}
				}
			} else {
				List<CommPrintKey> commPrintKeys = payBillPrintDao.selectByBillIdWithNoTemplate(payBillId, feeType);

				GroupBuilding groupBuilding = groupBuildingBaseDao.selectGroupBuildingBySeqId(gbIds.get(k));
				BigInteger companyId = getPropertyCompanyId(groupBuilding);
				String adIconUrl = payBillService.getConfigAdUrl(null);
				String pcName = propertyCompanyBaseDao.selectPropertyCompanyBySeqId(companyId).getName();
				String now = DateTime.now().toString("yyyy-MM-dd");

				for(CommPrintKey commPrintKey:commPrintKeys){
					commPrintKey.setAdIconUrl(adIconUrl);
					commPrintKey.setPcName(pcName);
					commPrintKey.setNow(now);
					templates.append(getCommPrintTemplate(commPrintKey, feeType));
				}
			}
		}
		
		return templates.toString();
	}
	
	/**
	 * 账期管理页面批量打印
	 * 
	 * @param gbId
	 * @param payBillIdList
	 * @return
	 */
	public String getZQPrintTemplateByBillId(String gbId, List<BigInteger> payBillIdList, String feeType){
		PrintTemplateEntity printTemplateEntity = getPrintTemplateByGbId(new BigInteger(gbId));
		
		StringBuilder templates = new StringBuilder(200);
		if(printTemplateEntity!=null){
			List<TemplatePrintKey> templatePrintKeys = payBillPrintDao.selectByBillIdWithTemplate(payBillIdList, feeType, printTemplateEntity.getCode());
			if(templatePrintKeys!=null && templatePrintKeys.size()>0){
				String[] allKeys = PrintKey.getAllKeys();
				String[] allEscapeKeys = PrintKey.getAllEscapeKeys();
				for(TemplatePrintKey templatePrintKey:templatePrintKeys){
					Map<String, String> printMap = templatePrintKey.getPrintMap(feeType, printTemplateEntity.getCode());
					clearNullForMap(printMap);
					
					String printTemplate = printTemplateEntity.getTemplateContent();
					for (int i=0; i<allKeys.length; i++) {
						printTemplate = printTemplate.replaceAll(allEscapeKeys[i], printMap.get(allKeys[i]));
					}
					templates.append(printTemplate);
				}
			}
		} else {
			List<CommPrintKey> commPrintKeys = payBillPrintDao.selectByBillIdWithNoTemplate(payBillIdList, feeType);
			
//			GroupBuilding groupBuilding = groupBuildingBaseDao.selectGroupBuildingBySeqId(new BigInteger(gbId));
//			BigInteger companyId = getPropertyCompanyId(groupBuilding);
//			String adIconUrl = payBillService.getConfigAdUrl(null);
//			String pcName = propertyCompanyBaseDao.selectPropertyCompanyBySeqId(companyId).getName();
//			String now = DateTime.now().toString("yyyy-MM-dd");
			
			for(CommPrintKey commPrintKey:commPrintKeys){
//				commPrintKey.setAdIconUrl(adIconUrl);
//				commPrintKey.setPcName(pcName);
//				commPrintKey.setNow(now);
				
				templates.append(getCommPrintTemplate(commPrintKey, feeType));
			}
		}
		return templates.toString();
	}
	
	
	/*=========================================批量打印end===========================================*/
	/*-----------------------------------------单个打印start-------------------------------------------*/
	/**
	 * 获取初始化过模板中key的模板字符串
	 * 
	 * @param groupBuilding
	 * @param payBillId
	 * @param propertyFeePrintId
	 * @return
	 */
	public String getInitPrintTemplate(String gbId, BigInteger payBillId, String feeType){
		return getPrintTemplateByBillId(gbId, Arrays.asList(payBillId), "zq", feeType);
	}
	
	/**
	 * 将Map中value为null的设置为""
	 * @param paramMap
	 */
	public void clearNullForMap(Map<String, String> paramMap){
		String[] allKeys = PrintKey.getAllKeys();
		for(String key:allKeys){
			if(paramMap.get(key)==null){
				paramMap.put(key, "&nbsp;");
			}
		}
	}
	
	/**
	 * “账单打印管理”-“账单查询”页面打印“空白页”
	 * 
	 * @param request
	 * @return
	 */
	public String printBlank(HttpServletRequest request){
		String adIconUrl = payBillService.getConfigAdUrl(null);
		request.setAttribute("adIconUrl", adIconUrl);
		
		return "/payBill/payBillPrint4Blank";
	}
	
	/**
	 * “账单打印管理”-“账单查询”页面打印
	 * 
	 * @param feePrintId
	 * @param request
	 * @return
	 */
	public String printForQry(Long feePrintId, HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("feePrintId", feePrintId);
		
		List<FeePrint> feePrintList = feePrintDao.getFeePrintList(paramMap);
		if(feePrintList != null && feePrintList.size() > 0) {
			FeePrint feePrint = feePrintList.get(0);
			request.setAttribute("addr", feePrint.getAddr());
			request.setAttribute("feeName", feePrint.getAccountName());
			request.setAttribute("month", feePrint.getAccountMonth());
			request.setAttribute("feeStr", feePrint.getAccount());
			request.setAttribute("currentDate", feePrint.getPayTm());
			request.setAttribute("feeDetailList", feePrint.getFeeDetailList());
			request.setAttribute("payTime", feePrint.getPayTm());//缴费时间
			String pcName = payBillService.getPropertyCompanyName(feePrint.getRealRoomEntity().getBuildingEntity().gettGroupBuildingFId().longValue());
			request.setAttribute("pcName", pcName);
			
			// 广告图片
			String adIconUrl = payBillService.getConfigAdUrl(feePrint.getRealRoomEntity().getBuildingEntity().gettGroupBuildingFId());
			request.setAttribute("adIconUrl", adIconUrl);
		}
		
		return "/payBill/payBillPrint4Add";
	}
	
	/**
	 * 账单详情页打印
	 * 
	 * @param paybillId
	 * @param request
	 * @return
	 */
	public String getCommPrintTemplate(BigInteger gbId, BigInteger paybillId, String feeType){
		return getZQPrintTemplateByBillId(gbId.toString(), Arrays.asList(paybillId), feeType);
		/*Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("paybillId", paybillId);
		com.cnfantasia.server.api.plotproperty.entity.PayBillEntity payBillEntity = payBillService.selectPayBillEntity(paramMap);
			
		CommPrintKey commPrintKey = new CommPrintKey();
		commPrintKey.setIsPay(payBillEntity.getIsPay());
		commPrintKey.setCustomerName(CommonRoomUtil.getAddressDetail(payBillEntity.getRealRoomEntity()));
		commPrintKey.setBillTypeName(payBillEntity.getBillTypeName());
		
		String month = PayBillShowUtil.getBillShowMonth(payBillEntity);
		if(payBillEntity.getPaytimeType()==1){// 月度缴费
			month = com.cnfantasia.server.business.pub.utils.DateUtil.strFormate(month, com.cnfantasia.server.business.pub.utils.DateUtil.formatSecond.get(), com.cnfantasia.server.business.pub.utils.DateUtil.chineseYearMonth.get());
		}else {// 周期缴费
			month = payBillEntity.getBillMonthStart().substring(0, 7) + "~" + payBillEntity.getBillMonthEnd().substring(0, 7);
		}
		if(payBillEntity.getSys0UpdTime()==null){
			commPrintKey.setPayTime("");
		} else {
			commPrintKey.setPayTime(payBillEntity.getSys0UpdTime());
		}
		
		commPrintKey.setMonth(month);
		
		if(payBillEntity.getPropertyFeeDetailList()!=null && payBillEntity.getPropertyFeeDetailList().size()>0){
			List<PrintFeeDetail> printFeeDetails = new ArrayList<PrintFeeDetail>();
			for(PropertyFeeDetail propertyFeeDetail:payBillEntity.getPropertyFeeDetailList()){
				if(StringUtils.isNotBlank(feeType) && !feeType.equals(propertyFeeDetail.getFeeType())){
					continue;
				}
				PrintFeeDetail printFeeDetail = new PrintFeeDetail();
				printFeeDetail.setName(propertyFeeDetail.getName());
				
				BigDecimal totalPriceFen = new BigDecimal(propertyFeeDetail.getTotalPrice());
				printFeeDetail.setTotalMoney(totalPriceFen.divide(new BigDecimal(100)));
				
				printFeeDetails.add(printFeeDetail);
			}
			commPrintKey.setPrintFeeDetails(printFeeDetails);
		}
		
		GroupBuildingEntity groupBuildingEntity = payBillEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity();
		BigInteger companyId = getPropertyCompanyId(groupBuildingEntity);
		
		commPrintKey.setAdIconUrl(payBillService.getConfigAdUrl(null));
		commPrintKey.setPcName(propertyCompanyBaseDao.selectPropertyCompanyBySeqId(companyId).getName());
		commPrintKey.setNow(DateTime.now().toString("yyyy-MM-dd"));
		if(payBillEntity.getLastUnpaid()!=null){
			commPrintKey.setLastUunpaid(PriceUtil.div100(payBillEntity.getLastUnpaid()));
		}
	
		return getCommPrintTemplate(commPrintKey, feeType);*/
	}
	
	/**
	 * 获取物业公司id
	 * @param groupBuilding
	 * @return
	 */
	private BigInteger getPropertyCompanyId(GroupBuilding groupBuilding){
		BigInteger companyId = groupBuilding.gettPropertyCompanyFId();
		if(companyId==null){
			PropertyManagement propertyManagement = propertyManagementBaseDao.selectPropertyManagementBySeqId(groupBuilding.gettPropertyManagementFId());
			companyId = propertyManagement.gettPropertyCompanyFId();
		}
		return companyId;
	}
	
	/**
	 * 普通打印模板
	 * 
	 * @param commPrintKey
	 * @return
	 */
	public String getCommPrintTemplate(CommPrintKey commPrintKey, String feeType){
		StringBuilder template = new StringBuilder();
		template.append("<div class=\"info posrelative page_break\">")
				.append("	<table class=\"tablePrint\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"850px;\" align=\"center\">")
				.append("		 <tr>")
				.append("			<td colspan=\"3\"><h2 style=\"text-align: center;font-size:22px;\">电子账单</h2></td>")
				.append("		</tr>")
				.append("		<tr>")
				.append("			<td width=\"20%\" style=\"line-height:150%;padding-right:2px;\" width=\"90\"><div align=\"right\" class=\"bold\">客户名称：</div></td>")
				.append("			<td width=\"70%\" style=\"border-bottom:1px solid #000;line-height:150%;text-align: left;padding-left:3px;\">").append(commPrintKey.getCustomerName()).append("</td>")
				.append("			<td width=\"10%\"></td>")
				.append("		</tr>")
				.append("		<tr>")
				.append("			<td width=\"20%\" style=\"line-height:150%;padding-right:2px;\" width=\"90\"><div align=\"right\" class=\"bold\">账单名称：</div></td>")
				.append("			<td width=\"70%\" style=\"border-bottom:1px solid #000;line-height:150%;text-align: left;padding-left:3px;\">").append(commPrintKey.getBillTypeName()).append("</td>")
				.append("			<td width=\"10%\"></td>")
				.append("		</tr>")
				.append("		<tr>")
				.append("			<td width=\"20%\" style=\"line-height:150%;padding-right:2px;\" width=\"90\"><div align=\"right\" class=\"bold\">账单月份：</div></td>")
				.append("			<td width=\"70%\" style=\"border-bottom:1px solid #000;line-height:150%;text-align: left;padding-left:3px;\">").append(commPrintKey.getMonth()).append("</td>")
				.append("			<td width=\"10%\"></td>")
				.append("		</tr>");
		if(commPrintKey.getIsPay()!=null && commPrintKey.getIsPay()==1){// 已缴费
			template.append("   <tr>")
				.append("			<td width=\"20%\" style=\"line-height:150%;padding-right:2px;\" width=\"90\"><div align=\"right\" class=\"bold\">缴费时间：</div></td>")
				.append("			<td width=\"70%\" style=\"border-bottom:1px solid #000;line-height:150%;text-align: left;padding-left:3px;\">").append(commPrintKey.getPayTime()).append("</td>")
				.append("			<td width=\"10%\"></td>")
				.append("		</tr>");
			}
		template.append("		<tr style=\"vertical-align:top\">")
				.append("			<td width=\"20%\" style=\"line-height:150%;padding-right:2px;\" width=\"90\"><div align=\"right\"  class=\"bold\">缴费明细：</div></td>")
				.append("			<td width=\"70%\" style=\"line-height:150%;text-align: left;padding-left:3px;\">");
		List<PrintFeeDetail> printFeeDetails = commPrintKey.getPrintFeeDetails();
		if(printFeeDetails!=null && printFeeDetails.size()>0){
			final DecimalFormat df = new DecimalFormat("0.00");
				template.append("		<table class=\"info-list-02 bordered\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">")
				        .append("			<tr>")
				        .append("				<td class=\"bold\">收费项目名称</td>")
				        .append("				<td class=\"bold\">金额</td>")
				        .append("			</tr>");
				
			TemplatePrintKey.mergeUnPaidSameNameWithRow(printFeeDetails);
			BigDecimal allMoney = BigDecimal.ZERO;
			for(PrintFeeDetail printFeeDetail:printFeeDetails){
				String pfdName = printFeeDetail.getName();
				if(printFeeDetail.isOwe()){
					pfdName  += "欠费";
				}
				template.append("			<tr>")
				        .append("				<td>").append(pfdName).append("</td>")
				        .append("				<td>").append(df.format(printFeeDetail.getTotalMoney().doubleValue())).append("</td>")
				        .append("			</tr>");
				
				allMoney = allMoney.add(printFeeDetail.getTotalMoney());
			}
				template.append("			<tr>")
				        .append("				<td class=\"trbg\">合计</td>")
				        .append("				<td class=\"trbg\">").append(df.format(allMoney.doubleValue())).append("</td>")
				        .append("			</tr>");
				template.append("		</table>");
		}
		template.append("			</td>")
				.append("			<td width=\"10%\">&nbsp;</td>")
				.append("		</tr>")
				.append("		<tr>")
				.append("			<td colspan=\"2\" style=\"text-align: right;padding-right: 70px;\">").append(commPrintKey.getPcName()).append("</td>")
				.append("		</tr>")
				.append("		<tr>")
				.append("			<td colspan=\"2\" style=\"text-align: right;padding-right: 70px; padding-top:10px;\">").append(DateTime.now().toString("yyyy-MM-dd")).append("</td>")
				.append("		</tr>")
				.append("		<tr id=\"adTr\">")
				.append("			<td colspan=\"3\" style=\"text-align: center; padding:50px;padding-top:10px;\">")
				.append("				<img width=\"100%\" border=\"0\" height=\"300\" src=\"").append(commPrintKey.getAdIconUrl()).append("\" onerror=\"this.parentNode.parentNode.remove();\"/>")
				.append("			</td>")
				.append("		</tr>")
				.append("	</table>")
				.append("</div>");
		
		return template.toString();
	}
	
	/**
	 * 根据小区id获取打印模板相关
	 * @param gbId
	 * @return
	 */
	public final PrintTemplateEntity getPrintTemplateByGbId(BigInteger gbId){
		Map<String, PrintTemplateEntity> printTemplateMap = payBillPrintDao.selectPrintTemplateByGbId(gbId);
		if(printTemplateMap!=null){
			for(Map.Entry<String, PrintTemplateEntity> entry:printTemplateMap.entrySet()){
				PrintTemplateEntity printTemplateEntity = entry.getValue();
				if(printTemplateEntity.getServiceState()==0){
					return printTemplateEntity;
				}
			}
		}
		
		return null;
	}
	/*-----------------------------------------单个打印end-------------------------------------------*/

	public void setGroupBuildingBaseDao(IGroupBuildingBaseDao groupBuildingBaseDao) {
		this.groupBuildingBaseDao = groupBuildingBaseDao;
	}

	public void setPayBillService(IPayBillService payBillService) {
		this.payBillService = payBillService;
	}

	public void setPropertyCompanyBaseDao(IPropertyCompanyBaseDao propertyCompanyBaseDao) {
		this.propertyCompanyBaseDao = propertyCompanyBaseDao;
	}

	public void setFeePrintDao(FeePrintDao feePrintDao) {
		this.feePrintDao = feePrintDao;
	}

	public void setPayBillPrintDao(PayBillPrintDao payBillPrintDao) {
		this.payBillPrintDao = payBillPrintDao;
	}

	public void setPropertyManagementBaseDao(IPropertyManagementBaseDao propertyManagementBaseDao) {
		this.propertyManagementBaseDao = propertyManagementBaseDao;
	}
	
}
