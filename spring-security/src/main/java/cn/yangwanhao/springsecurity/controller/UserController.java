package cn.yangwanhao.springsecurity.controller;

import cn.yangwanhao.springsecurity.common.bean.User;
import cn.yangwanhao.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/user/{id}")
    @RolesAllowed(value = "ROLE_ADMIN")
    public User getUserById(@PathVariable("id") Integer id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        System.out.println(authorities.toString());
        return userService.getById(id);
    }

    @GetMapping("/list/user")
    @RolesAllowed(value = "ROLE_USER")
    public List<User> listAllUser() {
        return userService.listAll();
    }
}
