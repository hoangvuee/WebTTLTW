package Admin;

import Dao.ActivityLogDAO;
import Models.User.User;
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
    private final ActivityLogDAO activityLogDAO = new ActivityLogDAO();
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
            
            activityLogDAO.logUserActivity(
                user.getUserName(),
                serviceRole.getRoleNameById(user.getIdRole()),
                LogActions.CATEGORY_ADD,
                "Added new category: " + categoryName,
                ipAddress,
                userAgent
            );

            req.setAttribute("successMessage", "Category added successfully!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("categorie.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            activityLogDAO.logUserActivity(
                user.getUserName(),
                serviceRole.getRoleNameById(user.getIdRole()),
                LogActions.CATEGORY_ADD_FAILED,
                "Failed to add category: " + e.getMessage(),
                ipAddress,
                userAgent
            );
            
            req.setAttribute("errorMessage", "Error adding category. Please try again.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("categorie.jsp");
            dispatcher.forward(req, resp);
        }
    }
}