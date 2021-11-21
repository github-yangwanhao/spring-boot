package cn.yangwanhao.springsecurity.service;

import cn.yangwanhao.springsecurity.common.bean.User;
import cn.yangwanhao.springsecurity.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public User getByNamePassword(User user) {
        return userDao.getByUserNamePassword(user);
    }

    @Override
    public List<User> listAll() {
        return userDao.listAll();
    }
}
