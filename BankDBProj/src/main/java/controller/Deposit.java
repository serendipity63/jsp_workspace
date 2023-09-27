package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
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
		request.getRequestDispatcher("deposit.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		Integer money = Integer.parseInt(request.getParameter("money"));
		try {
			AccountDAO accdao = new AccountDAO();
			Account acc = accdao.selectAccount(id);

			if (acc != null) {
				acc.deposit(money);
				accdao.updateAccount(acc);
				request.setAttribute("acc", acc);
				request.getRequestDispatcher("accountinfo.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("err", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);

		}

//		request.getRequestDispatcher(getServletInfo())
//deposit 불러오기

	}

}
