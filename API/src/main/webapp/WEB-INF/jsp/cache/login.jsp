<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>login</title>
		<style type="text/css">
			body{background-color: #99CCCC;}
			.container{width: 100%; margin-top: 200px;;text-align: center; vertical-align: center;}
			.login_div{width: 340px;height: 220px;background-color: #6699CC; border: 1px solid #336666; border-radius:5px;margin: auto;padding: 20px 40px 20px 40px;}
			table{width: 100%;}
			.title{width: 4em;font-size: 18px;}
			input[name='username'],input[name='password']{width: 100%;height: 25px;}
			.opt_btn{width: 46px;height: 26px;}
			.btn_td{text-align: right;padding-top: 5px;}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="login_div">
				<h2>缓存刷新登陆</h2>
				<form action="${pageContext.request.contextPath}/cache2/loginCheck.json" method="post">
					<table>
						<tr>
							<td class="title">用户名：</td>
							<td><input type="text" name="username" value="" maxlength="10" autocomplete="off"/></td>
						</tr>
						<tr>
							<td class="title">密<i style="width:1em;">&nbsp;</i>码：</td>
							<td><input type="password" name="password" value="" maxlength="20" autocomplete="off"/></td>
						</tr>
						<tr>
							<td colspan="2" class="btn_td"><input type="submit" value="登陆" class="opt_btn"/></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
		if("${msg}"!=""){
			alert("${msg}");
		}
	</script>
</html>