/**
 * 
 */
function validateForm(){
	var ok=true;
	//get the content if the name input field; check the html/jsp file for that element
	var el=document.getElementById("name").value;
	//simple test, the input field should not be empty
	if (el==""){
		alert("Enter a string");//pop-up an alert
		ok= false;//and return false
	}
	
	return ok;
}

function helloAjax(url){
	var xhr= new XMLHttpRequest(); //prepare an HTTP request
	var data='';//prepare the url,something like: /Hello?comm=ajax
	var el=document.querySelector("#name").value;//notice how I get the element 'name'
	data += "comm=ajax";
    
	if (el==""){ //check if there is name in the input field/validation
		alert("Enter a string");
		return false;
	}
	//complete the url, something like /Hello?comm=ajax&name=John
	data += "&name=";
	data +=el;
    console.log(data);//for debugging purposes in the browser
	xhr.open("GET", (url + "?" + data), true);//open the connection
	xhr.onreadystatechange = function() {//this is call back function, check the lecture
		handler(xhr);
	};
	xhr.send();
}


function handler(xhr){//this code is called for any change of the flag onreadystatechange
	  if ((xhr.readyState == 4) && (xhr.status == 200)){//check the response is received
	     var target = document.querySelector("#ajaxTarget");//change the content of the HTML
	     target.innerHTML = xhr.responseText;
	   }
	} 

//this does the same as helloAjax, but uses fetch function
function helloFetch(url){
	var data='';
	var el=document.getElementById("name").value;
	data += "comm=fetch";
    
	if (el==""){
		alert("Enter a string");
		return false;
	}
	data += "&name=";
	data +=el;
//fetch is simpler..the call back function  is specified by "then"	
fetch(url + "?" + data).then(function(response) {//replaces onreadystatechange
	  return response.text()
	}).then(function(text) {//replaces "handler"
	     var target = document.getElementById("fetchTarget");
	     target.innerHTML = text;
	});
}

