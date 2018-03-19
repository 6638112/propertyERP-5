package com.cnfantasia.server.api.access.web.socket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message;
import com.google.protobuf.CodedInputStream;

/**
 * @author wangzhe
 * @date 2016年3月22日
 * @description 车禁解包（艾科）
 *
 */
public class AccessAkeDecoder extends CumulativeProtocolDecoder {

    @Override
    public boolean doDecode(final IoSession session, final IoBuffer in, final ProtocolDecoderOutput out) throws Exception {
        boolean ret = false;
        in.mark();
        final byte[] buf = new byte[5];
        for (int i = 0; i < buf.length; ++i) {
            if (in.hasRemaining()) {
                buf[i] = in.get();
                if (buf[i] >= 0) {
                    final int length = CodedInputStream.newInstance(buf, 0, i + 1).readRawVarint32();

                    if (length > 0 && in.remaining() >= length) {
                        final Message msg = Message.parseFrom(in.asInputStream());
                        ret = true;
                        if (null != msg) {
                            out.write(msg);
                            break;
                        } else {
                        }
                    } else {
                        // 被断包
                        in.reset();
                    }
                }
            } else {
                in.reset();
                break;
            }
        }
        return ret;
    }
}
