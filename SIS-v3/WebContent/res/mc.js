/**
 * 
 */
/*function validate(){
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
		
}*/

function funcReportAjax(address){
	var request = new XMLHttpRequest();
	var data = '';
	
	data += "nameprefix=" + document.getElementById("nameprefix").value + "&";
	data += "minimumCreditTaken=" + document.getElementById("minimumCreditTaken").value + "&";
	data += "calculate=true";
	
	request.open("GET", (address + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send(null);
}

function handler(request){
	if ((request.readyState == 4) && (request.status == 200)){
		var target = document.getElementById("result");
		target.innerHTML = request.responseText;
	}
}