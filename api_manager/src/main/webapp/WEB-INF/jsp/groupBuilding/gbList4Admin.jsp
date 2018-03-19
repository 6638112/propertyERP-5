<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page import="com.cnfantasia.server.ms.groupBuilding.constant.GbMsgType"%>
<%@page import="com.cnfantasia.server.ms.pub.session.UserContext" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-正式小区参数配置</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css?20160526" />
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
<script src="${pageContext.request.contextPath}/js/word.count.js"></script>
</head>

<body>
<div class="info">
    <h2>小区管理</h2>
    <div class="bs-example bgebeb">
    	<form method="post" action="../groupBuilding/search4Admin.html">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div align="right">物业公司：</div></td>
	            <td><input type="text" value="${param.pcName }" class="input_text pp w120" name="pcName" /></td>
	            <td><div align="right">管理处：</div></td>
	            <td><input type="text" value="${param.pmName }" class="input_text pp w120" name="pmName" /></td>
	            <td><div align="right">小区名称：</div>
	            </td>
	            <td>
	          		<input type="text" class="input_text pp w120" value="${param.gbName }" name="gbName" />
	            </td>
	            <td><div align="right">是否可缴费：</div>
	            </td>
	            <td>
	          		<select name="isCanPay" class="select_normal w131">
	          			<option value="-1" >请选择</option>
	          			<option value="1" <c:if test="${param.isCanPay==1 }">selected="selected"</c:if> >是</option>
	          			<option value="2" <c:if test="${param.isCanPay==2 }">selected="selected"</c:if> >否</option>
	          		</select>
	            </td>
	            <td><div align="right">缴费方式：</div>
	            </td>
	            <td>
	          		<select name="paytimeType" class="select_normal w131">
	          			<option value="-1" >请选择</option>
	          			<option value="1" <c:if test="${param.paytimeType==1 }">selected="selected"</c:if> >月度缴费</option>
	          			<option value="2" <c:if test="${param.paytimeType==2 }">selected="selected"</c:if> >周期缴费</option>
	          		</select>
	            </td>
	            <td><div align="right">签约状态：</div></td>
	            <td>
					<select name="signStatus" class="select_normal w131">
	                    <option value="-1">全部</option>
	                    <option value="0" <c:if test="${param.signStatus==0 || signStatus ==0 }"> selected="selected"</c:if>>未签约</option>
	                    <option value="1" <c:if test="${param.signStatus==1 || signStatus==1 }"> selected="selected"</c:if>>已签约</option>
                    </select>
				</td>
				<td></td>
	            <td>
	            	<input class="input-btn w80" id="searchBtn" type="submit" value="搜索"/>
	            </td>
	            <td></td>
	            <td></td>
	          </tr>
	        </table>
    	</form>
    </div>
    
    <display:table name="resList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="resultSize" >
		<display:column title="小区名称" sortable="true" property='name'>
		</display:column>
		<display:column title="所在地">
			${row.addressProvinceName}-${row.addressCityName}-${row.addressBlockName}
		</display:column>
		<display:column title="详细地址" property="addressDesc" />
		<display:column title="所属管理处">
			${row.propertyManagementName}
		</display:column>
		<display:column title="所属物业公司">
			${row.propertyCompanyName}
		</display:column>
		<display:column title="签约状态">
			<c:if test="${row.signStatus ==1 }">已签约</c:if>
			<c:if test="${row.signStatus ==0 }">未签约</c:if>
		</display:column>
		<display:column title="是否可缴费">
			<c:if test="${row.propfeeCanpay ==0 }">已关闭</c:if>
			<c:if test="${row.propfeeCanpay ==1 }">已开启</c:if>
		</display:column>
		<display:column title="周期是否跨月">
			<c:if test="${row.propertyMonthChange ==-1 }">本月缴上月</c:if>
			<c:if test="${row.propertyMonthChange ==0 }">本月缴当月</c:if>
			<c:if test="${row.propertyMonthChange ==1 }">本月缴下月</c:if>
		</display:column>
		<display:column title="审核状态">
			<c:if test="${row.checkStatus ==1 }">审核通过</c:if>
			<c:if test="${row.checkStatus ==9 }">无须审核</c:if>
		</display:column>
		<display:column title="操作" >
			<a class="blue" name="view" href="../groupBuilding/initAuditEdit.html?id=${row.id }" >编辑</a> 
			&nbsp;&nbsp;&nbsp;&nbsp;<input class="input-btn" type="button" onclick="showImport(this)" value="导入楼栋房号业主" gbId='${row.id }' gbName='${row.name }'/>
			<c:if test="${row.signStatus == 1 }"> 
			&nbsp;&nbsp;&nbsp;&nbsp;<input class="input-btn" type="button" onclick="triggerDeducntion4GroupBuilding(this)" value="物业缴费卡划扣" gbId='${row.id }' gbName='${row.name }'/>
			</c:if>
			<c:if test="${row.signStatus ==1 }">
			&nbsp;&nbsp;&nbsp;&nbsp;<input class="input-btn" type="button" onclick="sendMsgDialog(${row.id})" value="短信通知"/>
			</c:if>
			<c:if test="${row.hasSoftwareDock }">
				&nbsp;&nbsp;&nbsp;&nbsp;<input class="input-btn" type="button" onclick="synSoftwareData(${row.id})" value="同步数据"/>
			</c:if>
		</display:column>
	</display:table>
