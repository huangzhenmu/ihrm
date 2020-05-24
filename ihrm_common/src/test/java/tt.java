import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=tt.class)
public class tt {
    @Test
    public void ttt(){
        String protocol ="J1939";
        int bustype = 2;
        System.out.println(!"J1939".equalsIgnoreCase(protocol) && bustype == 1);
    }


    @Test
    public void test(){
        /*String pattern = "^[a-zA-Z0-9_]+$";
        String a = "abcd_3哈";
        System.out.println( Pattern.matches(pattern,a));*/

        String str = "BAUDRATE           500000 /* in Hz */";
        System.out.println(str.toLowerCase().contains("BAUDRATE"));

    }
}
