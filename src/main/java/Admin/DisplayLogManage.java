package Admin;

import Dao.ActivityLogDAO;
import Services.LogService;
import log.ActivityLog;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/getRecentActivities")
public class DisplayLogManage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LogService activityLog = new LogService();
            List<ActivityLog> logs = activityLog.getUserActivity();
            
            // Set response type to JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            // Convert logs to JSON and send response
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(logs);
            response.getWriter().write(jsonResponse);
            
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
