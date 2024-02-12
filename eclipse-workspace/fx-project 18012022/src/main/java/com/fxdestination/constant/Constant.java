package com.fxdestination.constant;

public class Constant {
	public static final String SELECT_ALL_BRANCH = "SELECT * FROM `fx_project`.`branch`";
	public static final String SELECT_ALL_CURRENCY = "SELECT * FROM `fx_project`.`currency`";
	public static final String SELECT_CURRENCY_BY_CODE = "SELECT * FROM `fx_project`.`currency` WHERE `code` = ?";
	public static final String SELECT_RATE_BY_CODE = "SELECT rate FROM `fx_project`.`currency` WHERE `code` = ?";
	public static final String INSERT_ORDER = "INSERT INTO `fx_project`.`order` (`user_id`, `submit_date`, `approve`) VALUES (?,?,?)";
	public static final String SELECT_ORDER_BY_ID = "SELECT * FROM `fx_project`.`order` WHERE id = ?";
	public static final String SELECT_QTY_OF_ORDER_BY_USERID_TODAY = "SELECT count(*) as result FROM `fx_project`.`order` WHERE `user_id` = ? and date(`submit_date`) = current_date()";
	public static final String SELECT_LATEST_ORDER_BY_USERID = "SELECT * FROM `fx_project`.`order` WHERE `user_id` = ? ORDER BY id DESC LIMIT 1";
	public static final String INSERT_ORDER_DETAIL = "INSERT INTO `fx_project`.`order_details` (`order_id`, `currency_code`, `quantity`,`rate`,`fx_amount`) VALUES (?,?,?,?,?)";
	public static final String SELECT_ALL_CURRENCY_ORDERED_BY_ORDERID = "SELECT * FROM `fx_project`.`order_details` WHERE `order_id` = ?";
	public static final String SELECT_ALL_RATE_HISTORY_BY_CODE = "SELECT h.`update_time`, h.`currency_rate` FROM `fx_project`.`history` h WHERE h.`currency_code` = ?";
	public static final String SELECT_ALL_TRACKING_USERID_BY_CODE_AND_RATE = "SELECT `user_id` FROM `fx_project`.`tracker` WHERE `track_code` = ? and `status` = 'created' and `target_rate` <= ? GROUP BY `user_id`";
	public static final String UPDATE_TRACKER_BY_CODE_AND_RATE = "UPDATE `fx_project`.`tracker` SET `status` = 'done' WHERE `track_code` = ? and `status` = 'created' and `target_rate` <= ? and `user_id` = ?";
	public static final String INSERT_TRACKER = "INSERT INTO `fx_project`.`tracker` (`user_id`, `track_code`, `target_rate`, `status`) VALUES (?, ?, ?, ?)";
	public static final String SELECT_ALL_TRACKER_BY_USERID = "SELECT `track_code`,  MAX(`target_rate`) as rate FROM `fx_project`.`tracker` WHERE `status` = 'created' and `user_id` = ? GROUP BY `track_code`";
	public static final String SELECT_ALL_USER = "SELECT * FROM `fx_project`.`user`";
	public static final String INSERT_USER = "INSERT INTO `fx_project`.`user` (`first_name`, `last_name`, `gender`, `email`, `mobile_phone`, `address`, `state`, `city`, `password`) VALUES (?,?,?,?,?,?,?,?,?)";
	public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM `fx_project`.`user` WHERE `email` = ?";
	public static final String SELECT_USER_BY_ID = "SELECT * FROM `fx_project`.`user` WHERE `id` = ?";
	public static final String SELECT_ALL_USER_BY_ID = "SELECT * FROM `fx_project`.`user` WHERE `id` = ?";
	public static final String UPDATE_USER = "UPDATE `fx_project`.`user` SET `gender` = ? , `mobile_phone` = ?, `address` = ?, `state` = ?, `city` = ? WHERE (`id` = ?)";
	public static final String UPDATE_USER_PASSWORD = "UPDATE `fx_project`.`user` SET `gender` = ? , `mobile_phone` = ?, `address` = ?, `state` = ?, `city` = ?, `password` = ? WHERE (`id` = ?)";
	public static final String SELECT_TOTAL_AUD_BY_USERID = "SELECT SUM(derived_table.aud_amount) as result "
			+ "FROM (SELECT o.id, o.user_id, d.currency_code, d.rate, d.fx_amount,(d.fx_amount/d.rate) as aud_amount "
			+ "FROM fx_project.order o "
			+ "JOIN order_details d ON o.id = d.order_id WHERE o.user_id = ?) AS derived_table";
	public static final String SELECT_ALL_FX_CODE_AND_FX_AMOUNT_MODEL = "SELECT d.currency_code, sum(d.fx_amount) as total_fx FROM fx_project.order o "
			+ "JOIN order_details d ON o.id = d.order_id WHERE o.user_id = ? GROUP BY d.currency_code";
	public static final String SELECT_QTY_OF_ORDER_BY_USERID = "SELECT count(*) as result FROM fx_project.order WHERE user_id = ? GROUP BY user_id";

