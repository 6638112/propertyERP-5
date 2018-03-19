///**   
// * Filename:    TestFetchProcince.java   
// * @version:    1.0  
// * Create at:   2014年11月5日 上午7:28:18   
// * Description:  
// *   
// * Modification History:   
// * Date        Author      Version     Description   
// * ----------------------------------------------------------------- 
// * 2014年11月5日    shiyl      1.0         1.0 Version   
// */
//package test.htmlparse.fetchCityData;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.httpclient.Header;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.HttpStatus;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.commons.httpclient.params.HttpMethodParams;
//import org.htmlparser.Node;
//import org.htmlparser.NodeFilter;
//import org.htmlparser.Parser;
//import org.htmlparser.util.NodeList;
//import org.htmlparser.util.ParserException;
//
//import test.htmlparse.EncodeUtil;
//import test.stringCut.StringCutUtil;
//
///**
// * Filename: TestFetchProcince.java
// * 
// * @version: 1.0.0 Create at: 2014年11月5日 上午7:28:18 Description:
// * 
// *           Modification History: Date Author Version Description
// *           ------------------------------------------------------------------
// *           2014年11月5日 shiyl 1.0 1.0 Version
// */
//public class TestFetchProcince {
//	public static final String BASEURL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013";
//	@SuppressWarnings({ "unused", "serial" })
//	public static void main(String[] args) throws ParserException, HttpException, IOException {
//		List<CityDataEntity> curr = new ArrayList<CityDataEntity>();
//		String url = BASEURL+"/index.html";
//		String encoding = "ISO-8859-1";
//		HttpClient client = new HttpClient();
//		GetMethod getMethod = new GetMethod(url);
//		{
//			getMethod.addRequestHeader(new Header("Host", "www.stats.gov.cn"));
//			getMethod.addRequestHeader(new Header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:29.0) Gecko/20100101 Firefox/29.0"));
//			getMethod.addRequestHeader(new Header("Accept", "*/*"));
////			getMethod.addRequestHeader(new Header("Accept-Encoding","GB2312, deflate"));//
////			 getMethod.addRequestHeader(new Header("Accept-Encoding","gzip, deflate"));//gzip 是一种数据格式，deflate是压缩算法
//			getMethod.addRequestHeader(new Header("Cookie", "AD_RS_COOKIE=20080917"));
//			getMethod.addRequestHeader(new Header("Connection", "keep-alive"));
//			getMethod.addRequestHeader(new Header("Pragma", "no-cache"));
//			getMethod.addRequestHeader(new Header("Cache-Control", "no-cache"));
//		}
//		{
//			// getMethod.setParams(params);
//			getMethod.getParams().setHttpElementCharset(encoding);
//			getMethod.getParams().setUriCharset(encoding);
//			getMethod.getParams().setContentCharset(encoding);
//			getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
////			getMethod.addRequestHeader("Content-Type", "text/html; charset=gb2312");
////			getMethod.setRequestHeader("Content-Type", "text/html; charset=gb2312");
//			// 设置编码格式
//			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
//			client.getParams().setContentCharset(encoding);
//			client.getParams().setHttpElementCharset(encoding);
//			client.getParams().setUriCharset(encoding);
//		}
//		/* 执行方法 */
//		try {
//			int statusCode = client.executeMethod(getMethod);
//			if (statusCode != HttpStatus.SC_OK) {
//				System.err.println("Method failed: " + getMethod.getStatusLine());
//			}
//			/* 获得返回的结果 */
//			String responseHtmlStr = null;
//			{
//				String data = getMethod.getResponseBodyAsString();
//				{
//					String step01 = new String(data.getBytes("UTF-8"));
//					String step02 = new String(step01.getBytes("ISO-8859-1"), "GB2312");
////					System.out.println(step02);
//					System.out.println("data ="+EncodeUtil.getEncoding(data));
//					System.out.println("step01 ="+EncodeUtil.getEncoding(step01));
//					System.out.println("step02 ="+EncodeUtil.getEncoding(step02));
//					responseHtmlStr = step02;
//				}
//			}
//			//开始解析数据
//			System.err.println("开始解析数据...");
//			Parser parser = Parser.createParser(responseHtmlStr, "GB2312");
//			NodeList nodes = parser.extractAllNodesThatMatch(new NodeFilter() {
//				public boolean accept(Node node) {
//					return true;
////					if(!res){
////						if(node.toHtml().contains("resultset")){
////							System.out.println(node.toHtml());
////							res =  true;
////						}
////					}
////					return res;
//				}
//			});
//			for(int i=0;i<nodes.size();i++){
//				Node node = nodes.elementAt(i);
//				String currStr = BaseFetchUtil.nodeToString(node);
//				if(currStr.contains("table class='provincetable'")){
//					System.out.println("==="+currStr);
//					System.out.println(node.getChildren().size());
//					NodeList tbody = node.getChildren();
//					for(int k=0;k<tbody.size();k++){
//						Node tbody_tr = tbody.elementAt(k);//<tr class="provincetr">
//						String tbody_tr_Str = BaseFetchUtil.nodeToString(tbody_tr);
//						if(tbody_tr_Str.contains("class='provincetr'")){
//							NodeList tbody_tr_child = tbody_tr.getChildren();
//							for(int m=0;m<tbody_tr_child.size();m++){
//								Node tbody_tr_td = tbody_tr_child.elementAt(m);//<td>
//								NodeList tbody_tr_td_child = tbody_tr_td.getChildren();
//								for(int n=0;n<tbody_tr_td_child.size();n++){
//									Node tbody_tr_td_ahref = tbody_tr_td_child.elementAt(n);
//									String tbody_tr_td_ahref_Str = BaseFetchUtil.nodeToString(tbody_tr_td_ahref);
//									String provinceName = BaseFetchUtil.nodeToString(tbody_tr_td_ahref.getFirstChild());
//									String provinceCode = StringCutUtil.fethchFirstMatcher(tbody_tr_td_ahref_Str, "\\d*");
//									System.out.println(provinceCode+"---"+provinceName);
//									System.out.println("Sub url is:"+BASEURL+"/"+provinceCode+".html");
//								}
//							}
//						}
//					}
//				}
//			}
//		}finally {
//			getMethod.releaseConnection();
//		}
//	}
//
//}
