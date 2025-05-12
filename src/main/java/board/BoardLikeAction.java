package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Action;
import controller.ActionForward;

public class BoardLikeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		// 로그인 아이디 확인, 데이터 수집 용
		HttpSession session = req.getSession();
		String userid = (String) session.getAttribute("userid");
		
		if(userid == null) {
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("login.bo");
			return forward;
		}
		
	}

}
