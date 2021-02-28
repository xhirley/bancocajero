<%-- marco de trabajo getbootstrap--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilo.css" >

        <title>Menu Principal</title>


    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-navbar">            
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a href="Controlador?menu=Inicio" target="myFrame"  class="btn btn-outline-light" style="margin-left:10px; border:none">Inicio </a>
                    </li>
                    <c:if test = "${usuario.isEsadmin()}">                    
                        <li class="nav-item">
                            <a  href="Controlador?menu=Banco&accion=Listar" target="myFrame" class="btn btn-outline-light" style="margin-left:10px; border:none">Banco</a>
                        </li>
                        <li class="nav-item">
                            <a  href="Controlador?menu=Cliente&accion=Listar" target="myFrame" class="btn btn-outline-light" style="margin-left:10px; border:none">Clientes</a>
                        </li>
                        <li class="nav-item">
                            <a  href="Controlador?menu=Registrar_C&accion=Listar&filtro=" target="myFrame" class="btn btn-outline-light" style="margin-left:10px; border:none">Registrar cuentas</a>
                        </li> 
                    </c:if>
                    <li class="nav-item">
                        <a  href="Controlador?menu=Saldos&accion=Listar&idCliente=${usuario.getId()}" target="myFrame" class="btn btn-outline-light" style="margin-left:10px; border:none">Saldos</a>
                    </li> 

                </ul>
            </div>
            <div class="dropdown dropleft">
                <button class="btn btn-outline-light dropdown-toggle" style="border:none" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${usuario.getNombres()}
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="#">
                        <img src="img/user.jpg" alt="Usuario" width="60"/>
                    </a>
                    <a class="dropdown-item" href="#">${usuario.getUsuario()}</a>
                    <a class="dropdown-item" href="#">${usuario.getCorreo()}</a>
                    <div class="dropdown-divider"></div>
                    <form action="Validar" method="POST">
                        <button name="accion" value="Salir" class="dropdown-item" >Salir</button>
                    </form>

                </div>
            </div>
        </nav>
        <div class="m-4" style="height: 600px">
            <iframe name="myFrame" style="height: 100%; width: 100%; border:none;" src="inicio.jsp"></iframe>
        </div>




        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
