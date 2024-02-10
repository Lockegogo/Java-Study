import com.locke.dao.UserDaoImpl;
import com.locke.dao.UserDaoMysqlImpl;
import com.locke.service.UserServiceImpl;

public class MyTest {
    public static void main(String[] args) {
        // 用户实际调用的是业务层， dao层他们不需要接触
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(new UserDaoImpl());
        // 会实现 Dao 层的方法
        userService.getUser();


    }
}
