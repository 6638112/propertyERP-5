/**   
 * Filename:    Test01.java   
 * @version:    1.0  
 * Create at:   2014年11月6日 上午4:50:05   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年11月6日    shiyl      1.0         1.0 Version   
 */
package test.htmlparse.fetchCityData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Filename: Test01.java
 * 
 * @version: 1.0.0 Create at: 2014年11月6日 上午4:50:05 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月6日 shiyl 1.0 1.0 Version
 */
public class Test01 {
	public static void main(String[] args) {
//		String input = "first {test} is here,two {test2} is here!";
//		Scanner scanner = new Scanner(input);
//		scanner.findInLine("first (.+) is here,two (.+) is here!");
//		MatchResult result = scanner.match();
//		System.out.println(result.group());

		String str = "a href='37.html";
		Pattern p = Pattern.compile("\\d*");
		Matcher m = p.matcher(str);
		while (m.find()) {
			if (!"".equals(m.group())){
				System.out.println(m.group());
				break;
			}
		}
	}
}
