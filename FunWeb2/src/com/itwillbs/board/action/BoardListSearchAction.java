package com.itwillbs.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.action.Action;
import com.itwillbs.member.action.ActionForward;
import com.itwillbs.member.db.BoardDAO;
import com.itwillbs.member.db.BoardDTO;

public class BoardListSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("(from BoardListSearchAction) M: execute 메서드를 호출하셨나요^^");
		// 검색어 입력받은 값 가져와야 하니까 한글 처리
		request.setCharacterEncoding("utf-8");
		// 검색어 파라메타값 가져오기
		String search = request.getParameter("search");
		
		
		// DB에 대한 처리 할 거니까 DAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		// 게시판에 작성되어 있는 전체 글 개수 (검색어 포함하는~~!!!! 전체 글의 개수)
		int cnt = dao.getBoardCount(search);
		
		// 페이징 처리 -----------------------------------------------------------------------------
		// 모르겠으면 외우기~~ ^_ㅠ

		// http://localhost:8080/Model2/BoardList.bo?pageNum=2&pageSize=7
												// 2페이지      한 페이지에 글 7개씩 보여도
		
		// 한 페이지에 보여줄 글의 개수 설정
		String urlpageSize = request.getParameter("pageSize"); // 네이버 쇼핑처럼 ㅎ
			if(urlpageSize == null) {
				urlpageSize = "15"; 
			}
		
	  //int pageSize = 5; // 내가 개수 딱 정해놓는거    // 구
		int pageSize = Integer.parseInt(urlpageSize);   // 신
		
		// 현 페이지가 몇 번째 페이지인지 계산...
			// 기존 페이지 정보가 없을 경우에는 항상 1페이지!로 설정할 거
		String pageNum = request.getParameter("pageNum"); // 파라메타로 받아오네??? 페이지 움직일 때 페이지 넘 파라메타 보내야한다~
									// http://localhost:8080/Model2/BoardList.bo?pageNum=2 하면 2페이지 보이네?? 우와
									// 쇼핑몰 20개씩 보기, 40개씩 보기,, 이것도 파라메타 이렇게 변함
		if(pageNum == null){
			pageNum = "1";
		}
		
		// 시작행 번호 계산
			// 첫 페이지 젤 첫번째 글 = 1번, 
			// 두번째 페이지 젤 첫번째 글 = 11번,  
			// 세번째 페이지 젤 첫번째 글 = 21번,  
			// 네번째 페잉지 젤 첫번째 글 = 31번,, 인데,, 뒤집어서 정렬했으니까 번호는 바뀐거 이해 가지??? ㅇㅋㅇㅋ
		int currentPage = Integer.parseInt(pageNum); // String 타입인 pageNum -> int로 바꿈
		
		int startRow = (currentPage -1) * pageSize + 1;
					//  ( 1page - 1 ) * 10 + 1 === 1
					//  ( 2page - 1 ) * 10 + 1 === 11
					//  ( 3page - 1 ) * 10 + 1 === 21 인데,, 뒤집어서 정렬했으니까 번호는 바뀐거 이해 가지??? ㅇㅋㅇㅋ
		
		
		// 끝행 번호 계산
			// 첫 페이지 젤 막 글 = 10번, 
			// 두번째 페이지 젤 막 글 = 20번,  
			// 세번째 페이지 젤 막 글 = 30번,  
			// 네번째 페잉지 젤 막 글 = 40번,, 인데,, 뒤집어서 정렬했으니까 번호는 바뀐거 이해 가지??? ㅇㅋㅇㅋ
		int endRow = currentPage * pageSize;
					// 1page * 10 === 10
					// 2page * 10 === 20
					// 3page * 10 === 30 인데,, 뒤집어서 정렬했으니까 번호는 바뀐거 이해 가지??? ㅇㅋㅇㅋ
		
		// DB에서 들고올 때,, 한방에 들고 오는 거 vs 10개씩 나눠서 따로 따로 들고 오는 거
		//                                       <<<< 
		
		
		
		
		// 페이징 처리 -----------------------------------------------------------------------------
		
		
		
		
		
		// dao 메서드 중에서 게시판 글 정보 모두 가져오는 메서드 호출
