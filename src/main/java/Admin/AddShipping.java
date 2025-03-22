package Admin;

import Services.ServiceShipping;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String nameShipping = req.getParameter("deliveryService");
        double price = Double.parseDouble(req.getParameter("price"));

        try {
            serviceShipping.addShipping(nameShipping, price);

            // Thêm thông báo thành công vào request attribute
            req.setAttribute("successMessage", "Shipping method added successfully!");

            // Chuyển hướng về trang giao diện thêm dịch vụ
            RequestDispatcher dispatcher = req.getRequestDispatcher("dilivery.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            // Thêm thông báo lỗi vào request attribute
            req.setAttribute("errorMessage", "Error adding shipping method. Please try again.");

            // Chuyển hướng lại về trang giao diện thêm dịch vụ
            RequestDispatcher dispatcher = req.getRequestDispatcher("dilivery.jsp");
            dispatcher.forward(req, resp);
        }
    }
}