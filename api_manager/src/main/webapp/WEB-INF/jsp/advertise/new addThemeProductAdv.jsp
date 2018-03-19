<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <base href="<%=basePath%>//"/>
    <title>选择片区经理</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>

<body>
<div class="info">
    <form class="inputform" action="<%=basePath%>/adv/addThemeProductAdv.html" method="post">
        <h2>选择片区经理</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="search-input01 swap-info swap-val-2">
            <td><div class="list-name"><span class="red">*</span> 推广商品：</div></td>
            <td>
            	<input type="text" maxlength="8" class="input_text w120" placeholder="请输入关键字" />
				<input class="input-btn item-search-btn" type="button" value="搜索">
            </td>
          </tr>
          <tr class="city-con01 swap-info swap-val-2">
            <td><div class="list-name">搜索结果：</div></td>
            <td>
            	<ul class="address-list search-box01">
            	</ul>
            </td>
          </tr>
          <tr class="city-con01 swap-info swap-val-2">
            <td><div class="list-name">已选商品：</div></td>
            <td>
           		<li class="posrelative address-selected01 dsn">
           			<span class="address-name"></span><br>
           			<span class="grey">ID：<span class="data-obj-id"></span></span><span class="grey mleft10">供应商：<span class="data-obj-name">海吉星</span></span>
           			<div class="icon-delete"></div>
           		</li>
            	<ul class="address-list selected-box01">
            	</ul>
            	<input type="hidden" class="select-input01 input_text w80 dsn" datatype="*" nullmsg="请选择商品！" />
            </td>
          </tr>
          
          
          <tr class="search-input">
            <td width="20%"><div class="list-name"><span class="red">*</span> 用户范围：</div></td>
            <td>
            	<input type="text" maxlength="8" class="input_text w120" placeholder="请输入关键字" />
				<input class="input-btn common-search-btn" type="button" value="搜索">
            </td>
          </tr>
          <tr class="area-con">
            <td><div class="list-name">搜索结果：</div></td>
            <td>
            	<ul class="address-list search-box">
            	</ul>
            </td>
          </tr>
          <tr class="area-con">
            <td><div class="list-name">已选小区：</div></td>
            <td>
            	<ul class="address-list selected-box">
					<li class="posrelative address-selected dsn"><span class="address-name">招东小区</span><div class="icon-delete"></div></li>
            	</ul>
            	<input type="hidden" class="select-input input_text w80 dsn" datatype="*" nullmsg="请选择小区！" />
            </td>
          </tr>
        </table>

        <input id="sumTicket" class="info-btn save-set-prize-info-btn" type="submit" value="发布" />
        <div class="h30"></div>
    </form>
</div>

</body>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.common.js?v20160726"></script>
<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery.itemScreen.js"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		btnSubmit:"#sumTicket",
		tiptype:3,
		beforeSubmit: function (curform) {
			$(".inputform").attr('onsubmit', 'return false;');
		},
		callback: function (data) {
			$(".inputform").ajaxSubmit(function (data) {
				try {
					data = eval(data);
				} catch (e) {
					data = eval("(" + data + ")");
				}
				if (data.status == '0000') {
					alert("广告添加成功！");
					window.location.href = "<%=basePath%>/adv/themeProductAdvList.html";
				} else {
					alert(data.message);
					$(".inputform").Validform().resetStatus();
				}
			});
		}
	});

})(jQuery);
</script>
<script type="text/javascript">

