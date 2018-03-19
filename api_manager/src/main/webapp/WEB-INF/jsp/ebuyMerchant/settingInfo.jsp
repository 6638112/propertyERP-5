<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>店铺设置</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css">
<link rel="stylesheet" href="../dist/photoswipe.css">
<link rel="stylesheet" href="../dist/default-skin/default-skin.css">
<style>
.my-gallery {   width: 100%; float: left; }
.my-gallery img { max-width: 100%; width: 90px; height:90px; }
.my-gallery figure { display: block; float: left; margin: 0 10px 0 0; width: 90px; height:90px; }
.my-gallery figcaption { display: none; }
</style>
</head>

<body class="bggrey">
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock mleft15 left" href="setting.html"><img class="back-arrow" src="../images/merchant/back-arrow.png" /></a>
    <div class="header-title">店铺基本信息 </div>
</header>
<form class="inputform" id="inputform" action="uploadStorePic.html" enctype="multipart/form-data" method="post" onsubmit="return false;">
<section class="sectionBox">
    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <a class="displaybox" href="javascript:void(0)">
            <div class="item-standard-name f16 boxflex01">门店头像</div>
            <div class="boxflex01 box-arrow t-right grey height50 pos-relative"><input id="fileHeadImage" name="fileHeadImage" class="opacity0" type="file" accept="image/gif,image/jpeg,image/jpg,image/png" /><img class="setting-head-img wh50" src="${merchantSupply.storePicStr}" /></div>
        </a>
    </div>
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list">
        <li class="borderbottomgrey">
            <div class="displaybox">
                <div class="item-standard-name height36 f16 boxflex01">商铺名称</div>
                <div class="boxflex01 f16 t-right grey">${merchantSupply.name}</div>
            </div>
        </li>
        <li class="borderbottomgrey">
            <div class="displaybox">
                <div class="item-standard-name height36 f16 boxflex01">联系人</div>
                <div class="boxflex01 f16 t-right grey">${merchantSupply.linkName}</div>
            </div>
        </li>
        <li class="borderbottomgrey">
            <div class="displaybox">
                <div class="item-standard-name height36 f16 boxflex01">手机号码</div>
                <div class="boxflex01 f16 t-right grey">${merchantSupply.tel}</div>
            </div>
        </li>
        <li>
            <div class="displaybox">
                <div class="item-standard-name height36 f16 boxflex01">商铺地址</div>
                <div class="boxflex01 f16 t-right grey">${pcb.apName}${pcb.acName }${pcb.abName}&nbsp; ${merchantSupply.address } </div>
            </div>
        </li>
    </ul>
    <%--
    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <div class="displaybox">
            <div class="item-standard-name height36 f16 boxflex01">经营范围</div>
            <div class="boxflex01 f16 t-right grey">米面粮油</div>
        </div>
    </div>
     --%>
    <section class="sectionBox password-mind-box bordertbgrey">
        <div class="mleft15">服务范围</div>
    </section>
    <ul class="list-box bgwhite server-area">
    	<c:forEach var="build" items="${merchantSupply.groupBuildingList }">
    		<li>${build.name }</li>
    	</c:forEach>
    </ul>
    <section class="sectionBox password-mind-box bordertbgrey"> 
        <div class="mleft15">店铺照片</div>
    </section>
    <section class="list-box bgwhite image-view">
        <div class="my-gallery" itemscope>
 			<c:forEach items="${merchantSupply.shopPhotoeList }" var="item"> 
               	<figure itemprop="associatedMedia" itemscope>
       				<a href="${item}" data-size="640x540">
          			 <img src="${item}" itemprop="thumbnail" alt="Image description" />
       				</a>
     			</figure>
         	</c:forEach>
        </div>
    </section>
    <section class="sectionBox password-mind-box bordertbgrey">
        <div class="mleft15">营业执照</div>
    </section>
    <section class="list-box bgwhite image-view">
        <div class="my-gallery" itemscope>
        	<c:forEach var="licensePic" items="${merchantSupply.merchantLicenseList }">
        		<figure itemprop="associatedMedia" itemscope>
	              <a href="${licensePic.url }" itemprop="contentUrl" data-size="640x540">
	                  <img src="${licensePic.url }" itemprop="thumbnail" alt="Image description" />
	              </a>
            	</figure>
        	</c:forEach>
        </div>
    </section>



    <!-- Root element of PhotoSwipe. Must have class pswp. -->
    <div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
    
        <!-- Background of PhotoSwipe. 
             It's a separate element, as animating opacity is faster than rgba(). -->
        <div class="pswp__bg"></div>
    
        <!-- Slides wrapper with overflow:hidden. -->
        <div class="pswp__scroll-wrap">
    
            <!-- Container that holds slides. PhotoSwipe keeps only 3 slides in DOM to save memory. -->
            <!-- don't modify these 3 pswp__item elements, data is added later on. -->
            <div class="pswp__container">
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
            </div>
    
            <!-- Default (PhotoSwipeUI_Default) interface on top of sliding area. Can be changed. -->
            <div class="pswp__ui pswp__ui--hidden">
    
                    <div class="pswp__top-bar">
                    
                        <!--  Controls are self-explanatory. Order can be changed. -->
                    
                        <div class="pswp__counter"></div>
                    
                        <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
                    
                        <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
                    
                        <!-- Preloader demo http://codepen.io/dimsemenov/pen/yyBWoR -->
                        <!-- element will get class pswp__preloader--active when preloader is running -->
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
                    
                    <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)">
                    </button>
                    
                    <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)">
                    </button>
                    
                    <div class="pswp__caption">
                        <div class="pswp__caption__center"></div>
                    </div>
    
              </div>
    
        </div>
    
    </div>
    <form class="inputform" id="inputforms" action="../ebuyMerchant/editshoopMsg.html">    
	    <section class="sectionBox password-mind-box bordertbgrey">
	        <div class="mleft15 mright15"><span class="left shoppingInfo">店铺介绍</span><span class="right blue edit-desc-btn">编辑</span></div>
	    </section>
	    <ul class="list-box bgwhite">
	    	<li class="grey" style="margin: 0 10px;padding: 0;">
	    		<div id="noEditText" class="area-text wp100 dsn">${merchantSupply.introduce }</div>
	    		<textarea name="editIntroduce" id="editText" class="area-text wp100 textareas" style="margin-left:-10px;" readonly="readonly" maxlength="200" rows="6" type="text" placeholder="跟客官们介绍下你的店铺吧">${merchantSupply.introduce }</textarea>
	    		<div class="mtop10">还可以输入<span class="leftNum">200</span>字</div>
	    	</li>
	    </ul>
	</form>
    <section class="divide-box bordertopgrey"></section>
