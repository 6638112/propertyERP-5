<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>师傅备注信息</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/picbox.css">
<style type="text/css">
	.emoji{ width: 18px; height: auto; margin-left: 2px; vertical-align: text-bottom;}
</style>
<script src="https://twemoji.maxcdn.com/2/twemoji.min.js"></script>
</head>

<body>
<div class="info">
        
      <c:if test="${fn:length(processRecordList)>0 }">
       <h2 class="mtop20">师傅服务流程记录</h2>
       <table class="info-list-02" border="0" cellpadding="0" cellspacing="1" style="white-space: normal;">
       	<c:forEach items="${processRecordList }" var="item" varStatus="status">
          <tr>
            <td width="140" align="right">流程记录${fn:length(processRecordList) - (status.count - 1)}</td>
            <td>
            	${item.prAddTimeStr } ${item.prDesc} <br/>
            	<ul class="menu-img">
            		<c:forEach items="${item.picList }" var="pic">
           				<li><a href="${pic }" rel="lightbox-group"><img src="${pic }" border="0" /></a></li>
            		</c:forEach>
          			</ul>
            </td>
          </tr>
       	</c:forEach>
       </table>
      </c:if>
        
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/picbox.js"></script>
<script type="text/javascript">
	twemoji.parse(document.body, {"size":72});
</script>
</html>
