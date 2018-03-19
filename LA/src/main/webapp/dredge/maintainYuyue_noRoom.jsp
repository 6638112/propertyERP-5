<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>解放区上门服务预约</title>
<link rel="stylesheet" href="css/shopping.common.css?v20160105b" type="text/css">
	
<link href="${resourcePathHttps}/commoncss/mobiscroll_002.css" rel="stylesheet" type="text/css">
<link href="${resourcePathHttps}/commoncss/mobiscroll.css" rel="stylesheet" type="text/css">
<link href="${resourcePathHttps}/commoncss/mobiscroll_003.css?v20160107c" rel="stylesheet" type="text/css">
</head>

<body class="pos-relative bggrey">
<form class="inputform" enctype="multipart/form-data" action="addDredgeBill.do" method="post" onsubmit="return false;">
<div id="wrapBox" class="sectionBox shop-part01 bggrey pos-relative minheight100">
	<input name="dredgeTypeId" id="dredgeTypeId" type="hidden" value="${param.dredgeTypeId }">
	<input name="subTypeId" id="subTypeId" type="hidden" value="">
	<input name="dredgeProductId" type="text" hidden="hidden" value="" />
	<input name="specList" type="text" hidden="hidden" value="[]" />
	<input name="productList" type="text" hidden="hidden" value="[]" />
	<input name="cityId" type="text" hidden="hidden" value="${sessionScope.cityId}" />
	<input name="addressBlockId" type="text" hidden="hidden" value="" />
	<input name="gbId" type="text" hidden="hidden" value="" />
	<input name="gbSignStatus" type="text" hidden="hidden" value="" />
	<input name="changeDefaultRoom" type="text" hidden="hidden" value="0" />
	<input name="isAddNewAddr" type="text" hidden="hidden" value="1" />
	
    <div id="bookStep033">
	    <section class="sectionBox item-details-info pos-relative bggrey">

			<div id="citySelectBox" class="displaybox register-list">
				<div id="addressIcon" class="div-item-icon list-icon-yy01"></div>
				<ul class="register-list boxflex01">
					<li class="borderbottomgrey displaybox">
						<div id="serverAddressBox" class="boxflex01">
							<div id="addressTxt" class="f16 dsn"></div>
							<div id="getAddressBtn" class="f16">请完善地址信息</div>
							<input id="addressValid" type="text" hidden="hidden" value="" dataType="*" nullmsg="请完善地址信息" />
						</div>
						<div class="box-arrow w16"></div>
					</li>
					<!-- <li class="borderbottomgrey">
						<select  id="block" class="input-text wp100 list-arrow" name="addressBlockId" ignore="ignore" datatype="*" nullmsg="请完善上门地址！">
							<option value="">选择区</option>
						</select>
					</li> -->
					<!-- <li class="pos-relative searchBox borderbottomgrey" style="min-height:37px;">
						<div class="header-title mtop5 displaybox shop-create-header">
							<input id="areaCheckedInput" hidden="hidden" datatype="*" nullmsg="请选择小区！"  />
							<input id="shopCreateSearchBox" class="order-search boxflex01 shop-create-search" style="text-indent:0" placeholder="请输入小区名称" readonly="readonly" type="text" name="search" />
						</div>
					</li> -->
				</ul>
			</div>

			<div class="displaybox register-list">
				<div class="div-item-icon list-icon-yy02"></div>
				<ul class="register-list boxflex01">
					<li class="borderbottomgrey displaybox">
						<div class="item-standard-name height36 f16 boxflex01">预约时间</div>
						<input class="input-text wp100 t-right box-arrow" type="text" placeholder="请输入预约时间" value="" name="expectDate" id="appDateTime" datatype="*" nullmsg="请输入预约时间！" />
					</li>
				</ul>
			</div>

			<div class="displaybox register-list">
				<div class="div-item-icon list-icon-yy03"></div>
				<ul class="register-list boxflex01">
					<li class="borderbottomgrey displaybox">
						<div class="item-standard-name height36 f16 boxflex01">联系人</div>
						<input class="input-text wp100 t-right" type="text" placeholder="请输入联系人姓名" name="linkName" value="" id="contactName" maxlength="10" datatype="*" nullmsg="请输入联系人姓名！" />
					</li>
				</ul>
			</div>

			<div class="displaybox register-list">
				<div class="div-item-icon list-icon-yy04"></div>
				<ul class="register-list boxflex01">
					<li class="borderbottomgrey displaybox">
						<div class="item-standard-name height36 f16 boxflex01">联系电话</div>
						<input id="validPhoneNum" class="input-text wp100 t-right" type="text" name="tel" placeholder="请输入联系电话" value="" maxlength="11" datatype="m" nullmsg="请输入联系电话" errormsg="请输入正确的手机号码格式！" />
					</li>
				</ul>
			</div>

			<div class="displaybox register-list">
				<div class="div-item-icon list-icon-yy03"></div>
				<ul class="register-list boxflex01">
					<li class="displaybox">
						<div class="item-standard-name height36 f16 boxflex01">推荐人</div>
						<input class="input-text wp100 t-right <c:if test='${not empty sessionScope.Recommend_User_Phone}'>grey</c:if>" name="referrerMobile" type="text" 
						<c:if test='${not empty sessionScope.Recommend_User_Phone}'>value="${sessionScope.Recommend_User_Phone}" readonly="readonly"</c:if> 
						maxlength="11" placeholder="请输入推荐人手机号" ignore="ignore" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码格式！" />
					</li>
				</ul>
			</div>
			<section class="divide-box bordertbgrey"></section>

	    </section>
    </div>
	<div id="bookStep011">
		<section class="sectionBox padding-menu-yuyue bggrey item-details-info pos-relative">

			<ul class="register-list">
				<li class="grey borderbottomgrey"><textarea class="area-text wp100 text_limit" style="padding-top: 5px;" rows="5" type="text" placeholder="添加现状说明，便于师傅提前准备..." name="dredgeContent"  ></textarea></li>
			</ul>

			<ul class="register-list borderbottomgrey" style="padding-bottom: 5px;">
				<li class="img-add-num01"><span class="black f15">添加真相图</span><span class="grey f14" style="padding-left: 15px;">最多可上传3张</span></li>
				<li class="overvisible" style="padding-top: 0px;">
					<div class="uploadPreview01 mright6 mtop10 marb10"><input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="picInfo" class="uploadImage01 fimg1 w100 height100" /></div>
					<div class="uploadPreview01 mtop10 marb10 img-new-add img-hide"><div class="img-delete-btn">删除</div><input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="picInfo" class="uploadImage01 fimg1 w100 height100" /></div>
					<div class="uploadPreview01 mtop10 img-add-btn"></div>
				</li>
			</ul>
			<ul class="bottom-menu-box displaybox t-center">
				<li class="p00 boxflex01"><input class="btn-submit bgred noradius noborder btnSubmit" type="button" name="button" value="确认预约信息" /></li>
			</ul>
		</section>
	</div>
	<div class="h57"></div>
		
	<section class="sectionBox wrap-bg pop-box02 dsn">
		<div class="tips-box pay-tips bgred pb15">
			<img class="disblock" src="images/pay-tips.png"/>	
		</div>
	</section>
