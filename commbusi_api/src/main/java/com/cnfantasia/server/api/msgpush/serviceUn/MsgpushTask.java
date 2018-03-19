/**
* Filename:    MsgpushTask.java
* @version:    1.0
* Create at:   2014年9月22日 下午12:34:11
* Description:
*
* Modification History:
* Date        Author      Version     Description
* -----------------------------------------------------------------
* 2014年9月22日    shiyl      1.0         1.0 Version
*/
package com.cnfantasia.server.api.msgpush.serviceUn;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.msgpush.entity.BaiduMessageFormat;
import com.cnfantasia.server.api.msgpush.entity.MessagepushEntity;
import com.cnfantasia.server.api.msgpush.service.BaiduMsgPushService;
import com.cnfantasia.server.api.msgpush.util.DataConvertUtil;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename: MsgpushTask.java
 *
 * @version: 1.0.0 Create at: 2014年9月22日 下午12:34:11 Description:
 *
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年9月22日 shiyl 1.0 1.0 Version
 */
public class MsgpushTask extends BaiduMsgPushService implements IMsgpushTask {
    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public void autoSendTask() {
        int succMsgCount = 0;
        int failedMsgCount = 0;
        logger.debug("Start to execute mesSendTask.");
        final List<Long> messageType = getTaskTypeList();
        logger.debug("MsgpushTask autoSendTask messageType info:" + messageType == null ? null : JSON.toJSONString(messageType));
        if (messageType != null && messageType.size() > 0) {
            final List<BigInteger> msgIdList = msgpushDao.selectToSendMsgIdListNow(messageType);
            logger.debug("MsgpushTask autoSendTask toSendMsgCount is:" + msgIdList == null ? 0 : msgIdList.size());
            if (msgIdList != null && msgIdList.size() > 0) {
                for (final BigInteger msgId : msgIdList) {// 逐个获取消息对应的userhasmsgList
                    final int tmpCount = sendSignalByMsgId(msgId);
                    if (tmpCount > 0) {
                        succMsgCount += 1;
                    } else {
                        failedMsgCount += 1;
                    }
                }
            }
        }
        logger.debug("Finished to execute mesSendTask,succMsgCount is:" + succMsgCount + ",failedMsgCount is:" + failedMsgCount);
    }

    private List<Long> getTaskTypeList() {
        final List<Long> resList = new ArrayList<Long>();
        final String typeStr = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.MSG_TASK_TYPES);
        if (!StringUtils.isEmpty(typeStr)) {
            final String[] arrStr = typeStr.trim().split(",");
            for (final String tmpAA : arrStr) {
                resList.add(Long.valueOf(tmpAA));
            }
        }
        // Long messageType = NoticeDict.Message_Type.Property;
        return resList;
    }

    @Override
    public int sendSignalByMsgId(final BigInteger msgId) {
        int succCount = 0;
        int failedCount = 0;
        logger.debug("MsgpushTask sendSignalByMsgId start,msgId is:" + msgId);
        final List<BigInteger> userHasMsgIdList = msgpushDao.selectToSendUserHasMsgListByMsgId(msgId);
        if (userHasMsgIdList != null && userHasMsgIdList.size() > 0) {
            for (final BigInteger userHasMsgId : userHasMsgIdList) {// 逐个发送
                final int tmpCount = sendSignalByUserHasMsgId(userHasMsgId);
                if (tmpCount > 0) {
                    succCount += 1;
                } else {
                    failedCount += 1;
                }
            }
        }
        logger.debug("MsgpushTask sendSignalByMsgId finished,successCount is:" + succCount + ",failedCount is:" + failedCount);
        return succCount;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 60)
    @Override
    public int sendSignalByUserHasMsgId(final BigInteger userHasMsgId) {// TODO
                                                                        // 测试继承事务
        logger.debug("MsgpushTask sendSignalByUserHasMsgId start,userHasMsgId is:" + userHasMsgId);
        int successCount = 0;
        // 锁定单个表
        final BigInteger tmpId = msgpushDao.selectNotPushIdByLock(userHasMsgId);
        if (tmpId == null) {
            logger.info("MsgpushTask sendSignalByUserHasMsgId is send,userHasMsgId is:" + userHasMsgId);
        } else {
            // 获取详情
            final MessagepushEntity messagepushEntity = msgpushDao.selectToSendMsgById(userHasMsgId);

            if (messagepushEntity != null) {

                final String version = messagepushEntity.getUserPushInfo().getAppVersion();
                boolean oldversion = false;
                if (!TextUtils.isEmpty(version)) {
                    final String tmp = version.replaceAll("\\.", "");
                    try {
                        final Integer ver = Integer.parseInt(tmp);
                        oldversion = ver <= 502;
                    } catch (final NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    oldversion = true;
                }

                if (oldversion) {
                    final BaiduMessageFormat baiduMessageFormat = DataConvertUtil.messagePushBaseEntity2BaiduMessageFormat(messagepushEntity);
                    // 发送,锁超时处理,标识结果状态,释放锁
                    final List<BaiduMessageFormat> msgList = new ArrayList<BaiduMessageFormat>();
                    msgList.add(baiduMessageFormat);
                    successCount = super.sendSignalMsgBatch(msgList);
                } else {
                }
            } else {
            }
        }
        logger.debug("MsgpushTask sendSignalByUserHasMsgId finished,successCount is:" + successCount);
        return successCount;
    }

}
