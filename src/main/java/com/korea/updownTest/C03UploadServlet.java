package com.korea.updownTest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



@WebServlet("/upload3")
@MultipartConfig(
	fileSizeThreshold = 1024*1024*10, 	//10mb
	maxFileSize = 1024*1024*50,			//50mb
	maxRequestSize = 1024*1024*100		//100mb
			
)
//location : 업로드한 파일이 임시로 저장될 위치를 지정, 절대경로만 가능
//기본값은 해당 자바가 실행되는 temp폴더
//maxFileSize : 업로드 가능한 파일의 최대 크기를 바이트 안위로 지정, -1은 제한없음(기본값)
//fileSizeThresHold : 업로드한 파일의 크기가 태그값보다 크면 location 지정한 디렉토리에 임시파일조저

public class C03UploadServlet extends HttpServlet{
	

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		String subdir = UUID.randomUUID().toString();
		File upload = new File("C://upload/" + subdir);
		
		
		
		//하위 폴더 생성
		if(!upload.exists()) {
			upload.mkdir();
		}
		
		
		
		for(Part part : req.getParts()) {
			String FileName = getFileName(part); //파일이름 가져오기
			part.write("C://upload/" + subdir + "/" + FileName);
			
		}
		
		
		//request로 부터 파일정보(part)를 가져옴 "test"는 폼에 지정된 name
		Part part = req.getPart("test");
		
		
//		
//		String contentDisp = part.getHeader("content-disposition");
//		
//		String[] arr = contentDisp.split(";");
//		System.out.println("arr[2] : " + arr[2]);
//		System.out.println("FILENAME : " + arr[2].substring(11, (arr[2].length()-1)));
		
		//파일추출 함수 만들기(getFileName)
//		String Filename = getFileName(part);
//		System.out.println(Filename);
	}
	
	
	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] arr = contentDisp.split(";");
		String Filename = arr[2].substring(11, (arr[2].length()-1));
		
		return Filename;
	}
	
}
