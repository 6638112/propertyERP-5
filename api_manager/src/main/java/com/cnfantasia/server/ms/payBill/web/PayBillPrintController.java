package com.cnfantasia.server.ms.payBill.web;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.ms.payBill.service.PayBillPrintService;
import com.cnfantasia.server.ms.pd4ml.util.Html2PdfUtil;
import com.cnfantasia.server.ms.pd4ml.util.PrintConfig;
import com.cnfantasia.server.ms.pub.BaseController;

/**
 * 账单打印
 * 
 * @author liyulin
 * @version 1.0 2016年12月8日 下午4:44:39
 */
@Controller
@RequestMapping("/payBillPrint")
public class PayBillPrintController extends BaseController{
	private final Log logger = LogFactory.getLog(getClass());
	@Resource
	private PayBillPrintService payBillPrintService;
	
	/**
	 * 根据小区id批量打印
	 * 
	 * @param gbId
	 * @param gbbcId
	 * @param response
	 * @throws InvalidParameterException
	 * @throws IOException
	 */
	@RequestMapping("/batchPrintByGbId.html")
	public void batchPrintByGbId(BigInteger gbId, BigInteger gbbcId, String pageSize, String feeType, Integer pageDivision, HttpServletResponse response) throws InvalidParameterException, IOException{
		String tmpBaseDirUrl = Html2PdfUtil.getTmpBaseDirUrl();
		// 1、组装html，并生成pdf
		logger.debug("1、组装html，并生成pdf:"+tmpBaseDirUrl);
		List<File> pdfs = payBillPrintService.generatePDFs(gbId, gbbcId, tmpBaseDirUrl, pageSize, feeType, pageDivision);
		
		if(pdfs==null || pdfs.size()==0){// 没有账单，则生成空白pdf
			if(pdfs==null){pdfs = new ArrayList<File>();}
			String fileUrl = tmpBaseDirUrl +File.separator+"0.pdf";
			File pdf = new File(fileUrl);
			Html2PdfUtil.writePdf("", pdf, pageSize);
			pdfs.add(pdf);
		}
		
		// 2、合并，并在网页显示PDF
		logger.debug("2、合并，并在网页显示PDF:"+tmpBaseDirUrl);
		Html2PdfUtil.showMergePDF(tmpBaseDirUrl, pdfs, PrintConfig.PDF_NAME, response);
	}
	
	/**
	 * 根据账单id批量打印
	 * @param gbId
	 * @param payBillIds
	 * @param type 类型：账单"zd"，账期"zq"
	 * @return
	 * @throws IOException 
	 * @throws InvalidParameterException 
	 */
	@RequestMapping("/batchPprintByBillId.html")
	public void batchPrintByBillId(String gbId, String payBillIds, String type, String pageSize, String feeType, Integer pageDivision,
			HttpServletResponse response) throws InvalidParameterException, IOException {
		List<BigInteger> payBillIdList = JSON.parseArray(payBillIds, BigInteger.class);
		String templatesHtml = payBillPrintService.getPrintTemplateByBillId(gbId, payBillIdList, type, feeType);
		templatesHtml = Html2PdfUtil.getPageDivision(templatesHtml, pageDivision, pageSize);

		String html = Html2PdfUtil.getHtml(templatesHtml);
		String tmpBaseDirUrl = Html2PdfUtil.getTmpBaseDirUrl();
		// 1、生成pdf
		logger.debug("1、组装html，并生成pdf:"+tmpBaseDirUrl);
		String fileUrl = tmpBaseDirUrl +File.separator+"0.pdf";
		File pdf = new File(fileUrl);
		Html2PdfUtil.writePdf(html, pdf, pageSize);
		
		
		// 2、合并，并在网页显示PDF
		logger.debug("2、合并，并在网页显示PDF:"+tmpBaseDirUrl);
		Html2PdfUtil.showMergePDF(tmpBaseDirUrl, Arrays.asList(pdf), PrintConfig.PDF_NAME, response);
	}
}
