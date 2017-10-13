package ru.job4j.start;

import java.io.IOException;
import ru.job4j.tracker.*;

public class Menu {

    private Tracker tracker;
    private int switchPar; // параметр свитч
    private Input input;
    private final int AddItem = 0;
    private final int FindAllItems = 1;
    private final int UpdateItem = 2;
    private final int DeleteItem = 3;
    private final int FindByIdItem = 4;
    private final int FindByNameItem = 5;

    public Menu(Tracker tracker, Input input){
        this.tracker = tracker;
        this.input = input;
    }


    // вывод меню на экран
    private void menu() {
        System.out.println("0. Добавление новой заявки.");
        System.out.println("1. Показать все заявки.");
        System.out.println("2. Редактирование заявки.");
        System.out.println("3. Удаление заявки.");
        System.out.println("4. Поиск заявки по id.");
        System.out.println("5. Поиск заявки по имени.");
        System.out.println("6. Выход.");
    }

        private void addItem()throws IOException{
        String nameItem =  input.askString("Введите имя заявки: "); // запрашиваем имя заявки
        String descItem = input.askString("Введите описание заявки: "); // запрашиваем описание заявки
        String timeCreate = input.askString("Введите время создания заявки: "); // пока вводим вручную поэтому и тип стринг
        tracker.add(new Item(nameItem, descItem, timeCreate));
        System.out.println("Заявка добавлена и сохранена");
        System.out.println("<===================================================>");
    }

    private void findAllItems()throws IOException{
        if(tracker.findAll() == null){
            System.out.println("Список заявок пуст");
            System.out.println("<=====================================================>");
        }else {
            for(int count = 0; count < tracker.findAll().length; count++){
                System.out.println("Имя заявки: " + tracker.findAll()[count].getName());
                System.out.println("Описание заявки: " + tracker.findAll()[count].getDescription());
                System.out.println("Время создания заявки: " + tracker.findAll()[count].getCreate());
                System.out.println("ID заявки: " + tracker.findAll()[count].getId());
                if (count == tracker.findAll().length - 1) {
                    System.out.println("<=====================================================>");
                } else {
                    System.out.println("<------------------------------------------------------>");
                }
            }
        }
    }

    private void updateItem()throws IOException{
        String[] arrayUp = new String[10];
        arrayUp[0] = input.askString("Введите id заявки: ");
        Item itemUp = tracker.findById(arrayUp[0]);
        if(itemUp.getId().equals(arrayUp[0])){
            arrayUp[1] = input.askString("Введите новое имя заявки:");
            arrayUp[2] = input.askString("Введите новое описание заявки:");
            arrayUp[3] = input.askString("Введите время редактирования заявки:");
            tracker.update(itemUp, arrayUp);
            System.out.println("Заявка изменена и сохранена");
            System.out.println("<===========================================================>");
        }else{
            System.out.println("Заявка не найдена");
        }
    }

    private void deleteItem()throws IOException{
        String idDelete = input.askString("Введите id заявки: ");
        tracker.delete(idDelete);

        System.out.println("Заявка удалена.");
    }

    private void findByIdItem()throws IOException{
        String findId = input.askString("Введите id заявки: ");
        Item item = tracker.findById(findId);

        System.out.println(item.getCreate());
        System.out.println(item.getName());
        System.out.println(item.getDescription());
    }

    private void findByNameItem()throws IOException{
        String name = input.askString("Введите имя заявки: ");
        Item itemName = tracker.findByName(name);

        System.out.println((itemName.getCreate()));
        System.out.println(itemName.getId());
        System.out.println(itemName.getDescription());
    }

    // реализация работы меню
    public void menuAction() throws IOException {

        while(switchPar != 6) { // реализация выхода из программы

            this.menu();
            switchPar = input.askInt("Введите кнопку меню: ");
            System.out.println("<===================================================>");

            switch (switchPar) {

                case AddItem:
                    addItem();
                    break;

                case FindAllItems:
                    findAllItems();
                    break;

                case UpdateItem:
                   updateItem();
                   break;

                case DeleteItem:
                    deleteItem();
                    break;

                case FindByIdItem:
                    findByIdItem();
                    break;

                case FindByNameItem:
                    findByNameItem();
                    break;

            }
        }
    }

}
