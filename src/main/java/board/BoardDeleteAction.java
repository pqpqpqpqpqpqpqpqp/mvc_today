package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Action;
import controller.ActionForward;

public class BoardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
        
        //로그인 안하는 경우
        if(userId ==null) {
        	resp.sendRedirect("login.jsp");
        	return null;
        }
        int boardIdx = Integer.parseInt(req.getParameter("board_idx"));
        boardDAO dao = new boardDAO();
        
        //게시글 작성자 확인
        boardVO boardVO = dao.boardDetail(boardIdx);
        if(!userId.equals(boardVO.getInstUser())) {
        	resp.sendRedirect("list.bo");
        	return null;
        }
        //게시글 삭제 (DEL_YN을 'Y'로 업데이트)
        System.out.println("board_idx: "+boardIdx);
        dao.boardDelete(boardIdx);
        
        //게시글 삭제되면 넘어갈 장소
        ActionForward forward = new ActionForward();
        forward.setPath("/board/list.bo");
        forward.setRedirect(true);
        
		return forward;
	}

}
