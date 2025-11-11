package Controller;

import DAO.MemberDAO;
import Model.Member;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Member m = new Member(username, password);
        MemberDAO mDAO = new MemberDAO();

        try {
            Member result = mDAO.checkLogin(m);
            request.getSession().setAttribute("customer", result);
            if (result != null) {
                if (result.position != null && !result.position.trim().isEmpty()) {
                   
                    request.setAttribute("member", result);
                    request.getRequestDispatcher("/View/WarehouseMainView.jsp").forward(request, response);
                } else {    
                    request.setAttribute("member", result);
                    request.getRequestDispatcher("/View/CustomerMainView.jsp").forward(request, response);
                }

            } else {
                // Sai tài khoản hoặc mật khẩu
                request.setAttribute("message", "Invalid username or password!");
                request.getRequestDispatcher("/View/Login.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/View/Login.jsp");
    }
}
