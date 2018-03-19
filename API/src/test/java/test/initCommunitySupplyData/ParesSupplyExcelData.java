/**   
 * Filename:    ParesSupplyExcelData.java   
 * @version:    1.0  
 * Create at:   2014年9月16日 上午11:30:57   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年9月16日    shiyl      1.0         1.0 Version   
 */
package test.initCommunitySupplyData;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cnfantasia.server.api.room.constant.GroupBuildingDict;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.addressBlockSelfDefined.entity.AddressBlockSelfDefined;
import com.cnfantasia.server.domainbase.commentsLabel.entity.CommentsLabel;
import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentLabel.entity.CommunitySupplyTypeHasTCommentLabel;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.entity.GroupBuildingHasTCommunitySupply;

/**
 * Filename: ParesSupplyExcelData.java
 * 
 * @version: 1.0.0 Create at: 2014年9月16日 上午11:30:57 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年9月16日 shiyl 1.0 1.0 Version
 */
public class ParesSupplyExcelData extends BaseSupplyDataManagerOld{
	public static final BigInteger ZhaoFuWu_ParentId = new BigInteger("100");
	public static final BigInteger ZhaoShiHui_ParentId = new BigInteger("101");
	public static void main(String[] args) throws Exception {
		ParesSupplyExcelData paresSupplyExcelData = new ParesSupplyExcelData();
		paresSupplyExcelData.main();
	}
	/**
	 * TODO 
	 * 设定Id起始值,注意comm_uuid表的 起始uuid修改
	 * 修改数据库连接为本地
	 * 设定异常开启
	 * 设定打开数据库
	 * 关闭insertDataBatch,测试效果
	 * 成功后，打开insertDataBatch
	 * 从本地数据库的表中逐个导出数据，存入excel,导出数数据量大的情况下，加limit 0,100000
	 * excel中编辑头和尾
	 * 得到sql后，在测试环境执行校验
	 * 生产环境中执行sql
	 * 
	 * 录入商家图片
	 * 录入类别图片
	 */
//	@Test
	public  void main() throws Exception {
//		InputStream fileInputStream = new FileInputStream("src/test/java/test/initCommunitySupplyData/test02.xls");
//		InputStream fileInputStream = new FileInputStream("D:/ProgramData/svnspace/pulicSvn/product/dev/解放区/06 运营相关/管家模块/【管家模块】-商家数据录入_2014-9-15.xls");
//		InputStream fileInputStream = new FileInputStream("D:/ProgramData/svnspace/pulicSvn/product/dev/解放区/06 运营相关/管家模块/【管家模块】-商家数据录入_2014-10-29.xls");
//		InputStream fileInputStream = new FileInputStream("D:/ProgramData/svnspace/pulicSvn/product/dev/解放区/06 运营相关/管家模块/【管家模块】-商家数据录入_2014-11-04-成都.xls");
//		InputStream fileInputStream = new FileInputStream("D:/ProgramData/svnspace/pulicSvn/product/dev/解放区/06 运营相关/管家模块/【管家模块】-商家数据录入_2014-11-04-成都-补充.xls");
		InputStream fileInputStream = new FileInputStream("D:/ProgramData/svnspace/pulicSvn/product/dev/解放区/06 运营相关/管家模块/【管家模块】-商家数据录入_2014-11-17-试点小区数据补充.xls");
		XSSFWorkbook srcWorkBook = new XSSFWorkbook(fileInputStream);
		
		{
			XSSFSheet sheet = srcWorkBook.getSheetAt(0);
//			int rowStart = sheet.getFirstRowNum();
			int rowEnd = sheet.getLastRowNum();
			for (int j = 1; j < rowEnd; j++) {
				XSSFRow row = sheet.getRow(j);
				if (StringUtils.isEmpty(cellStr(row, 0))) {
					continue;
				}
//				int firstCellNum = row.getFirstCellNum();
				int lastCellNum = row.getLastCellNum();
				String suoshuMokuai = cellStr(row, 0);
				String typeName = cellStr(row, 1);
				String pinyinName = PinyinUtil.hanyuToPinyinSimple(typeName);
				Integer importanceLevel = Integer.valueOf(cellStr(row, 2));
				List<String> labelStrList = new ArrayList<String>();
				for (int m = 3; m < lastCellNum; m++) {
					if (!StringUtils.isEmpty(cellStr(row, m))) {
						labelStrList.add(cellStr(row, m));
					}
				}
				
				System.out.println(suoshuMokuai + "==" + typeName + "==" + labelStrList);
				//商家类别
				BigInteger supplyTypeId = getIdByCommunitySupplyTypeName(typeName);
				if(supplyTypeId==null){
					CommunitySupplyType tmpCommunitySupplyType = new CommunitySupplyType();
					if("1".equals(suoshuMokuai)){
						tmpCommunitySupplyType.setParentId(ZhaoShiHui_ParentId);
					}else if("2".equals(suoshuMokuai)){
						tmpCommunitySupplyType.setParentId(ZhaoFuWu_ParentId);
					}
					tmpCommunitySupplyType.setName(typeName);
					tmpCommunitySupplyType.setPinyinName(pinyinName);
					tmpCommunitySupplyType.setImportanceLevel(importanceLevel);
					tmpCommunitySupplyType.setSys0DelState(0);
					if(importanceLevel==1){
						tmpCommunitySupplyType.setIconBigUrl(pinyinName+".jpg");
					}else if(importanceLevel==2){
						tmpCommunitySupplyType.setIconSmallUrl(pinyinName+".jpg");
					}
					supplyTypeId = getCommunitySupplyType_Id();
					tmpCommunitySupplyType.setId(supplyTypeId);
					communitySupplyTypeList.add(tmpCommunitySupplyType);
				}
				for(String labelName:labelStrList){
					BigInteger commentsLabelId = getIdByCommentsLabelName(labelName);
					if(commentsLabelId==null){//评论标签
						CommentsLabel tmpCommentsLabel = new CommentsLabel();
						tmpCommentsLabel.setName(labelName);
						tmpCommentsLabel.setSys0DelState(0);
						commentsLabelId = getCommentsLabel_Id();
						tmpCommentsLabel.setId(commentsLabelId);
						commentsLabelList.add(tmpCommentsLabel);
					}
					BigInteger communitySupplyTypeHasTCommentLabelId = getIdByCommunitySupplyTypeAndCommentsLabel(supplyTypeId, commentsLabelId);
					if(communitySupplyTypeHasTCommentLabelId==null){//评论标签关系
						CommunitySupplyTypeHasTCommentLabel tmpCommunitySupplyTypeHasTCommentLabel = new CommunitySupplyTypeHasTCommentLabel();
						tmpCommunitySupplyTypeHasTCommentLabel.setSys0DelState(0);
						tmpCommunitySupplyTypeHasTCommentLabel.settCommentsLabelFId(commentsLabelId);
						tmpCommunitySupplyTypeHasTCommentLabel.settCommunitySupplyTypeFId(supplyTypeId);
						communitySupplyTypeHasTCommentLabelId = getCommunitySupplyTypeHasTCommentLabel_Id();
						tmpCommunitySupplyTypeHasTCommentLabel.setId(communitySupplyTypeHasTCommentLabelId);
						communitySupplyTypeHasTCommentLabelList.add(tmpCommunitySupplyTypeHasTCommentLabel);
					}
				}
			}
		}
		
		{
			XSSFSheet sheet = srcWorkBook.getSheetAt(1);
//			int rowStart = sheet.getFirstRowNum();
			int rowEnd = sheet.getLastRowNum();
			for (int j = 1; j < rowEnd; j++) {
				XSSFRow row = sheet.getRow(j);
				if (StringUtils.isEmpty(cellStr(row, 0))) {
					continue;
				}
//				int firstCellNum = row.getFirstCellNum();
//				int lastCellNum = row.getLastCellNum();
//				String addressBlockName = cellStr(row, 0);
//				String selfDefineBlockName = cellStr(row, 1);
				String supplyTypeName = cellStr(row, 2);
				String supplyName = cellStr(row, 3);
				String pinyinName = PinyinUtil.hanyuToPinyinSimple(supplyName);
				String contectUserName = cellStr(row, 4);
				List<String> phoList = new ArrayList<String>();
				{
					String contectUserPhone = cellStr(row, 5);
					if(contectUserPhone!=null){
						String[] pho = contectUserPhone.split("\\/");
						for(int aaIndex=0;aaIndex<pho.length;aaIndex++){
							phoList.add(pho[aaIndex].trim());
						}
					}
				}
				String supplyAddress = cellStr(row, 6);
				String supplyServiceDesc = cellStr(row, 7);
				String supplyInfoDesc = cellStr(row, 8);
				Long supplyAverageCost = null;
				if(!StringUtils.isEmpty(cellStr(row, 9))){
					if(cellStr(row, 9).trim().length()>0){
						supplyAverageCost = Long.valueOf(cellStr(row, 9));
					}
				}
				String supplyServerTime = cellStr(row, 10);
				System.out.println(/*addressBlockName + "=="+selfDefineBlockName + "=="+*/supplyTypeName + "=="+supplyName + "=="+contectUserName + "=="+phoList 
						+ "=="+supplyAddress + "=="+supplyServiceDesc + "=="+supplyInfoDesc + "=="+supplyAverageCost + "=="+supplyServerTime + "==");
				//商家信息
				BigInteger communitySupplyId = getIdByCommunitySupplyName(supplyName);
				if(communitySupplyId==null){
					//查询所属类别
					BigInteger supplyTypeId = getIdByCommunitySupplyTypeName(supplyTypeName);
					if(supplyTypeId==null&&throwException){
						throw new RuntimeException("商家所属类别为空，商家名称："+supplyName+",类别名称："+supplyTypeName); 
					}
					CommunitySupply tmpCommunitySupply = new CommunitySupply();
					tmpCommunitySupply.setAddressDetail(supplyAddress);
					if(supplyAverageCost!=null){
						tmpCommunitySupply.setAvgConsume(supplyAverageCost*100);//人均消费乘以100
					}
					tmpCommunitySupply.setDesc(supplyServiceDesc+"。"+supplyInfoDesc==null?"":supplyInfoDesc);
					tmpCommunitySupply.setName(supplyName);
					tmpCommunitySupply.setOpenHoursDesc(supplyServerTime);
					tmpCommunitySupply.setPinyinName(pinyinName);
					tmpCommunitySupply.setSys0DelState(0);
					tmpCommunitySupply.settCommunitySupplyTypeFId(supplyTypeId);
					communitySupplyId = getCommunitySupply_Id();
					tmpCommunitySupply.setId(communitySupplyId);
					communitySupplyList.add(tmpCommunitySupply);
				}
				//TODO 商家图片
				//商家联系方式
				if(phoList!=null&&phoList.size()>0){
					for(String contectInfo:phoList){
						//查询是否已存在
						BigInteger existRelaId = getIdByCommunitySupplyAndCommunityContect(communitySupplyId, contectInfo);
						if(existRelaId==null){
							CommunitySupplyContect tmpCommunitySupplyContect = new CommunitySupplyContect();
							tmpCommunitySupplyContect.setClickCount(0L);
							tmpCommunitySupplyContect.setContectInfo(contectInfo);
							tmpCommunitySupplyContect.setEsqName(contectUserName);
							tmpCommunitySupplyContect.setSys0DelState(0);
							tmpCommunitySupplyContect.settCommunitySupplyFId(communitySupplyId);
							tmpCommunitySupplyContect.setUserIdentity("商家");
							existRelaId = getCommunitySupplyContect_Id();
							tmpCommunitySupplyContect.setId(existRelaId);
							tmpCommunitySupplyContect.setType(Integer.valueOf("1"));
							communitySupplyContectList.add(tmpCommunitySupplyContect);
						}
					}
				}
			}
		}
		
		{
			XSSFSheet sheet = srcWorkBook.getSheetAt(2);
//			int rowStart = sheet.getFirstRowNum();
			int rowEnd = sheet.getLastRowNum();
			for (int j = 1; j < rowEnd; j++) {
				XSSFRow row = sheet.getRow(j);
				if (StringUtils.isEmpty(cellStr(row, 0))) {
					continue;
				}
//				int firstCellNum = row.getFirstCellNum();
//				int lastCellNum = row.getLastCellNum();
				String cityName = cellStr(row, 0);
				String blockName = cellStr(row, 1);
				String selfDefinBlockName = cellStr(row, 2);
				String groupBuildingName = cellStr(row, 3);
				String groupBuildingName_PinYin = PinyinUtil.hanyuToPinyinSimple(groupBuildingName);
				String groupBuildingAddressDesc = cellStr(row, 4);
				String gbUserCount = cellStr(row, 5);
				String propertyCompanyName = cellStr(row, 6);
				List<String> supplyNameList = new ArrayList<String>();
				{
					String supplyNameStr = cellStr(row,7);
					if(supplyNameStr!=null){
						String[] tmpArr = supplyNameStr.split("\n");
						for(String tmpSupplyName:tmpArr){
							supplyNameList.add(tmpSupplyName.trim());
						}
					}
				}
				System.out.println(cityName+"=="+blockName + "==" + selfDefinBlockName + "==" + groupBuildingName + "=="
						+ groupBuildingAddressDesc + "==" + gbUserCount + "=="
						+ propertyCompanyName + "==" + supplyNameList);
				
				//片区信息
				BigInteger selfDefineBlockId = null;
				if(!StringUtils.isEmpty(selfDefinBlockName)){
					selfDefineBlockId = getIdByAddressBlockSelfDefinedName(selfDefinBlockName);
					if(selfDefineBlockId==null){
						//查询所属行政市
						BigInteger addressCityId = getIdByAddressCityName(cityName);
						if(addressCityId==null&&throwException){
							throw new RuntimeException("片区所属行政市为空，片区名称："+selfDefinBlockName+",行政市名称："+cityName);
						}
						AddressBlockSelfDefined tmpAddressBlockSelfDefined = new AddressBlockSelfDefined();
						tmpAddressBlockSelfDefined.setName(selfDefinBlockName);
						tmpAddressBlockSelfDefined.setSys0DelState(0);
						tmpAddressBlockSelfDefined.settAddressCityFId(addressCityId);
						selfDefineBlockId = getAddressBlockSelfDefined_Id();
						tmpAddressBlockSelfDefined.setId(selfDefineBlockId);
						addressBlockSelfDefinedList.add(tmpAddressBlockSelfDefined);
					}
				}
				//小区信息
				BigInteger groupBuildingId = getIdByGroupBuildingName(groupBuildingName);
				if(groupBuildingId==null){
					//查询所属行政区
					BigInteger blockId = getIdByAddressBlockName(blockName);
					if(blockId==null&&throwException){
						throw new RuntimeException("小区所属行政区为空，小区名称："+groupBuildingName+",行政区名称："+blockName);
					}
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
					groupBuildingId = getGroupBuilding_Id();
					tmpGroupBuilding.setId(groupBuildingId);
					groupBuildingList.add(tmpGroupBuilding);
				}
				//商家小区关系信息
				for(String supplyName:supplyNameList){
					if(!StringUtils.isEmpty(supplyName)){
						BigInteger communitySupplyId = getIdByCommunitySupplyName(supplyName);
						if(communitySupplyId==null&&throwException){
							System.out.println("商家信息为空，商家名称："+supplyName);
							continue;
//							throw new RuntimeException("商家信息为空，商家名称："+supplyName);
						}
						//校验关系信息是否存在
						BigInteger existId = getIdByGroupBuildingAndCommunitySupply(groupBuildingId, communitySupplyId);
						if(existId==null){
							existId = getGroupBuildingHasTCommunitySupply_Id();
							GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupply = new GroupBuildingHasTCommunitySupply();
							groupBuildingHasTCommunitySupply.setSys0DelState(0);
							groupBuildingHasTCommunitySupply.settCommunitySupplyFId(communitySupplyId);
							groupBuildingHasTCommunitySupply.settGroupBuildingFId(groupBuildingId);
							groupBuildingHasTCommunitySupply.setId(existId);
							groupBuildingHasTCommunitySupplyList.add(groupBuildingHasTCommunitySupply);
						}
					}
				}
			}
		}
		showResultData();
		if(insertBath){
			insertDataBatch();
		}
		showLastStartId();
	}
	
}
