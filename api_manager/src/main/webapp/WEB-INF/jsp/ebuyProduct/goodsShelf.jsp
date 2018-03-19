<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page import="com.cnfantasia.server.ms.ebuyProduct.constant.EbuyProductConstant"%>
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

	request.setAttribute("default_order", EbuyProductConstant.OrderType.DEFAULT_ORDER);
	request.setAttribute("manay_to_one", EbuyProductConstant.OrderType.MANAY_TO_ONE);
	request.setAttribute("one_to_manay", EbuyProductConstant.OrderType.ONE_TO_MANAY);
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
</head>
<body>
<div class="info">
	<h2>货架管理</h2>
	<div class="bs-example bgebeb">
		<form id="searchForm" name="form_main" action="${pageContext.request.contextPath}/ebuyProduct/search.html?state=shelf" method="post">
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
					<td><div align="right">商品名称：</div></td>
					<td><input id="name" name="name" type="text" class="input_text w120 pp" value="${param.name}"></td>
					<td><div class="list-name">货架分类：</div></td>
					<td>
						<select id="tEbuyProductTypeFId" name="tEbuyProductTypeFId" class="select_normal">
							<option value="">全部</option>
							<c:forEach var="productType" items="${productTypeList}" >
								<option value="${productType.id}" <c:if test="${productType.id eq param.tEbuyProductTypeFId}">selected</c:if> >
									<c:if test="${productType.wlappType eq 1}">${productType.typeName}(app)</c:if>
									<c:if test="${productType.wlappType eq 2}">${productType.typeName}(文旅商城)</c:if>
									<c:if test="${productType.wlappType eq 3}">${productType.typeName}(轻应用)</c:if>
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<c:if test="${sm ne 'invisible'}">
						<td><div class="list-name">供应商名称：</div></td>
						<td>
							<select id="tSupplyMerchantFId" name="tSupplyMerchantFId" class="select_normal">
								<c:if test="${fn:length(supplyMerchantList) > 1}"><option value="">全部</option></c:if>
								<c:forEach var="supplyMerchant" items="${supplyMerchantList}" >
									<c:if test="${supplyMerchant.name !=null && supplyMerchant.name != ''}">
										<option value="${supplyMerchant.id}" <c:if test="${supplyMerchant.id eq param.tSupplyMerchantFId}">selected</c:if> >${supplyMerchant.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</c:if>
					<td><div class="list-name">上架时间：</div></td>
					<td><input type="text" class="input_text icon_dt" id="date01" name="upShelfTime_START" title="请选择起始时间" value="${param.upShelfTime_START}"> 至 <input type="text" class="input_text icon_dt" id="date02" name="upShelfTime_END" title="请选择结束时间" value="${param.upShelfTime_END}"></td>

					<td><div class="list-name">排序方式：</div></td>
					<td>
						<select class="select_normal" name="orderType" onchange="document.getElementById('searchForm').submit();">
							<option value="${default_order}" <c:if test="${orderType eq default_order}">selected</c:if>>按上架时间</option>
							<option value="${manay_to_one}" <c:if test="${orderType eq manay_to_one}">selected</c:if>>按库存多到少</option>
							<option value="${one_to_manay}" <c:if test="${orderType eq one_to_manay}">selected</c:if>>按库存少到多</option>
						</select>
					</td>
				</tr>
				<tr>

					<td><div class="list-name">上架渠道：</div></td>
					<td>
						<select id="appType" name="appType" class="select_normal">
							<option value="">全部</option>
							<option value="1" <c:if test="${param.appType eq 1}">selected</c:if>>app</option>
							<option value="3" <c:if test="${param.appType eq 3}">selected</c:if>>轻应用</option>
							<option value="2" <c:if test="${param.appType eq 2}">selected</c:if>>文旅</option>
						</select>
					</td>
					<td><div class="list-name">运营主题：</div></td>
					<td>
						<select class="select_normal" name="ebuyHomeType">
							<option value="">选择主题</option>
							<c:forEach var="ebuyHomeType"  items="${ebuyHomeTypes}">
								<option value="${ebuyHomeType.id}" ${param.ebuyHomeType eq ebuyHomeType.id?'selected':''}>${ebuyHomeType.name3}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="8" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
				</tr>
			</table>
		</form>
	</div>
	<display:table name="resList" id="row" class="info-list-02 mtop40" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<display:column title="<label><input id='selectAll' type='checkbox' name='checkbox' value=''/>全选</labe>" style="width:50px;">
			<c:if test="${row.status==0}">
				<input name="epId" type="checkbox" value="${row.id}" shelfId="${row.ebuyProductShelf.id}">
			</c:if>
			<c:if test="${row.status==1}">
				<input name="offepId" type="checkbox" value="${row.id}">
			</c:if>
		</display:column>
		<display:column title="供应商属性">
			<c:choose>
				<c:when test="${row.supplyMerchant.type eq 1}">自营（全国）</c:when>
				<c:when test="${row.supplyMerchant.type eq 2}">附近商家</c:when>
				<c:when test="${row.supplyMerchant.type eq 3}">物业专供</c:when>
				<c:when test="${row.supplyMerchant.type eq 4}">自营（区域）</c:when>
				<c:otherwise>无供应商属性</c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="供应商名称">${row.supplyMerchant.name}</display:column>
		<display:column title="货架分类">${row.productType.typeName}</display:column>

		<display:column title="上架渠道">
			<c:choose>
				<c:when test="${row.productType.wlappType eq 1}">App</c:when>
				<c:when test="${row.productType.wlappType eq 2}">文旅商城</c:when>
				<c:when test="${row.productType.wlappType eq 3}">轻应用</c:when>
			</c:choose>
		</display:column>
		<display:column title="运营主题">${row.ebuyHomeType.name3}</display:column>

		<display:column title="商品ID" property="id"/>
		<c:if test="${isAdmin == 1}">
			<display:column title="商品名称 <input class='input-btn' type='button' onclick='savename();' id='savenames' value='保存'>">
				<input type="text" value="${row.name}" placeholder="${row.name}" name="productname" id="${row.id}" style="color:#FF0000;" class="input_text pp w250">
			</display:column>
		</c:if>
		<c:if test="${isAdmin != 1}">
			<display:column title="商品名称">
				${row.name}
			</display:column>
		</c:if>
		<display:column title="市场价/元">${row.ebuyProductShelf.price/100}</display:column>
		<display:column title="采购价(折后价)/元">${row.purchasePrice/100}</display:column>
		<display:column title="销售价(对冲价)/元 <input class='input-btn' type='button' onclick='saveprice();' id='saveprices' value='保存'>">
			<input type="text" value="${row.ebuyProductShelf.priceDiscount/100}" maxlength="16" placeholder="${row.ebuyProductShelf.priceDiscount/100}" name="prices" id="${row.id}" shelfId="${row.ebuyProductShelf.id}" style="color:#FF0000;" class="input_text pp w80">
		</display:column>
		<display:column title="销量" property="selNum"/>
		<display:column title="库存" property="leftCount"/>
		<display:column title="上架时间" property="upShelfTime"/>
		<display:column title="状态">
			<c:choose>
				<c:when test="${row.statusAudit eq 5}">
					<c:if test="${row.ebuyProductShelf.upShelfState eq 0}">
						已上架
					</c:if>
					<c:if test="${row.ebuyProductShelf.upShelfState eq 1}">
						已下架
					</c:if>
				</c:when>
			</c:choose>
		</display:column>
		<c:if test="${isAdmin == 1}">
			<display:column title="操作人">
				<c:choose>
					<c:when test="${not empty row.updateMan}">${row.updateMan}</c:when>
					<c:otherwise>${row.addMan}</c:otherwise>
				</c:choose>
			</display:column>
		</c:if>
		<display:column title="操作"  >
			<a class="blue checkAccount" href="#" onclick="downshelf(${row.id}, ${row.ebuyProductShelf.id});">下架</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${isAdmin == 1}">
				<a class="blue checkAccount" href="${pageContext.request.contextPath}/ebuyProduct/offdetailsproduct.html?epId=${row.id}&ebuyProductShelfId=${row.ebuyProductShelf.id}&productType=${row.productType.typeName}&appType=${row.productType.wlappType}">编辑</a>
				<c:if test="${row.productType.wlappType eq 1 && row.statusAudit eq 5 && row.ebuyProductShelf.upShelfState eq 0}">
					<br/>
					<a class="blue checkAccount" href="${pageContext.request.contextPath}/ebuyProductFightGroups/addProductGroups.html?epId=${row.id}">发起拼购</a>
				</c:if>
				<c:if test="${row.supplyMerchant.type eq 2 && row.productType.wlappType eq 1}">
					<br/>
					<a class="blue checkAccount" href="${pageContext.request.contextPath}/flashDealActivityCfg/jumpToFlashDealActivityCfg.html?epId=${row.id}">发起幸运购</a>
				</c:if>
				<c:if test="${(row.productType.wlappType eq 1) and (row.ebuyHomeType.type ne 3)}">
					<br/>
					<a class="blue checkAccount" href="${pageContext.request.contextPath}/limitBuyActivity/addPage.html?productId=${row.id}">发起限时抢购</a>
				</c:if>
			</c:if>
			<c:if test="${row.productType.wlappType eq 3 && row.supplyMerchant.id eq experienceStoreId}">
				<a class="blue checkAccount" href="javascript:exportQrcode('${row.id}','${row.ebuyProductShelf.id}', '${row.name}')">二维码</a>
			</c:if>
			<%-- <a class="blue checkAccount" href="#" onclick="deleteApp(${row.id});">删除</a> --%>
		</display:column>
		<display:footer>
			<tr>
				<td colspan="17">
					<div class="overflow">
						<input class="input-btn left" onclick="downshelf();" id="alloffshelf" type="button" value="批量下架">
					</div>
				</td>
			</tr>
		</display:footer>
	</display:table>
</div>
<div id="qrcodess" class="content mt20 dsn"></div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js?v20150506"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/clearbox.js?v20150215"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tool.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/lrsjng.jquery-qrcode/0.14.0/jquery-qrcode.min.js"></script>

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

	function downshelf(epId, shelfId){
		var params = [];
		if(epId){
			params.push({"epId":epId,"shelfId":shelfId});
		} else {
			$("input[name='epId']:checkbox").each(function(){
				if($(this).is(":checked")){
					params.push({"epId":$(this).val(), "shelfId":$(this).attr("shelfId")});
				}
			});
		}
		if(params.length==0){
			$.cxhd.alert("请选择要下架的商品！");
		} else {
			steveConfirm("你确定要下架该商品吗？", function(){
				$.cxhd.loading();
				$.post("${pageContext.request.contextPath}/ebuyProduct/downEbuyProduct.html",{"params":JSON.stringify(params)},function(data) {
					if (data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>' ) {
						steveAlert(data.message, 1500);
						setTimeout(function () {
							location.reload();
						}, 1500);
					} else {
						$.cxhd.alert(data.message);
					}
				});
			}, true);
		}
	}

	function saveprice(){
		steveConfirm("是否修改商品价格？", function(){
			var params = [];
			$("input[name='prices']").each(function(){
				var price = $.trim($(this).val());
				if(price !="" && price!=$(this).attr("placeholder")){
					if($.isNumeric(price)){
						params.push({"epId":$(this).attr("id"), "price":price, "shelfId":$(this).attr("shelfId")});
					}
				}
			});
			if(params.length>0){
				$.post("${pageContext.request.contextPath}/ebuyProduct/editprices.html",{"params":JSON.stringify(params)},function(data) {
					if (data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>' ) {
						steveAlert("操作成功！", 1800);
						$("#saveprices").attr({onclick:""});
						$(function () {
							setTimeout(function () {
								location.reload();
							}, 2000);
						});
					}else{
						$.cxhd.alert("保存失败！！");
					}
				});
			}else{
				$.cxhd.alert("请输入有效的商品价格!!!");
			}
		}, true);
	}

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
	});

	function savename(){
		var names = "";
		var epIds  = "";
		steveConfirm("是否修改商品名称？", function(){
			$("input[name='productname']").each(function(){
				var value = $(this).val();
				if(value !="" && value!=$(this).attr("placeholder")){
					names += $(this).val()+",";
					epIds += $(this).attr("id")+",";
				}
			});
			if(names!=""){
				$.post("${pageContext.request.contextPath}/ebuyProduct/savenames.html",{names:names,epIds:epIds},function(data) {
					if(data=="保存成功"){
						$.cxhd.hide();
						$("#savenames").attr({value:"保存成功",onclick:""});
					}else{
						$.cxhd.alert("保存失败！！");
					}
					$(function () {
						setTimeout(function () {
							location.reload();
						}, 2000);
					});
				});
			}else{
				$.cxhd.alert("请输入要修改的的商品名称!!!");
			}
		}, true);
	}

    function exportCanvasAsPNG(canvas, fileName) {
        var MIME_TYPE = "image/png";
        var dlLink = document.createElement('a');
        dlLink.download = fileName;
        dlLink.href = canvas.toDataURL("image/png");
        dlLink.dataset.downloadurl = [MIME_TYPE, dlLink.href].join(':');
        document.body.appendChild(dlLink);
        dlLink.click();
        document.body.removeChild(dlLink);
    }
    function exportQrcode(productId, shelfId, productName) {
        var qrtxt = '${laUrl}' + "product/productDetail.do?ptId=" + shelfId;
        var srsize = 240;
        var exportName = productId + "_" + productName + ".png";
        $("#qrcodess").html("");
        $('#qrcodess').qrcode({ width: srsize, height: srsize, text: qrtxt });
        exportCanvasAsPNG($("#qrcodess").find("canvas")[0], exportName);
    }
</script>
</html>
