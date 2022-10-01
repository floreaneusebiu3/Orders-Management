package bll;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.NoSuchElementException;

import dao.ClientDAO;

import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 * Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientBLL {

    private ClientDAO clientDAO;

    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /***
     *
     * @param c clientul care se insereaza in baza de date
     */
    public void insertClient(Client c) {
        clientDAO.insert(c);
    }

    /***
     *
     * @param id id ul clientului de editat
     * @param field campul care se doreste a fi modificat
     * @param newValue valoarea atributului care va fi actualizat
     */
    public void editClient(int id, String field, String newValue) {
        clientDAO.update(id, field, newValue);
    }

    /***
     *
     * @param client clientul care va fi sters din baza de date
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void deleteClient(Client client) throws NoSuchFieldException, IllegalAccessException {
        clientDAO.delete(client);
    }

    /***
     *
     * @param id id ul clientului cautat
     * @return returneaza un obiect ce reprezinta clientul si apartine clasei Client
     * @throws IntrospectionException
     * @throws NoSuchMethodException
     */
    public Client findClientById(int id) throws IntrospectionException, NoSuchMethodException {
        Client client = clientDAO.selectById(id);
        if (client == null)
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        return client;
    }

    /***
     *
     * @return returneaza o lista cu toti clientii
     */
    public List<Client> findAll() {
        return clientDAO.selectAll();
    }

    /***
     *
     * @return returneaza capul de tabel specific campurilor din cadrul tabelului client din baza de date
     */
    public String[] giveTableHead()
    {
        return clientDAO.returnHeadTable();
    }

}
