package com.fxdestination.servlet;

import java.io.IOException;
import java.sql.SQLException;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fxdestination.constant.Constant;
import com.fxdestination.dao.BranchDAO;
import com.fxdestination.dao.CurrencyDAO;
import com.fxdestination.dao.RateHistoryDAO;
import com.fxdestination.dao.TrackerDAO;
import com.fxdestination.entity.Currency;
import com.fxdestination.entity.User;
import com.fxdestination.model.RateHistoryModel;
import com.fxdestination.util.GetRate;

@WebServlet("/ToolsServlet")
public class ToolsServlet extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(ToolsServlet.class);

	private static final long serialVersionUID = 1L;

	public ToolsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = request.getParameter(Constant.PARAM_FIRST_TRACK);
		String rateString = request.getParameter(Constant.PARAM_TARGET_RATE);
		String message = Constant.EMPTY_STRING;
		String command = request.getParameter(Constant.PARAM_COMMAND);

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute(Constant.ATTRIBUTE_USER);

		int userId = user.getId();

		if (code != null && code != Constant.EMPTY_STRING && rateString != null && command == null) {

			double rate = Double.parseDouble(rateString);

			TrackerDAO trackerDAO = new TrackerDAO();
			trackerDAO.insert(userId, code, rate);

			message = Constant.MESSAGE_TRACK_CREATED;

			request.setAttribute(Constant.ATTRIBUTE_MESSAGE, message);

			List<Currency> currencyList = new ArrayList<Currency>();

			CurrencyDAO currencyDAO = new CurrencyDAO();

			currencyList = currencyDAO.getAll();

			request.setAttribute(Constant.ATTRIBUTE_CURRENCY_LIST, currencyList);

			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.TOOLSPATH);
			dispatcher.forward(request, response);

		} else if (rateString != null && command == null) {
			message = Constant.ERROR_TRACK_INPUT;

			request.setAttribute(Constant.ATTRIBUTE_MESSAGE, message);

			List<Currency> currencyList = new ArrayList<Currency>();

			CurrencyDAO currencyDAO = new CurrencyDAO();

			currencyList = currencyDAO.getAll();

			request.setAttribute(Constant.ATTRIBUTE_CURRENCY_LIST, currencyList);

			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.TOOLSPATH);
			dispatcher.forward(request, response);

		} else {
			List<Currency> currencyList = new ArrayList<Currency>();

			CurrencyDAO currencyDAO = new CurrencyDAO();

			currencyList = currencyDAO.getAll();

			request.setAttribute(Constant.ATTRIBUTE_CURRENCY_LIST, currencyList);

			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.TOOLSPATH);
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter(Constant.PARAM_FIRST_GRAPH);
		String codeXE = request.getParameter(Constant.PARAM_CODE_XE);

		if (code == null && codeXE == null) {
			List<Currency> currencyList = new ArrayList<Currency>();

			CurrencyDAO currencyDAO = new CurrencyDAO();

			currencyList = currencyDAO.getAll();

			request.setAttribute(Constant.ATTRIBUTE_CURRENCY_LIST, currencyList);

			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.TOOLSPATH);
			dispatcher.forward(request, response);

		} else if (code != null) {
			String output = Constant.EMPTY_STRING;

			if (code != null & !code.isEmpty()) {

				RateHistoryDAO ratesHistoryDAO = new RateHistoryDAO();
				List<RateHistoryModel> rateList = ratesHistoryDAO.get(code);
				output = rateList.toString();
			}

			response.getWriter().write(output);
			
		} else if (codeXE != null) {
			
			String output = Constant.EMPTY_STRING;
			try {
				String rateXE = GetRate.getXE(codeXE);
				String rateTravelex = GetRate.getTravelex(codeXE);
				output = "rateXE=" + rateXE + "&rateTravelex=" + rateTravelex;
			} catch (IOException e) {
				logger.error("Error: ", e);
			} catch (SQLException e) {
				logger.error("Error: ", e);
			}

			response.getWriter().write(output);
		}

	}

}
