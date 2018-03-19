/**   
* Filename:    IDecryptAble.java   
* @version:    1.0  
* Create at:   2014年6月10日 上午11:44:18   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月10日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.cryption.simplepiple;

/**
 * Filename:    IDecryptAble.java
 * @version:    1.0.0
 * Create at:   2014年6月10日 上午11:44:18
 * Description: 可解密的
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月10日       shiyl             1.0             1.0 Version
 */
public interface IDecryptAble {
	/**
	 * 加密
	 * @param data
	 * @return
	 */
	public byte[] encrypt(byte[] data);
	/**
	 * 解密
	 * @param data
	 * @return
	 */
	public byte[] decrypt(byte[] data);
	
}
