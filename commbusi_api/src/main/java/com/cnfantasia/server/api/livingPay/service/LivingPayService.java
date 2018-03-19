package com.cnfantasia.server.api.livingPay.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.cnfantasia.server.api.livingPay.entity.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.ebuy.util.OrderNoGenerator;
import com.cnfantasia.server.api.livingPay.constant.LivingPayDict;
import com.cnfantasia.server.api.livingPay.dao.LivingPayDao;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.EbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.livingFeeItem.dao.LivingFeeItemBaseDao;
import com.cnfantasia.server.domainbase.livingFeeItem.entity.LivingFeeItem;
import com.cnfantasia.server.domainbase.livingFeePayRecord.dao.LivingFeePayRecordBaseDao;
import com.cnfantasia.server.domainbase.livingFeePayRecord.entity.LivingFeePayRecord;
import com.cnfantasia.server.domainbase.omsUser.dao.OmsUserBaseDao;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.revenueApply.dao.RevenueApplyBaseDao;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.domainbase.revenueSignalAmount.dao.RevenueSignalAmountBaseDao;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.domainbase.user.dao.UserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.ms.revenue.constant.RevenueConstant;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.util.RevenueTkNoGenerator;

/**
 * 生活缴费Service类
 * @Author: wenfq
 * @Date: 2017-11-13 11:20
 */
public class LivingPayService {
    private Log logger = LogFactory.getLog(getClass());
    @Resource
    LivingPayDao livingPayDao;

    @Resource
    LivingFeePayRecordBaseDao livingFeePayRecordBaseDao;

    @Resource
    LivingFeeItemBaseDao livingFeeItemBaseDao;

    @Resource
    IUuidManager uuidManager;

    @Resource
    EbuyOrderBaseDao ebuyOrderBaseDao;

    @Resource
    OmsUserBaseDao omsUserBaseDao;

    @Resource
    RevenueSignalAmountBaseDao revenueSignalAmountBaseDao;

    @Resource
    RevenueApplyBaseDao revenueApplyBaseDao;
    
    @Resource
    private UserBaseDao userBaseDao;

    @Resource
    ISysParamManager sysParamManager;

    public List<PayItem> qryPayItemList() {
        return livingPayDao.qryPayItemList();
    }

    public List<PayRecord> qryLivingPayRecordList(BigInteger userId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        return livingPayDao.qryLivingPayRecordList(paramMap);
    }

    public List<PayRecordRevenue> qryLivingPayRevenueList(Map<String, Object> paramMap) {
        return livingPayDao.qryLivingPayRevenueList(paramMap);
    }

    /**
     * 确认充值成功
     * @param id 缴费记录id
     * @param amtBalance 充值后的余额
     * @return
     */
    public int confirmCharge(BigInteger id, double amtBalance) {
        LivingFeePayRecord livingFeePayRecordUpd = new LivingFeePayRecord();
        livingFeePayRecordUpd.setId(id);
        livingFeePayRecordUpd.setAmountBalance(PriceUtil.multiply100(amtBalance));
        livingFeePayRecordUpd.setStatus(LivingPayDict.LivingPayRecordStatus_HasCharge);
        int updCount = livingFeePayRecordBaseDao.updateLivingFeePayRecord(livingFeePayRecordUpd);

        {//充值成功后给用户发短信
            LivingFeePayRecord lfpr = livingFeePayRecordBaseDao.selectLivingFeePayRecordBySeqId(id);
            LivingFeeItem lfi = livingFeeItemBaseDao.selectLivingFeeItemBySeqId(lfpr.gettLivingFeeItemFId());
            if(lfpr.gettLivingFeeItemFId().intValue() <= 3) {//只有水电燃气才发短信通知
                //您为户号：{1}，地址：{2}缴纳{3}{4}已充值成功，当前余额为{5}
                List<String> mobiles = new ArrayList<>();
                mobiles.add(lfpr.getLinkTel());
                logger.info("send msg mobile is: " + mobiles);
                ShortMsgUtil.sendMessages(mobiles, "charge_finished", lfpr.getChargeObject(), lfpr.getAddress(),
                        lfi.getName(), PriceUtil.div100(lfpr.getAmount()), new BigDecimal(amtBalance).setScale(2, RoundingMode.HALF_UP));
            }
        }
        return updCount;
    }

    public int qryLivingPayRevenueListTotalCount(Map<String, Object> paramMap) {
        return livingPayDao.qryLivingPayRevenueListTotalCount(paramMap);
    }

