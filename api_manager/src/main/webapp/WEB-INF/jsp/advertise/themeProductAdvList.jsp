<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <%--<base href="<%=basePath%>//"/>--%>
<title>推广商品广告列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>推广商品广告列表</h2>
    <form action="<%=basePath%>/adv/themeProductAdvList.html" method="post">
      <div class="bs-example bgebeb">
        <table class="info-list" border="0">
          <tr>
            <td><div class="list-name">广告名称：</div></td>
            <td><input type="text" name="advName" value="${param.advName}" class="input_text w120 pp"></td>
            <td><div class="list-name">状态：</div></td>
            <td>
              <select name="advStatus" class="select_normal">
                <option value="">全部</option>
                <option value="2" <c:if test="${param.advStatus == 2}">selected</c:if>>进行中</option>
                <option value="3" <c:if test="${param.advStatus == 3}">selected</c:if>>已结束</option>
                <option value="1" <c:if test="${param.advStatus == 1}">selected</c:if>>未开始</option>
              </select>
            </td>
            <td><div class="list-name">筛选时间：</div></td>
            <td><input type="text" value="${param.advStartTime}" name="advStartTime" class="input_text icon_dt pp" id="date01" title="请选择起始时间"> 至
              <input type="text" value="${param.advEndTime}" name="advEndTime" class="input_text icon_dt pp" id="date02" title="请选择结束时间">
            </td>
          </tr>
          <tr>
            <td><div class="list-name">广告类型：</div></td>
            <td>
              <select name="advCode" class="select_normal">
                <option value="">全部</option>
                <option value="EBUY_AD" <c:if test="${param.advCode == 'EBUY_AD'}">selected</c:if>>超市首页</option>
                <option value="LA_EBUY" <c:if test="${param.advCode == 'LA_EBUY'}">selected</c:if>>轻应用首页</option>
                <option value="MAIN_BUSINESS_AD" <c:if test="${param.advCode == 'MAIN_BUSINESS_AD'}">selected</c:if>>首页弹框</option>
                <option value="EBUY_THEME" <c:if test="${param.advCode == 'EBUY_THEME'}">selected</c:if>>社区店主题活动</option>
                <option value="DREDGE_THEME" <c:if test="${param.advCode == 'DREDGE_THEME'}">selected</c:if>>到家主题活动</option>
              </select>
            </td>
          </tr>
          <tr>
            <td colspan="6" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
          </tr>
        </table>
      </div>
    </form>

  <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="total" >
    <display:column title="编号" property="id" />
    <display:column title="起始时间" property="startTime" />
    <display:column title="结束时间" property="endTime" />
    <display:column title="广告名称" property="tittle" />
    <display:column title="广告类型">
      <c:if test="${row.code == 'LA_EBUY'}">轻应用首页</c:if>
      <c:if test="${row.code == 'EBUY_AD'}">超市首页</c:if>
      <c:if test="${row.code == 'MAIN_BUSINESS_AD'}">首页弹框</c:if>
      <c:if test="${row.code == 'EBUY_THEME'}">社区店主题活动</c:if>
      <c:if test="${row.code == 'DREDGE_THEME'}">到家主题活动</c:if>
    </display:column>
    <display:column title="状态">
      <c:choose>
        <c:when test="${row.status==1}">未开始</c:when>
        <c:when test="${row.status==2}">进行中</c:when>
        <c:when test="${row.status==3}">已结束</c:when>
        <c:otherwise>
          未知
        </c:otherwise>
      </c:choose>
    </display:column>
    <display:column title="优先级">
      <input type="number" class="input_text w120" id="${row.id}" value="${row.order}"> <input type="button" class="input-btn" onclick="updAdv(${row.id}, false)" value="保存">
    </display:column>
    <display:column title="添加人" property="addMan" />
    <display:column title="修改人" property="updateMan" />
    <display:column title="操作">
      <a class="blue viewTicket" href="<%=basePath%>/adv/themeProductAdvDetail.html?advId=${row.id}">查看</a>
      <a class="blue viewTicket" href="<%=basePath%>/adv/updThemeProductAdv.html?advId=${row.id}">编辑</a>
      <c:if test="${row.status == 2}">
        <input type="button" class="input-btn" onclick="updAdv(${row.id}, true)" value="结束">
      </c:if>
    </display:column>
  </display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script>
  function updAdv(id, isEndOperation) {
    var order = $('#' + id).val();
      $.ajax({
        type:"post",
        url:"<%=basePath%>/adv/updAdv.html",
        data:{'id': id, 'order': order, 'isEndOperation': isEndOperation},
        beforeSend:function(data){
        },
        success:function(data){
          try {
            data = eval(data);
          } catch (e) {
            data = eval("("+data+")");
          }
          if (data.status == '0000') {
            alert("操作成功!");
            window.location.reload();
          } else {
            alert(data.message);
            return;
          }
        }
      });
  }
</script>
</html>
