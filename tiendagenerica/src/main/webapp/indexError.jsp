<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/estilos.css">
<meta charset="UTF-8">
<title>Tienda Genérica</title>
</head>
<body>
        <div class="container1">
		<br><br>
		<h1>Bienvenidos a Tienda Genérica</h1>
        <form action="./tgServlet" method="post">
            Usuario 
            <input type="text" name="usuario" class="uno"><br><br>
            Contraseña  
            <input type="password" name="password" class="dos"><br>
            <br>
            <div class="boton1">
            <button type="submit">Aceptar</button>
            <button type="reset">Cancelar</button>
            </div>
        </form>
        <p style="color:red">Usuario no existe, o credenciales incorrectas</p>
        </div>
</body>
</html>