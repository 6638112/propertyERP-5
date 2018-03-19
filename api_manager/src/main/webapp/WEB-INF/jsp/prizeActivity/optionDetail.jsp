<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>//" target="_blank">
<title>轻应用活动运营-奖项管理-奖项详情</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/clearbox.css" />
</head>

<body>
<div class="info">
    <form class="inputform">
        <h2>奖项详情</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">奖项描述</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 奖项名称：</div></td>
            <td>${detail.name}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 奖项有效期：</div></td>
            <td>${detail.valiStartTime} 至 ${detail.valiEndTime}</td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 派奖范围：</div></td>
            <td>
            	<c:forEach var="it" items="${detail.cityNameList}">
            		${it}&nbsp;
            	</c:forEach>
            </td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 奖项说明：</div></td>
            <td>${detail.useDesc}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 奖项图标：</div></td>
            <td>
                <ul class="menu-img">
                    <li><a href="${iconPath}" rel="clearbox[test2]"><img src="${iconPath}" border="0" /></a></li>
                </ul>
            </td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 手气如何：</div></td>
            <td>${detail.luckDesc}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 备注信息：</div></td>
            <td>${detail.comment}</td>
          </tr>
          <tr>
            <td><div class="list-name">跳转URL：</div></td>
            <td><a class="blue" href="${detail.linkUrl}" target="_blank">${detail.linkUrl}</a></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 当前可用奖品总数：</div></td>
            <td>${detail.priGiftCount}份</td>
          </tr>
        </table>
        <div class="mtop40 f14"><span class="red">*</span> <span class="bold">是否开启奖项：</span> 
	        <input class="mtop3" name="status" value="2" type="radio" disabled="disabled" <c:if test="${detail.status == 2}">checked="checked"</c:if> /> 是 
	        <input class="mtop3 mleft20" name="status" value="1" type="radio" disabled="disabled" datatype="*" nullmsg="请选择是否开启奖项！" <c:if test="${detail.status == 1}">checked="checked"</c:if>/> 否
        </div>
        <table class="info-list-02 mtop20 dsn" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">奖项配置</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 奖品信息：</div></td>
            <td><input id="importBill" class="input-btn small-btn w100" type="button" value="奖品详情"/></td>
          </tr>
        </table>
        
    </form>
</div>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/clearbox.js"></script>
<script type="text/javascript" src="js/layer.min.js"></script>
<script type="text/javascript" src="js/jquery.common.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		dragonfly:true,
		ignoreHidden:true
	});
})(jQuery);
</script>
</body>

</html>
