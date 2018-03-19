/**
 * 
 */
package com.cnfantasia.server.api.notice.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.notice.entity.MessageWithReadStatusEntity;
import com.cnfantasia.server.api.notice.entity.NoticeBean;
import com.cnfantasia.server.api.notice.nuomi.entity.NoticeMonth;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.messageGroup.entity.MessageGroup;


/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月4日下午2:51:29
 */
public interface INoticeDao {
	
	List<NoticeBean> selectAllNoticeByUserid(PageModel pageModel, BigInteger userId);
	
	List<NoticeBean> selectAddresssByMessageId(BigInteger msgId);
	
	/**
	 * @param msgId
	 * @return
	 */
	List<MessageGroup> selectGroupBuildingByMsgid(BigInteger msgId);
	
	
	/**
	 * 根据用户Id和消息Id更改消息状态
	 * @param userId
	 * @param msgId
	 * @param state
	 * @return
	 */
	boolean updMsgState(BigInteger userId, BigInteger msgId, Integer state);
	
	/**
	 * 根据用户Id和消息Id删除消息
	 * @param userId
	 * @param msgId
	 * @return
	 */
	boolean deleteMsg(BigInteger userId, BigInteger msgId);

	/**
	 * 查询所有的系统消息列表
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	List<MessageWithReadStatusEntity> selectSystemMessageList(BigInteger userId, PageModel pageModel);
	/**
	 * 查询物业电话
	 */
	String selectmobilePhone(BigInteger pcId);
	/**
	 * 为新用户增加五条物业公告
	 */
	List<NoticeBean> selectForNewUser(BigInteger gbId, BigInteger uId);

	/**
	 * 小区签约状态和是否点召唤的信息
	 * @param defaultRoomId,userId
	 * @return
	 */
	Map<String,Object> queryGroupBuildingSignAndSummon(BigInteger defaultRoomId, BigInteger userId);
	
	/**
	 * 查询公告列表（糯米）
	 * @param paramMap
	 * @return
	 */
	public List<NoticeMonth> selectNoticeListForNuomi(Map<String,Object> paramMap);
	
	/**
	 * 查询公告数量（糯米）
	 * @param paramMap
	 * @return
	 */
	public Integer selectNoticeCountForNuomi(Map<String,Object> paramMap);
}
