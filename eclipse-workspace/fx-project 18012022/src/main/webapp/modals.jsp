<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="modal fade" id="Login">
	<div
		class="modal-dialog modal-fullscreen-md-down modal-dialog-centered">
		<div class="modal-content">

			<!--Login details-->
			<div class="modal-body bg-light rounded">

				<div class="d-flex justify-content-center h-100">
					<div class="row bg-light rounded">
						<!-- Modal Header -->
						<div class="modal-header d-flex justify-content-center p-3 mb-5">
							<h4 class="col-12 modal-title navbar-brand text-center">Login</h4>
							<button type="button" class="btn-close me-5"
								data-bs-dismiss="modal"></button>
						</div>
						<form class="form row needs-validation mx-1"
							action="MemberServlet" novalidate method="post">
							<div class="form-floating mb-5">
								<input type="email" id="login_email" name="loginEmail"
									class="form-control" placeholder="Email address" required /> <label
									for="login_email" class="form-label ms-3">Email address</label>
								<div class="valid-feedback"></div>
								<div class="invalid-feedback">Please enter your email
									address.</div>
							</div>
							<div class="form-floating mb-5">
								<input type="password" id="login_password" name="loginPassword"
									class="form-control" placeholder="Password" required /> <label
									for="login_email" class="form-label ms-3">Password</label>
								<div class="valid-feedback"></div>
								<div class="invalid-feedback">Please enter your password.</div>
								<p id="errorLogin" class="text-danger text-center">${sessionScope.ErrorLogin}</p>
							</div>
							<!-- 2 column grid layout for inline styling -->
							<div class="row">
								<div class="col d-flex justify-content-center mb-3">
									<a href="#LogOTP" data-bs-toggle="modal"
										data-bs-target="#LogOTP">Login OTP</a>
								</div>

								<div class="col d-flex justify-content-center mb-3">
									<a href="#ForgotPassword" data-bs-toggle="modal"
										data-bs-target="#ForgotPassword">Forgot password?</a>
								</div>
							</div>
							<div class="modal-footer row">
								<div class="row">
									<div class="col-6 d-flex justify-content-center">
										<div class="row d-flex justify-content-center">
											<small id="loginHelp"
												class="form-text text-muted text-center">First time
												with us?</small>
											<button type="button" class="btn btn-primary text-center"
												data-bs-toggle="modal" data-bs-target="#Register"
												style="width: fit-content">Register</button>

										</div>
									</div>
									<div class="col-6 d-flex justify-content-center">
										<div class="row d-flex justify-content-center">
											<small id="loginHelp"
												class="form-text text-muted text-center"></small> <input
												type="hidden" name="command" value="LOGIN"> <input
												type="hidden" name="url" id="url" />
											<button onclick="getURL()" type="submit"
												class="btn btn-warning btn" style="width: 80px">Login</button>
										</div>
									</div>
								</div>

							</div>

						</form>

					</div>

				</div>


			</div>

		</div>
	</div>

</div>

<div class="modal fade" id="Logout">
	<div
		class="modal-dialog modal-fullscreen-md-down modal-dialog-centered">
		<div class="modal-content">

			<!--Logout details-->
			<div class="modal-body bg-light rounded">

				<div class="d-flex justify-content-center h-100">
					<div class="row bg-light rounded">
						<!-- Modal Header -->
						<div class="modal-header d-flex justify-content-center p-3 mb-5">
							<h4 class="col-12 modal-title navbar-brand text-center">Logout
								confirmation</h4>
							<button type="button" class="btn-close me-5"
								data-bs-dismiss="modal"></button>
						</div>
						<div class="text-center mb-5">Do you want to log out now?</div>
						<form class="form row mx-1" action="MemberServlet">
							<div class="modal-footer row">
								<div class="row">
									<div class="col d-flex justify-content-center">
										<div class="row d-flex justify-content-center">
											<input type="hidden" name="command" value="LOGOUT">
											<button type="submit" class="btn btn-secondary text-center"
												style="width: fit-content">Yes</button>

										</div>
									</div>

								</div>
							</div>

						</form>

					</div>

				</div>


			</div>

		</div>
	</div>

</div>

