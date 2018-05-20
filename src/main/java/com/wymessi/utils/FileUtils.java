package com.wymessi.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件处理工具类
 * 
 * @author 王冶
 *
 */
public class FileUtils {

	public static String upload(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename(); // 文件名带后缀
		String savePath = "D:" + File.separator + "prsUploadFiles"+File.separator; // 文件保存路径
		byte buf[] = file.getBytes();
		File parent = new File(savePath);
		if (!parent.exists()) {
			parent.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(new File(savePath+fileName));
		out.write(buf);
		out.flush();
		out.close();
		return savePath+fileName;
	}
	
	public static String download(String path,HttpServletResponse res, HttpServletRequest req) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			return "下载的文件不存在，请联系管理员！";
		}
		String fileName = path.substring(path.lastIndexOf("\\")+1);
		
        if (req.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
        	fileName = URLEncoder.encode(fileName, "UTF-8");  
        } else {  
        	fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");  
        }  
        res.setHeader("Content-Disposition","attachment;"+ "filename="+ fileName);
        res.setContentType("application/octet-stream");
        
       /* 
        * 该段代码也是正确的
        * 
        * String userAgent = req.getHeader("User-Agent");  
            
        // 针对IE或者以IE为内核的浏览器：  
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {  
        	fileName = java.net.URLEncoder.encode(fileName, "UTF-8");  
        } else {  
            // 非IE浏览器的处理：  
        	fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");  
        }  
        res.setHeader("Content-disposition",  
                String.format("attachment; filename=\"%s\"", fileName));  
        res.setContentType("application/vnd.ms-excel;charset=utf-8");  
        res.setCharacterEncoding("UTF-8"); */
        
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
          os = res.getOutputStream();
          bis = new BufferedInputStream(new FileInputStream(new File(path)));
          int i = bis.read(buff);
          while (i != -1) {
            os.write(buff, 0, buff.length);
            os.flush();
            i = bis.read(buff);
          }
        } catch (IOException e) {
          e.printStackTrace();
          return "下载失败，请稍后重试";
        } finally {
          if (bis != null) {
            try {
              bis.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
          if (os != null) {
              try {
                os.close();
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
        }
        return "下载成功";
	}
}
