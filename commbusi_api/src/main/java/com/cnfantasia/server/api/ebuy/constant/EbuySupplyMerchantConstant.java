package com.cnfantasia.server.api.ebuy.constant;

public class EbuySupplyMerchantConstant {

	/**
	 * 店铺审核状态
	 * 
	 * @author wenfq
	 * 
	 */
	public static class StoreAuditStatus {
		public static int WaitAudit = 0; //待审核
		public static int PassAudit = 1;//审核通过
		public static int FailAudit = 2;//审核不通过
	}

	/**
	 * 店主审核状态
	 * 
	 * @author wenfq
	 * 
	 */
	public static class OwnerAuditStatus {
		public static int WaitAudit = 0; //待审核
		public static int PassAudit = 1;//审核通过
		public static int FailAudit = 2;//审核不通过
	}
	
	public static class RevenueType {
		public static int BUY = 1;//购销
		public static int AGENT = 2;//代销
	}

	public static class MerchantType {
		public static int Self_Country = 1;
		public static int Building_Service = 2;
		public static int Property_Service = 3;
		public static int Self_Area = 4;
	}
}
