/**   
* Filename:    SmallPicUploadConfig.java   
* @version:    1.0  
* Create at:   2014年9月4日 上午2:59:32   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月4日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.constant;

import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.api.commonBusiness.entity.WidthHeight;

/**
 * Filename:    SmallPicUploadConfig.java
 * @version:    1.0.0
 * Create at:   2014年9月4日 上午2:59:32
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月4日       shiyl             1.0             1.0 Version
 */
public class SmallPicUploadConfig {
	
	/**
	 * 图片所属业务模块类型
	 */
	public enum BusinessModelType{
		All(SmallPicUploadConfig.guigeList) //全部的
		,User(SmallPicUploadConfig.User_guigeList) //用户
		,Market(SmallPicUploadConfig.Ebuy_guigeList) //超市
		,Kitchen(SmallPicUploadConfig.Kitchen_guigeList) //厨房
		,JieFang(SmallPicUploadConfig.JieFang_guigeList) //街坊
		,FamilyMsg(SmallPicUploadConfig.FamilyMsg_guigeList) //家庭留言板
		,Pinyipin(SmallPicUploadConfig.Community_PingExchang_guigeList) //拼一拼
		,Exchange(SmallPicUploadConfig.Community_PingExchang_guigeList) //换一换
		,CommunitySupply(SmallPicUploadConfig.Community_SupplyList) //社区商家
		,CommunityCompany(SmallPicUploadConfig.Community_CompanyList); //社区商户
		
		private Map<String,WidthHeight> guigeList;
		private BusinessModelType(Map<String,WidthHeight> guigeList){
			this.guigeList = guigeList;
		}
		public Map<String, WidthHeight> getGuigeList() {
			return guigeList;
		}
	}
	
	//key文件名，value规格
	private static final Map<String,WidthHeight> User_guigeList = new HashMap<String,WidthHeight>();
	private static final Map<String,WidthHeight> Ebuy_guigeList = new HashMap<String,WidthHeight>();
	private static final Map<String,WidthHeight> Kitchen_guigeList = new HashMap<String,WidthHeight>();
	private static final Map<String,WidthHeight> JieFang_guigeList = new HashMap<String,WidthHeight>();
	private static final Map<String,WidthHeight> FamilyMsg_guigeList = new HashMap<String,WidthHeight>();
	private static final Map<String,WidthHeight> Community_PingExchang_guigeList = new HashMap<String,WidthHeight>();
	private static final Map<String,WidthHeight> Community_SupplyList = new HashMap<String,WidthHeight>();
	private static final Map<String,WidthHeight> Community_CompanyList = new HashMap<String,WidthHeight>();
	
