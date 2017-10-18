function $(v){
		return document.getElementById(v);	
	}
	
	
	/*function open_inbox(){
		$("sent_mail").style.display = "none";
		$("inbox").style.display = "block";
		$("compose").style.display = "none";
		$("draft").style.display = "none";
		$("trash").style.display = "none";
		$("stared").style.display = "none";
	}
	function open_sent_mail(){
		$("sent_mail").style.display = "block";
		$("inbox").style.display = "block";
		$("trash").style.display = "none";
		$("compose").style.display = "none";
		$("draft").style.display = "none";
		$("stared").style.display = "none";
	}
	function open_draft(){
		$("sent_mail").style.display = "none";
		$("inbox").style.display = "none";
		$("compose").style.display = "none";
		$("draft").style.display = "block";
		$("stared").style.display = "none";
		$("trash").style.display = "none";
	}
	function open_compose(){
		$("sent_mail").style.display = "none";
		$("inbox").style.display = "none";
		$("compose").style.display = "block";
		$("draft").style.display = "none";
		$("stared").style.display = "none";
		$("trash").style.display = "none";
	}
	function open_stared(){
		$("sent_mail").style.display = "none";
		$("inbox").style.display = "none";
		$("compose").style.display = "none";
		$("draft").style.display = "none";
		$("trash").style.display = "none";
		$("stared").style.display = "block";
	}
	function open_trash(){
		$("sent_mail").style.display = "none";
		$("inbox").style.display = "none";
		$("compose").style.display = "none";
		$("draft").style.display = "none";
		$("stared").style.display = "none";
		$("trash").style.display = "block";
	}
	function compose_validate(){
		
		var subject = $("subject").value;
		var message = $("message_body").value;
		if(message.length==0){
			$("message_error").innerHTML = "Message cannot be Empty.";
			return false;
		}
		else if(subject == ""){
			alert("Subject is Empty");	
		}
	}*/