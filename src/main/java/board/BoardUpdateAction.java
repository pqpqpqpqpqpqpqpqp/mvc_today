package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import controller.Action;
import controller.ActionForward;

public class BoardUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionForward forward = new ActionForward();
		boardDAO dao= new boardDAO();
		String idx = req.getParameter("board_idx");
		 
		int boardIdx = Integer.parseInt(idx);
		boardVO boardVO = new boardVO();
		
		req.setAttribute("boardVO", boardVO);
		
		forward.setRedirect(false);
		forward.setPath("/board/list.bo");
		
		
		
		
		return forward;
	}

}
