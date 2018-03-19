package com.cnfantasia.server.api.commonBusiness.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.service.ICommonGatherDataService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.domainbase.commonGatherData.entity.CommonGatherData;


public class GatherDataPool {
	protected final static Log logger = LogFactory.getLog(GatherDataPool.class);
	private static BlockingQueue<CommonGatherData> appendQueue = new LinkedBlockingQueue<CommonGatherData>();
	static{
		for(int i=0;i<2;i++){
			ICommonGatherDataService commonGatherDataService = (ICommonGatherDataService)CnfantasiaCommbusiUtil.getBeanManager("commonGatherDataService");
			new Thread(new GatherDataPoolWorker(appendQueue,commonGatherDataService)).start();
		}
		/*for(int i=0;i<2;i++){
			new Thread(new CommonGatherDataWorker(updateQueue,(ICommonGatherDataService)Env.getBean("clientUpdateService"))).start();
		}*/
	}
	static AtomicInteger at = new AtomicInteger(0);
	/** 批量插入数据量 */
	private static final int SUBMIT_NUMBER = 100;
	public static void addGatherData(CommonGatherData info) {
		at.incrementAndGet();
		appendQueue.offer(info); 
	}
	
	/**
	 * 每隔一段时间批量插入
	 * @author pony_ma
	 *
	 */
	static class GatherDataPoolWorker implements Runnable {
		private final BlockingQueue<CommonGatherData> queue;
		private final ICommonGatherDataService commonGatherDataService;
		GatherDataPoolWorker(BlockingQueue<CommonGatherData> objQueue,ICommonGatherDataService commonGatherDataService){
			queue = objQueue;
			this.commonGatherDataService = commonGatherDataService;
		}
		public void run() {
			while (true) {
				try {
					List<CommonGatherData> lstInfo = new ArrayList<CommonGatherData>();
					queue.drainTo(lstInfo, SUBMIT_NUMBER);
					commonGatherDataService.appendCommonGatherData2Handle(lstInfo);
					while(queue.size()==0){//调用size比较多，用LinkedBlockingQueue的size无需对队列加锁
						Thread.sleep(60*1000);//5*60*1000
					}
				} catch (Exception ex) {
					logger.error("add commonGatherData failed!!!", ex);
				}
			}

		}

	}
	
}
