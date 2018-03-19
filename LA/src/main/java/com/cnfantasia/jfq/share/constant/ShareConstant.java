package com.cnfantasia.jfq.share.constant;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.springframework.security.web.header.writers.frameoptions.StaticAllowFromStrategy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.httpcllient.SimpleHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.HttpUtil;

public class ShareConstant {
	/**
	 * 分享图标
	 */
	public static String shareIcon = "http://www.jiefangqu.com/LA/shareDiscount/images/shareIcon.png";

	/**
	 * 分享标题
	 */
	public static String shareTitle = "我领了一个大粮票，分你一个";
	/**
	 * 拼购分享标题
	 */
	public static String shareFightTitle="解放区拼购，快来拼吧！！";
	
	/**
	 * 派奖类型=={1:"解放区粮票,消费券";2:"商品";3:"怡宝券";4:"58家政券"}
	 * */
	public static class Record_Type {
		/** 解放区粮票,消费券 */
		public static int jfq_hb = 1;
		/** 商品 */
		public static int jfq_sp = 2;
		/** 怡宝券 */
		public static int jfq_yb = 3;
		/** 58家政券 */
		public static int jfq_58 = 4;
		/** 电影票 */
		public static int jfq_film = 5;
		/** 租车券 */
		public static int jfq_zuche = 6;
		/** 市场运营专项 */
		public static int jfq_market = 99;
	}

	/**
	 * 获取分享描述
	 * 
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String getShareDesc() {
		String shareDesc = "砸金蛋，领粮票，我领了，你快砸！";
		HttpUtil httpUtil = new HttpUtil();
		httpUtil.addParameter("codeList", "['SHARE_SUPRISE_TXT']");
		String returnInfo;
		

		try {
			SimpleHttpClient simpleHttpClient = (SimpleHttpClient) CnfantasiaCommbusiUtil.getBeanManager("simpleHttpClient");
			returnInfo = httpUtil.post(simpleHttpClient.getBaseURL() + "/operation/qryOperationMulti.json", 5000);
			Map dateValue = (Map) JSON.parseObject(returnInfo, JsonResponse.class).getDataValue();
			List list = (List) dateValue.get("list");
			JSONObject object = (JSONObject) list.get(0);

			shareDesc = object.getString("content");
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return shareDesc;
	}
	
	/**
	 * 轻应用拼购分享描述
	 * @return
	 */
	public static String getShareFightDesc(){
		String shareDesc = "解放区超市商品拼购，快来拼吧！！";
		HttpUtil httpUtil = new HttpUtil();
		httpUtil.addParameter("codeList", "['SHARE_SUPRISE_TXT']");
		String returnInfo;
		try {
			SimpleHttpClient simpleHttpClient = (SimpleHttpClient) CnfantasiaCommbusiUtil.getBeanManager("simpleHttpClient");
			returnInfo = httpUtil.post(simpleHttpClient.getBaseURL() + "/operation/qryOperationMulti.json", 5000);
			Map dateValue = (Map) JSON.parseObject(returnInfo, JsonResponse.class).getDataValue();
			List list = (List) dateValue.get("list");
			JSONObject object = (JSONObject) list.get(0);

			shareDesc = object.getString("content");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return shareDesc;
	}

	public static void main(String[] args) {
		System.out.println(getShareDesc());
	}
}
