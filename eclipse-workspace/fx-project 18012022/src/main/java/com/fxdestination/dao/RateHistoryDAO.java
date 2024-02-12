package com.fxdestination.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fxdestination.constant.Constant;
import com.fxdestination.entity.Order;
import com.fxdestination.model.RateHistoryModel;
import com.fxdestination.util.MySqlDBConnector;

public class RateHistoryDAO {
	private static final Logger logger = LogManager.getLogger(RateHistoryDAO.class);

	public List<RateHistoryModel> get(String code) {
		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		RateHistoryModel rateHistory = null;
		List<RateHistoryModel> rateHistoryList = new ArrayList<RateHistoryModel>();

		try {
			ps = connection.prepareStatement(Constant.SELECT_ALL_RATE_HISTORY_BY_CODE);

			ps.setString(1, code);

			rs = ps.executeQuery();

			while (rs.next()) {
				Date date = rs.getTimestamp("update_time");
				double rate = rs.getDouble("currency_rate");

				rateHistory = new RateHistoryModel(date.getTime(), rate);
				rateHistoryList.add(rateHistory);
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
		return rateHistoryList;
	}
}
