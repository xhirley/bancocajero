<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Saldos</title>
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
                <div class="card p-3">
                    <h2>Mis Cuentas </h2>
                    <c:forEach var="cta" items="${cuentas}">
                        <form action="Controlador?menu=Saldos" method="post">
                            <input type="hidden" name="ctaNumero" value="${cta.getNumero()}"class="form-control" placeholder="Numero Cta">
                            <input type="hidden" name="idCuenta" value="${cta.getId()}"class="form-control" placeholder="Id Cta">
                            <input type="hidden" name="idCliente" value="${idCliente}"class="form-control" placeholder="Id Cliente">

                            <div class="card mb-4 box-shadow">
                                <div class="card-header">
                                    <h4 class="my-0 font-weight-normal">${cta.getTipo()} NRO ${cta.getNumero()}</h4>
                                </div>
                                <div class="card-body">
                                    <h1 class="card-title pricing-card-title">$${cta.getSaldo()} <small class="text-muted">/ US</small></h1>

                                    <button type="submit" name="accion" value="ListarTransacciones" class="btn btn-lg btn-block btn-outline-primary">Ver Transacciones</button>
                                </div>
                                <div id="accordion${cta.getId()}">
                                    <div class="card-body d-flex">
                                        <button id="btn1${cta.getId()}" type="button" class="btn btn-success" data-toggle="collapse" data-target="#deposito${cta.getId()}"  aria-expanded="false" aria-controls="deposito${cta.getId()}">Depósitos</button>
                                        <button id="btn2${cta.getId()}" type="button" class="btn btn-danger ml-1" data-toggle="collapse" data-target="#retiro${cta.getId()}"  aria-expanded="false" aria-controls="retiro${cta.getId()}">Retiros</button>
                                        <button id="btn3${cta.getId()}" type="button" class="btn btn-info ml-1" data-toggle="collapse" data-target="#transferencia${cta.getId()}"  aria-expanded="${expande && cta.getId()== idCuenta}" aria-controls="transferencia${cta.getId()}">Transferencias</button>                                    
                                    </div>
                                    <div  id="deposito${cta.getId()}" class="collapse" aria-labelledby="btn1${cta.getId()}" data-parent="#accordion${cta.getId()}">
                                        <div class="card card-body">
                                            <h4>Deposito</h4>
                                            <div class="form-group d-flex">                                                
                                                <input type="number" name="txtValorDeposito" value="1" step=0.01 min="1" max="999999" class="form-control">
                                                <button type="submit" name="accion" value="Depositar" class=" btn btn-success">Depositar</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="retiro${cta.getId()}" class="collapse" aria-labelledby="btn2${cta.getId()}" data-parent="#accordion${cta.getId()}">
                                        <div class="card card-body">
                                            <h4>Retiro</h4>
                                            <div class="form-group d-flex">                                               
                                                <input type="number" name="txtValorRetiro" value="1" min="1" max="999999" step=0.01 class="form-control">
                                                <button type="submit" name="accion" value="Retirar" class=" btn btn-danger">Retirar</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="transferencia${cta.getId()}" class="collapse ${expande && cta.getId()== idCuenta ? 'show' :''}" aria-labelledby="btn3${cta.getId()}" data-parent="#accordion${cta.getId()}">
                                        <div class="card card-body">
                                            <h4>Transferencia</h4>
                                            <div class="form-group d-flex">
                                                <div class="col-sm-4 pr-1">
                                                    <strong>Cta Destino:</strong>
                                                </div>
                                                <div class="col-sm-8 d-flex">
                                                    <input type="text" name="ctaBusq" value="${ctaBusq.getNumero()}" class="form-control" placeholder="Cuenta">
                                                    <button type="submit" name="accion" value="BuscarCuenta" class=" btn btn-outline-info">Buscar</button>
                                                </div>                                                
                                            </div>
                                            <div class="form-group d-flex">
                                                <div class="col-sm-8 pr-1">
                                                    <input type=" text" name="nombrescliente" value="${ctaBusq.getCliente().getNombreCompletos()}" class="form-control" placeholder="Datos cliente" readonly="">
                                                </div>
                                                <div class="col-sm-4 pl-1">
                                                    <input type=" text" name="tipocuenta" value="${ctaBusq.getTipo()}" class="form-control" placeholder="Tipo Cuenta" readonly="">
                                                </div>
                                            </div>
                                            <div class="form-group d-flex">                                               
                                                <input type="number" name="txtValorTransferencia" value="1" min="1" max="999999" step=0.01 class="form-control">
                                                <button type="submit" name="accion" value="Transferir" class=" btn btn-info">Transferir</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:forEach>
                </div>
            </div>
            <div class="col-sm-7">
                <div class="card"> 
                    <div class="card-body"> 
                        <c:choose>
                            <c:when test="${mostrarmensaje == 'ok'}"> 
                                <div class="alert alert-success" role="alert">
                                    <h4 class="alert-heading">Transacción Exitosa!</h4>  
                                    <hr>
                                    <p class="mb-0">${mensaje}</p>
                                </div>
                            </c:when> 
                            <c:when test="${mostrarmensaje == 'error'}"> 
                                <div class="alert alert-danger" role="alert">
                                    <h4 class="alert-heading">Error!</h4>    
                                    <hr>
                                    <p class="mb-0">${mensaje}</p>
                                </div>
                            </c:when> 
                            <c:otherwise>                                
                            </c:otherwise>   
                        </c:choose>


                        <form action="Controlador?menu=Saldos" method="post">                            
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Fecha</th>
                                        <th>Acción</th>
                                        <th>Tipo</th>
                                        <th>Valor</th>
                                        <th>Banco Transferencia</th>
                                        <th>Tipo Cta Transferencia</th>
                                        <th>Cta Transferencia</th>                                       
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="t" items="${transacciones}">
                                        <tr>
                                            <td>${t.getId()}</td>
                                            <td><fmt:formatDate type = "both" value = "${t.getFechahora()}" /></td>
                                            <td>${t.getAccion()}</td>
                                            <td>${t.getTipo()}</td>
                                            <td><fmt:setLocale value = "es_EC"/><fmt:formatNumber value = "${t.getMonto()}" type = "currency"/>
                                            <td>${t.getBancotransfer()}</td>
                                            <td>${t.getTipoctatransfer()}</td>
                                            <td>${t.getNroctatransfer()}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </form>
                    </div>
                    <div class="card-footer d-flex">
                        <div class="col-sm-8">
                            <a href="#"  onclick="print()"class="btn btn-outline-success">Imprimir</a>        
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
