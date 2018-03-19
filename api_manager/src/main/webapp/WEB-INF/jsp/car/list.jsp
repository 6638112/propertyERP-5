<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%
	response.setHeader("Pragma","No-cache");    
	response.setHeader("Cache-Control","no-cache");    
	response.setDateHeader("Expires", -10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>车辆管理</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/select2.css">
		<style>
			.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
			.w180{width: 170px;}
		</style>
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">车辆查询</h2> 
		    <div class="bs-example bgebeb">
		    	<form id="carForm" method="post">
			        <table class="info-list" border="0">
			          <tr>
				            <td><div class="list-name">小区：</div></td>
				            <td>
				            	<select id="gbName" name="gbName" class="select_normal w131">
				            		<c:if test="${not empty param.gbLable and not empty param.gbName}">
				            			<option value="${param.gbName}" selected>${param.gbLable}</option>
				            		</c:if>
				            	</select>
				            </td>
				            <td><div class="list-name">楼栋：</div></td>
				            <td>
				            	<select id="buildingName" name="buildingName" class="select_normal w131">
				            		<c:if test="${not empty param.buildingLable and not empty param.buildingName}">
				            			<option value="${param.buildingName}" selected>${param.buildingLable}</option>
				            		</c:if>
				            	</select>
				            </td>
				            <td><div class="list-name">房间号：</div></td>
				            <td>
				            	<select id="roomName" name="roomName" class="select_normal w131">
				            		<c:if test="${not empty param.roomLable and not empty param.roomName}">
				            			<option value="${param.roomName}" selected>${param.roomLable}</option>
				            		</c:if>
				            	</select>
				            </td>
			          </tr>
			          <tr>
				            <td><div class="list-name">车牌：</div></td>
				            <td><input type="text" class="input_text w240 pp" id="carNum" name="carNum" value="${param.carNum}" maxlength="8"></td>
				            <td><div class="list-name">停车场：</div></td>
				            <td>
				            	<select id="park" name="park" class="select_normal w131">
				            		<c:if test="${not empty param.parkLable and not empty param.park}">
				            			<option value="${param.park}" selected>${param.parkLable}</option>
				            		</c:if>
				            	</select>
				            </td>
				            <td></td>
			          		<td>
				            	<input class="input-btn w100" type="button" value="搜索" onclick="searchCar()">
				            </td>
			          </tr>
			          <tr>
				            <td><div class="list-name">费用（元）：</div></td>
				            <td><input type="text" class="input_text w240 pp" id="fee" name="fee" value="${param.fee}" placeholder="请填写数字，可带两位小数" maxlength="10"></td>
				            <td><div class="list-name">有效期：</div></td>
				            <td>
				            	<input type="text" id="expireDate" name="expireDate" title="请选择有效期" value="${param.expireDate}" placeholder="请选择有效期" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd 23:59:59'});" class="input_text pp w180 icon_dt pp">
				            </td>
				            <td></td>
			          		<td>
				            	<input class="input-btn w100" type="button" value="插入" onclick="addCar()">
				            </td>
			          </tr>
			        </table>
			        <input type="hidden" id="parkLable" name="parkLable" value=""/>
			        <input type="hidden" id="gbLable" name="gbLable" value=""/>
			        <input type="hidden" id="buildingLable" name="buildingLable" value=""/>
			        <input type="hidden" id="roomLable" name="roomLable" value=""/>
			    </form>
		    </div>  
		    <display:table name="carEntities" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="total" >
				<display:column title="车牌" property="carNum" style="text-align:center;" headerClass="t_center"/>
				<display:column title="停车场" property="park" style="text-align:center;" headerClass="t_center"/>
				<display:column title="小区" property="xqName" style="text-align:center;" headerClass="t_center"/>
				<display:column title="楼栋" property="buildingName" style="text-align:center;" headerClass="t_center"/>
				<display:column title="房间号" property="roomName" style="text-align:center;" headerClass="t_center"/>
				<display:column title="每月费用" style="text-align:center;" headerClass="t_center">
					<fmt:formatNumber value="${row.fee}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="有效期" property="expireDate" style="text-align:center;" headerClass="t_center"/>
				<display:column title="是否小区内部车辆" style="text-align:center;" headerClass="t_center">
					<c:choose>
						<c:when test="${row.from eq 1}"><span style="color:red;">否</span></c:when>
						<c:otherwise>是</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="操作" style="text-align:center;" headerClass="t_center">
					<c:choose>
						<c:when test="${row.from eq 1}">
							<a class="blue" name="view" href="javascript:setFrom(${row.carId}, '${row.carNum}', 0)">设置为内部车辆</a>
						</c:when>
						<c:otherwise>
							<a class="blue" name="view" href="javascript:setFrom(${row.carId}, '${row.carNum}', 1)">设置为外部车辆</a>
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;
					<a class="blue" name="view" href="javascript:delCar(${row.carId}, '${row.carNum}')">删除</a>
				</display:column>
			</display:table>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/select2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function searchCar(){
			$("#parkLable").val($("#park").find("option:selected").text());
			$("#gbLable").val($("#gbName").find("option:selected").text());
			$("#buildingLable").val($("#buildingName").find("option:selected").text());
			$("#roomLable").val($("#roomName").find("option:selected").text());
			
			location.href = "${pageContext.request.contextPath}/car/index.html?"+$("#carForm").serialize();
		}
	
		function setFrom(carId, carNum, source){
			var msg = "确定要将【"+carNum+"】设置为";
			if(source==0){
				msg += "内部车吗？";
			} else {
				msg += "外部车吗？";
			}
			if(confirm(msg)){
				$.post("${pageContext.request.contextPath}/car/setFrom.html", {"carId":carId, "from":source}, function(data){
					alert(data.message);
					if(data.status == "<%=CommConstants.ResponseStatus.SUCCESS%>") {
						location = location;
					} 
				});
			}
		}
		
		function addCar(){
			var carNum       = $.trim($("#carNum").val());
			var park         = $.trim($("#park").val());
			var gbName       = $.trim($("#gbName").val());
			var buildingName = $.trim($("#buildingName").val());
			var roomName     = $.trim($("#roomName").val());
			var fee          = $.trim($("#fee").val());
			var expireDate   = $.trim($("#expireDate").val());
			
			if(carNum==""){
				alert("车牌不能为空！");
				location = "#carNum";
				return;
			}
			
			if(park==""){
				alert("请选择停车场！");
				location = "#park";
				return;
			}
			
			if(fee==""){
				alert("请填写每月费用！");
				location = "#fee";
				return;
			} else if(!/(^[1-9]\d*(\.\d{1,2})?$)|(^[0]{1}(\.\d{1,2})?$)/.test(fee)){
				alert("请填写数字，可带两位小数！");
				location = "#fee";
				return;
			}
			
			if(expireDate==""){
				alert("有效期不能为空！");
				location = "#expireDate";
				return;
			}
			
			if(gbName==""){
				alert("请选择小区！");
				location = "#gbName";
				return;
			}
			
			if(buildingName==""){
				alert("请选择楼栋！");
				location = "#buildingName";
				return;
			}
			
			if(roomName==""){
				alert("请选择房间号！");
				location = "#roomName";
				return;
			}
			
			$.post("${pageContext.request.contextPath}/car/addCar.html", {"carNum":carNum, "park":park, "roomName":roomName, "fee":fee, "expireDate":expireDate}, function(data){
				alert(data.message);
				if(data.status == "<%=CommConstants.ResponseStatus.SUCCESS%>") {
					location = location;
				} 
			});
		}
		
		function delCar(carId, carNum){
			if(confirm("您确定要删除车牌【"+carNum+"】吗？")){
				$.post("${pageContext.request.contextPath}/car/delCar.html", {"carId":carId}, function(data){
					alert(data.message);
					if(data.status == "<%=CommConstants.ResponseStatus.SUCCESS%>") {
						location = location;
					} 
				});
			}
		}
		
		function autoCompletePark(){
			$("#park").select2({
				  ajax: {
				    url: "${pageContext.request.contextPath}/car/qryGbByName.html",
				    type: "POST",
				    dataType: 'json',
				    delay: 10,
				    data: function (params) {
				      return {
				    	  gbName: params.term
				      };
				    },
				    processResults: function (data, params) {
				    	var items = data.dataValue;
				    	var options = new Array();
				    	for(var i=0; i<items.length; i++){
				    		options.push({"id":items[i].id, "text":items[i].name});
				    	}
				      return {
				        results: options,
				        pagination: {
				          more: (params.page * 30) < data.total_count
				        }
				      };
				    },
				    cache: true
				  },
				  escapeMarkup: function (markup) { return markup; },
				  minimumInputLength: 0,
				  allowClear: true,
				  placeholder: "请输入停车场名称，进行搜索"
				});
		}
		
		function autoCompleteGb(){
			$("#gbName").select2({
				  ajax: {
				    url: "${pageContext.request.contextPath}/car/qryGbByName.html",
				    type: "POST",
				    dataType: 'json',
				    delay: 10,
				    data: function (params) {
				      return {
				    	  gbName: params.term
				      };
				    },
				    processResults: function (data, params) {
				    	var items = data.dataValue;
				    	var options = new Array();
				    	for(var i=0; i<items.length; i++){
				    		options.push({"id":items[i].id, "text":items[i].name});
				    	}
				      return {
				        results: options,
				        pagination: {
				          more: (params.page * 30) < data.total_count
				        }
				      };
				    },
				    cache: true
				  },
				  escapeMarkup: function (markup) { return markup; },
				  minimumInputLength: 0,
				  placeholder: "请输入小区名称，进行搜索",
				  allowClear: true,
				  templateSelection: function(data){
					  return data.text;
				  }
				});
		}
		
		function autoCompleteBuilding(){
			$("#buildingName").select2({
				  ajax: {
				    url: "${pageContext.request.contextPath}/car/qryBuildingByName.html",
				    type: "POST",
				    dataType: 'json',
				    delay: 10,
				    data: function (params) {
				      return {
				    	  "gbId": $("#gbName").val(),
				    	  "buildingName": params.term
				      };
				    },
				    processResults: function (data, params) {
				    	var items = data.dataValue;
				    	var options = new Array();
				    	for(var i=0; i<items.length; i++){
				    		options.push({"id":items[i].id, "text":items[i].name});
				    	}
				      return {
				        results: options,
				        pagination: {
				          more: (params.page * 30) < data.total_count
				        }
				      };
				    },
				    cache: true
				  },
				  escapeMarkup: function (markup) { return markup; },
				  minimumInputLength: 0,
				  placeholder: "请输入楼栋名称，进行搜索",
				  allowClear: true,
				  templateSelection: function(data){
					  return data.text;
				  }
				});
		}
		
		function autoCompleteRoom(){
			$("#roomName").select2({
				  ajax: {
				    url: "${pageContext.request.contextPath}/car/qryRealRoomByName.html",
				    type: "POST",
				    dataType: 'json',
				    delay: 10,
				    data: function (params) {
				      return {
				    	  "buildingId": $("#buildingName").val(),
				    	  "roomName": params.term
				      };
				    },
				    processResults: function (data, params) {
				    	var items = data.dataValue;
				    	var options = new Array();
				    	for(var i=0; i<items.length; i++){
				    		options.push({"id":items[i].id, "text":items[i].name});
				    	}
				      return {
				        results: options,
				        pagination: {
				          more: (params.page * 30) < data.total_count
				        }
				      };
				    },
				    cache: true
				  },
				  escapeMarkup: function (markup) {return markup;},
				  minimumInputLength: 0,
				  allowClear: true,
				  placeholder: "请输入房间，进行搜索"
				});
		}
		
		$(function(){
			autoCompletePark();
			autoCompleteGb();
			autoCompleteBuilding();
			autoCompleteRoom();
			
			$("#gbName").change(function(){
				$("#buildingName").empty();
				$("#select2-buildingName-results, #select2-buildingName-container").remove();
				if($("#buildingName").val()==""||$("#buildingName").val()==null){
					$("#roomName").empty();
					$("#select2-roomName-results, #select2-roomName-container").remove();
					autoCompleteRoom();
				}
				autoCompleteBuilding();
			});
			
			$("#buildingName").change(function(){
				$("#roomName").empty();
				$("#select2-roomName-results, #select2-roomName-container").remove();
				autoCompleteRoom();
			});
		});
	</script>
</html>