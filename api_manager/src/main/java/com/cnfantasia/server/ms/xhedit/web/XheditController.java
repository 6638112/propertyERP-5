package com.cnfantasia.server.ms.xhedit.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * xhedit文件上传
 * 
 * @author liyulin
 * @version 1.0 2016年5月16日 上午11:06:48
 */
@Controller
@RequestMapping("/xhedit")
public class XheditController extends BaseController{

    // 上传文件数据处理过程
	@RequestMapping("/upload.html")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        String err = "";
        String newFileName = "";
        String baseUrl = OmsSysParamManager.getImageServerUrl(PathConstants.XHEDIT) + PathConstants.XHEDIT;
        String yyyyMMFileFolder = new SimpleDateFormat("yyyyMM").format(new Date()); //按月存入目录
        String filepathString = "";
        if ("application/octet-stream".equals(request.getContentType())) { //HTML 5 上传
            try {
                String dispoString = request.getHeader("Content-Disposition");

                int iFindStart = dispoString.indexOf("filename=\"") + 10;
                int iFindEnd = dispoString.indexOf("\"", iFindStart);
                String sFileName = dispoString.substring(iFindStart, iFindEnd);

                int i = request.getContentLength();
                byte buffer[] = new byte[i];
                int j = 0;
                while (j < i) { //获取表单的上传文件
                    int k = request.getInputStream().read(buffer, j, i - j);
                    j += k;
                }

                filepathString = getSaveFilePath(sFileName, yyyyMMFileFolder, response);

                OutputStream out = new BufferedOutputStream(new FileOutputStream(filepathString, true));
                out.write(buffer);
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                newFileName = "";
                err = "错误: " + ex.getMessage();
            }
        } else {
            DiskFileUpload upload = new DiskFileUpload();

            try {
                List<FileItem> items = upload.parseRequest(request);
                Map<String, Serializable> fields = new HashMap<String, Serializable>();
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        fields.put(item.getFieldName(), item.getString());
                    } else {
                        fields.put(item.getFieldName(), item);
                    }
                }
                FileItem uploadFile = (FileItem) fields.get("filedata"); //获取表单的上传文件
                String fileNameLong = uploadFile.getName(); //获取文件上传路径名称
                filepathString = getSaveFilePath(fileNameLong, yyyyMMFileFolder, response);

                File savefile = new File(filepathString);
                uploadFile.write(savefile); //存储上传文件
            } catch (Exception ex) {
                ex.printStackTrace();
                newFileName = "";
                err = "错误: " + ex.getMessage();
            }
        }

        newFileName = baseUrl + yyyyMMFileFolder+"/"+ filepathString.substring(filepathString.lastIndexOf("/")+1);
        printInfo(response, err, newFileName);
    }

    /**
     * 获取保存文件的路径
     *
     * @param sFileName
     * @param response
     * @param yyyyMMFileFolder 按月存入目录
     * @return
     * @throws IOException
     */
    public String getSaveFilePath(String sFileName, String yyyyMMFileFolder, HttpServletResponse response) throws IOException {
    	String extensionName = sFileName.substring(sFileName.lastIndexOf(".") + 1); //获取文件扩展名
        String basePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.XHEDIT;
        
        String saveFilePath = basePath + yyyyMMFileFolder + "/"; //文件存储的相对路径

        File fileDir = new File(saveFilePath); //构建文件目录以及目录文件
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String filename = UUID.randomUUID().toString(); //重命名文件

        return saveFilePath + filename + "." + extensionName;
    }

    // 使用I/O流输出 json格式的数据
    public void printInfo(HttpServletResponse response, String err, String newFileName) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("{\"err\":\"" + err + "\",\"msg\":\"" + newFileName + "\"}");
        out.flush();
        out.close();
    }
}
