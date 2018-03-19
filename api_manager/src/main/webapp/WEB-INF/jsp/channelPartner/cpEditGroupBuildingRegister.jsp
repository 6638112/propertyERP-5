<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>合伙人管理-合伙人资料-编辑推荐小区</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
	<form class="inputform" action="../channelPartner/updateGroupBuildingRegister.html">
		<input type="hidden" value="${cprId }" name="cprId" />
		<input type="hidden" value="${gbr.id }" name="gbrId" />
		
        <h2>编辑推荐小区</h2>

			<table class="info-list-02" border="0" cellspacing="1"
				cellpadding="0">
				<tr>
					<td align="right">小区所在地：</td>
					<td><select id="province"
						onchange="onSelectChange(this,'city');"
						class="province select_normal" data-first-title="选择省" title="选择省">
							<option value="-1">选择省</option>
							<c:forEach items="${pcbList}" var="pcb">
								<c:choose>
									<c:when test="${pcb.id==pId}">
										<option value="${pcb.id}" selected="selected">${pcb.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${pcb.id}">${pcb.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select> <select id="city" onchange="onSelectChange(this,'block');"
						class="city select_normal" data-first-title="选择市">
							<option value="${cId}" selected="selected">${cName }</option>
					</select> <select id="block" name="abId" class="area select_normal"
						data-first-title="选择区">
							<option value="${abId }" selected="selected">${abName }</option>
					</select></td>
				</tr>

				<tr>
					<td align="right"><Span class="red">*</Span> 小区详细地址：</td>
					<td><input name="gbAddrDesc" class="input_text pp" type="text"
						value="${gbr.addressDesc }"></td>
				</tr>

				<tr>
					<td align="right"><Span class="red">*</Span> 小区名称：</td>
					<td><input name="gbName" class="input_text pp" type="text"
						value="${gbr.name }"></td>
				</tr>
			</table>

			<div class="padb mtop10"><input class="info-btn coperView" type="submit" value="提 交"  /></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.cxselect.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" >

//省市切换
function onSelectChange(obj,toSelId){
	setSelect(obj.value,toSelId);
}

//省市切换详情逻辑处理
function setSelect(fromSelVal,toSelId){
	document.getElementById(toSelId).innerHTML="";//清空之前的选项
	if(toSelId === "city"){//选择省，更新市
		jQuery.ajax({
			  url: "../propertyCompany/getCityList.html",
			  cache: false,
			  dataType:"json",
			  async:false,
			  data:"apId="+fromSelVal,
			  success: function(data){
			    $.each(JSON.parse(data), function(i, item) {
			    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
			    });
			  }
			});
		$("#city").val(1);
		$("#block").empty();
	}else {
		jQuery.ajax({//选择市，更新区
			  url: "../propertyCompany/getBlockList.html",
			  cache: false,
			  dataType:"json",
			  data:"acId="+fromSelVal,
			  success: function(data){
			    $.each(JSON.parse(data), function(i, item) {
			    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
			    });
			  }
			});
	}	
}

</script>
</html>
