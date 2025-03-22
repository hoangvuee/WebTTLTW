package Controller.Account;

import Models.User.User;
import Sercurity.JwtUtil;
import Services.ServiceRole;
import Services.ServiceUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(
        value = "/checkLogin"
)
public class SignIn extends HttpServlet {
    ServiceUser serviceUser = new ServiceUser();
    ServiceRole serviceRole = new ServiceRole();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer id = (Integer) session.getAttribute("idUser");
        if (id != null) {
            resp.sendRedirect("setupData");
            return;
        } else {
            session = req.getSession(true);
        }

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String hassPassword;

        try {
            hassPassword = serviceUser.hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if (serviceUser.checkCredentials(email, hassPassword)) {
            try {
                if(serviceUser.checkIsAvtive(email,hassPassword) == 0){
                    req.setAttribute("errorMessage", "Tài khoản bị khoá");
                    req.getRequestDispatcher("Account/login.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // Xử lý thành công
            int idUser = 0;
            int idRole = 0;
            String nameRole = null;
            try {
                idUser = serviceUser.check(email, hassPassword);
                idRole = serviceUser.checkRole(email, hassPassword);
                nameRole = serviceRole.getRoleNameById(idRole);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            session.setAttribute("idUser", idUser);
            session.setAttribute("idRole", idRole);
            session.setAttribute("nameRole", nameRole);

            User user = serviceUser.getUserByEmail(email);
            if (user != null) {
                session.setAttribute("userInfor", user);
                String token = JwtUtil.generateToken(email, serviceRole.getRoleNameById(user.getIdRole()));
               session.setAttribute("authToken", token);
                System.out.println(token);
                resp.setHeader("Authorization", "Bearer " + token);
            }
            resp.sendRedirect("setupData");
        } else {
            // Xử lý thất bại
            req.setAttribute("errorMessage", "Sai tài khoản hoặc mật khẩu.");
            req.getRequestDispatcher("Account/login.jsp").forward(req, resp);
        }
    }
}
