package com.cnfantasia.server.ms.communitySupplyCompany.web;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.communitySupplyCompanyPic.entity.CommunitySupplyCompanyPic;
import com.cnfantasia.server.domainbase.communitySupplyCompanyPic.service.ICommunitySupplyCompanyPicBaseService;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.communitySupplyContect.service.ICommunitySupplyContectBaseService;
import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;
import com.cnfantasia.server.domainbase.communitySupplyPic.service.ICommunitySupplyPicBaseService;
import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;
import com.cnfantasia.server.domainbase.communitySupplyTmp.service.ICommunitySupplyTmpBaseService;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.omsUser.service.IOmsUserBaseService;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;
import com.cnfantasia.server.ms.communitySupply.constant.CommunitySupplyConstant;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyTmpEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyTmpViewEntity;
import com.cnfantasia.server.ms.communitySupply.service.ICommunitySupplyService;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyEditEntity;
import com.cnfantasia.server.ms.communitySupplyCompany.service.ICommunitySupplyCompanyService;
import com.cnfantasia.server.ms.communitySupplyTmp.constant.CommunitySupplyTmpConstant;
import com.cnfantasia.server.ms.communitySupplyTmp.service.ICommunitySupplyTmpService;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.omsPermiRole.service.IOmsPermiRoleService;
import com.cnfantasia.server.ms.provinceCityBlock.service.IProvinceCityBlockService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtil;

