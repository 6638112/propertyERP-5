<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	response.setHeader("Pragma","No-cache");    
	response.setHeader("Cache-Control","no-cache");    
	response.setDateHeader("Expires", -10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>托收信息维护</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<style>
			td{border:1px solid #eeeeee;}
		</style>
	</head>
	<body>
		<div class="info">
			<div style="color:orange;">
				托收相关信息维护：
				1、<a style="color:orange;" href="${pageContext.request.contextPath}/bankCollection/ppbankCollectionInfo.html">导入业主托收相关信息</a>---->
				2、<a style="color:orange;" href="#bcBankInfoListH">维护各小区托收银行</a>---->
				3、<a style="color:orange;" href="#bcDateH">确定银行托收时间</a>
			</div>
		    <h2 class="mtop20">托收信息维护</h2> 
		    <div class="bs-example bgebeb">
		        <table class="info-list" border="0">
		          <tr>
			            <td style="width: 130px;text-align: center;">业主托收信息维护</td>
			            <td style="text-align: left;padding-left: 5px;"><a class="blue" href="${pageContext.request.contextPath}/bankCollection/ppbankCollectionInfo.html">业主托收信息维护</a></td>
		          </tr>
		        </table>
		    </div>  
		    
		    <h2 class="mtop20" id="bcBankInfoListH">托收银行维护</h2>
		    <table class="mars info-list-02 mtop20">
				<thead>
					<tr class="title t_center">
						<td>编号</td>
						<td>托收范围</td>
						<td>包含小区</td>
						<td>托收银行</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${bcBankInfoList}" var="row" varStatus="i">
					<tr class="t_center">
						<td>${row.no}</td>
						<td>
							<c:choose>
								<c:when test="${row.collectionRange eq 1}">小区</c:when>
								<c:otherwise>业主</c:otherwise>
							</c:choose>
						</td>
						<td>${row.gbNames}</td>
						<td>${row.bankName}</td>
						<td>
							<a href="${pageContext.request.contextPath}/bankCollection/goBankCollectionInfoEditPage.html?pcbciId=${row.pcbciId}" class="blue">修改</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<input class="input-btn mtop10"type="button" onclick="add()" value="添加"/>
			
			<h2 class="mtop20" id="bcDateH">托收时间维护</h2> 
		    <div class="bs-example bgebeb">
		        <table class="info-list" border="0">
		          <tr>
			            <td style="width: 130px;text-align: center;">托收时间</td>
			            <td style="text-align: left;padding-left: 5px;">
			           		<input type="text" id="bcDate1" class="input_text pp w80" value="${(bcDateSize ge 1)?bankCollectionDates[0]:''}" maxlength="2" placeholder="1-31内的整数"/>&nbsp;&nbsp;、
			           		<input type="text" id="bcDate2" class="input_text pp w80" value="${(bcDateSize le 2)?bankCollectionDates[1]:''}" maxlength="2" placeholder="1-31内的整数"/>
			           		 日
			            </td>
		          </tr>
		        </table>
		        <input class="info-btn mtop10" type="button" onclick="save()" value="保存"/>
		    </div>  
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
		function add(){
			location.href = "${pageContext.request.contextPath}/bankCollection/goBankCollectionInfoEditPage.html";
		}
		
		function save(){
			var bcDate1 = $.trim($("#bcDate1").val());
			var bcDate2 = $.trim($("#bcDate2").val());
			
			if(bcDate1=="" && bcDate2==""){
				alert("托收时间至少填一个！");
			} else if((bcDate1!="" && (bcDate1<1 || bcDate1>31)) || (bcDate2!="" && (bcDate2<1 || bcDate2>31))){
				alert("托收时间不符合规范，范围应为【1-31】！");
			} else if(!((bcDate1=="" || /^\d+$/.test(bcDate1)) && (bcDate2=="" || /^\d+$/.test(bcDate2)))){
				alert("请输入整数！");
			} else {
				if(bcDate1==bcDate2){
					alert("请勿填入相同的日期！");
				} else {
					$.post("${pageContext.request.contextPath}/bankCollection/saveBankCollectionDate.html", {"bcDate1":bcDate1, "bcDate2":bcDate2}, function(data){
						alert(data.message);
					});
				}
			}
		}
	</script>
</html>