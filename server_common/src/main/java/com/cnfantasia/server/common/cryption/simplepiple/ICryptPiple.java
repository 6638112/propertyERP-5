/**   
* Filename:    ICryptPiple.java   
* @version:    1.0  
* Create at:   2014年6月10日 上午11:42:46   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月10日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.cryption.simplepiple;

import java.util.LinkedList;

/**
 * Filename:    ICryptPiple.java
 * @version:    1.0.0
 * Create at:   2014年6月10日 上午11:42:46
 * Description: 加密通道
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月10日       shiyl             1.0             1.0 Version
 */
public interface ICryptPiple extends IDecryptAble{
	public LinkedList<IDecryptAble> getDecryptAbleList();
	public void setDecryptAbleList(LinkedList<IDecryptAble> decryptAbleList);
	/**
	 * 增加加密算法
	 * @param decryptAble
	 */
	public void addLast(IDecryptAble decryptAble);
	public void addFirst(IDecryptAble decryptAble);
	/**
	 * 移除加密算法
	 * @param decryptAble
	 */
	public void removeFirst();
	public void removeLast();
	
}
