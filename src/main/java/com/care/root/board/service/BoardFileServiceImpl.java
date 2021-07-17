package com.care.root.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;


public class BoardFileServiceImpl implements BoardFileService{
	public String getMessage(int num, HttpServletRequest req) {
		String message = null;
		if(num == 1) {
			message = "<script>alert('새글이 추가되었습니다');";
			message += "location.href='"+req.getContextPath()+
					"/board/boardAllList'</script>";
		}else {
			message = "<script>alert('문제가발생되었습니다.');";
			message += "location.href='"+req.getContextPath()+
					"/board/writeForm'</script>";
		}
		return message;
	}
	public String saveFile(MultipartFile file) {
		SimpleDateFormat simpl = new SimpleDateFormat("yyyyMMddHHmmss-");
		Calendar calendar = Calendar.getInstance();
		String sysFileName = 
		simpl.format(calendar.getTime()) + file.getOriginalFilename();
		File saveFile = new File(IMAGE_REPO +  "/" + sysFileName);
		try {
			file.transferTo(saveFile);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return sysFileName;
	}
	public String getMessage(String msg, String url) {
		String message;
		message = "<script>alert('"+ msg +"');";
		message += "location.href='"+ url +"'</script>";
		return message;
		
	}
	
	public void deleteImage(String originFileName) {
		File deleteFile = new File(IMAGE_REPO + "/" + originFileName);
		deleteFile.delete();
	}
}

