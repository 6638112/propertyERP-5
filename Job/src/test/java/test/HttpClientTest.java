/** 
 * 参考http://hc.apache.org/httpclient-3.x/tutorial.html 
 */
package test;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * The general process for using HttpClient consists of a number of steps: 1.
 * Create an instance of HttpClient. 2. Create an instance of one of the methods
 * (GetMethod in this case). The URL to connect to is passed in to the the
 * method constructor. 3. Tell HttpClient to execute the method. 4. Read the
 * response. 5. Release the connection. 6. Deal with the response.
 * */
public class HttpClientTest {

	/**
	 * 查询省列表：
	 * http://cyz.colourlife.com/site/regions?region_id=0
	 * 根据省查询市列表
	 * http://cyz.colourlife.com/site/regions?parent_id=14
	 * 根据市查询地区列表
	 * http://cyz.colourlife.com/site/regions?parent_id=167
	 * 根据地区查询小区列表
	 * http://cyz.colourlife.com/site/communities?region_id=1580
	 * 根据小区查询楼栋
	 * http://cyz.colourlife.com/site/builds?community_id=989
	 * 根据楼栋查询房间
	 * http://cyz.colourlife.com/site/colourdRooms?build_id=HYN-BY-2
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) {
			//获取省列表 广东省
			JSONArray provinceList = doPost("http://cyz.colourlife.com/site/regions?region_id=0");
			System.out.println(provinceList);
			for(Object obj1:provinceList){
				JSONObject province  = (JSONObject)obj1;
				String id1 = province.getString("id");
				String name1 = province.getString("name");
				System.out.println(id1+" "+name1);
				//根据省查询市 深圳市
				JSONArray address_cityList = doPost("http://cyz.colourlife.com/site/regions?parent_id="+id1);
				System.out.println(address_cityList);
				for(Object obj2:address_cityList){
					JSONObject address_city  = (JSONObject)obj2;
					String id2 = address_city.getString("id");
					String name2 = address_city.getString("name");
					System.out.println(id2+" "+name2);
					//根据市查询区 宝安区
					JSONArray address_blockList = doPost("http://cyz.colourlife.com/site/regions?parent_id="+id2);
					System.out.println(address_blockList);
					for(Object obj3:address_blockList){
						JSONObject address_block  = (JSONObject)obj3;
						String id3 = address_block.getString("id");
						String name3 = address_block.getString("name");
						System.out.println(id3+" "+name3);
						//根据地区查询小区列表 深圳花郡
						JSONArray group_buildingList = doPost("http://cyz.colourlife.com/site/communities?region_id="+id3);
						System.out.println(group_buildingList);
						for(Object obj4:group_buildingList){
							JSONObject group_building  = (JSONObject)obj4;
							String id4 = group_building.getString("id");
							String name4 = group_building.getString("name");
							System.out.println(id4+" "+name4);
							//根据小区查询楼栋
							JSONArray buildingList = doPost("http://cyz.colourlife.com/site/builds?community_id="+id4);
							System.out.println(buildingList);
							for(Object obj5:buildingList){
								JSONObject building  = (JSONObject)obj5;
								String id5 = building.getString("id");
								String name5 = building.getString("name");
								System.out.println(id5+" "+name5);
								
							}
						}
					}
				}
			}
			
	}
	public static JSONArray doPost(String url){
		System.out.println("Request is:"+url);
		HttpMethodParams params = new HttpMethodParams();
		return doPost(url, params);
	}
	public static JSONArray doPost(String url,HttpMethodParams params){
		String encoding = "UTF-8";
		HttpClient client = new HttpClient();
		JSONArray resArray = null;
		GetMethod getMethod = new GetMethod(url);
		{
			Header header1 = new Header("Host", "cyz.colourlife.com");
			Header header2 = new Header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:29.0) Gecko/20100101 Firefox/29.0");
			Header header3 = new Header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			Header header4 = new Header("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
			Header header5 = new Header("Accept-Encoding", "utf-8, deflate");
			Header header6 = new Header("Cookie", 
					"Hm_lvt_058e9d49a09427ea51ed7fafb0321531=1398234941,1398234943,1398234947,1398235174; "
					+ "customer_user_agent=2780964; CNZZDATA1000022871=538168512-1401258507-http%3A%2F%2Fpassport.colourlife.com%2F|1401512017; "
					+ "WebsiteSession=kfb4gd2ao7vvk5upmgje0elm12; community_id=989");
			Header header7 = new Header("Connection", "keep-alive");
			getMethod.addRequestHeader(header1);
			getMethod.addRequestHeader(header2);
			getMethod.addRequestHeader(header3);
			getMethod.addRequestHeader(header4);
			getMethod.addRequestHeader(header5);
			getMethod.addRequestHeader(header6);
			getMethod.addRequestHeader(header7);
		}
		{
			getMethod.setParams(params);
			getMethod.getParams().setHttpElementCharset(encoding);
			getMethod.getParams().setUriCharset(encoding);
			getMethod.getParams().setContentCharset(encoding);
			getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
//	    getMethod.addRequestHeader("Content-Type","text/html;charset="+encoding);
//	    getMethod.setRequestHeader("Content-Type", "text/html;charset="+encoding);
	    // 设置编码格式
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
			client.getParams().setContentCharset(encoding);
			client.getParams().setHttpElementCharset(encoding);
			client.getParams().setUriCharset(encoding);
		}
		/* 执行方法 */
		try {
			int statusCode = client.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			/* 获得返回的结果 */
			byte[] responseBody = getMethod.getResponseBody();
			String resStr = new String(responseBody,"utf-8");
			System.out.println(resStr);
			resArray = JSON.parseArray(resStr);
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return resArray;
	}
}