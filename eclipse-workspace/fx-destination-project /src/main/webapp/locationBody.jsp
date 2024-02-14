<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<section class="services section-padding" id="Location">

	<div class="container-fluid px-5">
		<h3>Our offices in Australia</h3>
		<div class="row">
			<div class="col-6">
				<c:forEach var="branch" items="${branchList}">
					<div class="mx-5 mb-5 pb-5">
						<table class="table table-striped table-light table-hover">
							<thead>
								<tr>
									<th colspan="3"><h6 class="text-primary">${branch.name}</h6></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Address:</td>
									<td colspan="2">${branch.address},${branch.state},
										${branch.city}</td>
								</tr>
								<tr>
									<td>Contact:</td>
									<td colspan="2">${branch.phoneNumber}</td>
								</tr>
								<tr>
									<td>Opening hour:</td>
									<td colspan="2">${branch.tradingHour}</td>
								</tr>
								<tr>
									<td></td>
									<td colspan="2">${branch.note}</td>

								</tr>
							</tbody>
						</table>

					</div>
					<hr>
				</c:forEach>

			</div>

			<div class="col-6">
				<div class="mb-1" style="width: 100%">
					<iframe width="100%" height="300" frameborder="0" scrolling="no"
						marginheight="0" marginwidth="0"
						src="https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=56A%20John%20Street,%20Cabramatta,%20NSW%202166,%20Australia+(FX%20Destination)&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed">
						<a href="https://www.maps.ie/distance-area-calculator.html">measure
							area map</a>
					</iframe>
				</div>
				<hr>
				<div style="width: 100%">
					<iframe width="100%" height="300" frameborder="0" scrolling="no"
						marginheight="0" marginwidth="0"
						src="https://maps.google.com/maps?width=100%25&amp;height=300&amp;hl=en&amp;q=Shop%201,%20238%20Canley%20Vale%20Rd,%20NSW,%20Canley%20Heights+(FX%20Destination)&amp;t=&amp;z=15&amp;ie=UTF8&amp;iwloc=B&amp;output=embed">
						<a href="https://www.maps.ie/distance-area-calculator.html">measure
							area map</a>
					</iframe>
				</div>
			</div>

		</div>
		<!--The div element for the map -->
		<div id="map"></div>
	</div>
</section>