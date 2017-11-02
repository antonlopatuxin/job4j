package ru.job4j.start;

abstract public class BaseAction implements UserAction {
private int key;
private String menuButton;

    public BaseAction(int key, String menuButton){

        this.key = key;
        this.menuButton = menuButton;
}


}
