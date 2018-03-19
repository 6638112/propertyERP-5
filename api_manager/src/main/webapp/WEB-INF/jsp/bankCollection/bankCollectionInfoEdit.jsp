<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	response.setHeader("Pragma","No-cache");    
	response.setHeader("Cache-Control","no-cache");    
	response.setDateHeader("Expires", -10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>托收银行维护</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<style>
			td{text-align: center;}
			th{text-align: center !important;}
			.bankTd1{width: 200px; text-align: right;}
			.bankTd2{width: 240px; text-align: left;}
			.overflowDiv{max-height: 346px; overflow-y: scroll;}
		</style>
	</head>
	<body style="display:none;">
		<div class="info">
		    <h2>
		    	<c:choose>
		    		<c:when test="${empty pcbciId}">新增托收信息</c:when>
		    		<c:otherwise>修改托收信息</c:otherwise>
		    	</c:choose>
		    </h2> 
		    <form id="saveForm" method="post" action="${pageContext.request.contextPath}/bankCollection/saveBankCollectionInfo.html">
		    	<input type="hidden" name="pcbciId" value="${pcbciId}"/>
		    	<input type="hidden" id="bankOrgName" name="bankOrgName" value=""/>
		    	<input type="hidden" id="gbIds" name="gbIds" value=""/>
			    <table class="info-list-02" style="width: 440px;min-width: 440px;">
					<tr>
						<td class="bankTd1">编号</td>
						<td class="bankTd2"><input type="text" id="no" name="no" value="${pcBankCollectionInfo.no}" class="input_text w240 pp" maxlength="40"/></td>
					</tr>
					<tr>
						<td class="bankTd1">协议号</td>
						<td class="bankTd2"><input type="text" id="contractNo" name="contractNo" value="${pcBankCollectionInfo.contractNo}" class="input_text w240 pp" maxlength="40"/></td>
					</tr>
					<tr>
						<td class="bankTd1">托收银行/机构</td>
						<td class="bankTd2">
							<select id="bankOrg" name="bankOrg" class="select_normal">
								<c:forEach items="${bcFinanceOrgList}" var="row">
									<option value="${row.id}" <c:if test="${row.id eq pcBankCollectionInfo.tBankCollectionFinanceOrgFId}"> selected</c:if>>${row.orgName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="bankTd1">物业托收收款账号</td>
						<td class="bankTd2"><input type="text" id="bankAccount" name="bankAccount" value="${pcBankCollectionInfo.bankAccount}" class="input_text w240 pp" maxlength="40"/></td>
					</tr>
				</table>
				
				<h2 class="mtop20">托收范围</h2> 
				<select id="collectionRange" name="collectionRange" class="select_normal" onchange="showDivByRange()">
		            <option value="1" <c:if test="${pcBankCollectionInfo.collectionRange eq 1}"> selected</c:if>>小区</option>
		            <option value="2" <c:if test="${pcBankCollectionInfo.collectionRange eq 2}"> selected</c:if>>单户业主</option>
		        </select>
		        
		        <div id="gbRange">
		        	<h2 class="mtop20">已勾选列表</h2> 
		        	<div class="bs-example bgebeb overflowDiv">
		        		<table class="info-list-02" style="width: 400px;min-width: 700px;" id="selectedGbTable">
		        			<thead>
					          	<tr>
						            <th style="width: 400px;">管理处名称</th>
						            <th style="width: 300px;">小区</th>
					          	</tr>
				          	</thead>
				          	<tbody></tbody>
				        </table>
		        	</div>
			        
		        	<h2 class="mtop20">请选择</h2>
		        	<div class="bs-example bgebeb">
			        	<table class="info-list" style="width:680px;">
				          	<tr>
					            <td style="width: 100px;text-align: right;">管理处：</td>
					            <td style="width: 240px;text-align: left;padding-left: 5px;">
					           		<input type="text" id="pmName1" class="input_text pp w240"/>
					            </td>
					            <td style="width: 100px;text-align: right;">小区名称：</td>
					            <td style="width: 240px;text-align: left;padding-left: 5px;">
					           		<input type="text" id="gbName1" class="input_text pp w240"/>
					            </td>
				          	</tr>
				        </table>
		        	</div>
			        
				    <div class="mtop20 overflowDiv">
				        <table class="info-list-02" id="gbTable">
				        	<thead>
					          	<tr>
						            <th style="width: 80px;text-align: center;"><input type="checkbox" id="gbCheckboxAll" onclick="selectAllGbBox()"/>全选</th>
						            <th style="text-align: center;width: 200px;">管理处名称</th>
						            <th style="text-align: center;width: 200px;">小区</th>
					          	</tr>
				          	<thead>
				          	<tbody>
					          	<c:forEach items="${bcInfoByGbs}" var="row">
					          		<tr>
							           	<td style="width: 80px;text-align: center;">
							           		<input type="checkbox" name="gbId" value="${row.gbId}" bcStatus="${row.bcStatus}" <c:if test="${row.bcStatus eq 2}"> checked</c:if> onclick="selectGb(this)"/>
							           	</td>
							            <td style="text-align: center;width: 200px;">${row.pmName}</td>
							            <td style="text-align: center;width: 200px;">${row.gbName}</td>
						          	</tr>
								</c:forEach>
				          	</tbody>
				        </table>
			        </div>
		        </div>
		        
		        <div id="ppRange">
		        	<h2 class="mtop20">已勾选列表</h2> 
		        	<div class="bs-example bgebeb overflowDiv">
			        	<table class="info-list-02" id="selectedPpTable">
			        		<thead>
					          	<tr>
						            <th style="width: 200px;">管理处</th>
						            <th style="width: 200px;">小区</th>
						            <th style="width: 80px;">楼栋</th>
						            <th style="width: 80px;">单元</th>
						            <th style="width: 80px;">房号</th>
						            <th style="width: 50px;">业主姓名</th>
						            <th style="width: 150px;">业主托收银行卡号</th>
						            <th style="width: 150px;">业主开卡银行</th>
					          	</tr>
				          	</thead>
				          	<tbody></tbody>
				        </table>
			        </div>
			        
		        	<h2 class="mtop20">请选择</h2>
		        	<div class="bs-example bgebeb">
			        	<table class="info-list">
				          	<tr>
					            <td style="width: 80px;text-align: right;">管理处：</td>
					            <td style="width: 200px;text-align: left;padding-left: 5px;">
					           		<input type="text" id="pmName2" class="input_text pp w240"/>
					            </td>
					            <td style="width: 80px;text-align: right;">小区：</td>
					            <td style="width: 200px;text-align: left;padding-left: 5px;">
					           		<input type="text" id="gbName2" class="input_text pp w240"/>
					            </td>
					            <td style="width: 80px;text-align: right;">楼栋：</td>
					            <td style="width: 200px;text-align: left;padding-left: 5px;">
					           		<input type="text" id=buildingName class="input_text pp w240"/>
					            </td>
					            <td style="width: 80px;text-align: right;">单元：</td>
					            <td style="width: 200px;text-align: left;padding-left: 5px;">
					           		<input type="text" id="unitName" class="input_text pp w240"/>
					            </td>
				          	</tr>
				          	<tr>
					            <td style="width: 80px;text-align: right;">房号：</td>
					            <td style="width: 200px;text-align: left;padding-left: 5px;">
					           		<input type="text" id="roomName" class="input_text pp w240"/>
					            </td>
					            <td style="width: 80px;text-align: right;">业主姓名：</td>
					            <td style="width: 200px;text-align: left;padding-left: 5px;">
					           		<input type="text" id="ppName" class="input_text pp w240"/>
					            </td>
					            <td style="width: 80px;text-align: right;">业主银行卡号：</td>
					            <td style="width: 200px;text-align: left;padding-left: 5px;">
					           		<input type="text" id="bankAcount" class="input_text pp w240"/>
					            </td>
					            <td style="width: 80px;text-align: right;">业主开卡银行：</td>
					            <td style="width: 200px;text-align: left;padding-left: 5px;">
					           		<input type="text" id="bankName" class="input_text pp w240"/>
					            </td>
				          	</tr>
				        </table>
			        </div>
			        
				    <div class="mtop20 overflowDiv">
				        <table class="info-list-02" id="ppTable">
				        	<thead>
					          	<tr>
						            <th style="width: 80px;text-align: center;"><input type="checkbox" id="ppCheckboxAll" onclick="selectAllPpBox()"/>全选</th>
						            <th style="width: 200px;">管理处</th>
						            <th style="width: 200px;">小区</th>
						            <th style="width: 80px;">楼栋</th>
						            <th style="width: 80px;">单元</th>
						            <th style="width: 80px;">房号</th>
						            <th style="width: 50px;">业主姓名</th>
						            <th style="width: 150px;">业主托收银行卡号</th>
						            <th style="width: 150px;">业主开卡银行</th>
					          	</tr>
					        </thead>
					        <tbody>
					          	<c:forEach items="${bcInfoByPPs}" var="row">
					          		<tr style="width:100%;">
							           	<td style="width: 80px;text-align: center;">
							           		<input type="checkbox" name="ppbciId" value="${row.ppbciId}" gbId="${row.gbId}" bcStatus="${row.bcStatus}" <c:if test="${row.bcStatus eq 2}"> checked</c:if> onclick="selectPp(this)"/>
							           	</td>
							            <td style="width: 200px;">${row.pmName}</td>
							            <td style="width: 200px;">${row.gbName}</td>
							            <td style="width: 80px;">${row.buildingName}</td>
							            <td style="width: 80px;">${row.unitName}</td>
							            <td style="width: 80px;">${row.roomName}</td>
							            <td style="width: 50px;">${row.ppName}</td>
							            <td style="width: 150px;">${row.bankAcount}</td>
							            <td style="width: 150px;">${row.bankName}</td>
						          	</tr>
								</c:forEach>
							</tbody>
				        </table>
			        </div>
		        </div>
		        <input class="info-btn mtop10" type="button" onclick="save()" value="保存"/>
		    </form>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script src="${resourcePath}/commonjs/layer/layer.js"></script>
	<script type="text/javascript">
		
		function save(){
			if($.trim($("#no").val())==""){
				alert("编号不能为空！");
				return false;
			}
			if($.trim($("#contractNo").val())==""){
				alert("协议号不能为空！");
				return false;
			}
			if($.trim($("#bankAccount").val())==""){
				alert("物业托收收款账号不能为空！");
				return false;
			}
			
			var collectionRange = $("#collectionRange").val();
			if(collectionRange==1){
				if($("input[name=gbId]:checkbox:checked").length==0){
					alert("小区至少选一个，请勾选要托管的小区！");
					return false;
				}
			} else {
				if($("input[name=ppbciId]:checkbox:checked").length==0){
					alert("业主至少选一个，请勾选要托管的业主！");
					return false;
				}
				var gbIds = new Array();
				$("input[name=ppbciId]:checkbox:checked").each(function(){
					gbIds.push($(this).attr("gbId"));
				});
				$("#gbIds").val(JSON.stringify(gbIds));
			}
			$("#bankOrgName").val($("#bankOrg").find("option:selected").text());
			
			var layermsg = layer.msg('正在提交，请稍候', {
        		icon: 16,
        		time: 0,
        		shade: 0.5
        	});
			$("#saveForm").submit();
			layer.close(layermsg);
		}
		
		$("#pmName1, #gbName1").on("input propertychange", function(){
			gbFilter();
		});
		
		function gbFilter(){
			var pmName = $.trim($("#pmName1").val());
			var gbName = $.trim($("#gbName1").val());
			var searchKeys = [pmName, gbName];
			
			var $this = $("#gbTable tbody tr").hide();
			for(var i=0; i<searchKeys.length; i++){
				$this = $this.find("td:eq("+(i+1)+")").filter(":contains('"+searchKeys[i]+"')").parent();
			}
			$this.show();
			window.parent.TuneHeight();
		}
		
		$("#pmName2, #gbName2, #buildingName, #unitName, #roomName, #ppName, #bankAcount, #bankName").on("input propertychange", function(){
			ppFilter();
		});
		
		function ppFilter(){
			var pmName = $.trim($("#pmName2").val());
			var gbName = $.trim($("#gbName2").val());
			var buildingName = $.trim($("#buildingName").val());
			var unitName = $.trim($("#unitName").val());
			var roomName = $.trim($("#roomName").val());
			var ppName = $.trim($("#ppName").val());
			var bankAcount = $.trim($("#bankAcount").val());
			var bankName = $.trim($("#bankName").val());
			
			var searchKeys = [pmName, gbName, buildingName, unitName, roomName, ppName, bankAcount, bankName];
			
			var $this = $("#ppTable tbody tr").hide();
			for(var i=0; i<searchKeys.length; i++){
				$this = $this.find("td:eq("+(i+1)+")").filter(":contains('"+searchKeys[i]+"')").parent();
			}
			$this.show();
			window.parent.TuneHeight();
		}
		
		function selectAllGbBox(){
			var isChecked = $("#gbCheckboxAll").is(":checked");
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
				initSelectGb(true);
			} else {
				$("#selectedGbTable tbody").html("");
			}
			window.parent.TuneHeight();
		}
		
		function initSelectGb(isSelectAll){
			var allHtml="";
			$("#gbTable tbody tr").each(function(){
				if((isSelectAll && $(this).is(":visible")) || !isSelectAll){
					if(isSelectAll || (!isSelectAll && $(this).find(":checkbox").is(":checked"))){
						var html="";
						$(this).find("td:gt(0)").each(function(){
							html += "<td>"+$(this).html()+"</td>";
						});
						
						var gbId = $(this).find(":checkbox").val();
						html = "<tr gbId="+gbId+">"+html+"</tr>";
						allHtml += html;
					}
				}
			});
			$("#selectedGbTable tbody").html(allHtml);
		}
		
		function selectGb(o){
			var gbId = $(o).val();
			if($(o).is(":checked")){// insert
				var html="";
				$(o).parent().parent().find("td:gt(0)").each(function(){
					html += "<td>"+$(this).html()+"</td>";
				});
				
				html = "<tr gbId="+gbId+">"+html+"</tr>";
				if($("#selectedGbTable tbody tr").length==0){
					$("#selectedGbTable tbody").html(html);
				} else {
					$("#selectedGbTable tr:last").after(html);
				}
			} else {// remove
				$("#selectedGbTable").find("tr[gbId="+gbId+"]").remove();
			}
			window.parent.TuneHeight();
		}
		
		function selectAllPpBox(){
			var isChecked = $("#ppCheckboxAll").is(":checked");
			$("#ppTable tbody tr").each(function(){
				if($(this).is(":visible")){
					$(this).find(":checkbox").prop("checked", isChecked);
				} else {
					if(isChecked){
						$(this).find(":checkbox").prop("checked", false);
					}
				}
			});
			
			if(isChecked){
				initSelectPp(true);
			} else {
				$("#selectedPpTable tbody").html("");
			}
			window.parent.TuneHeight();
		}
		
		function initSelectPp(isSelectAll){
			var allHtml="";
			$("#ppTable tbody tr").each(function(){
				if((isSelectAll && $(this).is(":visible")) || !isSelectAll){
					if(isSelectAll || (!isSelectAll && $(this).find(":checkbox").is(":checked"))){
						var html="";
						$(this).find("td:gt(0)").each(function(){
							html += "<td>"+$(this).html()+"</td>";
						});
						
						var ppbciId = $(this).find(":checkbox").val();
						html = "<tr ppbciId="+ppbciId+">"+html+"</tr>";
						allHtml += html;
					}
				}
			});
			$("#selectedPpTable tbody").html(allHtml);
		}
		
		function selectPp(o){
			var ppbciId = $(o).val();
			if($(o).is(":checked")){// insert
				var html="";
				$(o).parent().parent().find("td:gt(0)").each(function(){
					html += "<td>"+$(this).html()+"</td>";
				});
				
				html = "<tr ppbciId="+ppbciId+">"+html+"</tr>";
				if($("#selectedPpTable tbody tr").length==0){
					$("#selectedPpTable tbody").html(html);
				} else {
					$("#selectedPpTable tr:last").after(html);
				}
			} else {// remove
				$("#selectedPpTable").find("tr[ppbciId="+ppbciId+"]").remove();
			}
			window.parent.TuneHeight();
		}
		
		function showDivByRange(){
			var range = $("#collectionRange").val();
			
			if(range==2){
				initSelectPp(false);
				$("#gbRange").hide();
				$("#ppRange").show();
			} else {
				initSelectGb(false);
				$("#gbRange").show();
				$("#ppRange").hide();
			} 
			window.parent.TuneHeight();
		}
		
		$(function(){
			showDivByRange();
			window.parent.TuneHeight();
			$("body").show();
		});
	</script>
</html>