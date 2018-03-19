/**   
* Filename:    MicroblogQueueFactory.java   
* @version:    1.0  
* Create at:   2015年8月25日 下午4:55:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.microblogQueue.task;

import java.math.BigInteger;

import com.cnfantasia.server.api.operation.entity.MultiSaIdEntity;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.commbusi.microblogQueue.constant.MicroblogQueueConstant;
import com.cnfantasia.server.commbusi.microblogQueue.constant.MicroblogQueueDict;
import com.cnfantasia.server.domainbase.microblogQueue.entity.MicroblogQueue;

/**
 * Filename:    MicroblogQueueFactory.java
 * @version:    1.0.0
 * Create at:   2015年8月25日 下午4:55:19
 * Description:街坊消息队列创建工厂
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月25日       shiyl             1.0             1.0 Version
 */
public class MicroblogQueueFactory implements IMicroblogQueueFactory{
	
	private IAddressOperationService addressOperationService;
	public void setAddressOperationService(IAddressOperationService addressOperationService) {
		this.addressOperationService = addressOperationService;
	}
	
	@Override
	public  MicroblogQueue createMicroblogQueueByAddressCity(String text, int sourceType, int isTiming,
			final BigInteger addressCityId, Long levelCode){
		BigInteger groupbuildingId = MicroblogQueueConstant.DEFAULT_NULL_GroupBuild;
		BigInteger saId = null;
		{//根据市Id得到对应的服务范围记录的Id
			MultiSaIdEntity multiSaIdEntity = addressOperationService.getCodeIdMultiEntity(null, null, addressCityId, null, null);
			saId = multiSaIdEntity.getCitySaId();//取城市级别的Id
			if(saId==null){
				saId = MicroblogQueueConstant.DEFAULT_ERROR_SaId;
			}
		}
		String linkDetailJson = null;
		return createMicroblogQueue(text, sourceType, isTiming, groupbuildingId, saId, levelCode,linkDetailJson);
	}
	
	@Override
	public  MicroblogQueue createMicroblogQueueByGB(String text, int sourceType, int isTiming,
			final BigInteger groupbuildingId, Long levelCode){
		String linkDetailJson = null;
		return createMicroblogQueueByGB(text, sourceType, isTiming, groupbuildingId, levelCode, linkDetailJson);
	}
	
	@Override
	public MicroblogQueue createMicroblogQueueByGB(String text, int sourceType, int isTiming, BigInteger groupbuildingId,
			Long levelCode, String linkDetailJson) {
		BigInteger saId = MicroblogQueueConstant.DEFAULT_NULL_SaId;
		return createMicroblogQueue(text, sourceType, isTiming, groupbuildingId, saId, levelCode, linkDetailJson);
	}
	
	private  MicroblogQueue createMicroblogQueue(String text, int sourceType, int isTiming,
			final BigInteger groupbuildingId,final BigInteger saId, Long levelCode,String linkDetailJson){
		MicroblogQueue queue = new MicroblogQueue();
		queue.setSourceType(sourceType);
		queue.setText(text);
		queue.setGroupBuildingId(groupbuildingId);
		queue.setSaId(saId);//syl-add-2015-8-25 17:29:41
		queue.setLevelCode(levelCode);
		queue.setSourceType(sourceType);
		queue.setUserId(getMQPushUserId(sourceType));
		
		queue.setIsTiming(isTiming);
//		if(isTiming==MicroblogQueueDict.Timing.YES){
//		//	queue.setValidTime(ApplicationContextBothUtil.getNextTime(Calendar.DATE,3));//定时消息有效期保存3天
//			queue.setValidTime(ApplicationContextBothUtil.getNextTime(Calendar.DATE,1));
//		}else{
//			queue.setValidTime(ApplicationContextBothUtil.getNextTime(Calendar.HOUR_OF_DAY,1));
//		}
		queue.setValidTime(DateUtil.getCurrDay2359(ApplicationContextBothUtil.getCurrentTime()));
		
		queue.setPushStatus(MicroblogQueueDict.Push_Status.NO);
		queue.setLevelCode(levelCode);
		
		queue.setCreateTime(ApplicationContextBothUtil.getCurrentTime());//syl-add-2015-8-25 17:29:41
		queue.setPushAble(MicroblogQueueDict.PushAble.NO);//syl-add-2015-8-25 17:29:41
		queue.setLinkDetailJson(linkDetailJson);;
		return queue;
	}
	
	/**
	 * 根据数据来源不同设置不同的参数值
	 * */
	private  BigInteger getMQPushUserId(int sourceType){
		if(sourceType==MicroblogQueueDict.Source_Type.CS){//商家
			return MicroblogQueueDict.Sys_User_Id.CS;
		}else if(sourceType==MicroblogQueueDict.Source_Type.PC){//物业
			return MicroblogQueueDict.Sys_User_Id.PC;
		}else{//其他都是解放区
			return MicroblogQueueDict.Sys_User_Id.ADMIN;
		}
	}
	
}
