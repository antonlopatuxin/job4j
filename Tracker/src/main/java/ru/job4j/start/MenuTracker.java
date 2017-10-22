package ru.job4j.start;

import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.*;

import java.io.IOException;

public class MenuTracker {
    /**
     * В этом классе мы реализуем меню с помощью массива.
     * Создаем массив в который инициализируем ссылки на объекты внутренних классов.
     * С помощью метода select мы запускаем необходимое нам действие
     **/

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7]; // массив для реализации меню
    private String exit = null; //переменная для реализации выхода из программы

    public MenuTracker(Input input, Tracker tracker){

        this.input = input;
        this.tracker = tracker;
    }

    public String getExit() {
        return this.exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public void fillActions(){ // инициализируем классы действий, используем вместо оператора switch

        this.actions[0] = new AddItem();
        this.actions[1] = new FindAll();
        this.actions[2] = new FindByName();
        this.actions[3] = new FindById();
        this.actions[4] = new UpdateItem();
        this.actions[5] = new DeleteItem();
        this.actions[6] = new Exit();
    }

    public void select(int key)throws IOException{ // метод выбора пункта меню из массива
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show(){ // вывод меню на экран

        for(UserAction action : this.actions){

            if(action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem implements UserAction{ //внутренний класс добавления заявок

        public int key(){
            return 0;
        }

        public void execute(Input input, Tracker tracker)throws IOException{ // реализация добавления заявок

            /*
            * тут все просто, создаем 3 переменных которым присваеваем значения путем ввода с консоли, для этого используем интерфейс ввода-вывода.
            * далее вызываем метод add класса Tracker и передаем туда эти переменные. ID генереруется на этапе создания заявки
            */
            System.out.println("<=====================================>");
            String name = input.askString("Введите имя заявки: ");
            String description = input.askString("Введите описание заявки: ");
            tracker.add(new Item(name, description));
            System.out.println("Заявка создана.");
        }
        public String info(){ // показывает пункт меню 0, здесь символы %s позволяют между методом и строкой вставить точку и пробел
            return String.format("<=====================================>\n%s. %s", this.key(),"Добавление новой заявки.");
        }
    }

    private class FindAll implements UserAction{ // внутренний класс списка заявок

        public int key(){
            return 1;
        }

        public void execute(Input input, Tracker tracker)throws IOException{ // реализация показа списка заявок

            if(tracker.findAll() == null){ // Если список заявок пуст, сообщаем об этом

                System.out.println("<=====================================>");
                System.out.println("Список заявок пуст.");
                System.out.println("<=====================================>");
            }else{ // иначе выводим на экран все заявки списком

                for(int count = 0; count < tracker.findAll().length; count++){ // так как заявки хранятся в массиве, то вывод на экран пунктов делаем через цикл

                    if(count == 0){
                        System.out.println("<=====================================>");
                    }
                    System.out.println("Имя заявки: " + tracker.findAll()[count].getName());
                    System.out.println("Опсание заявки: " + tracker.findAll()[count].getDescription());
                    System.out.println("Время создания заявки: " + tracker.findAll()[count].getCreate());
                    System.out.println("ID заявки: " + tracker.findAll()[count].getId());

                    if(count == tracker.findAll().length - 1){ // если список заявок закончился выводим разделитель

                        System.out.println("<=====================================>");
                    }else{ // иначе выводим разделитель, чтобы отделить заявки друг от друга

                        System.out.println("<------------------------------------->");
                    }
                }
            }
        }

        public String info() { // показывает пункт меню 1, здесь символы %s позволяют между методом и строкой вставить точку и пробел

            return String.format("%s. %s", this.key(), "Показ списка заявок.");
        }
    }

    private class FindByName implements UserAction { // реализация поиска заявки по имени

        public int key(){
            return 2;
        }

        public void execute(Input input, Tracker tracker) throws IOException {

            System.out.println("<=====================================>");
            String name = input.askString("Введите имя заявки: ");
            if(tracker.findByName(name) == null){ // эта проверка на случай если такой заявки нет

                System.out.println("<=====================================>");
                System.out.println("Заявка не найдена.");
            } else{ // так как метод findByName возвращает ссылку из массива на заявку, то мы можем на прямую к ней обращаться и выводить данные на экран

                System.out.println("<=====================================>");
                System.out.println("Время создания заявки: " + tracker.findByName(name).getCreate());
                System.out.println("Описание заявки: " + tracker.findByName(name).getDescription());
                System.out.println("ID заявки: " + tracker.findByName(name).getId());
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Поиск заявки по имени."); // вывод пункта меню 2 на экран, здесь символы %s позволяют между методом и строкой вставить точку и пробел
        }
    }

    private class FindById implements UserAction{ //реализация поиска заявки по ID

        public int key() {
            return 3;
        }

        public void execute(Input input, Tracker tracker) throws IOException {

            System.out.println("<=====================================>");
            String id = input.askString("Введите ID заявки.");
            if(tracker.findById(id) == null){ // проверка на случай отсутствия заявки

                System.out.println("<=====================================>");
                System.out.println("Заявка не найдена.");
            } else{ // так как метод findById возвращает ссылку на заявку, то мы можем обращаться к ней на прямую и выводить данные на экран

                System.out.println("<=====================================>");
                System.out.println("Имя заявки: " + tracker.findById(id).getName());
                System.out.println("Описание заявки: " + tracker.findById(id).getDescription());
                System.out.println("Время создания заявки: " + tracker.findById(id).getCreate());
            }
        }

        public String info() { // вывод на экран пункта меню 3, здесь символы %s позволяют между методом и строкой вставить точку и пробел
            return String.format("%s. %s", this.key(), "Поиск заявки по ID.");
        }


    }

    private class UpdateItem implements UserAction{ // реализация редактирования заявки

        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) throws IOException {

            System.out.println("<=====================================>");
            String id = input.askString("Введите ID заявки: "); // создаем переменную и присваиваем ей значение введенного ID, чтобы работать с методом findById

            if(tracker.findById(id) != null) { // проверка на наличие заявки с введенным ID

                /**
                 * если заявка найдена, то вызываем метод findById который возвращает ссылку на данную заявку
                 * благодаря этому мы используем сеттер класса заявки и меняем поля, сократив код до минимума
                 **/
                System.out.println("<=====================================>");
                tracker.findById(id).setName(input.askString("Введите новое имя заявки: "));
                System.out.println("<=====================================>");
                tracker.findById(id).setDescription(input.askString("Введите новое описание заявки: "));
                System.out.println("<=====================================>");
                tracker.findById(id).setCreate(input.askString("Введите время редактирования заявки: "));
                System.out.println("<=====================================>");
                System.out.println("Заявка изменена и сохранена.");
            } else { // если заявка не найдена то сообщаем об этом

                System.out.println("<=====================================>");
                System.out.println("Заявка не найдена.");
            }
        }

        public String info() { // показ пункта меню 4, здесь символы %s позволяют между методом и строкой вставить точку и пробел
            return String.format("%s. %s", this.key(), "Редактирование заявки.");
        }
    }

    private class DeleteItem implements UserAction {

        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) throws IOException {

            System.out.println("<=====================================>");
            tracker.delete(input.askString("Введите ID заявки: "));
            System.out.println("Заявка удалена.");
            System.out.println("<=====================================>");
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Удаление заявки.");
        }
    }

    private class Exit implements UserAction{

        /**
         * Выход из программы реализовал через дополнительное поле и геттер - сеттер
         * Геттер необходим для прерывания цикла do-while который обеспечивает непрерывную работу программы
         **/

        public int key() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) throws IOException {

            setExit(input.askString("Вы точно хотите выйти? (Да/Нет): "));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Выход.");
        }
    }


}
