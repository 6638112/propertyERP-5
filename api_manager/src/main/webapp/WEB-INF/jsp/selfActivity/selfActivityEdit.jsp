<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>配置活动页面</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>

<body>
<div class="info">
       <!--图片模版-->
       <table class="info-list-02 act-info-box mtop10 dsn" border="0" cellpadding="0" cellspacing="1">
         <tr>
           <td width="20%"><div class="list-name"><span class="red">*</span> 活动图片：</div></td>
           <td class="item-upload-img">
               <div class="uploadPreview01 img-shape-rectangle mright6">
               	<input type="file" name="photoimage" class="uploadImage02" />
               	<img class="imgPreview" width="400" height="100" src="../images/addimg_rectangle.jpg" />
               </div>
               <input class="img-upload-val dsn" type="text" ignore="ignore" value="" datatype="*" nullmsg="请上传商品图片！" />
               <span class="Validform_checktip"></span>
           </td>
         </tr>
         <tr>
           <td><div class="list-name"><span class="red">*</span> 链接类型：</div></td>
           <td>
               <select class="select_normal" name="jumpType">
                   <option value="0">不跳转</option>
                   <option value="1">社区店商品详情页</option>
                   <option value="2">到家服务商品详情页</option>
                   <option value="3">H5页面</option>
               </select>
           </td>
         </tr>
         <tr class="swap-info swap-val-1 dsn">
           <td><div class="list-name"><span class="red">*</span> 链接商品：</div></td>
           <td>
           	<input class="input-btn w120 btn-item-search mtop12" type="button" value="选择商品">
           	<input type="hidden" class="input_text pp w120" ignore="ignore" readonly="readonly" maxlength="200" datatype="*" nullmsg="请选择链接商品" />
           </td>
         </tr>
         <tr class="swap-info swap-val-2 dsn">
           <td><div class="list-name"><span class="red">*</span> 链接商品：</div></td>
           <td>
           	<input class="input-btn w120 btn-item-search mtop12" type="button" value="选择商品">
           	<input type="hidden" class="input_text pp w120" ignore="ignore" readonly="readonly" maxlength="200" datatype="*" nullmsg="请选择链接商品" />
           </td>
         </tr>
         <tr class="swap-info swap-val-3 dsn">
           <td><div class="list-name"><span class="red">*</span> H5页面链接：</div></td>
           <td><input type="text" class="h5LinkUrl input_text pp w500" maxlength="200" ignore="ignore" datatype="*" nullmsg="请填写页面链接" /></td>
         </tr>
         <input type="hidden" name="jumpParam" value="-1" />
         <tr>
           <td colspan="2" align="center"><a class="blue btn-delete-row" href="#">删除该行</a></td>
         </tr>
       </table>
    <form id="activityForm" class="inputform" method="post" action="<%=basePath%>/selfActivity/update.json" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${sad.id }" />
        <h2>配置活动页面</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">活动信息</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 活动名称：</div></td>
            <td><input type="text" class="input_text pp w500" name="name" maxlength="45" datatype="*" nullmsg="请填写活动名称" value="${sad.name }" /></td>
          </tr>
        </table>
        
        <table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2">
            	<div align="left" class="f14 bold">模版内容<span class="f12 mleft10">(建议图片尺寸640x240像素，大小2M以内)</span></div>
            </td>
          </tr>
        </table>
        <!--第一张图片-->
        <c:forEach items="${sad.sadList }" var="item" varStatus="status">
        <table class="info-list-02 act-info-box" border="0" cellpadding="0" cellspacing="1">
          	  <input type="hidden" name="sadId" value="${item.id }" />
	          <tr>
	            <td width="20%"><div class="list-name"><span class="red">*</span> 活动图片：</div></td>
	            <td class="item-upload-img">
	                <div class="uploadPreview01 img-shape-rectangle mright6">
	                	<input type="file" name="photoimage" class="uploadImage02" />
	                	<img class="imgPreview" width="400" height="100" src='<%=OmsSysParamManager.getImageServerUrl(PathConstants.SELF_ACTIVITY_PIC)+PathConstants.SELF_ACTIVITY_PIC%>${item.picUrl}?x-oss-process=image/resize,m_fill,w_160,h_160/format,jpg/interlace,1/quality,q_80' />
	                </div>
	                <input class="img-upload-val dsn" type="text" value="1" datatype="*" nullmsg="请上传商品图片！" />
	            </td>
	          </tr>
	          <tr>
	            <td><div class="list-name"><span class="red">*</span> 链接类型：</div></td>
	            <td>
	                <select class="select_normal" name="jumpType"> 
	                    <option value="0" <c:if test="${item.jumpType == 0}"> selected </c:if>>不跳转</option>
	                    <option value="1" <c:if test="${item.jumpType == 1}"> selected </c:if>>社区店商品详情页</option>
	                    <option value="2" <c:if test="${item.jumpType == 2}"> selected </c:if>>到家服务商品详情页</option>
	                    <option value="3" <c:if test="${item.jumpType == 3}"> selected </c:if>>H5页面</option>
	                </select>
	            </td>
	          </tr>
	          
	          <tr class="swap-info swap-val-1 <c:if test="${item.jumpType != 1}"> dsn </c:if>">
	            <td><div class="list-name"><span class="red">*</span> 链接商品：</div></td>
	            <td class="height48">
		        	<c:if test="${item.jumpType == 1}"> 
		            	<ul class="address-list">
			        		<li class="posrelative address-selected01 address-selected-1" data-class="address-selected-1">
				        			<span class="address-name">${paramList[status.index].name}</span><br>
				        			<span class="grey">ID：<span class="data-obj-id">${paramList[status.index].id}</span></span><!-- <span class="grey mleft10">供应商：<span class="data-obj-name">海吉星</span></span> -->
			        		</li>
		            	</ul>
					</c:if>
	            	<input class="input-btn w120 btn-item-search mtop12 item-class-a${status.index}" type="button" value="<c:if test='${item.jumpType == 1}'>重新选择商品</c:if><c:if test='${item.jumpType != 1}'>选择商品</c:if>" data-index="item-class-a${status.index }">
            		<input type="hidden" class="input_text pp w120" <c:if test='${item.jumpType == 1}'>value="1"</c:if> <c:if test='${item.jumpType != 1}'>ignore="ignore"</c:if> readonly="readonly" maxlength="200" datatype="*" nullmsg="请选择链接商品" />
	            	<span style="padding-left: 1px;"></span>
	            </td>
	          </tr>
	          <tr class="swap-info swap-val-2  <c:if test="${item.jumpType != 2}"> dsn </c:if>">
	            <td><div class="list-name"><span class="red">*</span> 链接商品：</div></td>
	            <td>
		        	<c:if test="${item.jumpType == 2}"> 
		            	<ul class="address-list">
			        		<li class="posrelative address-selected01 address-selected-1" data-class="address-selected-1">
				        			<span class="address-name">${paramList[status.index].name}</span><br>
				        			<span class="grey">ID：<span class="data-obj-id">${paramList[status.index].id}</span></span><!-- <span class="grey mleft10">供应商：<span class="data-obj-name">海吉星</span></span> -->
			        		</li>
		            	</ul>
					</c:if>
	            	<input class="input-btn w120 btn-item-search mtop12 server-class-b${status.index}" type="button" value="<c:if test='${item.jumpType == 2}'>重新选择商品</c:if><c:if test='${item.jumpType != 2}'>选择商品</c:if>" data-index="server-class-b${status.index}">
	            	<input type="hidden" class="input_text pp w120" <c:if test='${item.jumpType == 2}'>value="1"</c:if> <c:if test='${item.jumpType != 2}'>ignore="ignore"</c:if> readonly="readonly" maxlength="200" datatype="*" nullmsg="请选择链接商品" />
	            	<span style="padding-left: 1px;"></span>
	            </td>
	          </tr>
	          <tr class="swap-info swap-val-3  <c:if test="${item.jumpType != 3}"> dsn </c:if>">
	            <td><div class="list-name"><span class="red">*</span> H5页面链接：</div></td>
	            <td><input type="text" class="h5LinkUrl input_text pp w500" maxlength="200" <c:if test='${item.jumpType != 3}'>ignore="ignore"</c:if> 
	            	datatype="*" nullmsg="请填写页面链接" value="<c:if test='${item.jumpType == 3}'>${paramList[status.index]}</c:if>" /> </td>
	          </tr>
	          <c:if test='${item.jumpType == 1 || item.jumpType == 2}'>
          	  	<input type="hidden" name="jumpParam" value="${paramList[status.index].id}" />
          	  </c:if>
	          <c:if test='${item.jumpType == 3}'>
          	  	<input type="hidden" name="jumpParam" value="${paramList[status.index]}" />
          	  </c:if>
	          <c:if test='${item.jumpType == 0}'>
          	  	<input type="hidden" name="jumpParam" value="-1" />
          	  </c:if>
	          
	          <c:if test="${status.index > 0 }">
		          <tr>
		            <td colspan="2" align="center"><a class="blue btn-delete-row" href="#">删除该行</a></td>
		          </tr>
	          </c:if>
        </table>
        </c:forEach>
        
        <div class="act-info-bottom">
	        <input id="sumActivity" class="info-btn save-set-prize-info-btn" type="submit" value="保 存" />
	        <input class="info-btn mar-left15 info-btn01 mtop0 btn-add-row" type="button" value="新增一行">
	        <div class="h30"></div>
        </div>
    </form>
