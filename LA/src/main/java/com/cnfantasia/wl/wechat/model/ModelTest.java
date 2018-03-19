package com.cnfantasia.wl.wechat.model;

import com.alibaba.fastjson.JSON;

public class ModelTest {
	public static void main(String[] args) {
		String text = "{\"status\":\"0000\",\"message\":null,\"dataValue\":{\"list\":[{\"id\":5011,\"name\":\"新疆维吾尔自治区\"},{\"id\":5010,\"name\":\"宁夏回族自治区\"},{\"id\":5009,\"name\":\"青海省\"},{\"id\":5008,\"name\":\"甘肃省\"},{\"id\":5007,\"name\":\"西藏自治区\"},{\"id\":5006,\"name\":\"贵州省\"},{\"id\":5005,\"name\":\"海南省\"},{\"id\":5004,\"name\":\"山东省\"},{\"id\":5003,\"name\":\"吉林省\"},{\"id\":5002,\"name\":\"内蒙古自治区\"},{\"id\":5001,\"name\":\"山西省\"},{\"id\":3292,\"name\":\"访客体验区\"},{\"id\":27,\"name\":\"陕西省\"},{\"id\":25,\"name\":\"云南省\"},{\"id\":23,\"name\":\"四川省\"},{\"id\":22,\"name\":\"重庆市\"},{\"id\":20,\"name\":\"广西壮族自治区\"},{\"id\":19,\"name\":\"广东省\"},{\"id\":18,\"name\":\"湖南省\"},{\"id\":17,\"name\":\"湖北省\"},{\"id\":16,\"name\":\"河南省\"},{\"id\":14,\"name\":\"江西省\"},{\"id\":13,\"name\":\"福建省\"},{\"id\":12,\"name\":\"安徽省\"},{\"id\":11,\"name\":\"浙江省\"},{\"id\":10,\"name\":\"江苏省\"},{\"id\":9,\"name\":\"上海市\"},{\"id\":8,\"name\":\"黑龙江省\"},{\"id\":6,\"name\":\"辽宁省\"},{\"id\":3,\"name\":\"河北省\"},{\"id\":2,\"name\":\"天津市\"},{\"id\":1,\"name\":\"北京市\"},{\"id\":-1,\"name\":\"体验省\"}],\"hasNext\":false,\"count\":33},\"errcode\":null,\"transNo\":null}";

		EntityBase entityBase = JSON.parseObject(text, EntityBase.class);
	}
}
