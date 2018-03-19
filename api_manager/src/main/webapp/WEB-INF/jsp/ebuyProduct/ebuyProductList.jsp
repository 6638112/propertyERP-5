<%@page import="com.cnfantasia.server.api.payment.constant.EbuyDict"%>
<%@page import="com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager" %>
<%@page import="com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<% 
	response.setHeader("Pragma","No-cache");    
	response.setHeader("Cache-Control","no-cache");    
	response.setDateHeader("Expires", -10);
	
	request.setAttribute("app", EbuyDict.WlAppType.Jfq);
	request.setAttribute("jfqLightApp", EbuyDict.WlAppType.Jfq_Light_App);
	request.setAttribute("wlLightApp", EbuyDict.WlAppType.Wl_Light_App);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/clearbox.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-ui-1.11.4.custom/jquery-ui.min.css">
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
    <h2>商品列表</h2>
    <div class="bs-example bgebeb">
    	<form id="searchForm" name="form_main" action="${pageContext.request.contextPath}/ebuyProduct/search.html?state=list" method="post">
        <table class="info-list" border="0">
          <tr>
            <td><div align="right">商品名称：</div></td>
            <td><input id="name" name="name" type="text" class="input_text w200 pp" value="${param.name}"></td>
           	<td><div class="list-name">供应商名称：</div></td>
            <td>
                  <select id="tSupplyMerchantFId" name="tSupplyMerchantFId" class="select_normal">
                      <c:if test="${fn:length(supplyMerchantList) > 1}"><option value="">全部</option></c:if>
                      <c:forEach var="supplyMerchant" items="${supplyMerchantList}" >
						  <option value="${supplyMerchant.id}" <c:if test="${supplyMerchant.id eq param.tSupplyMerchantFId}">selected</c:if> >${supplyMerchant.name}</option>
					  </c:forEach>
                  </select>
            </td>
          </tr>
          <tr>
            <td><div class="list-name">商品状态：</div></td>
            <td>
                <select id="status" name="status" class="select_normal">
                    <option value="">全部</option>
                    <option value="0" <c:if test="${(not empty param.status) and (param.status eq 0)}">selected</c:if>>已上架</option>
                    <option value="1" <c:if test="${param.status eq 1}">selected</c:if>>已下架</option>
                    <option value="2" <c:if test="${param.status eq 2}">selected</c:if>>草稿</option>
                    <option value="3" <c:if test="${param.status eq 3}">selected</c:if>>待审核</option>
                    <option value="4" <c:if test="${param.status eq 4}">selected</c:if>>审核不通过</option>
                </select>
            </td>
            <td><div class="list-name">更新时间：</div></td>
            <td colspan="3"><input type="text" class="input_text icon_dt" id="date01" name="sys0UpdTime_START" title="请选择起始时间" value="${param.sys0UpdTime_START}"> 至 <input type="text" class="input_text icon_dt" id="date02" name="sys0UpdTime_END" title="请选择结束时间" value="${param.sys0UpdTime_END}"></td>
          </tr>
          <tr>
            <td colspan="8" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
          </tr>
        </table>
        </form>
    </div>
    <display:table name="resList" id="row" class="info-list-02 mtop40" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
    	<display:column title="<input id='checkAll' type='checkbox' name='checkbox' value=''>全选"  style="width:50px;">
     		<input name="epId" id="id_${row.id}" type="checkbox" value="${row.id}" auditStatus="${row.statusAudit}" status="${row.status}"></div>
    	</display:column>
		<display:column title="更新时间" sortable="true" >
			<c:choose><c:when test="${empty row.sys0UpdTime}">${row.sys0AddTime}</c:when><c:otherwise>${row.sys0UpdTime}</c:otherwise></c:choose>
		</display:column>
		<display:column title="供应商" sortable="true">${row.supplyMerchant.name}</display:column>
		<%-- <display:column title="商品分类" sortable="true">${row.productType.typeName}</display:column> --%>
		<display:column title="商品名称" property="name" sortable="true"/>
		<display:column title="市场价/元" sortable="true">${row.price/100}</display:column>
		<display:column title="采购价(折后价)/元" sortable="true">${row.purchasePrice/100}</display:column>
		<display:column title="销售价(对冲价)/元" sortable="true">${row.priceDiscount/100}</display:column>
  		<display:column title="状态">
	  		<c:choose>
	  			<c:when test="${row.status eq 0}">
	  				已上架
		      	</c:when>
		      	<c:when test="${row.status eq 1}">
					<c:if test="${row.statusAudit eq 2}">
						草稿
					</c:if>
					<c:if test="${row.statusAudit eq 3}">
						待审核
					</c:if>
					<c:if test="${row.statusAudit eq 4}">
						审核不通过
					</c:if>
					<c:if test="${row.statusAudit eq 5}">
						已下架
					</c:if>
		      	</c:when>
		      	<%-- <c:when test="${row.statusAudit eq 5}">
		      		<c:if test="${row.status eq 0}">
						已上架
					</c:if>
					<c:if test="${row.status eq 1}">
						已下架
					</c:if>
		      	</c:when>
		      	<c:when test="${row.statusAudit eq 2}">草稿</c:when>
		      	<c:when test="${row.statusAudit eq 3}">待审核</c:when>
		      	<c:when test="${row.statusAudit eq 4}">审核不通过</c:when> --%>
		     </c:choose>
  		</display:column>
		<%-- <display:column title="状态" property="statusStr" sortable="true" /> --%>
		<display:column title="销量" property="selNum" sortable="true"/>
		<display:column title="添加人" property="addMan" />
    	<display:column title="修改人" property="updateMan" />
		<display:column title="操作"  >
			<%-- <c:if test="${(row.statusAudit eq 5) and (row.status eq 0)}"><a class="blue checkAccount" href="javascript:void(0)" onclick="onOffProduct(${row.id}, 5);">下架</a></c:if> --%>
			<c:if test="${(row.status eq 1 )and ((row.statusAudit eq 2) or (row.statusAudit eq 4) or (row.statusAudit eq 5))}"><a class="blue checkAccount" href="javascript:void(0)" onclick="onOffProduct(${row.id}, 3);">上架申请</a></c:if>
			<a class="blue editShopInfo" href="../ebuyProduct/ebuyProductDetail.html?productId=${row.id}&pageType=detail">查看</a>
			<%-- 暂时注释掉，不然编辑时可能会有bug：当商品已付款，然后再编辑，之后再查看购买的商品的价格，可能不一样--%>
			<c:if test="${(row.status eq 1) and ((row.statusAudit eq 2) or (row.statusAudit eq 4) or (row.statusAudit eq 5))}"><a class="blue editShopInfo" href="../ebuyProduct/ebuyProductDetail.html?productId=${row.id}&pageType=edit">编辑</a></c:if> 
			<c:if test="${(row.status eq 1) and ((row.statusAudit eq 2) or (row.statusAudit eq 4) or (row.statusAudit eq 5))}"><a class="blue checkAccount" href="javascript:void(0)" onclick="deleteApp(${row.id});">删除</a></c:if>
		</display:column>
		<%--
		<display:column title="操作" media="html">
			<a class="blue editShopInfo" href="../order/viewOrderDetail.html?delieveOrderId=${row.id}">订单详情</a>
		</display:column>
		 --%>
	</display:table>
    
    <div class="overflow mtop20">
	    <input class="input-btn left"  type="button" value="批量上架申请" onclick="onOffProduct()">
	    <!-- <input class="input-btn left mar-left15" type="button" value="批量下架"> -->
	    <input class="input-btn left mar-left15" id="delAll" onclick="delAllData();" type="button" value="批量删除">
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js?v20150506"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/clearbox.js?v20150215"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript">
function deleteApp(id){
	steveConfirm("你确认要删除此商品吗?", function(){
		$.post("${pageContext.request.contextPath}/ebuyProduct/deleteProduct.html", {
			productId : id
		}, function(data) {
			if(data.errcode == null) {
				$("#searchForm").submit();
			} else if (data.message) {
				$.messager.alert('操作提示',data.message,data.status);
			}
		});
	});
}

