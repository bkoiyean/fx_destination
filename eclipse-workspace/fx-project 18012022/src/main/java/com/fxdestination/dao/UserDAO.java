package com.fxdestination.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fxdestination.constant.Constant;
import com.fxdestination.entity.User;
import com.fxdestination.util.BCrypt;
import com.fxdestination.util.MySqlDBConnector;

public class UserDAO {
	private static final Logger logger = LogManager.getLogger(UserDAO.class);

	public List<User> getAll() {

		List<User> userList = new ArrayList<User>();
		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_ALL_USER);

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String mobilePhone = rs.getString("mobile_phone");
				String address = rs.getString("address");
				String state = rs.getString("state");
				String city = rs.getString("city");
				String password = rs.getString("password");

				User user = new User(id, firstName, lastName, gender, email, mobilePhone, address, state, city,
						password);

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

	public void add(User user) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(Constant.INSERT_USER);

			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getGender());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getMobilePhone());
			ps.setString(6, user.getAddress());
			ps.setString(7, user.getState());
			ps.setString(8, user.getCity());
			ps.setString(9, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(Constant.GENSALT_DEFAULT_LOG2_ROUNDS)));

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

	public User get(String loginEmail, String loginPassword) {

		User user = new User();
		Connection connection = MySqlDBConnector.makeConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_USER_BY_EMAIL);

			ps.setString(1, loginEmail);			

			rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String mobilePhone = rs.getString("mobile_phone");
				String address = rs.getString("address");
				String state = rs.getString("state");
				String city = rs.getString("city");
				String hassPassword = rs.getString("password");
				
				if (BCrypt.checkpw(loginPassword, hassPassword)) {
					user = new User(id, firstName, lastName, gender, email, mobilePhone, address, state, city);
					return user;
				}

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

		return null;
	}

	public boolean isRegistered(String email) {

		Connection connection = MySqlDBConnector.makeConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_USER_BY_EMAIL);

			ps.setString(1, email);

			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
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

	public User get(int id) {

		User user = new User();
		Connection connection = MySqlDBConnector.makeConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_USER_BY_ID);

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String mobilePhone = rs.getString("mobile_phone");
				String address = rs.getString("address");
				String state = rs.getString("state");
				String city = rs.getString("city");
				String password = rs.getString("password");

				user = new User(id, firstName, lastName, gender, email, mobilePhone, address, state, city, password);
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

		return user;
	}
	
	public User get(String email) {

		User user = new User();
		Connection connection = MySqlDBConnector.makeConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_USER_BY_EMAIL);

			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String gender = rs.getString("gender");
				String mobilePhone = rs.getString("mobile_phone");
				String address = rs.getString("address");
				String state = rs.getString("state");
				String city = rs.getString("city");

				user = new User(id, firstName, lastName, gender, email, mobilePhone, address, state, city);
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

		return user;
	}
	
	public String getEmail(String token) {

		Connection connection = MySqlDBConnector.makeConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_EMAIL_BY_VALID_TOKEN);

			ps.setString(1, token);
			rs = ps.executeQuery();

			if (rs.next()) {
				String email = rs.getString("email");
				return email;
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

		return null;
	}

	public void update(int userId, String gender, String mobilePhone, String address, String state, String city, String password) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(Constant.UPDATE_USER_PASSWORD);

			ps.setString(1, gender);
			ps.setString(2, mobilePhone);
			ps.setString(3, address);
			ps.setString(4, state);
			ps.setString(5, city);
			ps.setInt(6, userId);
			ps.setString(7, password);

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
	
	public void update(int userId, String gender, String mobilePhone, String address, String state, String city) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(Constant.UPDATE_USER);

			ps.setString(1, gender);
			ps.setString(2, mobilePhone);
			ps.setString(3, address);
			ps.setString(4, state);
			ps.setString(5, city);
			ps.setInt(6, userId);

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
	
	public void update(String email, String token) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(Constant.UPDATE_USER_TOKEN_FORGOT);

			ps.setString(1, token);
			
			Date tokenValidDate = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(tokenValidDate); 
			c.add(Calendar.DATE, 1);
			tokenValidDate = c.getTime();
			
			ps.setTimestamp(2, new java.sql.Timestamp(tokenValidDate.getTime()));
			ps.setString(3, email);

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
	
	public void update(String token) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(Constant.UPDATE_USER_TOKEN_FIVE_MINUTE);

			ps.setString(1, token);

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
	
	public void update(String email, String token, String password) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(Constant.UPDATE_USER_RESET_PASSWORD);

			ps.setString(1, BCrypt.hashpw(password, BCrypt.gensalt(Constant.GENSALT_DEFAULT_LOG2_ROUNDS)));
			ps.setString(2, email);
			ps.setString(3, token);

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
	
	public double getWallet(int userId) {

		Connection connection = MySqlDBConnector.makeConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_WALLET_BY_USERID);

			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				Double wallet = rs.getDouble("wallet");
				return wallet;
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
	
	public void clearWallet(int userId) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(Constant.UPDATE_WALLET_BY_USERID);

			ps.setInt(1, userId);

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
	
	public void updateOTP(String OTP, String email) {

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(Constant.UPDATE_USER_OTP);

			ps.setString(1, OTP);
			ps.setString(2, email);

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
	
	public String getOTP(String email) {

		Connection connection = MySqlDBConnector.makeConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(Constant.SELECT_VALID_OTP_BY_EMAIL);

			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				String OTP = rs.getString("otp");
				return OTP;
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

		return null;
	}

}
