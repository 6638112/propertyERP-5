package com.cnfantasia.server.ms.revenue.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cnfantasia.server.common.utils.*;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.domainbase.revenueApply.service.IRevenueApplyBaseService;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleDetailAdminDto;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.ms.ebuyProductSettle.service.IEbuyProductSettleService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.entity.RevenueBatchParam;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

/**
 * 批量导出收益明细 
 * @author yangh
 *
 */
@Controller
@RequestMapping("/revenueBatchExport")
public class RevenueBatchExportController extends BaseController {
	@Resource
	private IRevenueService revenueService;
	@Resource
	private IRevenueApplyBaseService revenueApplyBaseService;

	@RequestMapping("/exportExcel.html")
	public void exportExcel(String batchParam, HttpSession session, HttpServletResponse response) throws Exception {
		List<RevenueBatchParam> batchParams = JSON.parseArray(batchParam, RevenueBatchParam.class);
		String filePath = session.getServletContext().getRealPath("/docs/empty_template.xls");
		
		try {
			if(batchParams != null && batchParams.size()>0) {
				FileInputStream fin = new FileInputStream(new File(filePath));
				HSSFWorkbook workbookTotal = new HSSFWorkbook(fin);
				
				for (int i = 0, totalSheetSize=batchParams.size(), sheetIndex = 0, len = batchParams.size(); i < len; i++) {
					RevenueBatchParam revenueBatchParam = batchParams.get(i);
					HSSFWorkbook workbook = revenueService.getRevenueHSSFWorkbook(revenueBatchParam);
					if(workbook != null) {
						HSSFSheet sourceSheet = workbook.getSheetAt(0);
						if(StringUtils.isNotBlank(revenueBatchParam.getEasBillNumbers())){
							// “单据编号”存在，则按“单据编号”命名；如果生成多张单据，则重复多次导出明细
							String[] easBillNumbers = revenueBatchParam.getEasBillNumbers().split(";");
							totalSheetSize += easBillNumbers.length - 1;
							for(String easBillNumber:easBillNumbers){
								copySheet(workbookTotal, workbookTotal.getSheetAt(sheetIndex), workbook, sourceSheet);
								// sheet title已存在
								int sheetTitleIndex = workbookTotal.getSheetIndex(easBillNumber);
								if(sheetTitleIndex >= 0){
									workbookTotal.setSheetName(sheetIndex, easBillNumber+"_"+sheetIndex);
								} else {
									workbookTotal.setSheetName(sheetIndex, easBillNumber);
								}
								if(sheetIndex < totalSheetSize - 1) {
									workbookTotal.createSheet();
								}
								sheetIndex++;
							}
						} else {
							copySheet(workbookTotal, workbookTotal.getSheetAt(sheetIndex), workbook, sourceSheet);
							workbookTotal.setSheetName(sheetIndex, revenueBatchParam.getApplyNo());
							
							if(sheetIndex < totalSheetSize - 1) {
								workbookTotal.createSheet();
							}
							sheetIndex++;
						}
					} 
				}
				
				outPutExcel(workbookTotal, response);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 复制目标 sheet
	 * @param sourceSheet 
	 * @param targetSheet 
	 */
	public void copySheet(HSSFWorkbook target, HSSFSheet targetSheet, HSSFWorkbook source, HSSFSheet sourceSheet) {
		try {
			PoiUtil.copySheet(targetSheet, sourceSheet, target, source, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出合并完成的excel
	 * @param request 
	 * @param response 
	 */
	public void outPutExcel(HSSFWorkbook workbook, HttpServletResponse response) {
		OutputStream fOut = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = format.format(new Date());
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" +new String(fileName.getBytes(), "iso8859-1") + ".xls");
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (fOut != null) {
					fOut.flush();
					fOut.close();
				}
			} catch (IOException ie) {
				throw new RuntimeException(ie);
			}
		}
	}
}
