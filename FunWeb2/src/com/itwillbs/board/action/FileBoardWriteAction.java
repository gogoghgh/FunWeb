package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.action.Action;
import com.itwillbs.member.action.ActionForward;
import com.itwillbs.member.db.BoardDAO;
import com.itwillbs.member.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileBoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from FileBoardWriteAction) M: execute() 호출 완");

//		// 1. 폼태그(writeForm.jsp)에서 글 쓰고,, submit 했으니 한글 데이터 넘어옴 -> 한글 처리
//		request.setCharacterEncoding("UTF-8");
		// 파일 업로드하는 프로그램에서 한글처리 해줘서 이건 필요 없음!!
		
		// 파일 업로드 먼저 하고!!! -> DTO에 담아서 -> DAO에 insert 하고 -> 이동할거
		
		
		// 1. 파일 업로드
		
		// 인수
		// 1) request 정보 가져옴
		// 2) 파일 업로드할 경로 (물리적인 경로)
				String uploadPath = request.getRealPath("/upload");
				//  C:\Users\ITWILL\workspace_JSP2\.meta data\.plug ins\org.eclipse.wst.server.core\tmp0\wtpweb apps\FunWeb2\ upload 와이라노?
				//   ㄴ 글 쓰기하면 여기(=가상 공간)에 업로드한 파일 들어와있음!! 댑악 ㄷㄷㄷ
		// 3) 파일 업로드 최대 크기 (ex. 최대 10MB 입니다~)
				int maxSize = 10*1024*1024;
		// 4) 한글처리!! 아~ 여기서 해서 따로 안 해줘도 되는거군
		// 5) 업로드 파일이 동일한 이름일 경우, 알아서 변경해주는 작업
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
						// 1)request 정보가 multi에 들어가게 돼서,, 밑에 request로 받던 애들 multi로 받아야 함!!!
		
		// 2. 전달된 정보 DTO 필통에 저장(제목, 비밀번호, 이름, 내용)
		// BoardDTO 객체 생성 & dto 필통에 form에서 넘어온 애들 담아주기
		BoardDTO dto = new BoardDTO();
		
		dto.setName(multi.getParameter("name"));
		dto.setPass(multi.getParameter("pass"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		// file => 업로드된 파일 이름
		// dto.setFile(multi.getOriginalFileName(arg0)); // 이거 말고 밑에걸로 써야 함!!! 파일 이름이 변경될 수도 있으니까~~~
		dto.setFile(multi.getFilesystemName("file")); // 파일이 시스템에 올라간 이름!! 
		
		
		// IP 주소 추가
		dto.setIp(request.getRemoteAddr());
		
		System.out.println("(from FileBoardWriteAction) M: " + dto);
		
		// 3. DB에 정보 저장
		// BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		// DB에 글 정보 저장
		dao.boardWrite(dto);
		
		// 4. 페이지 이동 정보 저장 + forward 리턴
		ActionForward forward = new ActionForward();
		// forward 객체 만들고 어디로 갈건지, 어떻게 갈건지 정해주기
		forward.setPath("./BoardList.bo"); // 글 다 썼으니, 게시판 목록으로 이동하것다,, 가상주소 적어주고
		forward.setRedirect(true); 
		
		return forward;
		
	}

}
