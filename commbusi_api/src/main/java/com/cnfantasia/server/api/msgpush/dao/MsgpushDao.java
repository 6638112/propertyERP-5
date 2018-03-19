/**
* Filename:    MsgpushDao.java
* @version:    1.0
* Create at:   2014年9月23日 上午2:05:22
* Description:
*
* Modification History:
* Date        Author      Version     Description
* -----------------------------------------------------------------
* 2014年9月23日    shiyl      1.0         1.0 Version
*/
package com.cnfantasia.server.api.msgpush.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.msgpush.entity.MessagepushEntity;
import com.cnfantasia.server.api.msgpush.entity.PreMonthDiscountInfoEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.userPushInfo.entity.UserPushInfo;

/**
 * Filename: MsgpushDao.java
 *
 * @version: 1.0.0 Create at: 2014年9月23日 上午2:05:22 Description:
 *
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年9月23日 shiyl 1.0 1.0 Version
 */
public class MsgpushDao extends AbstractBaseDao implements IMsgpushDao {

    @Override
    public String getPushSystemParamConfig(final String param_config) {
        return sqlSession.selectOne("msgpush.getPushSystemParamConfig", param_config);
    }

    // @Override
    // public List<MessagePushGroupEntity> selectSendListOfGroup() {
    // return sqlSession.selectList("msgpush.select_Send_List_OfGroup");
    // }

    // @Override
    // public Integer selectSendListCount() {
    // return sqlSession.selectOne("msgpush.select_Send_List_Count");
    // }

    @Override
    public BigInteger selectNotPushIdByLock(final BigInteger userHasMsgId) {
        final Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("userHasMsgId", userHasMsgId);
        return sqlSession.selectOne("msgpush.select_NotPushId_ByLock", tmpMap);
    }

    @Override
    public List<MessagepushEntity> selectSendListSignal() {
        final List<MessagepushEntity> resList = sqlSession.selectList("msgpush.select_Send_List_Signal");
        return resList;
    }

    @Override
    public MessagepushEntity selectToSendMsgById(final BigInteger userHasMsgId) {
        final Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("userHasMsgId", userHasMsgId);
        return sqlSession.selectOne("msgpush.select_ToSend_Msg_ById", tmpMap);
    }

    // @Override
    // public Integer removeHistoryBindInfo(Long subChannelId,BigInteger userId,
    // Integer userType, String baiduUserId, String baiduChannelId) {
    // Map<String,Object> tmpMap = new HashMap<String, Object>();
    // tmpMap.put("subChannelId", subChannelId);
    // tmpMap.put("userId", userId);
    // tmpMap.put("userType", userType);
    // tmpMap.put("baiduUserId", baiduUserId);
    // tmpMap.put("baiduChannelId", baiduChannelId);
    // return sqlSession.update("msgpush.remove_History_BindInfo", tmpMap);
    // }

    @Override
    public List<BigInteger> selectToSendMsgIdListNow(final List<Long> msgType) {
        final Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("msgType", msgType);
        return sqlSession.selectList("msgpush.select_ToSendMsgIdList_Now", tmpMap);
    }

    @Override
    public List<MessagepushEntity> selectToSendMsgListById(final List<BigInteger> userHasMsgIdList) {
        List<MessagepushEntity> resList = new ArrayList<MessagepushEntity>();
        if (userHasMsgIdList != null && userHasMsgIdList.size() > 0) {
            final Map<String, Object> tmpMap = new HashMap<String, Object>();
            tmpMap.put("userHasMsgIdList", userHasMsgIdList);
            resList = sqlSession.selectList("msgpush.select_ToSend_MsgList_ById", tmpMap);
        }
        return resList;
    }

    @Override
    public List<BigInteger> selectToSendUserHasMsgListByMsgId(final BigInteger msgId) {
        final Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("msgId", msgId);
        return sqlSession.selectList("msgpush.select_ToSendUserHasMsgList_ByMsgId", tmpMap);
    }

    @Override
    public List<PreMonthDiscountInfoEntity> selectUnusedPrizeDiscountList() {
        return sqlSession.selectList("msgpush.select_UnusedPrize_Discount_List");
    }

    @Override
    public List<UserPushInfo> selectUserPushInfoByBaidu(final Long subChannelId, final String baiduUserId, final String baiduChannelId) {
        final Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("subChannelId", subChannelId);
        tmpMap.put("baiduUserId", baiduUserId);
        tmpMap.put("baiduChannelId", baiduChannelId);
        return sqlSession.selectList("msgpush.select_UserPushInfo_ByBaidu", tmpMap);
    }

    @Override
    public List<UserPushInfo> selectUserPushInfoByChannel(final Long subChannelId, final String channelid, final String column,
            final List<UserPushInfo> exclude) {
        final Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("subChannelId", subChannelId);
        tmpMap.put("column", column);
        tmpMap.put("channelid", channelid);
        tmpMap.put("exclude", exclude);
        return sqlSession.selectList("msgpush.select_UserPushInfo_ByChannel", tmpMap);
    }

    @Override
    public List<UserPushInfo> selectUserPushInfoByUser(final Long subChannelId, final BigInteger userId, final Integer userType) {
        final Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("subChannelId", subChannelId);
        tmpMap.put("userId", userId);
        tmpMap.put("userType", userType);
        return sqlSession.selectList("msgpush.select_UserPushInfo_ByUser", tmpMap);
    }

    @Override
    public int updateChannelid(final List<UserPushInfo> upis, final String column, final String channelid) {
        final Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("upis", upis);
        tmpMap.put("column", column);
        tmpMap.put("channelid", channelid);
        return sqlSession.update("msgpush.batch_update_channelid", tmpMap);
    }

    @Override
    public int updateUserPushInfoBatch(List<UserPushInfo> upis_user) {
        return sqlSession.update("msgpush.update_userPushInfo_Batch", upis_user);
    }
}
