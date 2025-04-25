package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ImageDao {
    public void insertProductImages(int productId, List<String> imageNames) throws SQLException {
        Connection conn = new ConnDB().getConn();
        String sql = "INSERT INTO images (idProduct, imageData) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (String name : imageNames) {
                ps.setInt(1, productId);
                ps.setString(2, name);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
