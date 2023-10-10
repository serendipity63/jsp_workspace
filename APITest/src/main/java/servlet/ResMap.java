package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MapService;
import service.MapServiceImpl;

/**
 * Servlet implementation class ResMap
 */
@WebServlet("/resmap")
public class ResMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResMap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Integer num = Integer.parseInt(request.getParameter("num"));
		MapService mapService = new MapServiceImpl();
		try {
			Map<String, Object> res = mapService.viewMap(num);
			System.out.println(res);
			request.setAttribute("res", res);
			request.getRequestDispatcher("ResMap.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
