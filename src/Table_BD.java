import java.sql.*;
import javax.swing.*;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import java.lang.*;


public class Table_BD {

    public static void main(String[] args) {
        JFrame a = new JFrame("example");
        JLabel l = new JLabel("           first_name                                                              last_name                                                                    patronymic                                                         birthday (01.01.1900)      sex");
        l.setBounds(10, 50, 1000, 30);
        a.add(l);
        JTextField first_name = new JTextField("Петр", 25);
        first_name.setToolTipText("first name");
        first_name.setBounds(50, 100, 200, 30);
        a.add(first_name);
        JTextField last_name = new JTextField("Петров", 25);
        last_name.setToolTipText("last name");
        last_name.setBounds(300, 100, 200, 30);
        a.add(last_name);
        JTextField patronymic = new JTextField("Петрович", 25);
        patronymic.setToolTipText("patronymic");
        patronymic.setBounds(550, 100, 200, 30);
        a.add(patronymic);

        // Определение маски и поля ввода даты
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        JFormattedTextField birthday = new JFormattedTextField(date);

        birthday.setColumns(10);
        birthday.setBounds(800, 100, 80, 30);
        a.add(birthday);

        final String[] sexdata = {"M", "F"};
        JComboBox sex = new JComboBox(sexdata);
        sex.setBounds(940, 100, 50, 30);
        a.add(sex);
//        String[] type_documentF = {"Паспорт", "Снилс", "ИНН"};
        String[] type_documentM = {"Паспорт", "Снилс", "ИНН", "Военный Билет"};
        JComboBox document = new JComboBox(type_documentM);
        document.setBounds(50, 300, 200, 30);
        a.add(document);

        sex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    switch (sex.getSelectedIndex()) {
                        case 0:
                            document.addItem("Военный Билет");
                            break;
                        case 1:
                            document.removeItem("Военный Билет");
                            break;
                    }
                } catch (Exception v) {
                    v.printStackTrace();
                }

            }
        });

        JLabel l2 = new JLabel("id           type_document                                                              series                                                                    number                                                                       date");
        l2.setBounds(10, 250, 1000, 30);
        a.add(l2);

        JTextField iddoc = new JTextField("", 5);
        iddoc.setToolTipText("id");
        iddoc.setBounds(15, 300, 20, 30);
        a.add(iddoc);


        JTextField series = new JTextField("", 25);
        series.setToolTipText("series");
        series.setBounds(300, 300, 200, 30);
        a.add(series);

        JTextField number = new JTextField("", 25);
        number.setToolTipText("number");
        number.setBounds(550, 300, 200, 30);
        a.add(number);

        JFormattedTextField date3 = new JFormattedTextField(date);
        date3.setColumns(10);
        date3.setBounds(800, 300, 80, 30);


        a.add(date3);

//  --------------------------------КНОПКА------------------------------------------------------------------------------------------------------------
        JButton b = new JButton("Добавить");
        b.setBounds(400, 700, 100, 20);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection co = DriverManager.getConnection("jdbc:sqlite:bd1.db");

                } catch (Exception q) {
                    System.out.println(q.getMessage());
                }
                try {
                    // Создаем экземпляр по работе с БД
                    DBHandler dbHandler = DBHandler.getInstance();

                    // Добавляем записи
                    dbHandler.addPeople(new People(first_name.getText(), last_name.getText(), patronymic.getText(), birthday.getText(), sex.getName()));
                    String people_id = dbHandler.get_people_id();
                    dbHandler.addDocument(new Documents(Integer.parseInt(iddoc.getText()), document.getSelectedIndex() + 1, people_id, series.getText(), number.getText(), date3.getText()));

                    JList<String> listPeople = new JList<String>(dbHandler.getFLPAge().toArray(new String[0]));
                    listPeople.setBounds(1000, 100, 300, 500);
//                JScrollPane scrollPane = new JScrollPane(listPeople );
//                    scrollPane.setBounds(1000,100,300,500);
                    a.add(listPeople);

//                a.add(scrollPane);
                    a.repaint();

                } catch (SQLException w) {
                    System.out.println("SQLException w");
                    w.printStackTrace();
                }


            }


        });
        a.add(b);
//  --------------------------------КНОПКА--Конец----------------------------------------------------------------------------------------------------------

        a.setSize(1600, 800);


        a.setLayout(null);
        a.setVisible(true);


    }


}
