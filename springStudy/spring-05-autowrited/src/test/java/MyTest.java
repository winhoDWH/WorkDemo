
import com.dwh.Person;
import com.dwh.annotation.Home;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Home p = (Home) ApplicationContext.getBean("h1");
        p.getChild().shot();
        p.getFather().shot();
        p.getMother().shot();
    }
}
