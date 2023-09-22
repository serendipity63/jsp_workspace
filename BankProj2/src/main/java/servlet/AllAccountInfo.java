package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Account;

/**
 * Servlet implementation class AllAccountInfo
 */
@WebServlet("/allaccountinfo")
public class AllAccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllAccountInfo() {
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
		// 1. Account 목록을 담을 ArrayList 생성
		List<Account> accs = new ArrayList<>();

		// 2. session을 얻어온다.

		// 2. session에 있는 모든 키 목록을 가져온다. (계좌번호)
		Enumeration<String> e = session.getAttributeNames();
		// 키목록 names
		while (e.hasMoreElements()) {
			String name = e.nextElement();
			if (name.equals("member") || name.equals("id")) // 계좌번호가 아닌 키는 제외
				continue;
			Account acc = (Account) session.getAttribute(name); // 계좌번호로 계좌를 가져온다.
			accs.add(acc); // list에 계좌를 담는다
		}
		RequestDispatcher dispatcher = null;
		if (accs.size() == 0) { // 목록에 데이터가 없으면 error페이지로 포워딩한다
			request.setAttribute("err", "개설 된 계좌가 없습니다.");
			dispatcher = request.getRequestDispatcher("error.jsp");

		} else { // 데이터가 있으면 request에 담아 allaccountinfo.jsp로 포워딩
			request.setAttribute("accs", accs);
			dispatcher = request.getRequestDispatcher("allaccountinfo.jsp");

		}
		dispatcher.forward(request, response);

	}

}
