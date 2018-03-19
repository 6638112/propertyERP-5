package com.cnfantasia.server.ms.revenue.task;

import javax.annotation.Resource;

import com.cnfantasia.server.api.limitBuy.service.ILimitBuyService;

/**
 * 限时购商品价格同步task
 * @author wenfq  2017年8月29日
 *
 */
public class LimitBuyPriceSysTask implements ISynTask {
	
	@Resource
	ILimitBuyService limitBuyService;

	/**
	 * 活动开启后，商品售价变为限时促销价；<p>
     * 限时购活动结束或限时购商品卖完（即库存为0）时，还原商品的价格
	 */
	@Override
	public Integer execTask() {
		return limitBuyService.synchronizeProductPrice();
	}

}
