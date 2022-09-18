package com.itwillbs.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.action.Action;
import com.itwillbs.member.action.ActionForward;
import com.itwillbs.member.action.MemberInsertAction;

public class BoardFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("(from BoardFrontController_doProcess) doProcess() = doGet + doPost all 실행");

		System.out.println("\n1. 가상 주소 계산 시작---------------------------------------------");
		
		String requestURI = request.getRequestURI();
		System.out.println("(from BoardFrontController_doProcess) C: requestURI: " + requestURI);
		
		String ctxPath = request.getContextPath(); // 프로젝트 = 컨텍스트
		System.out.println("(from BoardFrontController_doProcess) C: contextPath: " + ctxPath);
		
		String command = requestURI.substring(ctxPath.length());
		System.out.println("(from BoardFrontController_doProcess) C: command: " + command);
		
		System.out.println("1. 가상 주소 계산 끝------------------------------------------------\n");

		
		
		System.out.println("\n2. 가상 주소 매핑 시작-------------------------------------------");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/BoardWrite.bo")){
			System.out.println("(from BoardFrontController_doProcess) C: /BoardWrite.bo 주소 호출됨");
			System.out.println("(from BoardFrontController_doProcess) C: DB 작업 O, 페이지 이동 O");
			
			forward = new ActionForward();
			forward.setPath("./center/write.jsp");
			forward.setRedirect(false); // Redirect 방식이냐? ㄴㄴ 아니다~ =forward방식으로 갈 것이다~~~
			
		}// BoardWrite.bo 끝 
		
		else if(command.equals("/BoardWriteAction.bo")){
			System.out.println("(from BoardFrontController_doProcess) C: BoardWriteAction.bo 호출");
			System.out.println("(from BoardFrontController_doProcess) C: DB 작업 O + 페이지 이동 O");
			
			action = new BoardWriteAction();
			
			try {
				forward = action.execute(request, response); // upcasting
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// BoardWriteAction.bo 끝
		
		else if(command.equals("/BoardList.bo")){
			// DB에서 select * 싹 들고와서,, 화면에 뿌려주기
			System.out.println("(from BoardFrontController_doProcess) C: BoardList.bo 호출");
			System.out.println("(from BoardFrontController_doProcess) C: DB 작업 O + 페이지 이동 O");
			
			action = new BoardListAction();
			
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}// BoardList.bo 끝
		
		else if(command.equals("/BoardListMain.bo")){
			System.out.println("(from BoardFrontController_doProcess) C: BoardListMain.bo 호출");
			
			action = new BoardListMainAction();
			
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}// BoardListMain.bo 끝
		
		else if(command.equals("/BoardContent.bo")){
			System.out.println("(from BoardFrontController_doProcess) C: BoardContent.bo 호출");
			
			action = new BoardContentAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}// BoardContent.bo 끝
		
		else if(command.equals("/BoardUpdate.bo")){
			System.out.println("(from BoardFrontController_doProcess) C: BoardUpdate.bo 호출");
			
			action = new BoardUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}// BoardUpdate.bo 끝
		
		else if(command.equals("/BoardUpdateProAction.bo")){
			System.out.println("(from BoardFrontController_doProcess) C: BoardUpdateProAction.bo 호출");
			
			action = new BoardUpdateProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}// BoardUpdateProAction.bo 끝
		
		// 댓글 구현 시작////////////////////////////////////////
		else if (command.equals("/CommentWrite.bo")){
			System.out.println("(from BoardFrontController_doProcess) C: /CommentWrite.bo 호출");
			
			action = new CommentWriteAction();
			
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} // CommentWrite.bo
		
		// 댓글 구현 끝////////////////////////////////////////
		
		System.out.println("\n3. 가상 주소 이동 시작-------------------------------------------------");
		if(forward != null){
			// 페이지 이동 정보가 있을 때!! = 통행권 있는 사람들 중에서,
			
			if(forward.isRedirect()){
				// 주소 변경되면서 이동 (가상 주소일 때는 무조건 !! sendRedirect)
				System.out.println("(from BoardFrontController_doProcess) C: isRedirect? true -> " + forward.getPath()+"로 이동, sendRedirect() 방식으로");
				response.sendRedirect(forward.getPath());
				
			} else {
				// 주소 변동 없이, request 값 가지고! 이동할 때 (jsp 페이지로 이동할 때+request영역에 저장했을 때는 무조건!! forward)  
				System.out.println("(from BoardFrontController_doProcess) C: isRedirect? false -> " + forward.getPath()+"로 이동, forward() 방식으로");
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				
			}
		}
		System.out.println("3. 가상 주소 이동 끝----------------------------------------------------\n\n\n");		
		
		
	
	
	} // doProcess

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println("(from MemberFrontController_doGet) get방식 호출 - doGet() 실행");
		doProcess(request, response);
	} // doGet

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println("(from MemberFrontController_doPost) post방식 호출 - doGet() 실행");
		doProcess(request, response);
	} // doPost
	
}
