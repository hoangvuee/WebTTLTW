package Admin;

import Dao.ActivityLogDAO;
import Models.User.User;
import Services.ServiceRole;
import Services.ServiceShipping;
import Utils.LogActions;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/admin/addShipping")
public class AddShipping extends HttpServlet {
    ServiceShipping serviceShipping = new ServiceShipping();
    private final ActivityLogDAO activityLogDAO = new ActivityLogDAO();
    private final ServiceRole serviceRole = new ServiceRole();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ipAddress = req.getRemoteAddr();
        String userAgent = req.getHeader("User-Agent");
        HttpSession session = req.getSession(false);


        String nameShipping = req.getParameter("deliveryService");
        double price = Double.parseDouble(req.getParameter("price"));

        try {
            User user = (User) session.getAttribute("userInfor");
            serviceShipping.addShipping(nameShipping, price);
            activityLogDAO.logUserActivity(
                    user.getUserName(),
                    serviceRole.getRoleNameById(user.getIdRole()),
                    LogActions.SHIPPING_ADD,
                    "Added new shipping method: " + nameShipping + " with price: " + price,
                    ipAddress,
                    userAgent
            );
            // Thêm thông báo thành công vào request attribute
            req.setAttribute("successMessage", "Shipping method added successfully!");

            // Chuyển hướng về trang giao diện thêm dịch vụ
            RequestDispatcher dispatcher = req.getRequestDispatcher("dilivery.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            User user = (User) session.getAttribute("userInfor");
            activityLogDAO.logUserActivity(
                    user.getUserName(),
                    serviceRole.getRoleNameById(user.getIdRole()),
                    LogActions.SHIPPING_ADD_FAILED,
                    "Failed to add shipping method: " + e.getMessage(),
                    ipAddress,
                    userAgent
            );
            // Thêm thông báo lỗi vào request attribute
            req.setAttribute("errorMessage", "Error adding shipping method. Please try again.");

            // Chuyển hướng lại về trang giao diện thêm dịch vụ
            RequestDispatcher dispatcher = req.getRequestDispatcher("dilivery.jsp");
            dispatcher.forward(req, resp);
        }
    }
}