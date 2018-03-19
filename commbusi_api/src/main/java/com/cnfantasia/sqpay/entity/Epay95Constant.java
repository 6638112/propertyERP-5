package com.cnfantasia.sqpay.entity;

public class Epay95Constant {
	
	/**
	 * 推送提现结果
	 * @author wenfq
	 *
	 */
	public static enum PushWithdrawResult {
		SCCESS_USCCESS_UP ("sccess_usccess_up", "成功上传"),
		SCCESS_TSCCESS_TRANSFER ("sccess_tsccess_transfer",	"成功转账"),
		ERROR_01ERROR_01("error_01", "encryptionstr加密字符串验证失败"),
		ERROR_02ERROR_02(" error_02", "支行地址为空"),
		ERROR_03ERROR_03 ("error_03", "省为空"),
		ERROR_04ERROR_04 ("error_04", "市为空"),
		ERROR_05ERROR_05 ("error_05", "Encryptionstr为空"),
		ERROR_06ERROR_06 ("error_06", "OrderNO和你以前上传的单号相同"),
		ERROR_07ERROR_07 ("error_07", "开户名错误"),
		ERROR_08ERROR_08 ("error_08", "银行卡号错误"),
		ERROR_09ERROR_09 ("error_09", "金额错误"),
		ERROR_10ERROR_10 ("error_10", "余额不足");
		
		private String msgCode; //message code
		private String msgDetail;//message detail
		
		private PushWithdrawResult(String msgCode, String msgDetail) {
			this.msgCode = msgCode;
			this.msgDetail = msgDetail;
		}

		public String getMsgCode() {
			return msgCode;
		}

		public String getMsgDetail() {
			return msgDetail;
		}
		
		public static String getPushWithdrawResultMsg(String msgCode){
			for(PushWithdrawResult pr : PushWithdrawResult.values()){
				if(pr.getMsgCode().equals(msgCode)){
					return pr.getMsgDetail();
				}
			}
			
			return null;
		}
	}
	
	public enum QryWithdrawTransferState {
		
	}
	
	public static void main(String[] args) {
		
	}
	
}