<div class="modal fade" id="Register">
	<div
		class="modal-dialog modal-lg modal-fullscreen-lg-down modal-dialog-centered">
		<div class="modal-content">

			<!--Register details-->
			<div class="modal-body bg-light rounded">
				<div class="p-md-5 text-black">
					<!-- Modal Header -->
					<div class="modal-header d-flex justify-content-center p-3 mb-5">
						<h4 class="col-12 modal-title navbar-brand text-center">REGISTER</h4>
						<button type="button" class="btn-close me-5"
							data-bs-dismiss="modal"></button>
					</div>
					<form id="registerForm" class="form row needs-validation"
						novalidate action="MemberServlet" method="post">
						<div class="row">
							<p id="errorRegister" class="text-danger text-center">${sessionScope.ErrorRegister}</p>
							<div class="col-md-7 mb-5">
								<div class="form-floating">
									<input type="text" id="fname" name="firstName"
										class="form-control" placeholder="Thi Huong Lan" required />
									<label class="form-label" for="fname">First names</label>
								</div>
							</div>
							<div class="col-md-5 mb-5">
								<div class="form-floating">
									<input type="text" id="lname" name="lastName"
										class="form-control" placeholder="Nguyen" required /> <label
										class="form-label" for="lname">Last name</label>
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
										class="form-control" placeholder="This will be your Username"
										required /> <label class="form-label" for="email">Email</label>
								</div>
							</div>
							<div class="col-md-5 mb-5">
								<div class="form-floating">
									<input type="text" id="mobile_number" name="mobilePhone"
										class="form-control" placeholder="Australian phone number"
										required /> <label class="form-label" for="mobile_number">Mobile
										number</label>
								</div>
							</div>
						</div>

						<div class="form-floating mb-5">
							<input type="text" id="address" name="address"
								class="form-control" placeholder="Full address in Australia"
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
									placeholder="City in Australia" required /> <label
									class="form-label ms-3" for="city">City</label>
							</div>
						</div>

						<div class="form-floating mb-4">
							<input type="password" id="pwd"
								class="password form-control validate" name="password"
								placeholder="Password" required /> <label class="ms-3"
								for="pwd" data-error="incorrect" data-success="correct">Password</label>
						</div>
						<div class="col-12">
							<div class="alert alert-warning password-alert" id="alert1"
								role="alert">
								<ul>
									<li class="requirements leng" id="alert1"><i
										class="bi bi-check-circle text-success"></i><i
										class="bi bi-x text-danger"></i> Your password must have at
										least 8 characters.</li>
									<li class="requirements big-letter" id="alert1"><i
										class="bi bi-check-circle text-success"></i><i
										class="bi bi-x text-danger"></i> Your password must have at
										least 1 uppercase letter.</li>
									<li class="requirements num" id="alert1"><i
										class="bi bi-check-circle text-success"></i><i
										class="bi bi-x text-danger"></i> Your password must have at
										least 1 number.</li>
									<li class="requirements special-char" id="alert1"><i
										class="bi bi-check-circle text-success"></i><i
										class="bi bi-x text-danger"></i> Your password must have at
										least 1 special character.</li>
								</ul>
							</div>
						</div>

						<div class="form-floating mb-4">
							<input type="password" id="pwd2" class="password form-control"
								placeholder="Re-enter Password" required /> <label
								class="form-label ms-3" for="pwd2">Re-enter Password</label>
						</div>


						<div class="col-12">
							<div class="alert alert-warning password-alert" id="alert2"
								role="alert">
								<p class="requirements same" id="alert2">
									<i class="bi bi-check-circle text-success"></i><i
										class="bi bi-x text-danger"></i> You must re-enter the same
									password.
								</p>
							</div>
						</div>

						<div class="modal-footer d-flex justify-content-end pt-3">
							<button type="reset" class="btn btn-light btn-lg">Reset
								all</button>
							<input type="hidden" name="command" value="REGISTER"> <input
								type="hidden" name="url" id="url">
							<button id="submitButton" onclick="getURL()" type="submit"
								class="btn btn-warning btn-lg ms-2">Submit form</button>

						</div>

					</form>



				</div>

			</div>

		</div>
	</div>

</div>

<div class="modal fade" id="ForgotPassword">
	<div
		class="modal-dialog modal-fullscreen-md-down modal-dialog-centered">
		<div class="modal-content">

			<div class="modal-body bg-light rounded">

				<div class="d-flex justify-content-center h-100">
					<div class="row bg-light rounded">
						<!-- Modal Header -->
						<div class="modal-header d-flex justify-content-center p-3 mb-3">
							<h4 class="col-12 modal-title navbar-brand text-center">Forgot
								Password?</h4>
							<button type="button" class="btn-close me-5"
								data-bs-dismiss="modal"></button>
						</div>
						<div class="text-center mb-2">Please provide correct email
							to receive our reset instructions</div>
						<form class="form row mx-1" action="MemberServlet" method="post">
							<div class="row">
								<div class="form-floating mb-3">
									<input type="text" id="forgotEmail" name="forgotEmail"
										class="form-control" placeholder="Your email address" required />
									<label class="form-label ms-3" for="forgotEmail">Email
										address: </label>
								</div>


								<div class="modal-footer row">
									<div class="col d-flex justify-content-center">
										<div class="row d-flex justify-content-center">
											<input type="hidden" name="command" value="FORGOT"> <input
												type="hidden" name="url" id="url">

											<button type="submit" onclick="getURL()"
												class="btn btn-primary text-center"
												style="width: fit-content">Send request</button>

										</div>
									</div>

								</div>
							</div>

						</form>

					</div>

				</div>


			</div>

		</div>
	</div>

