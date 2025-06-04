package Admin;

import Services.LogService;
import Services.ServiceRole;
import Services.ServiceUser;
import Utils.LogActions;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        value = "/admin/change_info"
)
public class ChangeInforUser extends HttpServlet {
    ServiceUser serviceUser = new ServiceUser();
    ServiceRole serviceRole = new ServiceRole();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idUser = Integer.parseInt(req.getParameter("idUser"));
        int idRole1 = Integer.parseInt(req.getParameter("role"));
        int isActive = Integer.parseInt(req.getParameter("state"));
        boolean is = isActive == 1;
        String ipAddress = req.getRemoteAddr();
        String userAgent = req.getHeader("User-Agent");
        System.out.println(isActive);
        String name  = req.getParameter("name");
       serviceUser.updateUser(idUser,idRole1,name,is);
        LogService.logUserActivity(
                name,
                serviceRole.getRoleNameById(idRole1),
                LogActions.USER_UPDATE,
                "Cập nhật Thành Công",
                ipAddress,
                userAgent
        );
     resp.sendRedirect("getAllUser");



    }
}
