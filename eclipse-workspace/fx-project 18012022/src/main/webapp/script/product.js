const fxs = [
      {
      "code": "USD",
	  "title": "Wonderful America",
      "location": ["Washington","New York","San Francisco","The Grand Canyon","Houston","Las Vegas"],
      },

      {
      "code": "EUR",
	  "title": "Classical Europe",
      "location": ["Eiffel Tower, France","Colosseum, Italy","Acropolis of Athens, Greece","Louvre Museum, France","Prague Castle, Czech Republic","Berlin's Brandenburg Gate, Germany","The Matterhorn, Switzerland","Amsterdam's Canals, The Netherlands","Sistine Chapel, Vatican City","Neuschwanstein Castle, Germany"],
      },

      {
      "code": "GBP",
	  "title": "Premier England",
      "location": ["Stonehenge","Tower of London","The Roman Baths and Georgian City of Bath","The British Museum","York Minster and Historic Yorkshire"],
      },

	 {
      "code": "VND",
	  "title": "Amazing Vietnam",
      "location": ["Ha Long Bay","Sai Gon","Hue","Phong Nha Cave","My Son","Hoi An","SaPa", "MeKong Delta","Cat Ba Island"],
      },

	 {
      "code": "JPY",
	  "title": "Snow Japan",
      "location": ["Temple City: Historic Nara","Mount Fuji","Imperial Tokyo","Hiroshima Peace Memorial Park","Historic Kyoto","The Island Shrine of Itsukushima, Miyajima"],
      },
      ];
      var firstFX;

var defaultCarousel ='<div class="carousel-item active"><div class="container position-relative h-100"><div class="carousel-caption d-flex flex-column justify-content-center"><small class="small-title">Explore worldwide <strong class="text-warning" id="firstCarousel">Hoi An - Vietnam</strong></small><div class="d-flex align-items-center mt-4"><a class="custom-btn btn custom-link" href="ProductServlet">Get Started</a> <a class="popup-youtube custom-icon d-flex ms-4" href="https://www.youtube.com"> <i class="bi-play play-icon d-flex m-auto text-white"></i></a></div></div></div><div class="carousel-image-wrap"><img src="http://localhost:8080/fx-project_18012022/img/VND/5.jpg" class="img-fluid carousel-image" alt=""></div></div>';
	defaultCarousel += '<div class="carousel-item"><div class="container position-relative h-100"><div class="carousel-caption d-flex flex-column justify-content-center"><small class="small-title"> <strong class="text-warning">New York - US</strong></small></div></div><div class="carousel-image-wrap"><img src="http://localhost:8080/fx-project_18012022/img/USD/1.jpg" class="img-fluid carousel-image" alt=""></div></div>';
	defaultCarousel += '<div class="carousel-item"><div class="container position-relative h-100"><div class="carousel-caption d-flex flex-column justify-content-center"><small class="small-title"> <strong class="text-warning">Eiffel Tower - France</strong></small></div></div><div class="carousel-image-wrap"><img src="http://localhost:8080/fx-project_18012022/img/EUR/0.jpg" class="img-fluid carousel-image" alt=""></div></div>';
	defaultCarousel += '<div class="carousel-item"><div class="container position-relative h-100"><div class="carousel-caption d-flex flex-column justify-content-center"><small class="small-title"> <strong class="text-warning">Mount Fuji - Japan</strong></small></div></div><div class="carousel-image-wrap"><img src="http://localhost:8080/fx-project_18012022/img/JPY/1.jpg" class="img-fluid carousel-image" alt=""></div></div>';
	defaultCarousel += '<div class="carousel-item"><div class="container position-relative h-100"><div class="carousel-caption d-flex flex-column justify-content-center"><small class="small-title"> <strong class="text-warning">Stonehenge - England</strong></small></div></div><div class="carousel-image-wrap"><img src="http://localhost:8080/fx-project_18012022/img/GBP/0.jpg" class="img-fluid carousel-image" alt=""></div></div>';
	defaultCarousel += '<div class="carousel-item"><div class="container position-relative h-100"><div class="carousel-caption d-flex flex-column justify-content-center"><small class="small-title"> <strong class="text-warning">Acropolis of Athens - Greece</strong></small></div></div><div class="carousel-image-wrap"><img src="http://localhost:8080/fx-project_18012022/img/EUR/2.jpg" class="img-fluid carousel-image" alt=""></div></div>';
	defaultCarousel += '<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev"><span class="carousel-control-prev-icon" aria-hidden="true"></span><span class="visually-hidden">Previous</span></button><button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next"><span class="carousel-control-next-icon" aria-hidden="true"></span><span class="visually-hidden">Next</span></button>';

