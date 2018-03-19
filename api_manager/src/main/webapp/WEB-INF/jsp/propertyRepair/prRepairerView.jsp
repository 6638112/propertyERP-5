<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修管理-查看维修工人</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/picbox.css">
</head>

<body>
<div class="info">
    <h2>查看维修工人</h2>
	<form class="inputform" method="post" action="../propertyRepair/saveRepairer.html" enctype="multipart/form-data">
		<c:if test="${not empty gbId }">
			<input type="hidden" name="gbId" value="${gbId}" /> 
		</c:if>
		<c:if test="${not empty propertyRepairer }">
			<input type="hidden" name="pmId" value="${propertyRepairer.tPropertyManagementFId}" /> 
			<input type="hidden" name="propertyRepairerId" value="${propertyRepairer.id}" /> 
		</c:if>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="120" align="right"><Span class="red">*</Span> 员工姓名：</td>
            <td><input type="text" name="repairerName" class="input_text" value="${propertyRepairer.name}"  readonly="readonly" /></td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 联系手机：</td>
            <td><input type="text" name="repairerTel" class="input_text" value="${propertyRepairer.tel}" readonly="readonly" /></td>
          </tr>
          <tr>
            <td align="right">大头照：</td>
            <td>
	            <ul class="menu-img">
	            	<li><a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.RepairPicBasePath) + PathConstants.RepairPicBasePath %>${propertyRepairer.headimgurl}" rel="lightbox-group"><img src="<%=OmsSysParamManager.getImageServerUrl(PathConstants.RepairPicBasePath) + PathConstants.RepairPicBasePath %>${propertyRepairer.headimgurl}" border="0" /></a></li>
	            </ul>
            </td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 身份证号：</td>
            <td><input type="text" name="idNumber" class="input_text pp"  datatype="*" nullmsg="请填写身份证号！" value="${propertyRepairer.idNo}" maxlength="18"></td>
          </tr>
          <tr>
            <td align="right">身份证正面：</td>
            <td>
	            <ul class="menu-img">
	            	<li><a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.RepairPicBasePath) + PathConstants.RepairPicBasePath %>${propertyRepairer.idNumberPic1}" rel="lightbox-group"><img src="<%=OmsSysParamManager.getImageServerUrl(PathConstants.RepairPicBasePath) + PathConstants.RepairPicBasePath %>${propertyRepairer.idNumberPic1}" border="0" /></a></li>
	            </ul>
	        </td>
          </tr>
          <tr>
            <td align="right">身份证反面：</td>
            <td>
	            <ul class="menu-img">
	            	<li><a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.RepairPicBasePath) + PathConstants.RepairPicBasePath %>${propertyRepairer.idNumberPic2}" rel="lightbox-group"><img src="<%=OmsSysParamManager.getImageServerUrl(PathConstants.RepairPicBasePath) + PathConstants.RepairPicBasePath %>${propertyRepairer.idNumberPic2}" border="0" /></a></li>
	            </ul>
	        </td>
          </tr>
          <tr>
            <td align="right"><div align="right"><Span class="red">*</Span> 负责报修类型：</div></td>
            <td><select name="repairType" class="select_normal" readonly="readonly" >
                    	<option value="">请选择该员工擅长类型</option>
	            		<c:forEach items="${prTypeList}" var="prType">
		                    <option value="${prType.id }"  <c:if test="${prType.id == propertyRepairer.tPropertyRepairTypeFId }"> selected="selected" </c:if> >${prType.name }——${prType.pmName } </option>
	            		</c:forEach>
                    </select></td>
          </tr>
        </table>
        <div class="padb mtop10"><!-- <input class="info-btn manageRepair" type="submit" value="保 存" /> --></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/picbox.js"></script>
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
