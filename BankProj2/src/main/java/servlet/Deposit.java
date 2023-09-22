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
 * Servlet implementation class Deposit
 */
@WebServlet("/deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Deposit() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("deposit.jsp");
		// deposit화면으로 위임
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 1. 계좌번호와 입금액을 가져온다.
		String id = request.getParameter("id");
		Integer money = Integer.parseInt(request.getParameter("money"));

		// 2. session에서 같은 계좌번호의 계좌를 가져온다.
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute(id); // account로 다시 캐스팅한다

		RequestDispatcher dispatcher = null;
		// 3. 계좌가 없으면 error페이지로 위임한다
		if (acc == null) {
			request.setAttribute("err", "계좌번호가 틀립니다");
			dispatcher = request.getRequestDispatcher("error.jsp");

		} else {
//		4. 계좌를 찾으면 찾은 계좌에 돈을 입금한다
			acc.deposit(money);
			request.setAttribute("acc", acc);
			// 5. request에 계좌를 넣는다
			dispatcher = request.getRequestDispatcher("accountinfo.jsp");

		}

		// 6. accountinfo.jsp 페이지로 위임한다
		dispatcher.forward(request, response);

	}

}