if (document.getElementById("carousel")!=null) {
	document.getElementById("carousel").innerHTML = defaultCarousel;
}
	
function showBtnDeno() {
	$("#btnDenoDetails").removeClass('d-none');
}

function showFirstDeno() {
	if ($("#firstTable").hasClass('d-none')) {
		$("#firstTable").removeClass('d-none');
	}
	else {
		$("#firstTable").addClass('d-none');
	}
}

function formatCurrency(amount, FXCode) {
	return Intl.NumberFormat({
		style: 'currency',
		FXCode
	}).format(amount);
}
function discountRate(rate, levelDiscount) {
	return 100*rate/(100-levelDiscount)
}

function firstSelected() {
	
	var levelDiscount = Number(document.getElementById("levelDiscount").textContent);
	var testFX = document.getElementById("firstSelect").value;
	if (testFX != "") {
			document.getElementById("carousel").innerHTML = "";
		}    
	firstFX = document.getElementById("firstSelect");
	let fx = fxs.find(fx => fx.code === firstFX.value);
	let locations = fx.location;
	let title = fx.title;
	var selectedCarousel = '<div class="carousel-item active"><div class="container position-relative h-100"><div class="carousel-caption d-flex flex-column justify-content-center"><small class="small-title">Explore '+title+'<strong class="text-warning" id="firstCarousel">'+locations[0]+'</strong></small><div class="d-flex align-items-center mt-4"><a class="custom-btn btn custom-link" href="ProductServlet">Get Started</a> <a class="popup-youtube custom-icon d-flex ms-4" href="https://www.youtube.com"> <i class="bi-play play-icon d-flex m-auto text-white"></i></a></div></div></div><div class="carousel-image-wrap"><img src="http://localhost:8080/fx-project_18012022/img/'+fx.code+'/0.jpg" class="img-fluid carousel-image" alt=""></div></div>';
	for (let i = 1; i < locations.length; i++) {
		selectedCarousel += '<div class="carousel-item"><div class="container position-relative h-100"><div class="carousel-caption d-flex flex-column justify-content-center"><small class="small-title"><strong class="text-warning">'+locations[i]+'</strong></small></div></div><div class="carousel-image-wrap"><img src="http://localhost:8080/fx-project_18012022/img/'+fx.code+'/'+i+'.jpg" class="img-fluid carousel-image" alt=""></div></div>';
        }
	selectedCarousel += '<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev"><span class="carousel-control-prev-icon" aria-hidden="true"></span><span class="visually-hidden">Previous</span></button><button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next"><span class="carousel-control-next-icon" aria-hidden="true"></span><span class="visually-hidden">Next</span></button>';
	if (document.getElementById("carousel")!=null) {
			document.getElementById("carousel").innerHTML = selectedCarousel;

	}
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var test = this.responseText;
			document.getElementById("selectedCode").innerHTML = test.substring(5, test.indexOf("name="));
			document.getElementById("selectedName").innerHTML = test.substring(test.indexOf("name=") + 5, test.indexOf("notes="));
			document.getElementById("selectedNotes").innerHTML = test.substring(test.indexOf("notes=") + 6, test.indexOf("rate="));
			document.getElementById("selectedRate").innerHTML = test.substring(test.indexOf("rate=") + 5);
		}
	};
	xhttp.open("POST", "ProductServlet", false);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	var postContent = "code=" + firstFX.value;
	xhttp.send(postContent);

	var code = document.getElementById("selectedCode").textContent;
	var name = document.getElementById("selectedName").textContent;
	var notesString = document.getElementById("selectedNotes").textContent;
	var notes = [];
	let item = 0;

	while (notesString.length != notesString.replace(" ", "").length) {
		notes[item] = parseInt(notesString.substring(0, notesString.indexOf(" ")));
		notesString = notesString.substring(1 + notesString.indexOf(" "));
		item = item + 1;
	}
	var rate = document.getElementById("selectedRate").textContent;
	document.getElementById("discountedRate").innerHTML = discountRate(rate, levelDiscount);
	var firstForm = "";
	for (let i = 0; i < notes.length; i++) {
		firstForm += "<div id='firstDeno' class='col-5 form-floating mx-2 my-2'> <input type='number' id='" + code + notes[i] + "' onchange='firstFXAmount()' class='form-control' placeholder='Notes' value='0'/><label class='form-label' for='" + notes[i] + "'>" + notes[i] + " " + code + ":</label></div>";
	}

	document.getElementById('firstTable').innerHTML = firstForm;
	document.getElementById('firstCode').innerHTML = code;
	if (levelDiscount > 0) {
		document.getElementById('firstRate').innerHTML = "1 AUD = <s>" + rate + '</s> <span class="text-danger">' + discountRate(rate, levelDiscount).toFixed(5) + "</span> " + code;

	} else {
		document.getElementById('firstRate').innerHTML = '1 AUD = <span class="text-danger">' + rate +  "</span> " + code;

	}
}

