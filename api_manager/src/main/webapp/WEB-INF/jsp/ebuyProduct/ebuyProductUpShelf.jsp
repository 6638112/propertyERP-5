<%@page import="com.cnfantasia.server.api.payment.constant.EbuyDict"%>
<%@page import="com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager" %>
<%@page import="com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<% 
	response.setHeader("Pragma","No-cache");    
	response.setHeader("Cache-Control","no-cache");    
	response.setDateHeader("Expires", -10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>上架管理</h2>
    <div class="bs-example bgebeb">
      <form id="searchForm" action="${pageContext.request.contextPath}/ebuyProduct/auditSearch.html?state=up" method="post">
        <table class="info-list" border="0">
          <tr>
            <td><div class="list-name">供应商属性：</div></td>
            <td>
               <select class="select_normal" name="supplyMerchantType">
                  <option value="">全部</option>
                  <option value="1" <c:if test="${param.supplyMerchantType==1}">selected</c:if>>自营（全国）</option>
                  <option value="2" <c:if test="${param.supplyMerchantType==2}">selected</c:if>>附近商家</option>
                  <option value="3" <c:if test="${param.supplyMerchantType==3}">selected</c:if>>物业专供</option>
                  <option value="4" <c:if test="${param.supplyMerchantType==4}">selected</c:if>>自营（区域）</option>
                </select>
            </td>
            <c:if test="${sm ne 'invisible'}">
	            <td><div class="list-name">供应商名称：</div></td>
	            <td>
	                <select id="tSupplyMerchantFId" name="tSupplyMerchantFId" class="select_normal">
	                <c:if test="${fn:length(supplyMerchantList) > 1}"><option value="">全部</option></c:if>
	                <c:forEach var="supplyMerchant" items="${supplyMerchantList}" >
	                   	<c:if test="${supplyMerchant.name !=null && supplyMerchant.name != ''}">
							<option value="${supplyMerchant.id}" <c:if test="${supplyMerchant.id==param.tSupplyMerchantFId}">selected</c:if> >${supplyMerchant.name}</option>
						</c:if>
					</c:forEach>
	                </select>
	            </td>
            </c:if>
            <td><div class="list-name">商品分类：</div></td>
            <td>
               <select id="tEbuyProductTypeFId" name="tEbuyProductTypeFId" class="select_normal">
                   <option value="">全部</option>
				   <c:forEach var="productType" items="${productTypeList}" >
						<option value="${productType.id}" <c:if test="${productType.id==param.tEbuyProductTypeFId}">selected</c:if> >
							<c:if test="${productType.wlappType == 1}">${productType.typeName}(app)</c:if>
							<c:if test="${productType.wlappType == 2}">${productType.typeName}(文旅商城)</c:if>
							<c:if test="${productType.wlappType == 3}">${productType.typeName}(轻应用)</c:if>	
						</option>
				   </c:forEach>
               </select>
            </td>
          </tr>
          <tr>
            <td><div align="right">商品名称：</div></td>
            <td><input id="name" name="name" type="text" class="input_text w120 pp" value="${param.name}"></td>
            <td><div class="list-name">商品状态：</div></td>
            <td colspan="3">
               <select class="select_normal" name="status">
                   <option value="">全部</option>
                   <option value="1" <c:if test="${param.status ==1}">selected</c:if>>已下架</option>
                   <option value="2" <c:if test="${param.status ==2}">selected</c:if>>审核不通过</option>
                   <option value="3" <c:if test="${param.status ==3}">selected</c:if>>待审核</option>
               </select>
            </td>
          </tr>
          <tr>
            <td><div class="list-name">申请时间：</div></td>
            <td><input type="text" class="input_text icon_dt" id="date01" name="applyTime_START" title="请选择起始时间" value="${param.applyTime_START}"> 至 <input type="text" class="input_text icon_dt" id="date02" name="applyTime_END" title="请选择结束时间" value="${param.applyTime_END}"></td>
            <td><div class="list-name">审批时间：</div></td>
            <td colspan="3"><input type="text" class="input_text icon_dt" id="date01" name="auditTime_START" title="请选择起始时间" value="${param.auditTime_START}"> 至 <input type="text" class="input_text icon_dt" id="date02" name="auditTime_END" title="请选择结束时间" value="${param.auditTime_END}"></td>
          </tr>
          <tr>
            <td colspan="8" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
          </tr>
        </table>
      </form>
    </div>
	<display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
      <display:column title="<input name='checkbox' type='checkbox' id='selectAll' value=''> 全选" >
	      <input class="input_text page-num sort-num pp" type="checkbox" name="epId" value="${row.id }" shelfId="${row.ebuyProductShelf.id}" wlAppType="${row.productType.wlappType}" countEqOnApp="${row.countEqOnApp}"/>
      </display:column>
      <display:column title="申请时间" property="ebuyProductShelf.applyTime"/>
      <display:column title="审批时间" property="ebuyProductShelf.handTime"/>
      <display:column title="供应商属性">
      	<c:if test="${row.supplyMerchant.type==1}">
			自营（全国）
		</c:if>
		<c:if test="${row.supplyMerchant.type==2}">
			附近商家
		</c:if>
		<c:if test="${row.supplyMerchant.type==3}">
			物业专供
		</c:if>
		<c:if test="${row.supplyMerchant.type==4}">
			自营（区域）
		</c:if>
      </display:column>
      <display:column title="供应商">
      	<c:out value="${row.supplyMerchant.name}"/>
      </display:column>
	  <display:column title="商品分类">
		<c:out value="${row.productType.typeName}"/>		
	  </display:column>
      <display:column title="商品名称" property="name"/>
      <display:column title="采购价(元)">${row.purchasePrice/100}</display:column>
      <display:column title="销售价(元)">${row.priceDiscount/100}</display:column>
      <display:column title="库存" property="leftCount"/>
      <display:column title="状态">
	      <c:choose>
	      	<c:when test="${row.statusAudit==5}">
				<c:if test="${row.status==1 or row.ebuyProductShelf.upShelfState==1}">
					已下架
				</c:if>
	      	</c:when>
	      	<c:when test="${row.statusAudit==3}">待审核</c:when>
	      	<c:when test="${row.statusAudit==4}">审核不通过</c:when>
	      </c:choose>
      </display:column>
      <display:column title="操作人">
			<c:choose>
				<c:when test="${not empty row.updateMan}">${row.updateMan}</c:when>
				<c:otherwise>${row.addMan}</c:otherwise>
			</c:choose>
	  </display:column>
      <display:column title="操作" style="text-align:center">
	      <c:choose>
	      	<c:when test="${row.statusAudit==5}">
		      	<%-- <c:if test="${row.status==0}">
					<a class="blue checkAccount" onclick="downshelf(${row.id});" href="#">下架</a> &nbsp;&nbsp;
				</c:if> --%>
				<c:if test="${row.status==1 or row.ebuyProductShelf.upShelfState==1}">
					<a class="blue checkAccount up_shelf_dialog" href="javascript:setShelfType('one',${row.id}, ${row.ebuyProductShelf.id}, ${row.productType.wlappType}, ${row.countEqOnApp});">上架</a> &nbsp;&nbsp;
				</c:if>
			</c:when>
		  </c:choose>
		<a class="blue checkAccount" href="${pageContext.request.contextPath}/ebuyProduct/auditproduct.html?id=${row.id}&productType=${row.productType.typeName}&shelfId=${row.ebuyProductShelf.id}&wlAppType=${row.productType.wlappType}&pageType=detail&from=up">查看</a>
		<c:if test="${row.statusAudit eq 3}">
			<a class="blue checkAccount" href="javascript:goAuditPage(${row.id}, '${row.productType.typeName}', '${row.ebuyProductShelf.id}', '${row.productType.wlappType}');">审核</a>
		</c:if>
      </display:column>
      <display:footer>
		<tr>
	      <td colspan="12">
	      	<div class="overflow mtop20">
    			<input id="allupshelf" class="input-btn left up_shelf_dialog" type="button" value="批量上架" onclick="setShelfType('ones');"/>
<!--      		<input class="input-btn left mar-left15" type="button" value="批量导出"/>-->
    		</div>
    	  </td>
    	</tr>	
	  </display:footer>
    </display:table>
</div>
<div class="layer-classify classify-editable dsn">
    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
      <tr class="title">
        <td colspan="2">将该商品上架到</td>
      </tr>
      <tr>
        <td id="addressApp">
        	<label><input type="checkbox" name="shelfAddress" value="<%=EbuyDict.WlAppType.Jfq%>" checked="checked"> app</label>
        </td>
        <td id="addressLightApp">
        	<label><input type="checkbox" name="shelfAddress" value="<%=EbuyDict.WlAppType.Jfq_Light_App%>" checked="checked"> 轻应用</label>
        </td>
      </tr>
    </table>
    <div class="mtop20 t_center">
    	<input class="info-btn small-btn w80" type="button" value="确定" onclick="checkAllData();" >
    	<input class="input-btn w80 mar-left15 classify-cancel-btn" type="button" value="取消" />
    </div>
</div>
<input type="hidden" id="shlefType"/>
<input type="hidden" id="productId"/>
<input type="hidden" id="shelfId"/>
<input type="hidden" id="wlAppType"/>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layer.min.js"></script>

<script type="text/javascript">
	$(function (){
		$("#selectAll").click( 
			function(){ 
				if(this.checked){ 
					$("input[name='epId']").each(function(){this.checked=true;}); 
				}else{ 
					$("input[name='epId']").each(function(){this.checked=false;}); 
				} 
			} 
		);
	
		$('.up_shelf_dialog').click(function(){
			if($("#shlefType").val()=="ones"){
				var isAppExisted = false;
				var epIds = "";
				$("input[name='epId']:checkbox").each(function(){ 
	        	    if($(this).is(":checked")){
	        		   epIds += $(this).val()+",";
	        		}
	        	    if($(this).prop("countEqOnApp")>0 && !isAppExisted){
	        	    	isAppExisted = true;
	        	    }
	            });
				if(epIds==""){
					alert("请选择要上架的商品!!!"); 
					return;
				}
				if(isAppExisted){
					$("#addressLightApp :checkbox").prop("checked", false);
					$("#addressLightApp").hide();
					$("#addressApp").prop("colspan", "2");
				} else {
					$("#addressApp").prop("colspan", "1");
					$("#addressLightApp :checkbox").prop("checked", true);
					$("#addressLightApp").show();
				}
			}
			$.layer({
				type: 1,
				shade: [0.4,'#000000'],
				area: ['auto', 'auto'],
				title: false,
				border : [5, 0.3, '#000'],
				page: {dom : '.classify-editable'}
			});
		});
		
		$('.classify-cancel-btn').click(function(){
			layer.closeAll();
		});
	});
	
	function checkAllData(){
		var shelfType = $("#shlefType").val();
		var params = new Array();
		if(shelfType=="ones"){
			$("input[name='epId']:checkbox").each(function(){ 
        	    if($(this).is(":checked")){
        		   params.push({"epId":$(this).val(), "shelfId":$(this).attr("shelfId"), "wlAppType":$(this).attr("wlAppType")});
        		}
            });
		} else {
			params.push({"epId":$("#productId").val(), "shelfId":$("#shelfId").val(), "wlAppType":$("#wlAppType").val()});
		}
		
		var shelfAddress = "";
		$("input[name='shelfAddress']:checkbox").each(function(){ 
     	    if($(this).is(":checked")){
     			shelfAddress += $(this).val()+",";
     		}
         });
		if(shelfType=="ones" && params.length==0){
			alert("请选择要上架的商品!!!"); 
		} else {
			if(shelfAddress.length==0){
				alert("请选择上架的平台！");
				return;
			}
			$.post("${pageContext.request.contextPath}/ebuyProduct/upShelf.html",{"params":JSON.stringify(params), "shelfAddress":shelfAddress},function(data) {
				alert(data);	
				location.reload();
			});
		}
	}
	
	/* function downshelf(epId){
		if(confirm("你确定要下架该商品吗？")){
			location.reload();
		}
	} */
	
	// 设置上架类型
	function setShelfType(type, id, shelfId, wlAppType, countEqOnApp){
		if(countEqOnApp>0){
			$("#addressLightApp :checkbox").prop("checked", false);
			$("#addressLightApp").hide();
			$("#addressApp").prop("colspan", "2");
		} else {
			$("#addressApp").prop("colspan", "1");
			$("#addressLightApp :checkbox").prop("checked", true);
			$("#addressLightApp").show();
		}
		$("#shlefType").val(type);
		$("#shelfId").val(shelfId);
		$("#wlAppType").val(wlAppType);
		if(id){
			$("#productId").val(id);
		}
	}
	
	function goAuditPage(id, typeName, shelfId, wlappType){
		// 审核后保留之前查询条件跳转
		var searchForm = JSON.stringify($("#searchForm").serializeArray());
		var url = "${pageContext.request.contextPath}/ebuyProduct/auditproduct.html?id="+id+"&productType="+typeName+"&shelfId="+shelfId+"&wlAppType="+wlappType+"&pageType=audit&searchForm="+searchForm;
		location.href = url;
	}
</script>
</html>
