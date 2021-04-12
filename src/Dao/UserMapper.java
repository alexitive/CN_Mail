package Dao;

import Bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public List<User> selectAllFriendById(@Param("id") int id);

    public List<User> selectAllUser();

    public User selectUserByUsername(String username);

    public void insertUser(User user);

    public void deleteUser(int id);

    /**
     *  对于权限author的更新，0为无权限，1为只能发不能收，2为只能收不能发，3为收发均可
     * @param user
     */
    public void updateUser(User user);

    public User selectUserById(int id);

}
