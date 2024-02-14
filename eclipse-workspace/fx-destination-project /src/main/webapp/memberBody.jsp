<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.Date"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="content-wrapper">
	<!-- Content -->

	<div class="row">
		<div class="col-lg-8 mb-4">
			<div class="card h-100">
				<div class="d-flex align-items-end row">
					<div class="col-sm">
						<div class="card-body">
							<h5 class="card-title text-primary">
								We wish you have a great day, ${sessionScope.User.firstName}
								${sessionScope.User.lastName}!   🎉 <div class="position-absolute top-0 end-0"><a data-bs-toggle="modal"
									data-bs-target="#Update" class="btn btn-outline-primary end-0">Update
									details</a></div>
							</h5>
							<div class="invisible" id="levelMemberFromSession">${sessionScope.levelMember}</div>
							<div id="levelMember" class="d-flex text-primary"></div>
							<c:if test="${Double.parseDouble(sessionScope.wallet) == 0}">
								<div id="wallet" class="text-danger">You have no credit in your wallet.</div>
							</c:if>
							<c:if test="${Double.parseDouble(sessionScope.wallet) > 0}">
								<div id="wallet" class="text-danger">You have ${sessionScope.wallet} AUD credit in your wallet.</div>
							</c:if>
							<div class="mb-3">
								<c:if
									test="${numberOrderToday==0 && selectedCurrencyModelList[0].code==null}">
								 You haven't purchased any currency today. <a
										href="ProductServlet" class="btn btn-sm btn-outline-primary">Buy
										it now!</a>
								</c:if>
								<c:if
									test="${numberOrderToday==0 && selectedCurrencyModelList[0].code!=null}">
								 You have a pending order below: <a href="CartServlet"
										class="btn btn-sm btn-outline-primary">Pay it now!</a>
									<c:forEach var="currency" items="${selectedCurrencyModelList}">
										<div class="table-responsive-md"
											style="margin: auto; width: 100%;">
											<table class="table table-hover">
												<thead class="table-light">
													<tr>
														<th colspan="3"><fmt:formatNumber type="number"
																value="${currency.fxAmount}" /> ${currency.code}
															(${currency.name})</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td colspan="3" class="text-center text-danger">Rate:
															<fmt:formatNumber type="number" maxFractionDigits="6"
																value="${currency.rate}" />
														</td>
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
																<td class="text-center"><fmt:formatNumber
																		type="number" value="${currency.notes[i]}" /></td>
																<td class="text-center"><fmt:formatNumber
																		type="number" value="${currency.qty[i]}" /></td>
																<td class="text-end"><fmt:formatNumber
																		type="number"
																		value="${currency.qty[i] * currency.notes[i]}" /></td>
															</tr>
														</c:if>
													</c:forEach>

													<tr>
														<td colspan="3" class="text-center text-primary">Amount
															paid: <fmt:formatNumber type="number"
																value="${Math.round(currency.fxAmount/currency.rate*100)/100}" />
															AUD
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</c:forEach>
								</c:if>
								<c:if
									test="${numberOrderToday!=0 && selectedCurrencyModelList[0].code!=null}">
								 Thank you for purchasing currency with us today. You still have a pending order below: <a
										href="CartServlet" class="btn btn-sm btn-outline-primary">Pay
										it now</a>
									<c:forEach var="currency" items="${selectedCurrencyModelList}">
										<div class="table-responsive-md"
											style="margin: auto; width: 100%;">
											<table class="table table-hover">
												<thead class="table-light">
													<tr>
														<th colspan="3"><fmt:formatNumber type="number"
																value="${currency.fxAmount}" /> ${currency.code}
															(${currency.name})</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td colspan="3" class="text-center text-danger">Rate:
															<fmt:formatNumber type="number" maxFractionDigits="6"
																value="${currency.rate}" />
														</td>
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
																<td class="text-center"><fmt:formatNumber
																		type="number" value="${currency.notes[i]}" /></td>
																<td class="text-center"><fmt:formatNumber
																		type="number" value="${currency.qty[i]}" /></td>
																<td class="text-end"><fmt:formatNumber
																		type="number"
																		value="${currency.qty[i] * currency.notes[i]}" /></td>
															</tr>
														</c:if>
													</c:forEach>

													<tr>
														<td colspan="3" class="text-center text-primary">Amount
															paid: <fmt:formatNumber type="number"
																value="${Math.round(currency.fxAmount/currency.rate*100)/100}" />
															AUD
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</c:forEach>
								</c:if>
								<c:if
									test="${numberOrderToday > 1 && selectedCurrencyModelList[0].code==null}">
								 Great! You have made ${numberOrderToday} orders today. Thank you for using our services! <a
										href="ToolsServlet" class="btn btn-sm btn-outline-primary">Explore
										our tools now</a>
								</c:if>
								<c:if
									test="${numberOrderToday == 1 && selectedCurrencyModelList[0].code==null}">
								 Great! You have made an order today. Thank you for using our services! <a
										href="ToolsServlet" class="btn btn-sm btn-outline-primary">Explore
										our tools now</a>
								</c:if>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 mb-4">
			<div class="card h-100">
				<div class="card-body">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<div class="d-flex flex-column align-items-center gap-1">
							<h2 class="mb-2">${trackerList.size()}</h2>
							<span>Total Tracker requests</span>
						</div>
					</div>
					<ul class="p-0 m-0">
						<c:forEach var="tracker" items="${trackerList}">
							<li class="d-flex mb-4 pb-1">
								<div class="avatar flex-shrink-0 me-3">
									<span class="avatar-initial rounded bg-label-primary"><i
										class="bx bx-mobile-alt"></i></span>
								</div>
								<div
									class="d-flex w-100 flex-wrap align-items-center justify-content-between gap-2">
									<div class="me-2">
										<h6 class="mb-0">${tracker.trackCode}</h6>
										<small class="text-muted">Target: </small>
									</div>
									<div class="user-progress">
										<small class="fw-semibold text-danger"> <fmt:formatNumber
												type="number" value="${tracker.targetRate}" />

										</small>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<!-- Order Statistics -->
		<div class="col-md-6 col-lg-6 col-xl-6 mb-4">
			<div class="card h-100">
				<div
					class="card-header d-flex align-items-center justify-content-between">
					<h5 class="card-title m-0 me-2">
						Order Statistics <small class="text-muted text-end col-6">Total: <fmt:formatNumber
								type="number" value="${totalAUD}" /> AUD
						</small>
					</h5>
				</div>
				<div class="card-body">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<div class="d-flex flex-column align-items-center gap-1">
							<h2 class="mb-2">${totalOrder}</h2>
							<span>Total Orders</span>
						</div>
					</div>
					<ul class="p-0 m-0">
						<c:forEach var="currency" items="${codeAndAmountList}">
							<li class="d-flex mb-4 pb-1">
								<div class="avatar flex-shrink-0 me-3">
									<span class="avatar-initial rounded bg-label-primary"><i
										class="bx bx-mobile-alt"></i></span>
								</div>
								<div
									class="d-flex w-100 flex-wrap align-items-center justify-content-between gap-2">
									<div class="me-2">
										<h6 class="mb-0">${currency.code}</h6>
										<small class="text-muted">Total: </small>
									</div>
									<div class="user-progress">
										<small class="fw-semibold text-danger"> <fmt:formatNumber
												type="number" value="${currency.totalFX}" />

										</small>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<!--/ Order Statistics -->

		<!-- Transactions -->
		<div class="col-md-6 col-lg-6 mb-4">
			<div class="card h-100">
				<div
					class="card-header d-flex align-items-center justify-content-between">
					<h5 class="card-title m-0 me-2">
						Latest Order
						<c:if test="${latestOrder!=null}">
							<small class="text-muted"> no. ${latestOrder.id} on <fmt:formatDate
									type="both" value="${latestOrder.submitDate}" />
							</small>
						</c:if>
					</h5>

				</div>
				<div class="card-body">
					<c:if test="${latestOrder!=null}">
						<c:forEach var="currency" items="${latestOrderCurrencyModelList}">
							<div class="table-responsive-md"
								style="margin: auto; width: 100%;">
								<table class="table table-hover">
									<thead class="table-light">
										<tr>
											<th colspan="3"><fmt:formatNumber type="number"
													value="${currency.fxAmount}" /> ${currency.code}
												(${currency.name})</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="3" class="text-center text-danger">Rate: <fmt:formatNumber
													type="number" maxFractionDigits="6"
													value="${currency.rate}" /></td>
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
													<td class="text-center"><fmt:formatNumber
															type="number" value="${currency.notes[i]}" /></td>
													<td class="text-center"><fmt:formatNumber
															type="number" value="${currency.qty[i]}" /></td>
													<td class="text-end"><fmt:formatNumber type="number"
															value="${currency.qty[i] * currency.notes[i]}" /></td>
												</tr>
											</c:if>
										</c:forEach>

										<tr>
											<td colspan="3" class="text-center text-primary">Amount
												paid: <fmt:formatNumber type="number"
													value="${Math.round(currency.fxAmount/currency.rate*100)/100}" />
												AUD
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
		<!--/ Transactions -->
	</div>
