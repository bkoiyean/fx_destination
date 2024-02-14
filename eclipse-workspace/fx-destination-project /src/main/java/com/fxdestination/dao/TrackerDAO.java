package com.fxdestination.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fxdestination.constant.Constant;
import com.fxdestination.entity.Tracker;
import com.fxdestination.entity.User;
import com.fxdestination.util.MySqlDBConnector;

public class TrackerDAO {
	private static final Logger logger = LogManager.getLogger(TrackerDAO.class);

	public List<User> getAll(String code, double rate) {

		List<User> userList = new ArrayList<User>();
		Connection connection = MySqlDBConnector.makeConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_ALL_TRACKING_USERID_BY_CODE_AND_RATE);

			ps.setString(1, code);
			ps.setDouble(2, rate);
			rs = ps.executeQuery();
			UserDAO userDAO = new UserDAO();

			while (rs.next()) {
				int id = rs.getInt("user_id");
				User user = userDAO.get(id);

				userList.add(user);

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

		return userList;

	}

	public void update(String code, double rate, int userId) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(Constant.UPDATE_TRACKER_BY_CODE_AND_RATE);

			ps.setString(1, code);
			ps.setDouble(2, rate);
			ps.setInt(2, userId);

			ps.executeUpdate();

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

	public void insert(int userId, String code, double rate) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(Constant.INSERT_TRACKER);

			ps.setInt(1, userId);
			ps.setString(2, code);
			ps.setDouble(3, rate);
			ps.setString(4, "created");
			ps.executeUpdate();

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

	public List<Tracker> getAll(int userId) {

		List<Tracker> trackerList = new ArrayList<Tracker>();

		Connection connection = MySqlDBConnector.makeConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_ALL_TRACKER_BY_USERID);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				String code = rs.getString("track_code");
				Double rate = rs.getDouble("rate");

				Tracker tracker = new Tracker(userId, code, rate);
				trackerList.add(tracker);

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
		return trackerList;

	}

}
