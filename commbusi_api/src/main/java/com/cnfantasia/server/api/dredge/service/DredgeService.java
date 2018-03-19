
package com.cnfantasia.server.api.dredge.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.comments.constant.CommentsConstant;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.entity.OperateConfigRange;
import com.cnfantasia.server.api.commonBusiness.service.ICommMobileService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.coupon.constant.CouponTypeConstant;
import com.cnfantasia.server.api.coupon.constant.CouponUseTypeConstant;
import com.cnfantasia.server.api.couponArea.contant.UserCouponStatus;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.dao.DredgeDao;
import com.cnfantasia.server.api.dredge.entity.AccountAmount;
import com.cnfantasia.server.api.dredge.entity.AddDredgeBillParamter;
import com.cnfantasia.server.api.dredge.entity.AmountDetail;
import com.cnfantasia.server.api.dredge.entity.ApplyListForMaster;
import com.cnfantasia.server.api.dredge.entity.ApplyWithdrawInfo;
import com.cnfantasia.server.api.dredge.entity.BlockForMaster;
import com.cnfantasia.server.api.dredge.entity.BlockWithDredgeService;
import com.cnfantasia.server.api.dredge.entity.CanWithdrawBill;
import com.cnfantasia.server.api.dredge.entity.CanWithdrawBill4Master;
import com.cnfantasia.server.api.dredge.entity.DredgeBillEntity;
import com.cnfantasia.server.api.dredge.entity.DredgeBillForDetail;
import com.cnfantasia.server.api.dredge.entity.DredgeBillForList;
import com.cnfantasia.server.api.dredge.entity.DredgeBillForMaster;
import com.cnfantasia.server.api.dredge.entity.DredgeBillHasEbuyProductShelfEnity;
import com.cnfantasia.server.api.dredge.entity.DredgeDetails;
import com.cnfantasia.server.api.dredge.entity.DredgeMasterList4admin;
import com.cnfantasia.server.api.dredge.entity.DredgeMsgEntity;
import com.cnfantasia.server.api.dredge.entity.DredgeParentType;
import com.cnfantasia.server.api.dredge.entity.DredgeProduct4Admin;
import com.cnfantasia.server.api.dredge.entity.DredgeProduct4Turn;
import com.cnfantasia.server.api.dredge.entity.DredgeProductEntity;
import com.cnfantasia.server.api.dredge.entity.DredgeProductView;
import com.cnfantasia.server.api.dredge.entity.DredgeType2ndForUser;
import com.cnfantasia.server.api.dredge.entity.DredgeTypeEntity;
import com.cnfantasia.server.api.dredge.entity.DredgeTypeForUser;
import com.cnfantasia.server.api.dredge.entity.IncomeWithdrawRecorder;
import com.cnfantasia.server.api.dredge.entity.MasterInfo4Audit;
import com.cnfantasia.server.api.dredge.entity.MasterProfile;
import com.cnfantasia.server.api.dredge.entity.ProcessRecord;
import com.cnfantasia.server.api.dredge.entity.ProductAddNewParamter;
import com.cnfantasia.server.api.dredge.entity.SMSInfo;
import com.cnfantasia.server.api.dredge.entity.SelfBuyProduct;
import com.cnfantasia.server.api.dredge.qsdj.QsdjServiceOrderPusher;
import com.cnfantasia.server.api.dredge.sfdj.SfdjServiceOrderPusher;
import com.cnfantasia.server.api.ebuy.entity.DredgeProductSpecEntity;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;
import com.cnfantasia.server.api.ebuy.util.DeliveryNoGenerator;
import com.cnfantasia.server.api.ebuy.util.OrderNoGenerator;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.FileServerService;
import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.service.IMsgpushService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.sysParam.SysParamManager;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redpoint.callable.RedpointAddRunnableMulti;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.constant.RedpointDict;
import com.cnfantasia.server.api.redpoint.entity.BasicSourceEntity;
import com.cnfantasia.server.api.redpoint.service.IRedpointService;
import com.cnfantasia.server.api.redpoint.service.RedpointService;
import com.cnfantasia.server.api.userCoupon.service.IUserCouponService;
import com.cnfantasia.server.business.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.business.commonBusiness.entity.WidthHeight;
import com.cnfantasia.server.business.commonBusiness.util.MailUtils;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.exception.ExceptionParseUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.ImageZipUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.utils.NumberUtils;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.commentsPoints.entity.CommentsPoints;
import com.cnfantasia.server.domainbase.commentsPoints.service.ICommentsPointsBaseService;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.coupon.service.CouponBaseService;
import com.cnfantasia.server.domainbase.dataChangeRecord.entity.DataChangeRecord;
import com.cnfantasia.server.domainbase.dataChangeRecord.service.IDataChangeRecordBaseService;
import com.cnfantasia.server.domainbase.dredgeBill.dao.DredgeBillBaseDao;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBillAmountDetail.dao.DredgeBillAmountDetailBaseDao;
import com.cnfantasia.server.domainbase.dredgeBillAmountDetail.entity.DredgeBillAmountDetail;
import com.cnfantasia.server.domainbase.dredgeBillAmountDetail.service.IDredgeBillAmountDetailBaseService;
import com.cnfantasia.server.domainbase.dredgeBillFollowRecord.entity.DredgeBillFollowRecord;
import com.cnfantasia.server.domainbase.dredgeBillFollowRecord.service.IDredgeBillFollowRecordBaseService;
import com.cnfantasia.server.domainbase.dredgeBillHasEbuyProductShelf.dao.DredgeBillHasEbuyProductShelfBaseDao;
import com.cnfantasia.server.domainbase.dredgeBillHasEbuyProductShelf.entity.DredgeBillHasEbuyProductShelf;
import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.dao.DredgeBillHasProcessRecordingBaseDao;
import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.entity.DredgeBillHasProcessRecording;
import com.cnfantasia.server.domainbase.dredgeBillHasProductSpec.entity.DredgeBillHasProductSpec;
import com.cnfantasia.server.domainbase.dredgeBillHasProductSpec.service.IDredgeBillHasProductSpecBaseService;
import com.cnfantasia.server.domainbase.dredgeBillOtherInfo.dao.DredgeBillOtherInfoBaseDao;
import com.cnfantasia.server.domainbase.dredgeBillOtherInfo.entity.DredgeBillOtherInfo;
import com.cnfantasia.server.domainbase.dredgeBillPushTime.entity.DredgeBillPushTime;
import com.cnfantasia.server.domainbase.dredgeBillPushTime.service.IDredgeBillPushTimeBaseService;
import com.cnfantasia.server.domainbase.dredgeProduct.dao.DredgeProductBaseDao;
import com.cnfantasia.server.domainbase.dredgeProduct.entity.DredgeProduct;
import com.cnfantasia.server.domainbase.dredgeProduct.service.IDredgeProductBaseService;
import com.cnfantasia.server.domainbase.dredgeProductSpecification.dao.DredgeProductSpecificationBaseDao;
import com.cnfantasia.server.domainbase.dredgeProductSpecification.entity.DredgeProductSpecification;
import com.cnfantasia.server.domainbase.dredgeProductSpecification.service.IDredgeProductSpecificationBaseService;
import com.cnfantasia.server.domainbase.dredgeType.dao.DredgeTypeBaseDao;
import com.cnfantasia.server.domainbase.dredgeType.entity.DredgeType;
import com.cnfantasia.server.domainbase.dredgeType2nd.entity.DredgeType2nd;
import com.cnfantasia.server.domainbase.dredgeType2nd.service.IDredgeType2ndBaseService;
import com.cnfantasia.server.domainbase.dredgeWorker.dao.DredgeWorkerBaseDao;
import com.cnfantasia.server.domainbase.dredgeWorker.entity.DredgeWorker;
import com.cnfantasia.server.domainbase.dredgeWorkerCancel.dao.DredgeWorkerCancelBaseDao;
import com.cnfantasia.server.domainbase.dredgeWorkerCancel.entity.DredgeWorkerCancel;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service.IEbuyDeliveryOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.entity.EbuyDeliveryOrderProduct;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.service.IEbuyDeliveryOrderProductBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.entity.EbuyOrderHasCoupon;
import com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.service.IEbuyOrderHasCouponBaseService;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity.EbuyOrderHasTEbuyProduct;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.service.IEbuyOrderHasTEbuyProductBaseService;
import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.entity.GroupBuildingHasTPropertyService;
import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.service.IGroupBuildingHasTPropertyServiceBaseService;
import com.cnfantasia.server.domainbase.loginNo.dao.LoginNoBaseDao;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.omsCommSysPara.dao.IOmsCommSysParaBaseDao;
import com.cnfantasia.server.domainbase.propertyDistrict.entity.PropertyDistrict;
import com.cnfantasia.server.domainbase.propertyRepair.dao.PropertyRepairBaseDao;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;
import com.cnfantasia.server.domainbase.propertyRepairType.service.IPropertyRepairTypeBaseService;
import com.cnfantasia.server.domainbase.revenueApply.dao.IRevenueApplyBaseDao;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.domainbase.room.service.IRoomBaseService;
import com.cnfantasia.server.domainbase.serviceSupplier.entity.ServiceSupplier;
import com.cnfantasia.server.domainbase.serviceSupplier.service.IServiceSupplierBaseService;
import com.cnfantasia.server.domainbase.smsMq.entity.SmsMq;
import com.cnfantasia.server.domainbase.user.dao.UserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;
import com.cnfantasia.server.domainbase.userCoupon.service.IUserCouponBaseService;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;
import com.cnfantasia.server.domainbase.userHasTMessage.dao.UserHasTMessageBaseDao;
import com.cnfantasia.server.domainbase.wechatDredgebillMq.entity.WechatDredgebillMq;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.entity.DredgeRevenue;
import com.cnfantasia.server.ms.revenue.entity.RevenueMoneyShow;
import com.cnfantasia.server.ms.revenue.util.RevenueTkNoGenerator;
import com.cnfantasia.server.notification.dao.NotificationDao;
import com.cnfantasia.server.notification.mq.constant.SMSMQConstant;
import com.propertySoftwareDock.base.dao.IPropertySoftwareDockDao;
import com.propertySoftwareDock.base.entity.RealRoomSoftwareInfo;
import com.propertySoftwareDock.base.entity.RepairPushEntity;
import com.propertySoftwareDock.base.service.IPropertySoftwareDockService;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public class DredgeService {
	private Log logger = LogFactory.getLog(getClass());
	private static final Object lock = new Object();
	@Resource
	private SysParamManager sysParamManager;

	@Resource
	private FileServerService fileServerService;

	@Resource
	IUuidManager uuidManager;

	@Resource
	DredgeBillBaseDao dredgeBillBaseDao;

	@Resource
	DredgeBillHasEbuyProductShelfBaseDao dredgeBillHasEbuyProductShelfBaseDao;

	@Resource
	DredgeDao dredgeDao;

	@Resource
	IDualDao dualDao;

	@Resource
	IEbuyOrderBaseDao ebuyOrderBaseDao;

	private ICommonEbuyService commonEbuyService;

	@Resource
	DredgeWorkerCancelBaseDao dredgeWorkerCancelBaseDao;

	@Resource
	DredgeWorkerBaseDao dredgeWorkerBaseDao;

	@Resource
	UserBaseDao userBaseDao;

	@Resource
	LoginNoBaseDao loginNoBaseDao;

	@Resource
	PropertyRepairBaseDao propertyRepairBaseDao;

	@Resource
	private IDredgeBillPushTimeBaseService dredgeBillPushTimeBaseService;

	@Resource
	DredgeBillAmountDetailBaseDao dredgeBillAmountDetailBaseDao;

	@Resource
	private IUserCouponBaseService userCouponBaseService;

	@Resource
	private CouponBaseService couponBaseService;


	private IUserCouponService userCouponService;

	@Resource
	private IEbuyOrderHasCouponBaseService ebuyOrderHasCouponBaseService;

	@Resource
	private IDredgeBillAmountDetailBaseService dredgeBillAmountDetailBaseService;

	@Resource
	ICommentsPointsBaseService commentsPointsBaseService;

	@Resource
	private IEbuyDeliveryOrderBaseService ebuyDeliveryOrderBaseService;

	@Resource
	private IEbuyOrderHasTEbuyProductBaseService ebuyOrderHasTEbuyProductBaseService;

	@Resource
	private IEbuyDeliveryOrderProductBaseService ebuyDeliveryOrderProductBaseService;

	@Resource
	private IDredgeBillFollowRecordBaseService dredgeBillFollowRecordBaseService;
	
	@Resource
	private DredgeBillHasProcessRecordingBaseDao dredgeBillHasProcessRecordingBaseDao;

	@Resource
	private IHomeMessageService homeMessageService;

	@Resource
	private IGroupBuildingHasTPropertyServiceBaseService groupBuildingHasTPropertyServiceBaseService;

	@Resource
	private IPropertySoftwareDockDao propertySoftwareDockDao;

	@Resource
	private IDataChangeRecordBaseService dataChangeRecordBaseService;

	@Resource
	private IRoomBaseService roomBaseService;

	@Resource
	private IPropertyRepairTypeBaseService propertyRepairTypeBaseService;
	
	@Resource
	DredgeProductBaseDao dredgeProductBaseDao; 
	
	@Resource
	DredgeProductSpecificationBaseDao dredgeProductSpecificationBaseDao;

    @Resource
    private IDredgeProductBaseService dredgeProductBaseService;

    @Resource
    private IDredgeType2ndBaseService dredgeType2ndBaseService;

    @Resource
    private IDredgeBillHasProductSpecBaseService dredgeBillHasProductSpecBaseService;

    @Resource
    private IDredgeProductSpecificationBaseService dredgeProductSpecificationBaseService;
	@Resource
	private IServiceSupplierBaseService serviceSupplierBaseService;

	@Resource
	private DredgePushService dredgePushService;

	public ICommonEbuyService getCommonEbuyService() {
		if (commonEbuyService == null) {
			return commonEbuyService = (ICommonEbuyService) CnfantasiaCommbusiUtil.getBeanManager("commonEbuyService");
		}
		return commonEbuyService;
	}

	public IUserCouponService getUserCouponService() {
		if (userCouponService == null) {
			return userCouponService = (IUserCouponService) CnfantasiaCommbusiUtil.getBeanManager("userCouponService");
		}
		return userCouponService;
	}

	/**
	 * 疏通类型转Map
	 *
	 * @param dredgeType
	 * @return
	 */
	public DredgeTypeForUser getDredgeTypeForUser(DredgeType dredgeType) {
		if (dredgeType == null) {
			return null;
		}
		String iconBasePath = sysParamManager.getSysParaValue(SysParamKey.DredgePicBasePath);

		DredgeTypeForUser dredgeTypeForUser = new DredgeTypeForUser();
		dredgeTypeForUser.setId(dredgeType.getId());
		dredgeTypeForUser.setName(dredgeType.getName());
		dredgeTypeForUser.setPicUrl(StringUtils.isEmpty(dredgeType.getPicUrl()) ? FileServerConstant.DEFAULT_NULL_PIC_URL : fileServerService.getAccessUrl(iconBasePath + dredgeType.getPicUrl(),
				dredgeType));
		dredgeTypeForUser.setDesc(dredgeType.getDesc());
		dredgeTypeForUser.setPriceDesc(dredgeType.getPriceDesc());
		dredgeTypeForUser.setServiceProcessUrl(dredgeType.getServiceProcessUrl());
		String picUrlGrey = dredgeType.getPicUrl();
		if (!StringUtils.isEmpty(picUrlGrey)) {
			int idx = picUrlGrey.lastIndexOf(".");
			picUrlGrey = picUrlGrey.substring(0, idx) + "_grey" + picUrlGrey.substring(idx);
			dredgeTypeForUser.setPicUrlGrey(fileServerService.getAccessUrl(iconBasePath + picUrlGrey,dredgeType));
		} else {
			dredgeTypeForUser.setPicUrlGrey(dredgeTypeForUser.getPicUrl());
		}
		return dredgeTypeForUser;
	}

	/**
	 * 疏通类型
	 *
	 * @param dredgeType
	 * @return
	 */
	public DredgeTypeForUser getDredgeTypeForUser(DredgeTypeEntity dredgeType) {
		if (dredgeType == null) {
			return null;
		}
		String iconBasePath = sysParamManager.getSysParaValue(SysParamKey.DredgePicBasePath);

		DredgeTypeForUser dredgeTypeForUser = new DredgeTypeForUser();
		dredgeTypeForUser.setId(dredgeType.getId());
		dredgeTypeForUser.setName(dredgeType.getName());
		dredgeTypeForUser.setPicUrl(StringUtils.isEmpty(dredgeType.getPicUrl()) ? FileServerConstant.DEFAULT_NULL_PIC_URL : fileServerService.getAccessUrl(iconBasePath + dredgeType.getPicUrl(),
				dredgeType));
		dredgeTypeForUser.setDesc(dredgeType.getDesc());
		dredgeTypeForUser.setServiceProcessUrl(dredgeType.getServiceProcessUrl());
		dredgeTypeForUser.setPriceDesc(dredgeType.getPriceDesc());
		String picUrlGrey = dredgeType.getPicUrl();
		if (!StringUtils.isEmpty(picUrlGrey)) {
			int idx = picUrlGrey.lastIndexOf(".");
			picUrlGrey = picUrlGrey.substring(0, idx) + "_grey" + picUrlGrey.substring(idx);
			dredgeTypeForUser.setPicUrlGrey(fileServerService.getAccessUrl(iconBasePath + picUrlGrey,dredgeType));
		} else {
			dredgeTypeForUser.setPicUrlGrey(dredgeTypeForUser.getPicUrl());
		}
		if(dredgeType.getIsHasNum() != null){
			dredgeTypeForUser.setIsHasNum(dredgeType.getIsHasNum());
		}

		for(DredgeType2nd dredgeType2nd: dredgeType.getDredgeType2ndList()){
			DredgeType2ndForUser dredgeType2ndForUser = new DredgeType2ndForUser();
			dredgeType2ndForUser.setId(dredgeType2nd.getId());
			dredgeType2ndForUser.setOrignalPrice(dredgeType2nd.getOriginalPrice() !=null ? BigDecimalUtil.div100(dredgeType2nd.getOriginalPrice()) : null);
			dredgeType2ndForUser.setDiscountPrice(dredgeType2nd.getDiscountPrice() !=null ? BigDecimalUtil.div100(dredgeType2nd.getDiscountPrice()) : null);
			dredgeType2ndForUser.setPriceDesc(dredgeType2nd.getPriceDesc());
			dredgeType2ndForUser.setName(dredgeType2nd.getName());
			dredgeType2ndForUser.setParentId(dredgeType2nd.gettDredgeTypeFId());
			dredgeTypeForUser.getSubTypeList().add(dredgeType2ndForUser);
		}
		return dredgeTypeForUser;
	}

	/**
	 * 保存图片信息
	 */
	private String uploadDredgePic(BigInteger userId, List<RequestFileEntity> picList) {
		StringBuilder picUrl = new StringBuilder();

		if (picList != null && picList.size() > 0) {
			String dredgePicBase = sysParamManager.getSysParaValue(SysParamKey.DredgePicBasePath);
			for (int i = 0; i < picList.size(); i++) {//上传图片
				RequestFileEntity requestFile = picList.get(i);
				//生成文件名 userId+time+index+3位随机数
				String resFileName = new StringBuffer().append(userId).append(DateUtil.getCurrSysTimeStr()).append("_").append(i + 1).append("_")
						.append(RandomUtils.createRandom(true, 3)).append(".").append(requestFile.getFileType()).toString();
				picUrl.append(resFileName).append(";");
				String serverPath = dredgePicBase + resFileName;
				try {
					long startTime = System.currentTimeMillis();
					fileServerService.uploadFile(serverPath, requestFile.getFileContent());
					logger.info("uploadFile use time: " + (System.currentTimeMillis() - startTime));
//					startTime = System.currentTimeMillis();
					//generateMiniImage(new File(fileServerService.getFileServierUploadBasePath() + serverPath));
//					new PicGenerateMiniImageThread(new File(fileServerService.getFileServierUploadBasePath() + serverPath)).start();
//					logger.info("generateMiniImage use time: " + (System.currentTimeMillis() - startTime));
//					startTime = System.currentTimeMillis();
				} catch (IOException e) {
					logger.info("uploadRepairPic upload Repair file cause exception:" + e.getMessage(), e);
					throw new BusinessRuntimeException("RepairService.uploadRepairPic.uploadFile.error", new Object[] { requestFile.getFileName() });
				}
			}
		}

		return picUrl.toString();
	}
	
	/**
	 * 保存图片信息
	 * @param picList 图片列表
	 * @param isFilterFirstPic 是否要过滤头部隐藏图片
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	private String uploadDredgePic(List<MultipartFile> picList) throws IllegalStateException, IOException {
		StringBuilder picUrl = new StringBuilder();

		String dredgePicBase = sysParamManager.getSysParaValue(SysParamKey.DredgePicBasePath);
		String picPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) +"/" + dredgePicBase;
		
		for (int imgIndex=0; imgIndex < picList.size(); imgIndex++) {
			MultipartFile picImageFile = picList.get(imgIndex);
			if (picImageFile != null && !StringUtils.isEmpty(picImageFile.getOriginalFilename())) {//有上传图片 
				int indexOfDot = picImageFile.getOriginalFilename().indexOf(".");
				String fileNameC = (System.currentTimeMillis() +RandomUtils.createRandom(false, 4) +imgIndex) + picImageFile.getOriginalFilename().substring(indexOfDot);
				File fileC = new File(picPath + fileNameC);
				if(!fileC.exists())
					fileC.mkdirs();
				picUrl.append(fileNameC).append(";");
				picImageFile.transferTo(fileC);
			}
		}

		return picUrl.toString();
	}

	/**
	 * 增加疏通预约单
	 */
	public Map<String, Object> addDredgeBill(AddDredgeBillParamter addDredgeBillParamter) {
		if (!DataUtil.isEmpty(addDredgeBillParamter.getOldBillId())) {
			dredgeBillBaseDao.deleteDredgeBillLogic(addDredgeBillParamter.getOldBillId());
		}
		BigInteger dredgeBillAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("dredgeBillId", dredgeBillAddId);

        //保存图片信息
        logger.info("update pic start...");
        long startTime = System.currentTimeMillis();
		String pics = addDredgeBillParamter.getPics();//转单传过来的图片
		String picUrl = pics == null ? uploadDredgePic(addDredgeBillParamter.getUserId(), addDredgeBillParamter.getPicList()) : pics;
        logger.info("update pic end... use time: " + (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();

        DredgeBill dredgeBill = new DredgeBill();
        dredgeBill.setId(dredgeBillAddId);
        dredgeBill.setPicUrl(picUrl); //多个图片保存在同一字段中，用分号分隔
        dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Submited);
        dredgeBill.setLinkName(addDredgeBillParamter.getLinkName());
		//记录是app下单还是LA下单
		dredgeBill.setSubmitChannel(addDredgeBillParamter.getSubmitChannel());
		BigInteger dredgeProductId = addDredgeBillParamter.getDredgeProductId();
		DredgeProduct dredgeProduct = null;
        if (dredgeProductId == null) {
            resultMap.put("needPay", false);
            dredgeBill.settDredgeTypeFId(addDredgeBillParamter.getDredgeTypeId());
            dredgeBill.settDredgeType2ndFId(addDredgeBillParamter.getSubTypeId());
            dredgeBill.setPushLevel(DredgeConstant.DredgeWorkerLevel.Low_Level);
            dredgeBill.setPushTime(DateUtils.getCurrentDate());
            dredgeBill.setBillType(DredgeConstant.DredgeBillType.Dredge_Repair);
        } else {
            dredgeProduct = dredgeProductBaseService.getDredgeProductBySeqId(dredgeProductId);
            resultMap.put("needPay", dredgeProduct.getPayType() == 1);
            BigInteger dredgeType2ndId = dredgeProduct.getDredgeType2ndId();
            DredgeType2nd dredgeType2nd = dredgeType2ndBaseService.getDredgeType2ndBySeqId(dredgeType2ndId);
            dredgeBill.settDredgeTypeFId(dredgeType2nd.gettDredgeTypeFId());
            dredgeBill.settDredgeType2ndFId(dredgeType2ndId);

            dredgeBill.setPushTime(DateUtils.getCurrentDate());
            //前付款不推送，后付款只推送解放区
            if (dredgeProduct.getPayType() == 1) {
                dredgeBill.setPushLevel(DredgeConstant.DredgeWorkerLevel.Low_Level);
                dredgeBill.setBillType(DredgeConstant.DredgeBillType.Dredge_Pay_First);
				dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Not_Pay_First);
            } else {
                if (dredgeProduct.getServiceSupplierId().compareTo(DredgeConstant.DredgeServiceSupplier.JFQ) == 0) {
                    dredgeBill.setPushLevel(DredgeConstant.DredgeWorkerLevel.High_Level);
                } else {
                    dredgeBill.setPushLevel(DredgeConstant.DredgeWorkerLevel.Low_Level);
                }
                dredgeBill.setBillType(DredgeConstant.DredgeBillType.Dredge_Bill_Common);
                dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Submited);
            }
        }
        dredgeBill.setAddress(addDredgeBillParamter.getDredgeAddress());
        dredgeBill.setContent(addDredgeBillParamter.getDredgeContent());
        dredgeBill.setTel(addDredgeBillParamter.getTel());
        dredgeBill.setExpectdate(DateUtil.formatSecond.get().format(new Date(addDredgeBillParamter.getExpectDate())));
        dredgeBill.setReferrerMobile(addDredgeBillParamter.getReferrerMobile());
        dredgeBill.settAddressBlockFId(addDredgeBillParamter.getBlockId());
        dredgeBill.setRoomid(addDredgeBillParamter.getRoomId());
        dredgeBill.settUserFId(addDredgeBillParamter.getUserId());
        //单号加上单号：yyyyMMdd+userId+3位随机整数
        String number = DateUtil.formatMinuteTogether.get().format(new java.util.Date()) + "" + addDredgeBillParamter.getUserId();
        number = number + RandomUtils.createRandom(true, 3);
        dredgeBill.setBillNo(number);
        dredgeBill.setDredgeTypeNum(addDredgeBillParamter.getDredgeTypeNum());
        dredgeBillBaseDao.insertDredgeBill(dredgeBill);
        logger.info("insert dredge bill... use time: " + (System.currentTimeMillis() - startTime));

        //保存商品规格
        long productAmount = saveProductSpec(addDredgeBillParamter.getProductSpecList(), dredgeBillAddId);

        //保存自选耗材
        saveSelfBuyProduct(addDredgeBillParamter.getProductIdQtyList(), dredgeBill);

        //保存预付款的费用
		if (productAmount >= 0 && dredgeProduct != null && dredgeProduct.getPayType() == 1) {
			DredgeBill updBill = new DredgeBill();
			updBill.setId(dredgeBillAddId);
			updBill.setPayAmount(productAmount);
			dredgeBillBaseDao.updateDredgeBill(updBill);
			insertAmountDetail(dredgeBillAddId, productAmount, 0);
		}

        DredgeType dredgeType = dredgeTypeBaseDao.selectDredgeTypeBySeqId(dredgeBill.gettDredgeTypeFId());
        //正常维修发邮件，推送到师傅端
        if (dredgeBill.getBillType().compareTo(DredgeConstant.DredgeBillType.Dredge_Repair) != 0) {
        	//服务后付款，要发推送
			if (dredgeBill.getBillType() != DredgeConstant.DredgeBillType.Dredge_Pay_First) {
				dredgePushService.submitBillSuccessMsg(dredgeBill.getId());
			}
			long t1 = System.currentTimeMillis();
			BigInteger serviceSupplierId = dredgeProduct.getServiceSupplierId();
			ServiceSupplier serviceSupplier = serviceSupplierBaseService.getServiceSupplierBySeqId(serviceSupplierId);
			sendEmailAfterAddDredgeBill(dredgeBill,dredgeProduct.getName(), serviceSupplier.getName());

            long t2 = System.currentTimeMillis();

            if (dredgeBill.getPushLevel() == DredgeConstant.DredgeWorkerLevel.High_Level) {
                try {//添加推送消息
                    new Thread(new PushMessgeThread(dredgeBill, "又有新的订单啦", MsgpushDict.PushId.DredgeBill_AddNew)).start();
                } catch (Exception e) {
                    logger.info(e.getMessage(), e);
                }
            }
            long t3 = System.currentTimeMillis();
            logger.debug("t1:" + t1 + ", " + "t2:" + t2 + ", t3:" + t3);

            //产生首页消息
			BigInteger homeMsgId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_home_message);
			UserHasHomeMessage message = new UserHasHomeMessage();
			message.setId(homeMsgId);
			message.setMessageCode(HomeMessageDict.MessageCode.DREDGE_SERVICE_ALERT);
			message.setResouceId(dredgeBillAddId);
			homeMessageService.generateCommonMsg(message);
        } else {//物业报修发邮件，推到极致客户端
            Map<String, Object> resMap = getRoomAddressIdByRoom(dredgeBill.getRoomid());
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("tGroupBuildingFId", resMap.get("gbId"));
            List<GroupBuildingHasTPropertyService> serviceList = groupBuildingHasTPropertyServiceBaseService
                    .getGroupBuildingHasTPropertyServiceByCondition(paramMap);
            if (!DataUtil.isEmpty(serviceList)) {
                ShortMsgUtil.sendMessage(serviceList.get(0).getServerMobile(), "order_dredge1",
                        dredgeBill.getAddress(), dredgeType.getName(), dredgeBill.getTel());
            }
            //推送到物业软件
            new PushRepairThread(dredgeBill, addDredgeBillParamter.getPicList()).start();

			//产生首页消息
			BigInteger homeMsgId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_home_message);
			UserHasHomeMessage message = new UserHasHomeMessage();
			message.setId(homeMsgId);
			message.setMessageCode(HomeMessageDict.MessageCode.PROPERTY_REPAIR_ALERT);
			message.setResouceId(dredgeBillAddId);
			homeMessageService.generateCommonMsg(message);
        }
        return resultMap;
	}

	private Long saveProductSpec(List<DredgeProductSpecEntity> specEntityList, BigInteger dredgeBillId) {
	    if (DataUtil.isEmpty(specEntityList)) {
	        return 0L;
        }
        List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_has_product_spec, specEntityList.size());
        List<DredgeBillHasProductSpec> specList = new ArrayList<>(specEntityList.size());
        int idx = 0;
	    DredgeBillHasProductSpec spec;
	    DredgeProductSpecification specification;
	    long amount = 0L;
        for (DredgeProductSpecEntity entity : specEntityList) {
            specification = dredgeProductSpecificationBaseService.getDredgeProductSpecificationBySeqId(entity.getSpecId());
            spec = new DredgeBillHasProductSpec();
            spec.setId(ids.get(idx++));
            spec.setDredgeBillId(dredgeBillId);
            spec.setSpecificId(entity.getSpecId());
            spec.setBuyCount(Long.valueOf(entity.getSpecNum()));
            spec.setBuyPrice(specification.getSellPrice());
            specList.add(spec);
            amount = amount + specification.getSellPrice() * entity.getSpecNum();
        }
        dredgeBillHasProductSpecBaseService.insertDredgeBillHasProductSpecBatch(specList);
        return amount;
    }

    private void saveSelfBuyProduct(List<ProductIdQtyEntity> entityList, DredgeBill dredgeBill) {
        if (entityList != null && entityList.size() > 0) {
            List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_has_ebuy_product_shelf, entityList.size());
            List<DredgeBillHasEbuyProductShelf> addDredgeBillHasEbuyProductShelfList = new ArrayList<>();
            for (int i = 0; i < entityList.size(); i++) {
                ProductIdQtyEntity productIdQtyEntity = entityList.get(i);
                DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf = new DredgeBillHasEbuyProductShelf();
                dredgeBillHasEbuyProductShelf.setId(idList.get(i));
                dredgeBillHasEbuyProductShelf.settDredgeBillFId(dredgeBill.getId());
                dredgeBillHasEbuyProductShelf.settEbuyProductShelfFId(productIdQtyEntity.getProductId());
                dredgeBillHasEbuyProductShelf.setQuantity(productIdQtyEntity.getProductQty().intValue());
                addDredgeBillHasEbuyProductShelfList.add(dredgeBillHasEbuyProductShelf);
            }
            dredgeBillHasEbuyProductShelfBaseDao.insertDredgeBillHasEbuyProductShelfBatch(addDredgeBillHasEbuyProductShelfList);

            try {//发送耗材信息给店家
                sendMsgThread(dredgeBill, "msg.DREDGE_SHOP");
            } catch (Exception e) {
                logger.info("发送耗材信息给店家，调用短息sendMsgThread方法异常:" + e.getMessage());
            }
        }
    }

	@Resource
	DredgeTypeBaseDao dredgeTypeBaseDao;

	private void sendEmailAfterAddDredgeBill(DredgeBill dredgeBill, String dredgeProductName, String supplierName) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sysParaCode", OmsSysParamKey.EmailSwitch);
		IOmsCommSysParaBaseDao omsCommSysParaBaseDao =(IOmsCommSysParaBaseDao) CnfantasiaCommbusiUtil.getBeanManager("omsCommSysParaBaseDao");
		String emailSwitch = omsCommSysParaBaseDao.selectOmsCommSysParaByCondition(paramMap, false).get(0).getSysParaValue();
		if ("0".equals(emailSwitch)) {
			// 0表示不需要推送邮件
			return;
		}

		String nofityEmails = sysParamManager.getSysParaValue(SysParamKey.AddDredgeBillNotifyEmail);
		PropertyDistrict pd = dredgeDao.selectPropertyDistrictByDredgeBillId(dredgeBill.getId());
		if (pd != null && StringUtils.isNotEmpty(pd.getDirectorEmail())) {
			nofityEmails += "," + pd.getDirectorEmail();
		}
		StringBuilder mailContent = new StringBuilder();
		mailContent.append("订单号：").append(dredgeBill.getBillNo());
		String fullTypeName = dredgeDao.selectFullDredgeTypeName(dredgeBill.gettDredgeType2ndFId());
		mailContent.append("<br>维修类型：").append(fullTypeName);
		mailContent.append("<br>服务供应商：").append(supplierName);
		String payDesc = dredgeBill.getBillType() == DredgeConstant.DredgeBillType.Dredge_Pay_First ? "服务前付款" : "服务后付款";
		mailContent.append("<br>付款方式：").append(payDesc);
		List<DredgeProductSpecEntity> specEntityList = getDredgeProductListByDbId(dredgeBill.getId());
		StringBuilder sb = new StringBuilder();
		for (DredgeProductSpecEntity entity : specEntityList) {
			sb.append(entity.getSpecName()).append("   ").append(PriceUtil.div100(Long.valueOf(entity.getSpecPrice())))
					.append("元 / ").append(entity.getSpecUnit()).append(" *").append(entity.getSpecNum()).append(";");
		}
		mailContent.append("<br>服务详情：").append("【").append(dredgeProductName).append("】  ").append(sb.toString());
		mailContent.append("<br>下单时间：").append(DateUtils.getCurrentDate());
		mailContent.append("<br>期望时间：").append(dredgeBill.getExpectdate());
		mailContent.append("<br>维修地址：").append(dredgeBill.getAddress());
		mailContent.append("<br>联系人：").append(dredgeBill.getLinkName() == null ? "" : dredgeBill.getLinkName());
		mailContent.append("<br>联系电话：").append(dredgeBill.getTel());
		mailContent.append("<br>问题描述：").append(dredgeBill.getContent() == null ? "" : dredgeBill.getContent());
		new MailSendThread(mailContent.toString(), nofityEmails).start();
	}

	/**
	 * 生成小图
	 *
	 * @param bigImageFile
	 */
	private void generateMiniImage(File bigImageFile) {
		Map<String, WidthHeight> guigeList = BusinessModelType.Dredge.getGuigeList();

		String fileName = bigImageFile.getName();
		int lastPointIndex = fileName.lastIndexOf(".");
		String fileType = fileName.substring(lastPointIndex); //文件后缀名，带.号

		//String goalDirectoryFilePath = descDirecBasePath + fileName.substring(0, lastPointIndex);
		//   ../dredgePic/5038472015-08-12_10_00_16_1_755.jpg
		String goalDirectoryFilePath = bigImageFile.getAbsolutePath().substring(0, bigImageFile.getAbsolutePath().lastIndexOf("."));

		// 创建小图的目标目录
		File goalFileDir = new File(goalDirectoryFilePath);
		if (!goalFileDir.exists()) {
			goalFileDir.mkdirs();
		}

		for (String goalFileName : guigeList.keySet()) {
			String smallIcon = goalDirectoryFilePath + "/" + goalFileName + fileType;
			try {
				WidthHeight tmpWidthHeight = guigeList.get(goalFileName);
				ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), tmpWidthHeight.getWidth(), tmpWidthHeight.getHeight(), 1f, smallIcon);
				//	String resSmallIcon = ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(),tmpWidth.getWidth(),null, quality, smallIcon);
				//	String resSmallIcon = ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), tmpWidth.getWidth(),tmpWidth.getHeight(), quality, smallIcon);
			} catch (Exception e) {
				logger.info(smallIcon + "create small image failure. ");
			}
		}
	}

	/**
	 * 我的预约
	 *
	 * @param userId
	 *            用户ID
	 * @param type
	 *            1：已预约，2：已结束
	 * @param roomId
	 *            门牌id
	 * @param pageModel
	 *            分页信息，将会被更新
	 * @return
	 */
	public List<DredgeBillForList> queryMyDredgeBillList(BigInteger userId, int type, String roomId, PageModel pageModel) {
		return dredgeDao.queryMyDredgeBillList(userId, type, roomId, pageModel);
	}

	public int qryIsOpenDredgeService4Block(String blockId, String dredgeTypeId) {
		return dredgeDao.qryIsOpenDredgeService4Block(blockId, dredgeTypeId);
	}

	public List<BlockWithDredgeService> qryDredgeServiceListByCity(String cityId, String cityName, String dredgeTypeId) {
		return dredgeDao.qryDredgeServiceListByCity(cityId, cityName, dredgeTypeId);
	}

	public DredgeBillForDetail qryDredgeBillDetail(String id) {
		return dredgeDao.qryDredgeBillDetail(id);
	}
	
	public DredgeDetails getDredgeBillDetail(String id) {
		return dredgeDao.getDredgeBillDetail(id);
	}
	
	/**
	 * 追加其它费
	 * @param db
	 */
	public void appendMaterialFee(DredgeBillForDetail db) {
		if(db.getAmountList() == null) {
			db.setAmountList(new ArrayList<AmountDetail>());
		}

		if(db.getAmountList().size() == 0) {
			AmountDetail ad = new AmountDetail();
			ad.setId(-1);
			ad.setFeeAmount(db.getPayAmount() == null ? BigDecimal.ZERO.setScale(2) : db.getPayAmount());
			ad.setFeeName("人工费");
			ad.setIsIncludePlatformFee(1);
			ad.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Labor_fee);
			db.getAmountList().add(ad);

			AmountDetail ad2 = new AmountDetail();
			ad2.setId(-2);
			ad2.setFeeAmount(BigDecimal.ZERO.setScale(2));
			ad2.setFeeName("其它费");
			ad2.setIsIncludePlatformFee(0);
			ad2.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Material_fee);
			db.getAmountList().add(ad2);
		} else if(db.getAmountList().size() == 1){
			AmountDetail ad = new AmountDetail();
			ad.setId(-1);
			ad.setFeeAmount(BigDecimal.ZERO.setScale(2));
			ad.setFeeName("其它费");
			ad.setIsIncludePlatformFee(0);
			ad.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Material_fee);
			db.getAmountList().add(ad);
		}

		//追加用户自行耗材费
		AmountDetail ad3 = new AmountDetail();
		ad3.setId(-3);
		ad3.setFeeAmount(db.getSelfBuyAmount().setScale(2));
		ad3.setFeeName("耗材费");
		ad3.setIsIncludePlatformFee(0);
		ad3.setFeeType(DredgeConstant.DredgeBillAmountDetailType.SelfBuy_fee);
		db.getAmountList().add(ad3);

		//把超市耗材加到维修订单
		if(db.getPayAmount() != null){
			if(db.getSelfBuyAmount() != null) {
				db.setPayAmount(db.getPayAmount().add(db.getSelfBuyAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
			}else{
				db.setPayAmount(db.getPayAmount().setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}
	}

	public DredgeBillForDetail qryPropertyRepairDetail(BigInteger id) {
		DredgeBillForDetail detail = dredgeDao.qryPropertyRepairDetail(id);
		//物业单状态映射成外部单
		int status = detail.getStatus();
		if (status == 0) {
			detail.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Submited);
		} else if (status == 1) {
			detail.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Canceled);
		} else if (status == 2) {
			detail.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Accepted);
		} else if (status == 3) {
			detail.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_FinishedHasComment);
		} //if (status == 6) sql取出来就是6，此种是物业关闭的订单，不需要再映射了
		return detail;
	}

	/**
	 * 确认支付，支付前生成生支付订单，即获得orderId，并扣减粮票或锁定优惠券，然后APP调用预支付接口进行支付
	 *
	 * @param dredgeBillId
	 *            疏通单Id
	 * @param hbAmount
	 *            使用的粮票金额
	 * @param couponIdSet
	 *            使用的优惠券Ids
	 * @return orderId 支付订单Id，如果已经生成，直接返回之前的orderId
	 */
	@Transactional
	public EbuyOrder confirmPayBill(BigInteger dredgeBillId, Long hbAmount, Set<BigInteger> couponIdSet, Integer subChannelId) {
		// 校验支付优惠信息
		if (hbAmount != null && hbAmount > 0 && couponIdSet != null && couponIdSet.size() > 0) {
			throw new BusinessRuntimeException("ebuy.submitOrder.coupon.multi.error");
		}
		DredgeBill dredgeBill = dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBillId);

		String redisKey = RedisCachePrefix.dredge_bill_confirm_pay_params + dredgeBillId;
		String redisToStore = (hbAmount == null ? 0 : hbAmount) + "," + (couponIdSet == null ? "null" : couponIdSet.toString());
		//判断重复提交，则直接返回
		boolean sameParam = false;
		synchronized (lock) {
			String redisValue = RedisCacheHandler.get(redisKey);
			RedisCacheHandler.set(redisKey, redisToStore, 5 * 60);
			sameParam = !DataUtil.isEmpty(dredgeBill.gettEbuyOrderFId()) && !DataUtil.isEmpty(redisValue) && redisValue.equals(redisToStore);
		}
		if (sameParam) {
			EbuyOrder order = ebuyOrderBaseDao.selectEbuyOrderBySeqId(dredgeBill.gettEbuyOrderFId());
			if (order.getPayStatus().equals(EbuyDict.EbuyOrder_PayStatus.Pay_Success)) {
				throw new BusinessRuntimeException("dredgeController.dredgeBill.isPaid");
			}
			return order;
		}

		String nowTime = dualDao.getNowTime();
		BigInteger resOrderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();



		//已经生成了订单, 删除之前的订单，现在优惠券和粮票可以随意变了。
		if (dredgeBill.gettEbuyOrderFId() != null) {
			EbuyOrder order = ebuyOrderBaseDao.selectEbuyOrderBySeqId(dredgeBill.gettEbuyOrderFId());
			if (order.getPayStatus().equals(EbuyDict.EbuyOrder_PayStatus.Pay_Success)) {
				throw new BusinessRuntimeException("dredgeController.dredgeBill.isPaid");
			}
			this.updateDredgeOrder2Delete(userId, dredgeBill.gettEbuyOrderFId());
		}



		long amount = dredgeBill.getPayAmount();
		long selfBuyAmount = getDredgeHasProductAmount(dredgeBillId);

		EbuyOrder ebuyOrder = new EbuyOrder();
		ebuyOrder.setType(EbuyDict.EbuyOrder_Type.Dredge_Bill);//订单类型：疏通工单
		ebuyOrder.setId(resOrderId);
		ebuyOrder.setBuyerId(userId);
		ebuyOrder.setCurrRoomId(userBaseDao.selectUserBySeqId(userId).getDefaultRoomId());
		ebuyOrder.setBuyTime(nowTime);
		ebuyOrder.setCreater(userId);
		ebuyOrder.setOrderNo(OrderNoGenerator.getOrderNo(resOrderId));
		ebuyOrder.setPayMethod(null);
		ebuyOrder.setPayTime(null);
		ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
		ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Not_Pay);//支付状态为待支付
		ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Not_Deliv);//发货状态为未发货
		ebuyOrder.setTotalDeliveryFee(0L);//总配送费
		ebuyOrder.setAmount(amount + selfBuyAmount); //应付金额

		ebuyOrder.setSubChannel(subChannelId == null ? null : subChannelId + "");

		// 新增订单
		int res = ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);
		if (res <= 0) {
			throw new BusinessRuntimeException("Plotproperty.confirmPayBill.insertEbuyOrder.error");
		}

		EbuyDeliveryOrder ebuyDeliveryOrder = addDredgeBillDeiliveryOrder(dredgeBillId, resOrderId);

		DredgeBill db = new DredgeBill();
		if (ebuyOrder.getAmount() == 0) {
			if (dredgeBill.getBillType() == DredgeConstant.DredgeBillType.Dredge_Pay_First) {
				db.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Submited);
			} else {
				db.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_FinishedNoComment);
			}
			db.setPayAmount(0L);
			db.setPtbtAmount(0L);

			ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.YiWanCheng);
			ebuyOrder.setPayMethod(EbuyDict.EbuyPay_PayMethod.Free.toString());
			ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Deliv_ALL);
			ebuyOrder.setTotalCouponAmount(0L);
			ebuyOrder.setPayTime(nowTime);
			ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Pay_Success);
			ebuyOrderBaseDao.updateEbuyOrder(ebuyOrder);
		} else if (hbAmount != null && hbAmount.compareTo(0L) > 0) {//粮票使用且大于0 粮票处理--syl-add 2014-7-3 14:34:58
			logger.debug("start updateOrderByHb ...");
			Long orderLeftAmount = getCommonEbuyService().updateOrderByHb(userId, resOrderId, hbAmount);
			logger.debug("updateOrderByHb success...");
			if (orderLeftAmount != null && orderLeftAmount.compareTo(0L) == 0) {//剩余应付金额直接设置订单状态为支付成功
				// 直接设定订单状态为成功
				logger.info("start to use hbAmount total.");
				getCommonEbuyService().paySuccessOperateComm(resOrderId, EbuyDict.EbuyPay_PayMethod.PureRedEnvelope);
				if (dredgeBill.getBillType() == DredgeConstant.DredgeBillType.Dredge_Pay_First) {
					db.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Submited);
					dredgePushService.submitBillSuccessMsg(dredgeBillId);
				} else {
					db.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_FinishedNoComment);
				}
				logger.info("use hbAmount only success.");
				//更新耗材库存
				try {
					logger.info("satrt update invetory.");
					new UpdateEbuyProductInventoryThread(dredgeBillId).start();
					logger.info("update invetory success.");
				} catch (Exception e) {
					logger.info(e.getMessage(), e);
				}
			}
			db.setPtbtAmount(hbAmount);
		} else if (couponIdSet != null && couponIdSet.size() > 0) {//使用优惠券
			logger.debug("start updateOrderByCopounList ...");
			Map<String, Long> couponMap = updateDredgeOrderByCopounList(userId, dredgeBillId, ebuyOrder, couponIdSet);
			logger.debug("updateOrderByCopounList success...");
			if (couponMap.get("orderLeftAmount") == 0) {//剩余应付金额直接设置订单状态为支付成功
				// 直接设定订单状态为成功
				logger.info("start to use couponIdSet total.");
				getCommonEbuyService().paySuccessOperateComm(resOrderId, EbuyDict.EbuyPay_PayMethod.PureSupriseGiftList);
				if (dredgeBill.getBillType() == DredgeConstant.DredgeBillType.Dredge_Pay_First) {
					db.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Submited);
					dredgePushService.submitBillSuccessMsg(dredgeBillId);
				} else {
					db.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_FinishedNoComment);
				}
				logger.info("use couponIdSet only success.");
				//更新耗材库存
				try {
					logger.info("satrt update invetory.");
					new UpdateEbuyProductInventoryThread(dredgeBillId).start();
					logger.info("update invetory success.");
				} catch (Exception e) {
					logger.info(e.getMessage(), e);
				}
			}
			db.setPtbtAmount(couponMap.get("dredgeBillCoupon"));

			if (ebuyDeliveryOrder != null) {
				ebuyDeliveryOrder.setTotalCoupon(couponMap.get("delivOrderCoupon"));
				ebuyDeliveryOrder.setAmount(ebuyDeliveryOrder.getAmount() - couponMap.get("delivOrderCoupon"));
				ebuyDeliveryOrderBaseService.updateEbuyDeliveryOrder(ebuyDeliveryOrder);
			}
		}

		if (db.getStatus() != null && db.getStatus() == DredgeConstant.DredgeBill.DredgeBill_Status_Submited) {
			boolean payFirstJfqBill = dredgeDao.isPayFirstJfqBill(dredgeBillId);
			logger.info("是否是前付款解放区：" + payFirstJfqBill);
			//前付款，且解放区师傅的，发推送
			if (payFirstJfqBill){
				try {//添加推送消息
					dredgeBill.setPushLevel(DredgeConstant.DredgeWorkerLevel.High_Level);
					new Thread(new PushMessgeThread(dredgeBill, "又有新的订单啦", MsgpushDict.PushId.DredgeBill_AddNew)).start();
				} catch (Exception e) {
					logger.info(e.getMessage(), e);
				}
			}
		}
		//更新疏通单
		db.setId(dredgeBillId);
		db.settEbuyOrderFId(ebuyOrder.getId());
		dredgeBillBaseDao.updateDredgeBill(db);

		return ebuyOrderBaseDao.selectEbuyOrderBySeqId(resOrderId);
	}

	private EbuyDeliveryOrder addDredgeBillDeiliveryOrder(BigInteger dredgeBillId, BigInteger orderId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", UserContext.getOperIdMustExistBigInteger());
		param.put("dredgeBillId", dredgeBillId);
		List<SelfBuyProduct> selfBuyProductList = getSelfBuyProductList(param);

		if (!DataUtil.isEmpty(selfBuyProductList)) {
			List<EbuyOrderHasTEbuyProduct> ebuyOrderHasTEbuyProductAddList = new ArrayList<EbuyOrderHasTEbuyProduct>(selfBuyProductList.size());
			List<EbuyDeliveryOrderProduct> deliveryOrderProductList = new ArrayList<EbuyDeliveryOrderProduct>(selfBuyProductList.size());
			List<BigInteger> hasProductIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_t_ebuy_product, selfBuyProductList.size());
			List<BigInteger> deliveryOrderProductIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_order_product, selfBuyProductList.size());
			BigInteger deliveryOrderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_order);
			int count = 0;
			for (SelfBuyProduct selfBuyProduct : selfBuyProductList) {
				EbuyOrderHasTEbuyProduct hasTEbuyProduct = new EbuyOrderHasTEbuyProduct();
				hasTEbuyProduct.setId(hasProductIds.get(count));
				hasTEbuyProduct.settEbuyOrderFId(orderId);
				hasTEbuyProduct.settEbuyProductFId(selfBuyProduct.getId());
				hasTEbuyProduct.setProductQty(Long.valueOf(selfBuyProduct.getQuantity()));
				hasTEbuyProduct.setProductPrice(selfBuyProduct.getPrice().multiply(BigDecimal.valueOf(100)).longValue());
				hasTEbuyProduct.setProductPricePoint(0L);
				hasTEbuyProduct.setSupplyMerchantId(selfBuyProduct.getSupplyMerchantId());
				hasTEbuyProduct.setPurchasePrice(Long.valueOf(selfBuyProduct.getProductPursePrice()));
				ebuyOrderHasTEbuyProductAddList.add(hasTEbuyProduct);

				EbuyDeliveryOrderProduct deliveryOrderProduct = new EbuyDeliveryOrderProduct();
				deliveryOrderProduct.setId(deliveryOrderProductIds.get(count++));
				deliveryOrderProduct.settEbuyDeliveryOrderFId(deliveryOrderId);
				deliveryOrderProduct.settEbuyOrderFId(orderId);
				deliveryOrderProduct.settEbuyOrderHasTEbuyProductFId(hasTEbuyProduct.getId());
				deliveryOrderProduct.settEbuyProductFId(hasTEbuyProduct.gettEbuyProductFId());
				deliveryOrderProduct.setSupplyMerchantId(hasTEbuyProduct.getSupplyMerchantId());
				deliveryOrderProductList.add(deliveryOrderProduct);

			}
			ebuyOrderHasTEbuyProductBaseService.insertEbuyOrderHasTEbuyProductBatch(ebuyOrderHasTEbuyProductAddList);

			//增加运单
			EbuyDeliveryOrder deliveryOrder = new EbuyDeliveryOrder();
			deliveryOrder.setId(deliveryOrderId);
			deliveryOrder.settEbuyOrderFId(orderId);
			deliveryOrder.settSupplyMerchantFId(selfBuyProductList.get(0).getSupplyMerchantId());
			deliveryOrder.setAmount(Long.valueOf(getDredgeHasProductAmount(dredgeBillId)));
			deliveryOrder.setTotalCoupon(0L);
			deliveryOrder.setDeliveryRealFee(0L);
			deliveryOrder.setUserDeliveryType(1);
			deliveryOrder.setDeliveryId(BigInteger.valueOf(-1));//-1为免邮费
			deliveryOrder.setBuyerId(UserContext.getOperIdMustExistBigInteger());
			deliveryOrder.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			deliveryOrder.setDeliveryNo(DeliveryNoGenerator.getDeliveryNo(deliveryOrderId, deliveryOrder.gettSupplyMerchantFId()));
			deliveryOrder.setStatus(EbuyDict.EbuyDeliveryOrder_Status.NotStart);// 未启动状态
			deliveryOrder.setRevenueStatus(0);//新增是否计算收益
			deliveryOrder.setPushStatus(EbuyDict.DeliveryOrderpush_Status.NotStart);//配送单推送状态0未发送
			ebuyDeliveryOrderBaseService.insertEbuyDeliveryOrder(deliveryOrder);
			ebuyDeliveryOrderProductBaseService.insertEbuyDeliveryOrderProductBatch(deliveryOrderProductList);
			return deliveryOrder;
		}
		return null;
	}

	/**
	 * 用消费券时更新维修单
	 * @param userId
	 * @param dredgeBillId
	 * @param ebuyOrder
	 * @param couponIdList
	 * @return
	 */
	private synchronized Map<String, Long> updateDredgeOrderByCopounList(BigInteger userId,BigInteger dredgeBillId, EbuyOrder ebuyOrder,Set<BigInteger> couponIdList) {

		Map<String, Long> resMap = new HashMap<String, Long>();

		//基本的数据条件校验
		if(couponIdList==null||couponIdList.size()<=0){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByCopounList.couponIdList.empty");
		}
		if (ebuyOrder == null) {
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByCopounList.ebuyOrder.isNull");
		}

		//校验消费券必须是属于当前用户的且有效, 实际消费券用户只能用一个
		List<UserCoupon> selectedCouponList = new ArrayList<UserCoupon>();

		for (BigInteger couponId : couponIdList) {
			UserCoupon userCoupon = userCouponBaseService.getUserCouponBySeqId(couponId);
			selectedCouponList.add(userCoupon);
			if (!userCoupon.gettUserFId().equals(userId) || !userCoupon.getStatus().equals(UserCouponStatus.VALID)) {
				throw new BusinessRuntimeException("CommonEbuyService.updateOrderByCopounList.couponIdList.hasUnKnown");
			}
		}

		UserCoupon usedUserCoupon = selectedCouponList.get(0);
		BigInteger couponId = usedUserCoupon.gettCouponFId();

		Coupon usedCoupon = couponBaseService.getCouponBySeqId(couponId);
		//取得最大优惠金额（分）
		long realCouponAmount = 0;
		//计算优惠券优惠金额
		if (usedCoupon.getCouponType().equals(CouponTypeConstant.CASHCOUPON)) {
			if (usedCoupon.getUseType().compareTo(CouponUseTypeConstant.REPAIR) == 0) {
				Long maxDiscountMoney = (long) (usedCoupon.getDiscountMoney() * usedCoupon.getMaxDiscountPercent());//此时单位为分
				long laborFee = 0;

				DredgeBillAmountDetail dbadQry = new DredgeBillAmountDetail();
				dbadQry.settDredgeBillFId(dredgeBillId);
				dbadQry.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Labor_fee);
				List<DredgeBillAmountDetail> dbadList = dredgeBillAmountDetailBaseService.getDredgeBillAmountDetailByCondition(MapConverter.toMap(dbadQry));
				if (!DataUtil.isEmpty(dbadList)) {
					laborFee = dbadList.get(0).getPayAmount();
				}
				realCouponAmount = Math.min(laborFee, maxDiscountMoney);
				resMap.put("dredgeBillCoupon", realCouponAmount);
				resMap.put("delivOrderCoupon", 0L);
			} else if (usedCoupon.getUseType().compareTo(CouponUseTypeConstant.HOME_PRODUCT) == 0){
				Long maxDiscountMoney = (long) (usedCoupon.getDiscountMoney() * usedCoupon.getMaxDiscountPercent());//此时单位为分
				DredgeBill bill = dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBillId);
				realCouponAmount = Math.min(bill.getPayAmount(), maxDiscountMoney);
				resMap.put("dredgeBillCoupon", realCouponAmount);
				resMap.put("delivOrderCoupon", 0L);
			} else {//抵扣自选耗材
				Long maxDiscountMoney = (long) (usedCoupon.getDiscountMoney() * usedCoupon.getMaxDiscountPercent());//此时单位为分
				Integer dredgeHasProductAmount = getDredgeHasProductAmount(dredgeBillId);
				realCouponAmount = Math.min(dredgeHasProductAmount, maxDiscountMoney);
				resMap.put("dredgeBillCoupon", 0L);
				resMap.put("delivOrderCoupon", realCouponAmount);
			}
		}

		//记录订单使用的消费券至t_ebuy_order_has_coupon
		EbuyOrderHasCoupon ebuyOrderHasCoupon = new EbuyOrderHasCoupon();
		ebuyOrderHasCoupon.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_coupon));
		ebuyOrderHasCoupon.settEbuyOrderFId(ebuyOrder.getId());
		ebuyOrderHasCoupon.setAmount(realCouponAmount);
		ebuyOrderHasCoupon.settUserCouponFId(usedUserCoupon.getId());

		ebuyOrderHasCouponBaseService.insertEbuyOrderHasCoupon(ebuyOrderHasCoupon);

		//更新订单金额相关信息
		EbuyOrder ebuyOrderUpd = new EbuyOrder();
		ebuyOrderUpd.setId(ebuyOrder.getId());
		ebuyOrderUpd.setAmount(ebuyOrder.getAmount()- realCouponAmount);
		ebuyOrderUpd.setTotalCouponAmount(realCouponAmount);
		int ebuyOrderUpdResCount = ebuyOrderBaseDao.updateEbuyOrder(ebuyOrderUpd);
		if(ebuyOrderUpdResCount<=0){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByCopounList.updateEbuyOrder.failed");
		}
		resMap.put("orderLeftAmount", ebuyOrderUpd.getAmount());
		return resMap;
	}

	private void updateDredgeOrder2Delete(BigInteger userId, BigInteger orderId) {
		// 查询当前订单状态
		EbuyOrder ebuyOrderDetail = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
		if (EbuyDict.EbuyOrder_Status.DaiFuKuan.compareTo(ebuyOrderDetail.getStatus()) == 0) {// 若为待付款，则可以删除
			// 退回粮票、优惠券信息
			Long totalCouponAmount = getCommonEbuyService().backHbByOrderId(userId, orderId);
			//消费券未绑定维修，不做返还。返还会导致先在维修使用，再在超市购物后，消费券变为仍然有效
			/*EbuyOrderHasCoupon orderHasCoupon = new EbuyOrderHasCoupon();
			orderHasCoupon.settEbuyOrderFId(orderId);
			List<EbuyOrderHasCoupon> coupons = ebuyOrderHasCouponBaseService.getEbuyOrderHasCouponByCondition(MapConverter.toMap(orderHasCoupon));
			if (!DataUtil.isEmpty(coupons)) {
				List<BigInteger> userCouponIds = new ArrayList<BigInteger>();
				for (EbuyOrderHasCoupon coupon : coupons) {
					userCouponIds.add(coupon.gettUserCouponFId());
				}
				//先返还有效，再过期处理过期券
				getUserCouponService().updateUserCouponValidBatchByIds(userCouponIds);
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", userId);
				getUserCouponService().updateUserCouponInvalidBatch(param);
			}*/

			// 标记订单为已删除
			EbuyOrder ebuyOrder = new EbuyOrder();
			ebuyOrder.setId(orderId);
			ebuyOrder.setBuyerId(userId);
			ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.YiShanChu);
			ebuyOrder.setSys0DelState(1);

			if (ebuyOrderDetail.getTotalCouponAmount() != null && totalCouponAmount != null) {// 更新订单金额
				ebuyOrder.setTotalCouponAmount(ebuyOrderDetail.getTotalCouponAmount() - totalCouponAmount);
				ebuyOrder.setAmount(ebuyOrderDetail.getAmount() + totalCouponAmount);
			}
			ebuyOrderBaseDao.updateEbuyOrder(ebuyOrder);

			//删除订单下运单
			EbuyDeliveryOrder ebuyDeliveryOrder = new EbuyDeliveryOrder();
			ebuyDeliveryOrder.settEbuyOrderFId(orderId);
			List<EbuyDeliveryOrder> deliveryOrders = ebuyDeliveryOrderBaseService.getEbuyDeliveryOrderByCondition(MapConverter.toMap(ebuyDeliveryOrder));
			if (!DataUtil.isEmpty(deliveryOrders)) {
				List<BigInteger> delivOrderIds = new ArrayList<BigInteger>(deliveryOrders.size());
				for (EbuyDeliveryOrder deliveryOrder : deliveryOrders) {
					delivOrderIds.add(deliveryOrder.getId());
				}
				ebuyDeliveryOrderBaseService.deleteEbuyDeliveryOrderLogicBatch(delivOrderIds);
			}

		}else{
			throw new BusinessRuntimeException("ebuyService.updateOrder2Cancel.currOrder.statusIsNotDaiFuKuan.error");
		}
	}

	/**
	 * 转化预约单的图片信息，将分号分隔的图片转到List中
	 *
	 * @param db
	 */
	public void processDredgeBillPicInfo(DredgeBillForDetail db) {
		boolean isTransed = db.getBillType() == 2;
		List<String> picInfo = getPicInfoList(isTransed, db.getPicUrl(), db.getSubmitDate());
		db.setPicInfo(picInfo);
	}

	/**
	 * 疏通图片，分号分隔符转化成List返回
	 *
	 * @param picUrls
	 *            多图拼成的串，以分号分隔
	 * @param updTime
	 *            提交时间
	 * @return 多图组成的List，其中每个元素可直接访问
	 */
	public List<String> getPicInfoList(boolean isTransed, String picUrls, String updTime) {
		String iconBasePath;
		if (isTransed) {
			iconBasePath = sysParamManager.getSysParaValue(SysParamKey.Repair_Pic_BasePath);
		} else {
			iconBasePath = sysParamManager.getSysParaValue(SysParamKey.DredgePicBasePath);
		}
		List<String> picInfo = new ArrayList<String>();
		if (!StringUtils.isEmpty(picUrls)) {
			for (String pic : picUrls.split(";")) {
				picInfo.add(fileServerService.getAccessUrl(iconBasePath + pic, (String)null));
			}
		}

		return picInfo;
	}

	/**
	 * 物业维修图片，分号分隔符转化成List返回
	 *
	 * @param picUrls
	 *            多图拼成的串，以分号分隔
	 * @param updTime
	 *            提交时间
	 * @return 多图组成的List，其中每个元素可直接访问
	 */
	public List<String> getPropertyPicInfoList(String picUrls, String updTime) {
		List<String> picInfo = new ArrayList<String>();
		if (!StringUtils.isEmpty(picUrls)) {
			for (String pic : picUrls.split(";")) {
				picInfo.add(fileServerService.getAccessUrl(PathConstants.RepairPicBasePath.substring(1)/*此处第一个/不需要*/ + pic, updTime));
			}
		}
		return picInfo;
	}

	/**
	 * 支付成功后更新疏通服务单状态
	 */
	public void updateDredgeBillAfterPaySuccess(BigInteger orderId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("tEbuyOrderFId", orderId);

		List<DredgeBill> dredgeBillList = dredgeBillBaseDao.selectDredgeBillByCondition(paramMap, false);
		if (dredgeBillList.size() == 0) {
			throw new BusinessRuntimeException("维修单还没有生成支付单");
		} else if (dredgeBillList.size() > 1) {
			throw new BusinessRuntimeException("维修单生成多个支付单");
		}

        DredgeBill nowBill = dredgeBillList.get(0);
        DredgeBill dredgeBill = new DredgeBill();
		dredgeBill.setId(nowBill.getId());
        if (nowBill.getBillType().compareTo(DredgeConstant.DredgeBillType.Dredge_Pay_First) == 0) {
            dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Submited);
        } else {
            dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_FinishedNoComment);
        }


        boolean payFirstJfqBill = dredgeDao.isPayFirstJfqBill(nowBill.getId());
        //前付款，且解放区师傅的，发推送
        if (payFirstJfqBill){
            try {//添加推送消息
				dredgeBill.setAddress(nowBill.getAddress());
				dredgeBill.settDredgeTypeFId(nowBill.gettDredgeTypeFId());
				dredgeBill.settAddressBlockFId(nowBill.gettAddressBlockFId());
                dredgeBill.setPushLevel(DredgeConstant.DredgeWorkerLevel.High_Level);
                new Thread(new PushMessgeThread(dredgeBill, "又有新的订单啦", MsgpushDict.PushId.DredgeBill_AddNew)).start();
            } catch (Exception e) {
                logger.info(e.getMessage(), e);
            }
        }
		dredgeBillBaseDao.updateDredgeBill(dredgeBill);
		//更新耗材库存
		try {
			new UpdateEbuyProductInventoryThread(dredgeBill.getId()).start();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}

	}

	/**
	 * 获取当前师傅可接单区域
	 *
	 * @param userId
	 *            师傅id
	 * @param cityId
	 *            城市id
	 * @param cityName
	 *            城市name
	 * @return 返回可选的城市列表
	 */
	public List<BlockForMaster> qryConfigServiceBlockByCity(BigInteger userId, String cityId, String cityName) {
		return dredgeDao.qryConfigServiceBlockByCity(userId, cityId, cityName);
	}

	/**
	 * 获取师傅的订单列表
	 *
	 * @param userId
	 *            师傅ID
	 * @param type
	 *            1可接订单，2处理中订单，3已完成
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillForMaster> qryDreDgeBillListForMaster(BigInteger userId, String type, PageModel pageModel) {
		return dredgeDao.qryDreDgeBillListForMaster(userId, type, pageModel);
	}

	/**
	 * 接单成功后发送短信通知业主
	 */
	private void sendSMSAfterConfirmReceiving(DredgeBill db, BigInteger userId) {
//		String msg = "尊敬的用户，您的预约单（{0}），师傅（{1}）已接单，联系电话：{2}，请保持电话畅通。【解放区】";
		String msg = "【解放区】亲爱的用户，您预约的（{0}）服务已由（{1}）师傅接单（{2}），请注意接听，以电话确认的服务项目及时间为准！";
		SMSInfo info = dredgeDao.selectSMSInfoByDgId(db.getId());

		String msgContent = MessageFormat.format(msg, info.getDredgeTypeName(), info.getMasterName(), info.getMasterTel());

		SmsMq smsMq = new SmsMq();
		smsMq.setSrcId(db.getId());
		smsMq.setSrcType(SMSMQConstant.SMSMQ_Status_SrcType_Dredge);
		smsMq.setSendStatus(SMSMQConstant.SMSMQ_Status_NotSend);
		smsMq.setMobile(db.getTel());
		smsMq.setContent(msgContent);
		smsMq.setSys0AddUser(userId);
		NotificationDao notificationDao = (NotificationDao) CnfantasiaCommbusiUtil.getBeanManager("notificationDao");
		notificationDao.insertSmsMq(smsMq);
	}

	/**
	 * 师傅确认接单
	 *
	 * @param dredgeBillId
	 *            被接疏通单的id
	 * @param userId
	 *            师傅id
	 * @return
	 */
	public int confirmReceiving(BigInteger dredgeBillId, BigInteger userId) {
		int updCount = dredgeDao.confirmReceiving(dredgeBillId, userId);

		if (updCount > 0) {
			DredgeBill db = dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBillId);

			sendSMSAfterConfirmReceiving(db, userId);

			// 发送短信比较耗时，做成异步的
			//new Thread(new DredgeSMSPusher(commMobileService, dredgeDao, dredgeBillId)).start();

			LoginNo loginNo = new LoginNo();
			loginNo.settUserFId(dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBillId).gettUserFId());
			loginNo.setType(LoginDict.AccountType.WEI_XIN_LIGHT_APP);
			loginNo.setSys0DelState(0);

			if(loginNoBaseDao.selectLoginNoCount(MapConverter.toMap(loginNo), false)>0){
				//如果是微信轻应用用户，添加到待推送的消息队列中
				WechatDredgebillMq wechatDredgebillMq = new WechatDredgebillMq();
				wechatDredgebillMq.setType(DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Type_MasterConfirm);
				wechatDredgebillMq.settDredgeBillFId(dredgeBillId);
				wechatDredgebillMq.setSendStatus(0);
				NotificationDao notificationDao = (NotificationDao) CnfantasiaCommbusiUtil.getBeanManager("notificationDao");
				notificationDao.insertWechatDredgebillMq(wechatDredgebillMq );
			}

			addRedPointInfo(dredgeBillId, db.gettUserFId(), RedpointConstant.RedpointContent_ModelCode.DREDGEBILL_CONFIRMED);
		}

		return updCount;
	}

	@Resource
	ICommMobileService commMobileService;

	/**
	 * 师傅取消接单
	 *
	 * @param dredgeBillId
	 * @param cancelDesc
	 * @return 影响的行数
	 */
	@Transactional
	public int dredgeWorkerCancel(BigInteger userId, BigInteger dredgeBillId, String cancelDesc) {
		//1 增加师傅取消订单的记录
		DredgeWorkerCancel dredgeWorkerCancel = new DredgeWorkerCancel();
		dredgeWorkerCancel.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_worker_cancel));
		dredgeWorkerCancel.settUserFId(userId);
		dredgeWorkerCancel.settCancelReason(cancelDesc);
		dredgeWorkerCancel.settDredgeBillFId(dredgeBillId);
		int insertCount = dredgeWorkerCancelBaseDao.insertDredgeWorkerCancel(dredgeWorkerCancel);

		//2 更新疏通单相关字段，标回可接单状态

		int udpCount = this.dredgeDao.dredgeWorkerCancel(userId, dredgeBillId);

		return insertCount + udpCount;
	}

	/**
	 * 提交当前师傅配置的接单区域
	 *
	 * @param userId
	 *            师傅id
	 * @param blockIdList
	 *            选中的区域id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void submitConfigServiceBlock(BigInteger userId, List<BigInteger> blockIdList) {
		dredgeDao.submitConfigServiceBlock(userId, blockIdList);
	}

	/**
	 *
	 * 提交当前师傅配置的接单区域
	 *
	 * @param userId
	 *            师傅id
	 * @param dredgeTypeIdList
	 *            选中的服务类型
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void submitConfigServiceType(BigInteger userId, List<BigInteger> dredgeTypeIdList) {
		dredgeDao.submitConfigServiceType(userId, dredgeTypeIdList);
	}

	/**
	 * 提交实名认证
	 *
	 * @param userId
	 *            用户id
	 * @param realName
	 *            实名姓名
	 * @param idNumber
	 *            身份证号
	 * @param personalDesc
	 *            个人描述
	 * @param picList
	 *            身份证的正反面信息
	 */
	@Transactional
	public void updateCertificateRealName(BigInteger userId, String realName, String idNumber, String personalDesc, List<RequestFileEntity> picList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tUserFId", userId);
		paramMap.put("sys0DelState", 0);
		List<DredgeWorker> DredgeWorkerList = dredgeWorkerBaseDao.selectDredgeWorkerByCondition(paramMap, false);
		if (DredgeWorkerList.size() > 1) {
			throw new BusinessRuntimeException("user has more one dredge Worker info");
		} else if (DredgeWorkerList.size() == 0) {
			throw new BusinessRuntimeException("user should has one dredge Worker info ");
		}
		long startTime = System.currentTimeMillis();
		String picInfos = uploadDredgePic(userId, picList);
		logger.info("upload pic use time: " + (System.currentTimeMillis() - startTime));

		startTime = System.currentTimeMillis();

		//更新师傅身份证号等信息
		DredgeWorker dredgeWorker = new DredgeWorker();
		dredgeWorker.setId(DredgeWorkerList.get(0).getId());
		dredgeWorker.setIdNo(idNumber);
		dredgeWorker.setRealName(realName);
		dredgeWorker.setPersonalDesc(personalDesc);
		dredgeWorker.setAuditStatus(DredgeConstant.DredgeWorker.DredgeWorker_Status_WaitCertificate);
		String[] idNumberPics = picInfos.split(";");
		dredgeWorker.setIdNumberPic1(idNumberPics[0]);
		dredgeWorker.setIdNumberPic2(idNumberPics[1]);
		dredgeWorkerBaseDao.updateDredgeWorker(dredgeWorker);

		logger.info("update dredgeWorker use time: " + (System.currentTimeMillis() - startTime));
	}

	/**
	 * 查看预约单详情-师傅端
	 *
	 * @param id
	 * @return
	 */
	public DredgeBillForMaster qryDredgeBillDetailForMaster(String id) {
		return dredgeDao.qryDredgeBillDetailForMaster(id);
	}

	/**
	 * 查询师傅个人信息
	 *
	 * @param userId
	 *            师傅的id
	 * @return
	 */
	public MasterProfile qryMyProfileById(BigInteger userId) {
		return dredgeDao.qryMyProfileById(userId);
	}

	/**
	 * 查询师傅可选择的疏通类型
	 *
	 * @param paramMap
	 * @return
	 */
	public List<DredgeParentType> qryConfigServiceType(Map<String, Object> paramMap) {
		return dredgeDao.qryConfigServiceType(paramMap);
	}

	public AccountAmount qryAccountAmount(BigInteger userId) {
		return dredgeDao.qryAccountAmount(userId);
	}

	public List<IncomeWithdrawRecorder> qryIncomeOrWithdrawList(Map<String, Object> paramMap, PageModel pageModel) {
		return dredgeDao.qryIncomeOrWithdrawList(paramMap, pageModel);
	}

	/**
	 * 查询可提现的维修列表(师傅)
	 *
	 * @param userId
	 * @return
	 */
	public List<CanWithdrawBill4Master> selectCanWithdrawList_master(BigInteger userId) {
		return dredgeDao.selectCanWithdrawList_master(userId);
	}

	/**
	 * 查询可提现的维修列表(推荐人)
	 *
	 * @param userId
	 * @return
	 */
	public List<CanWithdrawBill> selectCanWithdrawList_recommend(BigInteger userId) {
		return dredgeDao.selectCanWithdrawList_recommend(userId);
	}

	public ApplyWithdrawInfo selectApplyDetail_byApplyId(BigInteger applyRevenueId) {
		return dredgeDao.selectApplyDetail_byApplyId(applyRevenueId);
	}

	/**
	 * 查询师傅的提现记录
	 *
	 * @param userId
	 *            师傅对应的userId
	 * @param pageModel
	 *            分页信息
	 * @return
	 */
	public List<ApplyListForMaster> selectApplyList_byMasterId(
			BigInteger userId, PageModel pageModel) {
		return dredgeDao.selectApplyList_byMasterId(userId, pageModel);
	}

	@Resource
	IRevenueDao revenueDao;
	@Resource
	IRevenueApplyBaseDao revenueApplyBaseDao;

	/**
	 * 师傅发起提现
	 *
	 * @param userId
	 * @param dredgeBillIdList
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public BigInteger applyRevenue_master(BigInteger userId, List<BigInteger> dredgeBillIdList) {
		//先调用一下自动任务生成收益
		//注释以下这行，task只能在job里跑，才能保证生成收益是按最新的生成；这里直接调用与发布的版本相关 Added by wenfq 2016-09-05
		//((RevenueTask)CnfantasiaCommbusiUtil.getBeanManager("revenueTask")).synDredgePayAmoutForMaster();

		BigInteger applyAddId = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_apply);
		String lastEndTime = revenueDao.selectLastEndTime_byMasterUserId(userId);//上次结算的时间
		String thisEndTime = DateUtil.formatSecond.get().format(new Date(System.currentTimeMillis()));
		//标记sig为结算中的状态 //更新提款明细的状态
		revenueDao.updateRevenueSignalAmountAsDoing(applyAddId, userId, RevenueDict.MiniRoleType.RepairMaster, RevenueDict.RevenueProject.ServiceOrder,
				lastEndTime, thisEndTime, RevenueDict.TkOptType.User);
		RevenueMoneyShow revenueMoneyShow = revenueDao.selectRevenueApplyTotalFromDetail(applyAddId);
		Double totalAmount = revenueMoneyShow.getTotalAmount();
		//Double amountPtbt = revenueMoneyShow.getAmountPtbt();
		//Double amountUsrReal = revenueMoneyShow.getAmountUsrReal();
		if(totalAmount==null||totalAmount<=0){
			throw new BusinessRuntimeException("RevenueService.applyRevenue.amount0");
		}
		RevenueApply toAddRevenueApply = new RevenueApply();
		{//新增提款记录
			String applyNo = RevenueTkNoGenerator.getOrderNo(applyAddId);//设置提款单号
			Integer tkStatus = RevenueDict.TkStatus.Doing;
			String applyTime = ApplicationContextBothUtil.getDualDao().getNowTime();
			String tkSuccTime = null;
			toAddRevenueApply.setApplyNo(applyNo);
			toAddRevenueApply.setApplyTime(applyTime);
			toAddRevenueApply.setApplyUserId(userId);
			toAddRevenueApply.setGoalEndTime(thisEndTime);
			toAddRevenueApply.setGoalStartTime(lastEndTime);
			toAddRevenueApply.setGoalType(RevenueDict.RevenueProject.ServiceOrder);
			toAddRevenueApply.setId(applyAddId);
			toAddRevenueApply.setMiniRoleId(userId);
			toAddRevenueApply.setMiniRoleType(RevenueDict.MiniRoleType.RepairMaster);
			toAddRevenueApply.setOptType(RevenueDict.TkOptType.User);
			toAddRevenueApply.setTkStatus(tkStatus);
			toAddRevenueApply.setTkSuccTime(tkSuccTime);
			toAddRevenueApply.setVisibleType(RevenueDict.VisibleType.UserVisible);

			toAddRevenueApply.setTotalAmount(totalAmount);
//			if(amountPtbt >= totalAmount) { //当补贴的金额要大时，补贴直接等于收益金额处理，保证：收益totalAmount = 补贴amountPtbt + 实缴amountUsrReal
//				toAddRevenueApply.setAmountPtbt(totalAmount);
//				toAddRevenueApply.setAmountUsrReal(0.0);
//			} else {
//				toAddRevenueApply.setAmountPtbt(amountPtbt);
//				toAddRevenueApply.setAmountUsrReal(totalAmount - (amountPtbt == null ? 0 : amountPtbt));
//			}

			{
				double dredgeAmountUsrReal = selectDredgeAmountUsrReal(applyAddId);
				toAddRevenueApply.setAmountPtbt(totalAmount - dredgeAmountUsrReal);
				toAddRevenueApply.setAmountUsrReal(dredgeAmountUsrReal);
			}

			toAddRevenueApply.setRoleName("");

			Integer resCount = revenueApplyBaseDao.insertRevenueApply(toAddRevenueApply);
			if(resCount!=null&&resCount>0){
				resCount = revenueDao.updateRevenueApplyBakInfo(applyAddId, RevenueDict.MiniRoleType.RepairMaster);
			}
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RevenueService.applyRevenue.addApply.count0");
			}
		}
		//返回本次提款的Id
		return applyAddId;
	}

	/**
	 * <p>查询师傅实付（收益中心）<br>
	 * 1、实际支付 >= 平台收入<br>
	 *   dredgeAmountUsrReal = 实付-平台收入<br>
	 *   dredgeAmountPtbt = 订单金额-平台收入-代收<br>
	 * 2、实际支付 < 平台收入 <br>
	 *   dredgeAmountUsrReal=0<br>
	 *   dredgeAmountPtbt=订单金额-平台收入<br>
	 *
	 *   参考“【系统管理】-【收益管理】-【上门维修收益明细】-导出，”师傅收入“代收”、“粮票补贴”
	 * </p>
	 *
	 * @param applyId
	 * @return
	 */
	private Double selectDredgeAmountUsrReal(BigInteger applyId){
		BigDecimal amountUsrReal = BigDecimal.ZERO;
		List<DredgeRevenue> dredgeRevenues = revenueDao.selectDredgeAmountPtbt(applyId);
		for (DredgeRevenue dredgeRevenue : dredgeRevenues) {
			BigDecimal platfromProfit = dredgeRevenue.getSrcMoney().subtract(dredgeRevenue.getAmountRepair());
			BigDecimal amountRepair = BigDecimal.ZERO;
			if(dredgeRevenue.getAmountOrderReal().doubleValue() >= platfromProfit.doubleValue()){
				amountRepair = dredgeRevenue.getAmountOrderReal().subtract(platfromProfit);
			}
			amountUsrReal = amountUsrReal.add(amountRepair);
		}
		return amountUsrReal.doubleValue();
	}

	/**
	 * 推荐人发起提现
	 * @param userId 用户ID
	 * @param dredgeBillIdList 提现的ID列表
	 * @return
	 */
	public BigInteger applyRevenue_recommend(BigInteger userId,
											 List<BigInteger> dredgeBillIdList) {
		//先调用一下自动任务生成收益
//				((RevenueTask)CnfantasiaCommbusiUtil.getBeanManager("revenueTask")).synDredgePayAmoutForMaster();

		BigInteger applyAddId = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_apply);
		String lastEndTime = revenueDao.selectLastEndTime_byRecommendUserId(userId);//上次结算的时间
		String thisEndTime = DateUtil.formatSecond.get().format(new Date(System.currentTimeMillis()));
		//标记sig为结算中的状态 //更新提款明细的状态
		revenueDao.updateRevenueSignalAmountAsDoing(applyAddId, userId, RevenueDict.MiniRoleType.Recommender, RevenueDict.RevenueProject.ServiceOrder,
				lastEndTime, thisEndTime, RevenueDict.TkOptType.User);
		RevenueMoneyShow revenueMoneyShow = revenueDao.selectRevenueApplyTotalFromDetail(applyAddId);
		Double totalAmount = revenueMoneyShow.getTotalAmount();
		Double amountPtbt = revenueMoneyShow.getAmountPtbt();
		Double amountUsrReal = revenueMoneyShow.getAmountUsrReal();
		if(totalAmount==null||totalAmount<=0){
			throw new BusinessRuntimeException("RevenueService.applyRevenue.amount0");
		}
		RevenueApply toAddRevenueApply = new RevenueApply();
		{//新增提款记录
			String applyNo = RevenueTkNoGenerator.getOrderNo(applyAddId);//设置提款单号
			Integer tkStatus = RevenueDict.TkStatus.Doing;
			String applyTime = ApplicationContextBothUtil.getDualDao().getNowTime();
			String tkSuccTime = null;
			toAddRevenueApply.setApplyNo(applyNo);
			toAddRevenueApply.setApplyTime(applyTime);
			toAddRevenueApply.setApplyUserId(userId);
			toAddRevenueApply.setGoalEndTime(thisEndTime);
			toAddRevenueApply.setGoalStartTime(lastEndTime);
			toAddRevenueApply.setGoalType(RevenueDict.RevenueProject.ServiceOrder);
			toAddRevenueApply.setId(applyAddId);
			toAddRevenueApply.setMiniRoleId(userId);
			toAddRevenueApply.setMiniRoleType(RevenueDict.MiniRoleType.Recommender);
			toAddRevenueApply.setOptType(RevenueDict.TkOptType.User);
			toAddRevenueApply.setTkStatus(tkStatus);
			toAddRevenueApply.setTkSuccTime(tkSuccTime);
			toAddRevenueApply.setVisibleType(RevenueDict.VisibleType.UserVisible);

			toAddRevenueApply.setTotalAmount(totalAmount);
			toAddRevenueApply.setAmountPtbt(amountPtbt);
			toAddRevenueApply.setAmountUsrReal(amountUsrReal);

			toAddRevenueApply.setRoleName(UserContext.getCurrLoginNo().getUserEntity().getMobile());

			Integer resCount = revenueApplyBaseDao.insertRevenueApply(toAddRevenueApply);
			if(resCount!=null&&resCount>0){
				resCount = revenueDao.updateRevenueApplyBakInfo(applyAddId, RevenueDict.MiniRoleType.Recommender);
			}
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RevenueService.applyRevenue.addApply.count0");
			}
		}
		//返回本次提款的Id
		return applyAddId;
	}


	/**
	 * 师傅审核列表
	 *
	 * @param paramMap
	 * @return
	 */
	public List<DredgeMasterList4admin> selectMasterList4Audit(Map<String, Object> paramMap) {
		return dredgeDao.selectMasterList4Audit(paramMap);
	}

	/**
	 * 返回师傅列表总数
	 *
	 * @param paramMap
	 * @return
	 */
	public int getMaster4AuditListSize(Map<String, Object> paramMap) {
		return dredgeDao.getMaster4AuditListSize(paramMap);
	}
	
	/**
	 * 查询师傅详情
	 * @param dwId
	 * @return
	 */
	public MasterInfo4Audit selectMasterDetail_forAudit(java.math.BigInteger dwId){
		return dredgeDao.selectMasterDetail_forAudit(dwId);
	}

	/**
	 * 查询维修类型
	 *
	 * @return
	 */
	public List<Map> selectDredgeTypeNameList() {
		return dredgeDao.selectDredgeTypeNameList();
	}

	public int qryOpenDredgeService_by_cstId_and_cityName(Map<String, Object> paramMap){
		return dredgeDao.qryOpenDredgeService_by_cstId_and_cityName(paramMap);
	}
	public BigInteger qryBlockId_by_roomId(BigInteger roomId) {
		return dredgeDao.qryBlockId_by_roomId(roomId);
	}

	public List<DredgeTypeEntity> getDredgeTypeByParentTypeId(BigInteger parentTypeId, List<BigInteger> addrCodeIdList, boolean forceInclude2nd) {
		List<DredgeTypeEntity> entityList = dredgeDao.selectDredgeTypeByParentTypeId(parentTypeId, addrCodeIdList, forceInclude2nd);
		List<DredgeTypeEntity> resList = new ArrayList<>();
		if (parentTypeId.compareTo(DredgeConstant.ParentCommunitySupplyType.WYBX) == 0) {
            BigInteger userId = UserContext.getOperIdMustExistBigInteger();
            User user = userBaseDao.selectUserBySeqId(userId);
            Map<String, Object> addrMap = getRoomAddressIdByRoom(user.getDefaultRoomId());
			Long managementFId = (Long) addrMap.get("managementId");
			if (managementFId != null) {
				PropertyRepairType type;
				for (DredgeTypeEntity dredgeTypeEntity : entityList) {
					type = new PropertyRepairType();
					type.settDredgeTypeFId(dredgeTypeEntity.getId());
					type.settPropertyManagementFId(BigInteger.valueOf(managementFId));
					if (propertyRepairTypeBaseService.getPropertyRepairTypeCount(MapConverter.toMap(type)) > 0) {
						resList.add(dredgeTypeEntity);
					}
				}
			}
		} else {
			resList = entityList;
		}
		return resList;
	}

	public List<DredgeProductEntity> getDredgeProductListByDredgeType2ndId(BigInteger dredgeType2ndId,
                                                                           List<BigInteger> addrCodeIdList,
                                                                           PageModel pageModel) {
		return dredgeDao.getDredgeProductListByDredgeType2ndId(dredgeType2ndId, addrCodeIdList, pageModel);
	}

	public DredgeProductEntity getDredgeProductDetail(BigInteger dredgeProductId) {
		return dredgeDao.getDredgeProductDetail(dredgeProductId);
	}

	public void setPayAmountForBill(BigInteger dredgeBillId, Integer type,
									BigDecimal laborAmount, BigDecimal materialAmount, JsonResponse jsonResponse) {
		DredgeBill db = dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBillId);
		if(!db.gettWorkerFId().equals(UserContext.getOperIdMustExistBigInteger())){
			jsonResponse.setMessage("订单已被他人抢走，不能设置金额");
			jsonResponse.putData("isSuccess", 0);
			return;
		}
		if (db.gettEbuyOrderFId() != null) {
			jsonResponse.setMessage("订单支付中，不能重设金额");
			jsonResponse.putData("isSuccess", 0);
		} else if (db.getStatus() == DredgeConstant.DredgeBill.Master_Set_Free) {
			jsonResponse.setMessage("当前状态不能重设金额");
			jsonResponse.putData("isSuccess", 0);
		} else {
			DredgeBill dredgeBill = new DredgeBill();
			dredgeBill.setId(dredgeBillId);
			long laborAmountLong = laborAmount.multiply(new BigDecimal("100")).longValue();
			long materialAmountLong = materialAmount.multiply(new BigDecimal("100")).longValue();
			dredgeBill.setPayAmount(laborAmountLong + materialAmountLong);
			if (type != null && type != 1) {
				dredgeBill.setStatus(DredgeConstant.DredgeBill.Master_Set_Free);
			} else {
				dredgeBill.setStatus(DredgeConstant.DredgeBill.Master_Finish);
			}
			int i = dredgeBillBaseDao.updateDredgeBill(dredgeBill);

			if (db.getPayAmount() == null) {//首次设置
				i += insertAmountDetail(dredgeBillId, laborAmountLong, materialAmountLong);
			} else {//非首次
				i += updateAmountDetail(dredgeBillId, laborAmountLong, materialAmountLong);
			}

			if (i == 0) {
				jsonResponse.setMessage("订单金额未更新，金额设置失败");
				jsonResponse.putData("isSuccess", 0);
			} else if (i >= 1) {
				jsonResponse.setMessage("金额设置成功");
				jsonResponse.putData("isSuccess", 1);

				LoginNo loginNo = new LoginNo();
				loginNo.setType(LoginDict.AccountType.WEI_XIN_LIGHT_APP);
				loginNo.settUserFId(db.gettUserFId());
				loginNo.setSys0DelState(0);

				if (loginNoBaseDao.selectLoginNoCount(MapConverter.toMap(loginNo), false) > 0) {
					//如果是微信轻应用用户，添加到待推送的消息队列中
					WechatDredgebillMq wechatDredgebillMq = new WechatDredgebillMq();
					wechatDredgebillMq.setType(DredgeConstant.WeChatDredgeBillMQ.WeChatDredgeBillMQ_Type_SetPayAmount);
					wechatDredgebillMq.settDredgeBillFId(dredgeBillId);
					wechatDredgebillMq.setSendStatus(0);
					NotificationDao notificationDao = (NotificationDao) CnfantasiaCommbusiUtil.getBeanManager("notificationDao");
					notificationDao.insertWechatDredgebillMq(wechatDredgebillMq);
				}

				addRedPointInfo(dredgeBillId, db.gettUserFId(), RedpointConstant.RedpointContent_ModelCode.DREDGEBILL_SETPAYAMOUNT);
			}
		}
	}

	/**
	 * 更新维修单详情
	 * @param dredgeBillId
	 * @param laborAmountLong
	 * @param materialAmountLong
	 * @return
	 */
	private int updateAmountDetail(BigInteger dredgeBillId, long laborAmountLong, long materialAmountLong) {
		DredgeBillAmountDetail dredgeBillAmountDetailQry = new DredgeBillAmountDetail();
		dredgeBillAmountDetailQry.settDredgeBillFId(dredgeBillId);

		List<DredgeBillAmountDetail> dredgeBillAmountDetailList = dredgeBillAmountDetailBaseDao.selectDredgeBillAmountDetailByCondition(MapConverter.toMap(dredgeBillAmountDetailQry), false);
		for(DredgeBillAmountDetail dredgeBillAmountDetail: dredgeBillAmountDetailList){
			if(dredgeBillAmountDetail.getFeeType() == DredgeConstant.DredgeBillAmountDetailType.Labor_fee){
				dredgeBillAmountDetail.setPayAmount(laborAmountLong);
				dredgeBillAmountDetail.setSys0UpdTime(DateUtils.getCurrentDate());
			}else if(dredgeBillAmountDetail.getFeeType() == DredgeConstant.DredgeBillAmountDetailType.Material_fee){
				dredgeBillAmountDetail.setPayAmount(materialAmountLong);
				dredgeBillAmountDetail.setSys0UpdTime(DateUtils.getCurrentDate());
				//materialAmountLong == 0 也不用删除。
			}
		}

		int updCount = 0;
		if(dredgeBillAmountDetailList.size() == 1 && materialAmountLong > 0){ // 非首次设置其它费
			DredgeBillAmountDetail dbad = new DredgeBillAmountDetail();
			dbad.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_amount_detail));
			dbad.settDredgeBillFId(dredgeBillId);
			dbad.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Material_fee);
			dbad.setIsIncludePlatformFee(0);
			dbad.setFeeName("其它费");
			dbad.setPayAmount(materialAmountLong);
			updCount += dredgeBillAmountDetailBaseDao.insertDredgeBillAmountDetail(dbad);
		} else if(dredgeBillAmountDetailList.size() == 0) {
			DredgeBillAmountDetail dbad = new DredgeBillAmountDetail();
			dbad.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_amount_detail));
			dbad.settDredgeBillFId(dredgeBillId);
			dbad.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Labor_fee);
			dbad.setIsIncludePlatformFee(1);
			dbad.setFeeName("人工费");
			dbad.setPayAmount(laborAmountLong);
			updCount += dredgeBillAmountDetailBaseDao.insertDredgeBillAmountDetail(dbad);

			dbad.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_amount_detail));
			dbad.settDredgeBillFId(dredgeBillId);
			dbad.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Material_fee);
			dbad.setIsIncludePlatformFee(0);
			dbad.setFeeName("其它费");
			dbad.setPayAmount(materialAmountLong);
			updCount += dredgeBillAmountDetailBaseDao.insertDredgeBillAmountDetail(dbad);
			return updCount;
		}

		updCount += dredgeBillAmountDetailBaseDao.updateDredgeBillAmountDetailBatch(dredgeBillAmountDetailList);
		return updCount;
	}

	/**
	 * 插入费用明细
	 * @param dredgeBillId
	 * @param laborAmountLong 人工费
	 * @param materialAmountLong 其它费
	 * @return
	 */
	private int insertAmountDetail(BigInteger dredgeBillId, long laborAmountLong, long materialAmountLong) {
		DredgeBillAmountDetail dbad = new DredgeBillAmountDetail();
		dbad.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_amount_detail));
		dbad.settDredgeBillFId(dredgeBillId);
		dbad.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Labor_fee);
		dbad.setIsIncludePlatformFee(1);
		dbad.setFeeName("人工费");
		dbad.setPayAmount(laborAmountLong);
		int i = 0;
		i += dredgeBillAmountDetailBaseDao.insertDredgeBillAmountDetail(dbad);

		if(materialAmountLong > 0){
			dbad.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_amount_detail));
			dbad.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Material_fee);
			dbad.setIsIncludePlatformFee(0);
			dbad.setFeeName("其它费");
			dbad.setPayAmount(materialAmountLong);
			i += dredgeBillAmountDetailBaseDao.insertDredgeBillAmountDetail(dbad);
		}
		return i;
	}

	/**
	 * 添加红点提醒
	 *
	 * @param dredgeBillId
	 *            上门维修单id
	 * @param userId
	 *            需提醒的用户id
	 * @param modelCode
	 *            红点模块
	 */
	private void addRedPointInfo(BigInteger dredgeBillId, BigInteger userId, String modelCode) {
		{// 准备红点提醒信息
			List<CommUserDataEntity> commUserDataEntityList = new ArrayList<CommUserDataEntity>();

			User user = userBaseDao.selectUserBySeqId(userId);
			Integer tmpUserType = LoginDict.UserRegistOrTmp.REGIST_USER;// 已注册的用户

			CommUserDataEntity tmpCommUserDataEntity = new CommUserDataEntity(userId, tmpUserType, user.getDefaultRoomId());
			commUserDataEntityList.add(tmpCommUserDataEntity);

			{// 添加红点提醒
				List<BasicSourceEntity> sourceList = new ArrayList<BasicSourceEntity>();
				sourceList.add(new BasicSourceEntity(RedpointDict.Redpoint_Content_SourceType.Dredage, dredgeBillId,
						"UPDATE"));
				RedpointService redpointService = (RedpointService) CnfantasiaCommbusiUtil.getBeanManager("redpointService");
				FutureTask<Boolean> task = new FutureTask<Boolean>(new RedpointAddRunnableMulti(redpointService,
						commUserDataEntityList, modelCode, sourceList));
				new Thread(task).start();
			}
		}
	}

	public int qryNewDredgeBillCountForMaster(int type, BigInteger userId, String curTime) {
		return dredgeDao.qryNewDredgeBillCountForMaster(type, userId, curTime);
	}

	@Resource
	UserHasTMessageBaseDao userHasTMessageBaseDao;

	/**
	 * 维修单列表
	 * @param paramMap
	 * @return
	 */
	public List<DredgeBillEntity> getDredgeBillList(Map<String, Object> paramMap) {
		return dredgeDao.getDredgeBillList(paramMap);
	}

	public int getDredgeBillListCount(Map<String, Object> paramMap) {
		return dredgeDao.getDredgeBillListCount(paramMap);
	}

	/**
	 * 查询维修师傅list
	 * @return
	 */
	public List<Map<String, Object>> getDredgeWorkerList() {
		return dredgeDao.getDredgeWorkerList();
	}

	/**
	 * 维修单跟进
	 * @param paramMap
	 * @return
	 */
	public int updateDredgeBillProgress(Map<String, Object> paramMap) {
		//跟进信息像日志一样，不需要进行更新，每次操作都需要进行插入一条
		DredgeBillFollowRecord dredgeBillFollowRecord = new DredgeBillFollowRecord();
		dredgeBillFollowRecord.setId(uuidManager.getNextUuidBigInteger("t_dredge_bill_follow_record"));
		dredgeBillFollowRecord.setContent(paramMap.get("rateProgress").toString());
		dredgeBillFollowRecord.setFollowTime(DateUtils.getCurrentDate());
		dredgeBillFollowRecord.setFollowUser(paramMap.get("userAccount").toString());
		dredgeBillFollowRecord.settDredgeBillFId(new BigInteger(paramMap.get("dredgeBillId").toString()));
		return dredgeBillFollowRecordBaseService.insertDredgeBillFollowRecord(dredgeBillFollowRecord);
	}

	/**
	 * 修改维修单状态
	 * @param paramMap
     */
	public void editDredgeBillStatus(Map<String, Object> paramMap) {
		//更新维修单信息
		dredgeDao.editDredgeBillStatus(paramMap);
		//删除新维修单费用明细信息
		dredgeDao.delDredgeBillAmountDetail(new BigInteger(paramMap.get("dredgeBillId").toString()));
	}

	class PushMessgeThread implements Runnable{
		private DredgeBill dredgeBill = null;

		private String msgTitle = "";

		private String pushId  = "";

		public PushMessgeThread(DredgeBill dredgeBill, String msgTitle, String pushId) {
			this.dredgeBill = dredgeBill;
			this.msgTitle = msgTitle;
			this.pushId = pushId;
		}

		@Override
		public void run() {
			addDredgeBillPushMessage(dredgeBill, msgTitle, pushId);
		}
	}

	class UpdateEbuyProductInventoryThread extends Thread{

		private BigInteger dredgeBillId = null;

		public UpdateEbuyProductInventoryThread(BigInteger dredgeBillId) {
			this.dredgeBillId = dredgeBillId;
		}

		public void run() {
			updateEbuyProductInventory(dredgeBillId);
		}
	}

	/**
	 * 添加消息推送
	 *
	 * @author wenfq 2016-03-01
	 */
	public void addDredgeBillPushMessage(DredgeBill dredgeBill, String msgTitle, String pushId){
		DredgeType dt = dredgeTypeBaseDao.selectDredgeTypeBySeqId(dredgeBill.gettDredgeTypeFId());
		String now = DateUtils.getCurrentDate();

		Message msg = new Message();
		msg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message));
		msg.setTitle(msgTitle);
		msg.setContent("订单类型：" + dt.getName() + "，订单地址：" + dredgeBill.getAddress());
		msg.setTime(now);
		msg.setType(NoticeDict.Message_Type.Dredge);
		msg.setIsRelateRoom(0);//维修消息与门牌无关
		//推送等级
		int workerLevel = dredgeBill.getPushLevel();
		List<CommUserDataEntity> masterUserList = null;


		List<DredgeBillPushTime> pushTimes = new ArrayList<DredgeBillPushTime>();
		List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_push_time,
				DredgeConstant.DredgeWorkerLevel.Low_Level - workerLevel + 1);
		int idx = 0;
		while(workerLevel <= DredgeConstant.DredgeWorkerLevel.Low_Level) {
			//记录推送时间
			BigInteger id = ids.get(idx);
			idx++;
			DredgeBillPushTime dredgeBillPushTime = new DredgeBillPushTime();
			dredgeBillPushTime.setId(id);
			dredgeBillPushTime.setPushLevel(workerLevel);
			dredgeBillPushTime.settDredgeBillFId(dredgeBill.getId());
			dredgeBillPushTime.setPushTime(now);
			pushTimes.add(dredgeBillPushTime);

			//查找推送对象
			masterUserList = dredgeDao.getMasterList4Push(
					dredgeBill.gettAddressBlockFId(),dredgeBill.gettDredgeTypeFId(), workerLevel);

			workerLevel++;
			if (masterUserList != null && masterUserList.size() > 0)
				break;
		}

		//批量插入推送时间
		dredgeBillPushTimeBaseService.insertDredgeBillPushTimeBatch(pushTimes);

		//修改维修单的当前推送等级及推送时间
		DredgeBill newBill = new DredgeBill();
		newBill.setId(dredgeBill.getId());
		newBill.setPushLevel(workerLevel - 1);
		newBill.setPushTime(now);
		dredgeBillBaseDao.updateDredgeBill(newBill);

		//有可推送用户才推送
		if (masterUserList != null && masterUserList.size() > 0) {
			List<MessageParameter> mps = new ArrayList<MessageParameter>();
			MessageParameter tmpMessageParameter = new MessageParameter();
			tmpMessageParameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
			tmpMessageParameter.settMessageFId(msg.getId());
			tmpMessageParameter.setKey("pushId");
			tmpMessageParameter.setValue(pushId);

			mps.add(tmpMessageParameter);
			// 超时时间是第二天0点
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_YEAR, 1);
			IMsgpushService msgpushService = (IMsgpushService) CnfantasiaCommbusiUtil.getBeanManager("msgpushService");
			msgpushService.addMessage2Pool(masterUserList, msg, DateUtil.formatDay.get().format(c.getTime()), mps);
		}

	}


	public DredgeBillForMaster qryDredgeBillDetailForMaster(String id,
															int isPropertyBill) {
		if(isPropertyBill == 0){
			return qryDredgeBillDetailForMaster(id);
		}else if(isPropertyBill == 1){
			return dredgeDao.qryRepairBillDetailForMaster(id);
		}

		return null;
	}

	public int setServiceTime(Map<String, Object> paramMap) {
		Integer billType = (Integer) paramMap.get("billType");
		String serviceTime = (String) paramMap.get("serviceTime");
		if (DredgeConstant.DredgeBillType.Property_Repair == billType) {
			PropertyRepair pr = new PropertyRepair();
			pr.setId((BigInteger) paramMap.get("id"));
			pr.setSys0UpdUser((BigInteger) paramMap.get("userId"));
			pr.setEstimateDoorTimeBegin(serviceTime);

			return propertyRepairBaseDao.updatePropertyRepair(pr);
		} else {
			DredgeBill db = new DredgeBill();
			db.setId((BigInteger) paramMap.get("id"));
			db.setExpectWorkTime(serviceTime);
			return dredgeBillBaseDao.updateDredgeBill(db);
		}

	}

	/**
	 * 物业维修单，师傅点击完成
	 * @param paramMap
	 * @return
	 */
	public int confirmFinished(Map<String, Object> paramMap) {
		PropertyRepair pr = new PropertyRepair();
		pr.setId((BigInteger) paramMap.get("id"));
		pr.setSys0UpdUser((BigInteger) paramMap.get("userId"));
		pr.setRepaireState(3);//已结束
		pr.setFinishedTime(DateUtils.getCurrentDate());
		pr.setFinishDesc("师傅点击确认完成");
		int updCount = propertyRepairBaseDao.updatePropertyRepair(pr);
		if (updCount == 1) {
			try {//添加订单完成红点
				PropertyRepair propertyRepair = propertyRepairBaseDao.selectPropertyRepairBySeqId((BigInteger) paramMap.get("id"));
				List<BasicSourceEntity> sourceList = new ArrayList<BasicSourceEntity>();
				sourceList.add(new BasicSourceEntity(RedpointDict.Redpoint_Content_SourceType.Dredage, propertyRepair.getId(), "UPDATE"));
				IRedpointService redpointService = (IRedpointService) CnfantasiaCommbusiUtil.getBeanManager("redpointService");
				redpointService.addRedpointContent(propertyRepair.gettUserFId(), LoginDict.UserRegistOrTmp.REGIST_USER, propertyRepair.gettRoomFId(),
						RedpointConstant.RedpointContent_ModelCode.DREDGEBILL_HASFINISHED, sourceList);
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
			}
		}

		//产生首页消息
		UserHasHomeMessage message = new UserHasHomeMessage();
		message.setMessageCode(HomeMessageDict.MessageCode.PROPERTY_REPAIR_ALERT);
		message.setResouceId(pr.getId());
		homeMessageService.generateCommonMsg(message);
		return updCount;
	}

	/**
	 * 预约单支付成功，更新耗材库存
	 * @param dredgeBillId
	 * @return
	 */
	private int updateEbuyProductInventory(BigInteger dredgeBillId) {
		//查询需要更新的耗材信息
		List<Map<BigInteger, Integer>> productList = dredgeDao.selectEbuyProductByDredgeBillId(dredgeBillId);
		if(productList != null && productList.size() > 0) {
			return dredgeDao.updateEbuyProductInventory(productList);
		}
		return 0;
	}

	/**
	 * 发送 耗材信息短息
	 * @param dredgeBill 维修单
	 * @param sendType 短息类型（msg.DREDGE_SHOP 店主，msg.DREDGE_MASTER 师傅）
	 * @return
	 */
	public void sendMsgThread(DredgeBill dredgeBill, String sendType) {
		sendMsg(dredgeBill, sendType);
	}

	public void sendMsgThread(BigInteger dredgeBillId, String sendType) {
		DredgeBill dredgeBill = getDredgeBillById(dredgeBillId);
		sendMsg(dredgeBill, sendType);
	}

	private void sendMsg(DredgeBill dredgeBill, String sendType) {
		if(sendType.equals("msg.DREDGE_MASTER")) {//发送给师傅
			String mobile = dredgeDao.getDredgeMasterMobile(dredgeBill.gettWorkerFId());
			if(mobile != null && !"".equals(mobile)) {
				List<DredgeMsgEntity> dredgeMsgList = dredgeDao.getDredgeBillMsgContent(dredgeBill.getId());
				for (DredgeMsgEntity dredgeMsgEntity : dredgeMsgList) {
					int totalNum = 0;
					String listStr = "";
					for (DredgeBillHasEbuyProductShelfEnity dredgeBillHasEbuyProductShelfEnity : dredgeMsgEntity.getDredgeBillHasEbuyProductShelfList()) {
						totalNum = totalNum + dredgeBillHasEbuyProductShelfEnity.getQuantity();
						listStr += dredgeBillHasEbuyProductShelfEnity.getProductName() + " " + dredgeBillHasEbuyProductShelfEnity.getQuantity()+"件、";
					}
					listStr = listStr.substring(0, listStr.length()-1);//去除末尾的符号
					Object[] msgParam = {dredgeBill.getTel(),dredgeMsgEntity.getMerchantName(),totalNum,listStr};
//					String msg = SmsPropertyUtil.getProperty(sendType, msgParam);
//					commMobileService.sendMsg(mobile, msg);
					ShortMsgUtil.sendMessage(mobile, sendType, msgParam);
				}
			}
		} else {//发送给店主
			List<DredgeMsgEntity> dredgeMsgList = dredgeDao.getDredgeBillMsgContent(dredgeBill.getId());
			for (DredgeMsgEntity dredgeMsgEntity : dredgeMsgList) {
				String mobile = dredgeMsgEntity.getMerchantTel();
				int totalNum = 0;
				String listStr = "";
				if(mobile != null && !"".equals(mobile)) {
					for (DredgeBillHasEbuyProductShelfEnity dredgeBillHasEbuyProductShelfEnity : dredgeMsgEntity.getDredgeBillHasEbuyProductShelfList()) {
						totalNum = totalNum + dredgeBillHasEbuyProductShelfEnity.getQuantity();
						listStr += dredgeBillHasEbuyProductShelfEnity.getProductName() + " " + dredgeBillHasEbuyProductShelfEnity.getQuantity()+"件、";
					}
					listStr = listStr.substring(0, listStr.length()-1);//去除末尾的符号
					Object[] msgParam = {dredgeBill.getTel(),dredgeMsgEntity.getMerchantName(),totalNum,listStr};
//					String msg = SmsPropertyUtil.getProperty(sendType, msgParam);
//					commMobileService.sendMsg(mobile, msg);
					ShortMsgUtil.sendMessage(mobile, sendType, msgParam);
				}
			}
		}
	}

	/**
	 * 判断 维修单下是否存在耗材
	 * @param dredgeBillId
	 * @return
	 */
	public boolean isHasEbuyProductByDredgeBillId(BigInteger dredgeBillId) {
		int productCounts = dredgeDao.isHasEbuyProductByDredgeBillId(dredgeBillId);
		return productCounts > 0;
	}

	public DredgeBill getDredgeBillById(BigInteger dredgeBillId) {
		return dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBillId);
	}

	public void setCommMobileService(ICommMobileService commMobileService) {
		this.commMobileService = commMobileService;
	}

	public List<ApplyListForMaster> selectApplyList_byRecommendId(
			BigInteger userId, PageModel pageModel) {
		return dredgeDao.selectApplyList_byRecommendId(userId, pageModel);
	}

	public List<CommunitySupplyType> getCommunitySupplyTypesInDredgeType(List<BigInteger> addrCodeIdList) {
		return dredgeDao.getCommunitySupplyTypesInDredgeType(addrCodeIdList);
	}

	public List<DredgeBillForList> getDredgeBillList(BigInteger userId, int type,
													 BigInteger groupBuildingId, PageModel pageModel, boolean fromLA) {
		return dredgeDao.getDredgeBillList(userId, type, groupBuildingId, pageModel, fromLA);
	}

	public List<DredgeBillForList> getDredgeRepairList(BigInteger userId, int type, BigInteger groupBuildingId, PageModel pageModel) {
		return dredgeDao.getDredgeRepairList(userId, type, groupBuildingId, pageModel);
	}

	public List<CommentsPoints> getCommentsPointList() {
		CommentsPoints commentsPointsQry = new CommentsPoints();
		commentsPointsQry.setType(CommentsConstant.CommentsPoints.DredgeType);
		List<CommentsPoints> cpList = commentsPointsBaseService.getCommentsPointsByCondition(MapConverter.toMap(commentsPointsQry));
		Collections.reverse(cpList);
		return cpList;
	}

	/**
	 * 列出已选耗材
	 * @param paramMap
	 * @return
	 */
	public List<SelfBuyProduct> getSelfBuyProductList(Map<String, Object> paramMap) {
		return dredgeDao.qryProductListWithDredgeBillId(paramMap);
	}

	/**
	 * 列出可选耗材
	 * @param paramMap
	 * @return
	 */
	public List<SelfBuyProduct> qryProductList(Map<String, Object> paramMap) {
		return dredgeDao.qryProductList(paramMap);
	}

	/**
	 * 列出可选耗材总数
	 * @param paramMap
	 * @return
	 */
	public int qryProductListCount(Map<String, Object> paramMap) {
		return dredgeDao.qryProductListCount(paramMap);
	}

	public void saveSelfBuyProduct(BigInteger dredgeBillId, List<ProductIdQtyEntity> productIdQtyList, String operType) {
		if(productIdQtyList.size()>0){
			//1 先更新库里已选耗材的数量 
			DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelfQry = new DredgeBillHasEbuyProductShelf();
			dredgeBillHasEbuyProductShelfQry.settDredgeBillFId(dredgeBillId);
			List<DredgeBillHasEbuyProductShelf> existsList = dredgeBillHasEbuyProductShelfBaseDao.selectDredgeBillHasEbuyProductShelfByCondition(MapConverter.toMap(dredgeBillHasEbuyProductShelfQry), false);
			List<DredgeBillHasEbuyProductShelf> updDredgeBillHasEbuyProductShelfList = new ArrayList<DredgeBillHasEbuyProductShelf>();
			for (int i = existsList.size() - 1; i >= 0; i--) {
				for (int j = productIdQtyList.size() - 1; j >=0 ; j--) {
					if(productIdQtyList.get(j).getProductId().equals(existsList.get(i).gettEbuyProductShelfFId())){
						DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf = new DredgeBillHasEbuyProductShelf();
						dredgeBillHasEbuyProductShelf.setId(existsList.get(i).getId());
						if ("add".equals(operType)) {
							dredgeBillHasEbuyProductShelf.setQuantity(existsList.get(i).getQuantity() + productIdQtyList.get(j).getProductQty().intValue());
						} else {
							dredgeBillHasEbuyProductShelf.setQuantity(productIdQtyList.get(j).getProductQty().intValue());
						}
						updDredgeBillHasEbuyProductShelfList.add(dredgeBillHasEbuyProductShelf);
						productIdQtyList.remove(j);
					}
				}
			}
			if(updDredgeBillHasEbuyProductShelfList.size()>0){
				dredgeBillHasEbuyProductShelfBaseDao.updateDredgeBillHasEbuyProductShelfBatch(updDredgeBillHasEbuyProductShelfList);
			}

			//2 插入新选的耗材 
			List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_has_ebuy_product_shelf, productIdQtyList.size());
			List<DredgeBillHasEbuyProductShelf> addDredgeBillHasEbuyProductShelfList = new ArrayList<DredgeBillHasEbuyProductShelf>();
			for(int i = 0; i < productIdQtyList.size(); i++){
				ProductIdQtyEntity productIdQtyEntity = productIdQtyList.get(i);
				DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf = new DredgeBillHasEbuyProductShelf();
				dredgeBillHasEbuyProductShelf.setId(idList.get(i));
				dredgeBillHasEbuyProductShelf.settDredgeBillFId(dredgeBillId);
				dredgeBillHasEbuyProductShelf.settEbuyProductShelfFId(productIdQtyEntity.getProductId());
				dredgeBillHasEbuyProductShelf.setQuantity(productIdQtyEntity.getProductQty().intValue());
				addDredgeBillHasEbuyProductShelfList.add(dredgeBillHasEbuyProductShelf);
			}
			if(addDredgeBillHasEbuyProductShelfList.size()>0){
				dredgeBillHasEbuyProductShelfBaseDao.insertDredgeBillHasEbuyProductShelfBatch(addDredgeBillHasEbuyProductShelfList);
			}
		}
	}

	public int deleteSelfBuyProduct(BigInteger dredgeBillId, List<BigInteger> productShelfIds) {
		return dredgeDao.deleteSelfBuyProduct(dredgeBillId, productShelfIds);
	}

	public Integer getDredgeHasProductAmount(BigInteger dredgeBillId) {
		return dredgeDao.getDredgeHasProductAmount(dredgeBillId);
	}

    public List<DredgeProductSpecEntity> getDredgeProductListByDbId(BigInteger dredgeBillId) {
        return dredgeDao.getDredgeProductListByDbId(dredgeBillId);
    }

	/**
	 * 推送订单到十分到家 
	 * @param dredgeBillId
	 * @param workerId 师傅的userId
	 * @param price 订单价格
	 * @throws IOException 
	 * @throws HttpException 
	 */
	@Transactional
	public String pushOrderToSfdj(BigInteger dredgeBillId, BigInteger workerId, double laborFee, double otherFee) throws IOException {
		DredgeBill db = dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBillId);
		SfdjServiceOrderPusher sfdjServiceOrderPusher = new SfdjServiceOrderPusher();
		db.setPayAmount(NumberUtils.doubleM100ToLong(laborFee + otherFee));
		String pushReultInfo = sfdjServiceOrderPusher.pushNewOrder(db);
		// {"code":10000,"msg":"success","result":"SC147850323661800001"}
		
		JSONObject parseObject = JSON.parseObject(pushReultInfo);
		
		if(!"10000".equals(parseObject.getString("code")))//推送失败
			return pushReultInfo;

		// 派单给 十分到家
		// 要增加订单的关联信息
		DredgeBillOtherInfo dredgeBillOtherInfo = new DredgeBillOtherInfo();
		dredgeBillOtherInfo.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_other_info));
		dredgeBillOtherInfo.setServiceSplOrderNum(parseObject.getString("result"));
		dredgeBillOtherInfo.settDredgeBillFId(dredgeBillId);
		dredgeBillOtherInfo.settDredge3rdSplFId(DredgeConstant.DredgeServiceSupplier.SFDJ);
		DredgeBillOtherInfoBaseDao dredgeBillOtherInfoBaseDao = (DredgeBillOtherInfoBaseDao) CnfantasiaCommbusiUtil.getBeanManager("dredgeBillOtherInfoBaseDao");
		dredgeBillOtherInfoBaseDao.insertDredgeBillOtherInfo(dredgeBillOtherInfo);
				
		// assignBillToWorker(dredgeBillId, workerId, laborFee, otherFee);
		
		//标记为已经付款
		String payOrderResultString = sfdjServiceOrderPusher.payOrder(parseObject.getString("result"), laborFee+otherFee);
				
