package com.cnfantasia.server.api.ebuy.service;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.api.common.service.ICommonLockService;
import com.cnfantasia.server.api.ebuy.dao.IEguDao;
import com.cnfantasia.server.api.ebuy.entity.PushEguOrderEntity;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.business.commonBusiness.entity.WidthHeight;
import com.cnfantasia.server.business.pub.utils.ImageZipUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service.IEbuyDeliveryOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyProduct.dao.IEbuyProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.dao.IEbuyProductParametersTempBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;
import com.cnfantasia.server.ms.ebuyProductTemp.dao.IEbuyProductTempDao;
import com.cnfantasia.server.ms.ebuyProductTemp.entity.EbuyProductTempEntity;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

public class EguService implements IEguService{
	private IEbuyProductTempDao ebuyProductTempDao;
	public void setEbuyProductTempDao(IEbuyProductTempDao ebuyProductTempDao) {
		this.ebuyProductTempDao = ebuyProductTempDao;
	}
	
	private IEbuyProductParametersTempBaseDao ebuyProductParametersTempBaseDao;
	public void setEbuyProductParametersTempBaseDao(IEbuyProductParametersTempBaseDao ebuyProductParametersTempBaseDao) {
		this.ebuyProductParametersTempBaseDao = ebuyProductParametersTempBaseDao;
	}
	
