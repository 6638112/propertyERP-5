<%@page import="com.cnfantasia.server.ms.pub.constant.PathConstants"%>
<%@page import="com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager"%>
<%@page import="com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>商品详情</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/clearbox.css" />
		<style>
			.input_text{height:30px !important;}
		</style>
	</head>
	<body>
		<div class="info posrelative">
	        <h2>商品详情</h2>
	        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	          <tr class="list-title">
	            <td colspan="2"><div align="left" class="f14 bold">商品属性</div></td>
	          </tr>
	          <tr>
	            <td width="20%"><div class="list-name">供应商：</div></td>
	            <td>${supplyMerchantName}</td>
	          </tr>
	          <tr>
	            <td><div class="list-name">运营主题：</div></td>
	            <td>${ebuyHomeType.name3}</td>
	          </tr>
	          <tr>
	            <td><div class="list-name"><span class="red">*</span> 货架分类：</div></td>
	            <td>${ebuyProductType.typeName}</td>
	          </tr>
	          <tr>
	            <td><div class="list-name"><span class="red">*</span> 商品名称：</div></td>
	            <td>${ebuyProduct.name}</td>
	          </tr>
	          <tr>
	            <td><div class="list-name">短名称：</div></td>
	            <td>${ebuyProduct.nameMini}</td>
	          </tr>
	          <tr>
	            <td><div class="list-name"><span class="red">*</span> 商品价格：</div></td>
	            <td>${ebuyProduct.priceDiscount/100} 元</td>
	          </tr>
	          <tr>
	            <td><div class="list-name"><span class="red">*</span> 采购价格：</div></td>
	            <td>${ebuyProduct.purchasePrice/100} 元</td>
	          </tr>
	          <tr>
	            <td><div class="list-name">市场价：</div></td>
	            <td>${ebuyProduct.price/100}元</td>
	          </tr>
	          <tr>
	            <td><div class="list-name">库存：</div></td>
	            <td>${ebuyProduct.leftCount}</td>
	          </tr>
				<tr>
					<td><div class="list-name">商品描述：</div></td>
					<td>${ebuyProduct.desc}</td>
				</tr>
				<tr>
	            <td><div class="list-name">浏览图片：</div></td>
	            <td class="item-upload-img">
	            	<c:forEach var="ebuyProductPic"  items="${ebuyProductPics}">
		                <div class="uploadPreview01 marb15 mright6">
		                	<a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.Product_Image_Dir)+PathConstants.Product_Image_Dir%>${ebuyProductPic.urlBig}" rel="clearbox[test2]">
	                    		<img width="82" height="82" src='<%=OmsSysParamManager.getImageServerUrl(PathConstants.Product_Image_Dir)+PathConstants.Product_Image_Dir%>${fn:substring(ebuyProductPic.urlBig, 0, fn:indexOf(ebuyProductPic.urlBig, "."))}/72.${fn:substringAfter(ebuyProductPic.urlBig, ".")}' border="0" />
	                    	</a>
		                </div>
	                </c:forEach>
	            </td>
	          </tr>
	          <tr>
	            <td><div class="list-name">商品详情图片：</div></td>
	            <td id="dragItems">
	            	<c:forEach var="ebuyProductIntroducePic"  items="${ebuyProductIntroducePics}">
		            	<div class="uploadPreview01 marb15 mright6 drag-item">
		            		<a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.Product_Image_Dir)+PathConstants.Product_Image_Dir%>${ebuyProductIntroducePic.urlBig}" rel="clearbox[test2]">
	                    		<img width="82" height="82" src='<%=OmsSysParamManager.getImageServerUrl(PathConstants.Product_Image_Dir)+PathConstants.Product_Image_Dir%>${fn:substring(ebuyProductIntroducePic.urlBig, 0, fn:indexOf(ebuyProductIntroducePic.urlBig, "."))}/72.${fn:substringAfter(ebuyProductIntroducePic.urlBig, ".")}' border="0" />
	                    	</a>
		                </div>
	            	</c:forEach>
	            </td>
	          </tr>
				<h2 class="mtop40 f14"><span class="red">*</span> 商品参数</h2>
				<table class="info-list-02 product_property" border="0" cellpadding="0" cellspacing="1">
					<tr class="title">
						<td width="20%">参数名称</td>
						<td>
							参数说明
						</td>
					</tr>
					<c:forEach items="${params}" var="item">
						<tr class="paramAdded dsn" style="display: table-row;">
							<td align="right">
								<div align="left">
										${item.tPropName}
								</div>
							</td>
							<td>${item.tPropValue}</td>
						</tr>
					</c:forEach>
				</table>
	        </table>
	        <input type="hidden" name="auditStatus" id="auditStatus"/>
	        <input class="input-btn left" type="button" value="返回" onclick="history.back();" style="margin-top:10px;">
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/clearbox.js"></script>
</html>