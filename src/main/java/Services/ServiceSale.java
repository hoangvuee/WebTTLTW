package Services;

import Dao.SaleDao;
import Models.Sale.Sale;

import java.sql.SQLException;

public class ServiceSale {
    private SaleDao saleDao = new SaleDao();
    public void insertSales( int variantId, Sale[] sales) throws SQLException{
        saleDao.insertSales(variantId, sales);
    }
}
