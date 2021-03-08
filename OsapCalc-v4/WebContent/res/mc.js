/**
 * 
 */
function validate(){
	var ok = true;
	
	var p = document.getElementById("principal").value;
	if (isNaN(p) || p <= 0) {
		alert("Principle invalid!");
		document.getElementById("principal-error").style.display = "inline";
		document.getElementById("principal-error").style.color = "red";
		ok = false;
	} else {
		document.getElementById("principal-error").style.display = "none";	
	}
	
	if (!ok)
		return false;
		
	p = document.getElementById("annualInterestRate").value;
	if (isNaN(p) || p <= 0 || p >= 100){
		alert("Invalid. Must be in (0, 100).");
		document.getElementById("interest-error").style.display = "inline";
		document.getElementById("interest-error").style.color = "red";
		ok = false;
	} else {
		document.getElementById("interest-error").style.display = "none";
	}
	
	p = document.getElementById("paymentPeriod").value;
	if (isNaN(p) || p <= 0) {
		alert("Invalid. Must be greater than 0.");
		document.getElementById("paymentPeriod-error").style.display = "inline";
		document.getElementById("paymentPeriod-error").style.color = "red";
		ok = false;
	} else {
		document.getElementById("paymentPeriod-error").style.display = "none";
	}
		
}

function doSimpleAjax(address){
	var request = new XMLHttpRequest();
	var data = '';
	
	data += "principal=" + document.getElementById("principal").value + "&";
	data += "annualInterestRate=" + document.getElementById("annualInterestRate").value + "&";
	data += "paymentPeriod=" + document.getElementById("paymentPeriod").value + "&";
	data += "grace=" + document.getElementById("grace").value + "&";
	data += "calculate=true";
	
	request.open("GET", (address + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send(null);
}

function handler(request){
	if ((request.readyState == 4) && (request.status == 200)){
		var target = document.getElementById("ajaxTarget");
		target.innerHTML = request.responseText;
	}
}