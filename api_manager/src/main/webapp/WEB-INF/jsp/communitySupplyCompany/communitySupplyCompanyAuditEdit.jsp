<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家管理-店铺编辑审核</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
	<form class="inputform" action="../communitySupplyCompany/auditEdit.html">
		<input name="id" type="hidden" value="${entity.tmpId}" />
		<input name="supplyId" type="hidden" value="${entity.id}" />
		<input name="mobile" type="hidden" value="${entity.belongUserPhone}" />
        <table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
          <thead>
          <tr class="title">
            <th width="150"><div align="left">审核类型</div></th>
            <th><div align="left">提交时间</div></th>
          </tr>
          </thead>
          <tr>
            <td>商家编辑</td>
            <td>${entity.sys0AddTime }</td>
          </tr>
        </table>
        <h2 class="mtop20">店铺审核</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right"><Span class="red">*</Span> 店铺类别：</td>
            <td>${entity.supplyType }</td>
          </tr>
          <tr>
            <td width="120" align="right"><Span class="red">*</Span> 店铺名称：</td>
            <td>${entity.supplyName }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 店铺所在地：</td>
            <td>${entity.provinceName }${entity.cityName }${entity.blockName }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 详细地址：</td>
            <td>${entity.editAddressDetail }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 店铺电话：</td>
            <td>
                <div>
					<c:forEach items="${entity.csContects }" varStatus="i" var="contect">
		            	电话${i.index+1}:${contect.contectInfo }&nbsp;&nbsp;
		            </c:forEach>
	            </div>
	        </td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 认领人：</td>
            <td>${entity.belongUserName }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 认领人联系人电话：</td>
            <td>${entity.belongUserPhone }</td>
          </tr>
          <tr>
            <td align="right">店铺证件：</td>
            <td>
            	<c:forEach items="${comPicURL }" var="picurl">
             	<ul class="menu-img"><li><a href="<%= OmsSysParamManager.getImageServerUrl("/communityCompanyListPic/") %>/communityCompanyListPic/${picurl.picUrl}" rel="clearbox[test1]"><img src="<%= OmsSysParamManager.getImageServerUrl("/communityCompanyListPic/") %>/communityCompanyListPic/${picurl.picUrl}" border="0" /></a></li></ul>
            	</c:forEach>
            </td>
          </tr>
          <tr>
            <td align="right">店铺图标：</td>
            <td>
            	<c:forEach items="${picURL }" var="picurl">
             	<ul class="menu-img"><li><a href="<%= OmsSysParamManager.getImageServerUrl("/communitySupplyPic/") %>/communitySupplyPic/${picurl.picUrl}" rel="clearbox[test1]"><img src="<%= OmsSysParamManager.getImageServerUrl("/communitySupplyPic/") %>/communitySupplyPic/${picurl.picUrl}" border="0" /></a></li></ul>
            	</c:forEach>
            </td>
          </tr>
        </table>
        <h2 class="mtop20">审核</h2>
        <div class="bs-example">
            <table class="info-list" border="0">
              <tr>
                <td width="90" align="right"><span class="grey">审核结果：</span></td>
                <td colspan="5">
                	<select class="select_normal w131 select-check" name="auditResult">
	                    <option value="pass">通过</option>
	                    <option value="notpass">不通过</option>
                     </select></td>
              </tr>
              <tr class="dsn">
                <td align="right"><span class="grey">原因：</span></td>
                <td colspan="5"><textarea class="textareas txt02" name="notPassReason" cols="" rows="5" datatype="*4-100" nullmsg="审核结果为“不通过”时需填写原因！" errormsg="原因范围为4到100个字！"></textarea></td>
              </tr>
            </table>
        </div>
        <div class="padb mtop10"><input id="sumAccount" class="info-btn" type="submit" value="提 交" /></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	 $(".inputform").Validform({
		tiptype:3,
		ignoreHidden:true,
	}); 
});
</script>

</html>
