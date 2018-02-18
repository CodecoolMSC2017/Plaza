package main.java.com.codecool.plaza.cmdprog;

import main.java.com.codecool.plaza.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CmdProgram {
    private List<Product> cart = new ArrayList<Product>();
    private List<Float> prices = new ArrayList<Float>();
    private Plaza plaza = null;
    private Scanner inp = new Scanner(System.in);

    public CmdProgram(String[] args) {}

    public void run() {
        System.out.println("Welcome to Plaza Simulator 2010!\nPlease provide the name of your Plaza:\n");
        String name = inp.nextLine();
        plaza = new PlazaImpl(name);

        while(true) {
            System.out.println("You are now here:\n" + plaza.toString());
            System.out.println("You can choose following actions:");
            int i = 1;

            for (MainMenu menu : MainMenu.values()) {
                System.out.println(Integer.toString(i) + " " + menu.getValue());
                i++;
            }

            int option = inp.nextInt();
            String empty = inp.nextLine();

            if (option == 1) {
                plaza.open();
            }else if (option == 2) {
                try {
                    addNewShop();
                }catch (ShopAlreadyExistsException|PlazaIsClosedException e) {
                    e.printStackTrace();
                }
            }
            else if (option == 3) {
                try {
                    removeShop();
                }catch (NoSuchShopException|PlazaIsClosedException e){
                    e.printStackTrace();
                }
            }else if (option == 4) {
                try {
                    for (Shop shop : plaza.getShops()) {
                        System.out.println(shop.getName() + " is " + (shop.isOpen() ? "open" : "closed"));
                    }
                }catch (PlazaIsClosedException e) {
                    e.printStackTrace();
                }
            }else if (option == 5) {
                shopMenu();
            }else if (option == 6) {
                plaza.close();
            }else if (option == 7) { break; }

        }
    }

    private void addNewShop() throws ShopAlreadyExistsException, PlazaIsClosedException {
        System.out.println("Please provide the name of the shop:");
        String name = inp.nextLine();
        System.out.println("Please provide the owner of the shop:");
        String owner = inp.nextLine();
        plaza.addShop(new ShopImpl(name, owner));
    }

    private void removeShop() throws NoSuchShopException, PlazaIsClosedException {
        System.out.println("Please provide the name of the shop you want to remove:");
        String input = inp.nextLine();
        plaza.removeShop(plaza.findShopByName(input));
    }

    private void shopMenu() {
        System.out.println("Please type in the name of the shop you wish to visit:");
        try {
            Shop shop = plaza.findShopByName(inp.nextLine());

            while(true) {
                System.out.println("You are now here:\n" + shop.toString());
                System.out.println("You can choose following actions:");
                int i = 1;

                for (ShopMenu menu : ShopMenu.values()) {
                    System.out.println(Integer.toString(i) + " " + menu.getValue());
                    i++;
                }

                int option = inp.nextInt();
                String empty = inp.nextLine();

                if (option == 1) {
                    shop.open();
                }else if (option == 3) {
                    addNewProduct(shop);
                }else if (option == 4) {
                    refillProduct(shop);
                }else if (option == 5) {
                    buyStuff(shop);
                }else if (option == 6) {
                    shop.close();
                }else if (option == 7) {
                    break;
                }
            }
        }catch (NoSuchShopException|PlazaIsClosedException e) {
            e.printStackTrace();
        }

    }

    private void addNewProduct(Shop shop) {
        System.out.println("Please provide the type of product you want to add:");
        System.out.println("1 Clothing\n2 Food");
        int choice = inp.nextInt();
        String empty = inp.nextLine();
        System.out.println("What is the name of the product?");
        String name = inp.nextLine();
        System.out.println("What is the name of the manufacturer?");
        String manufacturer = inp.nextLine();
        System.out.println("What should be its price?");
        float price = inp.nextFloat();
        empty = inp.nextLine();
        System.out.println("How many you would like to add?");
        int amount = inp.nextInt();
        empty = inp.nextLine();

        if (choice == 1) {
            System.out.println("What type of clothing is it?");
            String type = inp.nextLine();
            System.out.println("What is it made of");
            String material = inp.nextLine();
            try {
                shop.addNewProduct(new ClothingProduct(BarCodeGen.genBarCode(), manufacturer, material, type), amount, price);
            }catch (ProductAlreadyExistsException|ShopIsClosedException e) {
                e.printStackTrace();
            }
        }else if (choice == 2) {
            System.out.println("How many calories does it have?");
            int calories = inp.nextInt();
            empty = inp.nextLine();
            System.out.println("When will it expire (yyyy.mm.dd)?");
            String expiration = inp.nextLine();
            try {
                shop.addNewProduct(new FoodProduct(BarCodeGen.genBarCode(), manufacturer, calories, expiration), amount, price);
            }catch (ProductAlreadyExistsException|ShopIsClosedException e) {
                e.printStackTrace();
            }
        }
    }

    private void refillProduct(Shop shop) {
        System.out.println("Please provide name of the product you wish to refill:");
        String name = inp.nextLine();
        System.out.println("How many you would like to add?");
        int amount = inp.nextInt();
        String empty = inp.nextLine();
        try {
            long barcode = (shop.findByName(name)).getBarcode();
            shop.addProduct(barcode, amount);
        }catch (NoSuchProductException|ShopIsClosedException e) {
            e.printStackTrace();
        }
    }

    private void buyStuff(Shop shop) {
        System.out.println("What would you like to buy?");
        String name = inp.nextLine();
        System.out.println("How many you need?");
        int amount = inp.nextInt();
        String empty = inp.nextLine();
        try {
            Product product = shop.findByName(name);
            if (amount == 1) {
                try {
                    Product bought = shop.buyProduct(product.getBarcode());
                    cart.add(bought);
                    prices.add(shop.findPrice(bought));
                } catch (NoSuchProductException | OutOfStockException | ShopIsClosedException e) {
                    e.printStackTrace();
                }
            } else if (amount > 1) {
                try {
                    List<Product> bought = shop.buyProducts(product.getBarcode(), amount);
                    for (Product item : bought) {
                        cart.add(item);
                        prices.add(shop.findPrice(item));
                    }
                } catch (NoSuchProductException | OutOfStockException | ShopIsClosedException e) {
                    e.printStackTrace();
                }
            }
        } catch (NoSuchProductException | ShopIsClosedException e) {
            e.printStackTrace();
        }
    }
}
