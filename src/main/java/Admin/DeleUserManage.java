package Admin;

import Services.ServiceUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        value = "/admin/delete_user"
)
public class DeleUserManage extends HttpServlet {
    ServiceUser serviceUser = new ServiceUser();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idUser = Integer.parseInt(req.getParameter("idUser"));
        if (serviceUser.deleteUser(idUser)){
            resp.sendRedirect("admin/getAllUser");
        }

    }
}
