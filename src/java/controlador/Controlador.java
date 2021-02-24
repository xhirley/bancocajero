/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import config.Encripta;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.ClienteDAO;

/**
 *
 * @author lguzman
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        System.out.println(menu + " - " + accion);
        switch (menu) {
            case "Principal":
                request.getRequestDispatcher("principal.jsp").forward(request, response);
                break;
            case "Banco":
                switch (accion) {
                    case "Listar":
                        break;
                    case "Agregar":
                        break;
                    case "Editar":
                        break;
                    case "Eliminar":
                        break;
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("banco.jsp").forward(request, response);
                break;
            case "Cliente":
                try {
                    ClienteDAO clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
                    Cliente cliente = new Cliente();
                    int idCliente = 0;
                    switch (accion) {
                        case "Listar":
                        case "Nuevo":
                            List lista = clienteDAO.listar();
                            request.setAttribute("clientes", lista);
                            request.getRequestDispatcher("clientes.jsp").forward(request, response);
                            break;

                        case "Agregar":
                            String cedula = request.getParameter("txtCedula");
                            String nombres = request.getParameter("txtNombres");
                            String apellidos = request.getParameter("txtApellidos");
                            String correo = request.getParameter("txtCorreo");
                            String celular = request.getParameter("txtCelular");
                            boolean esadmin = Boolean.parseBoolean(request.getParameter("txtEsadmin"));

                            cliente.setCedula(cedula);
                            cliente.setNombres(nombres);
                            cliente.setApellidos(apellidos);
                            cliente.setCorreo(correo);
                            cliente.setCelular(celular);
                            cliente.setEsadmin(esadmin);
                            clienteDAO.agregar(cliente);
                            request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                            break;

                        case "Editar":
                            idCliente = Integer.parseInt(request.getParameter("id"));
                            cliente = clienteDAO.getCliente(idCliente);
                            request.setAttribute("cliente", cliente);
                            request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);

                            break;
                        case "Actualizar":
                            idCliente = Integer.parseInt(request.getParameter("txtId"));
                            cedula = request.getParameter("txtCedula");
                            nombres = request.getParameter("txtNombres");
                            apellidos = request.getParameter("txtApellidos");
                            correo = request.getParameter("txtCorreo");
                            celular = request.getParameter("txtCelular");
                            esadmin = Boolean.parseBoolean(request.getParameter("txtEsadmin"));

                            cliente.setId(idCliente);
                            cliente.setCedula(cedula);
                            cliente.setNombres(nombres);
                            cliente.setApellidos(apellidos);
                            cliente.setCorreo(correo);
                            cliente.setCelular(celular);
                            cliente.setEsadmin(esadmin);
                            clienteDAO.actualizar(cliente);
                            request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);

                            break;

                        case "GuardarUsuario":
                            idCliente = Integer.parseInt(request.getParameter("txtId2"));
                            String usuario = request.getParameter("txtUsuario");
                            String clave = Encripta.EncriptaMD5(request.getParameter("txtClave"));
                            clienteDAO.guardarUsuario(idCliente, usuario, clave);
                            request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);

                            break;
                        case "Eliminar":
                            idCliente = Integer.parseInt(request.getParameter("id"));
                            clienteDAO.eliminar(idCliente);
                            request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);

                            break;
                        default:
                            throw new AssertionError();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                break;
            case "Saldos":
                request.getRequestDispatcher("saldos.jsp").forward(request, response);
                break;
            case "Transferencia":
                request.getRequestDispatcher("transferencias.jsp").forward(request, response);
                break;
            case "Retiro":
                request.getRequestDispatcher("retiros.jsp").forward(request, response);
                break;
            case "Deposito":
                request.getRequestDispatcher("depositos.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
