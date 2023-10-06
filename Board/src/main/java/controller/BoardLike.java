package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;
import service.BoardService;
import service.BoardServiceImpl;

/**
 * Servlet implementation class BoardLike
 */
@WebServlet("/like")
public class BoardLike extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardLike() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Integer num = Integer.parseInt(request.getParameter("num"));
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("user");
		try {
			BoardService boardService = new BoardServiceImpl();
			String res = boardService.boardLike(member.getId(), num);
			response.getWriter().print(res);

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("error!");
		}

	}

}
