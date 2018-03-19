<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link rel="stylesheet" href="../dist/photoswipe.css">
<link rel="stylesheet" href="../dist/default-skin/default-skin.css">
<style>
.my-gallery {   width: 100%; float: left; }
.my-gallery img { width: 100%;}
.my-gallery figure { display: block; float: left; margin:0; overflow: hidden;}
.my-gallery figcaption { display: none; }
</style>
</head>

<body>
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock mleft15 left" href="javascript:history.back();"><img class="back-arrow" src="../images/merchant/back-arrow.png" /></a>
    <div class="header-title">商品详情-出售中</div>
</header>

<form class="inputform pos-relative" id="inputform" action="saveProduct.html" enctype="multipart/form-data" method="post" onsubmit="return false;">
<section class="sectionBox item-details-info">
    <!-- Swiper -->
    <div class="swiper-container pos-relative">
        <div class="swiper-wrapper bgdarkgrey my-gallery lineheight0">
            <c:forEach var="prodPic" items="${prodDetail.prodPicList}" >
        		<figure class="swiper-slide" itemprop="associatedMedia" itemscope>
	              <a href="${prodPic.url}<c:choose><c:when test="${fn:contains(prodPic.url, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/format,jpg/interlace,1/quality,q_80" itemprop="contentUrl" data-size="640x540">
	                  <img src="${prodPic.url}<c:choose><c:when test="${fn:contains(prodPic.url, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,limit_0,w_640,h_467/format,jpg/interlace,1/quality,q_80" itemprop="thumbnail" alt="Image description" />
	              </a>
	            </figure>
        	</c:forEach>
        	<c:if test="${empty prodDetail.prodPicList}">
        		<figure class="swiper-slide" itemprop="associatedMedia" itemscope>
	              <a href="${prodDetail.picBase}" itemprop="contentUrl" data-size="640x540">
	                  <img src="${prodDetail.picBase}" itemprop="thumbnail" alt="Image description" />
	              </a>
	            </figure>
        	</c:if>	
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
    </div>
    
	<!--swiper zoom start-->
    <div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="pswp__bg"></div>
        <div class="pswp__scroll-wrap">
            <div class="pswp__container">
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
            </div>
            <div class="pswp__ui pswp__ui--hidden">
                    <div class="pswp__top-bar">
                        <div class="pswp__counter"></div>
                        <div class="pswp__button pswp__button--close" title="Close (Esc)"></div>
                        <div class="pswp__button pswp__button--zoom" title="Zoom in/out"></div>
                        <div class="pswp__preloader">
                            <div class="pswp__preloader__icn">
                              <div class="pswp__preloader__cut">
                                <div class="pswp__preloader__donut"></div>
                              </div>
                            </div>
                        </div>
                    </div>
                    <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
                        <div class="pswp__share-tooltip"></div> 
                    </div>
                    <div class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)">
                    </div>
                    <div class="pswp__button pswp__button--arrow--right" title="Next (arrow right)">
                    </div>
                    <div class="pswp__caption">
                        <div class="pswp__caption__center"></div>
                    </div>
              </div>
        </div>
    </div>
    <!--swiper zoom end-->
</section>

<section class="sectionBox padding-menu pos-relative item-details-info">
    <section class="divide-box bordertbgrey"></section>
    <input class="onShelf" type="hidden" name="onShelfStatus" id="onShelf" value="yes" />
    <input class="prodId" type="hidden" name="prodId" id="prodId" value="${prodDetail.id }" />
    <ul class="register-list">
        <li class="borderbottomgrey">
        	<div class="f16 height36">${prodDetail.prodName}</div>
        </li>
        <li>
        	<div class="f16 height36"><c:forEach var="prodType" items="${prodTypeList}"><c:if test="${prodDetail.productTypeId == prodType.id}">${prodType.typeName}</c:if></c:forEach></div>
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
                    <div class="img-desc-text" >添加描述</div>
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
                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage01" value="${status.index}" indexNum="${status.index}"/>
                </div>
                </c:forEach>
            </div>
            <div class="uploadPreview01 imgDesc-add-btn mtop15 marb20"></div> 
        </li>
        <li class="img-add-num grey">注：不超过10M，最多可上传10张</li>
    </ul>
    <section class="divide-box bordertbgrey"></section>
    <ul class="bottom-menu-box displaybox t-center">
    	<li class="p00"><input id="onshelfEdit" class="btn-submit btn-next noradius bordertbgrey red" data-param="0" type="button" value="保存修改" /></li>
    	<li class="p00 borderleft"><input id="offShelfPro" class="btn-submit btn-next noradius bordertbgrey red" data-param="1" type="button" name="button" value="下架商品" /></li>
    	<li id="promotionBtn" class="p00">
			<a href="activity/addLimitBuy.html?productId=${prodDetail.id}"><input class="btn-submit bgred noradius btnSubmit" data-param="2" type="button" value="发起促销" /></a>
		</li>
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
	<div class="pop-tips-text">提交成功<br></div>
</section>
<section class="pop-tips1 hide">
	<div class="pop-tips-text">提交成功<br>您可以在仓库中编辑该商品</div>
</section>
<section class="pop-tips3 upload-text hide">
	<div class="pop-tips-text">图片上传中...</div>
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

<section class="pop-tips2 hide">
	<div class="pop-tips-text">提交成功<br></div>
</section>
<section class="pop-tips1 hide">
	<div class="pop-tips-text">提交成功<br>您可以在仓库中编辑该商品</div>
</section>
<section class="pop-tips3 upload-text hide">
	<div class="pop-tips-text">信息提交中，请稍候...</div>
</section>

<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/swiper.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script src="../js/merchant/resetSwaperSize.js"></script>
<script src="../js/jquery.form.js"></script>
<script src="../js/merchant/Validform_v5.3.2.js"></script>
<script src="../js/merchant/lrz.bundle.js"></script>
<script src="../js/merchant/imgUploadResize.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
</script>
<script>	
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: false,
        spaceBetween: 0,
		loop:false
    });

    $("#offShelfPro").click(function(){
		$.ajax({
			type:"post",
			url:"offShelf.html",
			data:{prodId : $("#prodId").val()},
			dataType:"json",
			beforeSend:function(data){
				$.Showmsg("正在下架…");
				$("#offShelfPro").attr('disabled', 'disabled');
			},
			success:function(data){
				try {
					if (data.status == '0000') {
						$.Showmsg('下架商品成功');
						setTimeout(function(){
							location.reload();
						},1500);
					} else {
						$.Showmsg(data.message);
						$("#offShelfPro").removeAttr('disabled', 'disabled');
						return;
					}
				} catch (e) {
					$.Showmsg(e);
				}
			},  
            error: function(){  
            	$.Showmsg('网络不给力，请稍后重试'); 
				$("#offShelfPro").removeAttr('disabled', 'disabled');
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
<script src="../js/merchant/shopping.common.js"></script>
<script src="../dist/photoswipe.min.js"></script>
<script src="../dist/photoswipe-ui-default.min.js"></script>
<script src="../dist/index.js"></script>
<script type="text/javascript">
//表单验证
$(".inputform").Validform({
	tiptype:1,
	postonce:false,
	btnSubmit:"#onshelfEdit", 
	beforeSubmit:function(){
		$('.upload-text').removeClass('hide');
		$('#onshelfEdit').attr('disabled','disabled');
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
					if(curBtnParam == 0){
						var $popTips = $('.pop-tips2');
					}else if(curBtnParam == 1){
						var $popTips = $('.pop-tips2');
					}
					$popTips.removeClass('hide');
					setTimeout(function(){
						window.history.back(-1);
					},2000);
				} else {
					$.Showmsg(data.message);
					$('#onshelfEdit').removeAttr('disabled','disabled');
					return;
				}
            },  
            error: function(){  
            	$.Showmsg('网络不给力，请稍后重试'); 
				$('#onshelfEdit').removeAttr('disabled','disabled');
            }  
        }); 
	}
});
</script>

</body>
</html>