package Controller.Account;

import Models.User.User;
import Services.LogService;
import Services.ServiceResetToken;
import Services.ServiceRole;
import Services.ServiceUser;
import Utils.LogActions;
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
        value = "/changePassword"
)
public class ChangePassword  extends HttpServlet {
    ServiceUser serviceUser = new ServiceUser();
    ServiceResetToken serviceResetToken = new ServiceResetToken();
    ServiceRole serviceRole = new ServiceRole();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        int idUser = (int) session.getAttribute("idUser");
        String otp = (String) session.getAttribute("otp");
        String password =  req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        String otpCheck = req.getParameter("otp");
        String email = serviceUser.getEmailbyId(idUser);

        try {
            String hassPass = serviceUser.hashPassword(password);
            if(serviceUser.checkPasswordExists(idUser,hassPass) && newPassword.equals(confirmPassword) && otpCheck.equals(otp) ){
                String hassNewPassword = serviceUser.hashPassword(newPassword);
                serviceUser.updatePassword(idUser,hassNewPassword);
                System.out.println("Thay đổi mật khẩu thành công");

                // Get user's role

                User user = serviceUser.getUserByEmail(email);
                // Log successful password change
                LogService.logUserActivity(
                        user.getUserName(),
                        serviceRole.getRoleNameById(user.getIdRole()),
                    LogActions.USER_UPDATE,
                    "Password changed successfully",
                    req.getRemoteAddr(),
                    req.getHeader("User-Agent")
                );

                resp.sendRedirect("User/guest-info.jsp");
                session.removeAttribute("otp");
            } else {
                int idRole = serviceUser.checkRole(email, hassPass);
                String nameRole = serviceRole.getRoleNameById(idRole);
                User user = serviceUser.getUserByEmail(email);
                // Log failed password change attempt
                String failureReason = !serviceUser.checkPasswordExists(idUser, hassPass) ? "Invalid old password" :
                                     !newPassword.equals(confirmPassword) ? "New passwords do not match" :
                                     !otpCheck.equals(otp) ? "Invalid OTP" : "Unknown reason";
                LogService.logUserActivity(
                    user.getUserName(),
                        nameRole,
                    LogActions.SYSTEM_ERROR,
                    "Password change failed: " + failureReason,
                    req.getRemoteAddr(),
                    req.getHeader("User-Agent")
                );
            }
        } catch (NoSuchAlgorithmException | SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
