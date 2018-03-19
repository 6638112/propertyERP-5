<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-房号业主信息管理-新增</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>房号业主信息管理-新增</h2>
    <form class="inputform" action="../propertyProprietor/saveAddNew.html">
    <div class="bs-example bgebeb">
	        <table class="info-list" border="0">
	        
	         <tr>
	            <td width="120" align="right"><Span class="red">*</Span>小区：</td>
	            <td>
		            <select name='gbId' onchange="onSelectChange(this);"  > 
		            	<option >选择小区</option>
		    			<c:forEach var="gb" items="${gbList }">
		    				<option value="${gb.id}">${gb.name }</option>
		    			</c:forEach>
	    			</select>
    			</td>
	          </tr>
	          
	          <tr>
	            <td align="right"><Span class="red">*</Span>楼栋号：</td>
	            <td>
	            	<select name='buildingId' id ='buildingId' datatype='*'  nullmsg="请选择楼栋">
	            		<option value=""></option>
	            	</select>
				</td>
	          </tr>
	          
	          <tr>
	            <td align="right">单元号：</td>
	            <td><input type="text" class="input_text pp" name="unitName"  ignore="ignore" datatype="*1-20" errormsg="单元号范围在1到20个字符！"></td>
	          </tr>
	          <tr>
	            <td align="right"><Span class="red">*</Span> 房间号：</td>
	            <td><input type="text" class="input_text pp" name="realRoomNum" datatype="*1-20" nullmsg="请填房间号！" errormsg="房间号范围在1到20个字符！"></td>
	          </tr>
	          <tr>
	            <td align="right">业主姓名：</td>
	            <td><input type="text" class="input_text pp" name="ppName" datatype="*2-20" ignore="ignore" errormsg="姓名范围在2到20个字符！">若有多个业主，请用逗号分隔</td>
	          </tr>
	          <tr>
	            <td align="right">业主身份证号：</td>
	            <td><input type="text" maxlength="45" class="input_text pp" name="ppIdentifyNo" > 若有多个身份证，请用逗号分隔</td>
	          </tr>
	          <tr>
	            <td align="right">业主联系方式：</td>
	            <td><input type="text"  maxlength="45" class="input_text pp" name="ppPhone" >若有多个联系方式，请用逗号分隔</td>
	          </tr>
        
	          <tr>
	            <td align="right">房屋面积：</td>
	            <td><input type="text" class="input_text pp" name="roomSize"  ignore="ignore" dataType="/^[0-9]+(\.[0-9]+){0,1}$/" errormsg="请输入正确的房间面积" ></td>
	          </tr>
	          <tr>
	            <td align="right">管理费单价：</td>
	            <td><input type="text" class="input_text pp" name="roomManagerPrice" ignore="ignore" dataType="/^[0-9]+(\.[0-9]+){0,1}$/" errormsg="请输入正确的管理费单价" ></td>
	          </tr>
	          <tr>
	            <td align="right"><Span class="red">*</Span> 物业管理费：</td>
	            <td><input type="text" class="input_text pp" name="manangeFee" dataType="/^[0-9]+(\.[0-9]+){0,1}$/" nullmsg="请填物业管理费！" errormsg="请输入正确的物业管理费" ></td>
	          </tr>
	        </table>
    </div>
   	<input class="info-btn" type="submit" value="保 存" />
   	</form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		dragonfly:true,
	});
});

//省市切换
function onSelectChange(obj){
	var fromSelVal = obj.value;

	$('#buildingId').empty();//清空之前的选项
		
	jQuery.ajax({
		  url: "../propertyProprietor/getBuildingListByGbId.html",
		  cache: false,
		  dataType:"json",
		  async:false,
		  data:"gbId="+fromSelVal,
		  success: function(data){
		    $.each(JSON.parse(data), function(i, item) {
		    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#buildingId"));
		    });
		  }
		});
}
</script>
</html>
