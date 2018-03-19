package com.cnfantasia.server.commbusi.microblogQueue.task;

import java.math.BigInteger;

public interface IMicroblogQueueTask {

	public void excutePushTask();
	public void excutePushTaskDbGbId();
	
	public void executePropertyGoodNewsPush();
//	public PropertyGoodNewsShellEntity fetchShellEntity(GBWithPropMonthYear gbInfo);
	
	public Integer callMQPush2MicroblogContent();
	public Integer callMQPush2MicroblogContentDbGbId();

	public Integer callMQPush2MicroblogContent(BigInteger gbId);
	
	/**
	 * 将队列信息转移到微博
	 * @param mqDetail
	 * @param gbId
	 * @return
	 */
	public int transferMQ2MicroblogContent(BigInteger queueId,BigInteger gbId);
	
	

}
