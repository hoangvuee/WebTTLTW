package Dao;

import DTO.DescriptionDTO;

import Models.Description.Description;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DescriptionDao {

    public void addProductDescriptions( int productId, Description[] descriptions) throws SQLException {
        ConnDB dao = new ConnDB();
        Connection conn = dao.getConn();
        String sqlDesc = "INSERT INTO product_descriptions (id_product, title, content) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sqlDesc)) {
            for (Description desc : descriptions) {
                ps.setInt(1, productId);
                ps.setString(2, desc.getTitle());
                ps.setString(3, desc.getContent());
                ps.addBatch(); // gom các lệnh insert lại
            }
            ps.executeBatch(); // thực thi tất cả cùng lúc
        }
    }
    public List<DescriptionDTO> getAllDescriptionByIdProduct(String idProduct) {
        ConnDB dao = new ConnDB();
        List<DescriptionDTO> descriptionDTOS = new ArrayList<>();
        String sql = "SELECT id, title, content FROM product_descriptions WHERE id_product = ?";

        try (Connection conn = dao.getConn();  // Đảm bảo bạn có class DatabaseUtil
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProduct);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DescriptionDTO dto = new DescriptionDTO();
                    dto.setId(rs.getLong("id"));
                    dto.setTitle(rs.getString("title"));
                    dto.setContent(rs.getString("content"));
                    descriptionDTOS.add(dto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return descriptionDTOS;
    }

    public List<DescriptionDTO> getAllDescriptionByIdProduct(String idProduct) {
        ConnDB dao = new ConnDB();
        List<DescriptionDTO> descriptionDTOS = new ArrayList<>();
        String sql = "SELECT id, title, content FROM product_descriptions WHERE id_product = ?";

        try (Connection conn = dao.getConn();  // Đảm bảo bạn có class DatabaseUtil
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProduct);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DescriptionDTO dto = new DescriptionDTO();
                    dto.setId(rs.getLong("id"));
                    dto.setTitle(rs.getString("title"));
                    dto.setContent(rs.getString("content"));
                    descriptionDTOS.add(dto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return descriptionDTOS;
    }


}
