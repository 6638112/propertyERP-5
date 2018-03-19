/**   
* Filename:    BaseSupplyDataManager.java   
* @version:    1.0  
* Create at:   2014年9月17日 上午3:08:36   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月17日    shiyl      1.0         1.0 Version   
*/
package test.initCommunitySupplyData;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
import com.cnfantasia.server.domainbase.addressBlock.service.IAddressBlockBaseService;
import com.cnfantasia.server.domainbase.addressBlockSelfDefined.entity.AddressBlockSelfDefined;
import com.cnfantasia.server.domainbase.addressBlockSelfDefined.service.IAddressBlockSelfDefinedBaseService;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressCity.service.IAddressCityBaseService;
import com.cnfantasia.server.domainbase.commentsLabel.entity.CommentsLabel;
import com.cnfantasia.server.domainbase.commentsLabel.service.ICommentsLabelBaseService;
import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupply.service.ICommunitySupplyBaseService;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.communitySupplyContect.service.ICommunitySupplyContectBaseService;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.communitySupplyType.service.ICommunitySupplyTypeBaseService;
import com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentLabel.entity.CommunitySupplyTypeHasTCommentLabel;
import com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentLabel.service.ICommunitySupplyTypeHasTCommentLabelBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.entity.GroupBuildingHasTCommunitySupply;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.service.IGroupBuildingHasTCommunitySupplyBaseService;

/**
 * Filename:    BaseSupplyDataManager.java
 * @version:    1.0.0
 * Create at:   2014年9月17日 上午3:08:36
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月17日       shiyl             1.0             1.0 Version
 */
public class BaseSupplyDataManager  extends BaseTest{
	/**是否连接数据库获取数据*/
	public static final boolean connect2Db = true;
	/**是否校验数据*/
	public static final boolean throwException = true;
	/**是否插入数据库*/
	public static final boolean insertBath = true;
	
	protected ICommunitySupplyTypeBaseService  communitySupplyTypeBaseService ;
	protected ICommentsLabelBaseService commentsLabelBaseService ;
	protected ICommunitySupplyTypeHasTCommentLabelBaseService communitySupplyTypeHasTCommentLabelBaseService;
	protected ICommunitySupplyBaseService communitySupplyBaseService;
	protected ICommunitySupplyContectBaseService communitySupplyContectBaseService;
	protected IAddressCityBaseService addressCityBaseService;
	protected IAddressBlockBaseService addressBlockBaseService;
	protected IAddressBlockSelfDefinedBaseService addressBlockSelfDefinedBaseService;
	protected IGroupBuildingBaseService groupBuildingBaseService;
	protected IGroupBuildingHasTCommunitySupplyBaseService groupBuildingHasTCommunitySupplyBaseService;
	protected IUuidManager uuidManager;
	public void resData2Restart(){
		communitySupplyTypeList_db.addAll(communitySupplyTypeList);communitySupplyTypeList.clear();
		commentsLabelList_db.addAll(commentsLabelList);commentsLabelList.clear();
		communitySupplyTypeHasTCommentLabelList_db.addAll(communitySupplyTypeHasTCommentLabelList);communitySupplyTypeHasTCommentLabelList.clear();
		communitySupplyList_db.addAll(communitySupplyList);communitySupplyList.clear();
		communitySupplyContectList_db.addAll(communitySupplyContectList);communitySupplyContectList.clear();
		addressCityList_db.addAll(addressCityList);addressCityList.clear();
		addressBlockList_db.addAll(addressBlockList);addressBlockList.clear();
		addressBlockSelfDefinedList_db.addAll(addressBlockSelfDefinedList);addressBlockSelfDefinedList.clear();
		groupBuildingList_db.addAll(groupBuildingList);groupBuildingList.clear();
		groupBuildingHasTCommunitySupplyList_db.addAll(groupBuildingHasTCommunitySupplyList);groupBuildingHasTCommunitySupplyList.clear();
		
	 t_CommunitySupplyType_initId=t_CommunitySupplyType_startId;
	 t_CommentsLabel_initId = t_CommentsLabel_startId;
	 t_CommunitySupplyTypeHasTCommentLabel_initId = t_CommunitySupplyTypeHasTCommentLabel_startId;
	 t_CommunitySupply_initId = t_CommunitySupply_startId;
	 t_CommunitySupplyContect_initId = t_CommunitySupplyContect_startId;
	 t_AddressBlockSelfDefined_initId = t_AddressBlockSelfDefined_startId;
	 t_GroupBuilding_initId = t_GroupBuilding_startId;
	 t_GroupBuildingHasTCommunitySupply_initId = t_GroupBuildingHasTCommunitySupply_startId;
	}
	//类别数据
	protected List<CommunitySupplyType> communitySupplyTypeList_db;
	//评论标签数据
	protected List<CommentsLabel> commentsLabelList_db;
	//类别评论标签关系
	protected List<CommunitySupplyTypeHasTCommentLabel> communitySupplyTypeHasTCommentLabelList_db;
	//商家信息
	protected List<CommunitySupply> communitySupplyList_db;
	//商家联系方式
	protected List<CommunitySupplyContect> communitySupplyContectList_db;
	//行政市
	protected List<AddressCity> addressCityList_db;
	//行政区
	protected List<AddressBlock> addressBlockList_db;
	//片区信息
	protected List<AddressBlockSelfDefined> addressBlockSelfDefinedList_db;
	//小区信息
	protected List<GroupBuilding> groupBuildingList_db;
	//小区商家关系
	protected List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList_db;
	
	
	//类别数据
	protected List<CommunitySupplyType> communitySupplyTypeList = new ArrayList<CommunitySupplyType>();
	//评论标签数据
	protected List<CommentsLabel> commentsLabelList = new ArrayList<CommentsLabel>();
	//类别评论标签关系
	protected List<CommunitySupplyTypeHasTCommentLabel> communitySupplyTypeHasTCommentLabelList = new ArrayList<CommunitySupplyTypeHasTCommentLabel>();
	//商家信息
	protected List<CommunitySupply> communitySupplyList = new ArrayList<CommunitySupply>();
	//商家联系方式
	protected List<CommunitySupplyContect> communitySupplyContectList = new ArrayList<CommunitySupplyContect>();
	//行政市
	protected List<AddressCity> addressCityList = new ArrayList<AddressCity>();
	//行政区
	protected List<AddressBlock> addressBlockList = new ArrayList<AddressBlock>();
	//片区信息
	protected List<AddressBlockSelfDefined> addressBlockSelfDefinedList = new ArrayList<AddressBlockSelfDefined>();
	//小区信息
	protected List<GroupBuilding> groupBuildingList = new ArrayList<GroupBuilding>();
	//小区商家关系
	protected List<GroupBuildingHasTCommunitySupply> groupBuildingHasTCommunitySupplyList = new ArrayList<GroupBuildingHasTCommunitySupply>();
	
//TODO 以下的下次需要+1
	public BigInteger t_CommunitySupplyType_initId = null;
	public BigInteger t_CommunitySupplyType_startId = null;
	//501; 1000-1000 
	public BigInteger getCommunitySupplyType_Id(){
		t_CommunitySupplyType_startId=t_CommunitySupplyType_startId.add(new BigInteger("1"));
		return t_CommunitySupplyType_startId;
	}
	
