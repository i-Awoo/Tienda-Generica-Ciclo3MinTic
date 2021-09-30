<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/lux/bootstrap.min.css">
    <script src="js/productos.js"></script>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary" style="text-align: center;">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Tienda Virtual</a>
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="usuarios.jsp">Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="clientes.jsp">Clientes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="proveedores.jsp">Proveedores</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="productos.jsp">Productos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Ventas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Reportes</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="row align-items-center mt-4 text-center justify-content-center">
        <form name="formulario" class="col-lg-7" action="tgServlet_productos?accion=add" method="POST" enctype="multipart/form-data">
           		<div class="form-group">
                    <label for="formFile" class="form-label mt-4">Nombre del Archivo</label>
                   
                    <input class="form-control" name="archivo" type="file" id="formFile" onchange="cargarArchivo(this)">
                    <button class="btn btn-success mt-3" name="proceso" value="Procesar" type="submit" id="btnSubir" disabled >Cargar</button>
                </div>
               <input type="hidden" name="nombre" value=""/>
        </form>
        <iframe name="null" style="display:non;"></iframe><!-- mensajes-->
        </div>
    </div>