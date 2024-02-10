import com.locke.mapper.UserMapper;
import com.locke.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test2(){
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper mapper = (UserMapper) context.getBean("userMapper2");
        for (User user : mapper.selectUser()) {
            System.out.println(user);
        }
    }
}
