<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>帮帮忙详情</title>
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/style.css?v20161128">
	<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
	<link rel="stylesheet" type="text/css" href="../styles/displaytag-css/alternative.css">
	<link rel="stylesheet" type="text/css" href="../css/picbox.css" />
	<style type="text/css">
		.emoji{ width: 18px; height: auto; margin-left: 2px; vertical-align: text-bottom;}
	</style>
	<script src="https://twemoji.maxcdn.com/2/twemoji.min.js"></script>
</head>

<body>
<div class="info posrelative">
	<h2>内容详情查看</h2>
	<div class="bs-example bgebeb">
		<table class="info-list-02" border="0" cellpadding="0" cellspacing="1" style="white-space:normal">
			<tr>
				<td  width="120"><div class="list-name">发布小区：</div></td>
				<td>${uq.gbName}</td>
			</tr>
			<tr>
				<td><div class="list-name">小区所在地：</div></td>
				<td>${uq.location}</td>
			</tr>
			<tr>
				<td><div class="list-name">门牌号：</div></td>
				<td>
				 	<c:if test="${not empty uq.buildingName }">${uq.buildingName}-</c:if>${uq.roomNum}
				</td>
			</tr>
			<tr>
				<td><div class="list-name">解放号：</div></td>
				<td>${uq.huaId}</td>
			</tr>
			<tr>
				<td><div class="list-name">手机号：</div></td>
				<td>${uq.mobile}</td>
			</tr>
			<tr>
				<td><div class="list-name">发布内容：</div></td>
				<td>${uq.content}</td>
			</tr>
			<tr>
				<td><div class="list-name">上传的图片：</div></td>
				<td>
					<c:forEach items="${uq.picList }" var="picurl">
	             		<ul class="menu-img"><li><a href="${picurl }" rel="lightbox-group01"><img src="${picurl }" border="0" /></a></li></ul>
	            	</c:forEach>
				</td>
			</tr>
			<tr>
			  <td><div class="list-name">状态：</div></td>
			  <td>
				  <c:choose>
					<c:when test="${uq.status==1 }">待处理</c:when>
					<c:when test="${uq.status==5 }">解放区回复</c:when>
					<c:when test="${uq.status==6 }">物业回复</c:when>
					<c:when test="${uq.status==3 }">已转 <a href="../propertyRepair/searchRepair.html?repairId=${uq.serviceId}" class="blue">公共维修</a></c:when>
					<c:when test="${uq.status==4 }">已转 <a href="../dredge/dredgeOrderList.html?dredgeBillId=${uq.serviceId}" class="blue">普通维修</a></c:when>
					<c:when test="${uq.status==2 }">已通知片区经理</c:when>
				  </c:choose>
			  </td>
			</tr>
			<c:choose>
			  <c:when test="${uq.status == 5 }">
			  	<tr>
					<td><div class="list-name">解放区回复内容：</div></td>
					<td>${uq.answerContent}</td>
				</tr>
			  </c:when>
			  <c:when test="${uq.status == 6 }">
			  	<tr>
					<td><div class="list-name">物业回复内容：</div></td>
					<td>${uq.answerContent}</td>
				</tr>
			  </c:when>
			  <c:otherwise>
			  	<tr>
					<td><div class="list-name">回复内容：</div></td>
					<td>${uq.answerContent}</td>
				</tr>
			  </c:otherwise>
			</c:choose>
			<%-- <c:if test="${uq.status > 1 }"> --%>
				<tr>
					<td><div class="list-name">操作人：</div></td>
					<td>${uq.updateUserName}</td>
				</tr>
				<tr>
					<td><div class="list-name">操作时间：</div></td>
					<td>${uq.updateTime}</td>
				</tr>
			<%-- </c:if> --%>
		</table>
	</div>

	<c:if test="${uq.status == 1 }"> <!-- 待处理订单 -->
		<div class="mtop20">
			<input class="info-btn small-btn w150 swap-tab" type="button" value="转为工单">
			<input class="info-btn small-btn w150 mleft20 swap-tab" type="button" value="回复用户">
		</div> 
	</c:if>
    
    <div class="mtop30 swap-content-box dsn">
	    <form id="changeBillTypeForm" class="inputform" >
	    	<input id="roomId" name="roomId" type="hidden" value="${uq.roomId }" />
	    	<input name="gbId" type="hidden" value="${uq.gbId }" />
	    	<input name="uqId" type="hidden" value="${uq.uqId }" />
	    	<input name="userId" type="hidden" value="${uq.huaId }" />
	    	<input name="pics" type="hidden" value="${uq.pics }" /> 
	    	<input name="dredgeAddress" type="hidden" value="${fn:split(uq.location,"-")[fn:length( fn:split(uq.location,"-"))-2]}${fn:split(uq.location,"-")[fn:length( fn:split(uq.location,"-"))-1]}${uq.gbName }${uq.buildingName}-${uq.roomNum }" />
	    	<input name="tel" type="hidden" value="${uq.mobile }" />
	        <h2 class="mtop20 f14">转工单</h2>
	        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	          <tr>
	            <td width="20%"><div class="list-name">转为工单类型：</div></td>
	            <td>
		            <select id="billType" class="select_normal" datatype="*"  onchange="billTypeChange()" nullmsg="请选择工单类型！">
		                <option value="">请选择</option>
		                <option value="1">报事报修</option>
		                <option value="2">到家服务</option>
		            </select>
	            </td>
	          </tr>
	          <tr id="bigType">
	            <td><div class="list-name">服务类型：</div></td>
	            <td>
		            <select id="serviceType" name="serviceType" class="select_normal" datatype="*" nullmsg="请选择服务类型！" onchange="serviceTypeChange()">
		                <option value="">请选择</option>
		            </select>
	            </td>
	          </tr>   
	          <tr id="smallType">
	            <td><div class="list-name">服务项目：</div></td>
	            <td>
		            <select id="serviceItem" name="serviceItem" class="select_normal" datatype="*" nullmsg="请选择服务项目！" dragonfly="false" onchange="dt2Change()" >
		                <option value="">请选择</option>
		            </select>
	            </td>
	          </tr>  
	          <tr id="dredgePrdtTr">
	           <td><div class="list-name">服务商品：</div></td>
	           <td colspan="2">
	           	   <select id="dredgePrdt" name="dredgePrdtId" class="select_normal" style="width:180px;" onchange="dredgePrdtChange()"></select>
	           </td>
	         </tr>
	         <tr id="prdtSpecTr">
	           <td><div class="list-name">商品规格：</div></td>
	           <td colspan="2">
	           	   <!-- <select id="prdtSpec" name="prdtSpecId" class="select_normal" style="width:180px;"></select> -->
	           	   <input type="hidden" name="specList" />
	           	   <div id="prdtSpec"></div>
	           </td>
	         </tr>
	          <tr>
	            <td><div class="list-name">期望上门时间：</div></td>
	            <td><input name="expectDate" type="text" class="input_text icon_dt" id="date05" placeholder="选择期望上门时间" datatype="dateTime" nullmsg="请选择期望上门时间！"></td>
	          </tr>   
	        </table>
	        <table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
	          <tr>
	            <td width="20%"><div class="list-name">描述：</div></td>
	            <td class="posrelative" style="padding-bottom: 30px">
		            <textarea id="dredgeContent" class="textareasNew txt02" name="dredgeContent" rows="6" cols="" datatype="*" nullmsg="请填写内容！" errormsg="回复内容为1000字以内！">${uq.content }</textarea> 
		            <br /><div style="position: absolute; right: 5px; top: 115px;">还可以输入<span class="leftNum2">1000</span>字</div>
	            </td>
	          </tr>
	          <tr>
	            <td align="right">图片：</td>
	            <td class="item-upload-img">
	                <ul class="menu-img">
		                <c:forEach items="${uq.picList }" var="picurl">
		                    <li><a href="${picurl }" rel="lightbox-group02"><img src="${picurl }" border="0"></a></li>
		            	</c:forEach>
	                </ul>
	            </td>
	          </tr>
	        </table>
	        
			<div class="mtop20">
	    		<input id="changeBillTypeSave" class="info-btn small-btn w150" type="button" value="保存信息">
	    		<input class="weak-btn small-btn w150 mleft20 cancel-btn" type="button" value="取消">
			</div> 
	        <div class="h30"></div>
	        <div class="h30"></div>
	    </form>
    </div>
    
    <div class="mtop30 swap-content-box dsn">
	    <form id="replayUserForm" class="inputform" action="reply.json">
	    	<input name="uqId" type="hidden" value="${uq.uqId }" />
	    	<input name="userId" type="hidden" value="${uq.huaId }" />
	    	<div class="wp100 posrelative">
		        <h2 class="f14">回复用户</h2>
		        <div><input type="radio" name="replyType" value="5" datatype="*" nullmsg="请选择发起回复对象！"> 解放区回复
		        <input class="mleft20" type="radio" name="replyType" value="6" datatype="*" nullmsg="请选择发起回复对象！"> 物业回复</div>
	        	<textarea id="pushContent" class="textareasNew txt02" name="replyContent" cols="" rows="6" datatype="*" nullmsg="请填写回复内容！" errormsg="回复内容为200字以内！"></textarea>
	        	<br /><div style="position: absolute; right: 5px; top: 165px;">还可以输入<span class="leftNum">200</span>字</div>
	        </div>
		        
			<div class="mtop20">
	    		<input id="replayUserSave" class="info-btn small-btn w150" type="button" value="保存发送">
	    		<input class="weak-btn small-btn w150 mleft20 cancel-btn" type="button" value="取消">
			</div> 
	        <div class="h30"></div>
	    </form>
    </div>
	
