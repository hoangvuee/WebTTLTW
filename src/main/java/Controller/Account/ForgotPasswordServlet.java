package Controller.Account;


import Models.User.User;
import Services.ServiceResetToken;
import Services.ServiceUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
    ServiceUser serviceUser = new ServiceUser();
    ServiceResetToken serviceResetToken = new ServiceResetToken();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = serviceUser.getUserByEmail(email);

        if (user != null) {
            try {
                String token = serviceResetToken.generateRandomToken();
                boolean isTokenSaved = serviceResetToken.saveResetToken(user.getId(), token);
                
                if (isTokenSaved) {
                    String resetPasswordLink = "http://localhost:8080/WebFinall/Account/resetPassword.jsp?token=" + token;
                    String subject = "Thông Báo Đổi Mật Khẩu Tài Khoản Của Bạn";
                    String messageContent = "Kính gửi " + user.getUserName() + ",\n" +
                            "Để thay đổi mật khẩu tài khoản của bạn, vui lòng nhấn vào liên kết dưới đây:\n" +
                            resetPasswordLink + "\n" +
                            "Nếu bạn không yêu cầu thay đổi mật khẩu này, vui lòng bỏ qua email này.\n" +
                            "Trân trọng,\n" +
                            "[Đội ngũ hỗ trợ]";

                    serviceUser.sendEmail(email, subject, messageContent);
                    resp.sendRedirect("Account/login.jsp");
                } else {
                    // Ghi log lỗi token

                    resp.sendRedirect("Account/forgotPassword.jsp?error=token");
                }
            } catch (Exception e) {
                // Ghi log lỗi
                resp.sendRedirect("Account/forgotPassword.jsp?error=system");
            }
        }
            resp.sendRedirect("Account/forgotPassword.jsp?error=email");
        }
    }
