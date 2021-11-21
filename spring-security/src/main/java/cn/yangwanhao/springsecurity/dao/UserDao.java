package cn.yangwanhao.springsecurity.dao;

import cn.yangwanhao.springsecurity.common.bean.User;

import java.util.List;

public interface UserDao {

    User getById(Integer id);

    User getByUserNamePassword(User user);

    List<User> listAll();
}
