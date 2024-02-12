package com.fxdestination.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fxdestination.constant.Constant;
import com.google.gson.Gson;

import com.stripe.exception.StripeException;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(CheckoutServlet.class);
	private static final long serialVersionUID = 1L;
	private static Gson gson = new Gson();

	static class CreatePayment {
		double amount;

		public double getAmount() {
			return amount;
		}
	}

	static class CreatePaymentResponse {
		private String clientSecret;

		public CreatePaymentResponse(String clientSecret) {
			this.clientSecret = clientSecret;
		}
	}

	public CheckoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Stripe.apiKey = Constant.STRIPE_API_KEY;
		
		BufferedReader reader = request.getReader();
		
	    CreatePayment postBody = gson.fromJson(reader, CreatePayment.class);

		PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder().setCurrency("aud")
				.setAmount((long)postBody.amount).build();

		PaymentIntent intent;
		try {
			intent = PaymentIntent.create(createParams);
			CreatePaymentResponse paymentResponse = new CreatePaymentResponse(intent.getClientSecret());
			response.getWriter().write(gson.toJson(paymentResponse));
		} catch (StripeException e) {
			logger.error("Error: ", e);
		}

		
	}

}
