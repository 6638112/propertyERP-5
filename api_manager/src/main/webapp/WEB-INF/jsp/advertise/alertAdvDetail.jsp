<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>弹框广告详情</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css">
</head>
<body>
	<div class="info">
        <h2>
        	<c:choose>
        		<c:when test="${advType eq 1}">首页弹窗广告详情</c:when>
        		<c:when test="${advType eq 2}">首页拦腰广告详情</c:when>
        		<c:when test="${advType eq 3}">到家广告详情</c:when>
        		<c:when test="${advType eq 4}">街坊广告详情</c:when>
        		<c:when test="${advType eq 5}">到家底部广告详情</c:when>
        		<c:when test="${advType eq 6}">微信购物小票广告</c:when>
                <c:when test="${advType eq 7}">车禁缴费广告</c:when>
                <c:when test="${advType eq 8}">体验店banner广告</c:when>
        	</c:choose>
        </h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <tr>
                <td width="20%"><div class="list-name"><span class="red">*</span> 广告名称：</div></td>
                <td>${advertise.tittle}</td>
            </tr>
            <c:if test="${advType == 5}">
                <tr>
					<td width="20%"><div class="list-name"><span class="red">*</span> 第一行文案：</div></td>
					<td>${lines[0]}</td>
				</tr>
				<tr>
					<td width="20%"><div class="list-name"><span class="red">*</span> 第二行文案：</div></td>
					<td>${lines[1]}</td>
				</tr>
				<tr>
					<td width="20%"><div class="list-name"><span class="red">*</span> 第三行文案：</div></td>
					<td>${lines[2]}</td>
				</tr>
            </c:if>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 推广时间：</div></td>
            <td>${fn:substring(advertise.startTime, 0, 16)} 至 ${fn:substring(advertise.endTime, 0, 16)}</td>
          </tr>
          <c:if test="${advType eq 1}">
          <tr>
            <td><div class="list-name"><span class="red">*</span> 展示频率：</div></td>
            <td><c:if test="${advertise.frequency == 1}">每天一次</c:if>
                <c:if test="${advertise.frequency == 2}">展示期内用户点击后不弹</c:if>
            </td>
          </tr>
          </c:if>
            <c:if test="${advType != 6}">
                <tr>
                    <td><div class="list-name">APP版本起始：</div></td>
                    <td>
                        <span>开始：${empty advertise.version ? "不限":advertise.version}</span>
                        <span style="margin-left: 3em;">结束：${empty advertise.maxVersion ? "不限":advertise.maxVersion}</span>
                    </td>
                </tr>
            </c:if>

            <c:if test="${(not empty advType) and (advType ne 4)}">
	            <tr>
	                <td><div class="list-name">code：</div></td>
	                <td>${advertise.code}</td>
	            </tr>
            </c:if>
          <c:if test="${(advType eq 1) or (advType eq 2) or (advType eq 3) or (advType eq 8)}">
	          <tr>
	            <td><div class="list-name"><span class="red">*</span> 跳转类型：</div></td>
	            <td>
            		<c:if test="${((advType eq 1) or (advType eq 2)) and (advertise.type eq 2)}">产品(APP)</c:if>
            		<c:if test="${(advType eq 3) and (advertise.type eq 5)}">产品(APP)</c:if>
                    <c:if test="${(advType eq 8) and (advertise.type eq 5)}">商品详情（商品ID: ${advertise.linkUrl}）</c:if>
	                <c:if test="${advertise.type eq 1}">H5页面</c:if>
	                <c:if test="${advertise.type eq 4}">不跳</c:if>
	            </td>
	          </tr>  
	          <tr class="swap-con swap-box-2 <c:if test="${(((advType eq 1) or (advType eq 2)) and (advertise.type ne 2)) or ((advType eq 3) and (advertise.type ne 5)) or (advType eq 8)}">dsn</c:if>">
	            <td><div class="list-name"><span class="red">*</span> 跳转页面：</div></td>
	            <td>
	            	<c:choose>
	            		<c:when test="${advType eq 1}">
	            			<c:if test="${advertise.iosAddr == 'wuyebaoxiu'}">物业报修</c:if>
			                <c:if test="${advertise.iosAddr == 'wuyegonggao'}">物业公告</c:if>
			                <c:if test="${advertise.iosAddr == 'zhinengtingche'}">智能停车</c:if>
			                <c:if test="${advertise.iosAddr == 'shangmenfuwu'}">上门服务</c:if>
			                <c:if test="${advertise.iosAddr == 'dianpuliebiao'}">店铺列表</c:if>
			                <c:if test="${advertise.iosAddr == 'chaoshishouye'}">超市首页</c:if>
			                <c:if test="${advertise.iosAddr == 'wuyejiaofei'}">物业缴费</c:if>
	            		</c:when>
	            		<c:when test="${advType eq 2}">
	            			<c:if test="${advertise.code == 'superRepair'}">维修</c:if>
			                <c:if test="${advertise.code == 'wuyejiaofei'}">物业缴费</c:if>
	            		</c:when>
	            		<c:when test="${advType eq 3}">
	            			${typeName}
	            		</c:when>
	            	</c:choose>
	            </td>
	          </tr>
          </c:if>
          <tr class="swap-con swap-box-1 <c:if test="${(((advType ne 4) and (advertise.type != 1)) or ((advType eq 4) and (empty advertise.linkUrl))) and (advType ne 5)}">dsn</c:if>">
            <td><div class="list-name"><span class="red">*</span> H5页面地址：</div></td>
            <td>${advertise.linkUrl}</td>
          </tr>
            <c:if test="${advType != 6}">
                <tr class="swap-con swap-box-1 <c:if test="${advertise.type != 1}">dsn</c:if>">
                    <td><div class="list-name">与APP交互的H5约定code：</div></td>
                    <td>
                        <input <c:if test="${advertise.type == 1}">value="${advertise.iosAddr}"</c:if> name="ebuyAdvertise.androidAddr" type="text" class="input_text w150" placeholder="安卓code" readonly="readonly"> -
                        <input <c:if test="${advertise.type == 1}">value="${advertise.androidAddr}"</c:if> name="ebuyAdvertise.iosAddr" type="text" class="input_text w150" placeholder="IOS code" readonly="readonly"> <span class="red">（无则不填）</span>
                    </td>
                </tr>
            </c:if>

            <tr>
                <td align="right"><span class="red">*</span> 广告图片：</td>
                <td class="item-upload-img">
                    <div class="uploadPreview01 mright6"><input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage02" />
                        <img class="imgPreview" width="82" height="82" src="${picPath}" />
                    </div>
                    <span class="f12">注：建议尺寸640*200，小于200k，仅限一张。</span>
                </td>
            </tr>
            <tr>
                <td width="20%"><div class="list-name"><span class="red">*</span> 用户范围：</div></td>
                <td>
                    <c:if test="${areaType == 1}">全国范围</c:if>
                    <c:if test="${areaType == 4}">
                        <ul class="address-list selected-box">
                            <c:forEach items="${areas}" var="city">
                                <li class="posrelative address-selected"><span class="address-name">${city.cityName}-${city.blockName}</span></li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${areaType == 3}">
                        <ul class="address-list selected-box">
                            <c:forEach items="${areas}" var="gb">
                                <li class="posrelative address-selected"><span class="address-name">${gb.gbName}</span></li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </td>
            </tr>
        </table>
        <input class="input-btn w200" type="button" value="返回" onclick="history.back();" style="height:40px;margin-top: 10px;"/>
        <div class="h30"></div>
	</div>
</body>
</html>
