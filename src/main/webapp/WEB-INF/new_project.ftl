<!DOCTYPE html>
<html>
<head>
    <title>Projekt Erstellen</title>
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
            border-spacing: 20px;
        }
}
    </style>
</head>

<body>
    <h1>Projekt erstellen</h1>
    <div>
			<button style="float:left; display:inline;" type="button" onclick="location.href='MainServlet?action=userSelected&amp;user=${user}'">Home</button>
            <button type="button" style="background-color: cadetblue; float:right; display:inline;" 
    				onclick="location.href='http://localhost:8080/projectFunder/selectUserPage'" title="Benutzer wählen">Benutzer wählen</button></br></br></div>
      <form method="post" action="NewProjectServlet?action=newProject">
      <fieldset id="newProject">
      <table>
          <tr><th>Titel</th><td><input type="text" name="title" required maxlength="30"/></td></tr>
          <tr><th>Finanzierungslimit</th><td><input type="number" name="fundingLimit" required min="100" placeholder="0.00" step="0.01"/> €</td></tr>
          <tr><th style="vertical-align:top">Kategorie</th><td><input type="radio" name="category" value="1" required> Health & Wellness <input type="radio" name="category" value="2"> Art & Creative Works<br/> <input type="radio" name="category" value="3"> Education <input type="radio" name="category" value="4"> Tech & Innovation</td></tr>
          <tr><th style="vertical-align:top">Vorgänger</th><td>
        <#if UserProjects??>

        	<#list UserProjects as UserProject>
          		<input type="radio" name="vorg" value="${UserProject.kennung}"> ${UserProject.titel} <br/>
        	</#list>
        </#if>
		  <input type="radio" name="vorg" value="0" required> Kein Vorgänger <br/></td></tr>
          <tr><th style="vertical-align:top">Beschreibung</th><td><textarea name="desc" rows="7" cols="35"></textarea></td></tr>
      </table>
          <p></p>
      </fieldset>
      <input type="hidden" value="${user}" name="user">
    <button type="submit" style="float: right">Erstellen</button>
    </form><p></p>        
</body>
</html>