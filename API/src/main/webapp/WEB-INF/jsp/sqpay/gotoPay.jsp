<%@page import="com.cnfantasia.server.common.httpcllient.https.SqMD5Util"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String currPath = basePath+"/signalStyle/sqpay/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>支付跳转页面</title>
    <base href="<%=currPath%>" >
    <meta charset="utf-8">
    <meta http-equiv="cleartype" content="on">
	<meta name="HandheldFriendly" content="True">
	<meta name="MobileOptimized" content="320">
	<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
	<link rel="stylesheet" href="css/pay.common.css">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <body  class="bggrey" onload="document.formId.submit();">
  	<section class="sectionBox welcome-img p00">
	    <div class="change-password-box">
	    	<span><img class="pay-loading" src="images/loading01.gif" width="10" /><br>支付跳转中…</span>
	    </div>
	</section>
    <form name="formId" id="formId" action = "https://www.95epay.cn:443/sslpayment.action" method = "post">
		<input	type="hidden" name="terminalKind" 	value="${terminalKind}"/><br/>
		<input	type="hidden" name="MerNo" 			value="${MerNo}"/><br/>
		<input 	type="hidden" name="BillNo" 		value="${BillNo}"/><br/>
		<input 	type="hidden" name="Amount" 		value="${Amount}"/><br/>
		<input 	type="hidden" name="PaymentType" 	value="${PaymentType}"/><br/>
		<input 	type="hidden" name="PayType" 		value="${PayType}"><br/>
		<input 	type="hidden" name="ReturnURL" 		value="${ReturnURL}"/><br/>
		<input 	type="hidden" name="NotifyURL" 		value="${NotifyURL}"/><br/>
		<input 	type="hidden" name="MD5info" 		value="${MD5info}"/><br/>
		<input 	type="hidden" name="MerRemark" 		value="${MerRemark}"/><br/>
		<input 	type="hidden" name="products" 		value="${products}"/><br/>
		<!-- <p align ="center"><input id="commit" type="submit" name="b1" value="去支付"/></p> -->
   </form>
  </body>
</html>
