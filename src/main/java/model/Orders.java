package model;

public class Orders {
  private  int id;
    private int idClient;
    private int idProduct;
    private int quantity;

    /***
     *
     * @param id - id ul comenzii
     * @param idClient - id ul clientului
     * @param idProduct - id ul produsului
     * @param quantity - cantitatea
     */
    public Orders(int id, int idClient, int idProduct, int quantity) {
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }
    public Orders( int idClient, int idProduct, int quantity) {

        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public Orders(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
