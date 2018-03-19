package com.cnfantasia.server.api.user.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.user.entity.HobbyEntity;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.hobby.entity.Hobby;
import com.cnfantasia.server.domainbase.user.entity.User;
/**
 * 描述:() 具体业务Dao层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserDao extends AbstractBaseDao implements IUserDao{
	
	/**
	 * 更新(用户表)信息
	 * @param user
	 * @return
	 */
	@Override
	public int updateUserSignatureNull(User user){
		return sqlSession.update("user.update_user", user);
	}
	
	@Override
	public UserEntity selectUserById(BigInteger userId) {
		return sqlSession.selectOne("user.select_user_bySeqId",userId);
	}

	@Override
	public List<HobbyEntity> selectAllHobbyList(PageModel pageModel, BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		String pageSqlKey = "user.select_AllHobby_List_page";
		String countSqlKey = "user.select_AllHobby_List_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public List<Hobby> selectHobbyListByUserId(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectList("user.select_HobbyList_ByUserId", tmpMap);
	}

	@Override
	public Integer cancelHobbyIdsBatch(BigInteger userId,List<BigInteger> cancelHobbyIds) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("cancelHobbyIds", cancelHobbyIds);
		return sqlSession.update("user.cancel_HobbyIds_Batch", tmpMap);
	}

	@Override
	public int update_user_mobile_to_null(BigInteger userId) {
		return sqlSession.update("user.update_user_mobile_to_null", userId);
	}

	@Override
	public int selectHasBusinessDataCountByUserId(BigInteger userId) {
		return sqlSession.selectOne("user.selectHasBusinessDataCount_ByUserId", userId);
	}

	@Override
	public int changeCouponUser(BigInteger fromUserId, BigInteger toUserId) {
		Map<String, Object> param = new HashMap<>();
		param.put("fromUserId", fromUserId);
		param.put("toUserId", toUserId);
		return sqlSession.update("user.changeCouponUser", param);
	}

}
