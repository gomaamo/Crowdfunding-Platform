<!DOCTYPE html>
<html>
<head>
    <title>Projekt Suche</title>
    <style type="text/css">        
        h1{
            font-size: 25px;
            font-family:'Roboto',sans-serif;
            color: navy;
        }
        
        body{
           font-family:'Roboto',sans-serif; 
        }
        
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
        background-color: navy;
        color: white;
        text-align: center;
        border-color: black;
        padding: 1px 30px;        
        }
        
        button:hover{
			opacity: 0.8;
			cursor: pointer;
		}
        
        label{
            font-weight: bold;
        }
        
        table{
            border: 1px solid black;
        }
        
        th{
            text-align: center;
        }
                
        img{
            height: 90px;
            width: 90px;
        }
    </style>
</head>
<body>
    <div align="center" style="border-bottom:1px solid black">
        <form name="title" action="search" method="post">
        <button style="float:left; display:inline;" type="button" 
        	onclick="location.href='MainServlet?action=userSelected&amp;user=dummy@dummy.com'">Home</button>
        <label>Titel: </label> &nbsp <input type="text" name="title" required maxlength="30"/> &nbsp&nbsp 
        <button type="submit" class="b" id="searchProjects" name="searchProjects" value="Save">Suche</button>
            <button type="button" style="background-color: cadetblue; float:right;" 
    	onclick="location.href='http://localhost:8080/projectFunder/selectUserPage'" title="Benutzer wählen">Benutzer wählen</button> <p></p>
        </form>
    </div>
    <div>
        <h1>Suchergebnisse</h1>
        <#list availableProjects as p>
        <table style="display:inline-block">
        <tr><th><img src="D:\Dev\Projects\projectFunder\src\main\webapp\WEB-INF\${p.kategorie.icon}" alt=${p.kategorie.name}></th></tr>
        <tr><th><a href="ViewProjectServlet?action=viewProject&amp;user=${user}&amp;pid=${p.kennung}" title="View Project">${p.titel}</a></th></tr>
        <tr><td>von: <a href="ViewProfileServlet?action=viewProfile&amp;user=${user}&amp;profile=${p.ersteller.email}" title="View Profile">${p.ersteller.name}</td></tr>
        <tr><td>Aktuell: ${p.totalDonations()} €</td></tr>
        <tr><td>Status: ${p.status}</td></tr>
        </table> 
        </#list>
    </div>
</body>
</html>