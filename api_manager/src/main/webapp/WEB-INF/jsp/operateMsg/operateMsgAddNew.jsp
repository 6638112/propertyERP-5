<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>运营通知-新增</title>
		<link rel="stylesheet" type="text/css" href="../css/common.css">
		<link rel="stylesheet" type="text/css" href="../css/style.css">
		<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
		
		<style>
			.dpTd1{width: 120px; text-align: left;}
			.dpTd2{width: 240px; text-align: left;}
			.dpTd3{width: 800px; min-width: 800px;text-align: left;}
			.pcTd1{width: 60px; text-align: left;vertical-align:center;}
			.pcTd2{text-align: left;}
			.overflowDiv{max-height: 346px; overflow-y: scroll;}
			hr{height:2px;border:none;border-top:2px dotted #185598;margin-top: 10px;}
			.title{font-weight: bold;}
			.red{color:red;}
			#cityTable tbody, #gbTable tbody, #areaHasSelectedList tbody{  
				display:block;  
				max-height:346px;  
				overflow-y:scroll; 
				overflow-x:hidden; 
			}  
			
			#cityTable thead, #cityTable tr, #gbTable thead, #gbTable tr, #areaHasSelectedList thead, #areaHasSelectedList tr {  
				display:table;  
				width:100%;  
				table-layout:fixed;  
				overflow-x:hidden;
			}  
			  
			#cityTable thead, #gbTable thead, #areaHasSelectedList thead {  
				/*Standard */
				width: calc( 100% - 8px );
				/*chrome safari*/
				width: -webkit-calc( 100% - 8px );
				/*Firefox*/
				width: -moz-calc( 100% - 8px );				
			}
		</style>
	</head>
	<body style="display:none;">
		<div class="info">
		    <h2>新增运营通知</h2> 
		    <form class="inputform" enctype="multipart/form-data" method="post" action="saveAddNew.html" >
			    <table class="info-list-02" style="width: 640px;min-width: 440px;margin-top: 20px;">
					<tr>
						<td class="dpTd1 title">发送对象：</td>
						<td class="dpTd2">
				        	<!-- <label style="margin-right: 2em;" onclick="setSendRange('1')"><input type="radio" name="sendRange" value="1" checked/>全国</label> -->
				        	<label style="margin-right: 2em;" onclick="setSendRange('4')"><input type="radio" name="sendRange" value="4" checked />城市/区县</label>
							<label onclick="setSendRange('3')"><input type="radio" name="sendRange" value="3"/>小区</label>
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title">信息类型：</td>
						<td class="dpTd2">
							<label style="margin-right: 2em;"> <input name="msgType" type="radio" value="1" checked="checked">短信 </label>
							<input name="msgType" type="radio" value="2" >push
						</td>
					</tr>
					<tr id="jumpTargetTr">
						<td class="dpTd1 title">跳转目标：</td>
						<td class="dpTd2">
							<select name="jumpTarget">
								<c:forEach items="${oprtConfigList }" var="item">
								  <option value="${item.number }">${item.jumpDescription }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr id="prdtIdDetailTr">
						<td class="dpTd1 title">商品详情参数：</td>
						<td class="dpTd2">
							<input name="prdtParam" type="text">
						</td>
					</tr>
					<tr id="blackTr">
						<td class="dpTd1 title">短信黑名单：</td>
						<td class="dpTd2">
							<input name="blackFile" type="file" accept="text/plain"> <br>注意：只允许上传txt文件，一个手机号一行
						</td>
					</tr>
					<tr id="whiteTr">
						<td class="dpTd1 title">短信白名单：</td>
						<td class="dpTd2">
							<input name="whiteFile" type="file" accept="text/plain"> <br>注意：只允许上传txt文件，一个手机号一行
						</td>
					</tr>
					
					<tr>
						<td class="dpTd1 title">发送时间：</td>
						<td class="dpTd2">
							<label style="margin-right: 2em;"><input name="sendTimeType" type="radio" value="1" checked="checked">立即发送</label>
							<label><input name="sendTimeType" type="radio" value="2" >定时推送</label> 
								<p id="sendTimeP" class="dsn">时间  <input name="sendTime" type="text" class="input_text pp icon_dt" id="date01" title="请选择结束时间""></p>
						</td>
					</tr>
					
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>标题：</td>
						<td class="dpTd2">
							<input type="text" name="title" datatype="*2-45" nullmsg="请填写信息！" errormsg="长度2到45个字符！" class="input_text w240 pp" maxlength="45">
						</td>
					</tr>
					
					<tr id="shortUrlDiv">
						<td class="dpTd1 title">短链接：</td>
						<td class="dpTd2">
							<input type="text" name="shortUrl" id="shortUrl" class="input_text w240 pp">  <input type="button" value="插入短链接" onclick="pasteHTML()">
						</td>
					</tr>
					
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>内容：</td>
						<td class="dpTd2">
							<textarea rows="10" cols="100" name="content" datatype="*8-500" nullmsg="请填写信息！" errormsg="长度8到800个字符！" class="input_text w240 pp" maxlength="800"></textarea>
						</td>
					</tr>
				</table>
				
		        <div id="cityRange">
				    <h2 class="mtop10 f14">推送城市/区县</h2>
					<div class="bs-example bgebeb">
					    <table class="info-list" border="0">
					      <tr>
					        <td><div class="list-name">所在省：</div></td>
					        <td><input type="text" id="province1" class="input_text w120"></td>
					        <td><div class="list-name">所在市：</div></td>
					        <td><input type="text" id="city1" class="input_text w120"></td>
					        <td><div class="list-name">所在区：</div></td>
					        <td><input type="text" id="block1" class="input_text w120"></td>
					        <td><div class="list-name">选择状态：</div></td>
					        <td>
					        	<select class="select_normal" id="selectStatus" onchange="filterCity()">
					        		<option value="all">所有</option>
					        		<option value="selected">已选</option>
					        		<option value="unselected">未选</option>
					        	</select>
					        </td>
					      </tr>
					    </table>
					</div> 
					<h2 class="mtop10 f14">城市搜索结果</h2>
					<display:table name="blockList" id="cityTable" class="info-list-02" requestURI="" >
						<display:column title="<label><a href='javascript:void(0);'><input type='checkbox' onclick='selectAllCityBox(this)'/>全选</a></label>"  sortable="true">
							<input name="blockId" type="checkbox" value="${cityTable.blockId }" onclick="selectCity(this)"/>
						</display:column>
						<display:column title="省份" property="addressProvinceName"></display:column>
						<display:column title="城市" property="addressCityName"></display:column>
						<display:column title="区县" property="addressBlockName"></display:column>
					</display:table>
					<h2 class="mtop20 f14">已选择城市</h2>
				    <table id="areaHasSelectedList" class="info-list-02" border="0" cellpadding="0" cellspacing="1">
				      <thead>
					      <tr class="title">
					        <th>所在省</th>
					        <th>所在市</th>
					        <th>所在区</th>
				        	<th>操作</th>
					      </tr>
				      </thead>
				      <tbody>
				      </tbody>
				    </table>
					<div class="mtop10 t_center">
						<input class="input-btn w150" type="button" value="清空已选择城市" onclick="clearCity()"/>
					</div>
		        </div>
		        <div id="gbRange">
				    <h2 class="mtop10 f14">销售小区</h2>
					<div class="bs-example bgebeb">
					    <table class="info-list" border="0">
					      <tr>
					        <td><div class="list-name">所在省：</div></td>
					        <td><input type="text" id="province" class="input_text w120"></td>
					        <td><div class="list-name">所在市：</div></td>
					        <td><input type="text" id="city" class="input_text w120"></td>
					        <td><div class="list-name">所在区：</div></td>
					        <td><input type="text" id="block" class="input_text w120"></td>
					        <td><div class="list-name">小区选择状态：</div></td>
					        <td>
					        	<select class="select_normal" id="selectStatus" onchange="filterGb()">
					        		<option value="all">所有</option>
					        		<option value="selected">已选</option>
					        		<option value="unselected">未选</option>
					        	</select>
					        </td>
					      </tr>
					      <tr>
					        <td><div class="list-name">物业名称：</div></td>
					        <td><input type="text" id="pcName" class="input_text w120"></td>
					        <td><div class="list-name">小区名称：</div></td>
					        <td><input type="text" id="gbName" class="input_text w120"></td>
					        <!-- <td class="dsn"><div class="list-name">缴费权限：</div></td>
					        <td class="dsn"> 小昭说不需要这个过滤条件
					            <select class="select_normal merchant-type" id="payOpenStatus">
					                <option value="">全部</option>
					                <option value="是">已开通缴费</option>
					                <option value="否">未开通缴费</option>
					            </select>
					        </td> -->
					      </tr>
					    </table>
					</div> 
					<h2 class="mtop10 f14">小区搜索结果</h2>
					<display:table name="gbList" id="gbTable" class="info-list-02" requestURI="" >
						<display:column title="<label><a href='javascript:void(0);'><input type='checkbox' onclick='selectAllGbBox(this)'/>全选</a></label>"  sortable="true">
							<input name="gbId" type="checkbox" value="${gbTable.id }" onclick="selectGb(this)"/>
						</display:column>
						<display:column title="物业名称" property="propertyCompanyName"/>
						<display:column title="小区名称" property="name"/>
						<%-- <display:column title="缴费开通状态">
							<c:choose>
								<c:when test="${gbTable.propfeeCanpay eq 1}">是</c:when>
								<c:when test="${gbTable.propfeeCanpay eq 2}">否</c:when>
							</c:choose>
						</display:column> --%>
						<display:column title="所在省" property="addressProvinceName"></display:column>
						<display:column title="所在市" property="addressCityName"></display:column>
						<display:column title="所在区" media="html" property="addressBlockName"></display:column>
						<display:column title="详细地址" property="addressDesc" />
					</display:table>
					<h2 class="mtop20 f14">已选择小区</h2>
				    <table id="areaHasSelectedList" class="info-list-02" border="0" cellpadding="0" cellspacing="1">
				      <thead>
					      <tr class="title">
					        <th>物业名称</th>
					        <th>小区名称</th>
					        <!-- <th>缴费开通状态</th> -->
					        <th>所在省</th>
					        <th>所在市</th>
					        <th>所在区</th>
					        <th>详细地址</th>
				        	<th>操作</th>
					      </tr>
				      </thead>
				      <tbody>
				      </tbody>
				    </table>
					<div class="mtop10 t_center">
						<input class="input-btn w150" type="button" value="清空已选择小区" onclick="clearGb()"/>
					</div>
		        </div>
		        <input id="submitProduct" class="info-btn mtop10" type="submit" value="保存"/>
		    </form>
		</div>
		<table id="noTrTemplate" style="display:none;">
			<tbody>
				<tr>
					<td class="no">
						<input type="text" id="no" name="no" datatype="*2-30" nullmsg="请填写信息！" errormsg="长度2到30个字符！" class="input_text w120 pp" maxlength="40"/>
						<span class="Validform_checktip"></span>
					</td>
					<td class="sellPrice">
						<input type="text" id="sellPrice" name="sellPrice" datatype="numberFixTwoGt0" nullmsg="请填写信息！" class="input_text w120 pp" maxlength="40"/>
						<span class="Validform_checktip"></span>
					</td>
					<td class="price">
						<input type="text" id="price" name="price" datatype="numberFixTwo" nullmsg="请填写信息！"  class="input_text w120 pp" maxlength="40"/>
						<span class="Validform_checktip"></span>
					</td>
					<td class="unit">
						<input type="text" id="unit" name="unit" datatype="*1-8" nullmsg="请填写信息！" errormsg="长度1到8个字符！" class="input_text w120 pp" maxlength="40"/>
						<span class="Validform_checktip"></span>
					</td>
					<td class="op">
						<input type="button" class="input-btn" value="删除" onclick="$(this).parent().parent().remove();"/>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
	<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="../js/jquery.common.js"></script>
	<script type="text/javascript" src="../js/Sortable.js"></script>
	<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/xheditor_v1.2/xheditor-1.2.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/xheditor_v1.2/xheditor_lang/zh-cn.js"></script>
	
	<script type="text/javascript">
	$(function(){
		    //表单验证
			$(".inputform").Validform({
				tiptype:3,
				beforeCheck:function(curform){
				},
				beforeSubmit:function(curform){
					if($("input[name='sendRange']:checked").val() == 4 
							&& $("input[name='blockId']:checked").length == 0){
						alert("行政区/县至少要选择一个");
						return false;
					}
					if($("input[name='sendRange']:checked").val() == 3 
							&& $("input[name='gbId']:checked").length == 0){
						alert("小区至少要选择一个");
						return false;
					}
					$.Showmsg('正在提交，请稍后');
				}, 
				callback:function(data){
					console.log("calll back... ");
				}
			});
		    
		    
			$("#jumpTargetTr").hide();
			$("#prdtIdDetailTr").hide();
			
			$("input[type='radio'][name='msgType']").click(function(){
				var msgType = $(this).val();
				if(msgType == 1){//短信
					$('#shortUrlDiv').show();
					$("#blackTr").show();
					$("#whiteTr").show();
					$("#jumpTargetTr").hide();
					$("#prdtIdDetailTr").hide();
				}else{//push
					$('#shortUrlDiv').hide();
					$("#blackTr").hide();
					$("#whiteTr").hide();
					$("#jumpTargetTr").show();
					$("#prdtIdDetailTr").show();
				}
			});
			
			$("input[type='radio'][name='sendTimeType']").click(function(){
				var msgType = $(this).val();
				if(msgType == 1){//立即发送
					$('#sendTimeP').hide();
				}else{//定时发送
					$('#sendTimeP').show();
				}
			});
		}
	);
		
		// 添加规格
		function addNo(){
			var tr = $("#noTrTemplate").find("tbody").html();
			if($("#noBody").find("tr").length==0){
				$("#noBody").html(tr);
			} else {
				$("#noBody tr:last").after(tr);
			}
			window.parent.TuneHeight();
		}
		
		// 销售范围选择
		function setSendRange(range){
			if(range=="1"){
				$("#gbRange").hide();
				$("#cityRange").hide();
			} else if(range=="4"){
				$("#gbRange").hide();
				$("#cityRange").show();
			} else if(range=="3"){
				$("#gbRange").show();
				$("#cityRange").hide();
			}
			window.parent.TuneHeight();
		}
				
		//hover删除按钮
		$('.uploadPreview01').hover(function(){
			$(this).find('.img-delete-btn').show();
		}, function(){
			$(this).find('.img-delete-btn').hide();
		});
		
		/*<=====================================小区搜索=====================================*/
		$('#pcName, #gbName, #province, #city, #block').on('input propertychange', function(){
			filterGb();
		});
		
		function filterGb(){
			var province = $.trim($("#province").val());
			var city = $.trim($("#city").val());
			var block = $.trim($("#block").val());
			var pcName = $.trim($("#pcName").val());
			var gbName = $.trim($("#gbName").val());
			
			var searchKeys = [pcName, gbName, province, city, block];
			var $this = $("#gbTable tbody tr").hide();
			for(var i=0; i<searchKeys.length; i++){
				$this = $this.find("td:eq("+(i+1)+")").filter(":contains('"+searchKeys[i]+"')").parent();
			}
			
			var selectStatus = $("#gbRange #selectStatus").val();
			if(selectStatus=="selected"){
				$this = $this.find(":checkbox").filter(":checked").parent().parent();
			} else if(selectStatus=="unselected"){
				$this = $this.find(":checkbox").filter(":not(:checked)").parent().parent();
			}
			
			$this.show();
			window.parent.TuneHeight();
		}
		
		function selectGb(o){
			var gbId = $(o).val();
			if($(o).is(":checked")){// insert
				var html="";
				$(o).parent().parent().find("td:gt(0)").each(function(){
					html += "<td>"+$(this).html()+"</td>";
				});
				
				html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeGb(this)\">删除</a></td>";
				html = "<tr gbId="+gbId+">"+html+"</tr>";
				if($("#gbRange #areaHasSelectedList tbody tr").length==0){
					$("#gbRange #areaHasSelectedList tbody").html(html);
				} else {
					$("#gbRange #areaHasSelectedList tr:last").after(html);
				}
			} else {// remove
				$("#gbRange #areaHasSelectedList").find("tr[gbId="+gbId+"]").remove();
			}
			window.parent.TuneHeight();
		}
		
		function initSelectGbs(){
			var allHtml="";
			$("#gbTable tbody tr").each(function(){
				if($(this).find(":checkbox").is(":checked")){
					var gbId = $(this).find(":checkbox").val();
					var html="";
					$(this).find("td:gt(0)").each(function(){
						html += "<td>"+$(this).html()+"</td>";
					});
					html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeGb(this)\">删除</a></td>";
					html = "<tr gbId="+gbId+">"+html+"</tr>";
					allHtml += html;
				}
			});
			$("#gbRange #areaHasSelectedList tbody").html(allHtml);
			window.parent.TuneHeight();
		}
		
		function selectAllGbBox(o){
			var isChecked = $(o).is(":checked");
			$("#gbTable tbody tr").each(function(){
				if($(this).is(":visible")){
					$(this).find(":checkbox").prop("checked", isChecked);
				} else {
					if(isChecked){
						$(this).find(":checkbox").prop("checked", false);
					}
				}
			});
			
			if(isChecked){
				var allHtml="";
				$("#gbTable tbody tr").each(function(){
					if($(this).is(":visible")){
						var gbId = $(this).find(":checkbox").val();
						var html="";
						$(this).find("td:gt(0)").each(function(){
							html += "<td>"+$(this).html()+"</td>";
						});
						
						html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeGb(this)\">删除</a></td>";
						html = "<tr gbId="+gbId+">"+html+"</tr>";
						allHtml += html;
					}
				});
				$("#gbRange #areaHasSelectedList tbody").html(allHtml);
			} else {
				$("#gbRange #areaHasSelectedList tbody").html("");
			}
			window.parent.TuneHeight();
		};
		
		function removeGb(o){
			var delTr = $(o).parent().parent();
			var gbId = delTr.attr("gbId");
			$("#gbTable tbody tr").find(":checkbox[value="+gbId+"]").prop("checked", false);
			delTr.remove();
			window.parent.TuneHeight();
		}
		
		function clearGb(){
			$("#gbRange #areaHasSelectedList tbody").html("");
			$("#gbTable").find(":checkbox").each(function(){
				if($(this).is(":checked")){
					$(this).prop("checked", false);
				}
			});
			window.parent.TuneHeight();
		}
		/*=====================================小区搜索=====================================>*/
		/*<=====================================城市搜索=====================================*/
		$('#province1, #city1, #block1').on('input propertychange', function(){
			filterCity();
		});
		
		function filterCity(){
			var province = $.trim($("#province1").val());
			var city = $.trim($("#city1").val());
			var block = $.trim($("#block1").val());
			
			var searchKeys = [province, city, block];
			var $this = $("#cityTable tbody tr").hide();
			for(var i=0; i<searchKeys.length; i++){
				$this = $this.find("td:eq("+(i+1)+")").filter(":contains('"+searchKeys[i]+"')").parent();
			}
			
			var selectStatus = $("#cityRange #selectStatus").val();
			if(selectStatus=="selected"){
				$this = $this.find(":checkbox").filter(":checked").parent().parent();
			} else if(selectStatus=="unselected"){
				$this = $this.find(":checkbox").filter(":not(:checked)").parent().parent();
			}
			
			$this.show();
			window.parent.TuneHeight();
		}
		
		function selectCity(o){
			var blockId = $(o).val();
			if($(o).is(":checked")){// insert
				var html="";
				$(o).parent().parent().find("td:gt(0)").each(function(){
					html += "<td>"+$(this).html()+"</td>";
				});
				
				html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeCity(this)\">删除</a></td>";
				html = "<tr blockId="+blockId+">"+html+"</tr>";
				if($("#cityRange #areaHasSelectedList tbody tr").length==0){
					$("#cityRange #areaHasSelectedList tbody").html(html);
				} else {
					$("#cityRange #areaHasSelectedList tr:last").after(html);
				}
			} else {// remove
				$("#cityRange #areaHasSelectedList").find("tr[blockId="+blockId+"]").remove();
			}
			window.parent.TuneHeight();
		}
		
		function initSelectCitys(){
			var allHtml="";
			$("#cityTable tbody tr").each(function(){
				if($(this).find(":checkbox").is(":checked")){
					var blockId = $(this).find(":checkbox").val();
					var html="";
					$(this).find("td:gt(0)").each(function(){
						html += "<td>"+$(this).html()+"</td>";
					});
					html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeCity(this)\">删除</a></td>";
					html = "<tr blockId="+blockId+">"+html+"</tr>";
					allHtml += html;
				}
			});
			$("#cityRange #areaHasSelectedList tbody").html(allHtml);
			window.parent.TuneHeight();
		}
		
		function selectAllCityBox(o){
			var isChecked = $(o).is(":checked");
			$("#cityTable tbody tr").each(function(){
				if($(this).is(":visible")){
					$(this).find(":checkbox").prop("checked", isChecked);
				} else {
					if(isChecked){
						$(this).find(":checkbox").prop("checked", false);
					}
				}
			});
			
			if(isChecked){
				var allHtml="";
				$("#cityTable tbody tr").each(function(){
					if($(this).is(":visible")){
						var blockId = $(this).find(":checkbox").val();
						var html="";
						$(this).find("td:gt(0)").each(function(){
							html += "<td>"+$(this).html()+"</td>";
						});
						
						html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeCity(this)\">删除</a></td>";
						html = "<tr blockId="+blockId+">"+html+"</tr>";
						allHtml += html;
					}
				});
				$("#cityRange #areaHasSelectedList tbody").html(allHtml);
			} else {
				$("#cityRange #areaHasSelectedList tbody").html("");
			}
			window.parent.TuneHeight();
		};
		
		function removeCity(o){
			var delTr = $(o).parent().parent();
			var blockId = delTr.attr("blockId");
			$("#cityTable tbody tr").find(":checkbox[value="+blockId+"]").prop("checked", false);
			delTr.remove();
			window.parent.TuneHeight();
		}
		
		function clearCity(){
			$("#cityRange #areaHasSelectedList tbody").html("");
			$("#cityTable").find(":checkbox").each(function(){
				if($(this).is(":checked")){
					$(this).prop("checked", false);
				}
			});
			window.parent.TuneHeight();
		}
		/*=====================================城市搜索=====================================>*/
		$(function(){
			var range = $(":radio[name='sendRange']:checked").val();
			setSendRange(range);
			$("body").show();
		});
		
		// 处理插入短链接 
		var xheditor = null;
		$(document).ready(function () {
	        xheditor = $("textarea").xheditor({
	            tools:'',
	            upImgUrl:'${pageContext.request.contextPath}/xhedit/upload.html',  // 图片上传接口地址
	            skin:'nostyle',
	            width:'80%',   
	            height:'120',
	            layerShadow:1,
	            submitID:'submitBtn',
	            upBtnText:'上传',
	            internalScript:false,
	            inlineScript:false,
	            emotMark:true
	        });
	    });
		
		function pasteHTML(){
			var url = $.trim($("#shortUrl").val());
			if(url ==""){
				alert("插入的短链接不能为空");
				return;
			}
			if($("#xhe0_iframe").contents().find("input[type=button]").length == 1){
				alert("只允许插入一个短链接");
				return;
			}
			var urlBtn = "<input type='button' value='"+url+"'/>";
			xheditor.pasteHTML(urlBtn);
		}
		
	</script>
</html>