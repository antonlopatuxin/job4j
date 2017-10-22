package ru.job4j.tracker;

import ru.job4j.start.ConsoleInput;
import ru.job4j.start.Input;

import java.io.IOException;
import java.util.*;

public class Tracker {

    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RN = new Random(); // генерация случайного числа

    // добавление заявки
    public Item add(Item item){

        item.setId(this.generatedId());
        item.setCreate(this.generatedTime());
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

    private String generatedTime(){ // Метод генерации времени создания заявки

        Date date = new Date();
        return date.toString();
    }

    private String generatedId(){
        /* получаем псевдорандомное число типа int, и чтобы оно не повторялось добавляем текущее время,
        метод valueOf преобразует числовое значение в строку
        */
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    // получение всего списка заявок
    public Item[] findAll(){

        Item[] result = new Item[this.position]; // создаем массив для записи туда ссылок на заявки

            for (int count = 0; count != this.position; count++) {

                if(this.items[count] != null) { // проверяем есть ли в массиве ссылки

                    result[count] = this.items[count];// заполняем массив ссылками на заявки

                } else{ // иначе массиву присваиваем нулл

                    result = null;
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
    public void update(Item item, String[] arrayUpdate) throws IOException {
        item.setName(arrayUpdate[1]);
        item.setDescription(arrayUpdate[2]);
        item.setCreate(arrayUpdate[3]);
    }

    // удаление заявки по ее id
    public void delete(String id){

        for(int count = 0; count < this.items.length; count++){

            if(id.equals(this.items[count].getId())){

                this.items[count] = null;
                break;
            }
        }
    }

}