//		List<BoardDTO> boardList = dao.getBoardList();
		
		// 모두 가져오지 말고,, 나눠서 가져오기 위한 메서드
		List<BoardDTO> boardList = dao.getBoardList(startRow, pageSize, search); // 메서드 또 또 오버로딩!! 매개변수 다르게 하나 더 만들고 오자
		
		System.out.println("(from BoardListAction) M: 게시판 글 정보 저장 완"); ///???????????
		
		
		
		
		// 페이징 처리2 (하단 페이지 링크... 이전, 다음,, 1 2 3페이지ㅡ,,,,,) ----------------------------
		
		// 전체 페이지 수 계산
		// ex. 전체 글 50개 -> 한 페이지에 10개씩 출력하면?  5개 페이지 필요함
		//     전체 글 55개 -> 한 페이지에 10개씩 출력하면?  6개 페이지 필요함 50 + 5
		
		int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1 ); // js에서 사과 박스 구하던 로직,,이랑 똑같
											// 나머지 없으면 0 더하고 : 나머지 있으면 1페이지 더하고
		
		// 한 화면에 보여줄 페이지 수(= 페이지 블럭) 몇 개 할거냐??? 정하기     이전 1 2 3 4 5.. 9 10 다음
		int pageBlock = 10;  
		
		// 페이지 블럭의 시작 번호.. 1~10번 페이지블럭     11~20 페이지 블럭    21~30 페이지 블럭
								    //  시작 번호: 1          11                   21
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
					//       1  -       1  /     10     *     10    + 1  === 1
					//       3  -       1  /     10     *     10    + 1  === 1     int니까 나머지 버려서,, 1~10까지는 다 0
					//      11  -       1  /     10     
		
		// 페이지 블럭의 끝번호 
			// 1~10 페이지블럭 끝번호: 10
			// 11~20 페이지블럭 끝번호: 20
			// 21~30 페이지블럭 끝번호: 30
		int endPage = startPage + pageBlock -1;
		
		// 총 페이지 수와 ---  페이지 블럭 끝번호 비교 ??????????
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		// 페이징 처리2 (하단 페이지 링크... 이전, 다음,, 1 2 3페이지ㅡ,,,,,) ---------------------------- 끝
		
		
		// Model(지금 여기.. Action) -> view 페이지로 boardList 정보 전달을 위해, request 영역에 저장
		request.setAttribute("boardList", boardList);
//		request.setAttribute("bno", boardList.get(0)); // boardList에서 -> dto 에서 ->  bno 값 꺼내고 싶은데ㅠ
//		System.out.println("(from BoardListAction) M: BoardList: " + boardList.get(0).getBno());
		System.out.println("(from BoardListAction) M: BoardList 정보 request 영역에 저장 완");
//		System.out.println("(from BoardListAction) M: bno: " + boardList.); // 왜 얘는 콘솔에도 안 나옴? ㄱ-
		
		// + 페이징 처리 정보 전달을 위해 request 영역에 저장
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("cnt", cnt);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		System.out.println("(from BoardListAction) M: 페이징 처리 정보 request 영역에 저장 완");
		
		// ★★★ search 검색어 값 가져갈 수 있게 request에 저장 ★★★
		request.setAttribute("search", search);
		
		// 받아온 list.. 화면에 출력하기!!  <<????????????
		// 페이지 이동(화면 전환)하기 위해 ActionForward 객체 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./center/noticeSearch.jsp"); // 갈 곳, 어떻게 갈지
		forward.setRedirect(false); // 화면만 바뀌는 forward 방식으로 갈거니까 false.. 주소 안 변하면서 이동할거니까
		
		return forward;
		
	}

}