//		return "10000".equals(parseObject.getString("code"));
		
		return pushReultInfo;
	}
	
	
	/**
	 * 推送订单到 轻松到家 
	 * @param dredgeBillId
	 * @param workerId 师傅的userId
	 * @param price 订单价格
	 * @throws IOException 
	 * @throws HttpException 
	 */
	@Transactional
	public String pushOrderToQsdj(BigInteger dredgeBillId, BigInteger workerId, double laborFee, double otherFee) throws IOException {
		DredgeBill db = dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBillId);
		QsdjServiceOrderPusher sfdjServiceOrderPusher = new QsdjServiceOrderPusher();
		db.setPayAmount(NumberUtils.doubleM100ToLong(laborFee + otherFee));
		String pushReultInfo = sfdjServiceOrderPusher.pushNewOrder(db);
		// {"code":10000,"msg":"success","result":"SC147850323661800001"}
		
		JSONObject parseObject = JSON.parseObject(pushReultInfo);
		
		if(!"0".equals(parseObject.getString("code")))//推送失败
			return pushReultInfo;

		// 派单给 十分到家
		// 要增加订单的关联信息
		DredgeBillOtherInfo dredgeBillOtherInfo = new DredgeBillOtherInfo();
		dredgeBillOtherInfo.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_other_info));
		dredgeBillOtherInfo.setServiceSplOrderNum(parseObject.getJSONObject("result").getString("orderId"));
		dredgeBillOtherInfo.settDredgeBillFId(dredgeBillId);
		dredgeBillOtherInfo.settDredge3rdSplFId(DredgeConstant.DredgeServiceSupplier.QSDJ);
		DredgeBillOtherInfoBaseDao dredgeBillOtherInfoBaseDao = (DredgeBillOtherInfoBaseDao) CnfantasiaCommbusiUtil.getBeanManager("dredgeBillOtherInfoBaseDao");
		dredgeBillOtherInfoBaseDao.insertDredgeBillOtherInfo(dredgeBillOtherInfo);
				
