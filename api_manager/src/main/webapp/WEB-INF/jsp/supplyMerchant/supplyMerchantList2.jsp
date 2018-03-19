<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供应商列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>供应商列表</h2>
    <form action="<%=basePath%>/supplyMerchant/supplyMerchantList2.html" method="post">
      <div class="bs-example bgebeb">
        <table class="info-list" border="0">
          <tr>
            <td><div class="list-name">城市：</div></td>
            <td><input type="text" name="cityName" class="input_text w120 pp" value="${param.cityName}"></td>
            <td><div class="list-name">联系人：</div></td>
            <td><input type="text" name="linkName" class="input_text w120 pp" value="${param.linkName}"></td>
            <td><div class="list-name">合作模式：</div></td>
            <td>
              <select class="select_normal" name="revenueType">
                <option value="">全部</option>
                <option value="1" <c:if test="${param.revenueType==1}">selected</c:if>>购销</option>
                <option value="2" <c:if test="${param.revenueType==2}">selected</c:if>>抽佣</option>
              </select>
            </td>
          </tr>
          <tr>
            <td><div class="list-name">供应商名称：</div></td>
            <td><input type="text" name="supplierName" class="input_text w120 pp" value="${param.supplierName}"></td>
            <td><div class="list-name">新增时间：</div></td>
            <td><input type="text" name="addTime" class="input_text icon_dt" id="date01" title="请选择新增时间" value="${param.addTime}"></td>
          </tr>
          <tr>
            <td colspan="8" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
          </tr>
        </table>
      </div>
    </form>
  <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="total" >
    <display:column title="新增时间" property="sys0AddTime" />
    <display:column title="地址" property="address" />
    <display:column title="供应商名称" property="name" />
    <display:column title="联系人" property="linkName" />
    <display:column title="联系方式" property="tel" />
    <display:column title="添加人" property="addMan" />
    <display:column title="更新人" property="updateMan" />
    <display:column title="合作模式">
      <c:choose>
        <c:when test="${row.revenueType==1}">购销</c:when>
        <c:when test="${row.revenueType==2}">抽佣</c:when>
        <c:otherwise>
          未知
        </c:otherwise>
      </c:choose>
    </display:column>

    <display:column title="操作">
      <a class="blue viewTicket" href="<%=basePath%>/supplyMerchant/type1Detail.html?supplyMerchantId=${row.id}">查看</a>
      <a class="blue viewTicket" href="<%=basePath%>/supplyMerchant/type1Update.html?supplyMerchantId=${row.id}">编辑</a>
    </display:column>
  </display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
</html>
