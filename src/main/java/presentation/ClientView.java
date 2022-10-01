package presentation;

import bll.ClientBLL;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClientView {
    JFrame frame = new JFrame();
    JLabel titleLabel;
    JTextArea clientsArea;
    ClientBLL clientBLL = new ClientBLL();
    JLabel idButton;
    JLabel firstNameButton;
    JLabel lastNameButton;
    JLabel addressButton;
    JLabel phoneButton;
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


    public ClientView() {

    }

    public void show() {
        frame.getContentPane().setBackground(Color.decode("#CEE5F6"));
        frame.setTitle("CLIENT");
        frame.setBounds(0, 0, 900, 500);
        frame.setLayout(null);

        titleLabel = new JLabel("CLIENT");
        titleLabel.setBounds(350, 10, 500, 50);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));

        table = new JTable();
        table.setBounds(500, 20, 370, 400);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        idButton = new JLabel("  id");
        idButton.setBounds(10, 60, 40, 100);
        idButton.setFont(new Font("Verdana", Font.BOLD, 13));

        firstNameButton = new JLabel("First Name");
        firstNameButton.setBounds(55, 60, 80, 100);
        firstNameButton.setFont(new Font("Verdana", Font.BOLD, 13));

        lastNameButton = new JLabel("Last Name");
        lastNameButton.setBounds(150, 60, 80, 100);
        lastNameButton.setFont(new Font("Verdana", Font.BOLD, 13));

        addressButton = new JLabel("Address");
        addressButton.setBounds(255, 60, 80, 100);
        addressButton.setFont(new Font("Verdana", Font.BOLD, 13));

        phoneButton = new JLabel("Phone");
        phoneButton.setBounds(340, 60, 80, 100);
        phoneButton.setFont(new Font("Verdana", Font.BOLD, 13));

        operationButton = new JLabel("Operation");
        operationButton.setBounds(425, 60, 80, 100);
        operationButton.setFont(new Font("Verdana", Font.BOLD, 13));

        textField11 = new JTextField();
        textField12 = new JTextField();
        textField13 = new JTextField();
        textField14 = new JTextField();
        textField15 = new JTextField();

        textField21 = new JTextField();
        textField22 = new JTextField();
        textField23 = new JTextField();
        textField24 = new JTextField();
        textField25 = new JTextField();

        textField31 = new JTextField();
        textField32 = new JTextField();
        textField33 = new JTextField();
        textField34 = new JTextField();
        textField35 = new JTextField();
        //mergem pe diagonala
        textField11.setBounds(5, 160, 40, 40);
        textField21.setBounds(5, 260, 40, 40);
        textField31.setBounds(5, 360, 40, 40);

        textField12.setBounds(50, 160, 80, 40);
        textField22.setBounds(50, 260, 80, 40);
        textField32.setBounds(50, 360, 80, 40);

        textField13.setBounds(145, 160, 80, 40);
        textField23.setBounds(145, 260, 80, 40);
        textField33.setBounds(145, 360, 80, 40);

        textField14.setBounds(240, 160, 80, 40);
        textField24.setBounds(240, 260, 80, 40);
        textField34.setBounds(240, 360, 80, 40);

        textField15.setBounds(335, 160, 80, 40);
        textField25.setBounds(335, 260, 80, 40);
        textField35.setBounds(335, 360, 80, 40);

        operation1 = new JButton("add");
        operation1.setBounds(425, 160, 60, 40);

        operation2 = new JButton("delete");
        operation2.setBounds(415, 260, 80, 40);

        operation3 = new JButton("edit");
        operation3.setBounds(425, 360, 60, 40);
/***
 * la apasarea butonului de add se va adauga un nou client in baza de date si se va afisa noul tabel actualizat
 */
        operation1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client(Integer.parseInt(textField11.getText()), textField12.getText(), textField13.getText(),
                        textField14.getText(), textField15.getText());
                System.out.println("*" + client + "*");
                clientBLL.insertClient(client);
                showAllClients();
            }
        });
/***
 * la apasarea butonului de delete se va sterge Clientul
 */
        operation2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client(Integer.parseInt(textField21.getText()), "da", "da", "da", "da");
                try {
                    clientBLL.deleteClient(client);
                } catch (NoSuchFieldException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
                showAllClients();
            }
        });

        /***
         * la apasarea butonului de edit se vor actualiza datele clientului
         */
        operation3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                String firstName, lastName, addres, phoneNumber;
                id = Integer.parseInt(textField31.getText());
                if (textField32.getText().length() != 0)
                    clientBLL.editClient(id, "firstName", textField32.getText());
                if (textField33.getText().length() != 0)
                    clientBLL.editClient(id, "lastName", textField33.getText());
                if (textField34.getText().length() != 0)
                    clientBLL.editClient(id, "address", textField34.getText());
                if (textField35.getText().length() != 0)
                    clientBLL.editClient(id, "phoneNumber", textField35.getText());
                showAllClients();
            }
        });


        frame.add(operation1);
        frame.add(operation2);
        frame.add(operation3);
        frame.add(textField11);
        frame.add(textField15);
        frame.add(textField25);
        frame.add(textField35);
        frame.add(textField14);
        frame.add(textField24);
        frame.add(textField34);
        frame.add(textField13);
        frame.add(textField23);
        frame.add(textField33);
        frame.add(textField12);
        frame.add(textField22);
        frame.add(textField32);
        frame.add(textField21);
        frame.add(textField31);
        frame.add(lastNameButton);
        frame.add(operationButton);
        frame.add(addressButton);
        frame.add(phoneButton);
        frame.add(firstNameButton);
        frame.add(idButton);
        frame.add(table);
        frame.add(titleLabel);
        frame.setVisible(true);
        showAllClients();
    }

    /***
     * metoda prin care se afiseaza toti clientii
     */
    private void showAllClients() {
        JTable table2;
        List<Client> clients = new ArrayList<>();
        clients = clientBLL.findAll();
        Collections.sort(clients, Comparator.comparing(Client::getId));
        String s = new String();
        for (Client c : clients)
            s += +c.getId() + "." + c.getFirstName() + "-" + c.getLastName() + "," + c.getAddress() + ","
                    + c.getPhoneNumber() + "\n";
        //String[] head = {"ID", "First Name", "Last Name", "Addres", "Phone Number"};
        String[] head = clientBLL.giveTableHead();
        Object[][] obj = new Object[clients.size()][5];
        for(int i = 0; i < clients.size(); i ++){
            obj[i][0] = clients.get(i).getId();
            obj[i][1] = clients.get(i).getFirstName();
            obj[i][2] = clients.get(i).getLastName();
            obj[i][3] = clients.get(i).getPhoneNumber();
            obj[i][4] = clients.get(i).getAddress();
        }


       table2 = new JTable(obj, head);
        table2.setBounds(500, 20, 500, 500);
        frame.remove(table);
        JScrollPane scrollPane = new JScrollPane(table2);
        table2.getColumnModel().getColumn(0).setPreferredWidth(3);
        table2.getColumnModel().getColumn(1).setPreferredWidth(40);
        table2.getColumnModel().getColumn(2).setPreferredWidth(40);

        scrollPane.setBounds(500, 20, 380, 500);
       frame.add(scrollPane);
       frame.setVisible(true);
    }
}
