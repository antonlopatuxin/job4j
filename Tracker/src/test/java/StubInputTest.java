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

        Input input = new StubInput(new String[] {"0", "Anton", "Desc", "11:56", "6"}); // Создаем массив с последовательностью действий
        Tracker tracker = new Tracker(input);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("Anton"));
    }

    @Test
    public void updateTest() throws IOException{
        Item item = new Item("Anton", "Desc", "14:49"); // создаем заявку
        Input input = new StubInput(new String[] {"2", item.getId(), "Stepan", "Desc01", "14:55", "6"}); // создаем массив действий пользователя, item.getId() дает нам id заявки
        Tracker tracker = new Tracker(input);
        tracker.add(item);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("Stepan"));
    }

    @Test
    public void deleteTest() throws IOException {

        Input input = new StubInput(new String[] {"3", "1506241426732"});
        Tracker tracker = new Tracker(input);
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("список заявок пуст"));
    }
}
