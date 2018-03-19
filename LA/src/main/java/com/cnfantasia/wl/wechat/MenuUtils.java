package com.cnfantasia.wl.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.wl.wechat.util.AccessTokenGetter;

public class MenuUtils {


	/**
	 * 创建Menu
	 * 
	 * @Title: createMenu
	 * @Description: 创建Menu
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static String createMenu() {
//		String baseApiPath = "http://api.jiefangqu.com:8080";
		String baseLaPath = "http%3a%2f%2fwww.jiefangqu.com%2fLA";
		String baseDownPath = "http://app.jiefangqu.com/";
		return createMenuCommon(baseLaPath);
		/*String requestURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&" + "redirect_uri=REDIRECT_URI"
				+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

		String requestProductURL = requestURL.replace("APPID", WeChatConfig.APPID);
		requestProductURL = requestProductURL.replace("REDIRECT_URI", baseLaPath+"%2fproduct%2findex.do");

		//五户活动 
		//String FiveUserActivityURL = baseApiPath+"/htmlPages/activity_plan20/activity_20.htm";
		
		//个人中心
		String personalCenterURL = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI",
				baseLaPath+"%2fdredge%2fpersonalCenter.do");
		
		//约师傅
		String requestRepaireURL = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI",
				baseLaPath+"%2fdredge%2flistWeiXiuHomeType.do");
		

		//社区大V
		//String sheQuDaV = "http://api.jiefangqu.com:8080/API/htmlPages/zjxs/zjxs.htm";

		String requestPrizeURL = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI",
				baseLaPath+"%2fprize%2findex.do");

		//String buTie = requestURL.replace("APPID", WeChatConstant.APPID).replace("REDIRECT_URI", "http%3a%2f%2fwww.jiefangqu.com%2fLA%2fbuTie.jsp");

//		String orderList = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI",
//				baseLaPath+"%2forder%2fqryOrderList.do");

		//String hongBao = requestURL.replace("APPID", WeChatConstant.APPID).replace("REDIRECT_URI", "http%3a%2f%2fwww.jiefangqu.com%2fLA%2fhongBao.jsp");
		
		//String download = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", "http%3a%2f%2fwww.jiefangqu.com%2fmobi%2f");
		String download = baseDownPath;

		//String zhaoPing = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", "http%3a%2f%2fwww.jiefangqu.com%2fLA%2fzhaoPing.jsp");
		String zhaoPing = "http://mp.weixin.qq.com/s?__biz=MzAwMzAzNDU0OQ==&mid=207381345&idx=4&sn=9186ebd931999a5be804be770598f63e#rd";
		//联系我们
		String linkUs = "http://mp.weixin.qq.com/s?__biz=MzAwMzAzNDU0OQ==&mid=207431809&idx=1&sn=6523db34d490c8cf60c8835d2fc92e6e#rd";
		//常见问题
		String FAQ = "http://mp.weixin.qq.com/s?__biz=MzAwMzAzNDU0OQ==&mid=207438394&idx=1&sn=adf8e7953b208acb848140d240e5c32e#rd";
		//我的消费券
		String myCoupon = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", baseLaPath+"%2fprize%2flist.do");
		
		//有钱赚，钱生花
		String p2p = "http://m.hehenian.com/product/index.do?channel=8";

		String menu = "{\"button\":[" + "{\"name\":\"惊喜\",\"sub_button\":[{\"type\":\"view\",\"name\":\"有钱赚\",\"url\":\"" + p2p + "\"}]},"
				+ "{\"name\":\"服务\",\"sub_button\":[{\"type\":\"view\",\"name\":\"逛超市\",\"url\":\"" + requestProductURL
				+ "\"},{\"type\":\"view\",\"name\":\"约师傅\",\"url\":\"" + requestRepaireURL
				+ "\"},{\"type\":\"view\",\"name\":\"个人中心\",\"url\":\"" + personalCenterURL
						+ "\"}]},"
				+ "{\"name\":\"我们\",\"sub_button\":[{\"type\":\"view\",\"name\":\"APP下载\",\"url\":\"" + download
				+ "\"},{\"type\":\"view\",\"name\":\"联系合作\",\"url\":\"" + linkUs
 + "\"},{\"type\":\"view\",\"name\":\"常见问题\",\"url\":\"" + FAQ
				+ "\"},{\"type\":\"view\",\"name\":\"招聘信息\",\"url\":\"" + zhaoPing + "\"}]}]}";

		//此处改为自己想要的结构体，替换即可
		String access_token = AccessTokenGetter.getAccess_token();
		String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token;
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒 
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(menu.getBytes("UTF-8"));//传入参数    
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return "返回信息" + message;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "createMenu 失败";*/
	}

	
	/**
	 * 创建测试 Menu
	 * 
	 * @Title: createMenu
	 * @Description: 创建Menu
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static String createTestMenu() {
//		String baseApiPath = "http://api.jiefangqu.com:8080";
//		String baseLaPath = "http%3a%2f%2fhynwl.ngrok.cc%2fLA";
		String baseLaPath = "http%3a%2f%2fla.linlile.cn%2fLA";
		//String baseLaPath = "http%3a%2f%2fla.linlile.com.cn%2fLA";
//		String baseDownPath = "http://app.jiefangqu.com/";
		return createMenuCommon(baseLaPath);
	}
	/**
	 * 创建模拟Menu
	 * 
	 * @Title: createMenu
	 * @Description: 创建Menu
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static String createSimMenu() {
//		String baseApiPath = "http://api.jiefangqu.com:8080";
		String baseLaPath = "http%3a%2f%2fla.jiefangqu.cn%2fLA";
//		String baseDownPath = "http://app.jiefangqu.com/";
		return createMenuCommon(baseLaPath);
	}
	
	/**
	 * 创建菜单
	 * @param baseLaPath 轻应用basepath
	 * @return
	 */
	public final static String createMenuCommon(String baseLaPath){
		String requestURL = new StringBuilder()
							.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&")
							.append("redirect_uri=REDIRECT_URI")
							.append("&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect").toString();

		/* 1、最新活动 */
		/*// 1.1、社区大V
		String sheQuDaV = "http://api.jiefangqu.com:8080/API/htmlPages/zjxs/zjxs.htm";
		// 1.2、参与抽奖
		String requestPrizeURL = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", baseLaPath+"%2fprize%2findex.do");
		// 1.3、我的消费券
		String myCoupon = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", baseLaPath+"%2fprize%2flist.do");*/
		// 有钱赚
		String p2p = "http://m.hehenian.com/product/index.do?channel=8";
		
		/* 2、社区活动 */
		// 2.1、月卡续费
		String requestpayCarFeeURL = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", baseLaPath+"%2fpayCarFee%2findex.do");
		// 2.2、逛超市
		String requestProductURL = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", baseLaPath+"%2fproduct%2findex.do");
		// 2.3、保修维修
		String requestRepaireURL = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", baseLaPath+"%2fdredge%2flistWeiXiuHomeType.do");
		// 2.4、个人中心
		String personalCenterURL = requestURL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", baseLaPath+"%2fdredge%2fpersonalCenter.do");
		
		/* 3、关于我们 */
		// 3.1、解放区APP
		String download = "http://app.jiefangqu.com/";
		// 3.2、联系合作
		String linkUs = "http://mp.weixin.qq.com/s?__biz=MzAwMzAzNDU0OQ==&mid=207431809&idx=1&sn=6523db34d490c8cf60c8835d2fc92e6e#rd";
		// 3.3、招聘
		String zhaoPing = "http://mp.weixin.qq.com/s?__biz=MzAwMzAzNDU0OQ==&mid=207381345&idx=4&sn=9186ebd931999a5be804be770598f63e#rd";

		String menu = new StringBuilder()
					  .append("{\"button\":[")
					  .append("{\"name\":\"惊喜\",\"sub_button\":[")
					  /*.append("{\"type\":\"view\",\"name\":\"社区大V\",\"url\":\"").append(sheQuDaV).append("\"},")
					  .append("{\"type\":\"view\",\"name\":\"参与抽奖\",\"url\":\"").append(requestPrizeURL).append("\"},")
					  .append("{\"type\":\"view\",\"name\":\"我的消费券\",\"url\":\"").append(myCoupon).append("\"}")*/
					  .append("{\"type\":\"view\",\"name\":\"有钱赚\",\"url\":\"").append(p2p).append("\"}")
					  .append("]},")
					  .append("{\"name\":\"社区服务\",\"sub_button\":[")
					  //.append("{\"type\":\"view\",\"name\":\"月卡续费\",\"url\":\"").append(requestpayCarFeeURL).append("\"},")
					  .append("{\"type\":\"view\",\"name\":\"逛超市\",\"url\":\"").append(requestProductURL).append("\"},")
					  .append("{\"type\":\"view\",\"name\":\"报修维修\",\"url\":\"").append(requestRepaireURL).append("\"},")
					  .append("{\"type\":\"view\",\"name\":\"个人中心\",\"url\":\"").append(personalCenterURL).append("\"}")
					  .append("]},")
					  .append("{\"name\":\"关于我们\",\"sub_button\":[")
					  .append("{\"type\":\"view\",\"name\":\"解放区APP\",\"url\":\"").append(download).append("\"},")
					  .append("{\"type\":\"view\",\"name\":\"联系合作\",\"url\":\"").append(linkUs).append("\"},")
					  .append("{\"type\":\"view\",\"name\":\"招聘信息\",\"url\":\"").append(zhaoPing).append("\"}")
					  .append("]}")
					  .append("]}").toString();

		//此处改为自己想要的结构体，替换即可
		String access_token = AccessTokenGetter.getAccess_token();
		String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token;
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒 
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(menu.getBytes("UTF-8"));//传入参数    
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return "返回信息" + message;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "createMenu 失败";
	}

	/**
	 * 删除当前Menu
	 * 
	 * @Title: deleteMenu
	 * @Description: 删除当前Menu
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String deleteMenu() {
		String access_token = AccessTokenGetter.getAccess_token();
		String action = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + access_token;
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒 
			http.connect();
			OutputStream os = http.getOutputStream();
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return "deleteMenu返回信息:" + message;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "deleteMenu 失败";
	}

	public static void main(String[] args) {
		System.out.println(createMenu());
//		System.out.println(createTestMenu());
	}
}
