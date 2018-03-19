/**
 * 
 */
package com.cnfantasia.server.api.notice.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.notice.entity.MessageWithReadStatusEntity;
import com.cnfantasia.server.api.notice.entity.NoticeBean;
import com.cnfantasia.server.api.notice.nuomi.entity.NoticeMonth;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.messageGroup.entity.MessageGroup;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月4日下午2:51:45
 */
public class NoticeDao extends AbstractBaseDao implements INoticeDao{
	/**
	 * by zzh
	 */
	@Override
	public List<NoticeBean> selectAllNoticeByUserid(PageModel pageModel,BigInteger userId) {
		Map<String, BigInteger> param = new HashMap<String, BigInteger>();
		param.put("userId", userId);
		return PageQueryUtil.selectPageList(sqlSession, param, pageModel, "notice.selectAllNoticeByUserid", "notice.countAllNoticeByUserid");
	}
	
	/**
	 * by syl
	 */
	@Override
	public List<NoticeBean> selectAddresssByMessageId(BigInteger msgId) {
		return sqlSession.selectList("notice.selectAddresssByMessageId", msgId);
	}
	
	@Override
	public List<MessageGroup> selectGroupBuildingByMsgid(BigInteger msgId) {
		return sqlSession.selectList("notice.select_groupBuilding_by_msgId", msgId);
	}
	
	@Override
	public boolean updMsgState(BigInteger userId, BigInteger msgId, Integer state) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("msgId", msgId);
		tmpMap.put("state", state);
		int updCount = sqlSession.update("notice.updateMsgStatus_ByUserMsgId", tmpMap);
		return updCount > 0;
	}

	@Override
	public boolean deleteMsg(BigInteger userId, BigInteger msgId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("msgId", msgId);
		int delCount = sqlSession.update("notice.deleteMsg_ByUserMsgId", tmpMap);
		return delCount > 0;
	}

	@Override
	public List<MessageWithReadStatusEntity> selectSystemMessageList(BigInteger userId, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		String pageSqlKey = "notice.select_System_Message_List_Page";
		String countSqlKey = "notice.select_System_Message_List_Count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public String selectmobilePhone(BigInteger pcId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("pcId", pcId);
		String mobilePhone =sqlSession.selectOne("notice.selectmobilePhone",tmpMap);
		return mobilePhone;
	}

	@Override
	public List<NoticeBean> selectForNewUser(BigInteger gbId,BigInteger uId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		tmpMap.put("uId", uId);
		return sqlSession.selectList("notice.selectfornewuser", tmpMap);
	}

	@Override
	public Map<String, Object> queryGroupBuildingSignAndSummon(BigInteger defaultRoomId, BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("roomId", defaultRoomId);
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("notice.select_room_bySeqId_WithSummon",tmpMap);
	}
	
	/**
	 * 查询公告列表（糯米）
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<NoticeMonth> selectNoticeListForNuomi(Map<String,Object> paramMap){
		return sqlSession.selectList("notice.selectNoticeListForNuomi", paramMap);
	}
	
	/**
	 * 查询公告数量（糯米）
	 * @param paramMap
	 * @return
	 */
	@Override
	public Integer selectNoticeCountForNuomi(Map<String,Object> paramMap){
		return sqlSession.selectOne("notice.selectNoticeCountForNuomi", paramMap);
	}
}
