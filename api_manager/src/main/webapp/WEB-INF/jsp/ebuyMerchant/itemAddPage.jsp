<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<meta name="format-detection" content="telephone=no, email=no">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>添加商品</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css?20160224" type="text/css">
<link rel="stylesheet" href="../css/merchant/swiper.css" type="text/css">
</head>

<body class="pos-relative">
<header class="sectionBox fantasia-header order-top-bg">
	<a class="backLink disblock mleft15 left" href="javascript:history.back();"><img class="back-arrow" src="../images/merchant/back-arrow.png" /></a>
    <div class="header-title">添加商品</div>
</header>

<form class="inputform pos-relative" id="inputform" action="saveProduct.html" enctype="multipart/form-data" method="post" onsubmit="return false;">
<section class="sectionBox item-details-info pos-relative">
   	<div class="item-img-add-btn white"><input class="opacity0 fileImage zIndex9" name="imageFile" type="file" accept="image/gif,image/jpeg,image/jpg,image/png" multiple=true /><img src="../images/merchant/img-upload-icon.png" /><br>最多上传3张</div>
    <!-- Swiper -->
    <div class="swiper-container pos-relative">
        <div class="swiper-wrapper bgdarkgrey">
        </div>
        <div class="swiper-pagination"></div>
        <input class="img-upload-val hide" type="text" datatype="*" nullmsg="请上传商品图片！" />
    </div>
</section>

<section class="sectionBox padding-menu pos-relative item-details-info">
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list">
        <li class="borderbottomgrey"><input class="input-text wp100" type="text" name="prodName" placeholder="请输入商品名称" datatype="*1-30" nullmsg="请输入商品名称！" errormsg="商品名称由1-30个字符组成" /></li>
        <li class="pos-relative ">
        	<select class="input-text wp100 list-arrow" datatype="*" nullmsg="请选择商品上架的分类！" name="productTypeId">
            	<option value="">请选择商品上架的分类</option>
            	<c:forEach var="prodType" items="${prodTypeList}">
            		<option value="${prodType.id}">${prodType.typeName}</option>
            	</c:forEach>
            </select>
        </li>
    </ul>
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list">
        <li class="borderbottomgrey displaybox">
            <div class="item-standard-name f16 boxflex01">价格</div>
            <div class="boxflex01"><input class="input-text holder-right t-right wp100" type="text" name="priceDiscount" placeholder="￥" maxlength="10" datatype="numberFixTwo" nullmsg="请输入商品价格！" errormsg="请填写数字，可带两位小数！"  /></div>
        </li>
        <li class="borderbottomgrey displaybox">
            <div class="item-standard-name f16 boxflex01">库存</div>
            <div class="boxflex01"><input class="input-text holder-right t-right wp100" type="text" name="leftCount" placeholder="如：999" maxlength="4" datatype="fei0zhengzhengshu" nullmsg="请输入商品库存！" errormsg="请填写大于0的整数！"  /></div>
        </li>
        <%--
        <li class="displaybox">
            <div class="item-standard-name f16 boxflex01">规格</div>
            <div class="boxflex01"><input class="input-text t-right wp100" type="text" name="priceDiscount" placeholder="如:35g/袋" datatype="*" nullmsg="请输入商品规格！" /></div>
        </li>
         --%>
    </ul>
    <section class="sectionBox password-mind-box bordertbgrey">
        <div class="mleft15 grey">选填</div>
    </section>
    <ul class="register-list">
        <li class="borderbottomgrey displaybox">
            <div class="item-standard-name f16 boxflex01">市场参考价</div>
            <div class="boxflex01"><input class="input-text t-right wp100" type="text" name="price" placeholder="￥" ignore="ignore" maxlength="10" datatype="numberFixTwo" errormsg="请填写数字，可带两位小数！" /></div>
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
                <!--<div class="uploadPreview01 mright6 mtop30 marb20 drag-item">
                    <div class="img-desc-text">添加描述</div>
                    <div class="img-desc-box dsn"><span class="desc-text-info">文字描述</span><span class="icon-edit"><img width="14" height="14" src="images/icon-edit.png" /></span></div>
                    <input class="desc-text-input dsn" type="text" name="descText" value="" />
                    <input type="file" name="photoimage" class="uploadImage01" />
                </div>    -->
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
	    <li class="bordertopgrey displaybox item-param hide">
	        <div class="boxflex01"><input class="input-text holder-right param-key t-left wp100" name="" type="text" placeholder="参数名称" maxlength="10" /></div>
	        <div class="boxflex01"><input class="input-text holder-right param-value t-right wp100" name="" type="text" placeholder="商品参数" maxlength="50" /></div>
	    </li>
	    <li class="displaybox item-param">
	        <div class="boxflex01"><input class="input-text holder-right t-left wp100" type="text" placeholder="参数名称" maxlength="10" name="paramKey1" /></div>
	        <div class="boxflex01"><input class="input-text holder-right t-right wp100" type="text" placeholder="商品参数" maxlength="50" name="paramValue1" /></div>
	    </li>
    </ul>
    <section class="divide-box bordertbgrey"></section>
    <section class="sectionBox bgwhite ptb15">
        <div class="displaybox">
            <div class="boxflex01 m010"><input id="deleteItemParam" class="btn-submit btn-next" type="button" value="删减参数" /></div>
            <div class="boxflex01 m010"><input id="addItemParam" class="btn-submit btn-next" type="button" value="增加参数" /></div>
        </div>
    </section>
    <section class="divide-box bordertbgrey"></section>
    <section class="sectionBox bgwhite">
        <div class="m010 mtop15"><input id="itemParamSubmit" class="btn-submit bgred" type="button" name="button" value="保存并返回" /></div>
    </section>
