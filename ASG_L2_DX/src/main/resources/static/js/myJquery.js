$(document).ready(function(){
	if($("#login")){
		$("#login").click(function(){
			$("#loginForm").submit();
		});
	}
	if($("#register")){
    		$("#register").click(function(){
    		    document.location.href ="http://localhost:8080/register";
    			return false;
    		});
    	}
});