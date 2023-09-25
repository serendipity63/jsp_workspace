package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/userList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> userList = new ArrayList<>();
		userList.add(new User("Son", "손흥민", "서울시 강남구", "sonny@kosta.com"));
		userList.add(new User("Lee", "이강인", "서울시 금천구", "lee@kosta.com"));
		userList.add(new User("Kim", "김민재", "서울시 강북구", "kim@kosta.com"));
		userList.add(new User("Ki", "기성용", "서울시 마포구", "ki@kosta.com"));
		userList.add(new User("Ku", "구자철", "서울시 마포구", "ku@kosta.com"));

		request.setAttribute("userList", userList);
		request.getRequestDispatcher("userList.jsp").forward(request, response);

	}

}
