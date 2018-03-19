<%@ page import="com.cnfantasia.server.common.CommConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>物业公告管理</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/displaytag-css/alternative.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-ui-1.11.4.custom/jquery-ui.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/select2.css">
		<style type="text/css">
			.ui-autocomplete-category {
	            font-weight: bold;
	            padding: .2em .4em;
	            margin: .8em 0 .2em;
	            line-height: 1.5;
	        }
		</style>
	</head> 
	<body>
		<div class="info">
		    <h2>物业公告管理</h2>
		    <div class="bs-example bgebeb">
		    	<form id="searchForm" name="form_main" action="" method=post>
			        <table class="info-list" border="0">
			          <tr>
			            <td><div align="right">公告标题关键字：</div></td>
			            <td><input name="title" type="text" class="input_text w120 pp" value="${param.title}"></td>
			            <td><div class="list-name">发布时间：</div></td>
			            <td>
			            	<input id="date01" name="date01" type="text" class="input_text icon_dt"  title="请选择起始时间" value="${param.date01}"> 
			            	至
			            	<input  type="text" class="input_text icon_dt" id="date02"  name="date02" title="请选择结束时间" value="${param.date02}"></td>
			            <td><div class="list-name">推送小区：</div></td>
					    <td>
						  <select id="groupBuiliding" name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
							  <option value="">选择小区</option>
						  </select>
					    </td>
			            <td><input id="searchBtn" class="input-btn w80" type="submit" value="搜索"></td>
			          </tr>
			        </table>
		    	</form>
		    </div>    
		    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
				<display:column title="公告标题" property="title" sortable="true"/>
				<display:column title="推送小区">
					<span title="${row.gbNamesTip}">
						<c:forEach items="${row.gbNames}" var="gbName" varStatus="k"> 
							<c:if test="${k.index le 1}">
								${gbName}
								<c:if test="${(k.index eq 0) and (not k.last)}">，</c:if>
								<c:if test="${(k.index eq 1) and (not k.last)}">....</c:if>
							</c:if>
						</c:forEach>
					</span>
				</display:column>
				<display:column title="推送时间" property="time" />
				<display:column title="状态" >
					<c:choose>
						<c:when test="${row.sendState eq 1}">已发布</c:when>
						<c:otherwise>未发布</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="公告编辑" media="html">
					<a class="blue" href="${pageContext.request.contextPath}/notice/noticeEdit.html?id=${row.id}&pageType=view">查看</a> &nbsp;&nbsp;&nbsp;&nbsp;
					<a class="blue" href="${pageContext.request.contextPath}/notice/noticeEdit.html?id=${row.id}&pageType=update">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;
					<a class="blue deleteNotice" href="javascript:void(0)" name="${row.id}">删除</a> &nbsp;&nbsp;&nbsp;&nbsp;
				</display:column>
				<display:column title="操作" media="html">
					<c:if test="${row.sendState ne 1}">
						<a class="blue" href="javascript:publish(${row.id})">发布</a>&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					<a class="blue" href="javascript:printNotice(${row.id})">打印公告</a> 
				</display:column>
			</display:table>
			<div class="padb"><div id="addNotice" class="info-btn mtop20 left"><a id="addNotice" href="#">新增公告</a></div></div>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simple-dtpicker01.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/select2.js"></script>
	<script type="text/javascript" >
		$("#addNotice").click(function(){//新增公告
			var href = "${pageContext.request.contextPath}/notice/noticeEdit.html?pageType=add";
			$(window.parent.document).find("#mainFrame").attr("src", href);
		});
		
		$(".deleteNotice").click(function(event){
			if(window.confirm('您确定要删除该记录吗？')){
				var deleteURL = "${pageContext.request.contextPath}/notice/noticeDelete.html";
				var idValue = $(this).attr("name");//name属性里放的是ID值
				$.ajax({//使用ajax请求删除数据
					type: "GET",
					url: deleteURL,
					async:true,
					data: {id:idValue,},
					success: function(data, textStatus){
				        alert("操作提示："+data);
					},
				}); 
				$(this).parents('tr').remove();
			}else{
				return false;
			}
		});
		
		$("#searchBtn").click(function(){//查找
			$("#searchForm").attr("action","${pageContext.request.contextPath}/notice/search.html");
			$("#searchForm").submit();
		});
		
	    function gbFilter(){
	    	$.widget( "custom.catcomplete", $.ui.autocomplete, {
                _create: function() {
                    this._super();
                    this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
                },
                _renderMenu: function( ul, items ) {
                    var that = this,
                    currentCategory = "";
                    $.each( items, function( index, item ) {
                        var li;
                        if ( item.category != currentCategory ) {
                            ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
                            currentCategory = item.category;
                        }
                        li = that._renderItemData( ul, item );
                        if ( item.category ) {
                            li.attr( "aria-label", item.category + " : " + item.label );
                        }
                    });
                }
            });
	    	
	        $("#gbName" ).autocomplete({
	            source:function (request, response) {
	                $.post("${pageContext.request.contextPath}/notice/gbFilter.html", {"gbName": $.trim($("#gbName").val())},
	                    function (result) {
	                        response($.map(result.dataValue, function (item) {
	                            return {label: item.gbName, value: item.gbName};
	                        })); 
	                    }
	                );
	            },
	            delay:0
	        });
	    }
	    gbFilter(); 
	    
	    function printNotice(msgId){
	    	window.open("${pageContext.request.contextPath}/notice/print.html?msgId="+msgId);
	    }
	    
	    function publish(msgId){
	    	if(confirm("您确定要发布吗？")){
	    		$.post("${pageContext.request.contextPath}/notice/publish.html", {"id": msgId},
                    function (data) {
		    			alert(data.message);
						if(data.status == "<%=CommConstants.ResponseStatus.SUCCESS%>") {
							location = location;
						} 
                    }
                );
	    	}
	    }

		$(function(){
			var gbName = '${param.gbName}';
			$.ajax({
				url: '<%=basePath%>/groupBuilding/getGroupBuildings.json',
				dataType: 'json',
				success: function (data) {
					var list = JSON.parse(data);
					var strHtml = "<option value=''>选择小区&nbsp;&nbsp;&nbsp;</option>";
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
