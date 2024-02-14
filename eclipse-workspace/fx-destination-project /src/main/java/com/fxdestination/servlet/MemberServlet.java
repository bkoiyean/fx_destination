package com.fxdestination.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fxdestination.constant.Constant;
import com.fxdestination.dao.OrderDAO;
import com.fxdestination.dao.OrderDetailDAO;
import com.fxdestination.dao.TrackerDAO;
import com.fxdestination.dao.UserDAO;
import com.fxdestination.dao.UtilsDAO;
import com.fxdestination.entity.Order;
import com.fxdestination.entity.Tracker;
import com.fxdestination.entity.User;
import com.fxdestination.model.CurrencyModel;
import com.fxdestination.model.FXCodeAndFXAmountModel;
import com.fxdestination.util.BCrypt;
import com.fxdestination.util.CheckPassword;
import com.fxdestination.util.GetLevel;
import com.fxdestination.util.SendGmail;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter(Constant.PARAM_COMMAND);

		if (command != null && command.equals(Constant.COMMAND_REGISTER)) {
			insertUser(request, response);
		} else if (command != null && command.equals(Constant.COMMAND_LOGIN)) {
			loginUser(request, response);
		} else if (command != null && command.equals(Constant.COMMAND_LOGOUT)) {
			logoutUser(request, response);
		} else if (command != null && command.equals(Constant.COMMAND_FORGOT)) {
			sendResetPassword(request, response);
		} else if (command != null && command.equals(Constant.COMMAND_UPDATE)) {
			updateUser(request, response);
		} else if (command != null && command.equals(Constant.COMMAND_LOGINOTP)) {
			loginOTP(request, response);
		} else if (command != null && command.equals(Constant.COMMAND_LOGINWITHCODE)) {
			loginWithCode(request, response);
		} else {
			showUser(request, response);

		}

	}

	private void sendResetPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getParameter(Constant.PARAM_URL);

		String activeServlet;
		if (url.contains(Constant.PRODUCTPATH_SIGNAL)) {
			activeServlet = Constant.PRODUCTPATH_SHOW_MODAL;
		} else if (url.contains(Constant.LOCATIONPATH_SIGNAL)) {
			activeServlet = Constant.LOCATIONPATH_SHOW_MODAL;
		} else if (url.contains(Constant.TOOLSPATH_SIGNAL)) {
			activeServlet = Constant.TOOLSPATH_SHOW_MODAL;
		} else if (url.contains(Constant.CARTPATH_SIGNAL)) {
			activeServlet = Constant.CARTPATH_SHOW_MODAL;
		} else {
			activeServlet = Constant.HOMEPATH_SHOW_MODAL;
		}
		;

		String forgotEmail = request.getParameter(Constant.PARAM_FORGOT_EMAIL);

		UserDAO userDAO = new UserDAO();
		SendGmail sendGmail = new SendGmail();

		
		if (userDAO.isRegistered(forgotEmail)) {
			User user = userDAO.get(forgotEmail);
			
			String token = UUID.randomUUID().toString();
			userDAO.update(forgotEmail, token);
						
			sendGmail.sendResetPassword(forgotEmail, token, user);

		}
		
		response.sendRedirect(activeServlet + Constant.SHOW_FORGOT_NOTIFICATION);

	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDAO userDAO = new UserDAO();
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute(Constant.ATTRIBUTE_USER);

		String gender = request.getParameter(Constant.PARAM_GENDER);
		String mobilePhone = request.getParameter(Constant.PARAM_MOBILEPHONE);
		String address = request.getParameter(Constant.PARAM_ADDRESS);
		String state = request.getParameter(Constant.PARAM_STATE);
		String city = request.getParameter(Constant.PARAM_CITY);
		String currentPassword = request.getParameter(Constant.PARAM_CURRENT_PASSWORD);
		String newPassword = request.getParameter(Constant.PARAM_NEW_PASSWORD);
		String hashNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(Constant.GENSALT_DEFAULT_LOG2_ROUNDS));

		String messageUpdate = "";

		if (!currentPassword.isEmpty() && BCrypt.checkpw(currentPassword, userDAO.get(user.getId()).getPassword())
				&& CheckPassword.isValid(newPassword)) {
			userDAO.update(user.getId(), gender, mobilePhone, address, state, city, hashNewPassword);
			messageUpdate = Constant.MESSAGE_UPDATE_WITH_PASSWORD;
		} else {
			userDAO.update(user.getId(), gender, mobilePhone, address, state, city);
			messageUpdate = Constant.MESSAGE_UPDATE_WITHOUT_PASSWORD;

		}

		session.setAttribute(Constant.ATTRIBUTE_MESSAGE_UPDATE, messageUpdate);

		userDAO.update(user.getId(), gender, mobilePhone, address, state, city);

		user = new User(user.getId(), user.getFirstName(), user.getLastName(), gender, user.getEmail(), mobilePhone,
				address, state, city);

		session.setAttribute(Constant.ATTRIBUTE_USER, user);
		response.sendRedirect(Constant.MEMBERPATH_SHOW_UPDATE);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getParameter(Constant.PARAM_URL);
		String activeServlet;
		if (url.contains(Constant.PRODUCTPATH_SIGNAL)) {
			activeServlet = Constant.PRODUCTPATH_SHOW_MODAL;
		} else if (url.contains(Constant.LOCATIONPATH_SIGNAL)) {
			activeServlet = Constant.LOCATIONPATH_SHOW_MODAL;
		} else if (url.contains(Constant.TOOLSPATH_SIGNAL)) {
			activeServlet = Constant.TOOLSPATH_SHOW_MODAL;
		} else if (url.contains(Constant.CARTPATH_SIGNAL)) {
			activeServlet = Constant.CARTPATH_SHOW_MODAL;
		} else {
			activeServlet = Constant.HOMEPATH_SHOW_MODAL;
		}
		;

		String firstName = request.getParameter(Constant.PARAM_FIRST_NAME);
		String lastName = request.getParameter(Constant.PARAM_LAST_NAME);
		String gender = request.getParameter(Constant.PARAM_GENDER);
		String email = request.getParameter(Constant.PARAM_EMAIL);
		String mobilePhone = request.getParameter(Constant.PARAM_MOBILEPHONE);
		String address = request.getParameter(Constant.PARAM_ADDRESS);
		String state = request.getParameter(Constant.PARAM_STATE);
		String city = request.getParameter(Constant.PARAM_CITY);
		String password = request.getParameter(Constant.PARAM_PASSWORD);

		UserDAO userDAO = new UserDAO();

		if (userDAO.isRegistered(email)) {
			String error = Constant.ERROR_REGISTER;
			HttpSession session = request.getSession(false);
			session.setAttribute(Constant.ATTRIBUTE_ERROR_REGISTER, error);
			response.sendRedirect(activeServlet + Constant.SHOW_REGISTER);
		} else {
			User newUser = new User(firstName, lastName, gender, email, mobilePhone, address, state, city, password);

			userDAO.add(newUser);
			HttpSession session = request.getSession(false);
			session.removeAttribute(Constant.ATTRIBUTE_ERROR_REGISTER);
			session.removeAttribute(Constant.ATTRIBUTE_ERROR_LOGIN);
			response.sendRedirect(activeServlet + Constant.SHOW_LOGIN);
		}

	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getParameter(Constant.PARAM_URL);
		String filterServlet;
		if (url.contains(Constant.FILTER_PRODUCT_SIGNAL)) {
			filterServlet = Constant.PRODUCT_CONTROLLER;
		} else if (url.contains(Constant.FILTER_TOOLS_SIGNAL)) {
			filterServlet = Constant.TOOLS_CONTROLLER;
		} else {
			filterServlet = Constant.MEMBERPATH;
		}
		;

		String activeServlet;
		if (url.contains(Constant.PRODUCTPATH_SIGNAL)) {
			activeServlet = Constant.PRODUCTPATH_SHOW_MODAL;
		} else if (url.contains(Constant.LOCATIONPATH_SIGNAL)) {
			activeServlet = Constant.LOCATIONPATH_SHOW_MODAL;
		} else if (url.contains(Constant.TOOLSPATH_SIGNAL)) {
			activeServlet = Constant.TOOLSPATH_SHOW_MODAL;
		} else if (url.contains(Constant.CARTPATH_SIGNAL)) {
			activeServlet = Constant.CARTPATH_SHOW_MODAL;
		} else {
			activeServlet = Constant.HOMEPATH_SHOW_MODAL;
		}
		;

		String loginEmail = request.getParameter(Constant.PARAM_LOGIN_EMAIL);
		String loginPassword = request.getParameter(Constant.PARAM_LOGIN_PASSWORD);

		UserDAO userDAO = new UserDAO();

		User user = userDAO.get(loginEmail, loginPassword);

		if (user != null) {
			HttpSession session = request.getSession(false);
			session.setAttribute(Constant.ATTRIBUTE_USER, user);
			session.removeAttribute(Constant.ATTRIBUTE_ERROR_LOGIN);
			session.removeAttribute(Constant.ATTRIBUTE_ERROR_REGISTER);

			OrderDAO orderDAO = new OrderDAO();
			OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

			int userId = user.getId();

			int numberOrderToday = orderDAO.getQtyToday(userId);
			request.setAttribute(Constant.ATTRIBUTE_QTY_ORDER_TODAY, numberOrderToday);

			TrackerDAO trackerDAO = new TrackerDAO();
			List<Tracker> trackerList = trackerDAO.getAll(userId);
			request.setAttribute(Constant.ATTRIBUTE_TRACKER_LIST, trackerList);

			UtilsDAO utilsDAO = new UtilsDAO();
			double totalAUD = utilsDAO.getTotalAUD(userId);
			DecimalFormat format = new DecimalFormat("#.##");
			request.setAttribute(Constant.ATTRIBUTE_TOTAL_AUD, format.format(totalAUD));

			int totalOrder = utilsDAO.getQtyOfOrder(userId);
			request.setAttribute(Constant.ATTRIBUTE_TOTAL_ORDER, totalOrder);

			List<FXCodeAndFXAmountModel> codeAndAmountList = utilsDAO.get(userId);
			request.setAttribute(Constant.ATTRIBUTE_CODE_AND_AMOUNT_LIST, codeAndAmountList);

			List<CurrencyModel> selectedCurrencyModelList = (List<CurrencyModel>) session
					.getAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST);
			request.setAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST, selectedCurrencyModelList);

			Order latestOrder = orderDAO.getLatest(userId);
			if (latestOrder != null) {
				int orderId = latestOrder.getId();

				List<CurrencyModel> latestOrderCurrencyModelList = orderDetailDAO.getAllCurrencyOrdered(orderId);
				request.setAttribute(Constant.ATTRIBUTE_LATEST_ORDER, latestOrder);
				request.setAttribute(Constant.ATTRIBUTE_LATEST_ORDER_DETAIL, latestOrderCurrencyModelList);
			}

			int levelMember = GetLevel.getLevel(totalAUD);
			session.setAttribute(Constant.ATTRIBUTE_LEVEL_MEMBER, levelMember);
			
			double wallet = userDAO.getWallet(userId);
			session.setAttribute(Constant.ATTRIBUTE_WALLET, format.format(wallet));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(filterServlet);
			dispatcher.forward(request, response);

		} else {
			String error = Constant.ERROR_LOGIN;
			HttpSession session = request.getSession(false);
			session.setAttribute(Constant.ATTRIBUTE_ERROR_LOGIN, error);
			response.sendRedirect(activeServlet + Constant.SHOW_LOGIN);
		}

	}

	private void logoutUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.removeAttribute(Constant.ATTRIBUTE_USER);
		session.removeAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST);
		session.removeAttribute(Constant.ATTRIBUTE_NOTIFICATION_CONTACT);
		session.removeAttribute(Constant.ATTRIBUTE_MESSAGE_UPDATE);
		session.removeAttribute(Constant.ATTRIBUTE_LEVEL_MEMBER);
		session.removeAttribute(Constant.ATTRIBUTE_WALLET);
		session.removeAttribute(Constant.ATTRIBUTE_TRACKING_ORDER);
		session.removeAttribute(Constant.ATTRIBUTE_TRACKING_ORDER_DETAIL);
		session.removeAttribute(Constant.ATTRIBUTE_TRACKING_STATUS);

		response.sendRedirect(Constant.HOMEPATH_CONTROLLER);
	}

	private void showUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute(Constant.ATTRIBUTE_USER);

		int userId = user.getId();

		OrderDAO orderDAO = new OrderDAO();
		OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

		int numberOrderToday = orderDAO.getQtyToday(userId);
		request.setAttribute(Constant.ATTRIBUTE_QTY_ORDER_TODAY, numberOrderToday);

		TrackerDAO trackerDAO = new TrackerDAO();
		List<Tracker> trackerList = trackerDAO.getAll(userId);
		request.setAttribute(Constant.ATTRIBUTE_TRACKER_LIST, trackerList);

		UtilsDAO utilsDAO = new UtilsDAO();
		double totalAUD = utilsDAO.getTotalAUD(userId);
		DecimalFormat format = new DecimalFormat("#.##");
		request.setAttribute(Constant.ATTRIBUTE_TOTAL_AUD, format.format(totalAUD));

		int totalOrder = utilsDAO.getQtyOfOrder(userId);
		request.setAttribute(Constant.ATTRIBUTE_TOTAL_ORDER, totalOrder);

		List<FXCodeAndFXAmountModel> codeAndAmountList = utilsDAO.get(userId);
		request.setAttribute(Constant.ATTRIBUTE_CODE_AND_AMOUNT_LIST, codeAndAmountList);

		List<CurrencyModel> selectedCurrencyModelList = (List<CurrencyModel>) session
				.getAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST);
		request.setAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST, selectedCurrencyModelList);

		// Get the latest order and order details:
		Order latestOrder = orderDAO.getLatest(userId);
		if (latestOrder != null) {
			int orderId = latestOrder.getId();

			List<CurrencyModel> latestOrderCurrencyModelList = orderDetailDAO.getAllCurrencyOrdered(orderId);
			request.setAttribute(Constant.ATTRIBUTE_LATEST_ORDER, latestOrder);
			request.setAttribute(Constant.ATTRIBUTE_LATEST_ORDER_DETAIL, latestOrderCurrencyModelList);
		}
		
		int levelMember = GetLevel.getLevel(totalAUD);
		session.setAttribute(Constant.ATTRIBUTE_LEVEL_MEMBER, levelMember);
		
		UserDAO userDAO = new UserDAO();

		double wallet = userDAO.getWallet(userId);
		session.setAttribute(Constant.ATTRIBUTE_WALLET, format.format(wallet));

		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.MEMBERPATH);
		dispatcher.forward(request, response);

	}
	private void loginOTP(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getParameter(Constant.PARAM_URL);

		String activeServlet;
		if (url.contains(Constant.PRODUCTPATH_SIGNAL)) {
			activeServlet = Constant.PRODUCTPATH_SHOW_MODAL;
		} else if (url.contains(Constant.LOCATIONPATH_SIGNAL)) {
			activeServlet = Constant.LOCATIONPATH_SHOW_MODAL;
		} else if (url.contains(Constant.TOOLSPATH_SIGNAL)) {
			activeServlet = Constant.TOOLSPATH_SHOW_MODAL;
		} else if (url.contains(Constant.CARTPATH_SIGNAL)) {
			activeServlet = Constant.CARTPATH_SHOW_MODAL;
		} else {
			activeServlet = Constant.HOMEPATH_SHOW_MODAL;
		}
		;
		
		String loginOTPEmail = request.getParameter(Constant.PARAM_LOGINOTP_EMAIL);
		
		UserDAO userDAO = new UserDAO();

		if (!userDAO.isRegistered(loginOTPEmail)) {
			String error = Constant.ERROR_LOGIN;
			HttpSession session = request.getSession(false);
			session.setAttribute(Constant.ATTRIBUTE_ERROR_LOGIN, error);
			response.sendRedirect(activeServlet + Constant.SHOW_LOGIN);
		} else {
			HttpSession session = request.getSession(false);
			session.setAttribute(Constant.ATTRIBUTE_LOGIN_OTP_EMAIL, loginOTPEmail);
			
			Random rnd = new Random();
		    int number = rnd.nextInt(999999);
		    String OTP = String.format("%06d", number);
		    
			User user = userDAO.get(loginOTPEmail);
			SendGmail.sendOTP(loginOTPEmail, OTP, user);
			userDAO.updateOTP(OTP, loginOTPEmail);
			
			response.sendRedirect(activeServlet + Constant.SHOW_LOGINWITHCODE);
		}

	}
	
	private void loginWithCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getParameter(Constant.PARAM_URL);
		String filterServlet;
		if (url.contains(Constant.FILTER_PRODUCT_SIGNAL)) {
			filterServlet = Constant.PRODUCT_CONTROLLER;
		} else if (url.contains(Constant.FILTER_TOOLS_SIGNAL)) {
			filterServlet = Constant.TOOLS_CONTROLLER;
		} else {
			filterServlet = Constant.MEMBERPATH;
		}
		;

		String activeServlet;
		if (url.contains(Constant.PRODUCTPATH_SIGNAL)) {
			activeServlet = Constant.PRODUCTPATH_SHOW_MODAL;
		} else if (url.contains(Constant.LOCATIONPATH_SIGNAL)) {
			activeServlet = Constant.LOCATIONPATH_SHOW_MODAL;
		} else if (url.contains(Constant.TOOLSPATH_SIGNAL)) {
			activeServlet = Constant.TOOLSPATH_SHOW_MODAL;
		} else if (url.contains(Constant.CARTPATH_SIGNAL)) {
			activeServlet = Constant.CARTPATH_SHOW_MODAL;
		} else {
			activeServlet = Constant.HOMEPATH_SHOW_MODAL;
		}
		;

		String loginOTPEmail = request.getParameter(Constant.PARAM_LOGIN_OTP_EMAIL);
		String first = request.getParameter(Constant.PARAM_FIRST_CODE);
		String second = request.getParameter(Constant.PARAM_SECOND_CODE);
		String third = request.getParameter(Constant.PARAM_THIRD_CODE);
		String fourth = request.getParameter(Constant.PARAM_FOURTH_CODE);
		String fifth = request.getParameter(Constant.PARAM_FIFTH_CODE);
		String sixth = request.getParameter(Constant.PARAM_SIXTH_CODE);

		UserDAO userDAO = new UserDAO();

		String accessCode = first + second + third + fourth + fifth + sixth;
		String validOTP = userDAO.getOTP(loginOTPEmail);
		
		if (validOTP!=null && validOTP.equals(accessCode)) {
			
			HttpSession session = request.getSession(false);
			User user = userDAO.get(loginOTPEmail);
			session.setAttribute(Constant.ATTRIBUTE_USER, user);
			session.removeAttribute(Constant.ATTRIBUTE_ERROR_LOGIN);
			session.removeAttribute(Constant.ATTRIBUTE_ERROR_REGISTER);
			session.removeAttribute(Constant.ATTRIBUTE_LOGIN_OTP_EMAIL);

			OrderDAO orderDAO = new OrderDAO();
			OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

			int userId = user.getId();

			int numberOrderToday = orderDAO.getQtyToday(userId);
			request.setAttribute(Constant.ATTRIBUTE_QTY_ORDER_TODAY, numberOrderToday);

			TrackerDAO trackerDAO = new TrackerDAO();
			List<Tracker> trackerList = trackerDAO.getAll(userId);
			request.setAttribute(Constant.ATTRIBUTE_TRACKER_LIST, trackerList);

			UtilsDAO utilsDAO = new UtilsDAO();
			double totalAUD = utilsDAO.getTotalAUD(userId);
			DecimalFormat format = new DecimalFormat("#.##");
			request.setAttribute(Constant.ATTRIBUTE_TOTAL_AUD, format.format(totalAUD));

			int totalOrder = utilsDAO.getQtyOfOrder(userId);
			request.setAttribute(Constant.ATTRIBUTE_TOTAL_ORDER, totalOrder);

			List<FXCodeAndFXAmountModel> codeAndAmountList = utilsDAO.get(userId);
			request.setAttribute(Constant.ATTRIBUTE_CODE_AND_AMOUNT_LIST, codeAndAmountList);

			List<CurrencyModel> selectedCurrencyModelList = (List<CurrencyModel>) session
					.getAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST);
			request.setAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST, selectedCurrencyModelList);

			Order latestOrder = orderDAO.getLatest(userId);
			if (latestOrder != null) {
				int orderId = latestOrder.getId();

				List<CurrencyModel> latestOrderCurrencyModelList = orderDetailDAO.getAllCurrencyOrdered(orderId);
				request.setAttribute(Constant.ATTRIBUTE_LATEST_ORDER, latestOrder);
				request.setAttribute(Constant.ATTRIBUTE_LATEST_ORDER_DETAIL, latestOrderCurrencyModelList);
			}

			int levelMember = GetLevel.getLevel(totalAUD);
			session.setAttribute(Constant.ATTRIBUTE_LEVEL_MEMBER, levelMember);
			
			double wallet = userDAO.getWallet(userId);
			session.setAttribute(Constant.ATTRIBUTE_WALLET, format.format(wallet));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(filterServlet);
			dispatcher.forward(request, response);

		} else {
			String error = Constant.ERROR_LOGIN;
			HttpSession session = request.getSession(false);
			session.setAttribute(Constant.ATTRIBUTE_ERROR_LOGIN, error);
			response.sendRedirect(activeServlet + Constant.SHOW_LOGIN);
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
