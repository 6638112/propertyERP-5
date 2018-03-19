<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>公告打印</title>
		<style>
			.content{margin: 50px 20px;}
			.pageHeader{text-align: center;color: red;}
			.title{text-align: center;}
			.rqCode{margin-top: 50px;}
			.rqCode div{text-align: center;}
			.signature{margin-top: 20px;}
			.signature, .now{text-align: right;margin-right: 4em;}
			img{width: 200px;}
		</style>
	</head>
	<body>
		<div class="content">
			<div class="pageHeader"><h1>${pageHeader}</h1></div>
			<div class="title"><h2>${title}</h2></div>
			<div class="content">${content}</div>
			<div class="signature">${signature}</div>
			<div class="now">${now}</div>
			<c:if test="${rqCode eq 1}">
				<div class="rqCode">
					<div>
						<img src="http://public.image.jiefangqu.com/notice/jfq_app_down.png"/>
					</div>
				</div>
			</c:if>
		</div>
	</body>
	<script type="text/javascript">
		window.print();
	</script>
</html>