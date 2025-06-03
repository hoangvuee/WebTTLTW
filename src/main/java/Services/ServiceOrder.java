package Services;

import DTO.OrderItemDTO;
import Dao.ConnDB;
import Dao.OrderDao;
import Dao.PaymentDao;
import Models.Item;
import Models.Order.OrderDTO;
import Models.OrderManage.ListOrder;
import Models.OrderManage.Order;
import Models.Product.ProductDTO;
import Models.cart.Cart;
import Models.cart.CartProduct;
import Models.inforTransaction.Product;
import Models.inforTransaction.Transaction;
import Models.inforTransaction.TransactionHistory;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.management.Query;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrder {
    ConnDB dao = new ConnDB();
    OrderDao orderDao = new OrderDao();
    PaymentDao paymentDao = new PaymentDao();
    public  OrderDTO mapToOrderDTO(JSONObject orderDetails) throws SQLException {
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
        String orderId = findOrderIdByOrderCode(orderDetails.optString("order_code"));
        List<OrderItemDTO> orderItemDTOS = findAllByOrderId(orderId);
        List<ProductDTO> productDTOS = new ArrayList<>();
        System.out.println(orderId);
        System.out.println(productDTOS.toString());
        for (OrderItemDTO orderItemDTO:
             orderItemDTOS) {
            productDTOS.add(new ProductDTO(orderItemDTO.getIdProduct(),orderItemDTO.getNameProduct(),orderItemDTO.getQuantity(),orderItemDTO.getPrice(),orderItemDTO.getWeight()));
        }
        dto.setProducts(productDTOS);
        return dto;
    }
    public List<OrderItemDTO> findAllByOrderId(String orderId) {
        return orderDao.findAllByOrderId(orderId);
    }

        public String findOrderIdByOrderCode(String order_code) throws SQLException {
        return orderDao.findOrderIdByOrderCode(order_code);
    }



    public boolean insertPayment(int idUser, int idOrder, String methodPayment) throws SQLException {
        return orderDao.insertPayment(idUser, idOrder, methodPayment);

    }
    public int saveOrder(int userId, Double totalPrice, String receiveAddress, int shippingId,String order_code ) throws SQLException {

        return orderDao.saveOrder(userId, totalPrice, receiveAddress, shippingId,order_code);
    }
    public List<Product> getOrderDetailsByIdOrder(int idOrder) throws SQLException {

        return orderDao.getOrderDetailsByIdOrder(idOrder);
    }
    public void saveOrderDetails(int orderId, List<Item> items) throws SQLException{
    orderDao.saveOrderDetails(orderId, items);
    }
    public String getReceiveDate(int idUser, int idOrder) throws SQLException {

        return orderDao.getReceiveDate(idUser, idOrder);
    }
    public String paymentMethod(String idUser, int idOrder) throws SQLException {


        return paymentDao.paymentMethod(idUser,idOrder);

    }
    public ListOrder getAllOrders() throws SQLException {

        return orderDao.getAllOrders();
    }
    public ListOrder getOrdersByUserId(int userId) throws SQLException {

        return orderDao.getOrdersByUserId(userId);
    }

    public  boolean updateOrderStatus(int orderId, String status) throws SQLException {
     return orderDao.updateOrderStatus(orderId, status);
    }

    public  boolean deleteOrder(int orderId) throws SQLException {
        return orderDao.deleteOrder(orderId);
    }
    public ListOrder getOrderById(int idOrder) throws SQLException {
      return orderDao.getOrderById(idOrder);
    }
    public boolean updateOrderStatus(int id, int isActive) {
    return orderDao.updateOrderStatus(id, isActive);
    }
    public List<String> getOrdersCode() throws SQLException {
        return orderDao.getOrdersCode();
    }

    }


