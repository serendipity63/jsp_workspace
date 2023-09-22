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
 * Servlet implementation class MakeAccount
 */
@WebServlet("/makeaccount")
public class MakeAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MakeAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			request.setAttribute("err", "로그인하세요");

			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("makeaccount.jsp");
		// 화면만 보여주는
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 1. request로부터 입력값 가져온다.
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Integer money = Integer.parseInt(request.getParameter("money"));
		String type = request.getParameter("type");
		String grade = request.getParameter("grade");

		// 2. Account 객체 생성
		Account acc = new Account(id, name, money, type, grade);
		// 3. Session 얻어온다(request.getsession)
		HttpSession session = request.getSession();
		// 4. 생성된 Account 객체를 seession에 넣는다.
		session.setAttribute(id, acc);
		// 5. 생성된 Account 객체를 request에 넣는다.
		request.setAttribute("acc", acc);
		// 6. accountinfo.jsp로 포워드한다.
		request.setAttribute("page", "accountinfo");
		RequestDispatcher dispatcher = request.getRequestDispatcher("accountinfo.jsp");
		dispatcher.forward(request, response);

	}
}