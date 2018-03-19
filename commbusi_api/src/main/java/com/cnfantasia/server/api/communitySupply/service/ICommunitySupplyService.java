/**   
* Filename:    ICommunitySupplyService.java   
* @version:    1.0  
* Create at:   2014年8月26日 上午3:08:21   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.communitySupply.entity.*;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commentsLabel.entity.CommentsLabel;
import com.cnfantasia.server.domainbase.commentsPoints.entity.CommentsPoints;

/**
 * Filename:    ICommunitySupplyService.java
 * @version:    1.0.0
 * Create at:   2014年8月26日 上午3:08:21
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月26日       shiyl             1.0             1.0 Version
 */
public interface ICommunitySupplyService {
	
	/**
	 * 根据定位获取附近的小区信息
	 * @param userId
	 * @param baiduLocation
	 * @return
	 */
	public BigInteger fetchGroupBuildingIdByPosition(BigInteger userId,BaiduLocation baiduLocation);
	
	/**
	 * 查询商家详情
	 * @param communitySupplyId
	 * @return
	 */
	public GroupBuildingHasTCommunitySupply_Supply getCommunitySupplyDetail(BigInteger communitySupplyId,BigInteger userId,BigInteger groupBuildId);
	
	/**
	 * 查询商家类别
	 * @param parentTypeId 父类Id
	 * @param importanceLevel 重要级别
	 * @param pageModel 分页信息
	 * @return
	 */
	public List<CommunitySupplyTypeEntity> getCommunitySupplyTypeList(BigInteger parentTypeId,Integer importanceLevel,PageModel pageModel);
	
//	public List<CommunitySupplyTypeEntity> getCommunitySupplyTypeAll();
//	public List<CommunitySupplyTypeEntity> getCommunitySupplyTypeLevel01();
//	public List<CommunitySupplyTypeEntity> getCommunitySupplyTypeLevel02();
	
	/**
	 * 搜索商家
	 * @param supplyTypeId 商家所属类别Id
	 * @param supplyNameKey 商家名称关键字
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupply_Supply> searchCommunitySupplyList(BigInteger supplyTypeId,String supplyNameKey
			,PageModel pageModel,BigInteger userId,BigInteger groupBuildId);
	
	/**
	 * 根据商家类别Id查询对应的评论标签列表
	 * @param supplyTypeId
	 * @return
	 */
	public List<CommentsLabel> getCommentsLabelListBySupplyType(BigInteger supplyTypeId);
	
	/**
	 * 根据商家类别Id查询对应的评分项列表
	 * @param typeId
	 * @return
	 */
	public List<CommentsPoints> getCommentsPointsListBySupplyType(BigInteger supplyTypeId);
	
	/**
	 * 增加联系方式呼叫次数
	 * @param contectId 对应商家联系方式的Id
	 * @return 返回总的被呼叫的次数
	 */
	public CallCountEntity addCallCount(BigInteger contectId);
	
	/**
	 * 查询推荐的商家类别列表
	 * @return
	 */
	public List<CommunitySupplyTypeEntity> getCommunitySupplyTypeRecommend();
	
	/**
	 * 根据类别Id查询类别信息，包含其所属的顶级的类别信息
	 * @return
	 */
	public CommunitySupplyTypeEntity getCommunitySupplyTypeBySupplyTypeId(BigInteger suppluTypeId);
	
	/**
	 * 自动抓取美丽加数据
	 */
	public void autoFetchMljiaData();

	public List<CommunityPhoneTypeEntity> getCommunityPhoneTypeList(Integer version, BigInteger gbId);

}
