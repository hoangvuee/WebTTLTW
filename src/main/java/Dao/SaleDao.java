package Dao;


import DTO.PromotionDTO;
import DTO.SaleDTO;
import Models.Sale.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaleDao {
    ConnDB dao = new ConnDB();
    public void insertSales(int variantId, Sale[] sales) throws SQLException {


        Connection conn = dao.getConn();
        String sql = "INSERT INTO sales (idVariant, salePercent, saleStartDate, saleEndDate) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Sale sale : sales) {
                ps.setInt(1, variantId);
                ps.setDouble(2, sale.getSalePersent());
                ps.setTimestamp(3, new Timestamp(sale.getSaleStartDate().getTime()));
                ps.setTimestamp(4, new Timestamp(sale.getSaleEndDate().getTime()));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
    public double getSaleByIdVariant(String idVariant) {
        double salePercent = 0.0;
        String sql = "SELECT salePercent " +
                "FROM sales " +
                "WHERE idVariant = ? " +
                "AND CURRENT_DATE BETWEEN saleStartDate AND saleEndDate";

        try (PreparedStatement stmt = dao.conn.prepareStatement(sql)) {
            stmt.setString(1, idVariant);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    salePercent = rs.getDouble("salePercent");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salePercent;
    }
    public Map<String, Double> getSalesByProductId(int idProduct) {
        Map<String, Double> sales = new HashMap<>();

        String sql = "SELECT s.idVariant, s.salePercent " +
                "FROM sales s " +
                "JOIN product_variants pv ON s.idVariant = pv.id " +
                "WHERE pv.idProduct = ? " +
                "AND CURRENT_DATE BETWEEN s.saleStartDate AND s.saleEndDate";

        try (PreparedStatement stmt = dao.conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduct);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String idVariant = rs.getString("idVariant");
                    double sale = rs.getDouble("salePercent");
                    sales.put(idVariant, sale);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sales;
    }
    public SaleDTO getFullSaleByIdVariant(String idVariant) {
        SaleDTO saleDTO = new SaleDTO();

        String sql = "SELECT id,salePercent, saleStartDate, saleEndDate " +
                "FROM sales " +
                "WHERE idVariant = ? " +
                "AND CURRENT_DATE BETWEEN saleStartDate AND saleEndDate";

        try (PreparedStatement stmt = dao.conn.prepareStatement(sql)) {
            stmt.setString(1, idVariant);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Lấy thông tin khuyến mãi từ kết quả truy vấn
                    saleDTO.setSalePersent(rs.getDouble("salePercent"));
                    saleDTO.setSaleStartDate(rs.getTimestamp("saleStartDate"));
                    saleDTO.setSaleEndDate(rs.getTimestamp("saleEndDate"));
                    saleDTO.setId(rs.getInt("id"));
                } else {
                    // Nếu không có khuyến mãi, gán giá trị mặc định cho saleDTO
                    saleDTO.setSalePersent(0.0);  // Gán giá trị mặc định cho salePercent
                    saleDTO.setSaleStartDate(null);  // Không có ngày bắt đầu
                    saleDTO.setSaleEndDate(null);  // Không có ngày kết thúc
                    saleDTO.setId(-1);
                    System.out.println("No active sale found for idVariant: " + idVariant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return saleDTO;
    }
    public List<PromotionDTO> getFullPromotionByIdVariant(String idVariant) {
        List<PromotionDTO> promotionList = new ArrayList<>();

        String sql = "SELECT salePercent, saleStartDate, saleEndDate " +
                "FROM sales " +
                "WHERE idVariant = ? " +
                "AND saleStartDate > CURRENT_DATE " +
                "AND saleEndDate >= CURRENT_DATE";
        try (PreparedStatement stmt = dao.conn.prepareStatement(sql)) {
            stmt.setString(1, idVariant);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PromotionDTO promotionDTO = new PromotionDTO();
                    promotionDTO.setSalePercent(rs.getDouble("salePercent"));
                    promotionDTO.setStartDate(rs.getTimestamp("saleStartDate"));
                    promotionDTO.setEndDate(rs.getTimestamp("saleEndDate"));
                    promotionList.add(promotionDTO);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (promotionList.isEmpty()) {
            System.out.println("No active promotions found for idVariant: " + idVariant);
        }
        return promotionList;
    }


    public static void main(String[] args) {
        SaleDao saleDao = new SaleDao();
        System.out.println(saleDao.getSaleByIdVariant(String.valueOf(139)));
    }

}
