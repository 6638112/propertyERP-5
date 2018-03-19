package com.cnfantasia.server.ms.ebuyProduct.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cnfantasia.server.api.bankCollection.entity.ResultMsg;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.sysParam.SysParamManager;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HSSFCellUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.entity.EbuyHomeTypeHasProduct;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProductDto;
import com.cnfantasia.server.domainbase.ebuyProduct.service.EbuyProductBaseService;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;
import com.cnfantasia.server.domainbase.ebuyProductType.dao.EbuyProductTypeBaseDao;
import com.cnfantasia.server.domainbase.jfqStoreProductTypeInfo.dao.JfqStoreProductTypeInfoBaseDao;
import com.cnfantasia.server.domainbase.jfqStoreProductTypeInfo.entity.JfqStoreProductTypeInfo;
import com.cnfantasia.server.ms.ebuyHomeTypeHasProduct.dao.IEbuyHomeTypeHasProductDao;
import com.cnfantasia.server.ms.ebuyProduct.constant.EbuyProductConstant;
import com.cnfantasia.server.ms.ebuyProduct.dao.IEbuyProductDao;
import com.cnfantasia.server.ms.ebuyProduct.entity.EbuyProductEntity;
import com.cnfantasia.server.ms.ebuyProduct.entity.EbuyProductImport;
import com.cnfantasia.server.ms.ebuyProduct.entity.JfqStoreProductTypeInfoEntity;
import com.cnfantasia.server.ms.ebuyProductIntroducePic.dao.IEbuyProductIntroducePicDao;
import com.cnfantasia.server.ms.ebuyProductParameters.dao.IEbuyProductParametersDao;
import com.cnfantasia.server.ms.ebuyProductPic.dao.IEbuyProductPicDao;
import com.cnfantasia.server.ms.ebuyProductShelf.dao.IEbuyProductShelfDao;
import com.cnfantasia.server.ms.limitBuyActivity.dao.LimitBuyActivityDao;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class EbuyProductService extends EbuyProductBaseService implements IEbuyProductService {
	@Resource
	private LimitBuyActivityDao limitBuyActivityDao;
	
	private IEbuyProductDao ebuyProductDao;

	private IEbuyProductParametersDao ebuyProductParametersDao;
	
	private IEbuyProductIntroducePicDao ebuyProductIntroducePicDao;
	
	private IEbuyProductPicDao ebuyProductPicDao;
	
	private IEbuyHomeTypeHasProductDao ebuyHomeTypeHasProductDao;
	
	private IEbuyProductShelfDao ebuyProductShelfDao;
	
	@Resource
	private EbuyProductTypeBaseDao ebuyProductTypeBaseDao;
	@Resource
	private JfqStoreProductTypeInfoBaseDao jfqStoreProductTypeInfoBaseDao;
	@Resource
	private IUuidManager uuidManager;
	
	public void setEbuyProductDao(IEbuyProductDao ebuyProductDao) {
		this.ebuyProductDao = ebuyProductDao;
	}

	public void setEbuyProductPicDao(IEbuyProductPicDao ebuyProductPicDao) {
		this.ebuyProductPicDao = ebuyProductPicDao;
	}

	public void setEbuyHomeTypeHasProductDao(IEbuyHomeTypeHasProductDao ebuyHomeTypeHasProductDao) {
		this.ebuyHomeTypeHasProductDao = ebuyHomeTypeHasProductDao;
	}

	public void setEbuyProductShelfDao(IEbuyProductShelfDao ebuyProductShelfDao) {
		this.ebuyProductShelfDao = ebuyProductShelfDao;
	}

	public void setEbuyProductParametersDao(IEbuyProductParametersDao ebuyProductParametersDao) {
		this.ebuyProductParametersDao = ebuyProductParametersDao;
	}

	public void setEbuyProductIntroducePicDao(IEbuyProductIntroducePicDao ebuyProductIntroducePicDao) {
		this.ebuyProductIntroducePicDao = ebuyProductIntroducePicDao;
	}

	@Override
	public int getProductList_forCount(Map<String, Object> paramMap) {
		return ebuyProductDao.getProductList_forCount(paramMap);
	}
	
	@Override
	public List<EbuyProductEntity> getProductList_forPage(Map<String, Object> paramMap) {
		return ebuyProductDao.getProductList_forPage(paramMap);
	}
	@Override	
	public List<EbuyProductParameters> getEbuyProductParameters(Map<String,Object> paramMap,boolean isDim){
		return this.ebuyProductParametersDao.selectEbuyProductParametersByCondition(paramMap, isDim);
	}

	@Override
	public void addEbuyProductAudit(Map<String, Object> paramMap) {
		Object auditId = paramMap.get("auditId");
		Integer res = ebuyProductDao.selectProductAudit(auditId);
		if(res>=1){
			ebuyProductDao.updateProductAudit(paramMap);
		}else{
			this.ebuyProductDao.insertProductaudit(paramMap);
		}
	}
	
	/**
	 * 货架管理-编辑
	 * @param ebuyProduct
	 * @param ebuyProductShelf
	 * @param ebuyProductParameters
	 * @param ebuyProductIntroducePics
	 * @param ebuyHomeTypeHasProduct
	 * @return
	 */
	@Transactional
	@Override
	public boolean updateProductById(EbuyProduct ebuyProduct, EbuyProductShelf ebuyProductShelf,BigInteger productTypeId, List<EbuyProductParameters> ebuyProductParameters, 
			List<EbuyProductIntroducePic> ebuyProductIntroducePics, List<EbuyProductPic> ebuyProductPicList,  EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct){
		int effectedNum = 0;
		
		// 更新t_ebuy_product信息
		effectedNum += ebuyProductDao.updateEbuyProduct(ebuyProduct);
		
		// 更新t_ebuy_product_shelf字段f_price_discount
		if(ebuyProductShelf!=null){
			effectedNum += ebuyProductShelfDao.updateEbuyProductShelf(ebuyProductShelf);
		}
		
		//更新货架
		EbuyProductShelf eps = new EbuyProductShelf();
		eps.setId(ebuyProductShelf.getId());
		eps.settEbuyProductTypeId(productTypeId);
		effectedNum += ebuyProductShelfDao.updateEbuyProductShelf(eps);
		
		// 更新t_ebuy_product_parameters信息
		// delete olds
		effectedNum += ebuyProductParametersDao.deleteByEPId(ebuyProduct.getId());
		if(ebuyProductParameters!=null && ebuyProductParameters.size()>0){
			// insert news
			effectedNum += ebuyProductParametersDao.insertEbuyProductParametersBatch(ebuyProductParameters);
		}
		
		// 更新t_ebuy_product_introduce_pic信息
		// delete olds
		effectedNum += ebuyProductIntroducePicDao.deleteByProductIdLogic(ebuyProduct.getId());
		if(ebuyProductIntroducePics!=null && ebuyProductIntroducePics.size()>0){
			// insert news
			effectedNum += ebuyProductIntroducePicDao.insertEbuyProductIntroducePicBatch(ebuyProductIntroducePics);
		}
		
		// 更新t_ebuy_product_pic信息
		// delete olds
		effectedNum += ebuyProductPicDao.deleteByProductIdLogic(ebuyProduct.getId());
		if(ebuyProductPicList.size()>0){
			// insert news
			effectedNum += ebuyProductPicDao.insertEbuyProductPicBatch(ebuyProductPicList);
		}
		
		// t_ebuy_home_type_has_product
		effectedNum += ebuyHomeTypeHasProductDao.deleteByProductIdLogic(ebuyProduct.getId());
		if(ebuyHomeTypeHasProduct!=null){
			effectedNum += ebuyHomeTypeHasProductDao.insertEbuyHomeTypeHasProduct(ebuyHomeTypeHasProduct);
		}
		
		return effectedNum >0;
	}
	
	/**
	 * 单品录入
	 * @param ebuyProducts
	 * @param ebuyProductPics
	 * @param ebuyProductIntroducePics
	 * @param ebuyHomeTypeHasProduct
	 * @param ebuyProductShelf
	 * @return
	 */
	@Transactional
	@Override
	public boolean addEbuyProduct(EbuyProductDto ebuyProductDto){
		// insert t_ebuy_product
		EbuyProduct ebuyProduct = ebuyProductDto.getEbuyProduct();
		ebuyProduct.setIsPreSell(0);
		int effectedNum1 = ebuyProductDao.insertEbuyProduct(ebuyProduct);

		List<EbuyProductParameters> parameterses = ebuyProductDto.getParameterses();
		if (parameterses != null && parameterses.size() > 0) {
			ebuyProductParametersDao.insertEbuyProductParametersBatch(parameterses);
		}
		
		// insert t_ebuy_home_type_has_product
		int effectedNum2 = 0;
		EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct = ebuyProductDto.getEbuyHomeTypeHasProduct();
		if(ebuyHomeTypeHasProduct!=null){
			effectedNum2 = ebuyHomeTypeHasProductDao.insertEbuyHomeTypeHasProduct(ebuyHomeTypeHasProduct);
		}
		
		// insert t_ebuy_product_pic 
		int effectedNum3 = 0;
		List<EbuyProductPic> ebuyProductPics = ebuyProductDto.getEbuyProductPics();
		if(ebuyProductPics!=null && ebuyProductPics.size()>0){
			try {	
				effectedNum3 = ebuyProductPicDao.insertEbuyProductPicBatch(ebuyProductPics);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// insert t_ebuy_product_introduce_pic
		int effectedNum4 = 0;
		List<EbuyProductIntroducePic> ebuyProductIntroducePics = ebuyProductDto.getEbuyProductIntroducePics();
		if(ebuyProductIntroducePics!=null && ebuyProductIntroducePics.size()>0){
			try {
				effectedNum4 = ebuyProductIntroducePicDao.insertEbuyProductIntroducePicBatch(ebuyProductIntroducePics);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// insert t_ebuy_product_shelf
		int effectedNum5 = 0;
		EbuyProductShelf ebuyProductShelf = ebuyProductDto.getEbuyProductShelf();
		if(ebuyProductShelf!=null){
			effectedNum5 = ebuyProductShelfDao.insertEbuyProductShelf(ebuyProductShelf);
		}
		
		return (effectedNum1+effectedNum2+effectedNum3+effectedNum4+effectedNum5)>0;
	}
	
	/**
	 * 商品列表-更新
	 * 
	 * @param ebuyProduct
	 * @param ebuyProductPics
	 * @param ebuyProductIntroducePics
	 * @param ebuyHomeTypeHasProduct
	 * @param ebuyProductShelfList
	 * @return
	 */
	@Transactional
	@Override
	public boolean updateEbuyProduct(List<EbuyProductParameters> ebuyProductParameterses,
									 EbuyProduct ebuyProduct, List<EbuyProductPic> ebuyProductPics,
									 List<EbuyProductIntroducePic> ebuyProductIntroducePics,
									 EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct, List<EbuyProductShelf> ebuyProductShelfList){
		int effectedNum = 0;
		try {
			// insert t_ebuy_product
			effectedNum = ebuyProductDao.updateEbuyProduct(ebuyProduct);

			//商品参数处理
			if (!DataUtil.isEmpty(ebuyProductParameterses)) {
				ebuyProductParametersDao.deleteByEPId(ebuyProduct.getId());
				ebuyProductParametersDao.insertEbuyProductParametersBatch(ebuyProductParameterses);
			}
			// t_ebuy_home_type_has_product
			// delete old
			effectedNum += ebuyHomeTypeHasProductDao.deleteByProductIdLogic(ebuyProduct.getId());
			// insert new
			if(ebuyHomeTypeHasProduct!=null){
				effectedNum += ebuyHomeTypeHasProductDao.insertEbuyHomeTypeHasProduct(ebuyHomeTypeHasProduct);
			}
			
			// t_ebuy_product_pic 
			// delete old
			effectedNum += ebuyProductPicDao.deleteByProductIdLogic(ebuyProduct.getId());
			// insert new
			if(ebuyProductPics!=null && ebuyProductPics.size()>0){
				effectedNum += ebuyProductPicDao.insertEbuyProductPicBatch(ebuyProductPics);
			}
			
			// t_ebuy_product_introduce_pic
			// delete old
			effectedNum += ebuyProductIntroducePicDao.deleteByProductIdLogic(ebuyProduct.getId());
			// insert new
			if(ebuyProductIntroducePics!=null && ebuyProductIntroducePics.size()>0){
				effectedNum += ebuyProductIntroducePicDao.insertEbuyProductIntroducePicBatch(ebuyProductIntroducePics);
			}
			
			// t_ebuy_product_shelf
			// delete old
//			effectedNum += ebuyProductShelfDao.deleteByProductIdLogic(ebuyProduct.getId());
			// insert new
			if(ebuyProductShelfList!=null && ebuyProductShelfList.size()>0){
//				effectedNum += ebuyProductShelfDao.insertEbuyProductShelfBatch(ebuyProductShelfList);
				effectedNum += ebuyProductShelfDao.updateEbuyProductShelfBatch(ebuyProductShelfList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return effectedNum>0;
	}
	
	/**
	 * 上架管理-上架/批量上架
	 * @param ebuyProducts
	 * @param updateEbuyProductShelfs
	 * @param insertEbuyProductShelfs
	 */
	@Override
	@Transactional
	public void upShelf(List<EbuyProduct> ebuyProducts, List<EbuyProductShelf> updateEbuyProductShelfs, List<EbuyProductShelf> insertEbuyProductShelfs){
		// update t_ebuy_product
		ebuyProductDao.updateEbuyProductBatch(ebuyProducts);
		// update olds
		if(updateEbuyProductShelfs!=null && updateEbuyProductShelfs.size()>0){
			ebuyProductShelfDao.updateEbuyProductShelfBatch(updateEbuyProductShelfs);
		}
		// insert news
		if(insertEbuyProductShelfs!=null && insertEbuyProductShelfs.size()>0){
			ebuyProductShelfDao.insertEbuyProductShelfBatch(insertEbuyProductShelfs);
		}
	}
	
	/**
	 * 货架管理-批量下架
	 * @param ebuyProducts
	 * @param ebuyProductShelfs
	 * @return
	 */
	@Override
	@Transactional
	public boolean downShelf(List<EbuyProduct> ebuyProducts, List<EbuyProductShelf> ebuyProductShelfs){
		int affectedNum = 0;
		if(ebuyProducts!=null && ebuyProducts.size()>0){
			affectedNum = ebuyProductDao.updateEbuyProductBatch(ebuyProducts);
		}
		affectedNum += ebuyProductShelfDao.updateEbuyProductShelfBatch(ebuyProductShelfs);
		
		//限时购也要同时下架
		if(ebuyProducts.size()>0){
			affectedNum += limitBuyActivityDao.downShelf(ebuyProducts);
		}
		
		return affectedNum>0;
	}
	
	@Override
	public int getShlefCount(Map<String, Object> paramMap) {
		return ebuyProductDao.getShlefCount(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> getShlefAppType(Map<String, Object> paramMap) {
		return ebuyProductDao.getShlefAppType(paramMap);
	}
	
	/**
	 * 货架管理-更新价格
	 * 
	 * @param ebuyProducts
	 * @param ebuyProductShelfs
	 * @return
	 */
	@Override
	@Transactional
	public boolean updateShelfPrice(List<EbuyProduct> ebuyProducts, List<EbuyProductShelf> ebuyProductShelfs){
		int affectedNum = 0;
		if(ebuyProducts!=null && ebuyProducts.size()>0){
			affectedNum = ebuyProductDao.updateEbuyProductBatch(ebuyProducts);
			affectedNum += ebuyProductShelfDao.updateEbuyProductShelfBatch(ebuyProductShelfs);
		} 
		return affectedNum>0;
	}
	
	/**
	 * 查询APP平台上t_ebuy_product表id与t_ebuy_product_shelf表id相同的数量
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	public int queryCountForEpIdEqShelfIdOnApp(BigInteger productId){
		return ebuyProductDao.queryCountForEpIdEqShelfIdOnApp(productId);
	}
	
	/**
	 * 商品autocomplete框后台实现
	 * 
	 * @param gbName
	 * @return
	 */
	@Override
	public List<Map<String, Object>> epFilter(String epName){
		return ebuyProductDao.epFilter(epName);
	}
	
	/**
	 * 商品管理-上、下架
	 * 
	 * @param ebuyProducts
	 * @param ebuyProductShelfs
	 * @return
	 */
	@Override
	@Transactional
	public boolean onOffProduct(List<EbuyProduct> ebuyProducts, List<EbuyProductShelf> ebuyProductShelfs){
		int affectedNum = ebuyProductDao.updateEbuyProductBatch(ebuyProducts);
		if(ebuyProductShelfs!=null && ebuyProductShelfs.size()>0){
			affectedNum += ebuyProductShelfDao.updateEbuyProductShelfBatch(ebuyProductShelfs);
		}
		
		return affectedNum>0;
	}

	@Override
	public boolean isProductOwner(BigInteger userId, BigInteger ebuyProductId) {
		return ebuyProductDao.isProductOwner(userId, ebuyProductId);
	}

	@Resource
	private SysParamManager sysParamManager;
	
	@Override
	@Transactional
	public JsonResponse saveBatchImport4JFQStore(HttpServletRequest request) throws IOException {
		JsonResponse jsonResponse = new JsonResponse();
		if (request instanceof MultipartHttpServletRequest) {
			String[] typeNames = request.getParameterValues("typeName");
			String[] typeIds = request.getParameterValues("typeId");
			String[] sellPriceRates = request.getParameterValues("sellPriceRate");
			Map<String, JfqStoreProductTypeInfoEntity> typesMap = new HashMap<String, JfqStoreProductTypeInfoEntity>();
			for(int i=0; i<typeNames.length; i++) {
				JfqStoreProductTypeInfoEntity JfqStoreProductTypeInfo = new JfqStoreProductTypeInfoEntity();
				JfqStoreProductTypeInfo.setName(typeNames[i]);
				JfqStoreProductTypeInfo.setTypeId(BigInteger.valueOf(Long.valueOf(typeIds[i])));
				JfqStoreProductTypeInfo.setSellPriceRate(BigDecimal.valueOf(Double.valueOf(sellPriceRates[i])));
				if(!typesMap.containsKey(typeNames[i])) {
					typesMap.put(typeNames[i], JfqStoreProductTypeInfo);
				}
			}
			
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
				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
				return jsonResponse;
			}
			Sheet sheet = wb.getSheetAt(0);
			
			List<EbuyProduct> ebuyProductUpdList = new ArrayList<EbuyProduct>();
			List<EbuyProduct> ebuyProductAddNewList = new ArrayList<EbuyProduct>();
			List<EbuyProductImport> epImportList = new ArrayList<EbuyProductImport>();
			List<JfqStoreProductTypeInfo> jfqStoreProductTypeInfoNewList = new ArrayList<JfqStoreProductTypeInfo>();
			List<JfqStoreProductTypeInfo> jfqStoreProductTypeInfoUpdList = new ArrayList<JfqStoreProductTypeInfo>();
			
			//excel校验
			BigInteger storeId = new BigInteger(sysParamManager.getSysParaValue(SysParamKey.Experience_Store_Id));
			String verifyResult =  verifyPpInfoData(sheet, storeId, ebuyProductAddNewList, ebuyProductUpdList, epImportList, typesMap, 
					jfqStoreProductTypeInfoNewList, jfqStoreProductTypeInfoUpdList);
			if(!verifyResult.equals("通过校验")){
				jsonResponse.setMessage("校验失败："+ verifyResult);
				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			} else {
				//导入数据
				int i = 0;
				if(!DataUtil.isEmpty(ebuyProductAddNewList)) {
					CnfantasiaCommbusiUtil.newStandardList(ebuyProductAddNewList, SEQConstants.t_ebuy_product);
					i += ebuyProductDao.insertEbuyProductBatch(ebuyProductAddNewList);
				}

				if(!DataUtil.isEmpty(ebuyProductUpdList)) {
					ebuyProductDao.updateEbuyProductBatch(ebuyProductUpdList);
					i += ebuyProductUpdList.size();
				}
				
				if(!DataUtil.isEmpty(jfqStoreProductTypeInfoUpdList)) {
					jfqStoreProductTypeInfoBaseDao.updateJfqStoreProductTypeInfoBatch(jfqStoreProductTypeInfoUpdList);
				}
				
				if(!DataUtil.isEmpty(jfqStoreProductTypeInfoNewList)) {
					jfqStoreProductTypeInfoBaseDao.insertJfqStoreProductTypeInfoBatch(jfqStoreProductTypeInfoNewList);
				}
				ebuyProductDao.upToShelfProductAfterImported(epImportList);

				jsonResponse.setMessage("成功导入"+ i + "条");
				jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
			}
		}
		return jsonResponse;
	}
	
	public ResultMsg checkTitle(Row row) {
		if(row==null || row.getCell(0)==null || row.getCell(1)==null ||row.getCell(6)==null ||row.getCell(22)==null ||row.getCell(8)==null ||row.getCell(13)==null) {
			new ResultMsg("excel模板不匹配，第1行标题不能为空！", false);
		}
		// 货品条码
		String code = HSSFCellUtil.getStringValue(row.getCell(0));
		if(!"货品条码".equals(code)) {
			return new ResultMsg("excel模板不匹配，第1行第1列应为【货品条码】！", false);
		}
		// 货品名称
		String name = HSSFCellUtil.getStringValue(row.getCell(1));
		if(!"货品名称".equals(name)) {
			return new ResultMsg("excel模板不匹配，第1行第2列应为【货品名称】！", false);
		}
		// 类别
		String type = HSSFCellUtil.getStringValue(row.getCell(6));
		if(!"类别".equals(type)) {
			return new ResultMsg("excel模板不匹配，第1行第7列应为【类别】！", false);
		}
		// 显示单位
		String unit = HSSFCellUtil.getStringValue(row.getCell(22));
		if(!"显示单位".equals(unit)) {
			return new ResultMsg("excel模板不匹配，第1行第23列应为【显示单位】！", false);
		}
		// 零售价
		String sellPrice = HSSFCellUtil.getStringValue(row.getCell(8));
		if(!"零售价".equals(sellPrice)) {
			return new ResultMsg("excel模板不匹配，第1行第9列应为【零售价】！", false);
		}
		// 进货价
		String buyPrice = HSSFCellUtil.getStringValue(row.getCell(10));
		if(!"进货价".equals(buyPrice)) {
			return new ResultMsg("excel模板不匹配，第1行第11列应为【进货价】！", false);
		}
		// 当前库存
		String leftCount = HSSFCellUtil.getStringValue(row.getCell(13));
		if(!"当前库存".equals(leftCount)) {
			return new ResultMsg("excel模板不匹配，第1行第14列应为【当前库存】！", false);
		}
		
		return new ResultMsg(null, true);
	}
	
	private String verifyPpInfoData(Sheet sheet, BigInteger storeId, List<EbuyProduct> ebuyProductAddNewList, List<EbuyProduct> ebuyProductUpdList, List<EbuyProductImport> epImportList,
			Map<String, JfqStoreProductTypeInfoEntity> typesMap,
			List<JfqStoreProductTypeInfo> jfqStoreProductTypeInfoNewList, List<JfqStoreProductTypeInfo> jfqStoreProductTypeInfoUpdList){
		ResultMsg resultMsg= checkTitle(sheet.getRow(0));
		if(!resultMsg.isSuccess()) {
			return resultMsg.getMsg();
		}
		
		String resultInfo =  "通过校验";
		
		for(int i = 1; i < sheet.getLastRowNum() + 1; i++){
			if(sheet.getRow(i) == null || sheet.getRow(i).getCell(0)==null) { //空行，跳过
				continue;
			}
			
			EbuyProductImport epi = new EbuyProductImport();
			epi.setPrdtCode(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)));
			epi.setPrdtName(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)));
			
			//没有条码，没有商品名称，认为是无效行，跳过
			if(StringUtils.isEmpty(epi.getPrdtCode()) || StringUtils.isEmpty(epi.getPrdtName())) { continue; }
			//epi.setShelfState(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2)));
			//epi.setUpShelfState("上架".equals(epi.getShelfState()) ? 0 : 1);
			epi.setUpShelfState(0);// 导入成功的商品自动更新为上架状态
			epi.setShelfName(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(6)));
			epi.setUnitName(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(22)));
			double sellPriceOffLine = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(8));
			epi.setSellPriceOffLine(sellPriceOffLine);
			JfqStoreProductTypeInfoEntity jfqStoreProductTypeInfo = typesMap.get(epi.getShelfName());
			double sellPrice = BigDecimalUtil.roundForDouble(jfqStoreProductTypeInfo.getSellPriceRate().multiply(BigDecimal.valueOf(sellPriceOffLine)), 2);
			epi.setSellPrice(sellPrice);
			double buyPrice = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(10));
			epi.setBuyPrice(buyPrice);
			int leftCount = new BigDecimal(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(13))).intValue();
			epi.setLeftCount(leftCount);			
			
			if(epi.getSellPrice()<=0){
				resultInfo = "第" + (i+1) +"行的销售价必须大于0";
				return resultInfo;
			}
			
			epImportList.add(epi);
		}
		
		List<EbuyProduct> epExistsList = ebuyProductDao.getProductList_forImport(epImportList, storeId);
		for (EbuyProductImport epi : epImportList) {
			for (EbuyProduct ebuyProduct : epExistsList) {
				if (epi.getPrdtCode().equals(ebuyProduct.getCode())) {//如果Code一样，则认为是同一个商品，需要update
					EbuyProduct epUpdate = new EbuyProduct();
					epUpdate.setId(ebuyProduct.getId());
					
					//epUpdate.setName(epi.getPrdtName());
					epUpdate.setPriceUnit(epi.getUnitName());
					epUpdate.setLeftCount(new BigInteger(epi.getLeftCount() + ""));
					epUpdate.setPriceDiscount(PriceUtil.multiply100(epi.getSellPrice()));
					epUpdate.setPurchasePrice(PriceUtil.multiply100(epi.getBuyPrice()));
					long price = getPriceOffline(ebuyProduct.getPrice(), epi.getSellPriceOffLine());
					epUpdate.setPrice(price);
					ebuyProductUpdList.add(epUpdate);
					
					epi.setEbuyProduct(ebuyProduct);
					epi.setFirstImport(0);
					epi.setMarketPrice(BigDecimalUtil.div100(price).doubleValue());
					break;
				}
			}
			
			if(epi.getEbuyProduct() == null){//需新增
				EbuyProduct ebuyProductAddNew = new EbuyProduct();
				ebuyProductAddNew.settSupplyMerchantFId(storeId);
				ebuyProductAddNew.setCreateTime(DateUtils.getCurrentDate());
				ebuyProductAddNew.setSelNum(BigInteger.ZERO);
				ebuyProductAddNew.setPointType(1);
				ebuyProductAddNew.setSpecialProductType(1);
				ebuyProductAddNew.setIsHotSale(0);
				
				ebuyProductAddNew.setName(epi.getPrdtName());
				ebuyProductAddNew.setPriceUnit(epi.getUnitName());
				ebuyProductAddNew.setLeftCount(new BigInteger(epi.getLeftCount() + ""));
				ebuyProductAddNew.setPriceDiscount(PriceUtil.multiply100(epi.getSellPrice()));
				ebuyProductAddNew.setPurchasePrice(PriceUtil.multiply100(epi.getBuyPrice()));
				long price = getPriceOffline(null, epi.getSellPriceOffLine());
				
				ebuyProductAddNew.setPrice(price);
				ebuyProductAddNew.setStatus(epi.getUpShelfState());
				if(epi.getUpShelfState() == 0)
					ebuyProductAddNew.setUpShelfTime(DateUtils.getCurrentDate());
				
				ebuyProductAddNew.setCode(epi.getPrdtCode());
				ebuyProductAddNew.setStatusAudit(EbuyProductConstant.ProductAuditStatus.AUDIT_STATUS_PASSED);
				ebuyProductAddNew.setWlappType(1L);
				ebuyProductAddNew.setIsPreSell(0);
				ebuyProductAddNewList.add(ebuyProductAddNew);
				
				epi.setFirstImport(1);
				epi.setEbuyProduct(ebuyProductAddNew);
				epi.setMarketPrice(BigDecimalUtil.div100(price).doubleValue());
			}
		}
		
		//建立货架类型id映射
		for (EbuyProductImport epi : epImportList) {
			JfqStoreProductTypeInfoEntity jfqStoreProductTypeInfo = typesMap.get(epi.getShelfName());
			epi.setEbuyProductTypeId(jfqStoreProductTypeInfo.getTypeId());
			epi.getEbuyProduct().settEbuyProductTypeFId(jfqStoreProductTypeInfo.getTypeId());
		}
		
		// 类型处理
		Set<String> typeSet = new HashSet<String>();
		for(Map.Entry<String, JfqStoreProductTypeInfoEntity> entry : typesMap.entrySet()) {
			typeSet.add(entry.getKey());
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("typeSet", typeSet);
		List<JfqStoreProductTypeInfoEntity> jfqStoreProductTypeInfoList = selectExistedJfqStoreProductTypeInfo(paramMap);
		String now = DateUtils.getCurrentDate();
		for(Map.Entry<String, JfqStoreProductTypeInfoEntity> entry : typesMap.entrySet()) {
			JfqStoreProductTypeInfoEntity jfqStoreProductTypeInfoEntity = entry.getValue();
			BigInteger id = null;
			boolean isExisted = false;
			for(JfqStoreProductTypeInfoEntity jfqStoreProductTypeInfo:jfqStoreProductTypeInfoList) {
				if(jfqStoreProductTypeInfoEntity.getName().equals(jfqStoreProductTypeInfo.getName())) {
					isExisted = true;
					id = jfqStoreProductTypeInfo.getId();
					break;
				}
			}
			
			JfqStoreProductTypeInfo jfqStoreProductTypeInfo = new JfqStoreProductTypeInfo();
			jfqStoreProductTypeInfo.settEbuyProductTypeFId(jfqStoreProductTypeInfoEntity.getTypeId());
			jfqStoreProductTypeInfo.setSellPriceRate(jfqStoreProductTypeInfoEntity.getSellPriceRate().doubleValue());
			if(isExisted) {
				jfqStoreProductTypeInfo.setId(id);
				jfqStoreProductTypeInfo.setSys0UpdTime(now);
				jfqStoreProductTypeInfo.setSys0UpdUser(UserContext.getOperIdBigIntegerMustExist());
				
				jfqStoreProductTypeInfoUpdList.add(jfqStoreProductTypeInfo);
			} else {
				//jfqStoreProductTypeInfo.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_jfq_store_product_type_info));
				jfqStoreProductTypeInfo.setName(jfqStoreProductTypeInfoEntity.getName());
				jfqStoreProductTypeInfo.setSys0DelState(0);
				jfqStoreProductTypeInfo.setSys0AddTime(now);
				jfqStoreProductTypeInfo.setSys0AddUser(UserContext.getOperIdBigIntegerMustExist());
				
				jfqStoreProductTypeInfoNewList.add(jfqStoreProductTypeInfo);
			}
		}
		
		if(jfqStoreProductTypeInfoNewList!=null && jfqStoreProductTypeInfoNewList.size()>0) {
			List<BigInteger> jfqProductTypeInfoIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_jfq_store_product_type_info, jfqStoreProductTypeInfoNewList.size());
			for(int i=0; i<jfqProductTypeInfoIds.size(); i++) {
				JfqStoreProductTypeInfo jfqStoreProductTypeInfo = jfqStoreProductTypeInfoNewList.get(i);
				jfqStoreProductTypeInfo.setId(jfqProductTypeInfoIds.get(i));
			}
		}
		
		return resultInfo;
	}
	
	private long getPriceOffline(Long price, double sellPriceOffline) {
		long priceOffline = PriceUtil.multiply100(sellPriceOffline);
		if(price==null || price<=priceOffline) {
			// 若如果市场价为空或比销售价还要低，导入时系统会自动调整为【售价】的1.05倍
			return PriceUtil.multiply100(sellPriceOffline * 1.05);
		}
		
		return price;
	}
	
	@Override
	public List<JfqStoreProductTypeInfoEntity> selectExistedJfqStoreProductTypeInfo(Map<String, Object> paramMap){
		return ebuyProductDao.selectExistedJfqStoreProductTypeInfo(paramMap);
	}
}
