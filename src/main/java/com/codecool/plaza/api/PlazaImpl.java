package com.codecool.plaza.api;

import java.util.ArrayList;
import java.util.List;

public class PlazaImpl implements Plaza {
    private List<Shop> shops;
    private boolean open;

    public PlazaImpl(){
        this.shops = new ArrayList<Shop>();
    }

    public List<Shop> getShops() throws PlazaIsClosedException {
        return shops;
    }

    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {
        shops.add(shop);
    }

    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        shops.remove(shop);
    }

    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {
        for (Shop shop : shops) {
            if (shop.getName().equals(name)) {
                return shop;
            }
        }
        return null;
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

    public String toString(){
        return "Bakker",
    }
}

