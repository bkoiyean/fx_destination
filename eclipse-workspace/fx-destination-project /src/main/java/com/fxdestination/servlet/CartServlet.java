package com.fxdestination.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fxdestination.constant.Constant;
import com.fxdestination.dao.BranchDAO;
import com.fxdestination.dao.CurrencyDAO;
import com.fxdestination.dao.OrderDAO;
import com.fxdestination.dao.OrderDetailDAO;
import com.fxdestination.dao.UserDAO;
import com.fxdestination.entity.Currency;
import com.fxdestination.entity.Order;
import com.fxdestination.entity.OrderDetail;
import com.fxdestination.entity.User;
import com.fxdestination.model.CurrencyModel;

@WebServlet(urlPatterns = { "/CartServlet", "/cart" })
public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String command = request.getParameter(Constant.PARAM_COMMAND);
		if (command != null && command.equals(Constant.COMMAND_PAY)) {
			addOrder(request, response);
		} else if (command != null && command.equals(Constant.COMMAND_ADD_TO_CART)) {
			addToCart(request, response);
		} else if (command != null && command.equals(Constant.COMMAND_REMOVE)) {
			removeFromCart(request, response);
		} else if (command != null && command.equals(Constant.COMMAND_TRACK_ORDER)) {
			showTrackOrder(request, response);
		} else {
			showCart(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

	private void removeFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = request.getParameter(Constant.PARAM_CODE);

		HttpSession session = request.getSession();

		List<CurrencyModel> selectedCurrencyModelList = (List<CurrencyModel>) session
				.getAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST);

		Iterator itr = selectedCurrencyModelList.iterator();
		while (itr.hasNext()) {
			CurrencyModel currencyModel = (CurrencyModel) itr.next();
			if (currencyModel.getCode().equals(code)) {
				itr.remove();
			}
		}

		double totalPrice = Constant.DEFAULT_DOUBLE_VALUE;

		for (CurrencyModel currency : selectedCurrencyModelList) {
			double singlePrice = currency.getFxAmount() / currency.getRate();
			totalPrice += singlePrice;
		}
		DecimalFormat format = new DecimalFormat("#.##");

		session.setAttribute(Constant.ATTRIBUTE_TOTAL_PRICE, format.format(totalPrice));
		session.setAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST, selectedCurrencyModelList);

		response.sendRedirect(Constant.CARTPATH_SHOW);
	}

	private void addOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		OrderDAO orderDAO = new OrderDAO();
		OrderDetailDAO orderDetailsDAO = new OrderDetailDAO();

		User user = (User) session.getAttribute(Constant.ATTRIBUTE_USER);

		Order order = new Order(user.getId(), false);

		int orderId = orderDAO.add(order);

		List<CurrencyModel> selectedCurrencyModelList = (List<CurrencyModel>) session
				.getAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST);

		OrderDetail orderDetails = new OrderDetail();

		for (CurrencyModel currency : selectedCurrencyModelList) {
			String quantity = Constant.EMPTY_STRING;
			for (int i : currency.getQty()) {
				quantity += i + " ";
			}
			orderDetails = new OrderDetail(orderId, currency.getCode(), quantity, currency.getRate(),
					currency.getFxAmount());

			orderDetailsDAO.add(orderDetails);
		}
		
		UserDAO userDAO = new UserDAO();
		userDAO.clearWallet(user.getId());

		session.removeAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST);

		response.sendRedirect(Constant.MEMBERPATH_SHOW_ORDERID + orderId);
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = request.getParameter(Constant.PARAM_CODE);
		String[] deno = request.getParameterValues(code);
		String Qty = code + Constant.DEFAULT_ADDED_PARAM_QTY;
		String[] quantity = request.getParameterValues(Qty);
		String rate = request.getParameter(Constant.PARAM_RATE);

		List<Currency> currencyList = new ArrayList<Currency>();

		CurrencyDAO currencyDAO = new CurrencyDAO();

		currencyList = currencyDAO.getAll();

		int count = Constant.DEFAULT_INT_VALUE;
		Currency selectedCurrency = new Currency();
		for (Currency i : currencyList) {
			if (i.getCode().equalsIgnoreCase(code)) {
				selectedCurrency = i;
			}
		}
		count = selectedCurrency.getNotes().length;
		int[] notes = selectedCurrency.getNotes();
		int[] qty = new int[count];

		for (int i = 0; i < count; i++) {
			for (int j = 0; j < deno.length; j++) {
				if (notes[i] == Integer.parseInt(deno[j])) {
					qty[i] = Integer.parseInt(quantity[j]);
				}
			}
		}

		CurrencyModel selectedCurrencyModel = new CurrencyModel();

		List<CurrencyModel> selectedCurrencyModelList = new ArrayList<CurrencyModel>();

		selectedCurrencyModel = new CurrencyModel(code, selectedCurrency.getName(), notes, Double.parseDouble(rate),
				qty);

		int fxAmount = Constant.DEFAULT_INT_VALUE;
		for (int i = 0; i < selectedCurrencyModel.getNotes().length; i++) {
			fxAmount += selectedCurrencyModel.getNotes()[i] * selectedCurrencyModel.getQty()[i];
		}
		selectedCurrencyModel.setFxAmount(fxAmount);

		HttpSession session = request.getSession();

		selectedCurrencyModelList = (List<CurrencyModel>) session.getAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST);

		if (selectedCurrencyModelList == null) {
			selectedCurrencyModelList = new ArrayList<CurrencyModel>();
		}
		selectedCurrencyModelList.add(selectedCurrencyModel);

		double totalPrice = Constant.DEFAULT_DOUBLE_VALUE;

		for (CurrencyModel currency : selectedCurrencyModelList) {
			double singlePrice = currency.getFxAmount() / currency.getRate();
			totalPrice += singlePrice;
		}
		DecimalFormat format = new DecimalFormat("#.##");

		session.setAttribute(Constant.ATTRIBUTE_TOTAL_PRICE, format.format(totalPrice));
		session.setAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST, selectedCurrencyModelList);

		response.sendRedirect(Constant.CARTPATH_SHOW);
	}

	private void showCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		List<CurrencyModel> selectedCurrencyModelList = (List<CurrencyModel>) session
				.getAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST);

		double totalPrice = Constant.DEFAULT_DOUBLE_VALUE;
		if (selectedCurrencyModelList != null) {
			for (CurrencyModel currency : selectedCurrencyModelList) {
				double singlePrice = currency.getFxAmount() / currency.getRate();
				totalPrice += singlePrice;
			}
		}

		DecimalFormat format = new DecimalFormat("#.##");

		session.setAttribute(Constant.ATTRIBUTE_TOTAL_PRICE, format.format(totalPrice));
		session.setAttribute(Constant.ATTRIBUTE_SELECTED_CURRENCY_LIST, selectedCurrencyModelList);

		response.sendRedirect(Constant.CARTPATH);

	}
	
	private void showTrackOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int orderIdToTrack = Integer.parseInt(request.getParameter(Constant.PARAM_ORDER_TO_TRACK));

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constant.ATTRIBUTE_USER);
		int userId = user.getId();
		
		OrderDAO orderDAO = new OrderDAO();
		OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
		Order trackingOrder = orderDAO.get(orderIdToTrack);
		
		if (trackingOrder != null && trackingOrder.getUserId() == userId) {
			boolean trackingStatus = orderDAO.isApproved(orderIdToTrack);
			
			List<CurrencyModel> trackingOrderCurrencyModelList = orderDetailDAO.getAllCurrencyOrdered(orderIdToTrack);
			session.setAttribute(Constant.ATTRIBUTE_TRACKING_ORDER, trackingOrder);
			session.setAttribute(Constant.ATTRIBUTE_TRACKING_ORDER_DETAIL, trackingOrderCurrencyModelList);
			session.setAttribute(Constant.ATTRIBUTE_TRACKING_STATUS, trackingStatus);
		} else {
			session.setAttribute(Constant.ATTRIBUTE_TRACKING_ORDER, null);
			session.setAttribute(Constant.ATTRIBUTE_TRACKING_STATUS, false);


		}

		response.sendRedirect(Constant.CARTPATH_SHOW_MODAL + Constant.SHOW_TRACK_ORDER);

	}

}
