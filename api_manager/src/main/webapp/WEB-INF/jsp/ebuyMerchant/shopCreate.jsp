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

<title>创建店铺</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css?v20170217" type="text/css">
</head>

<body class="pos-relative">
<form class="inputform" enctype="multipart/form-data" action="saveShopBusinessInfo.json" method="post" onsubmit="return false;">
<div class="shop-part01">
    <header class="sectionBox fantasia-header order-top-bg">
        <a class="disblock mleft15 left" href="#"><img class="back-arrow" src="../images/back-arrow.png" /></a>
        <div class="header-title">创建店铺</div>
    </header>
    
    <section class="sectionBox padding-menu item-details-info pos-relative">
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">店铺名称</div>
        </section>
        <ul class="register-list">
            <li><input class="input-text wp100" name="shopName" type="text" placeholder="请输入店铺名称" maxlength="20" datatype="*" nullmsg="请输入店铺名称！" /></li>
        </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">联系人</div>
        </section>
        <ul class="register-list">
            <li><input class="input-text wp100" name="linkName" type="text" placeholder="请输入联系人" maxlength="20" datatype="*" nullmsg="请输入联系人！" /></li>
        </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">客服电话</div>
        </section>
        <ul class="register-list">
            <li><input class="input-text wp100" name="linkPhone" type="text" maxlength="15" placeholder="请输入客服电话" datatype="m|phoneNumber" nullmsg="请输入客服电话！" errormsg="请输入正确的手机或座机号码！" /></li>
        </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">店铺地址</div>
        </section>
        <ul class="register-list">
        	  <li class="borderbottomgrey">
   					<select id="province" onchange="onSelectChange(this,'city');" class="province select_normal input-text wp100 list-arrow" data-first-title="选择省" title="选择省"  datatype="*" nullmsg="请完善店铺地址！" >
	                   	<option value="">选择省</option>
	                   	<c:forEach items="${pcbList}" var="pcb" >
	                   		<option value="${pcb.id}">${pcb.name}</option>
                   		</c:forEach>
                   </select> 
        	  </li>
	                       
	                        
            <li class="borderbottomgrey">
            	<select id="city" onchange="onSelectChange(this,'block');"  class="input-text wp100 list-arrow" datatype="*" nullmsg="请完善店铺地址！">
                      	<option value="">选择城市</option>
                </select> 
            </li>
            <li class="borderbottomgrey">
             	<select  id="block" name="block" class="input-text wp100 list-arrow" datatype="*" nullmsg="请完善店铺地址！" onchange="clearSelectedGroupBuilding();">
	                <option value="">选择区</option>
	            </select>
            </li>
            <li><input class="input-text wp100" name="shopAddress" type="text" placeholder="请填写店铺具体地址" maxlength="50" datatype="*" nullmsg="请填写店铺具体地址！" /></li>
        </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">服务范围</div>
        </section>
        <ul class="register-list">
            <li>
                <input class="input-text wp100 list-arrow shop-area-select" type="text" readonly placeholder="请选择可派送的周边服务小区" datatype="*" nullmsg="请选择可派送的周边服务小区！" />
            </li>
            <li class="p00 bordertopgrey area-add-list dsn">
                <ul class="list-box bgwhite server-area area-list">
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
                <div class="uploadPreview01"><input type="file" data-imgName="storeImage" class="uploadImage01 fimg1 w100 height100" accept="image/gif,image/jpeg,image/jpg,image/png" /></div>
                <div class="uploadPreview01 img-add-btn" data-name="storeImage"></div>
                <input class="uploadImageVal01" hidden="true" type="text" name="text" datatype="*" nullmsg="请上传店铺照片！">
            </li>
            <li class="img-add-num grey">注：不超过10M，最多可上传3张</li>
        </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">营业执照</div>
        </section>
        <ul class="register-list">
            <li class="overvisible">
                <div class="uploadPreview01"><input type="file" data-imgName="blImage" class="uploadImage01 fimg1 w100 height100" /></div>
                <div class="uploadPreview01 img-new-add img-hide"><div class="img-delete-btn">删除</div><input type="file" name="" class="uploadImage01 fimg1 w100 height100" accept="image/gif,image/jpeg,image/jpg,image/png" /></div>
                <div class="uploadPreview01 img-add-btn" data-name="blImage"></div>
                <input class="uploadImageVal01" hidden="true" type="text" name="text" datatype="*" nullmsg="请上传营业执照！">
            </li>
            <li class="img-add-num grey">注：不超过10M，最多可上传3张</li>
        </ul>
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">店铺介绍</div>
        </section>
        <ul class="register-list">
            <li class="grey" style="margin: 0 25px;padding: 10px 0;overflow: visible;"><textarea name="introduce" class="area-text wp100 textareas" style="margin-left: -10px;" rows="5" type="text" placeholder="跟客官们介绍下你的店铺吧" datatype="*" nullmsg="请填写店铺介绍！" ></textarea><br />还可以输入<span class="leftNum">100</span>字</li>
        </ul> 
        <section class="divide-box bordertbgrey"></section>
        <ul class="bottom-menu-box displaybox t-center">
            <li class="p00"><div class="btn-submit btn-next noradius bordertbgrey btnSubmit red"><div class="create-btn-text">还差一步就可以开店咯<br><span class="grey f14">完善运费设置</span></div></div></li>
        </ul>
    </section>
    
