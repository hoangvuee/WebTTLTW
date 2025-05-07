package Dao;

import log.ActivityLog;
import Utils.LogConfig;

import java.sql.*;

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

    public void logUserActivity(String username, String roleName, String action, String details, String ipAddress, String userAgent) {
        ActivityLog activityLog = new ActivityLog(username, roleName, action, details, ipAddress, userAgent);
        log(activityLog);
    }
}