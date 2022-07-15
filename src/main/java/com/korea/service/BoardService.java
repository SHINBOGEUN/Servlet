package com.korea.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Part;
import com.korea.dao.BoardDAO;
import com.korea.dto.BoardDTO;
public class BoardService {
	BoardDAO dao = BoardDAO.getInstance();
	private String UploadPath = "C://upload/";
	//싱글톤 패턴
	private static BoardService instance=null;
	public static BoardService getInstance() {
		if(instance==null)
			instance=new BoardService();
		return instance;
	}
	private BoardService() {}
	
	public List<BoardDTO> getBoardList(int start, int end){
		return dao.Select(start, end);
	}
	public int getTotalCount() {
		return dao.getTotalCount();
	}
	
	public boolean PostBoard(BoardDTO dto) {
		return dao.Insert(dto);
	}
	public boolean PostBoard(BoardDTO dto, ArrayList<Part> parts) {
		//업로드 처리
		//하위폴더명(Email/2022-07-14/파일쌓이기~)
		//기본업로드Path+하위폴더명
		String email = dto.getWriter();
		Date now = new Date();
		//날짜포맷변경하기
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = simpleDateFormat.format(now);
		String subPath = email + "/" + date;
		//file클래스 경로 잡고
		File RealPath = new File(UploadPath + subPath);
		//하위폴더 생성
		if(!RealPath.exists()) {
			RealPath.mkdirs();
		}
		
		StringBuffer totalFilename = new StringBuffer();
		StringBuffer totalFilesize = new StringBuffer();
		
		//Parts의 각 Part를 write()
		for(Part part : parts) {
			if(part.getName().equals("files")){
				
				String FileName = getFileName(part); //파일이름 가져오기
					
				totalFilename.append(FileName + ";");
				
				totalFilesize.append(String.valueOf(part.getSize()) +  ";");
				
				
				 
				try {
				int start = FileName.length()-4;
				int end = FileName.length();
				String ext = FileName.substring(start,end);
				FileName= FileName.substring(0, start-1);
				
				FileName = FileName+" " + UUID.randomUUID().toString() + ext;
				//DTO저장위한 파일명 buffer에 추가
				
				part.write(RealPath + "/" + FileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//총 파일명과 파일사이즈 저장
		
		dto.setFilename(totalFilename.toString());		
		dto.setFilesize(totalFilesize.toString());		
		
		//DAO 파일명 전달
		return dao.Insert(dto);
	}
	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] arr = contentDisp.split(";");
		String Filename = arr[2].substring(11, (arr[2].length()-1));
		return Filename;
	}
	public BoardDTO getBoardDTO(int no) {
		return dao.Select(no);
	}
}