//		return "10000".equals(parseObject.getString("code"));
		
		return pushReultInfo;
	}

	/**
	 * 派单给师傅
	 * @param dredgeBillId 
	 * @param price 价格
	 * @param serviceSupplier
	 */
	@Transactional
	public void assignBillToWorker(BigInteger dredgeBillId,BigInteger workerId, double laborFee, double otherFee, String expectWorkTime) {
		DredgeBill nowBill = dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBillId);
		if (nowBill.getBillType() == DredgeConstant.DredgeBillType.Dredge_Pay_First) {
			//billType = 5, 前付款单不许设置金额，用户付款都完成了
			laborFee = 0;
			otherFee = 0;
		}
		DredgeBill dbUpd = new DredgeBill();
		dbUpd.setId(dredgeBillId);
		dbUpd.setAcceptTime(DateUtil.formatSecond.get().format(new Date()));
		dbUpd.setExpectWorkTime(expectWorkTime);
		if (laborFee + otherFee > 0) {
			dbUpd.setPayAmount(NumberUtils.doubleM100ToLong(laborFee + otherFee));
			dbUpd.setStatus(DredgeConstant.DredgeBill.Master_Finish);
		} else {
			dbUpd.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Accepted);
		}
		dbUpd.settWorkerFId(workerId);
		dredgeBillBaseDao.updateDredgeBill(dbUpd);

		if (laborFee > 0) {
			DredgeBillAmountDetail dredgeBillAmountDetail = new DredgeBillAmountDetail();
			dredgeBillAmountDetail.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_amount_detail));
			dredgeBillAmountDetail.setFeeName("人工费");
			dredgeBillAmountDetail.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Labor_fee);
			dredgeBillAmountDetail.setIsIncludePlatformFee(1);
			dredgeBillAmountDetail.setPayAmount(NumberUtils.doubleM100ToLong(laborFee));
			dredgeBillAmountDetail.settDredgeBillFId(dredgeBillId);
			dredgeBillAmountDetailBaseDao.insertDredgeBillAmountDetail(dredgeBillAmountDetail);
		}

		if (otherFee > 0) {
			DredgeBillAmountDetail dredgeBillAmountDetail = new DredgeBillAmountDetail();
			dredgeBillAmountDetail.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_amount_detail));
			dredgeBillAmountDetail.setFeeName("其它费");
			dredgeBillAmountDetail.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Material_fee);
			dredgeBillAmountDetail.setIsIncludePlatformFee(0);
			dredgeBillAmountDetail.setPayAmount(NumberUtils.doubleM100ToLong(otherFee));
			dredgeBillAmountDetail.settDredgeBillFId(dredgeBillId);
			dredgeBillAmountDetailBaseDao.insertDredgeBillAmountDetail(dredgeBillAmountDetail);
		}
	}

	public String querySfdjOrderStatus(BigInteger dredgeBillId) throws IOException {
		DredgeBillOtherInfo dredgeBillOtherInfoQry = new DredgeBillOtherInfo();
		dredgeBillOtherInfoQry.settDredgeBillFId(dredgeBillId);
		DredgeBillOtherInfoBaseDao dredgeBillOtherInfoBaseDao = (DredgeBillOtherInfoBaseDao) CnfantasiaCommbusiUtil.getBeanManager("dredgeBillOtherInfoBaseDao");
		String serviceSplOrderNumber = dredgeBillOtherInfoBaseDao.selectDredgeBillOtherInfoByCondition(MapConverter.toMap(dredgeBillOtherInfoQry), false).get(0).getServiceSplOrderNum();

		SfdjServiceOrderPusher sfdjServiceOrderPusher = new SfdjServiceOrderPusher();
		return sfdjServiceOrderPusher.queryOrderStatus(serviceSplOrderNumber);
	}

	public List<DredgeBillFollowRecord> qryOrderFollowRecord(String dredgeBillId) {
		return dredgeDao.qryOrderFollowRecord(dredgeBillId);
	}
	
	/**
	 * 转换流程记录，供app端使用
	 * @param prList
	 * @return
	 */
	public List<ProcessRecord> convertProcessRecordMap(List<DredgeBillHasProcessRecording> prList){
		List<ProcessRecord> prMapList = new ArrayList<ProcessRecord>();
		for(int i = 0; i < prList.size(); i++){
			ProcessRecord pr = new ProcessRecord();
			DredgeBillHasProcessRecording dredgeBillHasProcessRecording = prList.get(i);
			pr.setId(dredgeBillHasProcessRecording.getId());
			pr.setPrDesc(dredgeBillHasProcessRecording.getProcessDesc());
			pr.setPrAddTimeStr(dredgeBillHasProcessRecording.getRecordingTime());
			pr.setPrAddTime(DateUtils.convertStrToDate(dredgeBillHasProcessRecording.getRecordingTime(), "yyyy-MM-dd HH:mm:ss").getTime());
			pr.setPicList(getPicInfoList(false, dredgeBillHasProcessRecording.getProcessPics(), dredgeBillHasProcessRecording.getRecordingTime()));
			prMapList.add(pr);
		}
		return prMapList;
	}

	/**
	 * 添加流程记录
	 * @param billId 上门服务单id或物业公共维修单id
	 * @param processDesc 流程记录描述
	 * @param billType 1上门服务单，3物业公共维修单
	 * @param picList
	 */
	public void addProcessRecording(BigInteger billId, String processDesc, int billType, List<RequestFileEntity> picList) {
		//保存图片信息
		logger.info("update pic start...");
		long startTime = System.currentTimeMillis();
		String picUrl = uploadDredgePic(UserContext.getOperIdMustExistBigInteger(), picList);
		logger.info("update pic end... use time: " + (System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();
		
		BigInteger addNewId = uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_has_process_recording);
		DredgeBillHasProcessRecording dredgeBillHasProcessRecording = new DredgeBillHasProcessRecording();
		dredgeBillHasProcessRecording.setId(addNewId);
		dredgeBillHasProcessRecording.setBillType(billType == DredgeConstant.DredgeBillType.Dredge_Bill_Common ? 1:3);
		dredgeBillHasProcessRecording.settDredgeBillFId(billType == DredgeConstant.DredgeBillType.Dredge_Bill_Common ? billId : null);
		dredgeBillHasProcessRecording.settPropertyRepairFId(billType == DredgeConstant.DredgeBillType.Property_Repair ? billId : null);
		dredgeBillHasProcessRecording.setProcessDesc(processDesc);
		dredgeBillHasProcessRecording.setProcessPics(picUrl);
		dredgeBillHasProcessRecording.setRecordingTime(DateUtils.getCurrentDate());
		dredgeBillHasProcessRecordingBaseDao.insertDredgeBillHasProcessRecording(dredgeBillHasProcessRecording);
	}

	public List<BigInteger> qryRepairDredgeType(BigInteger gbId) {
		return dredgeDao.qryRepairDredgeType(gbId);
	}

	private class PushRepairThread extends Thread {
		private DredgeBill dredgeBill;
		private List<RequestFileEntity> picList;
		PushRepairThread(DredgeBill dredgeBill,
						 List<RequestFileEntity> picList) {
			this.dredgeBill = dredgeBill;
			this.picList = picList;
		}

		@Override
		public void run() {
			BigInteger roomId = userBaseDao.selectUserBySeqId(dredgeBill.gettUserFId()).getDefaultRoomId();
			BigInteger realRoomId = roomBaseService.getRoomBySeqId(roomId).gettRealRoomFId();
			RealRoomSoftwareInfo realRoomSoftwareInfo = propertySoftwareDockDao.getRealRoomSoftwareInfo(realRoomId);
			if (realRoomSoftwareInfo != null && realRoomSoftwareInfo.getSoftwareHouseId() != null
					&& realRoomSoftwareInfo.getDefaultRepairerId() != null) {
				RepairPushEntity entity = new RepairPushEntity();
				entity.setFailCount(0);
				entity.setDredgeBill(dredgeBill);
				entity.setSoftwareInfo(realRoomSoftwareInfo);
				entity.setPicList(picList);
				BigInteger repairTypeId = dredgeBill.gettDredgeTypeFId();
				if (repairTypeId != null) {
					entity.setRepairTypeName(dredgeTypeBaseDao.selectDredgeTypeBySeqId(repairTypeId).getName());
				}
				try {
					pushRepairToSoftware(entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void pushRepairToSoftware(RepairPushEntity entity) {
		BigInteger repairId = entity.getDredgeBill().getId();
		String key = RedisCachePrefix.property_repair_push_software + "dredge_" + repairId;

		IPropertySoftwareDockService dockService = (IPropertySoftwareDockService) CnfantasiaCommbusiUtil
				.getBeanManager(entity.getSoftwareInfo().getServiceClassName());
		String billNo = null;
		String exception = "";
		try {
			billNo = dockService.pushPropertyRepair(entity);
		} catch (Exception e) {
			e.printStackTrace();
			exception = ExceptionParseUtil.parseExceptionMessage(e);
		} finally {
			if (billNo != null) {
				//保存billNo，并设置为师傅已接单
				DredgeBill dredgeBill = entity.getDredgeBill();
				dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Accepted);
				dredgeBill.settWorkerFId(entity.getSoftwareInfo().getDefaultRepairerId());
				dredgeBill.setSoftwareBillId(billNo);
				dredgeBillBaseDao.updateDredgeBill(dredgeBill);

				//清除这个redis内容
				RedisCacheHandler.del(key);

				//将该单移至获取详情redis中
				entity.setFailCount(0);
				entity.setPicList(null);
				entity.setDredgeBill(dredgeBill);
				String getDetailKey = RedisCachePrefix.property_repair_need_software_detail + "dredge_" + repairId;
				RedisCacheHandler.set(getDetailKey, JSON.toJSONString(entity), getRepairDetailExpireSecond(entity));

			} else {
				int failCount = entity.getFailCount() + 1;
				entity.setFailCount(failCount);
				if (failCount < 3) {//添加失败计数
					RedisCacheHandler.set(key, JSON.toJSONString(entity), getRepairDetailExpireSecond(entity));
				} else {
					RedisCacheHandler.del(key);
					String notifyMail = sysParamManager.getSysParaValue(SysParamKey.PropertySoftwareNotifyFailMail);
					if (!DataUtil.isEmpty(notifyMail)) {
						String content = "推送物业管理软件维修单失败：<br>" +
								"pushEntity : " + JSON.toJSONString(entity) + "<br>" +
								"exception : " + exception + "<br>";
						new com.propertySoftwareDock.base.entity.MailSendThread("推送物业管理软件维修单失败",content, notifyMail).start();
					}
				}
			}
		}
	}

	/**
	 * redis过期时间，设置为预约时间后三天
	 * @param entity
	 * @return
	 */
	public int getRepairDetailExpireSecond(RepairPushEntity entity) {
		if (entity == null || entity.getDredgeBill() == null || entity.getDredgeBill().getExpectdate() == null) {
			return 1;
		}
		String expectTime = entity.getDredgeBill().getExpectdate();
		expectTime = expectTime.replaceAll("-", "/");
		Date now = new Date();
		Date expect = new Date(expectTime);
		Long second = (expect.getTime() - now.getTime()) / 1000;
		return second.intValue() + 60 * 60 * 24 * 3;
	}

	@Transactional
	@Deprecated//507开始不用这个了
	public void turnBillType(DredgeBill dredgeBill, List<ProductIdQtyEntity> productIdQtyList) {
		DredgeBill nowBill = dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBill.getId());
		dredgeDao.turnDredgeBillById(dredgeBill);

		//记录数据变化
		DataChangeRecord record = new DataChangeRecord();
		record.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_data_change_record));
		record.setTableName(SEQConstants.t_dredge_bill);
		record.setRowId(dredgeBill.getId());
		record.setDataFrom("dredgeType:" + nowBill.gettDredgeTypeFId() + ", dredgeType2nd:" + nowBill.gettDredgeType2ndFId());
		record.setDataTo("dredgeType:" + dredgeBill.gettDredgeTypeFId() + ", dredgeType2nd:" + dredgeBill.gettDredgeType2ndFId());
		record.setOperationDesc("新报修单内转外");
		dataChangeRecordBaseService.insertDataChangeRecord(record);

		dredgeBill = dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBill.getId());
		//发邮件
		sendEmailAfterAddDredgeBill(dredgeBill, "未知", "未知");
		try {//添加推送消息
			dredgeBill = dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBill.getId());
			dredgeBill.setPushLevel(DredgeConstant.DredgeWorkerLevel.High_Level);
			new Thread(new PushMessgeThread(dredgeBill, "又有新的订单啦", MsgpushDict.PushId.DredgeBill_AddNew)).start();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}

		//删除之前选的耗材
		DredgeBillHasEbuyProductShelf hasEbuyProductShelf = new DredgeBillHasEbuyProductShelf();
		hasEbuyProductShelf.settDredgeBillFId(dredgeBill.getId());
		List<DredgeBillHasEbuyProductShelf> productShelfList = dredgeBillHasEbuyProductShelfBaseDao
				.selectDredgeBillHasEbuyProductShelfByCondition(MapConverter.toMap(hasEbuyProductShelf), false);
		if (!DataUtil.isEmpty(productShelfList)) {
			List<BigInteger> delIds = new ArrayList<>(productShelfList.size());
			for (DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf : productShelfList) {
				delIds.add(dredgeBillHasEbuyProductShelf.getId());
			}
			dredgeBillHasEbuyProductShelfBaseDao.deleteDredgeBillHasEbuyProductShelfLogicBatch(delIds);
		}
		//保存自选耗材
		if(!DataUtil.isEmpty(productIdQtyList)){
			List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_bill_has_ebuy_product_shelf, productIdQtyList.size());
			List<DredgeBillHasEbuyProductShelf> addDredgeBillHasEbuyProductShelfList = new ArrayList<DredgeBillHasEbuyProductShelf>();
			for(int i = 0; i < productIdQtyList.size(); i++){
				ProductIdQtyEntity productIdQtyEntity = productIdQtyList.get(i);
				DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf = new DredgeBillHasEbuyProductShelf();
				dredgeBillHasEbuyProductShelf.setId(idList.get(i));
				dredgeBillHasEbuyProductShelf.settDredgeBillFId(dredgeBill.getId());
				dredgeBillHasEbuyProductShelf.settEbuyProductShelfFId(productIdQtyEntity.getProductId());
				dredgeBillHasEbuyProductShelf.setQuantity(productIdQtyEntity.getProductQty().intValue());
				addDredgeBillHasEbuyProductShelfList.add(dredgeBillHasEbuyProductShelf);
			}
			dredgeBillHasEbuyProductShelfBaseDao.insertDredgeBillHasEbuyProductShelfBatch(addDredgeBillHasEbuyProductShelfList);

			try {//发送耗材信息给店家
				sendMsgThread(dredgeBill, "msg.DREDGE_SHOP");
			} catch (Exception e) {
				logger.info("发送耗材信息给店家，调用短息sendMsgThread方法异常:" + e.getMessage());
			}
		}
	}

	public void assignMaster(DredgeBill dredgeBill) {
		dredgeBillBaseDao.updateDredgeBill(dredgeBill);
		dredgeBill = dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBill.getId());
		//管理处已为您安排师傅进行维修咯
		ShortMsgUtil.sendMessage(dredgeBill.getTel(), "dredge_on_way", "");

		List<Object> args = new ArrayList<Object>();
		args.add(dredgeBill.getAddress());
		args.add(dredgeTypeBaseDao.selectDredgeTypeBySeqId(dredgeBill.gettDredgeTypeFId()).getName());
		args.add(dredgeBill.getTel());
		//{1}提交报修单，报修类型：{2}，业主联系电话：{3}
		ShortMsgUtil.sendMessage(dredgeBill.getTel(), "order_dredge1", args);
	}

	/**
	 * 根据门牌获取各个上级的ID gbId, blockId, cityId, provinceId
	 * @param roomId
	 * @return
	 */
	public Map<String, Object> getRoomAddressIdByRoom(BigInteger roomId) {
		return dredgeDao.getRoomAddressIdByRoom(roomId);
	}

	/**
	 * 查询所有服务商品总数
	 * @param paramMap
	 * @return
	 */
	public int qryDredgeProductCount(Map<String, Object> paramMap) {
		paramMap.remove("_begin");
		paramMap.remove("_length");
		
		return dredgeDao.qryDredgeProductCount(paramMap);
	}
	
	/**
	 * 查询所有服务商品
	 * @param paramMap
	 * @return
	 */
	public List<DredgeProduct4Admin> qryDredgeProductList(Map<String, Object> paramMap) {
		return dredgeDao.qryDredgeProductList(paramMap);
	}

	/**
	 * 保存服务商品
	 * @param p
	 * @param sharePushPicList 
	 * @param sharePicList 
	 * @param introducePicList 
	 * @param prdtPicList 
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@Transactional
	public BigInteger saveDredgeProduct(ProductAddNewParamter p, List<MultipartFile> prdtPicList, List<MultipartFile> introducePicList, List<MultipartFile> sharePicList, List<MultipartFile> sharePushPicList) throws IllegalStateException, IOException {
		DredgeProduct dp = new DredgeProduct();
		dp.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_product));
		dp.setName(p.getPrdtName());
		dp.setDesc(p.getDesc());
		dp.setProductPic(uploadDredgePic(prdtPicList));
		dp.setIntroducePic(uploadDredgePic(introducePicList));
		dp.setSharePic(uploadDredgePic(sharePicList).replace(";", ""));
		dp.setSharePushPic(uploadDredgePic(sharePushPicList).replace(";", ""));
		dp.setPayType(p.getPayType());
		dp.setServiceSupplierId(p.getSsId());
		dp.setDredgeType2ndId(p.getDt2Id());
		dp.setShareFriendTitle(p.getShareFridendTitle());
		dp.setShareCycleTitle(p.getShareTimelineTitle());
		dp.setShareContent(p.getShareContent());
		dp.setStatus(1);//默认上架
		dp.setSys0UpdTime(DateUtils.getCurrentDate());
		
		int updCount = 0;
		updCount += dredgeProductBaseDao.insertDredgeProduct(dp);
		
		CnfantasiaCommbusiUtil.newStandardListForId(p.getPrdtSpecList(), SEQConstants.t_dredge_product_specification);
		for(int i = 0; i < p.getPrdtSpecList().size(); i++){
			DredgeProductSpecification dredgeProductSpecification = p.getPrdtSpecList().get(i);
			dredgeProductSpecification.setDredgeProductId(dp.getId());
			dredgeProductSpecification.setIsVisible(1);
		}
		updCount += dredgeProductSpecificationBaseDao.insertDredgeProductSpecificationBatch(p.getPrdtSpecList());
		
		return dp.getId();
	}

	public DredgeProductView qryDredgeProductDetail(BigInteger id) {
		return dredgeDao.qryDredgeProductDetail(id);
	}

	/**
	 * 更新服务商品
	 * @param p
	 * @param prdtPicList
	 * @param introducePicList
	 * @param sharePicList
	 * @param sharePushPicList
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public int updateDredgeProduct(ProductAddNewParamter p, List<MultipartFile> prdtPicList,
			List<MultipartFile> introducePicList, List<MultipartFile> sharePicList,
			List<MultipartFile> sharePushPicList) throws IllegalStateException, IOException {
		DredgeProduct dp = new DredgeProduct();
		dp.setId(p.getId());
		dp.setName(p.getPrdtName());
		dp.setDesc(StringUtils.isEmpty(p.getDesc()) ? " " : p.getDesc());
		dp.setProductPic(p.getPrdtPics() + uploadDredgePic(prdtPicList));
		dp.setIntroducePic(p.getIntroducePics() + uploadDredgePic(introducePicList));
		dp.setSharePic(uploadDredgePic(sharePicList).replace(";", ""));
		dp.setSharePushPic(uploadDredgePic(sharePushPicList).replace(";", ""));
		dp.setServiceSupplierId(p.getSsId());
		dp.setDredgeType2ndId(p.getDt2Id());
		dp.setShareFriendTitle(p.getShareFridendTitle());
		dp.setShareCycleTitle(p.getShareTimelineTitle());
		dp.setShareContent(p.getShareContent());
		dp.setSys0UpdTime(DateUtils.getCurrentDate());
		
		int updCount = 0;
		updCount += dredgeProductBaseDao.updateDredgeProduct(dp);
		
		List<DredgeProductSpecification> dpsAddNewList = new ArrayList<DredgeProductSpecification>();
		List<DredgeProductSpecification> dpsUpdList = new ArrayList<DredgeProductSpecification>();
		for(DredgeProductSpecification dps: p.getPrdtSpecList()){
			if(dps.getId()!=null)
				dpsUpdList.add(dps);
			else{
				dps.setDredgeProductId(dp.getId());
				dpsAddNewList.add(dps);
			}
		}
		CnfantasiaCommbusiUtil.newStandardListForId(dpsAddNewList, SEQConstants.t_dredge_product_specification);
		DredgeProductSpecification dpQry = new DredgeProductSpecification();
		dpQry.setDredgeProductId(dp.getId());
		List<DredgeProductSpecification> dpsExistList = dredgeProductSpecificationBaseDao.selectDredgeProductSpecificationByCondition(MapConverter.convertBean(dpQry), false);
		List<BigInteger> idList = new ArrayList<BigInteger>();
		for(DredgeProductSpecification dpsExist: dpsExistList){
			for(int i = 0; i < dpsUpdList.size(); i++){
				DredgeProductSpecification dpsUpd = dpsUpdList.get(i); 
				if(dpsExist.getId().equals(dpsUpd.getId()))
					break;
				if(i == dpsUpdList.size() -1)//不是更新的，那就是要删除的
					idList.add(dpsExist.getId());
			}
		}
		updCount += dredgeProductSpecificationBaseDao.insertDredgeProductSpecificationBatch(dpsAddNewList);
		updCount += dredgeProductSpecificationBaseDao.updateDredgeProductSpecificationBatch(dpsUpdList);
		updCount += dredgeProductSpecificationBaseDao.deleteDredgeProductSpecificationLogicBatch(idList);

		return updCount;
	}

	public List<DredgeProduct4Turn> qryDredgeProductList4Trun(BigInteger dt2Id) {
		return dredgeDao.qryDredgeProductList4Trun(dt2Id);
	}

	public List<OperateConfigRange> qryDredgeProductSellRangeByPrdtId(BigInteger id) {
		return dredgeDao.qryDredgeProductSellRangeByPrdtId(id);
	}

	public boolean isInDredgeProductArea(List<BigInteger> addrCodeIdList, BigInteger dredgeProductId) {
		return dredgeDao.isInDredgeProductArea(addrCodeIdList, dredgeProductId) > 0;
	}

	public Map<String, Object> getApplyRefundInfo(BigInteger dredgeBillId) {
		return dredgeDao.getApplyRefundInfo(dredgeBillId);
	}

	public void oosConfirmFinish(BigInteger dredgeBillId, BigDecimal laborAmount, BigDecimal materialAmount) {
		DredgeBill nowBill = dredgeBillBaseDao.selectDredgeBillBySeqId(dredgeBillId);
		DredgeBill dredgeBill = new DredgeBill();
		dredgeBill.setId(dredgeBillId);
		laborAmount = laborAmount == null ? BigDecimal.ZERO : laborAmount;
		materialAmount = materialAmount == null ? BigDecimal.ZERO : materialAmount;
		long laborAmountLong = laborAmount.multiply(new BigDecimal("100")).longValue();
		long materialAmountLong = materialAmount.multiply(new BigDecimal("100")).longValue();
		if (nowBill.getBillType() != DredgeConstant.DredgeBillType.Dredge_Pay_First) {
			dredgeBill.setPayAmount(laborAmountLong + materialAmountLong);
		}
		dredgeBill.setStatus(DredgeConstant.DredgeBill.Master_Finish);
		dredgeBillBaseDao.updateDredgeBill(dredgeBill);
		if (nowBill.getPayAmount() == null) {//首次设置
			insertAmountDetail(dredgeBillId, laborAmountLong, materialAmountLong);
		} else {//非首次
			updateAmountDetail(dredgeBillId, laborAmountLong, materialAmountLong);
		}
		//发送消息给用户
		dredgePushService.oosConfirmFinishMsg(dredgeBillId);
	}

	public String getOpenId(BigInteger userId) {
		return dredgeDao.getOpenId(userId);
	}
}

class DredgeSMSPusher implements Runnable {
	private Log logger = LogFactory.getLog(getClass());

	ICommMobileService commMobileService;
	DredgeDao dredgeDao;
	BigInteger dredgeBillId;

	public DredgeSMSPusher(ICommMobileService commMobileService, DredgeDao dredgeDao, BigInteger dredgeBillId) {
		super();
		this.commMobileService = commMobileService;
		this.dredgeDao = dredgeDao;
		this.dredgeBillId = dredgeBillId;
	}

	@Override
	public void run() {
		sendSMSAfterConfirmReceiving();
	}

	/**
	 * 接单成功后发送短信通知业主
	 */
	private void sendSMSAfterConfirmReceiving() {
		//String msg = "尊敬的用户，您的预约单（服务类型），师傅（师傅姓名）已接单，联系电话：电话，请保持电话畅通。";

//		String msg = "尊敬的用户，您的预约单（{0}），师傅（{1}）已接单，联系电话：{2}，请保持电话畅通。【解放区】";
		long time1 = System.currentTimeMillis();
		SMSInfo info = dredgeDao.selectSMSInfoByDgId(dredgeBillId);
		long time2 = System.currentTimeMillis();

//		String msgContent = MessageFormat.format(msg, info.getDredgeTypeName(), info.getMasterName(), info.getMasterTel());
//		String msgContent = SmsPropertyUtil.getProperty("send_to_user_after_dredge_received",
//				info.getDredgeTypeName(), info.getMasterName(), info.getMasterTel());
//		commMobileService.sendMsg(info.getDredgeTel(), msgContent);
		ShortMsgUtil.sendMessage(info.getDredgeTel(), "dredge_received", info.getDredgeTypeName(), info.getMasterName(), info.getMasterTel());
		long time3 = System.currentTimeMillis();

		logger.info("DredgeController sendSMSAfterConfirmReceiving1:" + (time2 - time1));
		logger.info("DredgeController sendSMSAfterConfirmReceiving1:" + (time3 - time2));
	}
}

