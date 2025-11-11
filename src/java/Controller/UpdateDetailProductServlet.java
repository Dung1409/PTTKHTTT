package Controller;

import DAO.ProductDAO;
import Model.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "UpdateDetailProduct", urlPatterns = {"/UpdateDetailProduct"})
public class UpdateDetailProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        ProductDAO pDAO = new ProductDAO();

        if ("edit".equalsIgnoreCase(action)) {
            // Trường hợp click "Edit" từ danh sách
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String supplierName = request.getParameter("supplierName");
                float purchasePrice = Float.parseFloat(request.getParameter("purchasePrice"));
                float sellingPrice = Float.parseFloat(request.getParameter("sellingPrice"));

                Product product = new Product(id, name, supplierName, description, purchasePrice, sellingPrice);
                request.setAttribute("product", product);

                RequestDispatcher rd = request.getRequestDispatcher("/View/UpdateDetailProduct.jsp");
                rd.forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("error", "Invalid product data.");
                request.getRequestDispatcher("/View/UpdateProductView.jsp").forward(request, response);
            }
            return;
        }

        if ("save".equalsIgnoreCase(action)) {
            // Trường hợp click "Save" trong trang chi tiết
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String supplierName = request.getParameter("supplierName");
                String description = request.getParameter("description");
                float purchasePrice = Float.parseFloat(request.getParameter("purchasePrice"));
                float sellingPrice = Float.parseFloat(request.getParameter("sellingPrice"));

                Product product = new Product(id, name, supplierName, description, purchasePrice, sellingPrice);

                boolean updated = pDAO.updateProduct(product);

                // Sau khi cập nhật -> load lại danh sách
                List<Product> list = pDAO.GetAllProduct();
                request.setAttribute("products", list);

                if (updated) {
                    request.setAttribute("message", "Product updated successfully!");
                } else {
                    request.setAttribute("message", "Failed to update product.");
                }

                request.getRequestDispatcher("/View/UpdateProductView.jsp").forward(request, response);
            } catch (NumberFormatException | SQLException ex) {
                Logger.getLogger(UpdateDetailProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "❌ Error updating product.");
                request.getRequestDispatcher("/View/UpdateProductView.jsp").forward(request, response);
            }
        }
    }
}