	private static final Map<String,WidthHeight> guigeList = new HashMap<String,WidthHeight>();
	public static final float Default_Quality = 1F;
	//之所以不使用 before_combi,是因为 before_combi 定义的信息尚不可确认为最新和最全,所以使用之前的最保险
	static{
		{
			//用户
			User_guigeList.put(new WidthHeight(54,54).getFileName(),new WidthHeight(54,54));
			User_guigeList.put(new WidthHeight(72,72).getFileName(),new WidthHeight(72,72));
			User_guigeList.put(new WidthHeight(74,74).getFileName(),new WidthHeight(74,74));
			
//			//用户 before_combi
//			User_guigeList.put(new WidthHeight(72,72).getFileName(),new WidthHeight(72,72));
//			User_guigeList.put(new WidthHeight(74,74).getFileName(),new WidthHeight(74,74));
		}
		{
			//超市
			Ebuy_guigeList.put(new WidthHeight(225,163).getFileName(),new WidthHeight(163,163));
			Ebuy_guigeList.put(new WidthHeight(640,467).getFileName(),new WidthHeight(380,380));
			Ebuy_guigeList.put(new WidthHeight(127,127).getFileName(),new WidthHeight(127,127));
			Ebuy_guigeList.put(new WidthHeight(72,72).getFileName(),new WidthHeight(72,72));
			//超市2.0
			Ebuy_guigeList.put(new WidthHeight(226,166).getFileName(),new WidthHeight(226,166));
			Ebuy_guigeList.put(new WidthHeight(640,365).getFileName(),new WidthHeight(640,365));
			Ebuy_guigeList.put(new WidthHeight(126,126).getFileName(),new WidthHeight(126,126));
			Ebuy_guigeList.put(new WidthHeight(72,72).getFileName(),new WidthHeight(72,72));
			//积分商品 2015-1-7 15:46:40
//			Ebuy_guigeList.put(new WidthHeight(180,120).getFileName(),new WidthHeight(180,120));
			
//			//超市 before_combi
//			Ebuy_guigeList.put(new WidthHeight(640,467).getFileName(),new WidthHeight(640,467));
//			Ebuy_guigeList.put(new WidthHeight(72,72).getFileName(),new WidthHeight(72,72));
//			Ebuy_guigeList.put(new WidthHeight(127,127).getFileName(),new WidthHeight(127,127));
//			Ebuy_guigeList.put(new WidthHeight(225,163).getFileName(),new WidthHeight(225,163));
		}
		{
			//厨房
			Kitchen_guigeList.put(new WidthHeight(640,400).getFileName(),new WidthHeight(640,400));
			Kitchen_guigeList.put(new WidthHeight(225,163).getFileName(),new WidthHeight(225,163));
			Kitchen_guigeList.put(new WidthHeight(640,430).getFileName(),new WidthHeight(640,430));
			//厨房2.0
			Kitchen_guigeList.put(new WidthHeight(640,470).getFileName(),new WidthHeight(640,470));
			Kitchen_guigeList.put(new WidthHeight(226,166).getFileName(),new WidthHeight(226,166));
			Kitchen_guigeList.put(new WidthHeight(640,365).getFileName(),new WidthHeight(640,365));
			Kitchen_guigeList.put(new WidthHeight(126,126).getFileName(),new WidthHeight(126,126));
			Kitchen_guigeList.put(new WidthHeight(640,555).getFileName(),new WidthHeight(640,555));
			Kitchen_guigeList.put(new WidthHeight(620,600).getFileName(),new WidthHeight(620,600));
			//厨房2014-9-26 11:05:51
			Kitchen_guigeList.put(new WidthHeight(640,520).getFileName(),new WidthHeight(640,520));
			Kitchen_guigeList.put(new WidthHeight(226,166).getFileName(),new WidthHeight(226,166));
			Kitchen_guigeList.put(new WidthHeight(640,520).getFileName(),new WidthHeight(640,520));
			Kitchen_guigeList.put(new WidthHeight(126,126).getFileName(),new WidthHeight(126,126));
			Kitchen_guigeList.put(new WidthHeight(640,520).getFileName(),new WidthHeight(640,520));
			Kitchen_guigeList.put(new WidthHeight(590,590).getFileName(),new WidthHeight(590,590));
			
//			//厨房 before_combi
//			Kitchen_guigeList.put(new WidthHeight(640,520).getFileName(),new WidthHeight(640,520));
//			Kitchen_guigeList.put(new WidthHeight(640,470).getFileName(),new WidthHeight(640,470));
//			Kitchen_guigeList.put(new WidthHeight(590,590).getFileName(),new WidthHeight(590,590));
//			Kitchen_guigeList.put(new WidthHeight(226,166).getFileName(),new WidthHeight(226,166));
//			Kitchen_guigeList.put(new WidthHeight(126,126).getFileName(),new WidthHeight(126,126));
		}
		{
			//街坊
			JieFang_guigeList.put(new WidthHeight(74,74).getFileName(),new WidthHeight(72,72));
			JieFang_guigeList.put(new WidthHeight(152,152).getFileName(),new WidthHeight(150,150));
			
//			//街坊 before_combi
//			JieFang_guigeList.put(new WidthHeight(74,74).getFileName(),new WidthHeight(74,74));
//			JieFang_guigeList.put(new WidthHeight(152,152).getFileName(),new WidthHeight(152,152));
		}
		{
			//家庭留言板
			FamilyMsg_guigeList.put(new WidthHeight(74,74).getFileName(),new WidthHeight(72,72));
			FamilyMsg_guigeList.put(new WidthHeight(152,152).getFileName(),new WidthHeight(150,150));
		}
		
		{
			//拼一拼 换一换
			Community_PingExchang_guigeList.put(new WidthHeight(107,107).getFileName(),new WidthHeight(107,107));
			Community_PingExchang_guigeList.put(new WidthHeight(170,170).getFileName(),new WidthHeight(170,170));
			Community_PingExchang_guigeList.put(new WidthHeight(265,230).getFileName(),new WidthHeight(265,230));
			Community_PingExchang_guigeList.put(new WidthHeight(640,520).getFileName(),new WidthHeight(640,520));
			
//			//拼一拼 换一换 before_combi
//			Community_PingExchang_guigeList.put(new WidthHeight(170,170).getFileName(),new WidthHeight(170,170));
//			Community_PingExchang_guigeList.put(new WidthHeight(265,230).getFileName(),new WidthHeight(265,230));
		}
		
		{
			Community_SupplyList.put(new WidthHeight(168,130).getFileName(),new WidthHeight(168,130));
		}
		{//Community_CompanyList
			
		}
	}
	static{
		guigeList.putAll(User_guigeList);
		guigeList.putAll(Ebuy_guigeList);
		guigeList.putAll(Kitchen_guigeList);
		guigeList.putAll(JieFang_guigeList);
		guigeList.putAll(FamilyMsg_guigeList);
		guigeList.putAll(Community_PingExchang_guigeList);
		guigeList.putAll(Community_SupplyList);
		guigeList.putAll(Community_CompanyList);
	}
	
//	public static void main(String[] args) {
//		System.out.println(guigeList.size());
//	}
	
}
