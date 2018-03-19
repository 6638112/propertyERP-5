package com.cnfantasia.server.commbusi.alterPeriod.service;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cnfantasia.parsii.util.ExpParserUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.commbusi.alterPeriod.dao.IAlterPeriodDao;
import com.cnfantasia.server.commbusi.propertyPayConfig.constant.PropertyPayConfigConstant;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.alterPeriodCfg.entity.AlterPeriodCfg;
import com.cnfantasia.server.domainbase.alterPeriodCfg.service.IAlterPeriodCfgBaseService;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.entity.AlterPeriodDataDetail;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.service.IAlterPeriodDataDetailBaseService;
import com.cnfantasia.server.domainbase.alterPeriodFeeItem.entity.AlterPeriodFeeItem;
import com.cnfantasia.server.domainbase.alterPeriodFeeItem.service.IAlterPeriodFeeItemBaseService;

/**
 * Created by yangh on 2016/10/20.
 */
public class LatefeeCalculateService {

    @Resource
    private IAlterPeriodCfgBaseService alterPeriodCfgBaseService;
    @Resource
    private IAlterPeriodDataDetailBaseService alterPeriodDataDetailBaseService;
    @Resource
    private IAlterPeriodFeeItemBaseService alterPeriodFeeItemBaseService;
    @Resource
    private IAlterPeriodDao alterPeriodDao;

