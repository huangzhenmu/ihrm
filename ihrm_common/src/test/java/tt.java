import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=tt.class)
public class tt {
    @Test
    public void ttt(){
        String protocol ="J1939";
        int bustype = 2;
        System.out.println(!"J1939".equalsIgnoreCase(protocol) && bustype == 1);
    }
}
