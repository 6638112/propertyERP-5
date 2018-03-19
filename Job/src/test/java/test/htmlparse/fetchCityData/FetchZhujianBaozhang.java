///**   
// * Filename:    FetchZhujianBaozhang.java   
// * @version:    1.0  
// * Create at:   2014年6月18日 上午1:30:26   
// * Description:  
// *   
// * Modification History:   
// * Date        Author      Version     Description   
// * ----------------------------------------------------------------- 
// * 2014年6月18日    shiyl      1.0         1.0 Version   
// */
//package test.htmlparse.fetchCityData;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
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
///**
// * Filename: FetchZhujianBaozhang.java
// * 
// * @version: 1.0.0 Create at: 2014年6月18日 上午1:30:26 Description:
// * 
// *           Modification History: Date Author Version Description
// *           ------------------------------------------------------------------
// *           2014年6月18日 shiyl 1.0 1.0 Version
// */
//public class FetchZhujianBaozhang {
//	private static List<TableEntity> allData = new ArrayList<TableEntity>();
//	public static void main(String[] args) throws ParserException {
//		for(int i=0;i<=148;i++){
//			allData.addAll(pageQuery(i));
//		}
//		System.out.println("========ok======");
//		for(int i=0;i<allData.size();i++){
//			System.out.println(allData.get(i));
//		}
//	}
//	
//	public static String nodeToString(Node node) throws UnsupportedEncodingException{
//		String currStr = new String(node.getText());
//		return currStr;
//	}
//	
//	@SuppressWarnings("serial")
//	public static List<TableEntity> pageQuery(int index) throws ParserException{
//		List<TableEntity> curr = new ArrayList<TableEntity>();
//		String url = "http://61.144.226.3:92/WyjgInsideWeb/e-business/prg/signup/Creditlist.jsp?EventID=InterpriseQuery&pages="+index;
//		String encoding = "GBK";	
//		HttpClient client = new HttpClient();
//		GetMethod getMethod = new GetMethod(url);
//		{
//			Header header1 = new Header("Host", "61.144.226.3:92");
//			Header header2 = new Header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:29.0) Gecko/20100101 Firefox/29.0");
//			Header header3 = new Header("Accept", "*/*");
//			Header header4 = new Header("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//			Header header5 = new Header("Accept-Encoding", "GBK, deflate");
//			// Header header5 = new Header("Accept-Encoding", "gzip, deflate");
//			Header header6 = new Header("Referer",
//					"http://61.144.226.3:92/WyjgInsideWeb/e-business/prg/signup/Creditlist.jsp?EventID=InterpriseQuery&pages="+index);
//			Header header7 = new Header("Cookie",
//					"JSESSIONID=TgqcpxxmmSH1v6h4kYwWTsWnMKYx19DyKytmVnN73J5md8LsZJTM!-1511555420");
//			Header header8 = new Header("Connection", "keep-alive");
//			getMethod.addRequestHeader(header1);
//			getMethod.addRequestHeader(header2);
//			getMethod.addRequestHeader(header3);
//			getMethod.addRequestHeader(header4);
//			getMethod.addRequestHeader(header5);
//			getMethod.addRequestHeader(header6);
//			getMethod.addRequestHeader(header7);
//			getMethod.addRequestHeader(header8);
//		}
//		{
//			// getMethod.setParams(params);
//			getMethod.getParams().setHttpElementCharset(encoding);
//			getMethod.getParams().setUriCharset(encoding);
//			getMethod.getParams().setContentCharset(encoding);
//			getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
//			// getMethod.addRequestHeader("Content-Type","text/html;charset="+encoding);
//			// getMethod.setRequestHeader("Content-Type",
//			// "text/html;charset="+encoding);
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
//			byte[] responseBody = getMethod.getResponseBody();
//			String resStr = new String(responseBody, "GBK");
////			String p = resStr.replaceAll("//&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");// 去掉网页中带有html语言的标签
//			System.out.println(resStr);
//			Parser parser = Parser.createParser(resStr, "GBK");
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
//				//id='resultset'
//				String currStr = nodeToString(node);
//				if(currStr.contains("id='resultset'")){
//					System.out.println("--------"+currStr);
//					System.out.println(node.getChildren().size());
//					NodeList a001 = node.getChildren();
//					for(int k=0;k<a001.size();k++){
//						Node tmpNode = a001.elementAt(k);
////						System.out.println("==="+nodeToString(tmpNode));
//						if(tmpNode==null){continue;}
//						NodeList a002=tmpNode.getChildren();
//						if(a002==null){continue;}
//						for(int j=0;j<a002.size();j++){
////							System.out.println(nodeToString(a002.elementAt(j)));
//							Node node003=a002.elementAt(j);
//							if(node003==null){continue;}
//							NodeList a003=node003.getChildren();
//							if(a003==null){continue;}
////							System.out.println(a003.size());
//							if(a003.elementAt(7)==null){continue;}
//							TableEntity tableEntity = new TableEntity();
//							tableEntity.setIndex(nodeToString(a003.elementAt(3).getChildren().elementAt(0)));
//							try {
//								Node what = a003.elementAt(5).getChildren().elementAt(0)
//										.getChildren().elementAt(1);
//								tableEntity.setCompany(
//										what.getText()//font color='#333333' style='text-decoration:underline
//											);
//							} catch (Exception e) {
//							}
//							try {tableEntity.setCode(nodeToString(a003.elementAt(7).getChildren().elementAt(0)));} catch (Exception e) {}
//							try {tableEntity.setLevel(nodeToString(a003.elementAt(9).getChildren().elementAt(0)));} catch (Exception e) {}
//							try {tableEntity.setName(nodeToString(a003.elementAt(11).getChildren().elementAt(0)));} catch (Exception e) {}
//							try {tableEntity.setTel(nodeToString(a003.elementAt(13).getChildren().elementAt(0)));} catch (Exception e) {}
//							System.out.println(tableEntity);
//							curr.add(tableEntity);
////							for(int m=0;m<a003.size();m++){
////								if(a003.elementAt(m)==null){continue;}
////								NodeList a004=a003.elementAt(m).getChildren();
////								if(a004==null){continue;}
//////								System.out.println(a004.size());
////								for(int q=0;q<a004.size();q++){
////									System.out.println(m+" ="+q+"  "+nodeToString(a004.elementAt(q)));
////								}
////							}
//						}
//					}
//					continue;
//				}
////				System.out.println("---------------"+new String(nodes.elementAt(i).toPlainTextString().getBytes("GBK")));
//			}
//			
////			System.out.println(new String(node.toPlainTextString().getBytes("GBK")));
//			
////			System.out.println(p);
//		} catch (HttpException e) {
//			System.err.println("Fatal protocol violation: " + e.getMessage());
//			e.printStackTrace();
//		} catch (IOException e) {
//			System.err.println("Fatal transport error: " + e.getMessage());
//			e.printStackTrace();
//		} finally {
//			getMethod.releaseConnection();
//		}
//		return curr;
//	}
//
//}
