<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%--<base href="<%=basePath%>//"/>--%>
    <title>常规收费项管理</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>常规收费项管理</h2>
    <form action="<%=basePath%>/fixedFeeCfg/getGroupBuildingList.html" method="post">
        <div class="bs-example bgebeb">
        <table class="info-list" border="0">
            <tr>
                <td>
                    <div class="list-name">小区名称：</div>
                </td>
                <td>
                    <input type="text" name="gbName" class="input_text w120 pp" value="${gbName}">
                </td>
                <td colspan="6" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
            <tr>
        </table>
    </div>
    </form>
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="total" >
        <display:column title="小区名称" property="gbName" />
        <display:column title="操作">
            <a class="blue viewTicket" href="<%=basePath%>/fixedFeeCfg/jumpToCreateFeeDetail.html?gbId=${row.id}&gbName=${row.gbName}">收费配置</a>&nbsp;&nbsp;
            <a class="blue viewTicket" href="<%=basePath%>/groupBuildingBillCycle/billCycleList.html?gbId=${row.id}&feeType=2">缴费周期</a>&nbsp;&nbsp;
            <a class="blue viewTicket" href="<%=basePath%>/payBill/listPeriod.html?gbId=${row.id}&cycleType=1&feeType=2">账单查询</a>
        </display:column>
    </display:table>

</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
</html>
