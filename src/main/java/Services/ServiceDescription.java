package Services;

import DTO.DescriptionDTO;
import Dao.DescriptionDao;
import Models.Description.Description;

import java.sql.SQLException;
import java.util.List;

public class ServiceDescription {
    private DescriptionDao descriptionDao = new DescriptionDao();
    public void addProductDescriptions( int productId, Description[] descriptions) throws SQLException{
        descriptionDao.addProductDescriptions(productId, descriptions);
    }
    public List<DescriptionDTO> getAllDescriptionByIdProduct(String idProduct) {
        return descriptionDao.getAllDescriptionByIdProduct(idProduct);
    }
    }
