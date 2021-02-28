<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" 
              integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>banco</title>
    </head>
    <body>
        <div class="d-flex"> 
            <div class="card col-sm-4">
                <div class="card-body" >
                    <form action="Controlador?menu=Banco" method="POST">
                        <input type="hidden" value="${ban.getId()}" name="txtId" class="form-control">  
                       
                        <div class="form-group">
                            <label>Razon Social:</label>
                            <input type="text" value="${ban.getRazon()}" name="txtRazon" class="form-control">
                        </div>
                       
                        <input type="hidden" value="${ban.getLogo()}" name="txtLogo" class="form-control">
                       
                        <c:choose>
                            <c:when test="${ban.getRazon() == null}"> 
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
                            <th>id</th>
                            <th>RAZON</th>
                            <th>LOGO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="banco" items="${bancos}">
                            <tr>
                                <td>${banco.getId()}</td>
                                <td>${banco.getRazon()}</td>
                                <td>${banco.getLogo()}</td>  
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Banco&accion=Editar&id=${banco.getId()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Banco&accion=Eliminar&id=${banco.getId()}">delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
            </div>
        </div>       
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
