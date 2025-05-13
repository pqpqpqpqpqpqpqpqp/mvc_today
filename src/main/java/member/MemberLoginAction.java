package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Action;
import controller.ActionForward;

public class MemberLoginAction implements Action {
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MemberVO memberVO = new MemberVO();

		MemberDAO dao = new MemberDAO();
		memberVO = dao.login(req.getParameter("userId"));

		ActionForward forward = new ActionForward();
		if (memberVO.getUser_pw().equals(req.getParameter("password"))) {

			HttpSession session = req.getSession();
			session.setAttribute("user_id", memberVO.getUser_id());
			session.setAttribute("user_nickname", memberVO.getUser_nickname());

			forward.setPath(req.getContextPath()+"/board/list.bo");
			forward.setRedirect(true);

		} else {
			forward.setPath("login.jsp");
			forward.setRedirect(true);
		}

		return forward;

	}
}