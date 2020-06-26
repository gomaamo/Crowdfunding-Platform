<!DOCTYPE html>
<html>
<head>
    <title>Projekt Spende</title>
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
        background-color: red;
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
    <form name="new_donate" action="DonateServlet?action=donate" method="post">
        <label>Spendenbetrag (€): <input type="number" name="fund" required min="1" placeholder="0.00" step="0.01"/></label><br><br>
        <label><input type="checkbox" name="checkBox"  value="privat"> Anonym spenden?</label><br><br>
        <button type="button" style="background-color:navy" 
        	onclick="location.href='http://localhost:8080/projectFunder/ViewProjectServlet?action=viewProject&amp;user=${user}&amp;pid=${kennung}'">Zurück</button>
        <button type="submit" class="b">Spenden</button>
        <input type="hidden" value="${kennung}" name="pid">
        <input type="hidden" value="${user}" name="user">
    </form>
    </div>
</body>
</html>