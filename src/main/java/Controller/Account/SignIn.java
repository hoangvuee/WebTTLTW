package Controller.Account;

import Models.User.User;
import Sercurity.JwtUtil;
import Services.ServiceRole;
import Services.ServiceUser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet("/checkLogin")
public class SignIn extends HttpServlet {
    ServiceUser serviceUser = new ServiceUser();
    ServiceRole serviceRole = new ServiceRole();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer id = (Integer) session.getAttribute("idUser");

        // Nếu đã đăng nhập, chuyển hướng luôn
        if (id != null) {
            resp.sendRedirect("setupData");
            return;
        }

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Đếm số lần đăng nhập sai trong session
        Integer failedAttempts = (Integer) session.getAttribute("failedAttempts");
        if (failedAttempts == null) {
            failedAttempts = 0;
        }

        // Kiểm tra reCAPTCHA nếu nhập sai >= 3 lần
        String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
        System.out.println("gRecaptchaResponse: " + gRecaptchaResponse);

        if (failedAttempts >= 3) {
            if (gRecaptchaResponse == null || gRecaptchaResponse.isEmpty()) {
                session.setAttribute("errorMessage", "Vui lòng xác minh CAPTCHA!");
                resp.sendRedirect(req.getContextPath() + "/Account/login.jsp");
                return;
            }

            boolean captchaVerified = verifyCaptcha(gRecaptchaResponse);
            if (!captchaVerified) {
                session.setAttribute("errorMessage", "CAPTCHA không hợp lệ. Vui lòng thử lại!");
                resp.sendRedirect(req.getContextPath() + "/Account/login.jsp");
                return;
            }
        }


        // Hash password
        String hashedPassword;
        try {
            hashedPassword = serviceUser.hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // Kiểm tra tài khoản có hợp lệ không
        if (serviceUser.checkCredentials(email, hashedPassword)) {
            try {
                if (serviceUser.checkIsAvtive(email, hashedPassword) == 0) {
                    session.setAttribute("errorMessage", "Tài khoản bị khóa!");
                    resp.sendRedirect(req.getContextPath() + "/Account/login.jsp");
                    return;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Đăng nhập thành công, reset failedAttempts
            session.removeAttribute("failedAttempts");

            // Lấy thông tin user
            int idUser, idRole;
            String nameRole;
            try {
                idUser = serviceUser.check(email, hashedPassword);
                idRole = serviceUser.checkRole(email, hashedPassword);
                nameRole = serviceRole.getRoleNameById(idRole);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Lưu thông tin user vào session
            session.setAttribute("idUser", idUser);
            session.setAttribute("idRole", idRole);
            session.setAttribute("nameRole", nameRole);

            // Tạo JWT token
            User user = serviceUser.getUserByEmail(email);
            if (user != null) {
                session.setAttribute("userInfor", user);
                String token = JwtUtil.generateToken(email, serviceRole.getRoleNameById(user.getIdRole()));
                session.setAttribute("authToken", token);
                resp.setHeader("Authorization", "Bearer " + token);
            }

            resp.sendRedirect("setupData");
        } else {
            failedAttempts++;
            System.out.println(failedAttempts + "Capcha");
            session.setAttribute("failedAttempts", failedAttempts);
            session.setAttribute("errorMessage", "Sai tài khoản hoặc mật khẩu!");
            resp.sendRedirect(req.getContextPath() + "/Account/login.jsp");
        }
    }

    // Kiểm tra reCAPTCHA với Google
    public boolean verifyCaptcha(String gRecaptchaResponse) {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String secretKey = "6Lf30PwqAAAAACfRxfocU0gf-EAtO13Av4NyP0l9"; // Thay bằng key thực tế

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            String postParams = "secret=" + secretKey + "&response=" + gRecaptchaResponse;
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = postParams.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Đọc phản hồi từ Google
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            return jsonResponse.get("success").getAsBoolean();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}