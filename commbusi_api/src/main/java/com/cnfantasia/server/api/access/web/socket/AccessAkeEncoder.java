package com.cnfantasia.server.api.access.web.socket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message;
import com.google.protobuf.CodedOutputStream;

/**
 * @author wangzhe
 * @date 2016年3月22日
 * @description 车禁打包（艾科）
 *
 */
public class AccessAkeEncoder extends ProtocolEncoderAdapter {

    @Override
    public void encode(final IoSession session, final Object obj, final ProtocolEncoderOutput out) throws Exception {

        CodedOutputStream cos = null;
        int length = 0;
        IoBuffer buffer = null;

        if (obj instanceof Message) {
            final Message msg = (Message) obj;
            length = msg.getSerializedSize();
            buffer = IoBuffer.allocate(length + 5).setAutoExpand(true);
            cos = CodedOutputStream.newInstance(buffer.asOutputStream());
            cos.writeRawVarint32(length);
            msg.writeTo(cos);
        } else if (obj instanceof byte[]) {
            byte[] bytes = (byte[]) obj;
            length = bytes.length;
            buffer = IoBuffer.allocate(length + 5).setAutoExpand(true);
            cos = CodedOutputStream.newInstance(buffer.asOutputStream());
            cos.writeRawVarint32(length);
            cos.writeByteArrayNoTag(bytes);
        } else {
        }
        cos.flush();
        buffer.flip();
        out.write(buffer);
    }
}
