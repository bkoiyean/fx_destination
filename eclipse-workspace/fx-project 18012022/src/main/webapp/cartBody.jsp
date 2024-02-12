<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.Date"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.fxdestination.entity.Currency"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<section class="section-padding" id="Cart">

	<div class="container h-100">
		<c:if test="${sessionScope.selectedCurrencyModelList[0].code == null}">
			<div class="col-12 text-center mb-5">
				You haven't selected any currency to buy yet. <a
					href="ProductServlet" class="btn btn-sm btn-outline-primary">
					Buy it now!</a>
				<div class="container h-100">
					<img class="img-responsive"
						src="http://localhost:8080/fx-project_18012022/img/empty-cart.jpg"
						alt="">
				</div>
			</div>
		</c:if>
		<c:if test="${sessionScope.selectedCurrencyModelList[0].code != null}">
			Thank you for using our service! <br> Please review your cart
				and make a payment when you are ready.
			<div
				class="h-100 row mt-5 d-flex align-items-center justify-content-center">

				<c:forEach var="currency" items="${selectedCurrencyModelList}">
					<div class="table-responsive-md" style="margin: auto; width: 70%;">
						<table class="table table-hover">
							<thead class="table-light">
								<tr>
									<th colspan="2"><fmt:formatNumber type="number"
											value="${currency.fxAmount}" /> ${currency.code}
										(${currency.name})</th>
									<th class="text-end">
										<button class="btn btn-danger"
											onclick="window.location.href='CartServlet?command=REMOVE&code=${currency.code}'">Remove</button>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="3" class="text-center text-success">Rate: <fmt:formatNumber
											type="number" maxFractionDigits="6" value="${currency.rate}" /></td>
								</tr>
								<tr>
									<td class="text-center">Denomination</td>
									<td class="text-center">Quantity</td>
									<td class="text-end">Total: <fmt:formatNumber
											type="number" value="${currency.fxAmount}" />
									</td>
								</tr>

								<c:forEach var="i" begin="0"
									end="${fn:length(currency.notes) - 1}">
									<c:if test="${currency.qty[i]!=0}">
										<tr>
											<td class="text-center"><fmt:formatNumber type="number"
													value="${currency.notes[i]}" /></td>
											<td class="text-center"><fmt:formatNumber type="number"
													value="${currency.qty[i]}" /></td>
											<td class="text-end"><fmt:formatNumber type="number"
													value="${currency.qty[i] * currency.notes[i]}" /></td>
										</tr>
									</c:if>
								</c:forEach>

								<tr>
									<td colspan="3" class="text-center text-primary">Cost: <fmt:formatNumber
											type="number"
											value="${Math.round(currency.fxAmount/currency.rate*100)/100}" />
										AUD
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:forEach>

			</div>



			<div class="container ">
				<button onclick="location.href = 'ProductServlet'"
					class="btn btn-warning absolute"
					style="position: fixed; top: 10rem; right: 1.625rem; z-index: 999999;">Buy
					More?</button>
			</div>
			<hr>
			
			
			<c:if test="${Double.parseDouble(sessionScope.wallet) > 0}">
				<div id="totalPrice" style="display: none;">${totalPrice - Double.parseDouble(sessionScope.wallet)}</div>
				<div class="text-center text-secondary">Total amount:
			<fmt:formatNumber type="number" value="${Math.round(totalPrice*100)/100}" />
				 AUD</div>
			<div class="text-center text-primary">Credit wallet:
			<fmt:formatNumber type="number" value="${Math.round(Double.parseDouble(sessionScope.wallet)*100)/100}" />
				AUD</div>
			<div class="text-center text-danger">Amount to pay:
			<fmt:formatNumber type="number" value="${Math.round((totalPrice - Double.parseDouble(sessionScope.wallet))*100)/100}" />
				AUD</div>
			</c:if>
			
			<c:if test="${Double.parseDouble(sessionScope.wallet) == 0 || sessionScope.wallet == null || sessionScope.wallet.isEmpty()}">
				<div id="totalPrice" style="display: none;">${totalPrice}</div>
				
			<div class="text-center text-danger">Amount to pay: 
				<fmt:formatNumber type="number" value="${totalPrice}" />
				 AUD</div>
			</c:if>
			

			<div class="container mt-5 w-75">
				<form id="payment-form">
					<div id="card-element">
						<!--Stripe.js injects the Card Element-->
					</div>
					<button id="submit">
						<div class="spinner hidden" id="spinner"></div>
						<span id="button-text">Pay by Card</span>
					</button>
					<p id="card-error" role="alert"></p>
					<p class="result-message hidden">Payment succeeded. Refresh the
						page to pay again.</p>
				</form>

				<form action="CartServlet" method="post" id="hidden-pay-form">
					<input type="hidden" value="PAY" name="command" id="command" /> <input
						type="submit" value="Make payment"
						class="btn btn-primary w-100 invisible" />
				</form>

			</div>

		</c:if>
	</div>

</section>