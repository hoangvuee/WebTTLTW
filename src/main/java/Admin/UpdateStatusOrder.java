package Admin;

import Models.User.User;
import Services.LogService;
import Services.ServiceOrder;
import Services.ServiceRole;
import Utils.LogActions;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(
        value = "/admin/updateOrderStatus"
)
public class UpdateStatusOrder  extends HttpServlet {
    ServiceOrder serviceOrder = new ServiceOrder();
    ServiceRole serviceRole = new ServiceRole();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ipAddress = req.getRemoteAddr();
        String userAgent = req.getHeader("User-Agent");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfor");

        try {
            int idOrder = Integer.parseInt(req.getParameter("idOrder"));
            int isActive = Integer.parseInt(req.getParameter("status"));
            serviceOrder.updateOrderStatus(idOrder,isActive);
            LogService.logUserActivity(
                    user.getUserName(),
                    serviceRole.getRoleNameById(user.getIdRole()),
                    LogActions.ORDER_STATUS_UPDATE,
                    "Updated order status - Order ID: " + idOrder + " to status: " + isActive,
                    ipAddress,
                    userAgent
            );
            resp.sendRedirect("getOrderManage");
        } catch (Exception e) {
            LogService.logUserActivity(
                    user.getUserName(),
                    serviceRole.getRoleNameById(user.getIdRole()),
                    LogActions.ORDER_STATUS_UPDATE_FAILED,
                    "Failed to update order status: " + e.getMessage(),
                    ipAddress,
                    userAgent
            );
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update order status");
        }
    }
}
