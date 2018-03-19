var serverDetailsVM = new Vue({
	el: '#container',
	data: {
		curItemNum: 1,
		itemIndex: '',
		hasSelectedNum: 0,
		list: [{
			"id": 51470,
			"orderNo": "20150817160051351",
			"status": 3,
			"payTime": 1439798466000,
			"deliverAddr": "广东省深圳市福田区湖北宝丰花园2栋2单元2-601",
			"receivePeople": "1",
			"phone": "18682277125",
			"deliverMoney": 0.01,
			"productList": [{
				"id": 101580,
				"productName": "111新西兰佳沛阳光金奇异果27枚 ",
				"price": 1,
				"qty": 1,
				"picBase": "http://image.linlile.com.cn:8086/productPic/150706_jinqiyi27_001.jpg?2015-08-17_15_59_53",
				"updTime": 1439798393000,
				"prodPrice": 1
			}],
			"tatalPrice": null,
			"prodsPrice": 0,
			"totalQty": 1,
			"payTimeStr": "2015-08-17 16:01"
		}],
		hasNext: true,
		isLoading: true,
		isScrolling: true,
		busy: false,
		msg: ''
	},
	mounted: function(){
		var _self = this;

		//_self.getAjax();
		
	    /*window.onscroll = function() {
			_self.loadNextPage();
		}*/
	},
	methods: {
		itemNumAdd: function(item) {
			item.totalQty += 1;
		},
		itemNumReduce: function(item) {
			if(item.totalQty > 1) {
				item.totalQty -= 1;
			}
		},
		onlyNumber: function(val, item, index) {
			var partten = /^[1-9]\d*$/;
			if(!partten.test(val) && val !== '') {
				item.totalQty = 1;
				console.log(val, item.$refs, index, this.$refs.input[index]);
				this.$refs.input[index].value = 1;
			}
		},
		selectItem: function(item) {
			itemSelected = item.itemSelected === undefined ? true : !item.itemSelected;
			Vue.set(item, "itemSelected", itemSelected);
			if(item.itemSelected === true) {
				this.hasSelectedNum += 1;
			} else {
				this.hasSelectedNum -= 1;
			}
		},
		itemRemove: function() {
			var _self = this;
			_self.list = _self.list.filter(function(val) {
				return val.itemSelected !== true;
			})
			
			//该方法遍历，选中多个元素时，删除掉第一个后，会导致后面的index发生改变，从而导致删除错乱
			/*_self.list.forEach(function(value, index, arr){
				if(value.itemSelected){
					var i = _self.list.indexOf(value)
					console.log(value + '：' + index + ':' + i);
					_self.list.splice(i, 1);
				}
			})*/
			
		},
		selectAllItem: function() {
			var _self = this;
			if(_self.allChecked === true) {
				_self.list.forEach(function(val) {
					val.itemSelected = false;
				})
			} else {
				_self.list.forEach(function(val) {
					itemSelected = val.itemSelected === undefined ? true : !val.itemSelected;
					Vue.set(val, "itemSelected", itemSelected);
					val.itemSelected = true;
				})
			}
		},
		
		getAjax: function(){
			var url = "js/cinema.json";		//.js后缀的json文件请求后返回的是blob格式的数据。。。又一坑啊
			//var url = "https://sug.so.360.cn/suggest?callback=suggest_so&word=a";		//.js后缀的json文件请求后返回的是blob格式的数据。。。又一坑啊
	        var _self=this;
	        
	        _self.isLoading = true;
	        
	       axios.get(url).then(function(response){
	       //this.$http.jsonp(url).then(function(response){		//vue-resource	jsonp
	        	//var dataList = response.data.s;
	        	var dataList = response.data[0].dataValue.list;
	        	dataList.forEach(function(value, index, arr){
	        		_self.list.push(value);
	        	})
	        	setTimeout(function(){
	        		_self.isLoading = false;
	        	}, 600);
	        })
		},
		scrollEnd: function() {
	
			this.isScrolling = true;
	
			var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
			//var docHeight = $(document).height() - $(window).height() - 1; //当前文档高度
			var docHeight = document.body.scrollHeight - document.body.clientHeight - 1; //当前文档高度-当前窗口高度-1
	
			//页面加载完成时，不触发
			if(scrollt === 0) {
				return false;
			}
	
			if(scrollt > docHeight) {
				this.getAjax();
			}
		},
		loadNextPage: function() {
			if(this.isScrolling) {
				setTimeout(this.scrollEnd, 1000);
				this.isScrolling = false;
			}
		}
	},
	computed: {
		allChecked: function() {
			var _self = this;
			var count = 0;
			_self.list.filter(function(val) {
				if(val.itemSelected !== true) {
					count++;
				}
			})
			if(count === 0 && _self.list.length > 0) {
				return true;
			} else {
				return false;
			}
		}
	}
})