/**   
* Filename:    ICommunitySupplyDao.java   
* @version:    1.0  
* Create at:   2014年8月26日 上午3:37:11   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.communitySupply.entity.CommunityPhoneTypeEntity;
import com.cnfantasia.server.api.communitySupply.entity.CommunitySupplyTypeEntity;
import com.cnfantasia.server.api.communitySupply.entity.GroupBuildingHasTCommunitySupply_Supply;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commentsLabel.entity.CommentsLabel;
import com.cnfantasia.server.domainbase.commentsPoints.entity.CommentsPoints;
import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;

/**
 * Filename:    ICommunitySupplyDao.java
 * @version:    1.0.0
 * Create at:   2014年8月26日 上午3:37:11
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月26日       shiyl             1.0             1.0 Version
 */
public interface ICommunitySupplyDao {
	/**
	 * 清除Mybatis缓存
	 */
	public void cleanCache();
	
	/**
	 * 查询商家详情
	 * @param communitySupplyId
	 * @param userId
	 * @return
	 */
	public GroupBuildingHasTCommunitySupply_Supply selectCommunitySupplyDetail(BigInteger communitySupplyId,BigInteger userId,BigInteger groupBuildingId);
	
	/**
	 * 查询商家类别列表
	 * @param parentTypeId 父类Id
	 * @param importanceLevel 重要级别
	 * @param pageModel 分页信息
	 * @return
	 */
	public List<CommunitySupplyTypeEntity> selectCommunitySupplyTypeList(BigInteger parentTypeId,Integer importanceLevel,PageModel pageModel);
	
	/**
	 * 搜索商家列表
	 * @param supplyTypeId 商家所属类别Id
	 * @param supplyNameKey 商家名称关键字
	 * @param pageModel
	 * @param userId
	 * @param groupBuildingId
	 * @return
	 */
	public List<GroupBuildingHasTCommunitySupply_Supply> searchCommunitySupplyList(BigInteger supplyTypeId,String supplyNameKey,PageModel pageModel
			,BigInteger userId,BigInteger groupBuildingId);
	
	/**
	 * 根据商家类别Id查询对应的评论标签列表
	 * @param supplyTypeId
	 * @return
	 */
	public List<CommentsLabel> selectCommentsLabelListBySupplyType(BigInteger supplyTypeId);
	
	/**
	 * 根据商家类别Id查询对应的评分项列表
	 * @param supplyTypeId
	 * @return
	 */
	public List<CommentsPoints> selectCommentsPointsListBySupplyType(BigInteger supplyTypeId);
	
	/**
	 * 联系方式呼叫次数加1
	 * @param contectId 对应商家联系方式的Id
	 * @return 返回被更新的记录数
	 */
	public Integer updateSupplyContectCallCount(BigInteger contectId);
	
	/**
	 * 查询对应联系方式的被呼叫次数
	 * @param contectId
	 * @return
	 */
	public Long selectSupplyContectCallCount(BigInteger contectId);
	
	/**
	 * 通过商家Id,查询其联系方式总的拨打次数
	 * @param communitySupplyId
	 * @return
	 */
	public Long selectSupplyContectTotalCallCountBySupplyId(BigInteger communitySupplyId);
	
	/**
	 * 通过联系方式Id,查询其所属商家的联系方式总的拨打次数
	 * @param contectId
	 * @return
	 */
	public Long selectSupplyContectTotalCallCountByContectId(BigInteger contectId);
	
	/**
	 * 查询推荐的商家类别列表
	 * @return
	 */
	public List<CommunitySupplyTypeEntity> selectCommunitySupplyTypeRecommend();
	
	/**
	 * 根据类别Id查询类别信息，包含其所属的顶级的类别信息
	 * @return
	 */
	public CommunitySupplyTypeEntity selectCommunitySupplyTypeBySupplyTypeId(BigInteger suppluTypeId);
	
	public List<CommunitySupplyContect> selectCommunitySupplyContect(BigInteger groupBuildingId);
	public List<CommunitySupply> selectCommunitySupply(BigInteger groupBuildingId);

//	/**
//	 * 查询所有商家类别列表
//	 * @return
//	 */
//	public List<CommunitySupplyTypeEntity> selectCommunitySupplyTypeAll();

	/**
	 * 查询一级商家类别列表
	 * @return
	 */
	public List<CommunitySupplyTypeEntity> selectCommunitySupplyTypeLevel01();

	/**
	 * 查询二级商家类别列表
	 * @return
	 */
	public List<CommunitySupplyTypeEntity> selectCommunitySupplyTypeLevel02();


    public List<CommunityPhoneTypeEntity> getCommunityPhoneTypeList(Integer version, BigInteger gbId);
}