	private IEguDao eguDao;
	public void setEguDao(IEguDao eguDao) {
		this.eguDao = eguDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ICommonLockService commonLockService;
	public void setCommonLockService(ICommonLockService commonLockService) {
		this.commonLockService = commonLockService;
	}
	
	private IEbuyDeliveryOrderBaseService deliveryOrderService;
	public void setDeliveryOrderService(IEbuyDeliveryOrderBaseService deliveryOrderService) {
		this.deliveryOrderService = deliveryOrderService;
	}
	
	private IEbuyProductBaseDao ebuyProductBaseDao;

	public void setEbuyProductBaseDao(IEbuyProductBaseDao ebuyProductBaseDao) {
		this.ebuyProductBaseDao = ebuyProductBaseDao;
	}

	@Override
	public EbuyProductTempEntity getProductTemp(String srcId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<EbuyProductTemp> ebuyProductTempList = ebuyProductTempDao.selectEbuyProductTempByCondition(paramMap, false);
		return null;
	}
	
	@Override
	public Integer updateExpress(Map<String, Object> paramMap) {
		return eguDao.updateExpress(paramMap);
	}

	@Override
	public List<PushEguOrderEntity> updatePushEguOrder() {
		commonLockService.getLock(Lock.Eguorder_billPush);
		List<PushEguOrderEntity> reslist = eguDao.selectPushEguOrder();
		List<EbuyDeliveryOrder> edoList = new ArrayList<EbuyDeliveryOrder>();
		for(PushEguOrderEntity pushOrder:reslist){
			EbuyDeliveryOrder order = new EbuyDeliveryOrder();
			order.setId(pushOrder.getEdo_id());
			order.setPushStatus(EbuyDict.DeliveryOrderpush_Status.TuisongZhong);
			edoList.add(order);
		}
		if(edoList.size()>0)
			deliveryOrderService.updateEbuyDeliveryOrderBatch(edoList);
		return reslist;
	}

	@Override
	public Integer updateDeliveryOrder(Map<String, Object> paramMap) {
		return eguDao.updateDeliveryOrder(paramMap);
	}

	@Override
	public void updateORinsertproduct(EbuyProductTempEntity ebuyProductTempEntity,String piclist) throws Exception {
		//判断商品是否存在
		EbuyProductTemp ebuyproductTemp = eguDao.selectFguProduct(ebuyProductTempEntity.getSrcId());
		if(ebuyproductTemp != null){
			//update,不下载图片
			ebuyProductTempEntity.setId(ebuyproductTemp.getId());
			ebuyProductTempEntity.setSys0UpdTime(CnfantasiaCommbusiUtil.getCurrentTime());
			ebuyProductTempDao.updateEbuyProductTemp(ebuyProductTempEntity);
//			ebuyProductParametersTempBaseDao.updateEbuyProductParametersTempBatch(ebuyProductTempEntity.getPrdtParamter());
			//库存为0时，商品下架, 主动下架时，商品下架
			if (ebuyProductTempEntity.getStatus() != null && ebuyProductTempEntity.getStatus().compareTo(EbuyDict.Product_Status.OFF_DOWN) == 0
					|| ebuyproductTemp.getIsSync() == 1 && ebuyProductTempEntity.getLeftCount().compareTo(new BigInteger("0")) == 0) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("srcId", ebuyproductTemp.getId());
				List<EbuyProduct> proList = ebuyProductBaseDao.selectEbuyProductByCondition(paramMap, false);
				EbuyProduct product = proList.get(0);
				product.setStatus(1);
				product.setDownShelfTime(CnfantasiaCommbusiUtil.getCurrentTime());
				ebuyProductBaseDao.updateEbuyProduct(product);
			}else{
			}
		}else{
			//insert
			BigInteger productId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_temp);
			ebuyProductTempEntity.setIsSync(0);
			ebuyProductTempEntity.setId(productId);
			if(StringUtils.isNotEmpty(piclist)){
				List<String> picList = Arrays.asList(piclist.split(";"));
				String pics = picList.get(0);
				//下载商品原图
				String houzuiPic = pics.substring(pics.lastIndexOf("/") + 1);
				ebuyProductTempEntity.setPicBase("egu"+houzuiPic);
				downPicBig(pics);	
			}
			
			if(eguDao.selectFguProduct(ebuyProductTempEntity.getSrcId()) == null)
				ebuyProductTempDao.insertEbuyProductTemp(ebuyProductTempEntity);
			else
				return;//依谷网那边推送数据可能是多个节点一起推送的，若上个节点已插入成功，本节点就不要插入数据
			
			List<BigInteger> tempIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_parameters_temp, ebuyProductTempEntity.getPrdtParamter().size());
			for(int i = 0; i < ebuyProductTempEntity.getPrdtParamter().size(); i++){
				ebuyProductTempEntity.getPrdtParamter().get(i).setId(tempIds.get(i));
				ebuyProductTempEntity.getPrdtParamter().get(i).settEbuyProductTempFId(productId);
			}
			ebuyProductParametersTempBaseDao.insertEbuyProductParametersTempBatch(ebuyProductTempEntity.getPrdtParamter());
		}
	}

	@Override
	public EbuyDeliveryOrder selectTenmimsg(BigInteger order_no) {
		return eguDao.selectTenmimsg(order_no);
	}
	
	private void downPicBig(String picUrl){
        URL url;
		try {
			url = new URL(picUrl);
	        //打开网络输入流
			DataInputStream dis = new DataInputStream(url.openStream());
			String productPicPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;
			String newImageName=productPicPath+("egu"+picUrl.substring(picUrl.lastIndexOf("/") + 1));
	        FileOutputStream fos = new FileOutputStream(new File(newImageName));
	        byte[] buffer = new byte[1024];
	        int length;
	        while( (length = dis.read(buffer))>0){
	            fos.write(buffer,0,length);
	        }
	        //生成小图
	        generateMiniImage(new File(newImageName));
	        dis.close();
	        fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//生成小图
	private void generateMiniImage(File bigImageFile) {
		Map<String, WidthHeight> guigeList = BusinessModelType.Market.getGuigeList();
		String fileName = bigImageFile.getName();
		int lastPointIndex = fileName.lastIndexOf(".");
		String fileType = fileName.substring(lastPointIndex); //文件后缀名，带.号
		String goalDirectoryFilePath = bigImageFile.getAbsolutePath().substring(0, bigImageFile.getAbsolutePath().lastIndexOf("."));
		// 创建小图的目标目录
		File goalFileDir = new File(goalDirectoryFilePath);
		if (!goalFileDir.exists()) {
			goalFileDir.mkdirs();
		}
		for (String goalFileName : guigeList.keySet()) {
			String smallIcon = goalDirectoryFilePath +"/"+goalFileName + fileType;
			try {
				WidthHeight tmpWidthHeight = guigeList.get(goalFileName);
				ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), tmpWidthHeight.getWidth(), tmpWidthHeight.getHeight(), 1f, smallIcon);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<EbuyDeliveryOrder> getEguDelivOrderNoExpress() {
		return eguDao.getEguDelivOrderNoExpress();
	}

	@Override
	public Integer delEguExpressByDeliverOrderId(BigInteger deliveryOrderId) {
		if (deliveryOrderId == null) {
			return 0;
		}
		return eguDao.delEguExpressByDeliverOrderId(deliveryOrderId);
	}
	
}
