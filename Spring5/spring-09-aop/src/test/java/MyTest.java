import com.locke.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 注意：动态代理代理的是接口
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
}
