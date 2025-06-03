package Controller.order;

import Models.Order.OrderDTO;
import Models.Product.ProductDTO;
import Services.GhnOrderService;
import Services.ServiceOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/orders/*")
public class OrderController extends HttpServlet {
    private ServiceOrder serviceOrder = new ServiceOrder();
    private GhnOrderService ghnOrderService = new GhnOrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<OrderDTO> orders = null;
        try {
            List<String> orderCodes = serviceOrder.getOrdersCode();

            orders = ghnOrderService.getAllOrder(orderCodes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JSONArray responseJson = new JSONArray();

        for (OrderDTO order : orders) {
            JSONObject json = new JSONObject();
            json.put("orderId", order.getOrderId());
            json.put("customerName", order.getCustomerName());
            json.put("customerEmail", order.getCustomerEmail());
            json.put("customerPhone", order.getCustomerPhone());
            json.put("address", order.getAddress());
            json.put("orderDate", order.getOrderDate());
            json.put("expectedDelivery", order.getExpectedDelivery());
            json.put("status", order.getStatus());

            JSONArray productArray = new JSONArray();
            for (ProductDTO product : order.getProducts()) {
                JSONObject productJson = new JSONObject();
                productJson.put("id", product.getId());
                productJson.put("name", product.getName());
                productJson.put("quantity", product.getQuantity());
                productJson.put("price", product.getPrice());
                productJson.put("weight",product.getWeight());
                productArray.put(productJson);
            }
            json.put("products", productArray);

            responseJson.put(json);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(responseJson.toString());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oderId = req.getParameter("orderId");
        System.out.println(oderId);
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo(); // VD: "/123"
        String orderId = (pathInfo != null && pathInfo.length() > 1) ? pathInfo.substring(1) : null;
        if (orderId == null || orderId.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing orderId in URL");
            return;
        }
        System.out.println("Deleting orderId: " + orderId);

            ghnOrderService.cancelGHNOrder(orderId);
        // Xóa đơn hàng trong DB (nếu cần)
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{\"message\": \"Order deleted successfully\"}");
    }

}
