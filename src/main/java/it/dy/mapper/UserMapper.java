package it.dy.mapper;

import it.dy.entity.User;

import java.util.List;

/**
 * @author w5489
 * @Description
 * @date 2022/7/28 3:41
 */
public interface UserMapper {

    int insert(User user);


    List<User> getList();


    int insertBatch(List<User> userList);
}
