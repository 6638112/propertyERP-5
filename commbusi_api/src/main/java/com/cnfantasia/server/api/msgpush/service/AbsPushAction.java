package com.cnfantasia.server.api.msgpush.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.msgpush.entity.MessagepushEntity;

/**
 * @author wangzhe
 * @date 2017年3月10日
 * @description 推送渠道基类
 *
 */
public abstract class AbsPushAction {
    protected Log logger = LogFactory.getLog(getClass());

    public abstract MessagepushEntity push(MessagepushEntity entity);
}
