package com.atminterface.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atminterface.Dao.ATMInterfaceDAO;
import com.atminterface.bean.StatmentBean;

@WebServlet("/Statement")
public class Statement extends HttpServlet {
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
		HttpSession session = request.getSession();																			// PIN and card number

		try {
			if (isAuthenticated) {
				List<StatmentBean> statementBeans = ATMInterfaceDAO.getStatementBeans();
			  
					out.println("<!DOCTYPE html>");
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Account Transaction Statement</title>");
					out.println( "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
					out.println("<link rel='stylesheet' href='style.css'>");
					out.println("</head>");
					out.println("<body>");
					out.println("<div class='container'>");
					out.println("<h1 class='text-center'>Account Transaction Statement</h1>");
					out.println("<table class='table table-bordered table-striped'>");
					out.println("<tr>");
					out.println("<th>Transaction ID</th>");
					out.println("<th>Card No</th>");
					out.println("<th>Transaction Amount</th>");
					out.println("<th>Transaction Type</th>");
					out.println("<th>Transaction Date</th>");
					out.println("</tr>");
					if (statementBeans != null && !statementBeans.isEmpty()) {
						for (StatmentBean bean : statementBeans) {
							out.println("<tr>");
							out.println("<td>" + "#" + bean.getTransactionid() + "</td>");
							out.println("<td>" + bean.getCardno() + "</td>");
							out.println("<td>" + bean.getAmount() + "</td>");
							out.println("<td>" + bean.getTransactionType() + "</td>");
							out.println("<td>" + bean.getTransactionDate() + "</td>");
							out.println("</tr>");
						}
						out.print( "<td colspan='10' style='text-align: center;'><a href='index.jsp' class='btn btn-primary text-white' >Continue</a></td>");
					} else {
						out.println("<script>");
						out.println("alert('Error: Unable to retrieve balance!');");
						out.println("window.location.href = 'index.jsp';");
						out.println("</script>");
						out.print( "<td colspan='10' style='text-align: center;'><a href='index.jsp' class='btn btn-primary text-white'>Go Back</a></td>");
					}
				
			} else {
				out.println("<script>");
				out.println("alert('Error: Invalid PIN or card number!');");
				out.println("window.location.href = 'index.jsp';");
				out.println("</script>");
				out.close();
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
