package bll;

import dao.OrderDAO;
import model.Orders;

public class OrderBLL {
    private OrderDAO orderDAO;
    public OrderBLL()
    {
        orderDAO = new OrderDAO();
    }

    /***
     *
     * @param order metoda care insereaza noua comanda in baza de date
     */
    public void makeOrder(Orders order)
    {
        orderDAO.insert(order);
    }

}
