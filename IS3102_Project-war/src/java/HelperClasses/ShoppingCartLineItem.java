package HelperClasses;
//###

import java.sql.Time;
import java.util.Date;

public class ShoppingCartLineItem{
    private String id;
    private String SKU;
    private String name;
    private String imageURL;
    private double price;
    private int quantity;
    private long countryID;
    private Date datePurchased;
    private Time timePurchased;
    private String storeName;
    private String storeAddress;
    private int orderId;

    public ShoppingCartLineItem() {
        this.id="";
        this.SKU = "";
        this.name = "";
        this.imageURL = "";
        this.price = 0.0;
        this.quantity = 0;
        this.countryID = 0;
        this.datePurchased = null;
        this.timePurchased = null;
        this.storeName = "";
        this.storeAddress = "";
        this.orderId = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCountryID() {
        return countryID;
    }

    public void setCountryID(long countryID) {
        this.countryID = countryID;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }
    
    public Time getTimePurchased() {
        return timePurchased;
    }

    public void setTimePurchased(Time timePurchased) {
        this.timePurchased = timePurchased;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof ShoppingCartLineItem) {
            ShoppingCartLineItem shoppingCartLineItem = (ShoppingCartLineItem) obj;
            result = shoppingCartLineItem.SKU.equals(this.SKU);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.SKU != null ? this.SKU.hashCode() : 0);
        return hash;
    }

}
