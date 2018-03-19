package com.cnfantasia.server.commbusi.microblogQueue.serviceUn;

import java.math.BigInteger;
import java.util.Date;

import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.commbusi.microblogQueue.dao.IMicroblogQueueDao;
import com.cnfantasia.server.commbusi.microblogQueue.entity.GBLeastDiscount;
import com.cnfantasia.server.commbusi.microblogQueue.entity.GBWithPropMonthYear;


/**
 * 小区最低折扣Service
 * @author shiyl
 *
 */
public class GbLeastDiscountService implements IGbLeastDiscountService{
	private IMicroblogQueueDao microblogQueueDao;
	public void setMicroblogQueueDao(IMicroblogQueueDao microblogQueueDao) {
		this.microblogQueueDao = microblogQueueDao;
	}

	@Override
	public GBWithPropMonthYear getGbPropertyInfoByGbId(BigInteger gbId) {
		GBWithPropMonthYear gbInfo = microblogQueueDao.selectGbPropertyCurrMonthInfoByGbId(gbId);
		if(gbInfo!=null&&gbInfo.getPropPayDateBegin()==null){
			Date nowTime = ApplicationContextBothUtil.getNowTime();
			gbInfo.freshPropInfo(nowTime);
		}
		return gbInfo;
	}

	@Override
	public GBLeastDiscount getLeastDiscountByGbId(BigInteger gbId,
			String propPayDateBegin, String propPayDateEnd) {
		return microblogQueueDao.selectLeastDiscountByGbId(gbId,propPayDateBegin,propPayDateEnd);
	}
	
	
}
