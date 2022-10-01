package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Orders;
import model.Product;
import model.Writter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;

public class OrderView {
    Writter writter;
    JFrame frame = new JFrame();
    JLabel titleLabel;
    JLabel id;
    JLabel idClient;
    JLabel idProduct;
    JLabel quantity;
    JLabel operation;
    JTextField[] textFields = new JTextField[4];
    JButton makeOrder;
    ProductBLL productBLL = new ProductBLL();
    ClientBLL clientBLL = new ClientBLL();
    ProductView productView;
   OrderBLL orderBll = new OrderBLL();

    public OrderView(ProductView productView)
    {
     this.productView = productView;
    }

    /***\
     * metoda prin care se creaza frame ul specific comenzilor
     */
    public void show(){
        frame.getContentPane().setBackground(Color.decode("#CEE5F6"));
        frame.setTitle("ORDER");
        frame.setBounds(400, 500, 900, 300);
        frame.setLayout(null);
        titleLabel = new JLabel("ORDER");
        titleLabel.setBounds(350, 10, 500, 50);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));

        id = new JLabel("  id");
        id.setBounds(50, 60, 100, 100);
        id.setFont(new Font("Verdana", Font.BOLD, 16));
        textFields[0] = new JTextField();
        textFields[0].setBounds(50, 160, 40, 40);
        textFields[0].setFont(new Font("Verdana", Font.BOLD, 16));

        idClient = new JLabel("  id Client");
        idClient.setBounds(200, 60, 100, 100);
        idClient.setFont(new Font("Verdana", Font.BOLD, 16));
        textFields[1] = new JTextField();
        textFields[1].setBounds(200, 160, 100, 40);
        textFields[1].setFont(new Font("Verdana", Font.BOLD, 16));


        idProduct = new JLabel("id Product");
        idProduct.setBounds(350, 60, 100, 100);
        idProduct.setFont(new Font("Verdana", Font.BOLD, 16));
        textFields[2] = new JTextField();
        textFields[2].setBounds(350, 160, 100, 40);
        textFields[2].setFont(new Font("Verdana", Font.BOLD, 16));


        quantity = new JLabel("  quantity");
        quantity.setBounds(500, 60, 100, 100);
        quantity.setFont(new Font("Verdana", Font.BOLD, 16));
        textFields[3] = new JTextField();
        textFields[3].setBounds(500, 160, 100, 40);
        textFields[3].setFont(new Font("Verdana", Font.BOLD, 16));

        operation = new JLabel("operation");
        operation.setBounds(650, 60, 100, 100);
        operation.setFont(new Font("Verdana", Font.BOLD, 16));
        makeOrder = new JButton("MAKE ORDER");
        makeOrder.setBounds(620, 160, 130, 40);
        makeOrder.setFont(new Font("Verdana", Font.BOLD, 12));
/***
 * la apasarea acestui buton se va crea comanda, va fi decrementata cantitatea si se va crea nota de plata
 */
        makeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = productBLL.findProductById(Integer.parseInt(textFields[2].getText()));
                int val = product.getStock() - Integer.parseInt(textFields[3].getText());
                Writter.createBill(product.getNameProduct(), Integer.parseInt(textFields[3].getText()), product.getPrice() * Integer.parseInt(textFields[0].getText()));
                if(val < 0)
                    JOptionPane.showMessageDialog(null, "Enter a valid quantity!",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                else {
                    if(val >0)
                    productBLL.editProduct(Integer.parseInt(textFields[2].getText()), "stock", String.valueOf(val));
                    else {
                        try {
                            productBLL.deleteProduct(product);
                        } catch (IllegalAccessException ex) {
                            ex.printStackTrace();
                        }
                    }
                    orderBll.makeOrder(new Orders(Integer.parseInt(textFields[0].getText()), Integer.parseInt(textFields[1].getText()),
                            Integer.parseInt(textFields[2].getText()), Integer.parseInt(textFields[3].getText())));

                }

                productView.showAllProducts();
            }
        });



        frame.add(makeOrder);
       for(int i=0; i<4; i++){
           frame.add(textFields[i]);
       }
        frame.add(id);
        frame.add(idClient);
        frame.add(idProduct);
        frame.add(quantity);
        frame.add(operation);
        frame.add(titleLabel);
        frame.setVisible(true);
    }
}
