package com.itwillbs.member.db;

import java.sql.Connection;

public class CommentService {
	// 싱글톤,,
	private static CommentService service = new CommentService();
	private CommentService(){}
	public static CommentService getInstance(){
		return service;
	}
	
	public boolean update(CommentDTO cdto){
		BoardDAO dao = new BoardDAO();
	}
	
}
