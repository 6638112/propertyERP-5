/**   
* Filename:    Test.java   
* @version:    1.0  
* Create at:   2015年1月16日 上午9:19:49   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月16日    shiyl      1.0         1.0 Version   
*/
package test.jsonparse;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

/**
 * Filename:    Test.java
 * @version:    1.0.0
 * Create at:   2015年1月16日 上午9:19:49
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月16日       shiyl             1.0             1.0 Version
 */
public class Test {
	public static void main(String[] args) {
		String src = "[{\"id\":100214,\"tEbuyProductFId\":100029,\"tPropName\":\"配送\",\"tPropValue\":\"当日20:00前下单，次日送达\"}]";
		List<EbuyProductParametersNew> tmpList = JSONArray.parseArray(src, EbuyProductParametersNew.class);
		String res = tmpList.get(0).gettPropValue();
		System.out.println(res);
	}
}
