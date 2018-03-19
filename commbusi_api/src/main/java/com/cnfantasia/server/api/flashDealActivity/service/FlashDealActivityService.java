package com.cnfantasia.server.api.flashDealActivity.service;

import com.cnfantasia.server.api.commonBusiness.service.ICommonRedenvelopeService;
import com.cnfantasia.server.api.ebuy.util.OrderNoGenerator;
import com.cnfantasia.server.api.flashDealActivity.constant.FlashDealActivityDict;
import com.cnfantasia.server.api.flashDealActivity.dao.IFlashDealActivityDao;
import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealActivityDetailEntity;
import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealActivityDetailNewEntity;
import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealRemindEntity;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.serviceUntran.ICommPayService;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.dao.IEbuyOrderHasTEbuyProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity.EbuyOrderHasTEbuyProduct;
import com.cnfantasia.server.domainbase.flashDealActivity.entity.FlashDealActivity;
import com.cnfantasia.server.domainbase.flashDealActivity.service.IFlashDealActivityBaseService;
import com.cnfantasia.server.domainbase.flashDealBuyRecord.entity.FlashDealBuyRecord;
import com.cnfantasia.server.domainbase.flashDealBuyRecord.service.IFlashDealBuyRecordBaseService;
import com.cnfantasia.server.domainbase.flashDealRemind.entity.FlashDealRemind;
import com.cnfantasia.server.domainbase.flashDealRemind.service.IFlashDealRemindBaseService;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: FlashDealActivityService
 * @Date: 2016-10-29 17:46
 * @Auther: kangduo
 * @Description:(秒杀活动Service层)
 */
public class FlashDealActivityService implements IFlashDealActivityService {

    private IFlashDealActivityDao flashDealActivityDao;

    public void setFlashDealActivityDao(IFlashDealActivityDao flashDealActivityDao) {
        this.flashDealActivityDao = flashDealActivityDao;
    }

    public void setAddressOperationService(IAddressOperationService addressOperationService) {
    }

    private IFlashDealBuyRecordBaseService flashDealBuyRecordBaseService;
    public void setFlashDealBuyRecordBaseService(IFlashDealBuyRecordBaseService flashDealBuyRecordBaseService) {
        this.flashDealBuyRecordBaseService = flashDealBuyRecordBaseService;
    }

    private IUuidManager uuidManager;
    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    private IEbuyOrderBaseService ebuyOrderBaseService;
    public void setEbuyOrderBaseService(IEbuyOrderBaseService ebuyOrderBaseService) {
        this.ebuyOrderBaseService = ebuyOrderBaseService;
    }

    private IUserBaseService userBaseService;
    public void setUserBaseService(IUserBaseService userBaseService) {
        this.userBaseService = userBaseService;
    }

    @Resource
    private ICommonRedenvelopeService commonRedenvelopeService;
    @Resource
    private ICommPayService commPayService;
    @Resource
    private IFlashDealRemindBaseService flashDealRemindBaseService;
    @Resource
    private  IFlashDealActivityBaseService flashDealActivityBaseService;

    @Resource
    private IEbuyOrderHasTEbuyProductBaseDao ebuyOrderHasTEbuyProductBaseDao;

    @Override
    public List<FlashDealActivityDetailNewEntity> getActivityList(BigInteger userId, BigInteger supplyMerchantId, BigInteger gbId, PageModel pageModel) {
        if(gbId == null) {
            gbId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("gbId", gbId);
        param.put("supplyMerchantId", supplyMerchantId);
        if (pageModel != null) {
            param.put("_begin", pageModel.getBegin());
            param.put("_length", pageModel.getLength());
        }
        return flashDealActivityDao.getActivityList(param);
    }
    
    /**
     * 我的中奖记录
     */
    @Override
    public List<FlashDealActivityDetailEntity> getMyRecords(Map<String, Object> param){
    	return flashDealActivityDao.getMyRecords(param);
    }

    @Override
    public FlashDealActivityDetailEntity getActivityEntityDetail(BigInteger activityId, BigInteger userId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("activityId", activityId);
        param.put("userId", userId);
        FlashDealActivityDetailEntity activityEntityDetail = flashDealActivityDao.getActivityEntityDetail(param);
        //产品图片列表
        List<String>  ebuyProductPicList = flashDealActivityDao.getEbuyProductPicList(activityId);
        if(ebuyProductPicList!=null && ebuyProductPicList.size()>0 && activityEntityDetail != null){
            String productPic = "";
            for (int i = ebuyProductPicList.size() - 1; i >= 0; i--) {
                productPic += ebuyProductPicList.get(i) + ";";
            }
            activityEntityDetail.setProductPic(productPic);
        }

        return activityEntityDetail;
    }

    @Override
    @Transactional
    public Map<String, Object> confirmPay(BigInteger activityId, BigInteger userId, String userMobile, String userName, String subChannelId, Long JFBAmount) {
        FlashDealActivityDetailEntity activityEntityDetail = this.getActivityEntityDetail(activityId, userId);
        //校验活动，校验不可多次参与
        if (activityEntityDetail == null) {
            throw new ValidateRuntimeException("flashDealActivityController.activityId.isnull");
        }
        if (activityEntityDetail.getBuyRecord() != null) {
            throw new ValidateRuntimeException("flashDealActivityController.buyRecord.isNotNull");
        }
        if (activityEntityDetail.getActivityStatus() != null
                && activityEntityDetail.getActivityStatus().compareTo(FlashDealActivityDict.activityStatus.Already_Begin) != 0){
            throw new ValidateRuntimeException("flashDealActivityController.activity.isOverOrNotBegin");
        }

        BigInteger currRoomId = userBaseService.getUserBySeqId(userId).getDefaultRoomId();

        //生成ebuyOrder
        BigInteger orderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
        EbuyOrder ebuyOrder = new EbuyOrder();
        ebuyOrder.setId(orderId);
        ebuyOrder.setOrderNo(OrderNoGenerator.getOrderNo(orderId));
        ebuyOrder.setBuyerId(userId);
        ebuyOrder.setCurrRoomId(currRoomId);
        ebuyOrder.setBuyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        ebuyOrder.setAmount(activityEntityDetail.getActivityPrice().longValue());
        ebuyOrder.setTotalCouponAmount(0L);
        ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
        ebuyOrder.setType(EbuyDict.EbuyOrder_Type.Flash_Deal_Activity);
        ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Not_Pay);//支付状态为待支付
        ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Not_Deliv);//发货状态为未发货
        ebuyOrder.setTotalDeliveryFee(0L);//总配送费
        ebuyOrder.setDelivPeopleName(userName);
        ebuyOrder.setDelivPhone(userMobile);
        ebuyOrder.setCreater(userId);
        ebuyOrder.setSubChannel(subChannelId);
        ebuyOrderBaseService.insertEbuyOrder(ebuyOrder);

        EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct = new EbuyOrderHasTEbuyProduct();
        ebuyOrderHasTEbuyProduct.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_t_ebuy_product));
        ebuyOrderHasTEbuyProduct.settEbuyOrderFId(orderId);
        ebuyOrderHasTEbuyProduct.settEbuyProductFId(activityEntityDetail.getProductShelfId());
        ebuyOrderHasTEbuyProduct.setProductPrice(Long.valueOf(activityEntityDetail.getActivityPrice()));
        ebuyOrderHasTEbuyProduct.setProductQty(1L);
        ebuyOrderHasTEbuyProduct.setSupplyMerchantId(activityEntityDetail.getMerchantId());
        ebuyOrderHasTEbuyProductBaseDao.insertEbuyOrderHasTEbuyProduct(ebuyOrderHasTEbuyProduct);

        //更新订单的粮票支付信息
        if(JFBAmount > 0) {
            commonRedenvelopeService.updateOrderEntityByJFB(ebuyOrder, JFBAmount, 0L);
        }

        ebuyOrderBaseService.updateEbuyOrder(ebuyOrder);


        //更新订单优惠信息
        if(JFBAmount > 0) {//外键约束
            commonRedenvelopeService.updatePayCouponByJFB(ebuyOrder, JFBAmount);
        }

        //生成buyRecord
        BigInteger recordId = uuidManager.getNextUuidBigInteger(SEQConstants.t_flash_deal_buy_record);
        FlashDealBuyRecord record = new FlashDealBuyRecord();
        record.setId(recordId);
        record.setRecordStatus(FlashDealActivityDict.BuyRecord.New_Record);
        record.settFlashDealActivityFId(activityEntityDetail.getActivityId());
        record.settUserFId(userId);
        record.setUserMobile(userMobile);
        record.settEbuyOrderFId(orderId);
        flashDealBuyRecordBaseService.insertFlashDealBuyRecord(record);

        boolean isAutoPaySucc = false;
        //订单单有免单的情况
        {//订单单待支付金额为0则直接设定订单状态为成功 syl-add-2015-8-12 11:11:39
            if (ebuyOrder.getAmount().compareTo(0L) == 0) {//剩余应付金额直接设置订单状态为支付成功
                commPayService.paySuccessOperate(ebuyOrder.getId(), EbuyDict.EbuyPay_PayMethod.PureRedEnvelope);//纯优惠支付
                isAutoPaySucc = true;
            }
        }

        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("orderId",orderId);
        resMap.put("isAutoPaySucc",isAutoPaySucc);

        return resMap;
    }

    @Override
    public boolean luckDrawByActivityId(BigInteger flashDealActivityId) {
        return flashDealActivityDao.luckDrawByActivityId(flashDealActivityId);
    }

    @Override
    public boolean saveFlashDealRemind(BigInteger activityId, BigInteger userId) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("tUserFId",userId);
        paraMap.put("tFlashDealActivityFId",activityId);
        List<FlashDealRemind> flashDealRemindList = flashDealRemindBaseService.getFlashDealRemindByCondition(paraMap);
        if(!DataUtil.isEmpty(flashDealRemindList)) {//存在提醒信息
            return false;
        }
        FlashDealRemind flashDealRemind = new FlashDealRemind();
        flashDealRemind.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_flash_deal_remind));
        FlashDealActivity flashDealActivity = flashDealActivityBaseService.getFlashDealActivityBySeqId(activityId);
        flashDealRemind.settFlashDealActivityFId(flashDealActivity.getId());
        flashDealRemind.setActivityStartTime(flashDealActivity.getActivityStartTime());
        flashDealRemind.setRemindStatus(0);
        flashDealRemind.settUserFId(userId);

        int i = flashDealRemindBaseService.insertFlashDealRemind(flashDealRemind);
        return i == 1;

    }

    @Override
    public List<FlashDealRemindEntity> getFlashDealRemindList() {
        return flashDealActivityDao.getFlashDealRemindList();
    }
}