</div>

</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.form.js?v=5"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js?v20161128"></script>
<script type="text/javascript" src="../js/picbox.js"></script>
<script type="text/javascript">
$(function(){
	//校验是否勾选规格
	$(document).on('change', '[name=speccheckbox]', function(){
		if($(this).is(':checked')){
			$(this).siblings('[name=specNum]').removeAttr('ignore').removeAttr('readonly');
		}else{
			$(this).siblings('[name=specNum]').attr('ignore','ignore').attr('readonly','readonly').val('');
		}
	});
	
    //转工单表单验证
	$("#changeBillTypeForm").Validform({
		btnSubmit:"#changeBillTypeSave", 
		tiptype:3,
		postonce:true,
		ajaxPost:true,
		ignoreHidden:true,
		beforeSubmit: function(){

			//增加规格列表，可多选
			var prdtSpecListNew = [];
			if($('.spec-item').length > 0){
				$('.spec-item').each(function(){
					
					var specItemInfo = {};
					if($(this).find('[type=checkbox]').is(':checked')){
						
						var specId = $(this).find('[name=specNum]').attr('itemId');
						var specNum = $(this).find('[name=specNum]').val();
						
						specItemInfo.specId = specId;
						specItemInfo.specNum = specNum;
						prdtSpecListNew.push(specItemInfo);
						
					}
				})
				
				//插入已选规格列表信息
				$('[name=specList]').val(JSON.stringify(prdtSpecListNew));
				
			}
			
		},
		callback:function(data){
			console.log(data);
			if(data.status=="0000"){
				alert("转单成功！");
				window.location.href = "userQuestionList.html";
			} else {
				alert(data.message);
			}
		}
	});
	
    //回复用户表单验证
	$("#replayUserForm").Validform({
		btnSubmit:"#replayUserSave", 
		tiptype:3,
		postonce:true,
		ajaxPost:true,
		callback:function(data){
			if(data.status=="0000"){
				alert("回复成功！");
				window.location.href = "userQuestionList.html";
			} else {
				alert(data.message);
			}
		}
	});
	
	//输入字数限制
	countTextNum('#pushContent', '.leftNum', 200);
	countTextNum('#dredgeContent', '.leftNum2', 1000);
	
	//切换
	swapContent('.swap-tab', '.swap-content-box');
	
	//取消
	$(".cancel-btn").click(function(){
		$(this).parents('.swap-content-box').hide();
		window.parent.TuneHeight();
	})
});

