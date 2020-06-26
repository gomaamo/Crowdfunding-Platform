<!DOCTYPE html>
<html>
<head>
    <title>SelectUserPage</title>
    <style type="text/css">
         a{
            color: black;
            text-decoration: none; 
        }
        a:hover{
            color: navy;
            cursor: pointer;
            opacity: 0.8;
            text-decoration: underline;
        }
        
        button{
        color: white;
        text-align: center;
        border-color: black;
        padding: 1px 30px;        
        }
        
        button:hover{
			opacity: 0.8;
			cursor: pointer;
		}
        
        h1{
            text-align: center;
            font-size: 60px;
            font-family:'Roboto',sans-serif;
            color: navy;
        }
        
        body{
           font-family:'Roboto',sans-serif; 
        }
        
        div{
 			 padding: 25px;
		}
    </style>
</head>

<body> 
    <h1>ProjectFunder</h1>
   	<h4 align="center">Herzlich willkommen auf unserer ProjectFunder WebApp!</h4>
   	<button type="button" style="background-color: cadetblue; float:right;" 
    	onclick="location.href='http://localhost:8080/projectFunder/search'" title="Schnell suchen">Suchen</button>
	<div>
        <h2 style="color:cadetblue"><b>Benutzer wählen:</b></h2>	
				<p align="center">
				<#list users as user>
				  <label><a href="MainServlet?action=userSelected&amp;user=${user.email}" title="Select User">${user.email}</a></label> &nbsp&nbsp&nbsp&nbsp
				</#list>
				</p>
    </div>      
</body>
</html>