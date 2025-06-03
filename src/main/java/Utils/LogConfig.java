package Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LogConfig {
    private static final Logger LOGGER = Logger.getLogger(LogConfig.class.getName());
    private static final String LOG_FOLDER = "logs";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    static {
        try {
            // Tạo thư mục logs nếu chưa tồn tại
            File logDir = new File(LOG_FOLDER);
            if (!logDir.exists()) {
                logDir.mkdir();
            }

            // Tạo file log với tên theo ngày
            String logFile = LOG_FOLDER + File.separator + "app_" + DATE_FORMAT.format(new Date()) + ".log";

            // Cấu hình FileHandler
            FileHandler fileHandler = new FileHandler(logFile, true);
            fileHandler.setFormatter(new SimpleFormatter());

            // Cấu hình ConsoleHandler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());

            // Xóa các handler mặc định
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }

            // Thêm các handler mới
            LOGGER.addHandler(fileHandler);
            LOGGER.addHandler(consoleHandler);

            // Đặt level log
            LOGGER.setLevel(Level.ALL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}