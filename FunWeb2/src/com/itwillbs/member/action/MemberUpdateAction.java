package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// 수정할 회원 정보 mb에 담아놨었으니까 그거 가져오기
		MemberBean mb = new MemberBean();
		mb.setId(request.getParameter("id"));
		mb.setPw(request.getParameter("pw"));
		mb.setName(request.getParameter("name"));
		mb.setEmail(request.getParameter("email"));
		System.out.println(mb.getId() + mb.getPw());
		
		// DB 쓸 거~~ 회원 정보 수정 updateMember 메서드 (리턴타입 int) 호출
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMember(mb);
		
		if(result == 0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 오류');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;

		} else if(result == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;

		} else {

			// 페이지 이동 정보 저장
			ActionForward forward = new ActionForward();
			forward.setPath("./Main.me"); // 가상주소로 갈거니까
			forward.setRedirect(true); //sendRedirect
			return forward;
			
		}// else
	}

}
