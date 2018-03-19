<%--
	配置了模板的账单打印
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>账单打印</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/payBillPrint.css">
		<!-- <style type="text/css">
			.mtop10 li{text-align: left;}
			.t_right li{text-align:right !important;margin-right: 3em;}
			.page_break{page-break-after: always;}
			.tablePrint td {padding-top:50px; font-size: 18px;}
			.tablePrint h2 {color: #000000;}
			body {color:#000000;}
			.bordered{border-bottom: 1px solid #000000 !important;}
			.trbg{color: #dd4929;background-color: #ffffff;}
			table.info-list-02 tr td {
			    border: 1px solid #000000;
			}			
			.tableBordered td, .tableBordered th {
			    padding: 5px;
			}
			.info {
  				padding: 0;
			}
		</style> -->
	</head>
	<body style="width: 1073px; margin: 15px auto; height: auto;">
		${printTemplate}
    </body>
</html>