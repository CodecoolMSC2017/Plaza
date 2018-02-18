package main.java.com.codecool.plaza.cmdprog;

public enum MainMenu {
    OPEN_PLAZA("Open plaza"),
    ADD_NEW_SHOP("Add new shop"),
    CLOSE_DOWN_SHOP("Close down shop"),
    CHECK_SHOPS_STATES("Check state of shops"),
    ENTER_SHOP("Enter specific shop"),
    CLOSE_PLAZA("Close plaza"),
    EXIT_PLAZA("Exit");

    final private String value;

    MainMenu(String s) {
        value = s;
    }

    public String getValue() { return value; }
}
