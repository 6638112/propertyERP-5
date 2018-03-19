<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>//"/>
    <title>幸运购详情</title>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <form class="inputform" action="<%=basePath%>/flashDealActivityCfg/saveFlashDealActivityCfg.html" method="post">
        <input type="hidden" name="tEbuyProductFId" value="${ebuyProduct.id}">
        <input type="hidden" name="ebuySupplyMerchantId" value="${ebuySupplyMerchant.id}">
        <h2>幸运购详情</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <tr class="list-title">
                <td colspan="2"><div align="left" class="f14 bold">商品信息</div></td>
            </tr>
            <tr>
                <td width="20%"><div class="list-name">供应商：</div></td>
                <td>${ebuySupplyMerchant.name}</td>
            </tr>
            <tr>
                <td><div class="list-name">商品ID：</div></td>
                <td>${ebuyProduct.id}</td>
            </tr>
            <tr>
                <td><div class="list-name">货架分类：</div></td>
                <td>${ebuyProductType.typeName}</td>
            </tr>
            <tr>
                <td><div class="list-name">商品名称：</div></td>
                <td>${ebuyProduct.name}</td>
            </tr>
            <tr>
                <td><div class="list-name">商品售价：</div></td>
                <td>${priceDiscount div 100}</td>
            </tr>
        </table>

        <table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
            <tr class="list-title">
                <td colspan="2"><div align="left" class="f14 bold">幸运购信息</div></td>
            </tr>
            <tr>
                <td width="20%"><div class="list-name">标题：</div></td>
                <td>${flashDealActivityCfgParam.title}</td>
            </tr>
            <tr>
                <td><div class="list-name">开始时间：</div></td>
                <td>${flashDealActivityCfgParam.activityStartTime}</td>
            </tr>
            <tr>
                <td><div class="list-name">截团时间：</div></td>
                <td>${flashDealActivityCfgParam.activityEndTime}</td>
            </tr>

            <tr>
                <td><div class="list-name">介绍：</div></td>
                <td>${flashDealActivityCfgParam.introduceContent}</td>
            </tr>
            <tr>
                <td><div class="list-name">仅限：</div></td>
                <td>${flashDealActivityCfgParam.prizeCount}份</td>
            </tr>
            <tr>
                <td><div class="list-name">价格：</div></td>
                <td>${flashDealActivityCfgParam.activityPrice/100}元</td>
            </tr>
        </table>
        <input class="info-btn" type="button" value="返回" onclick="goBack()"/>
        <div class="h30"></div>
    </form>
</div>
</body>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
    function goBack() {
        history.go(-1);
    }
</script>
</html>
