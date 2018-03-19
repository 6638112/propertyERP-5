<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>服务商品管理</title>
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/style.css?v20161128">
	<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
	<link rel="stylesheet" type="text/css" href="../styles/displaytag-css/alternative.css">
	<link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info posrelative">
	<h2>服务商品管理</h2>
	<div class="bs-example bgebeb">
		<form action="productList.html" method="post">
			<table class="info-list" border="0">
				<tr>
					<td><div class="list-name">商品名称：</div></td>
					<td>
						<input type="text" value='${param.dpName }' name="dpName" class="input_text  pp">
					</td>
					<td><div class="list-name">所属类目：</div></td>
					<td colspan="3">
						<select class="select_normal" name="cstId" onchange="cstTypeChange()">
							<option value="0">请选择</option>
							<c:forEach items="${cstTypeList}" var="item">
								<option value="${item.id}" <c:if test="${param.cstId==item.id }"> selected="selected"</c:if>>${item.name}</option>
							</c:forEach>
						</select>
						<select class="select_normal" name="dtId" onchange="dtChange()">
							<option value="0">请选择</option>
						</select>
						<select class="select_normal" name="dt2Id">
							<option value="0">请选择</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><div class="list-name">供应商：</div></td>
					<td>
						<select class="select_normal" name="ssId">
							<option value="0">请选择</option>
							<c:forEach items="${ssList}" var="item">
								<option value="${item.id}" <c:if test="${param.ssId==item.id }"> selected="selected"</c:if>>${item.name }</option>
							</c:forEach>
						</select>
					</td>
					<td><div class="list-name">更新时间：</div></td>
					<td><input type="text" name="updTimeStart" class="input_text pp icon_dt" id="date01" title="请选择起始时间" value="${param.updTimeStart }"> 至 <input name="updTimeEnd" type="text" class="input_text pp icon_dt" id="date02" title="请选择结束时间" value="${param.updTimeEnd }"></td>
					<td><div class="list-name">状态：</div></td>
					<td>
						<select class="select_normal" name="dpStatus">
							<option value="">请选择</option>
							<option value="1" <c:if test="${param.dpStatus==1 }"> selected="selected"</c:if>>上架</option>
							<option value="2" <c:if test="${param.dpStatus==2 }"> selected="selected"</c:if>>下架</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="6" class="t_center">
						<input class="input-btn w200" type="submit" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="productAddNew.html"><input class="weak-btn small-btn w150" type="button" value="新增"></a>
					</td>
				</tr>
			</table>
		</form>
	</div> 
	<display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI=""  partialList="true" size="resultSize">
		<display:column title="更新时间" property="updTime"/>
		<display:column title="商品名称" property="prdtName"/>
		<display:column title="所属类目" property="fullTypeName"/>
		<display:column title="服务供应商" property="ssName"/>
		<display:column title="付款方式" property="payTypeName"/>
		<display:column title="状态" class="special-text">
			<c:choose>
				<c:when test="${row.dpStatus==1 }">上架</c:when>
				<c:when test="${row.dpStatus==2 }">下架</c:when>
			</c:choose>
		</display:column>
		<display:column title="操作">
			<a class="blue" href="productView.html?id=${row.dpId }">查看</a>
			<a class="blue" href="productEdit.html?id=${row.dpId }">编辑</a>
			<a class="blue" href="productCopy.html?id=${row.dpId }">复制</a>
			<c:choose>
				<c:when test="${row.dpStatus==1 }"><a class="blue updateStatus" data-dpId="${row.dpId }" data-status="${row.dpStatus }" href="#">下架</a></c:when>
				<c:when test="${row.dpStatus==2 }"><a class="blue updateStatus" data-dpId="${row.dpId }" data-status="${row.dpStatus }" href="#">上架</a></c:when>
			</c:choose>
		</display:column>
	</display:table>
</div>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.form.js?v=5"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js?v20161128"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript" src="../js/dredgeProduct.js"></script> 
<script type="text/javascript">
	//上下架
	$("a.updateStatus").click(function(){
			var $this = $(this);
			var id = $this.attr("data-dpId");
			var status = $this.attr("data-status");
			console.log("productUdpStatus.json?dpId=" + id +"&status=" + status);
			$.ajax({//使用ajax请求
				url : "productUdpStatus.json?dpId=" + id +"&status=" + status,
				async : false,
				dataType:"json",
				success : function(data) {
					console.log( "return data: "+ data);
					if(data.status !='0000')
						alert("操作失败");
					else{
						alert("操作成功");
                        var nowStatus = $this.text();
						$this.swapText("上架","下架");
                        console.log($this.parent().siblings('.special-text'));
						$this.parent().siblings('.special-text').text(nowStatus);
					}
				}
			});
	});

	var dtId =  <c:choose><c:when test="${not empty param.dtId}">${param.dtId}</c:when>	<c:otherwise>0</c:otherwise></c:choose>;
	var dt2Id =  <c:choose><c:when test="${not empty param.dt2Id}">${param.dt2Id}</c:when>	<c:otherwise>0</c:otherwise></c:choose>;
	$(document).ready(function(){
		cstTypeChange();
		dtChange();
	});
	
</script>
	
</html>
