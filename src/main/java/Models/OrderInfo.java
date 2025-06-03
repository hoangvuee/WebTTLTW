package Models;
import Models.cart.Cart;

import java.util.List;

public class OrderInfo {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String city;
    private String district;
    private String ward;
    private String address;
    private String notes;
    private String paymentMethod;
    private double shippingFee;
    private int userId;
    private double totalPrice;

    private String receiveAddress;
    private List<Item> items;
    private String provider;
    private String district_id;
    private String ward_id;
    private String service_id;

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }



    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getWard_id() {
        return ward_id;
    }

    public void setWard_id(String ward_id) {
        this.ward_id = ward_id;
    }

    public String getProvider() {
        return provider;
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public OrderInfo(String fullName, String phoneNumber, String email, String city, String district, String ward, String address, String notes, String paymentMethod, double shippingFee, int userId, double totalPrice, String receiveAddress, List<Item> items, String provider, String district_id, String ward_id, String service_id) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.notes = notes;
        this.paymentMethod = paymentMethod;
        this.shippingFee = shippingFee;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.receiveAddress = receiveAddress;
        this.items = items;
        this.provider = provider;
        this.district_id = district_id;
        this.ward_id = ward_id;
        this.service_id = service_id;
    }

    // Constructors
    public OrderInfo() {
    }

    public OrderInfo(String fullName, String phoneNumber, String email, String city,
                     String district, String ward, String address, String notes,
                     String paymentMethod, double shippingFee, int userId,
                     double totalPrice, String receiveAddress) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.notes = notes;
        this.paymentMethod = paymentMethod;
        this.shippingFee = shippingFee;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.receiveAddress = receiveAddress;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }
}
