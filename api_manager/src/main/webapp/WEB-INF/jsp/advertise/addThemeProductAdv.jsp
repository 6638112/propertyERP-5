<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <base href="<%=basePath%>//"/>
    <title>新增推广商品广告</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <form class="inputform" action="<%=basePath%>/adv/addThemeProductAdv.html" method="post">
        <h2>新增推广商品广告</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 广告名称：</div></td>
            <td><input type="text" name="ebuyAdvertise.tittle" class="input_text" maxlength="20" datatype="*" placeholder="请填写广告名称" nullmsg="请填写广告名称！"></td>
          </tr>
			<tr>
				<td><div class="list-name"><span class="red">*</span> 广告类型：</div></td>
				<td><input type="radio" name="advType" value="1" data-val="1" datatype="*" nullmsg="请选择广告类型！"> 超市首页
					<input class="mleft20" type="radio" name="advType" value="3" data-val="3" datatype="*" nullmsg="请选择广告类型！"> 轻应用首页
					<input class="mleft20" type="radio" name="advType" value="4" data-val="4" datatype="*" nullmsg="请选择广告类型！"> 首页弹框
                    <input class="mleft20" type="radio" name="advType" value="5" data-val="5" datatype="*" nullmsg="请选择广告类型！"> 社区店主题活动
                    <input class="mleft20" type="radio" name="advType" value="6" data-val="6" datatype="*" nullmsg="请选择广告类型！"> 到家主题活动
				</td>
			</tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 推广时间：</div></td>
            <td>
				<input type="text" name="ebuyAdvertise.startTime" class="input_text icon_dt" id="date05" placeholder="请选择起始时间" datatype="dateTime" nullmsg="请选择起始时间！"> 至
				<input type="text" name="ebuyAdvertise.endTime" class="input_text icon_dt" id="date06" placeholder="请选择结束时间" datatype="dateTime" nullmsg="请选择结束时间！">
			</td>
          </tr>
            <tr class="swap-con swap-val-5 swap-val-6">
                <td><div class="list-name"><span class="red">*</span> 活动说明：</div></td>
                <td>
                    <input type="text" name="ebuyAdvertise.desc" class="input_text" maxlength="40" datatype="*" placeholder="请填写活动说明" nullmsg="请填写活动说明！">
                </td>
            </tr>
			<tr>
				<td><div class="list-name">弹框频率：</div></td>
				<td>
					<select name="ebuyAdvertise.frequency" class="select_normal">
						<option value="">选择弹框频率</option>
						<option value="1">一天一次</option>
						<option value="2">点击查看后不再弹</option>
					</select>
				</td>
			</tr>
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
          <tr>
            <td align="right"><span class="red">*</span> 广告图片：</td>
            <td class="item-upload-img">
                <div class="uploadPreview01 mright6">
					<input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage02" />
					<img class="imgPreview" width="82" height="82" src="images/addimg01.jpg" />
				</div>
                <span class="f12">注：建议尺寸640*200，小于200k，仅限一张。</span>
                <input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请上传商品图片！" />
            </td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 用户范围：</div></td>
            <td>
                <select id="areaType" name="areaType" class="select_normal addressAreaSelect" datatype="*" nullmsg="请选择用户范围！">
                    <option value="">选择范围</option>
                    <option value="1">全国范围</option>
                    <option value="2">城市</option>
                    <option value="3">小区</option>
                </select>
            </td>
          </tr>
          <tr class="search-input dsn">
            <td></td>
            <td>
            	<input type="text" maxlength="8" class="input_text w120" placeholder="请输入关键字" />
				<input class="input-btn common-search-btn" type="button" value="搜索">
            </td>
          </tr>
          <tr class="city-con dsn">
            <td><div class="list-name">搜索结果：</div></td>
            <td>
            	<ul class="address-list search-box">
            	</ul>
            </td>
          </tr>
          <tr class="city-con dsn">
            <td><div class="list-name">已选城市：</div></td>
            <td>
            	<ul class="address-list selected-box">
            		<li class="posrelative address-selected dsn"><span class="address-name">招东小区</span><div class="icon-delete"></div></li>
            	</ul>
            	<input type="hidden" class="select-input input_text w80 dsn" datatype="*" nullmsg="请选择城市！" />
            </td>
          </tr>
          <tr class="area-con dsn">
            <td><div class="list-name">搜索结果：</div></td>
            <td>
            	<ul class="address-list search-box">
            	</ul>
            </td>
          </tr>
          <tr class="area-con dsn">
            <td><div class="list-name">已选小区：</div></td>
            <td>
            	<ul class="address-list selected-box">

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
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/jquery.common.js?v20170728"></script>
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

	//校验时间先后顺序
	$('.input_text.icon_dt').compareTime('#date05', '#date06');

})(jQuery);
</script>
<script type="text/javascript">

