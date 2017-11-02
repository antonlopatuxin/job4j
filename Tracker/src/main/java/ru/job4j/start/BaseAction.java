package ru.job4j.start;

abstract public class BaseAction implements UserAction {
private int key;
private String menuButton;

    public BaseAction(int key, String menuButton){ // конструктор который избавляет от использования в каждом классе наследнике повторяющихся методов int key() & String info()

        this.key = key;
        this.menuButton = menuButton;
}

    public int key() { // по сути это геттер
        return this.key;
    }

    public String info(){
        return String.format("%s.%s", this.key, this.menuButton);
} // здесь символ %s дает нам точку между  полями и пробел

}
