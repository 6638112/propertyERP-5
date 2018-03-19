package com.cnfantasia.server.ms.revenue.task;

import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.dao.DredgeDao;
import com.cnfantasia.server.api.dredge.service.DredgeService;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBill.service.DredgeBillBaseService;
import com.cnfantasia.server.domainbase.dredgeBill.service.IDredgeBillBaseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 维修师傅端的定时任务
 */
public class DredgeTask implements ISynTask {

    private DredgeService dredgeService;

    private IDredgeBillBaseService dredgeBillBaseService;

    private ISysParamManager sysParamManager;

    private DredgeDao dredgeDao;

    @Override
    public Integer execTask() {
        //查询可接的单
        DredgeBill dredgeBill = new DredgeBill();
        dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Submited);
        List<DredgeBill> dredgeBills = dredgeBillBaseService.getDredgeBillByCondition(MapConverter.toMap(dredgeBill));
        if (dredgeBills == null || dredgeBills.size() == 0) {
            return 1;
        }

        int pushInteval = Integer.parseInt(sysParamManager.getSysParaValue("DredgeBillPushInterval"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date pushTime = null;
        Date now = new Date();
        for (DredgeBill bill : dredgeBills) {
            //物业报修不推送
            if (bill.getBillType().compareTo(DredgeConstant.DredgeBillType.Dredge_Repair) != 0) {
                int pushLevel = bill.getPushLevel();
                try {
                    pushTime = sdf.parse(bill.getPushTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //是否超过设定的推送时间间隔
                boolean isOverPushInteval = pushTime.getTime() + pushInteval * 60 * 1000 <= now.getTime();
                //当前推送等级还没推送到普通级且时间间隔到了
                if (pushLevel < DredgeConstant.DredgeWorkerLevel.Low_Level && isOverPushInteval) {
                    bill.setPushLevel(pushLevel + 1);
                    dredgeService.addDredgeBillPushMessage(bill, "又有新的订单啦", MsgpushDict.PushId.DredgeBill_AddNew);
                }
            }
        }
        return 1;
    }

    public void autoFinishBillTask() {
        dredgeDao.autoFinishBill();
    }

    public void setDredgeService(DredgeService dredgeService) {
        this.dredgeService = dredgeService;
    }

    public void setDredgeBillBaseService(DredgeBillBaseService dredgeBillBaseService) {
        this.dredgeBillBaseService = dredgeBillBaseService;
    }

    public void setSysParamManager(ISysParamManager sysParamManager) {
        this.sysParamManager = sysParamManager;
    }

    public void setDredgeDao(DredgeDao dredgeDao) {
        this.dredgeDao = dredgeDao;
    }
}
