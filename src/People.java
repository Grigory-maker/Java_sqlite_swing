
public class People {
    // Поля класса
    public int id;

    public String first_name;

    public String  last_name;

    public String  patronymic;

    public String birthday;

    public String  sex;

    // Конструктор
    public People(int id, String first_name, String  last_name, String  patronymic,String birthday,String  sex) {
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
        return String.format("%s  %s   %s |  %s",
                 this.first_name, this.last_name, this.patronymic,this.birthday);
    }
}
