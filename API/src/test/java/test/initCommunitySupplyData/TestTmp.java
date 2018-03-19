/**   
* Filename:    TestTmp.java   
* @version:    1.0  
* Create at:   2014年9月17日 上午2:39:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月17日    shiyl      1.0         1.0 Version   
*/
package test.initCommunitySupplyData;

import java.util.ArrayList;
import java.util.List;

/**
 * Filename:    TestTmp.java
 * @version:    1.0.0
 * Create at:   2014年9月17日 上午2:39:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月17日       shiyl             1.0             1.0 Version
 */
public class TestTmp {
	public static void main(String[] args) {
		String contectUserPhone ="0755-83407322/0755-83410072";
		String[] pho = contectUserPhone.split("/");
		List<String> phoList = new ArrayList<String>();
		for(int aa=0;aa<pho.length;aa++){
			phoList.add(pho[aa].trim());
		}
		System.out.println(phoList);
	}
}
