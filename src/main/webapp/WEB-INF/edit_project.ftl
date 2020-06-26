<!DOCTYPE html>
<html>
<head>
    <title>Projekt Editieren</title>
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
        background-color: cadetblue;
        color: white;
        text-align: center;
        border-color: black;
        padding: 5px 25px;        
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
    <h1>Projekt editieren</h1>
        <div>
			<button style=" background-color:navy;float:left; display:inline;" type="button" onclick="location.href='MainServlet?action=userSelected&amp;user=${user}'">Home</button>
            <button type="button" style="float:right; display:inline;" 
    				onclick="location.href='http://localhost:8080/projectFunder/selectUserPage'" title="Benutzer wählen">Benutzer wählen</button></br></br></div>
      <form method="post" action="EditProjectServlet?action=editProject">
      <fieldset id="editProject">
       <#list result as project>
      <table>
          <tr><th>Titel</th><td><input type="text" name="title" value="${project.titel}" required maxlength="30"/></td></tr>
          <tr><th>Finanzierungslimit</th><td><input type="number" name="fundingLimit" value="${project.fl}" required min="${project.fl}" placeholder="0.00" step="0.01" lang="en"/> €</td></tr>
          
          			
          			  <#if project.kategorie.id==1>
          			  <tr><th style="vertical-align:top">Kategorie</th><td><input type="radio" name="category" value="${project.kategorie.id}" checked="checked" required> ${project.kategorie.name}
          			 <input type="radio" name="category" value="2"> Art & Creative Works<br/> 
          			<input type="radio" name="category" value="3"> Education 
          			<input type="radio" name="category" value="4" > Tech & Innovation</td></tr>
          			 </#if>
          			 <#if project.kategorie.id==2>
          			 <tr><th style="vertical-align:top">Kategorie</th><td><input type="radio" name="category" value="1" required> Health & Wellness 
          			  <input type="radio" name="category" value="${project.kategorie.id}" checked="checked" required> ${project.kategorie.name}
          			<input type="radio" name="category" value="3"> Education 
          			<input type="radio" name="category" value="4" > Tech & Innovation</td></tr>
          			 </#if>
          			 <#if project.kategorie.id==3>
          			 <tr><th style="vertical-align:top">Kategorie</th><td><input type="radio" name="category" value="1" required> Health & Wellness 
          			  <input type="radio" name="category" value="2"> Art & Creative Works<br/> 
          			<input type="radio" name="category" value="${project.kategorie.id}" checked="checked" required> ${project.kategorie.name}
          			<input type="radio" name="category" value="4" > Tech & Innovation</td></tr>
          			 </#if>
          			 <#if project.kategorie.id==4>
          			 <tr><th style="vertical-align:top">Kategorie</th><td><input type="radio" name="category" value="1" required> Health & Wellness 
          			  <input type="radio" name="category" value="2"> Art & Creative Works<br/> 
          			<input type="radio" name="category" value="3"> Education 
          			<input type="radio" name="category" value="${project.kategorie.id}" checked="checked" required> ${project.kategorie.name}
          			 </#if>
          <tr><th style="vertical-align:top">Vorgänger</th><td>
        <#if UserProjects??>

        	<#list UserProjects as UserProject>
          		<input type="radio" name="vorg" value="${UserProject.kennung}"> ${UserProject.titel} <br/>
        	</#list>
        </#if>
        <#if titel??>
		  <input type="radio" name="vorg" value="${project.vorganger}" checked="checked" required> ${titel}  <br/></td></tr>
		  <#else>
        	 <input type="radio" name="vorg" value="0" checked="checked" required>Kein Vorgänger   <br/></td></tr>
        	</#if>
          <tr><th style="vertical-align:top">Beschreibung</th><td><textarea name="desc" rows="7" cols="35">${project.beschreibung}</textarea></td></tr>
      </table>
      <input type="hidden" value="${project.kennung}" name="pid">
      </#list>
          <p></p>
      </fieldset>
      <input type="hidden" value="${user}" name="user"></br>
    <button type="button" style="background-color:navy; float: left;" 
        	onclick="location.href='http://localhost:8080/projectFunder/ViewProjectServlet?action=viewProject&amp;user=${user}&amp;pid=${pid}'">Zurück</button>
    <button type="submit" style="float: right">Aktualisieren</button>
    </form><p></p>        
</body>
</html>