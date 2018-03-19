	window.onerror = function (errMsg, scriptURI, lineNumber, columnNumber, errorObj) {
	    setTimeout(function () {
	        var rst = {
	            "错误信息：": errMsg,
	            "出错文件：": scriptURI,
	            "出错行号：": lineNumber,
	            "出错列号：": columnNumber,
	            "错误详情：": errorObj
	        };
	
	        //alert('出错了，下一步将显示错误信息');
	        //alert(JSON.stringify(rst, null, 10));
	    });
	};
$(function() {

	var upLoadNum = 0;

	//新增上传图片前预览缩略图
	$(".uploadImage01").on("change", function() {
		
	var files = !!this.files ? this.files : [];
	var $this = $(this);
	var thisInputName = $this.attr('data-imgName');
	
    lrz(files[0], {
        //width: 800
    })
        .then(function (rst) {
			if (!files.length || !window.FileReader) return;
			if (files[0].size >= 10240000) {
				$.Showmsg('图片过大，应小于10M');
				return;
			}
			
			if (/^image/.test(files[0].type)) {
				var reader = new FileReader();
				reader.readAsDataURL(files[0]);
				reader.onloadend = function() {
					var $thisPreviewBox = $this.parent(".uploadPreview01");
					$thisPreviewBox.css("background-image", "url(" + rst.base64 + ")");
					$thisPreviewBox.siblings(".uploadImageVal01").val('1');
					
					if($thisPreviewBox.find('.newImgUpLoad').length == 0){
						var upLoadImgName = 'newUpLoad' + upLoadNum;
						upLoadNum += 1;
						var $newInput = $('<input class="newImgUpLoad dsn" type="hidden" indexNum="11">');
						if(thisInputName !== '' && thisInputName !== undefined){//创建店铺
							$newInput.attr('name', thisInputName).val(rst.base64);
						}else{// 上传商品
							$newInput.attr('name', upLoadImgName).val(rst.base64);
						}
						$newInput.prependTo($thisPreviewBox);
					}else{
						$thisPreviewBox.find('.newImgUpLoad').val(rst.base64);
					}
				}
			}
		});
	});


	//增加删除图片
	var $imgNewAdd = $('.img-new-add');
	var num = 0;
	$('.img-add-btn').click(function() {
		var dataName = $(this).attr('data-name');
		var newImgId = 'newImgId' + num;
		var imgLength = $(this).prevAll('.uploadPreview01:visible').length;
		if (imgLength == 2) {
			$(this).hide();
		}
		$imgNewAdd.clone(true).removeClass('img-hide').insertBefore($(this)).find('input').attr({'id':newImgId,'data-imgName':dataName});
		num += 1;
	});

	$('.img-delete-btn').click(function() {
		var $this = $(this);
		var $thisLi = $this.parents('li');
		var $thisUploadImageVal01 = $this.parent('.uploadPreview01').siblings(".uploadImageVal01");
		
		$this.parent('.img-new-add').siblings('.img-add-btn').show();
		$this.parent('.uploadPreview01').remove();
		
		var upLoadLength = $thisLi.find('.newImgUpLoad.dsn').length;
		if(upLoadLength === 0){
			$thisUploadImageVal01.val('');
		}else{
			$thisUploadImageVal01.val(upLoadLength);
		}
	});



	//增加删除商品详情图片、描述
	var $imgDescNewAdd = $('.imgDesc-new-add');
	var descNum = 0;
	$('.imgDesc-add-btn').click(function() {
		var newImgId = 'newImgId' + descNum;
		var imgLength = $(this).prev('#dragItems').find('.uploadPreview01:visible').length;
		if (imgLength == 10) {
			$.Showmsg('最多上传10张图片');
		} else {
			$imgDescNewAdd.clone(true).removeClass('img-hide').appendTo($(this).prev('#dragItems')).find('input[name=photoimage]').attr('id', newImgId);
		}
		descNum += 1;
	});

	$('.img-delete-btn').click(function() {
		$(this).parent('.uploadPreview01').remove();
	});

	//添加描述按钮
	var $shapeBox = $('.shape-box');
	var $imgDescText = $('.img-desc-text');
	var $imgDescTextBox = $('.img-desc-text-box');
	$imgDescText.click(function() {
		var noImgUrl = $(this).parent(".uploadPreview01").css("background-image");
		if (noImgUrl.indexOf('addimg01.jpg') > -1) {
			$.Showmsg('请先上传图片');
		} else {
			var thisVal = $(this).siblings('input.desc-text-input').val();
			$(this).siblings('input.desc-text-input').addClass('current-input');
			$imgDescTextBox.find('.textareas').val(thisVal);
			$('.leftNum').text('100');
			$imgDescTextBox.fadeIn();
			$shapeBox.fadeIn();
		}
	});
	//编辑描述按钮
	var $imgDescBox = $('.img-desc-box');
	$imgDescBox.click(function() {
		var thisVal = $(this).siblings('input.desc-text-input').val();
		$(this).siblings('input.desc-text-input').addClass('current-input');
		$imgDescTextBox.find('.textareas').val(thisVal);
		var valLength = $.trim(thisVal).length;
		var num = 100 - valLength;
		if(num < 0){
			num = 0;
		}
		$('.leftNum').text(num);
		$imgDescTextBox.fadeIn();
		$shapeBox.fadeIn();
	});

	//描述确定按钮
	$('#checkDescBtn').click(function() {
		var curText = $('.textareas').val();
		var omitText = $.trim(curText).substring(0, 5);
		var $thisImgDescBox = $('.current-input').siblings('.img-desc-box');
		var $thisImgDescText = $('.current-input').siblings('.img-desc-text');
		$thisImgDescBox.find('.desc-text-info').text(omitText + '…');
		if ($thisImgDescBox.is(':hidden')) {
			$('.current-input').siblings('.img-desc-text').hide();
			$thisImgDescBox.show();
		} else if ($.trim(curText) == '') {
			$thisImgDescBox.hide();
			$thisImgDescText.show();
		}
		$('.current-input').val(curText).removeClass('current-input');
		$imgDescTextBox.fadeOut();
		$shapeBox.fadeOut();
	});
	//描述取消按钮
	$('#backDescBtn').click(function() {
		$('.current-input').removeClass('current-input');
		$imgDescTextBox.fadeOut();
		$shapeBox.fadeOut();
	});

	//输入框限制字数
	$('.textareas').keyup(function() {
		var valLength = $.trim($(this).val()).length;
		var currentVal = $.trim($(this).val());
		if (valLength > 99) {
			$(this).val(currentVal.substring(0, 100));
		}
		var num = 100 - valLength;
		if(num < 0){
			num = 0;
		}
		$('.leftNum').text(num);
	});


});