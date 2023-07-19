package com.atminterface.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atminterface.bean.*;
import com.atminterface.Dao.*;
 
@WebServlet("/DepositMoney")
public class DepositMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
	        try {
	            int cardno = Integer.parseInt(request.getParameter("cardno"));
	            int amount = Integer.parseInt(request.getParameter("amount"));
	            int pin = Integer.parseInt(request.getParameter("pin"));
                String TransactionType=request.getParameter("TransactionType");
                
	        	boolean isAuthenticated = ATMInterfaceDAO.authenticateUser(pin, cardno); // Assuming there is a method to authenticate the user based on PIN and card number

	            if (isAuthenticated) {
	            ATMInterfaceBean bean = new ATMInterfaceBean(pin, cardno, amount,TransactionType);
	            int status = ATMInterfaceDAO.Deposit(bean);

	            if (status > 0) {
	           
	                out.println("<script>");
					out.println("alert('Money deposited successfully!');");
					out.println("window.location.href = 'index.jsp';");
					out.println("</script>");
	                out.close();
	            } else {
	            	    out.println("<script>");
						out.println("alert('Error: Invalid credentials!');");
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
	            
	        } catch (Exception e) {
	        	 
	            out.println("<h1>Error: " + e.getMessage() + "</h1>");
	        }
	    }
	}