function swapContent(tabObj, contentObj){
	$(tabObj).click(function(){
		var curIndex = $(tabObj).index(this);
		$(contentObj).hide().eq(curIndex).show();
		window.parent.TuneHeight();
	})
}

var serviceTypes = null;//全局变量，用来存储上门服务的所有类型
var oldRepairId = null;
var thzUserId = null;
function serviceTypeChange(){
	var itemTypeId = $("#serviceType").val();
	var parentId = $("#serviceType").find("option:selected").attr("parentId");
	$("#dredgePrdt").empty();
	$("#prdtSpec").empty();
	for(var i=0; i<serviceTypes.length; i++){
		var serviceType = serviceTypes[i];
		if(serviceType.id==parentId){
			var items = serviceType.item;
			for(var k=0; k<items.length; k++){
				if(items[k].id==itemTypeId){
					var subTypeList = items[k].dredgeType2ndList;
					if(subTypeList.length == 0){
						$("#serviceItem").empty().parents("tr").hide();
					}else{
						$("#serviceItem").empty().parents("tr").show();
						for(var j=0; j<subTypeList.length; j++){
							$("#serviceItem").append("<option value='"+subTypeList[j].id+"'>"+subTypeList[j].name+"</option>");
						}
						dt2Change();
					}
					break;
				}
			}
			break;
		}
	}
}

