import com.dao.userDao;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class mybatisCRUDTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private userDao userdao;

    /**
     * 查找全部用户
     */
    @Test
    public void testFindAll(){
        List<User> users = userdao.findAll();
        System.out.println(users);
    }

    /**
     * 根据id查找用户
     */
    @Test
    public void testFindOne(){
        User user = userdao.findById(2);
        System.out.println(user);
    }

    /**
     * 保存用户
     */
    @Test
    public void testSaveUser(){
        User user1 = new User();
        user1.setName("zl");
        user1.setMoney(1000d);

        userdao.saveUser(user1);
        System.out.println("增加一个用户" + user1);
    }

    /**
     * 修改用户
     */
    @Test
    public void updateUser(){
        User user = userdao.findById(9);
        user.setName("zls");
        user.setMoney(2000d);

        int res = userdao.updateUser(user);

        System.out.println("修改"+res+"个用户" + user);
    }

    /**
     * 根据id删除用户
     */
    @Test
    public void testdeleteOne(){
        int res = userdao.deleteUser(9);
        System.out.println(res);
    }


    /**
     * 根据用户名模糊查询用户
     */
    @Test
    public void testFindByName(){
        List<User> users = userdao.findByName("%z%");
        for (User user : users)
        {
            System.out.println(user);
        }
    }

    /**
     * 查询总记录条数
     */
    @Test
    public void testFindTotal(){
        int res = userdao.findTotal();
        System.out.println(res);
    }




    @Before //测试方法之前执行
    public void init()throws Exception{
//        1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
//        2.创建构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        3.创建SQLSession工厂对象
        factory = builder.build(in);
//        4.创建sqlSession对象
        session = factory.openSession();
//        5.创建dao的代理对象
        userdao = session.getMapper(userDao.class);
    }

    @After //测试方法之后执行
    public void destory() throws Exception {
        session.commit();
        session.close();
        in.close();
    }
}
