/**   
* Filename:    Discount2hbRule01.java   
* @version:    1.0  
* Create at:   2014年6月25日 上午7:23:59   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.util;

/**
 * Filename:    Discount2hbRule01.java
 * @version:    1.0.0
 * Create at:   2014年6月25日 上午7:23:59
 * Description:折扣兑粮票规则
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月25日       shiyl             1.0             1.0 Version
 */
public class Discount2hbRule01 extends AbstractDiscount2hbRule{
//	public static void main(String[] args) {
//		Discount2hbRule01 discount2hbRule01 = new Discount2hbRule01();
//		System.out.println(discount2hbRule01.getMoneyByDiscount(5.3));
//		System.out.println(discount2hbRule01.getMoneyByDiscount(1.3));
//		System.out.println(discount2hbRule01.getMoneyByDiscount(3.0));
//		System.out.println(discount2hbRule01.getMoneyByDiscount(4.0));
//		System.out.println(discount2hbRule01.getMoneyByDiscount(9.3));
//	}
	@Override
	public Long getMoneyByDiscount(Double discount) {
		if(discount==null||discount<0||discount>=10){return 0L;}
		else if(discount<1.0){return 95*100L;}
		else if(discount<2.0){return 85*100L;}
		else if(discount<3.0){return 75*100L;}
		else if(discount<4.0){return 65*100L;}
		else if(discount<5.0){return 55*100L;}
		else if(discount<6.0){return 45*100L;}
		else if(discount<7.0){return 35*100L;}
		else if(discount<8.0){return 25*100L;}
		else if(discount<9.0){return 15*100L;}
		else if(discount<10.0){return 10*100L;}
		else{return 0L;}
	}

}
