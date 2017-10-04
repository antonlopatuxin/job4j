package ru.job4j.start;

import com.sun.deploy.trace.Trace;
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
                    for(int count = 0; count < tracker.findAll().length; count++){
                        System.out.println("Имя заявки: " + tracker.findAll()[count].getName());
                        System.out.println("Описание заявки: " + tracker.findAll()[count].getDescription());
                        System.out.println("Время создания заявки: " + tracker.findAll()[count].getCreate());
                        System.out.println("ID заявки: " + tracker.findAll()[count].getId());
                       if(count == tracker.findAll().length - 1){
                           System.out.println("<==================================================>");
                       }else{
                           System.out.println("<-------------------------------------------------->");
                       }
                    }
                    break;

                case 2:
                    String[] arrayUpdate = new String[10];
                    arrayUpdate[0] = input.askString("Введите id заявки: ");
                    Item itemUpdate = tracker.findById(arrayUpdate[0]);
                    if(itemUpdate.getId().equals(arrayUpdate[0])){
                        arrayUpdate[1] = input.askString("Введите новое имя заявки:");
                        arrayUpdate[2] = input.askString("Введите новое описание заявки:");
                        arrayUpdate[3] = input.askString("Введите время редактирования заявки:");
                        tracker.update(itemUpdate, arrayUpdate);
                        System.out.println("Заявка изменена и сохранена ");
                        System.out.println("<==================================================>");
                    }else{
                        System.out.println("Заявка не найдена");
                    }
                    break;

                case 3:
                    String idDelete = input.askString("Введите id заявки: ");
                    tracker.delete(idDelete);

                    break;

                case 4:
                    String findId = input.askString("Введите id заявки: ");
                    tracker.findById(findId);

                    break;

                case 5:
                    String name = input.askString("Введите имя заявки: ");
                    tracker.findByName(name);

                    break;
            }
        }


        }

    public static void main(String[] args) throws IOException {
        Input input = new ConsoleInput();
        Menu start = new Menu(new Tracker(), input);
        start.menuAction();
    }
}