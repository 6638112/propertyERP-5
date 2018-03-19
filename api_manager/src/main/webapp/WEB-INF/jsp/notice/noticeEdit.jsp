<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增物业公告</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<style type="text/css">
	iframe{ border: solid 1px #ddd !important;}
	.editMode{ font-size:16px;}
	.editMode img{ max-width: 100%;}
	.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
	.w760{width: 760px;}
	.w180{width: 170px;}
	.xhe_nostyle table.xheLayout{ border: 0 !important;}
	.container{border: 1px solid #C5C5C5;padding-right: 2px;}
	.lh30{ line-height:30px;}
	.p15{ padding: 15px;}
	.xhe_nostyle table{ margin-top: 10px !important;}
	.xhe_nostyle td.xheTool{ border-bottom: none !important; border-top: 1px solid #ddd !important; padding-top: 10px !important;}
</style>
</head>
<body style="display:none;">
<div class="info" style="max-width:1024px;">
	<form id="saveForm" name="saveForm" method="post">
	<input id="noticeNontent" type="hidden" name="noticeNontent" value=""/>
	<h2 class="mtop10">公告编辑</h2>
    <div class="container p15">
        <table class="info-list" border="0">
          <tr>
          	<td align="center"><input id="pageHeader" type="text" class="input_text w760 pp h30 lh30 f16 black t_center" name="pageHeader" placeholder="单击此处添加抬头" value="${messagePrint.pageHeader}" maxlength="50" /></td>
          </tr>
          <tr>
          	<td align="center"><input id="postTitle" type="text" class="input_text w500 pp black t_center" name="title" placeholder="单击此处添加标题" value="${msgBean.title}" maxlength="50" /></td>
          </tr>
        </table>
    	<textarea id="content" name="content" rows="30" maxlength="5000">${msgBean.content}</textarea>
    	<div class="t_right mtop10"><input id="signature" type="text" class="input_text w200 black t_right pp" name="signature" placeholder="单击此处添加落款" value="${messagePrint.signature}" maxlength="100" /></div>
    	<div class="t_right mtop10" id="publishTime">
    		<c:choose>
    			<c:when test="${empty msgBean.id}">
    				${now}
    			</c:when>
    			<c:otherwise>
    				${publishTime}
    			</c:otherwise>
    		</c:choose>
    	</div>
    	<div id="rqCodeImgBox" class="t_right mtop10<c:if test="${messagePrint.rqCode ne 1}"> dsn</c:if>"><img src="${pageContext.request.contextPath}/images/jfqerweima.jpg"></div>
    </div>
   	<div>
   		解放区二维码：
   		<label><input type="radio" name="rqCode" value="1" <c:if test="${messagePrint.rqCode eq 1}">checked</c:if>>有</label>
   		<label style="margin-left: 2em;"><input type="radio" name="rqCode" value="0" <c:if test="${messagePrint.rqCode ne 1}">checked</c:if>>无</label>
   	</div> 
    <h2 class="mtop40 f14">推送设置</h2>
    <div class="bs-example">  
    	<div style="margin-bottom: 10px;">
    		推送时间：
    		<label><input class="mtop3 rad-01" name="pushWay" type="radio" value="pushNow" <c:if test="${empty msgBean.time}">checked</c:if> onclick="setPublishDate('pushNow')">立即推送</label>
    		<label style="margin-left: 2em;"><input class="mtop3 rad-02" name="pushWay" type="radio" value="pushOtherTime" <c:if test="${not empty msgBean.time}">checked</c:if>>定时推送</label>
    		<span class="ser-time">：
    			<input type="text" id="pushTime" name="pushTime" title="请选择推送时间" value="${msgBean.time}" placeholder="请选择推送时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',onpicked:function(){setPublishDate();}});" class="input_text pp w180 icon_dt pp">
    		</span>
    	</div>
    	<div>
    		公告有效期：
    		<input type="text" id="expiryDateStart" name="expiryDateStart" title="请选择起始时间" value="${msgBean.expiryDateStart}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
           	至 
           	<input type="text" id="expirDateEnd" name="expirDateEnd" title="请选择结束时间" value="${msgBean.expiryDateEnd}" placeholder="请选择结束时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
    	</div>
    </div>
    <c:if test="${pageType ne 'view'}">
	    <h2 class="mtop10 f14">推送小区</h2>
	    <div id="areaSelectBox">
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
		        <td><div class="list-name">缴费权限：</div></td>
		        <td>
		            <select class="select_normal merchant-type" id="payOpenStatus">
		                <option value="">全部</option>
		                <option value="是">已开通缴费</option>
		                <option value="否">未开通缴费</option>
		            </select>
		        </td>
		      </tr>
		    </table>
		</div> 
		<h2 class="mtop10 f14">小区搜索结果</h2>
	</c:if>
	<div style="max-height: 346px; overflow-y: scroll; border-top: 1px solid #ddd; border-bottom: 1px solid #ddd;" id="gbDiv" <c:if test="${pageType eq 'view'}">class="dsn"</c:if>>
		<display:table name="gbList" id="row" class="info-list-02" requestURI="" >
			<display:column title="<input type='checkbox' onclick='selectAllBox(this)'/>"  sortable="true">
				<input name="gbId" type="checkbox" value="${row.id }" <c:if test="${row.isPushed=='yes'}">checked="checked"</c:if> onclick="selectGb(this)"/>
			</display:column>
			<display:column title="物业名称" property="propertyCompanyName"/>
			<display:column title="小区名称" property="name"/>
			<display:column title="缴费开通状态">
				<c:choose>
					<c:when test="${row.propfeeCanpay eq 1}">是</c:when>
					<c:when test="${row.propfeeCanpay eq 2}">否</c:when>
				</c:choose>
			</display:column>
			<display:column title="所在省" property="addressProvinceName"></display:column>
			<display:column title="所在市" property="addressCityName"></display:column>
			<display:column title="所在区" media="html" property="addressBlockName"></display:column>
			<display:column title="详细地址" property="addressDesc" />
		</display:table>
	</div>
	<h2 class="mtop20 f14">已选择小区</h2>
	<div style="max-height: 346px; overflow-y: scroll; border-top: 1px solid #ddd; border-bottom: 1px solid #ddd;">
	    <table id="areaHasSelectedList" class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	      <thead>
		      <tr class="title">
		        <th>物业名称</th>
		        <th>小区名称</th>
		        <th>缴费开通状态</th>
		        <th>所在省</th>
		        <th>所在市</th>
		        <th>所在区</th>
		        <th>详细地址</th>
		        <c:if test="${pageType ne 'view'}">
		        	<th>操作</th>
		        </c:if>
		      </tr>
	      </thead>
	      <tbody>
	      </tbody>
	    </table>
	</div>
	<c:if test="${pageType ne 'view'}">
		<div class="mtop10 t_center">
			<input class="input-btn w150" type="button" value="清空已选择小区" onclick="clearGb()"/>
		</div> 
	</c:if>
</div>
	<c:if test="${pageType ne 'view'}">
	    <input name="msgId" type="hidden" value="${msgBean.id}"/>
	    <input name="mpId" type="hidden" value="${messagePrint.id}"/>
	    <input id="sumNotice" class="info-btn" type="submit" value="提 交" />
    </c:if>
</form>
</div>
<c:if test="${pageType eq 'view'}">
	<div style="z-index: 9999999; width: 100%;height: 100%;position: absolute;top: 0px;left: 0px;"></div>
</c:if>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/xheditor_v1.2/xheditor-1.2.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/xheditor_v1.2/xheditor_lang/zh-cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tool.js"></script>
<script src="${resourcePath}/commonjs/layer/layer.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		$('#content').xheditor({
            tools:'Bold,Align,Italic,Underline,Strikethrough,FontColor,Removeformat,Link,Unlink,Img,Preview,Print',
            upImgUrl:'${pageContext.request.contextPath}/xhedit/upload.html',  // 图片上传接口地址 
            skin:'nostyle',
            width:'100%', 
            height:'400px',
            layerShadow:1,
            submitID:'submitBtn',
            upBtnText:'上传',
            emotMark:true,
            cleanPaste:3	//参数：0(不做任何清理),1(简单清理Word),2(深入清理Word),3(强制转文本),默认为1简单清理Word
        });
		<c:if test="${not empty msgBean.time}">
		$(":radio[value='pushOtherTime']").click();
		</c:if>
    });

	/*<=====================================小区搜索=====================================*/
	$('#pcName, #gbName, #payOpenStatus, #province, #city, #block').on('input propertychange', function(){
		filterGb();
	});
	
	function filterGb(){
		var payOpenStatus = $("#payOpenStatus").val();
		var province = $.trim($("#province").val());
		var city = $.trim($("#city").val());
		var block = $.trim($("#block").val());
		var pcName = $.trim($("#pcName").val());
		var gbName = $.trim($("#gbName").val());
		
		var searchKeys = [pcName, gbName, payOpenStatus, province, city, block];
		var $this = $("#gbDiv tbody tr").hide();
		for(var i=0; i<searchKeys.length; i++){
			$this = $this.find("td:eq("+(i+1)+")").filter(":contains('"+searchKeys[i]+"')").parent();
		}
		
		var selectStatus = $("#selectStatus").val();
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
			if($("#areaHasSelectedList tbody tr").length==0){
				$("#areaHasSelectedList tbody").html(html);
			} else {
				$("#areaHasSelectedList tr:last").after(html);
			}
		} else {// remove
			$("#areaHasSelectedList").find("tr[gbId="+gbId+"]").remove();
		}
		window.parent.TuneHeight();
	}
	
	function initSelectGbs(){
		var allHtml="";
		$("#gbDiv tbody tr").each(function(){
			if($(this).find(":checkbox").is(":checked")){
				var gbId = $(this).find(":checkbox").val();
				var html="";
				$(this).find("td:gt(0)").each(function(){
					html += "<td>"+$(this).html()+"</td>";
				});
				<c:if test="${pageType ne 'view'}">
				html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeGb(this)\">删除</a></td>";
				</c:if>
				html = "<tr gbId="+gbId+">"+html+"</tr>";
				allHtml += html;
			}
		});
		$("#areaHasSelectedList tbody").html(allHtml);
		window.parent.TuneHeight();
	}
	
	function selectAllBox(o){
		var isChecked = $(o).is(":checked");
		$("#gbDiv tbody tr").each(function(){
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
			$("#gbDiv tbody tr").each(function(){
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
			$("#areaHasSelectedList tbody").html(allHtml);
		} else {
			$("#areaHasSelectedList tbody").html("");
		}
		window.parent.TuneHeight();
	};
	
	function removeGb(o){
		var delTr = $(o).parent().parent();
		var gbId = delTr.attr("gbId");
		$("#gbDiv tbody tr").find(":checkbox[value="+gbId+"]").prop("checked", false);
		delTr.remove();
		window.parent.TuneHeight();
	}
	
	function clearGb(){
		$("#areaHasSelectedList tbody").html("");
		$("#gbDiv").find(":checkbox").each(function(){
			if($(this).is(":checked")){
				$(this).prop("checked", false);
			}
		});
		window.parent.TuneHeight();
	}
	/*=====================================小区搜索=====================================>*/
	<c:if test="${pageType ne 'view'}">
	$("#sumNotice").click(
		function() {//提交保存
			if($("input[name=gbId]:checkbox:checked").length==0){
				alert("请选择要推送的小区！");
				return false;
			}
		
			if($.trim($("#postTitle").val())=="" ){
				alert("标题不可为空！");
				return false;
			}
			
			if($.trim($("#content").val())==""){
				alert("公告内容不可为空！");
				return false;
			}
			
			if($("#content").val().length>5000){
				alert("公告内容超长，请不要超出5000字符！");
				return false;
			}
			
			var pushWay = $(":radio[name='pushWay']:checked").val();
			if(pushWay=="pushOtherTime" && $.trim($("#pushTime").val())==""){
				alert("请选择定时推送时间！");
				return false;
			}
			var expiryDateStart = $.trim($("#expiryDateStart").val());
			if(expiryDateStart==""){
				alert("公告有效期开始时间不能为空！");
				location = "#expiryDateStart";
				return false;
			}
			var expirDateEnd = $.trim($("#expirDateEnd").val());
			if(expirDateEnd==""){
				alert("公告有效期截止时间不能为空！");
				location = "#expirDateEnd";
				return false;
			}
			
			// xheditor会对“"”等转义，所以此处会处理
			var noticeNontent = $("#xhe0_iframe").contents().find(".editMode").html();
			noticeNontent = noticeNontent.replace(/&amp;/g, '&');
			noticeNontent = noticeNontent.replace(/&gt;/g, '>');
			noticeNontent = noticeNontent.replace(/&lt;/g, '<');
			noticeNontent = noticeNontent.replace(/&nbsp;/g, ' ');
			$("#noticeNontent").val(noticeNontent);
			var layermsg = layer.msg('正在提交，请稍候', {
        		icon: 16,
        		time: 0,	//指定time为0，默认该消息不关闭；若2秒后，再弹出layer.alert('加载完成', 1);该消息同时关闭。
        		shade: 0.5
        	});
			$("#saveForm").attr("action","${pageContext.request.contextPath}/notice/noticeSave.html");
			
			layer.close(layermsg);
			//再刷新iframe
			$(window.parent.document).find("#mainFrame").attr('src', "${pageContext.request.contextPath}/notice/index.html");
	});
	</c:if>
	$(function(){
		initSelectGbs();
		$("#gbDiv").find("tr:eq(0) th:eq(0) a").attr("href", "javascript:void(0);");
		
		$('[name=rqCode]').change(function(){
			var thisVal = $(this).val();
			if($(this).is(':checked') && thisVal === '1'){
				$('#rqCodeImgBox').show();
			}else{
				$('#rqCodeImgBox').hide();
			}
			window.parent.TuneHeight();
		});
		
		$("body").show();
	});
	
	function setPublishDate(type){
		var d = '';
		if(type=="pushNow"){
			d = new Date().Format("yyyy年MM月dd日");
		} else {
			d = $("#pushTime").val();
			d = d.substring(0, 10);
			d = d.replace('-', '年');
			d = d.replace('-', '月');
			d = d+'日';
		}
		$("#publishTime").html(d);
	}
</script>
</html>