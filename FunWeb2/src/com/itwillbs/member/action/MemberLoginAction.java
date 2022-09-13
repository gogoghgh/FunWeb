package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;

public class MemberLoginAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 멤버빈 객체 생성
		MemberBean mb = new MemberBean();
		
		// id, pw
		mb.setId(request.getParameter("id"));
		mb.setPw(request.getParameter("pw"));
		
		// DAO 객체 챙성
		MemberDAO dao = new MemberDAO();
		
		// 로그인 체크 메서드 loginMember()
		int result = dao.loginMember(mb);
		
		if(result == 0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 오류');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
/*			스크립틀릿 안에 내용을 위에처럼 바꿔주기^^
 * 				%>
				<script type="text/javascript">
					alert("비밀번호 오류");
					history.back();
				</script>		
			<%*/
		} else if(result == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
/*			%>
				<script type="text/javascript">
					alert("아이디 없음");
					history.back();
				</script>		
			<%*/
		} else {
			// 아이디 비번 일치 = 로그인 성공
			System.out.println("(from MemberLoginAction) 로그인 성공 result: " + result);
			
			// 세션값 생성
			HttpSession session = request.getSession(); // java에서 세션 쓰려면 요 라인 추가
			session.setAttribute("loginID", mb.getId());
			
			// main.jsp로 이동이 아니라~~
//			response.sendRedirect("../main/main.jsp");
//			System.out.println("(from loginPro.jsp) 세션값 생성 완 -> main.jsp로 이동할게염");
			
			// 페이지 이동 정보 저장
			ActionForward forward = new ActionForward();
			forward.setPath("./Main.me"); // 가상주소로 갈거니까
			forward.setRedirect(true); //sendRedirect
			return forward;
			
		}// else
		
	}

}
