package com.atminterface.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atminterface.Dao.ATMInterfaceDAO;
import com.atminterface.bean.ATMInterfaceBean;

@WebServlet("/WithdrowMoney")
public class WithdrowMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println( "<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");

		try {
			int cardno = Integer.parseInt(request.getParameter("cardno"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			int pin = Integer.parseInt(request.getParameter("pin"));
			String TransactionType = request.getParameter("TransactionType");
			boolean isAuthenticated = ATMInterfaceDAO.authenticateUser(pin, cardno); // Assuming there is a method to
																						// authenticate the user based
																						// on PIN and card number

			if (isAuthenticated) {
				if (amount != 0) {
					int balance = ATMInterfaceDAO.CheckBalance(pin, cardno); // Assuming there is a method to get the
																				// account balance

					if (balance >= amount) {
						ATMInterfaceBean bean = new ATMInterfaceBean(pin, cardno, amount, TransactionType);
						int status = ATMInterfaceDAO.Withdraw(bean);

						if (status > 0) {
							out.println("<script>");
							out.println("alert('Money withdrawn successfully!');");
							out.println("window.location.href = 'index.jsp';");
							out.println("</script>");
							out.close();
						} else {
							out.println("<script>");
							out.println("alert('Error: Unable to withdraw money. Please try again later.!');");
							out.println("window.location.href = 'index.jsp';");
							out.println("</script>");
							out.close();
						}
					} else {
						out.println("<script>");
						out.println("alert('Error: Insufficient balance!');");
						out.println("window.location.href = 'index.jsp';");
						out.println("</script>");
						out.close();
					}
				} else {
					out.println("<script>");
					out.println("alert('Error: Amount cannot be zero!');");
					out.println("window.location.href = 'index.jsp';");
					out.println("</script>");
					out.close();
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
			out.close();
		} catch (Exception e) {
			out.println("<h1>Error: " + e.getMessage() + "</h1>");
			out.print("<button class='btn btn-primary'><a href='index.jsp' style='color: white;'>Go Back</a></button>");
			out.close();
		}
	}
}