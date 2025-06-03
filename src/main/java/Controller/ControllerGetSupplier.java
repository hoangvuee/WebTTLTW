package Controller;

import Models.Supplier.Supplier;
import Services.ServiceAddSupplier;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/getSuppliers")
public class ControllerGetSupplier extends HttpServlet {
    Supplier items = new Supplier();
    ServiceAddSupplier serviceAddSupplier = new ServiceAddSupplier();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            items = serviceAddSupplier.getSupplier();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            // Convert List<Category> to JSON
            Gson gson = new Gson();
            String json = gson.toJson(items);

            resp.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Có lỗi xảy ra!\"}");
        }
    }
}
