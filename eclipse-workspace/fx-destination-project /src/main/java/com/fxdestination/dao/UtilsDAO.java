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
import com.fxdestination.model.FXCodeAndFXAmountModel;
import com.fxdestination.util.MySqlDBConnector;

public class UtilsDAO {
	private static final Logger logger = LogManager.getLogger(UtilsDAO.class);

	public double getTotalAUD(int userId) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_TOTAL_AUD_BY_USERID);

			ps.setInt(1, userId);

			rs = ps.executeQuery();

			if (rs.next()) {
				Double totalAUD = rs.getDouble("result");
				return totalAUD;
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

		return 0;
	}

	public List<FXCodeAndFXAmountModel> get(int userId) {

		List<FXCodeAndFXAmountModel> codeAndAmountList = new ArrayList<FXCodeAndFXAmountModel>();
		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_ALL_FX_CODE_AND_FX_AMOUNT_MODEL);

			ps.setInt(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				String code = rs.getString("currency_code");
				Double totalFX = rs.getDouble("total_fx");

				FXCodeAndFXAmountModel codeAndAmount = new FXCodeAndFXAmountModel(code, totalFX);

				codeAndAmountList.add(codeAndAmount);
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

		return codeAndAmountList;
	}

	public int getQtyOfOrder(int userId) {

		Connection connection = MySqlDBConnector.makeConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_QTY_OF_ORDER_BY_USERID);

			ps.setInt(1, userId);

			rs = ps.executeQuery();

			if (rs.next()) {
				int totalOrder = rs.getInt("result");
				return totalOrder;
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

		return 0;
	}

}
