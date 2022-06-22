package login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import login.mapper.UserMapper;
import login.po.User;

@Service
public class DaoUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserDetailsByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
//        user.setRoles(userMapper.getRolesOfUser(user.getId()));
        return user;
    }
}
