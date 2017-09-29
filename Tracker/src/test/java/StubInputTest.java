import org.junit.Test;
import ru.job4j.start.*;
import ru.job4j.tracker.Tracker;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StubInputTest {

    @Test
    public void addTest(){
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"0", "Anton", "Desc", "-01", "6"}); // Создаем массив с последовательностью действий
        new Menu(tracker,input);
        assertThat(tracker.findAll()[0].getName(), is("Anton"));
    }
}
