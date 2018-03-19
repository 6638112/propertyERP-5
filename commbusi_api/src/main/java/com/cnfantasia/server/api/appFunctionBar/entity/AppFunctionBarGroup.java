package com.cnfantasia.server.api.appFunctionBar.entity;

import java.util.List;

/**
 * @ClassName: AppFunctionBarGroup
 * @Date: 2017-02-23 14:00
 * @Auther: kangduo
 * @Description: ()
 */
public class AppFunctionBarGroup {
    private Integer functionType;
    private List<AppFuncBar> functionList;

    public Integer getFunctionType() {
        return functionType;
    }

    public void setFunctionType(Integer functionType) {
        this.functionType = functionType;
    }

    public List<AppFuncBar> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<AppFuncBar> functionList) {
        this.functionList = functionList;
    }
}
