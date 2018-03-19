/**   
* Filename:    LoginDao.java   
* @version:    1.0  
* Create at:   2014年5月6日 上午9:12:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.login.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.omsUser.dao.IOmsUserBaseDao;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

/**
 * Filename:    LoginDao.java
 * @version:    1.0.0
 * Create at:   2014年5月6日 上午9:12:02
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月6日       shiyl             1.0             1.0 Version
 */
public class LoginDao extends AbstractBaseDao implements ILoginDao{
	private IOmsUserBaseDao omsUserBaseDao;
	public void setOmsUserBaseDao(IOmsUserBaseDao omsUserBaseDao) {
		this.omsUserBaseDao = omsUserBaseDao;
	}

	@Override
	public OmsUser selectOmsUser(String number, String password) {
		OmsUser qry = new OmsUser();
		qry.setUserAccount(number);
		qry.setPassword(password);
		qry.setUserState(0L);//启用状态的才能登录成功
		List<OmsUser> list = omsUserBaseDao.selectOmsUserByCondition(MapConverter.toMap(qry), false);
		if(list==null||list.size()<=0){
			return null;
		}else if(list.size()>1){
			throw new BusinessRuntimeException("LoginDao.selectOmsUser.multiResult.error");
		}else{
			return list.get(0);
		}
	}

	@Override
	public OmsUser selectOmsUserByAccount(String loginName) {
		OmsUser qry = new OmsUser();
		qry.setUserAccount(loginName);
		List<OmsUser> list = omsUserBaseDao.selectOmsUserByCondition(MapConverter.toMap(qry), false);
		if(list==null||list.size()<=0){
			return null;
		}else if(list.size()>1){
			throw new BusinessRuntimeException("LoginDao.selectOmsUserByAccount.multiResult.error");
		}else{
			return list.get(0);
		}
	}
	
	public List<EbuySupplyMerchant> selectSupplyMerchantListByUserId(BigInteger userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		
		return sqlSession.selectList("login.select_ebuySupplyMerchantByUserId",paramMap);
	}

	public BigInteger selectPropertyCompanyIdByUserId(BigInteger userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		
		return sqlSession.selectOne("login.select_PropertyCompanyIdByUserId", paramMap);
	}
	
	public List<BigInteger> selectGroupbuildingIdList(BigInteger userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		
		return sqlSession.selectList("login.selectGroupbuildingIdList",paramMap);
	}
	
	public BigInteger selectPropertyCompanyId(BigInteger userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		
		return sqlSession.selectOne("login.selectPropertyCompanyId",paramMap);
	}
	
}
