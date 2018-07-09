<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

    </c:if>

</div>
 
<form action="/getforecast/place" method="get">  

<div class="container">
<label>Forecast for 
<input list="places" name="place" /></label>
<datalist id="places">
  <option value="Hyderabad">
  <option value="Delhi">
  <option value="Mumbai">
  <option value="Chennai">
  <option value="Kolkata">
  <option value="Pune">
</datalist>
  for
   <input type="text" name="days" value="10"> days
   <input type="submit" value="get" />
   </div>  
  </form>  
  <div class="container">
  <%@ page import="com.hellokoding.auth.model.Weather" %>
  <% 
  String defaultloc=(String)request.getAttribute("defaultloc");  
  if(defaultloc!=null){
	  out.print( "Temperature for "+defaultloc); 
  }
  String place=(String)request.getAttribute("place");  
  
  if(place!=null){  
  out.print( "Temperature for "+place); 
  }
  
  else if(defaultloc==null)
	  out.print( "Temperature for Hyderabad");
String t=(String)request.getAttribute("temperature");  
if(t!=null){  
out.print(" is "+t+"<br/>");
}
else
	out.print(" is "+"23"+"<br/>");  

//else
//out.print("23"); 


String mintemp=(String)request.getAttribute("mintemp");  
if(mintemp!=null){  
out.print("Min temp: "+mintemp+"<br/>"); 

}
else 
	out.print("Min temp: "+21+"<br/>");
String maxtemp=(String)request.getAttribute("maxtemp");  
if(maxtemp!=null){  
out.print("Max temp: "+maxtemp+"<br/>"); 

}
out.print("Max temp: "+28+"<br/>");
String humidity=(String)request.getAttribute("humidity");  
if(humidity!=null){  
out.print("Humidity is "+humidity+"<br/>"); 

}
else 
	out.println("Humidity is "+"89%"+"<br/>");
String weather=(String)request.getAttribute("weather");  
if(weather!=null){  
out.println("It is a "+weather+" day today!! <br/>"); 
}
//else
	//out.print("empty :("); 
//int no = Integer.parseInt(request.getParameter("days"));

String no=(String)request.getAttribute("days"); 
int noo=0;
if(no!=null){
  noo=Integer.parseInt(no);
out.println("For "+noo+" days!! <br/>"); 
for(int i=0;i<noo;i++){
	
	Weather tem=(Weather)request.getAttribute("day"+i);  
	if(tem!=null){  
		out.print("Forecast for day "+i+"<br/>");
		out.print("=======================================<br/>");
	out.print("Min temp: "+tem.getMintemp()+"<br/>"); 
	out.print("Max temp: "+tem.getMaxtemp()+"<br/>"); 
	out.print("Humidity: "+tem.getHumidity()+"<br/>"); 
	out.print("=======================================<br/>");
	}
}

}

 %> 
  

</div>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