$(function(){
	
 	//选择商品
	var $addressSelectedLi01 = $('.address-selected01.dsn');
	var addressSelectedNum01 = 0;
	var selectCountNum01 = 0;

	$(document).on("click", '.address-list.search-box01 li:visible', function(){
		var $this = $(this);
		//选择
		if(!$this.hasClass('on')){
			var addressSelectedClass = 'address-selected01-';
			var $addressSelectedLiClone01 = $addressSelectedLi01.clone(true);
			var thisName = $this.find('.address-name').text();
			var thisId = $this.find('.data-obj-id').text();
			var thisSupplierName = $this.find('.data-obj-name').text();
			var id = $this.find('span').attr('data-itemid');
			//防止重复选择
			var areaSelectedNum = 0;
			$('.address-list.selected-box01 li:visible').each(function(){
				var thisAreaName = $(this).find('.address-name').text();
				if(thisAreaName === thisName){
					areaSelectedNum += 1;
				}
			})
			if(areaSelectedNum > 0){
				alert('请勿重复选择！');
				return false;
			}

			addressSelectedNum01 += 1;
			selectCountNum01 += 1;

			//已选，校验通过
			$('.address-list:visible').siblings('.select-input01').val(selectCountNum01);
			$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input01'));

			addressSelectedClass = addressSelectedClass + addressSelectedNum01;

			$this.addClass('on ' + addressSelectedClass).attr('data-class',addressSelectedClass);
			$addressSelectedLiClone01.find('.address-name').text(thisName);
			$addressSelectedLiClone01.find('.data-obj-id').text(thisId);
			$addressSelectedLiClone01.find('.data-obj-name').text(thisSupplierName);
			$addressSelectedLiClone01.append('<input type="hidden" name="shelfIds" value="'+id+'">');
			$addressSelectedLiClone01.removeClass('dsn').addClass(addressSelectedClass).attr('data-class',addressSelectedClass).appendTo('.selected-box01:visible');
		//反选
		}else{
			var thisUnSelectedClass = $this.attr('data-class');
			$this.removeClass();
			$('.selected-box01').find('.' + thisUnSelectedClass).remove();
			selectCountNum01 -= 1;
			//没有选择，校验不通过
			if(selectCountNum01 == 0){
				$('.address-list:visible').siblings('.select-input01').val('');
				$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input01'));
			}
		}
		//重置页面高度
		window.parent.TuneHeight();
	})

	//删除
	$(document).on("click", '.address-list.selected-box01 li .icon-delete', function(){
		var $this = $(this);
		var thisDeleteClass = $this.parent('li').attr('data-class');
		if($('.' + thisDeleteClass)){
			$('.' + thisDeleteClass).removeClass('on');
		}
		$this.parent('li').remove();

		selectCountNum01 -= 1;
		//没有选择，校验不通过
		if(selectCountNum01 == 0){
			$('.address-list:visible').siblings('.select-input01').val('');
			$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input01'));
		}
		//重置页面高度
		window.parent.TuneHeight();
	});


	var $searchList = $('.search-list-con.dsn');
	//搜索内容
	function ajaxSearch01(url,objBox){
		$.getJSON(url, function(data){

			var $objBox = $(objBox);
			var searchList = '';

			var $searchListClone = $searchList.clone(true);
			 $.each(data, function (i, aaList) {
                //搜索
				 searchList += '<li><span class="address-name" data-itemid="'+aaList.shelfId+'">' + aaList.productName + '</span><br><span class="grey">ID：<span class="data-obj-id">' + aaList.productId + '</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">'+aaList.merchantName+'</span></span></li>';
            });

			if(searchList == ''){
				searchList = '<span>' + '没有找到相关内容' + '</span>';
			}
			$objBox.html(searchList);
			//重置页面高度
			window.parent.TuneHeight();
		});
	}

	//开始搜索
	$('.item-search-btn').click(function(){
		var thisSearchBox = $('.search-box01:visible');
		currentVal = $.trim($('.search-input01 input.input_text').val());
		var appType = 2;	//$('input[name="advType"]:checked').val();
		if( appType == undefined && !currentVal == ''){
			$(".inputform").Validform({}).check(false, $('input[name="advType"]'));
		}
		if(appType == 4) {
			appType = 1;
		}
		var url = '<%=basePath%>/adv/getShelfProductForAdv.json?qryStr=' + encodeURI(currentVal, "utf-8") + '&appType=' + appType;
		if(!currentVal == ''){
			ajaxSearch01(url,thisSearchBox);
		}
	});
	
	//切换广告类型
	$('input[name="advType"]').change(function(){
		var $selectInput01 = $('.address-list.selected-box01').siblings('.select-input01');
		$('.address-list.search-box01, .address-list.selected-box01').html('');
		$selectInput01.val('');
		$('.address-list.selected-box01').siblings('.Validform_checktip').removeClass('Validform_right');
		
	});
	
	
/////////////////////////////////////////////////////////////////////////////
	
	//选择城市、小区
	var itemScreenSwap = $.itemScreenStart({	
		//选择配置项
		selectObj: '.address-list.search-box li:visible',	//要选择的对象
		deleteObj: '.address-list.selected-box li .icon-delete',	//要删除的对象
		itemsSelected: '.address-list.selected-box:visible li:visible',		//已选对象
		itemDemo: '.address-selected.dsn',	//用来克隆的对象		
		itemDemoBox: '.address-list.selected-box:visible',	//用来插入克隆的容器对象	
		validInput: '.select-input',	//校验已选数量
	});
	
	//选择、反选、删除
	itemScreenSwap.itemSelectAndDelete();

	$('.common-search-btn').click(function(){
		var thisSearchBox = $('.search-box:visible');
		currentVal = $.trim($('.search-input input.input_text').val());
		var areaType = 3;	//$('#areaType').val();
		var url = '<%=basePath%>/groupBuildingJson/getBuildingListByNameAndCityId.json?name=' + encodeURI(currentVal, "utf-8");
		if(!currentVal == ''){
			itemScreenSwap.ajaxSearch(url,thisSearchBox);
		}
	});
});

</script>
</html>
