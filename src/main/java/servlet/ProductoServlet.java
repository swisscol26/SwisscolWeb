/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.ProductoDAO;
import modelo.Producto;
import java.util.List;

/**
 * Proyecto: SwissColWeb
 *
 * Framework utilizado: Jakarta EE.
 *
 * Justificación:
 * Se seleccionó Jakarta EE porque permite desarrollar aplicaciones web
 * utilizando Servlets, JSP y JDBC. Además, facilita la implementación
 * de la arquitectura MVC, la integración con la base de datos MySQL
 * y el despliegue de la aplicación en Apache Tomcat.
 *
 * Este Servlet actúa como controlador de la aplicación.
 * Recibe las solicitudes HTTP enviadas desde el formulario de registro,
 * procesa la información del producto y la envía al ProductoDAO para
 * realizar las operaciones sobre la base de datos.
 *
 * @author Marlon Pulido
 */

@WebServlet("/producto")
public class ProductoServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

    try {

        Producto producto = new Producto();

        producto.setProductId(Integer.parseInt(request.getParameter("productId")));
        producto.setName(request.getParameter("name"));
        producto.setDescription(request.getParameter("description"));
        producto.setPrice(Double.parseDouble(request.getParameter("price")));
        producto.setStock(Integer.parseInt(request.getParameter("stock")));
        producto.setImage(request.getParameter("image"));
        producto.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));

        ProductoDAO dao = new ProductoDAO();
        dao.insertarProducto(producto);

        request.setAttribute("mensaje", "Producto registrado correctamente.");

        request.getRequestDispatcher("resultado.jsp")
               .forward(request, response);

    } catch (Exception e) {

        request.setAttribute("mensaje", "Error: " + e.getMessage());

        request.getRequestDispatcher("resultado.jsp")
               .forward(request, response);

    }

}
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
