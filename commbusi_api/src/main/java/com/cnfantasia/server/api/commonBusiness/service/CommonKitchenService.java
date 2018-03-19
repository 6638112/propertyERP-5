/**   
* Filename:    CommonKitchenService.java   
* @version:    1.0  
* Create at:   2014年9月24日 上午1:54:37   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.kitchen.constant.KitchenDict;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookCollectEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookStepEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.kitchenCookbook.entity.KitchenCookbook;
import com.cnfantasia.server.domainbase.kitchenCookbookStep.entity.KitchenCookbookStep;
import com.cnfantasia.server.domainbase.kitchenCookbookStepTips.entity.KitchenCookbookStepTips;
import com.cnfantasia.server.domainbase.kitchenCookbookTopType.entity.KitchenCookbookTopType;
import com.cnfantasia.server.domainbase.kitchenCookbookType.entity.KitchenCookbookType;
import com.cnfantasia.server.domainbase.kitchenDetail.entity.KitchenDetail;

/**
 * Filename:    CommonKitchenService.java
 * @version:    1.0.0
 * Create at:   2014年9月24日 上午1:54:37
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月24日       shiyl             1.0             1.0 Version
 */
public class CommonKitchenService implements ICommonKitchenService{
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}

	private ISysParamParser kitchenCookbookTypePicParamParser;
	public void setKitchenCookbookTypePicParamParser(ISysParamParser kitchenCookbookTypePicParamParser) {
		this.kitchenCookbookTypePicParamParser = kitchenCookbookTypePicParamParser;
	}

