package login.mapper;

import org.apache.ibatis.annotations.*;
import login.po.Role;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("INSERT INTO role (name, name_zw) VALUES (#{role.name}, #{role.nameZw})")
    void addRole(@Param("role") Role role);

    @Result(column = "name_zw", property = "nameZw")
    @Select("SELECT id, name, name_zw FROM role WHERE name = #{name}")
    Role getRoleByName(@Param("name") String name);

    @Result(column = "name_zw", property = "nameZw")
    @Select("SELECT id, name, name_zw FROM role WHERE id = #{id}")
    Role getRoleById(@Param("id")String id);

    @Result(column = "name_zw", property = "nameZw")
    @Select("SELECT id, name, name_zw FROM role " +
            "WHERE id in " +
            "(SELECT role_id FROM user_roles WHERE user_id = #{userId})")
    List<Role> getRolesOfUser(@Param("userId") Long userId);
}