	public static final String PARAM_COMMAND = "command";
	public static final String COMMAND_PAY = "PAY";
	public static final String COMMAND_ADD_TO_CART = "ADD_TO_CART";
	public static final String COMMAND_REMOVE = "REMOVE";

	public static final String PARAM_CODE = "code";
	public static final String ATTRIBUTE_SELECTED_CURRENCY_LIST = "selectedCurrencyModelList";
	public static final double DEFAULT_DOUBLE_VALUE = 0;
	public static final int DEFAULT_INT_VALUE = 0;
	public static final String ATTRIBUTE_TOTAL_PRICE = "totalPrice";
	public static final String CARTPATH_SHOW = "CartServlet?command=SHOW";
	public static final String ATTRIBUTE_USER = "User";
	public static final String EMPTY_STRING = "";
	public static final String MEMBERPATH_SHOW_ORDERID = "MemberServlet?command=SHOW&orderId=";
	public static final String DEFAULT_ADDED_PARAM_QTY = "Qty";
	public static final String PARAM_RATE = "rate";
	public static final String CARTPATH = "cart.jsp";
	public static final String HOMEPATH = "index.jsp";
	public static final String ATTRIBUTE_BRANCH_LIST = "branchList";
	public static final String LOCATIONPATH = "location.jsp";
	public static final String COMMAND_REGISTER = "REGISTER";
	public static final String COMMAND_LOGIN = "LOGIN";
	public static final String COMMAND_LOGOUT = "LOGOUT";
	public static final String COMMAND_UPDATE = "UPDATE";
	public static final String PARAM_GENDER = "gender";
	public static final String PARAM_MOBILEPHONE = "mobilePhone";
	public static final String PARAM_ADDRESS = "address";
	public static final String PARAM_STATE = "state";
	public static final String PARAM_CITY = "city";
	public static final String MEMBER_CONTROLLER = "MemberServlet";
	public static final String PARAM_URL = "url";
	public static final String PRODUCTPATH_SIGNAL = "Product";
	public static final String LOCATIONPATH_SIGNAL = "Location";
	public static final String TOOLSPATH_SIGNAL = "Tools";
	public static final String CARTPATH_SIGNAL = "Cart";
	public static final String PRODUCTPATH_SHOW_MODAL = "ProductServlet#";
	public static final String LOCATIONPATH_SHOW_MODAL = "LocationServlet#";
	public static final String TOOLSPATH_SHOW_MODAL = "ToolsServlet#";
	public static final String CARTPATH_SHOW_MODAL = "CartServlet#";
	public static final String HOMEPATH_SHOW_MODAL = "IndexServlet#";
	public static final String PARAM_FIRST_NAME = "firstName";
	public static final String PARAM_LAST_NAME = "lastName";
	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_PASSWORD = "password";

