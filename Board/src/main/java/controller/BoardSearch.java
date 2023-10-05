package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.BoardServiceImpl;

/**
 * Servlet implementation class BoardSearch
 */
@WebServlet("/boardsearch")
public class BoardSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardSearch() {
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
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		String page = request.getParameter("page");
		int curPage = 1;
		if (page != null) {
			curPage = Integer.parseInt(page);

		}
		if (type.equals("all")) {
			response.sendRedirect("boardlist");
			return;

		}

		try {
			BoardService boardService = new BoardServiceImpl();
			Map<String, Object> res = boardService.boardSearch(type, keyword, curPage);
			request.setAttribute("res", res);
			request.getRequestDispatcher("boardlist.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시판 검색 오류");
			request.getRequestDispatcher("error.jsp").forward(request, response);

		}

	}

}
