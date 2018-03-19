package com.cnfantasia.server.ms.revenue.task;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.commSysPara.entity.CompanyInfoConfig;
import com.cnfantasia.server.api.commSysPara.parser.CompanyInfoParamParser;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.ms.payBill.entity.MsgForPaybill;
import com.cnfantasia.server.ms.payBill.service.IPayBillService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: PayBillMsgAutoSendTask
 * @Date: 2016-12-09 16:55
 * @Auther: kangduo
 * @Description: (物业账单自己发短信通知用户)
 */
public class PayBillMsgAutoSendTask implements ISynTask {

    private IPayBillService payBillService;
    public void setPayBillService(IPayBillService payBillService) {
        this.payBillService = payBillService;
    }

    @Override
    public Integer execTask() {
    	CompanyInfoParamParser companyInfoParamParser = (CompanyInfoParamParser) CnfantasiaCommbusiUtil.getBeanManager("companyInfoParamParser");
    	CompanyInfoConfig companyInfoConfig = companyInfoParamParser.parseParamValue();
    	//客服电话
    	String servePhone = companyInfoConfig.getTel();
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");


        List<MsgForPaybill> msgForPaybillList = payBillService.getMsgForPaybillEnd();
    	for(MsgForPaybill msgForPaybill : msgForPaybillList) {
            if (!DataUtil.isEmpty(msgForPaybill.getMobile())
                    && !DataUtil.isEmpty(msgForPaybill.getBillMothDate())
                    && !DataUtil.isEmpty(msgForPaybill.getBillEndDate())) {
                //尊敬的业主，{1}{2}物业缴费现已开始，{3}截止缴费，特此提醒。垂询{4}  王冲 说暂时保留以前的模板
               /* ShortMsgUtil.sendMessage(msgForPaybill.getMobile(), "gb_pay_bill_end", msgForPaybill.getGbName(),
                        DateUtils.getMonthOfDate(msgForPaybill.getBillMothDate()) + "月",
                        sdf.format(msgForPaybill.getBillEndDate()),
                        servePhone);*/
                //尊敬的业主：您所在门牌{1}{2}，{3}{4}合计{5}，请于{6}>登录解放区缴费，特此提醒 ~ 垂询{7}
                List<Object> list = new ArrayList<Object>();
                list.add(msgForPaybill.getGbName());
                list.add(msgForPaybill.getRoomNum());
                mergeBillPayMonth(msgForPaybill, list);
                list.add(msgForPaybill.getBillName());
                list.add(msgForPaybill.getAmount());
                list.add(sdf.format(msgForPaybill.getBillEndDate()));
                list.add(servePhone);
                ShortMsgUtil.sendMessage(getFirstMobile(msgForPaybill.getMobile()), "gb_pay_bill", list);
            }
    	}

    	//银行托收成功
        msgForPaybillList = payBillService.getMsgForPaybillAfterBankSuccess();
        sendMsg(msgForPaybillList, "gb_bill_after_bank_s");
        sleep(2000);

//    	未注册的业主短信
        msgForPaybillList = payBillService.getMsgForPaybillStartUnRegister();
        sendMsg(msgForPaybillList, "gb_bill_unregister");
        sleep(1000);

//        首次出盘前一天发短信
        msgForPaybillList = payBillService.getMsgForPaybillBeforeBank();
        sendMsg(msgForPaybillList, "gb_bill_before_bank");
        sleep(1000);

//        首次回盘后一天发短信
        msgForPaybillList = payBillService.getMsgForPaybillAfterBank();
        sendMsg(msgForPaybillList, "gb_bill_after_bank");


        msgForPaybillList = payBillService.getMsgForPaybillStart();
    	for(MsgForPaybill msgForPaybill : msgForPaybillList) {
            if (!DataUtil.isEmpty(msgForPaybill.getMobile())
                    && !DataUtil.isEmpty(msgForPaybill.getBillMothDate())
                    && !DataUtil.isEmpty(msgForPaybill.getBillEndDate())) {
                //尊敬的业主，{1}{2}物业缴费仅剩两天，{3}截止缴费，特此提醒。垂询{4}
                /*ShortMsgUtil.sendMessage(msgForPaybill.getMobile(), "gb_pay_bill_start", msgForPaybill.getGbName(),
                        DateUtils.getMonthOfDate(msgForPaybill.getBillMothDate()) + "月",
                        sdf.format(msgForPaybill.getBillEndDate()),
                        servePhone);*/
                //尊敬的业主：您所在门牌{1}{2}，{3}{4}合计{5}，请于{6}>登录解放区缴费，特此提醒 ~ 垂询{7}
                List<Object> list = new ArrayList<Object>();
                list.add(msgForPaybill.getGbName());
                list.add(msgForPaybill.getRoomNum());
                mergeBillPayMonth(msgForPaybill, list);
                list.add(msgForPaybill.getBillName());
                list.add(msgForPaybill.getAmount());
                list.add(sdf.format(msgForPaybill.getBillEndDate()));
                list.add(servePhone);
                ShortMsgUtil.sendMessage(getFirstMobile(msgForPaybill.getMobile()), "gb_pay_bill", list);
            }
    	}

        return 1;
    }

    private void sendMsg(List<MsgForPaybill> msgForPaybillList, String msgTemplateKey) {
        for (MsgForPaybill msgForPaybill : msgForPaybillList) {
            if (!DataUtil.isEmpty(msgForPaybill.getMobile())
                    && !DataUtil.isEmpty(msgForPaybill.getBillMothDate())
                    && !DataUtil.isEmpty(msgForPaybill.getBillEndDate())) {
                //您所在门牌{1}{2}，尚未缴纳{3}{4}合计{5}。您可以点击http://t.cn/RJrFqJG下载物业服务APP-解放区，注册登录后即可获取您的账单并缴纳，本小区大部分业主正在使用，祝您生活愉快。退订回T
                List<Object> list = new ArrayList<Object>();
                list.add(msgForPaybill.getGbName());
                list.add(msgForPaybill.getRoomNum());
                mergeBillPayMonth(msgForPaybill, list);
                //list.add(DateUtils.getMonthOfDate(msgForPaybill.getBillMothDate()) + "月");
                list.add(msgForPaybill.getBillName());
                list.add(msgForPaybill.getAmount());
                ShortMsgUtil.sendMessage(getFirstMobile(msgForPaybill.getMobile()), msgTemplateKey, list);
            }
        }
    }
    
    private void mergeBillPayMonth(MsgForPaybill msgForPaybill, List<Object> list) {
    	if(StringUtils.isNotBlank(msgForPaybill.getBillMonthStart()) 
        		&& StringUtils.isNotBlank(msgForPaybill.getBillMonthEnd())){
        	if(msgForPaybill.getBillMonthStart().equals(msgForPaybill.getBillMonthEnd())){
        		list.add(msgForPaybill.getBillMonthStart());
        	} else {
        		list.add(msgForPaybill.getBillMonthStart()+"至"+msgForPaybill.getBillMonthEnd());
        	}
        } else {
        	list.add(DateUtils.getMonthOfDate(msgForPaybill.getBillMothDate()) + "月");
        }
    }
    
    private void sleep(long millis) {
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    private String getFirstMobile(String mobile) {
        if (DataUtil.isEmpty(mobile)) {
            return null;
        }
        return Arrays.asList(mobile.split(",")).get(0);
    }
}
