<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>滞纳金设置</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
    <link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>
<body>
<div class="info">
    <h2>${gbName}--滞纳金计算规则设置</h2>
    <div class="bs-example bgebeb">
        <form class="inputform" method="post" action="../groupBuildingBillCycle/savelateFeeCfg.json">
            <input type="hidden" name="tGbId" value="${gbId }"/>
            <input type="hidden" name="id" value="${entity.id }"/>
            <table class="info-list-02" border="0">
                <tr>
                    <td><div class="list-name">计算方式：</div></td>
                    <td>
                        <input class="mtop3" name="calculateType"<c:if test="${entity.calculateType == null ||  entity.calculateType == 1}">checked="checked"</c:if> type="radio" value="1" />按月计算
                        (<input type="number" class="input_text w50 pp" name="calculateDaysByMonth" value="<c:if test="${entity.calculateType != 2 && entity.calculateType > 0}">${entity.calculateDaysByMonth }</c:if>"/>天)&nbsp;&nbsp;
                        <input class="mtop3" name="calculateType" <c:if test="${entity.calculateType == 2}">checked="checked"</c:if> type="radio" value="2" />按天计算
                    </td>
                </tr>
                <tr>
                    <td><div class="list-name">开始计费时间：</div></td>
                    <td>
                        每月<input type="number" class="input_text w50 pp" name="calculateStart" value="${entity.calculateStart }"/>号（小于28号的时间)
                    </td>
                </tr>
                <tr>
                    <td><div class="list-name">滞纳金利率：</div></td>
                    <td>
                        <input type="number" class="input_text pp" style="width: 75px;" name="calculateRate" value="${entity.calculateRate }"/>%（填写小于等于100的数字，最多保留四位小数)
                    </td>
                </tr>
                <tr>
                    <td colspan="10"><input class="info-btn small-btn w150" id="saveCfg" type="button" value="保 存"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" >
    $("#saveCfg").click(function () {
        var isSubmit = true;
        var msg = "";
        $(".info").find("input[type='number']").each(function () {
            var val = $(this).val() * 1;
            var attrName = $(this).attr("name");
            if(0 > val) {
                isSubmit = false;
                msg = attrName + ": 必须为大于零的数字！";
                return;
            }
            if(attrName == "calculateDaysByMonth" && val > 31) {
                isSubmit = false;
                msg = "按月计算天数不能大于31";
                return;
            }
            if(attrName == "calculateStart" && val > 28) {
                isSubmit = false;
                msg = "开始计费时间必须小于28号的时间";
                return;
            }
            var regex = /^\d+(\.\d{1,4})?$/;
            if(attrName == "calculateRate" && val>100 || !regex.test(val)) {
                isSubmit = false;
                msg = "填写小于等于100的数字，最多保留四位小数";

            }
        });
        if(!isSubmit) {
            alert(msg);
            return false;
        }
        $(".inputform").submit();
    });
</script>
</html>