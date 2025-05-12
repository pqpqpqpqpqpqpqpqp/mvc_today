package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.Action;
import controller.ActionForward;

public class MemberRegisterAction implements Action {
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MemberVO memberVO = new MemberVO();
		memberVO.setUser_id(req.getParameter("userId"));
		memberVO.setUser_pw(req.getParameter("password"));
		memberVO.setUser_nickname(req.getParameter("nickname"));
		

		MemberDAO dao = new MemberDAO();
		dao.regiset(memberVO);

		ActionForward forward = new ActionForward();
		forward.setPath("login.jsp");
		forward.setRedirect(true);

		return forward;

	}
}
