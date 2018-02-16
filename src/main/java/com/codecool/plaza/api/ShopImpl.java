package main.java.com.codecool.plaza.api;

import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop {
    private String name;
    private String owner;
    private boolean open;
    private Map<Long, ShopImplEntry> product;

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
        if (!open) {
            throw new ShopIsClosedException("Sorry, " + this.getName() + " is closed.");
        }
        for (long i=0; i<product.size();i++) {
            if (product.get(i).getProduct().getName().equals(name)) {
                return product.get(i).getProduct();
            }
        }
        throw new NoSuchProductException("Sorry, this product is not available here");
    }

    public boolean hasProduct(long barcode) throws ShopIsClosedException{
        if (!open) {
            throw new ShopIsClosedException("Sorry, " + this.getName() + " is closed.");
        }
        for (long i=0; i<product.size();i++) {
            if (product.get(i).getProduct().getBarcode() == barcode) {
                return true;
            }
        }
        return false;
    }

    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {
        if (!open) {
            throw new ShopIsClosedException("Sorry, " + this.getName() + " is closed.");
        }
        for (ShopImplEntry entry : this.product.values()) {
            if (entry.getProduct().equals(product)) {
                throw new ProductAlreadyExistsException("Shop already has this product!");
            }
        }
        this.product.put(Long.valueOf((this.product.size())+1), new ShopImplEntry(product, quantity, price));
    }

    public void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException {
        if (!open) {
            throw new ShopIsClosedException("Sorry, " + this.getName() + " is closed.");
        }
        for (long i=0; i<product.size();i++) {
            if (product.get(i).getProduct().getBarcode() == barcode) {
                product.get(i).increaseQuantity(quantity);
            }
        }
        throw new NoSuchProductException("Product is not available in this shop.");
    }

    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        if (!open) {
            throw new ShopIsClosedException("Sorry, " + this.getName() + " is closed.");
        }
        for (long i=0; i<product.size();i++) {
            if (product.get(i).getProduct().getBarcode() == barcode) {
                if (product.get(i).getQuantity() == 0) {
                    throw new OutOfStockException("Sorry, there is no more " + product.get(i).getProduct().getName() + " available.");
                }
                product.get(i).decreaseQuantity(1);
            }
        }
        throw new NoSuchProductException("Product is not available in this shop.");
    }

    public List<Product> buyProducts (long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        if (!open) {
            throw new ShopIsClosedException("Sorry, " + this.getName() + " is closed.");
        }
        for (long i=0; i<product.size();i++) {
            if (product.get(i).getProduct().getBarcode() == barcode) {
                if (product.get(i).getQuantity() < quantity) {
                    throw new OutOfStockException("Sorry, there is not enough " + product.get(i).getProduct().getName() + " available.");
                }
                product.get(i).decreaseQuantity(quantity);
            }
        }
        throw new NoSuchProductException("Product is not available in this shop.");
    }

    @Override
    public String toString() {
        return "ShopImpl{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", open=" + open +
                ", product=" + product +
                '}';
    }

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