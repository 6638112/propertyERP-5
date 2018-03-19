<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="../error/404.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<title>到家服务</title>
<link rel="dns-prefetch" href="//jiefangqu.com">
<link type="text/css" rel="stylesheet" href="css/swiper.css">
<link type="text/css" rel="stylesheet" href="css/homeservice.css">
</head>
<body class="pos-relative" style="display:none;">
	<section id="slideBox" class="slideBox mtop0 bgwhite" style="height: 89px;">
	    <div id="slidePic" class="bd">
			<section id="slideList" class="server-list"></section>
	    </div>
	    <div id="slideDot" class="hd bottomminus5">
	        <ul></ul>
	    </div>
	</section>
	
	<section class="sectionBox mtop5" style="max-height: 98px;">
		<ul class="server-small-list bordertopgrey m010 pt12" id="typeUl"></ul>
	</section>
	
	<section class="sectionBox">
		<section class="divide-box"></section>
		<div class="m010" id="productDiv"></div>
	</section>
<section class="pop-tips hide">
	<div class="pop-tips-text">请选择</div>
</section>
<div class="sectionBox loading grey hide"><img src="images/loading01.gif" /> 加载中…</div>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/TouchSlide.1.1.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.lazyload.js"></script>
<script type="text/javascript">

	var $productDiv = $("#productDiv");
	var $typeUl = $("#typeUl");
	var $moreType = $("#moreType");
	var $loading = $(".loading.hide");
	var isLoading = false;

	$(document).on('click', '#moreType', function(){
		
		var $this = $(this);
		
		if($this.html().indexOf('更多') > -1){
			
			$this.next('li').addClass('mright0');
			$this.html($this.html().replace("更多", "收起"));
			$(this).find('img').attr("src", "images/arrow_up.png");
			$this.appendTo($typeUl);
			$typeUl.parent().css("max-height", "");
			
		}else{
			
			$typeUl.find('li').eq(7).removeClass('mright0');
			$this.html($this.html().replace("收起", "更多"));
			$(this).find('img').attr("src", "images/icon_city_arrow.png");
			$this.insertBefore($typeUl.find('li').eq(7));
			$typeUl.parent().css("max-height", "98px");
			
		}
		
	});

	var dredgeType2ndList = new Array();
	function init(){
		/* var data = '{"status":"0000","message":null,"dataValue":{"dredgeTypeList":[{"picUrl":"20170517152734093.png","picUrlLight":"http://public.image.linlile.com.cn/dredgePic/20170517152734093.png","id":5056,"name":"家电安装","picUrlGrey":"http://public.image.linlile.com.cn/dredgePic/20170517152734093_grey.png","dredgeType2ndList":[{"id":5057,"name":"电视"},{"name":"空调","id":5058},{"id":5059,"name":"冰箱"},{"name":"油烟机","id":5061},{"name":"燃气灶具","id":5062},{"id":5063,"name":"热水器"},{"id":5064,"name":"消毒柜"},{"id":5065,"name":"水家电"},{"name":"其他","id":5066}]},{"picUrl":"20170517152734094.png","picUrlGrey":"http://public.image.linlile.com.cn/dredgePic/20170517152734094_grey.png","picUrlLight":"http://public.image.linlile.com.cn/dredgePic/20170517152734094.png","dredgeType2ndList":[{"name":"空调","id":5067}],"name":"家电清洗","id":5057},{"picUrl":"20170517152734095.png","id":5058,"dredgeType2ndList":[],"picUrlLight":"http://public.image.linlile.com.cn/dredgePic/20170517152734095.png","picUrlGrey":"http://public.image.linlile.com.cn/dredgePic/20170517152734095_grey.png","name":"家电维修"}]},"errcode":null,"transNo":null,"jsonCallback":null}';
		data = JSON.parse(data); */
		//var dataParams = {"myNumber":"${myNumber}", "myPassword":"${myPassword}", "parentTypeId":"${parentTypeId}"};
		var dataParams = {"parentTypeId":"${parentTypeId}"};
		if(getCookie('gbId')){
			dataParams['gbId'] = getCookie('gbId');
		}else{
			dataParams['blockId'] = getCookie('blockId');
		}
		
		$.post("${pageContext.request.contextPath}/dredge/getTypelist.do", dataParams, function(data){
			if(data.status=="0000"){
				var rowCount = 4;//每行显示的数量
				var dredgeTypeList = data.dataValue.dredgeTypeList;
				var typeHtml = "";
				for(var i=0; i<dredgeTypeList.length; i++){
					dredgeType2ndList[i] = dredgeTypeList[i].dredgeType2ndList;
					if(i==0){
						typeHtml += "<div>";
						typeHtml += "    <ul class=\"maintain-menus displaybox m010 t-center\">";
						//initItems(i);
					}
					typeHtml += "        <li class=\"server-big boxflex01\" data-id=\""+dredgeTypeList[i].id+"\" data-name=\""+dredgeTypeList[i].name+"\" onclick=\"initItems("+i+ ",this" + ");\"><img data-light='" +dredgeTypeList[i].picUrlLight+ "' data-grey='" +dredgeTypeList[i].picUrlGrey+ "' src=\""+dredgeTypeList[i].picUrlGrey+"\"/><div class=\"mtop5\">"+dredgeTypeList[i].name+"</div></li>";
					if(i==dredgeTypeList.length-1 && (i+1)%rowCount!=0){
						var x = rowCount - (i+1)%rowCount;
						for(var k=0; k<x; k++){
							typeHtml += "        <li class=\"server-big boxflex01\"></li>";
						}
					}
					if(((i+1)%rowCount==0 && i!=0) || dredgeTypeList.length==1 || i==dredgeTypeList.length-1){
						typeHtml += "    </ul>";
						typeHtml += "</div>";
					}
					if(((i+1)%rowCount==0 && (dredgeTypeList.length!=1) && i!=0 && i!=dredgeTypeList.length-1)){
						typeHtml += "<div>";
						typeHtml += "    <ul class=\"maintain-menus displaybox m010 t-center\">";
					}
				}
				
				$("#slideList").html(typeHtml);
				$("body").show();
				$.getScript("js/method.serverlist.js");
				if(dredgeTypeList.length>0){
					initItems(0, $('.server-big').eq(0));
				}
			}
		});
	}
	
	// 截取超过4个字的name
	function subMore4(s){
		if(s!=null && s!=undefined && s.length>4){
			return s.substring(0, 3)+"...";
		}
		return s;
	}
	
	function initItems(k, obj){
		var type2ndList = dredgeType2ndList[k];
		var s = "";
		
		if(isLoading){
			return;
		}
		
		if($(obj).children().length > 0){
			$('.server-big').addClass('grey');
			$(obj).removeClass('grey');
			
			var $siblingServers = $('.server-big').not(obj);
			var curImg = $(obj).find('img').attr('data-light');
			$(obj).find('img').attr('src', curImg);
			
			$siblingServers.each(function(){
				var curGreyImg = $(this).find('img').attr('data-grey');
				var curLightImg = $(this).find('img').attr('src');
				if(curLightImg && curLightImg.indexOf('grey') === -1){
					$(this).find('img').attr('src', curGreyImg);
				} 
			})
		}
		
		if(type2ndList.length === 0){
			$typeUl.html("");
			$productDiv.html("");
			$loading.clone(true).addClass('h43').removeClass('hide').html('该服务暂无详细信息').appendTo($typeUl);
			return;
		}
		
		for(var i=0; i<type2ndList.length; i++){
			if(i==0){
				s += "<li class=\"server-small-item on\" onclick=\"getProductList("+type2ndList[i].id+", '1')\" type2ndId=\""+type2ndList[i].id+"\">"+type2ndList[i].name+"</li>";
			} else if(i==7 && type2ndList.length > 8){
				s += "<li class=\"server-small-item mright0 pointer\" id=\"moreType\" >更多 <img class=\"w10\" src=\"images/icon_city_arrow.png\"/></li>";
				s += "<li class=\"server-small-item\" type2ndId=\""+type2ndList[i].id+"\" onclick=\"getProductList("+type2ndList[i].id+", '1')\">"+type2ndList[i].name+"</li>";
			} else if((i+1)%4==0){
				s += "<li class=\"server-small-item mright0\" type2ndId=\""+type2ndList[i].id+"\" onclick=\"getProductList("+type2ndList[i].id+", '1')\">"+type2ndList[i].name+"</li>";
			} else {
				s += "<li class=\"server-small-item\" type2ndId=\""+type2ndList[i].id+"\" onclick=\"getProductList("+type2ndList[i].id+", '1')\">"+type2ndList[i].name+"</li>";
			}
		}
		
		$typeUl.html(s);
		
		$productDiv.html("");
		if(type2ndList.length>0){
			getProductList(type2ndList[0].id, "1");
		}
	}
	
	var page=1;
	var pageNum=10;
	var dredgeType2ndId = null;
	function getProductList(id, type, next){
		if(id!=null){
			
			if(isLoading){
				return;
			}
			
			if(type==1){
				dredgeType2ndId = id;
				page=1;
			} else {
				page++;
			}
			
			var $curLi = $("li[type2ndId="+id+"]");
			
			if(!$curLi.hasClass('on')){
				$typeUl.find("li").removeClass("on");
				$curLi.addClass("on");
			}
			
			isLoading = true;

 			//判断是否滚动翻页
			if(next !== 'scrollNext'){
				$productDiv.html("");
			}

			if($productDiv.find('.newLoading').length == 0){
				$loading.clone(true).addClass('newLoading').removeClass('hide').appendTo($productDiv);
			}
			
			var productList = $curLi.data('productList');
			if(productList && type == 1){
				insertProductList(productList);
			}else{
				
				var hasNext = $typeUl.find("li.on").attr('hasNext');
				
				if(hasNext === 'false'){
					$('.newLoading').html('已加载至最后一页！');
					isLoading = false;
					return;
				}
				
				var dataParams = {"dredgeType2ndId":id, "page":page, "pageNum":pageNum};
				if(getCookie('gbId')){
					dataParams['gbId'] = getCookie('gbId');
				}else{
					dataParams['blockId'] = getCookie('blockId');
				}
				
				$.post("${pageContext.request.contextPath}/dredge/getProductList.do", dataParams, function(data){
					
					if(data.status=="0000"){
						
						var newProductList = data.dataValue.productList;
						
						if(productList){
							//合并数据，并缓存
							productList = newProductList.reduce( function(coll,item){
							    coll.push( item );
							    return coll;
							}, productList );
							$curLi.data('productList', productList);
						}else{
							$curLi.data('productList', newProductList);
						}
						
						insertProductList(newProductList);
					}else{
						showMsg(data.message);
					}
					
				});
			
			}
			
		}
	}
	
	function insertProductList(productList){
		var s = "";
		
		//判断是否有下一页
		if(productList.length === 0){
			$typeUl.find("li.on").attr('hasNext', 'false');
			$('.newLoading').html('已加载至最后一页！');
			isLoading = false;
			return;
		}
		
		for(var i=0; i<productList.length; i++){
			
			var curPicUrl = productList[i].productPicList ? productList[i].productPicList[0] + '?x-oss-process=image/resize,m_fill,w_200,h_200/format,jpg/quality,q_90/interlace,1' : '';
			
			s += "<div class=\"displaybox item-list-info borderbottomgrey\" onclick=\"goDetail("+productList[i].id+")\">";
			s += "    <div class=\"item-list-img-box mright15\">";
			s += "        <img class=\"item-pic-big bgloading\" data-original=\""+curPicUrl+"\" src=\"images/pixel.gif\" />";
			s += "    </div>";
			s += "    <div class=\"item-list-title boxflex01 f16\"><span class=\"word-break1\">"+productList[i].name+"</span>";
			s += "        <div class=\"f14 grey word-break1\">"+productList[i].desc+"</div>";
			s += "        <div class=\"f14 grey mtop5\">"+productList[i].supplierName+"</div>";
			s += "        <div class=\"f14 mtop5\">";
			s += "            <span class=\"red\">￥ "+(productList[i].sellPrice/100).toFixed(2)+"</span>";
			s += "            <span class=\"grey f12\">";
			s += "            <span>|</span>";
			s += "            <span>"+productList[i].priceUnit+"</span>";
			s += "            <span class=\"mleft10 line-through\">￥ "+(productList[i].marketPrice/100).toFixed(2)+"</span>";
			s += "            </span>";
			s += "        </div>";
			s += "    </div>";
			s += "</div>";
		}
		
		$(s).insertBefore('.newLoading');
		$('.newLoading').remove();
		isLoading = false;
		
	    //lazyload
	    $("img.bgloading").lazyload(
	    	{effect: "fadeIn"},
	    	null
	    );
	}
	
	function goDetail(id){
		location = "${pageContext.request.contextPath}/dredge/productDetail.html?productId="+id+'&jfqsource=weixin';
	}
	
	$(function(){
		init();
		
		$(window).scroll(function(event){
			if(isScrolling){ 
				setTimeout(scrollNextPage, 1000); 
				isScrolling = false;
			}
		});
	});
	
	var isScrolling = true;
	function scrollNextPage(){
		isScrolling = true;
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
		var docHeight = $(document).height() - $(window).height()-1; //当前文档高度
		
		//页面加载完成时，不触发
		if(scrollt === 0){
			return false;
		}
		
		if(scrollt > docHeight){ 
			getProductList(dredgeType2ndId, 2, 'scrollNext');
		}
	}

	function showMsg(txt){
		if($(".pop-tips").is(':visible')){
			return;
		}
		$(".pop-tips-text").text(txt);
		$(".pop-tips").fadeIn();
		setTimeout(function(){
			$(".pop-tips").fadeOut();
		}, 2500)
	}
	
	function getCookie(name){
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg)){
			return unescape(arr[2]);
		}else{
			return null;
		}
	}
	
	var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>