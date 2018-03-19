package com.cnfantasia.server.ms.revenue.constant;

public final class RevenueDict {
	
	/**
	 * 收益项目类别==收益项目类别=={"1":"认证门牌","2":"服务类订单","3":"物业宝佣金","4":"超市收益","5":"物业费实收","6":"停车费实收","7":"其他代收费用","8":"物业宝抵扣收益","9":"停车宝佣金","10":"停车宝抵扣收益","15":"物业补贴","16":"供应商结算","17":"停车费补贴"}
	 */
	public final static class RevenueProject{
		/** 认证门牌 */
		public static final Integer RoomValidate = 1;
		/** 服务类订单 */
		public static final Integer ServiceOrder = 2;
		/** 物业宝佣金 */
		public static final Integer WuyebaoAmount = 3;
		/** 停车宝佣金 */
		public static final Integer CarFinanceBaoAmout = 9;
		/** 超市收益 */
		public static final Integer MarketAmout = 4;
		/** 物业费实收 */
		public static final Integer PropertyRealPayAmout = 5;
		/** 停车费实收 */
		public static final Integer CarAmount = 6;
		/** 其他代收费用 */
		public static final Integer PropertyOtherFee = 7;
		/** 物业宝抵扣收益 */
		public static final Integer FinanceDeduAmount = 8;
		/** 停车宝抵扣收益 */
		public static final Integer CarDeduAmount = 10;
		/** 物业补贴 */
		public static final Integer PropertySubsidyAmout = 15;
		/** 供应商结算 */
		public static final Integer DirectPurchase = 16;
		/** 停车费补贴 */
		public static final Integer CarAmountBt = 17;
		/** 生活缴费 */
		public static final Integer LivingPayAmount = 18;
	}
	
	/**
	 * "1":"系统管理员","2":"物业公司角色","3":"代理商","4":"财务人员","5":"楼下店铺店主","6":"维修师傅"
	 */
	public static enum UserRole{
		SysAdmin(MiniRoleType.SysAdmin)
		,PropertyCompany(MiniRoleType.PropertyCompany)
		,PropertyAgent(MiniRoleType.PropertyAgent,false)
		,Financer(MiniRoleType.Financer)
		,CustomerService(MiniRoleType.CustomerService)
		,DownstairStore(MiniRoleType.DownstairStore)
		,RepairMaster(MiniRoleType.RepairMaster)
		,PCManagement(MiniRoleType.PCManagement)
		,District(MiniRoleType.District)
		,Default(MiniRoleType.Default);
		private Integer code;
		/**是否为最底部数据*/
		private boolean isBottom;
		private UserRole(Integer code){
			this.code = code;
			this.isBottom = true;
		}
		private UserRole(Integer code,boolean isBottom){
			this.code = code;
			this.isBottom = isBottom;
		}
		public Integer getCode() {
			return code;
		}
		public boolean isBottom() {
			return isBottom;
		}
		
		public static UserRole createUserRole(Integer code){
			if(code!=null){
				if(MiniRoleType.SysAdmin.compareTo(code)==0){
					return UserRole.SysAdmin;
				}else if(MiniRoleType.PropertyCompany.compareTo(code)==0){
					return UserRole.PropertyCompany;
				}else if(MiniRoleType.PCManagement.compareTo(code)==0){
					return UserRole.PCManagement;
				}else if(MiniRoleType.PropertyAgent.compareTo(code)==0){
					return UserRole.PropertyAgent;
				}else if(MiniRoleType.Financer.compareTo(code)==0){
					return UserRole.Financer;
				}else if(MiniRoleType.DownstairStore.compareTo(code)==0){
					return UserRole.DownstairStore;
				}else if(MiniRoleType.PCManagement.compareTo(code)==0){
					return UserRole.PCManagement;
				}else if(MiniRoleType.Default.compareTo(code)==0){
					return UserRole.Default;
				}else if(MiniRoleType.CustomerService.compareTo(code)==0){
					return UserRole.CustomerService;
				}else if(MiniRoleType.District.compareTo(code)==0){
					return UserRole.District;
				}
			}
			return null;
		}
		
//		public static final Integer SysAdmin = 1;
//		public static final Integer PropertyCompany = 2;
//		public static final Integer PropertyAgent = 3;
//		public static final Integer Financer = 4;
//		public static final Integer DownstairStore = 5;
	}
	
	
	/**
	 * 有效状态=={"1":"开启","2":"关闭"}
	 */
	public final static class ActiveStatus{
		public static final Integer OPEN = 1;
		public static final Integer CLOSED = 2;
	}
	
	/**
	 * 规则类型=={"2":"按数量","1":"按百分比"}
	 */
	public final static class RuleType{
		public static final Integer ByPercent = 1;
		public static final Integer ByCount = 2;
	}
	
	
	/**
	 * 所属最小粒度角色类型=={"1":"系统管理员","2":"物业公司角色","3":"代理商","4":"财务人员","5":"楼下店铺店主","6":"维修师傅","7":"推荐人",“12”：“电商供应商”}
	 */
	public final static class MiniRoleType{
		public static final Integer SysAdmin = 1;
		public static final Integer PropertyCompany = 2;
		public static final Integer PropertyAgent = 3;
		public static final Integer Financer = 4;
		public static final Integer DownstairStore = 5;
		public static final Integer RepairMaster = 6;
		public static final Integer Recommender = 7;
		public static final Integer DirectPurchase = 12;
		public static final Integer PCManagement = 13;//物业管理处
		public static final Integer CustomerService = 14;//客服
		public static final Integer LivingFeePay = 15;//生活缴费运营
		public static final Integer District = 50; //片区经理
		public static final Integer Default = 99;
	}
	
	/**
	 * 提款状态=={"1":"未提款","2":"提款中","3":"已结算"}
	 */
	public final static class TkStatus{
		public static final Integer Undo = 1;
		public static final Integer Doing = 2;
		public static final Integer Finished = 3;
	}
	
	/**
	 * 提款操作类型=={"1":"用户手工操作","2":"系统自动创建"}
	 */
	public final static class TkOptType{
		public static final Integer User = 1;
		public static final Integer SystemAuto = 2;
	}
	
	/**
	 * 提款单可见类型=={"1":"财务(admin)可见","2":"用户可见"}
	 */
	public final static class VisibleType{
		public static final Integer FinanceVisible = 1;
		public static final Integer UserVisible = 2;
	}
}
