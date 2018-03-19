/**   
 * Filename:    StringCutUtil.java   
 * @version:    1.0  
 * Create at:   2014年11月6日 上午4:59:20   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年11月6日    shiyl      1.0         1.0 Version   
 */
package test.stringCut;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Filename: StringCutUtil.java
 * 
 * @version: 1.0.0 Create at: 2014年11月6日 上午4:59:20 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月6日 shiyl 1.0 1.0 Version
 */
public class StringCutUtil {
	public static void main(String[] args) {
		String str = "a href='37.html";
		Pattern p = Pattern.compile("\\d*");
		Matcher m = p.matcher(str);
		while (m.find()) {
			if (!"".equals(m.group())) {
				System.out.println(m.group());
				break;
			}
		}
	}
	public static String fethchFirstMatcher(String data,String regix){
		String resData = null;
		Pattern p = Pattern.compile(regix);
		Matcher m = p.matcher(data);
		while (m.find()) {
			if (!"".equals(m.group())) {
				resData = m.group();
				break;
			}
		}
		return resData;
	}
}
