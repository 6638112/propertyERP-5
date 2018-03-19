package com.cnfantasia.server.api.revenueApplyPush.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alipay.sign.Base64;
/**
 * 附件
 * */
public class AttachmentEntity {
	private String fileName;
	private String simpleName;			// 文件后缀(xlsx、doc...)
	private String file;				// 使用java.util.Base64将byte[]序列化
	private String size;				// 文件大小(123 KB)
	private Integer sizeInByte;			// 字节大小(123000)
	
	// 以下参数请勿传值
	private Boolean isShared = false;
	private String storageType = "0";
	private String attachId;
	public AttachmentEntity(){}
	public AttachmentEntity(String fileName, String simpleName, String file, String size, Integer sizeInByte){
		this.fileName = fileName;
		this.simpleName = simpleName;
		this.file = file;
		this.size = size;
		this.sizeInByte = sizeInByte;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSimpleName() {
		return simpleName;
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getSize() {
		return size;
	}
	public Integer getSizeInByte() {
		return sizeInByte;
	}
	public void setSizeInByte(Integer sizeInByte) {
		this.size = sizeInByte / 1024 + " KB";
		this.sizeInByte = sizeInByte;
	}

	public Boolean getIsShared() {
		return isShared;
	}

	public String getStorageType() {
		return storageType;
	}

	public String getAttachId() {
		// 唯一标识附件
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Random rm = new Random(); 
		double pross = (1 + rm.nextDouble()) * Math.pow(10, 9);
		String fixLenthString = String.valueOf(pross); 
		attachId = format.format(new Date()) + fixLenthString.substring(1, 10);
		return attachId;
	}
	
	/**
	 * 按Base64将文件转换字节流
	 * @return
	 * @throws IOException
	 */
    public String ioToBase64(String fullFileName) throws IOException {
//        String fullFileName = "d:/gril.gif"; //源文件
        String strBase64 = null;
        try {
            InputStream in = new FileInputStream(fullFileName);
            // in.available()返回文件的字节长度
            byte[] bytes = new byte[in.available()];
            this.size = in.available() + "";
            // 将文件中的内容读入到数组中
            in.read(bytes);
            strBase64 = Base64.encode(bytes);      //将字节流数组转换为字符串
            this.sizeInByte = bytes.length;
            this.file = strBase64;
            in.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return strBase64;
    }
    
    /**
	 * 按Base64将文件转换字节流
	 * @return
	 * @throws IOException
	 */
    public String ioToBase64(HSSFWorkbook wb) throws IOException {
    	String tmpFileName =System.getProperty("java.io.tmpdir") +"jfq-temp.xls";
    	File file = new File(tmpFileName);
    	FileOutputStream fos = new FileOutputStream(file);
    	
        String strBase64 = null;
        
        try {
        	wb.write(fos);
        	fos.close();
        	
            InputStream in = new FileInputStream(tmpFileName);
            // in.available()返回文件的字节长度
            byte[] bytes = new byte[in.available()];
            this.size = in.available() + "";
            // 将文件中的内容读入到数组中
            in.read(bytes);
            strBase64 = Base64.encode(bytes);      //将字节流数组转换为字符串
            this.sizeInByte = bytes.length;
            this.file = strBase64;
            in.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        return strBase64;
    }
}
