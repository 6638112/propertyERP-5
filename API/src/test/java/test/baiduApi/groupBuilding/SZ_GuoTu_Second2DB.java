/**   
* Filename:    SZ_GuoTu_Second2DB.java   
* @version:    1.0  
* Create at:   2014年11月27日 下午12:27:44   
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

import test.baiduApi.entity.SZGuoTuGroupBuilding02;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.room.constant.GroupBuildingDict;
import com.cnfantasia.server.common.utils.FileUtils;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

/**
 * Filename:    SZ_GuoTu_Second2DB.java
 * @version:    1.0.0
 * Create at:   2014年11月27日 下午12:27:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月27日       shiyl             1.0             1.0 Version
 */
public class SZ_GuoTu_Second2DB extends BaseGroupBuildingDB{
	public static final String srcFilePath = "F:\\addressData\\szGuoTu_Second.json";
	public static void main(String[] args) throws Exception {
		SZ_GuoTu_Second2DB aaa = new SZ_GuoTu_Second2DB();
		aaa.main();
	}
	/**
	 * TODO 更新部分小区的数据 目前只是新增
	 * 2014-11-27 20:53:40 local
	 * 	t_CommunitySupplyType_startId=4511~4511
			t_CommentsLabel_startId=4394~4394
			t_CommunitySupplyTypeHasTCommentLabel_startId=4586~4586
			t_CommunitySupply_startId=5664~5664
			t_CommunitySupplyContect_startId=5666~5666
			t_AddressBlockSelfDefined_startId=54009~54009
			t_GroupBuilding_startId=5000~7102
			t_GroupBuildingHasTCommunitySupply_startId=65850~65850
	 * 
	 * 2014-11-27 20:57:50 test
	 * 	t_CommunitySupplyType_startId=2503~2503
			t_CommentsLabel_startId=2386~2386
			t_CommunitySupplyTypeHasTCommentLabel_startId=2578~2578
			t_CommunitySupply_startId=3657~3657
			t_CommunitySupplyContect_startId=3658~3658
			t_AddressBlockSelfDefined_startId=3148~3148
			t_GroupBuilding_startId=5000~7102
			t_GroupBuildingHasTCommunitySupply_startId=69142~69142
	 * 
	 * @throws Exception
	 */
//	@Test
	public void main() throws Exception{
		BigInteger addressCityId = new BigInteger("233");//深圳市
		String srcDataJson = new String(FileUtils.fileToByte(srcFilePath));
		List<SZGuoTuGroupBuilding02> groupBuildingJsonList =JSON.parseArray(srcDataJson, SZGuoTuGroupBuilding02.class);
		if(groupBuildingJsonList!=null&&groupBuildingJsonList.size()>0){
			System.out.println("待处理json数据总数："+groupBuildingJsonList.size()+"个。");
			for(SZGuoTuGroupBuilding02 tmpData:groupBuildingJsonList){

				String groupBuildingAddressDesc = null;
				String groupBuildingName = tmpData.getPrjName();
				String groupBuildingName_PinYin = PinyinUtil.hanyuToPinyinSimple(groupBuildingName);
				BigInteger selfDefineBlockId = null;
				//查询所属行政区
				String blockName = tmpData.getBlockName();
				if(!blockName.endsWith("区")){
					blockName = blockName+"区";
//					if("前海区".equals(blockName)){//TODO 深圳市特殊处理
//						blockName = "南山区";
//					}
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
						tmpGroupBuilding.setSourceType(CommonModuleDict.Data_SourceType.SZ_GuoTu_02);
						tmpGroupBuilding.setSzgt02Agentname(tmpData.getAgentName());
						tmpGroupBuilding.setSzgt02Agenttel(tmpData.getAgentTel());
						tmpGroupBuilding.setSzgt02Area(tmpData.getArea());
						tmpGroupBuilding.setSzgt02Contractflowno(tmpData.getContractFlowNo());
						tmpGroupBuilding.setSzgt02Floor(tmpData.getFloor()==null?null:tmpData.getFloor().toString());
						tmpGroupBuilding.setSzgt02Publishtime(tmpData.getPublishTime());
						tmpGroupBuilding.setSzgt02Roomsrccode(tmpData.getRoomSrcCode());
						tmpGroupBuilding.setSzgt02Usedesc(tmpData.getUseDesc());
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
