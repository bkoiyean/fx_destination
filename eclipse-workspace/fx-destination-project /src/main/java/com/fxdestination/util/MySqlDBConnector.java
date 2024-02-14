package com.fxdestination.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fxdestination.dao.BranchDAO;

public class MySqlDBConnector {
	private static final Logger logger = LogManager.getLogger(MySqlDBConnector.class);

	public static Connection makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/fx_project?serverTimezone=Australia/Sydney&useSSL=false", "fx_project_admin",
					"FXProject");
			return conn;
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
		return null;

	}

}