var dredgePrdtList;
function dt2Change(){
	var serviceItem =$("#serviceItem").val();
	$.post("../dredge/qryProductList.json?dt2Id=" + serviceItem,function(data){
		$("#dredgePrdt").empty();
		$("#prdtSpec").empty();
		dredgePrdtList = data.dataValue.list;
		for(var i=0; i<dredgePrdtList.length; i++){
			var dredgePrdt = dredgePrdtList[i];
			$("#dredgePrdt").append("<option value='"+dredgePrdt.id+"'" + "'>"+dredgePrdt.fullName+"</option>");
		}
		if(dredgePrdtList.length>0){
			var dredgePrdt = dredgePrdtList[0];
			var items = dredgePrdt.prdtSpecList;
			if(items.length>0){
				for(var k=0; k<items.length; k++){
					//$("#prdtSpec").append("<option value='"+items[k].id+"' parentId='"+dredgePrdt.id+"'>"+items[k].specification+"</option>");
					if(k === 0){
						$("#prdtSpec").append('<div class="spec-item"><input class="mright10" type="checkbox" name="speccheckbox" datatype="*" nullmsg="请选择规格" />' + items[k].specification + '<input itemId="' + items[k].id + '" parentId="' + dredgePrdt.id + '" type="text" class="input_text mleft10 w30" maxlength="3" name="specNum" readonly="readonly" ignore="ignore" datatype="fei0zhengzhengshu" nullmsg="请输入规格数量" /><span class="Validform_checktip"></span></div>');
					}else{
						$("#prdtSpec").append('<div class="spec-item mtop10"><input class="mright10" type="checkbox" name="speccheckbox" />' + items[k].specification + '<input itemId="' + items[k].id + '" parentId="' + dredgePrdt.id + '" type="text" class="input_text mleft10 w30" maxlength="3" name="specNum" readonly="readonly" ignore="ignore" datatype="fei0zhengzhengshu" nullmsg="请输入规格数量" /><span class="Validform_checktip"></span></div>');
					}
				}

				window.parent.TuneHeight();
				//循环了两次服务商品？？？
				//dredgePrdtChange();
			}
		}
	});
}

function dredgePrdtChange(){
	var dredgePrdtId =$("#dredgePrdt").val();
	$("#prdtSpec").empty();
	for(var i=0; i<dredgePrdtList.length; i++){
		var dredgePrdt = dredgePrdtList[i];
		if(dredgePrdtId == dredgePrdt.id){
			var items = dredgePrdt.prdtSpecList;
			for(var k=0; k<items.length; k++){
				//$("#prdtSpec").append("<option value='"+items[k].id+"' parentId='"+dredgePrdt.id+"'>"+items[k].specification+"</option>");
				if(k === 0){
					$("#prdtSpec").append('<div class="spec-item"><input class="mright10" type="checkbox" name="speccheckbox" datatype="*" nullmsg="请选择规格" />' + items[k].specification + '<input itemId="' + items[k].id + '" parentId="' + dredgePrdt.id + '" type="text" class="input_text mleft10 w30" maxlength="3" name="specNum" readonly="readonly" ignore="ignore" datatype="fei0zhengzhengshu" nullmsg="请输入规格数量" /><span class="Validform_checktip"></span></div>');
				}else{
					$("#prdtSpec").append('<div class="spec-item mtop10"><input class="mright10" type="checkbox" name="speccheckbox" />' + items[k].specification + '<input itemId="' + items[k].id + '" parentId="' + dredgePrdt.id + '" type="text" class="input_text mleft10 w30" maxlength="3" name="specNum" readonly="readonly" ignore="ignore" datatype="fei0zhengzhengshu" nullmsg="请输入规格数量" /><span class="Validform_checktip"></span></div>');
				}
			}
			window.parent.TuneHeight();
		}
	}
}

