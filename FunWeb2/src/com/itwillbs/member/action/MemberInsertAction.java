package com.itwillbs.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;


public class MemberInsertAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from MemberInsertAction) M: execute() 호출 완");
		// 1. 폼태그에서 submit 했으니 한글 데이터 넘어옴 -> 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// 2. 전달된 정보 Bean 필통에 저장
		MemberBean mb = new MemberBean();
		
		mb.setId(request.getParameter("id"));
		mb.setPw(request.getParameter("pw"));
		mb.setName(request.getParameter("name"));
		mb.setRegdate(new Timestamp(System.currentTimeMillis()));
		
		System.out.println("(from MemberInsertAction) M: " + mb);
		
		// 3. DB에 정보 저장
		// MemberDAO 객체 생성
		MemberDAO dao = new MemberDAO();
		
		// DB에 글 정보 저장
		dao.insertMember(mb);
		
		// 4. 페이지 이동 정보 저장 + forward 리턴 .... 여기서 이동하는 게 XXX !! 이동은 컨트롤러에서
		ActionForward forward = new ActionForward();
		// forward 객체 만들고 어디로 갈건지, 어떻게 갈건지 정해주기
		forward.setPath("./MemberLogin.me"); // 글 다 썼으니, 게시판 목록으로 이동하것다,, 가상주소 적어주고
		forward.setRedirect(true); // ㄴ 가상 주소로 이동하는 거니까,,, sendRedirect!!!!!!!!!!!! 
						// true -> sendRedirect()   /  false -> forward()
						//  주소 바뀜+화면도 바뀜   /  주소는 그대로 + 화면만 바뀜
		return forward;
	}// execute
}
