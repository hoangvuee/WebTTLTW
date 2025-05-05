package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InventoryDao {
    ConnDB dao = new ConnDB();
    private OrderDao orderDao = new OrderDao();
    public int inventoryProduct(int id, int weight) {
        String sql = "SELECT " +
                "    od.idProduct, " +
                "    od.weight, " +
                "    SUM(od.quantity) AS soldQuantity " +
                "FROM " +
                "    order_details od " +
                "JOIN " +
                "    orders o ON od.idOrder = o.id " +
                "WHERE " +
                "    o.isPaid = 1 " +
                "    AND od.idProduct = ? " +
                "    AND od.weight = ? " +
                "GROUP BY " +
                "    od.idProduct, od.weight";

        int soldQuantity = 0;
        try (Connection conn = dao.getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set tham số cho PreparedStatement
            pstmt.setInt(1, id);
            pstmt.setInt(2, weight);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    soldQuantity = rs.getInt("soldQuantity");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soldQuantity;
    }
    public Map<String, Integer> getAllSoldQuantities() {
        String sql = "SELECT od.idProduct, od.weight, SUM(od.quantity) AS soldQuantity " +
                "FROM order_details od " +
                "JOIN orders o ON od.idOrder = o.id " +
                "WHERE o.isActive = 1 " +
//        chuyen thanh isPaid        "WHERE o.isPaid = 1 " +
                "GROUP BY od.idProduct, od.weight";

        Map<String, Integer> soldMap = new HashMap<>();

        try (Connection conn = dao.getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int idProduct = rs.getInt("idProduct");
                int weight = rs.getInt("weight");
                int sold = rs.getInt("soldQuantity");

                String key = idProduct + "_" + weight;
                soldMap.put(key, sold);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soldMap;
    }

}
