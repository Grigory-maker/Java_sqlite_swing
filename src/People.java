import java.util.Date;

public class People {
    // Поля класса
    public int id;

    public String first_name;

    public String  last_name;

    public String  patronymic;

    public Date birthday;

    public String  sex;

    // Конструктор
    public People(int id, String first_name, String  last_name, String  patronymic,Date birthday,String  sex) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.sex = sex;
    }

    // Выводим информацию
    @Override
    public String toString() {
        return String.format("ID: %s | first_name: %s | last_name: %s | patronymic: %s| birthday: %s| sex: %s",
                this.id, this.first_name, this.last_name, this.patronymic,this.birthday,this.sex);
    }
}
