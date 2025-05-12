package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Action;
import controller.ActionForward;
import member.MemberVO;

public class BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		boardDAO dao = new boardDAO();
		ActionForward forward = new ActionForward();
		boardVO boardVO = new boardVO();
		MemberVO memberVO = new MemberVO();
		
		boardVO.setBoardTitle(req.getParameter("boardTitle"));
		boardVO.setBoardContent(req.getParameter("boardContent"));
		boardVO.setBoardCategory(req.getParameter("boardCategory"));
		boardVO.setTravelLocation(req.getParameter("travelLocation"));
		boardVO.setTravelPeriod(req.getParameter("travelPeriod"));
		boardVO.setTravelBudget(Integer.parseInt(req.getParameter("travelBudget")));
		
		HttpSession session = req.getSession();
		
		String user = null;
		if(session.getAttribute("userId") == null) {
			user = memberVO.getUser_id();
		} else {
			user = (String) session.getAttribute("userId"); 
		}
		boardVO.setInstUser(user);
		boardVO.setUpdtUser(user);
		
		dao.boardInsert(boardVO);
		
		
		forward.setPath("list.bo");
		forward.setRedirect(true);
		return forward;
	}

}
