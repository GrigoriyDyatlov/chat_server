package ServerTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.Server.Log;

public class LogTests {
    @Test
public void getLogTest(){
        Log.getLogger().log("Hello world");

        Assertions.assertEquals("[Hello world]", Log.getLogger().getLog());
    }
}
