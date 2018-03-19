package com.cnfantasia.server.ms.ebuyProductTemp.importter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.dao.EbuyProductParametersTempBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.entity.EbuyProductParametersTemp;
import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;
import com.cnfantasia.server.ms.ebuyProductTemp.dao.EbuyProductTempDao;
import com.cnfantasia.server.ms.ebuyProductTemp.entity.EbuyProductTempEntity;
import com.cnfantasia.server.ms.pub.utils.DateUtil;

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
		};

		//映射解放区的商品分类
		String[] mappingJFQProductType = new String[] { "2", "1", "10", "10", "9", "9" };

		int totalPage = 30; //预计每一类有30页数据
		List<String> srcIdList = new ArrayList<String>();

		for (int i = 0; i < allCats.length; i++) {
			for (int page = 1; page < totalPage; page++) {
				Document doc = Jsoup.connect(String.format(urlString, allCats[i], page)).get();
				if (doc.select("h2").last().text().equals("很抱歉，没有找到相关商品。")) {//没找到更多商品，表明该类下没有更多数据
					break;
				}

				Elements elsPrice = doc.select("span.pro-price"); //价格
				Elements elsPriceOrg = doc.select("div.pro-org del");//市场价
				Elements elsProName = doc.select("div.proList div.pro-name a");//每一页所有产品列表

				for (int j = 0; j < elsPrice.size(); j++) {
					EbuyProductTempEntity product = new EbuyProductTempEntity();
					String proDetailUrl = elsProName.get(j).attr("abs:href");
					String srcId = proDetailUrl.substring(proDetailUrl.indexOf("id=") + 3, proDetailUrl.indexOf("&"));
					srcIdList.add(srcId);
					product.setSrcId(new BigInteger(srcId));
					double d = 1.1;//没有市场价时，市场价默认上浮10%
					{//在数据库里先查是否已下拉数据，若有，只更新其价格
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("srcId", product.getSrcId());
						paramMap.put("fromWhere", SupplyMerchantCode);
						paramMap.put("srcProductTypeId", allCats[i]);
						List<EbuyProductTemp> ptList = ebuyProductTempDao.selectEbuyProductTempByCondition(paramMap, false);
						if (ptList.size() > 0) {
							EbuyProductTemp ept = ptList.get(0);
							ept.setPriceDiscount((long) (Double.parseDouble(elsPrice.get(j).text().substring(1)) * 100)); //去掉第一个字符 ¥ 
							if ((long) Double.parseDouble(elsPriceOrg.get(j).text().substring(1)) * 100 > 0) {
								ept.setPrice((long) Double.parseDouble(elsPriceOrg.get(j).text().substring(1)) * 100);//去掉第一个字符 ¥ 
							} else {//没有市场价时，市场价默认上浮10%
								ept.setPrice((long) (Double.parseDouble(elsPrice.get(j).text().substring(1)) * d) * 100);
							}
							ept.setName(elsProName.get(j).attr("title"));
							ebuyProductTempDao.updateEbuyProductTemp(ept);
							continue;
						}
					}

					product.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_temp));
					product.setName(elsProName.get(j).attr("title"));
					product.setPriceDiscount((long) (Double.parseDouble(elsPrice.get(j).text().substring(1)) * 100)); //去掉第一个字符 ¥ 
					if ((long) Double.parseDouble(elsPriceOrg.get(j).text().substring(1)) * 100 > 0) {
						product.setPrice((long) Double.parseDouble(elsPriceOrg.get(j).text().substring(1)) * 100);//去掉第一个字符 ¥ 
					} else {//没有市场价时，市场价默认上浮10%
						product.setPrice((long) (Double.parseDouble(elsPrice.get(j).text().substring(1)) * d) * 100);
					}

					product.setSrcProductTypeId(new BigInteger(allCats[i]));
					product.settEbuyProductTypeFId(new BigInteger(mappingJFQProductType[i]));
					product.setFromWhere(SupplyMerchantCode);
					product.settSupplyMerchantFId(new BigInteger("200"));// 200是海吉星商场
					product.setCreateTime(DateUtil.formatSecond.format(new Date()));
					product.setStatus(0);//默认是上架状态
					product.setIsSync(0);//初始是未同步

					{//进入每个商品的详情信息页， 获取商品详情
						Document doc_proDetailPage = Jsoup.connect(proDetailUrl).get();
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

						{//下载产品图片
							Response resultImageResponse = Jsoup.connect(productImageLocation).ignoreContentType(true).execute();
							
							String productPicPath = "/productPic/";

							productPicPath = serverRealPath + productPicPath;
							// output here
							FileOutputStream out = new FileOutputStream(new java.io.File(productPicPath
									+ productImageLocation.substring(productImageLocation.lastIndexOf("/") + 1)));
							out.write(resultImageResponse.bodyAsBytes());
							// resultImageResponse.body() is where the image's contents are.
							out.close();
						}

						Elements els_baseInfos = doc_proDetailPage.select("div.basic-info ul li p ");//基本信息
						List<EbuyProductParametersTemp> prdtParamList = new ArrayList<EbuyProductParametersTemp>(els_baseInfos.size());
						List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_parameters_temp, els_baseInfos.size());
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

						ebuyProductTempDao.insertEbuyProductTemp(product);
						if (prdtParamList.size() > 0) {
							ebuyProductParametersTempBaseDao.insertEbuyProductParametersTempBatch(prdtParamList);
						}
					}
				}
			}
		}

		if (srcIdList.size() > 0) {//与商城的商品对比，更新上下架状态
			ebuyProductTempDao.updateShelfStatus(srcIdList);
		}
	}
}
