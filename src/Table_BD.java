import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;


public class Table_BD {
    public String firstName;
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
//        String[][] dataPeople;
//        try {
//            // Создаем экземпляр по работе с БД
//            DBHandler dbHandler = DBHandler.getInstance();
//
//            // Добавляем запись
//            //dbHandler.addProduct(new Product("Музей", 200, "Развлечения"));
//            // Получаем все записи и выводим их на консоль
//            List<People> peoples = dbHandler.getAllPeople();

//            for (People people : peoples) {
//                System.out.println(people.toString());
//                dataPeople.
//            }
//         } catch (SQLException e) {
//            System.out.println("3");
//            e.printStackTrace();
//        }

// Таблица
//        String[] columnPeople = {
//                "id", "first_name", "last_name", "patronymic","birthday","sex"
//        };

    //    JTable table = new JTable(peoples, columnPeople);

        JFrame a = new JFrame("example");


        JTextField first_name = new JTextField("Текст поля", 25);
        first_name.setToolTipText("first name");
        first_name.setBounds(50,100,200,30);
        a.add(first_name );
        JTextField last_name = new JTextField("Текст поля", 25);
        last_name.setToolTipText("last name");
        last_name.setBounds(300,100,200,30);
        a.add(last_name );
        JTextField patronymic = new JTextField("Текст поля", 25);
        patronymic.setToolTipText("patronymic");
        patronymic.setBounds(550,100,200,30);
        a.add(patronymic );


        JButton b = new JButton("Добавить");
        b.setBounds(400,700,85,20);
//        ActionListener actionListener = new TestActionListener();
//          b.addActionListener(new TestActionListener());
//          b.addActionListener(new ButtonAction());
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(first_name.getText());
            }
        });
        a.add(b);


        a.setSize(800,800);
//        DefaultListModel<String> l = new DefaultListModel< >();
//        try {
//            // Создаем экземпляр по работе с БД
//            DBHandler dbHandler = DBHandler.getInstance();
//
//            // Добавляем запись
//            //dbHandler.addProduct(new Product("Музей", 200, "Развлечения"));
//            // Получаем все записи и выводим их на консоль
//            List<People> peoples = dbHandler.getAllPeople();
//
//            for (People people : peoples) {
//                l.addElement(people.toString());
//            }
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }
//
//        JList<String> c = new JList< >(l);
//        c.setBounds(100,100,500,75);
//        a.add(c);


        a.setLayout(null);
        a.setVisible(true);

        System.out.println(first_name.getText());

    }


}
