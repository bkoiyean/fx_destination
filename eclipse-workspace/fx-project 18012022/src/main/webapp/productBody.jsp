<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<section class="services section-padding" id="Product">

	<div class="container-fluid">
		<div class="container h-100">
			<div class="container h-100 row d-flex justify-content-start"
				id="infoFirstSelect">
				<div class="h-100 col-lg-8 d-none d-lg-block">
					<section class="hero">
						<div class="container-fluid h-100">
							<div class="row h-100">

								<div id="carouselExampleCaptions"
									class="carousel carousel-fade hero-carousel slide"
									data-bs-ride="carousel">
									<div class="carousel-inner" id="carousel"></div>
								</div>

							</div>
						</div>
					</section>
				</div>
				<div class="h-100 col-lg-4">


					<select id="firstSelect"
						onchange="firstSelected(); showBtnDeno();AUDAmountEntered(); FXAmountEntered()"
						class="form-select form-control" name="firstCurrency"
						placeholder="Currency to buy">
						<option selected disabled value="">Select currency to buy</option>
						<c:forEach var="currency" items="${currencyList}">
							<c:set var="count" value="${0}"></c:set>
							<c:forEach var="currencyModel"
								items="${sessionScope.selectedCurrencyModelList}">
								<c:if test="${currency.code.equals(currencyModel.code)}">
									<c:set var="count" value="${1}"></c:set>
								</c:if>
							</c:forEach>
							<c:if test="${count==1}">
								<option disabled value="${currency.code}">${currency.code}
									- ${currency.name} : Added to Cart</option>
							</c:if>
							<c:if test="${count==0}">
								<option value="${currency.code}">${currency.code} -
									${currency.name}</option>
							</c:if>

						</c:forEach>

					</select>
					<div id="levelDiscount" style="display: none;">${sessionScope.levelMember}</div>
					<div id="discountedRate" style="display: none;"></div>
					<div id="selectedCode" style="display: none;"></div>
					<div id="selectedName" style="display: none;"></div>
					<div id="selectedNotes" style="display: none;"></div>
					<div id="selectedRate" style="display: none;"></div>
					<div class="h-100 d-flex justify-content-end my-2">
						<button
							class="d-none d-flex btn btn-primary text-white text-end my-3"
							id="btnDenoDetails" onclick="showFirstDeno()">Denomination</button>
					</div>

					<div class='h-100 row d-none d-flex justify-content-end g-0'
						id='firstTable'></div>
					<div class="row h-100 d-flex justify-content-end g-0">
						<div class="col-7 h-100 form-floating my-2">
							<input type="number" id="total_fx1" onchange="FXAmountEntered()"
								class="form-control" placeholder="Enter amount" /> <label
								class="form-label" for="total_fx1">Enter amount in <span
								id="firstCode"></span></label>
						</div>
						<div class="col-9 h-100 my-2">
							<h6 class="text-danger text-end text-muted">
								<small id="firstRate"> </small>
							</h6>
						</div>
						<hr />
						<div class="col-7 h-100 form-floating my-2">
							<input type="number" step="0.05" id="total_aud1"
								onchange="AUDAmountEntered(); FXAmountEntered()"
								class="form-control" placeholder="Amount in AUD" /> <label
								class="form-label" for="total_aud1">Total AUD</label>
						</div>
					</div>

				</div>
			</div>

			<hr style="border-top: 3px solid #0c0808" />
			<div class="row container-fluid h-100">
				<div class="col-8 d-flex justify-content-center my-2">
					<button
						class="d-flex d-none btn btn-primary text-white text-center my-3"
						id="btnAddToCart" onclick="showConfirmation()"
						data-bs-toggle="modal" data-bs-target="#AddToCart">Add to
						Cart</button>
				</div>

			</div>
		</div>
	</div>
</section>