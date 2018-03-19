<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>商品详情</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css" type="text/css">
<link rel="stylesheet" href="../css/merchant/swiper.css" type="text/css">
</head>

<body class="pos-relative">
<header class="sectionBox fantasia-header order-top-bg">
	<a class="backLink disblock mleft15 left" href="javascript:history.back();"><img class="back-arrow" src="../images/merchant/back-arrow.png" /></a>
    <div class="header-title">商品详情-仓库中</div>
</header>

<form class="inputform pos-relative" id="inputform" action="saveProduct.html" enctype="multipart/form-data" method="post" onsubmit="return false;">
<section class="sectionBox item-details-info pos-relative">
    <!-- Swiper -->
   	<div class="item-img-add-btn white"><input class="opacity0 fileImage zIndex9" name="imageFile" type="file" accept="image/gif,image/jpeg,image/jpg,image/png" multiple=true /><img src="../images/merchant/img-upload-icon.png" /><br>最多上传3张</div>
    <div class="swiper-container pos-relative">
    	<input class="imageNums" type="hidden" name="imageNums" id="imageNums" value="">
    	<input class="imageDelete" type="hidden" name="imageDelete" id="imageDelete" value="">
        <div class="swiper-wrapper bgdarkgrey">
        	<c:forEach var="prodPic" items="${prodDetail.prodPicList}" >
        		<div class="swiper-slide"><input class="hide input-file-img" name="imageFile" type="file" accept="image/gif,image/jpeg,image/jpg,image/png" value="" /><img src="${prodPic.url}<c:choose><c:when test="${fn:contains(prodPic.url, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,limit_0,w_640,h_467/format,jpg/interlace,1/quality,q_80"><a href="javascript:void(0)" deleteId="${prodPic.id}" class="upload_delete" title="删除"></a></div>
        	</c:forEach>
        	<%--
            <div class="swiper-slide"><img src="../images/merchant/1b.jpg"><a href="javascript:void(0)" class="upload_delete" title="删除"></a></div>  
            <div class="swiper-slide"><img src="../images/merchant/welcome-img.png"><a href="javascript:void(0)" class="upload_delete" title="删除"></a></div>
             --%>   	
        </div>
        <div class="swiper-pagination"></div>
        <input class="img-upload-val hide" type="text" datatype="*" <c:if test="${!empty prodDetail.prodPicList}">value="true"</c:if> nullmsg="请上传商品图片！" />
    </div>
</section>

<section class="sectionBox padding-menu pos-relative item-details-info">
    <section class="divide-box bordertbgrey"></section>
    <input class="prodId" type="hidden" name="prodId" id="prodId" value="${prodDetail.id }" />
    <ul class="register-list">
        <li class="borderbottomgrey"><input class="input-text wp100" type="text" name="prodName" placeholder="请输入商品名称" datatype="*" value="${prodDetail.prodName}" nullmsg="请输入商品名称！" /></li>
        <li class="pos-relative ">
        	<select class="input-text wp100 list-arrow" datatype="*" nullmsg="请选择商品上架的分类！" name="productTypeId" >
            	<option value="">请选择商品上架的分类</option>
            	<c:forEach var="prodType" items="${prodTypeList}">
            		<option value="${prodType.id}" <c:if test="${prodDetail.productTypeId == prodType.id}">selected="selected"</c:if>>${prodType.typeName}</option>
            	</c:forEach>
            	<%--
            	<option value="1">粮油干货</option>
            	<option value="2">蔬菜瓜果</option>
            	 --%>
            </select>
        </li>
    </ul>
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list">
        <li class="borderbottomgrey displaybox">
            <div class="item-standard-name f16 boxflex01">价格</div>
            <div class="boxflex01"><input class="input-text holder-right t-right wp100" type="text" name="priceDiscount" placeholder="￥" datatype="*" value="${prodDetail.priceDiscount}" nullmsg="请输入商品价格！" /></div>
        </li>
        <li class="displaybox">
            <div class="item-standard-name f16 boxflex01">库存</div>
            <div class="boxflex01"><input class="input-text holder-right t-right wp100" type="text" name="leftCount" placeholder="如：999" datatype="*" value="${prodDetail.leftCount}" nullmsg="请输入商品库存！" /></div>
        </li>
        <%--
        <li class="displaybox">
            <div class="item-standard-name f16 boxflex01">规格</div>
            <div class="boxflex01"><input class="input-text t-right wp100" type="text" placeholder="如:35g/袋" datatype="*"  nullmsg="请输入商品规格！" /></div>
        </li>
        --%>
    </ul>
    <section class="sectionBox password-mind-box bordertbgrey">
        <div class="mleft15 grey">选填</div>
    </section>
    <ul class="register-list">
        <li class="borderbottomgrey displaybox">
            <div class="item-standard-name f16 boxflex01">市场参考价</div>
            <div class="boxflex01"><input class="input-text t-right wp100" name="price" type="text" value="${prodDetail.price}" placeholder="￥" /></div>
        </li>
        <li>
        	<a class="displaybox item-param-btn" href="javascript:void(0)">
                <div class="item-standard-name f16 boxflex01">商品参数</div>
                <div class="boxflex01"><input class="input-text t-right wp100 list-arrow" type="text" disabled /></div>
            </a>
        </li>
    </ul>
    <section class="sectionBox password-mind-box bordertbgrey">
        <div class="mleft15 grey">商品详情图片</div>
    </section>
    <ul class="register-list">
        <li class="overvisible">
        	<div id="dragItems">
        		<c:forEach var="introducePic" items="${introducePicList}" varStatus="status">
        	     <div class="uploadPreview01 mright6 mtop30 marb20 drag-item" style="background-image: url(<%=OmsSysParamManager.getImageServerUrl("/productPic/") %>/productPic/${introducePic.urlBig}<c:choose><c:when test="${fn:contains(introducePic.urlBig, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_140,h_140/format,jpg/interlace,1/quality,q_80)">
        	     	<div class="img-delete-btn">删除</div>
        	     	<c:if test="${introducePic.textDesc ==null || introducePic.textDesc ==''}">
                    <div class="img-desc-text">添加描述</div>
                    <div class="img-desc-box dsn"><span class="desc-text-info">文字描述</span><span class="icon-edit"><img width="14" height="14" src="../images/icon-edit.png" /></span></div>
                    </c:if>
                    <c:if test="${introducePic.textDesc !=null && introducePic.textDesc !=''}">
                    	<div class="img-desc-text" style="display: none;">添加描述</div>
                        <div class="img-desc-box dsn" style="display: block;">
							<span class="desc-text-info">${fn:substring(introducePic.textDesc, 0, 5)}…</span>
							<span class="icon-edit"><img width="14" height="14" src="../images/icon-edit.png" /></span>
						</div>
                    </c:if>
                    <input class="desc-text-input dsn" type="text" name="descText" value="${introducePic.textDesc}" />
                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage01" value="" indexNum="${status.index}"/>
                </div>
                </c:forEach>
            </div>
            <div class="uploadPreview01 imgDesc-add-btn mtop15 marb20"></div> 
        </li>
        <li class="img-add-num grey">注：不超过10M，最多可上传10张</li>
    </ul>
    <section class="divide-box bordertbgrey"></section>
    <ul class="bottom-menu-box displaybox t-center">
    	<li class="p00"><input class="btn-submit btn-next noradius bordertbgrey btnSubmit" data-param="1" type="button" value="放入仓库" /></li>
    	<li id="sellBtn" class="p00"><input class="btn-submit bgred noradius btnSubmit" data-param="2" type="button" value="提交出售" /></li>
    </ul>
    <div class="hide"><input class="click-btn-param" type="text" name="submitStatus" value="" /></div>
</section>

<section id="itemParamInfo" class="sectionBox padding-menu pos-relative hide">
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list item-param-box">
    	<c:choose>
   		  <c:when test="${empty prodDetail.prodParamList || fn:length(prodDetail.prodParamList) == 0}">
   		  	<li class="borderbottomgrey displaybox item-param">
	            <div class="boxflex01"><input class="input-text holder-right t-left wp100" type="text" placeholder="参数名称" name="paramKey1" /></div>
	            <div class="boxflex01"><input class="input-text holder-right t-right wp100" type="text" placeholder="商品参数" name="paramValue1" /></div>
	        </li>
   		  </c:when>
   		  <c:otherwise>
   			<c:forEach var="prodParam" items="${prodDetail.prodParamList}" varStatus="status">
   		  		<li class="borderbottomgrey displaybox item-param">
		            <div class="boxflex01"><input class="input-text holder-right t-left wp100" type="text" placeholder="参数名称" name="paramKey${status.count}" value="${prodParam.propName}" /></div>
		            <div class="boxflex01"><input class="input-text holder-right t-right wp100" type="text" placeholder="商品参数" name="paramValue${status.count}" value="${prodParam.propValue}" /></div>
		        </li>
   		  	</c:forEach>
   		  </c:otherwise>
    	</c:choose>
    </ul>
    <li class="borderbottomgrey displaybox item-param hide">
        <div class="boxflex01"><input class="input-text holder-right param-key t-left wp100" name="" type="text" placeholder="参数名称" /></div>
        <div class="boxflex01"><input class="input-text holder-right param-value t-right wp100" name="" type="text" placeholder="商品参数" /></div>
    </li>
    <section class="divide-box bordertbgrey"></section>
    <section class="sectionBox bgwhite ptb15">
        <div class="displaybox">
            <div class="boxflex01 m010"><input id="deleteItemParam" class="btn-submit btn-next" type="button" value="删减参数" /></div>
            <div class="boxflex01 m010"><input id="addItemParam" class="btn-submit btn-next" type="button" value="增加参数" /></div>
        </div>
    </section>
    <section class="divide-box bordertbgrey"></section>
    <section class="sectionBox bgwhite">
        <div class="m010 mtop15"><input id="itemParamSubmit" class="btn-submit bgred" type="button" name="button" value="提交" /></div>
    </section>
</section>
</form>
<section class="pop-tips2 hide">
	<div class="pop-tips-text">提交成功<br>审核通过后商品便可出售咯</div>
</section>
<section class="pop-tips1 hide">
	<div class="pop-tips-text">提交成功<br>您可以在仓库中编辑该商品</div>
</section>
<section class="pop-tips3 upload-text hide">
	<div class="pop-tips-text">信息提交中，请稍候...</div>
</section>
<div class="img-desc-text-box dsn">
    <h3>添加图片描述</h3>
    <textarea class="textareas txt02 w470" name="" cols="" rows="4" ></textarea>
    <div class="mar-left15">还可输入<span class="leftNum">100</span>字</div>
    <div>
        <input id="checkDescBtn" class="info-btn mar-left15" type="button" value="确定" />
        <input id="backDescBtn" class="info-btn mar-left15 info-btn01 mtop0" type="button" value="取消" />
    </div>
</div>
<div class="shape-box dsn"></div>
</form>
<div class="uploadPreview01 imgDesc-new-add drag-item mtop30 marb20 img-hide">
    <div class="img-delete-btn">删除</div>
    <div class="img-desc-text">添加描述</div>
    <div class="img-desc-box dsn"><span class="desc-text-info">文字描述</span><span class="icon-edit"><img width="14" height="14" src="../images/merchant/icon-edit.png" /></span></div>
    <input class="desc-text-input dsn" type="text" name="descText" />
    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage01" indexNum="11"/>
</div>
<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/swiper.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script src="../js/merchant/resetSwaperSize.js"></script>
<script src="../js/jquery.form.js?v08241900"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
</script>
<script>	
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        centeredSlides: true,
    });
</script>
<script src="../js/merchant/Validform_v5.3.2.js"></script>
<script src="../js/merchant/lrz.bundle.js"></script>
<script src="../js/merchant/imgUploadResize.js"></script>
<script>
	$(function(){
		//传递按钮参数
		$('.btnSubmit').click(function(){
			var btnParam = $(this).attr('data-param');
			$('.click-btn-param').val(btnParam);
		});
		
		//店铺状态审核中，改变‘提交出售’按钮状态
		if(${storeStatus != 1}) {
			$('#sellBtn').addClass('borderleft').find('input').attr('class','btn-submit btn-next noradius bordertbgrey no-sell-button').attr('data-param','4');
			$('.no-sell-button').click(function(){
				$.Showmsg('商户通过审核才能提交出售哟，先放入仓库吧!');
			});
		}
		
		//表单验证
		$(".inputform").Validform({
			tiptype:1,
			postonce:false,
			btnSubmit:".btnSubmit", 
			beforeSubmit:function(){
				$('.upload-text').removeClass('hide');
				$('.btnSubmit').attr('disabled','disabled');
				$("input[class='uploadImage01']").each(function(i){ 
					var $thisPreviewBox = $(this).parent(".uploadPreview01");
					if($thisPreviewBox.find('.newImgUpLoad.dsn').length == 0){
						var $newInput = $('<input class="newImgUpLoad dsn" type="hidden">');
						$newInput.prependTo($thisPreviewBox);
					}
					swiper.appendSlide('<input class="hide" type="hidden" name="imageindex'+i+'" id="imageindex" value="'+$(this).attr("indexNum")+'">');
				});
				$("input[class='newImgUpLoad dsn']").each(function(i){ 
				 	  $(this).attr('name','newUpLoad'+i);
				    });
				$("input[name='descText']").each(function(i){ 
				 	  $(this).attr('name','descText'+i);
				    });

				$("#imageNums").val($("input[class='uploadImage01']:visible").length);
			},
			callback:function(data){
				$("#inputform").ajaxSubmit({  
                    success: function(data){
    					$('.upload-text').addClass('hide');
    					var curBtnParam = $('.click-btn-param').val();
						if (data.status == '0000') {
							if(curBtnParam == 1){
								var $popTips = $('.pop-tips1');
							}else if(curBtnParam == 2){
								var $popTips = $('.pop-tips2');
							}
							$popTips.removeClass('hide');
							setTimeout(function(){
								window.history.back(-1);
							},2000);
						} else {
							$.Showmsg(data.message);
	        				$('.btnSubmit').removeAttr('disabled');
							return;
						}
                    },  
                    error: function(){  
						$('.upload-text').addClass('hide');
        				$('.btnSubmit').removeAttr('disabled');
                    	$.Showmsg('网络不给力，请稍后重试'); 
                    }  
                }); 
			}
		});
	});	
	
	$(function(){	
		//增加删除商品参数
		//var paramNum = 1;
		var paramNum = ${prodDetail.paramNum};
		var $itemParam = $('.item-param.hide');
		$('#addItemParam').click(function(){
			
			var $itemParamClone = $itemParam.clone(true);
			if($('.new-item-param').length == 9){
				$.Showmsg('最多10个商品参数！');
				return false;	
			}
			paramNum += 1;
			
			$itemParamClone.find('.param-key').attr('name', 'paramKey' + paramNum);
			$itemParamClone.find('.param-value').attr('name', 'paramValue' + paramNum);
			$('.item-param-box').append($itemParamClone.addClass('new-item-param').removeClass('hide'));
		});
		
		$('#deleteItemParam').click(function(){
			var $itemParamLast = $('.item-param-box .new-item-param:last-child');
			$itemParamLast.remove();
			if(paramNum > 1){
				paramNum -= 1;
			}
		});
		
		$('.item-param-btn').click(function(){
			$('.item-details-info').hide();
			$('#itemParamInfo').removeClass('hide');
			$('.backLink').addClass('hide');
		});
		$('#itemParamSubmit').click(function(){
			$('#itemParamInfo').addClass('hide');
			$('.item-details-info').show();
			$('.backLink').removeClass('hide');
		});
		
	});
</script>

<script>

$(function(){

	$(document).on('change', '.fileImage', function(evt) {
	
		// 如果浏览器不支持FileReader，则不处理
	
		if (!window.FileReader) return;
	
		var files = evt.target.files;
		var $this = $(this);
	
		for (var i = 0, f; f = files[i]; i++) {
	
			if (!f.type.match('image.*')) {
	
				continue;
	
			}else if(f.size >= 10240000) {
				$.Showmsg('图片过大，应小于10M！');	
				return;
			}
	
	
			var reader = new FileReader();
			var imgHtml = '';
	
			reader.onload = (function(theFile) {
	
				return function(e) {
	
					// 添加 img 元素
					var listLength = $('div.swiper-slide').length;
					var $imgUploadVal = $('.img-upload-val');
					var uploadNum = 0;
					if(listLength > 2){
						$.Showmsg('最多上传3张图片！');
						$this.val('');
						return false;
					}
					
					swiper.appendSlide('<div class="swiper-slide"><img src="' + e.target.result + '" />' + '<a href="javascript:void(0)" class="upload_delete" title="删除"' + '></a>' + '</div>');
					uploadNum += 1;
					$imgUploadVal.val(uploadNum);

					$this.clone().val('').prependTo('.item-img-add-btn');
					$this.removeClass('zIndex9').addClass('hide');
					
				};
				
	
			})(f);
			
			
			reader.readAsDataURL(f);
	
		}
	});
});


$(function(){
	var newId = '';
	$(document).on('click', '.upload_delete', function(){
		var currentId = $('#imageDelete').val();
		var itemId = $(this).attr('deleteId');
		var liLength = $(this).parents('.swiper-wrapper').children('.swiper-slide').length;
		var liIndex = $(this).parents('.swiper-slide').index();
		if(liLength == 1){
			 $('.img-upload-val').val('');
		}
		swiper.removeSlide(liIndex);
		if(itemId != undefined){
			if(currentId == ''){
				newId = itemId;
			}else{
				newId = currentId + ',' + itemId;
			}
			$('#imageDelete').val(newId);
		}
	});
	
});

</script>
</body>
</html>