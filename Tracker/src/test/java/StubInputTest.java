import org.junit.Test;
import ru.job4j.start.*;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StubInputTest {

    @Test
    public void addTest() throws IOException { // проверка создания заявки

        Input input = new StubInput(new String[] {"0", "Anton", "Desc", "6", "Да"}); // Создаем массив с последовательностью действий
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("Anton")); // заодно проверка метода findAll
    }

    @Test
    public void updateTest() throws IOException{
        Item item = new Item("Anton", "Desc"); // создаем заявку
        Tracker tracker = new Tracker();
        tracker.add(item);
        Input input = new StubInput(new String[] {"2", item.getId(), "Stepan", "Desc01", "6", "Да"}); // создаем массив действий пользователя, item.getId() дает нам id заявки
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("Stepan"));
    }

    @Test
    public void deleteTest() throws IOException {
        Item item = new Item("Anton", "Desc"); // создаем заявку
        Tracker tracker = new Tracker();
        tracker.add(item);
        Input input = new StubInput(new String[] {"3", item.getId(), "6", "Да"});
        Item[] expected = new Item[] {null};
        new StartUI(input, tracker).init();
        Item[] result = tracker.findAll();
        assertThat(result, is(expected));
    }

    @Test
    public void findByIdTest(){
        Item item = new Item("Anton", "Desc"); // создаем заявку
        Tracker tracker = new Tracker();
        tracker.add(item);
        Input input = new StubInput(new String[]{"4", item.getId(), "6", "Да"});
        String result = item.getName();
        new StartUI(input, tracker);
        assertThat(result, is("Anton"));
    }

    @Test
    public void findByName(){
        Item item = new Item("Anton", "Desc"); // создаем заявку
        Tracker tracker = new Tracker();
        tracker.add(item);
        Input input = new StubInput(new String[]{"5", item.getId(), "6", "Да"});
        String result = item.getName();
        new StartUI(input, tracker);
        assertThat(result, is("Anton"));
    }
}
