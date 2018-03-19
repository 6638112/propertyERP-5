<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家管理-店铺编辑</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
	<form class="inputform" id="editForm" enctype="multipart/form-data" method="post" action="../communitySupplyCompany/saveEdit.html">
		<input type="hidden" name="supplyTmpId" value="${entity.tmpId }" />
		<input type="hidden" name="supplyId" value="${entity.id }" />
		<input type="hidden" id="delImg" name="delImgIds" />
		<input type="hidden" id="delContect" name="delContectIds" />
        <h2 class="mtop20">店铺基本信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right"><span class="red">*</span> 店铺类别：</td>
            <td>
				<select name="supplyTmpType" class="select_normal" datatype="*" nullmsg="请选择店铺类别！">
					<option value=""></option>
					<c:forEach items="${cstList}" var="cst"> 
	                    <option value="${cst.id }" <c:if test="${entity.tCommunitySupplyTypeFId==cst.id }">selected="selected"</c:if>>${cst.name }</option>
            		</c:forEach>
				</select>
			</td>
          </tr>
          <tr>
            <td width="120" align="right"><span class="red">*</span> 店铺名称：</td>
            <td><input type="text" class="input_text pp" name="supplyTmpName" value="${entity.supplyName }"
            	datatype="*3-20" nullmsg="请输入商家名称！" errormsg="商家名称至少由3位字符组成！"></td>
          </tr>
           <tr>
            <td align="right" rowspan="2"><span class="red">*</span> 店铺地址：</td>
            <td>
            	<select id="province" onchange="onSelectChange(this,'city');" class="province select_normal" data-first-title="选择省" title="选择省"
            		 datatype="*" nullmsg="请选择省！">
                	<option value="-1">选择省</option>
                	<c:forEach items="${pcbList}" var="pcb" >
                		<option value="${pcb.id}" <c:if test="${entity.provinceName==pcb.name }">selected="selected"</c:if>>${pcb.name}</option>
                	</c:forEach>
                </select> 
                <select id="city" onchange="onSelectChange(this,'block');"  class="city select_normal" data-first-title="选择市" datatype="*" nullmsg="请选择市！">
                	<option value="-1">选择市</option>
                	<c:if test="${entity.cityName!=null }"><option value="0" selected="selected">${entity.cityName}</option></c:if>
                </select> 
                <select id="block" name="block_id" class="area select_normal" data-first-title="选择区" datatype="*" nullmsg="请选择区！">
                	<option value="-1">选择区</option>
                	<c:if test="${entity.blockName!=null }"><option value="${entity.addressBlockId}" selected="selected">${entity.blockName}</option></c:if>
                </select>
            </td>
          </tr>
          <tr >
            <td><input type="text" class="input_text pp" id="addressDetailId" name="addressDetail" value="${entity.editAddressDetail }"
            	datatype="*4-30" nullmsg="请输入详细地址！" errormsg="详细地址至少由4位字符组成！"/></td>
          </tr>
          <tr >
            <td align="right">添加新的店铺电话：</td>
            <td>
            	<!-- <div>
            	<input type="text" class="input_text pp" name="newContectPhone" datatype="t" errormsg="请输入正确的电话格式！" />
            	</div> -->
            	<input onclick="createNewPhone();" class="input-btn mtop2 mar-left15" type="button" value="增加店铺电话" />
            </td>
          </tr>
          <tr>
            <td align="right"><span class="red">*</span> 店铺电话：</td>
            <td id="addPhoneTD">
            	 <c:forEach items="${entity.csContects }" var="contect">
	            	<div class="mtop10" id="csPhone_${contect.id }"> 
	            		<input type="text" class="input_text pp" name="newContectPhone" value="${contect.contectInfo }" datatype="t" errormsg="请输入正确的电话格式！" />&nbsp;&nbsp;&nbsp;
	            		<a class="blue" href="javaScript:delCSPhone(this,${contect.id });">删除</a>
	            	</div>
	             </c:forEach>
            </td>
          </tr>
           <tr>
            <td align="right"><span class="red">*</span> 认领人：</td>
            <td>${entity.belongUserName }</td>
          </tr>
          <tr>
            <td align="right"><span class="red">*</span> 认领人联系电话：</td>
            <td>${entity.belongUserPhone}</td>
          </tr>
          <tr>
            <td align="right">店铺图片：</td>
            <td valign="bottom" id="imgShow">
             	<ul class="menu-img">
	            	<c:forEach items="${picURL }" var="picurl">
	             		<li id="ulID${picurl.id}" style="position:relative; margin-bottom:20px;">
	             		<a href="<%= OmsSysParamManager.getImageServerUrl("/communitySupplyPic/") %>/communitySupplyPic/${picurl.picUrl}" rel="clearbox[test1]">
	             		<img src="<%= OmsSysParamManager.getImageServerUrl("/communitySupplyPic/") %>/communitySupplyPic/${picurl.picUrl}" border="0" />
	             		</a>
	             		<a class="blue" style="display:bolck; position:absolute; left:50px; top:125px; line-height:20px;" name="view" href="javascript:delImg(${picurl.id});" >删除</a>
	             		</li>
	            	</c:forEach>
             	</ul>
            </td>
            
          </tr>
          <tr >
            <td align="right">添加新图片：</td>
            <td id="addImgTD">
	            <div>
	            	<input id="imageInput" name="imageFile"  type="file" class="w150" onchange="checkImgType(this)" />
	            	<input onclick="createNewImg();" class="input-btn mtop2 mar-left15" type="button" value="增加新图片" />
	            </div>
            </td>
          </tr>
        </table>
        <div class="padb mtop10">
        	<input id="goBack" class="info-btn" type="button" value="返 回" onclick="history.back();"/>
        	<input id="sbtForm" class="info-btn" type="button" onclick="valdaSubmit();" value="确认提交" />
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		dragonfly:true
	});
});
//省市切换
function onSelectChange(obj,toSelId){
	setSelect(obj.value,toSelId);
}

