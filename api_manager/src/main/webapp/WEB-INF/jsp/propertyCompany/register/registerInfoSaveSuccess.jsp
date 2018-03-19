<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>物业公司 ——注册完成中转页</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body class="resetbody">
<div class="header resetheader">
	<div class="left">解放区<span> 解放后，一切都会有!</span></div>
    <div class="right">欢迎来到解放区运营管理系统！</div>
</div>
<div class="main posrelative resetmain">
    <div class="info w960">
        <form class="inputform">
        <h2>账号申请</h2>
        <div class="distr">
            <div class="bs-example">
                <div class="add-user">
                    <table border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td align="center">您的申请已成功提交！解放区工作人员将在3个工作日内把审核结果发送至您的手机及邮箱，请注意查收，谢谢！</td>
                      </tr>
                    </table>
                </div>
            </div>
        </div>
        </form>
    </div>
</div>
<div class="footer rela-foot">
    <p>粤ICP备14059299号<br />Copyright <span>&copy;</span> 2014 深圳前海邻里乐科技服务有限公司 All rights reserved.</p>
</div>

</body>
</html>
