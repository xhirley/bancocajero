
package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Prueba;
import modelo.PruebaDAO;


@WebServlet(name = "prueba", urlPatterns = {"/prueba"})
public class PruebaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    PruebaDAO pruebaDao;

    @Override
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        try {

            pruebaDao = new PruebaDAO(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

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
        try {
            String accion = request.getParameter("action");
            System.out.println(accion);
            switch (accion) {
                case "index":
                    //index(request, response);
                    break;
                case "nuevo":
                    nuevo(request, response);
                    break;
                case "register":
                    System.out.println("entro");
                    //registrar(request, response);
                    break;
                case "mostrar":
                    mostrar(request, response);
                    break;
                case "showedit":
                    showEditar(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "eliminar":
                    eliminar(request, response);//sirve paa eleminar
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
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

    private void index(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        //mostrar(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Prueba prueba = new Prueba(0, request.getParameter("descripcion"));
        pruebaDao.insertar(prueba);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/register.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
        List<Prueba> listaPruebas = pruebaDao.listarPruebas();
        request.setAttribute("lista", listaPruebas);
        dispatcher.forward(request, response);
    }

    private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Prueba prueba = pruebaDao.obtenerPorId(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("prueba", prueba);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/editar.jsp");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Prueba prueba = new Prueba(Integer.parseInt(request.getParameter("codigo")), request.getParameter("descripcion"));
        pruebaDao.actualizar(prueba);
        index(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Prueba prueba = pruebaDao.obtenerPorId(Integer.parseInt(request.getParameter("id")));
        pruebaDao.eliminar(prueba);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }
}
