<section class="hero">
	<div class="container-fluid h-100">
		<div class="row h-100">

			<div id="carouselExampleCaptions"
				class="carousel carousel-fade hero-carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<div class="container position-relative h-100">
							<div
								class="carousel-caption d-flex flex-column justify-content-center">
								<small class="small-title"> FX Destination <strong
									class="text-warning"> 24/7</strong></small>

								<h1>
									Buy FX in Australia <span class="text-warning"> with the best
										rate</span>
								</h1>

								<div class="d-flex align-items-center mt-4">
									<a class="custom-btn btn custom-link" href="ProductServlet">Get
										Started</a> <a class="popup-youtube custom-icon d-flex ms-4"
										href="https://www.youtube.com"> <i
										class="bi-play play-icon d-flex m-auto text-white"></i>
									</a>
								</div>
							</div>
						</div>

						<div class="carousel-image-wrap">
							<img src="http://localhost:8080/fx-project_18012022/img/background4.jpg"
								class="img-fluid carousel-image" alt="">
						</div>
					</div>

					<div class="carousel-item">
						<div class="container position-relative h-100">
							<div
								class="carousel-caption d-flex flex-column justify-content-center">
								<small class="small-title"> Expert yourself <strong
									class="text-warning"></strong></small>

								<h1>
									Be smart buyer with <span class="text-warning">
										intelligent FX tools</span>
								</h1>

								<div class="d-flex align-items-center mt-4">
									<a class="custom-btn btn custom-link" href="ToolsServlet">Explore
										FX tools</a> <a class="popup-youtube custom-icon d-flex ms-4"
										href="https://www.youtube.com"> <i
										class="bi-play play-icon d-flex m-auto text-white"></i>
									</a>
								</div>
							</div>
						</div>

						<div class="carousel-image-wrap">
							<img src="http://localhost:8080/fx-project_18012022/img/background2.jpg"
								class="img-fluid carousel-image" alt="">
						</div>
					</div>
					
					<div class="carousel-item">
						<div class="container position-relative h-100">
							<div
								class="carousel-caption d-flex flex-column justify-content-center">
								<small class="small-title"> Enjoy <strong
									class="text-warning"> membership benefits</strong></small>

								<h1>
									Get <span class="text-warning">even better rate
										</span> with membership<span class="text-warning">**</span>
								</h1>

								<div class="d-flex align-items-center mt-4">
									<a class="custom-btn btn custom-link" href="#Register"
								data-bs-toggle="modal" data-bs-target="#Register">Join us now
										</a> <a class="popup-youtube custom-icon d-flex ms-4"
										href="https://www.youtube.com"> <i
										class="bi-play play-icon d-flex m-auto text-white"></i>
									</a>
								</div>
							</div>
						</div>

						<div class="carousel-image-wrap">
							<img src="http://localhost:8080/fx-project_18012022/img/background3.jpg"
								class="img-fluid carousel-image" alt="">
						</div>
					</div>
					
					<div class="carousel-item">
						<div class="container position-relative h-100">
							<div
								class="carousel-caption d-flex flex-column justify-content-center">
								<small class="small-title"> Guaranteed rate <strong
									class="text-warning"> in 6 hours</strong></small>

								<h1>
									Just buy now and <span class="text-warning"> covered in 6 hours</span> 								</h1>

								<div class="d-flex align-items-center mt-4">
									<a class="custom-btn btn custom-link" href="#Register"
								data-bs-toggle="modal" data-bs-target="#Register">Join us now
										</a> <a class="popup-youtube custom-icon d-flex ms-4"
										href="https://www.youtube.com"> <i
										class="bi-play play-icon d-flex m-auto text-white"></i>
									</a>
								</div>
							</div>
						</div>

						<div class="carousel-image-wrap">
							<img src="http://localhost:8080/fx-project_18012022/img/background1.jpg"
								class="img-fluid carousel-image" alt="">
						</div>
					</div>

					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>

					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>

		</div>
	</div>
</section>
<section class="contact" id="contact">

	<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320">
		<path fill="#f9c10b" fill-opacity="1"
			d="M0,288L48,272C96,256,192,224,288,197.3C384,171,480,149,576,165.3C672,181,768,235,864,250.7C960,267,1056,245,1152,250.7C1248,256,1344,288,1392,304L1440,320L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>

	<div class="contact-container-wrap">
		<div class="container">
			<div class="row">

				<div class="col-lg-6 col-12">
					<h5 class="text-primary"> ${notification}</h5>
					<form class="custom-form contact-form" action="IndexServlet" method="post"
						role="form">
						<small class="small-title">Contact <strong
							class="text-white">04/05</strong></small>

						<h2 class="mb-5">Message for Us</h2>

						<div class="row">
							<div class="col-lg-6 col-md-6 col-12">
								<input type="text" name="name" id="name" class="form-control"
									placeholder="Your Name" required>
							</div>

							<div class="col-lg-6 col-md-6 col-12">
								<input type="email" name="email" id="email"
									pattern="[^ @]*@[^ @]*" class="form-control"
									placeholder="your@company.com" required>
							</div>

							<div class="col-12">
								<textarea class="form-control" rows="7" id="message"
									name="message" placeholder="Message" required></textarea>

								<button type="submit" class="form-control">Submit</button>
							</div>

						</div>
					</form>
				</div>

				<div class="col-lg-6 col-12">
					<div class="contact-thumb">

						<div class="contact-info bg-white shadow-lg">
							<h4 class="mb-4">56A John Street, Cabramatta</h4>

							<h4 class="mb-2">
								<a href="tel: 240-480-9600"> <i
									class="bi-telephone contact-icon me-2"></i> 240-480-9600
								</a>
							</h4>

							<h5>
								<a href="mailto:info@fxdestination.com" class="footer-link"> <i
									class="bi-envelope-fill contact-icon me-2"></i>
									info@fxdestination.com
								</a>
							</h5>

							<!-- Copy "embed a map" HTML code from any point on Google Maps -> Share Link  -->

							<div style="width: 100%">
							<iframe class="google-map mt-4" width="100%" height="300" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=56A%20John%20Street,%20Cabramatta,%20NSW%20Australia+(FX%20Destination)&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed"><a href="https://www.maps.ie/distance-area-calculator.html">distance maps</a></iframe></div>
							

						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</section>