package controlador;

import config.Encripta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import modelo.Transaccion;
import modelo.TransaccionDAO;
import sun.invoke.empty.Empty;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    BancoDAO bdao;
    Banco ban = new Banco();
    int idBanco = 0;

    ClienteDAO clienteDAO;
    Cliente cliente = new Cliente();
    int idCliente = 0;

    CuentaDAO cuentaDAO;
    Cuenta cuenta = new Cuenta();
    int idCuenta = 0;

    TransaccionDAO transaccionDAO;
    List listaCuentas = new ArrayList();
    List listaTransacciones = new ArrayList();
    Transaccion transaccion = new Transaccion();

    String mostrarmensaje = "";
    String mensaje = "";

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
            case "Inicio":
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
                break;
            case "Banco":
                try {
                    bdao = new BancoDAO(jdbcURL, jdbcUsername, jdbcPassword);
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
                            request.setAttribute("ban", ban);
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
                    clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
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

            case "Registrar_C":
                try {
                    cuentaDAO = new CuentaDAO(jdbcURL, jdbcUsername, jdbcPassword);
                    clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
                    bdao = new BancoDAO(jdbcURL, jdbcUsername, jdbcPassword);
                    switch (accion) {
                        case "Listar":
                        case "Nuevo":
                            String filtro = request.getParameter("filtro");
                            request.setAttribute("nfiltro", filtro);
                            filtro = filtro.equals("") ? "%" : "%" + filtro.toLowerCase() + "%";
                            List lista = cuentaDAO.listar(filtro);
                            request.setAttribute("cuentas", lista);
                            request.getRequestDispatcher("registrar_cuentas.jsp").forward(request, response);
                            break;

                        case "BuscarCliente":
                            String busqCedula = request.getParameter("cedula");
                            cliente = clienteDAO.getClientexCedula(busqCedula);
                            cuenta.setCliente(cliente);
                            cuenta.setBanco(ban);
                            request.setAttribute("cta", cuenta);
                            request.getRequestDispatcher("registrar_cuentas.jsp").forward(request, response);
                            break;
                        case "BuscarBanco":
                            int busqBanco = Integer.parseInt(request.getParameter("codigobanco"));
                            ban = bdao.getBanco(busqBanco);
                            cuenta.setCliente(cliente);
                            cuenta.setBanco(ban);
                            request.setAttribute("cta", cuenta);
                            request.getRequestDispatcher("registrar_cuentas.jsp").forward(request, response);
                            break;

                        case "AgregarCuenta":
                            int cliId = Integer.parseInt(request.getParameter("cliId"));
                            int banId = Integer.parseInt(request.getParameter("banId"));
                            String numero = request.getParameter("txtNumero");
                            String tipo = request.getParameter("txtTipo");
                            double saldo = 0;
                            cuenta = new Cuenta();
                            cuenta.setBanId(banId);
                            cuenta.setCliId(cliId);
                            cuenta.setTipo(tipo);
                            cuenta.setNumero(numero);
                            cuenta.setSaldo(saldo);
                            int i = cuentaDAO.agregar(cuenta);
                            request.setAttribute("cuenta", cuenta);
                            request.getRequestDispatcher("Controlador?menu=Registrar_C&accion=Listar&filtro=").forward(request, response);
                            break;

                        case "Editar":
                            idCuenta = Integer.parseInt(request.getParameter("id"));
                            cuenta = cuentaDAO.getCuenta(idCuenta);
                            request.setAttribute("cta", cuenta);
                            request.getRequestDispatcher("Controlador?menu=Registrar_C&accion=Listar&filtro=").forward(request, response);
                            break;

                        default:
                            throw new AssertionError();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "Saldos":
                try {
                    cuentaDAO = new CuentaDAO(jdbcURL, jdbcUsername, jdbcPassword);
                    transaccionDAO = new TransaccionDAO(jdbcURL, jdbcUsername, jdbcPassword);
                    String ctaNumero;
                    int idCliente;
                    int idCuenta;
                    switch (accion) {
                        case "Listar":
                            idCliente = Integer.parseInt(request.getParameter("idCliente"));
                            String mostrarmensaje = request.getParameter("mostrarmensaje") == null ? "" : request.getParameter("mostrarmensaje");
                            String mensaje = request.getParameter("mensaje") == null ? "" : request.getParameter("mensaje");
                            listaCuentas = cuentaDAO.listarCtasCliente(idCliente);
                            listaTransacciones = new ArrayList();
                            request.setAttribute("cuentas", listaCuentas);
                            request.setAttribute("transacciones", listaTransacciones);
                            request.setAttribute("idCliente", idCliente);
                            request.setAttribute("mostrarmensaje", mostrarmensaje);
                            request.setAttribute("mensaje", mensaje);
                            request.getRequestDispatcher("saldos.jsp").forward(request, response);
                            break;

                        case "ListarTransacciones":
                            idCliente = Integer.parseInt(request.getParameter("idCliente"));
                            ctaNumero = request.getParameter("ctaNumero");
                            listaTransacciones = transaccionDAO.listar(ctaNumero);
                            request.setAttribute("cuentas", listaCuentas);
                            request.setAttribute("transacciones", listaTransacciones);
                            request.setAttribute("idCliente", idCliente);
                            request.getRequestDispatcher("saldos.jsp").forward(request, response);
                            break;

                        case "Depositar":
                            idCliente = Integer.parseInt(request.getParameter("idCliente"));
                            idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
                            double valorDeposito = Double.parseDouble(request.getParameter("txtValorDeposito"));
                            transaccion.setCueId(idCuenta);
                            transaccion.setMonto(valorDeposito);
                            transaccion.setAccion("DEPOSITO");
                            transaccion.setTipo("CREDITO");
                            transaccion.setBancotransfer("");
                            transaccion.setTipoctatransfer("");
                            transaccion.setNroctatransfer("");
                            transaccionDAO.agregar(transaccion);
                            cuentaDAO.actualizarSaldo(idCuenta, transaccion.getTipo(), valorDeposito);
                            request.setAttribute("cuentas", listaCuentas);
                            mostrarmensaje = "ok";
                            mensaje = "Depósito Realizado";
                            request.getRequestDispatcher("Controlador?menu=Saldos&accion=Listar&idCliente=" + idCliente + "&mostrarmensaje=" + mostrarmensaje + "&mensaje=" + mensaje).forward(request, response);
                            break;

                        case "Retirar":
                            idCliente = Integer.parseInt(request.getParameter("idCliente"));
                            idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
                            double valorRetiro = Double.parseDouble(request.getParameter("txtValorRetiro"));
                            cuenta = cuentaDAO.getCuenta(idCuenta);
                            if (cuenta.getSaldo() >= valorRetiro) {
                                transaccion.setCueId(idCuenta);
                                transaccion.setMonto(valorRetiro);
                                transaccion.setAccion("RETIRO");
                                transaccion.setTipo("DEBITO");
                                transaccion.setBancotransfer("");
                                transaccion.setTipoctatransfer("");
                                transaccion.setNroctatransfer("");
                                transaccionDAO.agregar(transaccion);
                                cuentaDAO.actualizarSaldo(idCuenta, transaccion.getTipo(), valorRetiro);
                                mostrarmensaje = "ok";
                                mensaje = "Retiro Realizado";
                            } else {
                                mostrarmensaje = "error";
                                mensaje = "Fondos Insuficientes para hacer el retiro";
                            }
                            request.setAttribute("cuentas", listaCuentas);
                            request.getRequestDispatcher("Controlador?menu=Saldos&accion=Listar&idCliente=" + idCliente + "&mostrarmensaje=" + mostrarmensaje + "&mensaje=" + mensaje).forward(request, response);
                            break;

                        case "BuscarCuenta":
                            idCliente = Integer.parseInt(request.getParameter("idCliente"));
                            idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
                            ctaNumero = request.getParameter("ctaBusq");
                            cuenta = cuentaDAO.getCuentaxNumero(ctaNumero);
                            request.setAttribute("ctaBusq", cuenta);
                            request.setAttribute("cuentas", listaCuentas);
                            request.setAttribute("transacciones", listaTransacciones);
                            request.setAttribute("expande", true);
                            request.setAttribute("idCliente", idCliente);
                            request.setAttribute("idCuenta", idCuenta);                           
                            request.getRequestDispatcher("saldos.jsp").forward(request, response);
                            break;
                        case "Transferir":
                            idCliente = Integer.parseInt(request.getParameter("idCliente"));
                            idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
                            String ctaBusq = request.getParameter("ctaBusq");
                            Cuenta cuentadestino = cuentaDAO.getCuentaxNumero(ctaBusq);
                            cuenta = cuentaDAO.getCuenta(idCuenta);
                            if (cuentadestino.getNumero() != null) {
                                double valorTransferencia = Double.parseDouble(request.getParameter("txtValorTransferencia"));
                                if (cuenta.getId() == cuentadestino.getId()) {
                                    mostrarmensaje = "error";
                                    mensaje = "No se acepta transferencias a la misma cuenta";
                                } else {
                                    if (cuenta.getSaldo() >= valorTransferencia) {
                                        transaccion.setCueId(idCuenta);
                                        transaccion.setMonto(valorTransferencia);
                                        transaccion.setAccion("TRANSFERENCIA");
                                        transaccion.setTipo("DEBITO");
                                        transaccion.setBancotransfer(cuentadestino.getBanco().getRazon());
                                        transaccion.setTipoctatransfer(cuentadestino.getTipo());
                                        transaccion.setNroctatransfer(cuentadestino.getNumero());
                                        transaccionDAO.agregar(transaccion);
                                        cuentaDAO.actualizarSaldo(idCuenta, transaccion.getTipo(), valorTransferencia);

                                        transaccion.setCueId(cuentadestino.getId());
                                        transaccion.setMonto(valorTransferencia);
                                        transaccion.setAccion("TRANSFERENCIA");
                                        transaccion.setTipo("CREDITO");
                                        transaccion.setBancotransfer(cuenta.getBanco().getRazon());
                                        transaccion.setTipoctatransfer(cuenta.getTipo());
                                        transaccion.setNroctatransfer(cuenta.getNumero());
                                        transaccionDAO.agregar(transaccion);
                                        cuentaDAO.actualizarSaldo(cuentadestino.getId(), transaccion.getTipo(), valorTransferencia);
                                        mostrarmensaje = "ok";
                                        mensaje = "Transferencia Realizada";

                                    } else {
                                        mostrarmensaje = "error";
                                        mensaje = "Fondos Insuficientes para hacer la transferencia";
                                    }
                                }
                            } else {
                                mostrarmensaje = "error";
                                mensaje = "Cuenta no existe " + ctaBusq;
                            }
                            request.setAttribute("cuentas", listaCuentas);
                            request.getRequestDispatcher("Controlador?menu=Saldos&accion=Listar&idCliente=" + idCliente + "&mostrarmensaje=" + mostrarmensaje + "&mensaje=" + mensaje).forward(request, response);
                            break;

                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
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
    }// </editor-fold>}
}
