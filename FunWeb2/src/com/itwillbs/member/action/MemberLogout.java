package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogout implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션값 초기화
		HttpSession session = request.getSession(); // java에서 세션 쓰려면 요 라인 추가
		session.invalidate();
		
		// /Main.me로 이동
		// 페이지 이동 정보 저장
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me"); // 가상주소로 갈거니까
		forward.setRedirect(true); //sendRedirect
		return forward;
		
	}

}
