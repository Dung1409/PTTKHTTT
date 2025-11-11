/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import Model.Member;
import Model.OrderProduct;
import Model.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "OrderProductView", urlPatterns = {"/OrderProductView"})
public class OrderProductViewServlet extends HttpServlet {

    Product product;
    Member customer;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        float sellingPrice = Float.parseFloat(request.getParameter("sellingPrice"));
        float purchasePrice = Float.parseFloat(request.getParameter("purchasePrice"));
        String supplierName = request.getParameter("supplierName");
        
        Product p = new Product(id, name, supplierName, description, purchasePrice,sellingPrice);
        request.setAttribute("product", p);
        product = p;
        customer = (Member) request.getSession().getAttribute("customer");
        request.getRequestDispatcher("/View/OrderProductView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        OrderProduct op = new OrderProduct(product , quantity , product.sellingPrice);
        OrderDAO oDAO = new OrderDAO();
        try {
            boolean order = oDAO.OrderProduct(op, customer);
            if(order) response.sendRedirect(request.getContextPath() + "/ProductView");
        } catch (SQLException ex) {
            Logger.getLogger(OrderProductViewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
