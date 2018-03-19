<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>合伙人管理-合伙人资料</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
	<form class="inputform" enctype="multipart/form-data" action="../channelPartner/updateChannelPartner.html" method="post">
		<input  name="id" type='hidden' value='${cpDetail.id}' />
		<input  name="partnerType" type='hidden' value='${cpDetail.partnertype}' />
        <h2>合伙人资料</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right">邮箱：</td>
            <td><input class="input_text pp" type="text" name="email" value="${cpDetail.email }" datatype="e" nullmsg="请输入联系邮箱！" errormsg="请输入正确的邮箱格式！"/> </td>
          </tr>
          <tr>
            <td width="120" align="right">姓名：</td>
            <td><input class="input_text pp" type="text" name="name" value="${cpDetail.name }" datatype="*" nullmsg="请输入姓名！"/> </td>
          </tr>
          
          <tr>
            <td align="right">手机号码：</td>
            <td><input class="input_text pp" type="text" name="phone" value="${cpDetail.phone }" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码格式！"/> </td>
          </tr>
          <tr>
            <td align="right">身份证：</td>
            <td> <input class="input_text pp" type="text" name="idNumber" value="${cpDetail.idNumber }" maxlength="18"/></td>
          </tr>
          <tr>
            <td align="right">开户行：</td>
            <td> <input class="input_text pp" type="text" name='bankName' value="${cpDetail.bankName }"/> </td>
          </tr>
          <tr>
            <td align="right">银行卡号：</td>
            <td><input class="input_text pp" type="text" name='bankCardNo' value="${cpDetail.bankCardNo }" /> </td>
          </tr>
          <tr>
            <td width="120" align="right">公司名称：</td>
            <td><input class="input_text pp" type="text" name='companyName' value="${cpDetail.companyName }" /> </td>
          </tr>
          <tr>
            <td align="right">营业执照：</td>
            <td>
            	<div class="uploadPreview01" 
            		<c:if test="${not empty  cpDetail.businessLicense }">
            			style="background:url(<%=OmsSysParamManager.getImageServerUrl(PathConstants.ChannelPartnerPath)+PathConstants.ChannelPartnerPath %>${cpDetail.businessLicense }) no-repeat; background-size:82px 82px;"
            		</c:if>
            	>
            		<input type="file" name="photoimage" class="uploadImage01 fimg1 w100 height100" />
            	</div>
           		<span class="f12 mar-left15">注：点击图片上传营业执照</span></td>
          </tr>
        </table>
        <div class="padb mtop10">
        		<input id="saveCPInfo" class="info-btn" type="submit" value="保存" onclick="return window.confirm('确定要修改？');" />
        </div>
        <h2 class="mtop20">推荐物业公司</h2>
        
        <display:table class="info-list-02" name="channelPartnerRecommendList" id="row" >
        	<display:column title="公司名称" property="pcName"></display:column>
        	<display:column title="推荐时间" property="sys0AddTime"></display:column>
        	<display:column title="锁定到期日" property="lockDate"></display:column>
        	<display:column title="物业资质" >
        		<c:choose>
	        		<c:when test="${row.propertyQualifications==1 }">一级</c:when>
	        		<c:when test="${row.propertyQualifications==2 }">二级</c:when>
	        		<c:when test="${row.propertyQualifications==3 }">三级</c:when>
        		</c:choose>
        	</display:column>
        	<display:column title="管理小区住户数" property="residentCount"></display:column>
        	
        	<display:column title="是否已签约">
        		<c:choose>
        			<c:when test="${row.signStatus==1 }"><span class="blue alive" >是</span></c:when>
        			<c:otherwise><span class="grey asleep" >否</span></c:otherwise>
        		</c:choose>
        	</display:column>
        	<display:column title="是否上传营业执照" >
        		<c:choose>
	        		<c:when test="${empty row.iconBusinessLicense }"><span class="grey asleep" >否</span></c:when>
	        		<c:otherwise><span class="blue alive" >是</span></c:otherwise>
        		</c:choose>
        	</display:column>
        	<display:column title="签约后可获得（元）">${row.residentCount*10}</display:column>
        	<display:column title="操作" ><a href="../channelPartner/viewGroupBuildingRegister.html?cprId=${row.id }">查看管辖小区</a></display:column>
        </display:table>
        <% if(UserContext.getCurrUser().getIsadmin()==0){ //管理员不需要新增按钮 %>
        	<div class="padb mtop10">
        		<input id="createCoper" class="info-btn" type="button" value="新增推荐" onclick="addNewChannelPartnerRecommend();" />
        	</div>
        <%} %>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		 //表单验证
		 $(".inputform").Validform({
			tiptype:3,
			dragonfly:true
		}); 
	});
	
	function addNewChannelPartnerRecommend(){
		window.location.href="../channelPartner/addNewChannelPartnerRecommend.html?cpId=${cpDetail.id}";
	}

</script>
</html>
