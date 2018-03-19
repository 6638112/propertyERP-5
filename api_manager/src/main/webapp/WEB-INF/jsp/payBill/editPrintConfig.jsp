<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>账单打印模版配置</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/xheditor_v1.2/xheditor-1.2.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/xheditor_v1.2/xheditor_lang/zh-cn.js"></script>
    <style>
    	.title{text-align: center;font-size: 32px;margin-top: 20px;margin-bottom: 10px;}
    	.title u{color:red;}
    	.xheLayout{width: 100% !important;}
    	.btnDiv{ float:left;max-width: 200px;margin-left: 15px;margin-top: 15px;padding: 5px;border: 1px solid #66AFE9;}
    	.btnDiv:button{margin-bottom: 5px;}
    	.xhe_nostyle table.xheLayout{ border: 0 !important; width:1070px !important;}
    </style>
</head>
<body style="display:none;">
	<div class="info" style="margin: 15px auto; text-align: center;">
		<h1 class="title"><u>${gbName}</u>打印模板配置</h1>
		<div style="margin: 0 auto;width:1300px; overflow: hidden;">
			<div style="text-align: left;">
				<label style="margin-right: 2em;" onclick="chooseTemplate('templateDiv1')">
					<input type="radio" id="templateCode1" name="templateCode" value="${printTemplateMapResult.template1.code}" divId="templateDiv1" ${(printTemplateMapResult.template1.serviceState eq 0)?'checked':''}/>模板一
				</label>
				<label  onclick="chooseTemplate('templateDiv2')">
					<input type="radio" id="templateCode2" name="templateCode" value="${printTemplateMapResult.template2.code}" divId="templateDiv2" ${(printTemplateMapResult.template2.serviceState eq 0)?'checked':''}/>模板二
				</label>
			</div>
		</div>
		<div id="templateDiv1" style="margin: 0 auto; width:1300px; overflow: hidden;">
			<div style="width: 1073px; margin: 15px auto; float:left;">
				<div style="width: 1073px; border: 1px solid #C5C5C5;">
		       		<textarea id="printDesc" name="printDesc" rows="40">${printTemplateMapResult.template1.templateContent}</textarea>
		       	</div>
			</div>
			<div class="btnDiv" order="0">
			    <c:forEach items="${letterTextMap1}" var="item" varStatus="k">
			        <input type="button" onclick="pasteHTML(this);" value="${item.value}" data-letter='${item.key}'/>
			        <c:if test="${not k.last}">
			            <br>
			        </c:if>
			    </c:forEach>
		    </div>
	    </div>
	    
	    <div id="templateDiv2" style="margin: 0 auto; width:1300px; overflow: hidden;">
			<div style="width: 1073px; margin: 15px auto; float:left;">
				<div style="width: 1073px; border: 1px solid #C5C5C5;">
		       		<textarea id="printDesc" name="printDesc" rows="40">${printTemplateMapResult.template2.templateContent}</textarea>
		       	</div>
			</div>
			<div class="btnDiv" order="1">
			    <c:forEach items="${letterTextMap2}" var="item" varStatus="k">
			        <input type="button" onclick="pasteHTML(this);" value="${item.value}" data-letter='${item.key}'/>
			        <c:if test="${not k.last}">
			            <br>
			        </c:if>
			    </c:forEach>
		    </div>
	    </div>
		<input class="info-btn" type="button" onclick="savePrintConfig()" value="保存"/>
	</div>
</body>
<script type="text/javascript">
	var xheditor = null;
	$(document).ready(function () {
        xheditor = $("textarea").xheditor({
            tools:'Blocktag,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,SelectAll,Removeformat,List,Outdent,Indent,Img,Hr,Table,<c:if test="${isAdmin}">Source,</c:if>Print',
            upImgUrl:'${pageContext.request.contextPath}/xhedit/upload.html',  // 图片上传接口地址
            skin:'nostyle',
            width:'100%',   
            height:'590',
            layerShadow:1,
            submitID:'submitBtn',
            upBtnText:'上传',
            loadCSS: '${pageContext.request.contextPath}/css/payBillPrint.css',
            internalScript:false,
            inlineScript:false,
            emotMark:true
        });
    });
	
	function pasteHTML(o){
		var order = $(o).parent().attr("order");
		xheditor[order].pasteHTML($(o).attr("data-letter"));
	}
	
    function savePrintConfig(){
    	var templateDivId=$("input[name='templateCode']:checked").attr("divId");
    	var printDesc = $.trim($("#"+templateDivId+" #printDesc").val());
        if(printDesc==""){
        	alert("模板内容不能为空！");
        } else {
        	var templateCode= $("input[name='templateCode']:checked").val();
        	$.post("${pageContext.request.contextPath}/payBill/editPrintConfig.html", {"gbId": "${gbId}", "printDesc": printDesc, "templateCode": templateCode}, function(data){
                if(data.status == "0000") {
                    alert("操作成功！");
                    window.location.href = "${pageContext.request.contextPath}/payBill/printConfig.html";
                } else {
                    alert(data.message);
                }
            });
        }
    }
    
    function chooseTemplate(divId){
    	$("div[id^=templateDiv]").hide();
    	$("#"+divId).show();
    }
    
    $(function(){
    	$("#templateDiv1").hide();
    	$("#templateDiv2").hide();
    	var template2State = '${printTemplateMapResult.template2.serviceState}';
    	if(template2State=="0"){
			$("#templateDiv2").show();
    	} else {
    		$("#templateCode1").click();
    		$("#templateDiv1").show();
    	}
    	$("body").show();
    });
</script>
</html>