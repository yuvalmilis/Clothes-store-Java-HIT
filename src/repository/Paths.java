package repository;

public enum Paths {

    CUSTOMERS_FILE_PATH("data/customers.json"),
    EMPLOYEES_FILE_PATH("data/employees.json"),
    PRODUCTS_FILE_PATH("data/product.json");

    private final String value;

    Paths(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
