package DTO;

public class ProductDTO {
    private String id;
    private String name;
    private String image;
    private String category;
    private double price;
    private int weight;
    private int inventory;
    private double sale;
    private String status;
    private int quantity;
    private String isActive;
    private String idProduct;

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getImportDate() {
        return importDate;
    }


    private String importDate;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductDTO() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public int getInventory() {
        return inventory;
    }

    public double getSale() {
        return sale;
    }

    public String getStatus() {
        return status;
    }

    public ProductDTO(String id, String name, String image, String category, double price, int weight, int inventory, double sale, String status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.category = category;
        this.price = price;
        this.weight = weight;
        this.inventory = inventory;
        this.sale = sale;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", inventory=" + inventory +
                ", sale=" + sale +
                ", status='" + status + '\'' +
                ", quantity=" + quantity +
                ", isActive='" + isActive + '\'' +
                ", idProduct='" + idProduct + '\'' +
                ", importDate='" + importDate + '\'' +
                '}';
    }
}
