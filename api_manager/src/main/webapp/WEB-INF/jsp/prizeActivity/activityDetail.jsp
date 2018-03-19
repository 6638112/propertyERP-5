<%@page import="com.cnfantasia.server.api.commSysPara.constant.SysParamKey"%>
<%@page import="com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>//" target="_blank"/>
<title>轻应用活动运营-活动管理-活动详情</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/clearbox.css" />
</head>

<body>
<div class="info">
    <form class="inputform">
        <h2>活动详情</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">活动基本信息</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 活动名称：</div></td>
            <td>${detail.name}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 活动时间：</div></td>
            <td>${detail.startTime} 至 ${detail.endTime}</td>
          </tr>
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">活动分享信息</div></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 分享链接文案：</div></td>
            <td>${detail.shareText}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 分享链接图标：</div></td>
            <td>
                <ul class="menu-img">
                    <li><a href="${shareIconPath}" rel="clearbox[test2]"><img src="${shareIconPath}" border="0" /></a></li>
                </ul>
            </td>
          </tr>
        </table>
        <div class="mtop40 f14"><span class="red">*</span> <span class="bold">是否开启活动：</span> 
	        <input class="mtop3" name="activityStatus" value="2" type="radio" disabled="disabled" <c:if test="${detail.activityStatus == 2}">checked="checked"</c:if> />是 
	        <input class="mtop3 mleft20" name="activityStatus" value="1" type="radio" disabled="disabled" <c:if test="${detail.activityStatus == 1}">checked="checked"</c:if> datatype="*" nullmsg="请选择是否开启活动！"/> 否
        </div>
        <h2 class="mtop40 f14">活动抽奖配置</span></h2>
        <table class="info-list-02 f14" border="0" cellpadding="0" cellspacing="1">
	       	<c:forEach items="${detail.msPrizeActHasOptList}" var="it">
      			<tr class="prize-single pointer">
		            <td><span class="black bold mar-right5">${it.msPrizeOption.name}</span></td>
		            <td><span class="right prize-list-arrow animated rotateOut"></span>有效期：${it.msPrizeOption.valiStartTime} 至 ${it.msPrizeOption.valiEndTime}</td>
				</tr>
				<tr class="prize-info nobg">
				  <td colspan="2" bgcolor="#f5f5f5">
				      <div class="prize-info-text">${it.msPrizeOption.comment}<br /></div>
				      <table class="ranking-info" border="0" cellpadding="0" cellspacing="1">
				        <tr class="nobg">
				          <td width="90"><span class="red">*</span> 派奖额度：</td>
				          <td>${it.totalMaxCount}份</td>
				        </tr>
				        <tr class="nobg">
				          <td><span class="red">*</span> 日均派发数：</td>
				          <td>${it.dayMaxCount}份</td>
				        </tr>
				      </table>
				  </td>
				</tr>
	       	</c:forEach>
        </table>
    </form>
</div>

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/clearbox.js"></script>
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
