package com.cnfantasia.server.commbusi.microblogQueue.serviceUn;

import java.math.BigInteger;

import com.cnfantasia.server.commbusi.microblogQueue.entity.GBLeastDiscount;
import com.cnfantasia.server.commbusi.microblogQueue.entity.GBWithPropMonthYear;


/**
 * 小区最低折扣服务类
 * @author shiyl
 *
 */
public interface IGbLeastDiscountService {
	/**
	 * 查询小区Id当前物业信息
	 * 如果小区不能缴费，则部分月份数据会为空
	 * @param gbId
	 * @return
	 */
	public GBWithPropMonthYear getGbPropertyInfoByGbId(BigInteger gbId);
	
	/**
	 * 查询小区对应缴费周期最低折扣信息
	 * @param gbId
	 * @param propPayDateBegin
	 * @param propPayDateEnd
	 * @return
	 */
	public GBLeastDiscount getLeastDiscountByGbId(BigInteger gbId,String propPayDateBegin,String propPayDateEnd);
}
