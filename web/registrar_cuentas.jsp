<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Cuentas</title>
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
                    <form action="Controlador?menu=Registrar_C" method="post">
                        <div class="card-body"> 
                            <div class="form-group">
                                <label>Datos de Cliente </label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="cedula" value="${cta.getCliente().getCedula()}"class="form-control" placeholder="Cédula">
                                    <button type="submit" name="accion" value="BuscarCliente" class=" btn btn-outline-info">Buscar</button>
                                </div>
                                <div class="col-sm-6">
                                    <input type=" text" name="nombrescliente" value="${cta.getCliente().getNombreCompletos()}" class="form-control" placeholder="Datos cliente" readonly="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Datos del Banco </label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="codigobanco" value="${cta.getBanco().getId()}" class="form-control" placeholder="Codigo" >
                                    <button type="submit" name="accion" value="BuscarBanco" class=" btn btn-outline-info">Buscar</button>
                                </div>
                                <div class="col-sm-6">
                                    <input type=" text" name="nombrebanco" value="${cta.getBanco().getRazon()}" class="form-control" placeholder="Datos banco" readonly="">
                                </div>
                            </div>
                        </div>
                    </form>

                    <form action="Controlador?menu=Registrar_C" method="post">
                        <input type="hidden" name="banId" value="${cta.getBanco().getId()}"class="form-control" placeholder="Cédula">
                        <input type="hidden" name="cliId" value="${cta.getCliente().getId()}" class="form-control" placeholder="Codigo">

                        <div class="card-body"> 
                            <div class="form-group">
                                <label>Datos de la Cuenta </label>
                            </div>
                            <div class="form-group">
                                <label>Tipo:</label>
                                <select class="form-control"  name="txtTipo" id="txtEsadmin" required="">
                                    <option value="AHORROS" ${cta.getTipo().equals('AHORROS')  ? 'selected' : ''}>AHORROS</option>
                                    <option value="CORRIENTE" ${cta.getTipo().equals('CORRIENTE')  ? 'selected' : ''}>CORRIENTE</option>                             
                                </select>
                            </div>

                            <div class="form-group d-flex">
                                <div class="col-sm-6">
                                    <label>Número Cta: </label>
                                    <input type="text" name="txtNumero" onkeypress="return valideKey(event);"  maxlength="8" value="${cta.getNumero()}" class="form-control" required="">
                                </div>
                                <div class="col-sm-6">
                                    <label>Número Cta: </label>
                                    <input type=" number" name="saldo" value="${cta.getSaldo()}" class="form-control" placeholder="Saldo" readonly>
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
                        <form action="Controlador?menu=Registrar_C" method="post">
                            <div class="form-group">
                                <div class="col-sm-12 d-flex">
                                    <label>Búscar por Cédula o Nombre: </label>
                                    <input type=" text" name="filtro" value="${nfiltro}"class="form-control">
                                    <button type="submit" name="accion" value="Listar" class="btn btn-outline-info">Buscar</button>
                                </div>                           
                            </div>                       
                            <br>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Banco</th>
                                        <th>Cédula</th>
                                        <th>Cliente</th>
                                        <th>Tipo Cta</th>
                                        <th>Nro Cta</th>
                                        <th>Saldo</th>
                                        <th class="ae" >Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="c" items="${cuentas}">
                                        <tr>
                                            <td>${c.getId()}</td>
                                            <td>${c.getBanco().getRazon()}</td>
                                            <td>${c.getCliente().getCedula()}</td>
                                            <td>${c.getCliente().getNombreCompletos()}</td>
                                            <td>${c.getTipo()}</td>
                                            <td>${c.getNumero()}</td>
                                            <td>${c.getSaldo()}</td>                                                                   
                                            <td class="d-flex">
                                                <a href="Controlador?menu=Registrar_C&accion=Editar&id=${c.getId()}" class="btn btn-warning"> Editar</a>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </form>
                    </div>                   
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
   <script type="text/javascript">
		function valideKey(evt){
			
			// code is the decimal ASCII representation of the pressed key.
			var code = (evt.which) ? evt.which : evt.keyCode;
			
			if(code==8) { // backspace.
			  return true;
			} else if(code>=48 && code<=57) { // is a number.
			  return true;
			} else{ // other keys.
			  return false;
			}
		}
		</script>
    </body>
</html>
