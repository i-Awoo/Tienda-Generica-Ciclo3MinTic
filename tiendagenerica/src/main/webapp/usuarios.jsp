<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
            <link rel="stylesheet" type="text/css" href="css/estilos.css">
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Tienda genérica</title>
</head>
<body>
            <header class="header">
                <div class="container logo-nav-container">
                    <a href="tiendagenerica.jsp" class="logo">Tienda genérica</a>
                </div>
                
           		 <span class="menu-icon">Ver menú</span>
            </header>
           		 <nav class="navigation">
					<div class="container logo-nav-container2">
                        <ul>
                        <li><a href="usuarios.jsp">Usuarios</a></li>
                        <li><a href="clientes.jsp">Clientes</a></li>
                        <li><a href="proveedores.jsp">Proveedores</a></li>
                        <li><a href="#">Productos</a></li>
                        <li><a href="#">Ventas</a></li>
                        <li><a href="#">Reportes</a></li>
                        </ul>
					</div>	
                </nav>
                
                
            <main class="main">
                <div class="container">
                    <div class="container1">
                    <form action="./tgServlet_usuario_crud" method="post">
                        <div class="usuario1">
                            Cédula 
                            <input type="text" name="cedula" class="tres"><br>
                            Nombre Completo  
                            <input type="text" name="nombre" class="dos"><br>
                            Correo electrónico
                            <input type="email" name="email" class="dos"><br>
                        </div>
                        <div class="usuario2">
                            Usuario 
                            <input type="text" name="usuario" class="cuatro"><br>
                            Contraseña  
                            <input type="password" name="password" class="dos"><br>
                        </div>
                        <br><br><br><br><br>
                        <div class="boton1">
                            <button name= 'button' type="submit" value="0">Consultar</button>
                            <button name= 'button' type="submit" value="1">Crear</button>
                            <button name= 'button' type="submit" value="2">Actualizar</button>
                            <button name= 'button' type="submit" value="3">Borrar</button>
                        </div>
                    </form>
                    <p style="color:${color}">${mensaje}</p>
                    </div>
                </div>
            </main>
            <footer class="footer">
                <div class="container">
                    <p>Página diseñada por GRUPO 01-17</p>
                </div>
            </footer>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="js/scripts.js"></script>
</body>
</html>