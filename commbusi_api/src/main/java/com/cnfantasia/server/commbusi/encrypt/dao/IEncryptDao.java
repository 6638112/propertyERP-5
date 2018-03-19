/**   
* Filename:    IEncryptDao.java   
* @version:    1.0  
* Create at:   2016年2月19日 上午11:48:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2016年2月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.encrypt.dao;

import java.util.List;

import com.cnfantasia.server.commbusi.encrypt.entity.EncryptUrlEntity;

/**
 * Filename:    IEncryptDao.java
 * @version:    1.0.0
 * Create at:   2016年2月19日 上午11:48:26
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2016年2月19日       shiyl             1.0             1.0 Version
 */
public interface IEncryptDao {

	/**
	 * 缓存 排序 
	 * @return
	 */
	public List<EncryptUrlEntity> selectEncryptUrlAll();

	/**
	 * 刷新缓存
	 */
	public void cleanCache();
	
}
