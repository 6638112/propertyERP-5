package test;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cnfantasia.server.business.TmpData;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;

public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {
//		AbstractClassGenerator a = null;
		
//		{
////		org.springframework.jdbc.datasource.DataSourceTransactionManager a = null;
////		org.apache.commons.dbcp.BasicDataSource b = null;
//			Map<String,Object> map = new HashMap<String, Object>();
//			String[] datas = new String[3];
//			datas[0]="/a/001.jpg";
//			datas[1]="/a/001.jpg";
//			datas[2]="/a/001.jpg";
//			map.put("url", datas);
//			System.out.println(JSON.toJSONString(map));
//		}
		
//			{
//	//			String url = "http://cyz.colourlife.com/site/colourdRooms?build_id=HYN-DG- ZZ";
//				String url = "http://cyz.colourlife.com/site/colourdRooms?build_id=";
//				url+=URLEncoder.encode("ZZS-THY-东门","UTF-8");
//				url = url.replaceAll("\\+", "%20");
//				System.out.println(url);
////				String newUrl = URLEncoder.encode(url,"UTF-8");
////				System.out.println(newUrl);
//				JSONArray realRoomList = HttpClientTest.doPost(url);
//				System.out.println(realRoomList);
//			}
		
//		{
//			String url="http://cyz.colourlife.com/site/colourdBuilds";
//			HttpMethodParams params= new HttpMethodParams();
//			params.setParameter("community_id", "112");
//			JSONArray list = HttpClientTest.doPost(url,params);
//			System.out.println(list);
//		}
//		{
//			String url="http://cyz.colourlife.com/site/colourdBuilds?community_id=112";
//			JSONArray list = HttpClientTest.doPost(url);
//			System.out.println(list);
//		}
//		{
//			String entityValue = URLEncodedUtils.format(parameters, HTTP.UTF_8);
//			// Do your replacement here in entityValue
//			StringEntity entity = new StringEntity(entityValue, HTTP.UTF_8);
//			entity.setContentType(URLEncodedUtils.CONTENT_TYPE);
//			// And now do your posting of this entity
//		}
		
//		{
//			Double starLevel = 3.1666;
//			System.out.println(Double.valueOf(CommentsConstant.Default_Comments_Level_Format.format(starLevel)));
//		}
		
//		{
//			Map<String,Integer> tmpMap = new HashMap<String, Integer>();
//			tmpMap.put("50001", 1);
//			tmpMap.put("50002", 1);
//			tmpMap.put("63452", 2);
//			System.out.println(JSON.toJSONString(tmpMap));
//		}
//		{
//			List<String> list = new ArrayList<String>();
//			list.add("53010");
//			list.add("53011");
//			list.add("53012");
//			System.out.println(JSON.toJSONString(list));
//		}
		
//			Date date = new Date(1427817600000L);
//			System.out.println(DateUtil.formatSecond.get().format(date));
		
		TmpData.main(null);
		
		System.out.println(java.nio.charset.Charset.defaultCharset().toString()); 
		System.out.println("搞Java".getBytes().length);  
		System.out.println("搞Java".getBytes("GB2312").length);  
		System.out.println("13145826351".getBytes().length);  
		
		
		   Pattern pattern =Pattern.compile("value=\"(.+?)\"");

		    //通配符中也要加入转移字符 (.+?)代表要查找的内容

		   String s = "asfdasfda<input type=\"button\" value=\"http://t.cn\" />";
		    Matcher matcher=pattern.matcher(s);

		    while(matcher.find())

		    {

		       System.out.println(matcher.group(1));

		    }
	}
	
	public static void bb(){
		int b=0;
		aa();
		for(int i=0;i<10;i++){
			System.out.println(i);
			if(i==9){
				i=0;
				b++;
				System.out.println(b);
			}
		}
	}
	public static void aa(){
		System.out.println("111");
		throw new BusinessRuntimeException();
	}
	
}
