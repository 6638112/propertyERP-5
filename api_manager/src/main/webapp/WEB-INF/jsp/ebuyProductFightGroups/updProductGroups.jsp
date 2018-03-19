<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>//"/>
    <title>拼购编辑</title>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <form class="inputform" enctype="multipart/form-data" action="<%=basePath%>/ebuyProductFightGroups/updProductGroups.html" method="post">
        <input type="hidden" name="id" value="${dto.ebuyProductFightGroups.id}">
        <h2>拼购编辑</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <tr class="list-title">
                <td colspan="2"><div align="left" class="f14 bold">商品信息</div></td>
            </tr>
            <input type="hidden" name="tEbuyProductFId" value="${dto.ebuyProduct.id}">
            <tr>
                <td width="20%"><div class="list-name">供应商：</div></td>
                <input type="hidden" name="tEbuyFightSupplyMerchantFId" value="${dto.ebuySupplyMerchant.id}">
                <td>${dto.ebuySupplyMerchant.name}</td>
            </tr>
            <tr>
                <td><div class="list-name">商品ID：</div></td>
                <td>${dto.ebuyProduct.id}</td>
            </tr>
            <tr>
                <td><div class="list-name">货架分类：</div></td>
                <td>${productTypeName}</td>
            </tr>
            <tr>
                <td><div class="list-name">商品名称：</div></td>
                <td>${dto.ebuyProduct.name}</td>
            </tr>
            <tr>
                <td><div class="list-name">商品售价：</div></td>
                <td>${priceDiscount div 100}元</td>
            </tr>
        </table>

        <table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
            <tr class="list-title">
                <td colspan="2"><div align="left" class="f14 bold">拼购信息</div></td>
            </tr>
            <tr>
                <td width="20%"><div class="list-name"><span class="red">*</span> 拼购价：</div></td>
                <td>
                    <input type="hidden" name="priceDiscount" id="priceDiscount">
                    <input type="text" id="oldPriceDiscount" class="input_text pp w120" value="${dto.ebuyProductFightGroups.priceDiscount div 100}" datatype="numberFixTwo" nullmsg="请完善拼购信息" errormsg="请填写数字，可带两位小数" /> 元
                </td>
            </tr>
            <tr>
                <td><div class="list-name">起拼人数：</div></td>
                <td>
                    <input type="hidden" name="fightStatus" id="fightStatus" value="${dto.ebuyProductFightGroups.fightStatus}">
                    <input type="text" name="fightNum" class="input_text pp w120" value="${dto.ebuyProductFightGroups.fightNum}" datatype="fei0zhengzhengshu" nullmsg="请完善拼购信息" errormsg="请填写大于0的整数" /> 人
                </td>
            </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span> 开始时间：</div></td>
                <td><input type="text" name="startDate" class="input_text icon_dt endlessTime pp" id="date05" value="${fn:substring(dto.ebuyProductFightGroups.startDate, 0, 16)}" readonly="readonly" placeholder="请选择结束时间" datatype="*" nullmsg="请选择结束时间！" ></td>
            </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span> 截团时间：</div></td>
                <td><input type="text" name="expireDate" class="input_text icon_dt endlessTime pp" id="date06" value="${fn:substring(dto.ebuyProductFightGroups.expireDate, 0, 16)}" readonly="readonly" placeholder="请选择结束时间" datatype="*" nullmsg="请选择结束时间！" ></td>
            </tr>
            <tr>
                <td><div class="list-name">拼购简介：</div></td>
                <input type="hidden" id="productDesc" name="productDesc">
                <td><textarea id="productDescOld" class="textareas txt02" placeholder="限200字以内" rows="5" maxlength="200">${dto.ebuyProduct.desc}</textarea></td>
            </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span> 拼购图片：</div></td>
                <td class="item-upload-img">
                    <div class="uploadPreview01 mright6">
                        <input type="file" name="productPic" class="uploadImage02" />
                        <img class="imgPreview" width="82" height="82" src="${picPath}" />
                    </div>
                    <span class="f12">注：建议图片尺寸640x240像素，大小2M以内，仅限1张</span>
                    <input class="img-upload-val dsn" type="text" value="1" datatype="*" nullmsg="请上传商品图片！" />
                </td>
            </tr>
            <tr class="search-input">
                <td><div class="list-name"><span class="red">*</span> 拼购自提点：</div></td>
                <td>
                    <input type="text" maxlength="8" value="${fightMerchantName}" class="input_text w120 pp" placeholder="请输入关键字" />
                    <input class="input-btn" type="button" value="搜索">
                </td>
            </tr>
            <tr class="city-con">
                <td><div class="list-name">搜索结果：</div></td>
                <td>
                    <ul class="address-list search-box">
                        <li class="posrelative address-selected address-selected-3" data-class="address-selected-3">
                            <span class="address-name" data-itemid="${dto.ebuyProductFightGroups.tEbuyFightSupplyMerchantFId}">${fightMerchantName}</span>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr class="city-con">
                <td><div class="list-name">已选自提点：</div></td>
                <td>
                    <ul class="address-list selected-box">
                        <li class="posrelative address-selected address-selected-3" data-class="address-selected-3">
                            <span class="address-name">${fightMerchantName}</span><div class="icon-delete"></div>
                            <input type="hidden" id="cityId" value="${dto.ebuyProductFightGroups.tEbuyFightSupplyMerchantFId}">
                        </li>
                        <li class="posrelative address-selected dsn"><span class="address-name"></span><div class="icon-delete"></div></li>
                    </ul>
                    <input type="hidden" class="select-input input_text w80 dsn" value="1" datatype="*" nullmsg="请选择自提点！" />
                </td>
            </tr>
        </table>

        <input class="btn-submit info-btn save-set-prize-info-btn" type="button" value="保存" />
        <div class="h30"></div>
    </form>
