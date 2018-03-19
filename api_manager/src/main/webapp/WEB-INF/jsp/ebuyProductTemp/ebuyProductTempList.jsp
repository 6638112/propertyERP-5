<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="../css/picbox.css" />
</head>

<body>
<div class="info">
    <h2>电商商品同步管理</h2>
    
    <form id="importform" action="../ebuyProductTemp/importDataHandller.html" >
	    <div class="bs-example">
	    	<select id="supplyMerchantSelect" class="select_normal" name="supplyMerchant">
	    		<option value="hjx" selected="selected">海吉星</option>
	    		<option value="zl">中粮</option>
	    	</select>
	    	<input id="importBtn" class="info-btn small-btn w100 mleft10" type="submit" value="开始导入" />
	    </div>
    </form><br/>

    <div class="bs-example bgebeb">
    	<form action="../ebuyProductTemp/search.html">
	        <table class="info-list" border="0">
	          <tr>
	          	<td align="right">商品名称：</td>
	          	<td><input class="input_text pp" value="${prdtName}" type="text" name="prdtName"></td>
	          	<td align="right">抓取时间：</td>
	          	<td colspan="2"><input id="date01" value="${startTime}" name="startTime" readonly type="text" class="input_text icon_dt"  title="请选择起始时间" > 至 <input  type="text" readonly class="input_text icon_dt" id="date02"  value="${endTime}" name="endTime" title="请选择结束时间"></td>
	           	<td><div align="right">供应商：</div></td>
	            <td>
	            	<select class="select_normal w131" name="supplyName">
	                    <option value="">所有</option>
	                    <option value="200" <c:if test="${supplyName == 200}">selected</c:if>>海吉星</option>
	                    <option value="202" <c:if test="${supplyName == 202}">selected</c:if>>中粮</option>
	                    <option value="204" <c:if test="${supplyName == 204}">selected</c:if>>依谷网(全国)</option>
	                    <option value="212" <c:if test="${supplyName == 212}">selected</c:if>>依谷网(深圳)</option>
	                </select>
	            </td>
	            <td><div align="right">状态：</div></td>
	            <td>
	            	<select class="select_normal w131" name="isSync">
	                    <option value="-1" <c:if test="${isSync == -1}">selected</c:if>>所有</option>
	                    <option value="0" <c:if test="${isSync == 0}">selected</c:if>>未同步</option>
	                    <option value="1" <c:if test="${isSync == 1}">selected</c:if>>已同步</option>
	                </select>
	            </td>
	            <td><input class="input-btn w80" type="submit" value="查询"/></td>
	          </tr>
	        </table>
        </form>
    </div>    

    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="<input id='checkAll' name='checkbox' type='checkbox' value=''> 全选"  >
			<c:if test="${row.isSync==0 }">
				<input class="input_text page-num sort-num pp" type="checkbox" name="eptId" value="${row.id }" />
			</c:if>
		</display:column>
		
		<display:column title="商品名称" >
			<c:if test="${row.tSupplyMerchantFId ==200}"><a class="blue" href="http://www.hjxmall.com/space/sell_show.php?id=${row.srcId }" target="_blank">${row.name }</a></c:if>
			<c:if test="${row.tSupplyMerchantFId ==204}"><a class="blue" href="http://www.egu365.com/jiefangqu/product.action?goods_id=${row.srcId }" target="_blank">${row.name }</a></c:if>
			<c:if test="${row.tSupplyMerchantFId ==212}"><a class="blue" href="http://www.egu365.com/jiefangqu/product.action?goods_id=${row.srcId }" target="_blank">${row.name }</a></c:if>
			<c:if test="${row.tSupplyMerchantFId ==202}"><a class="blue" href="#" target="_blank">${row.name }</c:if>
		</display:column>
		<display:column title="供应商" >
			<c:if test="${row.tSupplyMerchantFId ==200}">海吉星</c:if>
			<c:if test="${row.tSupplyMerchantFId ==204}">依谷网(全国)</c:if>
			<c:if test="${row.tSupplyMerchantFId ==212}">依谷网(深圳)</c:if>
			<c:if test="${row.tSupplyMerchantFId ==202}">中粮</c:if>
		</display:column>
		<display:column title="商品图片" >
			<ul class="menu-img-72"><li><a href="<%=OmsSysParamManager.getImageServerUrl("/productPic/") %>/productPic/${row.picBase }" rel="lightbox-group"><img src='<%=OmsSysParamManager.getImageServerUrl("/productPic/")  %>/productPic/${fn:substring(row.picBase, 0, fn:indexOf(row.picBase, "."))}/72.${fn:substringAfter(row.picBase, ".")}' border="0" /></a></li></ul>
		</display:column>
		<display:column title="市场价" >${row.price/100 }</display:column>
		<display:column title="销售价" >${row.priceDiscount/100 }</display:column>
		<display:column title="抓取时间" >${row.createTime }</display:column>
		<display:column title="状态" class="isSync" >
			<c:choose>
				<c:when test="${row.isSync==0 }">未同步</c:when>
				<c:when test="${row.isSync==1 }">已同步</c:when>
				<c:otherwise>其它</c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="操作" class="operation" >
			<c:if test="${row.isSync==0}">
				<a class="blue sync" href="../ebuyProductTemp/sync.html?id=${row.id }">同步</a>
			</c:if>
		</display:column>
		
	   <display:footer>
	      <tr>
	        <td colspan="9"> <div class="overflow"><input id="syncBatchBtn" class="input-btn left" type="button" value="批量同步"></div></td>
	      </tr>
	    </display:footer>
	</display:table>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker01.js"></script>
