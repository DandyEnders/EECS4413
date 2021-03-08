function validate() {
    var x = document.forms["myform"]["language"].value;  //note I access a parameter value
    if (x == null || x == "") {
        alert("the field cannot be empty");  //note how I pop-up an alert
        document.getElementById("error").innerHTML="*";
        return false;
    }
    if (!((x=="english")||(x=="french"))) {
        alert("I only understand 'french' or 'english'");
        document.getElementById("error").innerHTML="*";
        return false;
    }
    document.getElementById("error").innerHTML="";
    	return true;
}
