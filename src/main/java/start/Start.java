package start;

import java.beans.IntrospectionException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//import bll.StudentBLL;
import bll.ClientBLL;
import bll.ProductBLL;
import dao.ClientDAO;
import dao.OrderDAO;
import model.Client;
import model.Orders;
import  model.Product;
import presentation.Controller;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException, IntrospectionException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {

		/*Client gavrea = new Client(1, "Gavrea", "Ioan", "+40765432223", "CLUJ-NAPOCA");
		ClientDAO clientDAO = new ClientDAO();
		Orders order = new Orders(1, 1, 1, 1);
		OrderDAO orderDAO = new OrderDAO();
		ArrayList<String> values = new ArrayList<>();
		values.add("1");
		values.add("Florean");
		values.add("Eusebiu");
		values.add("0762570580");
		values.add("BM");*/

/*		Client c= new Client(10,"Chis", "Vasile", "+40756337822", "ARAD");
		try {
			clientDAO.delete(c);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}*/
		//ClientBLL clientBLL = new ClientBLL();

//		System.out.println(clientDAO.createInsertQuery(gavrea));
		//clientDAO.insert(c);
		//System.out.println(clientDAO.createUpdateQuery("id", "5", values));
       // clientDAO.delete("id",  "1");
		//System.out.println(clientDAO.createDeleteQuery("id"));
/*		System.out.println(clientDAO.createSelectQuery(null));  	*/
	/*	List<Client> clientsList= clientDAO.selectAll();
        for(Client cc : clientsList) {
			System.out.println(cc);
		}*/

		//Client ccc = clientBLL.findClientById(8);
		//System.out.println(ccc);
		//System.out.println(orderDAO.createUpdateQuery(1, "name"));
	/*	Client c= new Client(18,"Daniela", "Rosca", "n are", "Boscheti");*/
		/***
		 * prin instantierea obiectului al clasei controller va avea loc afisarea celor 3 frame uri, ceea ce permite folosirea operatiilor
		 * implementate in cadrul aplicatiei
		 */
		Controller controller = new Controller();



	}
	
	

}
