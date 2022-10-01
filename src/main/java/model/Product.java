package model;

public class Product {
    private int id;
    private String nameProduct;
    private int price;
    private int stock;

    public Product(){}

    public Product(int id, String nameProduct, int price, int stock) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.stock = stock;
    }
    public Product( String nameProduct, int price, int stock) {

        this.nameProduct = nameProduct;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
