$('document').ready(function(e) {
        $('#password').keyup(function(e) {
            var l = $('#password').val().length;
			if(l<1){
				$('#password_error').text("Not Valid Password");	
				$('#password_error').addClass("weak");
				$('#password_error').removeClass("normal");
				$('#password_error').removeClass("strong");
			}
			else if(l<6){
				$('#password_error').text("Weak");
				$('#password_error').addClass("weak");
				$('#password_error').removeClass("normal");
				$('#password_error').removeClass("strong");
			}
			else if(l<10){
				$('#password_error').text("Normal");
				$('#password_error').addClass("normal");
				$('#password_error').removeClass("weak");
				$('#password_error').removeClass("strong");
			}
			else{
				$('#password_error').text("Strong");
				$('#password_error').addClass("strong");
				$('#password_error').removeClass("normal");
				$('#password_error').removeClass("weak");
			}
        });
});
$(document).ready(function(e){
	//alert("1");
	$("#country").change(function(){
		//alert("2");
		$(document).ajaxStart(function(){
    	$("#wait").css("display","block");
		});
		$(document).ajaxComplete(function(){
		$("#wait").css("display","none");
		});
		var country = document.getElementById("country");
		$.ajax({
			url: "GetState",
			data: "country="+country.value,
			type: "POST",
			dataType: "html",
			success : function(data) {
				var userObj = JSON.parse(data);
				$.each(userObj, function(i, v) {
					$("#state").append(
							'<option value="' + v['id'] + '">' + v['state_name']
									+ '</option>');
				});
			}
		});
	});
	$("#state").change(function(){
		$("#district").html('<option value="select">select</option>');
		var state = document.getElementById("state");
		$.ajax({
			url: "GetDistrict",
			data: "district="+state.value,
			type: "POST",
			dataType: "html",
			success : function(data) {
				var userObj = JSON.parse(data);
				$.each(userObj, function(i, v) {
					$("#district").append(
							'<option value="' + v['id'] + '">' + v['city_name']
									+ '</option>');
				});
			}
		});
	});
});

$(document).ready(function(){

	$("#username").keyup(function() { 

	var usr = $("#username").val();

	if(usr.length >= 4)
	{
	$("#status2").html('<img src="images/loader.gif" align="absmiddle">&nbsp;Checking availability...');

	    $.ajax({  
	    type: "POST",  
	    url: "Username",  
	    data: "username="+ usr,  
	    success: function(msg){  
	   
	   $("#status2").ajaxComplete(function(event, request, settings){ 
		//alert(msg);
		if(msg.success == "true")
		{ 
	        alert("");
			$("#username").removeClass('object_error'); // if necessary
			$("#username").addClass("object_ok");
			$(this).html('&nbsp;<img src="images/tick.gif" align="absmiddle">');
		}  
		else  
		{  
			$("#username").removeClass('object_ok'); // if necessary
			$("#username").addClass("object_error");
			//$(this).html('&nbsp;<img src="images/tick.gif" align="absmiddle">');
			$(this).html(msg);
		}  
	   
	   });

	 } 
	   
	  }); 

	}
	else
		{
		$("#status2").html('<font color="red">The username should have at least <strong>4</strong> characters.</font>');
		$("#username").removeClass('object_ok'); // if necessary
		$("#username").addClass("object_error");
		}

	});

	
	$("#email_id").change(function() { 

		var usr = $("#email_id").val();

		if(usr.length >= 4)
		{
		$("#status1").html('<img src="images.loader.gif" align="absmiddle">&nbsp;Checking availability...');

		    $.ajax({  
		    type: "POST",  
		    url: "Email",  
		    data: "email_id="+ usr,  
		    success: function(msg){  
		   
		   $("#status1").ajaxComplete(function(event, request, settings){ 
			if(msg.success == "true")
			{ 
		        alert("");
				$("#email_id").removeClass('object_error'); // if necessary
				$("#email_id").addClass("object_ok");
				$(this).html('&nbsp;<img src="images/tick.gif" align="absmiddle">');
			}  
			else  
			{  
				$("#email_id").removeClass('object_ok'); // if necessary
				$("#email_id").addClass("object_error");
				//$(this).html('&nbsp;<img src="images/tick.gif" align="absmiddle">');
				$(this).html(msg);
			}  
		   
		   });

		 } 
		   
		  }); 

		}
		else
			{
			$("#status1").html('<font color="red">The username should have at least <strong>4</strong> characters.</font>');
			$("#email_id").removeClass('object_ok'); // if necessary
			$("#email_id").addClass("object_error");
			}

		});
	
	
});