class PicGenerateMiniImageThread extends Thread {
	File bigImageFile ;

	public PicGenerateMiniImageThread(File bigImageFile) {
		super();
		this.bigImageFile = bigImageFile;
	}

	/**
	 * 生成小图
	 *
	 * @param bigImageFile
	 */
	private void generateMiniImage(File bigImageFile) {
		Map<String, WidthHeight> guigeList = BusinessModelType.Dredge.getGuigeList();

		String fileName = bigImageFile.getName();
		int lastPointIndex = fileName.lastIndexOf(".");
		String fileType = fileName.substring(lastPointIndex); //文件后缀名，带.号

		//String goalDirectoryFilePath = descDirecBasePath + fileName.substring(0, lastPointIndex);
		//   ../dredgePic/5038472015-08-12_10_00_16_1_755.jpg 
		String goalDirectoryFilePath = bigImageFile.getAbsolutePath().substring(0, bigImageFile.getAbsolutePath().lastIndexOf("."));

		// 创建小图的目标目录
		File goalFileDir = new File(goalDirectoryFilePath);
		if (!goalFileDir.exists()) {
			goalFileDir.mkdirs();
		}

		for (String goalFileName : guigeList.keySet()) {
			String smallIcon = goalDirectoryFilePath + "/" + goalFileName + fileType;
			try {
				WidthHeight tmpWidthHeight = guigeList.get(goalFileName);
				ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), tmpWidthHeight.getWidth(), tmpWidthHeight.getHeight(), 1f, smallIcon);
				//	String resSmallIcon = ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(),tmpWidth.getWidth(),null, quality, smallIcon);
				//	String resSmallIcon = ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), tmpWidth.getWidth(),tmpWidth.getHeight(), quality, smallIcon);
			} catch (Exception e) {
				System.err.println(smallIcon + "create small image failure. ");
			}
		}
	}


	public void run() {
		this.generateMiniImage(bigImageFile);
	}
}

class MailSendThread extends Thread {

	private String mailContent;

	private String nofityEmails;

	public MailSendThread(String mailContent, String nofityEmails) {
		this.mailContent = mailContent;
		this.nofityEmails = nofityEmails;
	}

	public void run() {
		MailUtils.sendMail("又来新的维修订单啦", mailContent, nofityEmails);
	}
}