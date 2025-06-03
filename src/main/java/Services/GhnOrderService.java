package Services;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import java.text.SimpleDateFormat;

import Models.Item;
import Models.Order.OrderDTO;
import Models.OrderInfo;
import Models.User.UserDTO;
import Models.cart.CartProduct;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;



public class GhnOrderService {
    private static final String GHN_TOKEN = "6900a9ab-3f5f-11f0-9b81-222185cb68c8";
    private static final int GHN_SHOP_ID = 196752; // thay bằng shopId thực
    private ServiceUser serviceUser = new ServiceUser();
    private ServiceOrder serviceOrder = new ServiceOrder();
    private static final String GHN_CANCEL_ENDPOINT = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/switch-status/cancel";

    public static String createGhnOrder(OrderInfo orderInfo, String token, int shopId) throws IOException {
        URL url = new URL("https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Token", token);
        conn.setRequestProperty("ShopId", String.valueOf(shopId));
        conn.setDoOutput(true);

        double totalWeight = orderInfo.getItems()
                .stream()
                .mapToDouble(Item::getWeight)
                .sum();
        System.out.println(totalWeight + "djlhfsdfsdf");
        JSONArray itemsArray = new JSONArray();
        int i = 1;
        for (Item item : orderInfo.getItems()) {
            System.out.println(item.toString() + "them sp");
            JSONObject itemJson = new JSONObject();
            itemJson.put("name", item.getName());
            itemJson.put("code", item.getCode()); // hoặc item.getCode() nếu có
            itemJson.put("quantity", item.getQuantity());
            itemJson.put("price", item.getPrice());
            itemJson.put("length", 10);  // hoặc item.getLength() nếu có
            itemJson.put("width", 10);
            itemJson.put("height", 10);
            itemJson.put("weight", item.getWeight());

            JSONObject category = new JSONObject();
            category.put("level1", "Thực phẩm"); // hoặc lấy từ item nếu có
            itemJson.put("category", category);
            itemsArray.put(itemJson);
        }


        JSONObject json = new JSONObject();
        json.put("payment_type_id", 2);
        json.put("note",orderInfo.getNotes());
        json.put("required_note", "KHONGCHOXEMHANG");
        json.put("return_phone","0868032463");
        json.put("return_address","199 Nguyễn Đình Chính, Phường 11, Quận Phú Nhuận, Tp.HCM");
        json.put("to_name", orderInfo.getFullName());
        json.put("to_phone", orderInfo.getPhoneNumber());
        json.put("to_address", orderInfo.getAddress());
        json.put("to_ward_code", orderInfo.getWard_id());
        json.put("to_district_id", Integer.valueOf(orderInfo.getDistrict_id()));
        json.put("cod_amount", orderInfo.getTotalPrice());
        json.put("weight", totalWeight );
        json.put("length", 50);
        json.put("width", 50);
        json.put("height", 10);
        json.put("service_type_id",Integer.valueOf(orderInfo.getService_id()));
        json.put("items",itemsArray);
        System.out.println(json.toString());

        OutputStream os = conn.getOutputStream();
        byte[] input = json.toString().getBytes("utf-8");
        os.write(input, 0, input.length);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line.trim());
        }
        conn.disconnect();

        System.out.println("GHN Response: " + response.toString());

        JSONObject jsonResponse = new JSONObject(response.toString());
        if (jsonResponse.getInt("code") == 200) {
            // Lấy mã vận đơn (order_code) từ data
            String orderCode = jsonResponse.getJSONObject("data").getString("order_code");
            return orderCode;
        } else {
            throw new IOException("GHN API error: " + jsonResponse.getString("message"));
        }
    }
    
    public static JSONObject getOrderInfo(String orderCode, String token, int shopId) throws IOException {
        URL url = new URL("https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/detail");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Token", token);
        conn.setRequestProperty("ShopId", String.valueOf(shopId));
        conn.setDoOutput(true);

        // Create request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("order_code", orderCode);

        // Write request body
        OutputStream os = conn.getOutputStream();
        byte[] input = requestBody.toString().getBytes("utf-8");
        os.write(input, 0, input.length);

        // Read response
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line.trim());
        }
        conn.disconnect();

        // Parse response
        JSONObject jsonResponse = new JSONObject(response.toString());
        if (jsonResponse.getInt("code") == 200) {
            return jsonResponse.getJSONObject("data");
        } else {
            throw new IOException("GHN API error: " + jsonResponse.getString("message"));
        }
    }

    public static JSONObject getDistrictInfo(int districtId, String token) throws IOException {
        URL url = new URL("https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Token", token);
        conn.setDoOutput(false);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line.trim());
        }
        conn.disconnect();

        JSONObject jsonResponse = new JSONObject(response.toString());
        if (jsonResponse.getInt("code") == 200) {
            JSONArray districts = jsonResponse.getJSONArray("data");
            for (int i = 0; i < districts.length(); i++) {
                JSONObject district = districts.getJSONObject(i);
                if (district.getInt("DistrictID") == districtId) {
                    // Lấy thông tin tỉnh/thành phố
                    int provinceId = district.getInt("ProvinceID");
                    JSONObject province = getProvinceInfo(provinceId, token);
                    district.put("ProvinceName", province.getString("ProvinceName"));
                    return district;
                }
            }
        }
        throw new IOException("District not found");
    }

    public static JSONObject getProvinceInfo(int provinceId, String token) throws IOException {
        URL url = new URL("https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Token", token);
        conn.setDoOutput(false);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line.trim());
        }
        conn.disconnect();

        JSONObject jsonResponse = new JSONObject(response.toString());
        if (jsonResponse.getInt("code") == 200) {
            JSONArray provinces = jsonResponse.getJSONArray("data");
            for (int i = 0; i < provinces.length(); i++) {
                JSONObject province = provinces.getJSONObject(i);
                if (province.getInt("ProvinceID") == provinceId) {
                    return province;
                }
            }
        }
        throw new IOException("Province not found");
    }

    public static JSONObject getWardInfo(String wardCode, int districtId, String token) throws IOException {
        URL url = new URL("https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=" + districtId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Token", token);
        conn.setDoOutput(false);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line.trim());
        }
        conn.disconnect();

        JSONObject jsonResponse = new JSONObject(response.toString());
        if (jsonResponse.getInt("code") == 200) {
            JSONArray wards = jsonResponse.getJSONArray("data");
            for (int i = 0; i < wards.length(); i++) {
                JSONObject ward = wards.getJSONObject(i);
                if (ward.getString("WardCode").equals(wardCode)) {
                    return ward;
                }
            }
        }
        throw new IOException("Ward not found");
    }

    public static String getFullAddress(int districtId, String wardCode, String token) throws IOException {
        JSONObject district = getDistrictInfo(districtId, token);
        JSONObject ward = getWardInfo(wardCode, districtId, token);
        
        return ward.getString("WardName") + ", " + 
               district.getString("DistrictName") + ", " + 
               district.getString("ProvinceName");
    }

    public static String getWardName(String wardCode, int districtId, String token) throws IOException {
        JSONObject wardInfo = getWardInfo(wardCode, districtId, token);
        return wardInfo.getString("WardName");
    }

    public static JSONObject getReceiverInfo(String orderCode, String token, int shopId) throws IOException {
        URL url = new URL("https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/detail");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Token", token);
        conn.setRequestProperty("ShopId", String.valueOf(shopId));
        conn.setDoOutput(true);

        // Create request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("order_code", orderCode);

        // Write request body
        OutputStream os = conn.getOutputStream();
        byte[] input = requestBody.toString().getBytes("utf-8");
        os.write(input, 0, input.length);

        // Read response
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line.trim());
        }
        conn.disconnect();

        // Parse response
        JSONObject jsonResponse = new JSONObject(response.toString());
        if (jsonResponse.getInt("code") == 200) {
            JSONObject data = jsonResponse.getJSONObject("data");
            JSONObject receiverInfo = new JSONObject();

            // Lấy thông tin người nhận
            receiverInfo.put("to_name", data.getString("to_name"));
            receiverInfo.put("to_phone", data.getString("to_phone"));
            receiverInfo.put("to_address", getFullAddress(data.getInt("to_district_id"),data.getString("to_ward_code"),GHN_TOKEN));
            receiverInfo.put("to_district_id", data.getInt("to_district_id"));
            receiverInfo.put("to_ward_code", data.getString("to_ward_code"));

            // Thêm thông tin thời gian
            receiverInfo.put("order_date", data.getString("order_date")); // Thời gian đặt hàng
            receiverInfo.put("leadtime", data.getString("leadtime")); // Thời gian dự kiến giao hàng

            return receiverInfo;
        } else {
            throw new IOException("GHN API error: " + jsonResponse.getString("message"));
        }
    }

    public static JSONObject getOrderDetails(String orderCode) throws IOException {
  String GHN_TOKEN = "6900a9ab-3f5f-11f0-9b81-222185cb68c8";
    int GHN_SHOP_ID = 196752; // thay bằng shopId thực

        URL url = new URL("https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/detail");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Token", GHN_TOKEN);
        conn.setRequestProperty("ShopId", String.valueOf(GHN_SHOP_ID));
        conn.setDoOutput(true);

        // Create request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("order_code", orderCode);

        // Write request body
        OutputStream os = conn.getOutputStream();
        byte[] input = requestBody.toString().getBytes("utf-8");
        os.write(input, 0, input.length);

        // Read response
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line.trim());
        }
        conn.disconnect();

        // Parse response
        JSONObject jsonResponse = new JSONObject(response.toString());
        if (jsonResponse.getInt("code") == 200) {
            JSONObject data = jsonResponse.getJSONObject("data");
            JSONObject orderDetails = new JSONObject();

            // Lấy thông tin cơ bản
            orderDetails.put("order_code", data.getString("order_code"));
            orderDetails.put("to_name", data.getString("to_name"));
            orderDetails.put("to_phone", data.getString("to_phone"));
            orderDetails.put("to_address",  getFullAddress(data.getInt("to_district_id"),data.getString("to_ward_code"),GHN_TOKEN));
            orderDetails.put("status", data.getString("status"));
            orderDetails.put("order_date", data.getString("order_date"));
            orderDetails.put("leadtime", data.getString("leadtime"));

            // Lấy thông tin sản phẩm
            JSONArray items = data.getJSONArray("items");
            JSONArray products = new JSONArray();
            for (int i = 0; i < items.length(); i++) {

                JSONObject item = items.getJSONObject(i);
                JSONObject product = new JSONObject();
                product.put("name", item.getString("name"));
                product.put("quantity", item.getInt("quantity"));
                products.put(product);
            }
            orderDetails.put("products", products);

            return orderDetails;
        } else {
            throw new IOException("GHN API error: " + jsonResponse.getString("message"));
        }
    }
    public static List<JSONObject> getMultipleOrderDetails(List<String> orderCodes) throws IOException {
        String GHN_TOKEN = "6900a9ab-3f5f-11f0-9b81-222185cb68c8";
        int GHN_SHOP_ID = 196752; // thay bằng shopId thực
        List<JSONObject> orderDetailsList = new ArrayList<>();

        for (String orderCode : orderCodes) {
            try {
                JSONObject orderDetails = getOrderDetails(orderCode);
                orderDetailsList.add(orderDetails);
            } catch (IOException e) {
                // Ghi log lỗi, tiếp tục với order tiếp theo
                System.err.println("Error fetching details for order code: " + orderCode + ". Reason: " + e.getMessage());

                JSONObject errorObj = new JSONObject();
                errorObj.put("order_code", orderCode);
                errorObj.put("error", e.getMessage());
                orderDetailsList.add(errorObj);
            }
        }

        return orderDetailsList;
    }
    public  List<OrderDTO> getAllOrder(List<String> orderCodes) throws IOException, SQLException {
        List<JSONObject> jsonObjects = getMultipleOrderDetails(orderCodes);

        List<OrderDTO> dtoList = new ArrayList<>();

        for (JSONObject json : jsonObjects) {
            if (json.has("error")) {
                OrderDTO errorDto = new OrderDTO();
                errorDto.setOrderId(json.optString("order_code", null));
                errorDto.setStatus("ERROR: " + json.optString("error", "Unknown"));
                dtoList.add(errorDto);
            } else {
                OrderDTO dto = serviceOrder.mapToOrderDTO(json);
                dtoList.add(dto);
            }
        }

        return dtoList;
    }

    public void cancelGHNOrder(String orderCode) throws IOException {
        URL url = new URL(GHN_CANCEL_ENDPOINT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Token", GHN_TOKEN);
        connection.setDoOutput(true);

        String jsonInputString = "{\"order_codes\": [\"" + orderCode + "\"]}";

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = connection.getResponseCode();
        System.out.println("GHN Cancel Response Code: " + code);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("GHN Cancel Response: " + response);
        }
    }
    public static void main(String[] args) throws IOException {
        // Test getOrderInfo
        System.out.println("Order Info:");
        System.out.println(getOrderInfo("LB48DT", GHN_TOKEN, GHN_SHOP_ID));
        
        System.out.println("\nReceiver Info:");
        JSONObject receiverInfo = getReceiverInfo("LB48DT", GHN_TOKEN, GHN_SHOP_ID);
        System.out.println("Tên người nhận: " + receiverInfo.getString("to_name"));
        System.out.println("Số điện thoại: " + receiverInfo.getString("to_phone"));
        System.out.println("Địa chỉ: " +  receiverInfo.getString("to_address") +" " +getFullAddress(receiverInfo.getInt("to_district_id"),receiverInfo.getString("to_ward_code"),GHN_TOKEN));
        System.out.println("Thời gian đặt hàng: " + receiverInfo.getString("order_date"));
        System.out.println("Thời gian dự kiến giao: " + receiverInfo.getString("leadtime"));
        System.out.println(getOrderDetails("LB48DT"));
        


    }
}
