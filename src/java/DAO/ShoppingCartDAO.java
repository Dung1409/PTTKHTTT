/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Member;
import Model.OrderProduct;
import Model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ShoppingCartDAO extends DAO {

    public List<OrderProduct> getShoppingCart(Member m) throws SQLException {
        List<OrderProduct> orderProducts = new ArrayList<>();
        String sql = "SELECT "
                + "op.id AS orderproductId, "
                + "p.id AS productId, "
                + "p.name AS productName, "
                + "p.description, "
                + "p.supplierName, "
                + "op.quantity, "
                + "op.sellingPrice, "
                + "p.purchasePrice "
                + "FROM tblOrderProduct op "
                + "JOIN tblProduct p ON op.tblProductId = p.id "
                + "JOIN tblShoppingCart sc ON op.tblShoppingCartId = sc.id "
                + "WHERE sc.tblCustomerid = ? AND op.tblOrderid IS NULL;";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, m.id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int orderProductId = rs.getInt("orderproductId");
                    int productId = rs.getInt("productId");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    String supplierName = rs.getString("supplierName");
                    int quantity = rs.getInt("quantity");
                    float sellingPrice = rs.getFloat("sellingPrice");
                    float purchasePrice = rs.getFloat("purchasePrice");

                    Product p = new Product(productId, productName, supplierName, description, purchasePrice, sellingPrice);
                    OrderProduct op = new OrderProduct(orderProductId, p, quantity, sellingPrice);
                    orderProducts.add(op);
                }
            }
        }
        return orderProducts;
    }

}
