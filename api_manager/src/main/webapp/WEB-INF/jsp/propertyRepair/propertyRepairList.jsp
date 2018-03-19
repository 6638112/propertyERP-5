<%@page import="com.cnfantasia.server.ms.pub.utils.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修管理-报修工单管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css?v20160718">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="../css/select2.css">
<style>
	.icon_dt{background: #fff url(../images/icon_dt.jpg) no-repeat 150px 3px;}
	.w180{width: 170px;}
	.blue{color:blue;}
</style>
</head>

<body>
<div class="info main-info-box01">
    <h2>报修工单管理</h2>
    <div class="bs-example bgebeb">
    <form action="../propertyRepair/searchRepair.html">
        <table class="info-list " border="0">
          <tr>
          	<td><div align="right">工单编号：</div></td>
            <td><input type="text" name="repairNumber" class="input_text w100 pp" value="${param.repairNumber }"></td>
          	<td><div align="right">解放号：</div></td>
            <td><input type="text" name="huaId" class="input_text w80 pp" value="${param.huaId }"></td>
          	<td><div align="right">小区：</div></td>
		  	<td>
			  <select id="groupBuiliding" name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
				  <option value="">选择小区</option>
			  </select>
		  	</td>
          </tr>
          <tr>
            <td align="right">报修类别：</td>
            <td>
           		<select class="select_normal" name="repairType">
                    <option value="">全部</option>
	            	<c:forEach items="${prTypeSet}" var="prTypeName">
	                    <option value="${prTypeName }" <c:if test="${param.repairType eq prTypeName}">selected</c:if> >${prTypeName }</option>
	            	</c:forEach>
                   </select></td>
            <td><div class="list-name">状态：</div></td>
            <td>
                <select class="select_normal" name="repairState">
	                 <option value="" >全部</option>
	                 <option value="0" <c:if test="${not empty param.repairState and param.repairState eq 0}">selected</c:if>>待处理</option>
	                 <!-- <option value="1">已取消</option> -->
	                 <option value="2" <c:if test="${param.repairState eq 2}">selected</c:if>>已分配</option>
	                 <option value="3" <c:if test="${param.repairState eq 3}">selected</c:if>>已结束</option>
                </select>
            </td>
            <td><div class="list-name">处理人：</div></td>
            <td>
                  <select class="select_normal" name="repairer">
                 	  <option value="">全部</option>
	                  <c:forEach items="${propertyRepairerList }" var="repairer">
	                  	<option value="${repairer.name }" <c:if test="${param.repairer eq repairer.name}">selected</c:if> >${repairer.name }</option>
	                  </c:forEach>
                  </select>
            </td>
          </tr>
          <tr>
            <td><div class="list-name">创建时间：</div></td>
            <td><input type="text" class="input_text icon_dt pp nocheck" id="date01" name="createTimeBegin" title="请选择起始时间" value="${param.createTimeBegin }"> 至
            	 <input type="text" class="input_text icon_dt pp nocheck" id="date02" name="createTimeEnd" title="请选择结束时间" value="${param.createTimeEnd }"></td>
            <td><div align="right">派单时间：</div></td>
            <td><input type="text" class="input_text icon_dt pp nocheck" id="date01" name="assignTimeBegin" title="请选择起始时间" value="${param.assignTimeBegin }"> 
            	至 <input type="text" class="input_text icon_dt pp nocheck" id="date02" name="assignTimeEnd" title="请选择结束时间" value="${param.assignTimeEnd }"></td>
            <td><div align="right">工单结束时间：</div></td>
            <td><input type="text" class="input_text icon_dt pp nocheck" id="date01" name="finishTimeBegin" title="请选择起始时间" value="${param.finishTimeBegin }"> 
            	至 <input type="text" class="input_text icon_dt pp nocheck" id="date02" name="finishTimeEnd" title="请选择结束时间" value="${param.finishTimeEnd }"></td>
          </tr>
        </table>
  		<div class="mtop10 t_center"><input class="input-btn w200" type="submit" value="搜索" /></div>
     </form>
    </div>
    <h2 class="mtop40">报修处理流程 
        <span class="f12 mar-left15">
            <span class="step orange"><span class="f16 bold">流程 1</span>：报修单管理</span>
            <span class="step"><span class="grey">-></span> <span class="f16 bold">流程 2</span>：分配处理人</span>
            <span class="step"><span class="grey">-></span> <span class="f16 bold">流程 3</span>：师傅上门处理</span>
            <span class="step"><span class="grey">-></span> <span class="f16 bold">流程 4</span>：管理处关闭提单</span>
        </span>
    </h2>
	<input type="hidden" id="inToOutType" value="1">
	<display:table class="info-list-02 mtop20" cellpadding="0" cellspacing="1"  name="resList" id="row" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize"> 
    	<display:column title="工单编号" class="blue repairDetails">
			<c:if test="${row.tPropertyRepairTypeFId != -100}">
				<a href="../propertyRepair/viewRepairDetail.html?repairId=${row.id}" class="blue">${row.number }</a>
			</c:if>
			<c:if test="${row.tPropertyRepairTypeFId == -100}">
				<a href="../propertyRepair/viewRepairDetail.html?dredgeId=${row.id}" class="blue">${row.number }</a>
			</c:if>
    	</display:column>
    	<display:column property="sys0AddTime" title="创建时间" />
    	<display:column property="repairTypeName" title="报修类型" />

    	<display:column property="sys0AddUser" title="解放号" />
    	<display:column property="address" title="报修地址" />
    	<display:column property="repairContent" title="报修描述" maxLength="20" />
    	<display:column property="pmName" title="管理处" />
    	<display:column title="预约上门时间" >
			<c:if test="${row.tPropertyRepairTypeFId != -100}">
				${fn:substring(row.expectDate,0,10)} ${fn:substring(row.expectTimeBegin,0,5)}
			</c:if>
			<c:if test="${row.tPropertyRepairTypeFId == -100}">${fn:substring(row.expectDate,0,16)}</c:if>
    	</display:column>
    	<display:column title="派单时间" property="asignTime" />
    	<display:column title="工单结束时间" property="finishedTime">
    	</display:column>
		<display:column title="订单总额">
			<c:if test="${row.selfBuyAmount + row.manuFeeAmount + row.otherAmount > 0}">
				${(row.selfBuyAmount + row.manuFeeAmount + row.otherAmount) div 100}
			</c:if>
		</display:column>

    	<display:column title="状态" >
			<c:if test="${row.tPropertyRepairTypeFId != -100}">
    		<c:choose> 
    			<c:when test="${row.repaireState==0 }"><span class='pending'>待处理</span></c:when>
    			<%-- <c:when test="${row.repaireState==1 }">已取消</c:when> --%>
    			<c:when test="${row.repaireState==2 }"><span class='pended'>已分配</span></c:when>
    			<c:when test="${row.repaireState==3 }">已结束</c:when>
    		</c:choose>
			</c:if>
			<c:if test="${row.tPropertyRepairTypeFId == -100}">
				<c:choose>
					<c:when test="${row.repaireState == 1}"><span class="pending">待处理</span></c:when>
					<c:when test="${row.repaireState == 2}"><span class='pended'>已分配</span></c:when>
					<c:when test="${row.repaireState == 6}">已关闭</c:when>
					<c:otherwise>已完成</c:otherwise>
				</c:choose>
			</c:if>
    	</display:column>
    	<display:column  title="处理人" > ${row.propertyRepairer.name } ${row.propertyRepairer.tel }</display:column>
    	<display:column title="操作" >
			<c:if test="${row.tPropertyRepairTypeFId != -100}">
				<c:if test="${row.repaireState==0 }">
					<a class="blue editRepair" href="../propertyRepair/assignRepair.html?repairId=${row.id }">分配处理人</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${row.repaireState!=3}">
					<a class="blue deleteRepairer" href="../propertyRepair/closeRepair.html?repairId=${row.id}">关闭此单</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${isKefu && (row.repaireState eq 3) and (empty row.tPropertyRepairerFId) and (row.isTransed ne 1)}">
					<a class="blue editRepair" href="javascript:showInToOutDialog('${row.id}', '${row.tUserFId}', 1);">内转外</a>
				</c:if>
			</c:if>
			<c:if test="${row.tPropertyRepairTypeFId == -100}">
				<c:if test="${row.repaireState == 1}">
					<a class="blue editRepair" href="../propertyRepair/assignRepair.html?dredgeId=${row.id }">分配处理人</a>
				</c:if>
				<c:if test="${row.repaireState == 1}">
					<a class="blue editRepair" href="javascript:closeDredge(${row.id})">关闭此单</a>
				</c:if>
				<c:if test="${row.repaireState == 6 && isKefu}">
					<a class="blue editRepair" href="javascript:showInToOutDialog('${row.id}', '${row.tUserFId}', 3);">内转外</a>
				</c:if>
			</c:if>
    	</display:column>
    </display:table>
</div>

<div class="info main-info-box02" style="display:none">
    <h2 class="toggleSearch">报修工单管理 <span class="f12 blue slideText pointer">收起</span></h2>
    <div class="bs-example bgebeb">
    <form action="../propertyRepair/searchRepair.html">
        <table class="info-list " border="0">
          <tr>
          	<td><div align="right">解放号：</div></td>
            <td><input type="text" name="huaId" class="input_text w100"></td>
          </tr>
          <tr>
          	<td><div align="right">小区：</div></td>
            <td><input type="text" name="gbName" class="input_text w100"></td>
          </tr>
          <tr>
            <td><div class="list-name">状态：</div></td>
            <td>
                <select class="select_normal" name="repairState">
	                 <option value="">全部</option>
	                 <option value="0">待处理</option>
	                 <!-- <option value="1">已取消</option> -->
	                 <option value="2">已分配</option>
	                 <option value="3">已结束</option>
                </select>
            </td>
          </tr>
          <tr>
            <td><div class="list-name">创建时间：</div></td>
            <td><input type="text" class="input_text icon_dt nocheck" id="date01" name="createTimeBegin" title="请选择起始时间" value=""> 至</td>
          </tr>
          <tr>
            <td></td>
            <td><input type="text" class="input_text icon_dt nocheck" id="date02" name="createTimeEnd" title="请选择结束时间" value=""></td>
          </tr>
        </table>
  		<div class="mtop10 t_center"><input class="input-btn w200" type="submit" value="搜索" /></div>
     </form>
    </div>
    
    
    <table class="info-list-02 mtop10 new-order-list" style="border-bottom:2px solid #eeeeee;" cellpadding="0" cellspacing="1">
		<tbody>
		</tbody>
	</table>
    
    <div id="newOrderListBox"></div>
    
	<display:table class="info-list-02 mtop20 org-order-list" style="display:none" cellpadding="0" cellspacing="1"  name="resList" id="row" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize"> 
    	<display:column title="工单编号" class="blue repairDetails">
			<c:if test="${row.tPropertyRepairTypeFId != -100}">
				<a class="cur-order-url" href="../propertyRepair/viewRepairDetail.html?repairId=${row.id}" class="blue">${row.number }</a>
			</c:if>
			<c:if test="${row.tPropertyRepairTypeFId == -100}">
				<a class="cur-order-url" href="../propertyRepair/viewRepairDetail.html?dredgeId=${row.id}" class="blue">${row.number }</a>
			</c:if>
    	</display:column>
    	<display:column property="sys0AddTime" title="创建时间" />
    	<display:column property="repairTypeName" title="报修类型" />
    	    	<display:column property="address" title="报修地址" />
    	<display:column title="预约上门时间" >
			<c:if test="${row.tPropertyRepairTypeFId != -100}">
				${fn:substring(row.expectDate,0,10)} ${fn:substring(row.expectTimeBegin,0,5)}
			</c:if>
			<c:if test="${row.tPropertyRepairTypeFId == -100}">${fn:substring(row.expectDate,0,16)}</c:if>
    	</display:column>
		<display:column title="订单总额">
			<c:if test="${row.selfBuyAmount + row.manuFeeAmount + row.otherAmount > 0}">
				${(row.selfBuyAmount + row.manuFeeAmount + row.otherAmount) div 100}
			</c:if>
		</display:column>
    	<display:column title="状态" >
			<c:if test="${row.tPropertyRepairTypeFId != -100}">
    		<c:choose> 
    			<c:when test="${row.repaireState==0 }"><span class='pending'>待处理</span></c:when>
    			<%-- <c:when test="${row.repaireState==1 }">已取消</c:when> --%>
    			<c:when test="${row.repaireState==2 }"><span class='pended'>已分配</span></c:when>
    			<c:when test="${row.repaireState==3 }">已结束</c:when>
    		</c:choose>
			</c:if>
			<c:if test="${row.tPropertyRepairTypeFId == -100}">
				<c:choose>
					<c:when test="${row.repaireState == 1}"><span class="pending">待处理</span></c:when>
					<c:when test="${row.repaireState == 2}"><span class='pended'>已分配</span></c:when>
					<c:when test="${row.repaireState == 6}">已关闭</c:when>
					<c:otherwise>已完成</c:otherwise>
				</c:choose>
			</c:if>
    	</display:column>
    	<display:column  title="处理人" > ${row.propertyRepairer.name } ${row.propertyRepairer.tel }</display:column>
    	<display:column title="操作" >
			<c:if test="${row.tPropertyRepairTypeFId != -100}">
				<c:if test="${row.repaireState==0 }">
					<a class="blue editRepair" href="../propertyRepair/assignRepair.html?repairId=${row.id }">分配处理人</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${row.repaireState!=3}">
					<a class="blue deleteRepairer" href="../propertyRepair/closeRepair.html?repairId=${row.id}">关闭此单</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${isKefu && (row.repaireState eq 3) and (empty row.tPropertyRepairerFId) and (row.isTransed ne 1)}">
					<a class="blue editRepair" href="javascript:showInToOutDialog('${row.id}', '${row.tUserFId}', 1);">内转外</a>
				</c:if>
			</c:if>
			<c:if test="${row.tPropertyRepairTypeFId == -100}">
				<c:if test="${row.repaireState == 1}">
					<a class="blue editRepair" href="../propertyRepair/assignRepair.html?dredgeId=${row.id }">分配处理人</a>
				</c:if>
				<c:if test="${row.repaireState == 1}">
					<a class="blue editRepair" href="javascript:closeDredge(${row.id})">关闭此单</a>
				</c:if>
				<c:if test="${row.repaireState == 6 && isKefu}">
					<a class="blue editRepair" href="javascript:showInToOutDialog('${row.id}', '${row.tUserFId}', 3);">内转外</a>
				</c:if>
			</c:if>
    	</display:column>
    </display:table>
</div>
<div id="inToOut" class="dsn" style="height: auto;padding-top:15px;padding-bottom:50px;padding-left: 15px;padding-right: 15px;">
  <form id="msgForm" enctype="multipart/form-data" action="${pageContext.request.contextPath}/groupBuilding/sendMsg.html" method="post" onsubmit="return false;"> 
    <h2>内转外设置</h2>
    <div class="bs-example bgebeb" style="width:100%; height: 270px; overflow-y: scroll;">
		<table class="info-list" border="0">
	         <tr>
	           <td><div class="list-name bold">服务类型：</div></td>
	           <td colspan="2">
	           	   <select id="serviceType" name="serviceType" class="select_normal" style="width:180px;" onchange="serviceTypeChange()"></select>
	           </td>
	         </tr>
	         <tr id="subTypeTr">
	           <td><div class="list-name bold">具体类目：</div></td>
	           <td colspan="2">
	           	   <select id="serviceItem" name="serviceItem" class="select_normal" style="width:180px;" onchange="dt2Change()"></select>
	           </td>
	         </tr>
	         <tr id="dredgePrdtTr">
	           <td><div class="list-name bold">服务商品：</div></td>
	           <td colspan="2">
	           	   <select id="dredgePrdt" name="dredgePrdt" class="select_normal" style="width:180px;" onchange="dredgePrdtChange()"></select>
	           </td>
	         </tr>
	         <tr id="prdtSpecTr">
	           <td><div class="list-name bold">商品规格：</div></td>
	           <td colspan="2">
	           	   <!-- <select id="prdtSpec" name="prdtSpec" class="select_normal" style="width:180px;"></select> -->
	           	   <div id="prdtSpec"></div>
	           </td>
	         </tr>
	         <tr>
	           <td><div class="list-name bold">预约时间：</div></td>
	           <td colspan="2">
	           	 <input type="text" class="input_text pp w180 icon_dt nocheck" id="expectDate" name="expectDate" readonly="readonly" value="" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate:'<%=DateUtils.getCurrentDate()%>'});">
	           </td>
	         </tr>
	         <tr>
	         	<td colspan="3" style="text-align: right;">
	         		<button class="sendBtn input-btn w160" onclick="inToOut()">提交</button>
	         	</td>
	         </tr>
    	 </table>
     </div>
	</form>
