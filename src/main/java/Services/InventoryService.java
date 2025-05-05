package Services;

import Dao.InventoryDao;

public class InventoryService {
    private InventoryDao inventoryDao = new InventoryDao();
    public int inventoryProduct(int id, int weight) {
       return  inventoryDao.inventoryProduct(id,weight);
    }
}
