package Services;

import Dao.DescriptionDao;
import Models.Description.Description;

import java.sql.SQLException;

public class ServiceDescription {
    private DescriptionDao descriptionDao = new DescriptionDao();
    public void addProductDescriptions( int productId, Description[] descriptions) throws SQLException{
        descriptionDao.addProductDescriptions(productId, descriptions);
    }
}
