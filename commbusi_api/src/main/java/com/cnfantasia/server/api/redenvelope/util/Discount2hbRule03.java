/**   
* Filename:    Discount2hbRule03.java   
* @version:    1.0  
* Create at:   2014年12月31日 上午9:05:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.util;

/**
 * Filename:    Discount2hbRule03.java
 * @version:    1.0.0
 * Create at:   2014年12月31日 上午9:05:45
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月31日       shiyl             1.0             1.0 Version
 */
public class Discount2hbRule03 extends AbstractDiscount2hbRule{
	public static void main(String[] args) {
//		System.out.println(new Discount2hbRule03().getMoneyByDiscount(1.2));
//		System.out.println(new Discount2hbRule03().getMoneyByDiscount(2.2));
//		System.out.println(new Discount2hbRule03().getMoneyByDiscount(3.2));
//		System.out.println(new Discount2hbRule03().getMoneyByDiscount(8.2));
	}
	
	@Override
	public Long getMoneyByDiscount(Double discount) {
		if(discount==null||discount<0||discount>=10){return 0L;}
		if(discount>=0&&discount<1){return 20*100L;}
		if(discount>=1&&discount<5){return 10*100L;}
		if(discount>=5&&discount<8){return 5*100L;}
		if(discount>=8&&discount<9){return 2*100L;}
		if(discount>=9&&discount<10){return 1*100L;}
		return 0L;
	}

}
