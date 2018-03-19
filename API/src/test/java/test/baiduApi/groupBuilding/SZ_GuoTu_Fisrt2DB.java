/**   
* Filename:    SZ_GuoTu_Fisrt2DB.java   
* @version:    1.0  
* Create at:   2014年11月27日 上午9:49:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月27日    shiyl      1.0         1.0 Version   
*/
package test.baiduApi.groupBuilding;

import java.math.BigInteger;
import java.util.List;

import test.baiduApi.entity.SZGuoTuGroupBuilding;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.room.constant.GroupBuildingDict;
import com.cnfantasia.server.common.utils.FileUtils;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

/**
 * Filename:    SZ_GuoTu_Fisrt2DB.java
 * @version:    1.0.0
 * Create at:   2014年11月27日 上午9:49:26
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月27日       shiyl             1.0             1.0 Version
 */
public class SZ_GuoTu_Fisrt2DB extends BaseGroupBuildingDB{
	public static final String srcFilePath = "F:\\addressData\\szGuoTu_first.json";
	public static void main(String[] args) throws Exception {
		SZ_GuoTu_Fisrt2DB aaa = new SZ_GuoTu_Fisrt2DB();
		aaa.main();
	}
	/**
	 * TODO 更新部分小区的数据 目前只是新增
	 * operation record  
	 * 2014-11-27 20:39:18 local
	 * 	t_CommunitySupplyType_startId=3508~3508
			t_CommentsLabel_startId=3391~3391
			t_CommunitySupplyTypeHasTCommentLabel_startId=3583~3583
			t_CommunitySupply_startId=4661~4661
			t_CommunitySupplyContect_startId=4663~4663
			t_AddressBlockSelfDefined_startId=53006~53006
			t_GroupBuilding_startId=3200~4858
			t_GroupBuildingHasTCommunitySupply_startId=64847~64847
	 * 
	 * 2014-11-27 20:45:49 test
	 * 	t_CommunitySupplyType_startId=1502~1502
			t_CommentsLabel_startId=1385~1385
			t_CommunitySupplyTypeHasTCommentLabel_startId=1577~1577
			t_CommunitySupply_startId=2656~2656
			t_CommunitySupplyContect_startId=2657~2657
			t_AddressBlockSelfDefined_startId=2147~2147
			t_GroupBuilding_startId=3200~4858
			t_GroupBuildingHasTCommunitySupply_startId=68141~68141
	 * 
	 * @throws Exception
	 */
//	@Test
	public void main() throws Exception{
		BigInteger addressCityId = new BigInteger("233");//深圳市
		String srcDataJson = new String(FileUtils.fileToByte(srcFilePath));
		List<SZGuoTuGroupBuilding> groupBuildingJsonList =JSON.parseArray(srcDataJson, SZGuoTuGroupBuilding.class);
		if(groupBuildingJsonList!=null&&groupBuildingJsonList.size()>0){
			System.out.println("待处理json数据总数："+groupBuildingJsonList.size()+"个。");
			for(SZGuoTuGroupBuilding tmpData:groupBuildingJsonList){
				String groupBuildingAddressDesc = null;
				String groupBuildingName = tmpData.getName();
				String groupBuildingName_PinYin = PinyinUtil.hanyuToPinyinSimple(groupBuildingName);
				BigInteger selfDefineBlockId = null;
				//查询所属行政区
				String blockName = tmpData.getBlockName();
				if(!blockName.endsWith("区")){
					blockName = blockName+"区";
					if("前海区".equals(blockName)){//TODO 深圳市特殊处理
						blockName = "南山区";
					}
				}
				BigInteger blockId = getIdByAddressBlockName(blockName,addressCityId);
				if(blockId==null&&throwException){
					throw new RuntimeException("小区所属行政区为空，小区名称："+groupBuildingName+",行政区名称："+blockName);
				}
				//小区信息
				BigInteger groupBuildingId = getIdByGroupBuildingName(groupBuildingName,blockId);
				if(groupBuildingId==null){
					GroupBuilding tmpGroupBuilding = new GroupBuilding();
					tmpGroupBuilding.setAddressDesc(groupBuildingAddressDesc);
					tmpGroupBuilding.setCheckStatus(GroupBuildingDict.CheckStatus.WuXuShenHe);//无需审核
					tmpGroupBuilding.setCreater(null);
					tmpGroupBuilding.setIconUrl(null);
					tmpGroupBuilding.setLevel(null);
					tmpGroupBuilding.setName(groupBuildingName);
					tmpGroupBuilding.setPayPeriodEnd(null);
					tmpGroupBuilding.setPayPeriodStart(null);
					tmpGroupBuilding.setPicDescUrl(null);
					tmpGroupBuilding.setPinyinName(groupBuildingName_PinYin);
					tmpGroupBuilding.setSignStatus(0);
					tmpGroupBuilding.setSys0DelState(0);
					tmpGroupBuilding.settAddressBlockSelfDefinedFId(selfDefineBlockId);
					tmpGroupBuilding.settBlockFId(blockId);
					tmpGroupBuilding.setTel(null);
					tmpGroupBuilding.settPropertyManagementFId(null);//物业Id
					{//
						tmpGroupBuilding.setSourceType(CommonModuleDict.Data_SourceType.SZ_GuoTu_01);
						tmpGroupBuilding.setSzgt01Allowtime(tmpData.getAllowTime());
						tmpGroupBuilding.setSzgt01Enterprisename(tmpData.getEnterpriseName());
						tmpGroupBuilding.setSzgt01Presellno(tmpData.getPreSellNo());
						tmpGroupBuilding.setSzgt01Seqid(tmpData.getSeqId());
					}
					groupBuildingId = getGroupBuilding_Id();
					tmpGroupBuilding.setId(groupBuildingId);
					groupBuildingList.add(tmpGroupBuilding);
				}
			}
		}
		
		showResultData();
		if (insertBath) {
			insertDataBatch();
		}
		showLastStartId();
	}
	
}
