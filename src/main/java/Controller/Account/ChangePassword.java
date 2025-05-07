package Controller.Account;

import Services.ServiceResetToken;
import Services.ServiceUser;
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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        int idUser = (int) session.getAttribute("idUser");
        String otp = (String) session.getAttribute("otp");
    String password =  req.getParameter("oldPassword");
    String newPassword = req.getParameter("newPassword");
    String confirmPassword = req.getParameter("confirmPassword");
    String otpCheck = req.getParameter("otp");
        try {
            String hassPass = serviceUser.hashPassword(password);
            if(serviceUser.checkPasswordExists(idUser,hassPass) && newPassword.equals(confirmPassword) && otpCheck.equals(otp) ){
                String hassNewPassword = serviceUser.hashPassword(newPassword);
                serviceUser.updatePassword(idUser,hassNewPassword);
                System.out.println("Thay đổi mật khẩu thành công");
             resp.sendRedirect("User/guest-info.jsp");
             session.removeAttribute("otp");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }



    }
}
