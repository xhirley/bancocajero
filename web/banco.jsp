<%-- 
    Document   : banco
    Created on : 22/02/2021, 21:55:15  
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilo.css" >

        <title>Bancos</title>
    </head>
    <body>
        <div class="d-flex"> 
            <div class="card col-sm-6">
                <div class="card-body">
                    <form>
                        <div class="form-group">
                            <labe>Razón Social:</labe>
                            <input type="text" name="txtRazon" class="form-control">
                        </div>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-primary"
                    </form>
                </div>
            </div>
            <div class="col-sm-6">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Razón Social</th>
                        </tr>
                    </thead>

                </table>
            </div>
        </div>

    </body>
</html>
