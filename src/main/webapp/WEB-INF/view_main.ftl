<!DOCTYPE html>
<html>
<head>
    <title>Hauptseite</title>
    <style type="text/css">
        a{
            color: black;
            text-decoration: none;
        }
        a:hover{
            color: navy;
            text-decoration: underline;
            opacity: 0.8;
			cursor: pointer;
        }
        
        h1{
            text-align: center;
            font-size: 45px;
            font-family:'Roboto',sans-serif;
            color: navy;
        }
        
        body{
           font-family:'Roboto',sans-serif; 
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
    <h1>ProjectFunder</h1>
    <button type="button" onclick="location.href='ViewProfileServlet?action=viewProfile&amp;user=${user}&amp;profile=${user}' " title="View ${user}'s Profile">Mein Profil</button>
        <button type="button" style="background-color: cadetblue; float:center;" 
    		onclick="location.href='http://localhost:8080/projectFunder/search'" title="Suchen">Suchen</button>
    <button type="button" style="background-color: cadetblue; float:right;" 
    	onclick="location.href='http://localhost:8080/projectFunder/selectUserPage'" title="Benutzer wählen">Benutzer wählen</button>
    
    <div>
        <h2><span style="color:yellowgreen">Offene</span> Projekte</h2>
         <#list openProjects as open>
            
            <table style="display: inline-block">
            <tr><th><img src="D:\Dev\Projects\projectFunder\src\main\webapp\WEB-INF\${open.kategorie.icon}" alt=${open.kategorie.name}></th></tr>
                <tr><th><a href="ViewProjectServlet?action=viewProject&amp;user=${user}&amp;pid=${open.kennung}" title="View Project">${open.titel}</a></th></tr>
                <tr><td>von: <a href="ViewProfileServlet?action=viewProfile&amp;user=${user}&amp;profile=${open.ersteller.email}" title="View Profile">${open.ersteller.name}</td></tr>
                <tr><td>Aktuell: ${open.totalDonations()} €</td></tr>
            </table>
            
            </#list>
    </div>
    
    <div>
        <h2><span style="color:red">Abgeschlossene</span> Projekte</h2>
        <#list closedProjects as closed>
            
            <table style="display: inline-block">
            <tr><th><img src="D:\Dev\Projects\projectFunder\src\main\webapp\WEB-INF\${closed.kategorie.icon}" alt=${closed.kategorie.name}></th></tr>
                <tr><th><a href="ViewProjectServlet?action=viewProject&amp;user=${user}&amp;pid=${closed.kennung}" title="View Project">${closed.titel}</a></th></tr>
                <tr><td>von: <a href="ViewProfileServlet?action=viewProfile&amp;user=${user}&amp;profile=${closed.ersteller.email}" title="View Profile">${closed.ersteller.name}</td></tr>
                <tr><td>Aktuell: ${closed.totalDonations()} €</td></tr>
            </table>
            
            </#list>
    </div>
    
    <button type="button" onclick="location.href='NewProjectServlet?action=createProject&amp;user=${user}' " title="Create Project" style="float: right">Projekt erstellen</button>
</body>
</html>