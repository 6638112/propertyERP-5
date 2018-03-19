var pulldown = function (obj, offset, url, callback) {

        var start,

            end,

            isLock = false,//是否锁定整个操作

            isCanDo = false,//是否移动滑块

            isTouchPad = (/hp-tablet/gi).test(navigator.appVersion),

            hasTouch = 'ontouchstart' in window && !isTouchPad;

        var obj = document.getElementById(obj);

        var objparent = obj.parentNode;
        
        var url = url || '';

        var fn =

            {

                //移动容器

                translate: function (diff) {

                    obj.style.transform = "translate(0," + diff + "px)";

                },

                //设置效果时间

                setTranslition: function (time) {

                    obj.style.transform = "all " + time + "s";

                },

                //返回到初始位置

                back: function () {

                    fn.translate(0 - offset);

                    //标识操作完成

                    isLock = false;
                    
                }

            };

        //滑动开始

        obj.addEventListener("touchstart", function (e) {

            if (objparent.scrollTop <= 0 && !isLock) {

                var even = typeof event == "undefined" ? e : event;

                //标识操作进行中

                isLock = true;

                isCanDo = true;

                //保存当前鼠标Y坐标

                start = hasTouch ? even.touches[0].pageY : even.pageY;

                //消除滑块动画时间

                fn.setTranslition(0);

            }

        });

        //滑动中

        obj.addEventListener("touchmove", function (e) {

            if (objparent.scrollTop <= 0 && isCanDo) {

                var even = typeof event == "undefined" ? e : event;

                //保存当前鼠标Y坐标

                end = hasTouch ? even.touches[0].pageY : even.pageY;

                if (start < end) {

                    even.preventDefault();

                    //消除滑块动画时间

                    fn.setTranslition(0);

                    //移动滑块

                    fn.translate(end - start - offset);

                }
                    
                if (end - start >= offset) {
                    document.getElementById("reloadIcon").style.backgroundPosition = '32px 0';
                    document.getElementById("reloadText").textContent = '松开刷新';
                }else{
                    document.getElementById("reloadIcon").style.backgroundPosition = '0 0';
                    document.getElementById("reloadText").textContent = '下拉刷新';
                }

            }

        });

        //滑动结束

        obj.addEventListener("touchend", function (e) {

            if (isCanDo) {

                isCanDo = false;

                //判断滑动距离是否大于等于指定值

                if (end - start >= offset) {

                    //设置滑块回弹时间

                    fn.setTranslition(1);

                    //保留提示部分

                    fn.translate(0);

                    //执行回调函数

                    if (typeof callback == "function") {

                        callback.call(fn, e);

                    }
                    
                    document.getElementById("reloadIcon").style.backgroundPosition = '16px 0';
                    
                    if(url !== '' && url !== undefined){
	                    axios.get(url).then(function(response){
	                    	
				        	var dataList = response.data.dataValue.noticeMonths.slice(0,5);
				        	var hasSameData = JSON.stringify(orderVM.list) === JSON.stringify(dataList);
				        	
				        	if(!hasSameData || (hasSameData && document.readyState !== "complete")){
	                            document.getElementById("reloadText").textContent = '正在刷新';
	                    		location.reload();
	                    	}else{
	                            document.getElementById("reloadText").textContent = '暂无更新';
	                    	}
	                    	
				        })
                    }else{
                    	document.getElementById("reloadText").textContent = '正在刷新';
                		location.reload();
                    }

                } else {

                    //返回初始状态
	
                    fn.back();

                }

            }
            
            //防止点击时产生页面刷新
            end = undefined;

        });

    }
