var slide = function (obj, offset, callback) {

        var start,

            end,

            isLock = false,//是否锁定整个操作

            isCanDo = false,//是否移动滑块

            isTouchPad = (/hp-tablet/gi).test(navigator.appVersion),

            hasTouch = 'ontouchstart' in window && !isTouchPad;

        //将对象转换为jquery的对象

        obj = $(obj);

        var objparent = obj.parent();

       

        var fn =

            {

                //移动容器

                translate: function (diff) {

                    obj.css({

                        "-webkit-transform": "translate(0," + diff + "px)",

                        "transform": "translate(0," + diff + "px)"

                    });

                },

                //设置效果时间

                setTranslition: function (time) {

                    obj.css({

                        "-webkit-transition": "all " + time + "s",

                        "transition": "all " + time + "s"

                    });

                },

                //返回到初始位置

                back: function () {

                    fn.translate(0 - offset);

                    //标识操作完成

                    isLock = false;
                    
                }

            };

        //滑动开始

        obj.bind("touchstart", function (e) {

            if (objparent.scrollTop() <= 0 && !isLock) {

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

        obj.bind("touchmove", function (e) {

            if (objparent.scrollTop() <= 0 && isCanDo) {

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
                    $("#reloadIcon").css('backgroundPosition', '28px 0');
                    $("#reloadText").text('松开刷新');
                }else{
                    $("#reloadIcon").css('backgroundPosition', '0 0');
                    $("#reloadText").text('下拉刷新');
                }

            }

        });

        //滑动结束

        obj.bind("touchend", function (e) {

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

                    $("#reloadIcon").css('backgroundPosition', '14px 0');
                    
                    //比对之前请求的缓存数据，无更新并且文档已完全加载，则不刷新
                    var xhr01 = $.get("../dredge/qryCommunitySupplyType.json", function(data,status){
                    	var hasSameTypeData = JSON.stringify(typeData) === JSON.stringify(data.dataValue.dredgeList);
                    	if(!hasSameTypeData || (hasSameTypeData && document.readyState !== "complete")){
                            $("#reloadText").text('正在刷新');
                    		location.reload();
                    		xhr02.abort();
                    	}else{
                            $("#reloadText").text('暂无更新');
                    	}
                    })
                    var xhr02 = $.get("../homeService/qryAds.json", {"code":"WX", "city":"深圳"}, function(data01,status01){
                    	var hasSameBannerData = JSON.stringify(bannerData) === JSON.stringify(data01.dataValue.list);
                    	if(!hasSameBannerData || (hasSameBannerData && document.readyState !== "complete")){
                            $("#reloadText").text('正在刷新');
                    		location.reload();
                    		xhr01.abort();
                    	}else{
                            $("#reloadText").text('暂无更新');
                    	}
                    })

                } else {

                    //返回初始状态
	
                    fn.back();

                }

            }
            
            //防止点击时产生页面刷新
            end = undefined;

        });

    }