	public BigInteger t_CommentsLabel_initId = null;
	public BigInteger t_CommentsLabel_startId = null;
	//500; 1000- 
	public BigInteger getCommentsLabel_Id(){
		t_CommentsLabel_startId=t_CommentsLabel_startId.add(new BigInteger("1"));
		return t_CommentsLabel_startId;
	}
	
	public BigInteger t_CommunitySupplyTypeHasTCommentLabel_initId = null;
	public BigInteger t_CommunitySupplyTypeHasTCommentLabel_startId = null;
	//558; 1000-1000;
	public BigInteger getCommunitySupplyTypeHasTCommentLabel_Id(){
		t_CommunitySupplyTypeHasTCommentLabel_startId=t_CommunitySupplyTypeHasTCommentLabel_startId.add(new BigInteger("1"));
		return t_CommunitySupplyTypeHasTCommentLabel_startId;
	}
	
	public BigInteger t_CommunitySupply_initId = null;
	public BigInteger t_CommunitySupply_startId = null;
	//599; 1000-1268;1300-1421,1450-1568,1580-1588,1590-1655,5062
	public BigInteger getCommunitySupply_Id(){
		t_CommunitySupply_startId=t_CommunitySupply_startId.add(new BigInteger("1"));
		return t_CommunitySupply_startId;
	}
	
	public BigInteger t_CommunitySupplyContect_initId = null;
	public BigInteger t_CommunitySupplyContect_startId = null;
	//=604; 1000-1268;1300-1421,1450-1568,1580-1588,1590-1656,4076
	public BigInteger getCommunitySupplyContect_Id(){
		t_CommunitySupplyContect_startId=t_CommunitySupplyContect_startId.add(new BigInteger("1"));
		return t_CommunitySupplyContect_startId;
	}
	
	public BigInteger t_AddressBlockSelfDefined_initId = null;
	public BigInteger t_AddressBlockSelfDefined_startId = null;
	//515; 1000-1007;1100-1101,1105-1146,
	public BigInteger getAddressBlockSelfDefined_Id(){
		t_AddressBlockSelfDefined_startId=t_AddressBlockSelfDefined_startId.add(new BigInteger("1"));
		return t_AddressBlockSelfDefined_startId;
	}
	
	public BigInteger t_GroupBuilding_initId = null;
	public BigInteger t_GroupBuilding_startId = null;
	//2537; 2600-2605;2620-2626,2650-3055,3100-3101
	public BigInteger getGroupBuilding_Id(){
		t_GroupBuilding_startId=t_GroupBuilding_startId.add(new BigInteger("1"));
		return t_GroupBuilding_startId;
	}
	
	public BigInteger t_GroupBuildingHasTCommunitySupply_initId = null;
	public BigInteger t_GroupBuildingHasTCommunitySupply_startId = null;
	//3712; 5665 6665;6700-7576,7600-62656,66252,67000-67140,74601
	//72711
	public BigInteger getGroupBuildingHasTCommunitySupply_Id(){
		t_GroupBuildingHasTCommunitySupply_startId=t_GroupBuildingHasTCommunitySupply_startId.add(new BigInteger("1"));
		return t_GroupBuildingHasTCommunitySupply_startId;
	}
	
