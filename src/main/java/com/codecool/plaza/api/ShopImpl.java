package com.codecool.plaza.api;

import java.util.List;

public class ShopImpl implements Shop {
    private String name;
    private String owner;
    private boolean open;
    private Map<Long, ShopImpl.ShopEntryImpl> product;

    public String getName(){
        return name;
    }

    public String getOwner(){
        return owner;
    }

    public boolean isOpen(){
        return open;
    }

    public void open(){
        open = true;
    }

    public void close(){
        open = false;
    }

    public Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {
        for (Product prod : product) {
            if (prod.getName().equals(name)) {
                return prod;
            }
        }
        return null;
    }

    public boolean hasProduct(long barcode) throws ShopIsClosedException{

    }

    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException;

    public void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException;

    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException;

    public List<Product> buyProducts (long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException;

    public String toString();

    class ShopImplEntry {
        private Product product;
        private int quantity;
        private float price;

        private ShopImplEntry(Product product, int quantity, float price) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void increaseQuantity(int amount) {
            quantity = quantity + amount;
        }

        public void decreaseQuantity(int amount) {
            quantity = quantity - amount;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String toString() {
            return "Bakker";
        }
    }
}