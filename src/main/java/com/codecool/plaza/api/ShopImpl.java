package main.java.com.codecool.plaza.api;

import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop {
    private String name;
    private String owner;
    private boolean open;
    private Map<Long, ShopImplEntry> product;

    public ShopImpl(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

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

    public float findPrice(Product prod) throws NoSuchProductException, ShopIsClosedException {
        if (!open) {
            throw new ShopIsClosedException("Sorry, " + this.getName() + " is closed.");
        }
        for (long i=0; i<product.size();i++) {
            if (product.get(i).getProduct().equals(prod)) {
                return product.get(i).getPrice();
            }
        }
        throw new NoSuchProductException("Sorry, this product is not available here");
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
        return "Shop{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", open=" + open +
                ", product=" + product +
                "}\n";
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

        private Product getProduct() {
            return product;
        }

        private void setProduct(Product product) {
            this.product = product;
        }

        private int getQuantity() {
            return quantity;
        }

        private void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        private void increaseQuantity(int amount) {
            quantity = quantity + amount;
        }

        private void decreaseQuantity(int amount) {
            quantity = quantity - amount;
        }

        private float getPrice() {
            return price;
        }

        private void setPrice(int price) {
            this.price = price;
        }

        public String toString() {
            return "Bakker";
        }
    }
}