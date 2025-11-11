/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.Product;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Admin
 */
public class ProductDAO extends DAO{
    public List<Product> GetAllProduct() throws SQLException{
        Connection conn = getConnection();
        List<Product> products = new ArrayList<>();
        String sql = "select * from tblproduct";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String supplierName = rs.getString("supplierName");
            String description = rs.getString("description");
            float purchasePrice = rs.getFloat("purchasePrice");
            float sellingPrice = rs.getFloat("sellingPrice");
            
            Product p = new Product(id, name, supplierName, description, purchasePrice, sellingPrice);
            products.add(p);
        }
        System.out.println(products.size());
        return  products;
        
    }

    public boolean updateProduct(Product p) throws SQLException {
        Connection conn = getConnection();
        String sql = """
            UPDATE tblproduct 
            SET name = ?, 
                supplierName = ?, 
                description = ?, 
                purchasePrice = ?, 
                sellingPrice = ? 
            WHERE id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.name);
            stmt.setString(2, p.supplierName);
            stmt.setString(3, p.description);
            stmt.setFloat(4, p.purchasePrice);
            stmt.setFloat(5, p.sellingPrice);
            stmt.setInt(6, p.id);

            return stmt.executeUpdate() > 0;
        }
    }
}
