package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.ActionForward;

public class BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionForward forward = new ActionForward();
		boardDAO dao = new boardDAO();
		String idx = req.getParameter("board_idx");

		int boardIdx = Integer.parseInt(idx);
		boardVO boardVO = dao.boardDetail(boardIdx);

		req.setAttribute("boardVO", boardVO);

		forward.setRedirect(false);
		forward.setPath("boardUpdate.jsp");

		return forward;
	}

}