</div>

<div class="shop-part02 dsn">
    <header class="sectionBox fantasia-header order-top-bg">
        <div class="header-title">选择服务小区</div>
        <!-- <a class="disblock p010 right white add-shop-area-btn" href="javascript:void(0)">添加小区</a> -->
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
            <li class="p00 bordertopgrey area-add-list dsn">
                <ul class="list-box bgwhite server-area area-list">
                </ul>
            </li>
            <li class="p00"><input class="btn-submit btn-next noradius bordertbgrey red area-add-btn" type="button" name="button" value="确定"></li>
        </ul>
        <li class="newAreaName dsn"><div class="quick-delete"></div></li>
	</section>
    
</div>

<div class="shop-part03 dsn">
    <header class="sectionBox fantasia-header order-top-bg">
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
</div>
</form>
<section class="pop-tips3 upload-text hide">
	<div class="pop-tips-text">信息提交中，请稍候 (<span class="uploadPercent"></span>)</div>
</section>
<div class="sectionBox loading grey hide"><img src="../images/merchant/loading01.gif" /> 加载中…</div>
<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script src="../js/jquery.form.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
<script src="../js/merchant/Validform_v5.3.2.js"></script>
<script src="../js/merchant/shopcreate.js?v20170217"></script>
<script src="../js/merchant/lrz.bundle.js"></script>
<script src="../js/merchant/imgUploadResize.js"></script>
<script>
$(function(){
	
	//表单验证
/* 	$(".inputform").Validform({
		tiptype:1,
		btnSubmit:".btnSubmit",
		beforeSubmit:function(){
			$(".upload-text").appendTo('body').removeClass("hide");
			$(".btnSubmit").removeClass("red").addClass("grey");
		}
	}); */
	$(".inputform").Validform({
		tiptype:1,
		postonce:false,
		btnSubmit:".btnSubmit", 
		beforeSubmit:function(){
			if($(".btnSubmit").attr('hasSubmit') === 'true'){
				return false;
			}
			$(".upload-text").removeClass("hide");
			$(".btnSubmit").removeClass("red").addClass("grey").attr('hasSubmit','true');
		},
		callback:function(data){
			$(".inputform").ajaxSubmit({ 
				uploadProgress: function(event, position, total, percentComplete) {//上传的过程
	                //position 已上传了多少
	                //total 总大小
	                //已上传的百分数
                	var percentVal = ((percentComplete - 1) < 0 ? 0 : (percentComplete - 1)) + '%';
	                $('.uploadPercent').text(percentVal);
	            },
                success: function(data){  
	                $('.uploadPercent').text('100%');   
					$('.upload-text').addClass('hide');
					location.href = "toPage.html?page=settingFreight";
                },  
                error: function(){     
					$('.upload-text').addClass('hide');
    				$('.btnSubmit').removeClass("grey").addClass("red").removeAttr('hasSubmit');
                	$.Showmsg('网络不给力，请稍后重试'); 
                }  
            }); 
		}
	});
	
});

$(function(){

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