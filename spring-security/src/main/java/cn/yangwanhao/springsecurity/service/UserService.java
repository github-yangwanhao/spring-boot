package cn.yangwanhao.springsecurity.service;


import cn.yangwanhao.springsecurity.common.bean.User;

import java.util.List;

public interface UserService {

    User getById(Integer id);

    User getByNamePassword(User user);

    List<User> listAll();
}
