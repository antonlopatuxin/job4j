package ru.job4j.tracker;

import ru.job4j.start.ConsoleInput;
import ru.job4j.start.Input;

import java.io.IOException;
import java.util.*;

public class Tracker {

    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RN = new Random(); // генерация случайного числа
    private Input input;

    // добавление заявки
    public Item add(Item item){

        item.setId(this.generatedId());
        this.items[position++] = item; // position++ означает, что при каждом вызове функции мы смещаемся на ячейку в массиве
        return item;
    }

    // получение заявки по id
    public Item findById(String id){

        Item result = null;

        for(Item item : this.items){

            if(item != null && item.getId().equals(id)){
                /* если переменная не равна null(такое бывает, когда не заполнен массив)
                то берем id из объекта item и сравниваем с входящим параметром
                 */
                result = item;
                break;
            }
        }
        return result;
    }

    private String generatedId(){
        /* получаем псевдорандомное число типа int, и чтобы оно не повторялось добавляем текущее время,
        метод valueOf преобразует числовое значение в строку
        */
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    // получение всего списка заявок
    public Item[] findAll(){

        Item[] result = new Item[this.position]; // создаем массив с текущим количеством элементов в основном массиве

        if(this.position == 0){ // проверяем пустой ли массив
           // String test = "null";
            System.out.println("Список заявок пуст");
            System.out.println();
        } else { // Если не пустой то заводим цикл
            for (int count = 0; count != this.position; count++) {

                result[count] = this.items[count]; // заполняем массив текущими значениями

                // Доработал метод, теперь он выводит данные каждой заявки
                System.out.println(result[count].getCreate());
                System.out.println(result[count].getName());
                System.out.println(result[count].getDescription());
                System.out.println(result[count].getId());
                System.out.println();
            }
        }
        return result;
    }

    // получение заявки по имени
    public Item findByName(String key){

        Item result = null;

        for(Item item : this.items){

            if(item != null && item.getName().equals(key)){ // ищем введеное имя в массиве заявок

                result = item;
                break;
            }
        }
        return result;
    }

    // редактирование заявки
    // модифицировал метод для адекватного редактирования
    public void update(String id) throws IOException {

        for(Item array : this.items){

            int count = 0; // переменная счетчика для подсчета ячейки массива

            if(array != null && array.getId().equals(id)){

                System.out.println("Заявка найдена.");

                String name =  this.input.askString("Ведите новое имя заявки: ");
                items[count].setName(name); // меняем имя заявки

                String description = this.input.askString("Введите новое описание заявки: ");
                items[count].setDescription(description); // меняем описание заявки

                String time = this.input.askString("Введите время изменения заявки: ");
                items[count].setCreate(time);

                System.out.println("Изменения сохранены.");
            }

            count++;
        }
    }

    // удаление заявки по ее id
    public void delete(String id){

        for(Item temp : this.items){

            int count = 0;

            if(temp != null && temp.getId().equals(id)){

                this.items[count] = null;
            }

            count++;
        }
    }

}
