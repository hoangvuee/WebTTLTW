package Controller.Account;

import Dao.ActivityLogDAO;
import Models.User.User;
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

@WebServlet(
        value = "/changePassword"
)
public class ChangePassword  extends HttpServlet {
    ServiceUser serviceUser = new ServiceUser();
    ServiceResetToken serviceResetToken = new ServiceResetToken();
    ServiceRole serviceRole = new ServiceRole();
    private ActivityLogDAO activityLogDAO = new ActivityLogDAO();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        int idUser = (int) session.getAttribute("idUser");
        String otp = (String) session.getAttribute("otp");
        String password = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        String otpCheck = req.getParameter("otp");
        String email = req.getParameter("email");
        try {
            User user = serviceUser.getUserById(idUser);
            String hassPass = serviceUser.hashPassword(password);
            if (serviceUser.checkPasswordExists(idUser, hassPass) && newPassword.equals(confirmPassword) && otpCheck.equals(otp)) {
                String hassNewPassword = serviceUser.hashPassword(newPassword);
                serviceUser.updatePassword(idUser, hassNewPassword);

                // Log successful password change
                User newUser = serviceUser.getUserByEmail(user.getEmail());
                int idRole = serviceUser.checkRole(email, password);
                String nameRole = serviceRole.getRoleNameById(idRole);
                activityLogDAO.logUserActivity(
                        newUser.getUserName(),
                        nameRole,
                    LogActions.USER_UPDATE,
                    "Password changed successfully",
                    req.getRemoteAddr(),
                    req.getHeader("User-Agent")
                );

                session.setAttribute("successMessage", "Thay đổi mật khẩu thành công!");
                resp.sendRedirect("User/guest-info.jsp");
                session.removeAttribute("otp");
            } else {
                User newUser = serviceUser.getUserByEmail(user.getEmail());
                int idRole = serviceUser.checkRole(email, newPassword);
                String nameRole = serviceRole.getRoleNameById(idRole);
                // Log failed password change attempt
                activityLogDAO.logUserActivity(
                        newUser.getUserName(),
                        nameRole,
                    LogActions.SYSTEM_ERROR,
                    "Invalid password change attempt - incorrect password or OTP mismatch",
                    req.getRemoteAddr(),
                    req.getHeader("User-Agent")
                );

                session.setAttribute("errorMessage", "Thông tin không hợp lệ!");
                resp.sendRedirect("User/guest-info.jsp");
            }
        } catch (Exception e) {
            session.setAttribute("errorMessage", "Đã xảy ra lỗi!");
            resp.sendRedirect("User/guest-info.jsp");
        }
    }
}
