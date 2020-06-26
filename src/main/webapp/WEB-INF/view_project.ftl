<!DOCTYPE html>
<html>
<head>
    <title>Projekt Details</title>
    <style type="text/css">
          h1{
            text-align: center;
            font-size: 50px;
            font-family:'Roboto',sans-serif;
            color: navy;  
        	}
         
         a{
            color: black;
            text-decoration: none; 
        }
        a:hover{
            color: navy;
        }
        
        body{
            font-family:'Roboto',sans-serif;
        }
        
        img{
            height: 90px;
            width: 90px;
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
    </style>
    <script>
		function myFunction() {
		  var txt;
		  if (confirm("Sind Sie sicher, dass Sie dieses Projekt löschen möchten?")){
		    location.href='DeleteServlet?action=delete&amp;user=${user}&amp;profile=${project.ersteller.email}&amp;pid=${project.kennung}';
		  } else {
		    txt = "Cancel!";
		  }
		}
	</script>
</head>
    
<body>
	<div><h1>ProjectFunder</h1>
	<button type="button" onclick="location.href='MainServlet?action=userSelected&amp;user=${user}'" 
		style="background-color:navy; float:left">Home</button>
    <button type="button" style="background-color: cadetblue; float:right;" 
    	onclick="location.href='http://localhost:8080/projectFunder/selectUserPage'" title="Benutzer wählen">Benutzer wählen</button><br></div> 
    <h2 style="color: navy">Informationen</h2>
    <div align="center" style="border-bottom: 1px solid black">
        <table>
            <tr><th><img src="D:\Dev\Projects\projectFunder\src\main\webapp\WEB-INF\${project.kategorie.icon}" alt=${project.kategorie.name}></th></tr>
            <tr><th>${project.titel}</th></tr>
            <tr><td><center>von <a href="ViewProfileServlet?action=viewProfile&amp;user=${user}&amp;profile=${project.ersteller.email}" title="View Profile">${project.ersteller.name}</a></center></td></tr>
            <tr><td style="text-align: center; font-size:15px;">${project.beschreibung}</td></tr>
            <tr><th style="float: left">Finanzierungslimit: ${project.fl} €</th></tr>
            <tr><th style="float: left">Aktuelle Spendensumme: ${project.totalDonations()} €</th></tr>
            <tr><th style="float: left">Status: ${project.status}</th></tr>
            <#if titel??>
            <tr><th style="float: left">Vorgänger-Projekt: <a href="ViewProjectServlet?action=viewProject&amp;user=${user}&amp;pid=${project.vorganger}" title="View Project">${titel}</th></tr>
        	<#else>
        	<tr><th style="float: left">Vorgänger-Projekt: Kein Vorgänger vorhanden</th></tr>
        	</#if>
        </table><p></p>
         
    </div>
    <h2 style="color: navy">Aktionsleiste</h2>
    <div align="center" style="border-bottom: 1px solid black">
        <button type="button" onclick="location.href='DonateServlet?action=donate&amp;user=${user}&amp;profile=${project.ersteller.email}&amp;pid=${project.kennung}'" style="background-color:green">Spenden</button>
        <button type="button" onclick="myFunction()" style="background-color:red">Projekt Löschen</button>
        <button type="button" onclick="location.href='EditProjectServlet?action=editProject&amp;user=${user}&amp;profile=${project.ersteller.email}&amp;pid=${project.kennung}' " style="background-color:navy">Projekt Editieren</button><p></p>
    </div>
    
    
    <h2 style="color: navy">Liste der Spender</h2>
    <div style="border-bottom: 1px solid black">
    <#list spende as spende>
        <table>
        <#if spende.sichtbarkeit == "oeffentlich">
            <tr><td>${spende.benutzer.name} : ${spende.spendenbetrag} €</td></tr>
        <#else>
        	<tr><td>Anonym : ${spende.spendenbetrag} €</td></tr>
        </#if>           
        </table><p></p>
        </#list>
    </div>
    <h2 style="color: navy">Kommentare</h2>
    <div>
        <#list kommentar as kommentar>
        <table>
        <#if kommentar.sichtbarkeit == "oeffentlich">
            <tr><td>${kommentar.benutzer.name} : ${kommentar.text} </td></tr>
        <#else>
        	<tr><td>Anonym : ${kommentar.text} </td></tr>
        </#if> 
        </table>
        </#list>

        <button type="button" onclick="location.href='NewKommentar?action=newComment&amp;user=${user}&amp;pid=${project.kennung}'" style="background-color:cadetblue; float:right">Kommentieren</button>
        <p></p>
    </div>
    

</body>
</html>