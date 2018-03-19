package com.cnfantasia.server.api.ebuyProductTemp.importter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cnfantasia.server.business.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.business.commonBusiness.entity.WidthHeight;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.ImageZipUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.dao.EbuyProductParametersTempBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.entity.EbuyProductParametersTemp;
import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;
import com.cnfantasia.server.ms.ebuyProductTemp.dao.EbuyProductTempDao;
import com.cnfantasia.server.ms.ebuyProductTemp.entity.EbuyProductTempEntity;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 海吉星的商品数据导入实现
 * 
 * @author wenfq
 * 
 */
public class ProductImpotter4HJX extends ProductImporterBase {
	/**
	 * 供应商代号
	 */
	private final static String SupplyMerchantCode = "hjx";

	public ProductImpotter4HJX(IUuidManager uuidManager, EbuyProductTempDao ebuyProductTempDao,
			EbuyProductParametersTempBaseDao ebuyProductParametersTempBaseDao, String serverRealPath) {
		super(uuidManager, ebuyProductTempDao, ebuyProductParametersTempBaseDao, serverRealPath);
	}
	
	public ProductImpotter4HJX(){
		
	}

	private Log logger = LogFactory.getLog(getClass());

	@Override
	public void run() {
		try {
			executeImportTask();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	@Override
	public void executeImportTask() throws IOException {
		String urlString = "http://www.hjxmall.com/sell/list.php?catid=%s&q=&page=%d";

		//要从海吉星抓取的数据分类
		String[] allCats = new String[] { "20230000", //新鲜水果 2
				"20240000",//精品蔬菜 1
				"20170000",//干货特产10
				"20200000",//粮油米面10
				"20270000",//海鲜水产9
				"20280000",//肉禽蛋9
				"20190000",//餐料调料102
				"20210000",//日用百货101
		};

		//映射解放区的商品分类
		String[] mappingJFQProductType = new String[] { "2", "1", "10", "10", "9", "9", "102", "101", };

		int totalPage = 30; //预计每一类有30页数据
		List<String> srcIdList = new ArrayList<String>();

		for (int i = 0; i < allCats.length; i++) {
			for (int page = 1; page < totalPage; page++) {
				Document doc = Jsoup.connect(String.format(urlString, allCats[i], page)).get();
				if (doc.select("h2").last().text().equals("很抱歉，没有找到相关商品。")) {//没找到更多商品，表明该类下没有更多数据
					break;
				}

				Elements elsPrice = doc.select("span.pro-price"); //价格
				//Elements elsPriceOrg = doc.select("div.pro-org del");//市场价
				Elements elsProName = doc.select("div.proList div.pro-name a");//每一页所有产品列表

				for (int j = 0; j < elsPrice.size(); j++) {
					EbuyProductTempEntity product = new EbuyProductTempEntity();
					String proDetailUrl = elsProName.get(j).attr("abs:href");
					String srcId = proDetailUrl.substring(proDetailUrl.lastIndexOf("/") + 1, proDetailUrl.lastIndexOf(".html"));
					srcIdList.add(srcId);
					product.setSrcId(new BigInteger(srcId));
					
					double d = NumberUtils.toDouble(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.ProductPriceMarketRate), 1.1);//没有市场价时，市场价默认上浮10%
					double priceDiscount = Double.parseDouble(elsPrice.get(j).text().substring(1).replace(",", "")) * 100; //售价（要去掉价格中的','），已经乘了100
					// double elsePrice = Double.parseDouble(elsPriceOrg.get(j).text().substring(1)) * 100; //市场价, 去掉第一个字符 ¥
					{//在数据库里先查是否已下拉数据，若有，只更新其价格
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("srcId", product.getSrcId());
						paramMap.put("fromWhere", SupplyMerchantCode);
						paramMap.put("srcProductTypeId", allCats[i]);
						List<EbuyProductTemp> ptList = ebuyProductTempDao.selectEbuyProductTempByCondition(paramMap, false);
						if (ptList.size() > 0) {
							EbuyProductTemp ept = ptList.get(0);
							ept.setPriceDiscount(com.cnfantasia.server.business.pub.utils.NumberUtils.doubleToLong(priceDiscount));
							double price = priceDiscount * d; //市场价
							ept.setPrice(com.cnfantasia.server.business.pub.utils.NumberUtils.doubleToLong(price));
							ept.setName(elsProName.get(j).attr("title"));
							ept.setStatus(0);//既然能同步到商品，那就更新为上架状态，因为可能某段时间海吉星会有下架，再次同步时，又上架了 added by wenfq 2016-01-04
							ebuyProductTempDao.updateEbuyProductTemp(ept);
							
							Document doc_proDetailPage = Jsoup.connect(proDetailUrl).get();
							if ("0".equals(doc_proDetailPage.select("label#maxAm").html())) {//0库存
								logger.info(srcId + " zero stock"); //0库存
								srcIdList.remove(srcId);//此商品要下架
							}
							
							continue;
						}
					}

					product.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_temp));
					product.setName(elsProName.get(j).attr("title"));
					product.setPriceDiscount(com.cnfantasia.server.business.pub.utils.NumberUtils.doubleToLong((priceDiscount))); //去掉第一个字符 ¥ 

					double price = priceDiscount * d; //市场价
					product.setPrice(com.cnfantasia.server.business.pub.utils.NumberUtils.doubleToLong(price));

					product.setSrcProductTypeId(new BigInteger(allCats[i]));
					product.settEbuyProductTypeFId(new BigInteger(mappingJFQProductType[i]));
					product.setFromWhere(SupplyMerchantCode);
					product.settSupplyMerchantFId(new BigInteger("200"));// 200是海吉星商场
					product.setCreateTime(DateUtil.formatSecond.get().format(new Date()));
					product.setStatus(0);//默认是上架状态
					product.setIsSync(0);//初始是未同步

					{//进入每个商品的详情信息页， 获取商品详情
						Document doc_proDetailPage = Jsoup.connect(proDetailUrl).get();
						downloadImage(product, doc_proDetailPage);

						Elements els_baseInfos = doc_proDetailPage.select("div.basic-info ul li p ");//基本信息
						List<EbuyProductParametersTemp> prdtParamList = new ArrayList<EbuyProductParametersTemp>(els_baseInfos.size());
						List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_parameters_temp, els_baseInfos.size() + 1);
						for (int k = 0; k < els_baseInfos.size(); k++) {
							EbuyProductParametersTemp prdtParam = new EbuyProductParametersTemp();
							prdtParamList.add(prdtParam);
							prdtParam.setId(ids.get(k));
							String[] nameValuePair = els_baseInfos.get(k).text().split("：");
							prdtParam.settPropName(nameValuePair[0]);
							if (nameValuePair.length >= 2) {
								prdtParam.settPropValue(nameValuePair[1]);
							}
							prdtParam.settEbuyProductTempFId(product.getId());
						}

						//增加“配送说明”参数
						EbuyProductParametersTemp prdtParam = new EbuyProductParametersTemp();
						prdtParamList.add(prdtParam);
						prdtParam.setId(ids.get(els_baseInfos.size()));
						prdtParam.settPropName("配送");
						if (i == 1) {//蔬菜
							prdtParam.settPropValue("当日20:00前下单，次日全天配送");
						} else {
							prdtParam.settPropValue("当日24:00前下单，次日全天配送");
						}
						prdtParam.settEbuyProductTempFId(product.getId());

						ebuyProductTempDao.insertEbuyProductTemp(product);
						if (prdtParamList.size() > 0) {
							ebuyProductParametersTempBaseDao.insertEbuyProductParametersTempBatch(prdtParamList);
						}

						if ("0".equals(doc_proDetailPage.select("label#maxAm").html())) {//0库存
							logger.info(srcId + " zero stock"); //0库存
							srcIdList.remove(srcId);//此商品要下架
						}
					}
				}
			}
		}

		if (srcIdList.size() > 0) {//与商城的商品对比，更新上下架状态《补充：ebuyProductTemp的上下架状态，会触发ebuyProduct的上下架状态》
			ebuyProductTempDao.updateShelfStatus(srcIdList);
		}
	}

	private void downloadImage(EbuyProductTempEntity product, Document doc_proDetailPage) throws IOException, FileNotFoundException {
		Elements elsImgs = doc_proDetailPage.select("li.mr1 img");
		String productImageLocation = "";
		if (elsImgs.size() > 1) {
			//取商品的第二张大图，不带海吉星Logo图
			productImageLocation = elsImgs.get(1).attr("big");
			if (productImageLocation.contains("pic_no.png")) {//第2张是无图，即http://img.hjxmall.com//NcpManager/img/pic_no.png
				productImageLocation = elsImgs.get(0).attr("big");//再取第一张图片
			}
		} else {
			productImageLocation = elsImgs.get(0).attr("big");
		}
		product.setPicBase(productImageLocation.substring(productImageLocation.lastIndexOf("/") + 1));

		logger.info("product name is: " + product.getName());

		try {//下载产品图片
			Response resultImageResponse = Jsoup.connect(productImageLocation).ignoreContentType(true).execute();
			
			String productPicPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;

			// 商品图片全名（含路径）
			String fullProductFileName = productPicPath + productImageLocation.substring(productImageLocation.lastIndexOf("/") + 1);

			File bigImageFile = new File(fullProductFileName);
			FileOutputStream out = new FileOutputStream(bigImageFile);
			out.write(resultImageResponse.bodyAsBytes());
			// resultImageResponse.body() is where the image's contents are.
			out.close();

			generateMiniImage(bigImageFile);
		} catch (Exception e) {
			logger.info("download photo failure: " + product.getName());
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 生成小图
	 * 
	 * @param bigImageFile
	 */
	private void generateMiniImage(File bigImageFile) {
		Map<String, WidthHeight> guigeList = BusinessModelType.Market.getGuigeList();
		String descDirecBasePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;

		String fileName = bigImageFile.getName();
		int lastPointIndex = fileName.lastIndexOf(".");
		String fileType = fileName.substring(lastPointIndex); //文件后缀名，带.号

		String goalDirectoryFilePath = descDirecBasePath + fileName.substring(0, lastPointIndex);
		
		// 创建小图的目标目录
		File goalFileDir = new File(goalDirectoryFilePath);
		if(!goalFileDir.exists()){
			goalFileDir.mkdirs();
		}

		for (String goalFileName : guigeList.keySet()) {
			String smallIcon = goalDirectoryFilePath + "/" + goalFileName + fileType;
			try {
				WidthHeight tmpWidthHeight = guigeList.get(goalFileName);
				ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), null, tmpWidthHeight.getHeight(), 1f, smallIcon);
				//	String resSmallIcon = ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(),tmpWidth.getWidth(),null, quality, smallIcon);
				//	String resSmallIcon = ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), tmpWidth.getWidth(),tmpWidth.getHeight(), quality, smallIcon);
			} catch (Exception e) {
				logger.info(smallIcon + "create small image failure. ");
			}
		}
	}
}
