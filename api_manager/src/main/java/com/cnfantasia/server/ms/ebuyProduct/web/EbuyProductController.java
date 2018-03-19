package com.cnfantasia.server.ms.ebuyProduct.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.cnfantasia.server.api.bankCollection.entity.ResultMsg;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.HSSFCellUtil;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyHomeType.entity.EbuyHomeType;
import com.cnfantasia.server.domainbase.ebuyHomeType.service.IEbuyHomeTypeBaseService;
import com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.entity.EbuyHomeTypeHasProduct;
import com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.service.IEbuyHomeTypeHasProductBaseService;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProductDto;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.service.IEbuyProductIntroducePicBaseService;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductParameters.service.IEbuyProductParametersBaseService;
import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;
import com.cnfantasia.server.domainbase.ebuyProductPic.service.IEbuyProductPicBaseService;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;
import com.cnfantasia.server.domainbase.ebuyProductShelf.service.IEbuyProductShelfBaseService;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.ms.ebuyProduct.constant.EbuyProductConstant;
import com.cnfantasia.server.ms.ebuyProduct.entity.EbuyProductEntity;
import com.cnfantasia.server.ms.ebuyProduct.entity.JfqStoreProductTypeInfoEntity;
import com.cnfantasia.server.ms.ebuyProduct.service.IEbuyProductService;
import com.cnfantasia.server.ms.ebuyProduct.util.ProductImageUtil;
import com.cnfantasia.server.ms.ebuyProductType.service.IEbuyProductTypeService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtils;

@Controller
@RequestMapping("/ebuyProduct")
public class EbuyProductController {

	private final Log logger = LogFactory.getLog(getClass());
	
	private List<EbuyProductType> productTypeList;
	private List<EbuySupplyMerchant> supplyMerchantList;
	
	@Resource
	private IEbuyProductService ebuyProductService;
	@Resource
	private IEbuyProductTypeService ebuyProductTypeService;
	@Resource
	private IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;
	@Resource
	private IUuidManager uuidManager;
	@Resource
	private IEbuyProductPicBaseService ebuyProductPicBaseService;
	@Resource
	private IEbuyProductShelfBaseService ebuyProductShelfBaseService;
	@Resource
	private IEbuyProductIntroducePicBaseService ebuyProductIntroducePicBaseService;
	@Resource
	private IEbuyHomeTypeBaseService ebuyHomeTypeBaseService;
	@Resource
	private IEbuyHomeTypeHasProductBaseService ebuyHomeTypeHasProductBaseService;
	@Resource
	private IEbuyProductParametersBaseService ebuyProductParametersBaseService;
	@Resource
	private ISysParamManager sysParamManager;
	
