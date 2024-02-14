package com.fxdestination.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fxdestination.constant.Constant;
import com.fxdestination.entity.Currency;
import com.fxdestination.entity.Order;
import com.fxdestination.util.MySqlDBConnector;

public class OrderDAO {
	private static final Logger logger = LogManager.getLogger(OrderDAO.class);

	public int add(Order order) {
		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, order.getUserId());
			ps.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
			ps.setBoolean(3, order.isApprove());

			ps.execute();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int insertedId = rs.getInt(1);
				return insertedId;
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

	public Order get(int orderId) {
		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		Order order = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_ORDER_BY_ID);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();

			if (rs.next()) {
				int userId = rs.getInt("user_id");
				Date submitDate = rs.getTimestamp("submit_date");
				boolean approve = rs.getBoolean("approve");

				order = new Order(orderId, userId, submitDate, approve);
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
		return order;
	}

	public int getQtyToday(int userId) {
		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_QTY_OF_ORDER_BY_USERID_TODAY);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				int numberOrderToday = rs.getInt("result");
				return numberOrderToday;
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

	public Order getLatest(int userId) {
		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		Order order = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_LATEST_ORDER_BY_USERID);

			ps.setInt(1, userId);

			rs = ps.executeQuery();

			if (rs.next()) {
				int orderId = rs.getInt("id");
				Date submitDate = rs.getTimestamp("submit_date");
				boolean approve = rs.getBoolean("approve");

				order = new Order(orderId, userId, submitDate, approve);
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
		return order;
	}
	
	public boolean isApproved(int orderId) {
		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_STATUS_BY_ID);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();

			if (rs.next()) {
				boolean approve = rs.getBoolean("approve");
				return approve;
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
		return false;
	}

}
