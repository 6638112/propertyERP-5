package com.cnfantasia.server.ucpaas.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.json.JSONException;
import org.xml.sax.SAXException;

import com.cnfantasia.server.ucpaas.SysConfig;

public class WelinkMsgUtil {

	private static Log logger = LogFactory.getLog(UcpaasMsgUtil.class);
	
	/**
	 * 用微网通联接口发送短信
	 * @param phone 手机号码
	 * @param shortMsg 短信内容
	 * @return
	 */
	public static boolean sendMsg(String mobile, String shortMsg) {
		String result = "";
		try {
			String sname = SysConfig.getInstance().getProperty("sname");
			String spwd = SysConfig.getInstance().getProperty("spwd");
			String sprdid = SysConfig.getInstance().getProperty("sprdid");
			String serverUrl = SysConfig.getInstance().getProperty("server_url")+"/g_Submit";
			//暂时没有模板，所以写死作为测试使用
			//shortMsg = "您好，您的激活码870592，请输入激活码后，在原账号18063336010的手机短信中点击确认打开链接完成修改，感谢你使用我们的服务。【微网通联】";
			String xmlStr = SMS(mobile, shortMsg, sname, spwd, sprdid, serverUrl);
			Map<String, String> map = doXMLParse(xmlStr);
			
			if(map!=null && "0".equals(map.get("State"))) {
				return true;
			} else {
				return false;
			}
		}  catch (Exception e) {
			logger.error("result-error:" + result);
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * 微信通联信息发送
	 * @param mobile
	 * @param smsg
	 * @param sname
	 * @param spwd
	 * @param sprdid
	 * @param serverUrl
	 * @return
	 */
	 public static String SMS(String mobile, String smsg, String sname, String spwd, String sprdid, String serverUrl) throws UnsupportedEncodingException {
		 String postData = "sname="+sname+"&spwd="+spwd+"&scorpid=&sprdid="+sprdid+"&sdst="+mobile+"&smsg="+smsg;
		 try {
	            //发送POST请求
	            URL url = new URL(serverUrl);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            conn.setRequestProperty("Connection", "Keep-Alive");
	            conn.setUseCaches(false);
	            conn.setDoOutput(true);

	            conn.setRequestProperty("Content-Length", "" + postData.length());
	            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	            out.write(postData);
	            out.flush();
	            out.close();

	            //获取响应状态
	            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	                System.out.println("connect failed!");
	                return "";
	            }
	            //获取响应内容体
	            String line, result = "";
	            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	            while ((line = in.readLine()) != null) {
	                result += line + "\n";
	            }
	            in.close();
	            return result;
	        } catch (IOException e) {
	            e.printStackTrace(System.out);
	        }
	        return "";
	 }
	 
	 
	public static void main(String[] args) throws JSONException, ParserConfigurationException, SAXException, IOException {
		//int vcode = 100000 + new Random().nextInt(899999);
		//boolean result = WelinkMsgUtil.sendMsg("18565714990","您好，您的激活码870592，请输入激活码后，在原账号18063336010的手机短信中点击确认打开链接完成修改，感谢你使用我们的服务。【微网通联】");
	}
		
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */	
	public static Map<String, String> doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map<String, String> m = new HashMap<String, String>();
		
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List<?> list = root.getChildren();
		Iterator<?> it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List<?> children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
		
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List<?> children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator<?> it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List<?> list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
}
