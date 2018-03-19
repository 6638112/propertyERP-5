package com.cnfantasia.server.api.msgpush.service;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.cnfantasia.server.domainbase.userPushInfo.entity.UserPushInfo;

/**
 * @author wangzhe
 * @date 2017年3月9日
 * @description 推送通道
 *
 */
public enum EnumPushChannel {

    BAIDU("f_channel_bd") {
        @Override
        public boolean updateId(final UserPushInfo upi, final String channelid) {
            final boolean changed = !EqualsBuilder.reflectionEquals(channelid, upi.getChannelBd());
            upi.setChannelBd(channelid);
            upi.setChannelXm("");
            upi.setChannelHw("");
            upi.setChannelGt("");
            return changed;
        }
    },
    HUAWEI("f_channel_hw") {
        @Override
        public boolean updateId(final UserPushInfo upi, final String channelid) {
            final boolean changed = !EqualsBuilder.reflectionEquals(channelid, upi.getChannelHw());
            upi.setChannelHw(channelid);
            upi.setChannelBd("");
            upi.setChannelXm("");
            upi.setChannelGt("");
            return changed;
        }
    },
    XIAOMI("f_channel_xm") {
        @Override
        public boolean updateId(final UserPushInfo upi, final String channelid) {
            final boolean changed = !EqualsBuilder.reflectionEquals(channelid, upi.getChannelXm());
            upi.setChannelXm(channelid);
            upi.setChannelBd("");
            upi.setChannelHw("");
            upi.setChannelGt("");
            return changed;
        }
    },
    GETUI("f_channel_gt") {
        @Override
        public boolean updateId(final UserPushInfo upi, final String channelid) {
            final boolean changed = !EqualsBuilder.reflectionEquals(channelid, upi.getChannelGt());
            upi.setChannelGt(channelid);
            upi.setChannelBd("");
            upi.setChannelXm("");
            upi.setChannelHw("");
            return changed;
        }
    };

    private String columnName; // t_user_push表中各推送的字段名称

    EnumPushChannel(final String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    /**
     * @author wangzhe
     * @date 2017年3月10日
     * @description 更新所属通道的channel id
     *
     * @param upi
     * @param channelid
     * @return id是否更改
     */
    public boolean updateId(final UserPushInfo upi, final String channelid) {
        throw new AbstractMethodError();
    }

}
