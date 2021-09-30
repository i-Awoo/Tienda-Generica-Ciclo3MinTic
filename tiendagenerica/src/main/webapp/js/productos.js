window.addEventListener('DOMContentLoaded', inicio, false);

function inicio(){
    document.getElementById('formFile').addEventListener('change', subirArchivo, false);
}
function subirArchivo(){
    let ref = document.getElementById('formFile'); //referencia al input file
    let btn = document.getElementById('btnSubir'); //referencia al boton
    //condicional que valida si hay un archivo cargado.
    if(ref.value !== ''){        //en caso de que sea diferente a vacio, re habilita el botón
        btn.removeAttribute('disabled');
    }else{ //en caso que no haya un archivo cargado el boton queda inhabilitado
        btn.setAttribute("disabled", "true");
		alert("No se ha cargado un archivo")
    }
}
function cargarArchivo(elemento){
	var file = elemento.files[0];
	var objHidden = document.formulario.nombre;
	objHidden.value = file.name; //almacenar el nombre del archivo
	document.formulario.target = "null";
	document.formulario.action = "tgServlet_productos";
	//document.formulario.submit(); //el archivo se guarda en un directorio por medio del servlet
	//alert("Archivo cargado con éxito");
}