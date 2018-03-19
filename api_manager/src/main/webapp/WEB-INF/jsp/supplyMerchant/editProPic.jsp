<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>楼下店装修</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>

<body>
<div class="info">
    <form class="inputform" action="<%=basePath%>/supplyMerchant/editProPic.html" method="post" enctype="multipart/form-data">
        <h2>楼下店信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
			  <input type="hidden" name="supplyMerchantId" value="${supplyMerchant.id}">
            <td width="20%"><div class="list-name"><span class="red">*</span> 店铺名称：</div></td>
            <td>${supplyMerchant.name}</td>
          </tr>
			<tr>
				<td><div class="list-name"><span class="red">*</span> 店铺地址：</div></td>
				<td>${supplyMerchant.address}</td>
			</tr>
        </table>

		<h2 class="mtop20">修改内容</h2>
		<table class="info-list-02" border="0" cellpadding="0" cellspacing="0">
			<tbody>
			<tr>
				<td width="120" align="right" class=""><span class="red">*</span> 店铺版面图：</td>
				<td class="item-upload-img">
					<div class="uploadPreview01 mright6">
						<input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage02">
						<c:if test="${empty supplyMerchant.proPic}">
							<img class="imgPreview" width="82" height="82" src="">
						</c:if>
						<c:if test="${! empty supplyMerchant.proPic}">
							<img class="imgPreview" width="82" height="82" src="<%= OmsSysParamManager.getImageServerUrl("/ebuyStorePic/") %>/ebuyStorePic/${supplyMerchant.proPic}">
						</c:if>
					</div>
					<input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请上传广告图片！">
					<span class="Validform_checktip"></span></td>
			</tr>
			<%--<tr>
				<td><div class="list-name">店铺详情图：</div></td>
				<td class="item-upload-img">
					<div class="uploadPreview01 img-new-add marb15 img-hide">
						<div class="img-delete-btn">删除</div>
						<input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="shopPhotoes" class="uploadImage02" />
						<input type="hidden" name="imgUrl" value=""/>
						<img class="imgPreview" width="82" height="82" src="${pageContext.request.contextPath}/images/addimg01.jpg" />
					</div>
					<c:forEach items="${picList}" var="pic">
						<div class="uploadPreview01 marb15 mright6">
							<div class="img-delete-btn">删除</div>
							<input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="shopPhotoes" class="uploadImage02" />
							<input type="hidden" name="imgUrl" value="${pic}"/>
							<img class="imgPreview" width="82" height="82" src="${pic}?x-oss-process=image/resize,m_fill,w_82,h_82,limit_0/format,jpg/interlace,1/quality,q_90" />
						</div>
					</c:forEach>
					<div class="uploadPreview01 img-add-btn marb15"></div><br />
					<span class="f12">注：图片尺寸640x540像素，建议图片2M以内，最多可上传3张</span>
					<input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请上传店铺详情图片！" />
					<div id="picDiv" style="width: 1px; height: 1px;"></div>
				</td>
			</tr>--%>
			<tr>
				<td><div class="list-name">店铺详情图：</div></td>
				<td id="dragItems">
					<div class="uploadPreview01 imgDesc-new-add drag-item marb15 img-hide">
						<div class="img-delete-btn">删除</div>
						<input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="shopPhotoes" class="uploadImage02" />
						<input type="hidden" name="imgUrl" value=""/>
						<img class="imgPreview" width="82" height="82" src="${pageContext.request.contextPath}/images/addimg01.jpg" />
					</div>
					<c:forEach var="pic"  items="${picList}">
						<div class="uploadPreview01 marb15 mright6 drag-item">
							<div class="img-delete-btn">删除</div>
							<input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="shopPhotoes" class="uploadImage02" />
							<input type="hidden" name="imgUrl" value="${pic}"/>
							<img class="imgPreview" src="<%= OmsSysParamManager.getImageServerUrl("/ebuyStorePic/") %>/ebuyStorePic/${pic}?x-oss-process=image/resize,m_fill,w_82,h_82,limit_0/format,jpg/interlace,1/quality,q_90" border="0" />
						</div>
					</c:forEach>

					<div class="uploadPreview01 imgDesc-add-btn marb15"></div><br />
					<span class="f12 marb15">注：图片尺寸640x540像素，建议图片2M以内，最多可上传3张</span>
				</td>
			</tr>
			</tbody>
		</table>

        <input id="sumSupplier" class="info-btn save-set-prize-info-btn" type="submit" value="发布" />
        <div class="h30"></div>
    </form>
</div>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		btnSubmit:"#sumSupplier",
		tiptype:3,
		ignoreHidden: true,
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
					alert("修改装修图成功！");
					window.location.href = "<%=basePath%>/supplyMerchant/supplyMerchantList.html";
				} else {
					alert(data.message);
					$(".inputform").Validform().resetStatus();
				}
			});
		}
	});

	/*//增加删除图片
	var $imgNewAdd = $('.img-new-add');
	var num = 0;
	$('.img-add-btn').click(function(){
		var newImgId = 'newImgId' + num;
		var imgLength = $(this).prevAll('.uploadPreview01:visible').length;
		if(imgLength >= 3){
			alert('最多上传3张图片');
		}else{
			$imgNewAdd.clone(true).removeClass('img-hide').insertBefore($(this)).find('input').attr('id', newImgId);
		}
		num += 1;
	});

	$('.img-delete-btn').click(function(){
		$(this).parent('.uploadPreview01').remove();
	});
	//hover删除按钮
	$('.uploadPreview01').hover(function(){
		$(this).find('.img-delete-btn').show();
	},function(){
		$(this).find('.img-delete-btn').hide();
	});*/

	$('.img-delete-btn').click(function(){
		$(this).parent('.uploadPreview01').remove();
		/*if($("#dragItems").find(".uploadPreview01 ").length > 3) {
			var imgUrlValNum = 0;
			var imgSrcNum = 0;
			$("#dragItems").find(".uploadPreview01 ").each(function () {
				if($(this).find("input[name='imgUrl']").val()) {
					imgUrlValNum ++;
				}
				imgSrcNum ++;
			});
			if(imgUrlValNum >= 2 || $(this).siblings("input[name='imgUrl']").val() == "" || ($(this).siblings("input[name='imgUrl']").val() != "" && imgSrcNum > 3)) {
				$(this).parent('.uploadPreview01').remove();
			} else {
				alert("必须保留一张图片！");
			}
		} else {
			alert("必须保留一张图片！");
		}*/
	});

	//增加删除商品详情图片
	var $imgDescNewAdd = $('.imgDesc-new-add');
	var descNum = 0;
	$('.imgDesc-add-btn').click(function(){
		var newImgId = 'newImgId' + descNum;
		var imgLength = $(this).prevAll('.uploadPreview01:visible').length;
		if(imgLength >= 3){
			alert('最多上传3张图片');
		}else{
			$imgDescNewAdd.clone(true).removeClass('img-hide').insertBefore($(this)).find('input').attr('id', newImgId);
		}
		descNum += 1;
	});
	//hover删除按钮
	$('.uploadPreview01').hover(function(){
		$(this).find('.img-delete-btn').show();
	},function(){
		$(this).find('.img-delete-btn').hide();
	});

	$('.uploadImage02').change(function(){
		$(this).siblings('[name=imgUrl]').val('');
	});



})(jQuery);
</script>
</html>
