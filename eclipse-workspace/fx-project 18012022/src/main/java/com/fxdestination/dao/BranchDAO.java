package com.fxdestination.dao;

import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fxdestination.constant.Constant;
import com.fxdestination.entity.Branch;
import com.fxdestination.util.MySqlDBConnector;

public class BranchDAO {
	private static final Logger logger = LogManager.getLogger(BranchDAO.class);

	public List<Branch> getAllBranch() {

		List<Branch> branchList = new ArrayList<Branch>();
		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_ALL_BRANCH);
			rs = ps.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				String state = rs.getString("state");
				String city = rs.getString("city");
				String tradingHour = rs.getString("trading_hour");
				String phoneNumber = rs.getString("phone_number");
				String googleLat = rs.getString("google_map_lat");
				String googleLng = rs.getString("google_map_lng");
				String note = rs.getString("note");

				Branch branch = new Branch(name, address, state, city, tradingHour, phoneNumber, googleLat, googleLng,
						note);
				branchList.add(branch);
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

		return branchList;

	}

}
