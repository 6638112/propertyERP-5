/**   
* Filename:    CacheConstant.java   
* @version:    1.0  
* Create at:   2015年7月8日 上午8:23:18   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.cache.constant;

import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.loan.constants.LoanDict;

/**
 * Filename:    CacheConstant.java
 * @version:    1.0.0
 * Create at:   2015年7月8日 上午8:23:18
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月8日       shiyl             1.0             1.0 Version
 */
public class CacheConstant {
	
	public static final String KEY_RECHARGE_PACKAGE = "KEY_RECHARGE_PACKAGE";
	
	/**
	 * 各缓存模块的编码
	 */
	public class ModelCode{
		/**周边服务范围，编码+id*/
		public static final String hset_sa_codeid = "hset_sa_codeid";
		/**社区商家类别信息*/
		public static final String hset_commSupplyType_info = "hset_commSupplyType_info";
		/**街坊消息推送类别信息*/
		public static final String hset_microblogPushType_info = "hset_microblogPushType_info";
		
		public static final String FLOW_RECHARGE = "FLOW_RECHARGE";
		
		public static final String PAY_SUCCESS = "PAY_SUCCESS";
		
		/**国家信息*/
		public static final String hset_acc_info = "_acc";
		/**省信息*/
		public static final String hset_ap_info = "_ap";
		/**行政市信息*/
		public static final String hset_ac_info = "_ac";
		/**行政区信息*/
		public static final String hset_ab_info = "_ab";
		
		/**微博列表数据访问记录*/
		public static final String hset_mc_vis_rec = "vis0908";
		/**微博列表当天已推送记录*/
		public static final String hset_mc_push_count = "push0908";
		/**小区当月最低折扣*/
		public static final String set_gb_least_discount = "_gl";
	}
	
	public static List<String> modelNamePerfixList;
	static{
		modelNamePerfixList = new ArrayList<String>();
//		modelNamePerfix.add(appendModelCodePerfix(ModelCode.hset_sa_codeid));
		modelNamePerfixList.add(appendModelCodePerfix(ModelCode.hset_acc_info));
		modelNamePerfixList.add(appendModelCodePerfix(ModelCode.hset_ap_info));
		modelNamePerfixList.add(appendModelCodePerfix(ModelCode.hset_ac_info));
		modelNamePerfixList.add(appendModelCodePerfix(ModelCode.hset_ab_info));
		modelNamePerfixList.add(appendModelCodePerfix(ModelCode.hset_mc_push_count));
		modelNamePerfixList.add(appendModelCodePerfix(ModelCode.set_gb_least_discount));
		modelNamePerfixList.add(appendModelCodePerfix(LoanDict.CACHE_KEY_PERFIX));
		modelNamePerfixList.add(appendModelCodePerfix(AccessDict.CachePrefix.CARNUM_KEY_PREFIX));
		modelNamePerfixList.add(appendModelCodePerfix(AccessDict.CachePrefix.TMP_CAR_PLOT_PREFIX));
	}
	public static boolean allowBatchDelByPerfix(String patternPerfix){
		return modelNamePerfixList.contains(patternPerfix);
	}
	
	public static String appendModelCodePerfix(String modelCode){
//		return modelCode+"_";
		return modelCode;
	}
	
}
