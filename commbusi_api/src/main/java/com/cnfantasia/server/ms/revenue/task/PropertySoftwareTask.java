package com.cnfantasia.server.ms.revenue.task;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.propertyRepair.constant.PropertyRepairDict;
import com.cnfantasia.server.api.propertyRepair.service.IPropertyRepairService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.exception.ExceptionParseUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.propertySoftwareDock.base.dao.IPropertySoftwareDockDao;
import com.propertySoftwareDock.base.entity.MailSendThread;
import com.propertySoftwareDock.base.entity.RealRoomSoftwareInfo;
import com.propertySoftwareDock.base.entity.RepairPushEntity;
import com.propertySoftwareDock.base.service.IPropertySoftwareDockService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: PropertySoftwareTask
 * @Date: 2016-11-28 15:15
 * @Auther: kangduo
 * @Description:()
 */
public class PropertySoftwareTask implements ISynTask {

    private IPropertySoftwareDockDao propertySoftwareDockDao;
    public void setPropertySoftwareDockDao(IPropertySoftwareDockDao propertySoftwareDockDao) {
        this.propertySoftwareDockDao = propertySoftwareDockDao;
    }

    private IPropertyRepairService propertyRepairService;
    public void setPropertyRepairService(IPropertyRepairService propertyRepairService) {
        this.propertyRepairService = propertyRepairService;
    }

    private ISysParamManager sysParamManager;
    public void setSysParamManager(ISysParamManager sysParamManager) {
        this.sysParamManager = sysParamManager;
    }

    @Override
    public Integer execTask() {
        List<RealRoomSoftwareInfo> unNotifyPayBillIdList = propertySoftwareDockDao.getUnNotifySoftwarePayBillIds();
        for (RealRoomSoftwareInfo softwareInfo : unNotifyPayBillIdList) {
            IPropertySoftwareDockService softwareDockService = (IPropertySoftwareDockService) CnfantasiaCommbusiUtil.
                    getBeanManager(softwareInfo.getServiceClassName());
            softwareDockService.noticePaid(softwareInfo.getPayBillId());
        }
        return 1;
    }

    public Integer pushRepairTask() throws Exception {
        String keyPrefix = RedisCachePrefix.property_repair_push_software;
        Set<String> keys = RedisCacheHandler.keys(keyPrefix);
        if (keys == null) {
            return 0;
        }
        for (String key : keys) {
            RepairPushEntity entity = JSON.parseObject(RedisCacheHandler.get(key), RepairPushEntity.class);
            if (entity != null) {
                propertyRepairService.pushRepairToSoftware(entity);
            }
        }
        return 1;
    }

    public Integer getRepairDetailTask() throws Exception {
        String keyPrefix = RedisCachePrefix.property_repair_need_software_detail;
        Set<String> keys = RedisCacheHandler.keys(keyPrefix);
        if (keys == null) {
            return 0;
        }
        for (String key : keys) {
            RepairPushEntity entity = JSON.parseObject(RedisCacheHandler.get(key), RepairPushEntity.class);
            if (entity != null) {
                synRepairDetail(entity, key);
            }
        }
        return 1;
    }

    @Transactional
    private void synRepairDetail(RepairPushEntity entity, String key) {
        RealRoomSoftwareInfo softwareInfo = entity.getSoftwareInfo();
        IPropertySoftwareDockService dockService = (IPropertySoftwareDockService) CnfantasiaCommbusiUtil.
                getBeanManager(softwareInfo.getServiceClassName());
        String result = null;
        String exception = "";
        try {
            result = dockService.synRepairDetail(entity);
        } catch (Exception e) {
            e.printStackTrace();
            exception = ExceptionParseUtil.parseExceptionMessage(e);
        }
        if (result == null) {
            entity.setFailCount(entity.getFailCount() + 1);
            if (entity.getFailCount() > 2) {
                RedisCacheHandler.del(key);
                String notifyMail = sysParamManager.getSysParaValue(SysParamKey.PropertySoftwareNotifyFailMail);
                if (!DataUtil.isEmpty(notifyMail)) {
                    String content = "获取物业管理软件维修单详情失败：<br>" +
                            "pushEntity : " + JSON.toJSONString(entity) + "<br>" +
                            "exception：" + exception + "<br>";
                    new MailSendThread("获取物业管理软件维修单详情失败", content, notifyMail).start();
                }
            } else {
                RedisCacheHandler.set(key, JSON.toJSONString(entity), propertyRepairService.getRepairDetailExpireSecond(entity));
            }
        } else if (entity.getRepair() != null && entity.getRepair().getRepaireState().equals(PropertyRepairDict.RepairStatus.DONE)) {
            RedisCacheHandler.del(key);
        } else if (entity.getDredgeBill() != null && DredgeConstant.DredgeBill.DredgeBill_Status_FinishedHasComment == entity.getDredgeBill().getStatus()) {
            RedisCacheHandler.del(key);
        }
    }
}
