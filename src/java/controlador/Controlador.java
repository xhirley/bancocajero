
package controlador;

import config.Encripta;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Banco;
import modelo.BancoDAO;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Cuenta;
import modelo.CuentaDAO;

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
       
        try {
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
                    try {
                        BancoDAO bdao = new BancoDAO(jdbcURL, jdbcUsername, jdbcPassword);
                        Banco ban = new Banco();
                        int idBanco = 0;
                        switch (accion) {
                            case "Listar":
                            case "Nuevo":
                                List lista = bdao.listar();
                                request.setAttribute("bancos", lista);
                                request.getRequestDispatcher("bancos.jsp").forward(request, response);
                                break;
                                
                            case "Agregar":
                                String razon = request.getParameter("txtRazon");
                                String logo = request.getParameter("txtLogo");
                                
                                ban.setRazon(razon);
                                ban.setLogo(logo);
                                bdao.agregar(ban);
                                request.getRequestDispatcher("Controlador?menu=Banco&accion=Listar").forward(request, response);
                                break;
                                
                            case "Editar":
                                idBanco = Integer.parseInt(request.getParameter("id"));
                                ban = bdao.getBanco(idBanco);
                                request.setAttribute("banco", ban);
                                request.getRequestDispatcher("Controlador?menu=Banco&accion=Listar").forward(request, response);
                                
                                break;
                            case "Actualizar":
                                idBanco = Integer.parseInt(request.getParameter("txtId"));
                                razon = request.getParameter("txtRazon");
                                logo = request.getParameter("txtLogo");
                                
                                ban.setId(idBanco);
                                ban.setRazon(razon);
                                ban.setLogo(logo);
                                
                                bdao.actualizar(ban);
                                request.getRequestDispatcher("Controlador?menu=Banco&accion=Listar").forward(request, response);
                                
                                break;
                                
                            case "Eliminar":
                                idBanco = Integer.parseInt(request.getParameter("id"));
                                bdao.eliminar(idBanco);
                                request.getRequestDispatcher("Controlador?menu=Banco&accion=Listar").forward(request, response);
                                
                                break;
                            default:
                                throw new AssertionError();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
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
                    
                case "Cuenta":
                    try {
                        CuentaDAO cuentaDAO = new CuentaDAO(jdbcURL, jdbcUsername, jdbcPassword);
                        Cuenta cuenta = new Cuenta();
                        int idCuenta = 0;
                        switch (accion) {
                            case "Listar":
                            case "Nuevo":
                                List lista = cuentaDAO.listar();
                                request.setAttribute("cuentas", lista);
                                request.getRequestDispatcher("cuentas.jsp").forward(request, response);
                                break;
                                
                            case "Agregar":
                                String numero=request.getParameter("txtNumero");
                                String tipo = request.getParameter("txtTipo");
                                String saldo = request.getParameter("txtSaldo");
                                
                                cuenta.setNumero(numero);
                                cuenta.setTipo(tipo);
                                //cuenta.setSaldo(saldo);
                                cuentaDAO.agregar(cuenta);
                                request.getRequestDispatcher("Controlador?menu=Cuenta&accion=Listar").forward(request, response);
                                break;
                                
                            case "Editar":
                                idCuenta = Integer.parseInt(request.getParameter("id"));
                                cuenta = cuentaDAO.getCuenta(idCuenta);
                                request.setAttribute("cuenta", cuenta);
                                request.getRequestDispatcher("Controlador?menu=Cuenta&accion=Listar").forward(request, response);
                                
                                break;
                            case "Actualizar":
                                idCuenta= Integer.parseInt(request.getParameter("txtId"));
                                numero = request.getParameter("txtNumero");
                                tipo = request.getParameter("txtTipo");
                                saldo= request.getParameter("txtSaldo");
                                
                                cuenta.setId(idCuenta);
                                cuenta.setNumero(numero);
                                cuenta.setTipo(tipo);
                                //cuenta.setSaldo(saldo);
                                cuentaDAO.actualizar(cuenta);
                                request.getRequestDispatcher("Controlador?menu=Cuenta&accion=Listar").forward(request, response);
                                
                                break;
                                
                            case "Eliminar":
                                idCuenta= Integer.parseInt(request.getParameter("id"));
                                cuentaDAO.eliminar(idCuenta);
                                request.getRequestDispatcher("Controlador?menu=Cuenta&accion=Listar").forward(request, response);
                                
                                break;
                            default:
                                throw new AssertionError();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                    break;
                case "Registar_Cuentas":
                    ClienteDAO clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
                    Cliente cliente = new Cliente();
                    try {
                        switch (accion) {
                            case "Generar Cuentas ":

                                String cedula = request.getParameter("codigocliente");
                                cliente.setCedula(cedula);
                                cliente = clienteDAO.Buscar(cedula);
                                request.setAttribute("clientes", cliente);
                                request.getRequestDispatcher("registrar_cuentas.jsp").forward(request, response);
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
        } /*
        case "BuscarCuenta":
        int id = Integer.parseInt(request.getParameter("codigocuenta"));
        cuenta = cuentaDAO.getCuenta(id);
        request.setAttribute("cuenta", cuenta);
        request.setAttribute("lista", lista);
        request.setAttribute("total", total);
        request.setAttribute("nserie", numeroserie);
        break;
        case "AgregarProducto":
        total = 0.0;
        item = item + 1;
        cod = p.getId();
        descripcion = request.getParameter("nombreproducto");
        precio = Double.parseDouble(request.getParameter("precio"));
        cant = Integer.parseInt(request.getParameter("cant"));
        subtotal = precio * cant;
        v = new Venta();
        v.setItem(item);
        v.setIdproducto(cod);
        v.setDescripcionP(descripcion);
        v.setPrecio(precio);
        v.setCantidad(cant);
        v.setSubtotal(subtotal);
        lista.add(v);
        for (int i = 0; i < lista.size(); i++) {
        total = total + lista.get(i).getSubtotal();
        }
        request.setAttribute("nserie", numeroserie);
        request.setAttribute("total", total);
        request.setAttribute("lista", lista);
        break;
        case "GenerarVenta":
        //Guardar Venta -----------------------------------------
        v.setIdcliente(18);
        v.setIdempleado(2);
        v.setNumserie(numeroserie);
        java.util.Date ahora=new java.util.Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyy/MM/dd");
        v.setFecha(formateador.format(ahora));
        v.setMonto(total);
        v.setEstado("1");
        vdao.guardarVenta(v);
        //Guardar Detalle venta --------------------------------
        int idv = Integer.parseInt(vdao.IdVentas());
        for (int i = 0; i < lista.size(); i++) {
        v = new Venta();
        v.setId(idv);
        v.setIdproducto(lista.get(i).getIdproducto());
        v.setCantidad(lista.get(i).getCantidad());
        v.setPrecio(lista.get(i).getPrecio());
        vdao.guardarDetalleVentas(v);
        }
        //Actualizar Stock --------------------------------
        for (int i = 0; i < lista.size(); i++) {
        Producto pr = new Producto();
        int cantidad = lista.get(i).getCantidad();
        int idproducto = lista.get(i).getIdproducto();
        ProductoDAO aO = new ProductoDAO();
        pr = aO.buscar(idproducto);
        int sac = pr.getStock()-cantidad;
        aO.actualizarstock(idproducto, sac);
        }
        lista = new ArrayList<>();
        break;
        default:
        lista = new ArrayList<>();
        item=0;
        numeroserie = vdao.GenerarSerie();
        if (numeroserie == null) {
        numeroserie = "00001";
        request.setAttribute("nserie", numeroserie);
        } else {
        int incrementar = Integer.parseInt(numeroserie);
        numeroserie = vdao2.NumeroSerie(incrementar);
        request.setAttribute("nserie", numeroserie);
        }
        request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
        request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
        }
        request.getRequestDispatcher("registrar_cuentas.jsp").forward(request, response);
        break;
         */ catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
    }// </editor-fold>}
}