package com.cnfantasia.server.ms.payBill.entity;

import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

import java.util.List;

/**
 * @ClassName: ExportBillDetailHead
 * @Date: 2017-09-28 11:36
 * @Auther: yanghua
 * @Description:(账单导出明细头)
 */
public class ExportBillDetailHead {
    private String feeItemName;
    private List<ExportBillDetailMrNameForHead> exportBillDetailMrNameForHeads;

    public List<ExportBillDetailMrNameForHead> getExportBillDetailMrNameForHeads() {
        return exportBillDetailMrNameForHeads;
    }

    public void setExportBillDetailMrNameForHeads(List<ExportBillDetailMrNameForHead> exportBillDetailMrNameForHeads) {
        this.exportBillDetailMrNameForHeads = exportBillDetailMrNameForHeads;
    }

    public String getFeeItemName() {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName) {
        this.feeItemName = feeItemName;
    }
}
