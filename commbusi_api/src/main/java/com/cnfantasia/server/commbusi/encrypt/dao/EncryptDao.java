/**   
* Filename:    EncryptDao.java   
* @version:    1.0  
* Create at:   2016年2月19日 上午11:48:41   
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
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

/**
 * Filename:    EncryptDao.java
 * @version:    1.0.0
 * Create at:   2016年2月19日 上午11:48:41
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2016年2月19日       shiyl             1.0             1.0 Version
 */
public class EncryptDao extends AbstractBaseDao implements IEncryptDao{

	@Override
	public List<EncryptUrlEntity> selectEncryptUrlAll() {
		return sqlSession.selectList("encrypt.select_EncryptUrl_All");
	}

	@Override
	public void cleanCache() {
		sqlSession.selectOne("encrypt.cleanCache");
	}
	
	
}
