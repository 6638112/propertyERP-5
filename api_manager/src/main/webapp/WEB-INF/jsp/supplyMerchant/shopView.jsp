<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>/" target="_blank">
<title>电商-商家管理-商家审核</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/picbox.css" />
</head>

<body>
<div class="info">
        <h2>店铺信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="120" align="right"><Span class="red">*</Span> 店铺名称：</td>
            <td>${bean.shopName }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 店铺所在地：</td>
            <td class="citySelect01">${bean.shopArea }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 详细地址：</td>
            <td>${bean.shopAddress }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 店铺图片：</td>
            <td>
                <ul class="menu-img">
                	<c:forEach items="${bean.shopPhotoeList }" var="item"> 
	                    <li><a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.Ebuy_Store_Pic) + PathConstants.Ebuy_Store_Pic  %>${item}" rel="lightbox-group">
	                    <img src="<%=OmsSysParamManager.getImageServerUrl(PathConstants.Ebuy_Store_Pic) +PathConstants.Ebuy_Store_Pic  %>${item}" border="0" /></a></li>
                	</c:forEach>
                </ul>
            </td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 营业执照：</td>
            <td>
                <ul class="menu-img">
                    <c:forEach items="${bean.businessPhotoesList }" var="item"> 
	                    <li><a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.Ebuy_Store_Pic) + PathConstants.Ebuy_Store_Pic  %>${item}" rel="lightbox-group">
	                    <img src="<%=OmsSysParamManager.getImageServerUrl(PathConstants.Ebuy_Store_Pic) +PathConstants.Ebuy_Store_Pic  %>${item}" border="0" /></a></li>
                	</c:forEach>
                </ul>
            </td>
          </tr>
          <tr>
            <td align="right">店铺简介：</td>
            <td>${bean.shopIntroduce }</td>
          </tr>
        </table>
        <h2 class="mtop20">服务小区<input id="createNewBill" class="weak-btn small-btn w100 mleft20" type="button" value="新增"></h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <thead>
          <tr class="title">
            <th width="120"><div align="left">小区名字</div></th>
            <th><div align="left">所在地区</div></th>
            <th><div align="left">详细地址</div></th>
            <th><div align="left">操作</div></th>
          </tr>
          </thead>
          <c:forEach items="${bean.serviceGbList }" var="gb" varStatus="status" >
              <c:if test="${gb.gbName != null and gb.gbName != ''}">
                  <tr>
                    <td id="testid"><span>${gb.gbName }</span></td>
                    <td>${gb.gbArea }</td>
                    <td>${gb.gbAddress }</td>
                    <td><a class="blue deleteAddress" data-href="<%=basePath%>/supplyMerchant/delStoreRange.json?gbId=${gb.gbId}&shopId=${bean.shopId}" href="javascript:void(0);">删除</a></td>
                  </tr>
              </c:if>
          </c:forEach>
        </table>
        <div id="newBillBox" class="layer-bill dsn" style="width: 480px; height: 260px;">
            <table class="info-list" border="0">
                <tr class="search-input">
                    <td></td>
                    <td>
                        <input type="text" maxlength="8" class="input_text w120" placeholder="请输入关键字" />
                        <input class="input-btn common-search-btn" type="button" value="搜索">
                    </td>
                </tr>
                <tr class="city-con">
                    <td width="60"><div class="list-name">搜索结果：</div></td>
                    <td>
                        <ul class="address-list search-box ept">
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input id="saveGroupBuildingIds" class="info-btn small-btn w150" type="button" value="保 存"></td>
                </tr>
                <tr><td></td><td style="color: red;">小区选择一次性不能超过20个！</td></tr>
            </table>
        </div>
        <h2 class="mtop20">运费信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <thead>
	          <tr class="title">
	            <th><div align="left">描述</div></th>
	            <th><div align="left">运费（元）</div></th>
	          </tr>
          </thead>
          <c:forEach items="${merchantFees}" var="merchantFee"> 
	          <tr>
	            <td>${merchantFee.desc}</td> 
	            <td>
	           	    <c:if test="${(not empty merchantFee.fee) and (merchantFee.fee>0)}">
	            		<fmt:formatNumber value="${merchantFee.fee/100}" type="currency" pattern="0.00" maxFractionDigits="2"/>
	            	</c:if>
				</td> 
	          </tr> 
          </c:forEach>
        </table>
        
        <h2 class="mtop20">店主信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="120" align="right"><Span class="red">*</Span> 店主姓名：</td>
            <td>${bean.ownerInfo.ownerName }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 店铺电话：</td>
            <td>${bean.ownerInfo.ownerPhone }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 身份证件：</div></td>
            <td>
                <ul class="menu-img">
                	<c:forEach items="${bean.ownerInfo.owenerIDPhotoList }" var="item">
	                    <li><a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.Ebuy_Store_Pic) + PathConstants.Ebuy_Store_Pic  %>${item}" rel="lightbox-group">
	                    <img src="<%=OmsSysParamManager.getImageServerUrl(PathConstants.Ebuy_Store_Pic) +PathConstants.Ebuy_Store_Pic  %>${item}" border="0" /></a></li>
                	</c:forEach>
                </ul>
            </td>
          </tr>
        </table>
