package com.atminterface.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atminterface.Dao.ATMInterfaceDAO;

@WebServlet("/ForgotPin")
public class ForgotPin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println( "<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");

		int cardno = Integer.parseInt(request.getParameter("cardno"));
		int accountno = Integer.parseInt(request.getParameter("accountno"));

		boolean isAuthenticated = ATMInterfaceDAO.authenticateaccount(accountno, cardno); // Assuming there is a method
																							// to authenticate the user
																							// based on PIN and card
																							// number
		try {
			if (isAuthenticated) {

				out.println("<div style=\"text-align: center; margin-top: 20px;\">");
				out.println("<form action=\"Reset\" method=\"post\" onsubmit=\"return validateForm();\">");
				out.println("<table>");
				out.println("<tr>");
				out.println("<td><input type='hidden' id='cardno' name='cardno' value='" + cardno + "' /></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td><label for=\"pin\">Enter New PIN:</label></td>");
				out.println( "<td><input type=\"text\" class=\"form-control\" id=\"pin\" name=\"pin\" maxlength=\"4\" required ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td><label for=\"pin1\">Enter New PIN Again:</label></td>");
				out.println(
						"<td><input type=\"text\" class=\"form-control\" id=\"pin1\" name=\"pin1\" maxlength=\"4\" required></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println(
						"<td colspan=\"2\"><button type=\"submit\" class=\"btn btn-primary\">Reset PIN</button></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</div>");
				out.println("<style>");
				out.println("div {");
				out.println("  position: absolute;");
				out.println("  top: 10%;");
				out.println("  left: 50%;");
				out.println("  transform: translate(-50%, -50%);");
				out.println("}");
				out.println("</style>");
				out.println("<script>");
				out.println("function validateForm() {");
				out.println("  var pin = document.getElementById('pin').value;");
				out.println("  var pin1 = document.getElementById('pin1').value;");
				out.println("  if (pin !== pin1) {");
				out.println("    alert('PINs do not match. Please try again.');");
				out.println("    return false;");
				out.println("  }");
				out.println("  return true;");
				out.println("}");
				out.println("</script>");
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
	}
}