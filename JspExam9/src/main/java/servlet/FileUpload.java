package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/fileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("fileUploadForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uploadPath = request.getRealPath("upload");
		int size = 10 * 1024 * 1024;
//		10메가
		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8",
				new DefaultFileRenamePolicy());
		// 똑같은 파일이라면 파일명뒤에 1,2,3 붙이게

		String name = multi.getParameter("name"); // 올린사람
		String title = multi.getParameter("title"); // 제목

		String orgFileName1 = multi.getOriginalFileName("file1");
		String orgFileName2 = multi.getOriginalFileName("file2");

		System.out.println(name);
		System.out.println(title);
		System.out.println(orgFileName1);
		System.out.println(orgFileName2);

		request.setAttribute("name", name);
		request.setAttribute("title", title);
		request.setAttribute("file1", orgFileName1);
		request.setAttribute("file2", orgFileName2);
		request.getRequestDispatcher("fileUploadResult.jsp").forward(request, response);

	}

}
