package Servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/vnpay-payment")
public class VNPayPaymentServlet extends HttpServlet {
    private static final String VNP_TMN_CODE = "4E30I0VE";
    private static final String VNP_HASH_SECRET = "LOWGPKSNF7LPKB37CHOPWAED2EIMW0X8";
    private static final String VNP_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    private static final String VNP_RETURN_URL = "http://vcebook.io.vn:8080/WebFinall/vnpay-return";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vnp_TxnRef = generateOrderId();
        String vnp_OrderInfo = "Thanh toan don hang " + vnp_TxnRef;
        String vnp_OrderType = "billpayment";
        String vnp_Locale = "vn";
        String vnp_IpAddr = request.getRemoteAddr();
        String vnp_CreateDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String vnp_Amount = request.getParameter("amount");
        String vnp_CurrCode = "VND";

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", VNP_TMN_CODE);
        vnp_Params.put("vnp_Amount", String.valueOf(Long.parseLong(vnp_Amount) * 100)); // Số tiền * 100
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        vnp_Params.put("vnp_CurrCode", vnp_CurrCode);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_Locale", vnp_Locale);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", vnp_OrderType);
        vnp_Params.put("vnp_ReturnUrl", VNP_RETURN_URL);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);

        // Sắp xếp các tham số theo thứ tự a-z
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        for (int i = 0; i < fieldNames.size(); i++) {
            String fieldName = fieldNames.get(i);
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                // Tạo chuỗi hash data
                hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8));

                // Tạo chuỗi query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.UTF_8))
                     .append('=')
                     .append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8));

                if (i < fieldNames.size() - 1) {
                    hashData.append('&');
                    query.append('&');
                }
            }
        }

        String queryUrl = query.toString();
        String vnp_SecureHash = hmacSHA512(VNP_HASH_SECRET, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;

        String paymentUrl = VNP_URL + "?" + queryUrl;

        // Trả về JSON response
        JsonObject job = new JsonObject();
        job.addProperty("code", "00");
        job.addProperty("message", "success");
        job.addProperty("data", paymentUrl);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(job));
    }

    private String generateOrderId() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + new Random().nextInt(1000);
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