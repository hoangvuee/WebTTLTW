package Dao;

import Models.Sale.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SaleDao {
    public void insertSales(int variantId, Sale[] sales) throws SQLException {
        ConnDB dao = new ConnDB();
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

}
