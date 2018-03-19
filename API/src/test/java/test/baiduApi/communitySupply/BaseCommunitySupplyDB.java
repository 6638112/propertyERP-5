/**   
* Filename:    BaseCommunitySupplyDB.java   
* @version:    1.0  
* Create at:   2014年11月26日 上午6:58:41   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月26日    shiyl      1.0         1.0 Version   
*/
package test.baiduApi.communitySupply;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.communitySupply.service.IAutoFetchSupllyDataService;
import com.cnfantasia.server.api.room.service.IGroupBuildingService;

/**
 * Filename:    BaseCommunitySupplyDB.java
 * @version:    1.0.0
 * Create at:   2014年11月26日 上午6:58:41
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月26日       shiyl             1.0             1.0 Version
 */
public class BaseCommunitySupplyDB extends BaseTest{
	protected IGroupBuildingService groupBuildingService;
	protected IAutoFetchSupllyDataService autoFetchSupllyDataService;
	protected ICommonRoomService commonRoomService;
	
	@Override
	public void init() {
		super.init();
		groupBuildingService = ctx.getBean("groupBuildingService", IGroupBuildingService.class);
		autoFetchSupllyDataService = ctx.getBean("autoFetchSupllyDataService", IAutoFetchSupllyDataService.class);
		commonRoomService = ctx.getBean("commonRoomService", ICommonRoomService.class);
	}
	
////	@Override
////	public BigInteger getCommunitySupplyType_Id(){
////		return uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_type);
////	}
//	public List<BigInteger> getCommunitySupplyType_Id(Integer size){
//		return uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_type,size);
//	}
////	@Override
////	public BigInteger getCommentsLabel_Id(){
////		return uuidManager.getNextUuidBigInteger(SEQConstants.t_comments_label);
////	}
//	public List<BigInteger> getCommentsLabel_Id(Integer size){
//		return uuidManager.getNextUuidBigInteger(SEQConstants.t_comments_label,size);
//	}
////	@Override
////	public BigInteger getCommunitySupplyTypeHasTCommentLabel_Id(){
////		return uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_type_has_t_comment_label);
////	}
//	public List<BigInteger> getCommunitySupplyTypeHasTCommentLabel_Id(Integer size){
//		return uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_type_has_t_comment_label,size);
//	}
////	@Override
////	public BigInteger getCommunitySupply_Id(){
////		return uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply);
////	}
//	public List<BigInteger> getCommunitySupply_Id(Integer size){
//		return uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply,size);
//	}
////	@Override
////	public BigInteger getCommunitySupplyContect_Id(){
////		return uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_contect);
////	}
//	public List<BigInteger> getCommunitySupplyContect_Id(Integer size){
//		return uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_contect,size);
//	}
////	@Override
////	public BigInteger getAddressBlockSelfDefined_Id(){
////		return uuidManager.getNextUuidBigInteger(SEQConstants.t_address_block_self_defined);
////	}
//	public List<BigInteger> getAddressBlockSelfDefined_Id(Integer size){
//		return uuidManager.getNextUuidBigInteger(SEQConstants.t_address_block_self_defined,size);
//	}
////	@Override
////	public BigInteger getGroupBuilding_Id(){
////		return uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building);
////	}
//	public List<BigInteger> getGroupBuilding_Id(Integer size){
//		return uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building,size);
//	}
////	@Override
////	public BigInteger getGroupBuildingHasTCommunitySupply_Id(){
////		return uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building_has_t_community_supply);
////	}
//	public List<BigInteger> getGroupBuildingHasTCommunitySupply_Id(Integer size){
//		return uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building_has_t_community_supply,size);
//	}
	
}
