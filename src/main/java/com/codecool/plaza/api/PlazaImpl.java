package main.java.com.codecool.plaza.api;

import java.util.ArrayList;
import java.util.List;

public class PlazaImpl implements Plaza {
    private String name;
    private List<Shop> shops;
    private boolean open;

    public PlazaImpl(String name) {
        this.name = name;
        this.shops = new ArrayList<Shop>();
    }

    public List<Shop> getShops() throws PlazaIsClosedException {
        if (!open) {
            throw new PlazaIsClosedException("Sorry, " + this.getName() + " is closed.");
        }
        return shops;
    }

    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {
        if (!open) {
            throw new PlazaIsClosedException("Sorry, " + this.getName() + " is closed.");
        } else if (shops.contains(shop)) {
            throw new ShopAlreadyExistsException("This shop had been added already.");
        }
        shops.add(shop);
    }

    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        if (!open) {
            throw new PlazaIsClosedException("Sorry, " + this.getName() + " is closed.");
        }else if (!shops.contains(shop)) {
            throw new NoSuchShopException("Shop doesn't exist!");
        }
        shops.remove(shop);
    }

    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {
        if (!open) {
            throw new PlazaIsClosedException("Sorry, " + this.getName() + " is closed.");
        } else {
            for (Shop shop : shops) {
                if (shop.getName().equals(name)) {
                    return shop;
                }
            }
            throw new NoSuchShopException("Sorry, there is no shop available with this name");
        }
    }

    public boolean isOpen() {
        return open;
    }

    public void open() {
        open = true;
    }

    public void close() {
        open = false;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PlazaImpl{" +
                "name='" + name + '\'' +
                ", shops=" + shops +
                ", open=" + open +
                '}';
    }
}