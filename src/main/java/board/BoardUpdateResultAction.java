package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.ActionForward;

public class BoardUpdateResultAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		boardVO boardVO=new boardVO();
		boardVO.setBoardIdx(Integer.parseInt(req.getParameter("board_idx")));
		boardVO.setBoardTitle(req.getParameter("board_title"));
		boardVO.setBoardContent(req.getParameter("board_content"));
		boardVO.setTravelLocation(req.getParameter("travel_location"));
		boardVO.setTravelBudget(Integer.parseInt(req.getParameter("travel_budget")));
		boardVO.setTravelPeriod(req.getParameter("travel_budget"));
		boardVO.setBoardCategory(req.getParameter("board_category"));
		
		boardDAO dao = new boardDAO();
		dao.boardUpdate(boardVO);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/board/list.bo");
		
		
		return forward;
	}

}