</div>

</body>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/jquery.common.js?20160701"></script>
<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
    (function($){
        //表单验证
        $(".inputform").Validform({
            btnSubmit:".btn-submit",
            tiptype:3,
            beforeSubmit: function (curform) {
                $('#priceDiscount').val(100 * $('#oldPriceDiscount').val());
                $('#productDesc').val($('#productDescOld').val());
                $(".inputform").attr('onsubmit', 'return false;');

    			/* var InputTime = $('#date05').val().substring(0,16),
                    nowTime = getNowFormatDate().substring(0,16);
                if(InputTime < nowTime){
                	alert('开始时间须大于当前时间！');
                	return false;
                }  */
            },
            callback:function(data){
                $(".inputform").ajaxSubmit(function (data) {
                    try {
                        data = eval(data);
                    } catch (e) {
                        data = eval("(" + data + ")");
                    }
                    if (data.status == '0000') {
                        alert("拼购修改成功！");
                        window.location.href = "<%=basePath%>/ebuyProductFightGroups/list.html";
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
        //选择城市/小区
        var $addressSelectedLi = $('.address-selected.dsn');
        var addressSelectedNum = 0;
        var selectCountNum = 1;

        $(document).on("click", '.address-list.search-box li:visible', function(){
            var $this = $(this);
            //选择
            if(!$this.hasClass('on')){
                var addressSelectedClass = 'address-selected-';
                var $addressSelectedLiClone = $addressSelectedLi.clone(true);
                var thisName = $this.find('.address-name').text();

    			//只能选择一个自提点
    			/*if(selectCountNum == 1){
    				alert('只能选择一个自提点！');
    				return false;
    			}*/
    			
                addressSelectedNum += 1;
                selectCountNum += 1;
                //已选城市/小区，校验通过
                $('.address-list:visible').siblings('.select-input').val(selectCountNum);
                $(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input'));

                addressSelectedClass = addressSelectedClass + addressSelectedNum;


                $this.addClass('on ' + addressSelectedClass).attr('data-class',addressSelectedClass);
                $addressSelectedLiClone.find('.address-name').text(thisName);
                $addressSelectedLiClone.removeClass('dsn').addClass(addressSelectedClass).attr('data-class',addressSelectedClass).appendTo('.selected-box:visible');

                id = $this.find('span').attr('data-itemid');
                
                $addressSelectedLiClone.append('<input type="hidden" name="tEbuyFightSupplyMerchantFId" value="'+id+'">');
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
                $("input[name=tEbuyFightSupplyMerchantFId]").remove();
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
            $("input[name=tEbuyFightSupplyMerchantFId]").remove();
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
                $.each(data.dataValue.list, function (i, aaList) {
                    searchList += '<li><span class="address-name" data-itemid="'+aaList.id+'">' + aaList.name + '</span></li>';
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
        $('.input-btn').click(function(){
            var url = '<%=basePath%>/ebuyFightSupplyMerchant/getFightMerchantListByName.json';
            var thisSearchBox = $('.search-box:visible');
            currentVal = $.trim($('.search-input input.input_text').val());
            currentVal = encodeURI(currentVal, "utf-8");
            url = url + '?name=' + currentVal;
            if(!currentVal == ''){
                ajaxSearch(url,thisSearchBox);
            }
        });
    });

</script>
</html>
