package com.cnfantasia.server.api.common;

import java.util.List;
import java.util.Map;

public class HomePageList {

	public static List<Map<String,Object>> list(List<Map<String,Object>> leveList) {
		if(leveList != null && leveList.size() > 1) {
			for(int i = leveList.size() - 1; i > 0; i--) {
				if(leveList.get(i).get("name").equals(leveList.get(i-1).get("name"))) {
					if(leveList.get(i).get("isLink") != null &&  (Boolean) leveList.get(i).get("isLink") == true) {
						leveList.remove(i);
					} else if(leveList.get(i-1).get("isLink") != null &&  (Boolean) leveList.get(i-1).get("isLink") == true) {
						leveList.remove(i-1);
					}
				}
			}
		}
		
		return leveList;
	}

}
