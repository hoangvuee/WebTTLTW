package DTO;

public class OrderItemDTO {
    private String id;
    private String idOrder;
    private String idProduct;
    private int quantity;
    private double price;
    private String nameProduct;
    private double weight;
    private boolean isRated;

    // Constructors
    public OrderItemDTO() {
    }

    public OrderItemDTO(String id, String idOrder, String idProduct, int quantity, double price,
                        String nameProduct, double weight, boolean isRated) {
        this.id = id;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
        this.nameProduct = nameProduct;
        this.weight = weight;
        this.isRated = isRated;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isRated() {
        return isRated;
    }

    public void setRated(boolean rated) {
        isRated = rated;
    }


    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "id='" + id + '\'' +
                ", idOrder='" + idOrder + '\'' +
                ", idProduct='" + idProduct + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", nameProduct='" + nameProduct + '\'' +
                ", weight=" + weight +
                ", isRated=" + isRated +
                '}';
    }
}
