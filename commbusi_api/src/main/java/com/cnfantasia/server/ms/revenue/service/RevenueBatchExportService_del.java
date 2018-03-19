package com.cnfantasia.server.ms.revenue.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.PoiUtil;
import com.cnfantasia.server.ms.revenue.entity.CarRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.service.RevenueCarService;

/**
 * 批量导出收益明细 线程类
 * @author yangh
 *
 */
@Service
public class RevenueBatchExportService_del implements Runnable{

	private List<String> projectTypeList;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private ServletContext servletContext;
	private RevenueCarService revenueCarService;
	private OutputStream outputStream;

	@Override
	public void run() {
		revenueCarService = (RevenueCarService) CnfantasiaCommbusiUtil.getBeanManager("revenueCarService");
		String filePath = servletContext.getRealPath("/docs/empty_template.xls");
		try {
			FileInputStream fin = new FileInputStream(new File(filePath));
			HSSFWorkbook workbookTotal = new HSSFWorkbook(fin);
			
			if(!projectTypeList.isEmpty()) {
				int i = 0;
				for (String projectType : projectTypeList) {
					if(projectType!=null && !"".equals(projectType)) {
						HSSFSheet targetSheet = workbookTotal.getSheetAt(i);
						HSSFWorkbook workbook = getRevenueHSSFWorkbook(projectType);
						HSSFSheet sourceSheet = workbook.getSheetAt(0);
						
						copySheet(workbookTotal, targetSheet, workbook, sourceSheet);
						i ++;
					}
				}
			}
			
			outPutExcel(workbookTotal);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取不同收益明细的HSSFWorkbook
	 */
	public HSSFWorkbook getRevenueHSSFWorkbook(String projectType) {
		HSSFWorkbook workbook = null;
		
		if(projectType.equals("1")) {//上门维修
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			List<CarRevenueSignalAmount> carRevenueSignalAmountList = revenueCarService.selectCarRevenueSaList(paramMap);
			workbook = null;//revenueCarService.createReport(carRevenueSignalAmountList, servletContext);
		}
		return workbook;
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
	 */
	public void outPutExcel(HSSFWorkbook workbook) {
		OutputStream fOut = outputStream;
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

	public List<String> getProjectTypeList() {
		return projectTypeList;
	}

	public void setProjectTypeList(List<String> projectTypeList) {
		this.projectTypeList = projectTypeList;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
}