	public static final String ERROR_REGISTER = "This email address was in use! Please login or register with another email.";
	public static final String ATTRIBUTE_ERROR_REGISTER = "ErrorRegister";
	public static final String ATTRIBUTE_ERROR_LOGIN = "ErrorLogin";
	public static final String SHOW_LOGIN = "Login";
	public static final String SHOW_REGISTER = "Register";
	public static final String FILTER_PRODUCT_SIGNAL = "filter=product";
	public static final String FILTER_TOOLS_SIGNAL = "filter=tools";
	public static final String PRODUCT_CONTROLLER = "ProductServlet";
	public static final String TOOLS_CONTROLLER = "ToolsServlet";
	public static final String MEMBERPATH = "member.jsp";
	public static final String MEMBERPATH_SHOW_UPDATE = "member.jsp#Update";

	public static final String PARAM_LOGIN_EMAIL = "loginEmail";
	public static final String PARAM_LOGIN_PASSWORD = "loginPassword";

	public static final String ATTRIBUTE_QTY_ORDER_TODAY = "numberOrderToday";
	public static final String ATTRIBUTE_TRACKER_LIST = "trackerList";
	public static final String ATTRIBUTE_TOTAL_AUD = "totalAUD";
	public static final String ATTRIBUTE_TOTAL_ORDER = "totalOrder";
	public static final String ATTRIBUTE_CODE_AND_AMOUNT_LIST = "codeAndAmountList";
	public static final String ATTRIBUTE_LATEST_ORDER = "latestOrder";
	public static final String ATTRIBUTE_LATEST_ORDER_DETAIL = "latestOrderCurrencyModelList";

	public static final String ERROR_LOGIN = "Invalid login details!";
	public static final String HOMEPATH_CONTROLLER = "IndexServlet";

	public static final String ATTRIBUTE_CURRENCY_LIST = "currencyList";
	public static final String PRODUCTPATH = "product.jsp";

	public static final String PARAM_FIRST_TRACK = "firstTrack";
	public static final String PARAM_TARGET_RATE = "targetRate";
	public static final String MESSAGE_TRACK_CREATED = "Your request is created! We will email you once the rate reaches your target!";
	public static final String ATTRIBUTE_MESSAGE = "Message";
	public static final String ERROR_TRACK_INPUT = "Please select the code to track!";
	public static final String TOOLSPATH = "tools.jsp";
	public static final String PARAM_FIRST_GRAPH = "firstGraph";

	public static final String PARAM_NAME_CONTACT = "name";
	public static final String PARAM_EMAIL_CONTACT = "email";
	public static final String PARAM_MESSAGE_CONTACT = "message";
	public static final String DEFAULT_SUBJECT_CONTACT = "Message from ";
	public static final String DEFAULT_SEND_TO_CONTACT = "kevin240au@gmail.com";
	public static final String SUCCESSFUL_SUBMIT_CONTACT_MESSAGE = "Thank you for your message! We will contact you shortly.";
	public static final String ATTRIBUTE_NOTIFICATION_CONTACT = "notification";
	public static final String CONTACTPATH = "index.jsp#contact";

	public static final int GENSALT_DEFAULT_LOG2_ROUNDS = 12;
	public static final String PARAM_CURRENT_PASSWORD = "currentPassword";
	public static final String PARAM_NEW_PASSWORD = "newPassword";
	public static final String ATTRIBUTE_MESSAGE_UPDATE = "messageUpdate";
	public static final String MESSAGE_UPDATE_WITHOUT_PASSWORD = "Your details excluding password have been updated successfully! To update password, please enter correct Current Password and valid New Password";
	public static final String MESSAGE_UPDATE_WITH_PASSWORD = "Your details have been updated successfully!";

	public static final String COMMAND_FORGOT = "FORGOT";
	public static final String PARAM_FORGOT_EMAIL = "forgotEmail";

	public static final String UPDATE_USER_TOKEN_FORGOT = "UPDATE `fx_project`.`user` SET `token` = ? , `token_valid_date` = ? WHERE (`email` = ?)";
	public static final String SUBJECT_FORGOT_PASSWORD = "Reset password instruction";
	public static final String SHOW_FORGOT_NOTIFICATION = "ForgotNotification";

