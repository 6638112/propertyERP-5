package com.cnfantasia.server.ms.ebuyProduct.constant;

import java.util.HashMap;
import java.util.Map;

public class EbuyProductConstant {
	
	public static Map<Integer,String> STATUS_AUDIT_MAP = new HashMap<Integer,String>();
	public static Map<Integer,String> STATUS_MAP = new HashMap<Integer,String>();
	
	static {
		STATUS_AUDIT_MAP.put(ProductAuditStatus.AUDIT_STATUS_DRAF, "草稿");
		STATUS_AUDIT_MAP.put(ProductAuditStatus.AUDIT_STATUS_WAITE_AUDIT, "待审核");
		STATUS_AUDIT_MAP.put(ProductAuditStatus.AUDIT_STATUS_NO_PASSED, "审核不通过");
		STATUS_AUDIT_MAP.put(ProductAuditStatus.AUDIT_STATUS_PASSED, "审核通过");
		
		STATUS_MAP.put(ProductStatus.STATUS_ONSHELF, "已上架");  
		STATUS_MAP.put(ProductStatus.STATUS_OFFSHELF, "已下架"); 
	}
	
	
	// 产品状态=={"0":"上架","1":"下架"}
	public static class ProductStatus {
		/** 上架 */
		public static final int STATUS_ONSHELF = 0;
		/** 下架 */
		public static final int STATUS_OFFSHELF = 1;
	}
	
	public static class ProductAuditStatus {
		
		public static final int AUDIT_STATUS_DRAF = 2;
		
		public static final int AUDIT_STATUS_WAITE_AUDIT = 3;
		
		public static final int AUDIT_STATUS_NO_PASSED = 4;
		
		public static final int AUDIT_STATUS_PASSED  = 5;
	}
	
	/**
	 * 货架管理-排序方式
	 * @author liyl
	 * @date 2016-04-15 16:19:00
	 */
	public final static class OrderType{
		public final static String DEFAULT_ORDER = "1";// 默认按时间排序
		public final static String MANAY_TO_ONE = "2";// 由多到少
		public final static String ONE_TO_MANAY = "3";// 由少到多
	}
	
}
