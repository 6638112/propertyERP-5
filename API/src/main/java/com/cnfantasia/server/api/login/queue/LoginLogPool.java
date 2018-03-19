package com.cnfantasia.server.api.login.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.domainbase.loginLog.entity.LoginLog;
import com.cnfantasia.server.domainbase.loginLog.service.ILoginLogBaseService;

public class LoginLogPool {
	protected final static Logger LOG = Logger.getLogger(LoginLogPool.class);
	private static BlockingQueue<LoginLog> activityQueue = new LinkedBlockingQueue<LoginLog>();
	static{
		for(int i=0;i<2;i++){
			new Thread(new LoginLogWorker(activityQueue,(ILoginLogBaseService)CnfantasiaCommbusiUtil.getBeanManager("loginLogBaseService"))).start();
		}
		/*for(int i=0;i<2;i++){
			new Thread(new LoginLogWorker(updateQueue,(ILoginLogBaseService)Env.getBean("clientUpdateService"))).start();
		}*/
	}
	static AtomicInteger at = new AtomicInteger(0);
	/** 批量插入数据量 */
	private static final int SUBMIT_NUMBER = 500;
	public static void addActivity(LoginLog info) {
		//LOG.debug("offer");
		at.incrementAndGet();
		activityQueue.offer(info); 
	}
	
	/**
	 * 每隔一段时间批量插入
	 * @author pony_ma
	 *
	 */
	static class LoginLogWorker implements Runnable {
		private final BlockingQueue<LoginLog> queue;
		private final ILoginLogBaseService loginLogBaseService;
		LoginLogWorker(BlockingQueue<LoginLog> objQueue,ILoginLogBaseService updateService){
			queue = objQueue;
			this.loginLogBaseService = updateService;
		}
		public void run() {
			while (true) {
				try {
					List<LoginLog> lstInfo = new ArrayList<LoginLog>();
					queue.drainTo(lstInfo, SUBMIT_NUMBER);
					if(lstInfo.size()>0){
						loginLogBaseService.insertLoginLogBatch(lstInfo);
					}
					while(queue.size()==0){//调用size比较多，用LinkedBlockingQueue的size无需对队列加锁
						Thread.sleep(60*1000);//5*60*1000
					}
					
				} catch (Exception ex) {
					LOG.error("LoginLogWorker add activity failed!!!", ex);
				}
			}

		}

	}
	
}
