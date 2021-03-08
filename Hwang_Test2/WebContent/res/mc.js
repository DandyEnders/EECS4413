/**
 * 
 */

function validate(){
	var ok = true;
	
	var p = document.getElementById("credit").value;
	if (isNaN(p) || p < 0 || p >= 121) {
		alert("Credit invalid! p >= 0 && p < 121");
		ok = false;
	} else {
	}
	
	if (!ok)
		return false;
		
}

function funcReportAjaxS2(address){
	var request = new XMLHttpRequest();
	var data = '';
	
	data += "credit=" + document.getElementById("credit").value + "&";
	data += "calculate=true";
	
	request.open("GET", (address + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send(null);
}

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
		var target = document.getElementById("ajaxTarget");
		target.innerHTML = request.responseText;
	}
}
