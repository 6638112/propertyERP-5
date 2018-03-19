/**   
* Filename:    ProductSpecUniqueUtil.java   
* @version:    1.0  
* Create at:   2015年3月22日 上午5:59:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.wl.wechat.util.product;


/**
 * Filename:    ProductSpecUniqueUtil.java
 * @version:    1.0.0
 * Create at:   2015年3月22日 上午5:59:52
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月22日       shiyl             1.0             1.0 Version
 */
public class ProductSpecUniqueUtil {
	public static void main(String[] args) {
		System.out.println(ProductSpecUniqueUtil.getNuiqueStr("asfewf", "蓝色"));
	}
	
	public static String getNuiqueStr(Object size,Object colour){
		StringBuffer sbf = new StringBuffer();
		{
			sbf.append(size==null?"":size.toString());
			sbf.append(",");
		}
		{
			sbf.append(colour==null?"":colour.toString());
			sbf.append(",");
		}
		return sbf.toString();
	}
}
