package Dao;

import Models.Description.Description;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
