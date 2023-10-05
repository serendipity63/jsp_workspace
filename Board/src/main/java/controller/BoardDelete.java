package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.BoardServiceImpl;

/**
 * Servlet implementation class BoardDelete
 */
@WebServlet("/boarddelete")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardDelete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Integer num = Integer.parseInt(request.getParameter("num"));
		Integer page = Integer.parseInt(request.getParameter("page"));
		try {
			BoardService boardService = new BoardServiceImpl();
			boardService.boardRemove(num);
			response.sendRedirect("boardlist?page=" + page);

//			Map<String, Object> res = boardService.boardListByPage(page);
//			request.setAttribute("res", res);
//			request.getRequestDispatcher("boardlist.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 삭제 오류");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