</div>

<div class="modal fade" id="ForgotNotification">
	<div
		class="modal-dialog modal-fullscreen-md-down modal-dialog-centered">
		<div class="modal-content">

			<div class="modal-body bg-light rounded">

				<div class="d-flex justify-content-center h-100">
					<div class="row bg-light rounded">
						<!-- Modal Header -->

						<div class="text-center text-danger mb-2">A reset password
							instruction will be sent to you shortly!</div>

					</div>

				</div>

			</div>

		</div>
	</div>

</div>

<div class="modal fade" id="InvalidToken">
	<div
		class="modal-dialog modal-fullscreen-md-down modal-dialog-centered">
		<div class="modal-content">

			<div class="modal-body bg-light rounded">

				<div class="d-flex justify-content-center h-100">
					<div class="row bg-light rounded">
						<!-- Modal Header -->

						<div class="text-center text-danger mb-2">
							Your reset password link already expired. Please click <a
								href="#" data-bs-toggle="modal" data-bs-target="#ForgotPassword">here</a>
							to reset password again!
						</div>

					</div>

				</div>

			</div>

		</div>
	</div>

</div>


<div class="modal fade" id="ValidToken">
	<div
		class="modal-dialog modal-lg modal-fullscreen-lg-down modal-dialog-centered">
		<div class="modal-content">

			<!--Reset details-->
			<div class="modal-body bg-light rounded">
				<div class="p-md-5 text-black">
					<!-- Modal Header -->
					<div class="modal-header d-flex justify-content-center p-3 mb-5">
						<h4 class="col-12 modal-title navbar-brand text-center">RESET
							PASSWORD</h4>
						<button type="button" class="btn-close me-5"
							data-bs-dismiss="modal"></button>
					</div>
					<div class="text-danger text-center mx-3">You have 5 minutes
						to create your new password!</div>
					<form id="resetForm" class="form row needs-validation" novalidate
						action="IndexServlet" method="post">

						<div class="form-floating mb-4">
							<input type="password" id="pwdReset"
								class="password form-control validate" name="password"
								placeholder="Password" required /> <label class="ms-3"
								for="pwdReset" data-error="incorrect" data-success="correct">Password</label>
						</div>
						<div class="col-12">
							<div class="alert alert-warning password-alert" id="alert1Reset"
								role="alert">
								<ul>
									<li class="requirements lengReset" id="alert1Reset"><i
										class="bi bi-check-circle text-success"></i><i
										class="bi bi-x text-danger"></i> Your password must have at
										least 8 characters.</li>
									<li class="requirements big-letterReset" id="alert1Reset"><i
										class="bi bi-check-circle text-success"></i><i
										class="bi bi-x text-danger"></i> Your password must have at
										least 1 uppercase letter.</li>
									<li class="requirements numReset" id="alert1Reset"><i
										class="bi bi-check-circle text-success"></i><i
										class="bi bi-x text-danger"></i> Your password must have at
										least 1 number.</li>
									<li class="requirements special-charReset" id="alert1Reset"><i
										class="bi bi-check-circle text-success"></i><i
										class="bi bi-x text-danger"></i> Your password must have at
										least 1 special character.</li>
								</ul>
							</div>
						</div>

						<div class="form-floating mb-4">
							<input type="password" id="pwd2Reset"
								class="password form-control" placeholder="Re-enter Password"
								required /> <label class="form-label ms-3" for="pwd2Reset">Re-enter
								Password</label>
						</div>


						<div class="col-12">
							<div class="alert alert-warning password-alert" id="alert2Reset"
								role="alert">
								<p class="requirements sameReset" id="alert2Reset">
									<i class="bi bi-check-circle text-success"></i><i
										class="bi bi-x text-danger"></i> You must re-enter the same
									password.
								</p>
							</div>
						</div>

						<div class="modal-footer d-flex justify-content-end pt-3">
							<button type="reset" class="btn btn-light btn-lg">Reset
								all</button>
							<input type="hidden" name="command" value="RESET"> <input
								type="hidden" name="token" value="${sessionScope.token}">
							<button id="submitButtonReset" type="submit"
								class="btn btn-warning btn-lg ms-2">Submit</button>

						</div>

					</form>



				</div>

			</div>

		</div>
	</div>

</div>

