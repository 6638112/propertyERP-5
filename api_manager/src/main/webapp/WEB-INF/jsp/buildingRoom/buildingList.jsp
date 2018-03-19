<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-楼栋管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info">
    <h2>楼栋管理</h2>
    <div class="bs-example bgebeb">
    	<form method="post" action="../buildingRoom/buildingSearch.html">
	        <table class="info-list" border="0">
	          <tr>
	          <td><div align="right">管理处：</div></td>
	          	<td><input type="text" value="${param.managementName }" class="input_text pp w120" name="managementName" /></td>
			  	<td width=""><div class="list-name">小区名称：</div></td>
			  	<td>
				  <select id="groupBuiliding" name="gbId" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
					  <option value="">选择小区</option>
				  </select>
			  	</td>
	            <td><div align="right">楼栋名称：</div>
	            </td>
	            <td>
	          		<input type="text" class="input_text pp w120" value="${param.buildingName }" name="buildingName" />
	            </td>
	            <td>
	            	<input class="input-btn w80" type="submit" value="搜索" />
	            	<!-- <input class="input-btn w80" type="button" onclick="$(window.parent.document).find('#mainFrame').attr('src', '../buildingRoom/initBuilding.html');" value="新增楼栋" /> -->
	            </td>
	            </tr>
	        </table>
    	</form>
    </div>
    
    <display:table name="resList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="resultSize" >
		<display:column title="管理处">
			${row.managementName}
		</display:column>
		<display:column title="小区名称">
			${row.groupbuildingName}
		</display:column>
		<display:column title="楼栋名称" sortable="true">
			<a class="blue" href="../buildingRoom/viewBuilding.html?id=${row.id}">${row.name}</a> 
		</display:column>
		<%-- <display:column title="数据来源">
			<c:if test="${row.sourceType==1 }">运维平台录入</c:if>
			<c:if test="${row.sourceType==1 }">手工搜集</c:if>
			<c:if test="${row.sourceType==1 }">百度地图API</c:if></display:column> --%>
		<display:column title="操作" >
			<a class="blue" name="view" href="../buildingRoom/initBuilding.html?id=${row.id }" >编辑</a>&nbsp;&nbsp;
			<%-- <a class="blue" name="view" href="../buildingRoom/roomManage.html?building_id=${row.id }" >房号管理</a>&nbsp;&nbsp; --%>
			
			<%-- <a class="blue" name="view" href="../buildingRoom/importBuildingRoomProprietor.html?groupbuildingId=${row.tGroupBuildingFId}" >导入房号</a>&nbsp;&nbsp; --%>
			<%-- <a class="blue" name="view" onclick="return confirm('您确定要删除吗?删除楼栋会同步删除楼栋下面的房号业主信息!!!');" href="javascript:delBuilding(${row.id });" >删除</a> --%>
		</display:column>
	</display:table>
</div>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript">
	function delBuilding(id){
		var reqUrl = "../buildingRoom/deleteBuilding.html";
		$.ajax({//使用ajax请求删除数据
			type:"GET",
			url:reqUrl,
			async:true,
			data:{id:id,},
			success:function(data, textStatus){
		        alert("操作提示："+data);
				$(window.parent.document).find("#mainFrame").attr('src', "<%=basePath%>/buildingRoom/buildingSearch.html");
			},
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
