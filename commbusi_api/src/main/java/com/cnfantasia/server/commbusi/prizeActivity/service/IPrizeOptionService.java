/**   
* Filename:    IPrizeOptionService.java   
* @version:    1.0  
* Create at:   2015年9月10日 上午11:41:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月10日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.GiftRepeatMap;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeGiftEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeGiftForPrize;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeOptionEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeOptionForList;
import com.cnfantasia.server.commbusi.prizeActivity.entity.PriGiftUploadParam;

/**
 * Filename:    IPrizeOptionService.java
 * @version:    1.0.0
 * Create at:   2015年9月10日 上午11:41:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月10日       shiyl             1.0             1.0 Version
 */
public interface IPrizeOptionService {
	/**
	 * 查询奖项列表
	 * @param name
	 * @param qryStatus
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeOptionForList> getMsPrizeOptionList(String name,
			Integer qryStatus,Integer useStatus, PageModel pageModel);
	
	/**
	 * 查询奖项详情
	 * @param priOptId
	 * @return
	 */
	public MsPrizeOptionEntity getMsPrizeOptionDetail(BigInteger priOptId);
	
	/**
	 * 删除奖项
	 * @param priOptId
	 * @return
	 */
	public void deleteMsPrizeOption(BigInteger priOptId);
	
	/**
	 * 新增奖项
	 * @param name 奖项名称
	 * @param valiStartTime 有效开始时间
	 * @param valiEndTime 有效截止时间
	 * @param iconImage 图标信息
	 * @param luckDesc 幸运信息描述
	 * @param useDesc 使用描述
	 * @param linkUrl 链接地址
	 * @param comment 备注信息
	 * @param status 奖项状态
	 * @return
	 */
	public void addMsPrizeOption(BigInteger userId,String name,String valiStartTime,String valiEndTime,RequestFileEntity iconImage,String luckDesc,String useDesc,String linkUrl,String comment,Integer status,String[] cityNameList);
	
	/**
	 * 修改奖项
	 * @param userId
	 * @param optId
	 * @param name
	 * @param valiStartTime
	 * @param valiEndTime
	 * @param iconImage
	 * @param luckDesc
	 * @param useDesc
	 * @param linkUrl
	 * @param comment
	 * @param status
	 */
	public void updMsPrizeOption(BigInteger userId,BigInteger optId,String name,String valiStartTime,String valiEndTime,RequestFileEntity iconImage,String luckDesc,String useDesc,String linkUrl,String comment,Integer status,String[] cityNameList);
	
	/**
	 * 删除奖品
	 * @param giftId
	 * @return
	 */
	public void deleteMsPrizeGift(BigInteger giftId);
	
	/**
	 * 上传奖品信息
	 * @param priOptId
	 * @param unitGiftList
	 * @return
	 */
	public Integer uploadPriGift(BigInteger priOptId,List<PriGiftUploadParam> unitGiftList,GiftRepeatMap giftRepeatMap);
	
	/**
	 * 查询奖品列表
	 * @param priOptId 奖项Id
	 * @param codeName 兑换码名称
	 * @param valueCode 兑换码取值
	 * @param uqMark 唯一标识码
	 * @param qryStatus
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeGiftEntity> getMsPrizeGiftList(BigInteger priOptId,String codeName,String valueCode,String uqMark,Integer qryStatus,PageModel pageModel);

	/**
	 * 查询奖品详情
	 * @param giftId 奖品Id
	 * @return
	 */
	public MsPrizeGiftForPrize getMsPrizeGiftDetail(BigInteger giftId);
	
}
