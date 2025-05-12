package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDeleteAction;
import board.BoardDetailAction;
import board.BoardLikeAction;
import board.BoardListAction;
import board.BoardUpdateAction;
import board.BoardUpdateResultAction;
import board.BoardWriteAction;

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String command = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		if (command.equals("/board/list.bo")) {
			action = new BoardListAction();	
		} else if (command.equals("/member/write.bo") || command.equals("/board/write.bo")) {
			forward = new ActionForward();
			forward.setPath("/250512_Today/board/boardWrite.jsp");
			forward.setRedirect(true);
		} else if (command.equals("/board/writeAction.bo")) {
			action = new BoardWriteAction();
		} else if (command.equals("/board/detail.bo")) {
			action = new BoardDetailAction();
		} else if (command.equals("/board/update.bo")) {
			action = new BoardUpdateAction();
		} else if (command.equals("/board/updateAction.bo")) {
			action = new BoardUpdateResultAction();
		} else if (command.equals("/board/deleteAction.bo")) {
			action = new BoardDeleteAction();
		} else if (command.equals("/board/like.bo")) {
			action = new BoardLikeAction();
		}
		
		try {
			if (action != null) {
				forward = action.execute(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (forward.isRedirect()) {
			resp.sendRedirect(forward.getPath());
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
			dispatcher.forward(req, resp);
		}
		
	}
}