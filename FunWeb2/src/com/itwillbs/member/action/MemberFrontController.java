package com.itwillbs.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class MemberFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("(from MemberFrontController_doProcess) doProcess() = doGet + doPost all 실행");

		System.out.println("\n1. 가상 주소 계산 시작---------------------------------------------");
		
		String requestURI = request.getRequestURI();
		System.out.println("(from MemberFrontController_doProcess) C: requestURI: " + requestURI);
		
		String ctxPath = request.getContextPath(); // 프로젝트 = 컨텍스트
		System.out.println("(from MemberFrontController_doProcess) C: contextPath: " + ctxPath);
		
		String command = requestURI.substring(ctxPath.length());
		System.out.println("(from MemberFrontController_doProcess) C: command: " + command);
		
		System.out.println("1. 가상 주소 계산 끝------------------------------------------------\n");
		
		
		
		
		System.out.println("\n2. 가상 주소 매핑 시작-------------------------------------------");
		Action action = null;
		ActionForward forward = null; // 미리 선언,, 왜냐면 이 변수 여러 번 쓸거라서
		
		if(command.equals("/MemberInsert.me")){
			// ★★ 패턴1 ★★

			System.out.println("(from MemberFrontController_doProcess) C: /MemberInsert.me 주소 호출됨");
			System.out.println("(from MemberFrontController_doProcess) C: DB 정보 필요 없음 -> view 페이지로 이동시킬거임");
			
			// 4) 객체 생성해주기~ = 티켓 만들기~ -> 티켓 안에 정보 저장해주기
			forward = new ActionForward();
			forward.setPath("./member/join.jsp");
				// 찐 주소네? WebContent/board/writeForm.jsp 페이지로 가라~
			forward.setRedirect(false); // Redirect 방식이냐? ㄴㄴ 아니다~ =forward방식으로 갈 것이다~~~
			
			// 이동 완?? ㄴㄴㄴ 아직 안 갔고,, 티켓만 만들었음!!!
			// 이동하러 3단계로 고고
		}// if ---  /MemberInsert.me  --- 패턴1
		else if(command.equals("/MemberInsertAction.me")){
			// ★★ 패턴2 ★★
			System.out.println("(from MemberFrontController_doProcess) C: /MemberInsertAction.bo 호출");
			System.out.println("(from MemberFrontController_doProcess) C: DB 작업 O + 페이지 이동 O");
															//~~Action == pro 페이지니까!!
															// DB에 insert 해야 함,,
			
			// MemberInsertAction() 객체 생성 + 예외 처리,, ㅋ
//			MemberInsertAction miAction = new MemberInsertAction();
			action = new MemberInsertAction();
			// 上            下
			
			try {
				// ㄴ execute 메서드 실행 결과 -> 리턴값 forward 욜로 갖고 옴!! -> 3단계 가상 주소 이동으로^^ 아하~
//				forward = miAction.execute(request, response);
				forward = action.execute(request, response); // upcasting
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// else if --- /MemberInsertAction.me --- 패턴2
		else if(command.equals("/MemberIdCheck.me")){
			// ★★ 패턴2 ★★ ????
			System.out.println("(from MemberFrontController_doProcess) C: /MemberIdCheck.me 호출");
			System.out.println("(from MemberFrontController_doProcess) C: DB 작업 O + 페이지 이동 XXX");
																					// ajax!!!니까 페이지 이동 없이~
			
			// MemberIdCheck 객체 생성 + 예외 처리
			action = new MemberIdCheck();
			// 上            下
			
			try {
				// ㄴ execute 메서드 실행 결과 -> 리턴값 forward 욜로 갖고 옴!! -> 3단계 가상 주소 이동으로^^ 아하~
//				forward = miAction.execute(request, response);
				forward = action.execute(request, response); // upcasting
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// else if --- /MemberIdCheck.me끝
		else if(command.equals("/MemberLogin.me")){
			// 패턴1   // 패턴 1일 때는 여기서 forward에 저장하구나~~~ 
			System.out.println("(from MemberFrontController_doProcess) C: /MemberLogin.me 주소 호출됨");
			System.out.println("(from MemberFrontController_doProcess) C: DB 정보 필요 없음 -> view 페이지로 이동시킬거임");
			
			// 4) 객체 생성해주기~ = 티켓 만들기~ -> 티켓 안에 정보 저장해주기
			forward = new ActionForward();
			forward.setPath("./member/login.jsp");
			forward.setRedirect(false); // Redirect 방식이냐? ㄴㄴ 아니다~ =forward방식으로 갈 것이다~~~
			
			// 이동 완?? ㄴㄴㄴ 아직 안 갔고,, 티켓만 만들었음!!!
			// 이동하러 3단계로 고고
			
		}// else if --- /MemberLogin.me끝
		else if(command.equals("/MemberLoginAction.me")){
			// ★★ 패턴2 ★★
			System.out.println("(from MemberFrontController_doProcess) C: /MemberLoginAction.me 호출");
			System.out.println("(from MemberFrontController_doProcess) C: DB 작업 O + 페이지 이동 O");
			
			// MemberLoginAction() 객체 생성 + 예외 처리
			action = new MemberLoginAction();
			// 上            下
			
			try {
				forward = action.execute(request, response); // upcasting
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// else if --- /MemberLoginAction.me끝
		else if(command.equals("/Main.me")){
			//패턴1.. 페이지 보여주기만,,^^
			System.out.println("(from MemberFrontController_doProcess) C: /Main.me 주소 호출됨");
			System.out.println("(from MemberFrontController_doProcess) C: DB 정보 필요 없음 -> view 페이지로 이동시킬거임");
			
			forward = new ActionForward();
			forward.setPath("./main/main.jsp");
			forward.setRedirect(false); // jsp 파일로 이동하니까 forward방식.. 주소 변화 X
		}
		else if(command.equals("/MemberLogout.me")){
			System.out.println("(from MemberFrontController_doProcess) C: /MemberLogout.me 호출");
			System.out.println("(from MemberFrontController_doProcess) C: DB 작업 X + 페이지 이동 O");
			
			action = new MemberLogout();
			// 上            下
			
			try {
				forward = action.execute(request, response); // upcasting
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println("2. 가상 주소 매핑 끝-------------------------------------------\n");
		
		
		
		
		System.out.println("\n3. 가상 주소 이동 시작-------------------------------------------------");
		if(forward != null){
			// 페이지 이동 정보가 있을 때!! = 통행권 있는 사람들 중에서,
			
			if(forward.isRedirect()){
				// 주소 변경되면서 이동 (가상 주소일 때는 무조건 !! sendRedirect)
				System.out.println("(from MemberFrontController_doProcess) C: isRedirect? true -> " + forward.getPath()+"로 이동, sendRedirect() 방식으로");
				response.sendRedirect(forward.getPath());
				
			} else {
				// 주소 변동 없이, request 값 가지고! 이동할 때 (jsp 페이지로 이동할 때는 무조건!! forward)  
				System.out.println("(from MemberFrontController_doProcess) C: isRedirect? false -> " + forward.getPath()+"로 이동, forward() 방식으로");
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				
			}
		}
		System.out.println("3. 가상 주소 이동 끝----------------------------------------------------\n\n");
		
		
	} // doProcess

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("(from MemberFrontController_doGet) get방식 호출 - doGet() 실행");
		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("(from MemberFrontController_doPost) post방식 호출 - doPost() 실행");
		doProcess(request, response);
	}
	
}