</section>
</form>

<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/jquery.form.js"></script>
<script src="../js/merchant/Validform_v5.3.2.js"></script>
<script>
$(function(){

	document.getElementById('fileHeadImage').onchange = function(evt) {
	
		// 如果浏览器不支持FileReader，则不处理
	
		if (!window.FileReader) return;
	
		var files = evt.target.files;
	
		for (var i = 0, f; f = files[i]; i++) {
	
			if (!f.type.match('image.*')) {
	
				continue;
	
			}else if(f.size >= 10240000) {
				$.Showmsg('图片过大，应小于10M');		
				return;
			}
	
	
			var reader = new FileReader();
			var imgHtml = '';
	
			reader.onload = (function(theFile) {
	
				return function(e) {
	
					// 添加 img 元素
					$('.setting-head-img').attr('src', e.target.result);
					$.Showmsg('正在上传…');
				};
				
	
			})(f);
			
			
			reader.readAsDataURL(f);
			
			$("#inputform").ajaxSubmit({  
                success: function(data){
					if (data.status == '0000') {
						$.Showmsg("门店头像已经上传成功！");
					} else {
						$.Showmsg(data.message);
						return;
					}
                },  
                error: function(){  
                	$.Showmsg('网络不给力，请稍后重试'); 
                }  
            }); 
			
		}
	
	}
});
</script>

<script src="../js/merchant/shopping.common.js"></script>
<script src="../dist/photoswipe.min.js"></script>
<script src="../dist/photoswipe-ui-default.min.js"></script>
<script src="../dist/index.js"></script>
<script src="../js/jquery.form.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script>
$(function(){
	new FastClick(document.body);
})
</script>
<script type="text/javascript">
$(function(){
	new FastClick(document.body);
	
	var $areaTextBox = $('#editText');
	var $noEditTextBox = $('#noEditText');
	//字数限制
	$areaTextBox.keyup(function(){
		var valLength = $(this).val().length;
		var currentVal = $(this).val();
		if(valLength > 199){
			$(this).val(currentVal.substring(0,200));	
		}
		var num = 200 - valLength;
		if(num < 0){
			num = 0;
		}
		$('.leftNum').text(num);
	});
	
	$areaTextBox.keyup().hide();
	$noEditTextBox.show();
	//切换输入框状态
	$('.edit-desc-btn').click(function(){
		$areaTextBox.show();
		$noEditTextBox.hide();
		if($areaTextBox.val().length == 0){
			$.Showmsg('请填写店铺介绍');
			return false;
		}
		$(this).swapAddClass('blue','red').swapText('编辑','保存');
		if($areaTextBox.attr('readonly') == 'readonly'){
			$areaTextBox.removeAttr('readonly').focus();
		}else{
			var editIntroduce = $('#editText').val();
			$.post("<%=basePath%>/ebuyMerchant/editshoopMsg.html", {
				editIntroduce : editIntroduce
			}, function(data) {
				if(data.status == '0000') {
					$areaTextBox.attr('readonly','readonly');
					$.Showmsg('修改成功');
					$noEditTextBox.text($areaTextBox.val()).show();
					$areaTextBox.hide().text($areaTextBox.val());
					
				} else {
					$.Showmsg('修改失败');
				}
			});
			
						
		}
	})
	
})
</script>

</body>
</html>