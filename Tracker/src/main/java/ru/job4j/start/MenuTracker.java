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
    private UserAction[] actions = new UserAction[100]; // массив для реализации меню
    private String exit = null; //переменная для реализации выхода из программы
    private int position = 0;

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

        this.actions[position++] = new AddItem(0, "Добавление новой заявки.");
        this.actions[position++] = new FindAll(1,"Показ списка заявок.");
        this.actions[position++] = new FindByName(2, "Поиск заявки по имени.");
        this.actions[position++] = new FindById(3, "Поиск заявки по ID.");
        this.actions[position++] = new UpdateItem(4, "Редактирование заявки.");
        this.actions[position++] = new DeleteItem(5, "Удаление заявки.");
        this.actions[position++] = new Exit(6, "Выход.\n\"<=====================================>");
    }

    public void addAction(UserAction action){ //Данный метод позволяет добавлять новые действия

        this.actions[position++] = action;
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

    private class AddItem extends BaseAction{ //внутренний класс добавления заявок

        public AddItem(int key, String menuButton){ // конструктор в котором мы вызываем конструкор родительского класса
            super(key, menuButton);
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
    }

    private class FindAll extends BaseAction{ // внутренний класс списка заявок

        public FindAll(int key, String menuButton){ // конструктор в котором мы вызываем конструкор родительского класса
            super(key, menuButton);
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
    }

    private class FindByName extends BaseAction{ // реализация поиска заявки по имени

        public FindByName(int key, String menuButton){ // конструктор в котором мы вызываем конструкор родительского класса
            super(key, menuButton);
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
    }

    private class FindById extends BaseAction{ //реализация поиска заявки по ID

        public FindById(int key, String menuButton){ // конструктор в котором мы вызываем конструкор родительского класса
            super(key, menuButton);
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
    }

    private class UpdateItem extends BaseAction{ // реализация редактирования заявки

        public UpdateItem(int key, String menuButton){ // конструктор в котором мы вызываем конструкор родительского класса
            super(key, menuButton);
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
    }

    private class DeleteItem extends BaseAction {

        public DeleteItem(int key, String menuButton){ // конструктор в котором мы вызываем конструкор родительского класса
            super(key,menuButton);
        }
        public void execute(Input input, Tracker tracker) throws IOException {

            System.out.println("<=====================================>");
            tracker.delete(input.askString("Введите ID заявки: "));
            System.out.println("Заявка удалена.");
            System.out.println("<=====================================>");
        }
    }

    private class Exit extends BaseAction{

        /**
         * Выход из программы реализовал через дополнительное поле и геттер - сеттер
         * Геттер необходим для прерывания цикла do-while который обеспечивает непрерывную работу программы
         **/

        public Exit(int key, String menuButton){ // конструктор в котором мы вызываем конструкор родительского класса
            super(key, menuButton);
        }
        public void execute(Input input, Tracker tracker) throws IOException {

            setExit(input.askString("Вы точно хотите выйти? (Да/Нет): "));
        }
    }
}
