package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;

public class MemberUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션값 가져오기
		HttpSession session = request.getSession();
		String loginID = (String)session.getAttribute("loginID");
		
		// MemberDAO_getMember 회원 정보 조회 메서드 호출 (리턴타입 MemberBean)
		MemberDAO dao = new MemberDAO();
		MemberBean mb = dao.getMember(loginID); 
		
		// 회원 정보를 mb에 담아서 -> update.jsp 로 이동할~~ 이동 정보 저장
		request.setAttribute("mb", mb);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./member/update.jsp");
		forward.setRedirect(false); // forward 방식으로 이동~ jsp페이지로 가고, request 영역에 세션값 저장했으니까
		
		return forward;
	}

}