$(function(){
	//主题活动类型才显示活动说明字段
	radioChange('[name=advType]');
	
	//选择跳转类型
	selectOptionChange('#jumpPageType');
	
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
				var listItemId = $(this).find('.data-obj-id').text();
				if(listItemId === thisId){
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
			if (data.dataValue != null) {
                $.each(data.dataValue.list, function (i, aaList) {
                    //搜索
                    searchList += '<li><span class="address-name" data-itemid="'+aaList.dpId+'">' + aaList.prdtName + '</span><br><span class="grey">ID：<span class="data-obj-id">' + aaList.dpId + '</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">'+aaList.ssName+'</span></span></li>';
                });
            } else {
                $.each(data, function (i, aaList) {
                    //搜索
                    searchList += '<li><span class="address-name" data-itemid="'+aaList.shelfId+'">' + aaList.productName + '</span><br><span class="grey">ID：<span class="data-obj-id">' + aaList.shelfId + '</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">'+aaList.merchantName+'</span></span></li>';
                });
            }


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
		var appType = $('input[name="advType"]:checked').val();
		if( appType == undefined && !currentVal == ''){
			$(".inputform").Validform({}).check(false, $('input[name="advType"]'));
		}
		if(appType == 4) {
			appType = 1;
		} else if (appType == 5) {
            appType = '';
        }
		var url = '<%=basePath%>/adv/getShelfProductForAdv.json?qryStr=' + encodeURI(currentVal, "utf-8") + '&appType=' + appType;
		if (appType == 6) {
            url = "<%=basePath%>/dredge/productList.json?upShelfState=1&dpName=" + encodeURI(currentVal, "utf-8");
        }
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
	

	//选择用户范围
	$('.addressAreaSelect').change(function(){
		var curVal = $(this).val();
		if(curVal == '' || curVal == 1){
			$('.area-con,.search-input').hide();
			$('.city-con,.search-input').hide();
			$('.area-con, .city-con').find('input').attr('ignore', 'ignore');
			selectCountNum = 0;
		}else if(curVal == 2){
			$('.area-con').hide();
			$('.city-con,.search-input').show();
			$('.city-con').find('input').attr('ignore', '');
			$('.area-con').find('input').attr('ignore', 'ignore');
			//获取已选城市个数
			var curNum = $('.address-list.selected-box:visible li:visible').length;
			if( curNum == ''){
				selectCountNum = 0;
			}else{
				selectCountNum = curNum;
			}

		}else if(curVal == 3){
			$('.city-con').hide();
			$('.area-con,.search-input').show();
			$('.area-con').find('input').attr('ignore', '');
			$('.city-con').find('input').attr('ignore', 'ignore');
			//获取已选小区个数
			var curNum = $('.address-list.selected-box:visible li:visible').length;
			if( curNum == ''){
				selectCountNum = 0;
			}else{
				selectCountNum = curNum;
			}
		}
		//重置页面高度
		window.parent.TuneHeight();
	});
	//选择使用条件
	$('.prizeLimitInfo').change(function(){
		var curVal = $(this).val();
		if(curVal == 2){
			$('.use-limit-info').show();
			$('.use-limit-info').find('input').attr('ignore', '');
		}else{
			$('.use-limit-info').hide();
			$('.use-limit-info').find('input').attr('ignore', 'ignore');
		}
		//重置页面高度
		window.parent.TuneHeight();
	});

	//选择城市/小区
	var $addressSelectedLi = $('.address-selected.dsn');
	var addressSelectedNum = 0;
	var selectCountNum = 0;

	$(document).on("click", '.address-list.search-box li:visible', function(){
		var $this = $(this);
		//选择
		if(!$this.hasClass('on')){
			var addressSelectedClass = 'address-selected-';
			var $addressSelectedLiClone = $addressSelectedLi.clone(true);
			var thisName = $this.find('.address-name').text();
			var id = $this.find('span').attr('data-itemid');

			//防止重复选择
			/* var areaSelectedNum = 0;
			$('.address-list.selected-box li:visible').each(function(){
				var thisAreaName = $(this).find('.address-name').text();
				if(thisAreaName === thisName){
					areaSelectedNum += 1;
				}
			})
			if(areaSelectedNum > 0){
				alert('请勿重复选择！');
				return false;
			} */

			addressSelectedNum += 1;
			selectCountNum += 1;
			//已选城市/小区，校验通过
			if(selectCountNum > 0){
				$('.address-list:visible').siblings('.select-input').val(selectCountNum);
				$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input'));
			}

			addressSelectedClass = addressSelectedClass + addressSelectedNum;


			$this.addClass('on ' + addressSelectedClass).attr('data-class',addressSelectedClass);
			$addressSelectedLiClone.find('.address-name').text(thisName);
			var areaType = $('#areaType').val();
			if (areaType == 2) {
				$addressSelectedLiClone.append('<input type="hidden" name="cityIds" value="'+id+'">');
			} else if(areaType = 3){
				$addressSelectedLiClone.append('<input type="hidden" name="gbIds" value="'+id+'">');
			}
			$addressSelectedLiClone.removeClass('dsn').addClass(addressSelectedClass).attr('data-class',addressSelectedClass).appendTo('.selected-box:visible');
		//反选
		}else{
			var thisUnSelectedClass = $this.attr('data-class');
			$this.removeClass();
			$('.selected-box').find('.' + thisUnSelectedClass).remove();
			selectCountNum -= 1;
			//没有选择城市/小区，校验不通过
			if(selectCountNum == 0){
				$('.address-list:visible').siblings('.select-input').val('');
				$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input'));
			}
		}
		//重置页面高度
		window.parent.TuneHeight();
	})

	//删除城市/小区
	$(document).on("click", '.address-list.selected-box li .icon-delete', function(){
		var $this = $(this);
		var thisDeleteClass = $this.parent('li').attr('data-class');
		if($('.' + thisDeleteClass)){
			$('.' + thisDeleteClass).removeClass('on');
		}
		$this.parent('li').remove();

		selectCountNum -= 1;
		//没有选择城市/小区，校验不通过
		if(selectCountNum == 0){
			$('.address-list:visible').siblings('.select-input').val('');
			$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input'));
		}
		//重置页面高度
		window.parent.TuneHeight();
	});

	var $searchList = $('.search-list-con.dsn');
	//搜索内容
	function ajaxSearch(url,objBox){
		$.getJSON(url, function(data){

			var $objBox = $(objBox);
			var searchList = '';

			var $searchListClone = $searchList.clone(true);
			$.each(data, function(i, ssList){
				//小区搜索，带详细地址
				if(ssList.addressDesc){
					if(ssList.signStatus == 1){
						searchList += '<li><span class="address-name red" data-itemid="'+ssList.id+'">' + ssList.name + '</span><br><span class="grey">地址：' + ssList.addressDesc + '</span></li>';
					}else{
						searchList += '<li><span class="address-name" data-itemid="'+ssList.id+'">' + ssList.name + '</span><br><span class="grey">地址：' + ssList.addressDesc + '</span></li>';
					}
					//城市搜索，不带详细地址
				}else if(ssList.name.indexOf(currentVal) > -1){
					searchList += '<li><span class="address-name" data-itemid="'+ssList.id+'">' + ssList.name + '</span></li>';
				}
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
	$('.common-search-btn').click(function(){
		var thisSearchBox = $('.search-box:visible');
		currentVal = $.trim($('.search-input input.input_text').val());
		var areaType = $('#areaType').val();
		var url;
		if(areaType == 2){
			url = '<%=basePath%>/addressCity/getAddressCityIdByName.json?name=' + encodeURI(currentVal, "utf-8");
		} else if(areaType == 3){
			url = '<%=basePath%>/groupBuildingJson/getBuildingListByNameAndCityId.json?name=' + encodeURI(currentVal, "utf-8");
		}
		if(!currentVal == ''){
			ajaxSearch(url,thisSearchBox);
		}
	});
});

</script>
</html>
