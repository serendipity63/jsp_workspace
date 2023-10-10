package servlet;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MapService;
import service.MapServiceImpl;

/**
 * Servlet implementation class MapReg
 */
@WebServlet("/mapreg")
public class MapReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapReg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String address = request.getParameter("address");
		String draw = request.getParameter("draw");
		System.out.println(address);
		System.out.println(draw);
		
		MapService mapService = new MapServiceImpl();
		try {
			//DB 에 저장
			BigInteger num = mapService.regMap(address, draw);
			response.sendRedirect("resmap?num="+num);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
