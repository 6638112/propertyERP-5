/**
 * 
 */
package com.cnfantasia.server.business.commonBusiness.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.ucpaas.client.UcpaasMsgUtil;
import com.cnfantasia.server.ucpaas.client.WelinkMsgUtil;

/**
 * 类说明：发送短信线程类,调用成功返回一个string值
 * 
 * @author hunter
 * @since 2014年6月9日下午4:19:25
 */
public class SendSmsRunnable implements Callable<String>{
	
	private static Log logger = LogFactory.getLog(SendSmsRunnable.class);
	
	private List<String> mobiles;
	private String content;
	private String sendChannel = "1"; //发送通道
	
	public SendSmsRunnable(List<String> mobiles, String content){
		this.mobiles = mobiles;
		this.content = content;
	}
	
	public SendSmsRunnable(List<String> mobiles, String content, String sendChannel){
		this.mobiles = mobiles;
		this.content = content;
		if(sendChannel != null) {
			this.sendChannel = sendChannel;
		}
	}
	
	/**
	 * 批量或者单条发送短信(发送同样的内容多个手机号要用该接口)
	 * 
	 * @param mobile
	 * @param msg
	 * @throws Exception 
	 */
	private String send(List<String> mobiles, String content) throws Exception {
		if(sendChannel.equals("2")) { //云之讯
			if(mobiles != null && mobiles.size() > 0){
				for(String mobile : mobiles){
					UcpaasMsgUtil.sendMsg(mobile, content);
				}
			}
		}else if(sendChannel.equals("3")){//微网通联
			if(mobiles != null && mobiles.size() > 0){
				for(String mobile : mobiles){
					WelinkMsgUtil.sendMsg(mobile, content);
				}
			}
		}else { //原来的
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("sn", ConstantsUtil.getValue("sms.sn"));
			//md5(sn+password)
			params.put("pwd", Md5Util.MD5(ConstantsUtil.getValue("sms.sn")+ConstantsUtil.getValue("sms.pwd")));
			if(mobiles != null && mobiles.size() > 0){
				StringBuilder builder = new StringBuilder();
				for(String mobile : mobiles){
					builder.append(mobile).append(",");
				}
				if(builder.length() > 0){
					builder.deleteCharAt(builder.length() - 1);
				}
				params.put("mobile", builder.toString());
			}
			//内容的签名一定要有，用中括号括起来
//			params.put("content", String.format("%s【解放区】", content));
			params.put("content", content);
			//例如：123（默认置空）
			params.put("ext", "");
			//例如：2010-12-29 16:27:03（非定时置空）
			params.put("stime", "");
			//最长18位，只能是数字或者 字母 或者数字+字母的组合
			params.put("rrid", "");
			//msgfmt内容编码,0：ASCII串,3：短信写卡操作,4：二进制信息,8：UCS2编码,空或15：含GB汉字.返回:唯一标识
			params.put("msgfmt", "");
			//函数返回值：String（唯一标识，即当前发送短信批次的唯一标识,和rrid对应，如为空则返回系统生成的rrid）此方法推荐用于大量群发.内容相同手机号多个
			String result="";
			try{
				logger.info("采用主地址发送短信...");
				result = RemoteInvoke.call(ConstantsUtil.getValue("sms.mainUrl"), params);
			}catch(Exception e){
				logger.error(e);
				try {
					logger.info("主地址发送短信超时，切换备地址发送...");
					result = RemoteInvoke.call(ConstantsUtil.getValue("sms.backupUrl"), params);
				} catch (Exception e1) {
					logger.error(e1);
					logger.info("备地址发送短信超时，切换多线程地址发送...");
					result = RemoteInvoke.call(ConstantsUtil.getValue("sms.multiThreadUrl"), params);
				}
			}
			result = HTMLSpirit.delHTMLTag(result);
			return result;
		}
		return "";
	}

	@Override
	public String call() throws Exception {
		return send(this.mobiles, this.content);
	}
	
	public static void main(String[] args) throws Exception{
		List<String> mobiles = new ArrayList<String>();
		mobiles.add("13826587335");
		String content = "验证码:"+ (100000 + new Random().nextInt(899999));
		try{
			FutureTask<String> task = new FutureTask<String>(new SendSmsRunnable(mobiles, content));
			new Thread(task).start();
			System.out.println("测试结果 = " + task.get());
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.err.println("连接超时了....");
		}
		
//		ExecutorService executorService  = Executors.newCachedThreadPool();
//		for(int i = 0;i<10;i++){
//			Future<String> future = executorService.submit(new Callable<String>() {
//				/* (non-Javadoc)
//				 * @see java.util.concurrent.Callable#call()
//				 */
//				@Override
//				public String call() throws Exception {
//					return "111";
//				}
//			});
//			List<Future<String>> results = executorService.invokeAll(null);
//			//阻塞的
//			String reuslt = future.get(5,TimeUnit.SECONDS);
//			System.out.println(reuslt);
//		}
//		
	}
}