function firstFXAmount() {
	
	var code = document.getElementById("selectedCode").textContent;
	var name = document.getElementById("selectedName").textContent;
	var notesString = document.getElementById("selectedNotes").textContent;
	var notes = [];
	let item = 0;

	while (notesString.length != notesString.replace(" ", "").length) {
		notes[item] = parseInt(notesString.substring(0, notesString.indexOf(" ")));
		notesString = notesString.substring(1 + notesString.indexOf(" "));
		item = item + 1;
	}
	var rate = document.getElementById("discountedRate").textContent;
	var totalAmount = 0;
	for (let i = 0; i < notes.length; i++) {
		totalAmount += parseInt(document.getElementById(code + notes[i]).value) * parseInt(notes[i]);
	}
	if (totalAmount > 0) {
		$("#btnAddToCart").removeClass('d-none');
	} else {
		$("#btnAddToCart").addClass('d-none');
	}
	document.getElementById('total_fx1').value = totalAmount;
	document.getElementById('total_aud1').value = (totalAmount / rate).toFixed(2);
}

function FXAmountToEachNote(FXAmount) {
	var code = document.getElementById("selectedCode").textContent;
	var name = document.getElementById("selectedName").textContent;
	var notesString = document.getElementById("selectedNotes").textContent;
	var notes = [];
	let item = 0;

	while (notesString.length != notesString.replace(" ", "").length) {
		notes[item] = parseInt(notesString.substring(0, notesString.indexOf(" ")));
		notesString = notesString.substring(1 + notesString.indexOf(" "));
		item = item + 1;
	}
	var rate = document.getElementById("discountedRate").textContent;
	let numberOfNote = notes.length;
	let result = new Array(numberOfNote);
	for (let i = result.length - 1; i >= 0; i--) {
		result[i] = parseInt(FXAmount / notes[i]);
		FXAmount = FXAmount - result[i] * notes[i];
	}
	return result;
}

