package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;

public class MemberIdCheck implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 'id':$('.id').val()
		String id = request.getParameter("id");
		
		// MemberDAO 객체 생성
		MemberDAO dao = new MemberDAO();
		
		// getMember(id) 메서드 호출
		MemberBean mb = dao.getMember(id); // 리턴값은 멤버빈에 담겨져 오지요
		String result="";
		
		if(mb.getId()!=null){
			// id가 null이 아님 == 기존 우리 DB에 똑같은 아이디 있음!! 중복임!!! 사용 불가
			result = "아이디 중복.. 다른 아이디를 입력해주세요";
		} else {
			// id가 null이다 == 기존 DB에 이 아이디 없음 == 중복 X == 아이디 사용 가능
			result = "아이디 사용 가능💝💝";
		}
		
		// 결과 출력
		response.setContentType("text/html; charset=UTF-8"); // jsp 파일 맨 위에 코드에 그거,,^^
		PrintWriter out = response.getWriter(); 
		out.println(result);
		
//		out.println("<script>"); // js를 java에서 쓰고 싶을 때,, 이렇게 하십시오,,,
//		out.println("alert('자바스크립트^^')");
//		out.println("</script>");
		
		out.close(); // 여까지 끝!! 자원 해제
		
		// 이동은 없음!!! ajax니까!!!!!!
		return null; // ajax니까 이동은 X지만,,, 출력 결과를 넘겨줘야 함 
	}

}
