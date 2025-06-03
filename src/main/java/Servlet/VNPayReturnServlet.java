package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet("/vnpay-return")
public class VNPayReturnServlet extends HttpServlet {
    private static final String VNP_HASH_SECRET = "LOWGPKSNF7LPKB37CHOPWAED2EIMW0X8";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = params.nextElement();
            String fieldValue = request.getParameter(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = fields.remove("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }

        // Sắp xếp các tham số theo thứ tự a-z
        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        for (int i = 0; i < fieldNames.size(); i++) {
            String fieldName = fieldNames.get(i);
            String fieldValue = fields.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                hashData.append(fieldName).append('=').append(URLDecoder.decode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (i < fieldNames.size() - 1) {
                    hashData.append('&');
                }
            }
        }

        String checkSum = hmacSHA512(VNP_HASH_SECRET, hashData.toString());
        
        // Log để debug
        System.out.println("Hash data: " + hashData.toString());
        System.out.println("Expected hash: " + checkSum);
        System.out.println("Received hash: " + vnp_SecureHash);
        
        // Kiểm tra kết quả thanh toán
        if (checkSum.equals(vnp_SecureHash)) {
            String vnp_ResponseCode = fields.get("vnp_ResponseCode");
            if ("00".equals(vnp_ResponseCode)) {
                // Thanh toán thành công
                request.setAttribute("status", "success");
                request.setAttribute("message", "Thanh toán thành công!");
                request.setAttribute("orderId", fields.get("vnp_TxnRef"));
                request.setAttribute("amount", fields.get("vnp_Amount"));
                request.setAttribute("bankCode", fields.get("vnp_BankCode"));
                request.setAttribute("payDate", fields.get("vnp_PayDate"));
            } else {
                // Thanh toán thất bại
                request.setAttribute("status", "error");
                request.setAttribute("message", "Thanh toán thất bại! Mã lỗi: " + vnp_ResponseCode);
            }
        } else {
            // Chữ ký không hợp lệ
            request.setAttribute("status", "error");
            request.setAttribute("message", "Chữ ký không hợp lệ! Vui lòng liên hệ hỗ trợ.");
        }

        // Chuyển hướng đến trang kết quả
        request.getRequestDispatcher("/payment-result.jsp").forward(request, response);
    }

    private String hmacSHA512(String key, String data) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA512");
            javax.crypto.spec.SecretKeySpec secretKeySpec = new javax.crypto.spec.SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
} 