<script type="text/javascript" src="../js/picbox.js?v20150215"></script>
<script type="text/javascript">	

// 异步同步商品数据到解放区
$(".sync").click(function(){
	if(window.confirm("你确定要同步商品到解放区？\n\n注意：同步后商品会默认为上架状态")){
		var toURL = $(this).attr("href");
		var x=$(this);
		$.ajax({//使用ajax请求
			type: "GET",
			url: toURL,
			success: function(data){
		        alert("操作提示："+data);
		        x.hide();
	        	x.parent("td").prev("td").html('已同步');
			},
		});
		return false;
	}else{//取消操作
		return false;
	}
});

// 同步电商数据也做成ajax请求
$("#importBtn").click(function(){
	if(window.confirm("确认要导入电商数据？\n\n注意：导入需要较长时间，设置在后台运行，导入结果可在下表中查看")){
		var toURL = $("#importform").attr("action");

		$.ajax({//使用ajax请求
			type: "GET",
			url: toURL,
			data:{supplyMerchant:$("#supplyMerchantSelect").val()},
			success: function(data){
		        alert("操作提示："+ data);
			},
		});
		return false;
	}else{
		return false;
	}
});

$("#syncBatchBtn").click(function(){
	if($("input[type=checkbox][name='eptId']:checked").length==0){
		alert("至少要选择一个商品才能同步。");
		return;
	};
	
	var ids = new Array();
	$("input[type='checkbox'][name='eptId']:checked").each(function(){ 
		ids.push($(this).val()); 
	});
	
	if(window.confirm("确认要指同步商品？")){
		$.ajax({//使用ajax请求
			type: "POST",
			url: "../ebuyProductTemp/sync.html",
			data:$.param({id:ids},true),
			success: function(data){
		        alert("操作提示："+data);
		        $("input[type='checkbox'][name='eptId']:checked").each(function(){
		        	$(this).parent("td").siblings("td.isSync").text("已同步");
		        	$(this).parent("td").siblings("td.operation").html("");
		        	$(this).remove();
		        });
			},
		});
	}else{
		return;
	}
});

</script>
</html>