</div>

<div class="layer-bill dsn">
   <a id="downLoadBtn" class="blue" href="javascript:exportExcelModel();"><img src="../images/download-icon.jpg" /> 第1步：下载房号数据导入模版</a>
   <br />
   <form id="inputFileForm" class="mtop10" enctype="multipart/form-data" action="../buildingRoom/importBuildingRoomProprietor.html" method="post">
   	 <input type="hidden" id="gbId" name="groupBuildingId" value="" />
   	 <input type="hidden" id="gbName" name="groupBuildingName" value="" />
     <span class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第2步：请选择您已完善的房号数据文件</span>
     <br/> 
     <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="file" id="excelFileInput" name="excelFile" size="50" title="上传楼栋" /></div>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="input-btn w120 mtop20" id="uploadBtn02" type="button" onclick="submitValidate();" value="上传" />
   </form>
</div>
<!-- 短信通知对话框 -->
<div class="msg_notice dsn" style="height: auto;padding-top:15px;padding-bottom:50px;padding-left: 15px;padding-right: 15px;">
  <form id="msgForm" enctype="multipart/form-data" action="${pageContext.request.contextPath}/groupBuilding/sendMsg.html" method="post" onsubmit="return false;"> 
  	<input type="hidden" id="gbId" name="gbId" value=""/>
    <h2>短信通知</h2>
    <div class="bs-example bgebeb">
		<table class="info-list" border="0">
	         <tr>
	           <td><div class="list-name bold">类型：</div></td>
	           <td>
	               <label><input type="radio" name="msgType" value="<%=GbMsgType.PAYMENT_NOTICE%>" checked="checked"/>缴费通知</label>
	           </td>
	           <td>
	           	   <label><input type="radio" name="msgType" value="<%=GbMsgType.OTHER_NOTICE%>"/>其它通知</label>
	           </td>
	         </tr>
	         <tr>
	           <td><div class="list-name bold">内部人员联系方式：</div></td>
	           <td colspan="2">
	           	   <input style="margin-top: 3px;" type="file" name="mobileFile" accept=".txt,.xlsx,.xls"/>
	           </td>
	         </tr>
	         <tr>
	         	<td colspan="3">
	         		<textarea id="msgContent" class="textareas" placeholder="请输入短信内容" name="msgContent" rows="5" cols="50"></textarea>
	         	</td>
	         </tr>
	         <tr>
	            <td><div class="black">短信内容</div></td>
	         	<td colspan="2" style="text-align: right;">
	         		<button class="sendBtn input-btn w160">发送</button>
	         	</td>
	         </tr>
    	 </table>
     </div>
	</form>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=5"></script>
