/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import DAO.ShoppingCartDAO;
import Model.Customer;
import Model.Member;
import Model.Order;
import Model.OrderProduct;
import Model.Receiver;
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
@WebServlet(name = "ShoppingCartView", urlPatterns = {"/ShoppingCartView"})
public class ShoppingCartViewServlet extends HttpServlet {

    List<OrderProduct> orders;
    Member customer;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        customer = (Member) request.getSession().getAttribute("customer");
        ShoppingCartDAO sDAO = new ShoppingCartDAO();
        try {
            orders = sDAO.getShoppingCart(customer);
            System.out.println(orders.size());

            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/View/ShoppingCartView.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartViewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy customer từ session
            Member customer = (Member) request.getSession().getAttribute("customer");

            // Lấy thông tin người nhận từ form
            String receiverName = request.getParameter("receiverName");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");

            Receiver receiver = new Receiver(receiverName, address, phoneNumber);

            // Lấy ngày tạo
            String createDate = java.time.LocalDate.now().toString();

            // Tạo đối tượng Order
            Order o = new Order(customer, createDate, null, receiver);

            // Gọi DAO để lưu vào DB
            OrderDAO dao = new OrderDAO();
            dao.CreateOrder(o);

            PrintWriter out = response.getWriter();
            out.println("<script type='text/javascript'>");
            out.println("alert('Order created successfully. Please wait for staff to approve the order.');");
            out.println("</script>");
            // Chuyển hướng về trang xác nhận
//            response.sendRedirect("View/OrderConfirm.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error creating order: " + e.getMessage());
        }
    }

}