</div>
</form>

<form class="inputform-location-box" method="post" onsubmit="return false;">
<div class="shop-part02 getlocation-box dsn">    
    <section class="sectionBox item-details-info overscroll getlocation-box pos-relative">
        <section class="sectionBox password-mind-box borderbottomgrey displaybox">
        	<div id="closeLocationBox" class="w10 p015 lineheight0"><img class="" src="../images/back_black.png"></div>
            <div class="grey boxflex01">请完善地址信息</div>
        </section>
        <ul class="register-list">
        	<li class="borderbottomgrey">
  				<select id="province" class="province select_normal input-text wp100 list-arrow" name="province" data-first-title="选择省" title="选择省" >
                   	<option value="">选择省</option>
                   	<c:forEach items="${pcbList}" var="pcb" >
                   		<option value="${pcb.id}">${pcb.name}</option>
                  	</c:forEach>
            	</select> 
        	</li>   
            <li class="borderbottomgrey">
            	<select id="city" name="city" class="input-text wp100 list-arrow">
                	<option value="">选择城市</option>
                </select> 
            </li>
            <li>
	            <select id="block" name="" class="input-text wp100 list-arrow">
	                <option value="">选择区县</option>
	            </select>
            </li>
        </ul>
		<div class="modifyAddressBox">
	        <ul id="citySelectNewBox" class="register-list">
	            <li class="pos-relative searchBox bordertopgrey" style="min-height:37px;"><div class="mtop5 displaybox shop-create-header"><input id="shopCreateSearchInput" class="order-search boxflex01 shop-create-search inputBorder" placeholder="请输入小区名称" type="text" name="search" /><div class="quick-delete hide"></div></div></li>
	        </ul>
		    <section class="sectionBox paddingMenu item-details-info pos-relative">
		        <li class="displaybox shop-create-check-single mleft15 bordertopgrey pointer hide">
        			<span class="area-map-icon"></span>
		            <div class="area-info-text boxflex01 mleft5">
		                <span class="area-info-name f16" gbId="" ></span>
		                <br><span class="area-info-address grey"></span>
		            </div>
		        </li>
		        <ul class="register-list shop-create-check">
		            
		        </ul>        
			</section>
			<div class="h57 bggrey paddingbottom dsn"></div>
		</div>
        <div class="item-list-arrow-box p00 exchang-fixed noborder dsn">
        	<input class="btn-submit btn-next noradius bordertbgrey btnSubmit bgwhite red" type="submit" name="button" value="确定">
        </div>
    </section>
