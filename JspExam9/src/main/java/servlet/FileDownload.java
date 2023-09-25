package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownload
 */
@WebServlet("/fileDown")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileDownload() {
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
		String fileName = request.getParameter("file");
		ServletContext context = request.getServletContext();
		String filePath = context.getRealPath("upload") + "\\" + fileName;

		FileInputStream in = new FileInputStream(filePath);

		String sMimeType = request.getServletContext().getMimeType(filePath);
		if (sMimeType == null) {
			sMimeType = "application/octet-stream"; // octet-stream:8비트로 된 일련의 데이터를 뜻함. 지정되지 않은 파일 형식을 의미
		}
		response.setContentType(sMimeType);
		String sEncoding = new String(fileName.getBytes("utf-8"), "8859_1"); // 한글파일명 깨짐방지
		response.setHeader("Content-Disposition", "attachment; filename=" + sEncoding);

		OutputStream out = response.getOutputStream();
		byte[] buff = new byte[1024];
		int len = 0;

		while ((len = in.read(buff)) > 0) {
			out.write(buff, 0, len);
		}
		out.close();
		in.close();
	}

}
