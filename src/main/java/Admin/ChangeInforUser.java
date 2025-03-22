package Admin;

import Services.ServiceUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(
        value = "/admin/change_info"
)
public class ChangeInforUser extends HttpServlet {
    ServiceUser serviceUser = new ServiceUser();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idUser = Integer.parseInt(req.getParameter("idUser"));
        int idRole1 = Integer.parseInt(req.getParameter("role"));
        int isActive = Integer.parseInt(req.getParameter("state"));
        boolean is = isActive == 1;
        System.out.println(isActive);
        String name  = req.getParameter("name");
       serviceUser.updateUser(idUser,idRole1,name,is);
     resp.sendRedirect("getAllUser");



    }
}
