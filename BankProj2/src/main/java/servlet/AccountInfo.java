package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Account;

/**
 * Servlet implementation class AccountInfo
 */
@WebServlet("/accountinfo")
public class AccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("accountinfoform.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 1. 계좌번호 가져온다
		String id = request.getParameter("id");
		// 2. 계좌번호로 session에서 계좌를 찾는다.
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute(id);

		RequestDispatcher dispatcher = null;
		// 3. 계좌가 없으면 error페이지로 포워딩한다.
		if (acc == null) {
			request.setAttribute("err", "계좌번호가 틀립니다");
			dispatcher = request.getRequestDispatcher("error.jsp");

		} else {
			// 4. 계좌를 찾으면 request에 계좌를 담다 accountinfo.jsp로 포워딩한다.
			request.setAttribute("acc", acc);
			dispatcher = request.getRequestDispatcher("accountinfo.jsp");

		}
		dispatcher.forward(request, response);

	}

}
