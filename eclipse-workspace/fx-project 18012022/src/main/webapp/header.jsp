<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="sticky-top">
	<header class="site-header">
		<div class="container-fluid">
			<div class="row align-items-center">

				<div class="col-lg-3 col-md-5 col-7">
					<p class="text-white mb-0">
						<i class="bi-clock site-header-icon me-2"></i> 7 days a week
						9:00-19:00
					</p>
				</div>

				<div class="col-lg-2 col-md-3 col-5">
					<p class="text-white mb-0">
						<a href="tel: 02-2222-4444" class="text-white"> <i
							class="bi-telephone site-header-icon me-2"></i> 02-2222-4444
						</a>
					</p>
				</div>

				<div class="col-lg-3 col-md-3 col-12 ms-auto">
					<ul class="social-icon">
						<li><a href="https://facebook.com"
							class="social-icon-link bi-facebook"></a></li>

						<li><a href="https://twitter.com"
							class="social-icon-link bi-twitter"></a></li>

						<li><a href="https://www.youtube.com"
							class="social-icon-link bi-youtube"></a></li>

						<li><a href="CartServlet"
							class="social-icon-link bi-cart-fill"></a></li>

						<c:if test="${sessionScope.User.firstName != null}">
							<li><a href="MemberServlet"
								class="social-icon-link bi-person-square" id="account"></a></li>
						</c:if>

					</ul>
				</div>

			</div>
		</div>
	</header>

	<nav class="navbar navbar-expand-lg bg-white shadow-lg">
		<div class="container-fluid">

			<a href="IndexServlet" class="navbar-brand">FX <span
				class="text-danger">Destination</span></a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNav">
				<c:if test="${sessionScope.User.firstName == null}">
					<form class="d-flex">
					<input class="form-control me-2" type="text" size="40"
						placeholder="Login to use this order track function" disabled>
					<button class="btn btn-warning ms-auto" type="button" disabled>
						<i class="bi bi-search"></i>
					</button>
				</form>
				</c:if>
				<c:if test="${sessionScope.User.firstName != null}">
					<form class="d-flex" action="CartServlet" method="post">
					<input class="form-control me-2" type="text" size="40" 
						id="orderToTrack" name="orderToTrack"
						placeholder="Enter your order no. to track...">
					<input type="hidden" name="command" value="TRACKORDER">
					<button class="btn btn-warning ms-auto" type="submit">
						<i class="bi bi-search"></i>
					</button>
				</form>
				</c:if>
				
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link click-scroll"
						href="ProductServlet"><small class="small-title"><strong
								class="text-warning">01</strong></small> Buy</a></li>

					<li class="nav-item"><a class="nav-link click-scroll"
						href="ToolsServlet"><small class="small-title"><strong
								class="text-warning">02</strong></small> Tools</a></li>

					<li class="nav-item"><a class="nav-link click-scroll"
						href="LocationServlet"><small class="small-title"><strong
								class="text-warning">03</strong></small> Locations</a></li>

					<li class="nav-item"><a class="nav-link click-scroll"
						href="IndexServlet#contact"><small class="small-title"><strong
								class="text-warning">04</strong></small> Contact</a></li>

					<li class="nav-item"><c:if
							test="${sessionScope.User.firstName == null}">
							<a class="nav-link click-scroll" href="#Login"
								data-bs-toggle="modal" data-bs-target="#Login"><small
								class="small-title"><strong class="text-warning">05</strong></small>
								Login</a>
						</c:if> <c:if test="${sessionScope.User.firstName != null}">
							<a class="nav-link click-scroll" href="#Logout"
								data-bs-toggle="modal" data-bs-target="#Logout"><small
								class="small-title"><strong class="text-warning">05</strong></small>
								Logout</a>

						</c:if></li>
				</ul>
			</div>

		</div>
	</nav>
</div>