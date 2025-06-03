package Controller;

import Models.Category.Category;
import Services.ServiceAddCategories;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/getCategories")
public class ControllerGetCategories extends HttpServlet {



        ServiceAddCategories categoriesService = new ServiceAddCategories();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                Category categories = categoriesService.getCategories();

                // Set content type for JSON
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                // Convert List<Category> to JSON
                Gson gson = new Gson();
                String json = gson.toJson(categories);

                resp.getWriter().write(json);

            } catch (SQLException e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\": \"Có lỗi xảy ra!\"}");
            }
        }
    }


