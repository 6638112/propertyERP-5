<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>合伙人管理-代理商列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>代理商列表</h2>
    <div class="distr">
        <div class="bs-example">
            <ul class="ranking">
            	<form action="../channelPartner/searchCPCompany.html" method="post">
	            	<li>姓名： <input type="text" name="name" class="input_text pp" value="${name}" palceholder="请输入姓名"></li>
	            	<li>联系号码： <input type="text" name="phone" value="${phone}" class="input_text pp" palceholder="请输入联系号码"></li>
	            	<li>账号： <input type="text" name="omsUserName" value="${omsUserName}" class="input_text pp" palceholder="请输入账号"></li>
	                <li><input class="input-btn" type="submit" value="搜索"></li>
            	</form>
            </ul>

			<display:table name="resList" id="row" class="info-list-02 mtop20"	pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
				<display:column title="账号" property="omsUserName" />
				<display:column title="姓名" property="name" />
				<display:column title='注册时间' property="sys0AddTime"/>
				<display:column title="联系方式" property="phone" />
				<display:column title="邮箱" property="email"/>
				<display:column title="操作">
					<a class="blue editOwnerInfo" href="../channelPartner/view.html?id=${row.id }">查看详细</a>
				</display:column>
			</display:table>
      	</div>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
</html>