</div>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js?v20170310"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript" >
	$(function(){
		//待分配行，整行为红
		$('.pending').parents('tr').addClass('red');
		$('.pended').parents('tr').addClass('blue');
		
		//点击tr整行进入详情页
		$(document).on('click', '#newOrderListBox .info-list-02 tbody tr:not(:last-child)', function(){
			var thisUrl = $(this).parent('tbody').find('.cur-order-url').attr('href');
			location.href = thisUrl;
		});
		
		//点击展开收起
		$('.toggleSearch').click(function(){
			$('.info-list').slideToggle();
			$('.slideText').swapText('展开','收起');
		});
		
		
		
		//重新排列订单形式
		function resetOrderList(objClass){
			var $orgTheadTr = $(objClass + ' thead tr');
			var $orgTbodyTrs = $(objClass + ' tbody tr');
			var $orderListTable = $('.new-order-list');
			
			$orgTbodyTrs.each(function(){
				var $orderListTableClone = $orderListTable.clone(true);
				var $curTr = $(this);
				$curTr.find('td').each(function(i){
					var trString = '<tr class="nobg"><td width="50%" class="blod">' + 
									$orgTheadTr.find('th').eq(i).text() + 
									'</td><td>' + 
									$(this).html() + 
									'</td></tr>';
					$orderListTableClone.append(trString);
				});
				$orderListTableClone.appendTo('#newOrderListBox');
				window.parent.TuneHeight();
			});
		}
		

		var u = navigator.userAgent, app = navigator.appVersion;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		
		if(isAndroid || isiOS){
			$('.main-info-box01').hide();
			
			//$('.main-info-box02 .org-order-list').hide();
			$('.main-info-box02').show();
			resetOrderList('.org-order-list');
		}
		
	});
	
	var serviceTypes = null;
	var oldRepairId = null;
	var thzUserId = null;
	function serviceTypeChange(){
		var itemTypeId = $("#serviceType").val();
		var parentId = $("#serviceType").find("option:selected").attr("parentId");
		//清空服务商品和规格
		$("#dredgePrdt").empty();
		$("#prdtSpec").empty();
		
		for(var i=0; i<serviceTypes.length; i++){
			var serviceType = serviceTypes[i];
			if(serviceType.id==parentId){
				var items = serviceType.item;
				for(var k=0; k<items.length; k++){
					if(items[k].id==itemTypeId){
						var subTypeList = items[k].dredgeType2ndList;
						if(subTypeList.length > 0){
							$('#subTypeTr').show();
							$("#serviceItem").empty();
							for(var j=0; j<subTypeList.length; j++){
								$("#serviceItem").append("<option value='"+subTypeList[j].id+"'>"+subTypeList[j].name+"</option>");
							}
							dt2Change();
						}else{
							$('#subTypeTr').hide();
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
		$("#dredgePrdt").empty();
		$.post("../dredge/qryProductList.json?dt2Id=" + serviceItem,function(data){
			dredgePrdtList = data.dataValue.list;
			for(var i=0; i<dredgePrdtList.length; i++){
				var dredgePrdt = dredgePrdtList[i];
				$("#dredgePrdt").append("<option value='"+dredgePrdt.id+"'" + "'>"+dredgePrdt.fullName+"</option>");
			}
			$("#prdtSpec").empty();
			if(dredgePrdtList.length>0){
				var dredgePrdt = dredgePrdtList[0];
				var items = dredgePrdt.prdtSpecList;
				if(items.length>0){
					for(var k=0; k<items.length; k++){
						//$("#prdtSpec").append("<option value='"+items[k].id+"' parentId='"+dredgePrdt.id+"'>"+items[k].specification+"</option>");
						$("#prdtSpec").append('<div class="spec-item"><input class="mright10" type="checkbox" />' + items[k].specification + '<input itemId="' + items[k].id + '" parentId="' + dredgePrdt.id + '" type="text" class="input_text mleft10 w30" maxlength="3" name="specNum" /></div>');
					}
					dredgePrdtChange();
				}
			}
		});
	}
	
	function dredgePrdtChange(){
		var dredgePrdtId =$("#dredgePrdt").val();
		for(var i=0; i<dredgePrdtList.length; i++){
			var dredgePrdt = dredgePrdtList[i];
			if(dredgePrdtId == dredgePrdt.id){
				$("#prdtSpec").empty();
				var items = dredgePrdt.prdtSpecList;
				for(var k=0; k<items.length; k++){
					//$("#prdtSpec").append("<option value='"+items[k].id+"' parentId='"+dredgePrdt.id+"'>"+items[k].specification+"</option>");
					$("#prdtSpec").append('<div class="spec-item"><input class="mright10" type="checkbox" />' + items[k].specification + '<input itemId="' + items[k].id + '" parentId="' + dredgePrdt.id + '" type="text" class="input_text mleft10 w30" maxlength="3" name="specNum" /></div>');
				}
			}
		}
	}

	
	function showInToOutDialog(repairId, userId, type){
		$('#inToOutType').val(type);
		if(oldRepairId==null || oldRepairId!=repairId){
			thzUserId = userId;
			oldRepairId = repairId;
			$.post("../propertyRepair/queryServiceTypes.json", {"userId":userId}, function(data){
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
				if(serviceTypes.length>0){
					var serviceType = serviceTypes[0].item;
					if(serviceType.length>0){
						var item = serviceType[0];
						if(item.dredgeType2ndList.length>0){
							var subTypeList = item.dredgeType2ndList;
							$("#serviceItem").empty();
							for(var k=0; k<subTypeList.length; k++){
								$("#serviceItem").append("<option value='"+subTypeList[k].id+"'>"+subTypeList[k].name+"</option>");
							}
						}
					}
				}
				
				serviceTypeChange();
			});
		}
		
		$.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['350px', 'auto'],
			title: false,
	    	border : [5, 0.3, '#000'],
			page: {dom : '#inToOut'}
		});
	}
	
	function inToOut(){
		var serviceType = $("#serviceType").val();
		var serviceItem = $("#serviceItem").val();
		var dredgePrdtId =$("#dredgePrdt").val();
		var prdtSpecId =$("#prdtSpec").val();
		
		var expectDate = $("#expectDate").val();
		var inToOutType = $('#inToOutType').val();
		
		//增加规格列表，可多选
		var prdtSpecListNew = [];
		var hasSelectSpec = true;
		var hasSelectSpecNum = 0;
		if($('.spec-item').length > 0){
			$('.spec-item').each(function(){
				
				var specItemInfo = {};
				if($(this).find('[type=checkbox]').is(':checked')){
					
					var specId = $(this).find('[name=specNum]').attr('itemId');
					var specNum = $(this).find('[name=specNum]').val();
					var reg = /^\+?[1-9][0-9]*$/;
					
					//至少选择一个规格
					hasSelectSpecNum += 1;
					
					if(specNum === ''){
						alert('请输入规格数量');
						$(this).find('[name=specNum]').focus();
						hasSelectSpec = false;
						return;
					}
					
					if( !reg.test( Number(specNum) )){
						alert('请输入数字');
						$(this).find('[name=specNum]').focus();
						hasSelectSpec = false;
						return;
					}
					
					specItemInfo.specId = specId;
					specItemInfo.specNum = specNum;
					prdtSpecListNew.push(specItemInfo);
					
				}else{
					if($(this).find('[name=specNum]').val() !== ''){
						alert('请勾选规格');
						$(this).find('[name=specNum]').focus();
						hasSelectSpec = false;

					}
				}
			});
			
			if(hasSelectSpecNum === 0){
				alert('至少选择一种规格');
				return;
			}
		}
		//没有选择规格，返回
		if(!hasSelectSpec){
			return;
		}
		
		if(serviceType==""){
			alert("请选择服务类型！");
			$("#serviceType").focus();
			return;
		}
		if(expectDate==""){
			alert("请设置预约时间！");
			$("#expectDate").focus();
			return;
		}
		if (inToOutType == 1) {
			$.post("../propertyRepair/inToOut.html", 
					{"userId":thzUserId,"repairId":oldRepairId, 
					 "dredgeTypeId":serviceType, "subTypeId":serviceItem, 
					 "dredgeProductId":dredgePrdtId,"specList":JSON.stringify(prdtSpecListNew),
					 "expectDate":expectDate}, function(data){
				if(data.status=="0000"){
					$(".xubox_close").click();
					alert("操作成功！");
					location=location;
				} else {
					alert("操作失败！");
				}
			});
		}
		if(inToOutType == 3) {
			$.post("../dredge/turnBillType.html", 
					{"dredgeId":oldRepairId, "dredgeTypeId":serviceType, 
					 "dredgeProductId":dredgePrdtId,"specList":JSON.stringify(prdtSpecListNew),
					 "subTypeId":serviceItem, "expectDate":expectDate}, function(data){
				if(data.status=="0000"){
					$(".xubox_close").click();
					alert("操作成功！");
					location=location;
				} else {
					alert("操作失败！");
				}
			});
		}

	}
	function closeDredge(dredgeId) {
		if(confirm("确定关闭此单？")) {
			$.post("../dredge/closeRepair.html", {"dredgeId":dredgeId}, function(data){
				if(data.status=="0000"){
					alert("操作成功！");
					window.location.reload();
				} else {
					alert("操作失败！");
				}
			});
		}

	}

	$(function(){
		var gbName = '${param.gbName}';
		$.ajax({
			url: '<%=basePath%>/groupBuilding/getGroupBuildings.json',
			dataType: 'json',
			success: function (data) {
				var list = JSON.parse(data);
				var strHtml = "<option value=''>选择小区</option>";
				$.each(list, function (i, item) {
					var str = "";
					if(gbName == item.name) {
						str = "<option value='" + item.name + "' selected='selected'>" + item.name + "</option>";
					} else {
						str = "<option value='" + item.name + "'>" + item.name + "</option>";
					}
					strHtml += str;
				});
				$("#groupBuiliding").html(strHtml);
				$('.select2_class').select2();
			}
		});
	});
</script>
</html>