function onOffProduct(id, statusAudit) {
	var msg = "";
	if(id){
		if(statusAudit == 3) {
			msg = '你确认要申请上架此商品吗?';
		} else {
			msg = '你确认要下架此商品吗?';
		}
	} else {
		id = "";
		// 批量上架
		var isPass = true;
		$("input[name='epId']:checkbox").each(function(){ 
	 	   if($(this).is(":checked")){
	 		  var auditStatus = $(this).attr("auditStatus");
	 		  var status = $(this).attr("status");
	 		  if(auditStatus=="3" || (auditStatus=="5" && status=="0")){
	 		    	isPass = false;
	 			    return;
	 		  }
	 		  id += $(this).val()+",";
	 		}
	    });
		if(!isPass){
			$.cxhd.alert("待审核和已上架的商品不能重复上架申请！");
			return;
		}
		statusAudit = "3";// 上架：待审核
		if(id==""){
			$.cxhd.alert("请选择要申请上架的商品！");
			return;
		} else {
			msg = "你确认要申请上架选中的商品吗?";
		}
	}
	
	steveConfirm(msg, function(){
		$.cxhd.loading();
		$.post("${pageContext.request.contextPath}/ebuyProduct/onOffProduct.html", {
			"productId" : id,
			"statusAudit" : statusAudit
		}, function(data) {
			$.cxhd.hide();
			if(data.errcode == null) {
				$("#searchForm").submit();
			} else if (data.message) {
				$.messager.alert('操作提示',data.message,data.status);
			}
		});
	});
}

function delAllData(){
	var value="";
	var isPass = true;
    $("input[name='epId']:checkbox").each(function(){ 
 	   if($(this).is(":checked")){
 		    var auditStatus = $(this).attr("auditStatus");
 		    var status = $(this).attr("status");
 		    if(auditStatus=="3" || (auditStatus=="5" && status=="0")){
 		    	isPass = false;
 			    return;
 		    }
 		   	value += $(this).val()+",";
 		}
    });
    if(isPass){
    	if(value !=""){
    		   $.post("${pageContext.request.contextPath}/ebuyProduct/deleteProduct.html",{epIds:value},function(data) {
    				if(data=="删除成功"){
    					$("#delAll").attr({value:"删除成功",onclick:""});
    				}else{
    					$.cxhd.alert("删除失败！");
    				}
    			   $(function () {
    				    setTimeout(function () {
    						var href = "${pageContext.request.contextPath}/ebuyProduct/list.html";
    						$(window.parent.document).find("#mainFrame").attr("src", href);
    				    }, 2000);
    				});
    			});
    	    }else{
    		   $.cxhd.alert("请选择要删除的商品!!!");
    	    }
    } else {
    	$.cxhd.alert("不能删除待审核或已上架的商品！");
    }
}

function epFilter(){
	$.widget("custom.catcomplete", $.ui.autocomplete, {
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
	
    $("#name").autocomplete({
        source:function (request, response) {
            $.post("${pageContext.request.contextPath}/ebuyProduct/epFilter.html", {"epName": $.trim($("#name").val())},
                function (result) {
            	console.log(result.dataValue);
                    response($.map(result.dataValue, function (item) {
                        return {label: item.epName, value: item.epName};
                    })); 
                }
            );
        },
        delay:0
    });
}

epFilter(); 
</script>
</html>
