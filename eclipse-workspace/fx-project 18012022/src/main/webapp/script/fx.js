document.addEventListener("DOMContentLoaded", function(event) {

	function OTPInput() {
		const inputs = document.querySelectorAll('#otp > *[id]');

		for (let i = 0; i < inputs.length; i++) { inputs[i].addEventListener('keydown', function(event) { if (event.key === "Backspace") { inputs[i].value = ''; if (i !== 0) inputs[i - 1].focus(); } else { if (i === inputs.length - 1 && inputs[i].value !== '') { return true; } else if (event.keyCode > 47 && event.keyCode < 58) { inputs[i].value = event.key; if (i !== inputs.length - 1) inputs[i + 1].focus(); event.preventDefault(); } else if (event.keyCode > 64 && event.keyCode < 91) { inputs[i].value = String.fromCharCode(event.keyCode); if (i !== inputs.length - 1) inputs[i + 1].focus(); event.preventDefault(); } } }); }
	} OTPInput();
});

function validate() {
	var $password = $(".form-control[id='pwd']");
	var $passwordAlert = $(".password-alert[id='alert1']");
	var $requirements = $(".requirements[id='alert1']");
	var leng, bigLetter, num, specialChar;
	var $leng = $(".leng");
	var $bigLetter = $(".big-letter");
	var $num = $(".num");
	var $specialChar = $(".special-char");
	var specialChars = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?`~";
	var numbers = "0123456789";

	var $password2 = $(".form-control[id='pwd2']");
	var $passwordAlert2 = $(".password-alert[id='alert2']");
	var $requirements2 = $(".requirements[id='alert2']");
	var same;
	var $same = $(".same");

	$requirements.addClass("incorrect");
	$password.on("focus", function() { $passwordAlert.show(); });

	$password.on("input blur", function(e) {
		var el = $(this);
		var val = el.val();
		$passwordAlert.show();

		if (val.length < 8) {
			leng = false;
		}
		else if (val.length > 7) {
			leng = true;
		}


		if (val.toLowerCase() == val) {
			bigLetter = false;
		}
		else { bigLetter = true; }

		num = false;
		for (var i = 0; i < val.length; i++) {
			for (var j = 0; j < numbers.length; j++) {
				if (val[i] == numbers[j]) {
					num = true;
				}
			}
		}

		specialChar = false;
		for (var i = 0; i < val.length; i++) {
			for (var j = 0; j < specialChars.length; j++) {
				if (val[i] == specialChars[j]) {
					specialChar = true;
				}
			}
		}

		console.log(leng, bigLetter, num, specialChar);

		if (leng == true && bigLetter == true && num == true && specialChar == true) {
			//$(this).addClass("is-valid").removeClass("is-invalid");
			$(this).removeClass("is-invalid");
			$requirements.removeClass("incorrect").addClass("correct");
			$passwordAlert.removeClass("alert-warning").addClass("alert-success");
			$("#submitButton").attr("disabled", false);

		}
		else {
			//$(this).addClass("is-invalid").removeClass("is-valid");
			$(this).addClass("is-invalid");

			$passwordAlert.removeClass("alert-success").addClass("alert-warning");
			$("#submitButton").attr("disabled", true);

			if (leng == false) { $leng.addClass("incorrect").removeClass("correct"); }
			else { $leng.addClass("correct").removeClass("incorrect"); }

			if (bigLetter == false) { $bigLetter.addClass("incorrect").removeClass("correct"); }
			else { $bigLetter.addClass("correct").removeClass("incorrect"); }

			if (num == false) { $num.addClass("incorrect").removeClass("correct"); }
			else { $num.addClass("correct").removeClass("incorrect"); }

			if (specialChar == false) { $specialChar.addClass("incorrect").removeClass("correct"); }
			else { $specialChar.addClass("correct").removeClass("incorrect"); }
		}


		if (e.type == "blur" && $passwordAlert.hasClass("alert-success")) {
			$passwordAlert.hide();
		}


		$requirements2.addClass("incorrect");

		$password2.on("focus", function() { $passwordAlert2.show(); });

		if ($password.val() == $password2.val() && leng == true && bigLetter == true && num == true && specialChar == true) {
			$requirements2.removeClass("incorrect").addClass("correct");
			$password2.removeClass("is-invalid");
			//$password2.addClass("is-valid").removeClass("is-invalid");

			$passwordAlert2.removeClass("alert-warning").addClass("alert-success");
			$("#submitButton").attr("disabled", false);

		}
		else {
			$("#submitButton").attr("disabled", true);
			$requirements2.removeClass("correct").addClass("incorrect");
			$password2.addClass("is-invalid");
			//$password2.addClass("is-invalid").removeClass("is-valid");
			$passwordAlert2.removeClass("alert-success").addClass("alert-warning");
			$passwordAlert2.show();
		}

		$password2.on("input blur", function(e) {
			var el = $(this);
			var val = el.val();
			$passwordAlert2.show();


			if (val == $password.val()) { same = true; }
			else if (val != $password.val()) { same = false; }

			console.log(same);

			if (val == $password.val() && leng == true && bigLetter == true && num == true && specialChar == true) {
				$("#submitButton").attr("disabled", false);
				$(this).removeClass("is-invalid");
				//$(this).addClass("is-valid").removeClass("is-invalid");
				$requirements2.removeClass("incorrect").addClass("correct");
				$same.removeClass("incorrect").addClass("correct");
				$passwordAlert2.removeClass("alert-warning").addClass("alert-success");
			}
			else {
				$("#submitButton").attr("disabled", true);
				$(this).addClass("is-invalid");
				//$(this).addClass("is-invalid").removeClass("is-valid");
				$passwordAlert2.removeClass("alert-success").addClass("alert-warning");
				$same.addClass("incorrect").removeClass("correct");

			}

			if (e.type == "blur" && $passwordAlert2.hasClass("alert-success")) {
				$passwordAlert2.hide();
			}
		});


	});

};

$(document).ready(
	validate(),
	validateReset()
);

function validateReset() {
	var $password = $(".form-control[id='pwdReset']");
	var $passwordAlert = $(".password-alert[id='alert1Reset']");
	var $requirements = $(".requirements[id='alert1Reset']");
	var leng, bigLetter, num, specialChar;
	var $leng = $(".lengReset");
	var $bigLetter = $(".big-letterReset");
	var $num = $(".numReset");
	var $specialChar = $(".special-charReset");
	var specialChars = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?`~";
	var numbers = "0123456789";

	var $password2 = $(".form-control[id='pwd2Reset']");
	var $passwordAlert2 = $(".password-alert[id='alert2Reset']");
	var $requirements2 = $(".requirements[id='alert2Reset']");
	var same;
	var $same = $(".sameReset");

	$requirements.addClass("incorrect");
	$password.on("focus", function() { $passwordAlert.show(); });

	$password.on("input blur", function(e) {
		var el = $(this);
		var val = el.val();
		$passwordAlert.show();

		if (val.length < 8) {
			leng = false;
		}
		else if (val.length > 7) {
			leng = true;
		}


		if (val.toLowerCase() == val) {
			bigLetter = false;
		}
		else { bigLetter = true; }

		num = false;
		for (var i = 0; i < val.length; i++) {
			for (var j = 0; j < numbers.length; j++) {
				if (val[i] == numbers[j]) {
					num = true;
				}
			}
		}

		specialChar = false;
		for (var i = 0; i < val.length; i++) {
			for (var j = 0; j < specialChars.length; j++) {
				if (val[i] == specialChars[j]) {
					specialChar = true;
				}
			}
		}

		console.log(leng, bigLetter, num, specialChar);

		if (leng == true && bigLetter == true && num == true && specialChar == true) {
			//$(this).addClass("is-valid").removeClass("is-invalid");
			$(this).removeClass("is-invalid");
			$requirements.removeClass("incorrect").addClass("correct");
			$passwordAlert.removeClass("alert-warning").addClass("alert-success");
			$("#submitButtonReset").attr("disabled", false);

		}
		else {
			//$(this).addClass("is-invalid").removeClass("is-valid");
			$(this).addClass("is-invalid");

			$passwordAlert.removeClass("alert-success").addClass("alert-warning");
			$("#submitButtonReset").attr("disabled", true);

			if (leng == false) { $leng.addClass("incorrect").removeClass("correct"); }
			else { $leng.addClass("correct").removeClass("incorrect"); }

			if (bigLetter == false) { $bigLetter.addClass("incorrect").removeClass("correct"); }
			else { $bigLetter.addClass("correct").removeClass("incorrect"); }

			if (num == false) { $num.addClass("incorrect").removeClass("correct"); }
			else { $num.addClass("correct").removeClass("incorrect"); }

			if (specialChar == false) { $specialChar.addClass("incorrect").removeClass("correct"); }
			else { $specialChar.addClass("correct").removeClass("incorrect"); }
		}


		if (e.type == "blur" && $passwordAlert.hasClass("alert-success")) {
			$passwordAlert.hide();
		}


		$requirements2.addClass("incorrect");

		$password2.on("focus", function() { $passwordAlert2.show(); });

		if ($password.val() == $password2.val() && leng == true && bigLetter == true && num == true && specialChar == true) {
			$requirements2.removeClass("incorrect").addClass("correct");
			$password2.removeClass("is-invalid");
			//$password2.addClass("is-valid").removeClass("is-invalid");

			$passwordAlert2.removeClass("alert-warning").addClass("alert-success");
			$("#submitButtonReset").attr("disabled", false);

		}
		else {
			$("#submitButtonReset").attr("disabled", true);
			$requirements2.removeClass("correct").addClass("incorrect");
			$password2.addClass("is-invalid");
			//$password2.addClass("is-invalid").removeClass("is-valid");
			$passwordAlert2.removeClass("alert-success").addClass("alert-warning");
			$passwordAlert2.show();
		}

		$password2.on("input blur", function(e) {
			var el = $(this);
			var val = el.val();
			$passwordAlert2.show();


			if (val == $password.val()) { same = true; }
			else if (val != $password.val()) { same = false; }

			console.log(same);

			if (val == $password.val() && leng == true && bigLetter == true && num == true && specialChar == true) {
				$("#submitButtonReset").attr("disabled", false);
				$(this).removeClass("is-invalid");
				//$(this).addClass("is-valid").removeClass("is-invalid");
				$requirements2.removeClass("incorrect").addClass("correct");
				$same.removeClass("incorrect").addClass("correct");
				$passwordAlert2.removeClass("alert-warning").addClass("alert-success");
			}
			else {
				$("#submitButtonReset").attr("disabled", true);
				$(this).addClass("is-invalid");
				//$(this).addClass("is-invalid").removeClass("is-valid");
				$passwordAlert2.removeClass("alert-success").addClass("alert-warning");
				$same.addClass("incorrect").removeClass("correct");

			}

			if (e.type == "blur" && $passwordAlert2.hasClass("alert-success")) {
				$passwordAlert2.hide();
			}
		});


	});

};

(function() {
	'use strict'

	var forms = document.querySelectorAll('.needs-validation');

	// Loop over them and prevent submission
	Array.prototype.slice.call(forms)
		.forEach(function(form) {
			form.addEventListener('submit', function(event) {

				if (!form.checkValidity()) {
					event.preventDefault()
					event.stopPropagation()
				}

				form.classList.add('was-validated')
			}, false)


			//form.querySelectorAll('.password').forEach(input => {
			//	input.addEventListener(('input'), () => {
			//		if (input.checkValidity()) {
			//			input.classList.remove('is-invalid')
			//			input.classList.add('is-valid');
			//		} else {
			//			input.classList.remove('is-valid')
			//			input.classList.add('is-invalid');
			//		}
			//	});
			//});

		})

})()

