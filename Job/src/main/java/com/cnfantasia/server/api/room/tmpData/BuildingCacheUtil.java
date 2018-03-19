package com.cnfantasia.server.api.room.tmpData;

import java.io.File;
import java.math.BigInteger;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.FileUtils;

public class BuildingCacheUtil {
	public static void main(String[] args) {
		JsonResponse tmpData = JSON.parseObject(null, JsonResponse.class);
		cacheObj.put(new BigInteger("1"), tmpData);
		System.out.println(tmpData);
	}
	static Map<BigInteger,JsonResponse> cacheObj = new ConcurrentHashMap<BigInteger, JsonResponse>();
	public static JsonResponse getBuildingCacheObj(BigInteger gbId){
		if(gbId==null){return null;}
		if(cacheObj.get(gbId)==null){
			synchronized(BuildingCacheUtil.class) {
				if(cacheObj.get(gbId)==null){
					String srcData = getDataStr(gbId);
					if(srcData!=null){
						try {
							JsonResponse tmpData = JSON.parseObject(srcData, JsonResponse.class);
							if(tmpData!=null){
								cacheObj.put(gbId, tmpData);
							}
						} catch (Exception e) {}
					}
				}
			}
		}else{
			if(!isExist(gbId)){
				cacheObj.remove(gbId);
			}
		}
		return cacheObj.get(gbId);
	}
	
//	public static JsonResponse getBuildingCache(BigInteger gbId){
//		JsonResponse tmpData = null;
//		String srcData = getDataStr(gbId);
//		if(tmpData==null){
//			synchronized(BuildingCacheUtil.class) {
//				if(tmpData==null){
//					tmpData = JSON.parseObject(srcData, JsonResponse.class);
//				}
//			}
//		}
//		return tmpData;
//	}
	
	private static String getDataStr(BigInteger gbId){
		String resStr = null;
		try {
			URI uri = new URI(BuildingCacheUtil.class.getResource("")+gbId.toString()+".txt");
			String filePath = uri.getPath();
			byte[] bytes = FileUtils.fileToByte(filePath);
			resStr = new String(bytes);
		} catch (Exception e) {}
		return resStr;
	}
	private static boolean isExist(BigInteger gbId){
		boolean exist = false;
		try {
			URI uri = new URI(BuildingCacheUtil.class.getResource("")+gbId.toString()+".txt");
			String filePath = uri.getPath();
			File file = new File(filePath);
			exist = file.exists();
		} catch (Exception e) {}
		return exist;
	}
	
//	public static void main(String[] args) {
//		System.out.println(getBuildingCache(new BigInteger("119209")).getStatus());
////		System.out.println(getBuildingCache(new BigInteger("11920911")).getStatus());
//		File directory = new File("");//设定为当前文件夹   
//		try{   
//		    System.out.println(directory.getCanonicalPath());//获取标准的路径   
//		    System.out.println(directory.getAbsolutePath());//获取绝对路径   
//		}catch(Exception e){}  
//		System.out.println(BuildingCacheUtil.class.getResource(""));
//		System.out.println(BuildingCacheUtil.class.getResource("."));
//		System.out.println(BuildingCacheUtil.class.getResource("/"));
//		System.out.println(BuildingCacheUtil.class.getResource("./"));
//		System.out.println(BuildingCacheUtil.class.getResource("../"));
//	}
	
}