	public static final String SHOW_VALID_TOKEN = "ValidToken";
	public static final String PARAM_TOKEN = "token";

	public static final String SELECT_EMAIL_BY_VALID_TOKEN = "SELECT `email` FROM `fx_project`.`user` WHERE `token` = ? AND CURRENT_TIMESTAMP() <= token_valid_date";
	public static final String SHOW_INVALID_TOKEN = "InvalidToken";
	public static final String UPDATE_USER_TOKEN_FIVE_MINUTE = "UPDATE `fx_project`.`user` SET `token_valid_date` = DATE_ADD(NOW(), INTERVAL 5 MINUTE) WHERE (`token` = ?)";
	public static final String ATTRIBUTE_EMAIL = "email";
	public static final String ATTRIBUTE_TOKEN = "token";
	public static final String UPDATE_USER_RESET_PASSWORD = "UPDATE `fx_project`.`user` SET `password` = ? , `token_valid_date` = NOW() WHERE (`email` = ? AND `token` = ?)";
	public static final String COMMAND_RESET = "RESET";

	public static final String PARAM_CODE_XE = "codeXE";
	public static final String STRIPE_API_KEY = "sk_test_51MZQkUBp4TjwK7woVTY79t4aVVPv2A1p5Doiu9CTs9u3EHeokTuKSTNL6DwqojKHulS0WfiydU2aPRPmDlCFBK9J00oX1Xq87F";
	public static final String ATTRIBUTE_LEVEL_MEMBER = "levelMember";

	public static final String SELECT_WALLET_BY_USERID = "SELECT `wallet` FROM `fx_project`.`user` WHERE `id` = ?";
	public static final String ATTRIBUTE_WALLET = "wallet";
	public static final String UPDATE_WALLET_BY_USERID = "UPDATE `fx_project`.`user` SET `wallet` = 0 WHERE `id` = ?";

	public static final String COMMAND_LOGINOTP = "LOGINOTP";
	public static final String COMMAND_LOGINWITHCODE = "LOGINWITHCODE";
	public static final String PARAM_LOGINOTP_EMAIL = "loginOTPEmail";
	public static final String SHOW_LOGINWITHCODE = "LogWithCode";
	public static final String SUBJECT_OTP_REQUEST = "OTP Access code";
	public static final String ATTRIBUTE_LOGIN_OTP_EMAIL = "loginOTPEmail";
	public static final String PARAM_LOGIN_OTP_EMAIL = "loginOTPEmail";
	public static final String PARAM_FIRST_CODE = "first";
	public static final String PARAM_SECOND_CODE = "second";
	public static final String PARAM_THIRD_CODE = "third";
	public static final String PARAM_FOURTH_CODE = "fourth";
	public static final String PARAM_FIFTH_CODE = "fifth";
	public static final String PARAM_SIXTH_CODE = "sixth";
	public static final String UPDATE_USER_OTP = "UPDATE `fx_project`.`user` SET `otp` = ? , `otp_valid_time` = DATE_ADD(NOW(), INTERVAL 1 MINUTE) WHERE (`email` = ?)";
	public static final String SELECT_VALID_OTP_BY_EMAIL = "SELECT `otp` FROM `fx_project`.`user` WHERE `email` = ? AND CURRENT_TIMESTAMP() <= otp_valid_time";

	public static final String COMMAND_TRACK_ORDER = "TRACKORDER";
	public static final String PARAM_ORDER_TO_TRACK = "orderToTrack";
	public static final String ATTRIBUTE_TRACKING_ORDER = "trackingOrder";
	public static final String ATTRIBUTE_TRACKING_ORDER_DETAIL = "trackingOrderCurrencyModelList";
	public static final String SHOW_TRACK_ORDER = "TrackOrder";
	public static final String SELECT_STATUS_BY_ID = "SELECT `approve` FROM `fx_project`.`order` WHERE id = ?";
	public static final String ATTRIBUTE_TRACKING_STATUS = "trackingStatus";




}