</div>
</form>

<!-- 地址列表 -->
<div id="serverAddressList" class="sectionBox dsn">
    <section class="sectionBox password-mind-box borderbottomgrey displaybox">
    	<div id="closeAddrListBox" class="w10 p015 lineheight0"><img class="" src="../images/back_black.png"></div>
        <div class="grey boxflex01">当前门牌</div>
    </section>
    <div class="list-box bgwhite bordertopgrey displaybox">
        <div id="currAddr" class="item-standard-name f16 boxflex01 list-icon-check">
			<div id="currAddrText" class="f16">
				${sessionScope.regist3rdResponse.defaultRoomInfo.groupBuilding}
				${sessionScope.regist3rdResponse.defaultRoomInfo.building}
				${sessionScope.regist3rdResponse.defaultRoomInfo.roomNum}
			</div>
			<span id="currBlockAddrText" class="f14 grey">
				${sessionScope.regist3rdResponse.defaultRoomInfo.city} ${sessionScope.regist3rdResponse.defaultRoomInfo.block}
			</span>
        </div>
    </div>

	<ul id="otherDregedAddr" class="boxflex01 dsn">
		<div class="displaybox bggrey"><span class="grey lineheight40 mleft15 otherAddrDesc dsn">其他地址</span></div>
	</ul>

	<li class="otherAddrLi dsn">
		<div class="list-box bgwhite bordertopgrey displaybox">
			<div class="item-standard-name orther-address-info f16 boxflex01 pointer">
				<div class="f16 addrText"></div>
				<div class="f14 blockAddrText grey"></div>
				<div class="f12 validText grey dsn"><img class="mright5" style="width: 12px; vertical-align: middle; margin-top: -2px;" src="images/icon_warning.png" />该地址不支持此项服务</div>
				<span class="f14 validText grey dsn">改地址不支持此项服务</span>
			</div>
			<div class="w40 f12 t-right lineheight65 disblock red"><a class="disblock updAddr red" href="#">修改</a></div>
			<div class="w40 f12 t-right lineheight65"><a class="disblock delAddr red" href="#">删除</a></div>
		</div>
		<section class="divide-box bordertopgrey"></section>
	</li>

	<div class="h57 bggrey"></div>
	<ul class="bottom-menu-box displaybox t-center">
		<li class="p00 boxflex01">
			<input class="btn-submit bgred noradius noborder btnSubmit" id="addNewAddrBtn" type="button" name="button" value="新增地址" />
		</li>
	</ul>
</div>



