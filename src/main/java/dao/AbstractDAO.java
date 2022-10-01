package dao;

import connection.ConnectionFactory;
import model.Client;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

import static javax.xml.bind.JAXBContext.newInstance;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /***
     * \
     * @param column = parametru prin care se specifica valoarea atributului
     * @return = returneaza string care reprezinta query-ul de select
     */
    public String createSelectQuery(String column) {
        StringBuilder s = new StringBuilder();
        s.append("SELECT * FROM ");
        s.append(type.getSimpleName());
        if (column != null) //daca e null => se afiseaza toate datele
        {
            s.append(" WHERE ");
            s.append(column);
            s.append("=?;");
        }
        else
            s.append(";");
        return s.toString();
    }

    /***
     *
     * @param column = parametru prin care se specifica valoarea atributului
     * @return = returneaza string care reprezinta query-ul
     */
    public String createDeleteQuery(String column) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        if (column != null) {
            sb.append(" WHERE " + column);
            sb.append("=?;");
        }
        return sb.toString();
    }

    /***
     *
     * @param object - reprezinta obiectul de inserat
     * @return = returneaza string care reprezinta query-ul de insert
     */
    public String createInsertQuery(T object) {
        StringBuilder s = new StringBuilder();
        s.append("INSERT INTO ");
        s.append(type.getSimpleName());
        s.append("( ");
        Field[] fields = type.getDeclaredFields();
        for (int i = 1; i < fields.length - 1; i++) {
            fields[i].setAccessible(true);
            s.append(fields[i].getName()).append(", ");
        }
        fields[fields.length - 1].setAccessible(true);
        s.append(fields[fields.length - 1].getName());
        s.append(" ) \nVALUES\n( ");

        Field[] objectField = type.getDeclaredFields();
        for (int i = 1; i < objectField.length; i++) {
            objectField[i].setAccessible(true);
            try {
                Object obj = objectField[i].get(object);
                if (i != objectField.length - 1) {
                    if (objectField[i].getType().getSimpleName().compareTo("String") == 0)
                        s.append("\"").append(obj).append("\", ");
                    else
                        s.append(obj).append(", ");
                } else if (objectField[i].getType().getSimpleName().compareTo("String") == 0)
                    s.append("\"").append(obj).append("\" );");
                else
                    s.append(obj).append(" );");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return s.toString();
    }

    /***
     *
     * @param id - idul obiectului la care se face update
     * @param column -noul atribut care va fi actualizat in cadrul obiectului
     * @return = returneaza stringul care reprezinta query-ul de update
     */
    public String createUpdateQuery(int id, String column) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append("\nSET ").append(column);
        sb.append(" =? WHERE ID = ").append(id).append(";");
        return sb.toString();
    }

    /***
     *
     * @param element = elementul ce se doreste a fi inserat
     */
    public void insert(T element) {
        int id;
        String query = createInsertQuery(element);



        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     *
     * @param id = id-ul obiectului
     * @param column = numele atributului
     * @param value = noua valoare a atributului
     */
    public void update(int id, String column, String value) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(id, column);

        connection = ConnectionFactory.getConnection();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, value);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /***
     *
     * @param element = elementul ce se doreste a fi sters
     * @throws NoSuchFieldException = exceptie campuri insuficiente
     * @throws IllegalAccessException = exceptie acces nepermis
     */
    public void delete(T element) throws NoSuchFieldException, IllegalAccessException {
        String column= "id";
        Field field= element.getClass().getDeclaredField("id");
        field.setAccessible(true);
        int intValue = (int) field.get(element);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery(column);
        System.out.println(query);

        connection = ConnectionFactory.getConnection();
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, intValue);
           statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     *
     * @return returneaza lista cu toate obiecte din baza de date
     */
    public List<T> selectAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(null);
        System.out.println(query);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
           resultSet =  statement.executeQuery();
           return createObjects(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     *
     * @param id = id-ul obiectului cautat
     * @return = returneaza obiectul cautat, null- in cazul in care elementul nu se afla in baza de date
     * @throws IntrospectionException
     * @throws NoSuchMethodException
     */
    public T selectById(int id) throws IntrospectionException, NoSuchMethodException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");


        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id );
            resultSet =  statement.executeQuery();
           return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param resultSet = rezultatul obtinut in urma executiei query-ului
     * @return returneaza lista de obiecte
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /***
     *
     * @return returneaza capul de tabel in mod dinamic in functie de atributele din baza de date
     */
    public String[] returnHeadTable()
    {   int i=-1;
        String[] res= new String[type.getDeclaredFields().length];
        for(Field field : type.getDeclaredFields())
            res[++i] = field.getName();
        return res;
    }

}
