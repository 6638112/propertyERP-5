<%@page import="com.cnfantasia.server.ms.pub.constant.PathConstants"%>
<%@page import="com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager"%>
<%@page import="com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品货架管理-商品详情</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/clearbox.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tool.js"></script>
</head>
<body>
<div class="info">
    <form class="inputform" id="editForm" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/ebuyProduct/updateProduct.html">
    	<input type="hidden" name="epId" value="${product.id}" />
    	<input type="hidden" name="ebuyProductShelfId" value="${ebuyProductShelf.id}" />
        <h2>商品详情</h2>
        <table class="info-list-02 product_detail" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">商品属性</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">运营主题：</div></td>
            <td>
                <select class="select_normal" name="theme">
                    <option value="">选择主题</option>
                    <c:forEach var="ebuyHomeType"  items="${ebuyHomeTypes}">
                    	<option value="${ebuyHomeType.id}" ${theme eq ebuyHomeType.id?'selected':''}>${ebuyHomeType.name3}</option>
                    </c:forEach>
                </select>
            </td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 货架分类：</div></td>
            <td>
            	<select name="productTypeId" >
	            	<c:forEach items="${productTypeList}" var="pr" >
	            		<option <c:if test="${pr.typeName eq productType }">selected="selected"</c:if> value="${pr.id }" >${pr.typeName }</option>
	            	</c:forEach>
            	</select>
            </td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 商品名称：</div></td>
            <td>
            	<input type="text" class="input_text pp w120" id="t-002" name="name" value="${product.name}" maxlength="100" datatype="*1-100" nullmsg="请完善商品信息" errormsg="商品名称为1-100个字符"/></td>
            </td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 购买单位：</div></td>
            <td>${product.priceUnit}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 库存：</div></td>
            <td>
            <input type="text" class="input_text pp w120" id="t-002" name="leftCount" value="${product.leftCount}" placeholder="如：999" maxlength="16" ignore="ignore" datatype="fei0zhengzhengshu" nullmsg="请完善商品信息" errormsg="请填写大于0的整数" /></td>
          </tr>
			<tr>
				<td><div class="list-name">商品描述：</div></td>
				<td><input type="text" class="input_text pp w500" name="productDesc" maxlength="100" placeholder="商品描述" value="${product.desc}" ignore="ignore" datatype="*"/></td>
			</tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 商品价格：</div></td>
            <td>
            <input type="text" class="input_text pp w120" name="shelfPrice" value="${ebuyProductShelf.priceDiscount/100}" maxlength="16" placeholder="商品价格" datatype="numberFixTwo" nullmsg="请完善商品信息" errormsg="请填写数字，可带两位小数" /></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 市场价：</div></td>
            <td>
            <input type="text" class="input_text pp w120" name="price" value="${ebuyProductShelf.price/100}" maxlength="16" placeholder="市场价格" datatype="numberFixTwo" nullmsg="请完善商品信息" errormsg="请填写数字，可带两位小数" /></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 采购价：</div></td>
            <td>
            <input type="text" class="input_text pp w120" name="purchasePrice" value="${product.purchasePrice/100}" maxlength="16" placeholder="采购价格" datatype="numberFixTwo" nullmsg="请完善商品信息" errormsg="请填写数字，可带两位小数" /></td>
          </tr>
          <tr>
            <td><div class="list-name">商品图片：</div></td>
            <td class="item-upload-img">
                <ul class="menu-img">

                <div class="uploadPreview01 imgDesc-new-add drag-item marb15 img-hide">
                    <div class="img-delete-btn">删除</div>
                    <div class="img-desc-box dsn">
                    	<span class="icon-edit">
                    		<img width="14" height="14" src="${pageContext.request.contextPath}/images/icon-edit.png" />
                    	</span>
                    </div>
                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="prdt_image" class="uploadImage02" />
                    <input type="hidden" name="prdt_imgUrl" value=""/>
                    <img class="imgPreview" width="82" height="82" src="${pageContext.request.contextPath}/images/addimg01.jpg" />
                </div>
                <c:forEach var="productPic"  items="${picList}">
	                <div class="uploadPreview01 marb15 mright6 drag-item">
	                	<div class="img-delete-btn">删除</div>
	                    <div class="img-desc-box dsn">
		                    <span class="icon-edit">
		                    	<img width="14" height="14" src="${pageContext.request.contextPath}/images/icon-edit.png" />
		                    </span>
	                    </div>
	                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="prdt_image" class="uploadImage02" />
	                    <input type="hidden" name="prdt_imgUrl" value="${productPic.urlBig}"/>
	                    <img class="imgPreview" src='<%=OmsSysParamManager.getImageServerUrl(PathConstants.Product_Image_Dir)+PathConstants.Product_Image_Dir%>${fn:substring(productPic.urlBig, 0, fn:indexOf(productPic.urlBig, "."))}/72.${fn:substringAfter(productPic.urlBig, ".")}' border="0" />
	                </div>
                </c:forEach>
                 <div class="uploadPreview01 imgDesc-add-btn marb15"></div><br />
                <span class="f12 marb15">注：图片尺寸640x540像素，建议图片2M以内，不限张数</span>

                <%--   <c:forEach var="EbuyProductPic"  items="${picList}">
                    	<li>
	                    	<a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.Product_Image_Dir)+PathConstants.Product_Image_Dir%>${EbuyProductPic.urlBig}" rel="clearbox[test2]">
		                    	<img src='<%=OmsSysParamManager.getImageServerUrl(PathConstants.Product_Image_Dir)+PathConstants.Product_Image_Dir%>${fn:substring(EbuyProductPic.urlBig, 0, fn:indexOf(EbuyProductPic.urlBig, "."))}/72.${fn:substringAfter(EbuyProductPic.urlBig, ".")}' border="0" />
		                    </a>
	                 	</li>
                    </c:forEach>
                     --%>

                </ul>
            </td>
          </tr>
          <tr>
            <td><div class="list-name">详情图片：</div></td>
            <td id="dragItems">
                <div class="uploadPreview01 imgDesc-new-add drag-item marb15 img-hide">
                    <div class="img-delete-btn">删除</div>
                    <div class="img-desc-text">添加描述</div>
                    <div class="img-desc-box dsn">
                    	<span class="desc-text-info">文字描述</span>
                    	<span class="icon-edit">
                    		<img width="14" height="14" src="${pageContext.request.contextPath}/images/icon-edit.png" />
                    	</span>
                    </div>
                    <input class="desc-text-input dsn" type="text" name="descText"/>
                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage02" />
                    <input type="hidden" name="imgUrl" value=""/>
                    <img class="imgPreview" width="82" height="82" src="${pageContext.request.contextPath}/images/addimg01.jpg" />
                </div>
                <c:forEach var="introducePic"  items="${introducePics}">
	                <div class="uploadPreview01 marb15 mright6 drag-item">
	                	<div class="img-delete-btn">删除</div>
	                    <div class="img-desc-text">添加描述</div>
	                    <div class="img-desc-box dsn">
		                    <span class="desc-text-info">文字描述</span>
		                    <span class="icon-edit">
		                    	<img width="14" height="14" src="${pageContext.request.contextPath}/images/icon-edit.png" />
		                    </span>
	                    </div>
	                    <input class="desc-text-input dsn" type="text" name="descText" value="${introducePic.textDesc}" />
	                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage02" />
	                    <input type="hidden" name="imgUrl" value="${introducePic.urlBig}"/>
	                    <img class="imgPreview" src='<%=OmsSysParamManager.getImageServerUrl(PathConstants.Product_Image_Dir)+PathConstants.Product_Image_Dir%>${fn:substring(introducePic.urlBig, 0, fn:indexOf(introducePic.urlBig, "."))}/72.${fn:substringAfter(introducePic.urlBig, ".")}' border="0" />
	                </div>
                </c:forEach>

                <div class="uploadPreview01 imgDesc-add-btn marb15"></div><br />
                <span class="f12 marb15">注：图片尺寸640x540像素，建议图片2M以内，不限张数</span>
            </td>
          </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span> 是否预售商品：</div></td>
                <td>
                    <input type="radio" name="isPreSell" value="1" <c:if test="${product.isPreSell == 1}">checked</c:if> /> 是
                    <input type="radio" name="isPreSell" value="0" <c:if test="${product.isPreSell == 0}">checked</c:if> /> 否
                </td>
            </tr>
        </table>
        <!--<h2 class="mtop40 f14"><span class="red">*</span> 商品规格</span></h2>
        <table class="info-list-02 item-standard-list01" border="0" cellpadding="0" cellspacing="1">
          <tbody>
            <tr class="title">
              <td width="20%">颜色</td>
              <td>尺寸</td>
              <td>售价</td>
            </tr>
            <tr class="color-index-1 size-index-1">
              <td class="color-name" rowspan="2">红色</td>
              <td>36码</td>
              <td>168.00 元</td>
            </tr>
            <tr class="size-index-2 color-index-1">
              <td>38码</td>
              <td>168.00 元</td>
            </tr>
            <tr class="size-index-1 color-index-2">
              <td class="color-name" rowspan="2">蓝色</td>
              <td>36码</td>
              <td>168.00 元</td>
            </tr>
            <tr class="size-index-2 color-index-2">
              <td>38码</td>
              <td>168.00 元</td>
            </tr>
          </tbody>
        </table>-->
        <h2 class="mtop40 f14"><span class="red">*</span> 商品参数</h2>
        <table class="info-list-02 product_property" border="0" cellpadding="0" cellspacing="1">
          <tr class="title">
            <td width="20%">参数名称</td>
            <td>
            	参数说明<input id="createParam" class="input-btn mtop3" type="button" value="添加商品参数" />
            	<!-- <input class="input-btn" type="button" onclick="addProperty()" value="添加" style="font-weight:normal;width:60px;text-align: center;margin-left: 1em;"/> -->
            </td>
          </tr>
          <c:forEach var="EbuyProductParameters"  items="${list}">
	          <tr class="paramAdded">
	            <td align="right"><div align="right"><input class="input_text pp w100 t_right" name="propName" value="${EbuyProductParameters.tPropName}" maxlength="45" type="text" width="auto" />：</div></td>
	            <td><input class="input_text pp w500" name="propValue" value="${EbuyProductParameters.tPropValue}" type="text" width="auto" maxlength="200"/> <a class="blue mar-left15 param-delete" href="#">删除</a></td>
	          </tr>
          </c:forEach>
        </table>
        <!--<h2 class="mtop40 f14">物流及服务</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="20%"><span class="red">*</span> 配送方式：</td>
            <td colspan="3">快递</td>
          </tr>
          <tr>
            <td rowspan="1"><span class="red">*</span> 配送费用：</td>
            <td>按订单数</td>
            <td>1 件</td>
            <td>8.00 元</td>
          </tr>
          <tr>
            <td><span class="red">*</span> 配送说明：</td>
            <td colspan="3">当日下单次日送达</td>
          </tr>
        </table>-->
        <input class="info-btn" type="submit" value="提 交" />
        <input class="input-btn w200 mar-left15" type="button" value="返回" onclick="history.back();" style="height:40px;">
    </form>
