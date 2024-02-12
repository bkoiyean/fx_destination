package com.fxdestination.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.fxdestination.constant.Constant;
import com.fxdestination.entity.User;

public class SendGmail {

	private static String USER_NAME = "info.fxdestination@gmail.com";
	private static String PASSWORD = "xzpuatrdudjtxayd";

	//public static void main(String[] args) {
		//String[] to = { RECIPIENT }; // list of recipient email addresses
		//String to = "kevin240au@gmail.com";
		//String subject = "Java send mail example";
		//String body = "Welcome to JavaMail!";

		//sendEmailFromContact(to, subject, body);
	//}

	public void sendEmailFromContact(String to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		String from = USER_NAME;
		String pass = PASSWORD;
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress toAddress = new InternetAddress(to);

			// To get the array of addresses
			//for (int i = 0; i < to.length; i++) {
			//	toAddress[i] = new InternetAddress(to[i]);
			//}

			//for (int i = 0; i < toAddress.length; i++) {
			//	message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			//}
			message.setRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
	
	public void sendResetPassword(String to, String token, User user) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		String from = USER_NAME;
		String pass = PASSWORD;
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress toAddress = new InternetAddress(to);

			message.setRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject(Constant.SUBJECT_FORGOT_PASSWORD);
			
			String body = "Dear " + user.getFirstName() + " " + user.getLastName() + ",\n\n"
					+ "You received this email because you asked us to help you reset your password.\n\n"
					+ "Please kindly click on the following link to create a new password for your account: \n\n"
					+ "http://localhost:8080/fx-project_18012022/IndexServlet?token=" + token + "\n\n"
					+ "Best regards,\n" + "The FX Destination Team";
			message.setText(body);
			
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
	
	public static void sendOTP(String to, String OTP, User user) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		String from = USER_NAME;
		String pass = PASSWORD;
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress toAddress = new InternetAddress(to);

			message.setRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject(Constant.SUBJECT_OTP_REQUEST);
			
			String body = "Dear " + user.getFirstName() + " " + user.getLastName() + ",\n\n"
					+ "You received this email because you are trying to log in by OTP access code.\n\n"
					+ "Your access code is: " + OTP + ". Please contact us immediately if it's not you. \n\n"
					+ "Best regards,\n" + "The FX Destination Team";
			message.setText(body);
			
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
}
