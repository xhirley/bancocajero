<%-- 
    Document   : cliente
    Created on : 22/02/2021, 21:55:25  
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilo.css" >
        <title>Clientes</title>
    </head>
    <body>
        <div class="d-flex"> 
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="Controlador?menu=Cliente" method="POST">  
                        <input type="hidden" name="txtId" value="${cliente.getId()}" class="form-control">
                        <div class="form-group">
                            <label>Cédula:</label>
                            <input type="text" name="txtCedula" value="${cliente.getCedula()}" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label>Nombres:</label>
                            <input type="text" name="txtNombres" value="${cliente.getNombres()}" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label>Apellidos:</label>
                            <input type="text" name="txtApellidos" value="${cliente.getApellidos()}" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label>Correo:</label>
                            <input type="email" name="txtCorreo" value="${cliente.getCorreo()}" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label>Celular:</label>
                            <input type="text" name="txtCelular" value="${cliente.getCelular()}" class="form-control" >
                        </div>
                        <div class="form-group">
                            <label>Es administrador:</label>
                            <select name="txtEsadmin" id="txtEsadmin" required="">
                                <option value="true" ${cliente.isEsadmin()  ? 'selected' : ''}>SI</option>
                                <option value="false" ${!cliente.isEsadmin()  ? 'selected' : ''}>NO</option>                             
                            </select>
                        </div>
                        <c:choose>
                            <c:when test="${cliente.getCedula() == null}"> 
                                <input type="submit" name="accion" value="Agregar" class="btn btn-primary">
                            </c:when>                                    
                            <c:otherwise>
                                <input type="submit" name="accion" value="Nuevo" class="btn btn-primary">
                                <input type="submit" name="accion" value="Actualizar" class="btn btn-warning">
                            </c:otherwise>   
                        </c:choose>
                    </form>
                </div>
            </div>
            <div class="col-sm-8">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Cédula</th>
                            <th>Nombres</th>
                            <th>Apellidos</th>
                            <th>Correo</th>
                            <th>Celular</th>
                            <th>Es Administrador</th>
                            <th>Usuario</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${clientes}">
                            <tr>
                                <td>${c.getId()}</td>
                                <td>${c.getCedula()}</td>
                                <td>${c.getNombres()}</td>
                                <td>${c.getApellidos()}</td>
                                <td>${c.getCorreo()}</td>
                                <td>${c.getCelular()}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${c.isEsadmin()}"> SI</c:when>                                    
                                        <c:otherwise>NO</c:otherwise>   
                                    </c:choose>
                                </td>
                                <td>${c.getUsuario()}</td>                                
                                <td>
                                    <a href="Controlador?menu=Cliente&accion=Editar&id=${c.getId()}" class="btn btn-warning"> Editar</a>
                                    <a href="Controlador?menu=Cliente&accion=Eliminar&id=${c.getId()}" class="btn btn-danger"> Eliminar</a>                                 
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="cargarUsuario(${c.getId()},'${c.getUsuario()}' )">Usuario</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
        <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="myModal">
            <div class="modal-dialog modal-md">
                <div class="modal-content">
                    <div class="card">
                        <div class="card-body">
                            <form action="Controlador?menu=Cliente" method="POST">  
                                <input type="hidden" name="txtId2"  id="txtId2" value="" class="form-control">
                                <div class="form-group">
                                    <label>Usuario</label>
                                    <input type="text" name="txtUsuario" id="txtUsuario" value="" class="form-control" required="">
                                </div>
                                <div class="form-group">
                                    <label>Clave:</label>
                                    <input type="password" name="txtClave" id="txtClave" value="" class="form-control" required="">
                                </div>
                                 <input type="submit" name="accion" value="GuardarUsuario" class="btn btn-primary">                                
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <script>
            function cargarUsuario(id, usuario){
                $('#txtId2').val(id);
                $('#txtUsuario').val(usuario);
            }
        </script>
    </body>
</html>
