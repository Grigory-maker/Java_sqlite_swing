import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.sql.SQLException;
import java.util.List;

public class Table_BD {

    public static void main(String[] args){
//        try
//        {
//            Class.forName("org.sqlite.JDBC");
//            Connection co = DriverManager.getConnection("jdbc:sqlite:bd1.db");
//            System.out.println("ваприварп");
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//       }

        try {
            // Создаем экземпляр по работе с БД
            DBHandler dbHandler = DBHandler.getInstance();
            System.out.println("1");
            // Добавляем запись
            //dbHandler.addProduct(new Product("Музей", 200, "Развлечения"));
            // Получаем все записи и выводим их на консоль
            List<People> peoples = dbHandler.getAllPeople();
            System.out.println("2");
            for (People people : peoples) {
                System.out.println(people.toString());
            }
            // Удаление записи с id = 8
            //dbHandler.deleteProduct(8);
        } catch (SQLException e) {
            System.out.println("3");
            e.printStackTrace();
        }




        JFrame a = new JFrame("example");
        JButton b = new JButton("Добавить");
        b.setBounds(40,90,85,20);
        a.add(b);
        a.setSize(300,300);
        a.setLayout(null);
        a.setVisible(true);
    }

}
