package main.java.com.codecool.plaza.api;

public abstract class Product {
    protected long barcode;
    protected String manufacturer;
    protected String name;

    protected Product(long barcode, String manufacturer) {
        this.barcode = barcode;
        this.manufacturer = manufacturer;
    }

    public long getBarcode(){
        return barcode;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public String getName() { return name; }

    public String toString(){
        return "Bakker";
    }
}
