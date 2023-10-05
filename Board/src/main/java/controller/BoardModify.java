package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.Board;
import service.BoardService;
import service.BoardServiceImpl;

/**
 * Servlet implementation class BoardModify
 */
@WebServlet("/boardmodify")
public class BoardModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardModify() {
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

		try {
			BoardService boardService = new BoardServiceImpl();
			Board board = boardService.boardDetail(num);
			request.setAttribute("board", board);
			request.getRequestDispatcher("modify.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 수정 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);

		}
		// 파라미터를 가져와서
	}
	// 폼에 데이터를 보낸다

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 파일 업로드
		String uploadPath = request.getServletContext().getRealPath("upload");
		int size = 10 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8",
				new DefaultFileRenamePolicy());
		// 파일 업로드 끝

		Integer num = Integer.parseInt(multi.getParameter("num"));
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		String fileUrl = multi.getOriginalFileName("file");

		Board board = new Board();
		board.setNum(num);
		board.setSubject(subject);
		board.setContent(content);
		board.setFileurl(fileUrl);

		try {
			BoardService boardService = new BoardServiceImpl();
			boardService.boardModify(board);
			response.sendRedirect("boarddetail?num=" + board.getNum());
			// boardserviceimpl.java에서
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", "게시글 수정 오류");
			request.getRequestDispatcher("error.jsp").forward(request, response);

		}

	} // 수정된걸 보냄

}
