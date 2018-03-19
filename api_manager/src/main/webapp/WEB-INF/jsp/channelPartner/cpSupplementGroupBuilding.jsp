<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>合伙人管理-合伙人资料-补充推荐小区</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
	<form class="inputform" action="../channelPartner/saveSupplementGroupBuilding.html">
		<input type="hidden" value="${cpr.tChannelPartnerFId }" name="cpId" />
		<input type="hidden" value="${cpr.id }" name="cprId" />
        <h2>新增物业公司推荐</h2>
        <table class="info-list-02" border="0" cellspacing="1" cellpadding="0">
                <tr>
                        <td align="right" width="300"><Span class="red">*</Span> 物业公司名称：</td>
                        <td>${cpr.pcName}</td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 物业资质：</td>
                        <td>
                        	<c:choose>
                        		<c:when test="${cpr.propertyQualifications==1 }">一级资质</c:when>
                        		<c:when test="${cpr.propertyQualifications==2 }">二级资质</c:when>
                        		<c:when test="${cpr.propertyQualifications==3 }">三级资质</c:when>
                        	</c:choose>
                        </td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 管理小区住户数：</td>
                        <td>${cpr.residentCount} 户</td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 公司地址：</td>
                        <td>${cpr.address }</td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 公司联系人：</td>
                        <td>${cpr.linkman }</td>
                      </tr>
                      <tr>
                        <td align="right" class="bold">物业公司管辖小区：</td>
                        <td></td>
                      </tr>
                      <tr>
                        <td align="right">小区所在地：</td>
                        <td class="citySelect">
                        	 <select id="province" onchange="onSelectChange(this,'city');" class="province select_normal" data-first-title="选择省" title="选择省">
	                        	<option value="-1">选择省</option>
	                        	<c:forEach items="${pcbList}" var="pcb" >
	                        		<option value="${pcb.id}">${pcb.name}</option>
	                        	</c:forEach>
	                        </select> 
	                        <select id="city" onchange="onSelectChange(this,'block');"  class="city select_normal" data-first-title="选择市">
	                        	<option value="-1">选择市</option>
	                        </select> 
	                        <select  id="block" class="area select_normal" data-first-title="选择区">
	                        	<option value="-1">选择区</option>
	                        </select>
                        </td>
                      </tr>
                      <tr>
                        <td align="right">详细地址：</td>
                        <td><input id="road" type="text" class="input_text" value="" placeholder='如富民路23号'></td>
                      </tr>
                      <tr>
                        <td align="right">小区名称：</td>
                        <td><input id="plotName" type="text" class="input_text" value=""></td>
                      </tr>
                      <tr id="btnPlot">
                        <td></td>
                        <td><input id="createPlot" class="input-btn mtop3" type="button" value="添加到管辖小区"></td>
                      </tr>
                      <tr class="plotAdded dsn">
                        <td align="right">管辖小区：</td>
                        <td><span class="plot-name"> <input name="province" type="text" value="" readonly="readonly" width="auto"> 
                        <input name="city" type="text" value="" readonly="readonly" width="auto"> 
                        <input name="block" type="text" value="" readonly="readonly"> 
                        <input name="abId" type="hidden" value="" readonly="readonly"> 
                        <input name="gbAddrDesc" type="text" value="" readonly="readonly"> 
                        <input name="gbName" type="text" value="" readonly="readonly"></span> <a class="blue mar-left15 plot-delete" href="#">删除</a></td>
                      </tr>
                    </table>
        <div class="padb mtop10"><input class="info-btn coperView" type="submit" value="提 交"  /></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.cxselect.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" >

//省市切换
function onSelectChange(obj,toSelId){
	setSelect(obj.value,toSelId);
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

//增加删除小区
$(function(){
	var $plotAdded = $('.plotAdded');
	var $plotDelete = $('.plot-delete');
	$('#createPlot').click(function(){
		var provinceVal = $('#province option:selected').text();
		var cityVal = $('#city option:selected').text();
		var blockVal = $('#block option:selected').text();
		var blockIdVal = $('#block option:selected').val();
		var roadVal = $('#road').val();
		var plotNameVal = $('#plotName').val();
		if(provinceVal == '选择省' || cityVal == '' || cityVal == '选择市' || blockVal == '选择区' || plotNameVal == ''){
			alert('请完善小区信息！');
		}else{
			var isValidGroupBuilding = true;
			$.ajax({ //验证待提交的小区是否被其它物业公司管辖
			      type:"POST",   
				  dataType:"text",
				  async:false,
			      url:"../channelPartner/verfyGB.html", 
			      data:{gbName:plotNameVal,blockVal:blockVal},
			      success:function(data, textStatus){ 
			          if(data!=="验证通过"){
				         alert(data);
				         isValidGroupBuilding = false;
			          }
			      },
			  });
			
			if(!isValidGroupBuilding) //不是有效小区
				return;
			
			var $plotTable = $(this).parents('table');
			var $newPlot = $plotAdded.clone(true).show().appendTo($plotTable);
			if(provinceVal == cityVal){
				cityVal = '';
			}
			$newPlot.find('input[name=province]').val(provinceVal);
			$newPlot.find('input[name=city]').val(cityVal);
			$newPlot.find('input[name=block]').val(blockVal);
			$newPlot.find('input[name=abId]').val(blockIdVal);
			$newPlot.find('input[name=gbAddrDesc]').val(roadVal);
			$newPlot.find('input[name=gbName]').val(plotNameVal);
			$newPlot.find('input').each(function(){  
			   if($(this).val() == ''){
				   $(this).hide(); 
			   }else{
				   var textWidth = function(text){ 
						var sensor = $('<div>'+ text +'</div>').css({display: 'none'}); 
						$('body').append(sensor); 
						var width = sensor.width();
						sensor.remove(); 
						return width;
					};
					$(this).width(textWidth($(this).val())); 
			   }
			}); 
		}
		if($('.plotAdded').length > 1){
			$(this).val('继续添加小区').addClass('btn-on');
			$('#road').val('');
			$('#plotName').val('');
		}
		window.parent.TuneHeight();
	});	
	$plotDelete.click(function(){
		$(this).parents('tr').remove();
		window.parent.TuneHeight();
		return false;
	});
});
</script>
</html>
