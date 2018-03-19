/**   
* Filename:    IPinyipinDao.java   
* @version:    1.0  
* Create at:   2014年10月15日 上午9:25:01   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pinyipin.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.pinyipin.entity.PinyipinContentEntity;
import com.cnfantasia.server.api.pinyipin.entity.PinyipinReserveEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communityPinyipinReserve.entity.CommunityPinyipinReserve;

/**
 * Filename:    IPinyipinDao.java
 * @version:    1.0.0
 * Create at:   2014年10月15日 上午9:25:01
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月15日       shiyl             1.0             1.0 Version
 */
public interface IPinyipinDao {
	
	/**
	 * 查询最热的一个拼单
	 * @param userId 用户Id
	 * @param groupBuildingId 所属小区Id
	 * @return
	 */
	public PinyipinContentEntity selectMostHotContent(BigInteger userId,BigInteger groupBuildingId);
	
	/**
	 * 查询热门拼单列表
	 * @param userId
	 * @param groupBuildingId 所属小区Id
	 * @return
	 */
	public List<PinyipinContentEntity> selectHotContentList(BigInteger userId,BigInteger groupBuildingId,PageModel pageModel);
	
	/**
	 * 查询我发起的拼单列表
	 * @param userId
	 * @param groupBuildingId 所属小区Id
	 * @return
	 */
	public List<PinyipinContentEntity> selectLaunchContentList(BigInteger userId,BigInteger groupBuildingId,PageModel pageModel);
	
	/**
	* 查询查询我发起的拼单详情
	* @param userId 用户Id
	* @param contentId 拼单Id
	* @return
	*/
	public PinyipinContentEntity selectPinyipinContentDetail(BigInteger userId, BigInteger contentId);
	
//	/**
//	 * 查询查询我发起的拼单详情
//	 * @param userId 用户Id
//	 * @param contentId 拼单Id
//	 * @return
//	 */
//	public PinyipinContentEntity selectLaunchContentDetail(BigInteger userId,BigInteger contentId);
	
	/**
	 * 查询我参与的拼单列表
	 * @param userId 用户Id
	 * @param groupBuildingId 所属小区Id
	 * @return
	 */
	public List<PinyipinContentEntity> selectJoinContentList(BigInteger userId,BigInteger groupBuildingId,PageModel pageModel);
	
	/**
	 * 查询我发起的和参与的拼单列表
	 * @param userId
	 * @param groupBuildingId
	 * @param pageModel
	 * @return
	 */
	public List<PinyipinContentEntity> selectLaunchAndJoinContentList(BigInteger userId, BigInteger groupBuildingId,
			PageModel pageModel);
	
	/**
	 * 查询拼单的当前预定记录数
	 * @param contentId
	 * @return
	 */
	public Integer selectJoinTotalCount(BigInteger contentId);
	
	/**
	 * 用户删除发起的拼单
	 * @param userId 用户Id
	 * @param contentId 拼单Id
	 * @return
	 */
	public Integer deleteLaunchContent(BigInteger userId,BigInteger contentId);
	
//	/**
//	 * 参与拼单用户查看预定拼单详情
//	 * @param userId 用户Id
//	 * @param contentId 拼单Id
//	 * @return
//	 */
//	public PinyipinContentEntity selectJoinReserveContentDetail(BigInteger userId,BigInteger contentId);
	
	/**
	 * 查询指定用户参与的拼单信息
	 * @param joinUserId 参与拼单的用户Id
	 * @param contentId 拼单Id
	 * @return
	 */
	public List<CommunityPinyipinReserve> selectPinyipinReserveById(BigInteger joinUserId,BigInteger contentId);
	
	/**
	 * 发起拼单用户查看预定列表
	 * @param userId 用户Id
	 * @param contentId 拼单Id
	 * @return
	 */
	public List<PinyipinReserveEntity> selectLaunchPinyipinReserveList(BigInteger userId,BigInteger contentId);
	/**
	 * 查询参与拼单的用户列表
	 * @param userId
	 * @param contentId
	 * @return
	 */
	public List<UserSimpleEntity> selectLaunchPinyipinReserveUserListByTime(BigInteger userId,BigInteger contentId);
	
	/**
	 * 修改指定用户参与的拼单数量(总量)
	 * @param currUserId 当前用户Id
	 * @param pinyipinReverseId 待修改的记录Id
	 * @param totalCount 待修改的总记录数
	 * @return 返回当前总的记录数
	 */
	public Integer updReserveContentByTotalCount(BigInteger currUserId,BigInteger pinyipinReverseId,Integer totalCount);
	
	/**
	 * 修改指定用户参与的拼单数量(增量)
	 * @param currUserId 当前用户Id
	 * @param pinyipinReverseId 待修改的记录Id
	 * @param changeCount 待修改的增量记录数
	 * @return 返回当前总的记录数	
	 */
	public Integer updReserveContentByChangeCount(BigInteger currUserId,BigInteger pinyipinReverseId,Integer changeCount);
	
	/**
	 * 标记拼单的发货清单状态为已生成成功
	 * @param userId
	 * @param contentId
	 * @return
	 */
	public Integer updatePinyipinContent2Delivery(BigInteger userId, BigInteger contentId);
	
	/**
	 * 发起拼单者标记已领和未领
	 * @param currUserId
	 * @param pinyipinReverseId 待修改的记录Id
	 * @param calimStatus 指定的领取状态
	 * @return
	 */
	public Integer updateClaimStatus(BigInteger currUserId,BigInteger pinyipinReverseId,Integer calimStatus);

	/**
	 * 查询已过时拼单，若过时则更改拼单状态为已过时
	 * @return
	 */
	public Integer updatePinyipinContentTimeOut();

	/**
	 * 查询所有热门拼单列表最新更新时间
	 * @param userId
	 * @return
	 */
	public String selectAllHotContentListLastUpdTime(BigInteger userId);

	/**
	 * 查询所有我发起的和我参与的拼单最新更新时间
	 * @param userId
	 * @return
	 */
	public String selectAllLaunchAndJoinContentListLastUpdTime(BigInteger userId);

}
