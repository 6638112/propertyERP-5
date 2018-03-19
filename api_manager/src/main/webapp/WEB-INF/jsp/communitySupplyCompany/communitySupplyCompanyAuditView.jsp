
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-店铺管理-店铺详情</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
	<form class="inputform">
        <h2>店铺基本信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right"><Span class="red">*</Span> 店铺类别：</td>
            <td>${entity.supplyType }</td>
          </tr>
          <tr>
            <td width="120" align="right"><Span class="red">*</Span> 店铺名称：</td>
            <td>${entity.supplyName }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 店铺所在地：</td>
            <td>${ entity.provinceName}${entity.cityName}${entity.blockName}</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 详细地址：</td>
            <td>${entity.editAddressDetail }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 店铺电话：</td>
            <td>
                <div>
					<c:forEach items="${entity.csContects }" varStatus="i" var="contect">
		            	电话${i.index+1}:${contect.contectInfo }&nbsp;&nbsp;
		            </c:forEach>
	            </div>
	        </td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 认领人：</td>
            <td>${entity.belongUserName }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 认领人联系人电话：</td>
            <td>${entity.belongUserPhone }</td>
          </tr>
          <tr>
            <td align="right">店铺证件：</td>
            <td>
            	<c:forEach items="${comPicURL }" var="picurl">
             	<ul class="menu-img"><li><a href="<%= OmsSysParamManager.getImageServerUrl("/communityCompanyListPic/") %>/communityCompanyListPic/${picurl.picUrl}" rel="clearbox[test1]"><img src="<%= OmsSysParamManager.getImageServerUrl("/communityCompanyListPic/") %>/communityCompanyListPic/${picurl.picUrl}" border="0" /></a></li></ul>
            	</c:forEach>
            </td>
          </tr>
          <tr>
            <td align="right">店铺图标：</td>
            <td>
            	<c:forEach items="${picURL }" var="picurl">
             	<ul class="menu-img"><li><a href="<%= OmsSysParamManager.getImageServerUrl("/communitySupplyPic/") %>/communitySupplyPic/${picurl.picUrl}" rel="clearbox[test1]"><img src="<%= OmsSysParamManager.getImageServerUrl("/communitySupplyPic/") %>/communitySupplyPic/${picurl.picUrl}" border="0" /></a></li></ul>
            	</c:forEach>
            </td>
          </tr>
        </table>
        <div class="padb mtop10"><input id="sumShopList" class="info-btn" type="button" value="返 回" onclick="history.back();"/></div>
    </form>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
</html>
