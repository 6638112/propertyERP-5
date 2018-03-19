package com.cnfantasia.server.api.userQuestion.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.userQuestion.entity.UserQuestion4Admin;
import com.cnfantasia.server.api.userQuestion.entity.UserQuestionDetail4Admin;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.domainbase.propertyDistrict.entity.PropertyDistrict;

/**
 * @ClassName: UserQuestionDao
 * @Date: 2017-02-23 14:31
 * @Auther: kangduo
 * @Description: ()
 */
public class UserQuestionDao extends AbstractBaseDao implements IUserQuestionDao {

	/**
	 * 查询指定房间片区信息
	 * @param roomId
	 * @return
     */
	@Override
	public PropertyDistrict getPropertyDistrictByRoomId(BigInteger roomId) {
		return sqlSession.selectOne("userQuestion.getPropertyDistrictByRoomId", roomId);
	}


	public int getUserQuestionCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "userQuestion.selectUserQuestionList", paramMap);
	}

	/**
	 * 运营后台管理员查看帮帮看列表
	 * @param paramMap
	 * @return
	 */
	public List<UserQuestion4Admin> getUserQuestionList(Map<String, Object> paramMap) {
		return sqlSession.selectList("userQuestion.selectUserQuestionList", paramMap);
	}

	/**
	 * 运营后台管理员查看帮帮详情
	 * @param uqId
	 * @return
	 */
	public UserQuestionDetail4Admin getUserQuestionDetailDetail(BigInteger uqId) {
		return sqlSession.selectOne("userQuestion.selectUserQuestionDetailById", uqId);
	}

	@Override
	public String getRoomDetailAddress(BigInteger roomId) {
		return sqlSession.selectOne("userQuestion.getRoomDetailAddress", roomId);
	}
}
