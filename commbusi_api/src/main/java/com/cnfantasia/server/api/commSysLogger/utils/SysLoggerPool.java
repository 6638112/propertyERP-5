package com.cnfantasia.server.api.commSysLogger.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;
import com.cnfantasia.server.domainbase.commLogger.service.ICommLoggerBaseService;

public class SysLoggerPool {
	
	protected final static Logger LOG = Logger.getLogger(SysLoggerPool.class);
	
	private static BlockingQueue<CommLogger> loggerQueue = new LinkedBlockingQueue<CommLogger>(2000);
	
	static{
		for(int i=0;i<5;i++){
			new Thread(new SysLoggerWorker(loggerQueue, (ICommLoggerBaseService)CnfantasiaCommbusiUtil.getBeanManager("commLoggerBaseService"))).start();
		}
	}
	
//	static AtomicInteger at = new AtomicInteger(0);
	/** 批量插入数据量 */
	private static final int SUBMIT_NUMBER = 100;
	
	public static void addCommLogger(CommLogger logger) {
		loggerQueue.offer(logger);
	}
	
	/**
	 * 每隔一段时间批量插入
	 * @author weijian_ye
	 *
	 */
	static class SysLoggerWorker implements Runnable {
		
		private final BlockingQueue<CommLogger> queue;
		
		private final ICommLoggerBaseService loggerService;
		
		SysLoggerWorker(BlockingQueue<CommLogger> objQueue, ICommLoggerBaseService loggerService){
			queue = objQueue;
			this.loggerService = loggerService;
		}
		
		public void run() {
			while (true) {
				try {
					List<CommLogger> loggerList = new ArrayList<CommLogger>();
					queue.drainTo(loggerList, SUBMIT_NUMBER);
//					at.decrementAndGet();
					if(loggerList != null && loggerList.size() != 0) {
						loggerService.insertCommLoggerBatch(loggerList);
					}
					
					while(queue.size() == 0){	//调用size比较多，用LinkedBlockingQueue的size无需对队列加锁
						Thread.sleep(2000);	//5*60*1000
					}
				} catch (Exception ex) {
					LOG.error("add CommLogger failed!!!", ex);
				}
			}

		}

	}
	
}
