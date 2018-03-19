<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cnfantasia.pub.entity.Regist3rdResponse" %>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>帐号设置</title>
<link rel="stylesheet" href="css/recommendaward.css">
</head>

<body class="bggrey">
<section id="wrapBox" class="sectionBox pos-relative minheight100 bggrey">
	<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
	    <section class="divide-box"></section>
	    <div class="list-box bgwhite bordertbgrey">
	        <a class="displaybox" href="javascript:void(0)">
	            <div class="item-standard-name height36 f16 boxflex01">微信号</div>
	            <div class="boxflex01 t-right height36 f16 grey">${sessionScope.snsUser.nickname }</div>
	        </a>
	    </div>
	    <section class="divide-box"></section>
	    <div class="list-box bgwhite bordertbgrey">
            <c:choose>
            	<c:when test="${not empty param.mobile}">
			        <a class="displaybox" href="phoneNum.jsp">
			            <div class="item-standard-name height36 f16 boxflex01">手机号</div>
            			<div class="boxflex01 box-arrow t-right grey">${param.mobile }</div>
			        </a>
			        <%	
			       		Regist3rdResponse regist3rdResponse = (Regist3rdResponse) request.getSession().getAttribute("regist3rdResponse");
			        	regist3rdResponse.setMobile("" + request.getParameter("mobile"));
			        %>
            	</c:when>
            	<c:when test="${not empty sessionScope.regist3rdResponse.mobile}">
            		<a class="displaybox" href="phoneNum.jsp">
			            <div class="item-standard-name height36 f16 boxflex01">手机号</div>
		            <div class="boxflex01 box-arrow t-right height36 f16 grey">${sessionScope.regist3rdResponse.mobile }</div>
			        </a>
            	</c:when>
            	<c:otherwise>
			        <a class="displaybox" href="bindingPhoneNum_to_personalCenter.jsp">
			            <div class="item-standard-name height36 f16 boxflex01">手机号</div>
            			<div class="boxflex01 box-arrow t-right grey">未绑定</div>
			        </a>
            	</c:otherwise>
            </c:choose>
	    </div>
	</section>
</section>

</body>
</html>