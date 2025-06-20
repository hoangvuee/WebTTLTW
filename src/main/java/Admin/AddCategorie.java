package Admin;

import Models.User.User;
import Services.LogService;
import Services.ServiceAddCategories;
import Services.ServiceRole;
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

@WebServlet(value = "/admin/addCategories")
public class AddCategorie extends HttpServlet {
    ServiceAddCategories serviceAddCategories = new ServiceAddCategories();
    private final ServiceRole serviceRole = new ServiceRole();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ipAddress = req.getRemoteAddr();
        String userAgent = req.getHeader("User-Agent");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfor");


        String categoryName = req.getParameter("categoryName");
        String categoryDescription = req.getParameter("categoryDescription");

        try {
            serviceAddCategories.addCategories(categoryName, categoryDescription);

            // Thêm thông báo thành công vào request attribute
            req.setAttribute("successMessage", "Category added successfully!");

            // Chuyển hướng về trang giao diện thêm category
            RequestDispatcher dispatcher = req.getRequestDispatcher("categorie.jsp");
            LogService.logUserActivity(
                    user.getUserName(),
                    serviceRole.getRoleNameById(user.getIdRole()),
                    LogActions.CATEGORY_ADD,
                    "Added new category: " + categoryName,
                    ipAddress,
                    userAgent
            );

            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            LogService.logUserActivity(
                    user.getUserName(),
                    serviceRole.getRoleNameById(user.getIdRole()),
                    LogActions.CATEGORY_ADD_FAILED,
                    "Failed to add category: " + e.getMessage(),
                    ipAddress,
                    userAgent
            );
            // Thêm thông báo lỗi vào request attribute
            req.setAttribute("errorMessage", "Error adding category. Please try again.");

            // Chuyển hướng lại về trang giao diện thêm category
            RequestDispatcher dispatcher = req.getRequestDispatcher("categorie.jsp");
            dispatcher.forward(req, resp);
        }
    }
}