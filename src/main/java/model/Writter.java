package model;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Writter {

    /***
     *
     * @param name numele produsului ce se doreste a fi comandat
     * @param quantity = cantitatea dorita
     * @param price = pretul per produs
     */
    public static void createBill(String name, int quantity, int price) {
        File f;
        FileWriter fstream;
        BufferedWriter buf;
        Random r = new Random();
        f = new File("bill_" + name+ "_" + r.nextInt() + ".txt");
        try {
            fstream = new FileWriter(f, true);
            buf = new BufferedWriter(fstream);
            buf.write("____________________________________________________________________________________________");
            buf.newLine();
            buf.write("                            BILL: " );
            buf.newLine();
            buf.newLine();
            buf.write("Product: " + name);
            buf.newLine();
            buf.write("Quantity: " + quantity);
            buf.newLine();
            buf.write("Price: " + price + " lei;");
            buf.newLine();
            buf.write("____________________________________________________________________________________________");
            buf.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}