function getURL() {
	document.getElementById('url').value = document.URL;
}
function showLevel(x) {
	if (x == 0) {
		document.getElementById('levelMember').innerHTML = 'You are at entry level. Buy up to 10,000 AUD to reach Level 1!';
	}
	if (x == 1) {
		document.getElementById('levelMember').innerHTML = 'Your level: &nbsp; <i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star"></i><i class="bi bi-star"></i><i class="bi bi-star"></i><i class="bi bi-star"></i> &nbsp; Buy up to 20,000 AUD to reach Level 2!';
	}
	if (x == 2) {
		document.getElementById('levelMember').innerHTML = 'Your level: &nbsp; <i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star"></i><i class="bi bi-star"></i><i class="bi bi-star"></i>&nbsp; Buy up to 50,000 AUD to reach Level 3!';
	}
	if (x == 3) {
		document.getElementById('levelMember').innerHTML = 'Your level: &nbsp; <i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star"></i><i class="bi bi-star"></i> &nbsp; Buy up to 100,000 AUD to reach Level 4!';
	}
	if (x == 4) {
		document.getElementById('levelMember').innerHTML = 'Your level: &nbsp;<i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star"></i> &nbsp; Buy up to 200,000 AUD to reach Level 5!';
	}
	if (x == 5) {
		document.getElementById('levelMember').innerHTML = 'Congratulation! You reached the max level: &nbsp;<i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star-fill" style="color:gold"></i><i class="bi bi-star-fill" style="color:gold"></i>';
	}
}


$(document).ready(
	function() {
		
		document.getElementById('url').value = document.URL;
		
		
		
		if (window.location.href.indexOf('#Login') == -1) {
			document.getElementById('errorLogin').innerHTML = "";
		}
		if (window.location.href.indexOf('#Register') == -1) {
			document.getElementById('errorRegister').innerHTML = "";
		}

		if (window.location.href.indexOf('#Login') != -1
			&& document.getElementById("account") == null) {
			$('#Login').modal('show');
		}
		if (window.location.href.indexOf('#LogWithCode') != -1
			&& document.getElementById("account") == null) {
			$('#LogWithCode').modal('show');
		}
		if (window.location.href.indexOf('#LogOTP') != -1
			&& document.getElementById("account") == null) {
			$('#LogOTP').modal('show');
		}
		if (window.location.href.indexOf('#Update') != -1) {
			$('#Update').modal('show');
		}
		if (window.location.href.indexOf('#InvalidToken') != -1) {
			$('#InvalidToken').modal('show');
		}
		if (window.location.href.indexOf('#ValidToken') != -1) {
			$('#ValidToken').modal('show');
		}
		if (window.location.href.indexOf('#ForgotNotification') != -1) {
			$('#ForgotNotification').modal('show');
		}
		if (window.location.href.indexOf('#Register') != -1
			&& document.getElementById("account") == null) {
			$('#Register').modal('show');
		}
		if (window.location.href.indexOf('#TrackOrder') != -1
			) {
			$('#TrackOrder').modal('show');
		}
		if (document.getElementById('levelMemberFromSession') != null) {
			showLevel(document.getElementById('levelMemberFromSession').innerHTML);
		}
	});
	
function getRate() {
	var FXCode = document.getElementById("firstCompare").value;
	document.getElementById("rateFEFX").innerHTML = '<button class="btn btn-primary"><span class="spinner-border spinner-border-sm"></span> Loading..</button>';
	document.getElementById("rateTravelex").innerHTML = '<button class="btn btn-success"><span class="spinner-border spinner-border-sm"></span> Loading..</button>';
	document.getElementById("rateXE").innerHTML = '<button class="btn btn-danger"><span class="spinner-border spinner-border-sm"></span> Loading..</button>';
	
	var URLToGetRateFromFEFX = "https://fefx-api.fefx.com.au/api/feol/rate/sell/"+FXCode;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var currency = JSON.parse(this.responseText);
			document.getElementById("rateFEFX").innerHTML = "FEFX Australia: 1 AUD = " + (Number(currency.rate)).toFixed(5).replace(/\d(?=(\d{3})+\.)/g, '$&,') + " " + FXCode;
		}
	};
	xhttp.open("GET", URLToGetRateFromFEFX, true);
	xhttp.send();
	
	var xhttpXE = new XMLHttpRequest();
	xhttpXE.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var output = this.responseText;
			var rateXE = output.substring(7, output.indexOf("&"));
			var rateTravelex = output.substring(output.indexOf("&rateTravelex=") + 14);
			document.getElementById("rateTravelex").innerHTML = "Travelex Australia: 1 AUD = " + rateTravelex + " " + FXCode;
			document.getElementById("rateXE").innerHTML = "XE Worldwide: 1 AUD = " + rateXE + " " + FXCode;

		}
	};
	xhttpXE.open("POST", "ToolsServlet", true);
	xhttpXE.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	var postContent = "codeXE=" + FXCode;
	xhttpXE.send(postContent);
	
}

