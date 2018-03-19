
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>申请高级合作模式</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/jRating.jquery.css" />
<link rel="stylesheet" type="text/css" href="../css/picbox.css" />

</head>

<body>
<div class="info">
    <form class="inputform" method="post" action="../propertyCompany/auditApply.html">
    	<input type="hidden" name="companyId" value="${entity.id }" />
    	<input type="hidden" name="adminId" value="${entity.adminId }" />
        <h2>公司基本信息</h2>
        <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right"><span class="grey">物业公司名称：</span></td>
            <td>${entity.name }</td>
          </tr>
          <tr>
            <td align="right"><span class="grey">公司电话：</span></td>
            <td>${entity.tel }</td>
          </tr>
          <tr>
            <td width="120" align="right"><span class="grey">联系人：</span></td>
            <td>${entity.linkman }</td>
          </tr>
          <tr>
            <td align="right"><span class="grey">联系手机：</span></td>
            <td>${entity.mobilePhone }</td>
          </tr>
          <tr>
            <td align="right"><span class="grey">申请合作模式：</span></td>
            <td>
				<c:choose>
	            	<c:when test="${entity.isAllCooperation!=null}">
	            		<span class="left mtop2">全面合作</span>
	            	</c:when>
	            	<c:when test="${entity.isHighCooperation!=null}"><span class="left mtop2">高级合作</span></c:when>
	            	<c:otherwise>
				　　       <span class="left mtop2">基础合作</span>
				　　</c:otherwise>
				</c:choose> 
			</td>
          </tr>
          <tr>
	        <td align="right"><span class="grey">营业执照：</span></td>
	        <td>
	        	<ul class="menu-img"><li><a href="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${entity.photoBusinessLicense }" rel="lightbox"><img src="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${entity.photoBusinessLicense }" border="0" /></a></li></ul>
	        </td>
	      </tr>
	      <tr>
	        <td align="right"><span class="grey">物业资质证书：</span></td>
	        <td>
	        	<ul class="menu-img"><li><a href="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${entity.photoCredentials }" rel="lightbox"><img src="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${entity.photoCredentials }" border="0" /></a></li></ul>
	        </td>
	      </tr>
        </table>
        <c:if test="${opType==1 }">
        <h2 class="mtop40">审核</h2>
	    <div class="bs-example">
	        <table class="info-list" border="0">
	          <tr>
	            <td width="90" align="right">审核结果：</td>
	            <td colspan="5">
	            	<select class="select_normal w131 select-check" name="auditResult">
                        <option value="pass">通过</option>
                        <option value="fail">不通过</option>
                    </select>
	            </td>
	          </tr>
	          <tr class="dsn">
	            <td align="right">原因：</td>
	            <td colspan="5"><textarea name="desc" class="textareas txt02" name="desc" cols="" rows="5" datatype="*4-100" nullmsg="审核结果为“不通过”时需填写原因！" errormsg="原因范围为4到100个字！"></textarea></td>
	          </tr>
	        </table>
	    </div>
	    <div class="padb"><input id="sumCheckCoperationApply" class="info-btn" type="submit" value="提 交" /></div>
	    </c:if>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		dragonfly:true
	});
});
</script>
<script type="text/javascript" src="../js/picbox.js?v20150215"></script>
</html>
