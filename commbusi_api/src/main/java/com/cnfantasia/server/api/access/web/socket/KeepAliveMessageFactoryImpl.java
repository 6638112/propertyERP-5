package com.cnfantasia.server.api.access.web.socket;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message;
import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message.MessageType;

public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

    Logger mlogger = Logger.getLogger(getClass());

    @Override
    public boolean isRequest(final IoSession session, final Object obj) {
        boolean ret = false;
        if (obj instanceof Message) {
            final Message msg = (Message) obj;
            ret = msg.getMessageType().equals(MessageType.HEARTBEAT_REQ);
        } else {
        }
        return ret;
    }

    @Override
    public boolean isResponse(final IoSession session, final Object obj) {
        boolean ret = false;
        if (obj instanceof Message) {
            final Message msg = (Message) obj;
            ret = msg.getMessageType().equals(MessageType.HEARTBEAT_RESP);
        } else {
        }
        return ret;
    }

    @Override
    public Object getRequest(final IoSession session) {
        final Message.Builder msgBuilder = Message.newBuilder();
        msgBuilder.setMessageId(Long.toString(new Date().getTime()));
        msgBuilder.setMessageType(MessageType.HEARTBEAT_REQ);
        return msgBuilder.build();
    }

    @Override
    public Object getResponse(final IoSession session, final Object request) {
        final Message.Builder msgBuilder = Message.newBuilder();
        msgBuilder.setMessageId(Long.toString(new Date().getTime()));
        msgBuilder.setMessageType(MessageType.HEARTBEAT_RESP);
        return msgBuilder.build();
    }

}
