<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电商-供应商列表-查看供应商</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>

<body>
<div class="info">
    <form class="inputform">
        <h2>查看供应商</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">基本属性</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 供应商属性：</div></td>
            <td>自营</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 供应商名称：</div></td>
            <td>${ebuySupplyMerchant.name}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 供应商地址：</div></td>
            <td>${ebuySupplyMerchant.address}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 联系人：</div></td>
            <td>${ebuySupplyMerchant.linkName}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 手机号码：</div></td>
            <td>${ebuySupplyMerchant.tel}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 配送范围：</div></td>
            <td>
              <c:if test="${ebuySupplyMerchant.type == 1}">全国</c:if>
              <c:if test="${ebuySupplyMerchant.type == 4}">城市</c:if>
            </td>
          </tr>
          <c:if test="${ebuySupplyMerchant.type == 4}">
            <tr class="city-con">
              <td><div class="list-name">已选城市：</div></td>
              <td>
                <ul class="address-list selected-box">
                  <c:forEach var="city" items="${cityList}">
                    <li class="posrelative address-selected address-selected-3" data-class="address-selected-3"><span class="address-name">${city.name}</span></li>
                  </c:forEach>
                </ul>
              </td>
            </tr>
          </c:if>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 运费收取方式：</div></td>
            <c:if test="${!hasDelivFee}">
              <td>免邮费</td>
            </c:if>
            <c:if test="${hasDelivFee}">
              <td>有条件包邮</td>
            </c:if>
          </tr>
          <c:if test="${hasDelivFee}">
            <tr class="swap-con swap-val-2">
              <td></td>
              <td>不满  ${leastOrderAmt} 元收  ${delivFee} 元</td>
            </tr>
          </c:if>
        </table>
        
        <table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">结算信息（非必填）</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">结算方式：</div></td>
            <td>
              <c:choose>
                <c:when test="${ebuySupplyMerchant.revenueType==1}">购销</c:when>
                <c:when test="${ebuySupplyMerchant.revenueType==2}">抽佣</c:when>
                <c:otherwise>
                  未知
                </c:otherwise>
              </c:choose>
            </td>
          </tr>
          <c:if test="${ebuySupplyMerchant.revenueType==1}">
            <tr class="swap-con swap-val-1">
              <td><div class="list-name">结算运费：</div></td>
              <td>不满 ${leastSettleOrderAmt} 元收 ${settleDelivFee} 元</td>
            </tr>
          </c:if>
          <c:if test="${ebuySupplyMerchant.revenueType==2}">
            <tr class="swap-con swap-val-1">
              <td><div class="list-name">佣金比例：</div></td>
              <td>${revenueRate} %</td>
            </tr>
          </c:if>

          <tr>
            <td><div class="list-name">收款账户：</div></td>
            <td>${bankAccount.accountName}</td>
          </tr>
          <tr>
            <td><div class="list-name">收款账号：</div></td>
            <td>${bankAccount.accountBank}</td>
          </tr>
          <tr>
            <td><div class="list-name">开户银行：</div></td>
            <td>${bankAccount.bankName}</td>
          </tr>
          <tr>
            <td><div class="list-name">开户银行：</div></td>
            <td>${bankAccount.bankBranch}</td>
          </tr>
          <tr>
            <td><div class="list-name">手机号码：</div></td>
            <td>${bankAccount.linkTel}</td>
          </tr>
        </table>
        <input class="info-btn save-set-prize-info-btn" type="button" onclick="history.back()" value="返回" />
        <div class="h30"></div>
    </form>
</div>

</body>
</html>
