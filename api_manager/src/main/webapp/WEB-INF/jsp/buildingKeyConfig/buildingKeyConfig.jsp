<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page import="com.cnfantasia.server.ms.buildingKeyConfig.constant.BuildingKeyConfigConstant"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	request.setAttribute("text", BuildingKeyConfigConstant.InputType.TEXT);
	request.setAttribute("radio", BuildingKeyConfigConstant.InputType.RADIO); 
	request.setAttribute("img", BuildingKeyConfigConstant.InputType.IMG); 
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/bootstrap3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome-4.5.0/css/font-awesome.min.css">
		<style type="text/css">
			.config_item{border:1px solid #DDDDDD;padding-top:5px;padding-bottom:5px;margin-bottom: 10px;}
			.config_item:hover{border:1px solid #BBBBBB;background-color:#EEEEEE;}
			.config_item .row{position: relative;}
			.div_op_btn{position: fixed;right:35px;z-index: 10;}
			.radio_label{font-weight: normal;cursor: pointer;}
		</style>
	</head>
	<body>
		<div class="info">
	        <h2 id="pageTitle" style="font-size:16px;font-weight: bold;">【${gbName}】门禁认证选项配置</h2>
	        <c:choose>
	            <%-- 用configs.size()会报错The function size must be used with a prefix when a default namespace is not specified --%>
			    <c:when test="${fn:length(configs)>0}">  
			    	<c:forEach items="${configs}" var="config" varStatus="i">
			    		<div class="container-fluid config_item">
				        	<div class="div_op_btn hide">
			        			<button class="btn btn-xs btn-default" onclick="addConfigItem(this);"><i class="fa fa-plus"></i></button>
			        			<button class="btn btn-xs btn-default btn_del" onclick="delConfigItem(this, ${config.id});"><i class="fa fa-minus"></i></button>
			        			<button class="btn btn-xs btn-default btn_up" onclick="upConfigItem(${config.id}, '${config.order}');"><i class="fa fa-arrow-up"></i></button>
			        			<button class="btn btn-xs btn-default btn_down" onclick="downConfigItem(${config.id}, '${config.order}');"><i class="fa fa-arrow-down"></i></button>
			        		</div>
				        	<div class="row">
				        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
				        			<div class="form-group">
				        				<label class="control-label" for="label" style="text-align: left;"><span class="red">*</span>左侧名称</label>
				        				<input type="text" id="label" class="form-control" maxlength="20" value="${config.label}"/>
				        			</div>
				        		</div>
				        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
				        			<div class="form-group">
				        				<label class="control-label" for="inputId" style="text-align: left;"><span class="red">*</span>右侧id</label>
				        				<input type="text" id="inputId" class="form-control" maxlength="50" value="${config.inputId}" placeholder="以字母或下划线开头，由字母、数字、下划线组成"/>
				        			</div>
				        		</div>
				        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
				        			<div class="form-group">
				        				<label class="control-label" for="inputType" style="text-align: left;"><span class="red">*</span>类型</label>
				        				<select id="inputType" class="form-control">
				        					<option value="">请选择类型</option>
				        					<option value="${text}" ${config.inputType==text?'selected':''}>文本框（text）</option>
				        					<option value="${radio}" ${config.inputType==radio?'selected':''}>单选按钮（radio）</option>
				        					<option value="${img}" ${config.inputType==img?'selected':''}>图片（image）</option>
				        				</select>
				        			</div>
				        		</div>
				        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
				        			<div class="form-group">
				        				<label class="control-label" for="inputPattern" style="text-align: left;">正则表达式</label>
				        				<input type="text" id="inputPattern" class="form-control" maxlength="300" value="${config.inputPattern}"/>
				        			</div>
				        		</div>
				        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
				        			<div class="form-group">
				        				<label class="control-label" for="inputDefault" style="text-align: left;">默认值</label>
				        				<input type="text" id="inputDefault" class="form-control" maxlength="50" value="${config.inputDefault}"/>
				        			</div>
				        		</div>
				        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
				        			<div class="form-group">
				        				<label class="control-label" for="inputHint" style="text-align: left;">提示信息</label>
				        				<input type="text" id="inputHint" class="form-control" maxlength="100" value="${config.inputHint}"/>
				        			</div>
				        		</div>
				        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
				        			<div class="form-group">
				        				<label class="control-label" for="inputRequired" style="text-align: left;">是否必填</label>
				        				<div class="control-label">
				        					<label class="radio_label"><input type="radio" name="inputRequired${i.index}" value="0" ${config.inputRequired == 0?'checked':''}/>必填</label>&nbsp;&nbsp;
				        					<label class="radio_label"><input type="radio" name="inputRequired${i.index}" value="1" ${config.inputRequired == 1?'checked':''}/>非必填</label>
				        				</div>
				        			</div>
				        		</div>
				        		<div class="col-lg-8 col-md-6 col-sm-12 col-xs-12">
				        			<div class="form-group">
				        				<label class="control-label" for="inputEnum" style="text-align: left;">radio的value与label</label>
				        				<textarea rows="5" id="inputEnum" class="form-control" placeholder='json数组格式。如[{"value":"M","label":"男"},{"value":"F","label":"女"}]'>${config.inputEnum}</textarea>
				        			</div>
				        		</div>
				        	</div>
				        </div>
			    	</c:forEach>
			    </c:when>
			    <c:otherwise> 
			    	<div class="container-fluid config_item">
			        	<div class="div_op_btn hide">
		        			<button class="btn btn-xs btn-default" onclick="addConfigItem(this);"><i class="fa fa-plus"></i></button>
		        			<button class="btn btn-xs btn-default btn_del" onclick="delConfigItem(this);"><i class="fa fa-minus"></i></button>
		        			<!-- <button class="btn btn-xs btn-default btn_up" onclick="upConfigItem();"><i class="fa fa-arrow-up"></i></button>
		        			<button class="btn btn-xs btn-default btn_down" onclick="downConfigItem();"><i class="fa fa-arrow-down"></i></button> -->
		        		</div>
			        	<div class="row">
			        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
			        			<div class="form-group">
			        				<label class="control-label" for="label" style="text-align: left;"><span class="red">*</span>左侧名称</label>
			        				<input type="text" id="label" class="form-control" maxlength="20"/>
			        			</div>
			        		</div>
			        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
			        			<div class="form-group">
			        				<label class="control-label" for="inputId" style="text-align: left;"><span class="red">*</span>右侧id</label>
			        				<input type="text" id="inputId" class="form-control" maxlength="50" placeholder="以字母或下划线开头，由字母、数字、下划线组成"/>
			        			</div>
			        		</div>
			        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
			        			<div class="form-group">
			        				<label class="control-label" for="inputType" style="text-align: left;"><span class="red">*</span>类型</label>
			        				<select id="inputType" class="form-control">
			        					<option value="">请选择类型</option>
			        					<option value="${text}">文本框（text）</option>
			        					<option value="${radio}">单选按钮（radio）</option>
			        					<option value="${img}">图片（image）</option>
			        				</select>
			        			</div>
			        		</div>
			        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
			        			<div class="form-group">
			        				<label class="control-label" for="inputPattern" style="text-align: left;">正则表达式</label>
			        				<input type="text" id="inputPattern" class="form-control" maxlength="300"/>
			        			</div>
			        		</div>
			        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
			        			<div class="form-group">
			        				<label class="control-label" for="inputDefault" style="text-align: left;">默认值</label>
			        				<input type="text" id="inputDefault" class="form-control" maxlength="50"/>
			        			</div>
			        		</div>
			        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
			        			<div class="form-group">
			        				<label class="control-label" for="inputHint" style="text-align: left;">提示信息</label>
			        				<input type="text" id="inputHint" class="form-control" maxlength="100"/>
			        			</div>
			        		</div>
			        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
			        			<div class="form-group">
			        				<label class="control-label" for="inputRequired" style="text-align: left;">是否必填</label>
			        				<div class="control-label">
			        					<label class="radio_label"><input type="radio" name="inputRequired0" value="0" checked/>必填</label>&nbsp;&nbsp;
			        					<label class="radio_label"><input type="radio" name="inputRequired0" value="1"/>非必填</label>
			        				</div>
			        			</div>
			        		</div>
			        		<div class="col-lg-8 col-md-6 col-sm-12 col-xs-12">
			        			<div class="form-group">
			        				<label class="control-label" for="inputEnum" style="text-align: left;">radio的value与label</label>
			        				<textarea rows="5" id="inputEnum" class="form-control" placeholder='json数组格式。如[{"value":"M","label":"男"},{"value":"F","label":"女"}]'></textarea>
			        			</div>
			        		</div>
			        	</div>
			        </div>
			    </c:otherwise>
			</c:choose>
	        <div class="row">
		        <button class="info-btn" onclick="saveConfig()">保 存</button> 
	        </div>
		</div>
		<%-- 点击添加按钮时所用的模板html --%>
		<div id="config_item_template" class="hide">
	        <div class="container-fluid config_item">
	        	<div class="div_op_btn hide">
        			<button class="btn btn-xs btn-default" onclick="addConfigItem(this);"><i class="fa fa-plus"></i></button>
        			<button class="btn btn-xs btn-default btn_del" onclick="delConfigItem(this);"><i class="fa fa-minus"></i></button>
        			<button class="btn btn-xs btn-default btn_up" onclick="upConfigItem();"><i class="fa fa-arrow-up"></i></button>
        			<button class="btn btn-xs btn-default btn_down" onclick="downConfigItem();"><i class="fa fa-arrow-down"></i></button>
        		</div>
	        	<div class="row">
	        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
	        			<div class="form-group">
	        				<label class="control-label" for="label" style="text-align: left;"><span class="red">*</span>左侧名称</label>
	        				<input type="text" id="label" class="form-control" maxlength="20"/>
	        			</div>
	        		</div>
	        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
	        			<div class="form-group">
	        				<label class="control-label" for="inputId" style="text-align: left;"><span class="red">*</span>右侧id</label>
	        				<input type="text" id="inputId" class="form-control" maxlength="50" placeholder="以字母或下划线开头，由字母、数字、下划线组成"/>
	        			</div>
	        		</div>
	        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
	        			<div class="form-group">
	        				<label class="control-label" for="inputType" style="text-align: left;"><span class="red">*</span>类型</label>
	        				<select id="inputType" class="form-control">
	        					<option value="">请选择类型</option>
	        					<option value="${text}">文本框（text）</option>
	        					<option value="${radio}">单选按钮（radio）</option>
	        					<option value="${img}">图片（image）</option>
	        				</select>
	        			</div>
	        		</div>
	        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
	        			<div class="form-group">
	        				<label class="control-label" for="inputPattern" style="text-align: left;">正则表达式</label>
	        				<input type="text" id="inputPattern" class="form-control" maxlength="300"/>
	        			</div>
	        		</div>
	        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
	        			<div class="form-group">
	        				<label class="control-label" for="inputDefault" style="text-align: left;">默认值</label>
	        				<input type="text" id="inputDefault" class="form-control" maxlength="50"/>
	        			</div>
	        		</div>
	        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
	        			<div class="form-group">
	        				<label class="control-label" for="inputHint" style="text-align: left;">提示信息</label>
	        				<input type="text" id="inputHint" class="form-control" maxlength="100"/>
	        			</div>
	        		</div>
	        		<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
	        			<div class="form-group">
	        				<label class="control-label" for="inputRequired" style="text-align: left;">是否必填</label>
	        				<div class="control-label">
	        					<label class="radio_label"><input type="radio" name="inputRequired" value="0" checked/>必填</label>&nbsp;&nbsp;
	        					<label class="radio_label"><input type="radio" name="inputRequired" value="1"/>非必填</label>
	        				</div>
	        			</div>
	        		</div>
	        		<div class="col-lg-8 col-md-6 col-sm-12 col-xs-12">
	        			<div class="form-group">
	        				<label class="control-label" for="inputEnum" style="text-align: left;">radio的value与label</label>
	        				<textarea rows="5" id="inputEnum" class="form-control" placeholder='json数组格式。如[{"value":"M","label":"男"},{"value":"F","label":"女"}]'></textarea>
	        			</div>
	        		</div>
	        	</div>
	        </div>
        </div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript">
		$(function(){
			configItemHover(".config_item");
			window.onresize=function(){  
				window.parent.TuneHeight();// 重置高度
            };
		});
		
		function configItemHover(classAttr){
			$(classAttr).hover(function(){
				$(this).find(".div_op_btn").removeClass("hide");
			}, function(){
				$(this).find(".div_op_btn").addClass("hide");
			});
		}
		
		// 添加
		function addConfigItem(dom){
			var appendHtml = $("#config_item_template").html();
			$(dom).parent().parent().after(appendHtml);
			var thisDom = $(dom).parent().parent().next();
			thisDom.find(".btn_up,.btn_down").remove();// 没有存储的item，删除里面的上移、下移按钮
			configItemHover(thisDom);
			var timestamp=new Date().getTime();// item与item之间的radio的name不能一样，否则选值时会有问题
			$(thisDom).find(":radio").each(function(){
				$(this).prop("name", "inputRequired"+timestamp);
			});
			window.parent.TuneHeight();// 重置高度
		}
		
		// 删除
		function delConfigItem(dom, id){
			if($(".config_item").size()<=2){
				alert("配置项必须最少有一个！");
			} else {
				if(window.confirm("您确定要删除该项吗？")){
					if(id){// 删除已存在数据库的item 
						$.post("${pageContext.request.contextPath}/buildingKeyConfig/deleteBuildingKeyConfig.html", {"id":id}, function(data){
							if(data.status == "<%=CommConstants.ResponseStatus.SUCCESS%>") {
								var delDom = $(dom).parent().parent();
								delDom.animate({height:"0px"}, 500).delay(400).queue(function(){
									delDom.remove();
									window.parent.TuneHeight();// 重置高度
								});
							} else {
								alert("删除失败");
							}
						});
					} else { 
						var delDom = $(dom).parent().parent();
						delDom.animate({height:"0px"}, 500).delay(400).queue(function(){
							delDom.remove();
							window.parent.TuneHeight();// 重置高度
						});
					} 
				}
			}
		}
		
		function saveConfig(){
			var configs = new Array();
			$(".info .config_item").each(function(){
				var config = new Object();
				config["label"]         = $.trim($(this).find("#label").val());
				config["inputId"]       = $.trim($(this).find("#inputId").val());
				config["inputType"]     = $.trim($(this).find("#inputType").val());
				config["inputPattern"]  = $.trim($(this).find("#inputPattern").val());
				config["inputDefault"]  = $.trim($(this).find("#inputDefault").val());
				config["inputHint"]     = $.trim($(this).find("#inputHint").val());
				config["inputEnum"]     = $.trim($(this).find("#inputEnum").val());
				config["inputRequired"] = $.trim($(this).find(":radio:checked").val());
				configs.push(config);
			});
			// inputId只能以字母、下划线开头，由字母、数字、下划线组成
			var reg = /^[a-zA-Z_]{1}[0-9a-zA-Z_]{0,}$/;
			for(var i=0; i<configs.length; i++){
				if(configs[i]["label"]==""){
					goErrorLocation(i, "#label");
					alert("左侧名称不能为空！");
					return;
				}
				if(configs[i]["inputId"]==""){
					goErrorLocation(i, "#inputId");
					alert("右侧id不能为空！");
					return;
				} else if(configs[i]["inputId"].match(reg)==null){
					goErrorLocation(i, "#inputId");
					alert("右侧id格式【以字母或下划线开头，由字母、数字、下划线组成】不正确！");
					return;
				}
				if(configs[i]["inputType"]==""){
					goErrorLocation(i, "#inputType");
					alert("请选择类型！");
					return;
				}
				// radio类型的value与label选项不能为空
				if(configs[i]["inputType"]=="${radio}"){
					if(configs[i]["inputEnum"]==""){
						goErrorLocation(i, "#inputEnum");
						alert("【单选按钮（radio）】类型的【radio的value与label】不能为空！");
						return;
					} else {
						// 校验json字符串格式是否正确
						try{
							$.parseJSON(configs[i]["inputEnum"]);
						}catch(e){
							goErrorLocation(i, "#inputEnum");
							alert("【radio的value与label】的json字符串格式不正确："+e);
							return;
						}
					}
				}
			}
			
			// 判断id是否有重名的
			for(var i=0; i<configs.length; i++){
				var isRepeated = false;
				for(var j=i+1; j<configs.length; j++){
					if(configs[i]["inputId"]==configs[j]["inputId"]){
						goErrorLocation(j, "#inputId");
						isRepeated = true;
						break;
					}
				}
				if(isRepeated){
					alert("右侧id值不能重复！");
					return;
				}
			}
			$.post("${pageContext.request.contextPath}/buildingKeyConfig/updateBuildingKeyConfig.html", {"groupBuildingId":"${groupBuildingId}", "configs":JSON.stringify(configs)}, function(data){
				if(data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>') {
					refreshData(data.dataValue);
				}
				alert(data.message);
			});
		}
		
	    // 上移
		function upConfigItem(id, order){
	    	if(id==undefined){
	    		alert("没有提交的数据不能上移！");
	    	} else {
	    		$.post("${pageContext.request.contextPath}/buildingKeyConfig/upOrder.html", {"groupBuildingId":"${groupBuildingId}", "id":id, "order":order}, function(data){
					if(data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>') {
						refreshData(data.dataValue);
					}
					alert(data.message);
				});
	    	}
		}
		
	    // 下移
		function downConfigItem(id, order){
			if(id==undefined){
	    		alert("没有提交的数据不能下移！");
	    	} else {
	    		$.post("${pageContext.request.contextPath}/buildingKeyConfig/downOrder.html", {"groupBuildingId":"${groupBuildingId}", "id":id, "order":order}, function(data){
					if(data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>') {
						refreshData(data.dataValue);
					}
					alert(data.message);
				});
	    	}
		}
		
	    // 刷新数据
	    function refreshData(list){
	    	// 删除原来的
			$(".info .config_item").each(function(){
				$(this).remove();
			});
			for(var i=0; i<list.length; i++){
				var html = $("#config_item_template").html();
				if(i==0){
					$(".info #pageTitle").after(html);
				} else {
					$(".info .config_item:last").after(html);
				}
				
				var dom = $(".info .config_item:last");
				dom.find("#label").val(list[i]["label"]);
				dom.find("#inputId").val(list[i]["inputId"]);
				dom.find("#inputType").val(list[i]["inputType"]);
				dom.find("#inputPattern").val(list[i]["inputPattern"]);
				dom.find("#inputDefault").val(list[i]["inputDefault"]);
				dom.find("#inputHint").val(list[i]["inputHint"]);
				dom.find("#inputEnum").val(list[i]["inputEnum"]);
				var timestamp=new Date().getTime();
				dom.find(":radio").each(function(){
					$(this).prop("name", "inputRequired"+timestamp);
				});
				dom.find(":radio[value='"+list[i]["inputRequired"]+"']").prop("checked", "checked");
				
				dom.find(".btn_del").attr("onclick", "delConfigItem(this, "+list[i]["id"]+");");
				dom.find(".btn_up").attr("onclick", "upConfigItem("+list[i]["id"]+",'"+list[i]["order"]+"');");
				dom.find(".btn_down").attr("onclick", "downConfigItem("+list[i]["id"]+",'"+list[i]["order"]+"');");
				
				configItemHover(".info .config_item:last");
			}
	    }
	    
	    // 定位到提示的控件
	    function goErrorLocation(i, classz){
	    	$(".info .config_item:eq("+i+")").find(classz).focus();
	    }
	</script>
</html>