function valdaSubmit(){
	var flag = true;
	if(getCsPhoneCount(1,0)>0){//验证重复
		$(':input[name=newContectPhone]').each(function() {	
			if($(this).val()){
				var curPhone = $(this).val();
				var isPhone=/^(([0-9]{3,4}-)?[0-9]{7,8})|(((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|14[012356789][0-9]{8}|15[012356789][0-9]{8}|17[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7}))$/;
				if(isPhone.test(curPhone)==false){
					$(this).focus();
			        $(this).css("boreder","1px solid red");
			        alert('请填写电话或手机号码格式！');
			        flag = false;
			        return false;
			    }
	    		if(getCsPhoneCount(2,curPhone)>1) 
	    		{
	    			$(this).focus();
	    			$(this).css("boreder","1px solid red");
	    			alert('联系电话重复!');
	    			flag = false;
	    	        return false;
	    		}
			}
    	});
	}else{
		alert('请至少保留一个店铺联系电话!');
		flag = false;
	}
	if(flag){
		$('#editForm').submit();
	}
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
//检查是否选择了图片文件
function checkImgType(ths) {
    if (ths.value == "") {
        alert("请上传图片");
        return false;
    } else {
        if (!/\.(gif|jpg|jpeg|png|bmp|GIF|JPG|PNG|BMP)$/.test(ths.value)) {
            alert("图片类型必须是.gif,jpeg,jpg,png,bmp中的一种");
            ths.value = "";
            return false;
        }
    }
    return true;
}

function delImg(imgId){
	if(confirm('您确定要删除吗?')==true){
		if($('#delImg').val()){
			var delIds = $('#delImg').val();
			$('#delImg').val(delIds+','+imgId);
		}else{
			$('#delImg').val(imgId);
		}
		$("#ulID"+imgId).remove();
	}
}
function delCSPhone(obj,imgId){
	if(confirm('您确定要删除吗?')==true){
		if($('#delContect').val()){
			var delIds = $('#delContect').val();
			$('#delContect').val(delIds+','+imgId);
		}else{
			$('#delContect').val(imgId);
		}
		$("#csPhone_"+imgId).remove();
		$("#hiddenContectPhone_"+imgId).remove();
	}
}
function getCsPhoneCount(type,phone){
	var i = 0;
    $("input[name=newContectPhone]").each(function(){
    	if(type==1){//验证数量
    		if($(this).val()){
         		i++;
         	}
    	}else{//验证重复
    		if($.trim($(this).val())==$.trim(phone)){
         		i++;
         	}
    	}
    });
    return i;
}
var div_index=1000;
//增加店铺电话、店铺图标
function createNewPhone(){
	var addPhoneDiv = "<div class='mtop10' id='addPhoneDiv_"+div_index+"'><input type='text' class='input_text pp' name='newContectPhone' datatype='t' errormsg='请输入正确的电话格式' />&nbsp;&nbsp;&nbsp;<a class='blue' href='javaScript:delAddCSPhone("+div_index+");'>删除</a></div>";
	$("#addPhoneTD").append(addPhoneDiv);
	window.parent.TuneHeight();
	div_index ++;
}
function delAddCSPhone(div_id){
	if(confirm('您确定要删除吗?')==true){
		$("#addPhoneDiv_"+div_id).remove();
	}
}
//增加店铺电话、店铺图标
function createNewImg(){
	var addPhoneDiv = "<div class='mtop10' id='addImgDiv_"+div_index+"'><input id='imageInput' name='imageFile'  type='file' class='w150' onchange='checkImgType(this)' />&nbsp;&nbsp;&nbsp;<a class='blue' href='javaScript:delAddCSImg("+div_index+");'>删除</a></div>";
	$("#addImgTD").append(addPhoneDiv);
	window.parent.TuneHeight();
	div_index ++;
}
function delAddCSImg(div_id){
	if(confirm('您确定要删除吗?')==true){
		$("#addImgDiv_"+div_id).remove();
	}
}
</script>

</html>
