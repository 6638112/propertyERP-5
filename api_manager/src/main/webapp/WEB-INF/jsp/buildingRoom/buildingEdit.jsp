
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-楼栋管理-编辑</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />

</head>

<body>
<div class="info">
	<form class="inputform" method="post" action="../buildingRoom/saveBuilding.html">
		<input type="hidden" id="bldId" name="buildingId" value="${entity.id }" />
        <h2>楼栋管理-编辑</h2>
	    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	      <tr>
	        <td align="right"><span class="red">*</span>所属小区：</td>
	        <td>
	        	<input type="hidden" id="gbId" name="groupbuildingId" value="${entity.tGroupBuildingFId }" />
	        	<input type="text" class="input_text pp" id="gbName" name="groupbuildingName" value="${entity.groupbuildingName }" style="background-color: #dad8d6;" readonly="readonly" datatype="*2-20" nullmsg="请选择小区！" />
	        	<!-- <input id="addInviteUser" onclick="showBuilding();" class="input-btn mtop2 mar-left15" type="button" value="选择小区" /> -->
	        </td>
	      </tr>
	      <tr>
	        <td width="120" align="right"><span class="red">*</span>所属管理处：</td>
	        <td>
	        	<input type="text" class="input_text pp" id="pmName" name="managementName" value="${entity.managementName }" style="background-color: #dad8d6;" readonly="readonly" nullmsg="请选择小区！" />
	        </td>
	      </tr>
	      <tr>
	        <td align="right"><span class="red">*</span> 楼栋号：</td>
	        <td>
	        	<input type="text" class="input_text pp" id="bldName" name="buildingName" value="${entity.name }" datatype="*1-10" nullmsg="请填写楼栋名！" errormsg="楼栋名由1到10位字符组成！" />(格式如:x栋、x座、x幢、x号)
	         </td>
	      </tr>
	      <%-- <tr>
	        <td align="right"><span class="red">*</span> 楼栋编码：</td>
	        <td><input type="text" class="input_text pp" name="buildingCode" value="${entity.code }" datatype="*2-20" nullmsg="请填写楼栋编码！" errormsg="楼栋编码由2到20位字符组成！" /></td>
	      </tr> --%>
	    </table>
	    <div class="padb">
	    	<input class="info-btn" type="button" onclick="submitValidate()" value="保存楼栋" />&nbsp;&nbsp;&nbsp;&nbsp;
	    	<%-- <c:if test="${entity.id!=null }">
	    		<input class="info-btn" type="button" onclick="addRoom(null);" value="新增房号" />
	    	</c:if> --%>
	    </div>
    </form>
    <%-- <c:if test="${entity.id!=null }">
    <h2>房号管理</h2>
    <display:table name="roomList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="roomSize" >
		<display:column title="单元号">
			${row.unitName}
		</display:column>
		<display:column title="房间号">
			${row.num}
		</display:column>
		<display:column title="户名">${row.proprietor.name}</display:column>
		<display:column title="联系电话">${row.proprietor.phone}</display:column>
		<display:column title="身份证">${row.proprietor.identifyNo}</display:column>
		<display:column title="操作" >
			<a class="blue" name="view" onclick="return confirm('您确定要编辑吗?');" href="../buildingRoom/initRoom.html?id=${row.id }" >编辑</a>
			<a class="blue" name="view" onclick="return confirm('您确定要删除吗?删除房号会同步删除房号的业主信息!!!');" href="javascript:delRoom(${row.id });" >删除</a>
		</display:column>
	</display:table>
	</c:if> --%>
</div>
<div class="layer-bill dsn" style="width: 560px; height: auto;">
	<div>
		管理处<input type="text" id="s_mgt_name" class="input_text w120" />
		小区名<input type="text" id="s_gb_name" class="input_text w120" />
		<input id="searchBtn" class="input-btn mtop2 mar-left15" type="button" onclick="queryBuildingData()" value="查询" />
	</div>
	<hr/>
	<div id="groupbuildingDiv"></div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
<script type="text/javascript">
	$(function(){
		 //表单验证
		$(".inputform").Validform({
			tiptype:3
		});
	});
	function submitValidate(){
		$(".inputform").submit();
	}
	function delRoom(id,pid){
		var reqUrl = "../buildingRoom/deleteRoom.html";
		$.ajax({//使用ajax请求删除数据
			type:"POST",
			url:reqUrl,
			async:true,
			data:{roomId:id,ppId:pid,},
			success:function(data, textStatus){
		        alert("操作提示："+data);
				$(window.parent.document).find("#mainFrame").attr('src', "<%=basePath%>/buildingRoom/buildingSearch.html");
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
	
	function showBuilding(){
		initBuildingData();
		//导入账单弹出层
		$.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
	    	border : [5, 0.3, '#000'],
			page: {dom : '.layer-bill'}
		});
	}
	function initBuildingData(){
		var gbId = $('#gbId').val();
		jQuery.ajax({
		  url:"../buildingRoom/queryGroupBuilding.html",
		  cache:false,
		  dataType:"json",
		  async:false,
		  data:"id="+gbId,
		  success:function(data){
			  var tableHmtl = '<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">';
			  tableHmtl += '<tr style="color:black;font-weight:bold;"><td width="150">小区</td><td width="200">管理处</td><td width="60">操作</td></tr>';
			  $.each(JSON.parse(data), function(i, item) {
				  var mgtName = item.propertyManagementName;
				  if(typeof(mgtName)=="undefined" || mgtName==null){
					  mgtName = "-";
				  }
				  tableHmtl += '<tr><td>'+item.name+'</td><td>'+mgtName+'</td><td><a class="red" href="javascript:submitBuilding('+item.id+',\''+item.name+'\',\''+mgtName+'\');">添加</a></td></tr>';
		      });
			  tableHmtl += "</table>";
			  $('#groupbuildingDiv').html(tableHmtl);
		  },
		  error:function(data){
			  alert("error out"+data);
		  }
		});
	}
	function queryBuildingData(){
		var mgtName = $('#s_mgt_name').val();
		var gbName = $('#s_gb_name').val();
		jQuery.ajax({
		  url:"../buildingRoom/queryGroupBuilding.html",
		  dataType:"json",
		  data:encodeURI("mgtName="+mgtName+"&gbName="+gbName),
		  success:function(data){
			  var tableHmtl = '<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">';
			  tableHmtl += '<tr style="color:black;font-weight:bold;"><td width="150">小区</td><td width="200">管理处</td><td width="60">操作</td></tr>';
			  $.each(JSON.parse(data), function(i, item) {
				  var mgtName = item.propertyManagementName;
				  if(typeof(mgtName)=="undefined" || mgtName==null){
					  mgtName = "-";
				  }
				  tableHmtl += '<tr><td>'+item.name+'</td><td>'+mgtName+'</td><td><a class="red" href="javascript:submitBuilding('+item.id+',\''+item.name+'\',\''+mgtName+'\');">添加</a></td></tr>';
		      });
			  tableHmtl += "</table>";
			  $('#groupbuildingDiv').html(tableHmtl);
			  var tbHeight = $("#groupbuildingDiv").height();
			  $(".xubox_main").height(tbHeight + 85);
			  $(".xubox_border").height(tbHeight + 95);
		  },
		  error:function(data){
			  alert("error out"+data);
		  }
		});
	}
	function submitBuilding(id,name,pmName){
		$('#gbId').val(id);
		$('#gbName').val(name);
		$('#pmName').val(pmName);
		layer.closeAll();
	}
</script>
</html>
