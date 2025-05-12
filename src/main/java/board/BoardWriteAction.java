package board;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Action;
import controller.ActionForward;

public class BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		boardDAO dao = new boardDAO();
		ActionForward forward = new ActionForward();
		boardVO boardVO = new boardVO();
		
		boardVO.setBoardTitle(req.getParameter("boardTitle"));
		boardVO.setBoardContent(req.getParameter("boardContent"));
		boardVO.setBoardCategory(req.getParameter("boardCategory"));
		boardVO.setTravelLocation(req.getParameter("travelLocation"));
		boardVO.setTravelPeriod(req.getParameter("travelPeriod"));
		boardVO.setTravelBudget(Integer.parseInt(req.getParameter("travelBudget")));
		
		HttpSession session = req.getSession();
		
		boardVO.setInstUser( (String) session.getAttribute("user_id") );
		boardVO.setUpdtUser( (String) session.getAttribute("user_id") );
		
		dao.boardInsert(boardVO);
		
		forward.setPath("/board/list.bo");
		forward.setRedirect(false);
		
		return forward;
	}

}