<!-- <div class="sectionBox shop-part02 bggrey dsn">
	<div class="modifyAddressBox">
        <ul id="citySelectNewBox" class="register-list">
            <li class="pos-relative searchBox" style="min-height:37px;"><div class="mtop5 displaybox shop-create-header"><input id="shopCreateSearchInput" class="order-search boxflex01 shop-create-search inputBorder" placeholder="请输入小区名称" type="text" name="search" /><div class="quick-delete dsn"></div></div></li>
        </ul>
	    <section class="sectionBox paddingMenu item-details-info pos-relative">
	        <li class="displaybox shop-create-check-single hide">
	        	<span class="area-check-icon"></span>
	            <div class="area-info-text boxflex01 bordertopgrey">
	                <span class="area-info-name f16" gbId="" ></span>
	                <br><span class="area-info-address grey"></span>
	            </div>
	        </li>
	        <ul class="register-list shop-create-check">

	        </ul>

		</section>
	</div>
</div> -->


 <ul id="selectCheckedRoomNum" class="register-list dsn">
     <li class="bordertbgrey">
         <select id="areaNameSelected" name="groupBuildingId" class="input-text wp100 areaNameBox" disabled="disabled" >
             <option value="" selected="selected">华海小区</option>
         </select>
     </li>
     <li class="borderbottomgrey">
         <select name="buildingName" id="buildingNum" class="input-text wp100 list-arrow" onchange="setUnitSelect(this, 'unitNum') " datatype="*" nullmsg="请选择楼栋号！" >
             <option value="">请选择楼栋号</option>
         </select>
     </li>
     <li class="borderbottomgrey unitLi">
         <select name="unitName" id="unitNum" class="input-text wp100 list-arrow" onchange="setRoomNumSelect('roomNum')" >
             <option value="">请选择单元号</option>
         </select>
     </li>
     <li class="borderbottomgrey">
         <select name="realRoomId" id="roomNum" class="input-text wp100 list-arrow" datatype="*" nullmsg="请选择房间号！" >
             <option value="">请选择房间号</option>
         </select>
     </li>
 </ul>

 <ul id="selectUncheckedRoomNum" class="register-list dsn">
     <li class="bordertbgrey">
         <select name="groupBuildingId" class="input-text wp100 areaNameBox" disabled="disabled" >
             <option value="" selected="selected">华海小区</option>
         </select>
         <input type="hidden" name="groupBuildingName" />
     </li>
   <li class="borderbottomgrey">
       <div class="displaybox">
           <div class="item-standard-name height36 f16 boxflex01">请填写楼栋号</div>
           <div class="boxflex01 f16 t-right grey"><input name="buildingName"  class="input-text wp100 t-right" type="text" placeholder="如：1栋" value="" maxlength="14" datatype="*" nullmsg="请填写楼栋号！" /></div>
       </div>
   </li>
   <li class="borderbottomgrey">
       <div class="displaybox">
           <div class="item-standard-name height36 f16 boxflex01">请填写单元号(选填)</div>
           <div class="boxflex01 f16 t-right grey"><input name="unitName" class="input-text wp100 t-right" type="text" placeholder="如：2" value="" maxlength="6"/></div>
       </div>
   </li>
   <li class="borderbottomgrey">
       <div class="displaybox">
           <div class="item-standard-name height36 f16 boxflex01">请填写门牌号</div>
           <div class="boxflex01 f16 t-right grey"><input name="roomNum"  class="input-text wp100 t-right" type="text" placeholder="如：102" value="" maxlength="6" datatype="*" nullmsg="请填写门牌号！" /></div>
       </div>
   </li>
 </ul>
    
