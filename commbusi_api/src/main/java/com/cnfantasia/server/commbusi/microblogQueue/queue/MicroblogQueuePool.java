package com.cnfantasia.server.commbusi.microblogQueue.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.commbusi.microblogQueue.serviceUn.IMicroblogQueueService;
import com.cnfantasia.server.domainbase.microblogQueue.entity.MicroblogQueue;

public class MicroblogQueuePool {
	protected final static Logger logger = Logger.getLogger(MicroblogQueuePool.class);
	//保存的消息队列List
	private static BlockingQueue<MicroblogQueue> insertQueue = new LinkedBlockingQueue<MicroblogQueue>();
	//开启两个线程跑
	static{
		for(int i=0;i<2;i++){
			new Thread(new ClientInfoWorker(insertQueue,ApplicationContextBothUtil.getMicroblogQueueService())).start();
		}
	}
	
	/** 批量插入数据量 */
	private static final int SUBMIT_NUMBER = 100;
	public static void addActivity(MicroblogQueue mq) {
		insertQueue.offer(mq); 
	}
	
	/**
	 * 满足队列条件就批量插入
	 */
	static class ClientInfoWorker implements Runnable {
		private final BlockingQueue<MicroblogQueue> queue;
		private final IMicroblogQueueService microblogQueueService;
		ClientInfoWorker(BlockingQueue<MicroblogQueue> objQueue,IMicroblogQueueService mqService){
			queue = objQueue;
			this.microblogQueueService = mqService;
		}
		public void run() {
			while (true) {
				try {
					List<MicroblogQueue> mqList = new ArrayList<MicroblogQueue>();
					queue.drainTo(mqList, SUBMIT_NUMBER);
					microblogQueueService.saveMQForQueueService(mqList);
					while(queue.size()==0){//调用size比较多，用LinkedBlockingQueue的size无需对队列加锁
						Thread.sleep(60*1000);//5*60*1000
					}
				} catch (Exception ex) {
					logger.error("MicroblogQueueService.saveMQForQueueService failed!!!", ex);
					ex.printStackTrace();
				}
			}
		}
	}
	
//	/**
//	 * 根据各模块的参数不同封装为街坊消息队列
//	 * */
//	public static MicroblogQueue packageMicroblogQueue(String text, int sourceType, int isTiming,
//			final BigInteger groupbuildingId, Long levelCode){
//		MicroblogQueue queue = new MicroblogQueue();
//		queue.setSourceType(sourceType);
//		queue.setText(text);
//		queue.setIsTiming(isTiming);
//		queue.setGroupBuildingId(groupbuildingId);
//		queue.setLevelCode(levelCode);
//		queue.setSourceType(sourceType);
//		queue.setUserId(getMQPushUserId(sourceType));
//		queue.setUserId(getMQPushUserId(sourceType));
//		if(isTiming==MicroblogQueueConstant.Timing.YES){
//			queue.setValidTime(CnfantasiaCommbusiUtil.getNextTime(Calendar.DATE,3));//定时消息有效期保存3天
//		}else{
////			queue.setValidTime(CnfantasiaCommbusiUtil.getCurrentTime());
//			queue.setValidTime(CnfantasiaCommbusiUtil.getNextTime(Calendar.HOUR_OF_DAY,1));
//		}
//		queue.setPushStatus(MicroblogQueueConstant.Push_Status.NO);
//		queue.setLevelCode(levelCode);
//		return queue;
//	}
	
//	/**
//	 * 根据数据来源不同设置不同的参数值
//	 * */
//	static BigInteger getMQPushUserId(int sourceType){
//		if(sourceType==MicroblogQueueConstant.Source_Type.CS){//商家
//			return MicroblogQueueConstant.Sys_User_Id.CS;
//		}else if(sourceType==MicroblogQueueConstant.Source_Type.PC){//物业
//			return MicroblogQueueConstant.Sys_User_Id.PC;
//		}else{//其他都是解放区
//			return MicroblogQueueConstant.Sys_User_Id.ADMIN;
//		}
//	}
	
}
