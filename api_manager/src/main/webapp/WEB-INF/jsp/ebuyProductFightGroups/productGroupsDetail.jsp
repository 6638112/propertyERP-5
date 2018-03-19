<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>//"/>
    <title>拼购详情</title>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/clearbox.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <form class="inputform">
        <h2>拼购详情</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <tr class="list-title">
                <td colspan="2"><div align="left" class="f14 bold">商品信息</div></td>
            </tr>
            <tr>
                <td width="20%"><div class="list-name">供应商：</div></td>
                <td>${dto.ebuySupplyMerchant.name}</td>
            </tr>
            <tr>
                <td><div class="list-name">商品ID：</div></td>
                <td>${dto.ebuyProduct.id}</td>
            </tr>
            <tr>
                <td><div class="list-name">货架分类：</div></td>
                <td>${productTypeName}</td>
            </tr>
            <tr>
                <td><div class="list-name">商品名称：</div></td>
                <td>${dto.ebuyProduct.name}</td>
            </tr>
            <tr>
                <td><div class="list-name">商品售价：</div></td>
                <td>${priceDiscount div 100}元</td>
            </tr>
        </table>

        <table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
            <tr class="list-title">
                <td colspan="2"><div align="left" class="f14 bold">拼购信息</div></td>
            </tr>
            <tr>
                <td width="20%"><div class="list-name"><span class="red">*</span> 拼购价：</div></td>
                <td>${dto.ebuyProductFightGroups.priceDiscount div 100} 元</td>
            </tr>
            <tr>
                <td><div class="list-name">起拼人数：</div></td>
                <td>${dto.ebuyProductFightGroups.fightNum}人</td>
            </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span> 开始时间：</div></td>
                <td>${fn:substring(dto.ebuyProductFightGroups.startDate, 0, 16)}</td>
            </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span> 截团时间：</div></td>
                <td>${fn:substring(dto.ebuyProductFightGroups.expireDate, 0, 16)}</td>
            </tr>
            <tr>
                <td><div class="list-name">拼购简介：</div></td>
                <td>${dto.ebuyProduct.desc}</td>
            </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span> 拼购图片：</div></td>
                <td class="item-upload-img">
                    <ul class="menu-img">
                        <li><a href="${picPath}" rel="clearbox[test1]"><img src="${picPath}" border="0"></a></li>
                    </ul>
                </td>
            </tr>
            <tr class="search-input">
                <td><div class="list-name"><span class="red">*</span> 拼购自提点：</div></td>
                <td>
                    ${fightMerchantName}
                </td>
            </tr>
        </table>

        <input id="returnGroupBuyingList" class="info-btn save-set-prize-info-btn" onclick="history.back()" type="submit" value="返回" />
        <div class="h30"></div>
    </form>
</div>

</body>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/clearbox.js"></script>
<script type="text/javascript" src="js/jquery.common.js"></script>
</html>
