<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修管理-报修工人管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="../css/picbox.css" />
</head>

<body>
<div class="info">
    <h2>报修工人管理</h2>
    <div class="bs-example bgebeb">
    <form action="../propertyRepair/searchRepairer.html">
        <table class="info-list" border="0">
          <tr>
            <td width="60"><div align="right">姓名：</div></td>
            <td width="100"><input type="text" name="prName" value="${param.prName }" class="input_text w100"></td>
            <td width="60"><div align="right">手机：</div></td>
            <td width="160"><input type="text" name="prPhone" value="${param.prPhone }" class="input_text w100"></td>
            <td><input class="input-btn w80" type="submit" value="搜索"></td>
            <td></td>
          </tr>
        </table>
    </form>
    </div>    
    
	<display:table class="info-list-02 mtop20" cellpadding="0" cellspacing="1"  name="resList" id="row" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize"> 
    	<display:column title="员工头像">
    		<c:if test="${empty row.headimgurl }">
	    		<ul class="menu-img-72">
	    			<li>
	    				<a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.RepairPicBasePath)+ PathConstants.RepairPicBasePath + OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.RepairerHeadImg) %>" rel="lightbox-group">
	    					<img src="<%=OmsSysParamManager.getImageServerUrl(PathConstants.RepairPicBasePath)+ PathConstants.RepairPicBasePath + OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.RepairerHeadImg) %>" border="0" style="width:90px">
	    				</a></li></ul>
    		</c:if>
    		<c:if test="${not empty row.headimgurl }">
	    		<ul class="menu-img-72">
	    			<li>
	    				<a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.RepairPicBasePath)+ PathConstants.RepairPicBasePath %>${row.headimgurl}" rel="lightbox-group">
	    				<img src="<%=OmsSysParamManager.getImageServerUrl(PathConstants.RepairPicBasePath)+ PathConstants.RepairPicBasePath %>${row.headimgurl}" border="0" style="width:90px"></a></li></ul>
    		</c:if>
    	</display:column>
    	<display:column property="name" title="姓名" />
    	<display:column property="tel" title="手机号码" />
    	<display:column property="propertyManagmentName" title="管理处名称" />
    	<display:column property="repairTypeName" title="类型" />
    	<display:column property="starLevel" title="业主评分"  format="{0,number, #0.00}"/>
    	<display:column title="操作" >
	    	<a class="blue viewRepairer" href="../propertyRepair/viewRepairer.html?repairerId=${row.id }">查看</a>
	    	&nbsp;&nbsp;<a class="blue editRepairer" href="../propertyRepair/editRepairer.html?repairerId=${row.id }">编辑</a>
	    	&nbsp;&nbsp;<a class="blue deleteRepairer" href="javascript:void(0);" repairerId="${row.id }">删除</a>
    	</display:column> 
    </display:table>
    
	<div class="padb"><div class="info-btn mtop20 left editRepair"><a href="../propertyRepair/addNewRepairer.html">新增报修人员</a></div></div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/picbox.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('a.deleteRepairer').click(function(){
			if(window.confirm("确认要删除？")){
				var $this = $(this);
				var repairerId = $this.attr('repairerId');
				$.ajax({
					url:'../propertyRepair/deleteRepairer.html',
					dataType:'text',
					data: {repairerId:repairerId},
					success:function(data, status){ 
			          if(data==="true"){
			        	  $this.parents('tr').hide();
			          }
				    },
				});
			}
		});
	});
</script>
</html>
