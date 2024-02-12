<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal fade" id="AddToCart">
	<div
		class="modal-dialog modal-fullscreen-md-down modal-dialog-centered">
		<div class="modal-content">

			<!--Product details-->
			<div class="modal-body bg-light rounded">

				<div class="d-flex justify-content-center h-100">
					<div class="row bg-light rounded">

						<form class="row mx-1" action="CartServlet" method="post">
							<div class="mb-1 row">
								<div class="col">
									<input type="text"
										class="form-control-plaintext navbar-brand text-center"
										id="FXConfirmation" disabled>
								</div>
							</div>
							<hr>
							<div class="mb-1 row">

								<div class="col" id="tableDeno"></div>
							</div>
							<hr>
							<div class="row d-flex">
								<label for="FXAmountConfirmation"
									class="col-7 d-flex col-form-label text-center">Total
									amount to buy: </label>
								<div class="col-3 d-flex text-end">
									<input type="text"
										class="form-control-plaintext text-primary text-end"
										name="FXAmount" id="FXAmountConfirmation" readonly>
								</div>
								<div class="col-2 d-flex text-end">
									<input type="text" class="form-control-plaintext text-primary"
										id="FXCodeConfirmation" name="code" readonly> <input
										type="hidden" id="command" name="command" value="ADD_TO_CART">
								</div>


							</div>

							<div class="d-flex flex-row justify-content-center">
								<input type="text"
									class="form-control-plaintext text-danger text-end" id="temp"
									value="Rate: " disabled> <input type="text"
									class="form-control-plaintext text-danger text-start"
									name="rate" id="RateConfirmation" readonly>

							</div>

							<div class="row d-flex">
								<label for="AUDAmountConfirmation"
									class="d-flex col-7 col-form-label text-center">Equivalent
									amount to pay: </label>
								<div class="d-flex col-3 text-end mb-1">
									<input type="text"
										class="form-control-plaintext text-primary text-end"
										name="AUDAmount" id="AUDAmountConfirmation" readonly>
								</div>
								<div class="d-flex col-2 col-form-label text-end text-primary">AUD</div>
							</div>

							<div class="modal-footer row">
								<div class="row">
									<div class="col-6 d-flex justify-content-center">
										<div class="row d-flex justify-content-center">
											<button type="button" class="btn btn-secondary text-center"
												data-bs-dismiss="modal" style="width: fit-content">Edit
											</button>

										</div>
									</div>
									<div class="col-6 d-flex justify-content-center">
										<div class="row d-flex justify-content-center">
											<button type="submit" class="btn btn-warning btn"
												style="width: 80px">Confirm</button>
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