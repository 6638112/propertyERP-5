
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-商家申请-商家详情</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
	<form class="inputform" method="post" action="../communitySupplyTmp/initSupplyTmp.html">
		<input type="hidden" name="supplyId" value="${entity.id }" />
        <h2>商家基本信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right"><Span class="red">*</Span> 商家类别：</td>
            <td>${entity.supplyType }</td>
          </tr>
          <tr>
            <td width="120" align="right"><Span class="red">*</Span> 商家名称：</td>
            <td>${entity.supplyName }</td>
          </tr>
          <tr>
            <td width="120" align="right"><Span class="red">*</Span> 商家电话：</td>
            <td><div>
					<c:forEach items="${contects }" varStatus="i" var="contect">
		            	电话${i.index+1}:${contect.contectInfo }&nbsp;&nbsp;
		            </c:forEach>
	            </div>
	        </td>
          </tr>
          <%-- <tr>
            <td align="right"><Span class="red">*</Span>联系人：</td>
            <td>${entity.companyName }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 店主手机号：</td>
            <td>${entity.companyPhone }</td>
          </tr> --%>
          <tr>
            <td align="right"><Span class="red">*</Span> 商家所在地：</td>
            <td>${ entity.apName}${entity.acName}${entity.abName}</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 详细地址：</td>
            <td>${entity.addressDetail }</td>
          </tr>
          <tr>
            <td align="right">商家图片：</td>
            <td>
            	<c:forEach items="${bigPicURL }" var="picurl">
             	<ul class="menu-img"><li><a href="<%= OmsSysParamManager.getImageServerUrl("/communitySupplyPic/") %>/communitySupplyPic/${picurl.picUrl}" rel="clearbox[test1]"><img src="<%= OmsSysParamManager.getImageServerUrl("/communitySupplyPic/") %>/communitySupplyPic/${picurl.picUrl}" border="0" /></a></li></ul>
            	</c:forEach>
            </td>
          </tr>
        </table>
        <h2 class="mtop20">服务小区</h2>
        <display:table name="gbList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
        	<display:column title="小区名字">${row.name}</display:column>
        	<display:column title="所在省">${row.addressProvinceName}</display:column>
        	<display:column title="所在市">${row.addressCityName}</display:column>
        	<display:column title="区">${row.addressBlockName}</display:column>
        	<display:column title="小区详细地址">${row.addressDesc}</display:column>
        </display:table>
        <h2 class="mtop20">店铺审核记录</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <tr>
              <td width="120" align="right"> 审核结果：</td>
              <td>
				<c:choose>
					<c:when test="${entity.auditStatus ==1 }">待审核</c:when>
					<c:when test="${entity.auditStatus ==2 }">审核通过</c:when>
					<c:when test="${entity.auditStatus ==3 }">审核失败</c:when>
				</c:choose>
			  </td>
            </tr>
	        <c:if test="${entity.auditStatus!=null && entity.auditStatus == 3 }">
	          <tr>
	            <td align="right"> 原因：</td>
	            <td>${entity.auditDesc}</td>
	          </tr>
	        </c:if>
	    </table>
        <div class="padb mtop10">
        	<input id="sumShopList" class="info-btn" type="button" value="返 回" onclick="history.back();"/>
        	<c:if test="${entity.auditStatus == 3 }">
        		<input id="sumShopList" class="info-btn" type="submit" value="编 辑" onclick="return confirm('您确定要编辑吗?');"/>
        	</c:if>
        </div>
    </form>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
</html>
