<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>维修单现场照片</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css"  href="../css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="../styles/displaytag-css/alternative.css">
<link rel="stylesheet" type="text/css" href="../css/select2.css">
<style type="text/css">	
	.content{
		width:800px;
		margin: 0 auto;
	}	
	
	li{
		margin-top:20px;
	}
</style>
</head>

<body>
	<div class="content">
		<ul>
			<c:forEach items="${resList}" var="item">
				<li><img style="width: 100%;" src="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl)%>/dredgePic/${item.picUrl}"></img></li>
			</c:forEach>
		</ul>
	</div>
</html>