    /**
     * 计算单条记录的滞纳金
     * @param periodDataDetailId
     */
    public boolean calculateByRoom(BigInteger periodDataDetailId, BigInteger alterPeriodcfgId) {
        AlterPeriodDataDetail alterPeriodDataDetail = alterPeriodDataDetailBaseService.getAlterPeriodDataDetailBySeqId(periodDataDetailId);
        AlterPeriodCfg alterPeriodCfg = alterPeriodCfgBaseService.getAlterPeriodCfgBySeqId(alterPeriodcfgId);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tGbId",alterPeriodCfg.gettGbId());
        paramMap.put("latefeeStatus",1);
        List<AlterPeriodFeeItem> alterPeriodFeeItemList = alterPeriodFeeItemBaseService.getAlterPeriodFeeItemByCondition(paramMap);

        long lateFeeAmount = 0;
        if(isCalculateLatefeeByRoom(alterPeriodDataDetail, alterPeriodCfg, alterPeriodFeeItemList)) {//需要计算滞纳金
            //计算滞纳金
            String latefeeStart = alterPeriodDataDetail.getLatefeeStart();
            if(!StringUtils.isEmpty(latefeeStart)) {//滞纳金时间为空 不需要计算
                Date latefeeDate = DateUtils.convertStrToDate(latefeeStart);
                try {
                    if((DateUtils.getDates(DateUtils.getCurrentDate()) - DateUtils.getDates(latefeeStart)) > 0) {//滞纳金时间要小于系统时间（用户缴费时间）
                        lateFeeAmount = getLateFee(alterPeriodDataDetail, alterPeriodCfg);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        alterPeriodDataDetail.setLatefeeAmount(lateFeeAmount);

        //保存滞纳金
        alterPeriodDataDetailBaseService.updateAlterPeriodDataDetail(alterPeriodDataDetail);

        return true;
    }

    /**
     * 异步计算并保存小区下的滞纳金信息
     * @param gbId
     */
    public void calculateByGroupBuildingByThread(BigInteger gbId) {
        new CalculateThread(gbId).start();
    }

    /**
     * 查询门牌对应的需要进行计算滞纳金的费用总和
     * @param realRoomId
     * @return
     */
    private long getNeedCalculateLatefeeSum(BigInteger realRoomId) {
        return alterPeriodDao.getNeedCalculateLatefeeSum(realRoomId);
    }

    /**
     * 获取滞纳金
     */
    private Long getLateFee(AlterPeriodDataDetail alterPeriodDataDetail, AlterPeriodCfg alterPeriodCfg) {
        //获取表达式
        String exp = alterPeriodCfg.getCalculateExpression();
        //校验表达式
        if(!ExpParserUtil.verification(exp)) {//表达式不正确
            return 0l;
        }
        //解释表达式
        StringBuilder newExp = new StringBuilder();
        for(int i=0; i<exp.length(); i++){
            String alpha = String.valueOf(exp.charAt(i));
            if(org.apache.commons.lang.StringUtils.isAlpha(alpha)){
                //查询对应字母的值
                getZiMuValue(newExp, alpha, alterPeriodDataDetail, alterPeriodCfg);
            } else {
                newExp.append(alpha);
            }
        }
        //表达式计算
        Long amt = ExpParserUtil.calculate(newExp.toString());
        if(amt != null && amt < 0) {
            amt = 0l;
        }
        return amt;
    }

    /**
     * 获取字母对应的值
     * @param alpha
     * @param alterPeriodDataDetail
     * @param alterPeriodCfg
     */
    private void getZiMuValue(StringBuilder newExp, String alpha, AlterPeriodDataDetail alterPeriodDataDetail, AlterPeriodCfg alterPeriodCfg) {
        if (PropertyPayConfigConstant.CalElement.PAYMENT_PER_MONTH.equals(alpha)) {//需计算滞纳金的每月应缴费
            NumberFormat nFormat2=NumberFormat.getNumberInstance();
            nFormat2.setMaximumFractionDigits(2);//设置小数点后面尾数为2
            newExp.append(nFormat2.format(BigDecimalUtil.div(getNeedCalculateLatefeeSum(alterPeriodDataDetail.gettRealRoomId()), 100.00)));
        }
        if(PropertyPayConfigConstant.CalElement.ACCRUAL_LATE_PAY.equals(alpha)) {//滞纳金利率
            NumberFormat nFormat2=NumberFormat.getNumberInstance();
            nFormat2.setMaximumFractionDigits(4);//设置小数点后面尾数为4
            newExp.append(nFormat2.format(BigDecimalUtil.div(alterPeriodCfg.getLatefeeRate(), 100.00)));
        }
        if(PropertyPayConfigConstant.CalElement.WY_START_TIME_BY_MONTH.equals(alpha)) {//物业费开始时间（按月计）
            newExp.append(DateUtils.getMonths(DateUtils.convertStrToDate(alterPeriodDataDetail.getBillMonthStart())));
        }
       /* if(PropertyPayConfigConstant.CalElement.WY_END_TIME_BY_MONTH.equals(alpha)) {//物业费截止时间（按月计）
            newExp.append(DateUtils.getMonths(DateUtils.convertStrToDate(alterPeriodDataDetail.getBillMonthStart())));
        }*/
        if(PropertyPayConfigConstant.CalElement.ZNJ_START_TIME_BY_MONTH.equals(alpha)) {//滞纳金开始时间（按月计）
            newExp.append(DateUtils.getMonths(DateUtils.convertStrToDate(alterPeriodDataDetail.getLatefeeStart())));
        }
        if(PropertyPayConfigConstant.CalElement.ZNJ_START_TIME_BY_DAY.equals(alpha)) {//滞纳金开始时间（按天计）
            try {
                newExp.append(DateUtils.getDates(alterPeriodDataDetail.getLatefeeStart()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(PropertyPayConfigConstant.CalElement.USER_PAY_TIME_PER_MONTH.equals(alpha)) {//用户缴费时间（按月计）
            newExp.append(DateUtils.getMonths(DateUtils.convertStrToDate(DateUtil.getCurrSysTimeStr())));
        }
        if(PropertyPayConfigConstant.CalElement.USER_PAY_TIME_PER_DAY.equals(alpha)) {//用户缴费时间（按天计）
            try {
                newExp.append(DateUtils.getDates(DateUtil.getCurrSysTimeStr()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断是否需要计算滞纳金(小区)
     * @param gbId
     * @return
     */
    private boolean isCalculateLatefeeByGb(AlterPeriodCfg alterPeriodCfg, List<AlterPeriodFeeItem> alterPeriodFeeItemList) {

        if(alterPeriodCfg == null) {//小区没有进行滞纳金配置 不需要计算
            return false;
        }

        if(alterPeriodFeeItemList.isEmpty()) {//没有任何收费项 不需要计算
            return false;
        }

        if(StringUtils.isEmpty(alterPeriodCfg.getCalculateExpression())) {//计算表达式为空 不需要计算
            return false;
        }

        return !(alterPeriodCfg.getLatefeeStatus() != null && alterPeriodCfg.getLatefeeStatus().equals(2));

    }

    /**
     * 判断是否需要计算滞纳金
     * @param alterPeriodDataDetail
     * @return
     */
    private boolean isCalculateLatefeeByRoom(AlterPeriodDataDetail alterPeriodDataDetail, AlterPeriodCfg alterPeriodCfg, List<AlterPeriodFeeItem> alterPeriodFeeItemList) {

        if(alterPeriodCfg == null) {//小区没有进行滞纳金配置 不需要计算
            return false;
        }

        if(alterPeriodFeeItemList.isEmpty()) {//没有任何收费项 不需要计算
            return false;
        }

        if(alterPeriodDataDetail == null) {//不存在门牌基础数据 不需要计算
            return false;
        }

        if(alterPeriodCfg.getLatefeeStatus() != null && alterPeriodCfg.getLatefeeStatus().equals(2)) {//不收取滞纳金
            return false;
        }

        if(StringUtils.isEmpty(alterPeriodCfg.getCalculateExpression())) {//计算表达式为空 不需要计算
            return false;
        }

        String latefeeStart = alterPeriodDataDetail.getLatefeeStart();
        return !StringUtils.isEmpty(latefeeStart);

    }

    /**
     * 异步 计算并保存滞纳金
     */
    class CalculateThread extends Thread {
        private BigInteger gbId;
        public CalculateThread(BigInteger gbId){
            this.gbId = gbId;
        }

        @Override
        public void run() {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("tGbId",gbId);
            AlterPeriodCfg alterPeriodCfg = null;
            List<AlterPeriodCfg> alterPeriodCfgList = alterPeriodCfgBaseService.getAlterPeriodCfgByCondition(paramMap);
            if(alterPeriodCfgList != null && alterPeriodCfgList.size() == 1) {
                alterPeriodCfg = alterPeriodCfgList.get(0);
            }
            paramMap.put("latefeeStatus",1);
            List<AlterPeriodFeeItem> alterPeriodFeeItemList = alterPeriodFeeItemBaseService.getAlterPeriodFeeItemByCondition(paramMap);
            paramMap.clear();
            paramMap.put("tAlterPeriodCfgId",alterPeriodCfg.getId());
            List<AlterPeriodDataDetail> detailList = alterPeriodDataDetailBaseService.getAlterPeriodDataDetailByCondition(paramMap);

            if(isCalculateLatefeeByGb(alterPeriodCfg, alterPeriodFeeItemList)) {//需要计算滞纳金
                for(int i = 0; i < detailList.size(); i++) {
                    //计算滞纳金
                    long lateFeeAmount = 0;
                    AlterPeriodDataDetail alterPeriodDataDetail = detailList.get(i);
                    String latefeeStart = alterPeriodDataDetail.getLatefeeStart();
                    if(!StringUtils.isEmpty(latefeeStart)) {//滞纳金时间为空 不需要计算
                        try {
                            if((DateUtils.getDates(DateUtils.getCurrentDate()) - DateUtils.getDates(latefeeStart)) > 0) {//滞纳金时间要小于系统时间（用户缴费时间）
                                lateFeeAmount = getLateFee(alterPeriodDataDetail, alterPeriodCfg);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    //保存滞纳金
                    alterPeriodDataDetail.setLatefeeAmount(lateFeeAmount);
                    alterPeriodDataDetailBaseService.updateAlterPeriodDataDetail(alterPeriodDataDetail);
                }
            } else {//保存滞纳金为零  只要无法计算的都是零
                for(int i = 0; i < detailList.size(); i++) {
                    //计算滞纳金
                    long lateFeeAmount = 0;
                    AlterPeriodDataDetail alterPeriodDataDetail = detailList.get(i);
                    //保存滞纳金
                    alterPeriodDataDetail.setLatefeeAmount(lateFeeAmount);
                    alterPeriodDataDetailBaseService.updateAlterPeriodDataDetail(alterPeriodDataDetail);
                }
            }
        }
    }
}
