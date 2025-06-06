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

		// 로그인 아이디 확인, 데이터 수집용
		HttpSession session = req.getSession();
		String userid = (String) session.getAttribute("user_id");

		// 로그인되어 있지 않으면 로그인 페이지로 이동하게끔
		if (userid == null) {
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("login.me");
			return forward;
		}
		
		// 좋아요 누르는 게시글 번호 가져오기
		int boardIdx = Integer.parseInt(req.getParameter("board_idx"));

		// 좋아요 처리
		boardDAO dao = new boardDAO();
		int result = dao.boardLikeCount(boardIdx);

		// 상세 페이지 리다이렉트
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("detail.bo?board_idx=" + boardIdx);

		return forward;
	}

}
