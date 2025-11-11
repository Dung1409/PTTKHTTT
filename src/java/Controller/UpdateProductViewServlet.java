/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateProductView", urlPatterns = {"/UpdateProductView"})
public class UpdateProductViewServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProductView</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductView at " + request.getContextPath() + "</h1>");
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
        ProductDAO pDAO = new ProductDAO();
        try {
            List<Product> productList = pDAO.GetAllProduct();

            // Lấy keyword từ ô tìm kiếm
            String keyword = request.getParameter("search");

            // Nếu keyword không null và không rỗng -> lọc danh sách
            if (keyword != null && !keyword.trim().isEmpty()) {
                String lowerKeyword = keyword.toLowerCase();
                productList.removeIf(p -> !p.name.toLowerCase().contains(lowerKeyword));
            }

            // Gửi dữ liệu sang JSP
            request.setAttribute("products", productList);
            request.setAttribute("searchKeyword", keyword); // để hiển thị lại trong ô tìm kiếm
            request.getRequestDispatcher("/View/UpdateProductView.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductViewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        // Nhận dữ liệu từ form
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String supplierName = request.getParameter("supplierName");
        String description = request.getParameter("description");
        float  purchasePrice = Float.parseFloat(request.getParameter("purchasePrice"));
        float sellingPrice = Float.parseFloat(request.getParameter("sellingPrice"));

        // Tạo đối tượng Product
        Product product = new Product(id, name, supplierName, description , purchasePrice, sellingPrice);

        // Gắn vào request
        request.setAttribute("product", product);

        // Chuyển nội bộ sang trang UpdateDetailProduct.jsp
        request.getRequestDispatcher("/View/UpdateDetailProduct.jsp").forward(request, response);
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
