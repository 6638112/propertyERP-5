//页面详情
var feeBillData = new Vue({
	el: '#wrapBox',
	data: {
		dataValue: [],	
		hasNoBills: false
	},
	mounted: function(){
		var self = this;
		self.getAjax();
	},
	methods: {
		getAjax: function(){
			var self=this;
			var url = '../common/toUrl.do';
	        var userId = getUrlParam('userId');
			
	        axios.get(url, {
	        	params:{ 'detailUrl': '/livingPay/qryLivingPayRecordList.json', 'userId': userId }
	        })
	        .then(function(response){
	        	self.dataValue = response.data.dataValue.list;
	        	if(self.dataValue.length === 0){
	        		self.hasNoBills = true;
	        	}
	        })
	        .then(function(){
	        	new FastClick(document.body);
	        })
	        .catch(function(error){
	        	alert(error);
	        })
		}
	}
});

