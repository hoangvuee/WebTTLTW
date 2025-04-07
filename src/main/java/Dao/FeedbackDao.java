package Dao;

import Models.Feedback.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDao {
    ConnDB con = new ConnDB();
    public List<Feedback> getFeedbacksByProductId(int productId) {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "SELECT f.comment, f.create_date, f.rating_rank, f.status, " +
                "od.nameProduct, u.userName, u.birthDate, u.phoneNumber " +
                "FROM feedbacks f " +
                "LEFT JOIN order_details od ON od.id = f.id_order_detail " +
                "INNER JOIN orders ON orders.id = od.idOrder " +
                "INNER JOIN users u ON u.id = orders.idUser " +
                "LEFT JOIN products p ON p.id = od.idProduct " +
                "WHERE p.id = ?";

        try (Connection conn = con.getConn();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setComment(rs.getString("comment"));
                feedback.setCreateDate(rs.getDate("create_date"));
                feedback.setRatingRank(rs.getInt("rating_rank"));
                feedback.setStatus(rs.getString("status"));
                feedback.setNameProduct(rs.getString("nameProduct"));
                feedback.setUserName(rs.getString("userName"));
                feedback.setBirthDate(rs.getDate("birthDate"));
                feedback.setPhoneNumber(rs.getString("phoneNumber"));
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    public static void main(String[] args) {
        FeedbackDao f = new FeedbackDao();
        System.out.println(f.getFeedbacksByProductId(62).toString());
    }
}