    /**
     * 提交缴费订单
     * @param submitParams
     * @return
     */
    public BigInteger submitOrder(LivingSubmitParams submitParams) {
        BigInteger userId = submitParams.getUserId();

        EbuyOrder eo = new EbuyOrder();
        eo.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order));
        eo.setType(EbuyDict.EbuyOrder_Type.Living_Fee_Bill);

        //宽带充值:金额为零需要进行特殊处理
        if(submitParams.getFeeTypeId() == 6 && submitParams.getAmount() == 0) {
            eo.setStatus(EbuyDict.EbuyOrder_Status.YiWanCheng);
            eo.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Pay_Success);
            eo.setPayMethod(EbuyDict.EbuyPay_PayMethod.PurePoint + "");
        } else {
            eo.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
        }

        eo.setAmount(PriceUtil.multiply100(submitParams.getAmount()));
        eo.setTotalDeliveryFee(0L);
        eo.setOrderNo(OrderNoGenerator.getOrderNo(eo.getId()));
        eo.setBuyTime(DateUtils.getCurrentDate());
        eo.setPayTime(DateUtils.getCurrentDate());

        eo.setBuyerId(userId);
		User user = userBaseDao.selectUserBySeqId(userId);
		if (null != user) {
			eo.setCurrRoomId(user.getDefaultRoomId());
		}
        eo.setCreater(userId);
        ebuyOrderBaseDao.insertEbuyOrder(eo);

        LivingFeePayRecord livingFeePayRecordNew = new LivingFeePayRecord();
        livingFeePayRecordNew.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_living_fee_pay_record));
        livingFeePayRecordNew.settLivingFeeItemFId(BigInteger.valueOf(submitParams.getFeeTypeId()));
        livingFeePayRecordNew.setAmount(PriceUtil.multiply100(submitParams.getAmount()));
        livingFeePayRecordNew.setGroupBuildingName(submitParams.getGbName());
        livingFeePayRecordNew.setRoomNumuber(submitParams.getRoomNum());

        if(!StringUtils.isEmpty(submitParams.getGbName()) && !StringUtils.isEmpty(submitParams.getRoomNum())) {
            livingFeePayRecordNew.setAddress(submitParams.getGbName() + submitParams.getRoomNum());
        }
        //宽带充值
        if(submitParams.getFeeTypeId() == 6) {
            //户号：“运营商--姓名--身份证号”，例：电信--张三--320481456545687921
            String spName = "";
            if(submitParams.getChargeObject() != null  && submitParams.getChargeObject().contains("--")) {
                spName = submitParams.getChargeObject().substring(0, submitParams.getChargeObject().indexOf("--"));
            } else {
                spName = submitParams.getChargeObject();
            }
            submitParams.setChargeObject(spName + "--" + submitParams.getName() + "--" + submitParams.getCardId());
            //直接置为已缴费成功状态
            livingFeePayRecordNew.setStatus(LivingPayDict.LivingPayRecordStatus_UnCharge);
            livingFeePayRecordNew.setPayTime(DateUtils.getCurrentDate());
        } else {
            livingFeePayRecordNew.setStatus(LivingPayDict.LivingPayRecordStatus_UnPay);
        }
        livingFeePayRecordNew.setChargeObject(submitParams.getChargeObject());
        livingFeePayRecordNew.setLinkTel(submitParams.getLinkTel());
        livingFeePayRecordNew.settUserFId(userId);
        livingFeePayRecordNew.settEbuyOrderFId(eo.getId());
        livingFeePayRecordNew.setName(submitParams.getName());
        livingFeePayRecordNew.setCardId(submitParams.getCardId());
        livingFeePayRecordNew.settSpId(submitParams.getSpId());
        livingFeePayRecordBaseDao.insertLivingFeePayRecord(livingFeePayRecordNew);

        return eo.getId();
    }

    /**
     * 支付成功回调逻辑
     * @param orderId 订单id
     */
    public void updateDredgeBillAfterPaySuccess(BigInteger orderId){
        LivingFeePayRecord livingFeePayRecordQry = new LivingFeePayRecord();
        livingFeePayRecordQry.settEbuyOrderFId(orderId);
        List<LivingFeePayRecord> livingFeePayRecordList = livingFeePayRecordBaseDao.selectLivingFeePayRecordByCondition(MapConverter.toMap(livingFeePayRecordQry), false);
        LivingFeePayRecord livingFeePayRecordUdp = new LivingFeePayRecord();
        livingFeePayRecordUdp.setId(livingFeePayRecordList.get(0).getId());
        livingFeePayRecordUdp.setStatus(LivingPayDict.LivingPayRecordStatus_UnCharge);
        livingFeePayRecordUdp.setPayTime(DateUtils.getCurrentDate());
        livingFeePayRecordBaseDao.updateLivingFeePayRecord(livingFeePayRecordUdp);

        {//发送短信通知运营人员：户号：<210001212>，地址：<招东小区1-101>用户缴纳<水费><100元>，请及时处理
            LivingFeePayRecord livingFeePayRecord = livingFeePayRecordList.get(0);

            String notifyPhoneStr = sysParamManager.getSysParaValue(SysParamKey.PAY_SUCC_NOTIFY_PHONE_LivingPayBill);
            LivingFeeItem livingFeeItem = livingFeeItemBaseDao.selectLivingFeeItemBySeqId(livingFeePayRecord.gettLivingFeeItemFId());
            List<String> mobiles = new ArrayList<String>();
            for (String notifyPhone : notifyPhoneStr.split(";")) {
                if (!StringUtils.isEmpty(notifyPhone)) {
                    mobiles.add(notifyPhone);
                }
            }
            //电话和固话充值是没有地址的
            String address = livingFeePayRecord.gettLivingFeeItemFId().intValue() > 3 ? "": "地址：" + livingFeePayRecord.getAddress()+"，";
            ShortMsgUtil.sendMessages(mobiles, "living_pay_notify", livingFeePayRecord.getChargeObject(), address,
                    livingFeeItem.getName(), PriceUtil.div100(livingFeePayRecord.getAmount()));
        }
    }

    /**
     * 发起提款
     * @return
     * @param omsUserId
     * @param userName
     */
    public double applyRevenue(BigInteger omsUserId, String userName) {
        List<Info4AppleRevenue> info4AppleRevenueList = livingPayDao.selectInfo4ApplyRevenue();
        if(info4AppleRevenueList.isEmpty())
            return 0;

        double totalAmt = 0;
        BigInteger applyId = uuidManager.getNextUuidBigInteger(SEQConstants.t_revenue_apply);
        List<RevenueSignalAmount> rsaList = new ArrayList<>();
        for (Info4AppleRevenue info4AppleRevenue : info4AppleRevenueList) {
            RevenueSignalAmount rsa = new RevenueSignalAmount();
            rsa.setId(info4AppleRevenue.getRsaId());
            rsa.setTkStatus(RevenueDict.TkStatus.Doing);
            rsa.setRevApplyId(applyId);
            rsaList.add(rsa);
            totalAmt += info4AppleRevenue.getRevenueAmt().doubleValue();
        }
        RevenueApply ra = new RevenueApply();
        ra.setId(applyId);
        ra.setTkStatus(RevenueDict.TkStatus.Doing);
        ra.setAmountPtbt(0d);
        ra.setAmountUsrReal(totalAmt);
        ra.setTotalAmount(totalAmt);
        ra.setApplyNo(RevenueTkNoGenerator.getOrderNo(applyId));
        ra.setVisibleType(RevenueDict.VisibleType.UserVisible);
        ra.setMiniRoleId(omsUserId);
        ra.setMiniRoleType(RevenueDict.MiniRoleType.LivingFeePay);
        ra.setGoalType(RevenueDict.RevenueProject.LivingPayAmount);
        ra.setApplyUserId(RevenueConstant.System);
        ra.setApplyTime(DateUtils.getCurrentDate());
        ra.setGoalEndTime(DateUtils.getCurrentDate());
        ra.setOptType(RevenueDict.TkOptType.User);
        ra.setRoleName(userName);
        ra.setSys0AddUser(omsUserId);


        revenueApplyBaseDao.insertRevenueApply(ra);
        revenueSignalAmountBaseDao.updateRevenueSignalAmountBatch(rsaList);

        livingPayDao.updBankInfo(ra.getId());

        return  totalAmt;
    }


    /**
     * 产生收益明细
     */
    public int generateRevenueSignalAmount(){
        List<Info4Revenue> info4RevenueList = livingPayDao.selectInfo4RevenueList();
        if(info4RevenueList.isEmpty())
            return 0;

        OmsUser omsUser = new OmsUser();
        omsUser.setUserAccount("tangtt");

        /**
         * 汤甜甜的账号
         */
        OmsUser tangttUser = omsUserBaseDao.selectOmsUserByCondition(MapConverter.toMap(omsUser), false).get(0);

        List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount, info4RevenueList.size());
        List<RevenueSignalAmount> revenueSignalAmountList = new ArrayList<>(idList.size());
        for (int i = 0;  i < info4RevenueList.size(); i++) {
            Info4Revenue info4Revenue = info4RevenueList.get(i);
            RevenueSignalAmount rsa = new RevenueSignalAmount();
            rsa.setId(idList.get(i));
            rsa.setGoalId(info4Revenue.getGoalId());
            rsa.setAmount(BigDecimalUtil.div100(info4Revenue.getPayAmount()).doubleValue());
            rsa.setAmountPtbt(0d);
            rsa.setSrcMoney(BigDecimalUtil.div100(info4Revenue.getPayAmount()).doubleValue());
            rsa.setAmountUsrReal(BigDecimalUtil.div100(info4Revenue.getPayAmount()).doubleValue());
            rsa.setGoalRevTime(DateUtils.getCurrentDate());
            rsa.setGoalRevTimeStr("");
            rsa.setMiniRoleId(tangttUser.getId());
            rsa.setMiniRoleType(RevenueDict.MiniRoleType.SysAdmin);
            rsa.setMoneyTime(DateUtils.getCurrentDate());
            rsa.setOrderNo(info4Revenue.getOrderNo());
            rsa.setPayFlowNo(info4Revenue.getFlowNo());
            rsa.setRoleName(tangttUser.getRealName());
            rsa.setGoalType(RevenueDict.RevenueProject.LivingPayAmount);
            rsa.setRevConfigId(BigInteger.ZERO);
            rsa.setRevConfigJson("");
            rsa.setTkStatus(RevenueDict.TkStatus.Undo);
            revenueSignalAmountList.add(rsa);
        }
        revenueSignalAmountBaseDao.insertRevenueSignalAmountBatch(revenueSignalAmountList);
        return revenueSignalAmountList.size();
    }

    public HSSFWorkbook createRevenueReport(Map<String, Object> paramMap) {
        List<PayRecord4RevenueExport> payRecord4RevenueExportList = livingPayDao.qrypayRecord4RevenueExportList(paramMap);
        if(payRecord4RevenueExportList.isEmpty())
            return null;

        String fileServiceBasePath = CnfantasiaCommbusiUtil.getFileServiceBasePath();
        String filePath = fileServiceBasePath + ("docs/jfq_revenue_living_pay_template.xls");
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = wb.getSheetAt(0);
        for (int i = 0; i < payRecord4RevenueExportList.size(); i++) {
            PayRecord4RevenueExport payRecord4RevenueExport = payRecord4RevenueExportList.get(i);
            HSSFRow row = sheet.createRow(i+1);
            int j = 0;
            row.createCell(j++).setCellValue(payRecord4RevenueExport.getOrderNo());
            row.createCell(j++).setCellValue(payRecord4RevenueExport.getFeeTypeName());
            row.createCell(j++).setCellValue(payRecord4RevenueExport.getChargeObject());
            row.createCell(j++).setCellValue(payRecord4RevenueExport.getAddress());
            row.createCell(j++).setCellValue(payRecord4RevenueExport.getAmount().setScale(2).toString());
            row.createCell(j++).setCellValue(payRecord4RevenueExport.getPayFlowNo());
            row.createCell(j++).setCellValue(payRecord4RevenueExport.getPayTime());
            row.createCell(j++).setCellValue(payRecord4RevenueExport.getPayMethod());
            row.createCell(j++).setCellValue(payRecord4RevenueExport.getChargeStatusString());
            row.createCell(j++).setCellValue(payRecord4RevenueExport.getSettleStatusString());
        }
        return wb;
    }

    /**
     * 查询生活缴费首页滚动广告
     * @return
     */
    public List<AdEntity> qryadsList() {
        //{imgUrl:imgLinkUrl} --> {"imgUrl":"images/feepay_banner.png","imgLinkUrl":"", "imgUrl":"images/feepay_banner1.png","imgLinkUrl":"http://www.jiefangqu.com"}
        String imgsStr = sysParamManager.getSysParaValue(SysParamKey.AD_IMAGES_FOR_LivingPay_INDEX);
        List<AdEntity> adEntityList = JSONArray.parseArray(imgsStr, AdEntity.class);
        return adEntityList;
    }
}
