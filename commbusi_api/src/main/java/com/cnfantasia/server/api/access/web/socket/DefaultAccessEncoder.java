package com.cnfantasia.server.api.access.web.socket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.cnfantasia.server.api.access.web.socket.codec.general.JfqRpc.JfqMsg;

/**
 * @author wangzhe
 * @date 2016年3月22日
 * @description 车禁打包（通用）
 *
 */
public class DefaultAccessEncoder extends ProtocolEncoderAdapter implements ProtocolDefine {

    @Override
    public void encode(final IoSession session, final Object obj, final ProtocolEncoderOutput out) throws Exception {

        if (obj instanceof JfqMsg) {
            final JfqMsg msg = (JfqMsg) obj;
            byte[] content = msg.toByteArray();
            final int length = content.length;
            final IoBuffer buffer = IoBuffer.allocate(length + HEADER.length() + OFFSET).setAutoExpand(true);
            buffer.put(HEADER.getBytes());
            buffer.putShort((short) length);
            buffer.put(content);
            buffer.flip();
            
            out.write(buffer);
        } else {
            // 出错，无法打包
        }
    }
}
