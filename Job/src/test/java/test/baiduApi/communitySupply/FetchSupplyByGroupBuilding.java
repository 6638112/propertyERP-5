/**   
 * Filename:    FetchSupplyByGroupBuilding.java   
 * @version:    1.0  
 * Create at:   2014年11月26日 上午2:28:30   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年11月26日    shiyl      1.0         1.0 Version   
 */
package test.baiduApi.communitySupply;

import java.math.BigInteger;
import org.junit.Test;

/**
 * Filename: FetchSupplyByGroupBuilding.java
 * 
 * @version: 1.0.0 Create at: 2014年11月26日 上午2:28:30 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月26日 shiyl 1.0 1.0 Version
 */
public class FetchSupplyByGroupBuilding extends BaseCommunitySupplyDB {
	private static final String baiduLbsAK="Kbf7C7Nrrr43Az1HnDHHknWH";
//	private static final String baiduLbsAK="UYWm94sFG0VZGHaXQ2r270YW";//正式环境使用的
	public static void main(String[] args) throws Exception {
		// FetchSupplyByGroupBuilding tmpFetchSupplyByGroupBuilding = new
		// FetchSupplyByGroupBuilding();
		// tmpFetchSupplyByGroupBuilding.main();
	}

	@Test
	public void fetchDataByGroupBuildingList() {
//		// 查询审核通过的所有小区数据 233 深圳市的数据
//		List<GroupBuildingEntity> checkedGroupBuildingList = groupBuildingService.getGroupBuildingDimBySearchKey("",new BigInteger("233"), null, null);
////		List<GroupBuildingEntity> checkedGroupBuildingList = commonRoomService.selectGroupBuildingIsRegisted();
//		// 逐个遍历小区数据
//		for (GroupBuildingEntity currGroupBuilding : checkedGroupBuildingList) {
//			if (currGroupBuilding.getBaiduFetchStatus()!= null||currGroupBuilding.getCheckStatus().compareTo(RoomDict.CheckStatus.DaiShenHe)==0
//					||currGroupBuilding.getCheckStatus().compareTo(RoomDict.CheckStatus.ShenHeBuTongGuo)==0) {
//				System.out.println("跳过小区："+currGroupBuilding.getName()+"~"+currGroupBuilding.getAddressBlockEntity().getName()+"~"+currGroupBuilding.getAddressBlockEntity().getAddressCityEntity().getName());
//				continue;
//			}
//			try {
//				autoFetchSupllyDataService.fetchSupply2DBForBaidu(currGroupBuilding.getId(),baiduLbsAK);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
//		autoFetchSupllyDataService.fetchSupply2DB(new BigInteger("23"));
//		autoFetchSupllyDataService.fetchSupply2DB(new BigInteger("4350"));
//		autoFetchSupllyDataService.fetchSupply2DB(new BigInteger("4350"));
		autoFetchSupllyDataService.fetchSupply2DB(new BigInteger("100409"));
		
	}
	
//	@Test
	public void fetchSupply2DBForMljia() {
		autoFetchSupllyDataService.fetchSupply2DBForMljia(new BigInteger("23"), baiduLbsAK,true);
	}

}