//	private ISysParamParser kitchenCookbookPicParamParser;
//	public void setKitchenCookbookPicParamParser(ISysParamParser kitchenCookbookPicParamParser) {
//		this.kitchenCookbookPicParamParser = kitchenCookbookPicParamParser;
//	}

	private ISysParamParser kitchenCookbookStepPicParamParser;
	public void setKitchenCookbookStepPicParamParser(ISysParamParser kitchenCookbookStepPicParamParser) {
		this.kitchenCookbookStepPicParamParser = kitchenCookbookStepPicParamParser;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}

	@Override
	public Map<String, Object> cookbookCollect2Map(KitchenCookbookCollectEntity collectEntity,Boolean defaultNullFlag){
		Map<String,Object> resMap = new HashMap<String, Object>();
		if(collectEntity!=null){
			{//菜谱本身信息
				KitchenEntity kitchenEntity = collectEntity.getKitchenEntity();
				Map<String,Object> tmpMap = cookbookInfo2Map(kitchenEntity, kitchenEntity.getTotalLikeEatCount(),
						kitchenEntity.getKitchenDetailList(), kitchenEntity.getIsLikeEat(), kitchenEntity.getKitchenCookbookStepList(),kitchenEntity.getCollectFlag(),defaultNullFlag);
				resMap.putAll(tmpMap);
			}
			{//收藏信息
				resMap.put("ext_fml_isLikeEat", collectEntity.getFml_isLikeEat());
				resMap.put("ext_fml_totalLikeEatCount", collectEntity.getFml_totalLikeEatCount());
				List<Map<String,Object>> resUserList = null;
				{//喜欢吃的用户列表
					List<UserSimpleEntity> userList = collectEntity.getFml_isLikeEat_userList();
					if(userList!=null){
						resUserList = new ArrayList<Map<String,Object>>();
						for(UserSimpleEntity tmpUser:userList){
							Map<String,Object> currUserMap = commEntityConvertService.baseUser2Map(tmpUser);
							resUserList.add(currUserMap);
						}
					}
				}
				resMap.put("ext_fml_isLikeEat_userList", resUserList);
				resMap.put("ext_cookbookCollect_id", collectEntity.getId());//收藏的菜谱Id
			}
		}
		return resMap;
	}
	
	/**
	 * 菜谱信息转Map
	 * @param kitchenCookbook
	 * @param totalLikeEatCount
	 * @param kitchenParamList
	 * @param isLikeEat
	 * @param kitchenCookbookStepList
	 * @return
	 */
	@Override
	public Map<String, Object> cookbookInfo2Map(KitchenCookbook kitchenCookbook, Integer totalLikeEatCount,
			List<KitchenDetail> kitchenParamList, Integer isLikeEat, List<KitchenCookbookStepEntity> kitchenCookbookStepList,Integer collectFlag) {
		return cookbookInfo2Map(kitchenCookbook, totalLikeEatCount, kitchenParamList, isLikeEat, kitchenCookbookStepList,collectFlag, null);
	}
	@Override
	public Map<String, Object> cookbookInfo2Map(KitchenCookbook kitchenCookbook, Integer totalLikeEatCount,
			List<KitchenDetail> kitchenParamList, Integer isLikeEat, List<KitchenCookbookStepEntity> kitchenCookbookStepList
			,Integer collectFlag
			,Boolean defaultNullFlag) {
		Map<String, Object> resMap = commEntityConvertService.cookbookInfo2Map(kitchenCookbook);
		
		if(defaultNullFlag!=null){//是否为默认空菜谱标识
			if(defaultNullFlag){
				resMap.put("defaultNullFlag", true);
			}else{
				resMap.put("defaultNullFlag", false);
			}
		}
		{// 总共想吃的人数
			if (totalLikeEatCount == null) {
				totalLikeEatCount = 0;
			}
			resMap.put("totalLikeEatCount", totalLikeEatCount);
		}
		if (kitchenParamList != null) {// 主料和辅料
			List<Map<String, Object>> cookbookMainParamList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> cookbookAssistParamList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> cookbookBatchingParamList = new ArrayList<Map<String, Object>>();
			for (KitchenDetail tmpKitchenDetail : kitchenParamList) {
				Map<String, Object> tmpParamMap = new HashMap<String, Object>();
				tmpParamMap.put("key", tmpKitchenDetail.getKey());
				tmpParamMap.put("value", tmpKitchenDetail.getValue());
				if(tmpKitchenDetail.getType()!=null){
					if (KitchenDict.KitchenDetail_Type.MAIN.compareTo(tmpKitchenDetail.getType()) == 0) {
						cookbookMainParamList.add(tmpParamMap);
					} else if (KitchenDict.KitchenDetail_Type.ASSIST.compareTo(tmpKitchenDetail.getType()) == 0) {
						cookbookAssistParamList.add(tmpParamMap);
					} else if (KitchenDict.KitchenDetail_Type.BATCHING.compareTo(tmpKitchenDetail.getType()) == 0) {
						cookbookBatchingParamList.add(tmpParamMap);
					}
				}
			}
			resMap.put("cookbookMainParamList", cookbookMainParamList);
			resMap.put("cookbookAssistParamList", cookbookAssistParamList);
			resMap.put("cookbookBatchingParamList", cookbookBatchingParamList);
		}
		if(isLikeEat!=null){// 当前用户是否已经喜欢吃
			if (isLikeEat != null && KitchenDict.Kitchen_Cookbook_IsLikeEat.YES.compareTo(isLikeEat) == 0) {
				resMap.put("isLikeEat", true);
			} else {
				resMap.put("isLikeEat", false);
			}
		}
		if(collectFlag!=null){// 当前用户是否已经收藏
			if (collectFlag != null && KitchenDict.Kitchen_Cookbook_CollectFlag.YES.compareTo(collectFlag) == 0) {
				resMap.put("collectFlag", true);
			} else {
				resMap.put("collectFlag", false);
			}
		}
		if (kitchenCookbookStepList != null && kitchenCookbookStepList.size() > 0) {
			// 根据stepNo 升序排序
			Collections.sort(kitchenCookbookStepList, new Comparator<KitchenCookbookStep>() {
				@Override
				public int compare(KitchenCookbookStep arg0, KitchenCookbookStep arg1) {
					if(arg0.getOrderNum()==null||arg1.getOrderNum()==null){
						return 0;
					}
					return arg0.getOrderNum().compareTo(arg1.getOrderNum());
				}
			});
			List<Map<String, Object>> cookStepList = new ArrayList<Map<String, Object>>();
			for (KitchenCookbookStepEntity tmpStep : kitchenCookbookStepList) {
				cookStepList.add(cookbookStepInfo2Map(tmpStep,tmpStep.getKitchenCookbookStepTipsList()));
			}
			resMap.put("cookStepList", cookStepList);
		} else {
			resMap.put("cookStepList", null);
		}
		return resMap;
	}
