package DAO;

import Model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO extends DAO {

    public Member checkLogin(Member m) throws SQLException {
        Connection conn = getConnection();

        String sql = """
            SELECT 
                mem.id,
                mem.name,
                mem.username,
                mem.password,
                mem.address,
                mem.dateOfBirth,
                mem.email,
                mem.phoneNumber,
                s.position
            FROM tblmember mem
            LEFT JOIN tblstaff s ON mem.id = s.tblMemberid
            WHERE mem.username = ? AND mem.password = ?
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, m.username);
        ps.setString(2, m.password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Member result = new Member();
            result.id = rs.getInt("id");
            result.name = rs.getString("name");
            result.username = rs.getString("username");
            result.password = rs.getString("password");
            result.address = rs.getString("address");
            result.dateOfBirth = rs.getString("dateOfBirth");
            result.email = rs.getString("email");
            result.phoneNumber = rs.getString("phoneNumber");
            result.position = rs.getString("position"); // null nếu là customer

            System.out.println("[MemberDAO] Login success: " + result.username + 
                               " | position=" + result.position);
            return result;
        }

        System.out.println("[MemberDAO] Login failed for username=" + m.username);
        return null;
    }
}
