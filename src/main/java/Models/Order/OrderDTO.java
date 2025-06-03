package Models.Order;

import Models.Product.Product;
import Models.Product.ProductDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private String orderId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String address;
    private String orderDate;
    private List<ProductDTO> products;
    private String expectedDelivery;
    private String status;

    public OrderDTO() {
    }

    // Getters and setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public List<ProductDTO> getProducts() {
        return products;
    }
    public static OrderDTO mapToOrderDTO(JSONObject orderDetails) {
        OrderDTO dto = new OrderDTO();

        dto.setOrderId(orderDetails.optString("order_code", null));
        dto.setCustomerName(orderDetails.optString("to_name", null));
        dto.setCustomerPhone(orderDetails.optString("to_phone", null));
        dto.setAddress(orderDetails.optString("to_address", null));
        dto.setOrderDate(orderDetails.optString("order_date", null));
        dto.setExpectedDelivery(orderDetails.optString("leadtime", null));
        dto.setStatus(orderDetails.optString("status", null));

        // Nếu có customerEmail trong dữ liệu khác, bạn có thể lấy tại đây
        dto.setCustomerEmail(null); // Vì GHN API không trả về

        // Parse danh sách sản phẩm
        JSONArray productsArray = orderDetails.optJSONArray("products");
        List<ProductDTO> productList = new ArrayList<>();
        if (productsArray != null) {
            for (int i = 0; i < productsArray.length(); i++) {
                JSONObject productJson = productsArray.getJSONObject(i);
                ProductDTO product = new ProductDTO();
                product.setName(productJson.optString("name", null));
                product.setQuantity(productJson.optInt("quantity", 0));
                product.setId(null);   // Nếu GHN không có ID
                product.setPrice(0.0); // Nếu GHN không trả về giá

                productList.add(product);
            }
        }
        dto.setProducts(productList);

        return dto;
    }


    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public OrderDTO(String orderId, String customerName, String customerEmail, String customerPhone, String address, String orderDate, List<ProductDTO> products, String expectedDelivery, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.address = address;
        this.orderDate = orderDate;
        this.products = products;
        this.expectedDelivery = expectedDelivery;
        this.status = status;
    }

    public String getExpectedDelivery() { return expectedDelivery; }
    public void setExpectedDelivery(String expectedDelivery) { this.expectedDelivery = expectedDelivery; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
