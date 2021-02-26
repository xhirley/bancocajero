<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>cuentas</title>
        <style>
            @media print{
                .parte01, .btn, .ae{
                    display: none;
                }  
            }
        </style>
    </head>
    <body>
        <div class="d-flex">
            <div class="col-sm-4 parte01"> 
                <div class="card">
                    <form action="Controlador?menu=Registrar_Cuentas" method="post">
                        <div class="card-body"> 
                            <div class="form-group">
                                <label>Datos de Cliente </label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="codigocliente" class="form-control" placeholder="Codigo">
                                    <input type="submit" name="accion" value="BuscarCliente" class=" btn btn-outline-info">
                                </div>
                                <div class="col-sm-6">
                                    <input type=" text" name="nombrescliente" class="form-control" placeholder="Datos cliente">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Datos del Banco </label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="codigobanco" value="${ban.getId()}" class="form-control" placeholder="Codigo">
                                    <button type="submit" name="accion" value="BuscarBanco" class=" btn btn-outline-info">Buscar</button>
                                </div>
                                <div class="col-sm-6">
                                    <input type=" text" name="nombrebanco" value="${ban.getRazon()}" class="form-control" placeholder="Datos banco">
                                </div>
                            </div>
                                <div class="form-group">
                                <label>Datos de la cuenta </label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="codigocuenta" value="${cuenta.getId()}" class="form-control" placeholder="Codigo">
                                    <button type="submit" name="accion" value="BuscarCuenta" class=" btn btn-outline-info">Buscar</button>
                                </div>
                                <div class="col-sm-6">
                                    <input type=" text" name="nombrebanco" value="${cuent.getRazon()}" class="form-control" placeholder="Datos Cuenta">
                                </div>
                            </div>
                            <div class="form-group d-flex">
                                  <div class="col-sm-3">
                                    <input type=" text" name="tipo" value="${cuenta.getTipo()}" class="form-control" placeholder="Tipo">
                                </div>
                                <div class="col-sm-6 d-flex">
                                    <input type=" number" name="saldo" value="${cuenta.getSaldo()}" class="form-control" placeholder="Saldo">
                                </div>
                            </div>
                            <!--BOTON AGREGAR  -->
                            <div class="form-group">
                                <button type="submit" name="accion" value="AgregarCuenta" class="btn btn-outline-info">Agregar Cuenta</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-sm-7">
                <div class="card"> 
                    <div class="card-body"> 
                        <div class="d-flex col-sm-5 ml-auto">
                            <label>Serie</label>
                            <input type=" text" name="NroSerie" value="${nserie}"class="form-control">
                        </div>
                        <br>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Nro</th>
                                    <th>banco</th>
                                    <th>cliente</th>
                                    <th>tipo</th>
                                    <th>Saldo</th>
                                    <th class="ae" >Acciones</th>
                                </tr>
                            </thead>
                           
                        </table>
                    </div>
                    <div class="card-footer d-flex">
                        <div class="col-sm-8">
                            <a href="Controlador?menu=Registrar_Cuentas&accion=Registrar_Cuentas"  onclick="print()"class="btn btn-outline-success">Generar Cuenta</a>
                            <input type=" submit" name="accion" value="Cancelar" class="btn btn-outline-danger">
                        </div>
                     
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
