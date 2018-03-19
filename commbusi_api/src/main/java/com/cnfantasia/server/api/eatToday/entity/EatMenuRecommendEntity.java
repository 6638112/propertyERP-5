/**   
* Filename:    EatMenuRecommendEntity.java   
* @version:    1.0  
* Create at:   2014年5月24日 上午5:11:37   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.eatToday.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.eatMenu.entity.EatMenu;
import com.cnfantasia.server.domainbase.eatMenuRecommend.entity.EatMenuRecommend;

/**
 * Filename:    EatMenuRecommendEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月24日 上午5:11:37
 * Description:推荐菜谱
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月24日       shiyl             1.0             1.0 Version
 */
public class EatMenuRecommendEntity extends EatMenuRecommend{
	private static final long serialVersionUID = 1L;
	/**具体菜谱详情*/
	private EatMenu eatMenu;
	public EatMenu getEatMenu() {
		return eatMenu;
	}
	public void setEatMenu(EatMenu eatMenu) {
		this.eatMenu = eatMenu;
	}
	@Override
	public BigInteger gettEatMenuFId() {
		if(eatMenu==null){ return null; }
		return eatMenu.getId();
	}
	@Override
	public void settEatMenuFId(BigInteger tEatMenuFId) {
		if(eatMenu==null){ eatMenu = new EatMenu(); }
		eatMenu.setId(tEatMenuFId);
	}
	
}
