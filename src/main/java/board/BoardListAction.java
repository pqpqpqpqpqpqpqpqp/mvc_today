package board;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.ActionForward;


public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		boardDAO dao = new boardDAO();
		List<boardVO> list = null;
		
		String keyword = request.getParameter("keyword");
		String category = request.getParameter("category");

		// 검색 조건
		if (keyword != null && !keyword.trim().isEmpty() &&
		   category != null && !category.trim().isEmpty()) {
			
			// 키워드, 카테고리 전부 있을 때
			list = dao.searchByKeywordAndCategory(keyword, category);
		}
		// 키워드만 있을 때
		else if (keyword != null && !keyword.trim().isEmpty()) {
			list = dao.searchByKeyword(keyword);
		}
		// 카테고리만 있을 때
		else if (category != null && !category.trim().isEmpty()) {
			list = dao.searchByCategory(category);
		}
		// 검색 조건이 없을 때
		else {
			list = dao.boardList();
		}
		
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/boardList.jsp");
		return null;
	}

}
