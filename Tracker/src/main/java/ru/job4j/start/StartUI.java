package ru.job4j.start;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.io.IOException;

public class StartUI {

    private Input input;
    private Tracker tracker;
    private int switchValue;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() throws IOException {

        while(switchValue != 6){

            switchValue = input.askInt("Введите кнопку меню:");

            switch(switchValue){

                case 0:
                    String nameItem =  input.askString("Введите имя заявки: "); // запрашиваем имя заявки
                    String descItem = input.askString("Введите описание заявки: "); // запрашиваем описание заявки
                    String timeCreate = input.askString("Введите время создания заявки: "); // пока вводим вручную поэтому и тип стринг

                    tracker.add(new Item(nameItem, descItem, timeCreate));
                    break;

                case 1:
                    System.out.println( tracker.findAll());
                    break;

                case 2:
                    String id = input.askString("Введите id заявки: ");
                    tracker.update(id);
                    break;

                case 3:
                    String idDelete = input.askString("Введите id заявки: ");
                    tracker.delete(idDelete);

                    break;

                case 4:
                    String findId = input.askString("Введите id заявки: ");
                    Item item = tracker.findById(findId);

                    break;

                case 5:
                    String name = input.askString("Введите имя заявки: ");
                    Item itemName = tracker.findByName(name);

                    break;
            }
        }


        }

    public static void main(String[] args) throws IOException {
        Input input = new ConsoleInput();
        Menu start = new Menu(new Tracker(input), input);
        start.menuAction();
    }
}