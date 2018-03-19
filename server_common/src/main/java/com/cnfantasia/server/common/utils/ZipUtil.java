package com.cnfantasia.server.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 打包压缩
 * 
 * @author liyulin
 * @version 1.0 2017年4月18日 上午9:30:46
 */
public final class ZipUtil {

	/**
	 * 文件打包压缩
	 * @param fileNames 待压缩的文件路径
	 * @param zipFile 压缩后的文件
	 */
	public static final void zipFiles(String[] fileNames, File zipFile){
		File parentFile = zipFile.getParentFile();
		if(parentFile!=null && !parentFile.exists()){
			parentFile.mkdirs();
		}
        InputStream inputStream = null;
        ZipOutputStream zipOutputStream = null;
        
        byte[] buf = new byte[8192];
        int len;
		try {
			zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
			for (String fileName : fileNames) {
				File tmpFile = new File(fileName);
				if(tmpFile.exists()){
					inputStream = new FileInputStream(tmpFile);
					String tmpFileName = tmpFile.getName();
					//农商行出盘文件，只能用固定名称：SRCC.SUM   SRCC.DAT  (固定名称)
					if (tmpFileName.contains("SRCC") && tmpFileName.contains(".DAT")) {
						tmpFileName = "SRCC.DAT";
					} else if (tmpFileName.contains("SRCC") && tmpFileName.contains(".SUM")) {
						tmpFileName = "SRCC.SUM";
					}
					zipOutputStream.putNextEntry(new ZipEntry(tmpFileName));
					while ((len = inputStream.read(buf)) > 0) {
						zipOutputStream.write(buf, 0, len);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(zipOutputStream!=null){
				try {
					zipOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		String[] fileNames = new String[]{"E:\\data1\\tomcat\\apache-tomcat-6.0.39-8086\\webapps\\bankCollection\\SRCC1492659350298.DAT",
				"E:\\data1\\tomcat\\apache-tomcat-6.0.39-8086\\webapps\\bankCollection\\SRCC1492659350298.SUM",
		"E:\\data1\\tomcat\\apache-tomcat-6.0.39-8086\\webapps\\bankCollection\\002_sum1493186052419.txt",};
		
		String zipFileName = "E:\\data1\\tomcat\\apache-tomcat-6.0.39-8086\\webapps\\bankCollection\\308f9dca-991e-4c91-8410-43d873b0f615.zip";
		// 要被压缩的文件夹
        File zipFile = new File(zipFileName);
		zipFiles(fileNames, zipFile);
    }
	

}
