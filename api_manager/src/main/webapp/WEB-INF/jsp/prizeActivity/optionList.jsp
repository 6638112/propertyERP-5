<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%--<base href="<%=basePath%>//"/>--%>
<title>轻应用活动运营-奖项管理-奖项列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css"/>
<link rel="stylesheet" type="text/css" href="../css/style.css?v20150916"/>
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css"/>
	<style type="text/css">
		.wati_import{
			background:#DCDCDC;
			color:#FFFFFF;
			border:0px;
			cursor:wait;
		}
	</style>
</head>

<body>
<div class="info">
    <h2>奖项列表</h2>
    <div class="bs-example bgebeb">
    	<form id="searchForm"  action="<%=basePath%>/prizeActivity/optionList.html" method="post">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div class="list-name">奖项名称：</div></td>
	            <td><input type="text" name="name" value="${param.name }" class="input_text w120"/></td>
	            <td><div class="list-name">开启状态：</div></td>
	            <td>
	                    <select class="select_normal" name="qryStatus">
	                        <option value="0" <c:if test="${param.qryStatus eq 0}">selected</c:if>>全部</option>
	                        <option value="1" <c:if test="${param.qryStatus eq 1}">selected</c:if>>已开启</option>
	                        <option value="2" <c:if test="${param.qryStatus eq 2}">selected</c:if>>未开启</option>
	                        <!-- <option value="3">使用中</option>
	                        <option value="4">已结束</option> -->
	                    </select>
	            </td>
	            <td><div class="list-name">使用状态：</div></td>
	            <td>
	                    <select class="select_normal" name="useStatus">
	                        <option value="0">全部</option>
	                        <option value="1" <c:if test="${param.useStatus eq 1}">selected</c:if>>未使用</option>
	                        <option value="2" <c:if test="${param.useStatus eq 2}">selected</c:if>>使用中</option>
	                    </select>
	            </td>
	            <td class="t_center"><input class="input-btn w200" type="submit" value="搜索"/></td>
	          </tr>
	        </table>
        </form>
    </div>
    <div class="mtop40"><a id="downLoadBtn" class="blue" href="javascript:void(0)"><img src="images/download-icon.jpg"/> 下载奖品模版</a></div>
     <display:table name="resList" id="row" class="mars info-list-02 mtop10" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="奖项编号" property="id" class="row_class"/>
		<display:column title="奖项名称" property="name" />
		<display:column title="有效开始时间" property="valiStartTime" />
		<display:column title="有效结束时间" property="valiEndTime" />
		<%-- <display:column title="库存" property="endTime" /> --%>
		<display:column title="开启状态">
			<c:if test="${row.qryStatus==1}">已开启</c:if>
			<c:if test="${row.qryStatus==2}">未开启</c:if>
			<%-- <c:if test="${row.qryStatus==3}">进行中</c:if>
			<c:if test="${row.qryStatus==4}">已结束</c:if> --%>
		</display:column>
		<display:column title="使用状态">
			<c:if test="${row.useStatus==1}">未使用</c:if>
			<c:if test="${row.useStatus==2}">使用中</c:if>
		</display:column>
		<display:column title="可用库存" >
			<span id="kcId_${row.id}" style="display:block;width:100%;" onclick="qryLeftPriOptCount(${row.id},'kcId_${row.id}','<%=basePath%>');">点击获取</span>
		</display:column>
		<display:column title="操作">
		 	<input class="input-btn w80 import-layer-bill-btn" value="上传奖品" type="button"/>
		 	<input class="input-btn small-btn layer-prize-details-btn w100" type="button" value="奖品详情"/>
		 	<a class="blue prize-info-btn mleft10" href="<%=basePath%>/prizeActivity/optionDetail.html?optId=${row.id}">查看</a> 
		 	<c:if test="${row.useStatus==1}"><!-- row.qryStatus==2 and  -->
		 		<span class="grey">|</span> <a class="blue prize-info-btn mleft10" href="<%=basePath%>/prizeActivity/optionUpd.html?optId=${row.id}">编辑</a>  
		 		<span class="grey">|</span> <a class="blue" onclick="doOptionDel(${row.id},this)" href="javascript:void(0)">删除</a>
		 	</c:if>
		</display:column>
    </display:table>
</div>


