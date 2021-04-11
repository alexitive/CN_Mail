package Service;

import Bean.Friend;
import Bean.User;
import Dao.FriendMapper;
import Dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/Spring-Config.xml");
    /**
     * 注册用户
     * @param user
     * @return
     */
    public boolean  registUser(User user){
        SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
        //首先查看是否存在相同用户名
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User isExist = userMapper.selectUserByUsername(user.getUsername());
        if(isExist != null)
             return false;
        try {
            userMapper.insertUser(user);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public boolean  removeUser(int id){

        try{
        SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
        sqlSession.getMapper(UserMapper.class).deleteUser(id);
        sqlSession.getMapper(FriendMapper.class).deleteFriendCause(id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public boolean  changeUserInformation(User user){

        SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User isExist = userMapper.selectUserByUsername(user.getUsername());
        if(isExist != null)
            return false;
        try {
            userMapper.updateUser(user);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;

    }

    /**
     * 查询一个用户
     * @param username
     * @return
     */
    public User queryUserByUsername(String username){

        User result = null;
        try{
            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            result = sqlSession.getMapper(UserMapper.class).selectUserByUsername(username);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return result;
    }


    /**
     * 获取一个用户的所有朋友
     * @param id
     * @return
     */
    public List<User> queryOneUserFriend(int id){

        List<User> result = null;
        try{
            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            result = sqlSession.getMapper(UserMapper.class).selectAllFriendById(id);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return result;

    }

    /**
     * 管理员批量查询用户
     * @return
     */
    public List<User> queryAllUser(){

        List<User> result = null;
        try{
            SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
            result = sqlSession.getMapper(UserMapper.class).selectAllUser();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return result;
    }

}
