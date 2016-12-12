package com.app;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 
 * @author hicham-abdedaime
 *  date :12/12/2016  20:00
 */
@WebServlet(urlPatterns = "/connect",loadOnStartup=1)
public class ServletUser extends HttpServlet {


	private static final String COUNT = "count1";
	private static final long serialVersionUID = 1L;
	private static final String PASSWORD = "password";
	private static final String NOM = "nom";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
      
		req.getRequestDispatcher("/index.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session=req.getSession();
		if (!isExiste(session, req.getParameter(NOM))) {
			saveUserToSession(req, resp);

		}
		ServletContext context = session.getServletContext();

		req.setAttribute(COUNT, context.getAttribute(COUNT));
		req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
		

	}

	private void saveUserToSession(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (request.getParameter(NOM) != null) {
			session.setAttribute(NOM, request.getParameter(NOM));
		}
		if (request.getParameter(PASSWORD) != null) {
			session.setAttribute(PASSWORD, request.getParameter(PASSWORD));

		}
		ServletContext context = session.getServletContext();
		if(context.getAttribute(COUNT)!=null){
	        context.setAttribute(COUNT, Integer.parseInt(context.getAttribute(COUNT).toString())+1);

		}
		else {
	         context.setAttribute(COUNT, 1);

		}
	}

	private Boolean isExiste(HttpSession session, String UserName) {

		if (session.getAttribute(NOM) != null
				&& session.getAttribute(NOM).equals(UserName)) {
			return true;
		}

		return false;
	}
}
