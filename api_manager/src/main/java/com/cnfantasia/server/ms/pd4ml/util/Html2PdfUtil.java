package com.cnfantasia.server.ms.pd4ml.util;

import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.zefer.pd4ml.PD4ML;
/**
 * html转pdf工具类
 *
 * @author liyulin
 * @version 1.0 2016年12月16日 下午2:15:29
 */
public final class Html2PdfUtil {

	private static final Log logger = LogFactory.getLog(Html2PdfUtil.class);

	public static final String getHtmlHead(){
		final StringBuilder html = new StringBuilder(200);
		html.append("<html>")
		    .append("	<head>")
		    .append("		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>")
		    .append("		<style>")
		    .append("           body{font-family:microsof_yahei;height: auto;color:#000000;}")
		    .append("           .mtop10 li{text-align: left;}")
		    .append("           .t_right li{text-align:right !important;margin-right: 3em;}")
		    .append("           .page_break{page-break-after: always;}")
		    .append("           .tablePrint td {padding-top:50px; font-size: 18px;}")
		    .append("           .tablePrint h2 {color: #000000;}")
		    .append("           .bordered{border-bottom: 1px solid #000000 !important;border: solid #000 1px;}")
		    .append("           .bordered td {height: 22px; border-left: 1px solid #000; border-top: 1px solid #000; padding: 10px !important; text-align: left;background-color:#ffffff;}")
		    .append("           .trbg{color: #dd4929;background-color: #ffffff;}")
		    .append("           .info{margin: 25px 20px 0;}")
		    .append("           .bold{font-weight: bold !important;}")
		    .append("           table.info-list-02 {width: 100%;background: #eeeeee;border-radius: 3px;border-bottom: 1px solid #dddfe1;font-size: 12px !important;}")
		    .append("		    .t_center {text-align: center !important;margin:0px;}")
		    .append("		    .t_right {text-align: right !important;}")
		    .append("		    .f14 {font-size: 14px !important;}")
		    .append("		    table.info-list {width: 100%;font-size: 12px;}")
		    .append("		    table.tableBordered {border-spacing: 0;width: 100%;}")
		    .append("		    .tableBordered {border: solid #666 1px;border-top: none;}")
		    .append("		    table.info-list tr td {padding: 5px 2px;}")
		    .append("		    .tableBordered td, .tableBordered th {height: 30px;border-left: 1px solid #666;border-top: 1px solid #666;padding: 10px;text-align: center;}")
		    .append("		    .mtop10 {margin-top: 5px !important;}")
		    .append("		    ul, li {list-style: none;margin: 0px;padding-bottom: 0px;}")

		    .append("		</style>")
		    .append("	</head>")
		    .append("	<body>");
		return html.toString();
	}

	/**
	 * 组装html
	 * 
	 * @param template
	 * @return
	 */
	public static final String getHtml(String template){
		StringBuilder html = new StringBuilder();
		html.append(getHtmlHead())
		.append(template)
		.append(getHtmlTail());

		return html.toString();
	}

	/**
	 * 获取html尾部
	 * 
	 * @param template
	 * @return
	 */
	public static final String getHtmlTail(){
		StringBuilder html = new StringBuilder();
		html.append("	</body>")
			.append("</html>");

		return html.toString();
	}

	/**
	 * 将html转pdf（此方式在Sring对象过大的情况下可能导致heap溢出）
	 * 
	 * @param response
	 * @param template
	 * @param pageSize
	 * @throws InvalidParameterException
	 * @throws IOException
	 */
	@Deprecated
	public static final void html2pdf(HttpServletResponse response, String template, String pageSize) throws InvalidParameterException, IOException{
		response.setContentType("application/pdf;charset=UTF-8");
		response.setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(PrintConfig.PDF_NAME, PrintConfig.UTF_8));
		ServletOutputStream outputStream = response.getOutputStream();

