<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.sql.*;"%>
<html>
<head>
<script language="javascript">
var Timer;
var TotalSeconds;

function CreateTimer(TimerID, Time) 
{
    Timer = document.getElementById(TimerID);
    TotalSeconds = Time;
    UpdateTimer();
    window.setTimeout("Tick()", 1000);
}

function Tick() 
{
    TotalSeconds -= 1;
    if(TotalSeconds ==-1)
      {
    alert("Time Up");
    // Show alert Plus redirect any other page
      }
   else
     {
    UpdateTimer()
    window.setTimeout("Tick()", 1000);
     }
}

function UpdateTimer() {
    Timer.innerHTML = TotalSeconds;
}
</script>
</head>
<body>
<div id='timer' />
<script type="text/javascript">window.onload = CreateTimer("timer", 60);</script>
</body>
</html>

