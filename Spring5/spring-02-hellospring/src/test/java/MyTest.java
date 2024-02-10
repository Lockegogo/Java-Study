import com.locke.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        // 获取 Spring 的上下文对象
        // 解析 beans.xml 文件，生成管理相应的 Bean 对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 我们的对象现在都在 Spring 中的管理了，我们要使用，直接去里面取出来就可以
        // getBean: 参数即为 spring 配置文件中的 bean 的 id
        Hello hello = (Hello) context.getBean("hello");

        System.out.println(hello.toString());

    }

}
