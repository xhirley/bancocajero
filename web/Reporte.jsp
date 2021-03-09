
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage ="error.jsp "%>
<%@page import="java.sql.*" %> 

<%@ page import="java.util.*" %> 
<%@ page import="java.io.*" %> 



<%@page import = " net.sf.jasperreports.engine.*"%>
<%@page import = " net.sf.jasperreports.engine.util.*"%>
<%@page import = " net.sf.jasperreports.engine.export.*"%>
<%@page import = " javax.naming . * "%>

<% /*Parametros para realizar la conexión*/ 



            Connection conn= null ;
            String urlDatabase =  "jdbc:postgresql://localhost:5432/banco"; 
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(urlDatabase,"postgres", "123");
            } catch (Exception e) {
                System.out.println("Ocurrio un error : "+e.getMessage());
            }
            System.out.println("La conexión se realizo sin problemas! =) ");


/*Establecemos la ruta del reporte*/ 
File reportFile = new File(application.getRealPath("Rep_cuenta.jasper")); 
/* No enviamos parámetros porque nuestro reporte no los necesita asi que escriba 
cualquier cadena de texto ya que solo seguiremos el formato del método runReportToPdf*/ 
Map<String,Object> parameter = new HashMap<String,Object>(); 
/*Enviamos la ruta del reporte, los parámetros y la conexión(objeto Connection)*/ 
byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (), parameter,conn); 
/*Indicamos que la respuesta va a ser en formato PDF*/
response.setContentType("application/pdf");
response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length); 
/*Limpiamos y cerramos flujos de salida*/
ouputStream.flush(); 
ouputStream.close();

 %>
