package Services;

import Dao.ImageDao;

import java.sql.SQLException;
import java.util.List;

public class ServiceImage {
    private ImageDao imageDao = new ImageDao();

    public void insertProductImages(int productId, List<String> imageNames) throws SQLException {
        imageDao.insertProductImages(productId, imageNames);
    }
}

