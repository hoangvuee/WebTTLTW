package Controller.Account;

import Dao.UserDao;
import Dao.ActivityLogDAO;
import Models.User.User;
import Sercurity.JwtUtil;
import Services.ServiceRole;
import Services.ServiceUser;
import Utils.LogActions;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import log.ActivityLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/callback")
public class GoogleLoginServlet extends HttpServlet {
    ServiceUser serviceUser = new ServiceUser();
    ServiceRole serviceRole = new ServiceRole();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accessToken = request.getParameter("access_token");
        System.out.println("Received access_token: " + accessToken);

        if (accessToken == null || accessToken.isEmpty()) {
            response.sendRedirect("Account/login.jsp?error=Login failed");
            return;
        }

        // Gọi Google API để lấy thông tin người dùng
        String userInfoJson = getUserInfoFromGoogle(accessToken);

        if (userInfoJson == null || userInfoJson.isEmpty() || userInfoJson.equals("{}")) {
            response.sendRedirect("Account/login.jsp?error=Login failed");
            return;
        }

        JSONObject userInfo = new JSONObject(userInfoJson);

        if (!userInfo.has("email")) {
            response.sendRedirect("Account/login.jsp?error=Login failed");
            return;
        }

        String email = userInfo.getString("email");
        String name = userInfo.getString("name");
        String picture = userInfo.getString("picture");

        UserDao userDAO = new UserDao();
        User user = userDAO.getUserByEmail(email);
        System.out.println(user.toString());

        if (user == null) {
            user = new User(email, name, picture, 2);
            userDAO.addUserGG(user);
        }

        HttpSession session = request.getSession();
        session.setAttribute("userInfor", user);
        session.setAttribute("idUser", user.getId());
        session.setAttribute("idRole", user.getIdRole());
        session.setAttribute("nameRole", serviceRole.getRoleNameById(user.getIdRole()));
        System.out.println(user.getIdRole());

        String jwtToken = JwtUtil.generateToken(email,serviceRole.getRoleNameById(user.getIdRole()));
        session.setAttribute("authToken", jwtToken);
        System.out.println(jwtToken);

        // Log activity
        ActivityLogDAO activityLogDAO = new ActivityLogDAO();
        ActivityLog activityLog = new ActivityLog(user.getUserName(), serviceRole.getRoleNameById(user.getIdRole()), LogActions.GOOGLE_LOGIN_SUCCESS, "Successful Google login ", request.getRemoteAddr(), request.getHeader("User-Agent"));
        activityLogDAO.log(activityLog);

        response.setContentType("application/json");
        response.getWriter().write("{\"status\": \"success\"}");
    }

    private String getUserInfoFromGoogle(String accessToken) throws IOException {
        URL url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?alt=json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            return "{}"; // Trả về JSON rỗng nếu lỗi
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }
}