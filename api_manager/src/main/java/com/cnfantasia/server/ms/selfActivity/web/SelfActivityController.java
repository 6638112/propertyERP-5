package com.cnfantasia.server.ms.selfActivity.web;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.selfActivity.dict.SelfActivityDict;
import com.cnfantasia.server.api.selfActivity.entity.SeftActivityWithDetail;
import com.cnfantasia.server.api.selfActivity.entity.SelfActivity4List;
import com.cnfantasia.server.api.selfActivity.service.SelfActivityService;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domainbase.dredgeProduct.service.IDredgeProductBaseService;
import com.cnfantasia.server.domainbase.ebuyProduct.service.IEbuyProductBaseService;
import com.cnfantasia.server.domainbase.selfActivity.entity.SelfActivity;
import com.cnfantasia.server.domainbase.selfActivity.service.ISelfActivityBaseService;
import com.cnfantasia.server.domainbase.selfActivityDetail.entity.SelfActivityDetail;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.page.PageUtils;

@Controller
@RequestMapping("/selfActivity")
public class SelfActivityController extends BaseController {

	@Resource
	private SelfActivityService selfActivityService;
	@Resource
	private ISelfActivityBaseService selfActivityBaseService;
	@Resource
	IEbuyProductBaseService ebuyProductBaseService;
	@Resource
	IDredgeProductBaseService dredgeProductBaseService;

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) throws ServletException, IOException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", request.getParameter("name"));
		paramMap.put("url", request.getParameter("url"));
		paramMap.put("addUser", request.getParameter("addUser"));
		paramMap.put("addTimeStart", request.getParameter("addTimeStart"));
		paramMap.put("addTimeEnd", request.getParameter("addTimeEnd"));
		int resultSize = selfActivityService.qrySelfActivityListForCount(paramMap);
		PageUtils.addPageInfoToParam(request, paramMap);
		List<SelfActivity4List> resList = selfActivityService.qrySelfActivityListForPage(paramMap);
		request.setAttribute("resList", resList);
		request.setAttribute("resultSize", resultSize);

		return new ModelAndView("/selfActivity/selfActivityList");
	}


	@RequestMapping(value = "/view.html", method = RequestMethod.GET)
    public ModelAndView view(BigInteger id) throws ServletException, IOException {
		SeftActivityWithDetail sa = selfActivityService.qrySelfActivityWithDetaiById(id);
		List<Object> jumpParamNameList = new ArrayList<Object>(sa.getSadList().size());
		for(int i = 0; i < sa.getSadList().size(); i++){
			SelfActivityDetail selfActivityDetail = sa.getSadList().get(i);
			if(selfActivityDetail.getJumpType() == 0){
				jumpParamNameList.add(null);
			}else if(selfActivityDetail.getJumpType() == 1){
				BigInteger productId = new BigInteger(selfActivityDetail.getJumpParam());
				jumpParamNameList.add(ebuyProductBaseService.getEbuyProductBySeqId(productId));
			}else if(selfActivityDetail.getJumpType() == 2){
				BigInteger dredgeProductId = new BigInteger(selfActivityDetail.getJumpParam());
				jumpParamNameList.add(dredgeProductBaseService.getDredgeProductBySeqId(dredgeProductId));
			}else if(selfActivityDetail.getJumpType() == 3){
				jumpParamNameList.add(selfActivityDetail.getJumpParam());
			}
		}
		ModelAndView modelAndView = new ModelAndView("/selfActivity/selfActivityView");
		modelAndView.addObject("sa", sa);
		modelAndView.addObject("paramList", jumpParamNameList);
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(BigInteger id) throws ServletException, IOException {
		SeftActivityWithDetail sa = selfActivityService.qrySelfActivityWithDetaiById(id);
		ModelAndView modelAndView = new ModelAndView("/selfActivity/selfActivityEdit");
		modelAndView.addObject("sad", sa);
		
		List<Object> jumpParamNameList = new ArrayList<Object>(sa.getSadList().size());
		for(int i = 0; i < sa.getSadList().size(); i++){
			SelfActivityDetail selfActivityDetail = sa.getSadList().get(i);
			if(selfActivityDetail.getJumpType() == 0){
				jumpParamNameList.add(null);
			}else if(selfActivityDetail.getJumpType() == 1){
				BigInteger productId = new BigInteger(selfActivityDetail.getJumpParam());
				jumpParamNameList.add(ebuyProductBaseService.getEbuyProductBySeqId(productId));
			}else if(selfActivityDetail.getJumpType() == 2){
				BigInteger dredgeProductId = new BigInteger(selfActivityDetail.getJumpParam());
				jumpParamNameList.add(dredgeProductBaseService.getDredgeProductBySeqId(dredgeProductId));
			}else if(selfActivityDetail.getJumpType() == 3){
				jumpParamNameList.add(selfActivityDetail.getJumpParam());
			}
		}
		modelAndView.addObject("paramList", jumpParamNameList);
		
		return modelAndView;
	}

	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse update(HttpServletRequest request) throws ServletException, IOException {
		JsonResponse json = new JsonResponse();
		BigInteger id = ParamUtils.getBigInteger(request,"id", null);
		if(id==null){
			throw new ValidateRuntimeException("id不能为空！");
		}

		List<String> pics = uploadImage(request, PathConstants.SELF_ACTIVITY_PIC);
		String name = request.getParameter("name");
		String[] sadIds = request.getParameterValues("sadId");
		String[] jumpTypes = request.getParameterValues("jumpType");
		String[] jumpParams = request.getParameterValues("jumpParam");
		List<BigInteger> updImgIdList = ParamUtils.getBigIntegerList(request, "updImgId", null); //更改了图片的Id集
		List<BigInteger> willDeleteIdList = ParamUtils.getBigIntegerList(request, "deleteId", null); //需要删除的Id集

		SelfActivity sa = new SelfActivity();
		sa.setId(id);
		sa.setName(name);

		List<SelfActivityDetail> sadUpdList = new ArrayList<SelfActivityDetail>();
		List<SelfActivityDetail> sadAddNewList = new ArrayList<SelfActivityDetail>();
		int j = 0;
		for (int i = 0; i < jumpTypes.length; i++) {
			if (i < sadIds.length) { //需要update的
				SelfActivityDetail sad = new SelfActivityDetail();
				sad.setId(new BigInteger(sadIds[i]));
				sad.setJumpType(Integer.parseInt(jumpTypes[i]));
				sad.setJumpParam(jumpParams[i]);
				if (updImgIdList.contains(sad.getId()))
					sad.setPicUrl(pics.get(j++));
				sadUpdList.add(sad);
			} else { //新增的
				SelfActivityDetail sad = new SelfActivityDetail();
				sad.settSelfActivityFId(sa.getId());
				sad.setJumpType(Integer.parseInt(jumpTypes[i]));
				sad.setJumpParam(jumpParams[i]);
				sad.setPicUrl(pics.get(j++));
				sadAddNewList.add(sad);
			}
		}

		int effectCount = selfActivityService.upddateActivity(sa, sadUpdList, sadAddNewList, willDeleteIdList);
		json.setMessage(effectCount > 0 ? "更新成功": "更新失败");
		return json;
	}

	@RequestMapping("/addNew.html")
	public String addNew() {
		return "/selfActivity/selfActivityAddNew";
	}

	@RequestMapping(value = "/saveAddNew.json", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse saveAddNew(HttpServletRequest request) throws ServletException, IOException {
		JsonResponse json = new JsonResponse();
		List<String> pics = uploadImage(request, PathConstants.SELF_ACTIVITY_PIC);
		String name = request.getParameter("name");
		String[] jumpTypes = request.getParameterValues("jumpType");
		String[] jumpParams = request.getParameterValues("jumpParam");
		if (DataUtil.isEmpty(pics) || pics.size() != jumpTypes.length || pics.size() != jumpParams.length ) {
			json.setStatus("0001");
			json.setMessage("数据不正确");
			return json;
		}
		selfActivityService.addSelfActivity(name, pics, Arrays.asList(jumpTypes), Arrays.asList(jumpParams));
		return json;
	}


	@RequestMapping("/delete.html")
	public ModelAndView delete(HttpServletRequest request) throws ServletException, IOException {
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		if(id==null){
			throw new ValidateRuntimeException("id不能为空！");
		}

		selfActivityBaseService.deleteSelfActivityLogic(id);

		return new ModelAndView("redirect:/selfActivity/list.html");
	}

	@RequestMapping("/publish.html")
	public ModelAndView publish(HttpServletRequest request) throws ServletException, IOException {
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		if(id==null){
			throw new ValidateRuntimeException("id不能为空！");
		}

		SelfActivity selfActivity = new SelfActivity();
		selfActivity.setId(id);
		selfActivity.setStatus(SelfActivityDict.SelfActivityStatus_Published);
		selfActivityBaseService.updateSelfActivity(selfActivity);


		return new ModelAndView("redirect:/selfActivity/list.html");
	}

	private List<String> uploadImage(HttpServletRequest request, String path) throws IOException {
		List<String> pics = null;
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> picImageFiles = multipartRequest.getFiles("photoimage");
			if (null != picImageFiles && picImageFiles.size() > 0) {
				pics = new ArrayList<>(picImageFiles.size());
				String picPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + path;
				for (MultipartFile picImageFile : picImageFiles) {
					if (picImageFile != null && !StringUtils.isEmpty(picImageFile.getOriginalFilename())) {//有上传图片
						int indexOfDot = picImageFile.getOriginalFilename().indexOf(".");
						String fileNameC = UUIDGenerater.getFileName() + picImageFile.getOriginalFilename().substring(indexOfDot);
						File fileC = new File(picPath + fileNameC);
						if (!fileC.exists()) {
							fileC.mkdirs();
						}
						picImageFile.transferTo(fileC);
						pics.add(fileNameC);
					}
				}
			}
		}
		return pics;
	}
}
