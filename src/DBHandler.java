import org.sqlite.JDBC;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.Period;

public class DBHandler {

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:bd1.db";

    private static DBHandler instance = null;

    public static synchronized DBHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DBHandler();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private DBHandler() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }
    private String getAge(String docDate){
        if (docDate == null) return null;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            LocalDate oldDate = LocalDate.parse(docDate,dateFormat);


            return Integer.toString(Period.between(oldDate, LocalDate.now()).getYears());

    }

    public List<String> getFLPAge() {

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {

            List<String> FLPAge = new ArrayList<String>();


            ResultSet resultSet = statement.executeQuery("SELECT id, first_name, last_name, patronymic, birthday FROM people");
            try{
                while (resultSet.next()) {
                FLPAge.add(new String(
                        resultSet.getString("first_name")+" "+
                        resultSet.getString("last_name")+" "+
                        resultSet.getString("patronymic")+" "+
                        resultSet.getString("birthday")+" "+
                        getAge(resultSet.getString("birthday"))

                ));
                }
            }
            catch (Exception g){
                g.printStackTrace();
            }
            // Возвращаем наш список
            return FLPAge;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    // Добавление в БД
    public void addPeople(People people) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO people(`first_name`, `last_name`, `patronymic`, `birthday`, `sex`) " +
                        "VALUES(?, ?, ?, ?, ?)")) {
//            statement.setObject(1, people.id);
            statement.setObject(1, people.first_name);
            statement.setObject(2, people.last_name);
            statement.setObject(3, people.patronymic);
            statement.setObject(4, people.birthday);
            statement.setObject(5, people.sex);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addDocument(Documents doc) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO documents (id, type_id , people_id , series, number, date)" +
                        "VALUES(?,?, ?, ?, ?, ?)")) {
            statement.setObject(1, doc.id);

            statement.setObject(2, doc.type_id);
            statement.setObject(3, doc.peole_id);
            statement.setObject(4, doc.series);
            statement.setObject(5, doc.number);
            statement.setObject(6, doc.date);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException d) {
            d.printStackTrace();
        }

    }
    public  String get_people_id() {
        try (Statement statement = this.connection.createStatement()) {
//            statement.setObject(1, people.id);
            // Выполняем запрос
            return (statement.executeQuery("SELECT id FROM people ORDER BY id DESC LIMIT 1;")).getString("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "0";
    }


//    // Удаление продукта по id
//    public void deleteProduct(int id) {
//        try (PreparedStatement statement = this.connection.prepareStatement(
//                "DELETE FROM people WHERE id = ?")) {
//            statement.setObject(1, id);
//            // Выполняем запрос
//            statement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}