<ul id="writeRoomNum" class="register-list dsn">
    <li class="bordertbgrey">
        <div class="displaybox">
            <div class="item-standard-name height36 f16 boxflex01">请填写小区名</div>
            <div class="boxflex01 f16 t-right grey"><input name="groupBuildingName" class="input-text wp100 t-right" type="text" placeholder="如：玫瑰园" value="" maxlength="14" datatype="*" nullmsg="请填写小区名！" /></div>
        </div>
    </li>
    <li class="borderbottomgrey">
        <div class="displaybox">
            <div class="item-standard-name height36 f16 boxflex01">请填写楼栋号</div>
            <div class="boxflex01 f16 t-right grey"><input name="buildingName" class="input-text wp100 t-right" type="text" placeholder="如：1栋" value="" maxlength="14" datatype="*" nullmsg="请填写楼栋号！" /></div>
        </div>
    </li>
    <li class="borderbottomgrey">
        <div class="displaybox">
            <div class="item-standard-name height36 f16 boxflex01">请填写单元号(选填)</div>
            <div class="boxflex01 f16 t-right grey"><input name="unitName" class="input-text wp100 t-right" type="text" placeholder="如：2" value="" maxlength="6" /></div>
        </div>
    </li>
    <li class="borderbottomgrey">
        <div class="displaybox">
            <div class="item-standard-name height36 f16 boxflex01 ">请填写门牌号</div>
            <div class="boxflex01 f16 t-right grey"><input name="roomNum" class="input-text wp100 t-right" type="text" placeholder="如：102" value="" maxlength="6" datatype="*" nullmsg="请填写门牌号！" /></div>
        </div>
    </li>
</ul>
<div class="sectionBox loading grey bordertopgrey dsn"><img src="images/loading01.gif" /> 加载中…</div>
<div class="sectionBox creatInfo grey bordertopgrey dsn borderbottomgrey pointer">搜索不到您的小区？<div class="blue" style="display:inline-block">手动添加小区信息</div></div>
<div class="sectionBox reSearch grey bordertopgrey dsn borderbottomgrey pointer"><div class="blue">重新搜索</div></div>

<section class="pop-tips1 upload-text hide">
	<div class="pop-tips-text">信息提交中，请稍后…</div>
</section>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.form.js"></script>
<script src="${resourcePathHttps}/commonjs/imgUpload.js?v20160201"></script>
<script src="${resourcePathHttps}/commonjs/mobiscroll_002.js?v20160107" type="text/javascript"></script>
<script src="${resourcePathHttps}/commonjs/mobiscroll_004.js" type="text/javascript"></script>
<script src="${resourcePathHttps}/commonjs/mobiscroll.js" type="text/javascript"></script>
<script src="${resourcePathHttps}/commonjs/mobiscroll_003.js" type="text/javascript"></script>
<script src="${resourcePathHttps}/commonjs/mobiscroll_005.js" type="text/javascript"></script>
<script src="${resourcePathHttps}/commonjs/jquery.cookie.js"></script>

<script>
    //获取默认门牌信息
	$.ajax({
		url: "../common/toUrl.do",
		async: false,
		data:{"detailUrl" : "/plotproperty/qryCurrGroupbuildingInfo.json", "isNeedLogin": "1"},
		success: function(data){
			if(data.status === '0000'){
				var dataValue = data.dataValue;

				//保存默认门牌信息到cookie，在预约信息页面获取默认门牌地址
				var defaultRoomAddr01 = dataValue.defaultRoomInfo.city + dataValue.defaultRoomInfo.block;
				var defaultRoomAddr02 = dataValue.defaultRoomInfo.groupBuilding + dataValue.defaultRoomInfo.building + dataValue.defaultRoomInfo.roomNum;
				$.cookie('defaultRoomAddr01', defaultRoomAddr01, { path: '/' });
				$.cookie('defaultRoomAddr02', defaultRoomAddr02, { path: '/' });

		        //更新addressBlockId
				if($('[name=addressBlockId]').length){
					$('[name=addressBlockId]').val(dataValue.blockId);
				}

				//保存默认门牌id及房间号，用来比对是否修改了门牌
				$.cookie('defaultRealRoomId', dataValue.defaultRoomInfo.realRoomId, { path: '/' });
                //判断是否新用户，预约提交时老用户需要设置changeDefaultRoom参数值为0
                $.cookie('changeDefaultRoom', '0', { path: '/' });
            }else{
                //没有默认门牌，则通过定位获取用户当前位置
                $.cookie('changeDefaultRoom', '1', { path: '/' });
			}
		},
		error: function(){
			showMsg('网络不给力，请稍后重试');
		}
	});

</script>

<script src="js/appointment.common.js" type="text/javascript"></script>
<script src="js/shoppingcart.common.js" type="text/javascript"></script>
<script src="js/service.reservation.js?V20171018" type="text/javascript"></script>
</body>
</html>