<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<section class="services section-padding" id="Tools">
	<div class="container">
		<div class="row">

			<div class="col-lg-10 col-12 text-center mx-auto mb-5">
				<small class="small-title">Tools <strong
					class="text-warning">02/05</strong></small>

				<h2>How can we help you?</h2>

			</div>

			<div class="col-lg-6 col-12">
				<nav>
					<div class="nav nav-tabs flex-column align-items-baseline"
						id="nav-tab" role="tablist">
						<c:if test="${Message == null}">
							<button class="nav-link active" id="nav-business-tab"
								data-bs-toggle="tab" data-bs-target="#nav-business"
								type="button" role="tab" aria-controls="nav-business"
								aria-selected="false">
								<h3>Graph History</h3>

								<span>Great way to decide when you should buy </span>
							</button>
							<button class="nav-link" id="nav-strategy-tab"
								data-bs-toggle="tab" data-bs-target="#nav-strategy"
								type="button" role="tab" aria-controls="nav-strategy"
								aria-selected="true">
								<h3>Rate Tracker</h3>

								<span>Just let us know which rate you are waiting for, we
									will email you once it reaches your target </span>
							</button>
						</c:if>
						<c:if test="${Message != null}">
							<button class="nav-link" id="nav-business-tab"
								data-bs-toggle="tab" data-bs-target="#nav-business"
								type="button" role="tab" aria-controls="nav-business"
								aria-selected="false">
								<h3>Graph History</h3>

								<span>Great way to decide when you should buy </span>
							</button>
							<button class="nav-link active" id="nav-strategy-tab"
								data-bs-toggle="tab" data-bs-target="#nav-strategy"
								type="button" role="tab" aria-controls="nav-strategy"
								aria-selected="true">
								<h3>Rate Tracker</h3>

								<span>Just let us know which rate you are waiting for, we
									will email you once it reaches your target </span>
							</button>
						</c:if>

						<button class="nav-link" id="nav-video-tab" data-bs-toggle="tab"
							data-bs-target="#nav-video" type="button" role="tab"
							aria-controls="nav-video" aria-selected="false">
							<h3>Market Analysis</h3>

							<span>Current market rates provided in Australia and in
								the World</span>
						</button>
					</div>
				</nav>
			</div>

			<div class="col-lg-6 col-12">
				<div class="tab-content" id="nav-tabContent">
					<c:if test="${Message == null}">
						<div class="tab-pane fade show active" id="nav-business"
							role="tabpanel" aria-labelledby="nav-business-tab">

							<h5 class="mt-4 mb-2">Rate Monitoring</h5>


							<select id="firstGraph" class="form-select form-control"
								name="firstGraph" onchange="firstGraphSelected()">
								<option selected disabled value="">Select currency to
									view</option>
								<c:forEach var="currency" items="${currencyList}">
									<option value="${currency.code}">${currency.code}-
										${currency.name}</option>
								</c:forEach>
							</select>
							<div class="my-3" id="chart">
								<div class="toolbar">
									<button id="one_month" class="d-none active">1M</button>
									<button id="six_months" class="d-none">6M</button>
									<button id="one_year" class="d-none">1Y</button>
									<button id="ytd" class="d-none">YTD</button>
									<button id="all" class="d-none">All</button>
								</div>
								<div id="chart-timeline" style="min-height: 365px;"></div>
							</div>

						</div>

						<div class="tab-pane fade show" id="nav-strategy" role="tabpanel"
							aria-labelledby="nav-strategy-tab">

							<h5 class="mt-3 mb-3">Rate tracker tool:</h5>
							<form action="ToolsServlet" method="get">
								<select id="firstTrack" class="form-select form-control"
									name="firstTrack">
									<option selected disabled value="">Select currency to
										track</option>
									<c:forEach var="currency" items="${currencyList}">
										<option value="${currency.code}">${currency.code}-
											${currency.name}</option>
									</c:forEach>
								</select>
								<div class="form-floating my-3">
									<input type="number" step="0.00001" id="targetRate"
										name="targetRate" class="form-control"
										placeholder="Thi Huong Lan" required /> <label
										class="form-label" for="targetRate">Target Rate: </label>
								</div>
								<br>
								<div class="text-danger">${Message}</div>
								<br>
								<button type="submit" class="btn btn-warning btn-lg ms-2">Track</button>
							</form>

						</div>
					</c:if>

					<c:if test="${Message != null}">
						<div class="tab-pane fade show" id="nav-business" role="tabpanel"
							aria-labelledby="nav-business-tab">

							<h5 class="mt-4 mb-2">Rate Monitoring</h5>


							<select id="firstGraph" class="form-select form-control"
								name="firstGraph" onchange="firstGraphSelected()">
								<option selected disabled value="">Select currency to
									view</option>
								<c:forEach var="currency" items="${currencyList}">
									<option value="${currency.code}">${currency.code}-
										${currency.name}</option>
								</c:forEach>
							</select>
							<div class="my-3" id="chart">
								<div class="toolbar">
									<button id="one_month" class="d-none active">1M</button>
									<button id="six_months" class="d-none">6M</button>
									<button id="one_year" class="d-none">1Y</button>
									<button id="ytd" class="d-none">YTD</button>
									<button id="all" class="d-none">All</button>
								</div>
								<div id="chart-timeline" style="min-height: 365px;"></div>
							</div>

						</div>

						<div class="tab-pane fade show active" id="nav-strategy"
							role="tabpanel" aria-labelledby="nav-strategy-tab">

							<h5 class="mt-3 mb-3">Rate tracker tool:</h5>
							<form action="ToolsServlet" method="get">
									<select id="firstTrack" class="form-select form-control"
										name="firstTrack">
										<option selected disabled value="">Select currency to
											track</option>
										<c:forEach var="currency" items="${currencyList}">
											<option value="${currency.code}">${currency.code}-
												${currency.name}</option>
										</c:forEach>
									</select>
								<div class="form-floating my-3">
									<input type="number" step="0.00001" id="targetRate"
										name="targetRate" class="form-control"
										placeholder="Thi Huong Lan" required /> <label
										class="form-label" for="targetRate">Target Rate: </label>
								</div>
								<br>
								<div class="text-danger">${Message}</div>
								<br>
								<button type="submit" class="btn btn-warning btn-lg ms-2">Track</button>
							</form>

						</div>
					</c:if>

					<div class="tab-pane fade show" id="nav-video" role="tabpanel"
						aria-labelledby="nav-video-tab">

						<h5 class="mt-4 mb-2">Market analysis</h5>
						<select id="firstCompare" class="form-select form-control"
							name="firstCompare" onchange="getRate()">
							<option selected disabled value="">Select currency to
								compare rate</option>
							<c:forEach var="currency" items="${currencyList}">
								<option value="${currency.code}">${currency.code}-
									${currency.name}</option>
							</c:forEach>
						</select>
						<hr>
						<div id="resultCompare">
							<div class="text-primary my-3" id="rateFEFX"></div>

							<div class="text-success my-3" id="rateTravelex"></div>

							<div class="text-danger my-3" id="rateXE"></div>

						</div>

					</div>


				</div>
			</div>

		</div>
	</div>
</section>
