<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>配置活动页面</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/clearbox.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/picbox.css">
</head>

<body>
<div class="info">
        <h2>配置活动页面</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">活动信息</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 活动名称：</div></td>
            <td>${sa.name}</td>
          </tr>
          
          <tr class="list-title"><td colspan="2">详细信息：</td></tr>
          
            <c:forEach items="${sa.sadList}" var="sad" varStatus="status">
                <tr>
                    <td width="20%"><div class="list-name"><span class="red">*</span> 图片：</div></td>
                    <td>
                    	<a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.SELF_ACTIVITY_PIC)+PathConstants.SELF_ACTIVITY_PIC%>${sad.picUrl}?x-oss-process=image/resize,m_fill,w_1000/format,jpg/interlace,1/quality,q_80" rel="clearbox[test2]">
                    		<img width="82" height="82" src='<%=OmsSysParamManager.getImageServerUrl(PathConstants.SELF_ACTIVITY_PIC)+PathConstants.SELF_ACTIVITY_PIC%>${sad.picUrl}?x-oss-process=image/resize,m_fill,w_160,h_160/format,jpg/interlace,1/quality,q_80' border="0" />
                    	</a>
                    </td>
                </tr>
                <tr>
                    <td width="20%"><div class="list-name"><span class="red">*</span> 链接类型：</div></td>
                    <td>
                        <c:if test="${sad.jumpType == 0}" >不跳转</c:if>
                        <c:if test="${sad.jumpType == 1}" >社区店商品详情-&gt; <a target="blank" class="blue" href="../ebuyProduct/ebuyProductDetail.html?productId=${paramList[status.index].id}&pageType=detail"> ${paramList[status.index].name} </a> </c:if>
                        <c:if test="${sad.jumpType == 2}" >到家服务商品详情-&gt;<a target="blank" class="blue" href="../dredge/productView.html?id=${paramList[status.index].id}">${paramList[status.index].name}</a> </c:if>
                        <c:if test="${sad.jumpType == 3}" >H5页面: ${paramList[status.index]}</c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/clearbox.js"></script>
<script type="text/javascript" src="${resourcePath}/commonjs/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${resourcePath}/commonjs/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/picbox.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.itemScreen.js"></script>
</html>
