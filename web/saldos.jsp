
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilo.css" >
        <title>saldos</title>
    </head>
    <body>
       
         <div class="d-flex"> 
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="Controlador?menu=Saldo" method="POST" >  
                        <input type="hidden" name="txtId"  class="form-control">
                        <div class="form-group">
                            <label>Numero Cuenta:</label>
                            <input type="text" name="txtNumero"  class="form-control" >
                        </div>
                        <div class="form-group">
                            <label>Tipo:</label>
                            <input type="text" name="txtTipo" class="form-control" >
                        </div>
                        <div class="form-group">
                            <label>Saldo:</label>
                            <input type="number" name="txtSaldo"  class="form-control" >
                        </div>

                        <c:choose>
                            <c:when test="${cuenta.getNumero() == null}"> 
                                <input type="submit" name="accion" value="Ver todas mis cuentas" class="btn btn-primary">
                            </c:when>                                    
                            <input type="submit" name="accion" value="Tranferir" class="btn btn-primary">
                        </c:choose>
                    </form>
                </div>
            </div>
                                
         </div>
                               
  
                                
            <div class="col-sm-5">
               
                <table class="table table-hover table-striped ">
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Descripcion</th>
                            <th>Monto</th>
                            <th>Saldo</th>
                        </tr>
                    </thead>
                  
                </table>
            </div>
        </div>
        
        
        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
