/**
* Filename:    IMsgpushDao.java
* @version:    1.0
* Create at:   2014年9月23日 上午2:04:49
* Description:
*
* Modification History:
* Date        Author      Version     Description
* -----------------------------------------------------------------
* 2014年9月23日    shiyl      1.0         1.0 Version
*/
package com.cnfantasia.server.api.msgpush.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.msgpush.entity.MessagepushEntity;
import com.cnfantasia.server.api.msgpush.entity.PreMonthDiscountInfoEntity;
import com.cnfantasia.server.domainbase.userPushInfo.entity.UserPushInfo;

/**
 * Filename: IMsgpushDao.java
 * 
 * @version: 1.0.0 Create at: 2014年9月23日 上午2:04:49 Description:
 *
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年9月23日 shiyl 1.0 1.0 Version
 */
public interface IMsgpushDao {
    /**
     * 查询个推，小米配置参数
     * 
     * @param param_config
     * @return
     */
    String getPushSystemParamConfig(String param_config);

    // /**
    // * 查询未成功发送的消息个数
    // * @return
    // */
    // public Integer selectSendListCount();

    // /**
    // * 查询消息组的消息列表
    // * @return
    // */
    // public List<MessagePushGroupEntity> selectSendListOfGroup();

    BigInteger selectNotPushIdByLock(BigInteger userHasMsgId);

    /**
     * 查询待发送的消息列表
     */
    List<MessagepushEntity> selectSendListSignal();

    /**
     * 根据Id查询消息详情
     * 
     * @param msgId
     * @return
     */
    MessagepushEntity selectToSendMsgById(BigInteger userHasMsgId);

    // /**
    // * 移除历史绑定信息
    // * @param userId
    // * @param userType
    // * @param baiduUserId
    // * @param baiduChannelId
    // * @return
    // */
    // public Integer removeHistoryBindInfo(Long subChannelId,BigInteger
    // userId,Integer userType,String baiduUserId, String baiduChannelId);

    List<BigInteger> selectToSendMsgIdListNow(List<Long> msgType);

    /**
     * 根据消息IdList查询消息列表
     * 
     * @param msgIdList
     * @return
     */
    List<MessagepushEntity> selectToSendMsgListById(List<BigInteger> userHasMsgIdList);

    List<BigInteger> selectToSendUserHasMsgListByMsgId(BigInteger msgId);

    /**
     * 查询上个月所有用户折扣未使用的信息
     */
    List<PreMonthDiscountInfoEntity> selectUnusedPrizeDiscountList();

    /**
     * 按条件查询对应的配置信息
     * 
     * @param subChannelId
     * @param baiduUserId
     * @param baiduChannelId
     * @return
     */
    List<UserPushInfo> selectUserPushInfoByBaidu(Long subChannelId, String baiduUserId, String baiduChannelId);

    /**
     * @author wangzhe
     * @date 2017年3月10日
     * @description 根据channeltype和id查
     *
     * @param subChannelId
     * @param channelid
     * @param exclude TODO
     * @param channeltype
     * @return
     */
    List<UserPushInfo> selectUserPushInfoByChannel(Long subChannelId, String channelid, String column, List<UserPushInfo> exclude);

    /**
     * 按条件查询对应的配置信息
     * 
     * @param subChannelId
     * @param userId
     * @param userType
     * @return
     */
    List<UserPushInfo> selectUserPushInfoByUser(Long subChannelId, BigInteger userId, Integer userType);

    int updateChannelid(List<UserPushInfo> upis, String column, String channelid);

    int updateUserPushInfoBatch(List<UserPushInfo> upis_user);
}