	@Override
	public void init() {
		super.init();
		communitySupplyTypeBaseService = ctx.getBean("communitySupplyTypeBaseService", ICommunitySupplyTypeBaseService.class);
		commentsLabelBaseService = ctx.getBean("commentsLabelBaseService", ICommentsLabelBaseService.class);
		communitySupplyTypeHasTCommentLabelBaseService= ctx.getBean("communitySupplyTypeHasTCommentLabelBaseService", ICommunitySupplyTypeHasTCommentLabelBaseService.class);
		communitySupplyBaseService= ctx.getBean("communitySupplyBaseService", ICommunitySupplyBaseService.class);
		communitySupplyContectBaseService = ctx.getBean("communitySupplyContectBaseService", ICommunitySupplyContectBaseService.class);
		addressCityBaseService = ctx.getBean("addressCityBaseService", IAddressCityBaseService.class);
		addressBlockBaseService =ctx.getBean("addressBlockBaseService", IAddressBlockBaseService.class);
		addressBlockSelfDefinedBaseService =ctx.getBean("addressBlockSelfDefinedBaseService", IAddressBlockSelfDefinedBaseService.class);
		groupBuildingBaseService = ctx.getBean("groupBuildingBaseService", IGroupBuildingBaseService.class);
		groupBuildingHasTCommunitySupplyBaseService= ctx.getBean("groupBuildingHasTCommunitySupplyBaseService", IGroupBuildingHasTCommunitySupplyBaseService.class);
		uuidManager= ctx.getBean("uuidManager", IUuidManager.class);
		if(connect2Db){//初始加载数据
			//类别数据
			communitySupplyTypeList_db = communitySupplyTypeBaseService.getCommunitySupplyTypeByCondition(null);
			//评论标签数据
			commentsLabelList_db = commentsLabelBaseService.getCommentsLabelByCondition(null);
			//类别评论标签关系
			communitySupplyTypeHasTCommentLabelList_db = communitySupplyTypeHasTCommentLabelBaseService.getCommunitySupplyTypeHasTCommentLabelByCondition(null);
			//商家信息
			communitySupplyList_db = communitySupplyBaseService.getCommunitySupplyByCondition(null);
			//商家联系方式
			communitySupplyContectList_db = communitySupplyContectBaseService.getCommunitySupplyContectByCondition(null);
			//行政市
			addressCityList_db = addressCityBaseService.getAddressCityByCondition(null);
			//行政区
			addressBlockList_db = addressBlockBaseService.getAddressBlockByCondition(null);
			//片区信息
			addressBlockSelfDefinedList_db = addressBlockSelfDefinedBaseService.getAddressBlockSelfDefinedByCondition(null);
			//小区信息
			groupBuildingList_db = groupBuildingBaseService.getGroupBuildingByCondition(null);
			//小区商家关系
			groupBuildingHasTCommunitySupplyList_db = groupBuildingHasTCommunitySupplyBaseService.getGroupBuildingHasTCommunitySupplyByCondition(null);
		}
		{
			initUuid();
			t_CommunitySupplyType_startId = t_CommunitySupplyType_initId;
			t_CommentsLabel_startId = t_CommentsLabel_initId;
			t_CommunitySupplyTypeHasTCommentLabel_startId = t_CommunitySupplyTypeHasTCommentLabel_initId;
			t_CommunitySupply_startId = t_CommunitySupply_initId;
			t_CommunitySupplyContect_startId = t_CommunitySupplyContect_initId;
			t_AddressBlockSelfDefined_startId = t_AddressBlockSelfDefined_initId;
			t_GroupBuilding_startId = t_GroupBuilding_initId;
			t_GroupBuildingHasTCommunitySupply_startId = t_GroupBuildingHasTCommunitySupply_initId;
		}
	}
	public void initUuid(){
		if(connect2Db){
			//初始化startId
			t_CommunitySupplyType_initId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_type).add(new BigInteger("1000"));
			
			t_CommentsLabel_initId = uuidManager.getNextUuidBigInteger(SEQConstants.t_comments_label).add(new BigInteger("1000"));
			
			t_CommunitySupplyTypeHasTCommentLabel_initId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_type_has_t_comment_label).add(new BigInteger("1000"));
			
//			t_CommunitySupply_initId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply).add(new BigInteger("1000"));
			t_CommunitySupply_initId = new BigInteger("19316");//1700,19315,54558
			
//			t_CommunitySupplyContect_initId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_contect).add(new BigInteger("1000"));
			t_CommunitySupplyContect_initId = new BigInteger("17239");//1700,17238,48415
			
			t_AddressBlockSelfDefined_initId = uuidManager.getNextUuidBigInteger(SEQConstants.t_address_block_self_defined).add(new BigInteger("1000"));
			
			t_GroupBuilding_initId = uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building).add(new BigInteger("1000"));
			t_GroupBuilding_initId = new BigInteger("7200");//3200-4858,5000~7102,7200,,
			
