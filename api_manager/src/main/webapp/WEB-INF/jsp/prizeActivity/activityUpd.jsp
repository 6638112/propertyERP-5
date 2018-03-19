<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>//"/>
<title>轻应用活动运营-活动管理-编辑活动</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css?v20151013"/>
</head>

<body>
<div class="info">
    <form class="inputform" action="<%=basePath%>/prizeActivityJson/doActivityUpd.json" enctype="multipart/form-data" method="post">
        <h2>编辑活动</h2>
        <input type="hidden" name="actId" value="${detail.id}"/>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">活动基本信息</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 活动名称：</div></td>
            <td><input type="text" name="name" value="${detail.name}" class="input_text w300 pp" datatype="*" nullmsg="请填写活动名称！" placeholder="请填写活动名称" /></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 活动时间：</div></td>
            <td><input type="text" name="startTime" value="${fn:substring(detail.startTime, 0, 16)}" class="input_text icon_dt pp" id="date01" placeholder="请选择起始时间" datatype="*" nullmsg="请选择起始时间！" /> 至 <input type="text" name="endTime" value="${fn:substring(detail.endTime, 0, 16)}" class="input_text icon_dt pp" id="date02" placeholder="请选择结束时间" datatype="*" nullmsg="请选择结束时间！" /></td>
          </tr>
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">活动分享信息</div></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 分享链接文案：</div></td>
            <td><input type="text" name="shareText" value="${detail.shareText}" class="input_text pp w500" placeholder="分享链接文案" maxlength="20" datatype="*" nullmsg="请完善商品信息！" /><span class="f12 mar-left15">注：20字以内</span></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 分享链接图标：</div></td>
            <td>
                <div class="uploadPreview01"><input type="file" name="shareIcon" class="uploadImage02" /><img class="imgPreview" width="82" height="82" src="${shareIconPath}" /></div>
                <span class="f12 mar-left15">注：仅限一张图片，最佳尺寸114px*114px</span>
                <div id="picDiv" style="width: 1px; height: 1px;"></div>
            </td>
          </tr>
        </table>
        <div class="mtop40 f14"><span class="red">*</span> <span class="bold">是否开启活动：</span> 
        <input class="mtop3" name="activityStatus" value="2" type="radio" <c:if test="${detail.activityStatus == 2}">checked="checked"</c:if> />是 
        <input class="mtop3 mleft20" name="activityStatus" value="1" type="radio" <c:if test="${detail.activityStatus == 1}">checked="checked"</c:if> datatype="*" nullmsg="请选择是否开启活动！"/> 否
        </div>
        
        <h2 class="mtop40 f14">活动抽奖配置</h2>
        <table class="info-list-02 f14" border="0" cellpadding="0" cellspacing="1">
        <tr class="list-title">
          <td colspan="2"><div align="left" class="f14 bold"><span class="red">*</span> 奖项选择（可多选）</div></td>
        </tr>
        
        <c:forEach var="it" items="${detail.msPrizeActHasOptList}">
    	<tr class="prize-single pointer">
		  <td><input class="optionCheckBox" type="checkbox" name="optionRelaList" checked="checked" value="${it.id}" /> <span class="black bold mar-right5">${it.msPrizeOption.name}</span></td>
		  <td><span class="right prize-list-arrow animated rotateOut"></span>有效期：<span class="optionDate01">${it.msPrizeOption.valiStartTime}</span> 至 <span class="optionDate02">${it.msPrizeOption.valiEndTime}</span></td>
		</tr>
		<tr class="prize-info nobg dsn">
		  <td colspan="2" bgcolor="#f5f5f5">
		      <div class="prize-info-text">${it.msPrizeOption.comment}</div>
		      <table class="ranking-info" border="0" cellpadding="0" cellspacing="1">
		        <tr class="nobg">
		          <td width="90"><span class="red">*</span> 派奖额度：</td>
		          <td><input type="text" name="${it.id}_totalRela" value="${it.totalMaxCount}" onclick="qryLeftPriOptCount(${it.msPrizeOption.id},'potIdRela_${it.id}','<%=basePath%>');" class="input_text w300 pp optionCountNum" datatype="n" nullmsg="请填写派奖额度！" placeholder="该次活动需要使用的数量" /> 
		          	剩余库存<span id="potIdRela_${it.id}" class="optionLeftNum">-</span>份</td>
		        </tr>
		        <tr class="nobg">
		          <td><span class="red">*</span> 日均派发数：</td>
		          <td><input type="text" name="${it.id}_dayRela" value="${it.dayMaxCount}" class="input_text w300 pp optionDateNum" datatype="n" nullmsg="请填写日均派发数！" placeholder="每天最大派奖份数" /></td>
		        </tr>
		      </table>
		  </td>
		</tr>
       	</c:forEach>
       	
       	<c:forEach var="it" items="${optionList}">
    	<tr class="prize-single pointer">
		  <td><input class="optionCheckBox" type="checkbox" name="optionList" value="${it.id}" /> <span class="black bold mar-right5">${it.name}</span></td>
		  <td><span class="right prize-list-arrow animated rotateOut"></span>有效期：<span class="optionDate01">${it.valiStartTime}</span> 至 <span class="optionDate02">${it.valiEndTime}</span></td>
		</tr>
		<tr class="prize-info nobg dsn">
		  <td colspan="2" bgcolor="#f5f5f5">
		      <div class="prize-info-text">${it.comment}</div>
		      <table class="ranking-info" border="0" cellpadding="0" cellspacing="1">
		        <tr class="nobg">
		          <td width="90"><span class="red">*</span> 派奖额度：</td>
		          <td><input type="text" name="${it.id}_total" onclick="qryLeftPriOptCount(${it.id},'potId_${it.id}','<%=basePath%>');" class="input_text w300 pp optionCountNum" ignore="ignore" datatype="n" nullmsg="请填写派奖额度！" placeholder="该次活动需要使用的数量" /> 
		          	库存<span id="potId_${it.id}" class="optionLeftNum">-</span>份</td>
		        </tr>
		        <tr class="nobg">
		          <td><span class="red">*</span> 日均派发数：</td>
		          <td><input type="text" name="${it.id}_day" class="input_text w300 pp optionDateNum" ignore="ignore" datatype="n" nullmsg="请填写日均派发数！" placeholder="每天最大派奖份数" /></td>
		        </tr>
		      </table>
		  </td>
		</tr>
       	</c:forEach>
       	
        </table>
        <input id="sumGoods" class="info-btn save-prize-info-btn" type="submit" value="保存" />
    </form>
