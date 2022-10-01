package bll;

import dao.ProductDAO;
import model.Client;
import model.Product;

import java.beans.IntrospectionException;
import java.util.List;

public class ProductBLL {
    private ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /***
     *
     * @param product produsul ce se doreste a fi inserat in baza de date
     */
    public void insertProduct(Product product) {
        productDAO.insert(product);
    }

    /***
     *
     * @param product produsul ce se va sterge din baza de date
     * @throws IllegalAccessException
     */
    public void deleteProduct(Product product) throws IllegalAccessException {
        try {
            productDAO.delete(product);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /***
     *
     * @param id id ul produsului de editat
     * @param field campul unde se doreste modificarea
     * @param newValue = noua valoare a atributului
     */
    public void editProduct(int id, String field, String newValue) {
        productDAO.update(id, field, newValue);
    }

    public List<Product> findAll() {
        return productDAO.selectAll();
    }
    public String[] giveTableHead()
    {
        return productDAO.returnHeadTable();
    }

    /***
     *
     * @param id id ul produsului cautat
     * @return produsul sau null in cazul in care acesta nu exista
     */
    public Product findProductById(int id)
    {
        try {
            return productDAO.selectById(id);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