//	public static void main(String[] args) {
//		List<KitchenCookbookStep> list = new ArrayList<KitchenCookbookStep>();
//		{
//			KitchenCookbookStep kitchenCookbookStep1 = new KitchenCookbookStep();
//			kitchenCookbookStep1.setOrderNum(3);
//			kitchenCookbookStep1.setDesc("第3步");
//			list.add(kitchenCookbookStep1);
//		}
//		{
//			KitchenCookbookStep kitchenCookbookStep1 = new KitchenCookbookStep();
//			kitchenCookbookStep1.setOrderNum(5);
//			kitchenCookbookStep1.setDesc("第5步");
//			list.add(kitchenCookbookStep1);
//		}
//		{
//			KitchenCookbookStep kitchenCookbookStep1 = new KitchenCookbookStep();
//			kitchenCookbookStep1.setOrderNum(1);
//			kitchenCookbookStep1.setDesc("第1步");
//			list.add(kitchenCookbookStep1);
//		}
//	// 根据stepNo 升序排序
//		Collections.sort(list, new Comparator<KitchenCookbookStep>() {
//			@Override
//			public int compare(KitchenCookbookStep arg0, KitchenCookbookStep arg1) {
//				return arg0.getOrderNum().compareTo(arg1.getOrderNum());
//			}
//		});
//		for(KitchenCookbookStep aa:list){
//			System.out.println(aa.getDesc());
//		}
//		
//	}
	@Override
	public Map<String, Object> cookbookStepInfo2Map(KitchenCookbookStep kitchenCookbookStep,List<KitchenCookbookStepTips> kitchenCookbookStepTipsList) {
		String stepPicBasePath = kitchenCookbookStepPicParamParser.parseParamValue();
		Map<String, Object> resMap = new HashMap<String, Object>();
		// resMap.put("id", kitchenCookbookStep.getId());
		resMap.put("desc", kitchenCookbookStep.getDesc());
		resMap.put("stepNo", kitchenCookbookStep.getOrderNum());
		resMap.put("picUrl", StringUtils.isEmpty(kitchenCookbookStep.getPicUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(stepPicBasePath + kitchenCookbookStep.getPicUrl(),kitchenCookbookStep));
		if(kitchenCookbookStepTipsList!=null){
			List<Map<String,Object>> tipsList = new ArrayList<Map<String,Object>>();
			for(KitchenCookbookStepTips tmpKitchenCookbookStepTips:kitchenCookbookStepTipsList){
				Map<String,Object> tmpMap = kitchenCookbookStepTips2Map(tmpKitchenCookbookStepTips);
				tipsList.add(tmpMap);
			}
			resMap.put("ext_tipsList", tipsList);
		}
		return resMap;
	}
	@Override
	public Map<String, Object> kitchenCookbookStepTips2Map(KitchenCookbookStepTips tmpTips){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("id", tmpTips.getId());
		tmpMap.put("content", tmpTips.getContent());
		tmpMap.put("title", tmpTips.getTitle());
		tmpMap.put("kitchenCookbookStepId", tmpTips.gettKitchenCookbookStepFId());
		return tmpMap;
	}
	
	@Override
	public Map<String, Object> cookbookTypeInfo2Map(KitchenCookbookType kitchenCookbookType,KitchenCookbookTopType kitchenCookbookTopType,Integer collectFlag) {
		String typePicBasePath = kitchenCookbookTypePicParamParser.parseParamValue();
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("id", kitchenCookbookType.getId());
		resMap.put("name", kitchenCookbookType.getName());
		resMap.put("picUrl", StringUtils.isEmpty(kitchenCookbookType.getPicUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(typePicBasePath + kitchenCookbookType.getPicUrl(),kitchenCookbookType));
		if(kitchenCookbookTopType!=null){
			Map<String,Object> topMap = new HashMap<String, Object>();
			topMap.put("id", kitchenCookbookTopType.getId());
			topMap.put("name", kitchenCookbookTopType.getName());
			if(kitchenCookbookTopType.getMultiFlag()!=null){
				if(KitchenDict.Kitchen_Cookbook_Type_MultiFlag.YES.compareTo(kitchenCookbookTopType.getMultiFlag())==0){
					topMap.put("multiFlagBool", true);
				}else{
					topMap.put("multiFlagBool", false);
				}
			}
			resMap.put("ext_topTypeInfo", topMap);
		}
		if(collectFlag!=null){
			if(KitchenDict.Kitchen_Cookbook_Type_CollectFlag.YES.compareTo(collectFlag)==0){
				resMap.put("ext_isCollect", true);
			}else{
				resMap.put("ext_isCollect", false);
			}
		}
		return resMap;
	}
}
