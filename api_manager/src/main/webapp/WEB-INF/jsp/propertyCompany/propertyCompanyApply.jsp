
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>申请高级合作模式</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/jRating.jquery.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		dragonfly:true
	});
});
//检查是否选择了图片文件
function checkImgType(ths) {
    if (ths.value == "") {
        alert("请上传图片");
        return false;
    } else {
        if (!/\.(gif|jpg|jpeg|png|bmp|GIF|JPG|PNG|BMP)$/.test(ths.value)) {
            alert("图片类型必须是.gif,jpeg,jpg,png,bmp中的一种");
            ths.value = "";
            return false;
        }
    }
    return true;
}

function submitValidate(){
	if($("#img1").val() && $("#img2").val()){
		return true;
	}else{
		alert('请上传营业执照和小区管理资格证!');
		return false;
	}
}
</script>
</head>

<body>
<div class="info">
    <form class="inputform" method="post" enctype="multipart/form-data" action="../propertyCompany/applySupper.html">
    	<input type="hidden" name="companyId" value="${companyId }" />
        <h2>申请高级合作模式</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="20%"><div class="list-name">合作模式：</div></td>
            <td>高级合作</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span>营业执照：</div></td>
            <td>
                <input type="file" id="img1" name="photoBusinessLicense" onchange="checkImgType(this)" class="w150" />
            </td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span>物业资质证书：</div></td>
            <td>
                <input type="file" id="img2" name="photoCredentials" onchange="checkImgType(this)" class="w150" />
            </td>
          </tr>
        </table>
        <input id="sumCoperationApply" class="info-btn" type="submit" onclick="return submitValidate();" value="提 交" />
    </form>
</div>

</body>
</html>
