<%@ page language="java" pageEncoding="UTF-8"%>
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

<title>店铺编辑</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css" type="text/css">
</head>

<body>
<input class="old-img-box hide" hidden="true" type="text" name="oldImgUrl" value="">
<div class="uploadPreview01 img-new-add img-hide">
	                <div class="img-delete-btn">删除</div>
	                <!-- <input class="old-img-box" hidden="true" type="text" name="oldImgUrl" value="newImg"> -->
	                <input type="file" name="" class="uploadImage01 fimg1 w100 height100" accept="image/gif,image/jpeg,image/jpg,image/png" />
</div>

                
<form id="inputform" class="inputform" enctype="multipart/form-data" action="updShopBusinessInfo.html" method="post" onsubmit="return false;">
<input type="hidden" name="id" value="${merchantSupply.id}"> 
<div class="shop-part01">
    <header class="sectionBox fantasia-header order-top-bg">
        <a class="disblock mleft15 left" href="setting.html"><img class="back-arrow" src="../images/back-arrow.png" /></a>
        <div class="header-title">完善店铺信息</div>
    </header>
    
    <section class="sectionBox padding-menu item-details-info pos-relative">
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">店铺名称</div>
        </section>
        <ul class="register-list">
            <li><input class="input-text wp100" name="shopName" type="text" placeholder="请输入店铺名称" maxlength="20" datatype="*" nullmsg="请输入店铺名称！" value="${merchantSupply.name}"/></li>
        </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">联系人</div>
        </section>
        <ul class="register-list">
            <li><input class="input-text wp100" name="linkName" type="text" placeholder="请输入联系人" maxlength="20" datatype="*" nullmsg="请输入联系人！" value="${merchantSupply.linkName}" /></li>
        </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">客服电话</div>
        </section>
        <ul class="register-list">
            <li><input class="input-text wp100" name="linkPhone" type="text" maxlength="15" placeholder="请输入客服电话" datatype="m|phoneNumber" nullmsg="请输入客服电话！" errormsg="请输入正确的手机或座机号码！" value="${merchantSupply.tel}"/></li>
        </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">店铺地址</div>
        </section>
        <ul class="register-list">
        	  <li class="borderbottomgrey">
   					<select id="province" onchange="onSelectChange(this,'city');" class="province select_normal input-text wp100 list-arrow" data-first-title="选择省" title="选择省"  datatype="*" nullmsg="请完善店铺地址！" >
	                   	<option value="">选择省</option>
	                   	<c:forEach items="${pcbList}" var="pcb2" >
	                   		<option value="${pcb2.id}" <c:if test='${pcb.apId == pcb2.id}'>selected='selected' </c:if> >${pcb2.name}</option>
                   		</c:forEach>
                   </select> 
        	  </li>
	                       
	                        
            <li class="borderbottomgrey">
            	<select id="city" onchange="onSelectChange(this,'block');"  class="input-text wp100 list-arrow" datatype="*" nullmsg="请完善店铺地址！">
                      	<option value="">选择城市</option>
                      	<option value="${pcb.acId}" selected='selected' >${pcb.acName }</option>
                </select> 
            </li>
            
            <li class="borderbottomgrey">
             	<select  id="block" name="block" class="input-text wp100 list-arrow" datatype="*" nullmsg="请完善店铺地址！">
	                <option value="">选择区</option>
	                <option value="${pcb.abId}" selected='selected' >${pcb.abName }</option>
	            </select>
            </li>
            <li><input class="input-text wp100" name="shopAddress" type="text" placeholder="请填写店铺具体地址" maxlength="50" datatype="*" nullmsg="请填写店铺具体地址！" value="${merchantSupply.address}" /></li>
        </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">服务范围</div>
        </section>
        <ul class="register-list">
            <li>
                <input class="input-text wp100 list-arrow shop-area-select" type="text" readonly placeholder="请选择可派送的周边服务小区" value="已选择3个小区" datatype="*" nullmsg="请选择可派送的周边服务小区！" />
            </li>
            <li class="p00 bordertopgrey area-add-list">
            	<ul class="list-box bgwhite server-area area-list">
                    <c:forEach items="${merchantSupply.groupBuildingList }" var="gb"> 
	                    <li class="newAreaName newArea${gb.id }">${gb.name }<input name="groupBuildingId" value="${gb.id }" class="dsn newAreaInputA1"><div class="quick-delete"></div></li>
            		</c:forEach>
                </ul>
            </li>
        </ul>
        <section class="sectionBox ptb10 bordertopgrey">
            <div class="mleft15 grey">若您有多家门店，或服务范围大于当前门店所在行政区，请与我们联系：<a class="green" href="tel:0755-22690962">0755-22690962</a></div>
        </section>
        
        
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">店铺照片</div>
        </section>
        <ul class="register-list">
            <li class="overvisible">
                 <c:forEach var="shopPhoto" items="${merchantSupply.shopPhotoeList }">
	                <div class="uploadPreview01 old-img" style="background-image:url(${shopPhoto}<c:choose><c:when test="${fn:contains(shopPhoto, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,limit_0,w_140,h_140/format,jpg/interlace,1/quality,q_90);">
		                <div class="img-delete-btn">删除</div>
		                <input type="file" data-name="storeImage" value="${shopPhoto}<c:choose><c:when test="${fn:contains(shopPhoto, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,limit_0,w_140,h_140/format,jpg/interlace,1/quality,q_90" class="uploadImage01 fimg1 w100 height100" accept="image/gif,image/jpeg,image/jpg,image/png" />
	                </div>
	            </c:forEach>
                <div class="uploadPreview01 img-add-btn <c:if test="${fn:length(merchantSupply.shopPhotoeList) > 2}">hide</c:if>" data-name="storeImage"></div>
                <input class="uploadImageVal01" hidden="true" type="text" name="text" datatype="*" value="1" nullmsg="请上传店铺照片！">
            </li>
            <li class="img-add-num grey">注：不超过10M，最多可上传3张</li>
        </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">营业执照</div>
        </section>
        <ul class="register-list">
            <li class="overvisible">
	            <c:forEach var="licensePic" items="${merchantSupply.merchantLicenseList }">
                	<div class="uploadPreview01 old-img" style="background-image:url(${licensePic.url}<c:choose><c:when test="${fn:contains(licensePic.url, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,limit_0,w_140,h_140/format,jpg/interlace,1/quality,q_90);">
	                	<div class="img-delete-btn">删除</div>
	                	<input type="file" data-name="blImage" value="${licensePic.url}<c:choose><c:when test="${fn:contains(licensePic.url, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,limit_0,w_140,h_140/format,jpg/interlace,1/quality,q_90" class="uploadImage01 fimg1 w100 height100" accept="image/gif,image/jpeg,image/jpg,image/png" />
                	</div>
	            </c:forEach>
                <div class="uploadPreview01 img-add-btn <c:if test="${fn:length(merchantSupply.merchantLicenseList) > 2}">hide</c:if>" data-name="blImage"></div>
                <input class="uploadImageVal01" hidden="true" type="text" name="text" datatype="*" value="2" nullmsg="请上传营业执照！">
            </li>
            <li class="img-add-num grey">注：不超过10M，最多可上传3张</li>
        </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">店铺介绍</div>
        </section>
        <ul class="register-list">
            <li class="grey" style="margin: 0 25px;padding: 10px 0;overflow: visible;"><textarea name="introduce" class="area-text wp100 textareas" style="margin-left: -10px;" rows="5" type="text" placeholder="跟客官们介绍下你的店铺吧" datatype="*" nullmsg="请填写店铺介绍！"  >${merchantSupply.introduce}</textarea><br />还可以输入<span class="leftNum">200</span>字</li>
        </ul>
        <section class="divide-box bordertbgrey"></section>
        <ul class="bottom-menu-box displaybox t-center">
            <li class="p00">
            	<!-- <div class="btn-submit btn-next noradius bordertbgrey btnSubmit red"><div class="create-btn-text"><span class="grey f14">提交资料</span></div></div> -->
            	<input class="btn-submit btn-next noradius bordertbgrey btnSubmit red" type="button" name="button" value="提交">
            </li>
        </ul>
    </section>
    
</div>

<div class="shop-part02 dsn">
    <header class="sectionBox fantasia-header order-top-bg">
        <a class="disblock mleft15 left" href="#"><img class="back-arrow" src="../images/back-arrow.png" /></a>
        <div class="header-title">选择服务小区</div>
       <!--  <a class="disblock p010 right white add-shop-area-btn" href="javascript:void(0)">添加小区</a> -->
    </header>
    <header class="sectionBox fantasia-header bggrey">
        <div class="header-title mtop5 displaybox shop-create-header"><input id="shopCreateSearchBtn" class="order-search boxflex01 shop-create-search" placeholder="请输入小区名称" type="text" name="search" /><div class="quick-delete dsn"></div></div>
    </header>
    <section class="sectionBox paddingMenu item-details-info pos-relative" style="padding-bottom:57px;">
    
        <li class="displaybox shop-create-check-single hide">
        	<span class="area-check-icon"></span>
            <div class="area-info-text boxflex01 borderbottomgrey">
                <span class="area-info-name f16" gbId="" >福年广场</span>
                <br><span class="area-info-address grey">福田保税区绒花路口</span>
            </div>
        </li>
        <ul class="register-list shop-create-check">
            
        </ul>        
        
        <ul class="bottom-menu-box t-center">
            <li class="p00 bordertopgrey area-add-list">
            	<ul class="list-box bgwhite server-area area-list">
                    <c:forEach items="${merchantSupply.groupBuildingList }" var="gb"> 
	                    <li class="newAreaName newArea${gb.id }">${gb.name }<input class="dsn newAreaInputA1"><div class="quick-delete"></div></li>
            		</c:forEach>
                </ul>
            </li>
            <li class="p00"><input class="btn-submit btn-next noradius bordertbgrey red area-add-btn" type="button" name="button" value="确定"></li>
        </ul>
        <li class="newAreaName dsn"><div class="quick-delete"></div></li>
	</section>
    
</div>

<div class="shop-part03 dsn">
    <header class="sectionBox fantasia-header order-top-bg">
        <a class="disblock mleft15 left" href="#"><img class="back-arrow" src="../images/back-arrow.png" /></a>
        <div class="header-title">添加小区</div>
        <a class="disblock p010 right white shop-area-create-btn" href="javascript:void(0)">确定</a>
    </header>
    
    <section class="sectionBox padding-menu item-details-info pos-relative">
        <section class="divide-box bordertbgrey"></section>
        <ul class="register-list borderbottomgrey">
            <li><input class="input-text wp100 area-name-input" type="text" placeholder="请输入小区名称" maxlength="15" /></li>
        </ul>
    </section>
</section>        

</form>

<div class="sectionBox loading grey hide"><img src="../images/merchant/loading01.gif" /> 加载中…</div>
<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
<script src="../js/merchant/Validform_v5.3.2.js"></script>
<script src="../js/merchant/shopcreate.js"></script>
<script src="../js/merchant/lrz.bundle.js"></script>
<script src="../js/jquery.form.js"></script>
<script>
$(function(){
	
	//表单验证
	$(".inputform").Validform({
		tiptype:1,
		btnSubmit:".btnSubmit",
		beforeSubmit:function(){
			$.Showmsg("正在更新资料…");
			getOldImages();
		},
		callback:function(){
			$("#inputform").ajaxSubmit({  
                success: function(data){
                	$.Showmsg(data.message);
    				setTimeout(function(){
    					window.location.href = "setting.html";
    				},1500);
                },  
                error: function(){  
                	$.Showmsg('网络不给力，请稍后重试'); 
                }  
            }); 
		}
	});
	
	//字数限制
	$('.textareas').keyup(function(){
		var valLength = $(this).val().length;
		var currentVal = $(this).val();
		if(valLength > 199){
			$(this).val(currentVal.substring(0,200));	
		}
		var num = 200 - valLength;
		$('.leftNum').text(num);
	});
	
	//添加小区
	$('.add-shop-area-btn').click(function(){
		$('.shop-part02').hide();
		$('.shop-part03').show();
	});
	
	$('.shop-area-create-btn').click(function(){
		
		var $areaList = $('.area-list');
		var $areaAddList = $('.area-add-list');
		var $paddingMenu = $('.paddingMenu');
		var $areaNameInput = $('.area-name-input');
		
		var $newAreaName = $('.newAreaName.dsn');
		var $newAreaNameClone = $newAreaName.clone(true);
		var areaCreateName = $.trim( $areaNameInput.val() );
		
		if(areaCreateName == ""){
			$.Showmsg('请输入小区名称！');
		}else{
			$newAreaNameClone.prepend(areaCreateName).removeClass('dsn').prependTo($areaList);
			$areaNameInput.val('');
			
			var thisLiLength = $('.shop-part02 .area-list').find('li').length;
			
			var areaAddListHeight = $areaAddList.height();
			$paddingMenu.css('padding-bottom', 57 + areaAddListHeight);
			$('.shop-area-select').val('已选择' + thisLiLength + '个小区');
			
			$('.shop-part01').hide();
			$('.shop-part03').hide();
			$('.shop-part02').show();
			$areaAddList.show();	
		}
	});
	
	//设置初始选中小区数
	var areaSelectNum = $('.area-list').eq(0).find('li').length;
	$('.shop-area-select').val('已选择' + areaSelectNum + '个小区');
	
	//新增上传图片前预览缩略图
	$(".uploadImage01").on("change", function() {
		
	var files = !!this.files ? this.files : [];
	var $this = $(this);
	var thisInputName = $this.attr('data-name');
	
    lrz(files[0], {
        //width: 800
    })
        .then(function (rst) {
			if (!files.length || !window.FileReader) return;
			if (files[0].size >= 10240000) {
				$.Showmsg('图片过大，应小于10M');
				return;
			}
			
			if (/^image/.test(files[0].type)) {
				var reader = new FileReader();
				reader.readAsDataURL(files[0]);
				reader.onloadend = function() {
					var $thisPreviewBox = $this.parent(".uploadPreview01");
					$thisPreviewBox.css("background-image", "url(" + rst.base64 + ")");
					$thisPreviewBox.siblings(".uploadImageVal01").val('1');
					if($thisPreviewBox.hasClass('old-img')){
						$thisPreviewBox.removeClass('old-img');
					}
					
					if($thisPreviewBox.find('.newImgUpLoad').length == 0){
						var $newInput = $('<input class="newImgUpLoad dsn" type="hidden" indexNum="11">');
						
						$newInput.attr('name', thisInputName).val(rst.base64);
						$newInput.prependTo($thisPreviewBox);
					}else{
						$thisPreviewBox.find('.newImgUpLoad').val(rst.base64);
					}
				}
			}
		});
	});
	
	
	//增加删除图片
	var $imgNewAdd = $('.img-new-add');
	var num = 0;
	$('.img-add-btn').click(function(){
		var dataName = $(this).attr('data-name');
		var newImgId = 'newImgId' + num;
		var imgLength = $(this).prevAll('.uploadPreview01:visible').length;
		if(imgLength == 2){
			$(this).addClass('hide');
		}
		$imgNewAdd.clone(true).removeClass('img-hide').insertBefore($(this)).find('input.uploadImage01').attr({'id':newImgId,'data-name':dataName});
		num += 1;
	});
	
	$('.uploadPreview01 .img-delete-btn').click(function(){
		$(this).parent('.uploadPreview01').siblings('.img-add-btn').removeClass('hide');
		$(this).parent('.uploadPreview01').remove();
	});
	
	//获取旧图片
	function getOldImages(){
		$('.old-img').each(function(){
			var $this = $(this);
			var thisType = $this.find('input[type=file]').attr('data-name');
			var oldImgUrl = $this.css("background-image");
			oldImgUrl = oldImgUrl.match(/(?!\/)\w*.jpg/);
			var $newInput;
			if(thisType === 'storeImage'){
				$newInput = $('<input class="dsn" type="hidden" name="storeImageOld">');
			}else{
				$newInput = $('<input class="dsn" type="hidden" name="blImageOld">');
			}
			$newInput.val(oldImgUrl).appendTo($this);
		})
	}
	
});

//省市切换
function onSelectChange(obj,toSelId){
	setSelect(obj.value,toSelId);
}
/**
 * 清空选择的小区
 */
function clearSelectedGroupBuilding(){
	$('.area-add-list').hide();
	$('ul.list-box.bgwhite.server-area.area-list').html("");
	$('.shop-area-select').val("");
}
//省市切换详情逻辑处理
function setSelect(fromSelVal,toSelId){
	document.getElementById(toSelId).innerHTML="";//清空之前的选项
	clearSelectedGroupBuilding();
	if(fromSelVal){
		if(toSelId === "city"){//选择省，更新市
			jQuery.ajax({
				  url: "../propertyCompany/getCityList.html",
				  cache: false,
				  dataType:"json",
				  async:false,
				  data:"apId="+fromSelVal,
				  success: function(data){
				    $.each(JSON.parse(data), function(i, item) {
				    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
				    });
				  },  
	              error: function(){  
	              	$.Showmsg('网络不给力，请稍后重试'); 
	              } 
			});
			$("#city").prepend('<option value="" selected>选择城市</option>');
			$("#block").html('<option value="" selected>选择区</option>');
		}else {
			jQuery.ajax({//选择市，更新区
				  url: "../propertyCompany/getBlockList.html",
				  cache: false,
				  dataType:"json",
				  data:"acId="+fromSelVal,
				  success: function(data){
				    $.each(JSON.parse(data), function(i, item) {
				    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
				    });
				  },  
	              error: function(){  
	              	$.Showmsg('网络不给力，请稍后重试'); 
	              } 
			});
			$("#block").prepend('<option value="" selected>选择区</option>');
		}
	}else{
		$("#block").html('<option value="" selected>选择区</option>');
	}
}
	
</script>
</body>
</html>