<!DOCTYPE html>
<html>
<head>
    <title>Projekt Kommentar</title>
    <style type="text/css">        
        h1{
            text-align: center;
            font-size: 25px;
            font-family:'Roboto',sans-serif;
            color: navy;
        }
        
        body{
           font-family:'Roboto',sans-serif; 
        }
        
        button{
        color: white;
        text-align: center;
        border-color: black;
        padding: 6px 30px;        
        }
        
        button:hover{
        	opacity: 0.8;
			cursor: pointer;
		}
        
        label{
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>${titel}</h1>
    
    <div align="center">
    <form name="new_comment" action="NewKommentar?action=newComment" method="post">
        <label><textarea name="comment" rows="7" cols="35" placeholder="Schreibe Kommentar..." required></textarea></label><br><br>
        <label><input type="checkbox" name="checkBox" value="privat"> Anonym kommentieren?</label><br><br>
        <input type="hidden" value="${kennung}" name="pid">
        <input type="hidden" value="${user}" name="user">
        <button type="button" style="background-color:navy" 
        	onclick="location.href='http://localhost:8080/projectFunder/ViewProjectServlet?action=viewProject&amp;user=${user}&amp;pid=${kennung}'">Zurück</button>
        <button type="submit" style="background-color:cadetblue">Kommentar hinzufügen</button>
    </form>
    </div>
</body>
</html>