</div>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js?v20151013"></script>
<script type="text/javascript" src="js/jquery.common.js?v20150921"></script>
<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="busiJS/prizeActivity.js?v5"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		dragonfly:true,
		btnSubmit:"#sumGoods",
		beforeSubmit:function(curform){
			$(".inputform").attr('onsubmit','return false;');
		},
		callback:function(data){
			$(".inputform").ajaxSubmit(function(data) {
				try {
					data = eval(data);
				} catch (e) {
					data = eval("("+data+")");
				}
				if (data.status == '0000') {
					alert("活动编辑成功！");
					window.location.href="<%=basePath%>/prizeActivity/activityDetail.html?actId="+data.dataValue.actId;
				} else {
					alert(data.message);
					$(".inputform").Validform().resetStatus();
				}
			});
		}
	});

    //校验奖项时间
    $('.optionCheckBox').click(function(){
    	if($(this).is(":checked")){
    		var date01 = $('#date01').val();
    		var date02 = $('#date02').val();
    		var optionDate01 = $(this).parent('td').siblings('td').find('.optionDate01').text();
    		var optionDate02 = $(this).parent('td').siblings('td').find('.optionDate02').text();
    		if(optionDate01 > date01 || optionDate02 < date02){
    			alert('该奖品时间与活动时间不符，请重新选择奖品！');
    			$(this).prop('checked', false);
    		}
    	}
    });
    
    //校验库存
    $('.optionCountNum').keyup(function(){
    	var optionCountNum = Number($.trim($(this).val()));
    	var optionLeftNum = Number($(this).siblings('.optionLeftNum').text());
    	var optionDateNum = Number($(this).parent('td').parent('tr').siblings('tr').find('.optionDateNum').val());
    	if(optionCountNum > optionLeftNum){
    		alert('须小于库存数量！');
    		$(this).val('');
    	}else if(optionCountNum < optionDateNum){
    		alert('须大于或等于派奖数量！');
    		$(this).val('');
    	}
    });
    $('.optionDateNum').keyup(function(){
    	var optionLeftNum = Number($(this).parent('td').parent('tr').siblings('tr').find('.optionCountNum').val());
    	var optionDateNum = Number($.trim($(this).val()));
    	if(optionDateNum > optionLeftNum){
    		alert('须小于派奖数量！');
    		$(this).val('');
    	}
    });
    
	$('.uploadImage02').change(function(){
		$('.img-upload-status').val('1');
	});
		
})(jQuery);
</script>
</body>

</html>
