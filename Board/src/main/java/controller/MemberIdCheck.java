package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import service.MemberServiceImpl;

/**
 * Servlet implementation class IdCheck
 */
@WebServlet("/idcheck")
public class MemberIdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberIdCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 중복검사
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");

		try {
			MemberService memberService = new MemberServiceImpl();
			response.getWriter().print(memberService.idCheck(id));
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("error!");

		}

	}

}