<script type="text/javascript">
//导入账单弹出层
	function showImport(ths){
		var $this = $(ths);
		$('#gbId').val($this.attr('gbId'));
		$('#gbName').val($this.attr('gbName'));
		
		$.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
	    	border : [5, 0.3, '#000'],
			page: {dom : '.layer-bill'}
		});
	}
	
	//触发划扣
	function triggerDeducntion4GroupBuilding(ths){
		if(!window.confirm("确认要触一次划扣？")){
			return false;
		}
		
		var $this = $(ths);		
		$.ajax({
			url : "../propertyCard/triggerDeducntion4GroupBuilding.json",
			data: {gbId:$this.attr('gbId')},
			async : false,
			success : function(data) {
				if(data.status == "0000"){
					alert("划扣操作成功");
				}else{
					alert("操作失败：" + data.message);
				}
			},
			error: function(){
				alert("系统异常，请联系客服");
			}
		});
	}
	
	function submitValidate(){
		var fullFileName = $("#excelFileInput").val(); //选中文件的全名，包含路径
		var d = fullFileName.length - ".xls".length;
		if(fullFileName == ""){
			alert("请先选中一个上传文件");
			return false;
		}else if(fullFileName.indexOf(".xls") !=d){ 
			alert("上传的必须是后缀名为xls的Excel文件，请先下载模板进行编辑后再上传");
			return false;			
		}
		$("#uploadBtn02").attr("disabled",true);
		$("#uploadBtn02").css({"background":"#DCDCDC","color":"#FFFFFF","border":"0px","cursor":"wait"});
		$("#inputFileForm").submit();
	}
	
	function exportExcelModel(ths){
		location.href= "../buildingRoom/exportTemplate.html?groupBuildingName=" + $('#gbName').val();
	}
	
	// 短信通知对话框
	function sendMsgDialog(gbId){
		$(".msg_notice #gbId").val(gbId);
		$("#msgContent").val("");
		wordTool.reStartInputNum("msgContent", 150);
		$.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
	    	border : [5, 0.3, '#000'],
			page: {dom : '.msg_notice'}
		});
	}
// 同步物业软件数据
function synSoftwareData(gbId){
	if(confirm("同步时间可能会比较长，确定同步？")) {
		var layermsg;
		$.ajax({
			url : "../groupBuilding/synSoftwareData.html",
			data: {gbId: gbId},
			async : true,
			method : 'post',
			beforeSend: function(){
				layermsg = layer.load('数据同步中，请稍候', {
					time: 0,
					shade: [0.5, '#000']
				});
			},
			success : function(data) {
				
				layer.close(layermsg);
				
				if(data.status == "0000"){
					alert("数据同步成功！");
				}else{
					alert("操作失败：" + data.message);
				}
			},
			error: function(){
				layer.close(layermsg);
				alert("系统异常，请联系客服");
			}
		});
	}
}
	
	$(function(){
		$("#msgForm").Validform({
			tiptype:3,
			btnSubmit:".sendBtn",
			dragonfly:true,
			ajaxPost:false,
			beforeCheck:function(){
				var msgContent = $.trim($("#msgContent").val());
				if(msgContent==""){
					alert("短信内容不能为空！");
					return false;
				}
			},
			callback:function(){
				$("#msgForm").ajaxSubmit(function(data) {
					if (data.status == "<%=CommConstants.ResponseStatus.SUCCESS%>") {
						layer.closeAll();
					}
					alert(data.message);
					$("#searchBtn").click();
				});
			}
		});
		
		wordTool.init([{"id":"msgContent", "max":150, "isWrap":false}]);
	});
</script>
</html>
