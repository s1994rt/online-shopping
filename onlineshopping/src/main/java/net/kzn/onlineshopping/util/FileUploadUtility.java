package net.kzn.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	
	private static final String ABS_PATH = "E:\\projects\\spring\\online-shopping\\onlineshopping\\src\\main\\webapp\\assets\\images";
	private static  String REAL_PATH = "";
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		
		REAL_PATH=request.getSession().getServletContext().getRealPath("/assets/images/");
		
		if(!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();
		}
		
		if(!new File(ABS_PATH).exists()){
			new File(ABS_PATH).mkdirs();
		}
		 try {
			 //Server upload
			 file.transferTo(new File(REAL_PATH + code +".jpg"));
			 //Project dirrectory upload
			 file.transferTo(new File(ABS_PATH + code +".jpg"));
		 }
		 catch(IOException ex) {
			 
		 }
	}

}
