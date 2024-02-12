package com.fxdestination.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fxdestination.constant.Constant;
import com.fxdestination.entity.Currency;
import com.fxdestination.util.MySqlDBConnector;



public class CurrencyDAO {
	private static final Logger logger = LogManager.getLogger(CurrencyDAO.class);

	public List<Currency> getAll() {

		List<Currency> currencyList = new ArrayList<Currency>();

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(Constant.SELECT_ALL_CURRENCY);
			rs = ps.executeQuery();

			while (rs.next()) {
				String code = rs.getString("code");
				String name = rs.getString("name");
				String notesString = rs.getString("notes");

				int count = notesString.length() - notesString.replace(" ", "").length();
				int[] notes = new int[count];
				int item = 0;
				while (notesString.length() != notesString.replace(" ", "").length()) {
					notes[item] = Integer.parseInt(notesString.substring(0, notesString.indexOf(" ")));
					notesString = notesString.substring(1 + notesString.indexOf(" "));
					item = item + 1;
				}
				double rate = rs.getDouble("rate");
				Currency currency = new Currency(code, name, notes, rate);
				currencyList.add(currency);

			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
		return currencyList;
	}

	public Currency get(String currencyCode) {

		Currency currency = new Currency();

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(Constant.SELECT_CURRENCY_BY_CODE);
			ps.setString(1, currencyCode);

			rs = ps.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String notesString = rs.getString("notes");
				int count = notesString.length() - notesString.replace(" ", "").length();
				int[] notes = new int[count];
				int item = 0;
				while (notesString.length() != notesString.replace(" ", "").length()) {
					notes[item] = Integer.parseInt(notesString.substring(0, notesString.indexOf(" ")));
					notesString = notesString.substring(1 + notesString.indexOf(" "));
					item = item + 1;
				}
				double rate = rs.getDouble("rate");
				currency = new Currency(currencyCode, name, notes, rate);
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
		return currency;
	}

	public double getRate(String code) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		double rate = 0;
		try {
			ps = connection.prepareStatement(Constant.SELECT_RATE_BY_CODE);
			ps.setString(1, code);

			rs = ps.executeQuery();

			if (rs.next()) {
				rate = rs.getDouble("rate");
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
		return rate;
	}

	public String getForJS(String code) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String output = "";
		try {
			ps = connection.prepareStatement(Constant.SELECT_CURRENCY_BY_CODE);
			ps.setString(1, code);
			rs = ps.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String notes = rs.getString("notes");
				double rate = rs.getDouble("rate");
				DecimalFormat format = new DecimalFormat("#.################");
				output = "code=" + code + "name=" + name + "notes=" + notes + "rate=" + format.format(rate);
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
		return output;
	}
}
