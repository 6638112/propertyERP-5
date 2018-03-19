package com.cnfantasia.server.ms.communitySupplyTmp.web;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.communitySupplyContect.service.ICommunitySupplyContectBaseService;
import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;
import com.cnfantasia.server.domainbase.communitySupplyPic.service.ICommunitySupplyPicBaseService;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.ms.communitySupply.service.ICommunitySupplyService;
import com.cnfantasia.server.ms.communitySupplyTmp.entity.CommunitySupplyTmpEntity;
import com.cnfantasia.server.ms.communitySupplyTmp.service.ICommunitySupplyTmpService;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleViewEntity;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.provinceCityBlock.service.IProvinceCityBlockService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;

@Controller
@RequestMapping("/communitySupplyTmp")
public class CommunitySupplyTmpController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	IGroupBuildingService msGroupBuildingService;
	
	@Resource
	IProvinceCityBlockService provinceCityBlockService;
	
	@Resource
	ICommunitySupplyService communitySupplyService;
	
	@Resource
	ICommunitySupplyTmpService communitySupplyTmpService;
	
	@Resource
	ICommunitySupplyPicBaseService communitySupplyPicBaseService;
	
	@Resource
	ICommunitySupplyContectBaseService communitySupplyContectBaseService;
	/**
	 * 商家类别
	 */
	private List<CommunitySupplyType> communitySupplyTypeList;
	
	/**
	 * 列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/listTmp.html")
	public ModelAndView list(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		handleListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupplyTmp/communitySupplyTmpList");
		return modelAndView;
	}
	
	/**
	 * 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchTmp.html")
	public ModelAndView search(HttpServletRequest request) {
		String supplyType = request.getParameter("supplyType");
		String csName = request.getParameter("csName");
		String gbName = request.getParameter("gbName");
		String auditStatus = request.getParameter("auditStatus");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		if (!"all".equals(supplyType)) {//查找全部时，不需要这个条件
			paramMap.put("supplyType", supplyType);
		}
		paramMap.put("supplyName", csName);
		paramMap.put("gbName", gbName);
		paramMap.put("auditStatus", auditStatus);
		handleListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupplyTmp/communitySupplyTmpList");
		return modelAndView;
	}
	
	/**
	 * 统一处理List和Search请求
	 * 
	 * @param request
	 * @param paramMap
	 *            请求参数
	 */
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		int resultSize = communitySupplyTmpService.getCommunitySupplyTmp_byUserId_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<CommunitySupplyTmpEntity> searchRestList = communitySupplyTmpService.getCommunitySupplyTmpList_byUserId_forPage(curPageIndex, pageSize, paramMap);
		request.setAttribute("resList", searchRestList);
	}
	/**
	 * 物业管理 新增商家
	 * @author huangzj 2015-04-23
	 * @param request
	 * @return
	 */
	@RequestMapping("/initSupplyTmp.html")
	public ModelAndView initSupply(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//商铺类别
		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);
		//地址级联
		request.setAttribute("pcbList", provinceCityBlockService.getProvinceWithCityBlockList());
		//用户有权限的小区
		paramMap.clear();
		paramMap.put("adminId", UserContext.getCurrUser().getId());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		List<GroupBuildingSimpleViewEntity> gbList = this.msGroupBuildingService.selectGroupBuildingByOmsUser(paramMap);
		request.setAttribute("gbList", gbList);
		
		String supplyId = request.getParameter("supplyId");
		if(StringUtils.isNotEmpty(supplyId)){//进入编辑页面
			CommunitySupplyTmpEntity entity = this.communitySupplyTmpService.getCommunitySupplyTmp_byId(new BigInteger(supplyId));
			request.setAttribute("entity", entity);
			//大图
			paramMap.clear();
			paramMap.put("tCommunitySupplyTmpFId", supplyId);
			List<CommunitySupplyPic> bigPicURL = communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap);
			request.setAttribute("bigPicURL", bigPicURL);
			//联系方式
			paramMap.clear();
		    paramMap.put( "tCommunitySupplyTmpFId" , supplyId);
		    List<CommunitySupplyContect> contects = communitySupplyContectBaseService.getCommunitySupplyContectByCondition(paramMap);
		    request.setAttribute("contects", contects);
			//服务小区
			paramMap.clear();
			paramMap.put("tmpId", supplyId);
			List<GroupBuildingSimpleViewEntity> serviceGbList = this.msGroupBuildingService.selectGroupBuildingBySupply(paramMap);
			if(gbList!=null && gbList.size()>0){
				for (GroupBuildingSimpleViewEntity gbse : gbList) {
					for (GroupBuildingSimpleViewEntity serviceGb : serviceGbList) {
						if(gbse.getId().equals(serviceGb.getId())){
							gbse.setIsService("1");
						}
					}
				}
			}
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupplyTmp/communitySupplyTmpAdd");
		return modelAndView;
	}
	/**
	 * 物业管理 新增商家
	 * @author huangzj 2015-04-23
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveSupplyTmp.html")
	public ModelAndView saveSupply(HttpServletRequest request) throws IOException{
		String supplyTmpId = request.getParameter("supplyTmpId").trim();//临时商家ID
		String miniPicId = request.getParameter("miniPicId");//略缩图ID
		String bigPicId = request.getParameter("bigPicId");//大图ID
		
		String supplyTmpType = request.getParameter("supplyTmpType").trim();//商家类别
		String supplyTmpName = request.getParameter("supplyTmpName").trim(); //商家名称
		String companyName = request.getParameter("contactName");//联系人
		String[] contectPhones = request.getParameterValues("newContectPhone");//店铺电话
		String companyPhone = request.getParameter("companyPhone");//店主手机号
		List<String> contactImgs = new ArrayList<String>();//商家图片
		String addressDetail = request.getParameter("addressDetail");//小区详细地址
		String addressBlockId = request.getParameter("block_id");//小区所属哪个区
		
		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			String filePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.CommunitySupplyPic;
			//String filePath = "D:/Program Files/development/workspace_comm/apache-tomcat-6.0.39-8080/webapps/uploadImages/propertyCompanyImg";
			
			//商家大图
			//MultipartFile uploadImageFile = multipartRequest.getFiles(name).getFile("imageFile");
			List<MultipartFile> uploadImageFiles = multipartRequest.getFiles("imageFile");
			if(null!=uploadImageFiles && uploadImageFiles.size()>0){
				int imgIndex=0;
				for (MultipartFile uploadImageFile : uploadImageFiles) {
					if (uploadImageFile != null && StringUtils.isNotEmpty(uploadImageFile.getOriginalFilename())) {//有上传图片 
						int indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
						String fileNameC = (System.currentTimeMillis()+imgIndex) + uploadImageFile.getOriginalFilename().substring(indexOfDot);
						File fileC = new File(filePath + "/" + fileNameC);
						if(!fileC.exists())
							fileC.mkdir();
						contactImgs.add(fileNameC);
						uploadImageFile.transferTo(fileC);
					}
					imgIndex ++;
				}
			}
		}
		String[] serviceGroupBuildingIds = request.getParameterValues("gbId");
		String delImgIds = request.getParameter("delImgIds");//删除的图片
		String delContectIds = request.getParameter("delContectIds");//删除的电话
		//数据交互
		this.communitySupplyTmpService.saveCommunitySupplyTmp(supplyTmpId, supplyTmpType, supplyTmpName, companyName, companyPhone, contectPhones, 
					addressBlockId, addressDetail, serviceGroupBuildingIds, miniPicId, bigPicId, contactImgs, delImgIds, delContectIds);
		request.setAttribute(JSPConstants.OprtResult, "保存成功");
		request.setAttribute(JSPConstants.ToURL, "../communitySupplyTmp/listTmp.html");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 查看明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewTmpDetail.html")
	public ModelAndView viewTmpDetail(HttpServletRequest request) {
		//临时商家
		String id = request.getParameter("id");
		CommunitySupplyTmpEntity entity = this.communitySupplyTmpService.getCommunitySupplyTmp_byId(new BigInteger(id));
		request.setAttribute("entity", entity);
		//图片
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.clear();
		paramMap.put("tCommunitySupplyTmpFId", id);
		List<CommunitySupplyPic> bigPicURL = communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap);
		request.setAttribute("bigPicURL", bigPicURL);
		//联系方式
		paramMap.clear();
	    paramMap.put( "tCommunitySupplyTmpFId" , id);
	    List<CommunitySupplyContect> contects = communitySupplyContectBaseService.getCommunitySupplyContectByCondition(paramMap);
	    request.setAttribute("contects", contects);
		//服务小区
		paramMap.clear();
		paramMap.put("tmpId", id);
		List<GroupBuildingSimpleViewEntity> gbList = this.msGroupBuildingService.selectGroupBuildingBySupply(paramMap);
		request.setAttribute("gbList", gbList);
		request.setAttribute("resultSize", gbList.size());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupplyTmp/communitySupplyTmpView");
		return modelAndView;
	}
	
	/**
	 * 删除明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/delTmpDetail.html")
	@ResponseBody
	public String delTmpDetail(String id) {
		BigInteger supplyTmpId = new BigInteger(id);
		if(communitySupplyTmpService.delCommunitySupplyTmp(supplyTmpId)>0){
			return "删除成功";
		}else{
			return "删除失败";
		}
	}
	
	/**
	 * 删除明细
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/validateGBHasCS.html")
	@ResponseBody
	public String validateGBHasCS(HttpServletRequest request,HttpServletResponse response){
		try {
			String gbName = "";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String gbIds = request.getParameter("gbId");
			String supplyName = request.getParameter("supplyName");
			paramMap.put("gbIds", gbIds);
			paramMap.put("supplyName", supplyName);
			List<String> gbNames = this.communitySupplyTmpService.getGBHasCSIsExists(paramMap,false);
			if(null!=gbNames && gbNames.size()>0){//第一次在商家找
				gbName = gbNames.toString();
			}else{//第二次在临时申请中找
				gbNames = this.communitySupplyTmpService.getGBHasCSIsExists(paramMap,true);
				if(null!=gbNames && gbNames.size()>0){
					gbName = gbNames.toString();
				}
			}
			return gbName;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}  
		return "";
	}
}
