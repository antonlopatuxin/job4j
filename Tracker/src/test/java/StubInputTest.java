import org.junit.Test;
import ru.job4j.start.Input;
import ru.job4j.start.StartUI;
import ru.job4j.start.StubInput;
import ru.job4j.tracker.Tracker;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StubInputTest {

    @Test
    public void addTest(){
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"0", "Anton", "Desc", "6"}); // Создаем массив с последовательностью действий
        new StartUI(input).init();
        assertThat(tracker.findAll()[0].getName(), is("Anton"));
    }
}
