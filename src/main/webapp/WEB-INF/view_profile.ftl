<!DOCTYPE html>
<html>
<head>
    <title>Nutzer Profil</title>
    <style type="text/css">
        a{
            color: black;
            text-decoration: none; 
        }
        a:hover{
            color: navy;
        }
        
        h1{
            text-align: center;
            font-size: 40px;
            font-family:'Roboto',sans-serif;
            color: navy;  
        }
        
        body{
           font-family:'Roboto',sans-serif; 
        }
        
        table{
            border: 1px solid black;
        }
        
        th{
            text-align:center;
        }
                
        img{
            height: 90px; width: 90px;
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
			<div><h1>ProjectFunder</h1>
			<button style="float:left; display:inline;" type="button" onclick="location.href='MainServlet?action=userSelected&amp;user=${user}'">Home</button>
            <button type="button" style="background-color: cadetblue; float:right; display:inline;" 
    				onclick="location.href='http://localhost:8080/projectFunder/selectUserPage'" title="Benutzer wählen">Benutzer wählen</button></br></br></div>
            <div style="border-bottom: 1px solid black">
            <h2 style="color:navy; text-align:left;">Profil von ${profile}</h2>
            <table style="border:none;">
            <tr><td><span style="font-weight:bold">Benutzername:</span> ${name}</td></tr>
            <tr><td><span style="font-weight:bold">Anzahl erstellter:</span> ${AnzahlE}</td></tr>
            <tr><td><span style="font-weight:bold">Anzahl unterstützter Projekte:</span> ${AnzahlU}</td></tr>
            </table> <p></p>
            </div>
            
            <div style="border-bottom: 1px solid black">
                <h2><span style="color:yellowgreen">Erstellte</span> Projekte</h2>
                <#list createdProjects as created>
                <table style="display:inline-block">
                <tr><th><img src="D:\Dev\Projects\projectFunder\src\main\webapp\WEB-INF\${created.kategorie.icon}" alt=${created.kategorie.name}></th></tr>
                <tr><th><a href="ViewProjectServlet?action=viewProject&amp;user=${user}&amp;pid=${created.kennung}" title="View Project">${created.titel}</a></th></tr>
                <tr><td>Aktuell: ${created.totalDonations()} €</td></tr>
                <tr><td>Status: ${created.status}</td></tr>
            	</table> 
            	</#list>
            </div>
    
            <div>
                <h2><span style="color:red">Unterstüzte</span> Projekte</h2>
                <#list supportedProjects as supported>
		        <#if supported.getSichtbarkeit(profile) == "oeffentlich">        
                <table style="display:inline-block">
                <tr><th><img src="D:\Dev\Projects\projectFunder\src\main\webapp\WEB-INF\${supported.kategorie.icon}" alt=${supported.kategorie.name}></th></tr>
                <tr><th><a href="ViewProjectServlet?action=viewProject&amp;user=${user}&amp;pid=${supported.kennung}" title="View Project">${supported.titel}</a></th></tr>
                <tr><td>Limit: ${supported.fl} €</td></tr>
                <tr><td>Status: ${supported.status}</td></tr>
                <tr><td>Gespendet: ${supported.getSpendenbetrag(profile)} €</td></tr>  
            	</table> 
            	</#if>
            	</#list><p></p>
            </div>


</body>
</html>