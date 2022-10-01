package presentation;

public class Controller {
    private ClientView clientView;
    private ProductView productView;
    private OrderView orderView;

    /***\
     * clasa prin care se face legatura intre aplicatia propriu zisa si interfata grafica
     */
    public Controller(){
        clientView = new ClientView();
        productView = new ProductView();
        orderView = new OrderView(productView);
        clientView.show();
        productView.show();
        orderView.show();
    }
}
