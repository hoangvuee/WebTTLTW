package com.example.vnpay.config;

import java.util.Properties;

public class VNPayConfig {
    public static String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static String vnp_ReturnUrl = "http://localhost:8080/WebFinall-1.0-SNAPSHOT/api/vnpay/vnpay-payment";
    public static String vnp_TmnCode = "YOUR_TMN_CODE"; // Mã website tại VNPAY 
    public static String vnp_HashSecret = "YOUR_HASH_SECRET"; // Chuỗi bí mật
    public static String vnp_apiUrl = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction";

    public static Properties getVNPayProperties() {
        Properties props = new Properties();
        props.setProperty("vnp_PayUrl", vnp_PayUrl);
        props.setProperty("vnp_ReturnUrl", vnp_ReturnUrl);
        props.setProperty("vnp_TmnCode", vnp_TmnCode);
        props.setProperty("vnp_HashSecret", vnp_HashSecret);
        props.setProperty("vnp_apiUrl", vnp_apiUrl);
        return props;
    }
} 