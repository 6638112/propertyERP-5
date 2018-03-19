package com.cnfantasia.server.common.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

	public static String getFilePrefix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0, splitIndex);
	}

	public static String getFileSufix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(splitIndex + 1);
	}

	public static void copyFile(String inputFile, String outputFile) throws FileNotFoundException {
		File sFile = new File(inputFile);
		File tFile = new File(outputFile);
		FileInputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int temp = 0;
		try {
			while ((temp = fis.read()) != -1) {
				fos.write(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
   * 将byte数组信息写入到目标文件中去,如果目标文件路径不存在，则提示错误
   * @param data byte数组信息
   * @param goalPath 目标文件目录
   * @return 返回true表示操作成功，返回false表示操作失败
	 * @throws IOException 
   */
  public static boolean byteToFile(byte[] data,String goalPath) throws IOException{
    boolean resBool=false;
    File file = new File(goalPath);
    OutputStream output=null;
    BufferedOutputStream bufferedOutput =null;
    output = new FileOutputStream(file);
    try {
    	bufferedOutput = new BufferedOutputStream(output);
    	bufferedOutput.write(data);
		}finally{
			if(bufferedOutput!=null){
				bufferedOutput.close();
			}
		}
    resBool=true;
    return resBool;
  }
  /**
   * 将文件信息转为byte数组输出
   * @param filePath 要转换的本地文件全路径
   * @return 返回文件内容的byte数组
   * @throws IOException 
   */
  public static byte[] fileToByte(String filePath) throws IOException{
    byte[] resData = null;
    File file = new File(filePath);
    InputStream input=null;
    try {
    	input = new FileInputStream(file);
      resData = new byte[input.available()];
      input.read(resData);
		}finally{
			if(input!=null){
				input.close();
			}
		}
    return resData;
  }
  
  /**
   * 删除空目录
   * @param dir 将要删除的目录路径
   */
  public static void doDeleteEmptyDir(String dir) {
      boolean success = (new File(dir)).delete();
      if (success) {
          System.out.println("Successfully deleted empty directory: " + dir);
      } else {
          System.out.println("Failed to delete empty directory: " + dir);
      }
  }
  
  /**
   * 递归删除目录下的所有文件及子目录下所有文件
   * @param dir 将要删除的文件目录
   * @return boolean Returns "true" if all deletions were successful.
   *                 If a deletion fails, the method stops attempting to
   *                 delete and returns "false".
   */
  public static boolean deleteDirs(File dir) {
      if (dir.isDirectory()) {
          String[] children = dir.list();
          //递归删除目录中的子目录下
          for (int i=0; i<children.length; i++) {
              boolean success = deleteDirs(new File(dir, children[i]));
              if (!success) {
                  return false;
              }
          }
      }
      // 目录此时为空，可以删除
      return dir.delete();
  }
}
