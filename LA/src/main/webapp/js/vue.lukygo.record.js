
//页面详情
new Vue({
	el: '#wrapBox',
	data: {
		recordDetail: null,	//商品详情
	},
	mounted: function(){
		var _self = this;
		_self.getAjax();
	},
	methods: {
		//服务详情页
		getAjax: function(){
			var _self=this;
			var url = "../common/toUrl.do";
	        
	        axios.get(url, {
	        	params:{ detailUrl: '/flashDealActivity/myRecord.json' }
	        })
	        .then(function(response){
	        	_self.recordDetail = response.data.dataValue;
	        })
	        .then(function(){
	        	new FastClick(document.body);

	        	//查看成功购买名单
	        	$(document).on('click', '.awards-list-btn', function(){
	        		var thisListHtml = $(this).parent('ul').siblings('.awards-list').html();
	        		$('.pop-box02').find('.awards-list').html(thisListHtml);
	        		
	        		$('#wrapBox, #tabBox').addClass('heightp100');
	        		$('.pop-box02').removeClass('dsn');
	        	});
	        	//关闭成功购买名单
	        	$(document).on('click', '#adwardListCloseBtn', function(){
	        		$('#wrapBox, #tabBox').removeClass('heightp100');
	        		$('.pop-box02').addClass('dsn');
	        	});
	        	
	        }).catch(function(error){
	        	alert(error);
	        })
		}
	}
});

