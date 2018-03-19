<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%--<base href="<%=basePath%>//"/>--%>
    <title>收费项设置</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <form class="inputform">
        <h2>收费项配置</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <tr>
                <td width="120" align="right">小区名称：</td>
                <td>${entity.gbName}</td>
            </tr>
            <tr>
                <td align="right">楼栋：</td>
                <td>${entity.bName}</td>
            </tr>
            <tr>
                <td align="right">单元号：</td>
                <td>${entity.unitName}</td>
            </tr>
            <tr>
                <td align="right">房间号：</td>
                <td>${entity.roomNumTail}</td>
            </tr>
            <c:forEach items="${entity.editFeeItemList}" var="feeItem">
                <tr>
                    <td colspan="2" class="bold">${feeItem.name}</td>
                </tr>
                <tr>
                    <td align="right"><span class="red">*</span> 金额：</td>
                    <td>${feeItem.totalPrice div 100} 元</td>
                </tr>
                <c:if test="${feeItem.calculateType == 1}"><%--单价*建筑面积--%>
                    <tr>
                        <td align="right"><span class="red">*</span> 建筑面积：</td>
                        <td>${feeItem.priceUnitValue div 100}平方米</td>
                    </tr>
                    <tr>
                        <td align="right"><span class="red">*</span> 单价：</td>
                        <td>${feeItem.signalPrice div 100} 元</td>
                    </tr>
                </c:if>
                <td align="right"><span class="red">*</span> 费用起始时间：</td>
                <td>
                    <c:if test="${not empty feeItem.billMonthStart}">
                        ${fn:substring(feeItem.billMonthStart,0,10)}
                    </c:if>
                </td>
            </c:forEach>
        </table>
        <div class="padb"><input class="info-btn" type="submit" value="返 回" onclick="history.back(-1)" /></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/setMainFrameUrl.js"></script> <!--仅限本地专用-->
</html>
