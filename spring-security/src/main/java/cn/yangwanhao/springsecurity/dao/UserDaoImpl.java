package cn.yangwanhao.springsecurity.dao;

import cn.yangwanhao.springsecurity.common.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private static List<User> users = new ArrayList<>();
    static {
        for (int i = 1; i <= 100; i++) {
            User user = new User();
            user.setId(i);
            user.setName("user" + i);
            user.setPassword("111111");
            user.setAge(i);
            users.add(user);
        }
    }

    @Override
    public User getById(Integer id) {
        return users.get(id-1);
    }

    @Override
    public User getByUserNamePassword(User user) {
        for (User user1 : users) {
            if (user1.getName().equals(user.getName())) {
                return user1;
            }
        }
        return null;
    }

    @Override
    public List<User> listAll() {
        return users;
    }
}
