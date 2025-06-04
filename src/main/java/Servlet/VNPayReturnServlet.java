package Servlet;

import Models.OrderInfo;
import Services.GhnOrderService;
import Services.ServiceOrder;
import Services.ServiceTransactionHistory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet("/vnpay-return")
public class VNPayReturnServlet extends HttpServlet {
    private ServiceOrder serviceOrder = new ServiceOrder();
    ServiceTransactionHistory serviceTransactionHistory = new ServiceTransactionHistory();
    private static final String VNP_HASH_SECRET = "LOWGPKSNF7LPKB37CHOPWAED2EIMW0X8";
    private final String GHN_TOKEN = "6900a9ab-3f5f-11f0-9b81-222185cb68c8";
    private final int GHN_SHOP_ID = 196752; // thay bằng shopId thực
    private static final int PAYMENT_METHOD_COD = 2;      // Thanh toán khi nhận hàng
    private static final int PAYMENT_METHOD_VNPAY = 1;    // Thanh toán qua VNPay

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session == null) {
            out.print("{\"success\":false, \"message\":\"Phiên làm việc hết hạn.\"}");
            return;
        }

        OrderInfo orderInfo = (OrderInfo) session.getAttribute("orderInfo");
        if (orderInfo == null) {
            out.print("{\"success\":false, \"message\":\"Không tìm thấy thông tin đơn hàng.\"}");
            return;
        }
        Map<String, String> vnpParams = new HashMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            vnpParams.put(entry.getKey(), entry.getValue()[0]);
        }

        String vnpSecureHash = vnpParams.remove("vnp_SecureHash");
        vnpParams.remove("vnp_SecureHashType");

        List<String> fieldNames = new ArrayList<>(vnpParams.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();

        for (String fieldName : fieldNames) {
            String encodedKey = URLEncoder.encode(fieldName, StandardCharsets.UTF_8);
            String encodedValue = URLEncoder.encode(vnpParams.get(fieldName), StandardCharsets.UTF_8);
            hashData.append(encodedKey).append("=").append(encodedValue).append("&");
        }
        if (hashData.length() > 0) {
            hashData.setLength(hashData.length() - 1);
        }

        String calculatedHash = hmacSHA512(VNP_HASH_SECRET, hashData.toString());

        String message;
        String status;

        if (calculatedHash.equals(vnpSecureHash)) {
            if ("00".equals(vnpParams.get("vnp_ResponseCode"))) {
                message = "Giao dịch thành công!";
                status = "success";
                // Đọc JSON body để lấy paymentMethod
                BufferedReader reader = request.getReader();
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                String paymentMethod = "vnpay"; // mặc định là vnpay

                try {
                    if ("vnpay".equals(paymentMethod)) {
                        String orderCode = GhnOrderService.createGhnOrder(orderInfo, GHN_TOKEN, GHN_SHOP_ID);
                        out.print("{\"success\":true, \"order_code\":\"" + orderCode + "\"}");
                        int orderId = serviceOrder.saveOrder(
                            orderInfo.getUserId(),
                            orderInfo.getTotalPrice(), 
                            orderInfo.getReceiveAddress(), 
                            PAYMENT_METHOD_VNPAY,  // Sử dụng 1 cho VNPay
                            orderCode
                        );
                        serviceOrder.insertPayment(
                            orderInfo.getUserId(),
                            orderId,
                            "VNPay-Thanh toán qua VNPay"  // Thay đổi mô tả phương thức thanh toán
                        );
                        String paymentMethod1 = serviceOrder.paymentMethod(
                            String.valueOf(orderInfo.getUserId()),
                            orderId
                        );
                        serviceOrder.saveOrderDetails(orderId, orderInfo.getItems());
                        serviceTransactionHistory.saveTransactionHistory(
                            orderInfo.getUserId(),
                            orderId,
                            orderInfo.getTotalPrice(),
                            paymentMethod,
                            orderInfo.getReceiveAddress()
                        );
                    } else {
                        out.print("{\"success\":false, \"message\":\"Phương thức thanh toán không hỗ trợ.\"}");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.print("{\"success\":false, \"message\":\"" + e.getMessage() + "\"}");
                }


            } else {
                message = "Giao dịch thất bại!";
                status = "fail";
            }
        } else {
            message = "Chữ ký không hợp lệ!";
            status = "fail";
        }

        request.setAttribute("message", message);
        request.setAttribute("status", status);
        request.setAttribute("orderId", vnpParams.get("vnp_TxnRef"));
        request.setAttribute("amount", vnpParams.get("vnp_Amount")); // Có thể cần chia 100
        request.setAttribute("bankCode", vnpParams.get("vnp_BankCode"));
        request.setAttribute("payDate", vnpParams.get("vnp_PayDate"));

        request.getRequestDispatcher("/payment_result.jsp").forward(request, response);
    }

    private String hmacSHA512(String key, String data) {
        try {
            Mac sha512Hmac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            sha512Hmac.init(secretKey);
            byte[] hashBytes = sha512Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error while hashing", e);
        }
    }
} 