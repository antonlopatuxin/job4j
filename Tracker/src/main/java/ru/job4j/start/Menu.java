package ru.job4j.start;

import java.io.IOException;
import ru.job4j.tracker.*;

public class Menu {

    Tracker tracker = new Tracker();
    private int switchPar; // параметр свитч
    ConsoleInput consoleInput = new ConsoleInput();


    // вывод меню на экран
    private void menu(){
        System.out.println("0. Добавление новой заявки.");
        System.out.println("1. Показать все заявки.");
        System.out.println("2. Редактирование заявки.");
        System.out.println("3. Удаление заявки.");
        System.out.println("4. Поиск заявки по id.");
        System.out.println("5. Поиск заявки по имени.");
        System.out.println("6. Выход.");

    }
    // реализация работы меню
    public void menuAction() throws IOException {

        while(switchPar != 6) { // реализация выхода из программы

            this.menu();
            switchPar = consoleInput.askInt("Введите кнопку меню: ");

            switch (switchPar) {

                case 0:
                    String nameItem =  consoleInput.askString("Введите имя заявки: "); // запрашиваем имя заявки
                    String descItem = consoleInput.askString("Введите описание заявки: "); // запрашиваем описание заявки
                    String timeCreate = consoleInput.askString("Введите время создания заявки: "); // пока вводим вручную поэтому и тип стринг

                    tracker.add(new Item(nameItem, descItem, timeCreate));
                    break;

                case 1:
                    System.out.println( tracker.findAll());
                    break;

                case 2:
                    String id = consoleInput.askString("Введите id заявки: ");
                    tracker.update(id);
                    break;

                case 3:
                    String idDelete = consoleInput.askString("Введите id заявки: ");
                    tracker.delete(idDelete);

                    System.out.println("Заявка удалена.");
                    break;

                case 4:
                    String findId = consoleInput.askString("Введите id заявки: ");
                    Item item = tracker.findById(findId);

                    System.out.println(item.getCreate());
                    System.out.println(item.getName());
                    System.out.println(item.getDescription());

                    break;

                case 5:
                    String name = consoleInput.askString("Введите имя заявки: ");
                    Item itemName = tracker.findByName(name);

                    System.out.println((itemName.getCreate()));
                    System.out.println(itemName.getId());
                    System.out.println(itemName.getDescription());

                    break;

            }
        }
    }

}
