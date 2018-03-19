package com.cnfantasia.server.api.carMsgTask.task;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.http.util.TextUtils;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

public class CarSendOfflineMsgRunnable implements Runnable {

    @Resource
    private ISysParamManager sysParamManager;

    @Resource
    private IGroupBuildingBaseDao groupBuildingBaseDao;

    @Override
    public void run() {

        Set<String> gbidsets = RedisCacheHandler.sinter(RedisCachePrefix.car_offline_list);

        if (0 < gbidsets.size()) {

            String liaisons = sysParamManager.getSysParaValue("car_offline_liaison");
            List<String> list = new ArrayList<String>();
            StringTokenizer st = new StringTokenizer(liaisons, ";");
            while (st.hasMoreTokens()) {
                String next = st.nextToken().trim();
                if (!TextUtils.isEmpty(next)) {
                    list.add(next);
                } else {
                }
            }

            for (String gbid : gbidsets) {
                GroupBuilding gb = groupBuildingBaseDao.selectGroupBuildingBySeqId(new BigInteger(gbid));
                if (null != gb && null != gb.getName()) {
                    for (String phone : list) {
                        ShortMsgUtil.sendMessage(phone, "car.offline", gb.getName());
                    }
                } else {
                }
            }

            RedisCacheHandler.srem(RedisCachePrefix.car_offline_list, gbidsets.toArray(new String[gbidsets.size()]));

        } else {
        }
    }

}
