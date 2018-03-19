<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>活动编辑</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <form class="inputform" enctype="multipart/form-data" action="${pageContext.request.contextPath}/activity/updateActivity.html" method="post">
    	<input type="hidden" name="ebuyAdvertise.id" value="${ebuyAdvertise.id}"/>
        <h2>活动编辑</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 活动名称：</div></td>
            <td>${ebuyAdvertise.tittle}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 活动链接：</div></td>
            <td>
            	${ebuyAdvertise.linkUrl}
            </td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 优先级：</div></td>
            <td>
                <input id="order" name="ebuyAdvertise.order" value="${ebuyAdvertise.order}" style="color:#FF0000;" class="input_text pp w250" maxlength="10" datatype="n" placeholder="请填写优先级" nullmsg="请填写优先级！"/>
            </td>
          </tr>  
          <tr>
            <td align="right"><span class="red">*</span> app显示图片：</td>
            <td class="item-upload-img">
                <div class="uploadPreview01 mright6"><input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="appPicFile" class="uploadImage02"/><img class="imgPreview" width="82" height="82" src="${basePicPath}${appPic}" /></div>
                <span class="f12">注：建议小于200k，仅限一张。</span>
                <input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请上传图片！" <c:if test="${not empty appPic}">ignore="ignore"</c:if>/>
                <input type="hidden" name="appPic" value="${appPic}"/>
            </td>
          </tr>
          <tr>
            <td align="right"><span class="red">*</span> 预告图片：</td>
            <td class="item-upload-img">
                <div class="uploadPreview01 mright6"><input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="previewPicFile" class="uploadImage02" /><img class="imgPreview" width="82" height="82" src="${basePicPath}${previewPic}" /></div>
                <span class="f12">注：建议小于200k，仅限一张。</span>
                <input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请上传图片！" <c:if test="${not empty appPic}">ignore="ignore"</c:if>/>
                <input type="hidden" name="previewPic" value="${previewPic}"/>
            </td>
          </tr> 
        </table>
          
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">  
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 用户范围：</div></td>
            <td>
                <select id="areaType" name="areaType" class="select_normal addressAreaSelect" datatype="*" nullmsg="请选择用户范围！">
                    <option value="">选择用户范围</option>
                    <option value="1" <c:if test="${areaType == 1}">selected</c:if>>全国范围</option>
                    <option value="2" <c:if test="${areaType == 2}">selected</c:if>>城市</option>
                    <option value="3" <c:if test="${areaType == 3}">selected</c:if>>小区</option>
                </select>
            </td>
          </tr>
          <tr class="search-input swap-con swap-box-common <c:if test="${(empty areaType) or (areaType eq 1)}">dsn</c:if>">
            <td></td>
            <td>
            	<input type="text" maxlength="8" class="input_text w120" placeholder="请输入关键字" />
				<input class="input-btn common-search-btn" type="button" value="搜索">
            </td>
          </tr>
          <tr class="city-con swap-con swap-box-2 <c:if test="${areaType != 2}">dsn</c:if>">
            <td><div class="list-name">搜索结果：</div></td>
            <td>
            	<ul class="address-list search-box">
            	</ul>
            </td>
          </tr>
          <tr class="city-con swap-con swap-box-2 <c:if test="${areaType != 2}">dsn</c:if>">
            <td><div class="list-name">已选城市：</div></td>
            <td>
            	<ul class="address-list selected-box">
            		<li class="posrelative address-selected dsn">
            			<span class="address-name">招东小区</span>
            			<div class="icon-delete"></div>
        				<input type="hidden" name="supplyMerchantId" value="">
            		</li>
            		<c:if test="${areaType == 2}">
						<c:forEach items="${areas}" var="city">
							<li class="posrelative address-selected">
								<span class="address-name">${city.cityName}</span>
								<div class="icon-delete"></div>
								<input type="hidden" name="cityIds" value="${city.cityId}">
							</li>
						</c:forEach>
					</c:if>
            	</ul>
            	<input type="hidden" <c:if test="${areaType == 2}">value="${fn:length(areas)}"</c:if> <c:if test="${areaType != 2}">ignore="ignore"</c:if> class="select-input input_text w80 dsn" datatype="*" nullmsg="请选择城市！" />
            </td>
          </tr>
          <tr class="area-con swap-con swap-box-3 <c:if test="${areaType != 3}">dsn</c:if>">
            <td><div class="list-name">搜索结果：</div></td>
            <td>
            	<ul class="address-list search-box">
            	</ul>
            </td>
          </tr>
          <tr class="area-con swap-con swap-box-3 <c:if test="${areaType != 3}">dsn</c:if>">
            <td><div class="list-name">已选小区：</div></td>
            <td>
            	<ul class="address-list selected-box">
					<c:if test="${areaType == 3}">
						<c:forEach items="${areas}" var="gb">
							<li class="posrelative address-selected address-selected-1" data-class="address-selected-1">
								<span class="address-name">${gb.gbName}</span><div class="icon-delete"></div>
								<input type="hidden" name="gbIds" value="${gb.gbId}">
							</li>
						</c:forEach>
					</c:if>
            	</ul>
            	<input type="hidden" <c:if test="${areaType == 3}">value="${fn:length(areas)}"</c:if> <c:if test="${areaType != 3}">ignore="ignore"</c:if> class="select-input input_text w80 dsn" datatype="*" nullmsg="请选择小区！" />
            </td>
          </tr>
        </table>
        
        <input id="sumPopAd" class="info-btn save-set-prize-info-btn" type="submit" value="保存" />
        <input class="info-btn mar-left15 info-btn01 mtop0" onclick="history.back()" type="button" value="取消">
        <div class="h30"></div>
    </form>
</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.itemScreen.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		btnSubmit:"#sumPopAd", 
		tiptype:3,
		callback:function(data){
			
		}
	});
	
	var changeJumpPage = $.itemScreenStart({
		selectChangeObj: '#jumpPageType',		//下拉框对象
	});

	
	//切换下拉框，显示对应内容
	changeJumpPage.changeUserRange();
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	
	//切换下拉框，显示对应内容
	itemScreenSwap.changeUserRange();
	
	//选择、反选、删除
	//itemScreenSwap.itemSelectAndDelete();
});
</script>
<script type="text/javascript">
	$(function(){
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
				var areaSelectedNum = 0;
				$('.address-list.selected-box li:visible').each(function(){
					var thisAreaName = $(this).find('.address-name').text();
					if(thisAreaName === thisName){
						areaSelectedNum += 1;
					}
				})
				if(areaSelectedNum > 0){
					alert('请勿重复选择！');
					return false;
				}

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
				url = '${pageContext.request.contextPath}/addressCity/getAddressCityIdByName.json?name=' + encodeURI(currentVal, "utf-8");
			} else if(areaType == 3){
				url = '${pageContext.request.contextPath}/groupBuildingJson/getBuildingListByNameAndCityId.json?name=' + encodeURI(currentVal, "utf-8");
			}
			if(!currentVal == ''){
				ajaxSearch(url,thisSearchBox);
			}
		});
	});
</script>
</html>

