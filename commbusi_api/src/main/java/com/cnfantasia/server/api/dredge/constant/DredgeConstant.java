package com.cnfantasia.server.api.dredge.constant;

import java.math.BigInteger;

public class DredgeConstant {
	/** 预约单  */
	public static class DredgeBill {
		public static final int DredgeBill_Not_Pay_First = 0;//预付款没付钱
		public static final int DredgeBill_Status_Submited = 1;//派单中
		public static final int DredgeBill_Status_Accepted = 2;//已接单
		public static final int DredgeBill_Status_FinishedNoComment = 3;//完成未评价
		public static final int DredgeBill_Status_Canceled = 4;//已取消
		public static final int DredgeBill_Status_FinishedHasComment = 5;//完成已评价
		public static final int DredgeBill_Status_Property_Closed = 6;//物业关闭
		public static final int Master_Set_Free = 7;//师傅设置免费
		public static final int Master_Finish = 8;//师傅设置收费金额，表示师傅已完成，预付款的表示师傅完成
		public static final int Apply_Refund = 9;//申请退款
		public static final int Refund_Success = 10;//已退款
	}

	//类型
	public static class DredgeBillType {
		public static final int Dredge_Bill_Common = 1;
		public static final int Dredge_Bill_Transed = 2;
		//物业单映射的
		public static final int Property_Repair = 3;
		//发起的物业维修单
		public static final int Dredge_Repair = 4;
		//先行付款的维修单
		public static final int Dredge_Pay_First = 5;
	}
	/**
	 * 师傅信息
	 */
	public static class DredgeWorker {
		public static int DredgeWorker_Status_NoCertificate = 0;//未提交认证
		public static int DredgeWorker_Status_WaitCertificate = 1;//提交待认证
		public static int DredgeWorker_Status_Accepted = 2;//认证成功
		public static int DredgeWorker_Status_Refused = 3;//认证失败
		
		public static int DredgeWorker_CreateType_Regist = 1;//师傅自己注册
		public static int DredgeWorker_CreateType_PropertyCreate = 2;//物业创建维修工人时自动创建
	}
	
	/**
	 * 上门维修费用类型
	 * @author wenfq
	 *
	 */
	public static class DredgeBillAmountDetailType {
		/**
		 * 人工费
		 */
		public static int Labor_fee = 0;
		/**
		 * 其它费
		 */
		public static int Material_fee = 1;
		/**
		 * 用户自选费
		 */
		public static int SelfBuy_fee = 2;
	}

	/**
	 * 师傅等级
	 */
	public static class DredgeWorkerLevel {
		public static final int High_Level = 1;
		public static final int Middle_Level = 2;
		public static final int Low_Level = 3;
	}

	/**
	 * 维修师傅的默认头像
	 */
	public static final String DredgeMasterDefaultHeadImg = "dredgeMasterDefaultHeadImg.png";
	
	/**
	 * 维修消息（微信消息）队列
	 * @author wenfq
	 *
	 */
	public static class WeChatDredgeBillMQ {
		/**
		 * 未发送
		 */
		public static int WeChatDredgeBillMQ_Status_NotSend = 0;
		/**
		 * 发送成功
		 */
		public static int WeChatDredgeBillMQ_Status_SendSuccess = 1;
		/**
		 * 发送失败
		 */
		public static int WeChatDredgeBillMQ_Status_SendFail = 2;
		/**
		 * 用户拒绝
		 */
		public static int WeChatDredgeBillMQ_Status_SendUserRefuse = 3;
		
		
		/**
		 * 师傅已接单 
		 */
		public static int WeChatDredgeBillMQ_Type_MasterConfirm = 0;
		/**
		 * 订单逾期自动取消
		 */
		public static int WeChatDredgeBillMQ_Type_AutoCanceled = 1;
		/**
		 * 师傅已设置金额
		 */
		public static int WeChatDredgeBillMQ_Type_SetPayAmount = 2;
	}
	
	/**
	 * 外部对接供应商ID
	 */
	public static class DredgeServiceSupplier {
		public static final BigInteger JFQ = BigInteger.ONE;//解放区
		public static final BigInteger SFDJ = new BigInteger("10");//十分到家
		public static final BigInteger QSDJ = new BigInteger("20");//轻松到家
	}

	public static class ParentCommunitySupplyType {
		public static final BigInteger WYBX = new BigInteger("119");//物业报修
	}

	public static class DredgeRefundStatus {
		public static final int APPLY = 1;
		public static final int SUCCESS = 2;
		public static final int FAIL = 3;
	}
}
