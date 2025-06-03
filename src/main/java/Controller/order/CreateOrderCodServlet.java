package Controller.order;

import Models.OrderInfo;
import Models.cart.Cart;
import Services.GhnOrderService;
import Services.ServiceOrder;
import Services.ServiceTransactionHistory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/create-order-cod")
public class CreateOrderCodServlet extends HttpServlet {
    private ServiceOrder serviceOrder = new ServiceOrder();
    ServiceTransactionHistory serviceTransactionHistory = new ServiceTransactionHistory();


    private final String GHN_TOKEN = "6900a9ab-3f5f-11f0-9b81-222185cb68c8";
    private final int GHN_SHOP_ID = 196752; // thay bằng shopId thực

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(false);
        if (session == null) {
            out.print("{\"success\":false, \"message\":\"Phiên làm việc hết hạn.\"}");
            return;
        }

        OrderInfo orderInfo = (OrderInfo) session.getAttribute("orderInfo");
        if (orderInfo == null) {
            out.print("{\"success\":false, \"message\":\"Không tìm thấy thông tin đơn hàng.\"}");
            return;
        }

        // Đọc JSON body để lấy paymentMethod
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        JSONObject jsonReq = new JSONObject(sb.toString());
        String paymentMethod = jsonReq.optString("paymentMethod");
        System.out.println(paymentMethod + "payment");

        try {
            if ("cod".equals(paymentMethod)) {
                String orderCode = GhnOrderService.createGhnOrder(orderInfo, GHN_TOKEN, GHN_SHOP_ID);
                out.print("{\"success\":true, \"order_code\":\"" + orderCode + "\"}");
                int orderId = serviceOrder.saveOrder(orderInfo.getUserId(),orderInfo.getTotalPrice(), orderInfo.getReceiveAddress(), 2,orderCode);
                serviceOrder.insertPayment(orderInfo.getUserId(),orderId,"COD-Thanh toán khi nhận hàng");
                String paymentMethod1 = serviceOrder.paymentMethod(String.valueOf(orderInfo.getUserId()),orderId);
                serviceOrder.saveOrderDetails(orderId, orderInfo.getItems());
                serviceTransactionHistory.saveTransactionHistory(orderInfo.getUserId(),orderId,orderInfo.getTotalPrice(),paymentMethod,orderInfo.getReceiveAddress());
            } else {
                out.print("{\"success\":false, \"message\":\"Phương thức thanh toán không hỗ trợ.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"success\":false, \"message\":\"" + e.getMessage() + "\"}");
        }
    }
}
