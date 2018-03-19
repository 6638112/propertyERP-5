package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	//卖家支付宝账号
	public static final String WIDseller_email = "linlile@cnfantasia.com";
	//设置未付款交易的超时时间
	// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
	// 取值范围：1m～15d。
	// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
	// 该参数数值不接受小数点，如1.5h，可转换为90m。
	public static final String it_b_pay = "30m";
	
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static final String partner = "2088711343843097";
	// 商户的私钥
	public static final String private_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANFK7UXV9rWQDLRczxDFDj6cC+Lhmw8aTDb4AWbljl+5W9AXHfI3DmvwsPlEW7cgdtjwvZ2nihkCMxSTLW30nUYenm0YtH+XVMbz2hYC0CZpN98pE7sKCyGfB4V0bUFblYDrI6lZRbcfvQlKYD6iHFdFKYbcZphnEeDbvikOHVbjAgMBAAECgYAPpR01gjgN/HWmqeVkqIZXEc0lqZ2wtFOMLF/WdER/TVjapk+7wnfWg8iG6aA9gTTBJMXX2iVNl0eAOzxDIuz6pTn9Wcyn+/xfO0UuU9SFqs9ruCS6piyb8rwD8jl7Qfo3V2ANvxKu0n6g82azIuaz3JmZe3Cr/V9S3QJ48S8EQQJBAPPlAYQAePvm73/PfmA9LpUo0BZs7xZvI92JZeA2ulY9nWdjNZpMlilyA+fWJSsX+TyoVTI/nzexbiNz+/yMZMMCQQDbrkJ3ffPduQaopCPjblz6TifqwdLauOHVWggNKYr3440a9W7TzPpwwauOWQ5ANZq9BgX6GF8iI8Rgvn/cAaNhAkEA6JAJ7fD1bF49KcaVXvd4H8sXAQtGwEGiFXEar73/2JRQL3Gufz9gQIzzpHHSZ1Eo2+o3sv5vuA98UloagirtOQJBAIq9XYD17QRFDK2JLJlAZDw5DAC5dJ9qUEZcW+VkLT4tWjqCcwIU3L5FskFugQ+QSy/CFBhFOB/pkua+qSWba0ECQQDcK0qEM/gCqPNfOArQt6G1EZkj4AAc6Kw1cUKqYIgr0epP07OwW9FYzp/5op0PQx45ZgKYnDrrG419df7Us7pu";
	
	// 支付宝的公钥，无需修改该值
	public static final String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static final String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static final String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static final String sign_type = "RSA";

}
