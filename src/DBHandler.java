import org.sqlite.JDBC;
import java.sql.*;
import java.util.*;


public class DBHandler {

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:bd1.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
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

    public List<People> getAllPeople() {

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {

            // В данный список будем загружать наши продукты, полученные из БД
            List<People> peoples = new ArrayList<People>();

            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
//            ResultSet resultSet2 = statement.executeQuery("SELECT id, first_name, last_name, patronymic, birthday, sex FROM people");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM people;");

            // Проходимся по нашему resultSet и заносим данные people
            while (resultSet.next()) {
                peoples.add(new People(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("patronymic"),
                        resultSet.getString("birthday"),
                        resultSet.getString("sex"))
                        );
            }
            // Возвращаем наш список
            return peoples;

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
                "INSERT INTO people(`id`,`first_name`, `last_name`, `patronymic`, `birthday`, `sex`) " +
                        "VALUES(?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, people.id);
            statement.setObject(2, people.first_name);
            statement.setObject(3, people.last_name);
            statement.setObject(4, people.patronymic);
            statement.setObject(5, people.birthday);
            statement.setObject(6, people.sex);
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



//    // Удаление продукта по id
//    public void deleteProduct(int id) {
//        try (PreparedStatement statement = this.connection.prepareStatement(
//                "DELETE FROM Products WHERE id = ?")) {
//            statement.setObject(1, id);
//            // Выполняем запрос
//            statement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}