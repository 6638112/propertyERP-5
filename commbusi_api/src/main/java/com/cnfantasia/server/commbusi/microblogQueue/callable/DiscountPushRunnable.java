package com.cnfantasia.server.commbusi.microblogQueue.callable;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.commbusi.microblogQueue.cache.GbLeastDiscountCache;

public class DiscountPushRunnable implements Runnable{

	private BigInteger userId;
	private BigInteger gbId;
	private Double discount;
	private BigInteger prizeRecId;
	private BigInteger userHasRoomFId;
	
	public DiscountPushRunnable(BigInteger userId,BigInteger gbId,Double discount,BigInteger userHasRoomFId,BigInteger prizeRecId){
		this.userId = userId;
		this.gbId = gbId;
		this.discount = discount;
		this.userHasRoomFId = userHasRoomFId;
		this.prizeRecId = prizeRecId;
	}
	@Override
	public void run() {
		if(discount==0){//0折
			ApplicationContextBothUtil.getMicroblogQueueService().discountZero(userId,userHasRoomFId);
		}else{
			GbLeastDiscountCache.checkAndUpdate(userId,gbId, discount,prizeRecId,userHasRoomFId);//小区最低折扣
		}
	}
	
	public static final ExecutorService pool = Executors.newFixedThreadPool(200);
	public static void execute(BigInteger prizeUserId,BigInteger gbId,Double discount,BigInteger tUserHasTRoomFId,BigInteger prizeRecordAddId){
		pool.execute(new DiscountPushRunnable(prizeUserId, gbId, discount, tUserHasTRoomFId,prizeRecordAddId));
	}
}
