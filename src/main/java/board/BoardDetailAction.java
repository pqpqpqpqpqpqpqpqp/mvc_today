package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.ActionForward;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		ActionForward forward = new ActionForward();
		boardDAO dao = new boardDAO();

		String idx = req.getParameter("board_idx");
		int boardIdx = Integer.parseInt(idx);

		dao.boardViewCount(boardIdx);

		boardVO vo = dao.boardDetail(boardIdx);
		dao.closeCon();

		req.setAttribute("boardVO", vo);

		forward.setRedirect(false);
		forward.setPath("/board/boardDetail.jsp");

		return forward;
	}

}
