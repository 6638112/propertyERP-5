<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-房号管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />

</head>

<body>
<div class="info">
	<span style="color: red;">${alertMsg }</span>
		<h2>小区信息</h2>
	    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	      <tr>
	        <td width="120" align="right">所属管理处：</td>
	        <td>
	        	${entity.managementName }
	        </td>
	      </tr>
	      <tr>
	        <td align="right">所属小区：</td>
	        <td>
	        	${entity.groupbuildingName }
	        </td>
	      </tr>
	      <tr>
	        <td align="right"><span class="red">*</span>所属楼栋名：</td>
	        <td>
	        	${entity.name }
	         </td>
	      </tr>
	    </table>
    <h2>房号管理</h2>
    <div class="bs-example bgebeb">
    	<form method="post" action="../buildingRoom/roomManage.html">
    		<input type="hidden" id="gbName" name="groupbuilding_name" value="${entity.groupbuildingName }" />
    		<input type="hidden" id="bldId" name="building_id" value="${entity.id }" />
    		<input type="hidden" id="bldName" name="building_name" value="${entity.name }" />
	        <table class="info-list" border="0">
	          <tr>
	            <td><div align="right">单元号</div></td>
	            <td><input type="text" value="${param.unitName }" class="input_text pp w120" name="unitName" /></td>
	            <td><div align="right">房号</div>
	            </td>
	            <td>
	          		<input type="text" class="input_text pp w120" value="${param.roomNum }" name="roomNum" />
	            </td>
	           	<td><div align="right">户名</div>
	            </td>
	            <td>
	          		<input type="text" class="input_text pp w120" value="${param.proprietorName }" name="proprietorName" />
	            </td>
	            <td><div align="right">联系电话</div>
	            </td>
	            <td>
	          		<input type="text" class="input_text pp w120" value="${param.proprietorPhone }" name="proprietorPhone" />
	            </td>
	           </tr>
	           <tr>
	            <td colspan="10" align="center">
	            	<input class="input-btn w80" type="submit" value="搜索" />
	            	<input class="input-btn w80" type="button" onclick="addRoom(null)" value="新增房号" />
	            	<!-- <input class="input-btn w80" type="button" onclick="showImport()" value="导入房号" /> -->
	            </td>
	            <td></td>
	            <td></td>
	          </tr>
	        </table>
    	</form>
    </div>
    <display:table name="roomList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="50" requestURI="" partialList="true" size="roomSize" >
    	<display:column title="小区名称" class="gb_${row.id }">
    		<input type="hidden" id="room_pp_${row.id }" value="${row.proprietor.id}" />
			${row.groupbuildingName}
		</display:column>
		<display:column title="楼栋名称" sortable="true">
			${row.buildingName}
		</display:column>
		<display:column title="单元号">
			${row.unitName}
		</display:column>
		<display:column title="房间号">
			${row.numTail}
		</display:column>
		<display:column title="户名">${row.proprietor.name}</display:column>
		<display:column title="联系电话">${row.proprietor.phone}</display:column>
		<display:column title="身份证">${row.proprietor.identifyNo}</display:column>
		<display:column title="操作" >
			<a class="blue" name="editRoom" onclick="return confirm('您确定要编辑吗?');" href="../buildingRoom/initRoom.html?id=${row.id }" >编辑</a>
			<%-- <a class="blue" name="deleteRoom" onclick="return confirm('您确定要删除吗?删除房号会同步删除房号的业主信息!!!');" href="javascript:delRoom(${row.id });" >删除</a> --%>
		</display:column>
	</display:table>
</div>
<div class="layer-bill dsn">
   <a id="downLoadBtn" class="blue" href="javascript:exportExcelModel();"><img src="../images/download-icon.jpg" /> 第1步：下载房号数据导入模版</a>
   <br />
   <form id="inputFileForm" class="mtop10" enctype="multipart/form-data" action="../buildingRoom/saveImportRooms.html" method="post">
   	 <input type="hidden" id="bldId" name="building_id" value="${entity.id }" />
     <span class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第2步：请选择您已完善的房号数据文件</span>
     <br/> 
     <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="file" id="excelFileInput" name="excelFile" size="50" title="上传楼栋" /></div>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="input-btn w120 mtop20" type="button" onclick="submitValidate();" value="上传" />
   </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
function delRoom(id){
	var reqUrl = "../buildingRoom/deleteRoom.html";
	var pid = $("#room_pp_"+id).val();
	$.ajax({//使用ajax请求删除数据
		type:"POST",
		url:reqUrl,
		async:true,
		data:{roomId:id,ppId:pid,},
		success:function(data, textStatus){
	        alert("操作提示："+data);
	        $(".gb_"+id).parents("tr").remove();
		},
	}); 
}
function addRoom(id){
	var reqUrl = "../buildingRoom/initRoom.html";
	if(id!=null && id!=''){
		reqUrl += "?id="+id;
	}else{
		var gbName = $("#gbName").val();
		var buildingId = $("#bldId").val();
		var buildingName = $("#bldName").val();
		reqUrl += "?gbName="+gbName+"&buildingId="+buildingId+"&buildingName="+buildingName;
	}
	$(window.parent.document).find("#mainFrame").attr('src', reqUrl);
}

//导入账单弹出层
function showImport(){
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
	}else if(fullFileName.lastIndexOf(".xls") !=d){ 
		alert("上传的必须是xls格式的Excel文件，请先下载模板进行编辑后再上传");
		return false;			
	}
	$("#inputFileForm").submit();
}

function exportExcelModel(){
	location.href="../docs/jfq_room_import_template.xls";
}
</script>
</html>