function billTypeChange(){
	var billType = $("#billType").find("option:selected").val();
	var roomId = $("#roomId").val();
	if(billType == 1){ //1报事报修
		$("#changeBillTypeForm").attr("action","convertToPropertyRepairBill.json");
		$("#serviceItem").empty().parents("tr").hide();
		$("#dredgePrdt").empty().parents("tr").hide();
		$("#prdtSpec").empty().parents("tr").hide();
		$.ajax({
			url: '../propertyRepair/qryIsOpenRepaireServiceByRoomId.json',
			dataType: 'json',
			data:{'roomId':roomId},
			async: true,
			timeout: 10000,
			success: function(data){
				if(data.status=="0000"){
					console.log(data.dataValue.isOpenService);
					if(data.dataValue.isOpenService == 1){
						//已开通服务，查询提供的服务类型
						$.ajax({
							url: '../propertyRepair/qryPrTypeListByRoomId.json',
							dataType: 'json',
							data:{'roomId':roomId},
							async: true,
							timeout: 10000,
							success: function(data){
								if(data.status=="0000"){
									console.log(data.dataValue.prtList);
									var option = "<option value=''>请选择</option>";
									$.each(data.dataValue.prtList,function(i,prt){
										option += "<option value=" + prt.baseTypeId + ">" + prt.name +"</option>";
									});	
									$("#serviceType").html(option);
								} else {
									alert(data.message);
								}
							},
							complete: function(XMLHttpRequest,status){
								if(status === 'timeout'){
									alert('网络不给力，请重试');
								}
							},
							error: function(){
							}
						});
					}else{
						//没开通服务  
						alert("该单所在的小区还没有开通报事报修服务");
						$("#serviceType").val("");
						$("#billType").val("");
					}
				} else {
					alert(data.message);
				}
			},
			complete: function(XMLHttpRequest,status){
				if(status === 'timeout'){
					alert('网络不给力，请重试');
				}
			},
			error: function(){
			}
		});
	}else if(billType == 2){// 2到家服务
		$("#serviceType").html("<option value=''>请选择</option>");
		$("#changeBillTypeForm").attr("action","convertToDredgeBill.json");
		$("#dredgePrdt").empty().parents("tr").show();
		$("#prdtSpec").empty().parents("tr").show();
		$.post("../propertyRepair/queryServiceTypes.json", {"userId":"${uq.huaId}",async:false}, function(data){
			$("#serviceType").empty();
			serviceTypes = data.dataValue.list;
			for(var i=0; i<serviceTypes.length; i++){
				var serviceType = serviceTypes[i];
				var optgroup = $("<optgroup label='"+serviceType.name+"'/>");
				var items = serviceType.item;
				for(var k=0; k<items.length; k++){
					optgroup.append("<option value='"+items[k].id+"' parentId='"+serviceType.id+"'>"+items[k].name+"</option>");
				}
				$("#serviceType").append(optgroup);
			}
			if(serviceTypes.length>0){//预设为第一个类型，同时带出其子类
				var serviceType = serviceTypes[0].item;
				if(serviceType.length>0){
					var item = serviceType[0];
					if(item.dredgeType2ndList.length>0){
						var subTypeList = item.dredgeType2ndList;
						$("#serviceItem").empty().parents("tr").show();
						for(var k=0; k<subTypeList.length; k++){
							$("#serviceItem").append("<option value='"+subTypeList[k].id+"'>"+subTypeList[k].name+"</option>");
						}
						dt2Change();
					}else{
						$("#serviceItem").empty().parents("tr").hide();
					}
				}
			}
		});
	}else{
		$("#serviceType").html("<option value=''>请选择</option>");
		$("#serviceItem").html("<option value=''>请选择</option>").parents("tr").show();
	}
}
</script>
<script type="text/javascript">
	twemoji.parse(document.body, {"size":72});
</script>
</html>
