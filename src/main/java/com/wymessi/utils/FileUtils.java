package com.wymessi.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
}
