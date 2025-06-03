package Dao;

import log.ActivityLog;
import Utils.LogConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityLogDAO {
    private ConnDB dao;

    public ActivityLogDAO() {
        this.dao = new ConnDB();
    }

    public void log(ActivityLog activityLog) {
        String sql = "INSERT INTO activity_logs (username, role_name, action, details, ip_address, user_agent) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, activityLog.getUsername());
            stmt.setString(2, activityLog.getRoleName());    // Đổi setInt thành setString
            stmt.setString(3, activityLog.getAction());
            stmt.setString(4, activityLog.getDetails());
            stmt.setString(5, activityLog.getIpAddress());
            stmt.setString(6, activityLog.getUserAgent());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LogConfig.getLogger().severe("Error logging activity: " + e.getMessage());
        }
    }

    public List<ActivityLog> getUserActivity() throws SQLException {
        String sql = "SELECT username, role_name, details, created_at FROM activity_logs ORDER BY created_at DESC LIMIT 10";
        List<ActivityLog> logs = new ArrayList<>();
        try (Connection conn = dao.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ActivityLog log = new ActivityLog();
                log.setUsername(rs.getString("username"));
                log.setRoleName(rs.getString("role_name")); // role
                log.setDetails(rs.getString("details"));
                log.setCreatedAt(rs.getTimestamp("created_at")); // Time
                logs.add(log);
            }
        }
        return logs;
    }

    public static void main(String[] args) {
        ActivityLogDAO activityLog = new ActivityLogDAO();
        try {
            List<ActivityLog> logs = activityLog.getUserActivity();
            for (ActivityLog log : logs) {
                System.out.println("Username: " + log.getUsername());
                System.out.println("Role Name: " + log.getRoleName());
                System.out.println("Details: " + log.getDetails());
                System.out.println("Created At: " + log.getCreatedAt());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            LogConfig.getLogger().severe("Error getting user activity: " + e.getMessage());
        }
    }
}