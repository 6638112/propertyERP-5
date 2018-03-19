/**   
* Filename:    DiscountMaker.java   
* @version:    1.0  
* Create at:   2014年7月8日 上午3:17:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cnfantasia.server.api.prize.entity.DiscountInterval;
import com.cnfantasia.server.api.prize.entity.DiscountValueEntity;

/**
 * Filename:    DiscountMaker.java
 * @version:    1.0.0
 * Create at:   2014年7月8日 上午3:17:26
 * Description:	折扣生成工具
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月8日       shiyl             1.0             1.0 Version
 */
public class DiscountMaker {
	
	public static void main(String[] args) {
		List<DiscountValueEntity> resList = getDiscountList(100, new DiscountInterval(new DiscountValueEntity(4000L), new DiscountValueEntity(5000L)));
		Double sum = 0.0;
		for(DiscountValueEntity tmp:resList){
			sum+=tmp.doubleValue();
			System.out.println(tmp.doubleValue());
		}
		System.out.println(sum/resList.size());
//		System.out.println(resList.get(3));
//		Random rand = new Random(); 
//		for(int i=0;i<100;i++){
//			System.out.println(rand.nextInt(10));
//		}
	}
	
	public static List<DiscountValueEntity> getDiscountList(int count,DiscountInterval discountInterval){
		List<DiscountValueEntity> resList = new ArrayList<DiscountValueEntity>();
		int start = discountInterval.getStart().multiplyValue(10L);
		int end = discountInterval.getEnd().multiplyValue(10L);
		Random rand = new Random(); 
		for(int i=0;i<count;i++){
			int v1 = rand.nextInt(end-start)+start;
			resList.add(new DiscountValueEntity(v1+0L,10L));
		}
		return resList;
	}
	
}
