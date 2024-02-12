package com.fxdestination.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fxdestination.constant.Constant;
import com.fxdestination.dao.CurrencyDAO;
import com.fxdestination.entity.Currency;

@WebServlet(urlPatterns = { "/buy", "/ProductServlet" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Currency> currencyList = new ArrayList<Currency>();

		CurrencyDAO currencyDAO = new CurrencyDAO();

		currencyList = currencyDAO.getAll();

		request.setAttribute(Constant.ATTRIBUTE_CURRENCY_LIST, currencyList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.PRODUCTPATH);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter(Constant.PARAM_CODE);

		if (code == null) {
			List<Currency> currencyList = new ArrayList<Currency>();

			CurrencyDAO currencyDAO = new CurrencyDAO();

			currencyList = currencyDAO.getAll();

			request.setAttribute(Constant.ATTRIBUTE_CURRENCY_LIST, currencyList);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.PRODUCTPATH);
			dispatcher.forward(request, response);
		} else {
			String output = Constant.EMPTY_STRING;
			
			CurrencyDAO currencyDAO = new CurrencyDAO();
			output = currencyDAO.getForJS(code);

			response.getWriter().write(output);
		}

	}

}