<div class="modal fade" id="LogOTP">
	<div
		class="modal-dialog modal-fullscreen-md-down modal-dialog-centered">
		<div class="modal-content">

			<div class="modal-body bg-light rounded">

				<div class="d-flex justify-content-center h-100">
					<div class="row bg-light rounded">
						<!-- Modal Header -->
						<div class="modal-header d-flex justify-content-center p-3 mb-3">
							<h4 class="col-12 modal-title navbar-brand text-center">Login
								OTP</h4>
							<button type="button" class="btn-close me-5"
								data-bs-dismiss="modal"></button>
						</div>
						<div class="text-center mb-2">Please provide correct email
							to receive access code</div>
						<form class="form row mx-1" action="MemberServlet" method="post">
							<div class="row">
								<div class="form-floating mb-3">
									<input type="text" id="loginOTPEmail" name="loginOTPEmail"
										class="form-control" placeholder="Your email address" required />
									<label class="form-label ms-3" for="loginOTPEmail">Email
										address: </label>
								</div>


								<div class="modal-footer row">
									<div class="col d-flex justify-content-center">
										<div class="row d-flex justify-content-center">
											<input type="hidden" name="command" value="LOGINOTP">
											<input type="hidden" name="url" id="url">

											<button type="submit" onclick="getURL()"
												class="btn btn-primary text-center"
												style="width: fit-content">Send request</button>

										</div>
									</div>

								</div>
							</div>

						</form>

					</div>

				</div>


			</div>

		</div>
	</div>

</div>

<div class="modal fade" id="LogWithCode">
	<div
		class="modal-dialog modal-fullscreen-md-down modal-dialog-centered">
		<div class="modal-content">

			<!--Login details-->
			<div class="modal-body bg-light rounded">

				<div class="d-flex justify-content-center h-100">
					<div class="row bg-light rounded">
						<!-- Modal Header -->
						<div class="modal-header d-flex justify-content-center p-3 mb-5">
							<h4 class="col-12 modal-title navbar-brand text-center">Login
								OTP</h4>
							<button type="button" class="btn-close me-5"
								data-bs-dismiss="modal"></button>
						</div>
						<div class="text-center text-danger mb-1">You have 1 minute to
							enter access code sent to your email!</div>
				
								<div class="p-2 text-center">
									<form method="post" action="MemberServlet">
										<div id="otp"
											class="inputs d-flex flex-row justify-content-center mt-2">

											<input class="m-2 text-center form-controlOTP rounded"
												type="text" id="first" name="first" maxlength="1" /> <input
												class="m-2 text-center form-controlOTP rounded" type="text"
												id="second" name="second" maxlength="1" /> <input
												class="m-2 text-center form-controlOTP rounded" type="text"
												id="third" name="third" maxlength="1" /> <input
												class="m-2 text-center form-controlOTP rounded" type="text"
												id="fourth" name="fourth" maxlength="1" /> <input
												class="m-2 text-center form-controlOTP rounded" type="text"
												id="fifth" name="fifth" maxlength="1" /> <input
												class="m-2 text-center form-controlOTP rounded" type="text"
												id="sixth" name="sixth"maxlength="1" />

										</div>
										<input type="hidden" name="command" value="LOGINWITHCODE">
										<input type="hidden" name="loginOTPEmail" value="${sessionScope.loginOTPEmail}">
										<input type="hidden" name="url" id="url" />
										<div class="mt-4">
											<button type="submit" onclick="getURL()"
												class="btn btn-primary text-center"
												style="width: fit-content">Login</button>
										</div>
									</form>
								</div>
						


					</div>

				</div>


			</div>

		</div>
	</div>

</div>


<div class="modal fade" id="TrackOrder">
	<div
		class="modal-dialog modal-fullscreen-md-down modal-dialog-centered">
		<div class="modal-content">

			<!--Login details-->
			<div class="modal-body bg-light rounded">

				<div class="d-flex justify-content-center h-100">
					<div class="row bg-light rounded">
						<!-- Modal Header -->
						<div class="modal-header d-flex justify-content-center p-3 mb-5">
							<h4 class="col-12 modal-title navbar-brand text-center">Order Status</h4>
							<button type="button" class="btn-close me-5"
								data-bs-dismiss="modal"></button>
						</div>
						<c:if test="${sessionScope.trackingOrder==null}">
						<div class="text-center text-danger mb-1">This order does not belong to you. Please enter correct order no.!</div>
						</c:if>
						<c:if test="${sessionScope.trackingOrder!=null && sessionScope.trackingStatus == true}">
						<div class="text-center text-danger mb-1">Your order has been completed.</div>
						</c:if>
						<c:if test="${sessionScope.trackingOrder!=null && sessionScope.trackingStatus == false}">
						<div class="text-center text-danger mb-1">Your order is being processed.</div>
						</c:if>
						
						<c:if test="${sessionScope.trackingOrder!=null}">
						<c:forEach var="currency" items="${sessionScope.trackingOrderCurrencyModelList}">
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

		</div>
	</div>

</div>

