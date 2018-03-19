<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>//"/>
    <title>发起幸运购</title>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <form class="inputform" action="<%=basePath%>/flashDealActivityCfg/saveFlashDealActivityCfg.html" method="post">
        <input type="hidden" name="tEbuyProductFId" value="${ebuyProduct.id}">
        <input type="hidden" name="ebuySupplyMerchantId" value="${ebuySupplyMerchant.id}">
        <h2>发起幸运购</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <tr class="list-title">
                <td colspan="2"><div align="left" class="f14 bold">商品信息</div></td>
            </tr>
            <tr>
                <td width="20%"><div class="list-name">供应商：</div></td>
                <td>${ebuySupplyMerchant.name}</td>
            </tr>
            <tr>
                <td><div class="list-name">商品ID：</div></td>
                <td>${ebuyProduct.id}</td>
            </tr>
            <tr>
                <td><div class="list-name">货架分类：</div></td>
                <td>${ebuyProductType.typeName}</td>
            </tr>
            <tr>
                <td><div class="list-name">商品名称：</div></td>
                <td>${ebuyProduct.name}</td>
            </tr>
            <tr>
                <td><div class="list-name">商品售价：</div></td>
                <td>${priceDiscount div 100}</td>
            </tr>
        </table>

        <table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
            <tr class="list-title">
                <td colspan="2"><div align="left" class="f14 bold">幸运购信息</div></td>
            </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span>标题：</div></td>
                <td><input type="text" name="title" v class="input_text pp w120"  nullmsg="请完善幸运购信息"/></td>
            </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span>介绍：</div></td>
                <td><input type="text" name="introduceContent" class="input_text pp w120" nullmsg="请完善幸运购信息"/></td>
            </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span>仅限：</div></td>
                <td><input type="text" name="prizeCount" class="input_text pp w120" maxlength="3" datatype="fei0zhengzhengshu" nullmsg="请完善幸运购信息" errormsg="请填写大于0的整数" /> 份</td>
            </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span> 开始时间：</div></td>
                <td><input type="text" name="activityStartTime" class="input_text icon_dt pp" id="date05" readonly="readonly" placeholder="请选择开始时间" datatype="*" nullmsg="请选择开始时间！" ></td>
            </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span> 截团时间：</div></td>
                <td><input type="text" name="activityEndTime" class="input_text icon_dt endlessTime pp" id="date06" readonly="readonly" placeholder="请选择结束时间" datatype="*" nullmsg="请选择结束时间！" ></td>
            </tr>
        </table>

        <input class="info-btn" type="submit" value="发布"/>
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
                $(".inputform").attr('onsubmit', 'return false;');
            },
            callback:function(data){
                $(".inputform").ajaxSubmit(function (data) {
                    alert(11);
                    try {
                        data = eval(data);
                    } catch (e) {
                        data = eval("(" + data + ")");
                    }
                    if (data.status == '0000') {
                        alert("幸运购修改成功");
                        window.location.href = "<%=basePath%>/flashDealActivityCfg/flashDealActivityList.html";
                    } else {
                        alert("幸运购修改失败");
                        $(".inputform").Validform().resetStatus();
                    }
                });
            }
        });

        //校验时间先后顺序
        $('.input_text.icon_dt').compareTime('#date05', '#date06');
    })(jQuery);
</script>
</html>