//			t_GroupBuildingHasTCommunitySupply_initId = uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building_has_t_community_supply).add(new BigInteger("1000"));
			t_GroupBuildingHasTCommunitySupply_initId = new BigInteger("85616");//85615,120858
		}else{
			t_CommunitySupplyType_startId = new BigInteger("1000000");
			t_CommentsLabel_initId = new BigInteger("1000000");
			t_CommunitySupplyTypeHasTCommentLabel_initId = new BigInteger("1000000");
			t_CommunitySupply_initId = new BigInteger("1000000");
			t_CommunitySupplyContect_initId = new BigInteger("1000000");
			t_AddressBlockSelfDefined_initId = new BigInteger("1000000");
			t_GroupBuilding_initId = new BigInteger("1000000");
			t_GroupBuildingHasTCommunitySupply_initId = new BigInteger("1000000");
		}
	}
	private void freshUuidTable(){
		freshUuidTable(t_CommunitySupplyType_startId, SEQConstants.t_community_supply_type);
		freshUuidTable(t_CommentsLabel_startId, SEQConstants.t_comments_label);
		freshUuidTable(t_CommunitySupplyTypeHasTCommentLabel_startId, SEQConstants.t_community_supply_type_has_t_comment_label);
		freshUuidTable(t_CommunitySupply_startId, SEQConstants.t_community_supply);
		freshUuidTable(t_CommunitySupplyContect_startId, SEQConstants.t_community_supply_contect);
		freshUuidTable(t_AddressBlockSelfDefined_startId, SEQConstants.t_address_block_self_defined);
		freshUuidTable(t_GroupBuilding_startId, SEQConstants.t_group_building);
		freshUuidTable(t_GroupBuildingHasTCommunitySupply_startId, SEQConstants.t_group_building_has_t_community_supply);
	}
	private void freshUuidTable(BigInteger currStartId,String tableName){
		BigInteger nowDBId = uuidManager.getNextUuidBigInteger(tableName);
		BigInteger length = currStartId.subtract(nowDBId);
		if(length.compareTo(new BigInteger("0"))>0){
			uuidManager.getNextUuidBigInteger(tableName,length.intValue());
		}
	}
	
	public void showLastStartId() {
		System.out.println("t_CommunitySupplyType_startId="+t_CommunitySupplyType_initId+"~"+t_CommunitySupplyType_startId);
		System.out.println("t_CommentsLabel_startId="+t_CommentsLabel_initId+"~"+t_CommentsLabel_startId);
		System.out.println("t_CommunitySupplyTypeHasTCommentLabel_startId="+t_CommunitySupplyTypeHasTCommentLabel_initId+"~"+t_CommunitySupplyTypeHasTCommentLabel_startId);
		System.out.println("t_CommunitySupply_startId="+t_CommunitySupply_initId+"~"+t_CommunitySupply_startId);
		System.out.println("t_CommunitySupplyContect_startId="+t_CommunitySupplyContect_initId+"~"+t_CommunitySupplyContect_startId);
		System.out.println("t_AddressBlockSelfDefined_startId="+t_AddressBlockSelfDefined_initId+"~"+t_AddressBlockSelfDefined_startId);
		System.out.println("t_GroupBuilding_startId="+t_GroupBuilding_initId+"~"+t_GroupBuilding_startId);
		System.out.println("t_GroupBuildingHasTCommunitySupply_startId="+t_GroupBuildingHasTCommunitySupply_initId+"~"+t_GroupBuildingHasTCommunitySupply_startId);
	}
	
	
	
	public void insertDataBatch(){
		if(communitySupplyTypeList.size()>0){
//			communitySupplyTypeBaseService.insertCommunitySupplyTypeBatch(communitySupplyTypeList);
			List<CommunitySupplyType> tmpList = new ArrayList<CommunitySupplyType>();
			for(int i=0;i<communitySupplyTypeList.size();i++){
				tmpList.add(communitySupplyTypeList.get(i));
				if(i!=0&&i%1000==0){
					if(tmpList.size()>0){
						communitySupplyTypeBaseService.insertCommunitySupplyTypeBatch(tmpList);
					}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){
				communitySupplyTypeBaseService.insertCommunitySupplyTypeBatch(tmpList);
			}
		}
		if(commentsLabelList.size()>0){
//			commentsLabelBaseService.insertCommentsLabelBatch(commentsLabelList);
			List<CommentsLabel> tmpList = new ArrayList<CommentsLabel>();
			for(int i=0;i<commentsLabelList.size();i++){
				tmpList.add(commentsLabelList.get(i));
				if(i!=0&&i%1000==0){
					if(tmpList.size()>0){
						commentsLabelBaseService.insertCommentsLabelBatch(tmpList);
					}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){
				commentsLabelBaseService.insertCommentsLabelBatch(tmpList);
			}
		}
		if(communitySupplyTypeHasTCommentLabelList.size()>0){
//			communitySupplyTypeHasTCommentLabelBaseService.insertCommunitySupplyTypeHasTCommentLabelBatch(communitySupplyTypeHasTCommentLabelList);
			List<CommunitySupplyTypeHasTCommentLabel> tmpList = new ArrayList<CommunitySupplyTypeHasTCommentLabel>();
			for(int i=0;i<communitySupplyTypeHasTCommentLabelList.size();i++){
				tmpList.add(communitySupplyTypeHasTCommentLabelList.get(i));
				if(i!=0&&i%1000==0){
					if(tmpList.size()>0){
						communitySupplyTypeHasTCommentLabelBaseService.insertCommunitySupplyTypeHasTCommentLabelBatch(tmpList);
					}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){
				communitySupplyTypeHasTCommentLabelBaseService.insertCommunitySupplyTypeHasTCommentLabelBatch(tmpList);
			}
		}
		if(communitySupplyList.size()>0){
//			communitySupplyBaseService.insertCommunitySupplyBatch(communitySupplyList);
			List<CommunitySupply> tmpList = new ArrayList<CommunitySupply>();
			for(int i=0;i<communitySupplyList.size();i++){
				tmpList.add(communitySupplyList.get(i));
				if(i!=0&&i%1000==0){
					if(tmpList.size()>0){
						communitySupplyBaseService.insertCommunitySupplyBatch(tmpList);
					}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){
				communitySupplyBaseService.insertCommunitySupplyBatch(tmpList);
			}
		}
		if(communitySupplyContectList.size()>0){
//			communitySupplyContectBaseService.insertCommunitySupplyContectBatch(communitySupplyContectList);
			List<CommunitySupplyContect> tmpList = new ArrayList<CommunitySupplyContect>();
			for(int i=0;i<communitySupplyContectList.size();i++){
				tmpList.add(communitySupplyContectList.get(i));
				if(i!=0&&i%1000==0){
					if(tmpList.size()>0){
						communitySupplyContectBaseService.insertCommunitySupplyContectBatch(tmpList);
					}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){
				communitySupplyContectBaseService.insertCommunitySupplyContectBatch(tmpList);
			}
		}
		if(addressCityList.size()>0){
//			addressCityBaseService.insertAddressCityBatch(addressCityList);
			List<AddressCity> tmpList = new ArrayList<AddressCity>();
			for(int i=0;i<addressCityList.size();i++){
				tmpList.add(addressCityList.get(i));
				if(i!=0&&i%1000==0){
					if(tmpList.size()>0){
						addressCityBaseService.insertAddressCityBatch(tmpList);
					}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){
				addressCityBaseService.insertAddressCityBatch(tmpList);
			}
		}
		if(addressBlockList.size()>0){
//			addressBlockBaseService.insertAddressBlockBatch(addressBlockList);
			List<AddressBlock> tmpList = new ArrayList<AddressBlock>();
			for(int i=0;i<addressBlockList.size();i++){
				tmpList.add(addressBlockList.get(i));
				if(i!=0&&i%1000==0){
					if(tmpList.size()>0){
						addressBlockBaseService.insertAddressBlockBatch(tmpList);
					}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){
				addressBlockBaseService.insertAddressBlockBatch(tmpList);
			}
		}
		if(addressBlockSelfDefinedList.size()>0){
//			addressBlockSelfDefinedBaseService.insertAddressBlockSelfDefinedBatch(addressBlockSelfDefinedList);
			List<AddressBlockSelfDefined> tmpList = new ArrayList<AddressBlockSelfDefined>();
			for(int i=0;i<addressBlockSelfDefinedList.size();i++){
				tmpList.add(addressBlockSelfDefinedList.get(i));
				if(i!=0&&i%1000==0){
					if(tmpList.size()>0){
						addressBlockSelfDefinedBaseService.insertAddressBlockSelfDefinedBatch(tmpList);
					}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){
				addressBlockSelfDefinedBaseService.insertAddressBlockSelfDefinedBatch(tmpList);
			}
		}
		if(groupBuildingList.size()>0){
//			groupBuildingBaseService.insertGroupBuildingBatch(groupBuildingList);
			List<GroupBuilding> tmpList = new ArrayList<GroupBuilding>();
			for(int i=0;i<groupBuildingList.size();i++){
				tmpList.add(groupBuildingList.get(i));
				if(i!=0&&i%1000==0){
					if(tmpList.size()>0){
						groupBuildingBaseService.insertGroupBuildingBatch(tmpList);
					}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){
				groupBuildingBaseService.insertGroupBuildingBatch(tmpList);
			}
		}
		
//		{
//			List<QQQ> tmpList = new ArrayList<QQQ>();
//			for(int i=0;i<xxx.size();i++){
//				tmpList.add(xxx.get(i));
//				if(i%1000==0){
//					if(tmpList.size()>0){
//						yyy;
//					}
//					tmpList.clear();
//				}
//			}
//			if(tmpList.size()>0){
//				yyy;
//			}
//		}
		
		if(groupBuildingHasTCommunitySupplyList.size()>0){
			List<GroupBuildingHasTCommunitySupply> tmpList = new ArrayList<GroupBuildingHasTCommunitySupply>();
			for(int i=0;i<groupBuildingHasTCommunitySupplyList.size();i++){
				tmpList.add(groupBuildingHasTCommunitySupplyList.get(i));
				if(i!=0&&i%1000==0){
					if(tmpList.size()>0){
						groupBuildingHasTCommunitySupplyBaseService.insertGroupBuildingHasTCommunitySupplyBatch(tmpList);
					}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){
				groupBuildingHasTCommunitySupplyBaseService.insertGroupBuildingHasTCommunitySupplyBatch(tmpList);
			}
		}
		
		freshUuidTable();//刷新Uuid表数据
	}
	
	public void showResultDataCount(){
		System.out.println("待处理数据");
		System.out.println("类别数据"+communitySupplyTypeList.size()+"个。");
		System.out.println("评论标签数据"+commentsLabelList.size()+"个。");
		System.out.println("类别评论标签关系"+communitySupplyTypeHasTCommentLabelList.size()+"个。");
		System.out.println("商家信息"+communitySupplyList.size()+"个。");
		System.out.println("商家联系方式"+communitySupplyList.size()+"个。");
		System.out.println("片区信息"+communitySupplyList.size()+"个。");
		System.out.println("小区信息"+groupBuildingList.size()+"个。");
		System.out.println("小区商家关系"+groupBuildingHasTCommunitySupplyList.size()+"个。");
	
	}
	public void showResultData(){
		System.out.println("类别数据");
		for(CommunitySupplyType tmpCommunitySupplyType:communitySupplyTypeList){
			System.out.println(tmpCommunitySupplyType);
		}
		System.out.println("评论标签数据");
		for(CommentsLabel tmpCommentsLabel:commentsLabelList){
			System.out.println(tmpCommentsLabel);
		}
		System.out.println("类别评论标签关系");
		for(CommunitySupplyTypeHasTCommentLabel tmpCommunitySupplyTypeHasTCommentLabel:communitySupplyTypeHasTCommentLabelList){
			System.out.println(tmpCommunitySupplyTypeHasTCommentLabel);
		}
		System.out.println("商家信息");
		for(CommunitySupply tmpCommunitySupply:communitySupplyList){
			System.out.println(tmpCommunitySupply);
		}
		System.out.println("商家联系方式");
		for(CommunitySupplyContect tmpCommunitySupplyContect:communitySupplyContectList){
			System.out.println(tmpCommunitySupplyContect);
		}
		System.out.println("片区信息");
		for(AddressBlockSelfDefined tmpAddressBlockSelfDefined:addressBlockSelfDefinedList){
			System.out.println(tmpAddressBlockSelfDefined);
		}
		System.out.println("小区信息");
		for(GroupBuilding tmpGroupBuilding:groupBuildingList){
			System.out.println(tmpGroupBuilding);
		}
		System.out.println("小区商家关系");
		for(GroupBuildingHasTCommunitySupply tmpGroupBuildingHasTCommunitySupply:groupBuildingHasTCommunitySupplyList){
			System.out.println(tmpGroupBuildingHasTCommunitySupply);
		}
	}
	
	/**
	 * 通过商家类别名称查询已存在的Id
	 * @param name
	 * @return
	 */
	public BigInteger getIdByCommunitySupplyTypeName(String name,BigInteger parentId){
		for(CommunitySupplyType tmpCommunitySupplyType:communitySupplyTypeList){
			if(tmpCommunitySupplyType.getName().trim().equals(name)){
				if(tmpCommunitySupplyType.getParentId().compareTo(parentId)==0){
					return tmpCommunitySupplyType.getId();
				}
			}
		}
		if(connect2Db){
			if(communitySupplyTypeList_db==null){
				communitySupplyTypeList_db = communitySupplyTypeBaseService.getCommunitySupplyTypeByCondition(null);
			}
			for(CommunitySupplyType tmpCommunitySupplyType:communitySupplyTypeList_db){
				if(tmpCommunitySupplyType.getName().trim().equals(name)){
					if(tmpCommunitySupplyType.getParentId().compareTo(parentId)==0){
						return tmpCommunitySupplyType.getId();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 通过评论标签名称查询已存在的Id
	 * @param name
	 * @return
	 */
	public BigInteger getIdByCommentsLabelName(String name){
		for(CommentsLabel tmpCommentsLabel:commentsLabelList){
			if(tmpCommentsLabel.getName().trim().equals(name)){
				return tmpCommentsLabel.getId();
			}
		}
		if(connect2Db){
			if(commentsLabelList_db==null){
				commentsLabelList_db = commentsLabelBaseService.getCommentsLabelByCondition(null);
			}
			for(CommentsLabel tmpCommentsLabel:commentsLabelList_db){
				if(tmpCommentsLabel.getName().trim().equals(name)){
					return tmpCommentsLabel.getId();
				}
			}
		}
		return null;
	}
	
	/**
	 * 通过类别Id+评论标签Id，查询对应记录的Id
	 */
	public BigInteger getIdByCommunitySupplyTypeAndCommentsLabel(BigInteger communitySupplyTypeId,BigInteger commentsLabelId){
		for(CommunitySupplyTypeHasTCommentLabel tmpCommunitySupplyTypeHasTCommentLabel:communitySupplyTypeHasTCommentLabelList){
			if(tmpCommunitySupplyTypeHasTCommentLabel.gettCommunitySupplyTypeFId().compareTo(communitySupplyTypeId)==0
					&&tmpCommunitySupplyTypeHasTCommentLabel.gettCommentsLabelFId().compareTo(commentsLabelId)==0){
				return tmpCommunitySupplyTypeHasTCommentLabel.getId();
			}
		}
		if(connect2Db){
			if(communitySupplyTypeHasTCommentLabelList_db==null){
				communitySupplyTypeHasTCommentLabelList_db = communitySupplyTypeHasTCommentLabelBaseService.getCommunitySupplyTypeHasTCommentLabelByCondition(null);
			}
			for(CommunitySupplyTypeHasTCommentLabel tmpCommunitySupplyTypeHasTCommentLabel:communitySupplyTypeHasTCommentLabelList_db){
				if(tmpCommunitySupplyTypeHasTCommentLabel.gettCommunitySupplyTypeFId().compareTo(communitySupplyTypeId)==0
						&&tmpCommunitySupplyTypeHasTCommentLabel.gettCommentsLabelFId().compareTo(commentsLabelId)==0){
					return tmpCommunitySupplyTypeHasTCommentLabel.getId();
				}
			}
		}
		return null;
	}
	
	/**
	 * 通过商家名称查询已存在的Id
	 * 如果商家名称相同且都属于同一个小区，则返回这个商家的Id
	 * @param name
	 * @return
	 */
	public BigInteger getIdByCommunitySupplyName(String name,BigInteger groupBuildIngId){
		for(CommunitySupply tmpCommunitySupply:communitySupplyList){
			if(tmpCommunitySupply.getName().trim().equals(name)){
				for(GroupBuildingHasTCommunitySupply tmpGroupBuildingHasTCommunitySupply:groupBuildingHasTCommunitySupplyList){
					if(tmpGroupBuildingHasTCommunitySupply.gettGroupBuildingFId().compareTo(groupBuildIngId)==0
							&&tmpGroupBuildingHasTCommunitySupply.gettCommunitySupplyFId().compareTo(tmpCommunitySupply.getId())==0){
						return tmpCommunitySupply.getId();
					}
				}
			}
		}
		if(connect2Db){
			if(communitySupplyList_db==null){
				communitySupplyList_db = communitySupplyBaseService.getCommunitySupplyByCondition(null);
			}
			for(CommunitySupply tmpCommunitySupply:communitySupplyList_db){
				if(tmpCommunitySupply.getName().trim().equals(name)){
					for(GroupBuildingHasTCommunitySupply tmpGroupBuildingHasTCommunitySupply:groupBuildingHasTCommunitySupplyList_db){
						if(tmpGroupBuildingHasTCommunitySupply.gettGroupBuildingFId().compareTo(groupBuildIngId)==0
								&&tmpGroupBuildingHasTCommunitySupply.gettCommunitySupplyFId().compareTo(tmpCommunitySupply.getId())==0){
							return tmpCommunitySupply.getId();
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 通过商家Id+联系方式，查询对应记录的Id
	 */
	public BigInteger getIdByCommunitySupplyAndCommunityContect(BigInteger communitySupplyId,String contectInfo){
		for(CommunitySupplyContect tmpCommunitySupplyContect:communitySupplyContectList){
			if(tmpCommunitySupplyContect.gettCommunitySupplyFId().compareTo(communitySupplyId)==0
					&&tmpCommunitySupplyContect.getContectInfo().trim().equals(contectInfo)){
				return tmpCommunitySupplyContect.getId();
			}
		}
		if(connect2Db){
			if(communitySupplyContectList_db==null){
				communitySupplyContectList_db = communitySupplyContectBaseService.getCommunitySupplyContectByCondition(null);
			}
			for(CommunitySupplyContect tmpCommunitySupplyContect:communitySupplyContectList_db){
				if(tmpCommunitySupplyContect.gettCommunitySupplyFId().compareTo(communitySupplyId)==0
						&&tmpCommunitySupplyContect.getContectInfo().trim().equals(contectInfo)){
					return tmpCommunitySupplyContect.getId();
				}
			}
		}
		return null;
	}
	
	/**
	 * 通过市区名称查询已存在的Id
	 * @param name
	 * @return
	 */
	public BigInteger getIdByAddressCityName(String name,BigInteger provinceFId){
		for(AddressCity tmpAddressCity:addressCityList){
			if(tmpAddressCity.getName().trim().equals(name)){
				if(tmpAddressCity.gettProvinceFId().compareTo(provinceFId)==0){
					return tmpAddressCity.getId();
				}
			}
		}
		if(connect2Db){
			if(addressCityList_db==null){
				addressCityList_db = addressCityBaseService.getAddressCityByCondition(null);
			}
			for(AddressCity tmpAddressCity:addressCityList_db){
				if(tmpAddressCity.getName().trim().equals(name)){
					if(tmpAddressCity.gettProvinceFId().compareTo(provinceFId)==0){
						return tmpAddressCity.getId();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 通过片区名称查询已存在的Id
	 * @param name
	 * @return
	 */
	public BigInteger getIdByAddressBlockSelfDefinedName(String name,BigInteger addressCityFId){
		for(AddressBlockSelfDefined tmpAddressBlockSelfDefined:addressBlockSelfDefinedList){
			if(tmpAddressBlockSelfDefined.getName().trim().equals(name)){
				if(tmpAddressBlockSelfDefined.gettAddressCityFId().compareTo(addressCityFId)==0){
					return tmpAddressBlockSelfDefined.getId();
				}
			}
		}
		if(connect2Db){
			if(addressBlockSelfDefinedList_db==null){
				addressBlockSelfDefinedList_db = addressBlockSelfDefinedBaseService.getAddressBlockSelfDefinedByCondition(null);
			}
			for(AddressBlockSelfDefined tmpAddressBlockSelfDefined:addressBlockSelfDefinedList_db){
				if(tmpAddressBlockSelfDefined.getName().trim().equals(name)){
					if(tmpAddressBlockSelfDefined.gettAddressCityFId().compareTo(addressCityFId)==0){
						return tmpAddressBlockSelfDefined.getId();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 通过小区名称查询已存在的Id
	 * @param name
	 * @return
	 */
	public BigInteger getIdByGroupBuildingName(String name,BigInteger blockFId){
		for(GroupBuilding tmpGroupBuilding:groupBuildingList){
			if(tmpGroupBuilding.getName().trim().equals(name)){
				if(tmpGroupBuilding.gettBlockFId().compareTo(blockFId)==0){
					if(tmpGroupBuilding.getCheckStatus().compareTo(RoomDict.CheckStatus.WuXuShenHe)==0||tmpGroupBuilding.getCheckStatus().compareTo(RoomDict.CheckStatus.ShenHeTongGuo)==0){
						return tmpGroupBuilding.getId();
					}
				}
			}
		}
		if(connect2Db){
			if(groupBuildingList_db==null){
				groupBuildingList_db = groupBuildingBaseService.getGroupBuildingByCondition(null);
			}
			for(GroupBuilding tmpGroupBuilding:groupBuildingList_db){
				if(tmpGroupBuilding.getName().trim().equals(name)){
					if(tmpGroupBuilding.gettBlockFId().compareTo(blockFId)==0){
						if(tmpGroupBuilding.getCheckStatus().compareTo(RoomDict.CheckStatus.WuXuShenHe)==0||tmpGroupBuilding.getCheckStatus().compareTo(RoomDict.CheckStatus.ShenHeTongGuo)==0){
							return tmpGroupBuilding.getId();
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 通过行政区名称查询已存在的Id
	 * @param name
	 * @return
	 */
	public BigInteger getIdByAddressBlockName(String name,BigInteger cityFId){
		for(AddressBlock tmpAddressBlock:addressBlockList){
			if(tmpAddressBlock.getName().trim().equals(name)){
				if(tmpAddressBlock.gettCityFId().compareTo(cityFId)==0){
					return tmpAddressBlock.getId();
				}
			}
		}
		if(connect2Db){
			if(addressBlockList_db==null){
				addressBlockList_db = addressBlockBaseService.getAddressBlockByCondition(null);
			}
			for(AddressBlock tmpAddressBlock:addressBlockList_db){
				if(tmpAddressBlock.getName().trim().equals(name)){
					if(tmpAddressBlock.gettCityFId().compareTo(cityFId)==0){
						return tmpAddressBlock.getId();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 通过小区Id+商家Id，查询对应记录的Id
	 */
	public BigInteger getIdByGroupBuildingAndCommunitySupply(BigInteger groupBuildingId,BigInteger communitySupplyId){
//		System.out.println("groupBuildingId="+groupBuildingId+",communitySupplyId"+communitySupplyId);
		for(GroupBuildingHasTCommunitySupply tmpGroupBuildingHasTCommunitySupply:groupBuildingHasTCommunitySupplyList){
			if(tmpGroupBuildingHasTCommunitySupply.gettGroupBuildingFId().compareTo(groupBuildingId)==0
					&&tmpGroupBuildingHasTCommunitySupply.gettCommunitySupplyFId().compareTo(communitySupplyId)==0){
				return tmpGroupBuildingHasTCommunitySupply.getId();
			}
		}
		if(connect2Db){
			if(groupBuildingHasTCommunitySupplyList_db==null){
				groupBuildingHasTCommunitySupplyList_db = groupBuildingHasTCommunitySupplyBaseService.getGroupBuildingHasTCommunitySupplyByCondition(null);
			}
			for(GroupBuildingHasTCommunitySupply tmpGroupBuildingHasTCommunitySupply:groupBuildingHasTCommunitySupplyList_db){
				if(tmpGroupBuildingHasTCommunitySupply.gettGroupBuildingFId().compareTo(groupBuildingId)==0
						&&tmpGroupBuildingHasTCommunitySupply.gettCommunitySupplyFId().compareTo(communitySupplyId)==0){
					return tmpGroupBuildingHasTCommunitySupply.getId();
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 获取cell字符串内容
	 * @param row
	 * @param index
	 * @return
	 */
	public static String cellStr(XSSFRow row, int index) {
		if(row==null){
			return null;
		}
		XSSFCell cell = row.getCell(index);// 得到每一行的每一列
		if (cell == null) {
			return null;
		}
		Object cellValue = null;
		// 根据excel中单元格内数字的属性，来用不同的方法取得有效值
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING:
			cellValue = cell.getRichStringCellValue().getString();
			if (((String) cellValue).trim().equals("") || ((String) cellValue).trim().length() <= 0) {
				cellValue = "";
			}
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
			BigDecimal big = new BigDecimal(cell.getNumericCellValue());
			// cellValue =big.toEngineeringString();
			cellValue = big.toString();
			break;
		case XSSFCell.CELL_TYPE_FORMULA:
			BigDecimal bigula = new BigDecimal(cell.getCachedFormulaResultType());
			// cellValue = bigula.toEngineeringString();
			cellValue = bigula.toString();
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			cellValue = "";
			break;
		default:
			break;
		}
		return cellValue.toString().trim();
	}
	
	//public static String cellStr(XSSFRow row,int index){
	// XSSFCell cell = row.getCell(index);
	// if(cell!=null&&!StringUtils.isEmpty(cell)){
	// return cell.toString();
	// }
	// return null;
	// }
	
}