@Controller
@RequestMapping("/communitySupplyCompany")
public class CommunitySupplyCompanyController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	IProvinceCityBlockService provinceCityBlockService;
	
	@Resource
	ICommunitySupplyService communitySupplyService;
	
	@Resource
	IGroupBuildingService msGroupBuildingService;
	
	@Resource
	ICommunitySupplyTmpService communitySupplyTmpService;
	
	@Resource
	ICommunitySupplyPicBaseService communitySupplyPicBaseService;
	
	@Resource
	ICommunitySupplyCompanyService communitySupplyCompanyService;
	
	@Resource
	ICommunitySupplyCompanyPicBaseService communitySupplyCompanyPicBaseService;
	@Resource
	IOmsPermiRoleService omsPermiRoleService;
	@Resource
	IUserBaseService userBaseService;
	@Resource
	IOmsUserBaseService omsUserBaseService;
	@Resource
	ICommunitySupplyTmpBaseService communitySupplyTmpBaseService;
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
	@RequestMapping("/list.html")
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
		modelAndView.setViewName("/communitySupplyCompany/communitySupplyCompanyList");
		return modelAndView;
	}
	
	/**
	 * 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		String csName = request.getParameter("csName");
		String cstId = request.getParameter("cstId");
		String auditStatus = ParamUtils.getString(request, "auditStatus", null);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		if ("all".equals(cstId)) {//查找全部时，不需要这个条件
			paramMap.put("cstId", null);
		}else{
			paramMap.put("cstId", cstId);
		}
		paramMap.put("csName", csName);
		paramMap.put("auditStatus", auditStatus);
		handleListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupplyCompany/communitySupplyCompanyList");
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
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		int resultSize = communitySupplyCompanyService.getCommunitySupplyEditEntityList_byUserId_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<CommunitySupplyEditEntity> searchRestList = communitySupplyCompanyService.getCommunitySupplyEditEntityList_byUserId_forPage(curPageIndex, pageSize, paramMap);
		request.setAttribute("resList", searchRestList);
	}
	/**
	 * 物业管理 新增商家
	 * @author huangzj 2015-04-23
	 * @param request
	 * @return
	 */
	@RequestMapping("/initEdit.html")
	public ModelAndView initEdit(HttpServletRequest request) {
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
		
		this.initEditCommunitySupply(request, false);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupplyCompany/communitySupplyCompanyEdit");
		return modelAndView;
	}
	/**
	 * 物业管理 新增商家
	 * @author huangzj 2015-04-23
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveEdit.html")
	public ModelAndView saveEdit(HttpServletRequest request) throws IOException{
		String supplyId = request.getParameter("supplyId").trim();//临时商家ID
		String supplyTmpId = request.getParameter("supplyTmpId");//临时商家ID
		
		String supplyTmpType = request.getParameter("supplyTmpType").trim();//商家类别
		String supplyTmpName = request.getParameter("supplyTmpName").trim(); //商家名称
		String[] contectPhones = request.getParameterValues("newContectPhone");//店铺电话
		List<String> contactImgs = new ArrayList<String>();//商家图片

		String addressDetail = request.getParameter("addressDetail");//小区详细地址
		String addressBlockId = request.getParameter("block_id");//小区所属哪个区
		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			String filePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.CommunitySupplyPic;
			//String filePath = "D:/Program Files/development/workspace_comm/apache-tomcat-6.0.39-8080/webapps/uploadImages/propertyCompanyImg";//本地测试路径

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
		String delImgIds = request.getParameter("delImgIds");//删除的图片
		String delContectIds = request.getParameter("delContectIds");//删除的电话
		//数据交互
		this.communitySupplyCompanyService.saveCommunitySupplyEditTmp(supplyTmpId, supplyTmpType, 
				supplyId, supplyTmpName, contectPhones, addressBlockId, addressDetail, contactImgs, delImgIds, delContectIds);
		request.setAttribute(JSPConstants.OprtResult, "保存成功");
		request.setAttribute(JSPConstants.ToURL, "../communitySupplyCompany/list.html");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 查看明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/view.html")
	public ModelAndView view(HttpServletRequest request) {
		//商家店铺明细
		this.initView(request, false);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupplyCompany/communitySupplyCompanyView");
		return modelAndView;
	}
	
	
	/**
	 * 店铺编辑审核列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditList.html")
	public ModelAndView auditList(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		handleCSTempListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}

		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupplyCompany/communitySupplyCompanyAuditList");
		return modelAndView;
	}
	
	/**
	 * 店铺编辑审核 查找
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditSearch.html")
	public ModelAndView auditSearch(HttpServletRequest request) {
		String auditStatus = request.getParameter("auditStatus");
		String cstName = ParamUtils.getString(request, "cstName", null);
		String cstId = request.getParameter("cstId");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("auditStatus", auditStatus);
		paramMap.put("cstName", cstName);
		paramMap.put("cstId", cstId);

		handleCSTempListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupplyCompany/communitySupplyCompanyAuditList");
		return modelAndView;
	}
	
	/**
	 * 统一处理 （店铺编辑审核列表） List和Search请求
	 * @param request
	 * @param paramMap
	 */
	private void handleCSTempListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("auditType", CommunitySupplyTmpConstant.CommunitySupplyTmp_Add_Type.Add_Type_Edit_CS);//只查询编辑的数据
		int resultSize = communitySupplyService.getCommunitySupplyTmpList_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<CommunitySupplyTmpEntity> searchRestList = communitySupplyService.getCommunitySupplyTmpList_forPage(curPageIndex, pageSize, paramMap);
		request.setAttribute("resList", searchRestList);
	}
	
	/**
	 * 查看审核明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditView.html")
	public ModelAndView auditView(HttpServletRequest request) {
		//商家店铺明细
		this.initView(request, true);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupplyCompany/communitySupplyCompanyAuditView");
		return modelAndView;
	}
	
	/**
	 * 商家编辑审核-初始化
	 * @author huangzj 2015-04-23
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAudit.html")
	public ModelAndView initAudit(HttpServletRequest request) {
		//商家店铺明细
		this.initEditCommunitySupply(request, true);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupplyCompany/communitySupplyCompanyAuditEdit");
		return modelAndView;
	}
	
	/**
	 * 商家编辑 审核保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditEdit.html")
	public ModelAndView auditEdit(HttpServletRequest request) {
		String tmpId = request.getParameter("id");
		String supplyId = request.getParameter("supplyId");
		String auditResult = request.getParameter("auditResult").trim();

		if ("notpass".equals(auditResult)) {//审核不通过
			CommunitySupplyTmp communitySupplyTmp = new CommunitySupplyTmp();
			communitySupplyTmp.setId(new BigInteger(tmpId));
			communitySupplyTmp.setAuditTime(DateUtil.formatSecond.format(new Date()));
			communitySupplyTmp.setAuditType(CommunitySupplyConstant.CommunitySupplyTmp_Audit_Type.EDIT_AUDIT);
			String notPassReason = request.getParameter("notPassReason").trim();// 未通过原因
			communitySupplyTmp.setAuditDesc(notPassReason);
			communitySupplyTmp.setDelContectIds(null);
			communitySupplyTmp.setDelImgIds(null);
			communitySupplyTmp.setAuditStatus(CommunitySupplyConstant.CommunitySupplyTmp_Audit_Status.AUDIT_NOT_PASS);
			String mobile = request.getParameter("mobile");
			//CnfantasiaCommUtil.sendSMS(mobile, MessageFormat.format(CommunitySupplyConstant.AddNew_AuditNotPassSMSInfo, notPassReason));
			if(communitySupplyTmpBaseService.updateCommunitySupplyTmp(communitySupplyTmp)>0){
				Map<String, Object> paramMap = new HashMap<String, Object>();
				//删除添加的图片
				paramMap.put("tCommunitySupplyFId", tmpId);
				List<CommunitySupplyPic> picURLs = communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap);
				if(null!=picURLs && picURLs.size()>0){
					for (CommunitySupplyPic communitySupplyPic : picURLs) {
						CnfantasiaCommUtil.deleteStandard(communitySupplyPic);
					}
					communitySupplyPicBaseService.updateCommunitySupplyPicBatch(picURLs);
				}
				//删除添加的店铺联系电话
				List<CommunitySupplyContect> contects = communitySupplyContectBaseService.getCommunitySupplyContectByCondition(paramMap);
				if(null!=contects && contects.size()>0){
					for (CommunitySupplyContect contect : contects) {
						CnfantasiaCommUtil.deleteStandard(contect);
					}
					communitySupplyContectBaseService.updateCommunitySupplyContectBatch(contects);
				}
			}
		} else if ("pass".equals(auditResult)) {
			//数据交互
			this.communitySupplyCompanyService.auditCSEditPass(tmpId, supplyId);
		}
		
		request.setAttribute(JSPConstants.OprtResult, "审批结果保存成功");
		request.setAttribute(JSPConstants.ToURL, "../communitySupplyCompany/auditList.html");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 查看编辑商家对象明细方法重用
	 * */
	private void initView(HttpServletRequest request, boolean isAudit) {
		//商家店铺明细
		String id = request.getParameter("id");
		CommunitySupplyEditEntity entity = this.communitySupplyCompanyService.getCommunitySupplyEditEntity_byId(new BigInteger(id));
		request.setAttribute("entity", entity);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String tmpId = request.getParameter("tmpId");
		paramMap.put("tCommunitySupplyFId", id);
		List<CommunitySupplyPic> picURL = communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap);
		//展示的店铺图片
		if(StringUtils.isNotEmpty(tmpId)){
	        CommunitySupplyTmpViewEntity tmp = communitySupplyService .getTmpDetailById(tmpId);
	        paramMap.clear();
	        paramMap.put( "tCommunitySupplyTmpFId" , tmp.getId());
	        picURL.addAll(communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap));
	        if(StringUtils. isNotEmpty(tmp.getDelImgIds())){ //过滤删除的图片
	           List<CommunitySupplyPic> picURLs = new ArrayList<CommunitySupplyPic>();
	           String[] delImgIds = tmp.getDelImgIds().split("," );
	           for (CommunitySupplyPic pic : picURL) {
	                 boolean isDel = false;
	                 for (String string : delImgIds) {
	                       if(CnfantasiaCommUtil. convert2BigInteger(string).equals(pic.getId())){
	                            isDel = true;
	                      }
	                }
	                 if(isDel== false){
	                      picURLs.add(pic);
	                }
	          }
	          request.setAttribute( "picURL", picURLs);
	        } else{
	            request.setAttribute( "picURL", picURL);
	        }
	        //联系方式
	        paramMap.clear();
	        paramMap.put( "tCommunitySupplyTmpFId" , tmp.getId());
	        entity.setCsContects(communitySupplyContectBaseService.getCommunitySupplyContectByCondition(paramMap));
		}else{
			request.setAttribute( "picURL", picURL);
		}
        if(isAudit){
			//店铺证件
			paramMap.clear();
			paramMap.put("tCommunitySupplyCompanyFId", entity.getCompanyId());
			List<CommunitySupplyCompanyPic> comPicURL = communitySupplyCompanyPicBaseService.getCommunitySupplyCompanyPicByCondition(paramMap);
			request.setAttribute("comPicURL", comPicURL);
        }
	}
	
	/**
	 * 初始化编辑或者审核商家店铺重用
	 * */
	private void initEditCommunitySupply(HttpServletRequest request, boolean isAudit) {
		//商家店铺明细
		String id = request.getParameter("id");
		CommunitySupplyEditEntity entity = this.communitySupplyCompanyService.getCommunitySupplyEditEntity_byId(new BigInteger(id));
		request.setAttribute("entity", entity);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCommunitySupplyFId", id);
		List<CommunitySupplyPic> picURL = communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap);
		request.setAttribute("picURL", picURL);
		//展示的店铺图片
		if(isAudit){//审核的才需要添加新图片
			String tmpId = request.getParameter("tmpId");
	        CommunitySupplyTmpViewEntity tmp = communitySupplyService .getTmpDetailById(tmpId);
	        paramMap.clear();
	        paramMap.put( "tCommunitySupplyTmpFId" , tmp.getId());
	        picURL.addAll(communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap));
	        if(StringUtils. isNotEmpty(tmp.getDelImgIds())){ //过滤删除的图片
	        	List<CommunitySupplyPic> picURLs = new ArrayList<CommunitySupplyPic>();
	        	String[] delImgIds = tmp.getDelImgIds().split("," );
	        	for (CommunitySupplyPic pic : picURL) {
	        		boolean isDel = false;
	        		for (String string : delImgIds) {
	        			if(CnfantasiaCommUtil. convert2BigInteger(string).equals(pic.getId())){
	        				isDel = true;
	        			}
	        		}
	        		if(isDel== false){
	        			picURLs.add(pic);
	        		}
	        	}
	        	request.setAttribute( "picURL", picURLs);
	        }else{
	        	request.setAttribute( "picURL", picURL);
	        }
	        //联系方式
	        paramMap.clear();
	        paramMap.put( "tCommunitySupplyTmpFId" , tmp.getId());
	        entity.setCsContects(communitySupplyContectBaseService.getCommunitySupplyContectByCondition(paramMap));
		}else{
        	request.setAttribute( "picURL", picURL);
        }
        if(isAudit){
			//店铺证件
			paramMap.clear();
			paramMap.put("tCommunitySupplyCompanyFId", entity.getCompanyId());
			List<CommunitySupplyCompanyPic> comPicURL = communitySupplyCompanyPicBaseService.getCommunitySupplyCompanyPicByCondition(paramMap);
			request.setAttribute("comPicURL", comPicURL);
        }
	}
}
