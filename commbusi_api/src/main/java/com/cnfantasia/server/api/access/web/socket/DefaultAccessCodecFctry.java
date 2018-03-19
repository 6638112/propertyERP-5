package com.cnfantasia.server.api.access.web.socket;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author wangzhe
 * @date 2016年3月22日
 * @description 车禁打解包工厂
 *
 */
public final class DefaultAccessCodecFctry implements ProtocolCodecFactory {

    private final ProtocolDecoder mDecoder = new DefaultAccessDecoder();

    private final ProtocolEncoder mEncoder = new DefaultAccessEncoder();

    @Override
    public ProtocolDecoder getDecoder(final IoSession arg0) throws Exception {
        return mDecoder;
    }

    @Override
    public ProtocolEncoder getEncoder(final IoSession arg0) throws Exception {
        return mEncoder;
    }

}
