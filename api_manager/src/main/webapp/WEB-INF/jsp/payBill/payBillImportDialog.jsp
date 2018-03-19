<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业缴费导入</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css" />
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/DatePicker.js"></script>
<script type="text/javascript" >
	$("#downLoadBtn").click(function() {
		var groupbuildingId = $("#gbId").val();
		var erportMonth = $("#erportMonth").val();
		location.href= "../payBill/exportPayBillTemplate.html?groupbuildingId="+groupbuildingId+"&erportMonth="+erportMonth;
	});

	//上传文件校验
	$("#uploadBtn").click(function(){
		var fullFileName = $("#excelFileInput").val(); //选中文件的全名，包含路径
		var d = fullFileName.length - ".xls".length;
		if(fullFileName == ""){
			alert("请先选中一个上传文件");
			return false;
		}else if(fullFileName.indexOf(".xls") !=d){ 
			alert("上传的必须是后缀名为xls的Excel文件，请先下载模板进行编辑后再上传");
			return false;			
		}
	});
	
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
				  tableHmtl += '<tr><td>'+item.name+'</td><td>'+item.propertyManagementName+'</td><td><a class="red" href="javascript:submitBuilding('+item.id+',\''+item.name+'\');">添加</a></td></tr>';
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
		  cache:false,
		  dataType:"json",
		  async:false,
		  data:"mgtName="+mgtName+"&gbName="+gbName,
		  success:function(data){
			  var tableHmtl = '<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">';
			  tableHmtl += '<tr style="color:black;font-weight:bold;"><td width="150">小区</td><td width="200">管理处</td><td width="60">操作</td></tr>';
			  $.each(JSON.parse(data), function(i, item) {
				  tableHmtl += '<tr><td>'+item.name+'</td><td>'+item.propertyManagementName+'</td><td><a class="red" href="javascript:submitBuilding('+item.id+',\''+item.name+'\');">添加</a></td></tr>';
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
	function submitBuilding(id,name){
		$('#gbId').val(id);
		$('#gbName').val(name);
		layer.closeAll();
	}
</script>
</head>

<body>
    <h2>物业缴费导入</h2>
	<div class="layer-bill dsn">
		<input type="hidden" id="gbId" name="groupbuildingId" />
	        选择导入的小区<input type="text" class="input_text pp" id="gbName" name="groupbuildingName" style="background-color: #dad8d6;" readonly="readonly" />
	    <input id="addInviteUser" onclick="showBuilding();" class="input-btn mtop2 mar-left15" type="button" value="选择小区" />
		选择导入的月份<input id="date01" name="erportMonth" type="text" class="input_text icon_dt"  title="请选择起始时间" />
	    <a id="downLoadBtn" class="blue" href="#"><img src="../images/download-icon.jpg" /> 下载账单模版</a>
	    <form class="mtop10" enctype="multipart/form-data" action="../payBill/importPayBill.html" method="post">
	         请选择要上传的新账单：<input id="excelFileInput" name="excelFile" type="file" size="50" title="上传新账单" /><br/> 
	         <input id="uploadBtn" class="input-btn w80 mtop10" type="submit" value="上传" />
	    </form>
	</div>
</body>

</html>
