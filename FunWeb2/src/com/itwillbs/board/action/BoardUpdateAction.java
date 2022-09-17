package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.action.Action;
import com.itwillbs.member.action.ActionForward;
import com.itwillbs.member.db.BoardDAO;
import com.itwillbs.member.db.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from BoardUpdateAction) M: execute() 메서더 호출됨");
		
		// 파라메타에서 넘어온,, 전달된,, 정보 (bno) 저장
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		// BoardDAO 객체 생성하고,, (for 조회수 증가 메서드 쓸라고,,, DB 쓸라고,,)
		BoardDAO dao = new BoardDAO();
		
		System.out.println("(from BoardUpdateAction) M: 조회수 +1 완");
		
		
		// 게시판 글 1개의 정보를 가져와서 출력 == 멤버 1명 정보 출력했었던 거랑 똑같음!! 그치그치,,
		// getBoard(bno) 메서드 (리턴타입: dto) 만들고 옵시다
		BoardDTO dto = dao.getBoard(bno);
		
		
		// Model에서 객체 정보 출력 X 
		// view에서 정보 출력 O!!!!! 보여주니까 view니까,,
		// so.. ==> model 객체(지금,, 여기,, action 페이지)에 있는 정보를  --> view로 이동시키자!!!!!!!
		
		// request 영역에 저장
		request.setAttribute("dto", dto);
		
		// 데이터 다 만들었고, request에 저장까지 해놨으니,, 인제 이 페이지에서 할 거 없음.. 나가자~~
		// 출력할 view 페이지로 이동
		//   이동할거니까 통행권 객체 생성 (어디로 갈지, 어떻게 갈지)
		ActionForward forward = new ActionForward();
		forward.setPath("./center/update.jsp");
		forward.setRedirect(false); // request 영역에 뭔가 저장한 순간,, 무조건 forward로 이동해야 함!!!!!!!!!!!! ★★
		
		return forward; // execute메서더 리턴타입은 ActionForward^^ 잊지 말자
	}

}
