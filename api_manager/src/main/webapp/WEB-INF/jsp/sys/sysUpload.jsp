<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>//" target="_blank">
<title>上传图片框架</title>
<link rel="stylesheet" type="text/css" href="css/citySelect.css"/>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/style.css?v1" />
<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css">
<link href="umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<form class="inputform" action="<%=basePath%>/sys/upload.json" enctype="multipart/form-data" method="post">
<div class="info">
        <h2>上传图片框架</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 服务器根目录：</div></td>
            <td><input type="text" name="path" value="" class="input_text w300" nullmsg="服务器根目录！" errormsg="服务器根目录！" placeholder="请填服务器根目录" /></td>
          </tr>
          <tr>
            <td><div id="imgListName" class="list-name"><span class="red">*</span> 文件：</div></td>
            <td>
                <input type="hidden" class="input_text w300 img-upload-status" hidden="true" datatype="*" nullmsg="请上传文件图标！" />
                <div class="uploadPreview01"><input type="file" name="icon" class="uploadImage02" /><img class="imgPreview" width="82" height="82" src="images/addimg01.jpg" /></div>
                <span class="f12 mar-left15">注：仅限一张图片</span>
                <div id="picDiv" style="width: 1px; height: 1px;"></div>
            </td>
          </tr>
        </table>
        
        <input id="sumGoods" class="info-btn save-set-prize-info-btn" type="submit" value="保存" />
    
</div>
</form>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/layer.min.js?v2"></script>
<script type="text/javascript" src="js/jquery.common.js?v2015091802"></script>
<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="busiJS/prizeActivity.js?v5"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		btnSubmit:"#sumGoods", 
		tiptype:3,
		dragonfly:true,
		ajaxpost:true,
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
					$('.prize-upload').show();
					window.parent.TuneHeight();
					alert("上传成功！");
				} else {
					alert(data.message);
					$(".inputform").Validform().resetStatus();
				}
			});
			//return false;
		}
	});
	
	$('.uploadImage02').change(function(){
		$('.img-upload-status').val('1');
	});
		
})(jQuery);
</script>

</body>

</html>

