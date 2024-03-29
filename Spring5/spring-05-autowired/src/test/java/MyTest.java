import com.locke.pojo.Cat;
import com.locke.pojo.People;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        People people = context.getBean("people", People.class);
        people.getDog().shout();

        Cat cat = context.getBean("cat", Cat.class);
        System.out.println(cat.name);
    }




}
