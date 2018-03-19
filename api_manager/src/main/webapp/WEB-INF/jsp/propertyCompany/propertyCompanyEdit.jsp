<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-物业公司管理-编辑</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>物业公司管理-编辑</h2>
    <div class="distr">
        <div class="bs-example">
        	<form class="inputform" action="../propertyCompany/save.html" method="post">        	
        		<input type="hidden" name="pcId" class="input_text pp" value="${pcEntity.id }">
	        	<table class="info-list" border="0">
		          <tr>
		            <td width="140" align="right"><Span class="red">*</Span> 公司名称：</td>
		            <td><input type="text" name="pcName" class="input_text pp" value="${pcEntity.name }" datatype="*2-30" nullmsg="请填写信息！" errormsg="长度2到30个字符！"></td>
		          </tr>
		          <tr>
		            <td align="right"><Span class="red">*</Span> 公司电话：</td>
		            <td>  <input type="text" name="tel" class="input_text pp" value="${pcEntity.tel }" datatype="*2-20" nullmsg="请填写信息！" errormsg="长度在2到20个字符！"></td>
		          </tr>
		          <tr>
		            <td align="right">公司描述：</td>
		            <td>  <input type="text" name="desc" class="input_text pp" value="${pcEntity.desc }"></td>
		          </tr>
		          <tr>
		            <td align="right"> 对账银行开户行：</td>
		            <td>${pcEntity.bankName }</td>
		          </tr>
		          <tr>
		            <td align="right"> 对账银行开户名：</td>
		            <td>${pcEntity.accountName } </td>
		          </tr>
		          <tr>
		            <td align="right"> 对账银行账号：</td>
		            <td>${pcEntity.accountNo } </td>
		          </tr>
		          <tr>
		            <td width="140" align="right"></td>
		            <td><input class="info-btn" style="margin:0" type="submit" value="保 存" /></td>
		          </tr>
				</table>				 
			 </form>
      	</div>
      	
        <h2 class="mtop40">已管辖小区</h2>
        <div class="bs-example">           
	        <display:table name="gbList" id="row" class="mars info-list-02 mtop20" pagesize="20" requestURI="">
				<display:column title="序号"  sortable="true" headerClass="sortable">
					<c:out value="${row_rowNum}"/>
				</display:column>
				<display:column title="小区名称" property="name" sortable="true"/>
				<display:column title="省份" property="addressProvinceName" />
				<display:column title="城市" property="addressCityName" />
				<display:column title="区域" property="addressBlockName" />
				<display:column title="详细地址" property="addressDesc" />
			</display:table>            
      	</div>
    </div>
    
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.cxselect.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype:3
	});
});
</script>
</html>
