package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardListAction;

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
		
		Action action = null;
		ActionForward forward = null;
		
		if (command.equals("/list.bo")) {
			action = new BoardListAction();
		} else if (command.equals("/write.bo")) {
			forward = new ActionForward();
			forward.setPath("boardWrite.jsp");
			forward.setRedirect(true);
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