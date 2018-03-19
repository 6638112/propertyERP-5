<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="com.cnfantasia.server.ms.pub.session.UserContext" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-小区管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/select2.css">

</head>

<body>
<div class="info">
    <h2>小区管理</h2>
    <div class="bs-example bgebeb">
    	<form method="post" action="../groupBuilding/search.html">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div align="right">管理处：</div></td>
	            <td><input type="text" value="${param.pmName }" class="input_text pp w120" name="pmName" /></td>
			  	<td width=""><div class="list-name">小区名称：</div></td>
			  	<td>
				  <select id="groupBuiliding" name="gbId" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
					  <option value="">选择小区</option>
				  </select>
			  	</td>
	            <td><div align="right">审核状态：</div></td>
	            <td>
					<select name="auditStatus" class="select_normal w131">
	                    <option value="-1" >全部</option>
	                    <option value="0" <c:if test="${param.auditStatus==0 }"> selected="selected"</c:if>>待审核</option>
	                    <option value="1" <c:if test="${param.auditStatus==1 }"> selected="selected"</c:if>>审核通过</option>
	                    <option value="2" <c:if test="${param.auditStatus==2 }"> selected="selected"</c:if>>审核失败</option>
                    </select>
				</td>
				<td></td>
	            <td>
	            	<input class="input-btn w80" type="submit" value="搜索" />
	            	<input class="input-btn w80" type="button" onclick="$(window.parent.document).find('#mainFrame').attr('src', '../groupBuilding/initEdit.html?type=add');" value="新增" />
	            </td>
	            <td></td>
	            <td></td>
	          </tr>
	        </table>
    	</form>
    </div>
    
    <display:table name="resList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="resultSize" >
		<display:column title="小区名称" sortable="true">
			<a class="blue" href="../groupBuilding/view.html?id=${row.id}">${row.name}</a> 
		</display:column>
		<display:column title="所在地">
			${row.addressProvinceName}-${row.addressCityName}-${row.addressBlockName}
		</display:column>
		<display:column title="详细地址" property="addressDesc" />
		<display:column title="所属管理处">
			${row.propertyManagementName}
		</display:column>
		<display:column title="所属物业公司">
			${row.propertyCompanyName}
		</display:column>
		<display:column title="审核状态">
			<c:if test="${row.checkStatus ==1 }">审核通过</c:if>
			<c:if test="${row.checkStatus ==2 }">审核失败</c:if>
			<c:if test="${row.checkStatus ==0 }">待审核</c:if>
			<c:if test="${row.checkStatus ==9 }">无须审核</c:if>
		</display:column>
		<display:column title="操作" >
			<a class="blue" name="view" href="../groupBuilding/initEdit.html?id=${row.id }" >编辑</a>
			<c:if test="${row.checkStatus ==1 or row.checkStatus ==9 }">
				<% if(UserContext.getUser().getCooperationType()>1 || UserContext.getCurrUser().getIsadmin() == 1) { //只有解放区管理员、以及高级或全面合作的物业才有资格导入%> 
				&nbsp;&nbsp;<input class="input-btn" type="button" onclick="showImport(this)" value="导入楼栋房号业主" gbId='${row.id }' gbName='${row.name }' />
				<% } %>
			</c:if>
		</display:column>
	</display:table>
</div>

<div class="layer-bill dsn">
   <a id="downLoadBtn" class="blue" href="javascript:exportExcelModel();"><img src="../images/download-icon.jpg" /> 第1步：下载房号数据导入模版</a>
   <br />
   <form id="inputFileForm" class="mtop10" enctype="multipart/form-data" action="../buildingRoom/importBuildingRoomProprietor.html" method="post">
   	 <input type="hidden" id="gbId" name="groupBuildingId" value="" />
   	 <input type="hidden" id="gbName" name="groupBuildingName" value="" />
     <span class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第2步：请选择您已完善的房号数据文件</span>
     <br/> 
     <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="file" id="excelFileInput" name="excelFile" size="50" title="上传楼栋" /></div>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="input-btn w120 mtop20" type="button" id="uploadBtn02" onclick="submitValidate();" value="上传" />
   </form>
</div>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript">
	function setConfig(id,type){
		var reqUrl = "../inviteReward/enableConfig.html";
		if(type=='disable'){
			reqUrl = "../inviteReward/disableConfig.html";
		}
		$.ajax({//使用ajax请求删除数据
			type:"GET",
			url:reqUrl,
			async:true,
			data:{id:id,},
			success:function(data, textStatus){
		        alert("操作提示："+data);
				$(window.parent.document).find("#mainFrame").attr('src', "<%=basePath%>/inviteReward/search.html");
			},
		}); 
	}
	$(function () {
		$('.select2_class').select2();
		var gbId = '${param.gbId}';
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
<script type="text/javascript">
//导入账单弹出层
	function showImport(ths){
		var $this = $(ths);
		$('#gbId').val($this.attr('gbId'));
		$('#gbName').val($this.attr('gbName'));
		
		$.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
	    	border : [5, 0.3, '#000'],
			page: {dom : '.layer-bill'}
		});
	}
	function submitValidate(){
		var fullFileName = $("#excelFileInput").val(); //选中文件的全名，包含路径
		var d = fullFileName.length - ".xls".length;
		if(fullFileName == ""){
			alert("请先选中一个上传文件");
			return false;
		}else if(fullFileName.indexOf(".xls") !=d){ 
			alert("上传的必须是后缀名为xls的Excel文件，请先下载模板进行编辑后再上传");
			return false;			
		}
		$("#uploadBtn02").attr("disabled",true);
		$("#uploadBtn02").css({"background":"#DCDCDC","color":"#FFFFFF","border":"0px","cursor":"wait"});
		$("#inputFileForm").submit();
	}
	
	function exportExcelModel(ths){
		location.href= "../buildingRoom/exportTemplate.html?groupBuildingName=" + $('#gbName').val();
	}
</script>

</html>
