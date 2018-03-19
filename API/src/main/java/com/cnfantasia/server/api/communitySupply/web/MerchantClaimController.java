/**   
* Filename:    MerchantClaimController.java   
* @version:    1.0  
* Create at:   2015年1月28日 上午2:04:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.communitySupply.service.IMerchantClaimService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;

/**
 * Filename:    MerchantClaimController.java
 * @version:    1.0.0
 * Create at:   2015年1月28日 上午2:04:38
 * Description: 商家认领
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月28日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/merchantClaim")
public class MerchantClaimController extends BaseController{
	
	private IMerchantClaimService merchantClaimService;
	public void setMerchantClaimService(IMerchantClaimService merchantClaimService) {
		this.merchantClaimService = merchantClaimService;
	}

	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}

	/**
	 * 查询可新增的商家类别列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryCanAddMerchantTypeList.json")
	@ResponseBody
	public JsonResponse qryCanAddMerchantTypeList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		UserContext.getOperIdMustExistBigInteger();//需要登录
		//2.交互
		List<CommunitySupplyType> communitySupplyTypeList = merchantClaimService.getCommunitySupplyTypeCanAddList();
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(communitySupplyTypeList!=null&&communitySupplyTypeList.size()>0){
			for(CommunitySupplyType tmpType:communitySupplyTypeList){
				Map<String,Object> tmpMap = commEntityConvertService.communitySupplyType2Map(tmpType, null);
				resList.add(tmpMap);
			}
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	/**
	 * 新增商家
	 * @param request
	 * @return
	 */
	@RequestMapping("/addMerchant.json")
	@ResponseBody
	public JsonResponse addMerchant(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger supplyTypeId = null;//店铺类型
		{
			String supplyTypeIdStr = request.getParameter("supplyTypeId");
			supplyTypeId = supplyTypeIdStr==null?null:new BigInteger(supplyTypeIdStr);
			if(supplyTypeId==null){
				throw new BusinessRuntimeException("merchantClaim.addMerchant.supplyTypeId.null");
			}
		}
//		BigInteger groupBuildingId = null;//物业小区Id
//		{
//			String groupBuildingIdStr = request.getParameter("groupBuildingId");
//			groupBuildingId = groupBuildingIdStr==null?null:new BigInteger(groupBuildingIdStr);
//			if(groupBuildingId==null){
//				throw new BusinessRuntimeException("merchantClaim.addMerchant.groupBuildingId.null");
//			}
//		}
		BigInteger addressBlockId = null;//行政小区Id
		{
			String addressBlockIdStr = request.getParameter("addressBlockId");
			addressBlockId = addressBlockIdStr==null?null:new BigInteger(addressBlockIdStr);
			if(addressBlockId==null){
				throw new BusinessRuntimeException("merchantClaim.addMerchant.addressBlockId.null");
			}
		}
		String supplyName = request.getParameter("supplyName");//店铺名称
		String addressDetail = request.getParameter("addressDetail");//详细地址描述
		String supplyContectInfo = request.getParameter("supplyContectInfo");//店铺电话
		List<RequestFileEntity> descriptPicList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "descriptPic");//店铺图片描述信息
		String merchantPhone = request.getParameter("merchantPhone");//商家手机号
		List<RequestFileEntity> merchantLicensePicList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "merchantLicensePic");//商家认证图片信息
		//用户
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		merchantClaimService.addMerchant(userId,supplyTypeId,supplyName,addressBlockId,addressDetail,supplyContectInfo,descriptPicList,merchantPhone,merchantLicensePicList);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 认领我的店铺
	 * @param request
	 * @return
	 */
	@RequestMapping("/doClaimMerchant.json")
	@ResponseBody
	public JsonResponse doClaimMerchant(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger groupBuildSupplyRelaId = null;//商家Id
		{
			String groupBuildSupplyRelaIdStr = request.getParameter("groupBuildSupplyRelaId");
			groupBuildSupplyRelaId = groupBuildSupplyRelaIdStr==null?null:new BigInteger(groupBuildSupplyRelaIdStr);
			if(groupBuildSupplyRelaId==null){
				throw new BusinessRuntimeException("merchantClaim.doClaimMerchant.groupBuildSupplyRelaId.null");
			}
		}
		String merchantPhone = request.getParameter("merchantPhone");//商家手机号
		List<RequestFileEntity> merchantLicensePicList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "merchantLicensePic");//商家认证图片信息
		//用户
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		merchantClaimService.doClaimMerchant(userId,groupBuildSupplyRelaId,merchantPhone,merchantLicensePicList);
		//3.结果处理
		return jsonResponse;
	}
	
}
