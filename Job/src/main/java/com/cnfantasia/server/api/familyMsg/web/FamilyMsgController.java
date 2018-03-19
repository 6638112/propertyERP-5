/**   
* Filename:    FamilyMsgController.java   
* @version:    1.0  
* Create at:   2015年3月12日 上午3:15:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyMsg.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonKitchenService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonUserService;
import com.cnfantasia.server.api.commonBusiness.util.RelativeDateFormat;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductWithCurrProductSpec;
import com.cnfantasia.server.api.familyMsg.constant.FamilyMsgDict;
import com.cnfantasia.server.api.familyMsg.entity.FamilyMsgEntity;
import com.cnfantasia.server.api.familyMsg.entity.MsgExtDataAdd;
import com.cnfantasia.server.api.familyMsg.service.IFamilyMsgService;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.kitchen.dao.IKitchenDao;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookCollectEntity;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.familyMsg.entity.FamilyMsg;
import com.cnfantasia.server.domainbase.familyMsgExtData.entity.FamilyMsgExtData;
import com.cnfantasia.server.domainbase.familyMsgHasTUser.entity.FamilyMsgHasTUser;
import com.cnfantasia.server.domainbase.familyMsgPic.entity.FamilyMsgPic;
import com.cnfantasia.server.domainbase.kitchenCookbook.entity.KitchenCookbook;

/**
 * Filename:    FamilyMsgController.java
 * @version:    1.0.0
 * Create at:   2015年3月12日 上午3:15:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月12日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/familyMsg")
public class FamilyMsgController extends BaseController{

    private ICommonKitchenService commonKitchenService;

    public void setCommonKitchenService(ICommonKitchenService commonKitchenService) {
        this.commonKitchenService = commonKitchenService;
    }
    
    private IKitchenDao kitchenDao;

    public void setKitchenDao(IKitchenDao kitchenDao) {
        this.kitchenDao = kitchenDao;
    }
	
	private ICommonUserService commonUserService;
	public void setCommonUserService(ICommonUserService commonUserService) {
		this.commonUserService = commonUserService;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private IFamilyMsgService familyMsgService;
	public void setFamilyMsgService(IFamilyMsgService familyMsgService) {
		this.familyMsgService = familyMsgService;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private ISysParamParser kitchenCookbookPicParamParser;
	public void setKitchenCookbookPicParamParser(ISysParamParser kitchenCookbookPicParamParser) {
		this.kitchenCookbookPicParamParser = kitchenCookbookPicParamParser;
	}

	/**
	 * 查询@成员列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryUserList.json")
	@ResponseBody
	public JsonResponse qryUserList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		List<UserSimpleEntity> userList = commonUserService.getFamilyMembersWithoutSelf(userId, false);//不要返回待审核的
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(userList!=null){
			for(UserSimpleEntity tmpUser:userList){
				Map<String,Object> tmpMap = commEntityConvertService.baseUser2MapForApply(tmpUser, RoomConstants.UserRoomApplyStatus.PASS, null);
				resList.add(tmpMap);
			}
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	
	/**
	 * 发表留言
	 * @param request
	 * @return
	 */
	@RequestMapping("/addMsg.json")
	@ResponseBody
	public JsonResponse addMsg(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//留言内容
		String content = request.getParameter("content");
		if(content!=null&&content.length()>120){
			throw new BusinessRuntimeException("FamilyMsg.addMsg.contentLength.toLong",new Object[]{120,content.length()});
		}
		//图片信息
		List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "picInfo");
		//@的用户列表
		Set<BigInteger> userIdSetList = null;
		String userIdListStr = request.getParameter("userIdList");
		if(!StringUtils.isEmpty(userIdListStr)){
			List<BigInteger> tmpList = JSON.parseArray(userIdListStr, BigInteger.class);
			if(tmpList!=null&&tmpList.size()>0){
				userIdSetList = new HashSet<BigInteger>();
				userIdSetList.addAll(tmpList);
			}
		}
		List<MsgExtDataAdd> msgExtDataAddList = null;
        Integer extDataType = null;
		{//拓展信息
			//拓展信息Id列表
			List<BigInteger> extDataIdList = null;
			String extDataIdListStr = request.getParameter("extDataIdList");
			if(!StringUtils.isEmpty(extDataIdListStr)){
				extDataIdList = JSON.parseArray(extDataIdListStr, BigInteger.class);
			}
			//拓展信息类别
			String extDataTypeStr = request.getParameter("extDataType");
			if(!StringUtils.isEmpty(extDataTypeStr)){
				extDataType = Integer.valueOf(extDataTypeStr);
			}
			if(extDataIdList!=null&&extDataIdList.size()>0){
				if(extDataType==null){
					throw new ValidateRuntimeException("FamilyMsg.addMsg.extDataType.null");
				}else{
					msgExtDataAddList = new ArrayList<MsgExtDataAdd>();
					for(BigInteger dataId:extDataIdList){
						MsgExtDataAdd tmpData = new MsgExtDataAdd(dataId, extDataType);
						msgExtDataAddList.add(tmpData);
					}
				}
			}
		}
		//2.交互
		familyMsgService.addMsg(userId,content,extDataType,userIdSetList,msgExtDataAddList, picList);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 查询留言列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryMsgList.json")
	@ResponseBody
	public JsonResponse qryMsgList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		List<FamilyMsgEntity> msgList = familyMsgService.qryMsgList(userId,pageModel);
		String nowTime = dualDao.getNowTime();
		Long nowTimeLong = DateUtil.timeToLong(nowTime);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(msgList!=null&&msgList.size()>0){
			for(FamilyMsgEntity tmpEntity:msgList){
				Map<String,Object> tmpMap = familyMsg2Map(userId,nowTimeLong, tmpEntity);
				resList.add(tmpMap);
			}
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 查询单个留言详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryMsgDetail.json")
	@ResponseBody
	public JsonResponse qryMsgDetail(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		String msgIdStr = request.getParameter("msgId");
		BigInteger msgId = new BigInteger(msgIdStr);
		//2.交互
		FamilyMsgEntity msgDetail = familyMsgService.qryMsgDetail(userId,msgId);
		String nowTime = dualDao.getNowTime();
		Long nowTimeLong = DateUtil.timeToLong(nowTime);
		//3.结果处理
		Map<String,Object> resMap = familyMsg2Map(userId,nowTimeLong, msgDetail);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	private Map<String,Object> familyMsg2Map(BigInteger userId,Long nowTimeLong, FamilyMsgEntity familyMsgEntity){
		return familyMsg2Map(userId,nowTimeLong,familyMsgEntity,familyMsgEntity.getCreaterInfo(), familyMsgEntity.getFamilyMsgHasTUserList(),familyMsgEntity.getFamilyMsgExtDataList()
				,familyMsgEntity.getFamilyMsgPicList(),familyMsgEntity.getPreCommentsEntityList(),familyMsgEntity.getExtCommentCount(),familyMsgEntity.getExtIsFavour(), familyMsgEntity.getExtFavourCount());
	}
	private Map<String,Object> familyMsg2Map(BigInteger userId,Long nowTimeLong,FamilyMsg familyMsg,UserSimpleEntity createrInfo,List<FamilyMsgHasTUser> familyMsgHasTUserList,List<FamilyMsgExtData> familyMsgExtDataList
			,List<FamilyMsgPic> familyMsgPicList,List<CommentsEntity> preCommentsEntityList,Integer commentTotalCount,Boolean isSupported, Integer totalSupportCount){
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("id", familyMsg.getId());
		resMap.put("content", familyMsg.getContent());
		try {
			resMap.put("createTime",  RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(familyMsg.getCreateTime()),nowTimeLong));
		} catch (Exception e) {
			resMap.put("createTime","");
		}
		if(familyMsgHasTUserList!=null&&familyMsgHasTUserList.size()>0){//艾特的用户列表
			List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
			for(FamilyMsgHasTUser tmpUser:familyMsgHasTUserList){
				UserSimpleEntity userSimpleEntity = new UserSimpleEntity();
				userSimpleEntity.setImgprofile(tmpUser.getImgprofile());
				userSimpleEntity.setNickName(tmpUser.getNickName());
				userSimpleEntity.setRealName(tmpUser.getRealName());
				userSimpleEntity.setSex(tmpUser.getSex()==null?"":tmpUser.getSex()+"");
				userSimpleEntity.setSignature(tmpUser.getSignature());
				userSimpleEntity.setHuaId(tmpUser.getHuaId());
				Map<String,Object> tmpUserMap = commEntityConvertService.baseUser2Map(userSimpleEntity);
				resList.add(tmpUserMap);
			}
			resMap.put("notifyUserList", resList);
		}
		if(familyMsgExtDataList!=null&&familyMsgExtDataList.size()>0){//拓展信息
			FamilyMsgExtData defaultData = familyMsgExtDataList.get(0);
			Integer dataType = defaultData.getDataType();
			resMap.put("extDataType", dataType);
			List<Map<String,Object>> extDataList = new ArrayList<Map<String,Object>>();
			if(FamilyMsgDict.FamilyMsgExtData_type.FamilyBuyCar.compareTo(dataType)==0){
				Map<String,Object> buyCarMap = new HashMap<String, Object>();
				buyCarMap.put("count", familyMsgExtDataList.size());
				buyCarMap.put("name", "家庭购物车");
				buyCarMap.put("unitName", "件商品");
				String productPicBasePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);//产品图片信息根路径
				String picBase = StringUtils.isEmpty(defaultData.getEpPicBase())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(productPicBasePath+defaultData.getEpPicBase(),"");
				buyCarMap.put("picUrl", picBase);
				extDataList.add(buyCarMap);
			}else if(FamilyMsgDict.FamilyMsgExtData_type.TodayCookBook.compareTo(dataType)==0){
                Map<String,Object> todayCookBookMap = new HashMap<String, Object>();
                todayCookBookMap.put("count", familyMsgExtDataList.size());
                todayCookBookMap.put("name", "家庭菜单");
                todayCookBookMap.put("unitName", "道菜");
                todayCookBookMap.put("collectTimeLong", defaultData.getKcbCollectTime()==null?"":DateUtil.timeToLong(defaultData.getKcbCollectTime()));
                todayCookBookMap.put("collectUserId", defaultData.getKcbCollectUser());
                String cookbookPicBasePath = kitchenCookbookPicParamParser.parseParamValue();
                String picUrl = StringUtils.isEmpty(defaultData.getKcbPicUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(cookbookPicBasePath + defaultData.getKcbPicUrl(),"");
                todayCookBookMap.put("picUrl", picUrl);
                boolean isToday = false;
                if(defaultData.getKcbCollectTime()!=null&&DateUtil.isSameDayOfMillis(DateUtil.timeToLong(defaultData.getKcbCollectTime()), nowTimeLong)){//菜谱收藏日期是否为当天
                    isToday = true;
                }
                todayCookBookMap.put("isToday", isToday);
                extDataList.add(todayCookBookMap);
			}else if(FamilyMsgDict.FamilyMsgExtData_type.TodayCookBookNew.compareTo(dataType)==0){
                List<BigInteger> cookbookIds = new ArrayList<BigInteger>();
                for (FamilyMsgExtData fme : familyMsgExtDataList) {
                    cookbookIds.add(fme.getDataId());
                }
                List<KitchenCookbookCollectEntity> kccs= kitchenDao.selectKitchenCookbookCollectListByIds(cookbookIds, userId, familyMsg.getCreateTime());
                Set<BigInteger> tmpSet = new HashSet<BigInteger>();
                for (KitchenCookbookCollectEntity kcc : kccs){
                    if (tmpSet.add(kcc.gettKitchenCookbookFId())) {
                        extDataList.add(commonKitchenService.cookbookCollect2Map(kcc, null));
                    } else {
                    }
                }
            }else if(FamilyMsgDict.FamilyMsgExtData_type.Discount.compareTo(dataType)==0){
				Map<String,Object> tmpPrizeRecordMap = new HashMap<String, Object>();
				tmpPrizeRecordMap.put("discount", defaultData.getPrDiscount());
				tmpPrizeRecordMap.put("name", "家庭抽奖");
				tmpPrizeRecordMap.put("unitName", "折");
				tmpPrizeRecordMap.put("prizeTimeLong", defaultData.getPrPrizeTime()==null?"":DateUtil.timeToLong(defaultData.getPrPrizeTime()));
				tmpPrizeRecordMap.put("picUrl", fileServerService.getAccessUrl(sysParamManager.getSysParaValue(SysParamKey.Family_Discount_Default_PicUrl),""));
				extDataList.add(tmpPrizeRecordMap);
			}else{
				for(FamilyMsgExtData familyMsgExtData:familyMsgExtDataList){
					if(FamilyMsgDict.FamilyMsgExtData_type.SignalProduct.compareTo(dataType)==0){
						EbuyProduct ebuyProduct = new EbuyProduct();
						ebuyProduct.setId(familyMsgExtData.getDataId());//商品Id
						ebuyProduct.setDesc(familyMsgExtData.getEpDesc());
						ebuyProduct.setName(familyMsgExtData.getEpName());
						ebuyProduct.setNameMini(familyMsgExtData.getEpNameMini());
						ebuyProduct.setPicBase(familyMsgExtData.getEpPicBase());
						ebuyProduct.setPicBaseMini(familyMsgExtData.getEpPicBaseMini());
						ebuyProduct.setPointType(familyMsgExtData.getEpPointType());
						ebuyProduct.setPrice(familyMsgExtData.getEpPrice());
						ebuyProduct.setPriceDiscount(familyMsgExtData.getEpPriceDiscount());
						ebuyProduct.setPriceDiscountPoint(familyMsgExtData.getEpPriceDiscountPoint());
						ebuyProduct.setPricePoint(familyMsgExtData.getEpPricePoint());
						ebuyProduct.setPriceUnit(familyMsgExtData.getEpPriceUnit());
						ebuyProduct.setSpecialProductType(familyMsgExtData.getEpSpecialProductType());
						EbuyProductWithCurrProductSpec ebuyProductWithCurrProductSpec = new EbuyProductWithCurrProductSpec(ebuyProduct, null);
						Map<String,Object> productMap = commEntityConvertService.productInfo2Map(ebuyProductWithCurrProductSpec);
						extDataList.add(productMap);
					}else if(FamilyMsgDict.FamilyMsgExtData_type.SignalCookBook.compareTo(dataType)==0){
						KitchenCookbook kitchenCookbook = new KitchenCookbook();
						kitchenCookbook.setId(familyMsgExtData.getDataId());//菜谱Id
						kitchenCookbook.setCookStep(familyMsgExtData.getKcbCookStep());
						kitchenCookbook.setCookTech(familyMsgExtData.getKcbCookTech());
						kitchenCookbook.setCookTime(familyMsgExtData.getKcbCookTime());
						kitchenCookbook.setDesc(familyMsgExtData.getKcbDesc());
						kitchenCookbook.setEatWeight(familyMsgExtData.getKcbEatWeight());
						kitchenCookbook.setName(familyMsgExtData.getKcbName());
						kitchenCookbook.setPicUrl(familyMsgExtData.getKcbPicUrl());
						kitchenCookbook.setTaste(familyMsgExtData.getKcbTaste());
						kitchenCookbook.setTips(familyMsgExtData.getKcbTips());
						Map<String,Object> kitchenCookbookMap = commEntityConvertService.cookbookInfo2Map(kitchenCookbook);
						extDataList.add(kitchenCookbookMap);
					}/*else if(FamilyMsgDict.FamilyMsgExtData_type.Discount.compareTo(dataType)==0){
						PrizeRecord prizeRecord = new PrizeRecord();
						prizeRecord.setDiscount(familyMsgExtData.getPrDiscount());
						prizeRecord.setEndTime(familyMsgExtData.getPrEndTime());
						prizeRecord.setPrizeTime(familyMsgExtData.getPrPrizeTime());
						prizeRecord.settUserHasTRoomFId(familyMsgExtData.getPrUserRoomId());
						Map<String,Object> prizeRecordMap = commEntityConvertService.prizeRecord2Map(prizeRecord, null, null);
						extDataList.add(prizeRecordMap);
					}*/
				}
			}
			resMap.put("extDataList", extDataList);
		}else{
			resMap.put("extDataType", FamilyMsgDict.FamilyMsgExtData_type.None);
		}
		if(createrInfo!=null){
			resMap.put("createrInfo", commEntityConvertService.baseUser2Map(createrInfo));
		}
		if(familyMsgPicList!=null){
			String fmlMsgPicBasePath = sysParamManager.getSysParaValue(SysParamKey.FAMILY_MSG_LIST_PIC_BASEPATH);
			List<String> picList = new ArrayList<String>();
			for(FamilyMsgPic tmpPic:familyMsgPicList){
				picList.add(StringUtils.isEmpty(tmpPic.getUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(fmlMsgPicBasePath+tmpPic.getUrl(),tmpPic));
			}
			resMap.put("picList", picList);
		}
		if(preCommentsEntityList!=null){//前几个评论信息
			List<Map<String,Object>> tmpList = new ArrayList<Map<String,Object>>();
			for(CommentsEntity tmpComment:preCommentsEntityList){
				Map<String,Object> tmpMap = commEntityConvertService.comments2Map(tmpComment,tmpComment.getUserGroupBuilding(),tmpComment.getUser(), tmpComment.getNoticeUserList(), tmpComment.getCommentsLabelList(),tmpComment.getCommentsHasTCommentsPointsEntityList(),tmpComment.getAvgTotalStarLevel());
				tmpList.add(tmpMap);
			}
			resMap.put("extPreCommentsList", tmpList);
		}
		if(commentTotalCount!=null){resMap.put("extCommentTotalCount", commentTotalCount);}
		if(isSupported!=null){resMap.put("extIsSupport", isSupported);}
		if(totalSupportCount!=null){resMap.put("extTotalSupportCount", totalSupportCount);}
		return resMap;
	}
	
}
