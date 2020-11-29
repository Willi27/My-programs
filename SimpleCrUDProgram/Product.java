package javaRash.Lev5.CrUDProgram;

class Product {
    private String id;
    private String productName;
    private String price;
    private String quantity;

    public Product(String id, String productName, String price, String quantity) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-30s%-8s%-4s", id, productName, price, quantity);
    }
}
