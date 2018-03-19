package com.cnfantasia.server.api.access.web.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.firewall.BlacklistFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * @author wangzhe
 * @date 2016年3月22日
 * @description TCP服务器
 *
 */
public class TcpServer {
	protected final Logger mLogger = Logger.getLogger(getClass());
	
    public static int READ_BUFFER_SIZE = 128 * 1024;

    public static int WRITE_BUFFER_SIZE = 128 * 1024;

    private final static NioSocketAcceptor mAcceptor = new NioSocketAcceptor();
    
    private final static BlacklistFilter mBlacklistFilter = new BlacklistFilter();

    protected void init(ProtocolEncoder encoder, ProtocolDecoder decoder, IoHandler handler) {

        synchronized (TcpServer.class) {
            if (null == mAcceptor.getFilterChain().get("Codec")) {
            	mAcceptor.getFilterChain().addLast("blacklist", mBlacklistFilter);
                mAcceptor.getFilterChain().addLast("Codec", new ProtocolCodecFilter(encoder, decoder));
                mAcceptor.getFilterChain().addLast("executor", new ExecutorFilter(Executors.newCachedThreadPool()));
                mAcceptor.setHandler(handler);

                final SocketSessionConfig scfg = mAcceptor.getSessionConfig();

                // 设置地址可重用
                scfg.setReuseAddress(true);
                // 设置读入缓存大小
                scfg.setReadBufferSize(READ_BUFFER_SIZE);
                // 设置输出缓存大小
                scfg.setSendBufferSize(WRITE_BUFFER_SIZE);
                // 超时时间60s
                scfg.setIdleTime(IdleStatus.READER_IDLE, 60);
                // scfg.setIdleTime(IdleStatus.WRITER_IDLE, 20);
            } else {
            }
        }
    }

    /**
     * @author wangzhe
     * @date 2016年4月14日
     * @description 获取当前连接
     *
     * @return
     */
    public Map<Long, IoSession> getManagedSessions() {
        synchronized (TcpServer.class) {
            return mAcceptor.getManagedSessions();
        }
    }

    public void bind(int port) {
        synchronized (TcpServer.class) {
            String tip = "程序已启动";
            try {
                mAcceptor.bind(new InetSocketAddress(port));
                mLogger.info(" Server(port:" + port + ") started...");
            } catch (final IOException e) {
                tip = "端口 " + port + " 被占用，请重换端口号";
                mLogger.error(tip, e);
            }
        }
    }

    public void destroy() {
        synchronized (TcpServer.class) {
            mAcceptor.dispose();
        }
    }
    
    /**
     * 添加黑名单
     * 
     * @param ip
     */
    public static void addBlackList(String ip){
    	try {
			mBlacklistFilter.block(InetAddress.getByName(ip));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 初始化黑名单
     * 
     * @param ip
     */
    public static void initBlacklist(InetAddress[] ips){
		mBlacklistFilter.setBlacklist(ips);
    }
    
    /**
     * 移除黑名单
     * 
     * @param ip
     */
    public static void removeBlackList(String ip){
    	try {
			mBlacklistFilter.unblock(InetAddress.getByName(ip));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }
}
