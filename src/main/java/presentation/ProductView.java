package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import dao.ProductDAO;
import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductView {
    JFrame frame = new JFrame();
    JLabel titleLabel;
    JTextArea clientsArea;
    ProductBLL productBLL = new ProductBLL();

    JLabel idButton;
    JLabel nameButton;
    JLabel priceButton;
    JLabel stockButton;
    JLabel operationButton;
    JButton operation1;

    JButton operation2;
    JButton operation3;
    JButton addClient;
    JButton editClient;
    JButton deleteClient;
    JTable table;

    JTextField textField11;
    JTextField textField12;
    JTextField textField13;
    JTextField textField14;
    JTextField textField15;

    JTextField textField21;
    JTextField textField22;
    JTextField textField23;
    JTextField textField24;
    JTextField textField25;

    JTextField textField31;
    JTextField textField32;
    JTextField textField33;
    JTextField textField34;
    JTextField textField35;

    public ProductView()
    {

    }
    public void show(){
        frame.getContentPane().setBackground(Color.decode("#CEE5F6"));
        frame.setTitle("PRODUCT");
        frame.setBounds(890, 0, 700, 500);
        frame.setLayout(null);
        titleLabel = new JLabel("PRODUCT");
        titleLabel.setBounds(300, 5, 200, 50);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));


        table = new JTable();
        table.setBounds(370, 50, 300, 400);

        idButton = new JLabel("  id");
        idButton.setBounds(5, 50, 40, 100);
        idButton.setFont(new Font("Verdana", Font.BOLD, 13));

        nameButton = new JLabel(" name");
        nameButton.setBounds(50, 50, 50, 100);
        nameButton.setFont(new Font("Verdana", Font.BOLD, 13));

        priceButton = new JLabel("price");
        priceButton.setBounds(120, 50, 70, 100);
        priceButton.setFont(new Font("Verdana", Font.BOLD, 13));

        stockButton = new JLabel("stock");
        stockButton.setBounds(180, 50, 80, 100);
        stockButton.setFont(new Font("Verdana", Font.BOLD, 13));

        operationButton = new JLabel("Operation");
        operationButton.setBounds(240, 50, 80, 100);
        operationButton.setFont(new Font("Verdana", Font.BOLD, 13));

        textField11 = new JTextField();
        textField11.setBounds(5, 120, 40, 40);
        textField12 = new JTextField();
        textField12.setBounds(50, 120, 50, 40);
        textField13 = new JTextField();
        textField13.setBounds(120, 120, 40, 40);
        textField14 = new JTextField();
        textField14.setBounds(180, 120, 40, 40);


        textField21 = new JTextField();
        textField21.setBounds(5, 240, 40, 40);
        textField22 = new JTextField();
        textField22.setBounds(50, 240, 50, 40);
        textField23 = new JTextField();
        textField23.setBounds(120, 240, 40, 40);
        textField24 = new JTextField();
        textField24.setBounds(180, 240, 40, 40);


        textField31 = new JTextField();
        textField31.setBounds(5, 360, 40, 40);
        textField32 = new JTextField();
        textField32.setBounds(50, 360, 50, 40);
        textField33 = new JTextField();
        textField33.setBounds(120, 360, 40, 40);
        textField34 = new JTextField();
        textField34.setBounds(180, 360, 40, 40);


        operation1 = new JButton("add");
        operation1.setBounds(250, 120, 60, 40);

        operation2 = new JButton("delete");
        operation2.setBounds(250, 240, 80, 40);

        operation3 = new JButton("edit");
        operation3.setBounds(250, 360, 60, 40);

/***
 * la apasarea butonului de add se va adauga un nou produs in baza de date
 */
        operation1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product(Integer.parseInt(textField11.getText()), textField12.getText(),
                        Integer.parseInt(textField13.getText()), Integer.parseInt(textField14.getText()));
                //System.out.println("*" + client + "*");
                productBLL.insertProduct(product);
                showAllProducts();
            }
        });
/***
 * la apasarea butonului delete se va sterge produsul cu id ul specificat
 */
        operation2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product(Integer.parseInt(textField21.getText()), "da", 0, 0);
                try {
                    productBLL.deleteProduct(product);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
                showAllProducts();
            }
        });
/***
 * la apasarea butonului edit se vor actualiza datele in cadrul atributelor produsului
 */
        operation3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id, price, stock;
                String nameProduct;
                id = Integer.parseInt(textField31.getText());
                if (textField32.getText().length() != 0)
                    productBLL.editProduct(id, "nameProduct", textField32.getText());
                if (textField33.getText().length() != 0)
                    productBLL.editProduct(id, "price", String.valueOf(Integer.parseInt(textField33.getText())));
                if (textField34.getText().length() != 0)
                    productBLL.editProduct(id, "stock", String.valueOf(textField34.getText()));
                showAllProducts();
            }
        });

        frame.add(textField11);
        frame.add(textField12);
        frame.add(textField13);
        frame.add(textField14);

        frame.add(textField21);
        frame.add(textField22);
        frame.add(textField23);
        frame.add(textField24);

        frame.add(textField31);
        frame.add(textField32);
        frame.add(textField33);
        frame.add(textField34);

        frame.add(nameButton);
        frame.add(priceButton);
        frame.add(stockButton);
        frame.add(operationButton);
        frame.add(idButton);
        frame.add(table);
        frame.add(titleLabel);
        frame.add(operation1);
        frame.add(operation2);
        frame.add(operation3);

        frame.add(textField11);
        frame.add(textField12);
        frame.add(textField13);
        frame.add(textField14);

        frame.add(textField21);
        frame.add(textField22);
        frame.add(textField23);
        frame.add(textField24);

        frame.add(textField31);
        frame.add(textField32);
        frame.add(textField33);
        frame.add(textField34);



       // frame.add(operationButton);
       // frame.add(addressButton);
       // frame.add(phoneButton);
//        frame.add(firstNameButton);
//        frame.add(idButton);
//        frame.add(table);
//        frame.add(titleLabel);
        frame.setVisible(true);
        showAllProducts();
    }

    /***
     * metoda prin care se interogheaza baza de date si se afiseaza toate produsele cu atributele lor
     */
     void showAllProducts() {
        JTable table2;
        List<Product> products = new ArrayList<Product>();
        products = (ArrayList<Product>)productBLL.findAll();
        Collections.sort(products, Comparator.comparing(Product::getId));
        String s = new String();
        for (Product c : products)
            s += +c.getId() + "--" + c.getNameProduct() + "--" + c.getPrice() + "--" + c.getStock() + "\n";
  //      String[] head = {"ID", "name Product", "Price", "Stock"};
            String[] head = productBLL.giveTableHead();
        System.out.println("**" + products.size() + "**");
        Object[][] obj = new Object[products.size()][4];
        for(int i = 0; i < products.size(); i ++){
            obj[i][0] = products.get(i).getId();
            obj[i][1] = products.get(i).getNameProduct();
            obj[i][2] = products.get(i).getPrice();
            obj[i][3] = products.get(i).getStock();
        }

        table2 = new JTable(obj, head);
        JScrollPane scrollPane = new JScrollPane(table2);
        scrollPane.setBounds(370, 50, 300, 400);
        table2.getColumnModel().getColumn(0).setPreferredWidth(3);
       // table2.setBounds
        frame.remove(table);
        frame.add(scrollPane);
        frame.setVisible(true);
    }
}
