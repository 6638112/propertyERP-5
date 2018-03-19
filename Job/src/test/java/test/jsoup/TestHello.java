/**   
 * Filename:    TestHello.java   
 * @version:    1.0  
 * Create at:   2014年11月25日 上午8:56:58   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年11月25日    shiyl      1.0         1.0 Version   
 */
package test.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Filename: TestHello.java
 * 
 * @version: 1.0.0 Create at: 2014年11月25日 上午8:56:58 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月25日 shiyl 1.0 1.0 Version
 *           
 *           中文文档地址：http://www.open-open.com/jsoup/working-with-urls.htm
 *           官网地址 http://jsoup.org/
 *           房产地址 http://www.szpl.gov.cn/xxgk/fdcgl/
 *           一手房：http://ris.szpl.gov.cn/bol/index.aspx
 *           二手房：http://ris.szpl.gov.cn/bol/essource.aspx
 *           深圳搜房网、
 *           
 *           
 *           http://developer.baidu.com/map/index.php?title=webapi/guide/webservice-geocoding 根据名字获取坐标 6.地理编码服务
 *           http://developer.baidu.com/map/index.php?title=webapi/guide/webservice-placeapi 
 *           
 *           
 *           http://api.map.baidu.com/place/v2/search?ak=您的密钥&output=json&query=%E9%93%B6%E8%A1%8C&page_size=10&page_num=0&scope=1&location=22.534,114.04&radius=2000
 *           http://api.map.baidu.com/geocoder/v2/?ak=E4805d16520de693a3fe707cdc962045&callback=renderReverse&location=39.983424,116.322987&output=xml&pois=1
 */
public class TestHello {
	public static void main(String[] args) throws IOException {
		// {
		// // Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
		// Document doc = Jsoup.connect("http://192.168.1.31:8090/").get();
		// Elements elementsList = doc.getElementsByTag("a");
		// for(Element tmpElemect:elementsList){
		// System.out.println(tmpElemect.html());
		// }
		// System.out.println(JSON.toJSONString(doc));
		// }
		{
			test02();
		}
	}
	@SuppressWarnings("unused")
	public static void test01() {
		String html = "<html><head><title>First parse</title></head>"
				+ "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse(html);
	}

	public static void test02() {
		String html = "<html><head><title>First parse</title></head><div><p>Lorem ipsum.</p></html>";
		Document doc = Jsoup.parseBodyFragment(html);
		Element body = doc.body();
		System.out.println(body.html());
	}
	@SuppressWarnings("unused")
	public static void test03() throws IOException {
		File input = new File("/tmp/input.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		Elements links = doc.select("a[href]"); // 带有href属性的a元素
		Elements pngs = doc.select("img[src$=.png]");
		// 扩展名为.png的图片
		Element masthead = doc.select("div.masthead").first();
		// class等于masthead的div标签
		Elements resultLinks = doc.select("h3.r > a"); // 在h3元素之后的a元素
	}

	@SuppressWarnings("unused")
	public static void test04() {
		String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
		Document doc = Jsoup.parse(html);// 解析HTML字符串返回一个Document实现
		Element link = doc.select("a").first();// 查找第一个a元素

		String text = doc.body().text(); // "An example link"//取得字符串中的文本
		String linkHref = link.attr("href"); // "http://example.com/"//取得链接地址
		String linkText = link.text(); // "example""//取得链接地址中的文本

		String linkOuterH = link.outerHtml();
		// "<a href="http://example.com"><b>example</b></a>"
		String linkInnerH = link.html(); // "<b>example</b>"//取得链接内的html内容
	}

}
