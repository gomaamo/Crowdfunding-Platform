<!DOCTYPE html>
<head>
    <title>Fehler</title>
    <style type="text/css">
    	body{
            font-family:'Roboto',sans-serif;
        }
    	
    	.error {
			font-size: 15px;
			color: green;
			font-weight: bold;
			text-align: center;
			margin: 0px auto;
		}
		
		button{
			background-color: navy;
            color: white;
            text-align: center;
            border-color: black;
            padding: 6px 30px;   
        }
        
        button:hover{
			opacity: 0.8;
			cursor: pointer;
		}
	</style>
</head>
<body>
	<div class="error">${message}</div><p></p>
	<div align="center"><button type="button" 
		onclick="location.href='ViewProjectServlet?action=viewProject&amp;user=${user}&amp;pid=${pid}'">Zurück</button></div>
</body>
</html>