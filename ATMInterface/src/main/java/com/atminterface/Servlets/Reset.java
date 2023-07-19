package com.atminterface.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atminterface.Dao.ATMInterfaceDAO;
 
 
 
@WebServlet("/Reset")
public class Reset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException {
		    response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			int cardno = Integer.parseInt(request.getParameter("cardno"));
			int pin = Integer.parseInt(request.getParameter("pin"));
			int pin1 = Integer.parseInt(request.getParameter("pin1"));
           
			int status=ATMInterfaceDAO.ForgetPin(pin,cardno);
			if(status>0)
			{
				out.println("<script>");
				out.println("alert('Password updated successfully!');");
				out.println("window.location.href = 'index.jsp';");
				out.println("</script>");

			}
			else
			{
				out.println("<script>");
				out.println("alert('Error! Pliz Try Again Lather');");
				out.println("window.location.href = 'index.jsp';");
				out.println("</script>");
			}
			 
		}

}
