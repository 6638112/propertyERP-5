/**   
* Filename:    WeiXinNotifyEntity.java   
* @version:    1.0  
* Create at:   2014年6月14日 上午5:21:03   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.tenpay.client.ClientResponseHandler;

/**
 * Filename:    WeiXinNotifyEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月14日 上午5:21:03
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月14日       shiyl             1.0             1.0 Version
 */
public class WeiXinNotifyEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(WeiXinNotifyEntity.class);
//	public static void main(String[] args) throws ParseException {
//		String s1="20140614170519";
//		Date date = DateUtil.formatSecondTogether.parse(s1);
//		String dateStr = DateUtil.formatSecond.format(date);
//		System.out.println(dateStr);
//	}
	/**返回码*/
	private String retcode;
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	/**签名方式 签名类型，取值：MD5、RSA，默认：MD5*/
	private String sign_type;
	/**接口版本 版本号，默认为1.0*/
	private String service_version;
	/**字符集 字符编码,取值：GBK、UTF-8，默认：GBK*/
	private String input_charset;
	/**签名 签名*/
	private String sign;
	/**密钥序号 多密钥支持的密钥序号，默认1*/
	private Integer sign_key_index;
	/**交易模式 1-即时到账 其他保留*/
	private Integer trade_mode;
	/**交易状态 支付结果： 0—成功 其他保留*/
	private Integer trade_state;
	/**支付结果信息 支付结果信息，支付成功时为空*/
	private String pay_info;
	/**商户号 商户号，也即之前步骤的partnerid,由微信统一分配的10位正整数(120XXXXXXX)号*/
	private String partner;
	/**付款银行 银行类型，在微信中使用WX*/
	private String bank_type;
	/**银行订单号 银行订单号*/
	private String bank_billno;
	/**总金额 支付金额，单位为分，如果discount有值，通知的total_fee + discount = 请求的total_fee*/
	private Long total_fee;
	/**币种 现金支付币种,目前只支持人民币,默认值是1-人民币*/
	private Integer fee_type;
	/**通知ID 支付结果通知id，对于某些特定商户，只返回通知id，要求商户据此查询交易结果*/
	private String notify_id;
	/**订单号 交易号，28位长的数值，其中前10位为商户号，之后8位为订单产生的日期，如20090415，最后10位是流水号*/
	private String transaction_id;
	/**商户订单号 商户系统的订单号，与请求一致*/
	private String out_trade_no;
	/**商家数据包 商家数据包，原样返回*/
	private String attach;
	/**支付完成时间 支付完成时间，格式为  yyyyMMddhhmmss，如2009年12月27日9点10分10秒表示为20091227091010。时区为GMT+8 beijing*/
	private String time_end;
	/**物流费用 物流费用，单位分，默认0。如果有值，必须保证transport_fee + product_fee = total_fee*/
	private Long transport_fee;
	/**物品费用 物品费用，单位分。如果有值，必须保证transport_fee + product_fee=total_fee*/
	private Long product_fee;
	/**折扣价格 折扣价格，单位分，如果有值，通知的total_fee + discount = 请求的total_fee*/
	private Long discount;
	/**买家别名 对应买家账号的一个加密串*/
	private String buyer_alias;
	
	/**在postData中还将包含xml数据*/
	private String postDataXml;

	/**
	 * 获取订单需要支付的总金额
	 * 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
	 * @return
	 */
	public Long fetchAmount(){
		if(discount!=null){
			return total_fee+discount;
		}else{
			return total_fee;
		}
	}
	public static WeiXinNotifyEntity loadData(ClientResponseHandler queryRes){
		WeiXinNotifyEntity weiXinNotifyEntity = new WeiXinNotifyEntity();
		weiXinNotifyEntity.setRetcode(queryRes.getParameter("retcode"));
		weiXinNotifyEntity.setSign_type(queryRes.getParameter("sign_type"));
		weiXinNotifyEntity.setService_version(queryRes.getParameter("service_version"));
		weiXinNotifyEntity.setInput_charset(queryRes.getParameter("input_charset"));
		weiXinNotifyEntity.setSign(queryRes.getParameter("sign"));
		weiXinNotifyEntity.setSign_key_index(getInteger(queryRes.getParameter("sign_key_index")));
		weiXinNotifyEntity.setTrade_mode(getInteger(queryRes.getParameter("trade_mode")));
		weiXinNotifyEntity.setTrade_state(getInteger(queryRes.getParameter("trade_state")));
		weiXinNotifyEntity.setPay_info(queryRes.getParameter("pay_info"));
		weiXinNotifyEntity.setPartner(queryRes.getParameter("partner"));
		weiXinNotifyEntity.setBank_type(queryRes.getParameter("bank_type"));
		weiXinNotifyEntity.setBank_billno(queryRes.getParameter("bank_billno"));
		weiXinNotifyEntity.setTotal_fee(getLong(queryRes.getParameter("total_fee")));
		weiXinNotifyEntity.setFee_type(getInteger(queryRes.getParameter("fee_type")));
		weiXinNotifyEntity.setNotify_id(queryRes.getParameter("notify_id"));
		weiXinNotifyEntity.setTransaction_id(queryRes.getParameter("transaction_id"));
		weiXinNotifyEntity.setOut_trade_no(queryRes.getParameter("out_trade_no"));
		weiXinNotifyEntity.setAttach(queryRes.getParameter("attach"));
		{//设置时间格式
			String time_endStr = queryRes.getParameter("time_end");
			Date date;
			try {
				date = DateUtil.formatSecondTogether.get().parse(time_endStr);
				String dateStr = DateUtil.formatSecond.get().format(date);
				weiXinNotifyEntity.setTime_end(dateStr);
			} catch (ParseException e) {
				logger.debug("time_endStr formate error:time_endStr is "+time_endStr,e);
			}
		}
		
		weiXinNotifyEntity.setTransport_fee(getLong(queryRes.getParameter("transport_fee")));
		weiXinNotifyEntity.setProduct_fee(getLong(queryRes.getParameter("product_fee")));
		weiXinNotifyEntity.setDiscount(getLong(queryRes.getParameter("discount")));
		weiXinNotifyEntity.setBuyer_alias(queryRes.getParameter("buyer_alias"));
//		weiXinNotifyEntity.set Attach(queryRes.getParameter("postDataXml"));//TODO ..
		return weiXinNotifyEntity;
	}
	public boolean validateFee(){
		//物品费用，单位分。如果有值，必须保证transport_fee + product_fee=total_fee
		if(product_fee!=null){
			Long tmp=product_fee+(transport_fee==null?0:transport_fee);
			if(tmp==null||total_fee==null||tmp.compareTo(total_fee)!=0){
				return false;
			}
		}
		return true;
		
	}
	private static Integer getInteger(String val){
		if(StringUtils.isEmpty(val)){return null;}
		return Integer.parseInt(val);
	}
	private static Long getLong(String val){
		if(StringUtils.isEmpty(val)){return null;}
		return Long.parseLong(val);
	}
	
	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getService_version() {
		return service_version;
	}

	public void setService_version(String service_version) {
		this.service_version = service_version;
	}

	public String getInput_charset() {
		return input_charset;
	}

	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}

	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Integer getSign_key_index() {
		return sign_key_index;
	}

	public void setSign_key_index(Integer sign_key_index) {
		this.sign_key_index = sign_key_index;
	}

	public Integer getTrade_mode() {
		return trade_mode;
	}

	public void setTrade_mode(Integer trade_mode) {
		this.trade_mode = trade_mode;
	}

	public Integer getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(Integer trade_state) {
		this.trade_state = trade_state;
	}

	public String getPay_info() {
		return pay_info;
	}

	public void setPay_info(String pay_info) {
		this.pay_info = pay_info;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getBank_billno() {
		return bank_billno;
	}

	public void setBank_billno(String bank_billno) {
		this.bank_billno = bank_billno;
	}

	public Long getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Long total_fee) {
		this.total_fee = total_fee;
	}

	public Integer getFee_type() {
		return fee_type;
	}

	public void setFee_type(Integer fee_type) {
		this.fee_type = fee_type;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public Long getTransport_fee() {
		return transport_fee;
	}
	public void setTransport_fee(Long transport_fee) {
		this.transport_fee = transport_fee;
	}
	public Long getProduct_fee() {
		return product_fee;
	}
	public void setProduct_fee(Long product_fee) {
		this.product_fee = product_fee;
	}
	public Long getDiscount() {
		return discount;
	}
	public void setDiscount(Long discount) {
		this.discount = discount;
	}
	public String getBuyer_alias() {
		return buyer_alias;
	}

	public void setBuyer_alias(String buyer_alias) {
		this.buyer_alias = buyer_alias;
	}

	public String getPostDataXml() {
		return postDataXml;
	}

	public void setPostDataXml(String postDataXml) {
		this.postDataXml = postDataXml;
	}
	
	
}
