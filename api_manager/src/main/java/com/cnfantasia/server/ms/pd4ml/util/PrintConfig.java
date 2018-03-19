package com.cnfantasia.server.ms.pd4ml.util;

/**
 * 打印配置
 * 
 * @author liyulin
 * @version 1.0 2016年12月15日 下午6:55:48
 */
public final class PrintConfig {
	/** 打印时的临时文件夹 */
	public static final String baseDirUrl = System.getProperty("java.io.tmpdir");
	/** 网页显示 */
	public static final String INLINE = "inline";
	/** 下载 */
	public static final String ATTACHMENT = "attachment";
	/** utf-8编码 */
	public static final String UTF_8 = "UTF-8";
	/** 缓存大小10M */
	public static final int BUFFERED_SIZE_10M = 1024 * 1024 * 10;
	/** 账单一次性写入的大小 */
	public static final int BILL_HTML_BUFFER_SIZE = BillSize.TWO_BILL*BillSize.THREE_BILL*8;//48
	/** SONGTI.TTF存放路径 */
	public static final String FONT_PACKAGE = "java:com/cnfantasia/server/ms/pd4ml/fonts";
	/** pdf文件名称 */
	public static final String PDF_NAME = "打印.pdf";
	/** 托盘文件名称 */
	public static final String BC_PDF_NAME = "出盘明细.pdf";
	/** 浏览器pdf缓存时间20分钟 */
	public static final long CLIENT_PDF_CACHE_20MINUTES = 60 * 20;
	/** 账单分页符 */
	public static final String PAGE_BREAK = "page_break";
	
	/**每页显示的账单个数*/
	public static final class BillSize{
		/**一页显示1个账单*/
		public static final Integer ONE_BILL = 1;
		/**一页显示2个账单*/
		public static final Integer TWO_BILL = 2;
		/**一页显示3个账单*/
		public static final Integer THREE_BILL = 3;
	}
}
