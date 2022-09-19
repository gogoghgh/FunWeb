package com.itwillbs.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.action.Action;
import com.itwillbs.member.action.ActionForward;
import com.itwillbs.member.db.BoardDAO;
import com.itwillbs.member.db.BoardDTO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// BoardDTO 객체 생성
		BoardDTO dto = new BoardDTO();
		
		// 파라미터값 저장
		dto.setBno(Integer.parseInt(request.getParameter("bno")));
		dto.setPass("1234"); // 로그인 한 상태에서 글 수정, 삭제하는 거니까,, 다시 비번 체크할 필요 X~~ 임의로 1234로 정해놓은 거
		
		BoardDAO dao = new BoardDAO(); // DB 써야 하니까 dao..

		// dao 가서 글 삭제하는 메서드(8. updateBoard) 만들고 오기!!
		//                              ㄴ 리턴타입: result = -1 | 0 | 1
		int result = dao.deleteBoard(dto);
		
		// 3. result = -1 | 0 | 1 에 따른 페이지 이동!!!!!!!!  
		//      1(=성공)일 때만 페이지 이동.... 0, -1 일 때는 자스에서 history.back 했었잖아염
		// 그걸 어떻게 하쥐~?!???   
		// Java에서 JS 호출하기!!!!!
		response.setContentType("text/html; charset=UTF-8"); // 응답 페이지는 html !!! 형태로 만들겠다~~
		PrintWriter out = response.getWriter(); // response 객체를 통해서,, 통로 만들겠다 ㅋ 응답 정보로 출력 객체 생성(통로 생성)
		
		if(result == 0){
			out.print("<script>");
			out.print("alert('비밀번호 오류...😥');");
			out.print("history.back();"); // js로 페이지 이동 완
			out.print("</script>");
			out.close(); // 자원 해제
			return null; // return= 메서드 종료! 지점,, 
						// 근데 null을 리턴한다??? null값이 Controller에 execute 메서드 호출한 결과값으로 넘어감..  
						// 그면 3단계로 이동 못 함~~^^ 휴~~ 다행이다~~~ 충돌 안 생김.. 걍 js로만 이동하겠다!!!!
					// == 컨트롤러를 통한 이동은 X 막은거고!!, JS만 사용해서 이동하도록 함
		} else if (result == -1) {
			out.print("<script>");
			out.print("alert('게시글 없음..😥');");
			out.print("history.back();"); // js로 페이지 이동 완
			out.print("</script>");
			out.close();
			return null;
		} 
		
		// result == 1 (정상 수정 완)  --> 페이지 이동!!! 
		
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(false);
		return forward;
		
	}

}