function firstGraphSelected() {
	$("#one_month").removeClass('d-none');
	$("#six_months").removeClass('d-none');
	$("#one_year").removeClass('d-none');
	$("#ytd").removeClass('d-none');
	$("#all").removeClass('d-none');

	one_year
	var dataGraph = "";
	var codeGraph = document.getElementById("firstGraph").value;
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			dataGraph = this.responseText;
		}
		;

	};

	xhttp.open("POST", "ToolsServlet", false);
	xhttp.setRequestHeader("Content-type",
		"application/x-www-form-urlencoded");
	var postContent = "firstGraph=" + codeGraph;
	xhttp.send(postContent);

	var optionString = '{"series":[{"data":'
		+ dataGraph
		+ '}],"chart":{"id":"area-datetime","type":"area","height":350,"zoom":{"autoScaleYaxis":true}},"dataLabels":{"enabled":false},"markers":{"size":0,"style":"hollow"},"xaxis":{"type":"datetime","min":1672491600000,"tickAmount":6},"tooltip":{"x":{"format":"dd MMM yyyy"}},"fill":{"type":"gradient","gradient":{"shadeIntensity":1,"opacityFrom":0.7,"opacityTo":0.9,"stops":[0,100]}}}';
	var options = JSON.parse(optionString);

	var chart = new ApexCharts(document.querySelector("#chart-timeline"),
		options);
	chart.render();
	var currentDate = new Date();
	currentDate.setMonth(currentDate.getMonth() - 1);
	var currentDate1 = new Date();
	chart.zoomX(currentDate.getTime(), currentDate1.getTime())

	var resetCssClasses = function(activeEl) {
		var els = document.querySelectorAll('button')
		Array.prototype.forEach.call(els, function(el) {
			el.classList.remove('active')
		})

		activeEl.target.classList.add('active')
	}

	document.querySelector('#one_month').addEventListener('click',
		function(e) {
			resetCssClasses(e)
			var currentDate = new Date();
			currentDate.setMonth(currentDate.getMonth() - 1);
			var currentDate1 = new Date();
			chart.zoomX(currentDate.getTime(), currentDate1.getTime())
		})

	document.querySelector('#six_months').addEventListener('click',
		function(e) {
			resetCssClasses(e)
			var currentDate = new Date();
			currentDate.setMonth(currentDate.getMonth() - 6);
			var currentDate1 = new Date();
			chart.zoomX(currentDate.getTime(), currentDate1.getTime())
		})

	document.querySelector('#one_year').addEventListener('click',
		function(e) {
			resetCssClasses(e)
			var currentDate = new Date();
			currentDate.setFullYear(currentDate.getFullYear() - 1);
			var currentDate1 = new Date();
			chart.zoomX(currentDate.getTime(), currentDate1.getTime())
		})

	document.querySelector('#ytd').addEventListener('click', function(e) {
		resetCssClasses(e)
		var currentDate = new Date();
		currentDate.setMonth(0);
		currentDate.setDate(1);
		var currentDate1 = new Date();
		chart.zoomX(currentDate.getTime(), currentDate1.getTime())
	})

	document.querySelector('#all').addEventListener(
		'click',
		function(e) {
			resetCssClasses(e)
			chart.zoomX(new Date('01 Jan 2023').getTime(), new Date()
				.getTime())
		})
}
