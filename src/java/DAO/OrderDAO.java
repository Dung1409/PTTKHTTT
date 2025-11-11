/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Member;
import Model.Order;
import java.sql.*;
import Model.OrderProduct;

/**
 *
 * @author Admin
 */
public class OrderDAO extends DAO {

    public boolean OrderProduct(OrderProduct o, Member m) throws SQLException {
        Connection conn = getConnection();

        String sql = "Insert into tblorderproduct("
                + "quantity , "
                + "sellingPrice , "
                + "tblProductid , "
                + "tblShoppingCartId) "
                + "values(?, ? , ? , ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        int sC = getShopingCart(m);
        if (sC == 0) {
            System.out.println("Fail to add order product");
            return false;
        }
        stmt.setInt(1, o.quanity);
        stmt.setFloat(2, o.sellingPrice);
        stmt.setInt(3, o.p.id);
        stmt.setInt(4, sC);

        return stmt.executeUpdate() != 0;
    }

    int getShopingCart(Member m) throws SQLException {
        Connection conn = getConnection();
        String sql = "select * from tblshoppingcart where tblCustomerid = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, m.id);
        ResultSet rs = stmt.executeQuery();
        int idx = 0;
        if (rs.next()) {
            idx = rs.getInt("id");
        }
        return idx;
    }

    public void CreateOrder(Order o) throws SQLException {
        Connection conn = getConnection();

        String sqlReceiver = "INSERT INTO tblReceiver (name, address, phoneNumber) VALUES (?, ?, ?)";
        PreparedStatement ps1 = conn.prepareStatement(sqlReceiver, Statement.RETURN_GENERATED_KEYS);
        ps1.setString(1, o.receiver.name);
        ps1.setString(2, o.receiver.address);
        ps1.setString(3, o.receiver.phoneNumber);
        ps1.executeUpdate();

        ResultSet rs1 = ps1.getGeneratedKeys();
        int receiverId = 0;
        if (rs1.next()) {
            receiverId = rs1.getInt(1);
        }

       
        String sqlOrder = "INSERT INTO tblOrder (createDate, tblMemberid, tblReceiverid) VALUES (?, ?, ?)";
        PreparedStatement ps2 = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
        ps2.setString(1, o.createDate);
        ps2.setInt(2, o.member.id);
        ps2.setInt(3, receiverId);
        ps2.executeUpdate();

        ResultSet rs2 = ps2.getGeneratedKeys();
        int orderId = 0;
        if (rs2.next()) {
            orderId = rs2.getInt(1);
        }

        
        String sqlUpdate = "UPDATE tblOrderProduct "
                + "SET tblOrderid = ? "
                + "WHERE tblShoppingCartid = (SELECT id FROM tblShoppingCart WHERE tblCustomerid = ?)";
        PreparedStatement ps3 = conn.prepareStatement(sqlUpdate);
        ps3.setInt(1, orderId);
        ps3.setInt(2, o.member.id);
        ps3.executeUpdate();

        
        rs1.close();
        rs2.close();
        ps1.close();
        ps2.close();
        ps3.close();
        conn.close();
    }

}
