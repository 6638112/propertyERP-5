package com.cnfantasia.server.api.carMsgTask.task;

import java.util.Map;

import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.constant.AccessDict.Code;
import com.cnfantasia.server.api.access.service.ICarService;
import com.cnfantasia.server.api.access.service.ThirdCarFactory;

/**
 * 车禁定时任务
 * 
 * @author liyulin
 * @version 1.0 2017年12月14日 下午5:03:00
 */
public class CarTask {
//	private final static int nThreads = AccessDict.Code.class.getFields().length;
//	private final static ExecutorService synMonthCarPayLogFixedThreadPool = Executors.newFixedThreadPool(nThreads);
//	private final static ExecutorService pushMonthCarPayLogFixedThreadPool = Executors.newFixedThreadPool(nThreads);
//	private final static ExecutorService pushTempCarPayLogFixedThreadPool = Executors.newFixedThreadPool(nThreads);
//	private final static ExecutorService synCarInfoFixedThreadPool = Executors.newFixedThreadPool(nThreads);
//	private final static ExecutorService synParkingRecordFixedThreadPool = Executors.newFixedThreadPool(nThreads);
	
	private ThirdCarFactory thirdCarFactory;
	
	public void setThirdCarFactory(ThirdCarFactory thirdCarFactory) {
		this.thirdCarFactory = thirdCarFactory;
	}
	
	public void releaseTaskLock() {
		Map<Code, ICarService> carServiceMap = thirdCarFactory.getCarServiceMap();
		for (Map.Entry<Code, ICarService> entry : carServiceMap.entrySet()) {
			entry.getValue().releaseTaskLock();
		}
	}
	
	/*==============小蜜蜂==============*/
	public void pushXMFTmpCarPayLogTask(){
		thirdCarFactory.getCarServiceMap().get(AccessDict.Code.xiaomifeng).pushTempCarPayLog();
	}
	
	public void pushXMFMonthCarPayLogTask(){
		thirdCarFactory.getCarServiceMap().get(AccessDict.Code.xiaomifeng).pushMonthCarPayLog();
	}
	
	public void synXMFMonthCarInfoTask(){
		thirdCarFactory.getCarServiceMap().get(AccessDict.Code.xiaomifeng).synCarInfo();
	}
	
	public void synXMFMonthCarPayLogTask(){
		thirdCarFactory.getCarServiceMap().get(AccessDict.Code.xiaomifeng).synMonthCarPayLog();
	}
	
	public void synXMFParkingRecord(){
		thirdCarFactory.getCarServiceMap().get(AccessDict.Code.xiaomifeng).synParkingRecord();
	}
	
	/*==============华安==============*/
	public void pushHATmpCarPayLogTask(){
		thirdCarFactory.getCarServiceMap().get(AccessDict.Code.huaan).pushTempCarPayLog();
	}
	
	public void pushHAMonthCarPayLogTask(){
		thirdCarFactory.getCarServiceMap().get(AccessDict.Code.huaan).pushMonthCarPayLog();
	}
	
	public void synHAMonthCarInfoTask(){
		thirdCarFactory.getCarServiceMap().get(AccessDict.Code.huaan).synCarInfo();
	}
	
	/*==============华鹏飞==============*/
	public void pushHPFMonthCarPayLogTask(){
		thirdCarFactory.getCarServiceMap().get(AccessDict.Code.huapengfei).pushMonthCarPayLog();
	}
	
	public void pushHPFTempCarPayLogTask(){
		thirdCarFactory.getCarServiceMap().get(AccessDict.Code.huapengfei).pushTempCarPayLog();
	}
	
	public void synHPFMonthCarFeeTask(){
		thirdCarFactory.getCarServiceMap().get(AccessDict.Code.huapengfei).synCarInfo();
	}
	
	/*private void execTask(ExecutorService executorService, final String method) {
		Map<Code, ICarService> carServiceMap = thirdCarFactory.getCarServiceMap();
		for (final Map.Entry<Code, ICarService> entry : carServiceMap.entrySet()) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					ICarService carService = entry.getValue();
					carService.synMonthCarPayLog();
					
			        Class<?> clazz = carService.getClass(); 
			        Method m;
					try {
						m = clazz.getDeclaredMethod(method);
						m.invoke(carService);
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}*/
	
}
