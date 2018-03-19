<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <base href="<%=basePath%>//"/>
    <title>片区新增</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>

<body>
<div class="info">
    <form class="inputform" action="<%=basePath%>/district/addDistrict.json" method="post">
        <h2>片区新增</h2>
        <input type="hidden" name="id" value="${param.id }" />
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="search-input01 swap-info swap-val-2">
            <td width="20%"><div class="list-name"><span class="red">*</span> 片区名称：</div></td>
            <td>
            	<input type="text" maxlength="20" name="name" class="input_text w300" datatype="*" placeholder="请输入片区名称" />
            </td>
          </tr>
          <tr class="search-input01 swap-info swap-val-2">
            <td><div class="list-name"><span class="red">*</span> 片区负责人：</div></td>
            <td>
            	<input type="text" maxlength="25" name="director" class="input_text w300" datatype="*" placeholder="请输入片区负责人" />
            </td>
          </tr>
          <tr class="search-input01 swap-info swap-val-2">
            <td><div class="list-name"><span class="red">*</span> 负责人电话：</div></td>
            <td>
            	<input type="text" maxlength="25" name="directorPhone" class="input_text w300" datatype="*" placeholder="请输入负责人电话" />
            </td>
          </tr>
          <tr class="search-input01 swap-info swap-val-2">
            <td><div class="list-name"><span class="red">*</span> 负责人邮件：</div></td>
            <td>
            	<input type="text" maxlength="80" name="directorEmail" class="input_text w300" datatype="*" placeholder="请输入负责人邮件" />
            </td>
          </tr>
        </table>

        <input id="sumTicket" style="margin:50px;" class="info-btn save-set-prize-info-btn" type="submit" value="保存" />
        <input id="sumTicket2" style="margin-left:50px;" class="info-btn save-set-prize-info-btn" type="button" value="返回" />
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
					alert("片区新增成功！");
					window.location.href = "<%=basePath%>/district/districtList.html";
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
			$addressSelectedLiClone01.append('<input type="hidden" name="userIds" value="'+id+'">');
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
				 searchList += '<li><span class="address-name" data-itemid="'+aaList.id+'">' + aaList.account + '</span><br><span class="grey">ID：<span class="data-obj-id">' + aaList.id + '</span></span></li>';
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
		var url = '<%=basePath%>/district/getOmsUserList.json?qryStr=' + encodeURI(currentVal, "utf-8");
		if(!currentVal == ''){
			ajaxSearch01(url,thisSearchBox);
		}
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
	
	
	
	$("#sumTicket2").click(function(){//新增
		var href = "<%=basePath%>/district/districtList.html";
		$(window.parent.document).find("#mainFrame").attr("src", href);
	});
});

</script>
</html>
