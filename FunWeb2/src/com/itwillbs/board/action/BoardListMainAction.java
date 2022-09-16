package com.itwillbs.board.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.itwillbs.member.action.Action;
import com.itwillbs.member.action.ActionForward;
import com.itwillbs.member.db.BoardDAO;
import com.itwillbs.member.db.BoardDTO;

public class BoardListMainAction implements Action {


	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from BoardListMainAction) M: execute 메서드를 호출하셨나요^^");
		
		// DB에 대한 처리 할 거니까 DAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		// 게시판에 작성되어 있는 전체 글 개수 (<- DAO 가서 getBoardCount 메서드 만들고 오기)
		// cnt 변수에 저장
		int cnt = dao.getBoardCount();
		
		// 페이징 처리 -----------------------------------------------------------------------------
		
		// 한 페이지에 보여줄 글의 개수 설정
		// 최근글 5개 가져올 거니까~~~~~ 페이지Num도 1 
		String urlpageSize = "5"; 
		
		int pageSize = Integer.parseInt(urlpageSize);  
		
		String pageNum = "1";
		
		// 시작하는 행번호 1로 셋팅
		int startRow = 1;
		
		
		// 모두 가져오지 말고,, 나눠서 가져오기 위한 메서드
		List<BoardDTO> boardList = dao.getBoardList(startRow, pageSize); 
		
		System.out.println("(from BoardListMainAction) M: 게시판 글 정보 저장 완"); ///???????????
		

		// 페이징 처리 2 (하단 페이지 링크) ------------------------------------------ 
		// json 변경해서 출력
		JSONArray boardList2 = new JSONArray();
		
		for(int i = 0; i < boardList.size(); i++){
			BoardDTO dto = boardList.get(i);
			
			JSONObject dto2 = new JSONObject();
			dto2.put("bno", dto.getBno());
			dto2.put("subject", dto.getSubject());
			// 날짜를 패턴에 맞게 문자로 변경해서 저장 (yyyy.MM.dd) 
			// jQuery에서 날짜가 숫자로 바뀌어서 날아오기 때메,, 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
			dto2.put("date", dateFormat.format(dto.getDate()));
			
			// 하나의 글을 => 배열 한 칸에 저장
			boardList2.add(dto2);
			
		}// for
		
		response.setContentType("text/html; charset=UTF-8"); // jsp 파일 맨 위에 코드에 그거,,^^
		PrintWriter out = response.getWriter(); 
		out.println(boardList2);
		out.close();
		
		return null; // ajax니까~~~ 페이지 이동할 필요 X forward == null
	}

}
