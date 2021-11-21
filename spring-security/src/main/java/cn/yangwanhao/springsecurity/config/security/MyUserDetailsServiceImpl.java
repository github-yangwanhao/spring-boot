package cn.yangwanhao.springsecurity.config.security;
import cn.yangwanhao.springsecurity.common.ErrorCodeEnum;
import cn.yangwanhao.springsecurity.common.bean.User;
import cn.yangwanhao.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByNamePassword(new User(null, username, null, null));
        if (user == null) {
            throw new UsernameNotFoundException(ErrorCodeEnum.U1009001.msg());
        }
        String role;
        if ("user1".equals(user.getName())) {
            role = "ROLE_ADMIN";
        } else {
            role = "ROLE_USER";
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), passwordEncoder.encode(user.getPassword()), AuthorityUtils.commaSeparatedStringToAuthorityList(role));
    }

}
