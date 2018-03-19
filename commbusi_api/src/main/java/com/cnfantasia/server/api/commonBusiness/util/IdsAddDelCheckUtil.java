/**   
 * Filename:    IdsAddDelCheckUtil.java   
 * @version:    1.0  
 * Create at:   2014年9月16日 上午11:16:15   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年9月16日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.commonBusiness.util;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cnfantasia.server.api.commonBusiness.entity.AddAndDelIdsEntity;

/**
 * Filename: IdsAddDelCheckUtil.java
 * 
 * @version: 1.0.0 Create at: 2014年9月16日 上午11:16:15 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年9月16日 shiyl 1.0 1.0 Version
 */
public class IdsAddDelCheckUtil {
	
	/**
	 * 分析哪些需要新增，哪些需要删除
	 * @param existIds 当前已存在的Id
	 * @param collectTypeIds 待新增的Id
	 * @return
	 */
	public static AddAndDelIdsEntity analyze(List<BigInteger> existIds, List<BigInteger> collectTypeIds) {
		// 对比当前用户要提交的 筛选出需要新增的和需要删除的
		Set<BigInteger> toAddIds = new HashSet<BigInteger>();
		Set<BigInteger> toDelIds = new HashSet<BigInteger>();
		Set<BigInteger> noNeedDoneIds = new HashSet<BigInteger>();// 要新增，且当前已经存在的，不需要处理
		if (existIds.size() == 0) {
			toAddIds.addAll(collectTypeIds);
		} else {
			for (BigInteger collectTypeTmpId : collectTypeIds) {
				boolean needAdd = true;
				for (BigInteger existTmpId : existIds) {
					if (collectTypeTmpId.compareTo(existTmpId) == 0) {// 已经存在，不需要新增
						needAdd = false;
						break;
					}
				}
				if(needAdd){
					toAddIds.add(collectTypeTmpId);
				}else{
					noNeedDoneIds.add(collectTypeTmpId);
				}
			}
			
			for (BigInteger existTmpId : existIds) {// 查找需要删除的
				boolean needDel = true;
				for (BigInteger tmpNoNeed : noNeedDoneIds) {
					if (tmpNoNeed.compareTo(existTmpId) == 0) {
						needDel = false;// 存在不需要删除的
					}
				}
				if (needDel) {
					toDelIds.add(existTmpId);
				}
			}
		}
		return new AddAndDelIdsEntity(toAddIds, toDelIds, noNeedDoneIds);
	}
	
}
