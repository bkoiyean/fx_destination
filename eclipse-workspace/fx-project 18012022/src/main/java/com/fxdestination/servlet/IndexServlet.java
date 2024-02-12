package com.fxdestination.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fxdestination.constant.Constant;
import com.fxdestination.dao.UserDAO;
import com.fxdestination.entity.User;
import com.fxdestination.util.SendGmail;

@WebServlet(urlPatterns = { "/IndexServlet" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IndexServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter(Constant.PARAM_NAME_CONTACT);
		String token = request.getParameter(Constant.PARAM_TOKEN);

		
		if (name==null && token==null) {
			response.sendRedirect(Constant.HOMEPATH);

		}
		else if (name!=null){
			SendGmail sendGmail = new SendGmail();
			
			String subject = Constant.DEFAULT_SUBJECT_CONTACT + name;
			subject += " " + request.getParameter(Constant.PARAM_EMAIL_CONTACT);
			
			String message = request.getParameter(Constant.PARAM_MESSAGE_CONTACT);
			
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute(Constant.ATTRIBUTE_USER);
			
			if (user!=null) {
				subject += " under session of " + user.getEmail();
			}
			
			sendGmail.sendEmailFromContact(Constant.DEFAULT_SEND_TO_CONTACT, subject, message);
			
			String notification = Constant.SUCCESSFUL_SUBMIT_CONTACT_MESSAGE;
			
			session.setAttribute(Constant.ATTRIBUTE_NOTIFICATION_CONTACT, notification);
			
			response.sendRedirect(Constant.CONTACTPATH);

		}
		
		else if (token!=null){
			
			UserDAO userDAO = new UserDAO();
			
			String email = userDAO.getEmail(token);
			
			if (email==null) {
				response.sendRedirect(Constant.HOMEPATH_SHOW_MODAL + Constant.SHOW_INVALID_TOKEN);
			}
			else {
				userDAO.update(token);
				
				HttpSession session = request.getSession(true);
				session.setAttribute(Constant.ATTRIBUTE_TOKEN, token);
				
				response.sendRedirect(Constant.HOMEPATH_SHOW_MODAL + Constant.SHOW_VALID_TOKEN);
				
			}
			

		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String token = request.getParameter(Constant.PARAM_TOKEN);
		String command = request.getParameter(Constant.PARAM_COMMAND);
		String password = request.getParameter(Constant.PARAM_PASSWORD);
		
		if (command==null) {
			doGet(request, response);
		}
		else {
			UserDAO userDAO = new UserDAO();
			
			String email = userDAO.getEmail(token);
			
			if (email==null) {
				response.sendRedirect(Constant.HOMEPATH_SHOW_MODAL + Constant.SHOW_INVALID_TOKEN);
			} else if (command!=null && command.equals(Constant.COMMAND_RESET) && password!=null) {
				userDAO.update(email, token, password);
			}
			
			HttpSession session = request.getSession(false);
			session.removeAttribute(Constant.ATTRIBUTE_TOKEN);
			
			response.sendRedirect(Constant.HOMEPATH_SHOW_MODAL + Constant.SHOW_LOGIN);
		}
		
	}

}
