package Dao;

import Models.Comment.Comment;
import Models.Comment.Comments;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    private final ConnDB connDB;

    public CommentDao() {
        this.connDB = new ConnDB();
    }

    // Create the comments table if it doesn't exist
    public void createCommentsTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS comments (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "product_id INT NOT NULL, " +
                "user_id INT NOT NULL, " +
                "user_name VARCHAR(255) NOT NULL, " +
                "content TEXT NOT NULL, " +
                "rating INT NOT NULL, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (product_id) REFERENCES products(id), " +
                "FOREIGN KEY (user_id) REFERENCES users(id)" +
                ")";
        
        try (Connection conn = connDB.getConn();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a new comment
    public boolean addComment(Comment comment) {
        String sql = "INSERT INTO comments (product_id, user_id, user_name, content, rating) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = connDB.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, comment.getProductId());
            stmt.setInt(2, comment.getUserId());
            stmt.setString(3, comment.getUserName());
            stmt.setString(4, comment.getContent());
            stmt.setInt(5, comment.getRating());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        comment.setId(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    // Get all comments for a product
    public Comments getCommentsByProductId(int productId) {
        Comments comments = new Comments();
        String sql = "SELECT * FROM comments WHERE product_id = ? ORDER BY created_at DESC";
        
        try (Connection conn = connDB.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, productId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Comment comment = new Comment();
                    comment.setId(rs.getInt("id"));
                    comment.setProductId(rs.getInt("product_id"));
                    comment.setUserId(rs.getInt("user_id"));
                    comment.setUserName(rs.getString("user_name"));
                    comment.setContent(rs.getString("content"));
                    comment.setRating(rs.getInt("rating"));
                    comment.setCreatedAt(rs.getTimestamp("created_at"));
                    
                    comments.addComment(comment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return comments;
    }

    // Get all comments
    public Comments getAllComments() {
        Comments comments = new Comments();
        String sql = "SELECT c.*, p.name as product_name FROM comments c " +
                     "JOIN products p ON c.product_id = p.id " +
                     "ORDER BY c.created_at DESC";
        
        try (Connection conn = connDB.getConn();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setProductId(rs.getInt("product_id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setUserName(rs.getString("user_name"));
                comment.setContent(rs.getString("content"));
                comment.setRating(rs.getInt("rating"));
                comment.setCreatedAt(rs.getTimestamp("created_at"));
                
                comments.addComment(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return comments;
    }

    // Delete a comment
    public boolean deleteComment(int commentId) {
        String sql = "DELETE FROM comments WHERE id = ?";
        
        try (Connection conn = connDB.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, commentId);
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    // Update a comment
    public boolean updateComment(Comment comment) {
        String sql = "UPDATE comments SET content = ?, rating = ? WHERE id = ?";
        
        try (Connection conn = connDB.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, comment.getContent());
            stmt.setInt(2, comment.getRating());
            stmt.setInt(3, comment.getId());
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
