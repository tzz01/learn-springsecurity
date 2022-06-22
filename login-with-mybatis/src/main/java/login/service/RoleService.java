package login.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import login.mapper.RoleMapper;
import login.po.Role;

@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public void addRole(Role role) {
        roleMapper.addRole(role);
    }

    public Role getRoleByName(String roleName) {
        return roleMapper.getRoleByName(roleName);
    }


}
