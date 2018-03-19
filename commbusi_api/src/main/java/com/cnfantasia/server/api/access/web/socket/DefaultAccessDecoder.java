package com.cnfantasia.server.api.access.web.socket;

import java.util.Arrays;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.cnfantasia.server.api.access.web.socket.codec.general.JfqRpc.JfqMsg;

/**
 * @author wangzhe
 * @date 2016年3月22日
 * @description 车禁解包（通用）
 *
 */
public class DefaultAccessDecoder extends CumulativeProtocolDecoder implements ProtocolDefine {

    @Override
    public boolean doDecode(final IoSession session, final IoBuffer buffer, final ProtocolDecoderOutput out) throws Exception {
        boolean ret = false;
        buffer.mark();
        if (buffer.remaining() > HEADER.length() + OFFSET) {
            byte[] header = new byte[HEADER.length()];
            buffer.get(header);
            if (Arrays.equals(header, HEADER.getBytes())) {

                if (buffer.remaining() > OFFSET) {
                    final short length = buffer.getShort();

                    if (buffer.remaining() >= length) {

                        byte[] content = new byte[length];
                        buffer.get(content);

                        JfqMsg msg = JfqMsg.parseFrom(content);
                        if (null != msg) {
                            out.write(msg);
                        } else {
                            // 解析失败，可能不会到这里，因为解析失败会抛错
                            // InvalidProtocolBufferException
                        }
                        ret = true;
                        buffer.mark();
                    } else {
                        // 被断包
                        buffer.reset();
                    }
                } else {
                    buffer.reset();
                }
            } else {
                // 错位
                buffer.reset();
                buffer.position(buffer.position() + 1);
                buffer.mark();
                ret = true;
            }
        } else {
            // 包头长度都不够
        }
        return ret;
    }
}