</div>
</body>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/picbox.js"></script>
<script type="text/javascript" src="js/jquery.common.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="js/layer.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
$(function(){
    //新增账单弹出层
    $('#createNewBill').click(function(){
        //清除原来的内容
        $(".ept").html("");
        $(".input_text").val("");
        $.layer({
            type: 1,
            shade: [0.4,'#000000'],
            area: ['auto', 'auto'],
            offset: ['300px', ''],
            title: false,
            border : [5, 0.3, '#000'],
            page: {dom : '#newBillBox'}
        });
    });
    var blockId = '${bean.blockId}';
    //开始搜索
    $('.common-search-btn').click(function(){
        var thisSearchBox = $('.search-box:visible');
        currentVal = $.trim($('.search-input input.input_text').val());
        var url = '<%=basePath%>/groupBuildingJson/getBuildingListByNameAndBlockId.json?name=' + encodeURI(currentVal, "utf-8")+"&blockId="+blockId;
        if(!currentVal == ''){
            ajaxSearch(url,thisSearchBox);
        }
    });
    //搜索内容
    function ajaxSearch(url,objBox){
        $.getJSON(url, function(data){

            var $objBox = $(objBox);
            var searchList = '';

            //var $searchListClone = $searchList.clone(true);
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
            console.log(searchList);
            $objBox.html(searchList);
            //重置页面高度
            window.parent.TuneHeight();
        });
    }

    //选择城市/小区
    var $addressSelectedLi = $('.address-selected.dsn');
    var addressSelectedNum = 0;
    var selectCountNum = 0;
    $(document).on("click", '.address-list.search-box li:visible', function(){
        var $this = $(this);
        //选择
        if(!$this.hasClass('on')){
            var addressSelectedClass = 'address-selected-';
            var thisName = $this.find('.address-name').text();
            var id = $this.find('span').attr('data-itemid');

            //防止重复选择
            var areaSelectedNum = 0;
            $('.address-list.selected-box li:visible').each(function(){
                var thisAreaName = $(this).find('.address-name').text();
                if(thisAreaName === thisName){
                    areaSelectedNum += 1;
                }
            });
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
    });

    $("#saveGroupBuildingIds").click(function () {
        var idStr = "";
        var idNums = 0;
        $('.address-list.search-box li.on').each(function(){
            var curId = $(this).find('.address-name').attr('data-itemid');
            idStr += curId + ",";
            idNums ++;
        });
        if(idStr.length == 0) {
            alert("请选择小区！");
            return;
        }
        if(idNums > 20) {
            alert("一次性选择小区不能超过20个，请重新选择！");
            return;
        }
        var shopId = '${bean.shopId}';
        $.ajax({
            url: '<%=basePath%>/supplyMerchant/addStoreRange.json',
            dataType: 'json',
            method:'post',
            data: {"gbIds":idStr,"id":shopId},
            success: function(data){
                if(data.status=="0000"){
                    alert(data.message);
                    window.location.reload(true);
                } else {
                    alert("操作超时，稍后重试！");
                }
            }
        });
    });

    $(".deleteAddress").click(function () {
        $.ajax({
            url: $(this).attr('data-href'),
            dataType: 'json',
            success: function(data, status){
                alert(data.message);
                window.location.reload(true);
            }
        });
    });

});

</script>

</html>
