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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.lang.*;



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



        JFrame a = new JFrame("example");
        JLabel l = new JLabel("id           first_name                                                              last_name                                                                    patronymic                                                            birthday                 sex");
        l.setBounds(10,50,1000,30);
        a.add(l );

        JTextField id = new JTextField("", 5);
        id.setToolTipText("id");
        id.setBounds(5,100,20,30);
        a.add(id);


        JTextField first_name = new JTextField("Петр", 25);
        first_name.setToolTipText("first name");
        first_name.setBounds(50,100,200,30);
        a.add(first_name );
        JTextField last_name = new JTextField("Петров", 25);
        last_name.setToolTipText("last name");
        last_name.setBounds(300,100,200,30);
        a.add(last_name );
        JTextField patronymic = new JTextField("Петрович", 25);
        patronymic.setToolTipText("patronymic");
        patronymic.setBounds(550,100,200,30);
        a.add(patronymic );

        // Определение маски и поля ввода даты
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        JFormattedTextField birthday = new JFormattedTextField(date);
        birthday.setColumns(10);
        birthday.setBounds(800,100,80,30);


        a.add(birthday );

        final String[] sexdata = { "M" ,"F"};
        JComboBox sex = new JComboBox(sexdata);
        sex.setBounds(900,100,50,30);
        a.add(sex);
        String[] type_documentF = { "Паспорт" ,"Снилс", "ИНН"};
        String[] type_documentM = { "Паспорт" ,"Снилс", "ИНН", "Военный Билет"};
        JComboBox document = new JComboBox(type_documentM);
        document.setBounds(50,300,200,30);
        a.add(document);

        sex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    switch (sex.getSelectedIndex()){
                        case 0: document.addItem("Военный Билет");
                            break;
                        case 1: document.removeItem("Военный Билет");
                            break;
                    }
                }
            catch (Exception v) {
                    v.printStackTrace();
                }

//                if (sex.getName() == "M") {
//                    document.addItem("Военный Билет");
//                }
//                else {
//                     document.removeItem("Военный Билет");
//                }
            }
        });

        JLabel l2 = new JLabel("id           type_document                                                              series                                                                    number                                                                       date");
        l2.setBounds(10,250,1000,30);
        a.add(l2);

        JTextField iddoc = new JTextField("", 5);
        iddoc.setToolTipText("id");
        iddoc.setBounds(5,300,20,30);
        a.add(iddoc);


        JTextField series = new JTextField("", 25);
        series.setToolTipText("series");
        series.setBounds(300,300,200,30);
        a.add(series);

        JTextField number = new JTextField("", 25);
        number.setToolTipText("number");
        number.setBounds(550,300,200,30);
        a.add(number);

        JFormattedTextField date3 = new JFormattedTextField(date);
        date3.setColumns(10);
        date3.setBounds(800,300,80,30);


        a.add(date3 );


        JButton b = new JButton("Добавить");
        b.setBounds(400,700,100,20);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

           //     People man =new People(0, first_name.getText(), last_name.getText(), patronymic.getText(),birthday.getText(),sex.getText());
                try
                {
                    Class.forName("org.sqlite.JDBC");
                    Connection co = DriverManager.getConnection("jdbc:sqlite:bd1.db");
                    System.out.println("ваприварп");
                }
                catch (Exception q){
                System.out.println(q.getMessage());
                }
                try {
                // Создаем экземпляр по работе с БД
                DBHandler dbHandler = DBHandler.getInstance();

                // Добавляем запись
                dbHandler.addPeople(new People(Integer.parseInt(id.getText()), first_name.getText(), last_name.getText(), patronymic.getText(),birthday.getText(),sex.getName()));
                dbHandler.addDocument(new Documents(Integer.parseInt(iddoc.getText()), document.getSelectedIndex()+1, Integer.parseInt(id.getText()), series.getText(), number.getText(), date3.getText()));
//                    dbHandler.addDocument(new Documents(1, 1, 5, series.getText(), number.getText(), date3.getText()));
                // Получаем все записи и выводим их на консоль

                }
                catch (SQLException w) {
                    System.out.println("3");
                    w.printStackTrace();
                    }



                System.out.println(first_name.getText());
            }



        });
        a.add(b);


        a.setSize(1600,800);



        a.setLayout(null);
        a.setVisible(true);


    }


}
