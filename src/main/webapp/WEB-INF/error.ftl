<!DOCTYPE html>
<head>
    <title>Fehler</title>
    <style type="text/css">
    	body{
            font-family:'Roboto',sans-serif;
        }
    	
    	.error {
			font-size: 17px;
			color: red;
			font-weight: bold;
			text-align: center;
			margin: 0px auto;
		}
		
		button{
			background-color: navy;
            color: white;
            text-align: center;
            border-color: black;
            padding: 4px 23px;   
        }
        
        button:hover{
			opacity: 0.8;
			cursor: pointer;
		}
		
		h1{
            text-align: center;
            font-size: 45px;
            font-family:'Roboto',sans-serif;
            color: navy;
        }
	</style>
</head>
<body>
	<div><h1>ProjectFunder</h1>
			<button style="float:left; display:inline;" type="button" onclick="location.href='MainServlet?action=userSelected&amp;user=${user}'">Home</button>
            <button type="button" style="background-color: cadetblue; float:right; display:inline;" 
    				onclick="location.href='http://localhost:8080/projectFunder/selectUserPage'" title="Benutzer wählen">Benutzer wählen</button>
    </br></br></br></br></div>
	<div class="error">${message}</div></br></br></br></br></br></br></br></br></br></br></br>
	<div align="left"><button type="button" 
		onclick="location.href='ViewProjectServlet?action=viewProject&amp;user=${user}&amp;pid=${pid}'">Zurück</button></div>
</body>
</html>