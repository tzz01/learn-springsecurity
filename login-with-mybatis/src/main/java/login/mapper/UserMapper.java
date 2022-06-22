package login.mapper;

import org.apache.ibatis.annotations.*;
import login.po.Role;
import login.po.User;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface UserMapper {

    @Result(column = "name_zw", property = "nameZw")
    @Select("SELECT id, name, name_zw FROM role " +
            "WHERE id " +
            "in (SELECT role_id FROM user_roles WHERE user_id = #{user_id})")
    List<Role> getRolesOfUser(@Param("user_id") Long userId);

    @Results(id = "UserMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "account_non_expired", property = "accountNonExpired"),
            @Result(column = "account_non_locked", property = "accountNonLocked"),
            @Result(column = "credentials_non_expired", property = "credentialsNonExpired"),
            @Result(column = "enabled", property = "enabled"),
            @Result(column = "id", property = "roles", javaType = List.class,
            many = @Many(select = "login.mapper.RoleMapper.getRolesOfUser", fetchType = FetchType.EAGER)),
    })
    @Select("SELECT id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled " +
            "FROM user WHERE username = #{username}")
    User findUserDetailsByUsername(@Param("username") String username);

    @Select("SELECT 1 FROM user WHERE username = #{username} limit 1")
    Integer userIsExist(@Param("username") String username);

}