</section>
</form>
<section class="pop-tips4 hide">
	<div class="pop-tips-text">商户通过审核才能提交出售哟，先放入仓库吧</div>
</section>
<section class="pop-tips2 hide">
	<div class="pop-tips-text">提交成功<%--<br>审核通过后商品便可出售咯--%></div>
</section>
<section class="pop-tips1 hide">
	<div class="pop-tips-text">提交成功<br>您可以在仓库中编辑该商品</div>
</section>
<section class="pop-tips3 upload-text hide">
	<div class="pop-tips-text">信息提交中，请稍候 (<span class="uploadPercent"></span>)</div>
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
    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage01" />
</div>

<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/swiper.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script src="../js/merchant/resetSwaperSize.js"></script>
<script src="../js/jquery.form.js"></script>
<script src="../js/jquery-migrate-1.1.0.min.js"></script>
<script src="../js/Sortable.js"></script> 
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
<script src="../js/merchant/Validform_v5.3.2.js?v20151029"></script>
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
			dragonfly:true,
			postonce:false,
			btnSubmit:".btnSubmit", 
			beforeSubmit:function(){
				$('.upload-text').removeClass('hide');
				$('.btnSubmit').attr('disabled','disabled');
				$("input[class='newImgUpLoad dsn']").each(function(i){ 
				 	  $(this).attr('name','newUpLoad'+i);
				    });
				$("input[name='descText']").each(function(i){ 
				 	  $(this).attr('name','descText'+i);
				    });
			},
			callback:function(data){
				$("#inputform").ajaxSubmit({ 
					uploadProgress: function(event, position, total, percentComplete) {//上传的过程
		                //position 已上传了多少
		                //total 总大小
		                //已上传的百分数
		                var percentVal = percentComplete - 1 + '%';
		                $('.uploadPercent').text(percentVal)
		            },
                    success: function(data){  
		                $('.uploadPercent').text('100%');
    					$('.upload-text').addClass('hide');
    					var curBtnParam = $('.click-btn-param').val();
                    	if (data.status == '0000') {
							if(curBtnParam == 1){
								var $popTips = $('.pop-tips1');
							}else if(curBtnParam == 2){
								var $popTips = $('.pop-tips2');
							}else if(curBtnParam == 4){
								var $popTips = $('.pop-tips4');
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
		//var paramNum = ${prodDetail.paramNum};
		var paramNum = 1;
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
			
			var paramComplete = true;
			$('.item-param:visible').each(function(){
				var inputValNum = 0;
				$(this).find('div.boxflex01').each(function(){
					var thisVal = $(this).find('.input-text').val();
					if(thisVal !== ''){
						inputValNum += 1;
					}
				})
				if(inputValNum === 1){
					$.Showmsg('请填写参数信息');
					paramComplete = false;
					return false;
				}
			})
			
			if(paramComplete){
				$('#itemParamInfo').addClass('hide');
				$('.item-details-info').show();
				$('.backLink').removeClass('hide');
			}
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
	$(document).on('click', '.upload_delete', function(){
		var liLength = $(this).parents('.swiper-wrapper').children('.swiper-slide').length;
		var liIndex = $(this).parents('.swiper-slide').index();
		if(liLength == 1){
			 $('.img-upload-val').val('');
		}
		swiper.removeSlide(liIndex);
	});

	//商品详情图片拖拽排序	
	var dragItems = document.getElementById('dragItems');
	var sortable = Sortable.create(dragItems);	
	var menuBox = document.querySelector('.bottom-menu-box');
	menuBox.addEventListener('touchmove', function(event) {
		event.preventDefault();
	});
	
});
</script>
</body>
</html>