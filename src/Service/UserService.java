package Service;

import Bean.Friend;
import Bean.User;
import Dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    public boolean  registUser(User user){

        return true;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public boolean  removeUser(int id){

        return true;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public boolean  changeUserInformation(User user){

        return true;
    }

    /**
     * 查询一个用户
     * @param username
     * @return
     */
    public User queryUserByUsername(String username){

        return null;
    }


    /**
     * 获取一个用户的所有朋友
     * @param id
     * @return
     */
    public List<User> queryOneUserFriend(int id){
        return null;
    }

    /**
     * 管理员批量查询用户
     * @return
     */
    public List<User> queryAllUser(){

        return null;
    }

}
