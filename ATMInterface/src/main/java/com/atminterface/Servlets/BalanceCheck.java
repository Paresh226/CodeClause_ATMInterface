package com.atminterface.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atminterface.bean.*;
import com.atminterface.Dao.*;

@WebServlet("/BalanceCheck")
public class BalanceCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println( "<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");

		int cardno = Integer.parseInt(request.getParameter("cardno"));
		int pin = Integer.parseInt(request.getParameter("pin"));

		boolean isAuthenticated = ATMInterfaceDAO.authenticateUser(pin, cardno); // Assuming there is a method to
																					// authenticate the user based on
																					// PIN and card number

		try {
			if (isAuthenticated) {
				ATMInterfaceBean bean = ATMInterfaceDAO.getBalance(pin, cardno);
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Account Balance</title>");
				out.println( "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
				out.println("<link rel='stylesheet' href='style.css'>");
				out.println("</head>");
				out.println("<body>");
				out.println("<div class='container'>");
				out.println("<h1 class='text-center'>Account Balance</h1>");
				out.println("<table class='table table-bordered table-striped'>");
				if (bean != null) {
					out.print("<tr><td>Your Account No:</td><td>" + bean.getAccountno() + "</td></tr>");
					out.print("<tr><td>Available Amount On Your Card No:</td><td>" + bean.getCardno() + "</td></tr>");
					out.print("<tr><td>Available Amount On Your A/C:</td><td>" + "Rs. " + bean.getAmount()
							+ "</td></tr>");
					out.print(
							"<td colspan='2' style='text-align: center;'><a href='index.jsp' class='btn btn-primary text-white'>Continue</a></td>");
				} else {
					out.println("<script>");
					out.println("alert('Error: Unable to retrieve balance!');");
					out.println("window.location.href = 'index.jsp';");
					out.println("</script>");
				}
			} else {
				out.println("<script>");
				out.println("alert('Error: Invalid PIN or card number!');");
				out.println("window.location.href = 'index.jsp';");
				out.println("</script>");
			}
		} catch (NumberFormatException e) {
			out.println("<script>");
			out.println("alert('Error: Invalid input!');");
			out.println("window.location.href = 'index.jsp';");
			out.println("</script>");
		} catch (Exception e) {
			out.println("<script>");
			out.println("alert('Error: An error occurred. Please try again later.!');");
			out.println("window.location.href = 'index.jsp';");
			out.println("</script>");
		}
		out.println("</table>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}