		PD4ML pd4ml = new PD4ML();
		// pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A5)); 
		pd4ml.setPageSize(PdfDimension.getDimension(pageSize)); 
		pd4ml.setHtmlWidth(1073);
		pd4ml.setPageInsets(new Insets(15, 10, 10, 10));
		pd4ml.enableDebugInfo();
		//pd4ml.monitorProgress(new ProgressMeter());
		pd4ml.useTTF(PrintConfig.FONT_PACKAGE, true);
		pd4ml.setDefaultTTFs("microsof_yahei", "microsof_yahei", "microsof_yahei");
		String html = getHtml(template);
		pd4ml.render(new StringReader(html), outputStream);
		outputStream.close();
	}

	/**
	 * 浏览器打开方式
	 * 
	 * @param isAttachment
	 * @return
	 */
	private static final String getOpenType(boolean isAttachment){
		if(isAttachment){
			return PrintConfig.ATTACHMENT;
		} else {
			return PrintConfig.INLINE;
		}
	}

	//=======================================================
	/**
	 * 将html写入pdf
	 * 
	 * @param html
	 * @param outFile
	 * @param pageSize
	 * @throws InvalidParameterException
	 * @throws IOException
	 */
	public static final void writePdf(String html, File outFile, String pageSize) throws InvalidParameterException, IOException{
		PD4ML pd4ml = new PD4ML();
		//pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A5)); 
		pd4ml.setPageSize(PdfDimension.getDimension(pageSize)); 
		pd4ml.setHtmlWidth(1073);
		pd4ml.setPageInsets(new Insets(10, 10, 10, 10));
		pd4ml.enableDebugInfo();
		pd4ml.useTTF(PrintConfig.FONT_PACKAGE, true);
		pd4ml.setDefaultTTFs("microsof_yahei", "microsof_yahei", "microsof_yahei");
		pd4ml.enableImgSplit(false);
		//pd4ml.monitorProgress(new ProgressMeter());

		OutputStream outputStream = FileUtils.openOutputStream(outFile);
		pd4ml.render(new StringReader(html), outputStream);
		
		outputStream.close();
	}

	private static final void mergePdf(List<File> pdfs, File mergedPdf) throws IOException{
		PDFMergerUtility mergePdf = new PDFMergerUtility();  
        for(File pdf:pdfs){
        	mergePdf.addSource(pdf);
        } 
        mergePdf.setDestinationFileName(mergedPdf.getAbsolutePath());     
        mergePdf.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
	}

	private static final void showPdf(HttpServletResponse response, File pdf, boolean isAttachment) throws IOException{
		// 设置response的Header
		response.setContentType("application/pdf;charset=UTF-8");
		response.setHeader("Content-Disposition", getOpenType(isAttachment)+";filename=" + URLEncoder.encode(PrintConfig.PDF_NAME, PrintConfig.UTF_8));
		response.setContentLength((int) pdf.length());  
		// 浏览器缓存20分钟
		response.setHeader("Cache-Control", "max-age="+PrintConfig.CLIENT_PDF_CACHE_20MINUTES);

		ServletOutputStream outputStream = response.getOutputStream();

		FileInputStream fis = FileUtils.openInputStream(pdf);  
        byte[] buffer = new byte[PrintConfig.BUFFERED_SIZE_10M];  
        
        while(fis.read(buffer, 0, PrintConfig.BUFFERED_SIZE_10M) != -1){  
        	outputStream.write(buffer, 0, PrintConfig.BUFFERED_SIZE_10M);  
        }  
        outputStream.flush();
        outputStream.close();
        fis.close();  
	}

	/**
	 * 合并，并在网页显示PDF
	 * 
	 * @param tmpBaseDirUrl 临时文件夹
	 * @param htmlFiles 所有生成的html
	 * @param response
	 * @throws InvalidParameterException
	 * @throws IOException
	 */
	public static final void showMergePDF(String tmpBaseDirUrl, List<File> pdfFiles, String pdfName, HttpServletResponse response) throws InvalidParameterException, IOException{
		// 2、pdf合成
		logger.debug("2.1、pdf合成:"+tmpBaseDirUrl);
		File mergedPdf = new File(tmpBaseDirUrl + File.separator + pdfName);
		Html2PdfUtil.mergePdf(pdfFiles, mergedPdf);

		// 3、浏览器文件流输出
		logger.debug("2.2、浏览器文件流输出:"+tmpBaseDirUrl);
		Html2PdfUtil.showPdf(response, mergedPdf, false);

		// 4、删除所有文件
		logger.debug("2.3、删除所有文件:"+tmpBaseDirUrl);
		FileUtils.deleteDirectory(new File(tmpBaseDirUrl));
	}
	
	/**
	 * A4纸分页
	 * @param srcText
	 * @param findText
	 * @param pageDivision
     * @return
     */
	public static final String getPageDivision(String srcText, Integer pageDivision, String pageSize) {
		if("A4".equals(pageSize) && !PrintConfig.BillSize.ONE_BILL.equals(pageDivision)) {// A4纸每页显示多少个账单处理
			StringBuffer temp = new StringBuffer();
			int count = 1;
			Pattern p = Pattern.compile(PrintConfig.PAGE_BREAK);
			Matcher m = p.matcher(srcText);
			while (m.find()) {
				if((count) % pageDivision != 0 || count == 1) {// 第一个不分页
					m.appendReplacement(temp, StringUtils.EMPTY);
				}
				count++;
			}
			
			m.appendTail(temp);
			return temp.toString();
		}
		return srcText;
	}
	
	/**
	 * 获取临时目录
	 * @return
	 */
	public static final String getTmpBaseDirUrl(){
		String tmpBaseDirUrl = "";
		if(PrintConfig.baseDirUrl.endsWith(File.separator)){
			tmpBaseDirUrl = PrintConfig.baseDirUrl + UUID.randomUUID().toString();
		} else {
			tmpBaseDirUrl = PrintConfig.baseDirUrl + File.separator + UUID.randomUUID().toString();
		}
		
		return tmpBaseDirUrl;
	}
}
