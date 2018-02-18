package main.java.com.codecool.plaza.cmdprog;

public enum ShopMenu {
    OPEN_SHOP("Open shop"),
    CHECK_PRODUCT_AVAILABILITY("Check product availability"),
    ADD_NEW_PRODUCT("Add new product"),
    REFILL_PRODUCT("Refill existing product"),
    BUY_PRODUCT("Buy product"),
    CLOSE_SHOP("Close shop"),
    EXIT_SHOP("Return to plaza");

    final private String value;

    ShopMenu(String s) {
        value = s;
    }

    public String getValue() { return value; }
}
