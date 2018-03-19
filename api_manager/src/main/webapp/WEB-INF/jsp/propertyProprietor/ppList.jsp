<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房号业主信息管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body onload="afterOnload11();">
<div class="info">
    <h2>房号业主信息管理</h2>
    <div class="bs-example bgebeb">
    <form name="searchForm" action="../propertyProprietor/search.html" method="post">
        <table class="info-list" border="0">
          <tr>
		   <td width=""><div class="list-name">小区名称：</div></td>
		   <td>
			  <select id="groupBuiliding" name="gbId" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
				  <option value="">选择小区</option>
			  </select>
		   </td>
			  <td width="90"><div class="list-name">楼栋号：</div></td>
			  <td width="120"><input type="text" class="input_text w120 pp"  name="bName" value="${param.bName}"></td>
			  <td width="90"><div class="list-name">单元号：</div></td>
			  <td width="120"><input type="text" class="input_text w120 pp"  name="rrUnitName" value="${param.rrUnitName}"></td>
			  <td width="90"><div class="list-name" align="right">房间号：</div></td>
			  <td width="130"><input type="text" class="input_text w120 pp"  name="rrRoomNum" value="${param.rrRoomNum}"></td>
			  <td width="90"><div class="list-name">业主姓名：</div></td>
			  <td width="120"><input type="text" class="input_text w120 pp"  name="ppName" value="${param.ppName}"></td>
		  </tr>
		<tr>
			<td width="90"><div class="list-name">业主联系方式：</div></td>
			<td width="120"><input type="text" class="input_text w120 pp"  name="ppTelPhone" value="${param.ppTelPhone}"></td>
			<td width="90"><div class="list-name">出售情况：</div></td>
			<td width="120">
				<select name="saleStatus" class="select_normal" >
					<option value="-1">全部</option>
					<option value="1" <c:if test="${param.saleStatus == 1}"> selected </c:if>>未出售</option>
					<option value="2" <c:if test="${param.saleStatus == 2}"> selected </c:if> >已出售</option>
					<option value="3" <c:if test="${param.saleStatus == 3}"> selected </c:if>>已交房</option>
				</select>
			</td>
			<td width="90"><div class="list-name">居住情况：</div></td>
			<td width="120">
				<select name="livingStatus" class="select_normal">
					<option value="-1">全部</option>
					<option value="1" <c:if test="${param.livingStatus == 1}"> selected </c:if>>未入住</option>
					<option value="2" <c:if test="${param.livingStatus == 2}"> selected </c:if> >已入住</option>
				</select>
			</td>
			<td width="90"><div class="list-name">出租情况：</div></td>
			<td width="120">
				<select name="leaseStatus" class="select_normal">
					<option value="-1">全部</option>
					<option value="1" <c:if test="${param.leaseStatus == 1}"> selected </c:if>>未出租</option>
					<option value="2" <c:if test="${param.leaseStatus == 2}"> selected </c:if> >已出租</option>
				</select>
			</td>
            <td></td>
            <td>
	            <input class="input-btn w80" type="submit" value="查询">
	            <input class="input-btn w80" type="button" onclick="$(window.parent.document).find('#mainFrame').attr('src', '../propertyProprietor/addNew.html');" value="新增" />
            </td>
          </tr>
        </table>
      </form>
    </div>

    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="小区名称" property="groupBuildingName" sortable="true"/>
		<display:column title="楼栋号" property="buildingName" />
		<display:column title="单元号" property="realRoomUnitName" />
		<display:column title="房间号" property="realRoomNum" />
		<display:column title="房间面积" >
			<c:if test="${row.roomSize>0 }">${row.roomSize }</c:if>
		</display:column>
		<display:column title="管理费单价" >
			<c:if test="${row.roomManagerPrice>0 }">${row.roomManagerPrice }</c:if>
		</display:column>
		<display:column title="每月管理费"  >
			<c:if test="${row.manangeFee>0}">${row.manangeFee }</c:if>
		</display:column>
		<display:column title="出售情况">
			<c:choose>
				<c:when test="${row.saleStatus == 1}">未出售</c:when>
				<c:when test="${row.saleStatus == 2}">已出售</c:when>
				<c:when test="${row.saleStatus == 3}">已交房</c:when>
			</c:choose>
		</display:column>
		<display:column title="居住情况">
			<c:choose>
				<c:when test="${row.livingStatus == 1}">未入住</c:when>
				<c:when test="${row.livingStatus == 2}">已入住</c:when>
			</c:choose>
		</display:column>
		<display:column title="出租情况">
			<c:choose>
				<c:when test="${row.leaseStatus == 1}">未出租</c:when>
				<c:when test="${row.leaseStatus == 2}">已出租</c:when>
			</c:choose>
		</display:column>
		<display:column title="业主姓名" property="name" />
		<display:column title="业主联系方式" property="phone" />
		<display:column title="操作" >
			<a class="blue editOwnerInfo" href="../propertyProprietor/edit.html?rrId=${row.realRoomId }">编辑</a>&nbsp;&nbsp;
			<a class="blue editOwnerInfo removeRoom" href="#" rrId='${row.realRoomId }'>删除</a>
		</display:column>
	</display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript" >
	$("#downLoadBtn").click(function() {
		window.location.href="../docs/jfq_pp_import_template.xls";
	});
	
	$(document).ready(function(){
		$('a.removeRoom').click(function(){
			if(window.confirm("确认要删除？")){
				var $this = $(this);
				var rrId = $this.attr('rrId');
				$.ajax({
					url:'../propertyProprietor/deleteRoom.html',
					dataType:'text',
					data: {rrId:rrId},
					success:function(data, status){ 
			          if(data==="true"){
			        	  $this.parents('tr').hide();
			          }else{
			        	  alert(data);
			          }
				    },
				});
			}
		});
	});
	
	function afterOnload11(){
		var curHref = 'propertyProprietor/list.html';
		window.parent.$('.menu-all').find('li').each(function(index){
			var thisHref = $(this).children('a').attr('href');
			if(thisHref.indexOf(curHref)>-1){
				//alert(thisHref);
				$(this).siblings('li').removeClass('on');
				$(this).siblings('li').find('a').removeClass('on');
				$(this).addClass('on');
				$(this).children('a').addClass('on');

			}
		});
	}

	$(function(){
		$('.select2_class').select2();

		var gbId = '${gbId}';
		$.ajax({
			url: '<%=basePath%>/groupBuilding/getGroupBuildings.json',
			dataType: 'json',
			success: function (data) {
				var list = JSON.parse(data);
				var strHtml = "<option value=''>选择小区&nbsp;&nbsp;&nbsp;</option>";
				$.each(list, function (i, item) {
					var str = "";
					if(gbId == item.id) {
						str = "<option value='" + item.id + "' selected='selected'>" + item.name + "</option>";
					} else {
						str = "<option value='" + item.id + "'>" + item.name + "</option>";
					}
					strHtml += str;
				});
				$("#groupBuiliding").html(strHtml);
			}
		});
	});
</script>
</html>
