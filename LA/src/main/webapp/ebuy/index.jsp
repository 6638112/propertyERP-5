<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="../error/404.jsp" %>
<%@page import="java.util.Calendar"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cleartype" content="on">
		<meta name="HandheldFriendly" content="True">
		<meta name="MobileOptimized" content="320">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<meta name="format-detection" content="telephone=no, email=no">
		<title>解放区商城</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css?V20170712a">
	</head>
	<body>
		<div class="main-part01">  
			<header class="sectionBox fantasia-header displaybox">
				<div id="cityOrientation" class="mleft10 boxflex01">
					<span class="mright5"><span id="curCity" class="f14">正在定位…</span></span>
					<div class="get-city-arrow disinline"></div>
				</div>
				<div class="boxflex01 mright10 t-right mtop3">
					<a class="ptb10" href="../order/qryOrderList.do"><img style="width:20px" src="../images/icon_csdd.png" /></a>
				</div>
			</header>
			<section id="slideBox" class="slideBox mtop0">
			    <div class="bd noborder">
			        <ul>
			            <!-- <li><a class="pic" href="../laGroupPurchase/ziTiDianList.do" target="_blank"><img src="../images/pingou-banner.jpg" /></a></li>
			            <li><a class="pic" href="http://app.jiefangqu.com/" target="_blank"><img src="../images/index-banner.jpg" /></a></li> -->
			        </ul>
			    </div>
			    <div class="hd">
			        <ul></ul>
			    </div>
			</section>
			<section id="tabBox" class="tabBox pos-relative">	
				<div class="shopping-cart-index bggreen"><a href="${pageContext.request.contextPath}/cart/qryBuyCar.do"><div id="cartNum" class="item-num bgwhite">${productCount.productCount}</div><img src="${pageContext.request.contextPath}/images/shoppingcart-white-big.png" /></a></div>
				<%-- <c:if test="${subscribe==0 }">
					<div class="shopping-cart-index jfq-btn"><a href="#erweima"><img class="img01" src="${pageContext.request.contextPath}/images/erweima.jpg" /><img class="img02" src="${pageContext.request.contextPath}/images/guanzhu-text.png" /></a></div>
				</c:if> --%>
			    <div class="hd tab-head">
			        <ul>
			        	<%-- <c:forEach items="${productTypeList}" var="pt"><li class="wp100"><a class="t-left" href="javascript:void(0)" id="${pt.id }">${pt.typeName}</a></li></c:forEach> --%>
			        	<li class="wp100 t-left"><a class="t-left" href="javascript:void(0)">超值单品</a></li>
			        </ul>
			    </div>
			    <div class="bd" id="tabBox-bd">
			        <div class="con">
			        	<div class="p010">
			            	<c:forEach items="${productList2}" var="product" varStatus="status"> 
				                <c:if test="${status.index % 2 ==0}">
				                	<ul class="item-list disbox">
				                </c:if>
				                    <li>
					                    <div class="item-list-small">
						                    <a href="${pageContext.request.contextPath}/product/productDetail.do?ptId=${product.id }" prdtId="${product.id}">
							                    <div class="item-list-img">
							                    	<c:choose>
							                    		<c:when test="${not empty product.picBase}">
							                    			<c:set var="picBasex" value="${product.picBase}"/>
							                    			<%request.setAttribute("dotIndex", pageContext.getAttribute("picBasex").toString().lastIndexOf("."));%>
							                    			<img class="scrollLoading" 
							                    				data-url="${fn:substring(product.picBase, 0, dotIndex)}/640x365${fn:substring(product.picBase, dotIndex, fn:length(product.picBase))}&x-oss-process=image/resize,m_fill,w_338,h_320,limit_0/format,jpg/interlace,1" 
							                    				style="background:url(${pageContext.request.contextPath}/images/loading01.gif) no-repeat center; background-size:26px;" 
							                    				src="${pageContext.request.contextPath}/images/pixel.gif" />
							                    		</c:when>
							                    		<c:otherwise>
							                    			<img class="scrollLoading" 
							                    				data-url="${pageContext.request.contextPath}/images/golf/logo-jfq.png"
							                    				style="background:url(${pageContext.request.contextPath}/images/loading01.gif) no-repeat center; background-size:26px;" 
							                    				src="${pageContext.request.contextPath}/images/pixel.gif" />
							                    		</c:otherwise>
							                    	</c:choose>
												</div>
							                    <p class="item-name ">${product.name}</p>
						                    </a>
				                            <div class="red f16 bold mtop5 m010">￥${product.priceDiscount }</div>
				                            <div class="mtop5 m010">
				                                <div class="market-price-small left">￥${product.price }</div>
				                                <div class="right join-btn" leftCount="${product.leftCount}"><img src="${pageContext.request.contextPath}/images/shoppingcart-yellow.png" /></div>
				                            </div>
					                    </div>
				                    </li>
				                <c:if test="${status.index % 2 ==1}">
					                </ul>
				                </c:if>
			            	</c:forEach>
			            </div>
			            <c:if test="${not empty productList2  }">
			            	<div><a href="http://app.jiefangqu.com/" target="_blank"><img src="${pageContext.request.contextPath}/images/index-banner02.jpg" /></a></div>
			            </c:if>
			        	<div class="p010 mtop10">
			            	<c:forEach items="${productList3 }" var="product" varStatus="status"> 
				                <c:if test="${status.index % 2 ==0}">
				                	<ul class="item-list disbox">
				                </c:if>
				                    <li>
					                    <div class="item-list-small">
						                    <a href="${pageContext.request.contextPath}/product/productDetail.do?ptId=${product.id }" prdtId="${product.id}">
							                    <div class="item-list-img">
							                    	<c:choose>
							                    		<c:when test="${not empty product.picBase}">
							                    			<c:set var="picBasex" value="${product.picBase}"/>
							                    			<%request.setAttribute("dotIndex", pageContext.getAttribute("picBasex").toString().lastIndexOf("."));%>
							                    			<img class="scrollLoading" 
							                    				data-url="${fn:substring(product.picBase, 0, dotIndex)}/640x365${fn:substring(product.picBase, dotIndex, fn:length(product.picBase))}&x-oss-process=image/resize,m_fill,w_338,h_320,limit_0/format,jpg/interlace,1" 
							                    				style="background:url(${pageContext.request.contextPath}/images/loading01.gif) no-repeat center; background-size:26px;" 
							                    				src="${pageContext.request.contextPath}/images/pixel.gif" />
							                    		</c:when>
							                    		<c:otherwise>
							                    			<img class="scrollLoading" 
							                    				data-url="${pageContext.request.contextPath}/images/golf/logo-jfq.png"
							                    				style="background:url(${pageContext.request.contextPath}/images/loading01.gif) no-repeat center; background-size:26px;" 
							                    				src="${pageContext.request.contextPath}/images/pixel.gif" />
							                    		</c:otherwise>
							                    	</c:choose>
							                    </div>
							                    <p class="item-name ">${product.name}</p>
						                    </a>
				                            <div class="red f16 bold mtop5 m010">￥${product.priceDiscount }</div>
				                            <div class="mtop5 m010">
				                                <div class="market-price-small left">￥${product.price }</div>
				                                <div class="right join-btn" leftCount="${product.leftCount}"><img src="${pageContext.request.contextPath}/images/shoppingcart-yellow.png" /></div>
				                            </div>
					                    </div>
				                    </li>
				                <c:if test="${status.index % 2 ==1}">
					                </ul>
				                </c:if>
			            	</c:forEach>
			            </div>
			            <c:if test="${not empty productList3}">
			            	<div><a href="http://app.jiefangqu.com/" target="_blank"><img src="${pageContext.request.contextPath}/images/index-banner03.jpg" /></a></div>
			            </c:if>
			        	<div class="p010 mtop10">
			       				<c:forEach items="${productList1 }" var="product" varStatus="status"> 
					                <c:if test="${status.index % 2 ==0}">
					                	<ul class="item-list disbox">
					                </c:if>
					                    <li>
						                    <div class="item-list-small">
							                    <a href="${pageContext.request.contextPath}/product/productDetail.do?ptId=${product.id }" prdtId="${product.id}">
								                    <div class="item-list-img">
								                    	<c:choose>
								                    		<c:when test="${not empty product.picBase}">
								                    			<c:set var="picBasex" value="${product.picBase}"/>
							                    				<%request.setAttribute("dotIndex", pageContext.getAttribute("picBasex").toString().lastIndexOf("."));%>
								                    			<img class="scrollLoading" 
								                    				data-url="${fn:substring(product.picBase, 0, dotIndex)}/640x365${fn:substring(product.picBase, dotIndex, fn:length(product.picBase))}&x-oss-process=image/resize,m_fill,w_338,h_320,limit_0/format,jpg/interlace,1" 
								                    				style="background:url(${pageContext.request.contextPath}/images/loading01.gif) no-repeat center; background-size:26px;" 
								                    				src="${pageContext.request.contextPath}/images/pixel.gif" />
								                    		</c:when>
								                    		<c:otherwise>
								                    			<img class="scrollLoading" 
								                    				data-url="${pageContext.request.contextPath}/images/golf/logo-jfq.png"
								                    				style="background:url(${pageContext.request.contextPath}/images/loading01.gif) no-repeat center; background-size:26px;" 
								                    				src="${pageContext.request.contextPath}/images/pixel.gif" />
								                    		</c:otherwise>
								                    	</c:choose>
													</div>
								                    <p class="item-name ">${product.name}</p>
							                    </a>
					                            <div class="red f16 bold mtop5 m010">￥${product.priceDiscount }</div>
					                            <div class="mtop5 m010">
					                                <div class="market-price-small left">￥${product.price }</div>
					                                <div class="right join-btn" leftCount="${product.leftCount}"><img src="${pageContext.request.contextPath}/images/shoppingcart-yellow.png" /></div>
					                            </div>
						                    </div>
					                    </li>
					                <c:if test="${status.index % 2==1}">
						                </ul>
					                </c:if>
			           			</c:forEach>
			            </div>
			        </div>
			    </div>
			</section>
			<c:if test="${subscribe==0}">
				<section class="sectionBox" id="erweima"><img src="${pageContext.request.contextPath}/images/erweima2.jpg" /></section>
			</c:if>
			<footer class="mtop10">
				<p>Copyright <span>&copy;</span> 2014 -<%=Calendar.getInstance().get(Calendar.YEAR)%>深圳前海邻里乐科技服务有限公司 <br />All rights reserved.</p>
			</footer>
		</div>
		<div class="main-part02 dsn">      
		    <section class="sectionBox item-details-info pos-relative">
		        <section class="sectionBox password-mind-box bordertbgrey">
		            <div class="mleft15 grey">请选择您所在省市</div>
		        </section>
		        <ul class="register-list">
		        	  <li class="borderbottomgrey">
		   					<select id="province" onchange="onSelectChange(this,'city');" class="province select_normal input-text wp100 list-arrow text-indent0 f16" name="province" data-first-title="选择省" title="选择省" >
			                   	<option value="">选择省</option>
			                   	<c:forEach items="${pcbList}" var="pcb" >
			                   		<option value="${pcb.id}">${pcb.name}</option>
		                   		</c:forEach>
		                   </select> 
		        	  </li>   
		            <li>
		            	<select id="city" class="input-text wp100 list-arrow text-indent0 f16" name="city">
		                      	<option value="">选择城市</option>
		                </select> 
		            </li>
		        </ul>
		    	<section class="divide-box bordertopgrey"></section>
		        <div class="item-list-arrow-box p00 borderbottomgrey"><input class="btn-submit btn-next noradius bordertbgrey btnSubmit bgwhite red" type="button" name="button" value="确定"></div>
		    </section>
		</div>
		<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
		<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
		<script src="${resourcePathHttps}/commonjs/TouchSlide.1.1.js"></script>
		<script src="${resourcePathHttps}/commonjs/jquery.cookie.js"></script>
		<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>
		<script src="https://api.map.baidu.com/api?v=2.0&ak=BF512d9c46c962e21adf4bd17340b6cc&s=1"></script>
		<script>
			$(function(){
				new FastClick(document.body);
			});
			
		    TouchSlide({ 
		        slideCell:"#slideBox",
		        titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
		        mainCell:".bd ul", 
		        effect:"left", 
		        autoPage:true,//自动分页
		        autoPlay:true //自动播放
		    });

			var $mainPart01 = $('.main-part01');
			var $mainPart02 = $('.main-part02');
			$(function(){
				var $li = $('#slideBox .hd ul li');
				var hdWidth = $('#slideBox .hd').width();
				$('#slideBox .hd').css('margin-left',-hdWidth/2);
				
				if($li.length == 1){
					$li.hide();
				}
				
				//设置定位城市
				$('#cityOrientation').click(function(){
					$mainPart01.hide();
					$mainPart02.show();
				});
				
				$('.item-list-arrow-box').click(function(){
					var $city = $('#city');
					if($city.val() !== ''){
						var cityName = $('#city').find('option:selected').text();
						$('#curCity').text(cityName);
						//先设置10年有效
						$.cookie('cityName', cityName, { expires: 365*10, path: '/'}); 
						$mainPart02.hide();
						$mainPart01.show();
						updateADAfterInsertCity();
					}else if($city.val() == ''){
						$.Showmsg('请选择城市');
					}
				});
			});
			
			//省市切换
		    function onSelectChange(obj,toSelId){
		    	setSelect(obj.value,toSelId);
		    }
		
		    //省市切换详情逻辑处理
		    function setSelect(fromSelVal,toSelId){
		    	$('#' + toSelId).html('<option value="">选择城市</option>');//清空之前的选项
		    	if(toSelId === "city"){//选择省，更新市
		    		jQuery.ajax({
		    			  url: "${pageContext.request.contextPath}/common/toUrl.do",
		    			  cache: false,
		    			  dataType:"json",
		    			  async:false,
		    			  data:{"detailUrl" : "/addressCity/getAddressCityListById.json", "provId":fromSelVal, "isNeedLogin":"0"}, //"provId="+fromSelVal,
		    			  success: function(data){
		    			    $.each(data.dataValue.list, function(i, item) {
		    			    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
		    			    });
		    			  },  
		                  error: function(){  
		                  	$.Showmsg('网络不给力，请稍后重试'); 
		                  }
		    			});
		    		//$("#city").prepend('<option value="">选择城市</option>');
		    	}	
		    }
		
			//更新城市后更新广告图
		    function updateADAfterInsertCity(){
		    	//在app端需要滚动一下，才能显示图片。。。
		    	$('body,html').animate({'scrollTop':1}, 200);
		    	
		    	var $ul = $("#slideBox ul");
		    	$.ajax({
					  url: "${pageContext.request.contextPath}/common/toUrl.do",
					  async: true,
					  dataType:"json",
					  data:{"code":"LA_EBUY", "city":$('#curCity').text(), "detailUrl":"/ebuyNew/qryAds.json"},
					  success: function(data){
						  if(data.status!="0000"){
							  $.Showmsg(data.message);
						  }else{
							  if(data.dataValue.list != null){
								  var li_item = "";
								  $.each(data.dataValue.list, function(i,item){
									  li_item += '<li><a href="' + item.linkUrl + '"><img src="' + item.picUrl + '" /></a></li>';
								  });  
								  $ul.html(li_item);
							  }
							  /* else{
								  var li_item = '<li><a href="javaScript:void(0)"><img src="${pageContext.request.contextPath}/images/index-banner.jpg" /></a></li>';
								  $ul.html(li_item);
							  } */
							  
							  TouchSlide({ 
								slideCell:"#slideBox",
								titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
								mainCell:".bd ul", 
								effect:"left", 
								autoPage:true,//自动分页
								autoPlay:false //自动播放
							  });
							  
								var $li = $('#slideBox .hd ul li');
								if($li.length == 1){
									$li.hide();
								}
						  }
					  },
					  error: function(){  
		               	$.Showmsg('网络不给力，请稍后重试'); 
		             }
				});
		    }
			
			$(document).ready(function(){
				var cookie_cityName = $.cookie('cityName');
				if(cookie_cityName==null || cookie_cityName =="" || cookie_cityName== undefined){
					autoLocation();
				}else{
					$('#curCity').html(cookie_cityName);
					updateADAfterInsertCity();
				}
			});	

			//声明地址解析器
			var geoc = new BMap.Geocoder();
			  
			//自动定位
			function autoLocation() {
			  if (navigator.geolocation) { //判断浏览器是否能获取当前位置
			    navigator.geolocation.getCurrentPosition(AddrSuc, AddrFail);
			  }
			  else {
			    $.Showmsg("无法自动定位，请手动选择所在城市");
				$mainPart01.hide();
				$mainPart02.show();
			  }
			}
			  
			//获取当前坐标成功
			function AddrSuc(param) {
			  var lng = param.coords.longitude;
			  var lat = param.coords.latitude;
			  var point = new BMap.Point(lng, lat);
			  
			  translateCallback(point);
			}
			  
			//获取坐标失败
			function AddrFail(err) {
			    $.Showmsg("无法自动定位，请手动选择所在城市");
				$mainPart01.hide();
				$mainPart02.show();
			}
			  
			//坐标转换
			function translateCallback(point) {
			  geoc.getLocation(point, function (rs) {
			    var addComp = rs.addressComponents; //查询得到的地址对象组件
				$('#curCity').html(addComp.city);
				//expires单位为天，永久先设置为10年
		    	$.cookie('cityName', addComp.city, { expires: 3650, path: '/' }); 
				updateADAfterInsertCity();
			  });
			}

		</script>
		<script src="${resourcePathHttps}/commonjs/requestAnimationFrame.js"></script>
		<script src="${resourcePathHttps}/commonjs/jquery.flying.js"></script>
		<script src="${resourcePathHttps}/commonjs/jquery.scrollLoading.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.index.main.js?V20170712a"></script>
		<script src="${resourcePathHttps}/commonjs/ajax_common.js"></script>
	</body>
	<script>
		var _hmt = _hmt || [];
		(function() {
		    var hm = document.createElement("script");
		    hm.src = "//hm.baidu.com/hm.js?37203afff68fe15791b03c221468237c";
		    var s = document.getElementsByTagName("script")[0]; 
		    s.parentNode.insertBefore(hm, s);
		})();
	</script>
</html>