<div class="layer-bill import-layer-bill dsn">
    <form id="uploadGiftForm" enctype="multipart/form-data" action="<%=basePath%>/prizeActivityJson/importPrizeGift.json" method="post">
         请选择要上传的奖品：<input id="excelFileInput" class="mtop10" name="excelFile" type="file" size="50" title="上传奖品"/><br /> 
         <input id="uploadGiftButton" class="input-btn w80 mtop20" type="submit" value="上传" />
         <div id="uploadError" class="dsn f12 mtop10" style="line-height:20px"></div>
         <input id="uploadOptId" type="hidden" name="optId"/>
    </form>
</div>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../busiJS/prizeActivity.js?v1"></script>
<script type="text/javascript">
	(function($){
	    //表单验证
		$("#uploadGiftForm").Validform({
			btnSubmit:"#uploadGiftButton", 
			tiptype:3,
			ajaxpost:true,
			beforeSubmit:function(curform){
				$("#uploadGiftForm").attr('onsubmit','return false;');
				$(".import-layer-bill").addClass("wati_import");
				$(".xubox_setwin").addClass("dsn");
				$("#uploadGiftButton").attr("disabled",true);
				$("#uploadGiftButton").val("上传中...");
			},
			callback:function(data){
				$("#uploadGiftForm").ajaxSubmit(function(data) {
					$(".import-layer-bill").removeClass("wati_import");
					$(".xubox_setwin").removeClass("dsn");
					$("#uploadGiftButton").attr("disabled",false);
					$("#uploadGiftButton").val("上传");
					try {
						data = eval(data);
					} catch (e) {
						data = eval("("+data+")");
					}
					if (data.status == '0000') {
						alert("成功上传"+data.dataValue.addCount+"份奖品信息~(⊙o⊙)");
						$("#uploadGiftForm").Validform().resetStatus();
						layer.closeAll();
					} else {
						$("#uploadError").html(data.message).show();
						$("#uploadGiftForm").Validform().resetStatus();
					}
				});
				//return false;
			}
		});
	})(jQuery);

	function doOptionDel(optId,tdTmp){
		if(confirm("确认删除?")){
			$.ajax({
				type:"post",
				url:"<%=basePath%>/prizeActivityJson/doOptionDel.json",
				data:{'optId': optId},
				dataType:"TEXT",
				beforeSend:function(data){
				},
				success:function(data){
					try {
						data = eval(data);
					} catch (e) {
						data = eval("("+data+")");
					}
					if (data.status == '0000') {
						alert("删除奖项成功!");
						$(tdTmp).parents('tr').remove();//TODO 刷新本页面？
					} else {
						alert(data.message);

					}
				}
			});
		}
	}
	$("#downLoadBtn").click(function() {
		var url = "<%=basePath%>/prizeActivity/exportPrizeGiftTemplate.html";
		window.open(url);
	});
	

	//导入账单弹出层
	$('.import-layer-bill-btn').click(function(){
		$.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
			border : [5, 0.3, '#000'],
			page: {dom : '.layer-bill.import-layer-bill'}
		});
		$("#uploadError").html("");
		$('#excelFileInput').val('');
		$('#uploadOptId').val($(this).parent('td').siblings('td.row_class').text());
	});
	
	$('.layer-prize-details-btn').click(function(){
		$.layer({
			type: 2,
			shadeClose: true,
			title: false,
			closeBtn: [0, true],
			shade: [0.4, '#000'],
			border: [5, 0.3, '#000'],
			offset: ['50px',''],
			area: ['960px', ($(window).height() - 200) +'px'],
			iframe: {src: "<%=basePath%>/prizeActivity/giftList.html?optId="+$(this).parent('td').siblings('td.row_class').text()}
		});
	});
	
	//上传文件校验
	$("#uploadBtn").click(function(){
		var fullFileName = $("#excelFileInput").val(); //选中文件的全名，包含路径
		var d = fullFileName.length - ".xls".length;
		if(fullFileName == ""){
			alert("请先选中一个上传文件");
			return false;
		}else if(fullFileName.indexOf(".xls") != d){ 
			alert("上传的必须是后缀名为xls的Excel文件，请先下载模板进行编辑后再上传");
			return false;

		}
	});
</script>
</body>

</html>
