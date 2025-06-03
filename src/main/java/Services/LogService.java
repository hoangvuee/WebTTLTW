package Services;

import Dao.ActivityLogDAO;
import log.ActivityLog;

import java.sql.SQLException;
import java.util.List;

public class LogService {
    private static final ActivityLogDAO activityLogDao = new ActivityLogDAO();

    public static void logUserActivity(String username, String roleName, String action, String details, String ipAddress, String userAgent) {
        ActivityLog activityLog = new ActivityLog(username, roleName, action, details, ipAddress, userAgent);
        activityLogDao.log(activityLog);
    }
    public List<ActivityLog> getUserActivity() throws SQLException {
        return activityLogDao.getUserActivity();
    }
}