</div>
<table style="display:none;">
	<tr class="paramAdded dsn">
	  <td align="right"><div align="right"><input class="input_text pp w100 t_right" name="propName" type="text" width="auto" maxlength="45"/>：</div></td>
	  <td><input class="input_text pp w500" name="propValue" type="text" width="auto"  maxlength="200"/> <a class="blue mar-left15 param-delete" href="#">删除</a></td>
	</tr>
</table>
<div class="img-desc-text-box dsn">
    <h3>添加图片描述</h3>
    <textarea class="textareas txt02 w470" name="" cols="" rows="5"></textarea>
    <div class="mar-left15">还可输入<span class="leftNum">100</span>字</div>
    <div>
        <input id="checkDescBtn" class="info-btn mar-left15" type="button" value="确定" />
        <input id="backDescBtn" class="info-btn mar-left15 info-btn01 mtop0" type="button" value="取消" />
    </div>
</div>
<div class="shape-box dsn"></div>
</table>
</body>
<script type="text/javascript" src="../js/clearbox.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
	
	//删除浏览图片
	$('.item-upload-img .img-delete-btn').click(function(){
		var imgLength = $(this).parents('td').find('.uploadPreview01:visible').length;
		var thisImgText = $(this).parents('td').prev('td').find('.list-name').text();
		if(imgLength === 2){
			alert('至少要上传一张' + thisImgText);
			return false;
		}else{
			$(this).parent('.uploadPreview01').remove();
			window.parent.TuneHeight();// 重置高度
		}
	});
	
	//删除详情图片
	$('#dragItems .img-delete-btn').click(function(){
		$(this).parent('.uploadPreview01').remove();
		window.parent.TuneHeight();// 重置高度
	});
	
	//增加删除商品详情图片、描述
	var descNum = 0;
	$('.imgDesc-add-btn').click(function(){
		var newImgId = 'newImgId' + descNum;
		var $imgDescNewAdd =$(this).siblings('.imgDesc-new-add.img-hide');
		$imgDescNewAdd.clone(true).removeClass('img-hide').insertBefore($(this)).find('input').attr('id', newImgId);
		descNum += 1;
	});
	
	//添加描述按钮
	var $shapeBox = $('.shape-box');
	var $imgDescText = $('.img-desc-text');
	var $imgDescTextBox = $('.img-desc-text-box');
	$imgDescText.click(function(){
		var noImgUrl = $(this).siblings('.imgPreview').attr('src');
		if(noImgUrl.indexOf('addimg01.jpg') > -1){
			alert('请先上传图片');	
		}else{
			var thisVal = $(this).siblings('input.desc-text-input').val();
			$(this).siblings('input.desc-text-input').addClass('current-input');
			$imgDescTextBox.find('.textareas').val(thisVal);
			$imgDescTextBox.fadeIn();
			$shapeBox.fadeIn();
		}
	});
	//编辑描述按钮
	var $imgDescBox = $('.img-desc-box');
	$imgDescBox.click(function(){
		var thisVal = $(this).siblings('input.desc-text-input').val();
		$(this).siblings('input.desc-text-input').addClass('current-input');
		$imgDescTextBox.find('.textareas').val(thisVal);
		$imgDescTextBox.fadeIn();
		$shapeBox.fadeIn();
	});
	
	//描述确定按钮
	$('#checkDescBtn').click(function(){
		var curText = $('.textareas').val();
		var omitText = $.trim(curText).substring(0,4);
		var $thisImgDescBox = $('.current-input').siblings('.img-desc-box');
		var $thisImgDescText = $('.current-input').siblings('.img-desc-text');
		$thisImgDescBox.find('.desc-text-info').text(omitText + '…');
		if($thisImgDescBox.is(':hidden')){
			$('.current-input').siblings('.img-desc-text').hide();
			$thisImgDescBox.show();
		}else if($.trim(curText) == ''){
			$thisImgDescBox.hide();
			$thisImgDescText.show();
		}
		$('.current-input').val(curText).removeClass('current-input');
		$imgDescTextBox.fadeOut();
		$shapeBox.fadeOut();
	});
	//描述取消按钮
	$('#backDescBtn').click(function(){
		$('.current-input').removeClass('current-input');
		$imgDescTextBox.fadeOut();
		$shapeBox.fadeOut();
	});
	
	//hover删除按钮
	$('.uploadPreview01').hover(function(){
		$(this).find('.img-delete-btn').show();
	},function(){
		$(this).find('.img-delete-btn').hide();
	});
	
}); 