</div>
<div class="modal fade" id="Update">
	<div
		class="modal-dialog modal-lg modal-fullscreen-lg-down modal-dialog-centered">
		<div class="modal-content">

			<!--Register details-->
			<div class="modal-body bg-light rounded">
				<div class="p-md-5 text-black">
					<!-- Modal Header -->
					<div class="modal-header d-flex justify-content-center p-3 mb-5">
						<h4 class="col-12 modal-title navbar-brand text-center">Account
							details</h4>
						<button type="button" class="btn-close me-5"
							data-bs-dismiss="modal"></button>
					</div>
					<div class="text-danger mb-3"> ${sessionScope.messageUpdate}</div>
					<form class="form row needs-validation" novalidate
						action="MemberServlet" method="post">
						<div class="row">
							<div class="col-md-7 mb-5">
								<div class="form-floating">
									<input type="text" id="fname" name="firstName"
										class="form-control" value="${sessionScope.User.firstName}"
										disabled readonly /> <label class="form-label" for="fname">First
										names</label>
								</div>
							</div>
							<div class="col-md-5 mb-5">
								<div class="form-floating">
									<input type="text" id="lname" name="lastName"
										class="form-control" value="${sessionScope.User.lastName}"
										disabled readonly /> <label class="form-label" for="lname">Last
										name</label>
								</div>
							</div>
						</div>

						<div
							class="d-md-flex justify-content-start align-items-center mb-4 py-1">

							<p class="mb-4 me-5">Gender:</p>

							<div class="form-check form-check-inline mb-4 me-5">
								<input class="form-check-input" type="radio" name="gender"
									id="femaleGender" value="female" required /> <label
									class="form-check-label" for="femaleGender">Female</label>
							</div>

							<div class="form-check form-check-inline mb-4 me-5">
								<input class="form-check-input" type="radio" name="gender"
									id="maleGender" value="male" required /> <label
									class="form-check-label" for="maleGender">Male</label>
							</div>

							<div class="form-check form-check-inline mb-4 ">
								<input class="form-check-input" type="radio" name="gender"
									id="otherGender" value="other" required /> <label
									class="form-check-label" for="otherGender">Other</label>
							</div>

						</div>

						<div class="row">
							<div class="col-md-7 mb-5">
								<div class="form-floating">
									<input type="email" id="email" name="email"
										class="form-control" value="${sessionScope.User.email}"
										disabled readonly /> <label class="form-label" for="email">Email</label>
								</div>
							</div>
							<div class="col-md-5 mb-5">
								<div class="form-floating">
									<input type="text" id="mobile_number" name="mobilePhone"
										class="form-control" value="${sessionScope.User.mobilePhone}"
										required /> <label class="form-label" for="mobile_number">Mobile
										number</label>
								</div>
							</div>
						</div>

						<div class="form-floating mb-5">
							<input type="text" id="address" name="address"
								class="form-control" value="${sessionScope.User.address}"
								required /> <label class="form-label ms-3" for="address">Address</label>
						</div>

						<div class="row">
							<div class="form-floating col-md-4 mb-5">
								<select class="form-select form-control" name="state" id="state"
									required>
									<option selected disabled value="">State</option>
									<option value="NSW">NSW</option>
									<option value="QLD">QLD</option>
									<option value="VIC">VIC</option>
									<option value="SA">SA</option>
									<option value="WA">WA</option>
									<option value="TAS">TAS</option>
									<option value="NT">NT</option>
									<option value="ACT">ACT</option>
								</select>
							</div>
							<div class="form-floating col-md-8 mb-5">
								<input type="text" id="city" class="form-control" name="city"
									value="${sessionScope.User.city}" required /> <label
									class="form-label ms-3" for="city">City</label>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-6 mb-5">
								<div class="form-floating">
									<input type="password" id="currentPassword" name="currentPassword"
										class="form-control" value=""/> 
									<label class="form-label" for="currentPassword">Current Password</label>
								</div>
							</div>
							<div class="col-md-6 mb-5">
								<div class="form-floating">
									<input type="password" id="newPassword" name="newPassword"
										class="form-control" value=""
										 /> <label class="form-label" for="newPassword">New Password</label>
								</div>
							</div>
						</div>

						<div class="modal-footer d-flex justify-content-end pt-3">
							<input type="hidden" name="command" value="UPDATE">
							<button type="submit" class="btn btn-warning btn-lg ms-2">Save
								changes</button>

						</div>

					</form>



				</div>

			</div>

		</div>
	</div>

</div>