</div>

<div class="layer-bill dsn" style="width: 760px; min-height: 520px; padding-bottom: 20px;">
    <form class="inputform layerform">
	    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	      <tr class="search-input01">
	        <td width="20%"><div class="list-name"><span class="red">*</span> 商品搜索：</div></td>
	        <td>
	        	<input id="itemSearchInput" type="text" maxlength="8" class="input_text w120" placeholder="请输入关键字" />
				<input class="input-btn item-search-btn" type="button" value="搜索">
	        </td>
	      </tr>
	      <tr class="city-con01">
	        <td><div class="list-name">搜索结果：</div></td>
	        <td>
	        	<ul class="address-list search-box01">
	        	</ul>
	        </td>
	      </tr>
	      <tr class="city-con01">
	        <td><div class="list-name">已选商品：</div></td>
	        <td>
	        	<ul class="address-list selected-box01">
	        		<li class="posrelative address-selected01 dsn">
	        			<span class="address-name"></span><br>
	        			<span class="grey">ID：<span class="data-obj-id"></span></span><!-- <span class="grey mleft10">供应商：<span class="data-obj-name">海吉星</span></span> -->
	        			<div class="icon-delete"></div>
	        		</li>
	        	</ul>
	        	<input type="hidden" class="select-input01 input_text w80 dsn" datatype="*" nullmsg="请选择商品！" />
	        </td>
	      </tr>
	    </table>
	    <div class="t_center mtop10">
	    	<input id="checkLayerClose" class="info-btn small-btn w150" type="submit" value="确 定">
	    </div>
	    <div class="h30"></div>
    </form>
</div>
</body>
<script type="text/javascript" src="${resourcePath}/commonjs/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${resourcePath}/commonjs/layer/layer.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/jquery.itemScreen.js"></script>
<script type="text/javascript" src="../js/page.itemSelect.js"></script>
</html>