$('.textareas').on('input propertychange', function() {
	var valLength = $.trim($(this).val()).length;
	var currentVal = $.trim($(this).val());
	if(valLength > 99){
		$(this).val(currentVal.substring(0,100));	
	}
	var num = 100 - valLength;
	if(num<0){
		num = 0;
	}
	$('.leftNum').text(num);
});

//增加删除商品参数
$(function(){
	$(".inputform").Validform({
		tiptype:3,
		ignoreHidden:true,
		beforeCheck:function(curform){
			var isPass = true;
			$('.paramAdded:visible').each(function(){
				var ThisParamNameVal = $(this).find('input[name=propName]').val();
				var ThisParamsVal = $(this).find('input[name=propValue]').val();
				if(ThisParamNameVal == "" || ThisParamsVal == ""){
					isPass = false;
					return;
				}
			});
			if(!isPass){
				alert("参数名称、参数说明不能为空！");
				return false;
			}
			var price = $.trim($("input[name=price]").val());
			var shelfPrice = $.trim($("input[name=shelfPrice]").val());
			if(price*100<shelfPrice*100){
				alert("市场价格应高于商品价格！");
				return false;
			} 
		},
		beforeSubmit:function(curform){
			$.Showmsg('正在提交，请稍后');
		}
	});

	var $paramAdded = $('.paramAdded.dsn');
	$('#createParam').click(function(){
		$paramAdded.clone(true).show().appendTo($(this).parents('table'));
		window.parent.TuneHeight();
	});	
	$('.param-delete').click(function(){
		$(this).parents('tr').remove();
		window.parent.TuneHeight();
		return false;
	});
});
</script>
</html>