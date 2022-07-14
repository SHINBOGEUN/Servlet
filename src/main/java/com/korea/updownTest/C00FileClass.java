package com.korea.updownTest;

import java.io.*;

public class C00FileClass {
	public static void main(String[] args) {
		File tmp = new File("c://upload");
		
		if(tmp.isFile()) {
			System.out.println("파일입니다.");
		}
		if(tmp.isDirectory()) {
			System.out.println("디렉토리입니다.");
			
		}
		
		//2경로
		System.out.println("PATH : " + tmp.getPath());			//상대경로
		System.out.println("PATH : " + tmp.getAbsolutePath());	//절대경로
		
		
		//3 디렉토리 생성
		if(!tmp.exists()) {
			tmp.mkdir();
		}
		System.out.println("------------------------");
		
		//4
		File[] list = tmp.listFiles();
		for (int i = 0; i < list.length; i++) {
			
			if(list[i].isFile()) {
				System.out.println("File : " + list[i]);
				System.out.println("File(이름만) : " + list[i].getName());
			}
			
		}
		//5 필터 처리
		System.out.println("------------------------");
		File[] list2 = tmp.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("s");
				//name.endWith("t");		//끝문자가 t인 
				//name.startsWith("s");		//첫문자가 s인
			}
		});
		
		for (int i = 0; i < list2.length; i++) {
			System.out.println(list2[i]);
		}
		
		
	}
}
