package com.cnfantasia.server.api.selfActivity.entity;

import com.cnfantasia.server.domainbase.selfActivity.entity.SelfActivity;
import com.cnfantasia.server.domainbase.selfActivityDetail.entity.SelfActivityDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: wenfq
 * @Date: 2017-07-07 13:30
 */
public class SeftActivityWithDetail extends SelfActivity {
    List<SelfActivityDetail> sadList = new ArrayList<SelfActivityDetail>();

    public List<SelfActivityDetail> getSadList() {
        return sadList;
    }

    public void setSadList(List<SelfActivityDetail> sadList) {
        this.sadList = sadList;
    }
}
