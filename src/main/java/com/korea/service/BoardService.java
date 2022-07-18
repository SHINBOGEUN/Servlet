package com.korea.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.korea.dao.BoardDAO;
import com.korea.dto.BoardDTO;

public class BoardService {

	private BoardDAO dao = BoardDAO.getInstance();
	private String UploadPath="C://upload/"; //File.seperator
	
	//싱글톤 패턴
	private static BoardService instance=null;
	public static BoardService getInstance() {
		if(instance==null)
			instance = new BoardService();
		return instance;
	}
	private BoardService() {}
	
	
	public List<BoardDTO> getBoardList(int start,int end)
	{
		return dao.Select(start, end);
	}
	
	public int getTotalCnt() {
		return dao.getTotalCount();
	}
	
	public boolean PostBoard(BoardDTO dto)
	{
		return dao.Insert(dto);
	}
	
	//파일포함 글쓰기 서비스
	public boolean PostBoard(BoardDTO dto,ArrayList<Part> parts)
	{
		//업로드 처리
		//1) 하위폴더명(Email/2022-07-14/파일쌓이기~)
		//2) 기본업로드Path+하위폴더명
		String email=dto.getWriter();
		Date now = new Date(); 
		//날짜포맷변경하기 : https://junghn.tistory.com/entry/JAVA-%EC%9E%90%EB%B0%94-%EB%82%A0%EC%A7%9C-%ED%8F%AC%EB%A7%B7-%EB%B3%80%EA%B2%BD-%EB%B0%A9%EB%B2%95SimpleDateFormat-yyyyMMdd
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		String date = simpleDateFormat.format(now); 
		
		String subPath=email+"/"+date;
		
		//3) File클래스 경로 잡고
		File RealPath= new File(UploadPath+subPath);
		
		//4) 하위폴더 생성
		if(!RealPath.exists())
			RealPath.mkdirs();
		
		
		//파일명 저장위한 StringBuffer 추가
		StringBuffer totalFilename = new StringBuffer();
		//파일사이즈 저장위한 StringBuffer 추가
		StringBuffer totalFilesize = new StringBuffer();
		
		
		//5) Parts의 각 Part 를 write()
		System.out.println("Parts : "+ parts.size());
		for(Part part : parts)
		{
			if(part.getName().equals("files"))
			{
				String FileName=getFileName(part); //파일이름 가져오기

	
				
				//파일명 ,확장자명 구분하기
				
				 
				int start=FileName.lastIndexOf(".");		//확장자구하기 위한 시작idx
				int end=FileName.length();					//확장자구하기 위한 끝idx
				String ext=FileName.substring(start,end);	//파일명잘라내기(확장자만)
				FileName = FileName.substring(0,start);	//파일명잘라내기(확장자제외)
				
				//파일명 + _UUID + 확장자
				FileName=FileName+"_"+UUID.randomUUID().toString()+ext;
				
				//DTO저장위한 파일명 buffer에추가
				totalFilename.append(FileName+";");
				//DTO저장위한 파일사이즈 buffer에추가				 
				totalFilesize.append(String.valueOf(part.getSize())+";");
				
				
				
				 try {
					part.write(RealPath+"/"+FileName); //파일업로드
				} catch (IOException e) {	 
					e.printStackTrace();
				}
			}
		}
		
		//Dto에 총파일명과 총파일사이즈를 저장
		dto.setFilename(totalFilename.toString());
		dto.setFilesize(totalFilesize.toString());
		
		//Dao 파일명전달 
		return dao.Insert(dto);
	}
	
	//파일명추출(PostBoard(dto,parts)가 사용)
	private String getFileName(Part part)
	{
		String contentDisp=part.getHeader("content-disposition");
		System.out.println(contentDisp);
		String[] arr = contentDisp.split(";"); // 배열화 		
		String Filename=arr[2].substring(11,arr[2].length()-1);	
		return Filename;
	}
	
	
	//게시물 하나 가져오기
	public BoardDTO getBoardDTO(int no)
	{
		return dao.Select(no);
	}
	
	
	//단일파일 다운로드
	public boolean download
	(		String filename,
			HttpServletRequest req,
			HttpServletResponse resp
	)
	{
		
		System.out.println("파일명 ! : " + filename);
		
		
		//파일명,	//등록날짜
		//이메일계정 가져오기
		HttpSession session = req.getSession();
		BoardDTO dto = (BoardDTO)session.getAttribute("dto");
		
		String email = dto.getWriter();
		String regdate = dto.getRegdate();
		regdate = regdate.substring(0,10);
		
		//System.out.println("REGDate : " +regdate);
		//1 경로설정
		String downdir="c://upload";	
		String filepath= downdir+"/"+email+"/"+regdate+"/"+filename;
		 
		//2 헤더설정
		resp.setContentType("application/octet-stream");


		//3 문자셋 설정
		try {
			
			filename=URLEncoder.encode(filename,"utf-8").replaceAll("\\+", "%20");
			resp.setHeader("Content-Disposition", "attachment; fileName="+filename);
		
			
			//04스트림형성(다운로드 처리)
			FileInputStream fin = new FileInputStream(filepath);
			ServletOutputStream bout=resp.getOutputStream();
			
			int read=0;
			byte[] buff = new byte[4096];
			while(true)
			{
				read=fin.read(buff,0,buff.length);		 
				if(read==-1)	 
					break;		 		
				bout.write(buff,0,read);	 
			}
			bout.flush();	
			bout.close();	
			fin.close();
			
			return true;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	 
		
		
		return false;
	}
	
	
	
	
	
}