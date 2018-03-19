package com.cnfantasia.server.api.access.entity;

/**
 * @author wangzhe
 * @date 2015年12月24日
 * @description 小区停车位信息
 *
 */
public class GroupBuildingParkingInfo {

    private Integer total; // 总共
    private Integer current; // 当前

    public Integer getTotal() {
        return total;
    }

    public void setTotal(final Integer total) {
        this.total = total;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(final Integer current) {
        this.current = current;
    }
}
