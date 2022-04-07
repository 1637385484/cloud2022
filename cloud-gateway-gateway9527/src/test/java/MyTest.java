import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

/**
 * @author LDW
 * @date 2022/4/5 17:31
 */
public class MyTest {
    @Test
    public void test() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
