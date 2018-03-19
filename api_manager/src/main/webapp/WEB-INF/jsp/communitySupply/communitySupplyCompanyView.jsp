
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家-商家管理-商家详情</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jRating.jquery.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
	<form class="inputform">
        <h2>商家基本信息</h2>
        <div class="bs-example bgebeb">
            <table class="info-list" border="0">
              <tr>
                <td align="right"><Span class="red">*</Span> 商家名称：</td>
                <td>${communitySupplyCompanyEntity.name }</td>
              </tr>
              <tr>
                <td width="120" align="right"><Span class="red">*</Span> 联系电话：</td>
                <td>${communitySupplyCompanyEntity.phone }</td>
              </tr>
              <tr>
                <td align="right"><Span class="red">*</Span> 创建日期：</td>
                <td>${communitySupplyCompanyEntity.sys0AddTime}</td>
              </tr>
              <tr>
                <td align="right"><Span class="red">*</Span> 商家描述：</td>
                <td>${communitySupplyCompanyEntity.description }</td>
              </tr>
            </table>
        </div>
        
        <h2>所拥有的店铺信息</h2>
        <display:table name="communitySupplyCompanyEntity.communitySupplyList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1"  requestURI="" >
			<display:column title="店铺名称" >${row.name}</display:column>
			<display:column title="店铺地址">${row.addressDetail}</display:column>
			<display:column title="操作" >
				<a class="blue editShopInfo" href="../communitySupply/viewDetail.html?id=${row.id }">查看</a>
			</display:column>
		</display:table> 
		
        <div class="padb mtop10"><input id="sumShopList" class="info-btn" type="button" value="返 回" onclick="history.back();"/></div>
    </form>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.cxselect.js"></script>
<script type="text/javascript" src="../js/jquery.jRating.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
</html>
