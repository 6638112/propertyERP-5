$(function(){
	
		
	//新增上传图片前预览缩略图
	$(".uploadImage01").on("change", function(){
		var files = !!this.files ? this.files : [];
		if (!files.length || !window.FileReader) return;
		if(files[0].size >= 10240000) {
			$.Showmsg('图片过大，应小于10M');	
			return;
		}
		var $this = $(this);
		if (/^image/.test( files[0].type)){
			var reader = new FileReader();
			reader.readAsDataURL(files[0]);
			reader.onloadend = function(){
				$this.parent(".uploadPreview01").css("background-image", "url("+this.result+")");
				$this.parent(".uploadPreview01").siblings(".uploadImageVal01").val('1');
			}
		}
	});

	
	//增加删除图片
	var $imgNewAdd = $('.img-new-add');
	var num = 0;
	$('.img-add-btn').click(function(){
		var dataName = $(this).attr('data-name');
		var newImgId = 'newImgId' + num;
		var imgLength = $(this).prevAll('.uploadPreview01:visible').length;
		if(imgLength == 2){
			$(this).hide();
		}
		$imgNewAdd.clone(true).removeClass('img-hide').insertBefore($(this)).find('input').attr({'id':newImgId,'name':dataName});
		num += 1;
	});
	
	$('.img-delete-btn').click(function(){
		$(this).parent('.img-new-add').siblings('.img-add-btn').show();
		$(this).parent('.uploadPreview01').remove();
	});
	
	
});
			