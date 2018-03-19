/**
 * 
 */
package com.cnfantasia.server.api.notice.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.notice.entity.MessageWithReadStatusEntity;
import com.cnfantasia.server.api.notice.entity.NoticeBean;
import com.cnfantasia.server.api.notice.nuomi.entity.NoticeMonth;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageGroup.entity.MessageGroup;

/**
 * 类说明：物业公告业务类
 *
 * @author hunter
 * @since 2014年6月4日下午3:44:16
 */
public interface INoticeService {
	
	/**
	 * 查询所有物业公共列表
	 * @param pageModel
	 * @param userId
	 * @return
	 */
	List<NoticeBean> queryPropertyNoticeList(PageModel pageModel, BigInteger userId);
	
	List<NoticeBean> queryAddressByMsgid(BigInteger msgId);
	
	int saveNoticeMessage(Message message, String[] messageGroups) throws BusinessRuntimeException;
	
	List<MessageGroup> getGroupBuildingByMsgid(BigInteger msgId);
	
	/**
	 * 查询所有系统消息列表列表
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	List<MessageWithReadStatusEntity> querySystemMessageList(BigInteger userId, PageModel pageModel);
	
//	/**
//	 * 更改消息状态
//	 * @param userId
//	 * @param msgId
//	 * @param state
//	 * @return
//	 */
//	public boolean updMsgState(BigInteger userId,BigInteger msgId,Integer state);
//	/**
//	 * 根据用户Id和消息Id删除消息
//	 */
//	public boolean deleteMsg(BigInteger userId,BigInteger msgId);
	
	String queryMobilePhone(BigInteger pcId);
	/**
	 * 为新用户推送五条公告
	 */
	List<NoticeBean> queryMsgForNewUser(BigInteger gbId, BigInteger uId);

	/**
	 * 小区签约状态和是否点召唤的信息
	 * @param userId
	 * @return
     */
	Map<String,Object> queryGroupBuildingSignAndSummon(BigInteger userId);
	
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
