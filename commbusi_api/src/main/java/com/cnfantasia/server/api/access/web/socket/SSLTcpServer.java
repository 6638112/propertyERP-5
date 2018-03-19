package com.cnfantasia.server.api.access.web.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * @author wangzhe
 * @date 2016年3月22日
 * @description TCP服务器
 *
 */
public class SSLTcpServer {

    public static int READ_BUFFER_SIZE = 128 * 1024;

    public static int WRITE_BUFFER_SIZE = 128 * 1024;

    private final NioSocketAcceptor mAcceptor = new NioSocketAcceptor();
    protected final Logger mLogger = Logger.getLogger(getClass());

    protected NioSocketAcceptor init(ProtocolEncoder encoder, ProtocolDecoder decoder) {

        // 多线程处理业务
        mAcceptor.getFilterChain().addLast("executor", new ExecutorFilter(Executors.newCachedThreadPool()));
        // 设置加密过滤器
        SSLContext context = SSLContextGenerator.getSslContext();
        if (null != context) {
            final SslFilter sslFilter = new SslFilter(context);
            mAcceptor.getFilterChain().addFirst("ssl", sslFilter);
        } else {
        }

        // 协议解析
        mAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(encoder, decoder));

        // 登陆控制
        mAcceptor.getFilterChain().addLast("login", new LoginFilter());

        // 心跳
        // final KeepAliveMessageFactory heartBeatFactory = new
        // KeepAliveMessageFactoryImpl();
        // final KeepAliveFilter heartBeat = new
        // KeepAliveFilter(heartBeatFactory, IdleStatus.READER_IDLE);
        // heartBeat.setRequestInterval(30);
        // heartBeat.setRequestTimeout(70);
        // mAcceptor.getFilterChain().addLast("heartbeat", heartBeat);

        final SocketSessionConfig scfg = mAcceptor.getSessionConfig();

        // 设置地址可重用
        scfg.setReuseAddress(true);
        // 设置读入缓存大小
        scfg.setReadBufferSize(READ_BUFFER_SIZE);
        // 设置输出缓存大小
        scfg.setSendBufferSize(WRITE_BUFFER_SIZE);
        // 超时时间70s
        scfg.setIdleTime(IdleStatus.READER_IDLE, 70);
        scfg.setIdleTime(IdleStatus.WRITER_IDLE, 0);

        return mAcceptor;
    }

    protected void bind(final int port) {
        while (true) {
            try {
                mAcceptor.bind(new InetSocketAddress(port));
                mLogger.info(" SSL TCP Server(port:" + port + ") started...");
                break;
            } catch (final IOException e) {
                mLogger.error("端口 " + port + " 被占用，请重换端口号", e);
            }
        }
    }

    public void destroy() {
        mAcceptor.dispose();
    }

}
