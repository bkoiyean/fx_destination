package com.fxdestination.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fxdestination.constant.Constant;
import com.fxdestination.entity.Currency;
import com.fxdestination.entity.Order;
import com.fxdestination.entity.OrderDetail;
import com.fxdestination.model.CurrencyModel;
import com.fxdestination.util.MySqlDBConnector;

public class OrderDetailDAO {
	private static final Logger logger = LogManager.getLogger(OrderDetailDAO.class);

	public void add(OrderDetail orderDetail) {
		Connection connection = MySqlDBConnector.makeConnection();

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(Constant.INSERT_ORDER_DETAIL);
			ps.setInt(1, orderDetail.getOrderId());
			ps.setString(2, orderDetail.getCurrencyCode());
			ps.setString(3, orderDetail.getQuantity());
			ps.setDouble(4, orderDetail.getRate());
			ps.setDouble(5, orderDetail.getFxAmount());

			ps.execute();

		} catch (Exception e) {
			logger.error("Error: ", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Error: ", e);
			}

		}
	}

	public List<CurrencyModel> getAllCurrencyOrdered(int orderId) {
		Connection connection = MySqlDBConnector.makeConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		List<CurrencyModel> currencyOrderedList = new ArrayList<CurrencyModel>();

		try {
			ps = connection.prepareStatement(Constant.SELECT_ALL_CURRENCY_ORDERED_BY_ORDERID);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();

			while (rs.next()) {
				String currencyCode = rs.getString("currency_code");
				String quantityString = rs.getString("quantity");

				int count = quantityString.length() - quantityString.replace(" ", "").length();
				int[] quantity = new int[count];
				int item = 0;
				while (quantityString.length() != quantityString.replace(" ", "").length()) {
					quantity[item] = Integer.parseInt(quantityString.substring(0, quantityString.indexOf(" ")));
					quantityString = quantityString.substring(1 + quantityString.indexOf(" "));
					item = item + 1;
				}
				
				double rate = rs.getDouble("rate");
				int fxAmount = rs.getInt("fx_amount");

				CurrencyDAO currencyDAO = new CurrencyDAO();

				Currency currency = currencyDAO.get(currencyCode);

				CurrencyModel currencyOrdered = new CurrencyModel(currencyCode, currency.getName(), currency.getNotes(),
						rate, quantity, fxAmount, orderId);

				currencyOrderedList.add(currencyOrdered);
			}

		} catch (Exception e) {
			logger.error("Error: ", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Error: ", e);
			}

		}
		return currencyOrderedList;
	}

}