	/**
	 * 列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		paramMap.put("state", "list");
		
		handleListOrSearch(request, paramMap);
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("/ebuyProduct/ebuyProductList");
		return modelAndView;
	}
	
	/**
	 * 批量导入体验店商品--保存
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/saveBatchImport4JFQStore.html")
	@ResponseBody
	public JsonResponse saveBatchImport4JFQStore(HttpServletRequest request) throws IOException {
		JsonResponse jsonResponse = null;
		try {
			jsonResponse = ebuyProductService.saveBatchImport4JFQStore(request);
		}catch (Exception e) {
			jsonResponse.setMessage("导入异常，请检查Excel中的数据");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			
			logger.error(e.getMessage(), e);
		}

		return jsonResponse;
	}
	
	/**
	 * 货架管理列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/shelfList.html")
	public ModelAndView shelfList(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		paramMap.put("statusAudit", Integer.valueOf(EbuyProductConstant.ProductAuditStatus.AUDIT_STATUS_PASSED));
		paramMap.put("status",Integer.valueOf(EbuyProductConstant.ProductStatus.STATUS_ONSHELF));
		paramMap.put("APP",true);
		paramMap.put("state", "shelf");
		//根据上架时间排序
		paramMap.put("orderbytime", "upshelfTime");
		
		// 只有审核通过的店铺，页面“供应商名 称”搜索框才显示
		BigInteger merchantId = UserContext.getCurrUser().getMerchantId();
		if(merchantId!=null){
			EbuySupplyMerchant ebuySupplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(merchantId);
			if(ebuySupplyMerchant.getStoreAuditStatus()!=null && ebuySupplyMerchant.getStoreAuditStatus()!=2){
				request.setAttribute("sm", "invisible");
			}
		}
				
		handleListOrSearch(request, paramMap);
		paramMap.clear();
		paramMap.put("type", 2);// 类型:1为粮票;2首页运营主题
		List<EbuyHomeType> ebuyHomeTypes = ebuyHomeTypeBaseService.getEbuyHomeTypeByCondition(paramMap);

		ModelAndView modelAndView = new ModelAndView("/ebuyProduct/goodsShelf");
		modelAndView.addObject("ebuyHomeTypes", ebuyHomeTypes);
		modelAndView.addObject("isAdmin", UserContext.getCurrUser().getIsadmin());

		String laUrl = sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL);
		modelAndView.addObject("laUrl", laUrl);
		return modelAndView;
	}
	
	/**
	 * 商品审核
	 * @param model
	 * @param id
	 * @param productType
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/auditproduct.html")
	public String productaudit(ModelMap model,String id,String productType,String shelfId, String wlAppType, String pageType, String searchForm, 
			HttpServletRequest request) throws UnsupportedEncodingException{ 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		BigInteger productId = CnfantasiaCommUtil.convert2BigInteger(id);
		EbuyProduct ebuyProduct = ebuyProductService.getEbuyProductBySeqId(productId);
		EbuySupplyMerchant ebuySupplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(ebuyProduct.gettSupplyMerchantFId());
		paramMap.put("tEbuyProductFId",productId);
		List<EbuyProductPic> picList = ebuyProductPicBaseService.getEbuyProductPicByCondition(paramMap);
		List<EbuyProductParameters> list = ebuyProductService.getEbuyProductParameters(paramMap, false);
		List<EbuyProductIntroducePic> introducePicList = ebuyProductIntroducePicBaseService.getEbuyProductIntroducePicByCondition(paramMap);

		EbuyHomeTypeHasProduct product = new EbuyHomeTypeHasProduct();
		product.settProductId(new BigInteger(id));
		List<EbuyHomeTypeHasProduct> homeTypeHasProduct = ebuyHomeTypeHasProductBaseService.getEbuyHomeTypeHasProductByCondition(MapConverter.toMap(product));
		if (homeTypeHasProduct.size() > 0) {
			product = homeTypeHasProduct.get(0);
			EbuyHomeType ebuyHomeType = new EbuyHomeType();
			ebuyHomeType.setId(product.gettHomeTypeId());
			List<EbuyHomeType> types = ebuyHomeTypeBaseService.getEbuyHomeTypeByCondition(MapConverter.toMap(ebuyHomeType));
			model.addAttribute("ebuyHomeTypeName3", types.get(0).getName3());
		}
		// APP平台上t_ebuy_product表id与t_ebuy_product_shelf表id相同的数量
		int countEqOnApp = ebuyProductService.queryCountForEpIdEqShelfIdOnApp(productId);
		
		model.addAttribute("product", ebuyProduct);
		model.addAttribute("supplyMerchantName", ebuySupplyMerchant.getName());
		model.addAttribute("shelfId", shelfId);
		model.addAttribute("pageType", pageType);
		model.addAttribute("wlAppType", wlAppType);
		model.addAttribute("productType", productType);
		Collections.reverse(list);
		model.addAttribute("list", list);
		model.addAttribute("countEqOnApp", countEqOnApp);
		model.addAttribute("picList", picList);
		model.addAttribute("searchForm", searchForm);
		model.addAttribute("introducePicList", introducePicList);
		return "/ebuyProduct/ebuyProductAudit";
	}
	
	@RequestMapping("/offdetailsproduct.html")
	public String productDetails(ModelMap model,String epId,String productType, String ebuyProductShelfId, Integer appType) throws UnsupportedEncodingException{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		BigInteger productId = CnfantasiaCommUtil.convert2BigInteger(epId);
		EbuyProduct ebuyProduct = ebuyProductService.getEbuyProductBySeqId(productId);
		EbuyProductShelf ebuyProductShelf = ebuyProductShelfBaseService.getEbuyProductShelfBySeqId(new BigInteger(ebuyProductShelfId));
		paramMap.put("tEbuyProductFId",productId);
		List<EbuyProductPic> picList = ebuyProductPicBaseService.getEbuyProductPicByCondition(paramMap);
		List<EbuyProductParameters> list = ebuyProductService.getEbuyProductParameters(paramMap, false);
		
		List<EbuyProductIntroducePic> introducePicList = ebuyProductIntroducePicBaseService.getEbuyProductIntroducePicByCondition(paramMap);
		
		// 查询运营主题
		paramMap.clear();
		paramMap.put("type", 2);// 类型:1为粮票;2首页运营主题
		List<EbuyHomeType> ebuyHomeTypes = ebuyHomeTypeBaseService.getEbuyHomeTypeByCondition(paramMap);
		
		// 商品本身的主题
		paramMap.clear();
		paramMap.put("tProductId", productId);
		List<EbuyHomeTypeHasProduct> ebuyHomeTypeHasProducts = ebuyHomeTypeHasProductBaseService.getEbuyHomeTypeHasProductByCondition(paramMap);
		BigInteger theme = null;
		if(ebuyHomeTypeHasProducts!=null && ebuyHomeTypeHasProducts.size()>0){
			theme = ebuyHomeTypeHasProducts.get(0).gettHomeTypeId();
		}
		
		//查询货架分类
		paramMap.clear();
		appType = appType == null ? 1 : appType;
		paramMap.put("wlappType", appType);// 默认app
		paramMap.put("pointType", "1");// 积分商品不上架
		List<EbuyProductType> ebuyProductTypes = ebuyProductTypeService.getEbuyProductTypeByConditionDim(paramMap);
		model.addAttribute("productTypeList", ebuyProductTypes);
		
		model.addAttribute("product", ebuyProduct);
		model.addAttribute("ebuyProductShelf", ebuyProductShelf);
		model.addAttribute("productType", productType);
		model.addAttribute("list", list);
		model.addAttribute("theme", theme);
		model.addAttribute("picList", picList);
		model.addAttribute("introducePics", introducePicList);
		model.addAttribute("ebuyHomeTypes", ebuyHomeTypes);
		return "/ebuyProduct/goodsDetails";
	}
	
	/**
	 * 商品列表-商品信息查看
	 * @param pageType 页面类型===>{"detail"："详细页面", "edit"："编辑页面"}
	 * @return
	 */
	@RequestMapping("/ebuyProductDetail.html")
	public ModelAndView ebuyProductDetail(String pageType, BigInteger productId){
		EbuyProduct ebuyProduct = ebuyProductService.getEbuyProductBySeqId(productId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tEbuyProductFId", productId);
		List<EbuyProductIntroducePic> ebuyProductIntroducePics = ebuyProductIntroducePicBaseService.getEbuyProductIntroducePicByCondition(paramMap);// 传入参数“tEbuyProductFId”
		List<EbuyProductPic> ebuyProductPics = ebuyProductPicBaseService.getEbuyProductPicByCondition(paramMap);// 传入参数“tEbuyProductFId”
		
		paramMap.clear();
		paramMap.put("tProductId", productId);
		List<EbuyHomeTypeHasProduct> ebuyHomeTypeHasProducts = ebuyHomeTypeHasProductBaseService.getEbuyHomeTypeHasProductByCondition(paramMap);
		EbuyHomeType ebuyHomeType = null;
		if(ebuyHomeTypeHasProducts!=null && ebuyHomeTypeHasProducts.size()>0){
			EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct = ebuyHomeTypeHasProducts.get(0);
			ebuyHomeType = ebuyHomeTypeBaseService.getEbuyHomeTypeBySeqId(ebuyHomeTypeHasProduct.gettHomeTypeId());
		}
		EbuySupplyMerchant ebuySupplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(ebuyProduct.gettSupplyMerchantFId());
		//商品参数 查询出来是倒序的，反转一下
		EbuyProductParameters ebuyProductParameters = new EbuyProductParameters();
		ebuyProductParameters.settEbuyProductFId(productId);
		List<EbuyProductParameters> params = ebuyProductParametersBaseService.
				getEbuyProductParametersByCondition(MapConverter.toMap(ebuyProductParameters));
		Collections.reverse(params);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("params", params);
		modelAndView.addObject("ebuyProduct", ebuyProduct);
		modelAndView.addObject("supplyMerchantName", ebuySupplyMerchant.getName());
		Collections.reverse(ebuyProductIntroducePics);//图片介绍取出是倒序，所以要反转一下
		modelAndView.addObject("ebuyProductIntroducePics", ebuyProductIntroducePics);
		modelAndView.addObject("ebuyProductPics", ebuyProductPics);
		modelAndView.addObject("ebuyHomeType", ebuyHomeType);
		if(pageType.equals("detail")){
			EbuyProductType ebuyProductType = ebuyProductTypeService.getEbuyProductTypeBySeqId(ebuyProduct.gettEbuyProductTypeFId());
			modelAndView.addObject("ebuyProductType", ebuyProductType);
			modelAndView.setViewName("/ebuyProduct/ebuyProductDetail");
		} else if(pageType.equals("edit")){
			// 查询运营主题
			paramMap.clear();
			paramMap.put("type", 2);// 类型:1为粮票;2首页运营主题
			List<EbuyHomeType> ebuyHomeTypes = ebuyHomeTypeBaseService.getEbuyHomeTypeByCondition(paramMap);
			// 查询货架分类
			paramMap.clear();
			paramMap.put("wlappType", "1");// 默认app
			List<EbuyProductType> ebuyProductTypes = ebuyProductTypeService.getEbuyProductTypeByConditionDim(paramMap);

			modelAndView.addObject("ebuyHomeTypes", ebuyHomeTypes);
			modelAndView.addObject("ebuyProductTypes", ebuyProductTypes);
			modelAndView.setViewName("/ebuyProduct/ebuyProductEdit");
		}
		return modelAndView;
	}
	
	/**
	 * 商品列表-信息编辑保存
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/saveForEbuyProductEdit.html")
	public ModelAndView saveForEbuyProductEdit(HttpServletRequest request) throws IllegalStateException, IOException{
		BigInteger productId = new BigInteger(request.getParameter("productId"));
		String theme = ParamUtils.getString(request, "theme", null);
		String productType = ParamUtils.getString(request, "productType", null);
		String productName = ParamUtils.getString(request, "productName", null);
		String nameMini = ParamUtils.getString(request, "nameMini", null);
		String priceDiscount = ParamUtils.getString(request, "priceDiscount", null);
		String purchasePrice = ParamUtils.getString(request, "purchasePrice", null);
		BigDecimal price = ParamUtils.getBigDecimal(request, "price", new BigDecimal("0"));
		BigInteger leftCount = ParamUtils.getBigInteger(request, "leftCount", new BigInteger("0"));
		String statusAudit = request.getParameter("auditStatus");
		String[] textDescs = request.getParameterValues("descText");
		String[] picUrls = request.getParameterValues("picUrl");
		String[] introduceUrls = request.getParameterValues("introduceUrl");
		String[] propNames = request.getParameterValues("propName");
		String[] propValues = request.getParameterValues("propValue");
		
		// 浏览图片
		List<String> picImgs = new ArrayList<String>();
		// 详情图片
		List<String> introduceImgs = new ArrayList<String>();
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 浏览图片
			List<MultipartFile> picImageFiles = multipartRequest.getFiles("productPic");
			if(null!=picImageFiles && picImageFiles.size()>0){
				String picPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;
				int imgIndex=0;
				for (MultipartFile picImageFile : picImageFiles) {
					if(imgIndex==0){
						++imgIndex;
						continue;// 过滤掉头部的隐藏模板图片
					}
					if (picImageFile != null && !StringUtils.isEmpty(picImageFile.getOriginalFilename())) {//有上传图片 
						int indexOfDot = picImageFile.getOriginalFilename().indexOf(".");
						String fileNameC = (System.currentTimeMillis()+imgIndex) + picImageFile.getOriginalFilename().substring(indexOfDot);
						File fileC = new File(picPath + fileNameC);
						if(!fileC.exists())
							fileC.mkdirs();
						picImgs.add(fileNameC);
						picImageFile.transferTo(fileC);
						ProductImageUtil.generateMiniImage(fileC, PathConstants.Product_Image_Dir);
					} else {
						if(!StringUtils.isEmpty(picUrls[imgIndex])){
							picImgs.add(picUrls[imgIndex]);
						}
					}
					++imgIndex;
				}
			}
			
			// 详情图片
			List<MultipartFile> introduceImageFiles = multipartRequest.getFiles("photoimage");
			if(null!=introduceImageFiles && introduceImageFiles.size()>0){
				String picPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;
				int imgIndex=0;
				for (MultipartFile introduceImageFile : introduceImageFiles) {
					if(imgIndex==0){
						++imgIndex;
						continue;// 过滤掉头部的隐藏模板图片
					}
					if (introduceImageFile != null && !StringUtils.isEmpty(introduceImageFile.getOriginalFilename())) {//有上传图片 
						int indexOfDot = introduceImageFile.getOriginalFilename().indexOf(".");
						String fileNameC = (System.currentTimeMillis()+imgIndex) + introduceImageFile.getOriginalFilename().substring(indexOfDot);
						File fileC = new File(picPath + fileNameC);
						if(!fileC.exists())
							fileC.mkdirs();
						introduceImgs.add(fileNameC);
						introduceImageFile.transferTo(fileC);
						ProductImageUtil.generateMiniImage(fileC, PathConstants.Product_Image_Dir);
					} else {
						if(!StringUtils.isEmpty(introduceUrls[imgIndex])){
							introduceImgs.add(introduceUrls[imgIndex]);
						}
					}
					++imgIndex;
				}
			}
		}

		//处理商品参数
		List<EbuyProductParameters> parameterses = null;
		EbuyProductParameters parameter = null;
		if (propNames != null && propNames.length > 0) {
			parameterses = new ArrayList<EbuyProductParameters>(propNames.length);
			List<BigInteger> productParamIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_parameters, propNames.length);
			for (int i = 0; i < propNames.length; i++) {
				if (!"".equals(propNames[i])) {
					parameter = new EbuyProductParameters();
					parameter.setId(productParamIds.get(i));
					parameter.settEbuyProductFId(productId);
					parameter.settPropName(propNames[i]);
					parameter.settPropValue(propValues[i]);
					parameterses.add(parameter);
				}
			}
		}
		
		// 生成参数对象
		List<EbuyProductPic> ebuyProductPicList = new ArrayList<EbuyProductPic>();
		List<EbuyProductIntroducePic> ebuyProductIntroducePicList = new ArrayList<EbuyProductIntroducePic>();
		
		List<BigInteger> picIds= uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_pic, picImgs.size());
		List<BigInteger> introducePicIds= uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_introduce_pic, introduceImgs.size());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.clear();
		paramMap.put("tEbuyProductId", productId);
		List<EbuyProductShelf> ebuyProductShelfs = ebuyProductShelfBaseService.getEbuyProductShelfByCondition(paramMap);
		
		BigInteger userId = UserContext.getOperIdBigInteger();
		EbuyProduct ebuyProduct = ebuyProductService.getEbuyProductBySeqId(productId);
		ebuyProduct.setName(productName);
		ebuyProduct.setNameMini(nameMini);
		if(picImgs.size()>0){
			ebuyProduct.setPicBase(picImgs.get(0));
			ebuyProduct.setPicBaseMini(picImgs.get(0));
		}
		ebuyProduct.setPrice(price.multiply(new BigDecimal(100)).longValue());
		ebuyProduct.setPriceDiscount(new BigDecimal(priceDiscount).multiply(new BigDecimal(100)).longValue());
		ebuyProduct.setPurchasePrice(new BigDecimal(purchasePrice).multiply(new BigDecimal(100)).longValue());
		ebuyProduct.setLeftCount(leftCount);
		ebuyProduct.setStatus(1);
		String now = DateUtils.getCurrentDate();
		ebuyProduct.setSys0UpdTime(now);
		ebuyProduct.setSys0UpdUser(userId);
		ebuyProduct.setStatusAudit(Integer.parseInt(statusAudit));
		ebuyProduct.settEbuyProductTypeFId(new BigInteger(productType));
		EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct = null;
		if(theme!=null && !theme.trim().equals("")){
			ebuyHomeTypeHasProduct = new EbuyHomeTypeHasProduct();
			BigInteger ebuyHomeTypeHasProductId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_home_type_has_product);
			ebuyHomeTypeHasProduct.setId(ebuyHomeTypeHasProductId);
			ebuyHomeTypeHasProduct.setSort(ebuyHomeTypeHasProductId.longValue());
			ebuyHomeTypeHasProduct.settProductId(productId);
			ebuyHomeTypeHasProduct.settHomeTypeId(new BigInteger(theme));
			ebuyHomeTypeHasProduct.setSys0DelState(0);
		}
		
		if(picImgs!=null && picImgs.size()>0){
			for(int i=0; i<picImgs.size(); i++){
				BigInteger picId = picIds.get(i);
				EbuyProductPic ebuyProductPic = new EbuyProductPic();
				ebuyProductPic.setId(picId);
				ebuyProductPic.settEbuyProductFId(productId);
				ebuyProductPic.setUrlBig(picImgs.get(i));
				ebuyProductPic.setUrlMini(picImgs.get(i));
				ebuyProductPic.setSys0DelState(0);
				ebuyProductPicList.add(ebuyProductPic);
			}
		}
		
		if(introduceImgs!=null && introduceImgs.size()>0){
			for(int i=0; i<introduceImgs.size(); i++){
				BigInteger introducePicId = introducePicIds.get(i);
				EbuyProductIntroducePic ebuyProductIntroducePic = new EbuyProductIntroducePic();
				ebuyProductIntroducePic.setId(introducePicId);
				ebuyProductIntroducePic.settEbuyProductFId(productId);
				ebuyProductIntroducePic.setTextDesc(textDescs[i+1]);// textDescs中的第一个为前端页面隐藏的模板，要去掉
				ebuyProductIntroducePic.setUrlBig(introduceImgs.get(i));
				ebuyProductIntroducePic.setSys0DelState(0);
				ebuyProductIntroducePic.setUrlMini(introduceImgs.get(i));
				ebuyProductIntroducePicList.add(ebuyProductIntroducePic);
			}
		}
		
		paramMap.clear();
		paramMap.put("typeName", "轻应用");
		paramMap.put("wlappType", EbuyDict.WlAppType.Jfq_Light_App);
		List<EbuyProductType> ebuyProductTypes = ebuyProductTypeService.getEbuyProductTypeByCondition(paramMap);
		
		for(EbuyProductShelf ebuyProductShelf:ebuyProductShelfs){
			ebuyProductShelf.setPrice(price.multiply(new BigDecimal(100)).longValue());
			ebuyProductShelf.setPriceDiscount(new BigDecimal(priceDiscount).multiply(new BigDecimal(100)).longValue());
			ebuyProductShelf.setSys0UpdTime(now);
			ebuyProductShelf.setSys0UpdUser(userId);
			if("3".equals(statusAudit)){
				ebuyProductShelf.setApplyTime(now);
			}
			
			if((productType!=null && !productType.trim().equals("")) 
					&& (ebuyProductShelf.gettEbuyProductTypeId().intValue() != ebuyProductTypes.get(0).getId().intValue())){// 更新不是“轻应用”的shelf类型
				ebuyProductShelf.settEbuyProductTypeId(new BigInteger(productType));
			}
		}
		
		// 数据交互
		boolean isSuccess = ebuyProductService.updateEbuyProduct(parameterses, ebuyProduct, ebuyProductPicList,
				ebuyProductIntroducePicList, ebuyHomeTypeHasProduct, ebuyProductShelfs);
		
		if(isSuccess){
			request.setAttribute(JSPConstants.OprtResult, "操作成功");
		} else {
			request.setAttribute(JSPConstants.OprtResult, "操作失败");
		}
		
		request.setAttribute(JSPConstants.ToURL, "../ebuyProduct/ebuyProductDetail.html?pageType=detail&productId="+productId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 进入单品录入页面
	 * @author Lily
	 * @date 20160419
	 * @return
	 */
	@RequestMapping("/addEbuyProductIndex.html")
	public ModelAndView addEbuyProductIndex(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		
		List<EbuySupplyMerchant> supplyMerchantsTmp = UserContext.getSupplyMerchantList();
		if((UserContext.getCurrUser().getIsadmin() == 1) || (supplyMerchantsTmp != null && supplyMerchantsTmp.size()>0)){
			// 查询运营主题
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("type", 2);// 类型:1为粮票;2首页运营主题
			List<EbuyHomeType> ebuyHomeTypes = ebuyHomeTypeBaseService.getEbuyHomeTypeByCondition(paramMap);
			// 查询货架分类
			paramMap.clear();
			paramMap.put("wlappType", "1");// 默认app
			paramMap.put("pointType", "1");// 积分商品不上架
			List<EbuyProductType> ebuyProductTypes = ebuyProductTypeService.getEbuyProductTypeByConditionDim(paramMap);
			
			List<EbuySupplyMerchant> supplyMerchants = null;
			if(UserContext.getCurrUser().getIsadmin() == 1){
				// 管理员进入
				paramMap.clear();
				paramMap.put("storeAuditStatus", 1);
				supplyMerchants = ebuySupplyMerchantBaseService.getEbuySupplyMerchantByConditionDim(paramMap);
			} else if(supplyMerchantsTmp != null && supplyMerchantsTmp.size()>0){
				// 供应商进入
				supplyMerchants = supplyMerchantsTmp;
			}
			String storeId = sysParamManager.getSysParaValue(SysParamKey.Experience_Store_Id);
			modelAndView.addObject("ebuyHomeTypes", ebuyHomeTypes);
			modelAndView.addObject("supplyMerchants", supplyMerchants);
			modelAndView.addObject("ebuyProductTypes", ebuyProductTypes);
			modelAndView.addObject("experienceStoreId", storeId);
			modelAndView.setViewName("/ebuyProduct/ebuyProductAdd");
		} else {
			request.setAttribute(JSPConstants.OprtResult, "非供应商无权限进入！");
			request.setAttribute(JSPConstants.ToURL, "../security/accessNoPermission.html");
			modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		}
		
		return modelAndView;
	}
	
	/**
	 * 单品录入数据提交
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/addEbuyProduct.html")
	public ModelAndView addEbuyProduct(HttpServletRequest request) throws IllegalStateException, IOException{
		BigInteger supplyMerchantId = ParamUtils.getBigInteger(request, "supplyMerchant", null);
		if(supplyMerchantId != null){
			String productName = ParamUtils.getString(request, "productName", null);
			String theme = ParamUtils.getString(request, "theme", null);
			BigInteger productType = ParamUtils.getBigInteger(request, "productType", null);
			String nameMini = ParamUtils.getString(request, "nameMini", null);
			String code = ParamUtils.getString(request, "code", null);
			String priceDiscount = ParamUtils.getString(request, "priceDiscount", null);
			String purchasePrice = ParamUtils.getString(request, "purchasePrice", null);
			BigDecimal price = ParamUtils.getBigDecimal(request, "price", new BigDecimal("0"));
			BigInteger leftCount = ParamUtils.getBigInteger(request, "leftCount", new BigInteger("0"));
			String statusAudit = request.getParameter("auditStatus");
			String productDesc = request.getParameter("productDesc");
			String[] textDescs = request.getParameterValues("descText");
			String[] propNames = request.getParameterValues("propName");
			String[] propValues = request.getParameterValues("propValue");

			// 浏览图片
			List<String> picImgs = new ArrayList<String>();
			// 详情图片
			List<String> introduceImgs = new ArrayList<String>();
			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 浏览图片
				List<MultipartFile> picImageFiles = multipartRequest.getFiles("productPic");
				if(null!=picImageFiles && picImageFiles.size()>0){
					String picPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;
					int imgIndex=0;
					for (MultipartFile picImageFile : picImageFiles) {
						if(imgIndex==0){
							++imgIndex;
							continue;// 过滤掉头部的隐藏模板图片
						}
						if (picImageFile != null && !StringUtils.isEmpty(picImageFile.getOriginalFilename())) {//有上传图片
							int indexOfDot = picImageFile.getOriginalFilename().indexOf(".");
							String fileNameC = (System.currentTimeMillis()+imgIndex) + picImageFile.getOriginalFilename().substring(indexOfDot);
							File fileC = new File(picPath + fileNameC);
							if(!fileC.exists())
								fileC.mkdirs();
							picImgs.add(fileNameC);
							picImageFile.transferTo(fileC);
							ProductImageUtil.generateMiniImage(fileC, PathConstants.Product_Image_Dir);
						}
						++imgIndex;
					}
				}

				// 详情图片
				List<MultipartFile> introduceImageFiles = multipartRequest.getFiles("photoimage");
				if(null!=introduceImageFiles && introduceImageFiles.size()>0){
					String picPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;
					int imgIndex=0;
					for (MultipartFile introduceImageFile : introduceImageFiles) {
						if(imgIndex==0){
							++imgIndex;
							continue;// 过滤掉头部的隐藏模板图片
						}
						if (introduceImageFile != null && !StringUtils.isEmpty(introduceImageFile.getOriginalFilename())) {//有上传图片
							int indexOfDot = introduceImageFile.getOriginalFilename().indexOf(".");
							String fileNameC = (System.currentTimeMillis()+imgIndex) + introduceImageFile.getOriginalFilename().substring(indexOfDot);
							File fileC = new File(picPath + fileNameC);
							if(!fileC.exists())
								fileC.mkdirs();
							introduceImgs.add(fileNameC);
							introduceImageFile.transferTo(fileC);
							ProductImageUtil.generateMiniImage(fileC, PathConstants.Product_Image_Dir);
						}
						++imgIndex;
					}
				}
			}

			//处理ebuyProduct
			BigInteger productId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product);
			EbuyProduct ebuyProduct = new EbuyProduct();
			ebuyProduct.setId(productId);
			ebuyProduct.setDesc(productDesc);
			ebuyProduct.setName(productName);
			ebuyProduct.setNameMini(nameMini);
			ebuyProduct.setCode(code);
			ebuyProduct.setPrice(price.multiply(new BigDecimal(100)).longValue());
			ebuyProduct.setPriceDiscount(new BigDecimal(priceDiscount).multiply(new BigDecimal(100)).longValue());
			ebuyProduct.setPurchasePrice(new BigDecimal(purchasePrice).multiply(new BigDecimal(100)).longValue());
			ebuyProduct.setLeftCount(leftCount);
			ebuyProduct.setStatus(1);
			if(picImgs.size()>0){
				ebuyProduct.setPicBase(picImgs.get(0));
				ebuyProduct.setPicBaseMini(picImgs.get(0));
			}
			String now = DateUtils.getCurrentDate();
			BigInteger userId = UserContext.getOperIdBigInteger();
			ebuyProduct.setSys0AddTime(now);
			ebuyProduct.setSys0AddUser(userId);
			ebuyProduct.setCreateTime(now);
			ebuyProduct.setUpShelfTime(now);
			ebuyProduct.setSelNum(new BigInteger("0"));
			ebuyProduct.setStatusAudit(Integer.parseInt(statusAudit));
			ebuyProduct.settEbuyProductTypeFId(productType);
			ebuyProduct.setPointType(1);
			ebuyProduct.setWlappType(Long.parseLong("1"));// 默认app
			ebuyProduct.setSpecialProductType(1);
			ebuyProduct.settSupplyMerchantFId(supplyMerchantId);

			//处理商品图片
			List<EbuyProductPic> ebuyProductPicList = new ArrayList<EbuyProductPic>();
			if(picImgs.size()>0){
				List<BigInteger> picIds= uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_pic, picImgs.size());
				for(int i=0; i<picImgs.size(); i++){
					BigInteger picId = picIds.get(i);
					EbuyProductPic ebuyProductPic = new EbuyProductPic();
					ebuyProductPic.setId(picId);
					ebuyProductPic.settEbuyProductFId(productId);
					ebuyProductPic.setUrlBig(picImgs.get(i));
					ebuyProductPic.setUrlMini(picImgs.get(i));
					ebuyProductPic.setSys0DelState(0);
					ebuyProductPicList.add(ebuyProductPic);
				}
			}

			//处理详情图片
			List<EbuyProductIntroducePic> ebuyProductIntroducePicList = new ArrayList<EbuyProductIntroducePic>();
			if(introduceImgs.size()>0){
				List<BigInteger> introducePicIds= uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_introduce_pic, introduceImgs.size());
				for(int i=0; i<introduceImgs.size(); i++){
					BigInteger introducePicId = introducePicIds.get(i);
					EbuyProductIntroducePic ebuyProductIntroducePic = new EbuyProductIntroducePic();
					ebuyProductIntroducePic.setId(introducePicId);
					ebuyProductIntroducePic.settEbuyProductFId(productId);
					ebuyProductIntroducePic.setTextDesc(textDescs[i+1]);// textDescs中的第一个为前端页面隐藏的模板，要去掉
					ebuyProductIntroducePic.setUrlBig(introduceImgs.get(i));
					ebuyProductIntroducePic.setSys0DelState(0);
					ebuyProductIntroducePic.setUrlMini(introduceImgs.get(i));
					ebuyProductIntroducePicList.add(ebuyProductIntroducePic);
				}
			}

			//处理运营主题
			BigInteger ebuyHomeTypeHasProductId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_home_type_has_product);
			EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct = null;
			if(theme!=null && !theme.trim().equals("")){
				ebuyHomeTypeHasProduct = new EbuyHomeTypeHasProduct();
				ebuyHomeTypeHasProduct.setId(ebuyHomeTypeHasProductId);
				ebuyHomeTypeHasProduct.setSort(ebuyHomeTypeHasProductId.longValue());
				ebuyHomeTypeHasProduct.settProductId(productId);
				ebuyHomeTypeHasProduct.settHomeTypeId(new BigInteger(theme));
				ebuyHomeTypeHasProduct.setSys0DelState(0);
			}

			
			BigInteger ebuyProductShelfId= uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product);
			EbuyProductShelf ebuyProductShelf = new EbuyProductShelf();
			ebuyProductShelf.setId(ebuyProductShelfId);
			ebuyProductShelf.settEbuyProductId(productId);
			ebuyProductShelf.setUpShelfState(1);
			ebuyProductShelf.setApplyTime(now);
			ebuyProductShelf.settEbuyProductTypeId(productType);
			ebuyProductShelf.setPrice(price.multiply(new BigDecimal(100)).longValue());
			ebuyProductShelf.setPriceDiscount(new BigDecimal(priceDiscount).multiply(new BigDecimal(100)).longValue());
			ebuyProductShelf.setSort(ebuyProductShelfId.longValue());
			ebuyProductShelf.setSys0UpdTime(now);
			ebuyProductShelf.setSys0AddUser(userId);

			//处理商品参数
			List<EbuyProductParameters> parameterses = null;
			EbuyProductParameters parameter = null;
			if (propNames != null && propNames.length > 0) {
				parameterses = new ArrayList<EbuyProductParameters>(propNames.length);
				List<BigInteger> productParamIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_parameters, propNames.length);
				for (int i = 0; i < propNames.length; i++) {
					if (!"".equals(propNames[i])) {
						parameter = new EbuyProductParameters();
						parameter.setId(productParamIds.get(i));
						parameter.settEbuyProductFId(productId);
						parameter.settPropName(propNames[i]);
						parameter.settPropValue(propValues[i]);
						parameterses.add(parameter);
					}
				}
			}

			// 数据交互
			EbuyProductDto ebuyProductDto = new EbuyProductDto();
			ebuyProductDto.setEbuyProduct(ebuyProduct);
			ebuyProductDto.setEbuyProductIntroducePics(ebuyProductIntroducePicList);
			ebuyProductDto.setEbuyProductPics(ebuyProductPicList);
			ebuyProductDto.setEbuyHomeTypeHasProduct(ebuyHomeTypeHasProduct);
			ebuyProductDto.setParameterses(parameterses);
			ebuyProductDto.setEbuyProductShelf(ebuyProductShelf);
			boolean isSuccess = ebuyProductService.addEbuyProduct(ebuyProductDto);
			
			if(isSuccess){
				request.setAttribute(JSPConstants.OprtResult, "操作成功");
			} else {
				request.setAttribute(JSPConstants.OprtResult, "操作失败");
			}
		} else {
			request.setAttribute(JSPConstants.OprtResult, "供应商不能为空！");
		}
		
		request.setAttribute(JSPConstants.ToURL, "../ebuyProduct/addEbuyProductIndex.html");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 更新商品详情
	 * @author Lily
	 * @date 20160419
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/updateProduct.html")
	public ModelAndView updateProduct(HttpServletRequest request) throws IllegalStateException, IOException{
		String productId = request.getParameter("epId");
		String name = request.getParameter("name");
		String leftCount = request.getParameter("leftCount").trim();
		String shelfPrice = request.getParameter("shelfPrice").trim();
		String price = request.getParameter("price").trim();
		String purchasePrice = request.getParameter("purchasePrice").trim(); 
		String theme = request.getParameter("theme");
		String ebuyProductShelfId = request.getParameter("ebuyProductShelfId");
		String productDesc = request.getParameter("productDesc");
		
		String[] propNames = request.getParameterValues("propName");
		String[] propValues = request.getParameterValues("propValue");
		String[] textDescs = request.getParameterValues("descText");
		String[] imgUrls = request.getParameterValues("imgUrl");//商品详情的图片地址，老的
		
		String[] prdt_imgUrls = request.getParameterValues("prdt_imgUrl");//商品图片地址，老的
		BigInteger productTypeId = ParamUtils.getBigInteger(request, "productTypeId", null);
		
		BigInteger userId = UserContext.getOperIdBigInteger();
		//无权限不给操作
		if (UserContext.getCurrUser().getIsadmin() != 1 && !ebuyProductService.isProductOwner(userId, new BigInteger(productId))) {
			request.setAttribute(JSPConstants.OprtResult, "保存失败");
			request.setAttribute(JSPConstants.ToURL, "../ebuyProduct/shelfList.html");
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName(JSPConstants.OprtSuccessPage);
			return modelAndView;
		}
		List<String> productIntroducePicList = new ArrayList<String>();//商品详情图片
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			String filePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;

			List<MultipartFile> uploadImageFiles = multipartRequest.getFiles("photoimage");
			if(null!=uploadImageFiles && uploadImageFiles.size()>0){
				int imgIndex=0;
				for (MultipartFile uploadImageFile : uploadImageFiles) {
					if(imgIndex==0){
						++imgIndex;
						continue;// 过滤掉头部的隐藏模板图片
					}
					if (uploadImageFile != null && !StringUtils.isEmpty(uploadImageFile.getOriginalFilename())) {//有上传图片 
						// 大图
						int indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
						String fileNameC = String.valueOf(System.currentTimeMillis()+imgIndex) + uploadImageFile.getOriginalFilename().substring(indexOfDot);
						File fileC = new File(filePath + fileNameC);
						if(!fileC.exists())
							fileC.mkdirs();
						productIntroducePicList.add(fileNameC);
						uploadImageFile.transferTo(fileC);
						// 小图
						ProductImageUtil.generateMiniImage(fileC, PathConstants.Product_Image_Dir);
						
						++imgIndex;
					} else {
						// 旧图片地址
						if(!StringUtils.isEmpty(imgUrls[imgIndex])){
							productIntroducePicList.add(imgUrls[imgIndex]);
						}
						++imgIndex;
					}
				}
			}
		}
		
		List<String> productPicList = new ArrayList<String>();//商品图片
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			String filePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;

			List<MultipartFile> uploadImageFiles = multipartRequest.getFiles("prdt_image");
			if(null!=uploadImageFiles && uploadImageFiles.size()>0){
				int imgIndex=0;
				for (MultipartFile uploadImageFile : uploadImageFiles) {
					if(imgIndex==0){
						++imgIndex;
						continue;// 过滤掉头部的隐藏模板图片
					}
					if (uploadImageFile != null && !StringUtils.isEmpty(uploadImageFile.getOriginalFilename())) {//有上传图片 
						// 大图
						int indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
						String fileNameC = String.valueOf(System.currentTimeMillis()+imgIndex) + uploadImageFile.getOriginalFilename().substring(indexOfDot);
						File fileC = new File(filePath + fileNameC);
						if(!fileC.exists())
							fileC.mkdirs();
						productPicList.add(fileNameC);
						uploadImageFile.transferTo(fileC);
						// 小图
						ProductImageUtil.generateMiniImage(fileC, PathConstants.Product_Image_Dir);
						
						++imgIndex;
					} else {
						// 旧图片地址
						if(!StringUtils.isEmpty(prdt_imgUrls[imgIndex])){
							productPicList.add(prdt_imgUrls[imgIndex]);
						}
						++imgIndex;
					}
				}
			}
		}
		

		String now = CnfantasiaCommUtil.getCurrentTime();
		// 生成参数对象
		EbuyProduct ebuyProduct = ebuyProductService.getEbuyProductBySeqId(new BigInteger(productId));
		ebuyProduct.setName(name);
		ebuyProduct.setLeftCount(new BigInteger(leftCount));
		Long price2 = new BigDecimal(price).multiply(new BigDecimal(100)).longValue();
		ebuyProduct.setPrice(price2);
		ebuyProduct.setPurchasePrice(new BigDecimal(purchasePrice).multiply(new BigDecimal(100)).longValue());
		ebuyProduct.setSys0UpdTime(now);
		ebuyProduct.setSys0UpdUser(userId);
		ebuyProduct.setDesc(productDesc);
		ebuyProduct.setIsPreSell(ParamUtils.getInteger(request, "isPreSell", 0));
		
		EbuyProductShelf ebuyProductShelf = ebuyProductShelfBaseService.getEbuyProductShelfBySeqId(new BigInteger(ebuyProductShelfId));
		Long priceDiscount = new BigDecimal(shelfPrice).multiply(new BigDecimal(100)).longValue();
		ebuyProductShelf.setPriceDiscount(priceDiscount);
		ebuyProductShelf.setPrice(price2);
		ebuyProductShelf.setSys0UpdTime(now);
		ebuyProductShelf.setSys0UpdUser(userId);
				
		List<EbuyProductParameters> ebuyProductParametersList = null;
		if(propNames!=null && propNames.length>0){
			ebuyProductParametersList = new ArrayList<EbuyProductParameters>();
			List<BigInteger> parametersIds= uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_parameters, propNames.length);
			for(int i=0; i<parametersIds.size(); i++){
				BigInteger parametersId = parametersIds.get(i);
				EbuyProductParameters ebuyProductParameters = new EbuyProductParameters();
				ebuyProductParameters.setId(parametersId);
				ebuyProductParameters.settEbuyProductFId(new BigInteger(productId));
				ebuyProductParameters.settPropName(propNames[i]);
				ebuyProductParameters.settPropValue(propValues[i]);
				ebuyProductParameters.setSys0DelState(0);
				
				ebuyProductParametersList.add(ebuyProductParameters);
			}
		}
		
		List<EbuyProductIntroducePic> ebuyProductIntroducePicList = null;
		if(productIntroducePicList!=null && productIntroducePicList.size()>0){
			ebuyProductIntroducePicList = new ArrayList<EbuyProductIntroducePic>();
			List<BigInteger> introducePicIds= uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_introduce_pic, productIntroducePicList.size());
			for(int i=0; i<introducePicIds.size(); i++){
				BigInteger introducePicId = introducePicIds.get(i);
				EbuyProductIntroducePic ebuyProductIntroducePic = new EbuyProductIntroducePic();
				ebuyProductIntroducePic.setId(introducePicId);
				ebuyProductIntroducePic.settEbuyProductFId(new BigInteger(productId));
				ebuyProductIntroducePic.setTextDesc(textDescs[i+1]);// textDescs中的第一个为前端页面隐藏的模板，要去掉
				ebuyProductIntroducePic.setUrlBig(productIntroducePicList.get(i));
				ebuyProductIntroducePic.setSys0DelState(0);
				ebuyProductIntroducePic.setUrlMini(productIntroducePicList.get(i));
				ebuyProductIntroducePicList.add(ebuyProductIntroducePic);
			}
		}
		
		//添加商品图片 Added by wenfq 20170316
		List<EbuyProductPic> ebuyProductPicList = new ArrayList<EbuyProductPic>();
		if(productPicList.size()>0){
			List<BigInteger> productPicIds= uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_pic, productPicList.size());
			for(int i = 0; i < productPicList.size(); i++){
				EbuyProductPic ebuyProductPic = new EbuyProductPic();
				ebuyProductPic.setId(productPicIds.get(i));
				ebuyProductPic.settEbuyProductFId(new BigInteger(productId));
				ebuyProductPic.setUrlBig(productPicList.get(i));
				ebuyProductPic.setUrlMini(productPicList.get(i));
				ebuyProductPicList.add(ebuyProductPic);
			}
			ebuyProduct.setPicBase(ebuyProductPicList.get(0).getUrlBig());//换商品大图
		}
		
		EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct = null;
		if(theme!=null && !theme.trim().equals("")){
			ebuyHomeTypeHasProduct = new EbuyHomeTypeHasProduct();
			BigInteger ebuyHomeTypeHasProductId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_home_type_has_product);
			ebuyHomeTypeHasProduct.setId(ebuyHomeTypeHasProductId);
			ebuyHomeTypeHasProduct.setSort(ebuyHomeTypeHasProductId.longValue());
			ebuyHomeTypeHasProduct.settHomeTypeId(new BigInteger(theme));
			ebuyHomeTypeHasProduct.settProductId(new BigInteger(productId));
		}
		
		//数据交互
		boolean isSuccess = ebuyProductService.updateProductById(ebuyProduct, ebuyProductShelf, productTypeId, ebuyProductParametersList, ebuyProductIntroducePicList,ebuyProductPicList, ebuyHomeTypeHasProduct);
		if(isSuccess){
			request.setAttribute(JSPConstants.OprtResult, "保存成功");
		} else {
			request.setAttribute(JSPConstants.OprtResult, "保存失败");
		}
		request.setAttribute(JSPConstants.ToURL, "../ebuyProduct/shelfList.html");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 上架管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/productup.html")
	public String productup(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		paramMap.put("productUp",true);
		paramMap.put("state", "up");
		//根据申请时间排序
		paramMap.put("orderbytime", "downshelfTime");
		
		// 只有审核通过的店铺，页面“供应商名 称”搜索框才显示
		BigInteger merchantId = UserContext.getCurrUser().getMerchantId();
		if(merchantId!=null){
			EbuySupplyMerchant ebuySupplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(merchantId);
			if(ebuySupplyMerchant.getStoreAuditStatus()!=null && ebuySupplyMerchant.getStoreAuditStatus()!=2){
				request.setAttribute("sm", "invisible");
			} 
		}
		
		handleListOrSearch(request, paramMap);
		return "/ebuyProduct/ebuyProductUpShelf";
	}
	
	/**
	 * 上架列表查找
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/auditSearch.html")
	public String upshelfSearch(HttpServletRequest request) throws UnsupportedEncodingException {
		String name = ParamUtils.getString(request, "name", null); // request.getParameter("name");
		Long tEbuyProductTypeFId = ParamUtils.getLong(request, "tEbuyProductTypeFId", null); //request.getParameter("tEbuyProductTypeFId");
		Long tSupplyMerchantFId = ParamUtils.getLong(request, "tSupplyMerchantFId", null); //request.getParameter("tSupplyMerchantFId");
		Integer status = ParamUtils.getInteger(request, "status", null); //request.getParameter("status");
		Integer supplyMerchantType = ParamUtils.getInteger(request, "supplyMerchantType", null);
		String applyTime_START = request.getParameter("applyTime_START");
		String applyTime_END = request.getParameter("applyTime_END");
		String auditTime_START = request.getParameter("auditTime_START");
		String auditTime_END = request.getParameter("auditTime_END");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(UserContext.getCurrUser().getIsadmin() != 1) {
			paramMap.put("supplyMerchantList", UserContext.getSupplyMerchantList());
		}
		paramMap.put("name", name);
		paramMap.put("ebuyProductTypeId", tEbuyProductTypeFId);
		paramMap.put("tSupplyMerchantFId", tSupplyMerchantFId);
		paramMap.put("supplyMerchantType", supplyMerchantType);
		if(status != null && (status == 0 || status == 1)) {
			paramMap.put("upShelfState", 1);
			paramMap.put("statusAudit", Integer.valueOf(EbuyProductConstant.ProductAuditStatus.AUDIT_STATUS_PASSED));
		} else if(status != null && (status ==2 || status ==3)){
			if(status ==2){
				paramMap.put("statusAudit", Integer.valueOf(EbuyProductConstant.ProductAuditStatus.AUDIT_STATUS_NO_PASSED));
			}else{
				paramMap.put("statusAudit", Integer.valueOf(EbuyProductConstant.ProductAuditStatus.AUDIT_STATUS_WAITE_AUDIT));
			}
		} else {
			paramMap.put("status", status);
		}
		if(!"请选择起始时间".equals(applyTime_START)) {
			paramMap.put("applyTime_START", applyTime_START);
		}
		if(!"请选择结束时间".equals(applyTime_END)) {
			paramMap.put("applyTime_END", applyTime_END);
		}
		if(!"请选择起始时间".equals(auditTime_START)) {
			paramMap.put("upShelfTime_START", auditTime_START);
		}
		if(!"请选择结束时间".equals(auditTime_END)) {
			paramMap.put("upShelfTime_END", auditTime_END);
		}
		paramMap.put("orderbytime", "downshelfTime");
		paramMap.put("productUp",true);
		paramMap.put("state", "up");
		handleListOrSearch(request, paramMap);
		return "/ebuyProduct/ebuyProductUpShelf";
	}
	
	/**
	 * 批量上架
	 * @param params  json数组===>{"epId":"", "shelfId":"", "wlAppType":""}
	 * @param shelfAddress  
	 * @return
	 */
	@RequestMapping("/upShelf.html")
	public @ResponseBody String upShelf(String params, String shelfAddress){
		List<Map> paramList = JSONArray.parseArray(params, Map.class);
		
		List<EbuyProduct> ebuyProductList = new ArrayList<EbuyProduct>();
		List<EbuyProductShelf> updateEbuyProductShelfs = new ArrayList<EbuyProductShelf>();
		List<EbuyProductShelf> insertEbuyProductShelfs = new ArrayList<EbuyProductShelf>();
		String now = CnfantasiaCommUtil.getCurrentTime();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("typeName", "轻应用");
		paramMap.put("wlappType", EbuyDict.WlAppType.Jfq_Light_App);
		List<EbuyProductType> ebuyProductTypes = ebuyProductTypeService.getEbuyProductTypeByCondition(paramMap);
		
		BigInteger userId = UserContext.getOperIdBigInteger();
		for(Map map:paramList){
			// t_ebuy_product
			BigInteger epId = new BigInteger(map.get("epId").toString());
			EbuyProduct product = ebuyProductService.getEbuyProductBySeqId(epId);
			product.setStatus(0);
			product.setStatusAudit(5);
			product.setUpShelfTime(now);
			product.setSys0UpdTime(now);
			product.setSys0UpdUser(userId);
			ebuyProductList.add(product);
			
			// t_ebuy_product_shelf
			if(map.get("wlAppType").toString().equals(String.valueOf(EbuyDict.WlAppType.Jfq))){
				if(!shelfAddress.contains(String.valueOf(EbuyDict.WlAppType.Jfq))){
					// app：没有选择，则status=1
					EbuyProductShelf ebuyProductShelf = ebuyProductShelfBaseService.getEbuyProductShelfBySeqId(new BigInteger(map.get("shelfId").toString()));
					ebuyProductShelf.setUpShelfState(1);
					ebuyProductShelf.setSys0UpdTime(now);
					ebuyProductShelf.setSys0UpdUser(userId);
					ebuyProductShelf.setHandTime(now);
					updateEbuyProductShelfs.add(ebuyProductShelf);
				} else {
					EbuyProductShelf ebuyProductShelf = ebuyProductShelfBaseService.getEbuyProductShelfBySeqId(new BigInteger(map.get("shelfId").toString()));
					ebuyProductShelf.setUpShelfState(0);
					ebuyProductShelf.setSys0UpdTime(now);
					ebuyProductShelf.setSys0UpdUser(userId);
					ebuyProductShelf.setHandTime(now);
					updateEbuyProductShelfs.add(ebuyProductShelf);
				}
				if(shelfAddress.contains(String.valueOf(EbuyDict.WlAppType.Jfq_Light_App))){
					// 查询轻应用是否存在：不存在，插入；存在，update
					paramMap.clear();
					paramMap.put("epId", epId);
					paramMap.put("appType", EbuyDict.WlAppType.Jfq_Light_App);
					List<Map<String, Object>> existShelfs = ebuyProductService.getShlefAppType(paramMap);
					if(existShelfs!=null && existShelfs.size()>0){
						for(Map<String, Object> shelfIds:existShelfs){
							EbuyProductShelf ebuyProductShelf = ebuyProductShelfBaseService.getEbuyProductShelfBySeqId(new BigInteger(shelfIds.get("id").toString())); 
							ebuyProductShelf.setUpShelfState(0);
							ebuyProductShelf.setSys0UpdTime(now);
							ebuyProductShelf.setSys0UpdUser(userId);
							ebuyProductShelf.setHandTime(now);
							updateEbuyProductShelfs.add(ebuyProductShelf);
						}
					} else {
						if(ebuyProductTypes!=null && ebuyProductTypes.size()>0){
							EbuyProductShelf ebuyProductShelf = new EbuyProductShelf();
							ebuyProductShelf.setId(epId);
							ebuyProductShelf.setPrice(product.getPrice());
							ebuyProductShelf.setPriceDiscount(product.getPriceDiscount());
							ebuyProductShelf.setSort(epId.longValue());
							ebuyProductShelf.settEbuyProductId(product.getId());
							ebuyProductShelf.settEbuyProductTypeId(ebuyProductTypes.get(0).getId());
							ebuyProductShelf.setUpShelfState(0);
							ebuyProductShelf.setHandTime(now);
							ebuyProductShelf.setSys0AddTime(now);
							ebuyProductShelf.setSys0AddUser(userId);
							
							paramMap.clear();
							paramMap.put("tEbuyProductId", epId);
							List<EbuyProductShelf> ebuyProductShelfOnApps = ebuyProductShelfBaseService.getEbuyProductShelfByCondition(paramMap);
							if(ebuyProductShelfOnApps!=null && ebuyProductShelfOnApps.size()>0){
								ebuyProductShelf.setApplyTime(ebuyProductShelfOnApps.get(0).getApplyTime());
							}
							ebuyProductShelf.setSys0DelState(0);
							insertEbuyProductShelfs.add(ebuyProductShelf);
						}
					}
				}
			} else if(map.get("wlAppType").toString().equals(String.valueOf(EbuyDict.WlAppType.Jfq_Light_App))){
				if(!shelfAddress.contains(String.valueOf(EbuyDict.WlAppType.Jfq_Light_App))){
					EbuyProductShelf ebuyProductShelf = ebuyProductShelfBaseService.getEbuyProductShelfBySeqId(new BigInteger(map.get("shelfId").toString()));
					ebuyProductShelf.setUpShelfState(1);
					ebuyProductShelf.setSys0UpdTime(now);
					ebuyProductShelf.setSys0UpdUser(userId);
					ebuyProductShelf.setHandTime(now);
					updateEbuyProductShelfs.add(ebuyProductShelf);
				} else {
					EbuyProductShelf ebuyProductShelf = ebuyProductShelfBaseService.getEbuyProductShelfBySeqId(new BigInteger(map.get("shelfId").toString()));
					ebuyProductShelf.setUpShelfState(0);
					ebuyProductShelf.setSys0UpdTime(now);
					ebuyProductShelf.setSys0UpdUser(userId);
					ebuyProductShelf.setHandTime(now);
					updateEbuyProductShelfs.add(ebuyProductShelf);
				}
				if(shelfAddress.contains(String.valueOf(EbuyDict.WlAppType.Jfq))){
					// app：status=0
					paramMap.clear();
					paramMap.put("epId", map.get("epId"));
					paramMap.put("appType", EbuyDict.WlAppType.Jfq);
					List<Map<String, Object>> existShelfs = ebuyProductService.getShlefAppType(paramMap);
					if(existShelfs!=null && existShelfs.size()>0){
						for(Map<String, Object> shelfIds:existShelfs){
							EbuyProductShelf ebuyProductShelf = ebuyProductShelfBaseService.getEbuyProductShelfBySeqId(new BigInteger(shelfIds.get("id").toString())); 
							ebuyProductShelf.setUpShelfState(0);
							ebuyProductShelf.setSys0UpdTime(now);
							ebuyProductShelf.setSys0UpdUser(userId);
							ebuyProductShelf.setHandTime(now);
							updateEbuyProductShelfs.add(ebuyProductShelf);
						}
					}
				}
			}
		}
		ebuyProductService.upShelf(ebuyProductList, updateEbuyProductShelfs, insertEbuyProductShelfs);
		cleanCache();
		return "上架成功!!!";
	}
	
	/**
	 * 上架审核
	 * @param epId
	 * @param auditResult
	 * @param auditReason
	 * @return
	 */
	@RequestMapping("/auditBatchUpShelf.html")
	public @ResponseBody String auditBatchUpShelf(String epId,String auditResult,String auditReason){
		BigInteger userId = UserContext.getOperIdBigInteger();
		String now = CnfantasiaCommUtil.getCurrentTime();
		if(auditResult != null && !auditResult.equals("") && auditResult.equals("pass")){
			EbuyProduct product = ebuyProductService.getEbuyProductBySeqId(CnfantasiaCommUtil.convert2BigInteger(epId));
			product.setStatus(0);
			product.setStatusAudit(5);
			product.setUpShelfTime(CnfantasiaCommUtil.getCurrentTime());
			product.setSys0UpdTime(now);
			product.setSys0UpdUser(userId);
			ebuyProductService.updateEbuyProduct(product);
		}else if(auditResult != null && !auditResult.equals("") && auditResult.equals("nopass")){
			EbuyProduct product = ebuyProductService.getEbuyProductBySeqId(CnfantasiaCommUtil.convert2BigInteger(epId));
			product.setStatusAudit(4);
			product.setUpShelfTime(CnfantasiaCommUtil.getCurrentTime());
			product.setSys0UpdTime(now);
			product.setSys0UpdUser(userId);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("auditId", CnfantasiaCommUtil.convert2BigInteger(epId));
			paramMap.put("auitReason", auditReason);
			ebuyProductService.addEbuyProductAudit(paramMap);
			ebuyProductService.updateEbuyProduct(product);
		}		
		cleanCache();
		return "审核成功!!!";
	}
	/**
	 * 下架批量下架
	 * @param params  json数组===>{"epId":"", "shelfId":""}
	 * @return
	 */
	@RequestMapping("/downEbuyProduct.html")
	@ResponseBody
	public  JsonResponse downEbuyProduct(String params){
		List<Map> paramList = JSONArray.parseArray(params, Map.class);
		
		String now = DateUtils.getCurrentDate();
		BigInteger userId = UserContext.getOperIdBigInteger();
		List<EbuyProductShelf>  ebuyProductShelfs = new ArrayList<EbuyProductShelf>();
		List<EbuyProduct>  ebuyProducts = new ArrayList<EbuyProduct>();
		for(Map map:paramList){
			// 判断t_ebuy_product：f_status是否设置为1
			int count = 0;
			for(Map mapTmp:paramList){
				if(mapTmp.get("epId").toString().equals(map.get("epId").toString())){
					++count;
				}
			}
			BigInteger epId = new BigInteger(map.get("epId").toString());
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tEbuyProductId", epId);
			paramMap.put("sys0DelState", 0);
			paramMap.put("upShelfState", 0);
			int shelfCount = ebuyProductShelfBaseService.getEbuyProductShelfCount(paramMap);
			if(count>=shelfCount){
				EbuyProduct ebuyProduct = ebuyProductService.getEbuyProductBySeqId(epId);
				ebuyProduct.setStatus(1);
				ebuyProduct.setStatusAudit(5);
				ebuyProduct.setIsHotSale(EbuyDict.Product_Hot_Sale_Status.Not_Hot_Sale);
				ebuyProduct.setDownShelfTime(now);
				ebuyProduct.setSys0UpdTime(now);
				ebuyProduct.setSys0UpdUser(userId);
				ebuyProducts.add(ebuyProduct);
			}
			
			EbuyProductShelf ebuyProductShelf = ebuyProductShelfBaseService.getEbuyProductShelfBySeqId(new BigInteger(map.get("shelfId").toString()));
			ebuyProductShelf.setUpShelfState(1);
			ebuyProductShelf.setSys0UpdTime(now);
			ebuyProductShelf.setSys0UpdUser(userId);
			ebuyProductShelfs.add(ebuyProductShelf);
		}
		
		JsonResponse jsonResponse = new JsonResponse();
		if(ebuyProductService.downShelf(ebuyProducts, ebuyProductShelfs)){
			jsonResponse.setMessage("操作成功！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
		} else {
			jsonResponse.setMessage("操作失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		return jsonResponse;
	}
	
	/**
	 * 货架管理-编辑价格
	 * 
	 * @param params json数组==>{"epId","", "price":"", "shelfId":""}
	 * @return
	 */
	@RequestMapping("/editprices.html")
	@ResponseBody
	public JsonResponse editPrices(String params){
		List<EbuyProduct> ebuyProducts = new ArrayList<EbuyProduct>();
		List<EbuyProductShelf> ebuyProductShelfs = new ArrayList<EbuyProductShelf>();
		String now = DateUtils.getCurrentDate();
		BigInteger userId = UserContext.getOperIdBigInteger();
		
		List<Map> paramList = JSONArray.parseArray(params, Map.class);
		for(Map map:paramList){
			Double price = Double.valueOf(map.get("price").toString())*100;
			
			EbuyProduct product = new EbuyProduct();
			product.setId(new BigInteger(map.get("epId").toString()));
			product.setPriceDiscount(price.longValue());
			product.setUpShelfTime(now);
			product.setSys0UpdTime(now);
			product.setSys0UpdUser(userId);
			ebuyProducts.add(product);
			
			EbuyProductShelf productShelf = new EbuyProductShelf();
			productShelf.setId(new BigInteger(map.get("shelfId").toString()));
			productShelf.setPriceDiscount(price.longValue());
			productShelf.setSys0UpdTime(now);
			productShelf.setSys0UpdUser(userId);
			ebuyProductShelfs.add(productShelf);
		}
		
		JsonResponse jsonResponse = new JsonResponse();
		if(ebuyProductService.updateShelfPrice(ebuyProducts, ebuyProductShelfs)){
			jsonResponse.setMessage("操作成功！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
		} else {
			jsonResponse.setMessage("操作失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		return jsonResponse;
	}
	/**
	 * 批量修改商品名称
	 * @param names
	 * @param epIds
	 * @return
	 */
	@RequestMapping("/savenames.html")
	public @ResponseBody String editnames(String names,String epIds){
		if(epIds!=null && !epIds.equals("") && names!=null && !names.equals("")){
			BigInteger userId = UserContext.getOperIdBigInteger();
			List<String> result = Arrays.asList(epIds.split(","));
			if (UserContext.getCurrUser().getIsadmin() != 1 && !DataUtil.isEmpty(result)) {
				if (!ebuyProductService.isProductOwner(userId, new BigInteger(result.get(0)))) {
					return "暂无修改权限";
				}
			}
			List<String> nameList = Arrays.asList(names.split(","));
			EbuyProduct product = new EbuyProduct();

			String now = CnfantasiaCommUtil.getCurrentTime();
			for (int i = 0; i < result.size(); i++) {
				product.setId(CnfantasiaCommUtil.convert2BigInteger(result.get(i)));
				product.setName(nameList.get(i));
				product.setSys0UpdTime(now);
				product.setSys0UpdUser(userId);
				ebuyProductService.updateEbuyProduct(product);
			}
		}
		return "保存成功";
	}
	/**
	 * 统一处理List和Search请求
	 * 
	 * @param request
	 * @param paramMap
	 *            请求参数
	 */
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		initSelectData(request, paramMap);
		
		int resultSize = ebuyProductService.getProductList_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);
		PageUtils.addPageInfoToParam(request, paramMap);
		List<EbuyProductEntity> searchRestList = ebuyProductService.getProductList_forPage(paramMap);
		String experienceStoreId = sysParamManager.getSysParaValue(SysParamKey.Experience_Store_Id);
		request.setAttribute("resList", searchRestList);
		request.setAttribute("experienceStoreId", experienceStoreId);
	}
	
	/**
	 * 查找
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) throws UnsupportedEncodingException {
		String state = ParamUtils.getString(request, "state", "proList");
		String name = ParamUtils.getString(request, "name", null);// request.getParameter("name");
		Long tEbuyProductTypeFId = ParamUtils.getLong(request, "tEbuyProductTypeFId", null); //request.getParameter("tEbuyProductTypeFId");
		Integer supplyMerchantType = ParamUtils.getInteger(request, "supplyMerchantType", null);
		Long tSupplyMerchantFId = ParamUtils.getLong(request, "tSupplyMerchantFId", null); //request.getParameter("tSupplyMerchantFId");
		Integer status = ParamUtils.getInteger(request, "status", null); //request.getParameter("status");
		String upShelfTime_START = request.getParameter("upShelfTime_START");
		String upShelfTime_END = request.getParameter("upShelfTime_END");
		String sys0UpdTime_START = request.getParameter("sys0UpdTime_START");
		String sys0UpdTime_END = request.getParameter("sys0UpdTime_END");
		String orderType = request.getParameter("orderType");
		String appType = ParamUtils.getString(request, "appType", null);
		BigInteger ebuyHomeType = ParamUtils.getBigInteger(request, "ebuyHomeType", null);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(UserContext.getCurrUser().getIsadmin() != 1) {
			paramMap.put("supplyMerchantList", UserContext.getSupplyMerchantList());
		}
		orderType = StringUtils.isEmpty(orderType) ? EbuyProductConstant.OrderType.DEFAULT_ORDER : orderType;
		paramMap.put("orderType", orderType);
		paramMap.put("name", (name!=null)?name.trim():null);
		paramMap.put("ebuyProductTypeId", tEbuyProductTypeFId);
		paramMap.put("tSupplyMerchantFId", tSupplyMerchantFId);
		paramMap.put("supplyMerchantType", supplyMerchantType);
		if(!"请选择起始时间".equals(upShelfTime_START)) {
			paramMap.put("upShelfTime_START", upShelfTime_START);
		}
		if(!"请选择结束时间".equals(upShelfTime_END)) {
			paramMap.put("upShelfTime_END", upShelfTime_END);
		}
		if(!"请选择起始时间".equals(sys0UpdTime_START)) {
			paramMap.put("sys0UpdTime_START", sys0UpdTime_START);
		}
		if(!"请选择结束时间".equals(sys0UpdTime_END)) {
			paramMap.put("sys0UpdTime_END", sys0UpdTime_END);
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orderType", orderType);
		paramMap.put("state", state);
		if(state.equals("shelf")){
			paramMap.put("statusAudit", Integer.valueOf(EbuyProductConstant.ProductAuditStatus.AUDIT_STATUS_PASSED));
			paramMap.put("status", Integer.valueOf(EbuyProductConstant.ProductStatus.STATUS_ONSHELF));
			//paramMap.put("APP",true);
			paramMap.put("orderbytime", "upshelfTime");
			paramMap.put("appType", appType);
			paramMap.put("ebuyHomeType", ebuyHomeType);
			handleListOrSearch(request, paramMap);
			String laUrl = sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL);
			modelAndView.addObject("laUrl", laUrl);
			modelAndView.addObject("isAdmin", UserContext.getCurrUser().getIsadmin());
			modelAndView.setViewName("/ebuyProduct/goodsShelf");
		}else{
			if(StringUtils.isEmpty(status)){
				
			} else if(status == 0 || status == 1) {
				paramMap.put("statusAudit", Integer.valueOf(EbuyProductConstant.ProductAuditStatus.AUDIT_STATUS_PASSED));
				paramMap.put("status", status);
			} else {
				paramMap.put("statusAudit", status);
				paramMap.put("status", 1);
			}
			handleListOrSearch(request, paramMap);
			modelAndView.setViewName("/ebuyProduct/ebuyProductList");
		}
		return modelAndView;
	}
	
	/**
	 * 查看明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewDetail.html")
	public ModelAndView viewDetail(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		initSelectData(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/ebuyProduct/ebuyProductDetail");
		return modelAndView;
	}
	
	@RequestMapping("/deleteProduct.html")
	@ResponseBody
	public Object deleteProduct(BigInteger productId,String epIds) throws Exception {
		JsonResponse message = new JsonResponse();
		List<String> idList = new ArrayList<String>();
		if(epIds!=null && !epIds.equals("")){
			idList = Arrays.asList(epIds.split(","));
			for(String id :idList){
				EbuyProduct product = new EbuyProduct();
				product.setId(CnfantasiaCommUtil.convert2BigInteger(id));
				CnfantasiaCommUtil.deleteStandard(product);
				ebuyProductService.updateEbuyProduct(product);
			}
			return "删除成功";
		}else{
			try {
				EbuyProduct ebuyProduct = new EbuyProduct();
				ebuyProduct.setId(productId);
				CnfantasiaCommUtil.deleteStandard(ebuyProduct);
				
				ebuyProductService.updateEbuyProduct(ebuyProduct);
				message.setMessage("删除产品成功");
				message.setStatus("info");
			} catch (RuntimeException re) {
				message.setMessage("删除产品失败:" + re.getMessage());
				message.setStatus("info");
			} catch (Exception e) {
				logger.error(e);
				message.setMessage("删除产品失败:" + e.getMessage());
				message.setStatus("error");
			}
		}
		return message;
	}
	
	/**
	 * 上下架、删除
	 * @param productId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/onOffProduct.html")
	@ResponseBody
	public Object onOffProduct(String productId, Integer statusAudit) throws Exception {
		String[] productIds = productId.split(",");
		String now = CnfantasiaCommUtil.getCurrentTime();
		BigInteger userId = UserContext.getOperIdBigInteger();
		
		List<EbuyProduct> ebuyProductList = new ArrayList<EbuyProduct>();
		List<EbuyProductShelf> ebuyProductShelfs = new ArrayList<EbuyProductShelf>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		for(String id:productIds){
			BigInteger epId = new BigInteger(id);
			EbuyProduct ebuyProduct = ebuyProductService.getEbuyProductBySeqId(epId);
			ebuyProduct.setStatus(1);
			if(statusAudit == 3) { //上架
				ebuyProduct.setUpShelfTime(now);
			} else { //下架
				ebuyProduct.setDownShelfTime(now);
			}
			ebuyProduct.setStatusAudit(statusAudit);
			ebuyProduct.setSys0UpdTime(now);
			ebuyProduct.setSys0UpdUser(userId);
			ebuyProductList.add(ebuyProduct);
			
			paramMap.put("tEbuyProductId", epId);
			List<EbuyProductShelf> ebuyProductShelfTmps = ebuyProductShelfBaseService.getEbuyProductShelfByConditionDim(paramMap);
			for(EbuyProductShelf shelf:ebuyProductShelfTmps){
				if(statusAudit == 3) {shelf.setApplyTime(now);}
				ebuyProductShelfs.add(shelf);
			}
		}
		JsonResponse message = new JsonResponse();
		if(ebuyProductService.onOffProduct(ebuyProductList, ebuyProductShelfs)){
			message.setMessage("删除产品成功");
			message.setStatus("info");
			cleanCache();
		} else {
			message.setMessage("删除产品失败");
			message.setStatus("info");
		}
		
		return message;
	}

	private void initSelectData(HttpServletRequest request, Map<String, Object> paramMap) {
		productTypeList = ebuyProductTypeService.getEbuyProductTypeByCondition(null);
		request.setAttribute("productTypeList", productTypeList);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", 2);// 类型:1为粮票;2首页运营主题
		List<EbuyHomeType> ebuyHomeTypes = ebuyHomeTypeBaseService.getEbuyHomeTypeByCondition(param);
		request.setAttribute("ebuyHomeTypes",ebuyHomeTypes);

		if(UserContext.getCurrUser().getIsadmin() == 1) {
			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤掉审核未通过的
			params.put("storeAuditStatus", 1);
			supplyMerchantList = ebuySupplyMerchantBaseService.getEbuySupplyMerchantByCondition(params);
			request.setAttribute("supplyMerchantList", supplyMerchantList);
		} else {
			request.setAttribute("supplyMerchantList", UserContext.getSupplyMerchantList());
			paramMap.put("supplyMerchantList", UserContext.getSupplyMerchantList());
		}
	}

	/**
	 * 刷新API接口的缓存，使其立即生效
	 */
	private void cleanCache() {
		try {
			HttpUtil httpUtil = new HttpUtil();
			httpUtil.post(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + "ebuyNew/cleanCache.json", 10000, "UTF-8");
		} catch (Exception e) {
		}
	}
	
	/**
	 * 商品autocomplete框后台实现
	 * 
	 * @param epName
	 * @return
	 */
	@RequestMapping("/epFilter.html")
	@ResponseBody
	public JsonResponse epFilter(String epName){
		List<Map<String, Object>> eps = ebuyProductService.epFilter(epName);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(eps);
		
		return jsonResponse;
	}
	
	/**
	 * 批量导入体验店商品
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/goJfqStoreBatchImport.html")
	public ModelAndView goJfqStoreBatchImport() throws IOException {
		return new ModelAndView("/ebuyProduct/ebuyProductBatchImport");
	}
	
	/**
	 * 上传体验店商品类型
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/uploadJfqStoreProductType.json")
	@ResponseBody
	public JsonResponse uploadJfqStoreProductType(HttpServletRequest request) throws IOException {
		JsonResponse jsonResponse = new JsonResponse();
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
			String fileName = uploadExcelfile.getOriginalFilename();
			Workbook wb = null;
			if(fileName.toLowerCase().endsWith(".xls")) {
				wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
			} else if (fileName.toLowerCase().endsWith(".xlsx")) {
				wb = new XSSFWorkbook(uploadExcelfile.getInputStream());
			} else {
				jsonResponse.setMessage("请上传Excel文件！");
				jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
				return jsonResponse;
			}
			
			Sheet sheet = wb.getSheetAt(0);
			ResultMsg resultMsg= ebuyProductService.checkTitle(sheet.getRow(0));
			if(!resultMsg.isSuccess()) {
				jsonResponse.setMessage(resultMsg.getMsg());
				jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
				return jsonResponse;
			}
			
			Set<String> typeSet = new HashSet<String>();
			for(int i = 1; i < sheet.getLastRowNum() + 1; i++){
				Row row = sheet.getRow(i);; 
				if(row == null || row.getCell(0)==null) { //空行，跳过
					continue;
				}
				
				//没有条码，没有商品名称，认为是无效行，跳过
				String code = HSSFCellUtil.getStringValue(row.getCell(0));
				String name = HSSFCellUtil.getStringValue(row.getCell(1));
				if(StringUtils.isEmpty(code) || StringUtils.isEmpty(name)) {
					continue;
				}
				
				String typeName = HSSFCellUtil.getStringValue(row.getCell(6));
				if(org.apache.commons.lang.StringUtils.isBlank(typeName)) {
					jsonResponse.setMessage("【列表】不能为空！");
					jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
					return jsonResponse;
				}
				typeSet.add(typeName);
			}
			
			if(typeSet.size()==0) {
				jsonResponse.setMessage("没有能满足导入条件的数据，请检查后重新上传！");
				jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
				return jsonResponse;
			} 
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("typeSet", typeSet);
			List<JfqStoreProductTypeInfoEntity> jfqStoreProductTypeInfoList = ebuyProductService.selectExistedJfqStoreProductTypeInfo(paramMap);
			for(String typeName:typeSet) {
				boolean isExisted = false;
				for(JfqStoreProductTypeInfoEntity jfqStoreProductTypeInfo:jfqStoreProductTypeInfoList) {
					if(typeName.equals(jfqStoreProductTypeInfo.getName())) {
						isExisted = true;
						break;
					}
				}
				
				if(!isExisted) {
					JfqStoreProductTypeInfoEntity JfqStoreProductTypeInfo = new JfqStoreProductTypeInfoEntity();
					JfqStoreProductTypeInfo.setName(typeName);
					jfqStoreProductTypeInfoList.add(JfqStoreProductTypeInfo);
				}
			}
			
			EbuyProductType eptQry = new EbuyProductType();
			eptQry.setSys0DelState(0);
			eptQry.setWlappType(1L);
			eptQry.setPointType(1);
			List<EbuyProductType> ebuyProductTypeList = ebuyProductTypeService.getEbuyProductTypeByCondition(MapConverter.toMap(eptQry));
			
			jsonResponse.putData("jfqStoreProductTypeInfoList", jfqStoreProductTypeInfoList);
			jsonResponse.putData("ebuyProductTypeList", ebuyProductTypeList);
			return jsonResponse;
		} else {
			jsonResponse.setMessage("请上传Excel文件！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			return jsonResponse;
		}
	}
}