function FXAmountEntered() {
	var code = document.getElementById("selectedCode").textContent;
	var name = document.getElementById("selectedName").textContent;
	var notesString = document.getElementById("selectedNotes").textContent;
	var notes = [];
	let item = 0;

	while (notesString.length != notesString.replace(" ", "").length) {
		notes[item] = parseInt(notesString.substring(0, notesString.indexOf(" ")));
		notesString = notesString.substring(1 + notesString.indexOf(" "));
		item = item + 1;
	}
	var rate = document.getElementById("discountedRate").textContent;
	var FXAmount = parseInt(document.getElementById('total_fx1').value);
	let result = FXAmountToEachNote(FXAmount);

	for (let i = 0; i < notes.length; i++) {
		document.getElementById(code + notes[i]).value = result[i];
	}
	var newTotal = 0;
	for (let i = 0; i < notes.length; i++) {
		newTotal += notes[i] * result[i];
	}
	if (newTotal > 0) {
		$("#btnAddToCart").removeClass('d-none');
	}else {
		$("#btnAddToCart").addClass('d-none');
	}
	document.getElementById('total_fx1').value = newTotal;
	document.getElementById('total_aud1').value = (newTotal / rate).toFixed(2);
}

function AUDAmountEntered() {
	var code = document.getElementById("selectedCode").textContent;
	var name = document.getElementById("selectedName").textContent;
	var notesString = document.getElementById("selectedNotes").textContent;
	var notes = [];
	let item = 0;

	while (notesString.length != notesString.replace(" ", "").length) {
		notes[item] = parseInt(notesString.substring(0, notesString.indexOf(" ")));
		notesString = notesString.substring(1 + notesString.indexOf(" "));
		item = item + 1;
	}
	var rate = document.getElementById("discountedRate").textContent;
	
	var AUDAmount = document.getElementById('total_aud1').value;
	var newTotal = parseInt(AUDAmount * rate);
	if (newTotal > 0) {
		$("#btnAddToCart").removeClass('d-none');
	}else {
		$("#btnAddToCart").addClass('d-none');
	}
	document.getElementById('total_fx1').value = newTotal;
	document.getElementById('total_aud1').value = (newTotal / rate).toFixed(2);
}
function showConfirmation() {
	var code = document.getElementById("selectedCode").textContent;
	var name = document.getElementById("selectedName").textContent;
	var notesString = document.getElementById("selectedNotes").textContent;
	var notes = [];
	let item = 0;
	var AUDAmount = document.getElementById('total_aud1').value;
	
	if (AUDAmount>0) {
		while (notesString.length != notesString.replace(" ", "").length) {
		notes[item] = parseInt(notesString.substring(0, notesString.indexOf(" ")));
		notesString = notesString.substring(1 + notesString.indexOf(" "));
		item = item + 1;
	}
	var rate = document.getElementById("discountedRate").textContent;
	
	
	var FXAmount = parseInt(document.getElementById('total_fx1').value);

	document.getElementById('FXConfirmation').value = name;

	var tableDeno = '<table class="table border-primary"><thead><tr><th scope="col">Denomination</th><th scope="col">Quantity</th></tr></thead><tbody>';
	for (let i = 0; i < notes.length; i++) {
		var qty = parseInt(document.getElementById(code + notes[i]).value);
		if (qty != 0) {
			tableDeno += '<tr><th scope="row" class="d-flex flex-row"><input type="text" class="form-control-plaintext" name="' + code + '" id="' + code + notes[i] + '" value="' + notes[i] + '" readonly style="width:80px "><input type="text"class="form-control-plaintext" value="' + code + '" readonly style="width:auto"></th><td><input type="text" class="form-control-plaintext" name="' + code + 'Qty" id="' + code + notes[i] + 'Qty" value="' + qty + '" readonly style="direction: rtl;width:auto;"></td></tr>';
		}
	}
	tableDeno += '</tbody></table>'

	document.getElementById('tableDeno').innerHTML = tableDeno;
	document.getElementById('FXAmountConfirmation').value = formatCurrency(FXAmount, code);
	document.getElementById('FXCodeConfirmation').value = code;
	document.getElementById('AUDAmountConfirmation').value = formatCurrency(AUDAmount, "AUD");
	document.getElementById('RateConfirmation').value = Number(rate).toFixed(5);
	}

	
}