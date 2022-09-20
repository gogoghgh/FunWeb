package com.itwillbs.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.action.Action;
import com.itwillbs.member.action.ActionForward;
import com.itwillbs.member.db.BoardDAO;
import com.itwillbs.member.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileBoardUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from FileBoardUpdateProAction) M: execute() 메서드 호출됨");
		
		// 업로드 MultipartRequest 객체 생성
		String uploadPath = request.getRealPath("/upload");
		int maxSize = 10*1024*1024;
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
//		// 1. BoardDTO 객체 생성하고  --> 파라미터로 넘어온 정보 dto에 저장
//		String pageNum = request.getParameter("pageNum");
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(Integer.parseInt(multi.getParameter("bno"))); // 파라메타로 String 타입으로 넘어와서,, int로 바꿔주기
		dto.setPass(multi.getParameter("pass"));
		dto.setName(multi.getParameter("name"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		// file
		if (multi.getFilesystemName("file") != null) {
			// 수정할 첨부파일(name="file") 있는 경우
			dto.setFile(multi.getFilesystemName("file"));
							// getParameter가 아니죠!!!!!! 
		} else {
			// 수정할 첨부파일(name="file")이 없는 경우 => 기존 oldfile 가져와서 저장
			dto.setFile(multi.getParameter("oldfile"));
		}
		
		
		// 2. 수정할 데이터(입력받아온 dto!!)를 DB로 보내서 정보 수정하기 (update)
		BoardDAO dao = new BoardDAO(); // DB 써야 하니까 dao..

		// dao 가서 글 수정하는 메서드(6. updateBoard) 만들고 오기!!
		//                              ㄴ 리턴타입: result = -1 | 0 | 1
		int result = dao.updateBoard(dto);
		
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
		forward.setRedirect(true);
		return forward;
	}

}
